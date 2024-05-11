package karuna.karuna_backend.content.domain;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import jakarta.transaction.Transactional;
import karuna.karuna_backend.content.dto.ContentDTO;
import karuna.karuna_backend.content.dto.MassContentDto;
import karuna.karuna_backend.content.dto.MassContentWrapper;
import karuna.karuna_backend.content.dto.MassContentWrapperRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {

    private static final String NOT_EXISTED = "NOT_EXISTED";

    private final ContentRepository contentRepository;
    private final LoadingCache<String, ContentDTO> cache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build(this::getContentByPageFromDatabase);

    public ContentDTO getContentByPage(String page) {
        return cache.get(page);
    }

    private ContentDTO getContentByPageFromDatabase(String page) {
        List<Content> pages = contentRepository.findByPageIgnoreCaseOrPageNull(page);
        HashMap<String, String> allContent = new HashMap<>();
        pages.forEach(singlePage -> {
            allContent.put(singlePage.getKey(), singlePage.getValuePl());
        });
        return convertToDTO(allContent);
    }


    public MassContentWrapper massUpdateContent(MassContentWrapperRequest massContentWrapperRequest) {
        return massContentOperation(massContentWrapperRequest, this::filterNotExistedInDatabaseAndUpdatedExisted);
    }

    public MassContentWrapper massAddContent(MassContentWrapperRequest massContentWrapperRequest){
        return massContentOperation(massContentWrapperRequest, this::addContentIfNotInDb);
    }

    private MassContentWrapper massContentOperation(MassContentWrapperRequest request, Predicate<MassContentDto> predicate){
        //TODO: Sanitize inputs for XSS
        Map<Boolean, List<MassContentDto>> partitionedContent = request.contents().stream()
                .collect(Collectors.partitioningBy(predicate));
        List<MassContentDto> validContent = partitionedContent.get(true);
        List<MassContentDto> invalidContent = partitionedContent.get(false);
        return new MassContentWrapper(validContent, invalidContent);
    }

    private boolean addContentIfNotInDb(MassContentDto massContentDto) {
        //TODO: Consider if batching would not be a better approach
        return contentRepository.getByPageAndKey(massContentDto.page(), massContentDto.key())
                .map(content -> false)
                .orElseGet(() -> {
                    Content newContent = mapDtoToContent(massContentDto);
                    contentRepository.save(newContent);
                    return true;
                });
    }

    @Transactional
    private boolean filterNotExistedInDatabaseAndUpdatedExisted(MassContentDto massContentDto) {
        //TODO: Consider if batching would not be a better approach
        return contentRepository.getByPageAndKey(massContentDto.page(), massContentDto.key())
                .map(content -> {
                    content.setValuePl(massContentDto.valuePl());
                    contentRepository.save(content);
                    return true;
                }).orElse(false);
    }

    private Content mapDtoToContent(MassContentDto dto){
        return Content.builder()
                .page(dto.page())
                .key(dto.key())
                .valuePl(dto.valuePl())
                .build();
    }

    private ContentDTO convertToDTO(HashMap<String, String> contents) {
        ContentDTO contentDTO = new ContentDTO(contents);
        return contentDTO;
    }
}

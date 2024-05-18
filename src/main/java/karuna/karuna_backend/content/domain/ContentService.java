package karuna.karuna_backend.content.domain;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import karuna.karuna_backend.content.dto.ContentDTO;
import karuna.karuna_backend.content.dto.MassContentDto;
import karuna.karuna_backend.content.dto.MassContentWrapper;
import karuna.karuna_backend.content.dto.MassContentWrapperRequest;
import karuna.karuna_backend.exception.database.DatabaseIntegrityException;
import karuna.karuna_backend.exception.keys.DataIntegrityErrorKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {

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
        pages.forEach(singlePage -> allContent.put(singlePage.getKey(), singlePage.getValuePl()));
        return ContentMapper.mapToDto(allContent);
    }


    public MassContentWrapper massUpdateContent(MassContentWrapperRequest massContentWrapperRequest) {
        return massContentOperation(massContentWrapperRequest, this::filterToUpdate);
    }

    public MassContentWrapper massAddContent(MassContentWrapperRequest massContentWrapperRequest){
        return massContentOperation(massContentWrapperRequest, this::filterToAdd);
    }

    private MassContentWrapper massContentOperation(MassContentWrapperRequest request, Predicate<MassContentDto> predicate){
        //TODO: Sanitize inputs for XSS
        Map<Boolean, List<MassContentDto>> partitionedContent = request.contents().stream()
                .collect(Collectors.partitioningBy(predicate));
        List<MassContentDto> validContent = partitionedContent.get(true);
        List<MassContentDto> invalidContent = partitionedContent.get(false);
        return new MassContentWrapper(validContent, invalidContent);
    }

    private boolean filterToAdd(MassContentDto massContentDto) {
        //TODO: Consider if batching would not be a better approach
        return contentRepository.getByPageAndKey(massContentDto.page(), massContentDto.key())
                .map(content -> false)
                .orElse(saveContentByMassContentDto(massContentDto));
    }

    private boolean saveContentByMassContentDto(MassContentDto massContentDto) {
        Content newContent = ContentMapper.mapToEntity(massContentDto);
        contentRepository.save(newContent);
        return true;
    }

    private boolean filterToUpdate(MassContentDto massContentDto) {
        //TODO: Consider if batching would not be a better approach
        return contentRepository.getByPageAndKey(massContentDto.page(), massContentDto.key())
                .map(content -> updateContentByMassContentDto(massContentDto, content))
                .orElse(false);
    }

    private boolean updateContentByMassContentDto(MassContentDto massContentDto, Content content) {
        content.setValuePl(massContentDto.valuePl());
        contentRepository.save(content);
        return true;
    }


}

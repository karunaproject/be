package karuna.karuna_backend.content.domain;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import karuna.karuna_backend.content.dto.ContentDTO;
import karuna.karuna_backend.content.dto.MassContentDto;
import karuna.karuna_backend.content.dto.MassContentWrapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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

    private ContentDTO convertToDTO(HashMap<String, String> contents) {
        ContentDTO contentDTO = new ContentDTO(contents);
        return contentDTO;
    }

    public MassContentWrapper massUpdateContent(MassContentWrapper massContentWrapper) {
        return new MassContentWrapper(massContentWrapper.contents().stream()
                .map(this::filterNotExistedInDatabaseAndUpdatedExisted)
                .filter(content -> !Objects.equals(NOT_EXISTED, content.getPage()))
                .map(contentRepository::save)
                .map(mapToDto())
                .toList());
    }

    @NotNull
    private static Function<Content, MassContentDto> mapToDto() {
        return content -> new MassContentDto(content.getPage(), content.getKey(), content.getValuePl());
    }

    @NotNull
    private Content filterNotExistedInDatabaseAndUpdatedExisted(MassContentDto massContentDto) {
        Content content = contentRepository.getByPageAndKey(massContentDto.page(), massContentDto.key()).orElse(Content.builder()
                .page(NOT_EXISTED).build());
        content.setValuePl(massContentDto.valuePl());
        return content;
    }
}

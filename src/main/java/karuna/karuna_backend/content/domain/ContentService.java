package karuna.karuna_backend.content.domain;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import karuna.karuna_backend.content.dto.ContentCreateDTO;
import karuna.karuna_backend.content.dto.ContentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
        pages.forEach(singlePage -> {
            allContent.put(singlePage.getKey(), singlePage.getValuePl());
        });
        return convertToDTO(allContent);
    }

    private ContentDTO convertToDTO(HashMap<String, String> contents) {
        ContentDTO contentDTO = new ContentDTO(contents);
        return contentDTO;
    }

    public ContentCreateDTO addOrUpdateContent(ContentCreateDTO contentCreateDTO, String page) {
        String key = contentCreateDTO.getKey();
        String value = contentCreateDTO.getValue();
        List<Content> existingContent = contentRepository.findByPageIgnoreCaseOrPageNull(page);
        Optional<Content> existingContentWithKey = existingContent.stream()
                .filter(content -> key.equals(content.getKey()))
                .findFirst();

        existingContentWithKey.ifPresentOrElse(
                content -> {
                    content.setValuePl(value);
                    contentRepository.save(content);
                },
                () -> {
                    Content newContent = Content.builder()
                            .page(page)
                            .key(key)
                            .valuePl(value)
                            .build();
                    contentRepository.save(newContent);
                }
        );

        return contentCreateDTO;
    }
}
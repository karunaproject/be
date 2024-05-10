package karuna.karuna_backend.content.domain;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import karuna.karuna_backend.content.dto.ContentCreateDTO;
import karuna.karuna_backend.content.dto.ContentDTO;
import karuna.karuna_backend.content.dto.ContentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

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

    public ContentResponseDTO addOrUpdateContent(ContentCreateDTO contentCreateDTO) {
        Optional<Content> contentOptional = contentRepository.findByPageAndKey(contentCreateDTO.page(), contentCreateDTO.key());
        AtomicReference<Content> contentAtomic = new AtomicReference<>(null);
        contentOptional.ifPresentOrElse(
                target -> {
                    target.setValuePl(contentCreateDTO.value());
                    contentAtomic.set(contentRepository.save(target));
                },
                () -> {
                    Content newContent = Content.builder()
                            .page(contentCreateDTO.page())
                            .key(contentCreateDTO.key())
                            .valuePl(contentCreateDTO.value())
                            .build();
                    contentAtomic.set(contentRepository.save(newContent));
                });
        Content content = contentAtomic.get();
        return new ContentResponseDTO(content.getId(), content.getPage(), content.getKey(), content.getValuePl());
    }
}
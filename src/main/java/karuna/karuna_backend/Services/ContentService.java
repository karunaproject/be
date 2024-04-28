package karuna.karuna_backend.Services;

import karuna.karuna_backend.Models.Content;
import karuna.karuna_backend.Repositories.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public HashMap<String, String> getContentByPage(String page) {
        List<Content> allPages = contentRepository.findByPageIsNull();
        List<Content> pages = contentRepository.findByPage(page);
        allPages.addAll(pages);
        HashMap<String, String> allContent = new HashMap<>();
        allPages.forEach(singlePage -> {
            allContent.put(singlePage.getKey(), singlePage.getValuePl());
        });

        return allContent;
    }
}

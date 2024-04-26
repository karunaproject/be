package karuna.karuna_backend.Services;

import karuna.karuna_backend.Models.Content;
import karuna.karuna_backend.Repositories.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public List<Content> getContentByPage(String page) {
        List<Content> allPages = contentRepository.findByPageIsNull();
        Optional<List<Content>> pages = contentRepository.findByPage(page);
        pages.ifPresent(allPages::addAll);

        return allPages;
    }
}

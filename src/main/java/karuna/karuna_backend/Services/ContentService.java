package karuna.karuna_backend.Services;

import karuna.karuna_backend.DTO.ContentDTO;
import karuna.karuna_backend.Models.Content;
import karuna.karuna_backend.Repositories.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentDTO getContentByPage(String page) {
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
}

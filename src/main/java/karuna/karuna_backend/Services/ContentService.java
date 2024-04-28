package karuna.karuna_backend.Services;

import karuna.karuna_backend.DTO.ContentDTO;
import karuna.karuna_backend.Models.Content;
import karuna.karuna_backend.Repositories.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public List<ContentDTO> getContentByPage(String page) {
        List<Content> allPages = contentRepository.findByPageIsNull();
        List<Content> pages = contentRepository.findByPageIgnoreCase(page);
        allPages.addAll(pages);
        List<ContentDTO> allContent = new ArrayList<>();
        allPages.forEach(singlePage -> allContent.add(convertToDTO(singlePage)));
        return allContent;
    }

    private ContentDTO convertToDTO(Content content) {
        ContentDTO contentDTO = new ContentDTO();
        contentDTO.setKey(content.getKey());
        contentDTO.setValuePl(content.getValuePl());
        return contentDTO;
    }
}

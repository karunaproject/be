package karuna.karuna_backend.content.domain;


import karuna.karuna_backend.Constants;
import karuna.karuna_backend.content.dto.ContentDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContentServiceTest {

    private final ContentService contentService = ContentTestConfiguration.contentService();

    @Test
    void shouldNotGetContentByPage() {
        //when: Get content for page
        String page = Constants.PAGE;
        ContentDTO contentDTO = contentService.getContentByPage(page);
        //then: Check if not exist content for page
        assertTrue(contentDTO.contents().isEmpty());
    }

}
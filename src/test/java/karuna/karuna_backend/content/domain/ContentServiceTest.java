package karuna.karuna_backend.content.domain;


import karuna.karuna_backend.Constants;
import karuna.karuna_backend.content.dto.ContentDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentServiceTest {

    private ContentService contentService = ContentTestConfiguration.contentService();

    @Test
    void shouldNotGetContentByPage() {
        //given: Give page = HOME to request;
        String page = Constants.PAGE;
        //when: Get content for page
        ContentDTO contentDTO = contentService.getContentByPage(page);
        //then: Assert not exist content for page
        assertEquals(0, contentDTO.contents().keySet().size());
    }

}
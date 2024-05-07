package karuna.karuna_backend.content.domain;


import karuna.karuna_backend.content.dto.ContentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentServiceTest {

    private ContentService contentService = ContentTestConfiguration.contentService();

    @Test
    void shouldGetContentByPage() {
        //given
        String page = Constants.PAGE;
        //when
        ContentDTO contentDTO = contentService.getContentByPage(page);
        //then
        assertEquals(Constants.VALUE_PL, contentDTO.contents().get(Constants.KEY));
    }

}
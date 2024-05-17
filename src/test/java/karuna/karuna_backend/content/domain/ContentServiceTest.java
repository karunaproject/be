package karuna.karuna_backend.content.domain;


import karuna.karuna_backend.Constants;
import karuna.karuna_backend.content.dto.ContentDTO;
import karuna.karuna_backend.content.dto.MassContentDto;
import karuna.karuna_backend.content.dto.MassContentWrapper;
import karuna.karuna_backend.content.dto.MassContentWrapperRequest;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentServiceTest {

    private final ContentService contentService = ContentTestConfiguration.contentService();

    @Test
    void shouldNotGetContentByPage() {
        //given: Give page = HOME to request;
        String page = Constants.PAGE;
        //when: Get content for page
        ContentDTO contentDTO = contentService.getContentByPage(page);
        //then: Assert not exist content for page
        assertEquals(0, contentDTO.contents().keySet().size());
    }

    @Test
    void shouldMassUpdateContent() {
        //given
        MassContentWrapperRequest massContentWrapperRequest = new MassContentWrapperRequest(List.of(new MassContentDto(Constants.PAGE, Constants.KEY, Constants.VALUE_PL)));
        //when
        MassContentWrapper massContentWrapperResponse = contentService.massUpdateContent(massContentWrapperRequest);
        //then
        assertEquals(1, massContentWrapperResponse.validContents().size());
        MassContentDto massContentDto = massContentWrapperResponse.validContents().get(0);
        assertEquals(Constants.PAGE, massContentDto.page());
        assertEquals(Constants.KEY, massContentDto.key());
        assertEquals(Constants.VALUE_PL, massContentDto.valuePl());
    }

}
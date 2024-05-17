package karuna.karuna_backend.content.domain;


import karuna.karuna_backend.Constants;
import karuna.karuna_backend.content.dto.ContentDTO;
import karuna.karuna_backend.content.dto.MassContentDto;
import karuna.karuna_backend.content.dto.MassContentWrapper;
import karuna.karuna_backend.content.dto.MassContentWrapperRequest;
import karuna.karuna_backend.user.domain.UserTestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContentServiceTest {

    private final ContentService contentService = ContentTestConfiguration.contentService();

    @BeforeEach
    void setUp() {
        ContentTestConfiguration.clearDatabase();
    }


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
        assertEquals(1, massContentWrapperResponse.invalidContents().size());
        MassContentDto massContentDto = massContentWrapperResponse.invalidContents().get(0);
        assertEquals(Constants.PAGE, massContentDto.page());
        assertEquals(Constants.KEY, massContentDto.key());
        assertEquals(Constants.VALUE_PL, massContentDto.valuePl());
    }

    @Test
    void shouldReturnInvalidElementsOnMassUpdateContent() {
        //when: Mass update content and return list of valid and invalid data
        MassContentWrapperRequest massContentWrapperRequest = new MassContentWrapperRequest(List.of(new MassContentDto(Constants.PAGE, Constants.KEY, Constants.VALUE_PL)));
        MassContentWrapper massContentWrapper = contentService.massUpdateContent(massContentWrapperRequest);
        //then: Check if data moved to invalid
        assertFalse(massContentWrapper.invalidContents().isEmpty());
        assertTrue(massContentWrapper.validContents().isEmpty());
    }

    @Test
    void shouldReturnValidElementsOnMassUpdateContent() {
        //given: Create content
        MassContentWrapperRequest massContentWrapperRequest = new MassContentWrapperRequest(List.of(new MassContentDto(Constants.PAGE, Constants.KEY, Constants.VALUE_PL)));
        contentService.massAddContent(massContentWrapperRequest);
        //when: Mass update content and return list of valid and invalid data
        MassContentWrapper massContentWrapper = contentService.massUpdateContent(massContentWrapperRequest);
        //then: Check if data moved to valid
        assertTrue(massContentWrapper.invalidContents().isEmpty());
        assertFalse(massContentWrapper.validContents().isEmpty());
    }

    @Test
    void shouldReturnValidElementsOnMassAddContent() {
        //when: Create content and return valid and invalid data
        MassContentWrapperRequest massContentWrapperRequest = new MassContentWrapperRequest(List.of(new MassContentDto(Constants.PAGE, Constants.KEY, Constants.VALUE_PL)));
        MassContentWrapper massContentWrapper = contentService.massAddContent(massContentWrapperRequest);
        //then: Check if data moved to valid
        assertTrue(massContentWrapper.invalidContents().isEmpty());
        assertFalse(massContentWrapper.validContents().isEmpty());
    }

    @Test
    void shouldReturnInvalidElementsOnMassAddContent() {
        //given: Create content
        MassContentWrapperRequest massContentWrapperRequest = new MassContentWrapperRequest(List.of(new MassContentDto(Constants.PAGE, Constants.KEY, Constants.VALUE_PL)));
        contentService.massAddContent(massContentWrapperRequest);
        //when: Create content and return valid and invalid data
        MassContentWrapper massContentWrapper = contentService.massAddContent(massContentWrapperRequest);
        //then: Check if data moved to invalid
        assertFalse(massContentWrapper.invalidContents().isEmpty());
        assertTrue(massContentWrapper.validContents().isEmpty());
    }
}
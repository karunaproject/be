package karuna.karuna_backend.visitor.message.domain;


import karuna.karuna_backend.visitor.message.dto.VisitorMessageCreateDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitorMessageServiceTest {

    private static final String BODY = "BODY";
    private static final String CONTACT = "CONTACT";

    private VisitorMessageService visitorMessageService = VisitorMessageTestConfiguration.visitorMessageService();

    @Test
    void shouldSendMessage() {
        //given
        VisitorMessageCreateDto visitorMessageCreateDto = new VisitorMessageCreateDto(BODY, CONTACT);
        //when
        VisitorMessageDto visitorMessageDto = visitorMessageService.sendMessage(visitorMessageCreateDto);
        //then
        assertEquals(1L, visitorMessageDto.id());
        Assertions.assertNotNull(visitorMessageDto.createdAt());
        assertEquals(BODY, visitorMessageDto.body());
        assertEquals(CONTACT, visitorMessageDto.contact());
    }
}
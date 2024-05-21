package karuna.karuna_backend.visitor.message.domain;


import karuna.karuna_backend.Constants;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageCreateDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitorMessageServiceTest {

    private final VisitorMessageService visitorMessageService = VisitorMessageTestConfiguration.visitorMessageService();

    @Test
    void shouldSendMessage() {
        //when: When send message save it to database and return dto
        VisitorMessageCreateDto visitorMessageCreateDto = new VisitorMessageCreateDto(Constants.BODY, Constants.CONTACT);
        VisitorMessageDto visitorMessageDto = visitorMessageService.sendMessage(visitorMessageCreateDto);
        //then: Check if database add id and save correctly data
        assertEquals(1L, visitorMessageDto.id());
        Assertions.assertNotNull(visitorMessageDto.createdAt());
        assertEquals(Constants.BODY, visitorMessageDto.body());
        assertEquals(Constants.CONTACT, visitorMessageDto.contact());
    }
}
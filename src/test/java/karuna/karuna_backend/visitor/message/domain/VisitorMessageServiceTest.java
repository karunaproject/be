package karuna.karuna_backend.visitor.message.domain;


import karuna.karuna_backend.Constants;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageCreateDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDto;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageRequest;
import karuna.karuna_backend.visitor.message.dto.VisitorMessageWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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


    @Test
    void shouldReceiveShortenMessage() {
        //given
        VisitorMessageCreateDto visitorMessageCreateDto = new VisitorMessageCreateDto(Constants.LONG_BODY, Constants.CONTACT);
        VisitorMessageRequest visitorMessageRequest = new VisitorMessageRequest(0, 10, 20);
        //when
        VisitorMessageWrapper shortenedMessages = visitorMessageService.getMessages(visitorMessageRequest);
        List<VisitorMessageDto> messages = shortenedMessages.messages();
        //then
        for (VisitorMessageDto message : messages)
            assertEquals(20, message.body().length());
    }
}
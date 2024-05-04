package karuna.karuna_backend.Services;

import karuna.karuna_backend.DTO.VisitorMessage.VisitorMessageCreateDto;
import karuna.karuna_backend.DTO.VisitorMessage.VisitorMessageDto;
import karuna.karuna_backend.Models.VisitorMessage;
import karuna.karuna_backend.Repositories.VisitorMessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitorMessageServiceTest {

    private static final String BODY = "BODY";
    private static final String CONTACT = "CONTACT";

    @Mock
    private VisitorMessageRepository visitorMessageRepository;

    @InjectMocks
    private VisitorMessageService visitorMessageService;

    @Test
    void shouldSendMessage() {
        //given
        VisitorMessageCreateDto visitorMessageCreateDto = new VisitorMessageCreateDto(BODY, CONTACT);
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        VisitorMessage visitorMessage = VisitorMessage.builder()
                .ID(1L)
                .createdAt(offsetDateTime)
                .body(BODY)
                .contact(CONTACT)
                .build();
        //when
        when(visitorMessageRepository.save(any(VisitorMessage.class))).thenReturn(visitorMessage);

        VisitorMessageDto visitorMessageDto = visitorMessageService.sendMessage(visitorMessageCreateDto);
        //then
        assertEquals(1L, visitorMessageDto.id());
        assertEquals(offsetDateTime, visitorMessageDto.createdAt());
        assertEquals(BODY, visitorMessageDto.body());
        assertEquals(CONTACT, visitorMessageDto.contact());
    }
}
package karuna.karuna_backend.receiver.domain;

import karuna.karuna_backend.Constants;
import karuna.karuna_backend.exception.receiver.ReceiverNotFoundException;
import karuna.karuna_backend.receiver.dto.ReceiverCreateDto;
import karuna.karuna_backend.receiver.dto.ReceiverDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReceiverServiceTest {

    private final ReceiverService receiverService = ReceiverTestConfiguration.receiverService();


    @BeforeEach
    void setUp() {
        ReceiverTestConfiguration.clearDatabase();
        receiverService.addReceiver(new ReceiverCreateDto(Constants.RECEIVER_ONE_EMAIL));
        receiverService.addReceiver(new ReceiverCreateDto(Constants.RECEIVER_TWO_EMAIL));
    }

    @Test
    void shouldAddReceiver() {
        //Given
        ReceiverCreateDto receiverCreateDto = new ReceiverCreateDto("testEmail3@gmail.com");
        //When
        receiverService.addReceiver(receiverCreateDto);
        int currentNumberOfReceivers = receiverService.getAllReceivers().receivers().size();
        //Then
        assertEquals(3, currentNumberOfReceivers);
    }

    @Test
    void shouldReturnExistingReceiver() {
        //Given
        ReceiverCreateDto receiverCreateDto = new ReceiverCreateDto(Constants.RECEIVER_ONE_EMAIL);
        //When
        ReceiverDTO receiver = receiverService.addReceiver(receiverCreateDto);
        int currentNumberOfReceivers = receiverService.getAllReceivers().receivers().size();
        //Then
        assertEquals(Constants.RECEIVER_ONE_EMAIL, receiver.email());
        assertEquals(2, currentNumberOfReceivers);
    }

    @Test
    void shouldDeleteReceiver() {
        //Given
        ReceiverCreateDto receiverCreateDto = new ReceiverCreateDto(Constants.RECEIVER_ONE_EMAIL);
        //When
        ResponseEntity<?> response = receiverService.deleteReceiver(receiverCreateDto);
        int currentNumberOfReceivers = receiverService.getAllReceivers().receivers().size();
        //Then
        assertEquals(1, currentNumberOfReceivers);
        assertEquals(ResponseEntity.ok("Recipient removed successfully."), response);
    }

    @Test
    void shouldThrowRecipientNotFoundExceptionDeleteReceiver() {
        assertThrows(ReceiverNotFoundException.class, () -> receiverService.deleteReceiver(new ReceiverCreateDto("notExistingEmail@gmail.com")));
    }
}

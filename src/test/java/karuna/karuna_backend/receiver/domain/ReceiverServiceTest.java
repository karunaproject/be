package karuna.karuna_backend.receiver.domain;

import karuna.karuna_backend.Constants;
import karuna.karuna_backend.exception.receiver.ReceiverNotFoundException;
import karuna.karuna_backend.receiver.dto.ReceiverRequestDto;
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
        receiverService.addReceiver(new ReceiverRequestDto(Constants.RECEIVER_ONE_EMAIL));
        receiverService.addReceiver(new ReceiverRequestDto(Constants.RECEIVER_TWO_EMAIL));
    }

    @Test
    void shouldAddReceiver() {
        //Given
        ReceiverRequestDto receiverRequestDto = new ReceiverRequestDto("testEmail3@gmail.com");
        //When
        receiverService.addReceiver(receiverRequestDto);
        int currentNumberOfReceivers = receiverService.getAllReceivers().receivers().size();
        //Then
        assertEquals(3, currentNumberOfReceivers);
    }

    @Test
    void shouldReturnExistingReceiver() {
        //Given
        ReceiverRequestDto receiverRequestDto = new ReceiverRequestDto(Constants.RECEIVER_ONE_EMAIL);
        //When
        ReceiverDTO receiver = receiverService.addReceiver(receiverRequestDto);
        int currentNumberOfReceivers = receiverService.getAllReceivers().receivers().size();
        //Then
        assertEquals(Constants.RECEIVER_ONE_EMAIL, receiver.email());
        assertEquals(2, currentNumberOfReceivers);
    }

    @Test
    void shouldDeleteReceiver() {
        //Given
        ReceiverRequestDto receiverRequestDto = new ReceiverRequestDto(Constants.RECEIVER_ONE_EMAIL);
        //When
        ResponseEntity<?> response = receiverService.deleteReceiver(receiverRequestDto);
        int currentNumberOfReceivers = receiverService.getAllReceivers().receivers().size();
        //Then
        assertEquals(1, currentNumberOfReceivers);
        assertEquals(ResponseEntity.ok("Recipient removed successfully."), response);
    }

    @Test
    void shouldThrowRecipientNotFoundExceptionDeleteReceiver() {
        assertThrows(ReceiverNotFoundException.class, () -> receiverService.deleteReceiver(new ReceiverRequestDto("notExistingEmail@gmail.com")));
    }
}

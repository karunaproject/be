package karuna.karuna_backend.receiver.domain;

import karuna.karuna_backend.Constants;
import karuna.karuna_backend.receiver.dto.ReceiverCreateDto;
import karuna.karuna_backend.receiver.dto.ReceiverDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        //When
        receiverService.addReceiver(new ReceiverCreateDto("testEmail3@gmail.com"));
        //Then
        assertEquals(3, receiverService.getAllReceivers().receivers().size());
    }

    @Test
    void shouldReturnExistingReceiver() {
        //When
        ReceiverDTO receiver = receiverService.addReceiver(new ReceiverCreateDto(Constants.RECEIVER_ONE_EMAIL));
        //Then
        assertEquals(Constants.RECEIVER_ONE_EMAIL, receiver.email());
        assertEquals(2, receiverService.getAllReceivers().receivers().size());
    }

    @Test
    void shouldDeleteReceiver() {
        //When
        ResponseEntity<?> response = receiverService.deleteReceiver(new ReceiverCreateDto(Constants.RECEIVER_ONE_EMAIL));
        //Then
        assertEquals(1, receiverService.getAllReceivers().receivers().size());
        assertEquals(ResponseEntity.ok("Recipient removed successfully."), response);
    }

    @Test
    void shouldReturnNotFoundDeleteReceiver() {
        //When
        ResponseEntity<?> response = receiverService.deleteReceiver(new ReceiverCreateDto("notExistingEmail@gmail.com"));
        //Then
        assertEquals(2, receiverService.getAllReceivers().receivers().size());
        assertEquals(new ResponseEntity<>("Recipient not found.", HttpStatus.NOT_FOUND), response);
    }
}

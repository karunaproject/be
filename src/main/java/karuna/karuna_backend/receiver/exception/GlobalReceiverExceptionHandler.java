package karuna.karuna_backend.receiver.exception;

import io.swagger.v3.oas.annotations.Hidden;
import karuna.karuna_backend.exception.dto.ReceiverErrorResponse;
import karuna.karuna_backend.exception.keys.ReceiverNotFoundKey;
import karuna.karuna_backend.exception.receiver.ReceiverNotFoundException;
import karuna.karuna_backend.receiver.infrastrucutre.ReceiverController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ReceiverController.class)
public class GlobalReceiverExceptionHandler {

    @ExceptionHandler(ReceiverNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Hidden
    ReceiverErrorResponse handleReceiverNotFoundException(ReceiverNotFoundException receiverNotFoundException) {
        return new ReceiverErrorResponse(ReceiverNotFoundKey.NOT_FOUND, receiverNotFoundException.getMessage());
    }
}

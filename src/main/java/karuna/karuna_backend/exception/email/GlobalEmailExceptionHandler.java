package karuna.karuna_backend.exception.email;

import karuna.karuna_backend.email.EmailSendException;
import karuna.karuna_backend.exception.dto.EmailErrorResponse;
import karuna.karuna_backend.exception.keys.EmailSendKey;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalEmailExceptionHandler {

    @ExceptionHandler(EmailSendException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    EmailErrorResponse handleEmailSendException(EmailSendException emailSendException) {
        return new EmailErrorResponse(EmailSendKey.CAN_NOT_SEND, emailSendException.getMessage());
    }
}

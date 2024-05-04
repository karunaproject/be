package karuna.karuna_backend.Errors;

import karuna.karuna_backend.DTO.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    CustomErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new CustomErrorResponse(CustomExceptionKey.VALIDATION.name(), methodArgumentNotValidException.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}

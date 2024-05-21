package karuna.karuna_backend.exception.database;

import io.swagger.v3.oas.annotations.Hidden;
import karuna.karuna_backend.exception.dto.DataIntegrityErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
class GlobalDatabaseExceptionHandler {

    private final DatabaseExceptionKeyTranslator databaseExceptionKeyTranslator;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Hidden
    @ExceptionHandler(DataIntegrityViolationException.class)
    DataIntegrityErrorResponse handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        DatabaseIntegrityException databaseException = databaseExceptionKeyTranslator.handleIntegrityException(ex);

        return databaseException.mapToErrorResponse();
    }
}

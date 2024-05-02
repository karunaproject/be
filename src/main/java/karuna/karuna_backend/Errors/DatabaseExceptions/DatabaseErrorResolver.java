package karuna.karuna_backend.Errors.DatabaseExceptions;

import jakarta.annotation.PostConstruct;
import karuna.karuna_backend.Errors.ErrorKeys.DataIntegrityErrorKey;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
class DatabaseErrorResolver {
    private static final Map<String, DataIntegrityErrorKey> errorMappings = new HashMap<>();

   static{
        errorMappings.put("1062", DataIntegrityErrorKey.UNIQUE_CONSTRAINT_VIOLATION); // MySQL unique constraint code
        errorMappings.put("23505", DataIntegrityErrorKey.UNIQUE_CONSTRAINT_VIOLATION); // PostgreSQL unique constraint code

        errorMappings.put("1048", DataIntegrityErrorKey.NOT_NULL_CONSTRAINT_VIOLATION);  // MySQL NOT NULL constraint
        errorMappings.put("23502", DataIntegrityErrorKey.NOT_NULL_CONSTRAINT_VIOLATION); // PostgreSQL NOT NULL constraint
    }

     DataIntegrityErrorKey resolveErrorCode(DataIntegrityViolationException e) {
        String errorCode = extractErrorCode(e);
        return errorMappings.getOrDefault(errorCode, DataIntegrityErrorKey.UNKNOWN_ERROR);
    }

     String extractErrorCode(DataIntegrityViolationException e) {
        Throwable rootCause = e.getMostSpecificCause();
        if (rootCause instanceof SQLException sqlEx) {
            return sqlEx.getSQLState();  // Get SQL state code
        }
        return e.getMessage();
    }
}

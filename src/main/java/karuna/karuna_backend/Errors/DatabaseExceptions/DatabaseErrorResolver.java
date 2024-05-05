package karuna.karuna_backend.Errors.DatabaseExceptions;

import karuna.karuna_backend.Errors.ErrorKeys.DataIntegrityErrorKey;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


/**
 * Component responsible for resolving database error codes to defined {@link DataIntegrityErrorKey} values.
 * This mapping helps in translating specific SQL state codes into more generic error descriptions,
 * making it easier to handle and respond to database errors consistently across the application.
 */
@Component
class DatabaseErrorResolver {
    private static final Map<String, DataIntegrityErrorKey> errorMappings = new HashMap<>();

    // Static initializer to populate the errorMappings map with common SQL error codes.
    static{
        errorMappings.put("1062", DataIntegrityErrorKey.UNIQUE_CONSTRAINT_VIOLATION); // MySQL unique constraint code
        errorMappings.put("23505", DataIntegrityErrorKey.UNIQUE_CONSTRAINT_VIOLATION); // PostgreSQL unique constraint code

        errorMappings.put("1048", DataIntegrityErrorKey.NOT_NULL_CONSTRAINT_VIOLATION);  // MySQL NOT NULL constraint
        errorMappings.put("23502", DataIntegrityErrorKey.NOT_NULL_CONSTRAINT_VIOLATION); // PostgreSQL NOT NULL constraint
    }

    /**
     * Resolves a {@link DataIntegrityViolationException} to a {@link DataIntegrityErrorKey} based on the SQL error code.
     * If no specific mapping is found, {@link DataIntegrityErrorKey#UNKNOWN_ERROR} is returned.
     *
     * @param e The {@link DataIntegrityViolationException} to resolve.
     * @return The corresponding {@link DataIntegrityErrorKey}, or {@link DataIntegrityErrorKey#UNKNOWN_ERROR} if no mapping exists.
     */
     DataIntegrityErrorKey resolveErrorCode(DataIntegrityViolationException e) {
        String errorCode = extractErrorCode(e);
        return errorMappings.getOrDefault(errorCode, DataIntegrityErrorKey.UNKNOWN_ERROR);
    }

    /**
     * Extracts the SQL state code from the most specific cause of the {@link DataIntegrityViolationException}.
     * If the root cause is not an {@link SQLException}, the exception message is returned instead.
     *
     * @param e The {@link DataIntegrityViolationException} from which to extract the error code.
     * @return The error code as a {@link String}, or the exception message if the error code cannot be determined.
     */
     String extractErrorCode(DataIntegrityViolationException e) {
        Throwable rootCause = e.getMostSpecificCause();
        if (rootCause instanceof SQLException sqlEx) {
            return sqlEx.getSQLState();  // Get SQL state code
        }
        return e.getMessage();
    }
}

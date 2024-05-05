package karuna.karuna_backend.Errors.DatabaseExceptions;

import karuna.karuna_backend.Errors.CustomException;
import karuna.karuna_backend.Errors.DTO.DataIntegrityErrorResponse;
import karuna.karuna_backend.Errors.ErrorKeys.DataIntegrityErrorKey;

public class DatabaseIntegrityException extends CustomException {
    public DatabaseIntegrityException(DataIntegrityErrorKey key, String desc) {
        super(key, desc);
    }

    @Override
    public DataIntegrityErrorResponse mapToErrorResponse(){
        //TODO: Think of the way to make CustomException Generic and still extend Throwable Java.
        if (!(getKey() instanceof DataIntegrityErrorKey)) {
            throw new IllegalStateException("Key type mismatch in DatabaseIntegerityException");
        }
        return new DataIntegrityErrorResponse((DataIntegrityErrorKey) getKey(), getDescription());
    }
}

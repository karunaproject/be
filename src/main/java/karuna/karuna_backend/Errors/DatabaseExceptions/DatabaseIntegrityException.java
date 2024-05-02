package karuna.karuna_backend.Errors.DatabaseExceptions;

import karuna.karuna_backend.Errors.CustomException;
import karuna.karuna_backend.Errors.DTO.AuthenticationErrorResponse;
import karuna.karuna_backend.Errors.DTO.DataIntegrityErrorResponse;
import karuna.karuna_backend.Errors.ErrorKeys.DataIntegrityErrorKey;

public class DatabaseIntegrityException extends CustomException {
    public DatabaseIntegrityException(DataIntegrityErrorKey key, String desc) {
        super(key, desc);
    }

    @Override
    public DataIntegrityErrorResponse mapToErrorResponse(){
        return new DataIntegrityErrorResponse(super.getKey(), super.getDescription());
    }
}

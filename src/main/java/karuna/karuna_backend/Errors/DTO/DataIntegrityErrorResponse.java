package karuna.karuna_backend.Errors.DTO;

import karuna.karuna_backend.Errors.ErrorKeys.DataIntegrityErrorKey;
import karuna.karuna_backend.Errors.IErrorResponse;

public record DataIntegrityErrorResponse(DataIntegrityErrorKey key, String description) implements IErrorResponse { }

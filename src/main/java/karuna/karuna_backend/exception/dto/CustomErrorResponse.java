package karuna.karuna_backend.exception.dto;

import karuna.karuna_backend.exception.CustomExceptionKey;
import karuna.karuna_backend.exception.IErrorResponse;
import karuna.karuna_backend.exception.keys.FailedValidationErrorKey;

public record CustomErrorResponse(FailedValidationErrorKey key, String description) implements IErrorResponse {
}

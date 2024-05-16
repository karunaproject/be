package karuna.karuna_backend.exception.dto;

import karuna.karuna_backend.exception.IErrorResponse;
import karuna.karuna_backend.exception.keys.FailedValidationErrorKey;

public record ValidationErrorResponse(FailedValidationErrorKey key, String description) implements IErrorResponse {
}

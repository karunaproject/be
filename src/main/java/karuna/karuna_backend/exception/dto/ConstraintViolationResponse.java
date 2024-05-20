package karuna.karuna_backend.exception.dto;

import karuna.karuna_backend.exception.IErrorResponse;
import karuna.karuna_backend.exception.keys.ConstraintViolationKey;

public record ConstraintViolationResponse(ConstraintViolationKey key, String description) implements IErrorResponse {
}

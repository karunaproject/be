package karuna.karuna_backend.exception.dto;

import karuna.karuna_backend.exception.IErrorResponse;
import karuna.karuna_backend.exception.keys.ReceiverNotFoundKey;

public record ReceiverErrorResponse(ReceiverNotFoundKey key, String description) implements IErrorResponse {
}

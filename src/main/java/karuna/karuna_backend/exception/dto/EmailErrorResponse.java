package karuna.karuna_backend.exception.dto;

import karuna.karuna_backend.exception.IErrorResponse;
import karuna.karuna_backend.exception.keys.EmailSendKey;

public record EmailErrorResponse(EmailSendKey key, String description) implements IErrorResponse {
}

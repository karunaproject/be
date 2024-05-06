package karuna.karuna_backend.exception.dto;

import karuna.karuna_backend.exception.CustomExceptionKey;
import karuna.karuna_backend.exception.IErrorResponse;

public record CustomErrorResponse(CustomExceptionKey key, String description) implements IErrorResponse {
}

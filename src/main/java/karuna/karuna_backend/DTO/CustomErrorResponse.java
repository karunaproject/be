package karuna.karuna_backend.DTO;

import karuna.karuna_backend.Errors.CustomExceptionKey;

public record CustomErrorResponse(CustomExceptionKey key, String description) {
}
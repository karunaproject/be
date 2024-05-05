package karuna.karuna_backend.Errors.DTO;

import karuna.karuna_backend.Errors.CustomExceptionKey;

public record CustomErrorResponse(CustomExceptionKey key, String description) {
}

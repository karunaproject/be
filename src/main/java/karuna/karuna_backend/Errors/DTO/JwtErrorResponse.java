package karuna.karuna_backend.Errors.DTO;

import karuna.karuna_backend.Errors.IErrorResponse;

public record JwtErrorResponse(String key, String description) implements IErrorResponse { }

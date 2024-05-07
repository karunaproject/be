package karuna.karuna_backend.exception.dto;


import karuna.karuna_backend.exception.IErrorResponse;
import karuna.karuna_backend.exception.keys.LoginPasswordAuthErrorKey;

public record AuthenticationErrorResponse(LoginPasswordAuthErrorKey key, String description) implements IErrorResponse { }

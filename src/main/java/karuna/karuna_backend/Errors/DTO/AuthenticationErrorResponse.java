package karuna.karuna_backend.Errors.DTO;


import karuna.karuna_backend.Errors.ErrorKeys.LoginPasswordAuthErrorKey;
import karuna.karuna_backend.Errors.IErrorResponse;

public record AuthenticationErrorResponse(LoginPasswordAuthErrorKey key, String description) implements IErrorResponse { }

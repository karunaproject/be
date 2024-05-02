package karuna.karuna_backend.Errors.DTO;


import karuna.karuna_backend.Errors.IErrorResponse;

public record CustomErrorResponse(String key, String description) implements IErrorResponse { }

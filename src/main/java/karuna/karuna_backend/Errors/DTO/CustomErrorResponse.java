package karuna.karuna_backend.Errors.DTO;

import karuna.karuna_backend.Errors.CustomExceptionKey;
import karuna.karuna_backend.Errors.IErrorResponse;

public record CustomErrorResponse(CustomExceptionKey key, String description) implements IErrorResponse {
}

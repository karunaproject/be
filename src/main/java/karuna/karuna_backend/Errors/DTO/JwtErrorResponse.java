    package karuna.karuna_backend.Errors.DTO;

    import karuna.karuna_backend.Errors.ErrorKeys.JwtErrorKey;
    import karuna.karuna_backend.Errors.IErrorResponse;

    public record JwtErrorResponse(JwtErrorKey key, String description) implements IErrorResponse { }

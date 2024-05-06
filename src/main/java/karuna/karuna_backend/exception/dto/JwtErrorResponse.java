    package karuna.karuna_backend.exception.dto;

    import karuna.karuna_backend.exception.IErrorResponse;
    import karuna.karuna_backend.exception.keys.JwtErrorKey;

    public record JwtErrorResponse(JwtErrorKey key, String description) implements IErrorResponse { }

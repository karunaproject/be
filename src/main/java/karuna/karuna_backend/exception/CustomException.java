package karuna.karuna_backend.exception;

import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException{
    private final Enum<?> key;
    private final String description;

    public CustomException(Enum<?> key, String description){
        super(description);
        this.key=key;
        this.description=description;
    }

    public abstract IErrorResponse mapToErrorResponse();
}

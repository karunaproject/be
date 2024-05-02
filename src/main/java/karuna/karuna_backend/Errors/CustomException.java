package karuna.karuna_backend.Errors;

import karuna.karuna_backend.Errors.DTO.CustomErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public abstract class CustomException extends RuntimeException{
    private final Enum<?> key;
    private final String description;

//    public CustomException(String key, String description){
//        super(description);
//        this.key=key;
//        this.description=description;
//    }

    //Overloaded constructor for enum values
    public CustomException(Enum<?> key, String description){
        super(description);
        this.key=key;
        this.description=description;
    }

    //TODO: Refactor into separate class

    public abstract IErrorResponse mapToErrorResponse();
}

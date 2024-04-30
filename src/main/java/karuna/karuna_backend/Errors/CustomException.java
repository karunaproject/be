package karuna.karuna_backend.Errors;

import karuna.karuna_backend.Errors.DTO.CustomErrorResponse;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private CustomExceptionKey key;
    private String description;

    public CustomException(CustomExceptionKey key, String description){
        super(description);
        this.key = key;
        this.description = description;
    }
    public CustomErrorResponse mapToErrorResponse(){
        return new CustomErrorResponse(key.name(), description);
    }
}
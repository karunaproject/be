package karuna.karuna_backend.Errors;

import karuna.karuna_backend.Errors.DTO.CustomErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomException extends RuntimeException{
    private  String key;
    private  String description;

    public CustomException(String key, String description){
        super(description);
        this.key=key;
        this.description=description;
    }

    //TODO: Refactor into separate class

    public CustomErrorResponse mapToErrorResponse(){
        return new CustomErrorResponse(key, description);
    }
}

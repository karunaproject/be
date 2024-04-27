package karuna.karuna_backend.Errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomException extends RuntimeException{
    private  String key;
    private  String description;

    public CustomExceptionDao mapToDao(){
        return new CustomExceptionDao(key, description);
    }
}

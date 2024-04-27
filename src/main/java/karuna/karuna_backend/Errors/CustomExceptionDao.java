package karuna.karuna_backend.Errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomExceptionDao {
    private String key;
    private String description;

}

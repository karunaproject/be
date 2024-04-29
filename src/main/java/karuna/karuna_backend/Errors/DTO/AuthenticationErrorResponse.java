package karuna.karuna_backend.Errors.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthenticationErrorResponse {
    private String key;
    private String description;
}

package karuna.karuna_backend.Authentication.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    //TODO: Implement hiberante validators, therefore add dependency
    private String username;
    private String password;
}

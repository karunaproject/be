package karuna.karuna_backend.Authentication.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;

public record RegisterRequestDto(String username, String password) {
    //TODO: Implement hiberante validators, therefore add dependency
}

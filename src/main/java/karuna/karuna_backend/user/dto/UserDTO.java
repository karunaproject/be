package karuna.karuna_backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public record UserDTO(
        long id,
        String username,
        String password,
        List<String> roles
) {
}

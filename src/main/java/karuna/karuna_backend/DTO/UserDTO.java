package karuna.karuna_backend.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String username;

    //TODO: remove this field
    private String password;
}

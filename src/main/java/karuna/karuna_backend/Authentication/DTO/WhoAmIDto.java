package karuna.karuna_backend.Authentication.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WhoAmIDto {
    private String username;
    private List<String> roles;
}

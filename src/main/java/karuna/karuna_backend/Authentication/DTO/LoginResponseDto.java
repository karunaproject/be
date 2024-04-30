package karuna.karuna_backend.Authentication.DTO;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Builder
@Getter
public class LoginResponseDto {
    private String username;
    private String jwt;
    private Date expirationTime;
    private List<String> roles;
}

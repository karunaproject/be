package karuna.karuna_backend.Authentication.DTO;

import karuna.karuna_backend.Authentication.CustomUserDetails;
import karuna.karuna_backend.Errors.AuthenticateExceptions.CustomAuthenticationException;
import karuna.karuna_backend.Models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WhoAmIDto {
    private String username;
    private List<String> roles;
}

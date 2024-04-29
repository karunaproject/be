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

    //TODO: Move this method into separate class
    public void MapAuthenticationToDto(Authentication authentication){

        Object principal = authentication.getPrincipal();

        this.roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        } else if (principal instanceof String) {
            username = (String) principal;
        }
        else {
            throw new CustomAuthenticationException("unknown_exception", "Something unexpected happened during token parse");
        }
    }
}

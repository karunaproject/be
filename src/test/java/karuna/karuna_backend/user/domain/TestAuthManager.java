package karuna.karuna_backend.user.domain;

import karuna.karuna_backend.Constants;
import karuna.karuna_backend.security.CustomUserDetails;
import karuna.karuna_backend.user.dto.UserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

class TestAuthManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return new UsernamePasswordAuthenticationToken(new CustomUserDetails(new UserDTO(1L, Constants.USERNAME, Constants.PASSWORD, List.of(Constants.ROLE_USER))), Constants.PASSWORD, List.of(Constants.ROLE_USER).stream().map(SimpleGrantedAuthority::new).toList());
    }
}

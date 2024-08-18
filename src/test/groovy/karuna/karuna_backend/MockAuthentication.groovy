package karuna.karuna_backend

import karuna.karuna_backend.Constants
import karuna.karuna_backend.security.CustomUserDetails
import karuna.karuna_backend.user.dto.UserDTO
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class MockAuthentication implements Authentication, Constants{
    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return null
    }

    @Override
    Object getCredentials() {
        return null
    }

    @Override
    Object getDetails() {
        return null
    }

    @Override
    Object getPrincipal() {
        new CustomUserDetails(new UserDTO(1L, USERNAME, USERNAME, List.of(ROLE_USER)))
    }

    @Override
    boolean isAuthenticated() {
        true
    }

    @Override
    void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    String getName() {
        USERNAME
    }
}

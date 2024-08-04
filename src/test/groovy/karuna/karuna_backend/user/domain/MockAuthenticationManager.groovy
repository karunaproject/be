package karuna.karuna_backend.user.domain

import karuna.karuna_backend.MockAuthentication
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException

class MockAuthenticationManager implements AuthenticationManager{

    @Override
    Authentication authenticate(Authentication authentication) throws AuthenticationException {
        new MockAuthentication()
    }
}

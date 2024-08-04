package karuna.karuna_backend.user.domain

import karuna.karuna_backend.Constants
import karuna.karuna_backend.MockAuthentication
import karuna.karuna_backend.security.CustomUserDetails
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class MockAuthenticationManager implements AuthenticationManager, Constants{

    private UserRepository userRepository = UserConfiguration.userRepository()

    @Override
    Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomUserDetails userDetails = new CustomUserDetails(
                UserMapper.toDto(
                        userRepository.findByUsername(authentication.getPrincipal().toString())
                                .orElseThrow(() -> new RuntimeException("Cannot authenticate user"))
                )
        )
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(userDetails.getAuthorities())

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                authentication.getCredentials(),
                grantedAuthorities
        )

        auth
    }
}

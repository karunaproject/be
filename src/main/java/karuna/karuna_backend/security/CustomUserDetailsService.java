package karuna.karuna_backend.security;

import karuna.karuna_backend.user.domain.UserService;
import karuna.karuna_backend.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.getUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found in the database")
        );

        return new CustomUserDetails(user);
    }
}

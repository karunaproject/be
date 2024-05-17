package karuna.karuna_backend.user.domain;

import jakarta.transaction.Transactional;
import karuna.karuna_backend.exception.keys.LoginPasswordAuthErrorKey;
import karuna.karuna_backend.security.CustomUserDetails;
import karuna.karuna_backend.security.exception.LoginPasswordAuthenticationException;
import karuna.karuna_backend.security.jwt.JwtTokenService;
import karuna.karuna_backend.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Retrieves a user by their ID.
     * @param id the ID of the user to retrieve.
     * @return an Optional containing the UserDTO if the user is found, or an empty Optional if not found.
     */
    public Optional<UserDTO> getUserById(long id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(UserMapper::toDto);

    }

    public Optional<UserDTO> getUserByUsername(String username){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(UserMapper::toDto);

    }


    /**
     * Registers a new user in the repository.
     * This method saves the provided User object and returns a UserDTO representation of the saved user.
     * @param username name of the user.
     * @param password password of the user.
     * @return the UserDTO representation of the registered user.
     */
    @Transactional
    public String registerUser(String username, String password)  {
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found."));

            //TODO: Sanitize inputs, XSS prevention
            User user = User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .roles(List.of(defaultRole))
                    .build();

            userRepository.save(user);
            return jwtTokenService.generateToken(UserMapper.toUserDetails(user));
    }

    public String authenticateUser(String username, String password) throws AuthenticationException{
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            if (!(authentication.getPrincipal() instanceof CustomUserDetails)) {
                throw new IllegalStateException("Principal is not an instance of CustomUserDetails");
            }

            return jwtTokenService.generateToken((CustomUserDetails) authentication.getPrincipal());

        }
        catch (AuthenticationException ex) {
            throw new LoginPasswordAuthenticationException(LoginPasswordAuthErrorKey.WRONG_LOGIN_CREDENTIALS,
                    "Provided login credentials are invalid");
        }


    }
}

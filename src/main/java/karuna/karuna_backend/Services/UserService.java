package karuna.karuna_backend.Services;

import karuna.karuna_backend.Authentication.CustomUserDetails;
import karuna.karuna_backend.Authentication.JWT.JwtTokenService;
import karuna.karuna_backend.DTO.UserDTO;
import karuna.karuna_backend.Errors.CustomException;
import karuna.karuna_backend.Errors.DatabaseExceptionHandler;
import karuna.karuna_backend.Models.Role;
import karuna.karuna_backend.Models.User;
import karuna.karuna_backend.Repositories.RoleRepository;
import karuna.karuna_backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       AuthenticationManager authenticationManager,
                       JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager=authenticationManager;
        this.jwtTokenService=jwtTokenService;
    }

    /**
     * Retrieves a user by their ID.
     * @param id the ID of the user to retrieve.
     * @return an Optional containing the UserDTO if the user is found, or an empty Optional if not found.
     */
    public Optional<UserDTO> getUserById(int id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(User::dto);

    }

    public Optional<UserDTO> getUserByUsername(String username){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(User::dto);

    }


    /**
     * Registers a new user in the repository.
     * This method saves the provided User object and returns a UserDTO representation of the saved user.
     * @param user the User to register.
     * @return the UserDTO representation of the registered user.
     */
    public String registerUser(User user)  {
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found."));

            user.getRoles().add(defaultRole);
            User savedUser = userRepository.save(user);

            return jwtTokenService.generateToken(savedUser.mapToUserDetails());
    }

    public String authenticateUser(String username, String password) throws AuthenticationException{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        return jwtTokenService.generateToken((CustomUserDetails) authentication.getPrincipal());

    }


}

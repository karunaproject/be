package karuna.karuna_backend.Authentication;
import karuna.karuna_backend.Authentication.JWT.JwtTokenService;
import karuna.karuna_backend.Models.User;
import karuna.karuna_backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService,
                          PasswordEncoder passwordEncoder)
    {
        this.service=userService;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody String name) {
        service.authenticateUser(name, "123");
        return ResponseEntity.ok("test");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody String name) {
        User user = User.builder()
                .username(name)
                .password(passwordEncoder.encode("123"))
                .build();
            service.registerUser(user);
            return ResponseEntity.ok("dodane");
    }
}
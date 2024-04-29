package karuna.karuna_backend.Authentication;
import karuna.karuna_backend.Authentication.DTO.LoginRequestDto;
import karuna.karuna_backend.Authentication.DTO.RegisterRequestDto;
import karuna.karuna_backend.Models.User;
import karuna.karuna_backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
        String jwt = service.authenticateUser(loginRequestDto.getUsername(),
                loginRequestDto.getPassword());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto registerRequstDto) {
        User user = User.builder()
                .username(registerRequstDto.getUsername())
                .password(passwordEncoder.encode(registerRequstDto.getPassword()))
                .build();
        String jwt = service.registerUser(user);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/WhoAmI")
    public String test(){
        return "test";
    }
}
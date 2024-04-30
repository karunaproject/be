package karuna.karuna_backend.Authentication;
import io.jsonwebtoken.Jwt;
import karuna.karuna_backend.Authentication.DTO.*;
import karuna.karuna_backend.Authentication.JWT.JwtTokenService;
import karuna.karuna_backend.Authentication.Utils.AuthenticationUtil;
import karuna.karuna_backend.Models.User;
import karuna.karuna_backend.Services.UserService;
import lombok.extern.java.Log;
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
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService,
                          JwtTokenService jwtTokenService,
                          PasswordEncoder passwordEncoder)
    {
        this.service=userService;
        this.passwordEncoder=passwordEncoder;
        this.jwtTokenService=jwtTokenService;
    }

    @PostMapping("/login")
    public LoginResponseDto authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
        //TODO: Sanitize inputs, XSS prevention
        String jwt = service.authenticateUser(loginRequestDto.getUsername(),
                loginRequestDto.getPassword());

        return  LoginResponseDto.builder()
                .username(jwtTokenService.getSubject(jwt))
                .jwt(jwt)
                .expirationTime(jwtTokenService.getExpirationDate(jwt))
                .roles(jwtTokenService.getRoles(jwt))
                .build();
    }

    @PostMapping("/register")
    public RegisterResponseDto registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        //TODO: Sanitize inputs, XSS prevention
        User user = User.builder()
                .username(registerRequestDto.getUsername())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .build();

        String jwt = service.registerUser(user);

        return  RegisterResponseDto.builder()
                .username(jwtTokenService.getSubject(jwt))
                .jwt(jwt)
                .expirationTime(jwtTokenService.getExpirationDate(jwt))
                .roles(jwtTokenService.getRoles(jwt))
                .build();
    }


    @GetMapping("/WhoAmI")
    public WhoAmIDto GetCurrentUserDetails() {
        return WhoAmIDto.builder()
                .username(AuthenticationUtil.getUsername())
                .roles(AuthenticationUtil.getRoles())
                .build();

    }
}
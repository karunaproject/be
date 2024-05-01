package karuna.karuna_backend.Authentication;
import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import karuna.karuna_backend.Authentication.DTO.*;
import karuna.karuna_backend.Authentication.JWT.JwtTokenService;
import karuna.karuna_backend.Authentication.Utils.AuthenticationUtil;
import karuna.karuna_backend.DTO.UserDTO;
import karuna.karuna_backend.Models.User;
import karuna.karuna_backend.Services.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
        //TODO: Sanitize inputs, XSS prevention
        String jwt = service.authenticateUser(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        LoginResponseDto body = LoginResponseDto.builder()
                .username(jwtTokenService.getSubject(jwt))
                .tokenExpirationTime(jwtTokenService.getExpirationDate(jwt))
                .build();

        return createJwtResponse(jwt, body, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {

        //TODO: Sanitize inputs, XSS prevention
        User user = User.builder()
                .username(registerRequestDto.getUsername())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .build();

        String jwt = service.registerUser(user);
        RegisterResponseDto body = RegisterResponseDto.builder()
                .username(jwtTokenService.getSubject(jwt))
                .tokenExpirationTime(jwtTokenService.getExpirationDate(jwt))
                .build();

        return createJwtResponse(jwt, body, HttpStatus.CREATED);
    }



    //TODO: This endpoint will be completely removed
    @GetMapping("/WhoAmI")
    public UserDTO GetCurrentUserDetails() {
        return service.getUserByUsername(AuthenticationUtil.getUsername())
                .orElse(null);
    }

    private ResponseEntity<?> createJwtResponse(String jwt, Object body, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);
        return new ResponseEntity<>(body, headers, status);
    }
}
package karuna.karuna_backend.Authentication;
import io.jsonwebtoken.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import karuna.karuna_backend.Authentication.DTO.*;
import karuna.karuna_backend.Authentication.JWT.JwtConfig;
import karuna.karuna_backend.Authentication.JWT.JwtTokenService;
import karuna.karuna_backend.Authentication.Utils.AuthenticationUtil;
import karuna.karuna_backend.DTO.UserDTO;
import karuna.karuna_backend.Errors.AuthenticateExceptions.LoginPasswordAuthenticationException;
import karuna.karuna_backend.Errors.DTO.AuthenticationErrorResponse;
import karuna.karuna_backend.Errors.DTO.DataIntegrityErrorResponse;
import karuna.karuna_backend.Errors.DTO.JwtErrorResponse;
import karuna.karuna_backend.Models.User;
import karuna.karuna_backend.Services.UserService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AuthController {

    private final UserService service;
    private final JwtTokenService jwtTokenService;
    private final JwtConfig jwtConfig;

    @PostMapping("/login")
    @Operation(summary = "Validates the provided authentication token",
            description = "Checks if the token is valid and not expired. Returns token status.")
    @ApiResponse(responseCode = "200", description = "Token is valid",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "401", description = "Credentials provided by user are invalid",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuthenticationErrorResponse.class)))
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
        //TODO: Sanitize inputs, XSS prevention
        String jwt = service.authenticateUser(loginRequestDto.username(), loginRequestDto.password());
        LoginResponseDto body = LoginResponseDto.builder()
                .username(jwtTokenService.getSubject(jwt))
                .tokenExpirationTime(jwtTokenService.getExpirationDate(jwt))
                .build();

        return createJwtResponse(jwt, body, HttpStatus.OK);
    }


    @PostMapping("/register")
    @Operation(summary = "Validates the provided authentication token",
            description = "Checks if the token is valid and not expired. Returns token status.")
    @ApiResponse(responseCode = "200", description = "Token is valid",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "409", description = "One of constraints was violated",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DataIntegrityErrorResponse.class)))
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {

        String jwt = service.registerUser(registerRequestDto.username(), registerRequestDto.password());
        RegisterResponseDto body = RegisterResponseDto.builder()
                .username(jwtTokenService.getSubject(jwt))
                .tokenExpirationTime(jwtTokenService.getExpirationDate(jwt))
                .build();

        return createJwtResponse(jwt, body, HttpStatus.CREATED);
    }



    //TODO: This endpoint will be completely removed
    @GetMapping("/WhoAmI")
    @Operation(summary = "Validates the provided authentication token",
            description = "Checks if the token is valid and not expired. Returns token status.")
    @ApiResponse(responseCode = "200", description = "Token is valid",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "401", description = "Token validation failed",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JwtErrorResponse.class)))
    public UserDTO GetCurrentUserDetails() {
        return service.getUserByUsername(AuthenticationUtil.getUsername())
                .orElse(null);
    }

    private ResponseEntity<?> createJwtResponse(String jwt, Object body, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + jwt);
        return new ResponseEntity<>(body, headers, status);
    }
}
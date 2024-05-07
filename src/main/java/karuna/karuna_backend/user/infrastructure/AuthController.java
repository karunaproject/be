package karuna.karuna_backend.user.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import karuna.karuna_backend.exception.dto.AuthenticationErrorResponse;
import karuna.karuna_backend.exception.dto.DataIntegrityErrorResponse;
import karuna.karuna_backend.exception.dto.JwtErrorResponse;
import karuna.karuna_backend.security.jwt.JwtConfig;
import karuna.karuna_backend.security.jwt.JwtTokenService;
import karuna.karuna_backend.user.domain.UserService;
import karuna.karuna_backend.user.dto.UserDTO;
import karuna.karuna_backend.user.dto.authentication.LoginRequestDto;
import karuna.karuna_backend.user.dto.authentication.LoginResponseDto;
import karuna.karuna_backend.user.dto.authentication.RegisterRequestDto;
import karuna.karuna_backend.user.dto.authentication.RegisterResponseDto;
import karuna.karuna_backend.utils.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
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
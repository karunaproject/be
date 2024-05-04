package karuna.karuna_backend.AuthenticationTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import karuna.karuna_backend.Authentication.CustomUserDetails;
import karuna.karuna_backend.Authentication.JWT.JwtConfig;
import karuna.karuna_backend.Authentication.JWT.JwtTokenService;
import karuna.karuna_backend.Models.Role;
import karuna.karuna_backend.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.Duration;
import java.util.*;


@ExtendWith(MockitoExtension.class)
public class JwtTokenServiceTest {
    @Mock
    private JwtConfig jwtConfig;
    private JwtTokenService jwtTokenService;

    private CustomUserDetails userDetails;

    @BeforeEach
    void setUp(){
        when(jwtConfig.getSecretKey()).thenReturn("sGkXqJf3chLX7T52V7kPOj+KSLPZBEyeU9HxniZb2Sw");
        when(jwtConfig.getTokenExpirationTime()).thenReturn(Duration.ofHours(1));
        when(jwtConfig.getRefreshTokenExpirationTime()).thenReturn(Duration.ofHours(1));

        jwtTokenService = new JwtTokenService(jwtConfig);

        User user = User.builder()
                .username("username")
                .password("password")
                .roles(Collections.singletonList(new Role("ROLE_USER")))
                .build();
        userDetails = new CustomUserDetails(user);
    }
    @Test
    public void testGeneratedToken() {
        String token = jwtTokenService.generateToken(userDetails);
        assertNotNull(token, "Token should not be null");
        assertDoesNotThrow(() -> jwtTokenService.verifyToken(token), "Token should be valid and verification should not throw any exception");
        assertEquals("username", jwtTokenService.getSubject(token), "Subject should match the username");
        List<String> roles = jwtTokenService.getRoles(token);
        assertTrue(roles.contains("ROLE_USER"), "Token should contain the correct role");
    }
    @Test
    public void testGeneratedTokenWithClaims(){
        Map<String, Object> additionalClaims = new HashMap<>();
        additionalClaims.put("test", "test");
        String token = jwtTokenService.generateToken(userDetails, additionalClaims);

        assertEquals("test", jwtTokenService.getAllClaims(token).get("test"), "Additional claims were not set properly");
        assertNotNull(token, "Token should not be null");
        assertDoesNotThrow(() -> jwtTokenService.verifyToken(token), "Token should be valid and verification should not throw any exception");
    }
    @Test
    public void testTokenExpiration() {
        when(jwtConfig.getTokenExpirationTime()).thenReturn(Duration.ofMillis(1));
        jwtTokenService = new JwtTokenService(jwtConfig);

        String token = jwtTokenService.generateToken(userDetails);
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertThrows(ExpiredJwtException.class, () -> jwtTokenService.verifyToken(token), "Token should be expired");
    }

}
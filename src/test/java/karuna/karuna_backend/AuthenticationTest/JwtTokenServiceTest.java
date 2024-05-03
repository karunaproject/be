package karuna.karuna_backend.AuthenticationTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import karuna.karuna_backend.Authentication.CustomUserDetails;
import karuna.karuna_backend.Authentication.JWT.JwtConfig;
import karuna.karuna_backend.Authentication.JWT.JwtTokenService;
import karuna.karuna_backend.Models.Role;
import karuna.karuna_backend.Models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Key;
import java.time.Duration;
import java.util.*;


@SpringBootTest
public class JwtTokenServiceTest {

    @Mock
    private JwtConfig jwtConfig;

    @InjectMocks
    private JwtTokenService jwtTokenService;


    private CustomUserDetails user;
    private Key signingKey;

    @BeforeEach
    void setUp()  {
        when(jwtConfig.getSignatureAlgorithm()).thenReturn("HS256");
        when(jwtConfig.getTokenExpirationTime()).thenReturn(Duration.ofHours(2));
        when(jwtConfig.getSecretKey()).thenReturn("secret123");


        SignatureAlgorithm algorithm = SignatureAlgorithm.forName(jwtConfig.getSignatureAlgorithm());
        this.signingKey = Keys.secretKeyFor(algorithm);

        try {

            Method postConstructMethod = JwtTokenService.class.getDeclaredMethod("init");
            postConstructMethod.setAccessible(true);
            postConstructMethod.invoke(jwtTokenService);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
        List<Role> roles = Collections.singletonList(
                Role.builder()
                        .name("ROLE_USER")
                        .build()
        );

        user = new CustomUserDetails(User.builder()
                .username("test123")
                .roles(roles)
                .build());
    }

    @Test
    void testTokenIsGenerated() {
        String token = jwtTokenService.generateToken(user);
        assertNotNull(token, "Token should not be null");
    }

    @Test
    void testTokenContents() {
        String token = jwtTokenService.generateToken(user);

        Jws<Claims> claims = Jwts.parserBuilder().
                setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token);
        assertEquals(user.getUsername(), claims.getBody().getSubject(), "Username should match");
        assertEquals(claims.getBody().get("roles", List.class), user.getAuthorities(), "User roles should match those in jwt claims");
        assertTrue(claims.getBody().getExpiration().after(new Date()), "Token should have correct expiration");
    }
}
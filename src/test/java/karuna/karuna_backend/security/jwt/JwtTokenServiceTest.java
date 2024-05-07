package karuna.karuna_backend.security.jwt;


import io.jsonwebtoken.ExpiredJwtException;
import karuna.karuna_backend.Constants;
import karuna.karuna_backend.security.CustomUserDetails;
import karuna.karuna_backend.user.dto.UserDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtTokenServiceTest {

    private JwtTokenService jwtTokenService = JwtTokenTestConfiguration.jwtTokenService();
    private CustomUserDetails userDetails = new CustomUserDetails(new UserDTO(1L, Constants.USERNAME, Constants.PASSWORD, List.of(Constants.ROLE_USER)));

    @Test
    public void testGeneratedToken() {
        String token = jwtTokenService.generateToken(userDetails);
        List<String> roles = jwtTokenService.getRoles(token);

        assertNotNull(token, "Token should not be null");
        assertDoesNotThrow(() -> jwtTokenService.verifyToken(token), "Token should be valid and verification should not throw any exception");
        assertEquals(Constants.USERNAME, jwtTokenService.getSubject(token), "Subject should match the username");
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
        String token = jwtTokenService.generateToken(userDetails);
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertThrows(ExpiredJwtException.class, () -> jwtTokenService.verifyToken(token), "Token should be expired");
    }
}
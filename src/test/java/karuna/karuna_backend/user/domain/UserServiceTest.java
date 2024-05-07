package karuna.karuna_backend.user.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import karuna.karuna_backend.Constants;
import karuna.karuna_backend.user.dto.UserDTO;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private UserService userService = UserTestConfiguration.userService();

    @Test
    void shouldFoundUserById() {
        //given
        long id = 1L;
        //when
        Optional<UserDTO> userDTOOptional = userService.getUserById(id);
        //then
        assertTrue(userDTOOptional.isPresent());
        UserDTO userDTO = userDTOOptional.get();
        assertEquals(1L, userDTO.id());
        assertEquals(Constants.USERNAME, userDTO.username());
        assertEquals(Constants.PASSWORD, userDTO.password());
        assertEquals(Constants.ROLE_USER, userDTO.roles().get(0));
    }

    @Test
    void shouldFoundUserByUsername() {
        //given
        String username = Constants.USERNAME;
        //when
        Optional<UserDTO> userDTOOptional = userService.getUserByUsername(username);
        //then
        assertTrue(userDTOOptional.isPresent());
        UserDTO userDTO = userDTOOptional.get();
        assertEquals(1L, userDTO.id());
        assertEquals(Constants.USERNAME, userDTO.username());
        assertEquals(Constants.PASSWORD, userDTO.password());
        assertEquals(Constants.ROLE_USER, userDTO.roles().get(0));
    }

    @Test
    void shouldRegisterUser() {
        //given
        String username = Constants.USERNAME;
        String password = Constants.PASSWORD;
        //when
        String token = userService.registerUser(username, password);
        //then
        assertNotNull(token);
        String subject = extractSubject(token);
        assertEquals(Constants.USERNAME, subject);
    }

    private String extractSubject(String token) {
        String secretKey = Constants.SECRET_KEY;
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKey signingKey = Keys.hmacShaKeyFor(keyBytes);
        Claims claims = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    @Test
    void shouldAuthenticateUser() {
        //given
        String username = Constants.USERNAME;
        String password = Constants.PASSWORD;
        //when
        String token = userService.authenticateUser(username, password);
        //then
        assertNotNull(token);
        String subject = extractSubject(token);
        assertEquals(Constants.USERNAME, subject);
    }
}
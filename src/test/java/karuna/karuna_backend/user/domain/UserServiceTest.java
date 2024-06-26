package karuna.karuna_backend.user.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.ConstraintViolationException;
import karuna.karuna_backend.Constants;
import karuna.karuna_backend.user.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private final UserService userService = UserTestConfiguration.userService();
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setUp() {
        UserTestConfiguration.clearDatabase();
    }

    @Test
    void shouldFoundUserById() {
        //given: Register user for tests
        String username = Constants.USERNAME;
        String password = Constants.PASSWORD;
        userService.registerUser(username, password);
        long id = 1L;
        //when: When getting user returns dto
        Optional<UserDTO> userDTOOptional = userService.getUserById(id);
        //then: Check if is current user
        assertTrue(userDTOOptional.isPresent());
        UserDTO userDTO = userDTOOptional.get();
        assertEquals(1L, userDTO.id());
        assertEquals(Constants.USERNAME, userDTO.username());
        assertTrue(bCryptPasswordEncoder.matches(Constants.PASSWORD, userDTO.password()));
        assertEquals(Constants.ROLE_USER, userDTO.roles().get(0));
    }

    @Test
    void shouldNotFoundUserById() {
        //when: When getting user return empty optional
        long id = 1;
        Optional<UserDTO> userDTOOptional = userService.getUserById(id);
        //then: Check if optional is empty
        assertTrue(userDTOOptional.isEmpty());
    }

    @Test
    void shouldFoundUserByUsername() {
        //given: Register user for tests
        String username = Constants.USERNAME;
        String password = Constants.PASSWORD;
        userService.registerUser(username, password);
        //when: When getting user returns dto
        Optional<UserDTO> userDTOOptional = userService.getUserByUsername(Constants.USERNAME);
        //then: Check if is current user
        assertTrue(userDTOOptional.isPresent());
        UserDTO userDTO = userDTOOptional.get();
        assertEquals(1L, userDTO.id());
        assertEquals(Constants.USERNAME, userDTO.username());
        assertTrue(bCryptPasswordEncoder.matches(Constants.PASSWORD, userDTO.password()));
        assertEquals(Constants.ROLE_USER, userDTO.roles().get(0));
    }

    @Test
    void shouldNotFoundUserByUsername() {
        //when: When getting user return empty optional
        String username = Constants.USERNAME;
        Optional<UserDTO> userDTOOptional = userService.getUserByUsername(username);
        //then: Check if optional is empty
        assertTrue(userDTOOptional.isEmpty());
    }
    @Test
    void shouldRegisterUser() {
        //when: When user service register user returns token
        String username = Constants.USERNAME;
        String password = Constants.PASSWORD;
        String token = userService.registerUser(username, password);
        //then: Check if token have username in subject
        assertNotNull(token);
        String subject = extractSubject(token);
        assertEquals(Constants.USERNAME, subject);
    }

    @Test
    void shouldNotRegisterUser() {
        //given: Register user for block unique name
        String username = Constants.USERNAME;
        String password = Constants.PASSWORD;
        userService.registerUser(username, password);
        //when/then: When register user again then throw error
        assertThrows(ConstraintViolationException.class, () -> userService.registerUser(username, password));
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
        //given: Register user for tests
        String username = Constants.USERNAME;
        String password = Constants.PASSWORD;
        userService.registerUser(username, password);
        //when: When user service authenticate user returns token
        String token = userService.authenticateUser(username, password);
        //then: Check if  token have username in subject
        assertNotNull(token);
        String subject = extractSubject(token);
        assertEquals(Constants.USERNAME, subject);
    }
}
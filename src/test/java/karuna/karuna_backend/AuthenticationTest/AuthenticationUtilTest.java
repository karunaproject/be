package karuna.karuna_backend.AuthenticationTest;

import karuna.karuna_backend.Authentication.CustomUserDetails;
import karuna.karuna_backend.Authentication.Utils.AuthenticationUtil;
import karuna.karuna_backend.Models.Role;
import karuna.karuna_backend.Models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationUtilTest {

    private Authentication authentication;

    @BeforeEach
    void setup(){
         authentication = new TestingAuthenticationToken(
                "username",
                null,
                "ROLE_USER");
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void testGetUsernameWithUserDetails() {
        User user = User.builder()
                .username("username")
                .password("password")
                .roles(Collections.singletonList(new Role("ROLE_USER")))
                .build();
        UserDetails userDetails = new CustomUserDetails(user);

        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken(userDetails,null,"ROLE_USER")
        );

        String username = AuthenticationUtil.getUsername();
        assertEquals("username", username);
    }

    @Test
    void testGetUsernameWithStringPrincipal() {
        String username = AuthenticationUtil.getUsername();
        assertEquals("username", username);
    }

    @Test
    void testGetUsernameUnauthenticated() {
        authentication.setAuthenticated(false);
        assertNull(AuthenticationUtil.getUsername());
    }

    @Test
    void testGetRolesAuthenticated() {
        assertEquals(List.of("ROLE_USER"), AuthenticationUtil.getRoles());
    }

    @Test
    void testGetRolesUnauthenticated() {
        authentication.setAuthenticated(false);
        assertTrue(AuthenticationUtil.getRoles().isEmpty());
    }

    @Test
    void testGetRolesNoAuthorities() {
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken(null,null,Collections.emptyList())
        );
        assertTrue(AuthenticationUtil.getRoles().isEmpty());
    }
}




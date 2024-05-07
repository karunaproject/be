package karuna.karuna_backend.utils;

import karuna.karuna_backend.Constants;
import karuna.karuna_backend.security.CustomUserDetails;
import karuna.karuna_backend.user.dto.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthenticationUtilTest {

    private Authentication authentication;

    @BeforeEach
    void setup(){
        authentication = new TestingAuthenticationToken(
                Constants.USERNAME,
                null,
                Constants.ROLE_USER);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void testGetUsernameWithUserDetails() {
        CustomUserDetails userDetails = new CustomUserDetails(new UserDTO(1L, Constants.USERNAME, Constants.PASSWORD, List.of(Constants.ROLE_USER)));

        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken(userDetails,null,Constants.ROLE_USER)
        );

        String username = AuthenticationUtil.getUsername();
        assertEquals(Constants.USERNAME, username);
    }

    @Test
    void testGetUsernameWithStringPrincipal() {
        String username = AuthenticationUtil.getUsername();
        assertEquals(Constants.USERNAME, username);
    }

    @Test
    void testGetUsernameUnauthenticated() {
        authentication.setAuthenticated(false);
        assertNull(AuthenticationUtil.getUsername());
    }

    @Test
    void testGetRolesAuthenticated() {
        assertEquals(List.of(Constants.ROLE_USER), AuthenticationUtil.getRoles());
    }

    @Test
    void testGetRolesUnauthenticated() {
        authentication.setAuthenticated(false);
        assertTrue(AuthenticationUtil.getRoles().isEmpty());
    }

    @Test
    void testGetRolesNoAuthorities() {
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken(null,null, Collections.emptyList())
        );
        assertTrue(AuthenticationUtil.getRoles().isEmpty());
    }
}

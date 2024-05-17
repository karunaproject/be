package karuna.karuna_backend.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecurityContextUtilTest {
    @BeforeEach
    public void setUp() {
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void testSetSecurityContext() {
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
        SecurityContextUtil.setSecurityContext("username", roles);

        SecurityContext context = SecurityContextHolder.getContext();
        assertNotNull(context.getAuthentication());
        assertTrue(context.getAuthentication().isAuthenticated());

        Authentication authentication = context.getAuthentication();
        assertEquals("username", authentication.getName(), "Username should be equal");
        assertEquals(2, authentication.getAuthorities().size(), "Size of role list should be equal in the one provided");
        assertTrue(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")), "Provided roles are not matching with authentication object");
        assertTrue(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")),"Provided roles are not matching with authentication object");
    }

    @Test
    void testSetSecurityContextWithNullUsername() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                SecurityContextUtil.setSecurityContext(null, List.of("ROLE_USER")), "Exception should be thrown"
        );
    }

    @Test
    void testSetSecurityContextWithNullRoles() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                SecurityContextUtil.setSecurityContext("username", null),"Exception should be thrown"
        );
    }
}

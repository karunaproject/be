package karuna.karuna_backend.Authentication.Utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AuthenticationUtil {

    private static final String ANONYMOUS_USER = "anonymousUser";

    /**
     * Retrieves the username of the currently authenticated user from the security context.
     *
     * @return The username of the currently authenticated user, or {@code null} if the user is not authenticated
     * or is recognized as an anonymous user.
     */
    public static String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !ANONYMOUS_USER.equals(authentication.getPrincipal())) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else if (principal instanceof String) {
                return (String) principal;
            }
        }
        return null; //TODO: or throw an exception if strict handling is required
    }

    /**
     * Retrieves a list of role names associated with the currently authenticated user.
     *
     * @return A list of role names if the user is authenticated, or an empty list if no roles are found
     * or the user is not authenticated.
     */
    public static List<String> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList(); // empty list if no roles or not authenticated
    }
}
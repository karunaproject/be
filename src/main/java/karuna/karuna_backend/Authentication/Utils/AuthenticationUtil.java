package karuna.karuna_backend.Authentication.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthenticationUtil {

    private static final String ANONYMOUS_USER = "anonymousUser";

    /**
     * Retrieves the current {@link Authentication} object if it's valid.
     * <p>
     * This method checks if the authentication object from the security context is non-null,
     * authenticated, and not marked as anonymous.
     *
     * @return an {@link Optional} containing the {@link Authentication} object if valid, empty otherwise.
     */
    private static Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(Authentication::isAuthenticated)
                .filter(auth -> !ANONYMOUS_USER.equals(auth.getPrincipal()));
    }

    /**
     * Retrieves the username of the currently authenticated user.
     * <p>
     * This method extracts the username from the authentication principal, which can be either
     * a {@link UserDetails} object or a simple {@link String}.
     *
     * @return the username as a {@link String}, or {@code null} if the user is not authenticated.
     */
    public static String getUsername() {
        return getAuthentication()
                .map(auth -> {
                    Object principal = auth.getPrincipal();
                    if (principal instanceof UserDetails) {
                        return ((UserDetails) principal).getUsername();
                    } else if (principal instanceof String) {
                        return (String) principal;
                    }
                    return null; // Consider whether you want to throw an exception here
                })
                .orElse(null); // or .orElseThrow(() -> new UsernameNotFoundException("User not authenticated"));
    }

    /**
     * Retrieves a list of role names associated with the currently authenticated user.
     * <p>
     * If the user is authenticated, this method returns a list of strings representing the role names.
     * If the user is not authenticated or has no roles, it returns an empty list.
     *
     * @return a {@link List} of role names, never {@code null}.
     */
    public static List<String> getRoles() {
        return getAuthentication()
                .map(auth -> auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .orElse(Collections.emptyList());
    }
}
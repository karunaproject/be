package karuna.karuna_backend.Authentication.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class to manipulate the Spring Security Context.
 * Provides methods to programmatically set the security context using a username and roles.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityContextUtil {

    /**
     * Sets the security context with a specific username and a list of roles.
     * This method constructs a {@link UsernamePasswordAuthenticationToken} and registers it with the
     * {@link SecurityContextHolder} to establish security context for subsequent security checks.
     *
     * @param username the username to be set in the security context, must not be null.
     * @param roles    a list of role names to be converted into {@link GrantedAuthority} and set in the security context,
     *                 must not be null.
     * @throws IllegalArgumentException if either username or roles is null.
     * @throws SecurityException        if setting the security context fails.
     */
    public static void setSecurityContext(String username, List<String> roles) {

        //TODO: Refactor that into CustomException most likely
        Assert.notNull(username, "Username must not be null");
        Assert.notNull(roles, "Roles must not be null");

        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}

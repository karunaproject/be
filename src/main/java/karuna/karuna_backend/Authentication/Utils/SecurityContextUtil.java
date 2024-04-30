package karuna.karuna_backend.Authentication.Utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityContextUtil {

    public static void setSecurityContext(String username, List<String> roles) {

        //TODO: Refactor that into CustomException most likely
        Assert.notNull(username, "Username must not be null");
        Assert.notNull(roles, "Roles must not be null");

        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, null, authorities);

        try {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        }  //TODO: Handle exception globally
         catch (Exception e) {
            // Proper error handling (e.g., logging) should be done here.
            throw new SecurityException("Failed to set security context", e);
        }
    }
}

package karuna.karuna_backend.security.config.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import karuna.karuna_backend.exception.dto.JwtErrorResponse;
import karuna.karuna_backend.exception.keys.JwtErrorKey;
import karuna.karuna_backend.security.jwt.JwtConfig;
import karuna.karuna_backend.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final JwtTokenService jwtTokenService;
    private final JwtConfig jwtConfig;


    /**
     * Internal filter method that executes once per request to authenticate users based on JWT.
     *
     * @param request The servlet request.
     * @param response The servlet response.
     * @param filterChain The filter chain allowing the request to proceed with the next filter in the chain.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs during request processing.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(jwtConfig.getAuthorizationHeader());
        if (header != null && header.startsWith(jwtConfig.getTokenPrefix())) {
            String token = header.replace(jwtConfig.getTokenPrefix(), "");
            try {
                jwtTokenService.verifyToken(token);
            } catch (Exception e) {
                handleException(response, e);
                return; // Stop further processing
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Handles exceptions thrown during JWT token verification by setting appropriate HTTP response.
     *
     * @param response The servlet response to write the error details to.
     * @param e The exception that was caught during JWT verification.
     * @throws IOException If an error occurs during response writing.
     */
    private void handleException(HttpServletResponse response, Exception e) throws IOException {
        JwtErrorKey errorMessageKey;
        if (e instanceof ExpiredJwtException) {
            errorMessageKey = JwtErrorKey.TOKEN_HAS_EXPIRED;
        } else if (e instanceof SignatureException) {
            errorMessageKey = JwtErrorKey.TOKEN_VERIFICATION_FAILURE;
        } else {
            errorMessageKey = JwtErrorKey.UNKNOWN_ERROR;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        JwtErrorResponse responseDto = new JwtErrorResponse(errorMessageKey, e.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(responseDto));
    }
}

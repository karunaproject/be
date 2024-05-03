package karuna.karuna_backend.Config.Filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import karuna.karuna_backend.Authentication.JWT.JwtTokenService;
import karuna.karuna_backend.Errors.AuthenticateExceptions.JwtAuthenticationException;
import karuna.karuna_backend.Errors.DTO.AuthenticationErrorResponse;
import karuna.karuna_backend.Errors.DTO.JwtErrorResponse;
import karuna.karuna_backend.Errors.ErrorKeys.JwtErrorKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final JwtTokenService jwtTokenService;

    private String errorMessageKey;
    private String errorMessageDescription;

    @Autowired
    public JwtFilter(JwtTokenService jwtTokenService){
        this.jwtTokenService=jwtTokenService;
    }

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

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.replace("Bearer ", "");
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

package karuna.karuna_backend.Config.Filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import karuna.karuna_backend.Authentication.JWT.JwtTokenService;
import karuna.karuna_backend.Errors.DTO.AuthenticationErrorResponse;
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

    private void handleException(HttpServletResponse response, Exception e) throws IOException {
        String errorMessageKey;
        if (e instanceof ExpiredJwtException) {
            errorMessageKey = "token_has_expired";
        } else if (e instanceof SignatureException) {
            errorMessageKey = "token_verification_failure";
        } else {
            errorMessageKey = "unknown_error";
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        AuthenticationErrorResponse responseDto = new AuthenticationErrorResponse(errorMessageKey, e.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(responseDto));
    }
}

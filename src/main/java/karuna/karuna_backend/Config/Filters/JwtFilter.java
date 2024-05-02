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

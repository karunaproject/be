package karuna.karuna_backend.security.config;


import karuna.karuna_backend.security.config.filters.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
class WebSecurityConfig {

    private final JwtFilter jwtFilter;

    /**
     * Configures the security filter chain for HTTP requests to enforce security policies.
     * This method customizes the HttpSecurity configuration to create a stateless application environment
     * by disabling CSRF protection and enforcing stateless session management. It also configures public access
     * to all endpoints (for now) and enables default configurations for form login and HTTP basic authentication.
     *
     * @param http the {@link HttpSecurity} that allows configuring web-based security for specific http requests.
     * @return the configured {@link SecurityFilterChain} instance that Spring Security will use to handle security concerns.
     * @throws Exception if an error occurs during the configuration of {@link HttpSecurity}.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(this::configureAuthorizeHttpRequests)
                .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

    private AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry configureAuthorizeHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        return configurePublicHttpRequests(auth)
                .requestMatchers(HttpMethod.GET, "/contents/**")
                .permitAll()
                .anyRequest().authenticated();
    }

    private static AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry configurePublicHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        return auth
                .requestMatchers(
                        "/login",
                        "/register",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/visitors/messages/**")
                .permitAll();
    }
}
package karuna.karuna_backend.user.domain

import karuna.karuna_backend.Constants
import karuna.karuna_backend.security.jwt.JwtConfig
import karuna.karuna_backend.security.jwt.JwtTokenServiceImpl
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import java.time.Duration

class UserConfiguration{

    private static final String SECRET_KEY = "sGkXqJf3chLX7T52V7kPOj+KSLPZBEyeU9HxniZb2Sw"
    private static final String TOKEN_PREFIX = "TOKEN_PREFIX"
    private static UserRepository userRepository = new MockUserRepository()

    static UserService userService() {
        JwtConfig jwtConfig = new JwtConfig()
        jwtConfig.setSecretKey(SECRET_KEY)
        jwtConfig.setTokenPrefix(TOKEN_PREFIX)
        jwtConfig.setTokenExpirationTime(Duration.ofSeconds(1))
        new UserService(new MockAuthenticationManager(), new JwtTokenServiceImpl(jwtConfig), userRepository, new MockRoleRepository(), new BCryptPasswordEncoder())
    }

    static void cleanDatabase() {
        userRepository.deleteAll()
    }

    static UserRepository userRepository() {
        return userRepository
    }
}

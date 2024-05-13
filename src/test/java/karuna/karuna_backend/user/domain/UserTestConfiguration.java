package karuna.karuna_backend.user.domain;

import karuna.karuna_backend.TestJwtConfig;
import karuna.karuna_backend.security.jwt.JwtTokenService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTestConfiguration {

    private static UserRepository userRepository = new InMemoryUserRepository();

    static UserService userService() {
        return new UserService(new TestAuthManager(), new JwtTokenService(new TestJwtConfig()), userRepository, new InMemoryRoleRepository(), new BCryptPasswordEncoder());
    }

    static void clearDatabase() {
        userRepository.deleteAll();
    }
}

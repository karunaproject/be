package karuna.karuna_backend.user.domain

import org.springframework.security.crypto.password.PasswordEncoder

class MockPasswordEncoder implements PasswordEncoder{
    @Override
    String encode(CharSequence rawPassword) {
        rawPassword
    }

    @Override
    boolean matches(CharSequence rawPassword, String encodedPassword) {
        rawPassword == encodedPassword
    }
}

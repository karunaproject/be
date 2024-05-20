package karuna.karuna_backend.exception.constraintViolation;

import karuna.karuna_backend.exception.keys.ConstraintViolationKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstraintViolationKeyResolver {

    static ConstraintViolationKey getKey(String message) {
        if (Objects.nonNull(message)) {
            switch (message.toLowerCase()) {
                case "invalid email address":
                    return ConstraintViolationKey.INVALID_EMAIL_PATTERN;
                case "size must be between 5 and 320":
                    return ConstraintViolationKey.LENGTH_IS_INCORRECT;
            }
        }
        return ConstraintViolationKey.UNKNOWN_ERROR;
    }
}

package karuna.karuna_backend.user.dto.authentication;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record LoginResponseDto(String username, OffsetDateTime tokenExpirationTime){}
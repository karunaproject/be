package karuna.karuna_backend.Authentication.DTO;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Date;

//@Builder
//@Getter
//public class LoginResponseDto {
//    private String username;
//    //private String jwt;
//    private OffsetDateTime tokenExpirationTime;
//}

@Builder
public record LoginResponseDto(String username, OffsetDateTime tokenExpirationTime){}
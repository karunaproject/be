package karuna.karuna_backend.user.dto.authentication;


public record RegisterRequestDto(String username, String password) {
    //TODO: Implement hiberante validators, therefore add dependency
}

package karuna.karuna_backend.Exceptions;

public class UserNotFound extends RuntimeException{

    public UserNotFound(String message) {
        super(message);
    }
}

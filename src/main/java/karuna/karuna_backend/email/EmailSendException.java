package karuna.karuna_backend.email;

public class EmailSendException extends RuntimeException {

    public EmailSendException(String email) {
        super("Can not send email to %s".formatted(email));
    }
}

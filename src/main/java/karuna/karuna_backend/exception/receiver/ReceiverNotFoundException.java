package karuna.karuna_backend.exception.receiver;

public class ReceiverNotFoundException  extends RuntimeException {

    public ReceiverNotFoundException(String email) {
        super("Recipient %s not found.".formatted(email));
    }
}

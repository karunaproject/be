package karuna.karuna_backend.exception.receiver;

public class ReceiverNotFoundException  extends RuntimeException {

    public ReceiverNotFoundException() {
        super("Recipient not found.");
    }
}

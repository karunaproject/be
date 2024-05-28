package karuna.karuna_backend.receiver.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReceiverTestConfiguration {

    private static final ReceiverRepository receiverRepository = new InMemoryReceiverRepository();

    static ReceiverService receiverService() {
        return new ReceiverService(receiverRepository);
    }

    public static void clearDatabase() {
        receiverRepository.deleteAll();
    }
}

package karuna.karuna_backend.receiver.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReceiverTestConfiguration {

    static ReceiverService receiverService() {
        return new ReceiverService(new InMemoryReceiverRepository());
    }
}

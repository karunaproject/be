package karuna.karuna_backend.visitor.message.domain;

import karuna.karuna_backend.TestEmailSender;
import karuna.karuna_backend.receiver.domain.InMemoryReceiverRepository;
import karuna.karuna_backend.receiver.domain.ReceiverService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class VisitorMessageTestConfiguration {

    static ReceiverService receiverService() {
        return new ReceiverService(new InMemoryReceiverRepository());
    }
    static VisitorMessageService visitorMessageService() {
        return new VisitorMessageService(new InMemoryVisitorMessageRepository(), new TestEmailSender(), receiverService());
    }

}

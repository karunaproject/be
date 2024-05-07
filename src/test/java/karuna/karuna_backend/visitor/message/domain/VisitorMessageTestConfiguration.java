package karuna.karuna_backend.visitor.message.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class VisitorMessageTestConfiguration {

    static VisitorMessageService visitorMessageService() {
        return new VisitorMessageService(new InMemoryVisitorMessageRepository());
    }

}

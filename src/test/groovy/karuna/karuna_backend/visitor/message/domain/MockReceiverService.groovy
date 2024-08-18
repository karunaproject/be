package karuna.karuna_backend.visitor.message.domain

import karuna.karuna_backend.Constants
import karuna.karuna_backend.receiver.domain.ReceiverService
import karuna.karuna_backend.receiver.dto.ReceiversDTO

class MockReceiverService extends ReceiverService implements Constants {

    MockReceiverService() {
        super(null)
    }

    @Override
    ReceiversDTO getAllReceivers() {
        new ReceiversDTO(Set.of(TO))
    }
}

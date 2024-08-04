package karuna.karuna_backend.receiver.domain

class ReceiverConfiguration {

    static ReceiverService receiverService () {
        new ReceiverService(new MockReceiverRepository())
    }
}

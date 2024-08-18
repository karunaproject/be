package karuna.karuna_backend.receiver.domain

class ReceiverConfiguration {

    private static ReceiverRepository receiverRepository = new MockReceiverRepository()

    static ReceiverService receiverService () {
        new ReceiverService(receiverRepository)
    }

    static void clearDatabase() {
        receiverRepository.deleteAll()
    }
}

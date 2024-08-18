package karuna.karuna_backend.visitor.message.domain

class VisitorMessageConfiguration {

    private static VisitorMessageRepository visitorMessageRepository = new MockVisitorMessageRepository()

    static VisitorMessageService visitorMessageService () {
        new VisitorMessageService(visitorMessageRepository, new MockEmailSender(), new MockReceiverService())
    }

    static void clearDatabase() {
        visitorMessageRepository.deleteAll()
    }
}

package karuna.karuna_backend.email

class EmailConfiguration {

    static EmailSender emailSender() {
        new MailjetEmailSender(emailConfig())
    }

    private static EmailConfig emailConfig() {
        EmailConfig emailConfig = new EmailConfig()
        emailConfig.secretKey = "52ae2f8eee72475c4fd2d0d3891a2145"
        emailConfig.apiKey = "c043cff10190f715e71898289ee830d0"
        emailConfig.from = "KARUNA@KASTRUJEMYBEZDOMNOSC.PL"
        emailConfig
    }
}

package karuna.karuna_backend.email;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MailjetEmailConfig {

    static EmailSender emailSender() {
        EmailConfig emailConfig = new EmailConfig();
        emailConfig.setFrom("TEST@TEST.PL");
        emailConfig.setApiKey("c043cff10190f715e71898289ee830d0");
        emailConfig.setSecretKey("52ae2f8eee72475c4fd2d0d3891a2145");
        return new MailjetEmailSender(emailConfig);
    }
}

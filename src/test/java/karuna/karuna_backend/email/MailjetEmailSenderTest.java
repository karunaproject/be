package karuna.karuna_backend.email;

import karuna.karuna_backend.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MailjetEmailSenderTest {

    private EmailSender emailSender = MailjetEmailConfig.emailSender();

    @Test
    void shouldSendEmail() {
        assertDoesNotThrow(() -> emailSender.sendEmail(Constants.TEST_DATA,Constants.TEST_DATA,Constants.TEST_DATA,Constants.TEST_DATA));
    }

}
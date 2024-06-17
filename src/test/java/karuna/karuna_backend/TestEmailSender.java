package karuna.karuna_backend;

import karuna.karuna_backend.email.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;

@Slf4j
public class TestEmailSender implements EmailSender {
    @Override
    public void sendEmail(String to, String subject, String contentText, String contentHtml) {
        log.info("Sent message to %s with subject %s and with content %s / %s".formatted(to, subject, contentText, contentHtml));
    }

    @Override
    public void sendEmails(JSONArray to, String subject, String contentText, String contentHtml) {
        log.info("Messages have been sent to all recipients");
    }
}

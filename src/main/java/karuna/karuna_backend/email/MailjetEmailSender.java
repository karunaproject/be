package karuna.karuna_backend.email;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.resource.Emailv31;
import karuna.karuna_backend.exception.email.EmailSendException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
class MailjetEmailSender implements EmailSender {

    private final EmailConfig emailConfig;

    @Override
    public void sendEmail(String to, String subject, String contentText, String contentHtml) {
        try {
            MailjetClient client = buildMailjetClient();
            MailjetRequest request = configureRequest(to, subject, contentText, contentHtml);
            client.post(request);
            log.info("Success sent message!");
        } catch (MailjetException ex) {
            throw new EmailSendException(to);
        }
    }

    @NotNull
    private MailjetClient buildMailjetClient() {
        return new MailjetClient(System.getenv("MJ_APIKEY_PUBLIC"), System.getenv("MJ_APIKEY_PRIVATE"));
    }

    private MailjetRequest configureRequest(String to, String subject, String contentText, String contentHtml) {
        return new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", emailConfig.getFrom())
                                        .put("Name", emailConfig.getFrom()))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", to)
                                                .put("Name", to)))
                                .put(Emailv31.Message.SUBJECT, subject)
                                .put(Emailv31.Message.TEXTPART, contentText)
                                .put(Emailv31.Message.HTMLPART, contentHtml)));
    }
}

package karuna.karuna_backend.email;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class MailjetEmailSender implements EmailSender {

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
    private static MailjetClient buildMailjetClient() {
        return new MailjetClient(System.getenv("MJ_APIKEY_PUBLIC"), System.getenv("MJ_APIKEY_PRIVATE"));
    }

    private static MailjetRequest configureRequest(String to, String subject, String contentText, String contentHtml) {
        return new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "KARUNA@KASTRUJEMYBEZDOMNOSC.PL")
                                        .put("Name", "KARUNA@KASTRUJEMYBEZDOMNOSC.PL"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", to)
                                                .put("Name", to)))
                                .put(Emailv31.Message.SUBJECT, subject)
                                .put(Emailv31.Message.TEXTPART, contentText)
                                .put(Emailv31.Message.HTMLPART, contentHtml)));
    }
}

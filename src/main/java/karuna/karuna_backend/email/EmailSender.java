package karuna.karuna_backend.email;

import org.json.JSONArray;

public interface EmailSender {
    void sendEmail(String to, String subject, String contentText, String contentHtml);

    void sendEmails(JSONArray to, String subject, String contentText, String contentHtml);
}

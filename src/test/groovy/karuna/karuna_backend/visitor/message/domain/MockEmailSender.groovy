package karuna.karuna_backend.visitor.message.domain

import karuna.karuna_backend.email.EmailSender
import org.json.JSONArray

class MockEmailSender implements EmailSender{

    @Override
    void sendEmail(String to, String subject, String contentText, String contentHtml) {

    }

    @Override
    void sendEmails(JSONArray to, String subject, String contentText, String contentHtml) {

    }
}

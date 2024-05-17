package karuna.karuna_backend.email;

public interface EmailSender {
    void sendEmail(String to, String subject, String contentText, String contentHtml);
}

package karuna.karuna_backend.email


import karuna.karuna_backend.Constants
import org.json.JSONArray
import spock.lang.Specification

class EmailSpec extends Specification implements Constants {

    EmailSender emailSender = EmailConfiguration.emailSender()

    def "should send single email" () {
        when: "Send email"
            emailSender.sendEmail(TO, SUBJECT, CONTENT_TEXT, CONTENT_HTML)
        then: "Email sent success"
            noExceptionThrown()
    }

    def "should send multiple emails" () {
        when: "Send emails"
            JSONArray toJsonArray = new JSONArray()
            toJsonArray.put(TO)
            emailSender.sendEmails(toJsonArray, SUBJECT, CONTENT_TEXT, CONTENT_HTML)
        then: "Emails sent success"
            noExceptionThrown()
    }
}

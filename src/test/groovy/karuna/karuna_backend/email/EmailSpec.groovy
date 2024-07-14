package karuna.karuna_backend.email

import karuna.karuna_backend.Constants
import spock.lang.Specification

class EmailSpec extends Specification implements Constants {

    EmailSender emailSender = EmailConfiguration.emailSender()

    def "should send email" () {
        when:
            emailSender.sendEmail(TO, SUBJECT, CONTENT_TEXT, CONTENT_HTML)
        then:
            noExceptionThrown()
    }
}

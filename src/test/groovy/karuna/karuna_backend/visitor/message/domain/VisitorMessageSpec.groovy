package karuna.karuna_backend.visitor.message.domain

import karuna.karuna_backend.Constants
import karuna.karuna_backend.visitor.message.dto.VisitorMessageCreateDto
import karuna.karuna_backend.visitor.message.dto.VisitorMessageDto
import karuna.karuna_backend.visitor.message.dto.VisitorMessageRequest
import karuna.karuna_backend.visitor.message.dto.VisitorMessageWrapper
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

class VisitorMessageSpec extends Specification implements Constants{

    private VisitorMessageService visitorMessageService = VisitorMessageConfiguration.visitorMessageService()

    def setup() {
        VisitorMessageConfiguration.clearDatabase()
    }

    def "should send message" () {
        when: "Send message with body: $BODY and contact: $CONTACT"
            VisitorMessageCreateDto request = new VisitorMessageCreateDto(BODY, CONTACT)
            VisitorMessageDto response = visitorMessageService.sendMessage(request)
        then: "Check if correct sent"
            response.body() == BODY
            response.contact() == CONTACT
    }

    def "should return empty page of messages" () {
        when: "Get messages when database is empty"
            VisitorMessageRequest request = new VisitorMessageRequest(PageRequest.of(0, 10), 10)
            VisitorMessageWrapper response = visitorMessageService.getMessages(request)
        then: "Check if returns empty page"
            response.messages().isEmpty()
    }

    def "should return page of messages" () {
        given: "Send message"
            VisitorMessageCreateDto sendRequest = new VisitorMessageCreateDto(BODY, CONTACT)
            visitorMessageService.sendMessage(sendRequest)
        when: "Get messages"
            VisitorMessageRequest request = new VisitorMessageRequest(PageRequest.of(0, 10), 10)
            VisitorMessageWrapper response = visitorMessageService.getMessages(request)
        then: "Check if returns empty page"
            !response.messages().isEmpty()
            VisitorMessageDto visitorMessageDto = response.messages().get(0)
            visitorMessageDto.contact() == CONTACT
            visitorMessageDto.body() == CUT_BODY
    }
}

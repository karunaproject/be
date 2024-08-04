package karuna.karuna_backend.receiver.domain

import karuna.karuna_backend.Constants
import karuna.karuna_backend.receiver.dto.ReceiverDTO
import karuna.karuna_backend.receiver.dto.ReceiverRequestDto
import karuna.karuna_backend.receiver.dto.ReceiversDTO
import spock.lang.Specification

class ReceiverSpec extends Specification implements Constants {

    private ReceiverService receiverService = ReceiverConfiguration.receiverService()

    def "should add receiver if not exists" () {
        when: "Add receiver"
            ReceiverRequestDto request = new ReceiverRequestDto(TO)
            ReceiverDTO response = receiverService.addReceiver(request)
        then: "Check if correct add receiver"
            response.email() == TO
    }

    def "should add receiver if exists" () {
        given: "Add receiver"
            ReceiverRequestDto request = new ReceiverRequestDto(TO)
            receiverService.addReceiver(request)
        when: "Add same receiver"
            ReceiverDTO response = receiverService.addReceiver(request)
        then: "Check if correct add receiver"
            response.email() == TO
    }

    def "should returns receivers" () {
        given: "Add receiver"
            ReceiverRequestDto request = new ReceiverRequestDto(TO)
            receiverService.addReceiver(request)
        when: "Get all receivers"
            ReceiversDTO response = receiverService.getAllReceivers()
        then: "Check if contain added receiver"
            !response.receivers().isEmpty()
            response.receivers().getAt(0).equals(TO)
    }
}

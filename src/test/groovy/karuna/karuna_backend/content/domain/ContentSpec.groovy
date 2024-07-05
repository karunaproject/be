package karuna.karuna_backend.content.domain

import karuna.karuna_backend.content.dto.MassContentDto
import karuna.karuna_backend.content.dto.MassContentWrapper
import karuna.karuna_backend.content.dto.MassContentWrapperRequest
import spock.lang.Specification

class ContentSpec extends Specification {

    def contentRepository = new MockContentRepository()

    def contentService = new ContentService(contentRepository)

    def "should not get content page" () {
        given: "Set up field for content page"
        def page = "Home"

        when:
        def contentDto = contentService.getContentByPage(page)

        then:
        contentDto.contents().isEmpty()
    }

    def "should get content page" () {
        given: "Set up fields for content page"
        def page = "Home"
        def key = "navigation.header"
        def value = "Welcome to my world!"

        and: "Add content page"
        def massContentDto = new MassContentDto(page,key,value)
        def request = new MassContentWrapperRequest(List.of(massContentDto))
        contentService.massAddContent(request)

        when: "Get content for page: $page"
        def contentDto = contentService.getContentByPage(page)

        then: "For key: $key content should have value: $value"
        !contentDto.contents().isEmpty()
        contentDto.contents().get(key) == value
    }

    def "should not add content page" () {
        given: "Set up fields for content page"
        def page = "Home"
        def key = "navigation.header"
        def value = "Welcome to my world!"

        and: "Add content page"
        def massContentDto = new MassContentDto(page,key,value)
        def request = new MassContentWrapperRequest(List.of(massContentDto))
        contentService.massAddContent(request)

        when: "Add content page again"
        MassContentWrapper massContentWrapper = contentService.massAddContent(request)

        then: "Invalid contents is not empty, so not save content page again"
        massContentWrapper.validContents().isEmpty()
        !massContentWrapper.invalidContents().isEmpty()
    }

    def "should add content page" () {
        given: "Set up fields for content page"
        def page = "Home"
        def key = "navigation.header"
        def value = "Welcome to my world!"
        def massContentDto = new MassContentDto(page,key,value)
        def request = new MassContentWrapperRequest(List.of(massContentDto))

        when: "Add content page"
        MassContentWrapper massContentWrapper = contentService.massAddContent(request)

        then: "Valid contents is not empty, so save content page"
        !massContentWrapper.validContents().isEmpty()
        massContentWrapper.invalidContents().isEmpty()
    }

    def "should update content page" () {
        given: "Set up fields for add content page"
        def page = "Home"
        def key = "navigation.header"
        def value = "Welcome to my world!"

        and: "Add content page"
        def massContentDto = new MassContentDto(page,key,value)
        def request = new MassContentWrapperRequest(List.of(massContentDto))
        contentService.massAddContent(request)

        and: "Set up fields for update content page"
        value = "Get the fuck out from my world!"
        massContentDto = new MassContentDto(page,key,value)
        request = new MassContentWrapperRequest(List.of(massContentDto))

        when: "Update content page"
        MassContentWrapper massContentWrapper = contentService.massUpdateContent(request)

        then: "Valid contents is not empty, so update content page"
        !massContentWrapper.validContents().isEmpty()
        massContentWrapper.invalidContents().isEmpty()
        massContentWrapper.validContents().get(0).key() == key
        massContentWrapper.validContents().get(0).page() == page
        massContentWrapper.validContents().get(0).valuePl() == value
    }

    def "should not update content page" () {
        given: "Set up fields for add content page"
        def page = "Home"
        def key = "navigation.header"
        def value = "Welcome to my world!"
        def massContentDto = new MassContentDto(page,key,value)
        def request = new MassContentWrapperRequest(List.of(massContentDto))

        when: "Update content page"
        MassContentWrapper massContentWrapper = contentService.massUpdateContent(request)

        then: "Invalid contents is not empty, so not update content page"
        massContentWrapper.validContents().isEmpty()
        !massContentWrapper.invalidContents().isEmpty()
    }
}

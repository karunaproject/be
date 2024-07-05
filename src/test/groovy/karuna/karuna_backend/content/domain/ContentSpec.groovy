package karuna.karuna_backend.content.domain

import karuna.karuna_backend.content.dto.MassContentDto
import karuna.karuna_backend.content.dto.MassContentWrapperRequest
import spock.lang.Specification

class ContentSpec extends Specification {

    def contentRepository = new MockContentRepository()

    def contentService = new ContentService(contentRepository)

    def "should not get content page" () {
        given: "Set up field for content"
        def page = "Home"

        when:
        def contentDto = contentService.getContentByPage(page)

        then:
        contentDto.contents().isEmpty()
    }

    def "should get content page" () {
        given: "Set up fields for content"
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
}

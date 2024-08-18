package karuna.karuna_backend.content.domain

import karuna.karuna_backend.Constants
import karuna.karuna_backend.content.dto.ContentDto
import karuna.karuna_backend.content.dto.MassContentDto
import karuna.karuna_backend.content.dto.MassContentWrapper
import karuna.karuna_backend.content.dto.MassContentWrapperRequest
import spock.lang.Specification

class ContentSpec extends Specification implements Constants {

    ContentService contentService = ContentConfiguration.contentService()

    def "should not get content page" () {
        when: "Get contents for not existing page"
            ContentDto contentDto = contentService.getContentByPage(PAGE)

        then: "Should not found contents"
            contentDto.contents().isEmpty()
    }

    def "should get content page" () {
        given: "Add content with page $PAGE, key $KEY, value $VALUE"
            MassContentWrapperRequest request = new MassContentWrapperRequest([new MassContentDto(PAGE,KEY,VALUE)])
            contentService.massAddContent(request)

        when: "Get content for page: $PAGE"
            ContentDto contentDto = contentService.getContentByPage(PAGE)

        then: "For key: $KEY content should have value: $VALUE"
            !contentDto.contents().isEmpty()
            contentDto.contents().get(KEY) == VALUE
    }

    def "should not add content page" () {
        given: "Add content page with page $PAGE, key $KEY, value $VALUE"
            MassContentWrapperRequest request = new MassContentWrapperRequest([new MassContentDto(PAGE,KEY,VALUE)])
            contentService.massAddContent(request)

        when: "Add content page again with same $request"
            MassContentWrapper massContentWrapper = contentService.massAddContent(request)

        then: "Invalid contents is not empty, so not save content page again"
            massContentWrapper.validContents().isEmpty()
            !massContentWrapper.invalidContents().isEmpty()
    }

    def "should add content page" () {
        when: "Add content page with page $PAGE, key $KEY, value $VALUE"
            MassContentWrapperRequest request = new MassContentWrapperRequest([new MassContentDto(PAGE,KEY,VALUE)])
            MassContentWrapper massContentWrapper = contentService.massAddContent(request)

        then: "Valid contents is not empty, so save content page"
            !massContentWrapper.validContents().isEmpty()
            massContentWrapper.invalidContents().isEmpty()
    }

    def "should update content page" () {
        given:  "Add content page with page $PAGE, key $KEY, value $VALUE"
            MassContentWrapperRequest request = new MassContentWrapperRequest([new MassContentDto(PAGE,KEY,VALUE)])
            contentService.massAddContent(request)

        when: "Update content page with page $PAGE, key $KEY by changing value"
            String value = "Get the fuck out from my world!"
            request = new MassContentWrapperRequest([new MassContentDto(PAGE,KEY,value)])
            MassContentWrapper massContentWrapper = contentService.massUpdateContent(request)

        then: "Valid update is success"
            !massContentWrapper.validContents().isEmpty()
            massContentWrapper.invalidContents().isEmpty()
            massContentWrapper.validContents().get(0).key() == KEY
            massContentWrapper.validContents().get(0).page() == PAGE
            massContentWrapper.validContents().get(0).valuePl() == value
    }

    def "should not update content page" () {
        when: "Update content page which not exists"
            MassContentWrapperRequest request = new MassContentWrapperRequest([new MassContentDto(PAGE,KEY,VALUE)])
            MassContentWrapper massContentWrapper = contentService.massUpdateContent(request)

        then: "Invalid contents is not empty, so not update content page"
            massContentWrapper.validContents().isEmpty()
            !massContentWrapper.invalidContents().isEmpty()
    }
}

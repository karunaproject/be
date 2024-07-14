package karuna.karuna_backend.content.domain

class ContentConfiguration {

    static ContentService contentService() {
        new ContentService(new MockContentRepository())
    }
}

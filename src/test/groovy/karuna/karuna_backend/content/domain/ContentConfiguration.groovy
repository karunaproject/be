package karuna.karuna_backend.content.domain

class ContentConfiguration {

    static ContentService contentService() {
        return new ContentService(new MockContentRepository())
    }
}

package karuna.karuna_backend.content.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ContentTestConfiguration {

    private static final ContentRepository contentRepository = new InMemoryContentRepository();

    static ContentService contentService() {
        return new ContentService(contentRepository);
    }

    static void clearDatabase() {
        contentRepository.deleteAll();
    }
}

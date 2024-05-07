package karuna.karuna_backend.content.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ContentTestConfiguration {

    static ContentService contentService() {
        return new ContentService(new InMemoryContentRepository());
    }
}

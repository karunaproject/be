package karuna.karuna_backend.content.domain;

import karuna.karuna_backend.content.dto.ContentDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ContentMapper {

    static ContentDTO mapToDto(HashMap<String, String> contents) {
        return new ContentDTO(contents);
    }
}

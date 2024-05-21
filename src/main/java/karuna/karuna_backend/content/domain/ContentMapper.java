package karuna.karuna_backend.content.domain;

import karuna.karuna_backend.content.dto.ContentDTO;
import karuna.karuna_backend.content.dto.MassContentDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ContentMapper {

    static ContentDTO mapToDto(HashMap<String, String> contents) {
        return new ContentDTO(contents);
    }

    static Content mapToEntity(MassContentDto dto){
        return Content.builder()
                .page(dto.page())
                .key(dto.key())
                .valuePl(dto.valuePl())
                .build();
    }
}

package karuna.karuna_backend.Services;

import karuna.karuna_backend.DTO.ContentDTO;
import karuna.karuna_backend.Models.Content;
import karuna.karuna_backend.Repositories.ContentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContentServiceTest {

    @Mock
    ContentRepository contentRepository;

    @InjectMocks
    ContentService contentService;

    @Test
    void getContentByPageReturnEmptyList() {
        when(contentRepository.findByPageIgnoreCaseOrPageNull("123")).thenReturn(listForNullPages());

        ContentDTO result = contentService.getContentByPage("123");

        assertEquals( 3, result.contents().size());
    }

    @Test
    void getContentByPageHome() {
        when(contentRepository.findByPageIgnoreCaseOrPageNull("home"))
                .thenReturn(Stream.concat(listForNullPages().stream(), listForHome().stream()).collect(Collectors.toList()));

        ContentDTO result = contentService.getContentByPage("home");

        assertEquals(6, result.contents().size());
    }

    private List<Content> listForNullPages() {
        List<Content> contents = new ArrayList<>();
        contents.add(new Content(1L, null, "key1", "valuePL1"));
        contents.add(new Content(2L, null, "key2", "valuePL2"));
        contents.add(new Content(3L, null, "key3", "valuePL3"));
        return contents;
    }

    private List<Content> listForHome() {
        List<Content> contents = new ArrayList<>();
        contents.add(new Content(1L, "home", "key4", "valuePL1"));
        contents.add(new Content(2L, "home", "key5", "valuePL2"));
        contents.add(new Content(3L, "home", "key6", "valuePL3"));
        return contents;
    }
}
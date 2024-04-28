package karuna.karuna_backend.Services;

import karuna.karuna_backend.Models.Content;
import karuna.karuna_backend.Repositories.ContentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContentServiceTest {

    @Mock
    ContentRepository contentRepository;

    @InjectMocks
    ContentService contentService;

    @Test
    void getContentByPageReturnEmptyHashMap() {
        HashMap<String, String> result = contentService.getContentByPage("123");

        assertEquals( 0, result.size());
    }

    @Test
    void getContentByPageNull() {
        when(contentRepository.findByPageIsNull()).thenReturn(listForNullPages());

        HashMap<String, String> result = contentService.getContentByPage(null);

        assertEquals(3, result.size());
    }

    @Test
    void getContentByPageHome() {
        when(contentRepository.findByPageIsNull()).thenReturn(listForNullPages());
        when(contentRepository.findByPage("home")).thenReturn(listForHome());

        HashMap<String, String> result = contentService.getContentByPage("home");

        assertEquals(3, result.size());
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
        contents.add(new Content(1L, "home", "key1", "valuePL1"));
        contents.add(new Content(2L, "home", "key2", "valuePL2"));
        contents.add(new Content(3L, "home", "key3", "valuePL3"));
        return contents;
    }
}
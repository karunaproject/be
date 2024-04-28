package karuna.karuna_backend.Controllers;

import karuna.karuna_backend.Models.Content;
import karuna.karuna_backend.Services.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/{page}")
    public ResponseEntity<HashMap<String, String>> getContentByPage(@PathVariable String page) {
        return ResponseEntity.ok(contentService.getContentByPage(page));
    }

}

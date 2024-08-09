package org.big.prj.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class MapController {

    @GetMapping("carbase/api/maps")
    public ResponseEntity<String> getMaps() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/public/maps.json");
        byte[] bytes = Files.readAllBytes(Paths.get(resource.getURI()));
        String content = new String(bytes);
        return ResponseEntity.ok().body(content);
    }
}

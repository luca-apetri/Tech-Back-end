package com.Intelligent_Forms.Intelligent_Forms_FCR.image;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageDataService imageDataService;

    @PostMapping
    public void uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        imageDataService.uploadImage(file);
    }

    @GetMapping("/info/{iamgeId}")
    public ResponseEntity<?> getImageInfoByName(@PathVariable UUID imageId) {
        ImageEntity image = imageDataService.getInfoByImageByName(imageId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<?> getImageByName(@PathVariable UUID imageId) {
        byte[] image = imageDataService.getImage(imageId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}

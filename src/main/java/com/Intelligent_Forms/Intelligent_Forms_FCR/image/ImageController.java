package com.Intelligent_Forms.Intelligent_Forms_FCR.image;

import com.Intelligent_Forms.Intelligent_Forms_FCR.qrcodegenerator.QRCodeGenerator;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
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
    public void uploadImage(@RequestParam("file") MultipartFile file) throws IOException, TesseractException {
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

    @GetMapping("/qr")
    public ResponseEntity<?> getQRCode(@RequestParam(value = "url") String url) {

        byte[] image = new byte[0];
        try {
            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImage(url, 250, 250);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}

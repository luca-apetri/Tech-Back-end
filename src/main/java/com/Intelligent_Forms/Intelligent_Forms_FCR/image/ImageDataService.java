package com.Intelligent_Forms.Intelligent_Forms_FCR.image;

import com.Intelligent_Forms.Intelligent_Forms_FCR.imageReader.ImageTextReader;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageDataService {
    private final ImageRepository imageDataRepository;

    public void uploadImage(MultipartFile file) throws IOException, TesseractException {
        File file1 = convert(file);
        ImageTextReader ImageTextReader = new ImageTextReader();
        ImageTextReader.ImageReader(file1);
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        fileName = fileName.substring(0, fileName.length() - 4);
        fileName = LocalDate.now()
                .toString() + "_" +
                LocalTime.now().toString().substring(0, 8) + "_" + fileName + ".PNG";
        imageDataRepository.save(ImageEntity.builder()
                .name(fileName)
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
    }

    @Transactional
    public ImageEntity getInfoByImageByName(UUID imageId) {
        Optional<ImageEntity> dbImage = imageDataRepository.findById(imageId);
        return ImageEntity.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

    }

    @Transactional
    public byte[] getImage(UUID imageId) {
        Optional<ImageEntity> dbImage = imageDataRepository.findById(imageId);

        return ImageUtil.decompressImage(dbImage.get().getImageData());
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


}
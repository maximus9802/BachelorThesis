package com.quyvx.main_server.api.application.services.log;

import com.quyvx.main_server.shared.services.GCSService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class LogService {
    private final GCSService gcsService;

    public String uploadImageToCloud(MultipartFile image) {
        try {
            byte[] content = image.getBytes();
            String fileName = generateRandomImageName();
            String contentType = image.getContentType();

            String imageUrl = gcsService.uploadImage(content, fileName, contentType);
            return imageUrl;
        }  catch (IOException e) {
            throw new RuntimeException("Error uploading image");
        }
    }

    private static String generateRandomImageName() {
        String randomString = UUID.randomUUID().toString();
        randomString = randomString.replace("-", "");
        return randomString + ".png";
    }
}

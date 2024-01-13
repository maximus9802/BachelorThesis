package com.quyvx.main_server.shared.services;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class GCSService {
    @Value("${google.cloud.storage.bucket-name}")
    private String bucketName;

    public String uploadImage(byte[] content, String fileName, String contentType) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Bucket bucket = storage.get(bucketName);

        if (bucket == null) {
            throw new NotFoundException("Bucket not found: " + bucketName);
        }

        Blob blob = bucket.create(fileName, content, contentType);
        return bucket.getName() + "/" + blob.getName();
    }
}
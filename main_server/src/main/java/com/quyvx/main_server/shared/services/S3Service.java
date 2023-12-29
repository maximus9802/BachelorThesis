package com.quyvx.main_server.shared.services;

import com.quyvx.main_server.shared.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
public class S3Service {
    private String region;

    S3Client getClient() {
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        return S3Client.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.of(region))
                .build();
    }

    public void putObject(String bucketName, String objectKey, byte[] content) {
        S3Client s3Client =getClient();
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();
        s3Client.putObject(request, RequestBody.fromBytes(content));
        log.info("----- File {} uploaded to S3 bucket: {}", objectKey, bucketName);
    }

    public String getObjectUrl(String bucketName, String objectKey) {
        S3Client s3Client = getClient();
        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();
        return s3Client.utilities().getUrl(getUrlRequest).toExternalForm();
    }

    public String getSignedObjectUrl(String bucketName, String objectKey, Long expirationTime) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();
        S3Presigner s3Presigner = S3Presigner.builder().region(Region.of(region)).build();
        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(
                GetObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofMinutes(expirationTime))
                        .getObjectRequest(getObjectRequest)
                        .build());
        return presignedGetObjectRequest.url().toString();
    }

    public void deleteObjects(String bucketName, List<String> objectKeys) {
        S3Client s3Client = getClient();
        List<ObjectIdentifier> toDelete = objectKeys.stream()
                .map(objectKey -> ObjectIdentifier.builder()
                        .key(objectKey)
                        .build()).toList();
        Delete delete = Delete.builder().objects(toDelete).build();
        DeleteObjectsRequest request = DeleteObjectsRequest.builder()
                .bucket(bucketName)
                .delete(delete).build();
        s3Client.deleteObjects(request);
        log.info("----- Files {} deleted from S3 bucket: {}", objectKeys, bucketName);
    }

    public String randomObjectKey(String objectKey) {
        return TimeUtils.getInstant(TimeUtils.now()).toEpochMilli() + "_" + objectKey;
    }
}

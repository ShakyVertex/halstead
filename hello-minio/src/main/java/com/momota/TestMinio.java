package com.momota;

import io.minio.*;
import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class TestMinio {

    public static void main(String[] args) {

        String endpoint = "http://192.168.10.101:9000";
        String accessKey = "minioadmin";
        String secretKey = "minioadmin";
        String bucketName = "hello-minio";

        MinioClient minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();

        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());

                String policy = """
                        {
                          "Statement" : [ {
                            "Action" : "s3:GetObject",
                            "Effect" : "Allow",
                            "Principal" : "*",
                            "Resource" : "arn:aws:s3:::%s/*"
                          } ],
                          "Version" : "2012-10-17"
                        }
                        """.formatted(bucketName);

                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(policy).build());
            }

            String filename = "E:\\迅雷下载\\合集-国内流出\\ChloePussy\\1 (1).jpg";
            minioClient.uploadObject(UploadObjectArgs.builder().filename(filename).bucket(bucketName).object("cp.jpg").build());

            System.out.println("Upload Successful!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

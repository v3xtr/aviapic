package com.api.backend.internal.adapter;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3 {
    @Value("${s3.access.key}")
    private String accessKey;

    @Value("${s3.secret.key}")
    private String secretKey;

    @Value("${s3.region.name}")
    private String s3RegionName;

    @Bean
    public AmazonS3 getAmazonS3Client(){
        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(s3RegionName)
                .build();
    }
}

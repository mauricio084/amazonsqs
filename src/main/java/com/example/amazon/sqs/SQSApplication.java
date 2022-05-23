package com.example.amazon.sqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@SpringBootApplication
public class SQSApplication {

	public static void main(String[] args) {
		SpringApplication.run(SQSApplication.class, args);
	}
	
	@Bean
    public AmazonSQS getAmazonSQS() {
		AWSCredentials credentials = new BasicAWSCredentials(
				"<accessKey>",
				"<secretKey>"				
				);
				
		return AmazonSQSClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_1)
				.build();
    }

}

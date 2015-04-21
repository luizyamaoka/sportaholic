package com.sportaholic.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sportaholic.service.AmazonS3Service;

@Component
public class AmazonS3ServiceImpl implements AmazonS3Service {

	private static final String BUCKETNAME = "sportaholic";
	private static final String ROOT_FOLDER = "images/";
	
	private AmazonS3Client amazonS3Client;
	
	public AmazonS3ServiceImpl() {
		this.amazonS3Client = new AmazonS3Client(new ProfileCredentialsProvider());
	}
	
	@Override
	public List<String> upload(String targetFileName, InputStream inputStream) throws Exception {
		
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType("image/png");
		objectMetadata.setCacheControl("max-age=1296000");
		PutObjectRequest putObjectRequest = 
				new PutObjectRequest(BUCKETNAME, ROOT_FOLDER + targetFileName, inputStream, objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		this.amazonS3Client.putObject(putObjectRequest);
		return null;
	}

	@Override
	public void delete(String fileName) throws Exception {
		this.amazonS3Client.deleteObject(BUCKETNAME, ROOT_FOLDER + fileName);
	}

}

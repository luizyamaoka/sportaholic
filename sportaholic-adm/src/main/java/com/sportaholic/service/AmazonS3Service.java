package com.sportaholic.service;

import java.io.InputStream;
import java.util.List;

public interface AmazonS3Service {

	public List<String> upload(String fileName, InputStream inputStream) throws Exception;
	
	public void delete(String fileName) throws Exception;
	
}

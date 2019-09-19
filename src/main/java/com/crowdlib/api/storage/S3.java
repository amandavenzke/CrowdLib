package com.crowdlib.api.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;

@Component
public class S3 {
	
	@Autowired
	private AmazonS3 amazonS3;
	
}

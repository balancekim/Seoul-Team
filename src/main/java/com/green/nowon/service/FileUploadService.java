package com.green.nowon.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.S3UploadDTO;

public interface FileUploadService {

	Map<String, String> tempUploadProcess(MultipartFile temp);

	S3UploadDTO tempToUpload(S3UploadDTO img);

	void clearTemp();

	
	
}

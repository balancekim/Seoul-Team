package com.green.nowon.service.proc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.green.nowon.domain.dto.S3UploadDTO;
import com.green.nowon.service.FileUploadService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileUploadServiceProcess implements FileUploadService{
	
	private final AmazonS3Client client;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.s3.temp-path}")
	private String tempPath;
	@Value("${cloud.aws.s3.upload-path}")
	private String uploadPath;
	
	@Override
	public Map<String, String> tempUploadProcess(MultipartFile temp) {
		Map<String, String> resultMap=new HashMap<>();
		String newName=createNewFileName(temp.getOriginalFilename());
		String tempKey=tempPath+newName;
		try(InputStream is=temp.getInputStream()) {
			
			PutObjectRequest putObjectRequest=new PutObjectRequest(bucketName, tempKey, is, objectMetadata(temp));
			client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));
			
			String imgUrl=client.getUrl(bucketName, tempKey).toString().substring(6); // https://
			
			resultMap.put("imgUrl", imgUrl);
			resultMap.put("orgName", temp.getOriginalFilename());
			resultMap.put("newName", newName);
			resultMap.put("tempKey", tempKey);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	private ObjectMetadata objectMetadata(MultipartFile temp) {
		ObjectMetadata objectMetadata=new ObjectMetadata();
		objectMetadata.setContentType(temp.getContentType());
		objectMetadata.setContentLength(temp.getSize());
		return objectMetadata;
	}
	
	
	private String createNewFileName(String orgName) {
		int idx=orgName.lastIndexOf(".");
		return UUID.randomUUID().toString()
				+orgName.substring(idx); //확장자
	}
	@Override
	public S3UploadDTO tempToUpload(S3UploadDTO img) {
		String bucketkey=uploadPath+img.getNewName();
		
		CopyObjectRequest copyObjectRequest=new CopyObjectRequest(bucketName, img.getTempKey(), bucketName, bucketkey);
		client.copyObject(copyObjectRequest.withCannedAccessControlList(CannedAccessControlList.PublicRead));
		client.deleteObject(bucketName, img.getTempKey());
		return img.bucketKey(bucketkey);
	}
	@Override
	public void clearTemp() {
		ObjectListing objectListing = client.listObjects(bucketName, tempPath);
		while (true) {
		    for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
		    	client.deleteObject(bucketName, objectSummary.getKey());
		    }

		    if (objectListing.isTruncated()) {
		        objectListing = client.listNextBatchOfObjects(objectListing);
		    } else {
		        break;
		    }
		}
		
		// 폴더를 삭제
		client.deleteObject(bucketName, tempPath);
		
	}
	
	

}

package com.green.nowon.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.GoodsSaveDTO;
import com.green.nowon.domain.dto.S3UploadDTO;
import com.green.nowon.domain.entity.GoodsEntity;
import com.green.nowon.domain.entity.GoodsImageEntity;
import com.green.nowon.domain.entity.GoodsImageReporitory;
import com.green.nowon.domain.entity.GoodsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
public class GoodsService {

	private final GoodsRepository repo;
	private final GoodsImageReporitory goodsImageRepo;
	private final FileUploadService fileService;
	
	
	public void save(GoodsSaveDTO dto) {
	
		GoodsEntity goods= new GoodsEntity();
		
		//1. 상품정보 저장
		repo.save(dto.toEntity());
		
		//2. 이미지 저장
		long goodsNo=repo.findNoByTitle(dto.getTitle());
		System.out.println("goodsNo : "+goodsNo);
		dto.files().forEach(img->{
			//temp-> upload 버킷으로 이동
			S3UploadDTO uploadResult=fileService.tempToUpload(img);
			GoodsImageEntity s3UploadResult=uploadResult.toEntity(goodsNo);
			
			//상품번호(uploadResult.gno(dto.getNo()))를 이미지 테이블에 저장
			//S3UploadDTO updatedUploadResult = fileService.tempToUpload(img);
			goodsImageRepo.save(s3UploadResult);
		});
		
		//이미지 temp-> upload 버킷으로 이동 후 temp 정리
		fileService.clearTemp();
		
	}


	public void listProcess(Model model) {
		List<GoodsEntity> result= repo.findAll();
		model.addAttribute("list", result);
	}

}

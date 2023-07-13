package com.green.nowon.service;

import java.io.Console;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.GoodsDetailDTO;
import com.green.nowon.domain.dto.GoodsImgListDTO;
import com.green.nowon.domain.dto.GoodsSaveDTO;
import com.green.nowon.domain.dto.S3UploadDTO;
import com.green.nowon.domain.entity.GoodsEntity;
import com.green.nowon.domain.entity.GoodsImageEntity;
import com.green.nowon.domain.entity.GoodsImageReporitory;
import com.green.nowon.domain.entity.GoodsRepository;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@RequiredArgsConstructor
@Service
public class GoodsService {

	private final GoodsRepository repo;
	private final GoodsImageReporitory goodsImageRepo;
	private final FileUploadService fileService;
	
	@Value("${cloud.aws.s3.domain}")
	private String domain;
	
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

	//상품 리스트 페이지용 정보 불러오기
	public void listProcess(Model model) {
		List<GoodsEntity> result= repo.findAll();
		model.addAttribute("list", result);
	}

	//상품 상세 정보 
	public void detailProcess(long no, Model model) {
		//1. 상세정보
		GoodsDetailDTO detail=repo.findAllByNo(no)
				.map(GoodsDetailDTO::new)
				.orElseThrow();
		//System.out.println("detail : "+detail);
		model.addAttribute("list", detail);
		
		//2. 이미지
		List<GoodsImgListDTO> imgs=goodsImageRepo.findAllByDefAndGoodsNo(false, no).stream()
				.map(dto-> new GoodsImgListDTO(dto,domain)).collect(Collectors.toList());
		model.addAttribute("imgs", imgs);
		
		//#. 이미지 test
		GoodsImgListDTO imgsss=goodsImageRepo.findAllByGoodsNoAndDef(no, true)
				.map(dto-> new GoodsImgListDTO(dto,domain)).orElseThrow();
		model.addAttribute("imgsss", imgsss);
		
	}

	public void deleteProcess(long no) {
		repo.deleteById(no);
		
	}



}

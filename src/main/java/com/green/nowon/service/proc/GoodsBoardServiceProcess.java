package com.green.nowon.service.proc;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.GoodsDetailDTO;
import com.green.nowon.domain.dto.GoodsImgListDTO;
import com.green.nowon.domain.dto.GoodsListDTO;
import com.green.nowon.domain.entity.GoodsImageEntity;
import com.green.nowon.domain.entity.GoodsImageReporitory;
import com.green.nowon.domain.entity.GoodsRepository;
import com.green.nowon.service.GoodsBoardService;

@Service
public class GoodsBoardServiceProcess implements GoodsBoardService {

	@Autowired
	private GoodsRepository repo;
	
	@Autowired
	private GoodsImageReporitory gIRepo;
	
	@Value("${cloud.aws.s3.domain}")
	private String domain;
	
	//상품리스트 페이지
	@Override
	public void listProcess(Model model) {
		model.addAttribute("list", repo.findAll().stream()
				.map(goods->new GoodsListDTO(goods)
				.defImg(domain,gIRepo.findByGoodsAndDef(goods, true)))
				.collect(Collectors.toList())	
	);
	}

	//상품페이지 detail 내용
	@Override
	public void detailProcess(long goodsNo, Model model) {
		//1. 상세정보
		GoodsDetailDTO detail=repo.findAllByNo(goodsNo)
				.map(GoodsDetailDTO::new)
				.orElseThrow();
		//System.out.println("detail : "+detail);
		model.addAttribute("detail", detail);
		//2. 상세정보 이미지
		List<GoodsImgListDTO> imgs=gIRepo.findAllByGoodsNo(goodsNo).stream()
				.map(dto-> new GoodsImgListDTO(dto,domain)).collect(Collectors.toList());
		
		model.addAttribute("imgs", imgs);
		
		
	}

}

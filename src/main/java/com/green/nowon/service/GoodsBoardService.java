package com.green.nowon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.GoodsListDTO;
import com.green.nowon.domain.entity.GoodsImageReporitory;
import com.green.nowon.domain.entity.GoodsRepository;

import lombok.RequiredArgsConstructor;


@Service
public class GoodsBoardService {

	@Autowired
	private GoodsRepository repo;
	
	@Autowired
	private GoodsImageReporitory gIRepo;
	
	@Value("${cloud.aws.s3.domain}")
	private String domain;
	
	public void listProcess(Model model) {
		
		 
		
		
		
	}
	

}

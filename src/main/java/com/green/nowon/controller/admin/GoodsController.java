package com.green.nowon.controller.admin;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.green.nowon.domain.dto.GoodsSaveDTO;
import com.green.nowon.service.FileUploadService;
import com.green.nowon.service.GoodsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GoodsController {

	private final GoodsService service;
	private final FileUploadService fileService;
	//상품등록 페이지로 이동
	@ResponseBody
	@GetMapping("/admin/goods/new")
	public ModelAndView write() {
		return new ModelAndView("admin/goods/write");
	}
	//이미지 저장
	@ResponseBody
	@PostMapping("/admin/goods/temp-img")
	public Map<String,String> tempUpload(MultipartFile temp){
		return fileService.tempUploadProcess(temp);
	
	}
	//상품 등록
	@ResponseBody
	@PostMapping("/admin/goods")
	public boolean save(GoodsSaveDTO dto) {
		service.save(dto);
		//System.out.println(dto);
		return true;
	}

	@ResponseBody
	@GetMapping("/admin/goods/list")
	public ModelAndView list(Model model) {
		service.listProcess(model);
		return new ModelAndView("admin/goods/list");
	}
}

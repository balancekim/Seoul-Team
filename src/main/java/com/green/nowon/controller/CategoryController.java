package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.CategorySaveDTO;
import com.green.nowon.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CategoryController {

	private final CategoryService service;
	
	@GetMapping("/admin/goods/category/new")
	public String write() {
		return "/admin/category/write";
	}
	
	@ResponseBody
	@PostMapping("/admin/category/new")
	public void save(CategorySaveDTO dto) {
		service.saveProcess(dto);
	}
}

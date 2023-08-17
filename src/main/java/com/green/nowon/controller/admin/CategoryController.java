package com.green.nowon.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.green.nowon.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CategoryController {

	private final CategoryService service;
	//카테고리 작성 페이지로 이동
	@GetMapping("/admin/goods/category/new")
	public String write() {
		return "/admin/category/write";
	}
	
	//카테고리 저장
	@ResponseBody
	@PostMapping("/admin/category/new")
	public void save(String name) {
		service.saveProcess(name);
	}
	
	//카테고리 리스트 페이지로 이동
	@GetMapping("/admin/goods/category/list")
	public String list(Model model) {
		service.listProcess(model);
		return "/admin/category/list";
	}
	
	@PatchMapping("/admin/category/list")
	public String cateList(Model model) {
		service.listProcess(model);
		return "admin/category/list-category";
	}
	
	@DeleteMapping("/admin/del/{no}")
	public String delete(@PathVariable long no) {
		service.deleteProcess(no);
		return "redirect:/admin";
	}
}

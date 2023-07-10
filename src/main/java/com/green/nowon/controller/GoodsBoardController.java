package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.green.nowon.service.GoodsBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GoodsBoardController {

	private final GoodsBoardService service;
	
	@GetMapping("/goods/list")
	public String list(Model model) {
		service.listProcess(model);
		return "goods/list";
	}
	
	@GetMapping("/goods/{goodsNo}")
	public String detail(@PathVariable long goodsNo, Model model) {
		service.detailProcess(goodsNo, model);
		return "goods/detail";
	}
}

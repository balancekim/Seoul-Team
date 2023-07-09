package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}

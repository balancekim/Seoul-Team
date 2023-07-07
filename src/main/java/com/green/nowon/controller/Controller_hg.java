package com.green.nowon.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.openapi.first.Item;
import com.green.nowon.service.ApiService;
import com.green.nowon.service.ApiService2;
import com.green.nowon.service.ApiService3;
import com.green.nowon.service.proc.ApiserviceProcess;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class Controller_hg {
	
	private final ApiService service;
	private final ApiService2 service2;
	private final ApiService3 service3;
	
	
	@GetMapping("/mapPage")
	public String mapPage_1() {
		return "member/map1";
	}
	@GetMapping("/mapPage2")
	public String mapPage_2() {
		return "member/map2";
	}
	@GetMapping("/mapPage3")
	public String mapPage_3() {
		return "member/map3";
	}
	
	@ResponseBody
	@PostMapping("/mapPage")
	public List<Item> CateProcess(@RequestParam("keyWord")String keyWord) throws Exception{
		//System.out.println(service.LocalCate(page,keyWord));
		
		return service.LocalCate(keyWord);
	}
	@ResponseBody
	@PostMapping("/mapPage2")
	public List<com.green.nowon.openapi.sec.Item> CateProcess2(@RequestParam("keyWord")String keyWord) throws Exception{
		//System.out.println(service.LocalCate(page,keyWord));
		
		return service2.LocalCate2(keyWord);
	}
	@ResponseBody
	@PostMapping("/mapPage3")
	public List<com.green.nowon.openapi.thr.Item> CateProcess3(@RequestParam("keyWord")String keyWord) throws Exception{
		//System.out.println(service.LocalCate(page,keyWord));
		
		return service3.LocalCate3(keyWord);
	}
	@ResponseBody
	@PostMapping("/mapPageSearch")
	public List<Item> CateAndSearchProcess(@RequestParam("keyWord")String keyWord,@RequestParam("searchValue")String searchValue) throws Exception{
		//System.out.println(service.LocalCate(page,keyWord));
		
		return service.LocalCateSearch(keyWord,searchValue);
	}	
	@ResponseBody
	@PostMapping("/mapPageSearch2")
	public List<com.green.nowon.openapi.sec.Item> CateAndSearchProcess2(@RequestParam("keyWord")String keyWord,@RequestParam("searchValue")String searchValue) throws Exception{
		//System.out.println(service.LocalCate(page,keyWord));
		
		return service2.LocalCateSearch2(keyWord,searchValue);
	}	
	@ResponseBody
	@PostMapping("/mapPageSearch3")
	public List<com.green.nowon.openapi.thr.Item> CateAndSearchProcess3(@RequestParam("keyWord")String keyWord,@RequestParam("searchValue")String searchValue) throws Exception{
		//System.out.println(service.LocalCate(page,keyWord));
		
		return service3.LocalCateSearch3(keyWord,searchValue);
	}	
		
		
	
	
}

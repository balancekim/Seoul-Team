package com.green.nowon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.nowon.domain.dto.FindIdDTO;
import com.green.nowon.domain.entity.Findrepository;
import com.green.nowon.domain.entity.UserEntity;
import com.green.nowon.domain.entity.UserEntityRepository;
import com.green.nowon.service.FindIdService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FindIdPwController {

	private final FindIdService service;
	
	private final UserEntityRepository repository;
	
	
	@GetMapping("/findId")
	public String findId() {
		return "finds/findId";
	}
	@GetMapping("/findPw")
	public String findpw() {
		return "finds/findPw";
	}
	
	@PostMapping("/userInfo")
	public String resultId(FindIdDTO dto,Model model) {
		UserEntity result=repository.findByEmail(dto.getEmail()).orElseThrow();
		String ad=result.getUserId();
		model.addAttribute("list", ad);
		return "redirect:/";
	}
}

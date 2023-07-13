package com.green.nowon.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.nowon.domain.dto.UserDTO;
import com.green.nowon.security.CustomDetails;
import com.green.nowon.service.InfoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserInfoController {
	
	private final InfoService service;
	private final BCryptPasswordEncoder bc;
	
	//비밀번호 재확인 페이지로 이동 ->소셜로그인인 경우 바로 개인정보 수정 페이지로
	@GetMapping("/userPwCheck")
	public String pwCheck(Authentication authentication) {
		CustomDetails cd= (CustomDetails) authentication.getPrincipal();
		String sns=cd.getPassword();
		if(sns=="1111") {
			return  "redirect:/userInfoDetail";
		}
		return "member/userInfo";
	}
	//개인정보 디테일 수정페이지 
	@GetMapping("/userInfoDetail")
	public String userInfoDetail(Model model,Authentication authentication) {
		String id=authentication.getName();
		service.infoList(model,id);
		return "member/userInfoDetail";
	}

	//수정 페이지 ㄱㄱ
	@PostMapping("/go-info")
	public String goInfo(Authentication authentication,String password) {
		String id=authentication.getName();
		
		return service.goInfo(id, password);
	}
	
	//수정 페이지
	@PostMapping("/userUpdate")
	public String update(UserDTO dto ) {
		service.updateInfo(dto);
		return "redirect:/"; 
	}
}

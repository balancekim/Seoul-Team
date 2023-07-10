package com.green.nowon.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.nowon.domain.dto.UserDTO;
import com.green.nowon.service.InfoService;
import com.green.nowon.service.proc.InfoServiceProcess;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserInfoController {
	
	private final InfoService service;
	
	//비밀번호 재확인 페이지로 이동
	@GetMapping("/userPwCheck")
	public String pwCheck() {
		return "member/userInfo";
	}
	//개인정보 디테일 수정페이지 
	@GetMapping("/userInfoDetail")
	public String userInfoDetail(Model model,Authentication authentication) {
		String id=authentication.getName();
		service.infoList(model,id);
		return "member/userInfoDetail";
	}

	
	@PostMapping("/userInfo")
	public String moveInfo(Authentication authentication,String password) {
		String id=authentication.getName();
		String pw=password;
		/* return "redirect:/userInfoDetail"; */
		 return service.goInfo(id,pw); 
	}
	@PostMapping("/userUpdate")
	public String update(UserDTO dto ) {
		
		return "redirect:/userInfoDetail"; 
	}
}

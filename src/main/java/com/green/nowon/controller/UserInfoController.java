package com.green.nowon.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@GetMapping("/userInfoDetail")
	public String userInfoDetail() {
		return "member/userInfoDetail";
	}

	/*
	 * //비밀번호 확인 과정
	 * 
	 * @PostMapping("/checkPw") public String getInfo(@RequestParam String userPw) {
	 * System.out.println(userPw); return ""; }
	 */
	@PostMapping("/userInfo")
	public String moveInfo(Authentication authentication,String password) {
		
		String id=authentication.getName();
		String pw=password;
		System.out.println(id+"+++++++++"+pw);
		/* return "redirect:/userInfoDetail"; */
		 return service.goInfo(id,pw); 
	}
}

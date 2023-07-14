package com.green.nowon.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.UserDTO;
import com.green.nowon.service.loginService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LogController {
	
	
	private final loginService service;
	
	
	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		String target = request.getHeader("referer");
		if(target.contains("/signup")||target.contains("/findPw")) {
			return "member/login";
		} 
		if(!target.contains("/login")) {
			HttpSession session=request.getSession();
			session.setAttribute("prevPage", target);
		}
		return "member/login";
		
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "member/signup";
	}
	
	@PostMapping("/signup")
	public String signup(UserDTO dto) {
		service.save(dto);
		return "redirect:/login";
	}
	
	@PostMapping("/login")
	public String login(UserDTO dto) {
		return service.login(dto);
	}
	
	
	
	@ResponseBody
	@PostMapping("/memberIdCheck")
	public String signupcheck(@RequestParam("idBox")String idBox) {
		
		return service.idCheck(idBox);
	}
	
	
	
	@ResponseBody
	@PostMapping("/memberEmailCheck")
	public String emailcheck(@RequestParam("emailBox")String email) {
		return service.emailCheck(email);
	}
	
}

package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindIdPwController {

	@GetMapping("/findId")
	public String findId() {
		return "finds/findId";
	}
	@GetMapping("/findPw")
	public String findpw() {
		return "finds/findPw";
	}
}

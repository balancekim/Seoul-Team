package com.green.nowon.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.green.nowon.domain.entity.EmailRepository;
import com.green.nowon.domain.entity.Emailauth;
import com.green.nowon.service.MailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class EmailController {
	private final MailService service;
	private final EmailRepository repository;
	@PostMapping("/mailauth")
	@ResponseBody
	public void emailAuth(@RequestParam("userEmail") String userEmail) {
		int number=service.sendMail(userEmail);
		String authnum=""+number;

		
		 Emailauth existingData = repository.findByEmail(userEmail);
		    if (existingData != null) {
		        repository.delete(existingData);
		    }

		
		repository.save(Emailauth.builder()
						.code(authnum)
						.email(userEmail)
						.createdTIme(LocalDateTime.now())
						.build());
		
	}
	
	@PostMapping("/mailauthed")
	@ResponseBody
	public String emailAuthed(@RequestParam("userEmail") String userEmail) {
		
		Emailauth auth= repository.findByEmail(userEmail);
		String code=auth.getCode();
		
		return code;
		
	}
	
	
	
}

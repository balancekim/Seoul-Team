package com.green.nowon.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.entity.EmailRepository;
import com.green.nowon.domain.entity.Emailauth;
import com.green.nowon.domain.entity.UserEntity;
import com.green.nowon.domain.entity.UserEntityRepository;
import com.green.nowon.service.FindIdService;
import com.green.nowon.service.MailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FindController {
		
	private final FindIdService service;
	private final MailService emailService;
	private final EmailRepository repository;
	private final UserEntityRepository userRepository;
	
	@ResponseBody
	@PostMapping("/email-check")
	public String emailCheck(@RequestParam("email") String email) {
		
		return service.emailCheck(email);
	}
	
	@ResponseBody
	@PostMapping("/mail-auth")
	public String sendAuth(@RequestParam("userEmail") String userEmail) {
		int number=emailService.sendMail(userEmail);
		String authnum=""+number;

		
		 Emailauth existingData = repository.findByEmail(userEmail);
		 
		 Optional<UserEntity> result=userRepository.findByEmail(userEmail);
			if(result.isPresent()) {
				return "true";
			}else {
			return "false";
			}
		 
			/*
		 if (existingData != null) {
		        repository.delete(existingData);
		    }

		
		repository.save(Emailauth.builder()
				.code(authnum)
				.email(userEmail)
				.createdTIme(LocalDateTime.now())
				.build());
		*/
	}
	
	
}


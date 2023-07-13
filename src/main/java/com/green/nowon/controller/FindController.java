package com.green.nowon.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	private final PasswordEncoder pe;
	

	
	@ResponseBody
	@PostMapping("/email-check")
	public String emailCheck(@RequestParam("email") String email) {
		
		return service.emailCheck(email);
	}
	
	@ResponseBody
	@PostMapping("/mail-auth")
	public void sendAuth(@RequestParam("userEmail") String userEmail) {
		int number=emailService.sendMail(userEmail);
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
	
	
	@ResponseBody
	@PostMapping("/check-name-email")
	public String checkIdName(@RequestParam("name") String name, @RequestParam("email") String email) {
		return service.findIdName(name,email);
	}
	
	@ResponseBody
	@PostMapping("/check-id")
	public String checkId(@RequestParam("userId") String id) {
		return service.findId(id);
	}
	
	@Transactional
	@ResponseBody
	@PostMapping("/pass-auth")
	public void sendPassAuth(@RequestParam("userEmail") String email) {
		
		String authpass=emailService.mail(email);
		UserEntity existedData=userRepository.findByEmail(email).orElseThrow();
		existedData.updatePassword(authpass,pe);
		
	}
}


package com.green.nowon.service.proc;


import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.UserDTO;
import com.green.nowon.domain.entity.UserEntity;
import com.green.nowon.domain.entity.UserEntityRepository;
import com.green.nowon.service.loginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class loginServiceProcess implements loginService{
	
	private final UserEntityRepository repository;
	private final PasswordEncoder encoder;
	
	
	
	@Override
	public void save(UserDTO dto) {



		repository.save(dto.toEntity(encoder));
	}


	@Override
	public String login(UserDTO dto) {
		Optional<UserEntity> byUserId=repository.findByUserId(dto.getUserId());
		if(byUserId.isPresent()) {
			//조회 결과가 있다면
			UserEntity userEntity = byUserId.get();
			String savedPw = userEntity.getPassword();
			System.out.println("savePW"+savedPw);
			String inputPw = dto.getPassword();
			System.out.println("inputPw:"+inputPw);
				
			if(savedPw.equals(inputPw)) {
				//비밀번호 일치
				UserDTO userDTO = UserDTO.toUserDTO(userEntity);
				return "redirect:/mapPage";
			}else {
				//비밀번호 불일치(로그인 실패)
			}
		} if(byUserId.isEmpty()) {
			
		}
		return "redirect:/login";
	}


	@Override
	public String idCheck(String idBox) {
		Optional<UserEntity> result=repository.findByUserId(idBox);
		
		if(result.isPresent()) {
			return "true";
		}else {
		return "false";
		}
		//존재하는 id 이면 true 존재하지 않는 id이면 false
	}


	@Override
	public String emailCheck(String email) {
		Optional<UserEntity> result=repository.findByEmail(email);
		if(result.isPresent()) {
			return "true";
		}else {
		return "false";
		}
	}



}

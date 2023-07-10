package com.green.nowon.service.proc;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.UserDTO;
import com.green.nowon.domain.entity.UserEntity;
import com.green.nowon.domain.entity.UserEntityRepository;
import com.green.nowon.service.InfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@RequiredArgsConstructor
@Service
public class InfoServiceProcess implements InfoService {

	private final UserEntityRepository repository;
	
	@Override
	public String goInfo(String id, String pw) {
		Optional<UserEntity> byUserId=repository.findByUserId(id);
		if(byUserId.isPresent()) {
			//조회 결과가 있다면
			UserEntity userEntity = byUserId.get(); 
			String savedPw = userEntity.getPassword();
			String inputPw = pw;
				
			if(savedPw.equals(inputPw)) {
				//비밀번호 일치
			
				/* UserDTO userDTO = UserDTO.toUserDTO(userEntity); */
				
				return "redirect:/";
			}else {
				//비밀번호 불일치(로그인 실패)
			}
		} if(byUserId.isEmpty()) {
			
		}
		 return "redirect:/mapPage"; 

	}

}

package com.green.nowon.service.proc;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
	private final BCryptPasswordEncoder pe;
	
	@Override
	public String goInfo(String id, String pw) {
		Optional<UserEntity> byUserId=repository.findByUserId(id);
		if(byUserId.isPresent()) {
			//조회 결과가 있다면
			UserEntity userEntity = byUserId.get(); 
			String savedPw = userEntity.getPassword();
			String inputPw = pw;
			 System.out.println( pe.matches(pw, savedPw));
				
			if( pe.matches(pw, savedPw)) {
				//비밀번호 일치
			
				/* UserDTO userDTO = UserDTO.toUserDTO(userEntity); */
				
				return "redirect:/userInfoDetail";
			}else {
				//비밀번호 불일치(로그인 실패)
			}
		} if(byUserId.isEmpty()) {
			
		}
		 return "redirect:/userPwCheck"; 

	}

	@Override
	public void infoList(Model model,String id) {
		Optional<UserEntity> entity=repository.findByUserId(id);
		UserEntity un=entity.get();
		model.addAttribute("info",un);
	}

}

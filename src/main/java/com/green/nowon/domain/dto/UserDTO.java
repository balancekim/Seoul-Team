package com.green.nowon.domain.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.MyRole;
import com.green.nowon.domain.entity.UserEntity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserDTO {
	
	
	private String userId;
	private String password;
	private String userName;
	private String email;
	private String liveIn;
	
	public UserEntity toEntity(PasswordEncoder encoder) {
		return UserEntity.builder()
				.userId(userId)
				.password(encoder.encode(password))
				.userName(userName)
				.email(email)
				.liveIn(liveIn)
				.build()
				.addRole(MyRole.USER);
	}
	public static UserDTO toUserDTO (UserEntity entity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(entity.getUserId());
		userDTO.setPassword(entity.getPassword());
		userDTO.setUserName(entity.getUserName());
		userDTO.setEmail(entity.getEmail());
		userDTO.setLiveIn(entity.getLiveIn());
		return userDTO;
	}
}

package com.green.nowon.service;

import com.green.nowon.domain.dto.UserDTO;

public interface loginService {

	void save(UserDTO dto);

	String login(UserDTO dto);

	String idCheck(String idBox);

	String emailCheck(String email);

	


}

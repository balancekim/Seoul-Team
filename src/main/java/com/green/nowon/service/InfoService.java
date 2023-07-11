package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.UserDTO;

public interface InfoService {

	String goInfo(String id, String pw);

	void infoList(Model model, String id);

	void updateInfo(UserDTO dto);

}

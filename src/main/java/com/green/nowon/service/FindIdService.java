package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.FindIdDTO;

public interface FindIdService {

	String emailCheck(String email);

	String findIdName(String name, String email);

	String findId(String id);




}

package com.green.nowon.service.proc;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.FindIdDTO;
import com.green.nowon.domain.entity.UserEntity;
import com.green.nowon.domain.entity.UserEntityRepository;
import com.green.nowon.service.FindIdService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindIdServiceProcess implements FindIdService {
	
	private final UserEntityRepository repository;

	@Override
	public String emailCheck(String email) {
		Optional<UserEntity> result=repository.findByEmail(email);
		if(result.isPresent()) {
			return "true";
		}else {
		return "false";
		}
	}

	@Override
	public String findIdName(String name, String email) {
		Optional<UserEntity> result=repository.findByUserNameAndEmail(name,email);
		if(result.isPresent()) {
			return "true";
		}else {
			return "false";
		}
	}

	@Override
	public String findId(String id) {
		Optional<UserEntity> result=repository.findByUserId(id);
		if(result.isPresent()) {
			return "true";
		}else {
			return "false";
		}
	}



}

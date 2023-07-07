package com.green.nowon.domain.dto;

import java.time.LocalDateTime;

import com.green.nowon.domain.entity.Emailauth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
	
	private long no;
	private String email;
	private String code;
	private LocalDateTime createdTIme;

}


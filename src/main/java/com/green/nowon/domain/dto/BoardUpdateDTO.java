package com.green.nowon.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardUpdateDTO {
	
	private String title;
	private String content;
	public LocalDateTime updatedDate;

}

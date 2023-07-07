package com.green.nowon.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateDTO {
	
	private String content;
	public LocalDateTime updatedDate;

	@Builder
	public CommentUpdateDTO(String editComment) {
		this.content=editComment;
	}
}

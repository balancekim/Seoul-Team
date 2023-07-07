package com.green.nowon.domain.dto;

import java.time.LocalDateTime;

import com.green.nowon.domain.entity.BoardEntity;
import com.green.nowon.domain.entity.UserEntity;

import lombok.Setter;

@Setter
public class BoardSaveDTO {

	private String title;
	private String content;
	public LocalDateTime updatedDate;
	public BoardEntity toEntity(UserEntity userEntity) {
		
		return BoardEntity.builder()
				.title(title).content(content).updatedDate(updatedDate).creator(userEntity)
				.build();
	}
	
	
}

package com.green.nowon.domain.dto;

import java.time.LocalDateTime;

import com.green.nowon.domain.entity.BoardEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BoardDTO {
	private long no;
	private String title;
	private String content;
	//private String writer;
	//private int readCount;//read_count
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	@Builder
	public BoardDTO(String title, String content) {
		this.title=title;
		this.content = content;
	}
	
	public BoardEntity toEntity() {
		return BoardEntity.builder()
				.title(title)
				.content(content)
				.build();
	}
}

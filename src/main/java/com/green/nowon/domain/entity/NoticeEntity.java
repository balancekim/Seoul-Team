package com.green.nowon.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.green.nowon.domain.dto.BoardUpdateDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="noticeBoard")
@Entity
public class NoticeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer no;
	
	@Column(nullable = false)
	private String title;
	
	
	@Column(nullable = false)
	private String content;
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp(6) null")
	private LocalDateTime createdDate;
	
	@Column(columnDefinition = "timestamp(6) null", nullable=true)
	private LocalDateTime updatedDate;
	
	@Column(columnDefinition = "integer default 0", nullable=false)
	private int view;
	
	public NoticeEntity updateTitleOrContent(BoardUpdateDTO dto) {
		if(dto.getTitle()!=null)title=dto.getTitle();
		if(dto.getContent()!=null)content=dto.getContent();
		if(dto.getUpdatedDate()!=null)updatedDate=dto.getUpdatedDate();
		return this;
	}
	
	public NoticeEntity incrementView() {
		view++;
		return this;
	}
	
	
	
	
	
		
}

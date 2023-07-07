package com.green.nowon.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.green.nowon.domain.dto.CommentUpdateDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="board_comment")
@Entity
public class CommentEntity {
	
	@Id
	@GeneratedValue
	private Integer no;
	
	@Column(nullable= false)
	private String commentContent;
	
		
	@CreationTimestamp
	@Column(columnDefinition = "timestamp(6) null")
	private LocalDateTime createdDate;
	
	//작성자는 UserEntity와 관계설정으로
	@JoinColumn(name = "uno", nullable = true)
	@ManyToOne
	private UserEntity creator; //creator_no
	
	//어떤 게시글의 댓글인지
	//@JoinColumn(name = "bno"/*fk 컬럼명 */, nullable = true)
	@ManyToOne
	private BoardEntity board; //board_no

	
	public CommentEntity updateContent(CommentUpdateDTO dto) {
		if(dto.getContent()!=null)commentContent=dto.getContent();
		if(dto.getUpdatedDate()!=null)createdDate=dto.getUpdatedDate();
		return this;
	}
}

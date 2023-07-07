package com.green.nowon.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.CommentUpdateDTO;
import com.green.nowon.domain.entity.BoardEntity;
import com.green.nowon.domain.entity.BoardRepository;
import com.green.nowon.domain.entity.CommentEntity;
import com.green.nowon.domain.entity.CommentRepository;
import com.green.nowon.domain.entity.UserEntityRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository repo;
	@Autowired
	private UserEntityRepository uRepo;
	@Autowired
	private BoardRepository bReop;
	
	public void commentSave(CommentEntity commentEntity) {
		repo.save(commentEntity);
	}
	
	public Page<CommentEntity> commentAll(Integer no, Pageable pageable) {
		return null;
	}

	public void commentSave(String userId, Integer bno, String commentContent) {
		repo.save(CommentEntity.builder()
				.commentContent(commentContent)
				.creator(uRepo.findByUserId(userId).orElseThrow())
				.board(BoardEntity.builder().no(bno).build())
				//.board(bReop.findById(bno).orElseThrow())
				.build());
		
	}

	public List<CommentEntity>  findAllByBno(Integer bno) {
		return repo.findAllByBoard_noOrderByNoDesc(bno);
		
	}

	@Transactional
	public void updateProcess(Integer no, CommentUpdateDTO dto) {
		dto.updatedDate=LocalDateTime.now();
		repo.findById(no).map(e->e.updateContent(dto));
		
	}

	public boolean isOwner(String name, Integer no) {
		CommentEntity commentEntity = repo.findById(no).get();
		if (commentEntity != null && commentEntity.getCreator().getUserId().equals(name)) {
			return true;
		}return false;
	}

	public void commentDelete(Integer no) {
		repo.deleteById(no);
		
	}
	
	

}

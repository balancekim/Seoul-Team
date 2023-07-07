package com.green.nowon.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.BoardDTO;
import com.green.nowon.domain.dto.BoardSaveDTO;
import com.green.nowon.domain.dto.BoardUpdateDTO;
import com.green.nowon.domain.entity.BoardEntity;
import com.green.nowon.domain.entity.BoardRepository;
import com.green.nowon.domain.entity.UserEntityRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository repo;
	
	@Autowired
	private UserEntityRepository  uRepo;
	
	//글작성
	public void boardSave(BoardEntity boardEntity) { 
		repo.save(boardEntity); 
	}
	 
	//게시글 리스트
	public Page<BoardEntity> boardList(Pageable pageable){
		return repo.findAll(pageable);
	}
	//특정 게시글 내용보기
	@Transactional
	public BoardEntity boardDetail(Integer no) {
		return repo.findById(no)
				.map(e->e.incrementView())
				.get();
	}
	//특정 게시글 삭제
	@Transactional
	public void boardDelete(Integer no) {
		repo.deleteById(no);
	}
	//키워드 검색
	public Page<BoardEntity> boardSearchList(String searchKeyword, Pageable pageable){
		return repo.findByTitleContaining(searchKeyword, pageable);
	}

	public boolean isOwner(String name, Integer no) {
		BoardEntity boardEntity = repo.findById(no).get();
		if (boardEntity != null && boardEntity.getCreator().getUserId().equals(name)) {
            return true;
		} return false;
		
	}

	@Transactional
	public void updateProcess(Integer no, BoardUpdateDTO dto) {
		dto.updatedDate=LocalDateTime.now();
		repo.findById(no).map(e->e.updateTitleOrContent(dto));
		
	}

	public void boardSave(String userId, BoardSaveDTO dto) {
		dto.updatedDate=LocalDateTime.now();
		repo.save(dto.toEntity(uRepo.findByUserId(userId).get()));
		
	}


	
}
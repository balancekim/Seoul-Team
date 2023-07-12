package com.green.nowon.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.BoardSaveDTO;
import com.green.nowon.domain.dto.BoardUpdateDTO;
import com.green.nowon.domain.entity.BoardEntity;
import com.green.nowon.domain.entity.NoticeBoardRepository;
import com.green.nowon.domain.entity.NoticeEntity;

@Service
public class NoticeBoardService {
	@Autowired
	NoticeBoardRepository repo;
	//공지사항 리스트
	public Page<NoticeEntity> boardList(Pageable pageable) {
		
		return repo.findAll(pageable);
	}
	//키워드 검색
	public Page<NoticeEntity> boardSearchList(String searchKeyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByTitleContaining(searchKeyword, pageable);
	}
	public void noticeBoardSave(BoardSaveDTO dto) {
		dto.updatedDate=LocalDateTime.now();
		
		repo.save(dto.toNoticeEntity());
		
	}
	public NoticeEntity boardDetail(Integer no) {
		// TODO Auto-generated method stub
		return repo.findById(no)
				.map(e->e.incrementView())
				.get();
	}
	@Transactional
	public void updateProcess(Integer no, BoardUpdateDTO dto) {
		dto.updatedDate=LocalDateTime.now();
		repo.findById(no).map(e->e.updateTitleOrContent(dto));
		
	}
	
	
	
}

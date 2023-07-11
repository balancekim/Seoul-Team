package com.green.nowon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	
}

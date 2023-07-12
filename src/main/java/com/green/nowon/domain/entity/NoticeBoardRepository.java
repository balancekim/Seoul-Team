package com.green.nowon.domain.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface NoticeBoardRepository extends JpaRepository<NoticeEntity, Integer>{

	Page<NoticeEntity> findByTitleContaining(String searchKeyword, Pageable pageable);

	

	List<NoticeEntity> findAllByOrderByNoDesc();



}
package com.green.nowon.domain.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{

	List<CommentEntity> findAllByBoard_no(Integer bno);

	List<CommentEntity> findAllByBoard_noOrderByNoDesc(Integer bno);


	//Page<CommentEntity> findByBoardnoContaining(Integer no, Pageable pageable);

	//Page<CommentEntity> findByBoardno(Integer no, Pageable pageable);

}

package com.green.nowon.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.dto.GoodsDetailDTO;

@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, Long>{

	@Query("SELECT g.no FROM GoodsEntity g WHERE g.title = :title")
    long findNoByTitle(@Param("title") String title);

	Optional<GoodsEntity> findAllByNo(long goodsNo);

	void deleteByNo(long no);




}

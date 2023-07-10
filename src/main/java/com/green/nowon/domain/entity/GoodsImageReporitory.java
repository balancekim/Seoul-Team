package com.green.nowon.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.nowon.domain.dto.S3UploadDTO;

public interface GoodsImageReporitory extends JpaRepository<GoodsImageEntity, Long>{

	void save(S3UploadDTO gno);

	
}

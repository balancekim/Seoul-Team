package com.green.nowon.domain.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.nowon.domain.dto.GoodsImgListDTO;
import com.green.nowon.domain.dto.S3UploadDTO;

public interface GoodsImageReporitory extends JpaRepository<GoodsImageEntity, Long>{

	void save(S3UploadDTO gno);

	GoodsImageEntity findByGoodsAndDef(GoodsEntity goods, boolean def);

	List<GoodsImageEntity> findAllByGoodsNo(long goodsNo);

	
}

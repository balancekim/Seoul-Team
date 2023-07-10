package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.GoodsEntity;
import com.green.nowon.domain.entity.GoodsImageEntity;

import lombok.Builder;
import lombok.Data;

@Data
public class GoodsListDTO {
	
	////이미지 정보
	private String bucketKey;
	
	private String defImg;
	
	
	////상품 정보
	private long no;
	private String title;
	private int price;
	private int stock;
	
	
	public GoodsListDTO defImg(String domain,GoodsImageEntity gIEntity) {
		bucketKey=gIEntity.getBucketKey();
		defImg=domain+bucketKey;
		return this;
	}
	
	public GoodsListDTO(GoodsEntity goods) {
		no=goods.getNo();
		title=goods.getTitle();
		price=goods.getPrice();
		stock=goods.getStock();
	}
	
}

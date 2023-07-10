package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.GoodsImageEntity;

import lombok.Data;

@Data
public class GoodsImgListDTO {
	
	private String bucketKey;
	
	private String url;
	
	private boolean def;
	
	public GoodsImgListDTO url(String domain) {
		url=domain+bucketKey;
		return this;
	}


	public GoodsImgListDTO GoodsImgListDTO(GoodsImageEntity e, String domain) {
		this.bucketKey = e.getBucketKey();
		this.url=domain+bucketKey;
		return this;
	}


	public GoodsImgListDTO(GoodsImageEntity dto, String domain) {
		this.url=domain+dto.getBucketKey();
		this.def=dto.isDef();
	}
}

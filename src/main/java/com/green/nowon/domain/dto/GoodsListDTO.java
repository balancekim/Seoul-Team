package com.green.nowon.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GoodsListDTO {

	private String bucketKey;
	
	private String defImg;
	
	public GoodsListDTO defImg(String domain) {
		defImg=domain+bucketKey;
		return this;
	}
	
	private long no;
	private String title;
	private int price;
	private int stock;
	
}

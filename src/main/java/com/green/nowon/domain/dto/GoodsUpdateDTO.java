package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.CategoryEntity;
import com.green.nowon.domain.entity.GoodsEntity;

import lombok.Data;

@Data
public class GoodsUpdateDTO {

	private long no;
	private String title;
	private int cost;
	private int price;
	private int stock;
	private int state;
	private String content;
	
	private int cateNo;
	
	public GoodsEntity toEntity() {
		return GoodsEntity.builder()
				.title(title).cost(cost).price(price).stock(stock).state(state).content(content).category(CategoryEntity.builder().no(cateNo).build())
				.build();
	}
}

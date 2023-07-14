package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.CategoryEntity;
import com.green.nowon.domain.entity.GoodsEntity;

import lombok.Data;

@Data
public class GoodsDetailDTO {

	private long no;
	private String title;
	private int state;
	private int cost;
	private int price;
	private int stock;
	private String content;
	private CategoryEntity entity;
	
	public GoodsDetailDTO(GoodsEntity e) {
		this.no = e.getNo();
		this.title = e.getTitle();
		this.state = e.getState();
		this.cost = e.getCost();
		this.price = e.getPrice();
		this.stock = e.getStock();
		this.content = e.getContent();
		this.entity=e.getCategory();
	}
	
	
}

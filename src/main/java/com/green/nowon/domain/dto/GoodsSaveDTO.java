package com.green.nowon.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.green.nowon.domain.entity.BoardEntity;
import com.green.nowon.domain.entity.CategoryEntity;
import com.green.nowon.domain.entity.GoodsEntity;

import lombok.Data;

@Data
public class GoodsSaveDTO {

	private String title;
	private int cost;
	private int price;
	private int stock;
	private int state;
	private String content;
	
	private int cateNo;
	
	private String[] tempKey;
	private String[] orgName;
	private String[] newName;
	private boolean[] def;
	
	public List<S3UploadDTO> files(){
		List<S3UploadDTO> list=new ArrayList<>();
		for(int i=0; i<tempKey.length ;i++) {
			if(tempKey[i]!=null && tempKey[i]!="") {
				list.add(S3UploadDTO.builder()
						.tempKey(tempKey[i])
						.orgName(orgName[i])
						.newName(newName[i])
						.def(def[i])
						.build());
			}
		}
		return list;
	}

	public GoodsEntity toEntity() {
		return GoodsEntity.builder()
				.title(title).cost(cost).price(price).stock(stock).state(state).content(content).category(CategoryEntity.builder().no(cateNo).build())
				.build();
	}
	
}

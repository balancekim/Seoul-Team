package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.CategoryEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySaveDTO {

	private String name;
	
	public CategoryEntity toEntity() {
		return CategoryEntity.builder()
				.name(name)
				.build();
	}
}

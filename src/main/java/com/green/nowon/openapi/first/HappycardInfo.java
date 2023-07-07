package com.green.nowon.openapi.first;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class HappycardInfo {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no;
	private String CATEGORY;
	private String COMPANY_NAME;
	private String ADDR;
	private String OFFICE_TEL;
	private String SUPPORT;
	
}

package com.green.nowon.openapi.first;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class Item {
	
	@JsonProperty("CATEGORY")
	private String category;
	
	@JsonProperty("COMPANY_NAME")
	private String companyName;
	
	@JsonProperty("ADDR")
	private String addr;
	
	@JsonProperty("OFFICE_TEL")
	private String officeTel;
	
	@JsonProperty("SUPPORT")
	private String support;
	
	
}

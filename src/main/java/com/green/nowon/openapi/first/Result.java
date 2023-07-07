package com.green.nowon.openapi.first;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Result {

	@JsonProperty("CODE")
	private String code;
	@JsonProperty("MESSAGE")
	private String message;

}

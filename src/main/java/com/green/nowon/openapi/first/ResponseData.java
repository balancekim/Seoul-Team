package com.green.nowon.openapi.first;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseData {
	@JsonProperty("InfoHappycard")
	private InfoHappycard infoHappycard;
}

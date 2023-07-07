package com.green.nowon.openapi.first;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class InfoHappycard {

	@JsonProperty("list_total_count")
	private int listTotalCount;
	@JsonProperty("RESULT")
	private Result result;
	private List<Item> row;
}

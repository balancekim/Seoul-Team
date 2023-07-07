package com.green.nowon.openapi.thr;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Data
public class LOCALDATA_030708 {

	@JsonProperty("list_total_count")
	private int listTotalCount;
	@JsonProperty("RESULT")
	private Result result;
	private List<Item> row;
}

package com.green.nowon.service;

import java.util.List;

import com.green.nowon.openapi.thr.Item;



public interface ApiService3 {

	List<Item> LocalCate3(String keyWord) throws Exception;

	List<Item> LocalCateSearch3(String keyWord, String searchValue) throws Exception;

}

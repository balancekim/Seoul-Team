package com.green.nowon.service;

import java.util.List;

import com.green.nowon.openapi.sec.Item;


public interface ApiService2 {

	List<com.green.nowon.openapi.sec.Item> LocalCate2(String keyWord) throws Exception;

	List<Item> LocalCateSearch2(String keyWord, String searchValue) throws Exception;

}

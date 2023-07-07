package com.green.nowon.service;

import java.util.List;

import org.springframework.ui.Model;

import com.green.nowon.openapi.first.Item;

public interface ApiService {

	

	

	List<Item> LocalCate(String keyWord) throws Exception;

	List<Item> LocalCateSearch(String keyWord, String searchValue) throws Exception;

	

}

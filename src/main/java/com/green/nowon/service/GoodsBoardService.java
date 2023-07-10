package com.green.nowon.service;

import org.springframework.ui.Model;

public interface GoodsBoardService {
	
	public void listProcess(Model model);

	public void detailProcess(long goodsNo, Model model); 
	

}

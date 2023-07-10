package com.green.nowon.service;

import org.springframework.ui.Model;

public interface InfoService {

	String goInfo(String id, String pw);

	void infoList(Model model, String id);

}

package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.green.nowon.domain.entity.BoardEntity;
import com.green.nowon.domain.entity.NoticeEntity;
import com.green.nowon.service.BoardService;
import com.green.nowon.service.NoticeBoardService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeBoardService boardService;
	
	//공지사항 등록 페이지로 이동
	@ResponseBody
	@GetMapping("/admin/notice/new")
	public ModelAndView write() {
		return new ModelAndView("admin/notice/write");
	}
	
	
	//공지사항 리스트서치 및 페이징
	@GetMapping("/notice-board/list")
	public String noticeBoard(Model model,
			@PageableDefault(page=0, size=10, sort="no", direction = Sort.Direction.DESC)Pageable pageable,
			String searchKeyword) {
		
		Page<NoticeEntity> list= null;
		
		if(searchKeyword == null) {
			list = boardService.boardList(pageable);
		}else {
			list = boardService.boardSearchList(searchKeyword, pageable);
		}
		
		int nowPage = list.getPageable().getPageNumber()+1;
		int startPage = Math.max(nowPage-4, 1);
		int endPage = Math.min(nowPage +5, Math.max(1, list.getTotalPages()));
		System.out.println(list.getContent().size());
		
		model.addAttribute("list", list.getContent());
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		
		
		return "board/noticeList";
	}
	
	
}

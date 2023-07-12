package com.green.nowon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.green.nowon.domain.dto.BoardSaveDTO;
import com.green.nowon.domain.dto.BoardUpdateDTO;
import com.green.nowon.domain.entity.NoticeBoardRepository;
import com.green.nowon.domain.entity.NoticeEntity;
import com.green.nowon.service.NoticeBoardService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeBoardService boardService;
	
	@Autowired
	private NoticeBoardRepository nr;
	
	//인덱스에 공지사항 간략 리스트 뿌리기
	@ResponseBody
	@PostMapping("/load-notice")
	public ModelAndView loadNotice() {
		
		List<NoticeEntity> noticeEntity=nr. findAllByOrderByNoDesc();
		List<NoticeEntity> showList=noticeEntity;
		if(noticeEntity.size() > 3) {
			showList=noticeEntity.subList(0, 3);
		}
		System.out.println("!!!!!!!!!!!!!"+noticeEntity);
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("admin/notice/simpleList");
		mv.addObject("list", showList);
		return mv;
	}
	//공지사항 등록 페이지로 html 가져와서 뿌리기
	@ResponseBody
	@GetMapping("/admin/notice/new")
	public ModelAndView write() {
		return new ModelAndView("admin/notice/write");
	}
	//공지사항 수정 페이지로 공지사항 리스트 페이지 뿌리기
	@ResponseBody
	@GetMapping("/admin/notice")
	public ModelAndView update(
			@PageableDefault(page=0, size=10, sort="no", direction = Sort.Direction.DESC)Pageable pageable,
			String searchKeyword) {
		ModelAndView mv=new ModelAndView();
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
		
		mv.addObject("list", list.getContent());
		mv.addObject("nowPage", nowPage);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.setViewName("admin/notice/admin-noticeList");
		
		
		
		return mv;
	}
	
	//공지사항 글 쓰기
	@PostMapping("/notice-board/write")
	public String saveProcess( BoardSaveDTO dto) {
		
		
		boardService.noticeBoardSave( dto);
		return "redirect:/admin";
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
		
		
		
		return "admin/notice/noticeList";
	}
	//특정 공지사항 상세보기
		@GetMapping("/notice-board/{no}")
		public String boardDetail(Model model,
								@PageableDefault(page=0, size=10, sort="no", direction = Sort.Direction.DESC) Pageable pageable,
								@PathVariable("no") Integer no) {
			
			model.addAttribute("board", boardService.boardDetail(no));
			return "admin/notice/detail";
		}
		//공지사항 수정 페이지
		@ResponseBody
		@GetMapping("/notice-board/modify/{no}")
		public ModelAndView boardModify(Model model,
			
				@PageableDefault(page=0, size=10, sort="no", direction = Sort.Direction.DESC) Pageable pageable,
				@PathVariable("no") Integer no) {
			ModelAndView mv=new ModelAndView();
			mv.addObject("board", boardService.boardDetail(no));
			mv.setViewName("admin/notice/admin-modify");
			return mv;
		}
		//특정 게시물 수정 내용 저장
		@PostMapping("/notice-board/update/{no}")
		public String boardUpdate(@PathVariable("no") Integer no, BoardUpdateDTO dto) {
			
			
			boardService.updateProcess(no,dto);
			
			return "redirect:/admin";
		}
		
	
	
}

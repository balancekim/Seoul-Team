package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.nowon.domain.dto.BoardSaveDTO;
import com.green.nowon.domain.dto.BoardUpdateDTO;
import com.green.nowon.domain.entity.BoardEntity;
import com.green.nowon.domain.entity.CommentEntity;
import com.green.nowon.service.BoardService;
import com.green.nowon.service.CommentService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;



@Controller
public class Controller_sm {


	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentService commentService;
	
	//자유게시판 리스트를 페이징하여 보여주기
	@GetMapping("/board/list")
	public String boardList(Model model, 
							@PageableDefault(page=0, size=10, sort="no", direction = Sort.Direction.DESC) Pageable pageable,//페이징
							String searchKeyword) {
		
		Page<BoardEntity> list= null;
		
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
		
		return "board/list";
	}
	//글쓰기 페이지로 이동
	@GetMapping("/board/write")
	public String board() {
		return "board/write";
	}
	//작성 글 저장
	@PostMapping("/board/write")
	public String saveProcess(Authentication authentication, BoardSaveDTO dto) {
		
		boardService.boardSave(authentication.getName(), dto);
		return "redirect:/board/list"; 
	}
	//특정 게시물 상세보기
	@GetMapping("/board/{no}")
	public String boardDetail(Model model,
							@PageableDefault(page=0, size=10, sort="no", direction = Sort.Direction.DESC) Pageable pageable,
							@PathVariable("no") Integer no) {
		
		model.addAttribute("board", boardService.boardDetail(no));
		return "board/detail";
	}
	//특정 게시물 삭제
	@GetMapping("/board/delete/{no}")
	public String boardDelete(Authentication authentication, @PathVariable("no") Integer no) {
		boolean isOwner = boardService.isOwner(authentication.getName(), no);
		if(isOwner) {
			boardService.boardDelete(no);
			return "redirect:/board/list";
		}else {
			return "redirect:/board/list";
		}
	}
	//특정 게시물 수정페이지로 이동
	@GetMapping("/board/modify/{no}")
	public String boardModify(Authentication authentication ,@PathVariable("no") Integer no, Model model) {
		boolean isOwner = boardService.isOwner(authentication.getName(), no);
		if(isOwner) {
			model.addAttribute("board", boardService.boardDetail(no));
			return "board/modify";
		} else {
			return "redirect:/board/list";
		}
	}
	//특정 게시물 수정 내용 저장
	@PostMapping("/board/update/{no}")
	public String boardUpdate(@PathVariable("no") Integer no, BoardUpdateDTO dto) {
		
		
		boardService.updateProcess(no,dto);
		
		return "redirect:/board/list";
	}
}

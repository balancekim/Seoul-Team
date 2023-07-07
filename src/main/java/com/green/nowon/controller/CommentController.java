package com.green.nowon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.green.nowon.domain.dto.CommentUpdateDTO;
import com.green.nowon.domain.entity.CommentEntity;
import com.green.nowon.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	//댓글작성
	@ResponseBody
	@PostMapping("/board/{bno}/comment")
	public void save(Authentication authentication,  @PathVariable("bno") Integer no, String commentContent) {
		
		commentService.commentSave(authentication.getName(), no, commentContent);
		//return "redirect:/board/"+no; 
	}
	
	@ResponseBody
	@GetMapping("/board/{bno}/comment")
	public ModelAndView save( @PathVariable("bno") Integer bno) {
		ModelAndView mv=new ModelAndView("comment/list");
		List<CommentEntity> result=commentService.findAllByBno(bno);
		mv.addObject("list", result);
		return mv;
	}
	
	//댓글수정
	@PostMapping("/board/{bno}/comment/{no}")
	public String editComment(@PathVariable("no") Integer no,
								@PathVariable("bno") Integer bno,CommentUpdateDTO dto) {
		commentService.updateProcess(no,dto);
		
		return "redirect:/board/{bno}";
	}
	//댓글 삭제
	
	  @GetMapping("/board/{bno}/comment/{no}") 
	  public String commentDelete(Authentication authentication, 
			  						@PathVariable("bno") Integer bno,
			  						@PathVariable("no") Integer no) { 
		  boolean isOwner = commentService.isOwner(authentication.getName(), no);
		  if(isOwner) { 
			  commentService.commentDelete(no);
			  return "redirect:/board/{bno}";
		  } else {
			  return "redirect:/board/{bno}";
		  }
	  
	  
	  
	  }
	 
	
}

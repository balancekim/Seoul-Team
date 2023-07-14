package com.green.nowon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.CategoryListDTO;
import com.green.nowon.domain.dto.CategorySaveDTO;
import com.green.nowon.domain.entity.CategoryEntity;
import com.green.nowon.domain.entity.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	
	public void saveProcess(String name) {
		repo.save(CategoryEntity.builder()
				.name(name)
				.build());
	}


	public void listProcess(Model model) {
		List<CategoryEntity> result= repo.findAll();
		model.addAttribute("list", result);
	}


	public void deleteProcess(long no) {
		repo.deleteById(no);
		
	}


	
}

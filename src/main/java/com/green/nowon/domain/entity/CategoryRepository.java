package com.green.nowon.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

	void save(String name);


}

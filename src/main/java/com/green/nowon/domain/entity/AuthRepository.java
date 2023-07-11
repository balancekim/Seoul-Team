package com.green.nowon.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<FindAuth,Long>{

	FindAuth findByEmail(String userEmail);





	



	
	
}

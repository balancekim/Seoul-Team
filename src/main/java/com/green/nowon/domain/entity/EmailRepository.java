package com.green.nowon.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Emailauth,Long>{

	Emailauth findByEmail(String userEmail);





	



	
	
}

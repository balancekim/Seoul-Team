package com.green.nowon.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.nowon.domain.dto.UserDTO;


public interface UserEntityRepository extends JpaRepository<UserEntity,Long>{

	

	

	Optional<UserEntity> findByUserId(String userId);

	Optional<UserEntity> findByEmail(String email);
	

}

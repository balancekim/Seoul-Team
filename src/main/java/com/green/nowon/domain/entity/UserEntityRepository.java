package com.green.nowon.domain.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserEntityRepository extends JpaRepository<UserEntity,Long>{

	

	

	Optional<UserEntity> findByUserId(String userId);

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByUserNameAndEmail(String name, String email);


	

}

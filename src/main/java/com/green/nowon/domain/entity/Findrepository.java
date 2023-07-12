package com.green.nowon.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Findrepository extends JpaRepository<UserEntity, Long>{

	List<UserEntity> findByEmail(String email);

}

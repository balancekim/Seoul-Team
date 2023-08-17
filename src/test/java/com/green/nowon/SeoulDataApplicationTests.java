package com.green.nowon;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.nowon.domain.entity.BoardEntity;
import com.green.nowon.domain.entity.BoardRepository;
import com.green.nowon.domain.entity.UserEntity;
import com.green.nowon.domain.entity.UserEntityRepository;

@SpringBootTest
class SeoulDataApplicationTests {

	@Autowired
	UserEntityRepository repository;
	
	//@Test
	void contextLoads() {
		repository.save(UserEntity.builder()
				.userId("dddd")
				.password("dddd")
				.userName("ddd")
				.build());
	}
}

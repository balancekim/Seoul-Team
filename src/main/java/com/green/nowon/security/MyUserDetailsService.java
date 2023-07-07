package com.green.nowon.security;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.green.nowon.domain.entity.UserEntity;
import com.green.nowon.domain.entity.UserEntityRepository;

public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private  UserEntityRepository repo;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		//DB에 유저가 존재하는지 확인하는 작업
		
		//존재하면 UserDetails 타입으로 리턴 : password 확인 / 정상 인증 session 구성
		
		
		/*
		 * UserEntity result=repo.findByUserId(userId).orElseThrow(()-> new
		 * UsernameNotFoundException("존재하지않아요")); Set<SimpleGrantedAuthority>
		 * grantedAuthority=result.getRole().stream() .map(myRole->new
		 * SimpleGrantedAuthority(myRole.roleName())) .collect(Collectors.toSet()) ;
		 * 
		 * return new User(result.getUserId(),result.getPassword(),grantedAuthority);
		 */
		 
		UserEntity result = repo.findByUserId(userId).orElseThrow(()-> new
				  UsernameNotFoundException("존재하지않아요"));
        if(result!=null) {
            return new CustomDetails(result);
        }

        return null;

	}

}

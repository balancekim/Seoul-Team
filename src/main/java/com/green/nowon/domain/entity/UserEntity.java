package com.green.nowon.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	@Column(unique = true, nullable = false)
	private String userId;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String userName;
	@Column(unique = true, nullable = false)
	private String email;
	@Column
	private String liveIn;
	
	@Builder.Default 
	@Enumerated(EnumType.STRING) 
	@CollectionTable(name="user_role") 
	@ElementCollection(fetch=FetchType.EAGER) 
	private Set<MyRole> role=new HashSet<>(); 
	public UserEntity addRole(MyRole myRole) {
		role.add(myRole);
		return this;
	}
	
	public String getRoleName() {
		
		String roleName=null;
		for (MyRole myRole : role) {
		    roleName = myRole.roleName();
		    // 또는 roleName = myRole.getRoleName(); (getter를 사용하는 경우)
		    break; // 첫 번째 요소만 가져오고 반복문 종료
		}
		return roleName; 
	}
}
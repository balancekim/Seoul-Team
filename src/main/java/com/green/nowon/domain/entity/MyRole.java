package com.green.nowon.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MyRole {
	//기본적으로 final static 이다
	
	USER("ROLE_USER", "일반사용자"),
	ADMIN("ROLE_ADMIN", "관리자"),;
	
	private final String roleName;
	private final String koName;
	
	public final String roleName() {
		return roleName;
	}
	public final String koName() {
		return koName;
	}
}

	
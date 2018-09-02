package com.kevin.venus.auth.model;

import lombok.Data;

@Data
public class LoginInfo {

	private Long loginId;
	private String name;
	private String account;
	
	private Long poolId;
	private String poolName;
	
	public LoginInfo() {
		
	}
	public LoginInfo(Long loginId, String name, String account) {
		this.loginId = loginId;
		this.name = name;
		this.account = account;
	}
	
}

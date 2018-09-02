package com.kevin.venus.auth.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 328353653027518695L;
	
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

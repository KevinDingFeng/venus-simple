package com.kevin.venus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.venus.dao.SysUserDao;

@Service
public class SysUserService {
	
	@Autowired
	private SysUserDao sysUserDao;



}

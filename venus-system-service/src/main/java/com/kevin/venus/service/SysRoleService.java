package com.kevin.venus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.venus.dao.SysRoleDao;

@Service
public class SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;

	
}

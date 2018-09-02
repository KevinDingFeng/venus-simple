package com.kevin.venus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.venus.dao.SysUserDao;
import com.kevin.venus.entity.SysUser;

@Service
public class SysUserService {
	
	@Autowired
	private SysUserDao sysUserDao;

	public SysUser findByAccount(String account) {
		return sysUserDao.findByAccount(account);
	}

	public SysUser findById(Long id) {
		return sysUserDao.findOne(id);
	}

	public SysUser findByCellphone(String cellphone) {
		return sysUserDao.findByCellphone(cellphone);
	}

	public SysUser findByEmail(String email) {
		return sysUserDao.findEmail(email);
	}



}

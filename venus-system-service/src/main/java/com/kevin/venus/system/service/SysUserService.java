package com.kevin.venus.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kevin.venus.system.dao.SysUserDao;
import com.kevin.venus.system.entity.SysUser;

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
		return sysUserDao.findByEmail(email);
	}

	public Page<SysUser> findBySpecification(Specification<SysUser> spec, Pageable pageable) {
		return sysUserDao.findAll(spec, pageable);
	}

	public SysUser save(SysUser user) {
		return sysUserDao.save(user);
	}

	/**
	 * 唯一性校验
	 * 	account 是唯一的，即库中不存在，返回 true；不是唯一的，即库中已经存在，则返回 false
	 * @param account
	 * @return
	 */
	public boolean checkUniquenessForAccount(String account) {
		return this.findByAccount(account) == null;
	}

	public boolean checkUniquenessForCellphone(String cellphone) {
		return this.findByCellphone(cellphone) == null;
	}

	public boolean checkUniquenessForEmail(String email) {
		return this.findByEmail(email) == null;
	}



}

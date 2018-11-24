package com.kevin.venus.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kevin.venus.system.dao.SysRoleDao;
import com.kevin.venus.system.entity.SysRole;

@Service
public class SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;

	public Page<SysRole> findBySpecification(Specification<SysRole> spec, Pageable pageable) {
		return sysRoleDao.findAll(spec, pageable);
	}

	public SysRole findById(Long id) {
		return sysRoleDao.findOne(id);
	}

	public SysRole save(SysRole entity) {
		return sysRoleDao.save(entity);
	}

	public List<SysRole> findByRemoved(boolean removed) {
		return sysRoleDao.findByRemoved(removed);
	}

	
}

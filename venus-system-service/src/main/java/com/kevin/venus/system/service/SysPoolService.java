package com.kevin.venus.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kevin.venus.system.dao.SysPoolDao;
import com.kevin.venus.system.entity.SysPool;

@Service
public class SysPoolService {
	
	@Value("${register.pool.id}")
	private Long sysPoolId;
	
	public Long getSysPoolId() {
		return sysPoolId;
	}
	
	@Autowired
	private SysPoolDao sysPoolDao;

	public SysPool findById(Long id) {
		return sysPoolDao.findOne(id);
	}

	public List<SysPool> findByRemoved(boolean removed) {
		return sysPoolDao.findByRemoved(removed);
	}

}

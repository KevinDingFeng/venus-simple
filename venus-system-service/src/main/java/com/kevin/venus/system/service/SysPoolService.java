package com.kevin.venus.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.venus.system.dao.SysPoolDao;

@Service
public class SysPoolService {
	
	@Autowired
	private SysPoolDao sysPoolDao;

}

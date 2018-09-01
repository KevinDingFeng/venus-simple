package com.kevin.venus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.venus.dao.SysPoolDao;

@Service
public class SysPoolService {
	
	@Autowired
	private SysPoolDao sysPoolDao;

}

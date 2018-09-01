package com.kevin.venus.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kevin.venus.service.SysUserService;

/**
 * 授权接口
 * 	登录
 * 	登出
 * 	登录密码加密
 *  
 * @author 程任强
 *
 */

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private SysUserService userService;
	
}

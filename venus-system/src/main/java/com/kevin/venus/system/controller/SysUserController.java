package com.kevin.venus.system.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kevin.venus.auth.model.LoginInfo;
import com.kevin.venus.entity.SysUser;
import com.kevin.venus.service.SysUserService;

/**
 * 用户管理
 * 	列表、删除、修改、新增
 * 	修改手机号、邮箱
 * 	修改密码
 * 	个人中心
 * @author 程任强
 *
 */

@Controller
@RequestMapping(value = "/user")
public class SysUserController {
	
	@Autowired
	private SysUserService userService;

	@RequestMapping(value = "/center", method = RequestMethod.GET)
	public String center(Model model) {
		
		LoginInfo loginInfo = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
		SysUser user = userService.findById(loginInfo.getLoginId());
		model.addAttribute("entity", user);
		return "user/center";
	}
}

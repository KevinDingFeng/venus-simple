package com.kevin.venus.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kevin.venus.system.entity.SysUser;
import com.kevin.venus.system.service.SysUserService;

@Controller
@RequestMapping(value = "/password")
public class PasswordController {

	@Autowired
	private SysUserService userService;
	/**
	 * 去往重置密码页面
	 * 	担心存在安全问题，不开放该接口
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {

		return "password/index";
	}
	private void initModel(String message, String account, Model model) {
		model.addAttribute("message", message);
		model.addAttribute("account", account);
	}
	/**
	 * 重置密码
	 * 	担心存在安全问题，不开放该接口
	 * @param account
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String reset(@RequestParam(value = "account") String account,
			@RequestParam(value = "password") String password, Model model) {
		SysUser user = userService.findByAccount(account);
		
		if (user == null) {
			System.out.println("用户不存在");
			this.initModel("用户不存在", account, model);
			return "forget/index";
		}
		user.setPassword(password);
		userService.save(user);
		return "redirect:/auth/login";
	}
	
}

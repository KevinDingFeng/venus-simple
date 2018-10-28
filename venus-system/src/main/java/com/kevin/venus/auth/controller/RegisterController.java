package com.kevin.venus.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kevin.venus.system.entity.SysUser;
import com.kevin.venus.system.service.SysPoolService;
import com.kevin.venus.system.service.SysUserService;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {
	
	@Autowired
	private SysUserService userService;
	@Autowired
	private SysPoolService poolService;
	
	/**
	 * 去往注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {

		this.initModel(new SysUser(), null, model);
		return "register/index";
	}

	private void initModel(SysUser user, String message, Model model) {
		model.addAttribute("entity", user);
		model.addAttribute("message", message);
	}
	
	/**
	 * 完成登录操作
	 * @param account
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String register(@Validated @ModelAttribute("entity") SysUser user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			this.initModel(user, result.toString(), model);
			return "register/index";
		}
		//对信息唯一性校验 手机号、邮箱、用户名
		if(user.getAccount() == null || !userService.checkUniquenessForAccount(user.getAccount())) {
			this.initModel(user, "用户名已存在", model);
			return "register/index";
		}
		if(user.getCellphone() == null || !userService.checkUniquenessForCellphone(user.getCellphone())) {
			this.initModel(user, "手机号已存在", model);
			return "register/index";
		}
		if(user.getEmail() == null || !userService.checkUniquenessForEmail(user.getEmail())) {
			this.initModel(user, "邮箱已存在", model);
			return "register/index";
		}
		Long sysPoolId = poolService.getSysPoolId();
		user.setSysPool(poolService.findById(sysPoolId));
		user.setSysPoolId(sysPoolId);
		user = userService.save(user);
		return "redirect:/auth/login";
	}
	
}

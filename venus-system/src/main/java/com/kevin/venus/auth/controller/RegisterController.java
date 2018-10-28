package com.kevin.venus.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kevin.venus.system.entity.SysUser;
import com.kevin.venus.system.service.SysPoolService;
import com.kevin.venus.system.service.SysUserService;
import com.kevin.venus.utils.JsonUtils;
import com.kevin.venus.utils.PasswordUtils;
import com.kevin.venus.utils.RandomUtil;

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
		
		if(user.getPassword() == null || user.getPassword().indexOf(SEP) < 0) {
			this.initModel(user, "密码设置错误", model);
			return "register/index";
		}
		String[] arr = user.getPassword().split(SEP);
		user.setPassword(arr[0]);
		user.setSalt(arr[1]);
		
		Long sysPoolId = poolService.getSysPoolId();
		user.setSysPool(poolService.findById(sysPoolId));
		user.setSysPoolId(sysPoolId);
		
		user = userService.save(user);
		return "redirect:/auth/login";
	}
	private static final String SEP = "KEVIN";// 作为密码和盐值的分隔符
	
	/**
	 * 加密算法，接收登录名和明文的密码 如果登录名在数据库存在，则找到对应的盐值，完成对明文密码加密操作，把密文返回
	 * 如果登录名在数据库不存在或明文密码为空，则返回错误信息 不对密码是否正确进行校验，只负责加密
	 * 
	 * @param name
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/encrypt", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject encrypt(@RequestParam(value = "password") String password) {
		// 获取登录名对应的数据
		String salt = RandomUtil.randomString(16);
		return JsonUtils.getSuccessJSONObject(PasswordUtils.encrypt(password, salt) + SEP + salt);
	}
}

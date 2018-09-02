package com.kevin.venus.auth.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kevin.venus.entity.SysUser;
import com.kevin.venus.service.SysUserService;
import com.kevin.venus.utils.JsonUtils;
import com.kevin.venus.utils.PasswordUtils;

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
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		if(SecurityUtils.getSubject().getPrincipal() != null) {
			System.out.println("处于已登录状态");
			return "redirect:user/center";
		}
		model.addAttribute("entity", new SysUser());
		return "login/login";
	}

	@Autowired
	private SysUserService userService;
	
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
	public JSONObject encrypt(@RequestParam(value = "account") String account, @RequestParam(value = "password") String password) {
		// 获取登录名对应的数据
		SysUser user = userService.findByAccount(account);
		if (user == null || user.getSalt() == null) {
			System.out.println("用户名不存在或用户的盐值不存在");
			return JsonUtils.getFailJSONObject("输入信息有误");
		}
		return JsonUtils.getSuccessJSONObject(PasswordUtils.encrypt(password, user.getSalt()));
	}
	
}

package com.kevin.venus.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kevin.venus.entity.SysUser;
import com.kevin.venus.service.SysUserService;

@Controller
@RequestMapping(value = "/forget")
public class ForgetController {

	@Autowired
	private SysUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		
		model.addAttribute("channel", Channel.Cellphone);//默认 找回密码的途径
		return "forget/index";
	}
	public enum Channel {
		Cellphone("手机号"), Email("邮箱");
		
		private String text;
		
		private Channel(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String send(
			@RequestParam(value = "keyword") String keyword,
			@RequestParam(value = "channel") Channel channel, Model model) {
		SysUser user = null;
		if(Channel.Cellphone.name().equals(channel.name())) {
			user = userService.findByCellphone(keyword);
		}else if(Channel.Email.name().equals(channel.name())){
			user = userService.findByEmail(keyword);
		}
		if(user == null) {
			//TODO 用户不存在
		}
		//发送验证码，并作记录
		if(Channel.Cellphone.name().equals(channel.name())) {
		
		}else if(Channel.Email.name().equals(channel.name())){
		
		}
		model.addAttribute("channel", Channel.Cellphone);//默认 找回密码的途径
		return "forget/index";
	}
}

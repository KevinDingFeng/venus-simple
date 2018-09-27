package com.kevin.venus.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kevin.venus.system.entity.SysUser;
import com.kevin.venus.system.service.SysUserService;
import com.kevin.venus.utils.JsonUtils;
import com.kevin.venus.utils.RandomUtil;

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
	@ResponseBody
	public JSONObject send(
			@RequestParam(value = "keyword") String keyword,
			@RequestParam(value = "channel") Channel channel) {
		SysUser user = null;
		if(Channel.Cellphone.name().equals(channel.name())) {
			user = userService.findByCellphone(keyword);
		}else if(Channel.Email.name().equals(channel.name())){
			user = userService.findByEmail(keyword);
		}
		if(user == null) {
			System.out.println("用户不存在");
			return JsonUtils.getFailJSONObject("输入的用户信息有误");
		}
		//发送验证码，并作记录-对发送的验证码进行一分钟不重复发送校验，对当前发送的信息进行缓存，使用redis 或者 ehcache
		if(Channel.Cellphone.name().equals(channel.name())) {
			//模拟发送手机验证信息 TODO
			String code = RandomUtil.randomString(6);
			System.out.println("模拟发送短信验证码：" + code);//模拟发送 TODO 和缓存
			return JsonUtils.getSuccessJSONObject(code);
		}else if(Channel.Email.name().equals(channel.name())){
			//模拟发送邮箱验证信息 TODO
			String code = RandomUtil.randomString(6);
			System.out.println("模拟发送邮箱验证码：" + code);//模拟发送 TODO 和缓存
			return JsonUtils.getSuccessJSONObject(code);
		}
		return JsonUtils.getFailJSONObject("异常错误");
	}
	/**
	 * 忘记密码之后，选择手机号或者邮箱找回密码，成功通过了校验手机号和邮箱的有效性后，再获取到验证码，到次接口完成对验证码的校验。
	 * 	校验通过，进入设置密码页面；校验不通过，返回到原页面，展示不通过原因。
	 * 设置新密码有两种方式：
	 * 	1，页面只提示校验成功，通过邮箱或者手机发送系统设置的密码过去，然后用户直接用新密码登录成功，然后修改密码。
	 * 	2，页面进入一个编辑页面，用户只要输入两次一致的密码即可，修改成功后，给提示，用户用自己设置的新密码完成登录。
	 * @param keyword
	 * @param channel
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/check_code", method = RequestMethod.POST)
	public String checkCode(
			@RequestParam(value = "keyword") String keyword,
			@RequestParam(value = "channel") Channel channel,
			@RequestParam(value = "code") String code, Model model) {
	
	
		return "";
	}
	
}

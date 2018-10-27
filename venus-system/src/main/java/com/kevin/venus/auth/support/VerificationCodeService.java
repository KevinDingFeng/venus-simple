package com.kevin.venus.auth.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.venus.utils.RandomUtil;
import com.kevin.venus.utils.RedisUtils;

@Service
public class VerificationCodeService {

	@Autowired
	private RedisUtils redisUtils;

	private static final String V_CODE_PREFIX = "v_code_";

	/**
	 * 模拟发送手机验证信息 TODO
	 * 
	 * @param cellphone
	 * @return
	 */
	public String sendToCellphone(String cellphone) {
		String code = RandomUtil.randomNum(6);
		System.out.println("模拟发送短信验证码：" + code);// 模拟发送 TODO
		// 缓存
		redisUtils.set(V_CODE_PREFIX + cellphone, code, 60L);
		return code;
	}

	/**
	 * 模拟发送邮箱验证信息 TODO
	 * @param email
	 * @return
	 */
	public String sendToEmail(String email) {
		String code = RandomUtil.randomNum(6);
		System.out.println("模拟发送邮箱验证码：" + code);// 模拟发送 TODO 和
		// 缓存
		redisUtils.set(V_CODE_PREFIX + email, code, 60L);
		return code;
	}

	public boolean validateByCellphone(String code, String cellphone) {
		String v = redisUtils.get(V_CODE_PREFIX + cellphone);
		return v != null && v.equals(code);
	}

	public boolean validateByEmail(String code, String email) {
		String v = redisUtils.get(V_CODE_PREFIX + email);
		return v != null && v.equals(code);
	}

}

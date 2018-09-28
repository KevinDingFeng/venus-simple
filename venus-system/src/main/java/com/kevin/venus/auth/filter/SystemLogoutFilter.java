package com.kevin.venus.auth.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.stereotype.Service;

public class SystemLogoutFilter extends LogoutFilter{

	@Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		//清楚缓存数据
		System.out.println("清楚缓存数据");
		return super.preHandle(request, response);
	}
}

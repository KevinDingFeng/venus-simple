package com.kevin.venus.shiro.conf;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.kevin.venus.redis.realization.KevinRedisCacheManager;
import com.kevin.venus.redis.realization.KevinRedisSessionDAO;
import com.kevin.venus.shiro.realization.KevinSessionManager;
import com.kevin.venus.shiro.realization.KevinShiroRealm;
import com.kevin.venus.shiro.realization.KevinSimpleCredentialsMatcher;

/**
 * Shiro 配置
 * 
 * @author kevin
 *
 */
@Configuration
public class ShiroConfig {

	/**
	 * 设置 Shiro 的 Filter 6
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 注意过滤器配置顺序 不能颠倒
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
		filterChainDefinitionMap.put("/logout", "logout");
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/s/**", "anon");
		filterChainDefinitionMap.put("/f/**", "anon");

		filterChainDefinitionMap.put("/auth/**", "anon");
		filterChainDefinitionMap.put("/forget/**", "anon");
		
		
		filterChainDefinitionMap.put("/register", "anon");
		filterChainDefinitionMap.put("/register/**", "anon");
		filterChainDefinitionMap.put("/retrieve", "anon");
		filterChainDefinitionMap.put("/retrieve/**", "anon");
		

		filterChainDefinitionMap.put("/403", "anon");
//		filterChainDefinitionMap.put("/**", "authc");
		filterChainDefinitionMap.put("/**", "anon");

		// 配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
		shiroFilterFactoryBean.setLoginUrl("/auth/login");
		// 登录成功后要跳转的链接，此处不做设置，在登录流程中，会判断是否需要跳回到请求链接
//		 shiroFilterFactoryBean.setSuccessUrl("/user/list");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	public static final String HASH_ALGORITHM_NAME = "md5";// 散列算法:这里使用MD5算法;
	public static final int HASH_ITERATIONS = 10;// 散列的次数，比如散列两次，相当于 md5(md5(""));

	/**
	 * 判断密码是否匹配的处理逻辑
	 * 
	 * @return
	 */
	@Bean
	public KevinSimpleCredentialsMatcher kevinSimpleCredentialsMatcher() {
		KevinSimpleCredentialsMatcher cm = new KevinSimpleCredentialsMatcher();
		return cm;
	}

	/**
	 * 授权和认证处理 2
	 * 
	 * @return
	 */
	@Bean
	public KevinShiroRealm kevinShiroRealm() {
		KevinShiroRealm kevinShiroRealm = new KevinShiroRealm();
		// kevinShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		kevinShiroRealm.setCredentialsMatcher(kevinSimpleCredentialsMatcher());
		kevinShiroRealm.setCacheManager(kevinRedisCacheManager());
		return kevinShiroRealm;
	}
	/**
	 * Security 设置 暂时不使用 redis 缓存 cache 信息 1
	 * 
	 * @return
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(kevinShiroRealm());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(kevinSessionManager());
		// // 自定义缓存实现 使用redis
		securityManager.setCacheManager(kevinRedisCacheManager());
		return securityManager;
	}

	/**
	 * 自定义sessionManager 暂时不使用 redis 缓存 session 信息 4
	 * 
	 * @return
	 */
	@Bean
	public KevinSessionManager kevinSessionManager() {
		KevinSessionManager kevinSessionManager = new KevinSessionManager();
		kevinSessionManager.setCacheManager(kevinRedisCacheManager());
		kevinSessionManager.setSessionDAO(kevinRedisSessionDAO());
//		kevinSessionManager.setGlobalSessionTimeout(1800);//毫秒
		return kevinSessionManager;
	}
	/**
	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 * 
	 * @return
	 */
	@Bean
	@DependsOn({ "lifecycleBeanPostProcessor" })
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		LifecycleBeanPostProcessor l = new LifecycleBeanPostProcessor();
		return l;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持; 7
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

    @Bean
    public KevinRedisCacheManager kevinRedisCacheManager() {
        return new KevinRedisCacheManager();
    }
	
	// /**
	// * RedisSessionDAO shiro sessionDao层的实现 通过redis
	// * <p>
	// * 使用的是shiro-redis开源插件
	// */
	@Bean
	public KevinRedisSessionDAO kevinRedisSessionDAO() {
		KevinRedisSessionDAO kevinRedisSessionDAO = new KevinRedisSessionDAO();
//		redisSessionDAO.setRedisManager(redisCacheManager());
		return kevinRedisSessionDAO;
	}

}

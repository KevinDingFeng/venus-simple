﻿venus 维纳斯，金星
	一个可用的管理框架，包括注册、登录、忘记密码、个人中心、修改密码、手机号验证、邮箱验证、用户管理、角色管理、权限管理
	分前后端分离和不分离两套，尽量使service层及以下部分可以复用
	
不分离使用到的技术：
	freemarker
	shiro
	redis
	jpa
	
结构：
	venus-common
	venus-system				使用ftl页面实现
	venus-system-service
	venus-system-dao
	venus-system-entity
	
system 功能点
	登录（用户名+密码）
	忘记密码（通过手机号或邮箱找回）
	注册（手机号、邮箱、用户名唯一性校验）
	手机号认证
	邮箱认证
	个人中心
	修改密码（原密码验证）
	修改手机号（原手机号验证）
	修改邮箱（原邮箱验证）
	修改其他基础信息
	用户管理：列表、新增、删除、修改 
	角色管理：列表、新增、删除、修改
	权限管理：列表、新增、删除、修改
	
system 現在完成的内容（有路径的表示经过了测试）：
	去往登录页
		/auth/login get
	登录（对用户名和密码进行校验，单独的加密接口） kevin 123
		/auth/encrypt get
		/auth/login post
	登出
		/logout get 使用配置文件里面的路径就可以，没有特殊需求不需要重定义
	个人中心（获取展示登录用户自身的信息）
		/user/center get
		手机号认证
		邮箱认证
		修改密码（原密码验证）
		修改手机号（原手机号验证）
		修改邮箱（原邮箱验证）
		修改其他基础信息
	去往忘记密码页-------------不开启
		/forget get
	通过手机号、邮箱号找回密码（可切换两种验证身份的方式，发送验证码，然后重置密码），即确认手机号和邮箱有效且在数据库中存在，然后通过验证码校验，再进入重置密码页-------------不开启
		/forget/send get 给手机号、邮箱发送验证码
		/forget/check_code post 提交手机号、邮箱验证码校验
	去往重置密码的页面-------------不开启
		/password get
	完成重置密码，前提是需要输入 account ，且该 account 在数据库存在，且该记录的 salt 有效-------------不开启
		/auth/encrypt get	密码加密
		/password/reset post
	去注册页面
		/register get
	注册密码加密
		/register/encrypt get
	完成注册
		/register post
	用户管理：
		列表-翻页、搜索
            /user get post
		新增、删除、修改
            /user/form
            /user/form?id=${id}
            /user/create
            /user/update
            /user/removed?id=${id}
	角色管理：列表、新增、删除、修改-------------------功能比较重复，且修改几率不大，所以不做完整开发
	权限管理：列表、新增、删除、修改-------------------功能比较重复，且修改几率不大，所以不做完整开发 
	
system 功能点扩展
	登录：使用手机号+密码登录、使用邮箱+密码登录、使用手机号+短信验证码登录、使用邮箱+邮箱验证码登录
	登录添加验证码
	shiro 权限判断添加，前后台
	缓存
	多环境配置文件
	
	
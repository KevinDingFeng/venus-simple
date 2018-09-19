package com.kevin.venus.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kevin.venus.auth.model.LoginInfo;
import com.kevin.venus.system.entity.SysUser;
import com.kevin.venus.system.service.SysUserService;

/**
 * 用户管理
 * 	列表、删除、修改、新增
 * 	修改手机号、邮箱
 * 	修改密码
 * 	个人中心
 * @author 程任强
 *
 */

@Controller
@RequestMapping(value = "/user")
public class SysUserController {
	
	@Autowired
	private SysUserService userService;

	@RequestMapping(value = "/center", method = RequestMethod.GET)
	public String center(Model model) {
		
		LoginInfo loginInfo = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
		SysUser user = userService.findById(loginInfo.getLoginId());
		model.addAttribute("entity", user);
		return "user/center";
	}
	
	/**
	 * 获取用户列表
	 * 	TODO 添加权限校验
	 * 	顺便测试：left join fetch 在 jpa 中是否好用 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(@RequestParam(value = "pageNum", required = false) Integer pageNum, 
			@RequestParam(value = "keyword", required = false) String keyword, Model model) {
//		TODO 添加权限校验
		Sort sort = new Sort(Direction.DESC, "creation");
		pageNum = pageNum == null ? 0 : pageNum;
		Pageable pageable = new PageRequest(pageNum, 10, sort);
		Page<SysUser> page = userService.findBySpecification(this.getSpecification(keyword), pageable);
		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageNum", pageNum);
		return "user/list";
	}
	private Specification<SysUser> getSpecification(String keyword) {

		return new Specification<SysUser>() {
			@Override
			public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.fetch("sysPool", JoinType.LEFT);
				
				List<Predicate> list = new ArrayList<Predicate>();
				
//				list.add(cb.isTrue(root.get("removed").as(Boolean.class)));//为测试返回空集合故意添加
				
				if (StringUtils.isNotBlank(keyword)) {
					String t = "%" + keyword.trim() + "%";
					list.add(cb.like(root.get("name").as(String.class), t));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
}

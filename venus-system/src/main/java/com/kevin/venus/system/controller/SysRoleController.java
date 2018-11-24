package com.kevin.venus.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kevin.venus.system.entity.SysRole;
import com.kevin.venus.system.service.SysRoleService;
import com.kevin.venus.utils.JsonUtils;

/**
 * 角色管理
 * 	列表、删除、修改、新增
 * @author 程任强
 *
 */

@Controller
@RequestMapping(value = "/role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 获取角色列表
	 * 	TODO 添加权限校验
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam(value = "pageNum", required = false) Integer pageNum, 
			@RequestParam(value = "keyword", required = false) String keyword, Model model) {
//		TODO 添加权限校验
		Sort sort = new Sort(Direction.DESC, "creation");
		pageNum = pageNum == null ? 0 : pageNum;
		Pageable pageable = new PageRequest(pageNum, 1, sort);
		Page<SysRole> page = sysRoleService.findBySpecification(this.getSpecification(keyword), pageable);
		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageNum", pageNum);
		return "role/list";
	}
	private Specification<SysRole> getSpecification(String keyword) {

		return new Specification<SysRole>() {
			@Override
			public Predicate toPredicate(Root<SysRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (StringUtils.isNotBlank(keyword)) {
					String t = "%" + keyword.trim() + "%";
					list.add(cb.like(root.get("name").as(String.class), t));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
	/**
	 * 根据 id 删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/removed", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject removed(@RequestParam(value = "id") Long id, 
			@RequestParam(value = "removed") boolean removed) {
//		TODO 添加权限校验
		try {
			SysRole role = sysRoleService.findById(id);
			role.setRemoved(removed);
			sysRoleService.save(role);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.getFailJSONObject();
		}
		return JsonUtils.getSuccessJSONObject();
	}
}

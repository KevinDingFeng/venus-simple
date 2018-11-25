package com.kevin.venus.system.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.kevin.venus.auth.model.LoginInfo;
import com.kevin.venus.system.entity.SysPool;
import com.kevin.venus.system.entity.SysRole;
import com.kevin.venus.system.entity.SysUser;
import com.kevin.venus.system.service.SysPoolService;
import com.kevin.venus.system.service.SysRoleService;
import com.kevin.venus.system.service.SysUserService;
import com.kevin.venus.utils.FileIOUtil;
import com.kevin.venus.utils.JsonUtils;
import com.kevin.venus.utils.PasswordUtils;

/**
 * 用户管理 列表、删除、修改、新增 修改手机号、邮箱 修改密码 个人中心
 * 
 * @author 程任强
 *
 */

@Controller
@RequestMapping(value = "/user")
public class SysUserController {

	@Autowired
	private SysUserService userService;
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private SysPoolService poolService;

	/**
	 * 设置允许自动绑定的属性名称
	 * 
	 * @param binder
	 * @param req
	 */
	@InitBinder("entity")
	private void initBinder(ServletRequestDataBinder binder, HttpServletRequest req) {
		List<String> fields = new ArrayList<String>(Arrays.asList("sysPoolId", "account", "name", "salt", "password", "email", "cellphone", "roles"));
		switch (req.getMethod().toLowerCase()) {
		case "post": // 新增 和 修改
			binder.setAllowedFields(fields.toArray(new String[fields.size()]));
			break;
		default:
			break;
		}
	}

	/**
	 * 预处理，一般用于新增和修改表单提交后的预处理
	 * 
	 * @param id
	 * @param req
	 * @return
	 */
	@ModelAttribute("entity")
	public SysUser prepare(@RequestParam(value = "id", required = false) Long id, HttpServletRequest req) {
		String method = req.getMethod().toLowerCase();
		if (id != null && id > 0 && "post".equals(method)) {// 修改表单提交后数据绑定之前执行
			return userService.findById(id);
		} else if ("post".equals(method)) {// 新增表单提交后数据绑定之前执行
			return new SysUser();
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/center", method = RequestMethod.GET)
	public String center(Model model) {

		LoginInfo loginInfo = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
		SysUser user = userService.findById(loginInfo.getLoginId());
		model.addAttribute("entity", user);
		return "user/center";
	}

	/**
	 * 获取用户列表 TODO 添加权限校验 顺便测试：left join fetch 在 jpa 中是否好用
	 * 
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String list(@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "keyword", required = false) String keyword, Model model) {
		// TODO 添加权限校验
		Sort sort = new Sort(Direction.DESC, "creation");
		pageNum = pageNum == null ? 0 : pageNum;
		Pageable pageable = new PageRequest(pageNum, 1, sort);
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
				// root.fetch("sysPool", JoinType.LEFT); 组合查询使用 fetch 看来有问题

				List<Predicate> list = new ArrayList<Predicate>();

				// list.add(cb.isTrue(root.get("removed").as(Boolean.class)));//为测试返回空集合故意添加

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
	 * 根据 id 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/removed", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject removed(@RequestParam(value = "id") Long id, @RequestParam(value = "removed") boolean removed) {
		// TODO 添加权限校验
		try {
			SysUser user = userService.findById(id);
			user.setRemoved(removed);
			userService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.getFailJSONObject();
		}
		return JsonUtils.getSuccessJSONObject();
	}

	/**
	 * 进入编辑用户的页面 id存在表示修改 id不存在表示新增 需要输入公司和角色信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam(value = "id", required = false) Long id, Model model) {
		// TODO 添加权限校验
		// 用户信息
		SysUser user = null;
		if (id != null && id > 0L) {
			user = userService.findById(id);
		} else {
			user = userService.getDefault();
		}
		model.addAttribute("entity", user);

		this.initFormModel(model);

		return "user/form";
	}

	private void initFormModel(Model model) {
		// 角色信息
		List<SysRole> roleList = roleService.findByRemoved(false);
		model.addAttribute("roleList", roleList);
		// 公司信息
		List<SysPool> poolList = poolService.findByRemoved(false);
		model.addAttribute("poolList", poolList);
	}

	private void initModel(SysUser user, String message, Model model) {
		model.addAttribute("entity", user);
		model.addAttribute("message", message);
		this.initFormModel(model);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("entity") SysUser user, Model model) {
		// TODO 添加权限校验
		SysUser original = userService.findById(user.getId());
		try {
			if (user.getCellphone() == null || !userService.checkUniquenessForCellphone(user.getId(), user.getCellphone())) {
				this.initModel(user, "手机号已存在", model);
				return "user/form";
			}
			if (user.getEmail() == null || !userService.checkUniquenessForEmail(user.getId(), user.getEmail())) {
				this.initModel(user, "邮箱已存在", model);
				return "user/form";
			}
			// 修改密码可以为 null
			if (user.getPassword() != null && !user.getPassword().trim().equals("")) {
				if (user.getPassword().indexOf(PasswordUtils.SEP) < 0) {
					//对比数据库中的值，是否一致，一致不做修改；不一致返回错误
					if(!user.getPassword().equals(original.getPassword())) {
						this.initModel(user, "密码设置错误", model);
						return "user/form";
					}
				}else {
					String[] arr = user.getPassword().split(PasswordUtils.SEP);
					user.setPassword(arr[0]);
					user.setSalt(arr[1]);
				}
			}
			user = userService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			this.initModel(user, "特殊错误", model);
			return "user/form";
		}
		return "redirect:/user";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("entity") SysUser user, Model model) {
		// TODO 添加权限校验
		try {
			if (user.getAccount() == null || !userService.checkUniquenessForAccount(user.getAccount())) {
				this.initModel(user, "用户名已存在", model);
				return "user/form";
			}
			if (user.getCellphone() == null || !userService.checkUniquenessForCellphone(null, user.getCellphone())) {
				this.initModel(user, "手机号已存在", model);
				return "user/form";
			}
			if (user.getEmail() == null || !userService.checkUniquenessForEmail(null, user.getEmail())) {
				this.initModel(user, "邮箱已存在", model);
				return "user/form";
			}
			//新增密码不能为 空
			if (user.getPassword() == null || user.getPassword().indexOf(PasswordUtils.SEP) < 0) {
				this.initModel(user, "密码设置错误", model);
				return "user/form";
			}
			String[] arr = user.getPassword().split(PasswordUtils.SEP);
			user.setPassword(arr[0]);
			user.setSalt(arr[1]);

			Long sysPoolId = user.getSysPoolId();
			user.setSysPool(poolService.findById(sysPoolId));

			user = userService.save(user);

		} catch (Exception e) {
			e.printStackTrace();
			this.initModel(user, "特殊错误", model);
			return "user/form";
		}
		return "redirect:/user";
	}
	@RequestMapping("/head_img")
	@ResponseBody
	public JSONObject uploadFile(@RequestParam(value = "headImg", required = true) MultipartFile uploadFile) {
		if (uploadFile.getSize() != 0L) {
			try {
				LoginInfo loginInfo = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
				SysUser user = userService.findById(loginInfo.getLoginId());
				
				String uploadPath = this.uploadFile(uploadFile.getOriginalFilename(), uploadFile.getInputStream());
				user.setHeadImg(uploadPath);
				userService.save(user);
				String showPath = showFilePath + uploadPath;
				return JsonUtils.getSuccessJSONObject(showPath);
			} catch (IOException e) {
				e.printStackTrace();
				return JsonUtils.getFailJSONObject();
			}
		}
		return JsonUtils.getFailJSONObject();
	}
	/**
	 * 上传文件根路径
	 */
	@Value("${upload.file.path}")
	private String uploadFilePath;
	
	@Value("${show.file.path}")
	private String showFilePath;
	
	private String uploadFile(String filename, InputStream is) {
		return FileIOUtil.uploadFile(filename, is, uploadFilePath, true);
	}
}

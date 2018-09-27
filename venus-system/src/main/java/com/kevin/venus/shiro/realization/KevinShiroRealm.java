package com.kevin.venus.shiro.realization;

import java.util.Iterator;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.kevin.venus.auth.model.LoginInfo;
import com.kevin.venus.system.entity.SysPermission;
import com.kevin.venus.system.entity.SysRole;
import com.kevin.venus.system.entity.SysUser;
import com.kevin.venus.system.service.SysUserService;

/**
 * 认证和授权处理
 * 
 * @author kevin
 *
 */
public class KevinShiroRealm extends AuthorizingRealm {


	@Autowired
	private SysUserService userService;

	/**
	 * 加入登录用户的角色和权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		LoginInfo info = (LoginInfo) principals.getPrimaryPrincipal();
		SysUser user = userService.findByAccount(info.getAccount());
		Set<SysRole> roles = user.getRoles();
		if(roles != null) {
			Iterator<SysRole> roleIts = roles.iterator();
			while(roleIts.hasNext()) {
				SysRole role = roleIts.next();
				if(role.isRemoved()) {
					continue;
				}
				authorizationInfo.addRole(role.getName());
				Set<SysPermission> perms = role.getPermissions();
				if(perms != null) {
					Iterator<SysPermission> permIts = perms.iterator();
					while(permIts.hasNext()) {
						SysPermission perm = permIts.next();
						authorizationInfo.addStringPermission(perm.getPerm());
					}
				}
			}
		}
		return authorizationInfo;
	}

	/**
	 * 登录成功认证 salt 值，暂时使用用户的登录名，所以需要从数据库层级把登录名字段设置为唯一键；以后版本可以生成新的 salt 值，并对 salt
	 * 进行管理 10
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		System.out.println("获取登录者信息-->MyShiroRealm.doGetAuthenticationInfo()");
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String account = token.getUsername();
		SysUser user = userService.findByAccount(account);
		
		if (user != null) {
			LoginInfo info = new LoginInfo(user.getId(), user.getName(), account);
			info.setPoolId(user.getSysPoolId());
			info.setPoolName(user.getSysPool().getName());
			return new SimpleAuthenticationInfo(info, user.getPassword(), getName()); 
		}
		return null;
	}
	
	/**
     * Calls {@code super.doClearCache} to ensure any cached authentication data is removed and then calls
     * {@link #clearCachedAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)} to remove any cached
     * authorization data.
     * <p/>
     * If overriding in a subclass, be sure to call {@code super.doClearCache} to ensure this behavior is maintained.
     *
     *	TODO 需要显示调用，才可以清除登出后的缓存
     * @param principals the principals of the account for which to clear any cached AuthorizationInfo
     * @since 1.2
     */
    @Override
    protected void doClearCache(PrincipalCollection principals) {
        super.doClearCache(principals);
        System.out.println("删除");
        clearCachedAuthorizationInfo(principals);
    }

}

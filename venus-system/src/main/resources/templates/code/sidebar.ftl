<!-- sidebar start -->
<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
	<div class="am-offcanvas-bar admin-offcanvas-bar">
    	<ul class="am-list admin-sidebar-list">
        	<li><a href="/user/center"><span class="am-icon-home"></span> 用户中心</a></li>
			<@shiro.hasPermissionOne name=["user:view", "role:view"]>  
        	<li class="admin-parent">
          		<a class="am-cf" data-am-collapse="{target: '#collapse-nav'}">
          			<span class="am-icon-file"></span> 系统管理<span class="am-icon-angle-right am-fr am-margin-right"></span>
          		</a>
          		<ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
        			<li><a href="/user"><span class="am-icon-table"></span> 用户管理</a></li>
        			<li><a href="/role"><span class="am-icon-table"></span> 角色管理</a></li>
          		</ul>
        	</li>
        	</@shiro.hasPermissionOne>
      	</ul>
	</div>
</div>
<!-- sidebar end -->
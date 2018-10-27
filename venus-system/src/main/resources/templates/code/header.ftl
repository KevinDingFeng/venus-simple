<header class="am-topbar am-topbar-inverse admin-header">
	<div class="am-topbar-brand">
		<strong>Venus Kevin</strong> <small>管理平台</small>
	</div>
	<button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>
		<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
		<ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
			<li class="am-dropdown" data-am-dropdown>
				<a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
					<span class="am-icon-users"></span> ${(loginUser.name)!"管理员"} <#-- span class="am-icon-caret-down" --></span>
				</a>
			</li>
			<li class="am-dropdown" data-am-dropdown onclick="javascript:window.location.href='/logout'">
				<a class="am-dropdown-toggle" data-am-dropdown-toggle href="/logout">
					<span class="am-icon-power-off"></span> 退出<#-- span class="am-icon-caret-down" --></span>
				</a>
			</li>
		</ul>
	</div>
</header>
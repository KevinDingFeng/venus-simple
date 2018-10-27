<@override name="title">
	<title>Venus - User Center</title>
</@override>
<@override name="head">
	<link rel="stylesheet" href="/assets/css/admin.css">
</@override>
<@override name="body">
	<#include "/code/header.ftl">
	<div class="am-cf admin-main">
		<#include "/code/sidebar.ftl">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">用户中心</strong> / <small>User Center</small>
					</div>
				</div>
    			<hr />
    			<div class="am-tabs am-margin" data-am-tabs>
      				<ul class="am-tabs-nav am-nav am-nav-tabs">
        				<li class="am-active"><a href="#tab1">个人信息</a></li>
      				</ul>
      				<div class="am-tabs-bd">
        				<div class="am-tab-panel am-fade am-in am-active" id="tab1">
            				<div class="am-g am-margin-top">
				            	<div class="am-u-sm-4 am-u-md-2 am-text-right">名称</div>
								<div class="am-u-sm-8 am-u-md-4">${(entity.name)!}</div>
								<div class="am-hide-sm-only am-u-md-6"></div>
            				</div>
				            <div class="am-g am-margin-top">
				            	<div class="am-u-sm-4 am-u-md-2 am-text-right">用户名</div>
				            	<div class="am-u-sm-8 am-u-md-4">${(entity.account)!}</div>
				            	<div class="am-hide-sm-only am-u-md-6"></div>
            				</div>
						</div>
					</div>
				</div>
			</div>
			<#include "/code/footer.ftl">
		</div>
		<!-- content end -->
	</div>
	<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>
	<script src="/assets/js/amazeui.min.js"></script>
	<script src="/assets/js/app.js"></script>
</@override>
<@extends name="/code/base.ftl"></@extends>
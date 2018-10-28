<@override name="title">
	<title>Venus - User Manage</title>
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
						<strong class="am-text-primary am-text-lg">用户列表</strong> / <small>User List</small>
					</div>
		    	</div>
    			<hr />
				<div class="am-g">
					<div class="am-u-sm-12 am-u-md-3">
						<form method="post" class="am-form" action="/user" id="userListForm">
						<input type="hidden" id="pageNum" name="pageNum" value="${pageNum!}" />
						<div class="am-input-group am-input-group-sm">
							<input type="text" class="am-form-field" name="keyword" value="${keyword!}" placeholder="请输入名称" />
							<span class="am-input-group-btn">
								<button class="am-btn am-btn-primary" type="button" onclick="doSearch()">搜索</button>
							</span>
						</div>
						</form>
						<script>
							function doSearch(){
								$("#assignOrderForm").submit();
							}
						</script>
					</div>
				</div>
				<div class="am-g">
        			<div class="am-u-sm-12">
        				<table class="am-table am-table-striped am-table-hover table-main">
              				<thead>
              				<tr>
								<#--<th class="table-check"><input type="checkbox" /></th>
								<th class="table-id">ID</th>-->
								<th class="table-author am-hide-sm-only">用户名</th>
								<th class="table-author am-hide-sm-only">名称</th>
								<th class="table-author am-hide-sm-only">公司名称</th>
								<th class="table-author am-hide-sm-only">联系方式</th>
								<th class="table-author am-hide-sm-only">邮箱</th>
								<th class="table-set">操作</th>
							</tr>
							</thead>
							<tbody>
							<#if page.content??>
							<#list page.content as em>
							<tr>
								<#--<td><input type="checkbox" /></td>
								<td>1</td>-->
								<td>${em.account}</td>
								<td>${em.name!}</td>
								<td>${em.sysPool.name!}</td>
								<td>${em.cellphone!}</td>
								<td>${em.email!"-"}</td>
								<td>
								<#-- 删除，修改 -->
									<div class="am-btn-toolbar">
				                    	<div class="am-btn-group am-btn-group-xs">
						        			<a href="#" class="am-btn am-btn-default am-btn-xs am-text-secondary " ><span class="am-icon-pencil-square-o"></span> 修改</a>
						                    <a href="#" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" ><span class="am-icon-trash-o"></span> 删除</a>
				                    	</div>
				                	</div>
								</td>
							</tr>
							</#list>
							<#else>
							<tr>
							  	<td>沒有符合的数据</td>
							</tr>
              				</#if>
              				</tbody>
						</table>
						<#import "/code/component.ftl" as p>
						<@p.pageInfo page pageNum "userListForm" />
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
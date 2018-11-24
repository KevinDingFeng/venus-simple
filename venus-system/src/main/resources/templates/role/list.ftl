<@override name="title">
	<title>Venus - Role Manage</title>
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
						<strong class="am-text-primary am-text-lg">角色列表</strong> / <small>Role List</small>
					</div>
		    	</div>
    			<hr />
				<div class="am-g">
				    <div class="am-u-sm-12 am-u-md-6">
                    <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">
								<a href="/role/form" class="am-btn am-btn-default am-btn-xs am-text-secondary " ><span class="am-icon-plus"></span> 新增</a>
                            </div>
                        </div>
                    </div>
					<div class="am-u-sm-12 am-u-md-3">
						<form method="post" class="am-form" action="/role" id="roleListForm">
						<input type="hidden" id="pageNum" name="pageNum" value="${pageNum!}" />
						<div class="am-input-group am-input-group-sm">
							<input type="text" class="am-form-field" name="keyword" value="${keyword!}" placeholder="请输入名称" />
							<span class="am-input-group-btn">
								<button class="am-btn am-btn-primary" type="button" onclick="doSearch()">搜索</button>
							</span>
						</div>
						</form>
					</div>
				</div>
				<div class="am-g">
        			<div class="am-u-sm-12">
        				<table class="am-table am-table-striped am-table-hover table-main">
              				<thead>
              				<tr>
								<#--<th class="table-check"><input type="checkbox" /></th>
								<th class="table-id">ID</th>-->
								<th class="table-author am-hide-sm-only">名称</th>
								<th class="table-author am-hide-sm-only">等级</th>
								<th class="table-author am-hide-sm-only">是否已删除</th>
								<th class="table-set">操作</th>
							</tr>
							</thead>
							<tbody>
							<#if page.content??>
							<#list page.content as em>
							<tr>
								<#--<td><input type="checkbox" /></td>
								<td>1</td>-->
								<td>${em.name!}</td>
								<td>${em.level!}</td>
								<td>${em.removed?string("是","否")}</td>
								<td>
								    <#-- 删除，修改 -->
									<div class="am-btn-toolbar">
				                    	<div class="am-btn-group am-btn-group-xs">
						        			<a href="/role/form?id=${em.id}" class="am-btn am-btn-default am-btn-xs am-text-secondary " ><span class="am-icon-pencil-square-o"></span> 修改</a>
						                    <a href="javascript:recoveryOrRemoved(${em.removed?string("true", "false")}, ${em.id});" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" ><span class="am-icon-trash-o"></span> <#if em.removed>恢复<#else>删除</#if></a>
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
						<@p.pageInfo page pageNum "roleListForm" />
					</div>
				</div>
			</div>
			<#include "/code/footer.ftl">
		</div>
  		<!-- content end -->
  	</div>
  	 <script>
        function doSearch(){
            $("#roleListForm").submit();
        }
        function recoveryOrRemoved(bool, id){
            if(id){
                var noteText = bool ? '确定恢复该用户吗？' : '确定删除该用户吗？';
                var r = !bool;
                if (window.confirm(noteText)) {
                    $.ajax({
                        url:'/role/removed',
                        type:'POST',
                        data:{id: id, removed: r},
                        success:function(res){
                            console.log(res);
                            if(res.code == "200"){
                                console.log("删除成功");
                                window.location.reload();
                            }else{
                                alert("加密失败");
                            }
                        }
                    })
                }
            }
        }
    </script>
	<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>
	<script src="/assets/js/amazeui.min.js"></script>
	<script src="/assets/js/app.js"></script>
</@override>
<@extends name="/code/base.ftl"></@extends>
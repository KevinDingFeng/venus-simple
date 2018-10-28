<#macro pageInfo page pageNum formId>
<div class="am-cf">
	共 ${page.totalElements} 条记录
	<div class="am-fr">
		<ul class="am-pagination">
  			<#if pageNum == 0>
	    		<li class="am-disabled"><a href="javascript:;">«</a></li>
		    <#else>
				<li><a href="javascript:;" onclick="doPages(0)">«</a></li>
  			</#if>
              
			<#if pageNum == 0>
				<li class="am-active"><a href="javascript:;">1</a></li>
				<#if page.totalPages gt 1>
					<li><a href="javascript:;" onclick="doPages(1)">2</a></li>
				</#if>
				<#if page.totalPages gt 2>
					<li>...</li>
				</#if>
			<#else>
				<#if pageNum gt 1>
					<li>...</li>
				</#if>
				<li><a href="javascript:;" onclick="doPages(${pageNum - 1})">${pageNum}</a></li>
				<li class="am-active"><a href="javascript:;">${pageNum + 1}</a></li>                  	
				<#if page.totalPages gt (pageNum + 1)>
					<li><a href="javascript:;" onclick="doPages(${pageNum + 1})">${pageNum + 2}</a></li>
				</#if>
				<#if page.totalPages gt (pageNum + 2)>
					<li>...</li>
				</#if>
			</#if>
			<#if page.totalPages == (pageNum + 1)>
				<li class="am-disabled"><a href="javascript:;">»</a></li>
			<#else>
				<li><a href="javascript:;" onclick="doPages(${pageNum + 1})">»</a></li>
			</#if>
		</ul>
	</div>
</div>
<script>
function doPages(n){
	$("#pageNum").val(n);
	$("#${formId}").submit();
}
</script>
<hr />
</#macro>

<#-- 自定义的分页指令。
属性说明：
	page	page类，{"totalPages":"总页数", "totalElements":"元素总个数"}
	pageNum	当前页数，从 0 开始
	formId	表单id，用于翻页查询，再 list 页面定义
 使用方式：
    <#import "/code.component.ftl" as p>
    <@p.pageInfo page pageNum formId />
 -->
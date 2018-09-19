<@override name="body">
<h3>用户列表</h3>

<#if page.content?? >
	<#list page.content as em>
ID：${(em.id)!}<br />
名称：${(em.name)!}<br />
用户名:${(em.account)!}<br />
池ID：${(em.sysPoolId)!}<br />
池名称：${(em.sysPool.name)!}<br />
	<#else>
	<#-- 和 if page.content?? else 功能相同 -->
	没有数据1
	</#list>
<#else>
没有数据2
</#if>


<script>

</script>
</@override>
<@extends name="/code/base.ftl"></@extends>
<@override name="body">
<h3>登录</h3>
<form action="/auth/login" method="post" id="loginForm">
用户名:<input type="text" value="${(entity.account)!}" name="account" id="account" /><br />
密码:<input type="text" id="passwordText" /><br />
<input type="hidden" name="password" id="password"/>
<input type="button" value="登录" onclick="doSubmit()" />
<#if message??>
错误信息：${message}
</#if>
<br />
<a href="/forget" target="_blank">==忘记密码？==</a>
</form>



<script>
function doSubmit(){
	var acc = $("#account").val();
	if(!acc){
		console.log("请输入用户名");
		return;
	}
	var pt = $("#passwordText").val();
	if(pt){
		$.ajax({
			url:'/auth/encrypt',
			type:'GET',
			data:{account: acc, password: pt},
			success:function(res){
				console.log(res);
				if(res.code == "200"){
					var p = res.data;
					$("#password").val(p);
					$("#loginForm").submit();
				}else{
					console.log("加密失败");
				}
			}
		})
	}else{
		console.log("请输入密码");
	}
}
</script>
</@override>
<@extends name="/code/base.ftl"></@extends>
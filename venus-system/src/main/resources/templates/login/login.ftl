<@override name="body">
<h3>登录</h3>
<form action="" method="post" id="loginForm">
用户名:<input type="text" value="${(entity.account)!}" name="account" id="account" /><br />
密码:<input type="text" id="passwordText" /><br />
<input type="hidden" name="password" id="password"/>
<input type="button" value="登录" onclick="doSubmit()" />
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
			url:'/auth/encrypt/',
			type:'GET',
			data:{account: acc, password: pt},
			success:function(res){
				console.log(res);
			}
		})
	}else{
		console.log("请输入密码");
	}
	//$("#loginForm").submit();
}
</script>
</@override>
<@extends name="/code/base.ftl"></@extends>
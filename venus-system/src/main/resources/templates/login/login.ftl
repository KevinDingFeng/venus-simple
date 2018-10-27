<@override name="title">
	<title>Venus - Login Page</title>
</@override>
<@override name="body">
<div class="header">
	<div class="am-g">
		<h1>Venus Kevin - 金星凯文</h1>
		<p>manage platform - 管理平台</p>
	</div>
	<hr />
</div>
<div class="am-g">
	<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    	<h3>登录</h3>
    	<a href="/regist" class="am-btn am-btn-primary am-btn-sm am-fr" >没有账号，去注册</a>
    	<hr />
    	<br />
    	<br />
    	<form method="post" class="am-form" action="/auth/login" id="loginForm">
      		<label for="account">用户名:</label>
      		<input type="text" name="account" id="account" value="${(entity.account)!}" />
      		<br />
      		<label for="password">密码:</label>
      		<input type="password" name="" id="passwordText" value="" />
      		<input type="hidden" name="password" id="password" />
      		<br />
      		<label for="remember-me">${message!}</label>
      		<br />
      		<div class="am-cf">
        		<input type="button" onclick="encrypt()" name="" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl" />
        		<#-- <a href="/forget" target="_blank" class="am-btn am-btn-default am-btn-sm am-fr">忘记密码 ^_^?</a> -->
      		</div>
    	</form>
		<script>
		function encrypt(){
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
		<#include "/code/footer.ftl">
	</div>

</div>
</@override>
<@extends name="/code/base.ftl"></@extends>

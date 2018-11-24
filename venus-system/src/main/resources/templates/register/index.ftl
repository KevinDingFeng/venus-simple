<@override name="title">
	<title>Venus - Regist</title>
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
    	<h3>注册</h3>
    	<hr />
    	<br />
    	<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
    			<div class="am-tabs am-margin" data-am-tabs>
					<ul class="am-tabs-nav am-nav am-nav-tabs">
						<li class="am-active"><a href="#">注册信息</a></li>
					</ul>
	  				<form method="post" class="am-form" action="/register" id="registForm">
      					<div class="am-tabs-bd">
        					<div class="am-tab-panel am-fade am-in am-active" id="tab1">
        						<div class="am-g am-margin-top">
              						<div class="am-u-sm-4 am-u-md-2 am-text-right">用户名</div>
          							<div class="am-u-sm-8 am-u-md-4">
            							<input type="text" class="am-input-sm" value="${(entity.account)!}" name="account" id="account"/>
          							</div>
          							<div class="am-hide-sm-only am-u-md-6"></div>
        						</div>
        						<div class="am-g am-margin-top">
              						<div class="am-u-sm-4 am-u-md-2 am-text-right">新密码</div>
          							<div class="am-u-sm-8 am-u-md-4">
            							<input type="text" class="am-input-sm" value="" id="password"/>
            							<input type="hidden" class="am-input-sm" value="" id="passwordE" name="password"/>
          							</div>
          							<div class="am-hide-sm-only am-u-md-6"></div>
        						</div>
        						<div class="am-g am-margin-top">
              						<div class="am-u-sm-4 am-u-md-2 am-text-right">再次输入新密码</div>
          							<div class="am-u-sm-8 am-u-md-4">
            							<input type="text" class="am-input-sm" value="" id="vPassword"/>
          							</div>
          							<div class="am-hide-sm-only am-u-md-6"></div>
        						</div>
        						<div class="am-g am-margin-top">
              						<div class="am-u-sm-4 am-u-md-2 am-text-right">姓名</div>
          							<div class="am-u-sm-8 am-u-md-4">
            							<input type="text" class="am-input-sm" value="${(entity.name)!}" name="name" id="name"/>
          							</div>
          							<div class="am-hide-sm-only am-u-md-6"></div>
        						</div>
        						<div class="am-g am-margin-top">
              						<div class="am-u-sm-4 am-u-md-2 am-text-right">手机号</div>
          							<div class="am-u-sm-8 am-u-md-4">
            							<input type="text" class="am-input-sm" value="${(entity.cellphone)!}" name="cellphone" id="cellphone"/>
          							</div>
          							<div class="am-hide-sm-only am-u-md-6"></div>
        						</div>
        						<div class="am-g am-margin-top">
              						<div class="am-u-sm-4 am-u-md-2 am-text-right">邮箱</div>
          							<div class="am-u-sm-8 am-u-md-4">
            							<input type="text" class="am-input-sm" value="${(entity.email)!}" name="email" id="email"/>
          							</div>
          							<div class="am-hide-sm-only am-u-md-6"></div>
        						</div>
            					<label for="remember-me">${message!}</label>
      							<br />
      							<div class="am-margin">
      								<input type="button" class="am-btn am-btn-primary am-btn-xs" value="完成" onclick="doSubmit()" />
							    </div>
							</div>
					   	</div>
          			</form>
      			</div>
			<#include "/code/footer.ftl">
			</div>
		</div>
		<!-- content end -->
<script>
function doSubmit(){
	var acc = $.trim($("#account").val()); 
	var pwd = $.trim($("#password").val());
	var vpwd = $.trim($("#vPassword").val());
	var cel = $.trim($("#cellphone").val());
	var ema = $.trim($("#email").val());
	var nam = $.trim($("#name").val());
	if(acc && pwd && vpwd && cel && ema && nam){
		if(pwd === vpwd){
			$.ajax({
				url:'/register/encrypt',
				type:'GET',
				data:{password: pwd},
				success:function(res){
					console.log(res);
					if(res.code == "200"){
						var p = res.data;
						$("#passwordE").val(p);
						$("#registForm").submit();
					}else{
						console.log("加密失败");
					}
				}
			})
		}else{
			console.log("两次输入的值不一致");
		}
	}else{
		console.log("请输入完整的信息");
	}
}
</script>
	</div>
</div>
<script src="/assets/js/amazeui.min.js"></script>
<script src="/assets/js/app.js"></script>
</@override>
<@extends name="/code/base.ftl"></@extends>
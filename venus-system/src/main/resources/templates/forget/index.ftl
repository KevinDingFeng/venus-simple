<@override name="title">
	<title>Venus - Forget Password</title>
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
    	<h3>忘记密码</h3>
    	<hr />
    	<br />
    	<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
    			<div class="am-tabs am-margin" data-am-tabs>
					<ul class="am-tabs-nav am-nav am-nav-tabs">
						<li class="am-active"><a href="#">找回密码</a></li>
					</ul>
	  				<form method="post" class="am-form" action="/forget/check_code" id="forgatCheckForm">
      					<div class="am-tabs-bd">
        					<div class="am-tab-panel am-fade am-in am-active" id="tab1">
        					
        						<div class="am-g am-margin-top">
        							<div class="am-u-sm-4 am-u-md-2 am-text-right">找回方式</div>
        							<div class="am-u-sm-8 am-u-md-10">
        								<div class="am-btn-group" data-am-button>
											<label class="am-btn am-btn-default am-btn-xs <#if channel == "Cellphone">am-active</#if>">
												<input type="radio" name="channel" value="Cellphone" <#if channel == "Cellphone">checked="checked"</#if> id="coachCell">手机号
											</label>
											<label class="am-btn am-btn-default am-btn-xs <#if channel == "Email">am-active</#if>">
												<input type="radio" name="channel" value="Email" <#if channel == "Email">checked="checked"</#if> id="coachEmail">邮箱
											</label>
										</div> 
									</div>
								</div>	
        						<div class="am-g am-margin-top">
              						<div class="am-u-sm-4 am-u-md-2 am-text-right">手机号或邮箱</div>
          							<div class="am-u-sm-8 am-u-md-4">
            							<input type="text" class="am-input-sm" value="${keyword!}" name="keyword" id="keyword"/>
          							</div>
          							<div class="am-hide-sm-only am-u-md-6"></div>
        						</div>
            					<div class="am-g am-margin-top">
              						<div class="am-u-sm-4 am-u-md-2 am-text-right">验证码</div>
          							<div class="am-u-sm-8 am-u-md-4">
            							<input type="text" class="am-input-sm" id="code" name="code" value=""/><input type="button" value="获取验证码" onclick="getCode()" />
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
var timer;//TODO 发送信息倒计时
function getCode(){
	var t = $.trim($("input[name='channel']:checked").val());
	if(!t){
		console.log("请选择校验方式");
		return;
	}
	var k = $.trim($("#keyword").val());
	if(!k){
		console.log("请输入接收路径");
		return;
	}
	console.log(k);
	console.log(t);
	$.ajax({
		url:'/forget/send',
		type:'GET',
		data:{keyword: k, channel: t},
		success:function(res){
			console.log(res);
			if(res.code == "200"){
				console.log("发送成功");
				//启动倒计时
			}else{
				console.log("发送失败");
			}
		}
	})
}

function doSubmit(){
	if(!$("#keyword").val()){
		console.log("请输入接收路径");
		return;
	}
	if(!$("#code").val()){
		console.log("请输入验证码");
		return;
	}
	$("#forgatCheckForm").submit();
}
</script>
	</div>
</div>
<script src="/assets/js/amazeui.min.js"></script>
<script src="/assets/js/app.js"></script>
</@override>
<@extends name="/code/base.ftl"></@extends>
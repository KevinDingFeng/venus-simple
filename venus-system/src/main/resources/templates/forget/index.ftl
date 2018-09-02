<@override name="body">
<h3>忘记密码</h3>
<input type="button" value="手机号找回" onclick="checkChannel('cellphone')" />
<input type="button" value="邮箱找回" onclick="checkChannel('email')" />

<form action="/forget/check_code" method="post" id="forgatCheckForm">
<span id="channalT"><#if channel??>${channel.text}<#else>手机号</#if></span>:<input type="text" value="${keyword!}" name="keyword" id="keyword" /><br />
验证码:<input type="text" id="code" name="code" /><input type="button" value="获取验证码" onclick="getCode()" /><br />
<input type="hidden" name="channel" id="channel" value="${(channel.name())!}"/>

<input type="button" value="完成" onclick="doSubmit()" />
<#if message??>
错误信息：${message}
</#if>
<br />
</form>

<script>
var timer;//TODO 发送信息倒计时
function getCode(){
	var t = $("#channel").val();
	if(!t){
		console.log("请选择校验方式");
		return;
	}
	var k = $("#keyword").val();
	if(!t){
		console.log("请输入接收路径");
		return;
	}
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
function checkChannel(t){
	var arr = {
		cellphone: "手机号",
		email: "邮箱"
	};
	$("#channel").val(t);
	$("#channalT").text(arr[t]);
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
</@override>
<@extends name="/code/base.ftl"></@extends>
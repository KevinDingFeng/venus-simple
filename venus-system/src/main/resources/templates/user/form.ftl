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
                        <strong class="am-text-primary am-text-lg">用户编辑</strong> / <small>User Form</small>
                    </div>
                </div>
                <hr />
                <div class="am-tabs am-margin data-am-tabs" >
                    <ul class="am-tabs-nav am-nav am-nav-tabs">
                        <li class="am-active"><a href="javascript:void(0);">用户信息</a></li>
                    </ul>
                    <#if entity.id??>
                    <form method="post" class="am-form" action="/user/update" id="editUserForm">
                        <input type="hidden" name="id" value="${entity.id!}" id="id"/>
                    <#else>
                    <form method="post" class="am-form" action="/user/create" id="editUserForm">
                    </#if>
                        <div class="am-tabs-bd">
                            <div class="am-tab-panel am-fade am-in am-active">
                                <div class="am-g am-margin-top">
                                    <div class="am-u-sm-4 am-u-md-2 am-text-right">所属公司</div>
                                    <div class="am-u-sm-8 am-u-md-10">
                                        <#if entity.id??>
                                            ${entity.sysPool.name!}
                                        <#else>
                                            <select name="sysPoolId" data-am-selected="{btnSize: 'sm'}">
                                                <#list poolList as pool>
                                                    <option value="${pool.id}" <#if entity.sysPoolId?? && entity.sysPoolId == pool.id>selected="selected"</#if>>${pool.name}</option>                                                
                                                </#list>
                                            </select>
                                        </#if>
                                    </div>
                                    <div class="am-hide-sm-only am-u-md-6"></div>
                                </div>
                                <div class="am-g am-margin-top">
                                    <div class="am-u-sm-4 am-u-md-2 am-text-right">用户名</div>
                                    <div class="am-u-sm-8 am-u-md-4">
                                        <#if entity.id??>
                                            ${entity.account!}
                                        <#else>
                                            <input type="text" class="am-input-sm" name="account" value="${entity.account!}" id="account" />
                                        </#if>
                                    </div>
                                    <div class="am-hide-sm-only am-u-md-6"></div>
                                </div>
                                <div class="am-g am-margin-top">
                                    <div class="am-u-sm-4 am-u-md-2 am-text-right">姓名</div>
                                    <div class="am-u-sm-8 am-u-md-4">
                                        <input type="text" class="am-input-sm" name="name" value="${entity.name!}" id="name" />
                                    </div>
                                    <div class="am-hide-sm-only am-u-md-6"></div>
                                </div>
                                <div class="am-g am-margin-top">
                                    <div class="am-u-sm-4 am-u-md-2 am-text-right">密码</div>
                                    <div class="am-u-sm-8 am-u-md-4">
                                        <input type="hidden" name="password" id="password" />
                                        <input type="text" class="am-input-sm" value="" id="passwordOriginal" />
                                    </div>
                                    <div class="am-hide-sm-only am-u-md-6"></div>
                                </div>
                                <div class="am-g am-margin-top">
                                    <div class="am-u-sm-4 am-u-md-2 am-text-right">确认密码</div>
                                    <div class="am-u-sm-8 am-u-md-4">
                                        <input type="text" class="am-input-sm" value="" id="passwordConfirm" />
                                    </div>
                                    <div class="am-hide-sm-only am-u-md-6"></div>
                                </div>
                                <div class="am-g am-margin-top">
                                    <div class="am-u-sm-4 am-u-md-2 am-text-right">邮箱</div>
                                    <div class="am-u-sm-8 am-u-md-4">
                                        <input type="text" class="am-input-sm" name="email" value="${entity.email!}" id="email" />
                                    </div>
                                    <div class="am-hide-sm-only am-u-md-6"></div>
                                </div>
                                <div class="am-g am-margin-top">
                                    <div class="am-u-sm-4 am-u-md-2 am-text-right">手机号</div>
                                    <div class="am-u-sm-8 am-u-md-4">
                                        <input type="text" class="am-input-sm" name="cellphone" value="${entity.cellphone!}" id="cellphone" />
                                    </div>
                                    <div class="am-hide-sm-only am-u-md-6"></div>
                                </div>
                                <div class="am-g am-margin-top">
                                    <div class="am-u-sm-4 am-u-md-2 am-text-right">角色</div>
                                    <div class="am-u-sm-8 am-u-md-10">
                                        <#if roleList??>
                                        <#list roleList as role>
                                            <#assign roleCheck = false>
                                            <#if entity.roles??>
                                            <#list entity.roles as rl>
                                                <#if rl.id == role.id>
                                                <#assign roleCheck = true>    
                                                </#if>
                                            </#list>
                                            </#if>
                                            <label class="am-btn am-btn-default am-btn-xs">
                                              <input type="checkbox" value="${role.id}" name="roles" <#if roleCheck>checked="checked"</#if> /> ${role.name}
                                            </label>
                                        </#list>
                                      </#if>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </form>
                </div>
                <label for="remember-me">${message!}</label>
                <br />
                <div class="am-margin">
                    <button type="button" onclick="cancel()" class="am-btn am-btn-primary am-btn-xs">取消</button>
                    <button type="button" onclick="doSubmit()" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
                </div>
            </div>
            <#include "/code/footer.ftl">
        </div>
        <!-- content end -->
    </div>
    <script>
    function cancel(){
        window.history.go(-1);
    }
    function doSubmit(){
        //如果是新增，密码必填
        //如果是修改，密码选填，但是一旦填了，就必须通过校验
        var id = $("#id").val();
        var passwordOriginal = $("#passwordOriginal").val();
        var passwordConfirm = $("#passwordConfirm").val();
        if(passwordOriginal || passwordConfirm){
            if(passwordOriginal === passwordConfirm){
                $.ajax({
                    url:'/register/encrypt',
                    type:'GET',
                    data:{password: passwordOriginal},
                    success:function(res){
                        console.log(res);
                        if(res.code == "200"){
                            var p = res.data;
                            $("#password").val(p);
                            $("#editUserForm").submit();
                        }else{
                            alert("加密失败");
                            return;
                        }
                    }
                })
                return;
            }else{
                alert("密码两次输入不一致");
                return;
            }
        }else{
            if(!id){//新增
                alert("密码必填");
                return;                    
            }
        }
        $("#editUserForm").submit();
        
    }
    </script>
    <a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>
    <script src="/assets/js/amazeui.min.js"></script>
    <script src="/assets/js/app.js"></script>
</@override>
<@extends name="/code/base.ftl"></@extends>
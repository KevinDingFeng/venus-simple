<@override name="title">
	<title>Venus - User Center</title>
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
						<strong class="am-text-primary am-text-lg">用户中心</strong> / <small>User Center</small>
					</div>
				</div>
    			<hr />
    			<div class="am-g">
                    <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
                          <div class="am-panel am-panel-default">
                                <div class="am-panel-bd">
                                      <div class="am-g">
                                            <div class="am-u-md-4">
                                                <#if entity.headImg??>
                                                <img id="headImgImg" class="am-img-circle am-img-thumbnail" src="/f/${entity.headImg!}" alt=""/>
                                                <#else>
                                                <img id="headImgImg" class="am-img-circle am-img-thumbnail" src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/200/h/200/q/80" alt=""/>
                                                </#if>
                                            </div>
                                            <div class="am-u-md-8">
                                                  <form id="headImgForm" class="am-form" method="post" action="/user/head_img" enctype="multipart/form-data">
                                                        <div class="am-form-group">
                                                              <input type="file" id="headImgFileInput" name="headImg" multiple accept="image/png, image/jpeg, image/jpg, .pdf" />
                                                              <p class="am-form-help">请选择要上传的文件...</p>
                                                              <button onclick="uploadHeadImg()" type="button" class="am-btn am-btn-primary am-btn-xs">保存</button>
                                                        </div>
                                                  </form>
                                                  <script>
function uploadHeadImg(){
    var formData = new FormData($("#headImgForm")[0]);
        $.ajax({
            type : "post",
            data : formData,
            url : '/user/head_img',
            dataType : 'json',
            contentType: false, //必须
            processData: false, //必须
            success : function(data) {
                console.log(data);
                if(data.code == 200){
                    $("#headImgImg").attr("src", data.data);
                }else{
                    console.log("更新失败");
                }
            }
        });

}

                                                  </script>
                                            </div>
                                      </div>
                                </div>
                          </div>
                    </div>

                    <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
                        <form class="am-form am-form-horizontal">
                            <div class="am-form-group">
                                <label for="user-name" class="am-u-sm-3 am-form-label">用户名 / Account</label>
                                <div class="am-u-sm-9">
                                    ${(entity.account)!}
                                    <small></small>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-name" class="am-u-sm-3 am-form-label">姓名 / Name</label>
                                <div class="am-u-sm-9">
                                    ${(entity.name)!}
                                    <small></small>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-name" class="am-u-sm-3 am-form-label">公司名称 / PoolName</label>
                                <div class="am-u-sm-9">
                                    ${(entity.sysPool.name)!}
                                    <small></small>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-name" class="am-u-sm-3 am-form-label">手机号 / Cellphone</label>
                                <div class="am-u-sm-9">
                                    ${(entity.cellphone)!}
                                    <small></small>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-name" class="am-u-sm-3 am-form-label">邮箱 / Email</label>
                                <div class="am-u-sm-9">
                                    ${(entity.email)!}
                                    <small></small>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
			</div>
			<#include "/code/footer.ftl">
		</div>
		<!-- content end -->
	</div>
	<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>
	<script src="/assets/js/amazeui.min.js"></script>
	<script src="/assets/js/app.js"></script>
</@override>
<@extends name="/code/base.ftl"></@extends>
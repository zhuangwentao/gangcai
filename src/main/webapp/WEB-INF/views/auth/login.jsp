<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>买卖钢材网maisteel.com-登录</title>
<meta name="description" content="">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/ico.png"/>
<link rel="bookmark" href="${pageContext.request.contextPath}/resource/images/ico.png"/>
</head>

<!--/head-->
<body>
<jsp:include page="../common/head.jsp"></jsp:include>
<!--/header-->
  <div class="container" style="width:400px" >
    <!--登录界面开始-->
	<div id="login_form">
		<form action="javascript:;" class="login-form" method="post">
			<div class="col-md-12" style="text-align:center;">
				<h1 style="font-color:blue">登录</h1>
<!-- 				<p class="label label-info"> 登录系统可以管理您自己的资源单. </p> -->
			</div>
			<div class="col-md-12" style="text-align:center;padding-top:15px ">
				<input class="form-control  input-sm form-group" type="text" autocomplete="off" placeholder="用户名" name="username" id="username" data-toggle="popover" data-placement="left" data-content=""/> 
			</div>
			<div class="col-md-12" style="text-align:center;">
				<input class="form-control  input-sm form-group" type="password" autocomplete="off" placeholder="密码" name="password" id="password" data-toggle="popover" data-placement="left" data-content="" />
			</div>
			<div class="col-md-12" style="text-align:center;">
				<div class="col-sm-4">
					<div class="rem-password">
						<p><input type="checkbox" class="rem-checkbox" id="isRememberMe" name="isRememberMe" value="Y" /><span>记住密码</span></p>
					</div>
				</div>
				<div class="col-sm-8 text-right" >
					<div class="forgot-password">
						<a href="javascript:void(0);" onclick="loginPage.fgtPwd();" id="forget-password-href" class="forget-password">忘记密码？</a>
					</div>
				</div>
			</div>
			<div class="col-md-12" style="text-align:center;">
				<button class="btn-search search" style="width:100%;height:35px;" type="button" id="login_btn">登录</button>
			</div>
			<div class="col-md-12" style="text-align:center;padding-top:15px;padding-bottom:15px  ">
				<button class="btn-search search" style="width:100%;height:35px;"  type="button" id="create_account_ref">注册</button>
			</div>
		</form>
	</div>
	<!--登录界面结束-->

 <!--创建界面开始-->
	<div id="create_form" style="display: none;">
		<form action="javascript:;"  method="post">
			<div class="col-md-12" style="text-align:center;">
				<h1 style="font-color:blue">注册</h1>
<!-- 				<p class="label label-info"> 加入本站，发布您的资源单. </p> -->
			</div>
			<div class="col-md-12" style="text-align:center;padding-top:15px ">
				<input class="form-control  input-sm form-group" type="text" autocomplete="off" placeholder="用户名" name="username" id="create_username"  data-toggle="popover" data-placement="left" data-content=""/> 
			</div>
			<div class="col-md-12" style="text-align:center;">
				<input class="form-control  input-sm form-group" type="password" autocomplete="off" placeholder="密码" name="password" id="create_password"  data-toggle="popover" data-placement="left" data-content=""/>
			</div>
			<div class="col-md-12" style="text-align:center;">
				<input class="form-control  input-sm  form-group" type="password" autocomplete="off" placeholder="确认密码" name="password2" id="create_password2"  data-toggle="popover" data-placement="left" data-content="" />
			</div>
			<div class="col-md-12" style="text-align:center;">
				<input class="form-control  input-sm  form-group" type="text" autocomplete="off" placeholder="手机号" name="phone" id="create_phone"  data-toggle="popover" data-placement="left" data-content="" maxlength="11"/> 
			</div>
			<div class="col-md-12" style="text-align:center;">
				<input class="form-control   input-sm form-group" type="text" autocomplete="off" placeholder="公司名称" name="companyName" id="create_companyName"  data-toggle="popover" data-placement="left" data-content="" /> 
			</div>
			<div class="col-md-12" style="text-align:center;">
				<input class="form-control  input-sm  form-group" type="text" autocomplete="off" placeholder="邮箱" name="email" id="create_email"  data-toggle="popover" data-placement="left" data-content="" /> 
			</div>
			<div class="col-md-12" style="text-align:center;">
				<button class="btn-search search" style="width:100%;height:35px;" type="button" id="create_account_btn">注册</button>
			</div>
			<div class="col-md-12" style="text-align:center;padding-top:15px;padding-bottom:15px">
				<button class="btn-search search" style="width:100%;height:35px;" type="button" id="create_return_login_btn">返回登录</button>
			</div>
		</form>
	</div>
	<!--创建界面结束-->


	<!-- 忘记密码界面开始 -->
<a data-target="#post_email" data-toggle="modal" style="display: none;" id="post_email_pwd"></a>
<div class="modal fade" id="post_email" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
	      <div class="modal-header">
	      	<p class="wel_title">重置密码</p>
	      </div>
         <div class="modal-body">
           		<div class="col-md-6 col-sm-6">
             		<input class="form-control  input-sm  form-group" type="text"  placeholder="输入您的登录名"  id="pwd-fgt-loginName"  data-toggle="popover" data-placement="bottom" data-content="" />
          		</div>
           		<div class="col-md-6 col-sm-6">
             		<input class="form-control  input-sm  form-group" type="text"  placeholder="输入您的邮箱"  id="pwd-fgt-email"  data-toggle="popover" data-placement="bottom" data-content="" />
          		</div>
          		<br>
         </div>
         <div class="modal-footer">
	         <button type="button" data-dismiss="modal" class="btn btn-outline dark no">取消</button>
	         <button type="button" class="btn green yes" id="sendMail" >发送密码到邮箱</button>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

  </div>

<script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/jquery.prettyPhoto.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/jquery.isotope.min.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/main.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/utils/util.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/utils/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/auth/login.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resource/plugin/jquery.blockui.min.js" type="text/javascript"></script> --%>

</body>

</html>

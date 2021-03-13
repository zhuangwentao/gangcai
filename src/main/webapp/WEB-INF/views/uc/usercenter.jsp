<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>买卖钢材网maisteel.com-个人中心</title>
<meta name="description" content="">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/ico.png"/>
<link rel="bookmark" href="${pageContext.request.contextPath}/resource/images/ico.png"/>

	
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<!--/head-->
<body>
<jsp:include page="../common/head.jsp"></jsp:include>
<!--/header-->

<div class="container">

	<div style="float:left;">
		<jsp:include page="../common/uc_nav.jsp"></jsp:include>
	</div>	
	<div class="uc_container">
		<div class="uc_container2">
		   <p class="wel_title">欢迎您： <shiro:principal property="loginName"/></p>
		   <shiro:hasPermission name="userPC">
		   <p style="display: inline-block;">您的身份是<p class="lightwarning">一般注册用户</p>，有以下操作权限：</p>
		               <div ><a href="${pageContext.request.contextPath}/system/uploadResourceList" class="btn_ucinfo_title"><i class="glyphicon glyphicon-cloud-upload"></i>&nbsp;上传资源单</a></div>
		               <div ><a href="${pageContext.request.contextPath}/system/rlManager" class="btn_ucinfo_title"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;我的资源单</a></div>
		               <div ><a href="${pageContext.request.contextPath}/system/persionInfo" class="btn_ucinfo_title"><i class="glyphicon glyphicon-info-sign"></i>&nbsp;个人信息</a></div>
		               <div ><a href="${pageContext.request.contextPath}/system/changePwd" class="btn_ucinfo_title"><i class="glyphicon glyphicon-cog"></i>&nbsp;修改密码</a></div>
         	</shiro:hasPermission>
         	<shiro:hasPermission name="adminPC">
         	<p style="display: inline-block;">您的身份是<p class="lightwarning">系统管理员</p>，有以下操作权限：</p>
         				<div ><a href="${pageContext.request.contextPath}/system/uploadResourceList" class="btn_ucinfo_title"><i class="glyphicon glyphicon-cloud-upload"></i>&nbsp;上传资源单</a></div>
		               <div ><a href="${pageContext.request.contextPath}/system/resourcelist" class="btn_ucinfo_title"><i class="glyphicon glyphicon-align-justify"></i>&nbsp;资源单管理</a></div>
		               <div ><a href="${pageContext.request.contextPath}/system/hsManager" class="btn_ucinfo_title"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;特卖管理</a></div>
		               <div ><a href="${pageContext.request.contextPath}/system/userManager" class="btn_ucinfo_title"><i class="glyphicon glyphicon-tower"></i>&nbsp;用户管理</a></div>
		               <div ><a href="${pageContext.request.contextPath}/system/persionInfo" class="btn_ucinfo_title"><i class="glyphicon glyphicon-info-sign"></i>&nbsp;个人信息</a></div>
		               <div ><a href="${pageContext.request.contextPath}/system/changePwd" class="btn_ucinfo_title"><i class="glyphicon glyphicon-cog"></i>&nbsp;修改密码</a></div>
	         </shiro:hasPermission>
	   </div>
	</div>
</div>

<!--/header-->

<script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/jquery.prettyPhoto.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/jquery.isotope.min.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/main.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/utils/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/utils/util.js"></script>
<!-- <script src="${pageContext.request.contextPath}/resource/js/logic/goods.js"></script> -->

<!---->
<script type="text/javascript">
	$(function() {
	  $("#usercenter").addClass('active_leftNav');
	});
</script>
	
</body>
</html>

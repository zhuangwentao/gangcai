<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- core CSS -->
<link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resource/css/font-awesome.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resource/css/prettyPhoto.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resource/css/main.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resource/css/responsive.css" rel="stylesheet">
<!--[if lt IE 9]>-->
<script src="${pageContext.request.contextPath}/resource/js/html5shiv.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/respond.min.js"></script>
    <!--<![endif]-->


<ul class="nav nav-list bs-docs-sidenav affix-top nav_uc">
<shiro:user>
	<li id="usercenter" >
		<a href="${pageContext.request.contextPath}/system/usercenter"><i class="icon-chevron-right"></i>个人中心首页</a>
	</li>
	<shiro:hasPermission name="userPC">
	<li id="rlManager" >
		<a href="${pageContext.request.contextPath}/system/rlManager"><i class="icon-chevron-right"></i> 资源单管理</a>
	</li>
	</shiro:hasPermission>
	<shiro:hasPermission name="adminPC">
		<li id="hsManager"  >
			<a href="${pageContext.request.contextPath}/system/hsManager"><i class="icon-chevron-right"></i> 特卖管理</a>
		</li>
		<li  id="userManager" >
			<a href="${pageContext.request.contextPath}/system/userManager"><i class="icon-chevron-right"></i> 用户管理</a>
		</li>
	</shiro:hasPermission>
	<li id="persionInfo" >
		<a href="${pageContext.request.contextPath}/system/persionInfo" ><i class="icon-chevron-right"></i> 个人信息</a>
	</li >
	<li id="changePwd" >
		<a href="${pageContext.request.contextPath}/system/changePwd" ><i class="icon-chevron-right"></i> 修改密码</a>
	</li>
	<li id="logout" >
		<a href="${pageContext.request.contextPath}/auth/logout" ><i class="icon-chevron-right"></i> 退出</a>
	</li>
</shiro:user>
</ul>
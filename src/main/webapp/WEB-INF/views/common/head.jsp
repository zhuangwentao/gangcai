<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resource/css/font-awesome.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resource/css/prettyPhoto.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resource/css/main.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resource/css/responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resource/js/html5shiv.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/respond.min.js"></script>

<link href="${pageContext.request.contextPath}/resource/plugin/SweetAlert-js/css/sweet-alert.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resource/plugin/SweetAlert-js/js/sweet-alert.min.js"></script>



<header id="header">
  <nav class="navbar navbar-inverse" role="banner">
    <div class="container">
      <div class="navbar-header">
        <img src="${pageContext.request.contextPath}/resource/images/logogc.png" style="height: 50px;width: 260px;" alt="logo" />
      </div>
      <div class="collapse navbar-collapse navbar-right">
        <ul class="nav navbar-nav">
          <li id="head_home" ><a href="${pageContext.request.contextPath}/">首&nbsp;页</a></li>
		  <li id="head_hotsale" ><a href="${pageContext.request.contextPath}/system/hotsale">每日特价</a></li>
          <li id="head_rl" ><a href="${pageContext.request.contextPath}/system/resourcelist">资源单</a></li>
          <li id="head_goods" ><a href="${pageContext.request.contextPath}/system/goods">现货搜索</a></li>
        <shiro:user> 
           <shiro:hasPermission name="userPC">
	        	<li class="dropdown">
	               	<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i><shiro:principal property="loginName"/><b class="caret"></b></a>
		            <ul class="dropdown-menu">
		               <li><a href="${pageContext.request.contextPath}/system/usercenter"><i class="glyphicon glyphicon-user"></i>&nbsp;个人中心</a></li>
		               <li class="divider"></li>
		               <li><a href="${pageContext.request.contextPath}/system/rlManager"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;我的资源单</a></li>
		               <li><a href="${pageContext.request.contextPath}/system/persionInfo"><i class="glyphicon glyphicon-info-sign"></i>&nbsp;个人信息</a></li>
		               <li><a href="${pageContext.request.contextPath}/system/changePwd"><i class="glyphicon glyphicon-cog"></i>&nbsp;修改密码</a></li>
		               <li><a href="${pageContext.request.contextPath}/auth/logout"><i class="glyphicon glyphicon-log-out"></i>&nbsp;退出</a></li>
		            </ul>
	         	</li>
         	</shiro:hasPermission>
         	<shiro:hasPermission name="adminPC">
	         	<li class="dropdown">
		            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i><shiro:principal property="loginName"/><b class="caret"></b></a>
		            <ul class="dropdown-menu">
		               <li><a href="${pageContext.request.contextPath}/system/usercenter"><i class="glyphicon glyphicon-user"></i>&nbsp;个人中心</a></li>
		               <li class="divider"></li>
		               <li><a href="${pageContext.request.contextPath}/system/hsManager"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;特卖管理</a></li>
		               <li><a href="${pageContext.request.contextPath}/system/userManager"><i class="glyphicon glyphicon-tower"></i>&nbsp;用户管理</a></li>
		               <li><a href="${pageContext.request.contextPath}/system/persionInfo"><i class="glyphicon glyphicon-info-sign"></i>&nbsp;个人信息</a></li>
		               <li><a href="${pageContext.request.contextPath}/system/changePwd"><i class="glyphicon glyphicon-cog"></i>&nbsp;修改密码</a></li>
		               <li><a href="${pageContext.request.contextPath}/auth/logout"><i class="glyphicon glyphicon-log-out"></i>&nbsp;退出</a></li>
		            </ul>
	         	</li>
	         </shiro:hasPermission>
        </shiro:user>
          <li>
             <shiro:guest>
                <a href="${pageContext.request.contextPath}/system/login">登录|注册</a>
             </shiro:guest>
           </li>
        </ul>
      </div>
    </div>
  </nav>
</header>


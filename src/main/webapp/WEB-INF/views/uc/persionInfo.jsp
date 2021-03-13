<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>买卖钢材网maisteel.com-个人中心-个人信息</title>
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
		  <div id="for1" style="width: 300px;">
			<div class="col-md-12" style="text-align:center;">
				<p class="uc_title" >个人信息</p><input  type="text" id="id"  style="display: none;" />
			</div>
			<div class="col-md-12" style="text-align:center;">
				<div class="col-md-4 gc-info-label" >
					<p>登录名:</p>
				</div>
				<div class="col-md-8 " >
					<input class="form-control input-sm form-group" type="text" id="loginName"  readonly="readonly"/>
				</div>
			</div>
			<div class="col-md-12" style="text-align:center;">
				<div class="col-md-4 gc-info-label" >
					<p>身份:</p>
				</div>
				<div class="col-md-8" >
					<input class="form-control input-sm form-group" type="text" id="role"  readonly="readonly"/>
				</div>
			</div>
			<div class="col-md-12" style="text-align:center;">
				<div class="col-md-4 gc-info-label" >
					<p>邮箱:</p>
				</div>
				<div class="col-md-8 " >
					<input class="form-control input-sm form-group" type="text" id="email" readonly="readonly" placeholder="邮箱" data-toggle="popover" data-placement="right" data-content="" />
				</div>
			</div>
			<div class="col-md-12" style="text-align:center;">
				<div class="col-md-4 gc-info-label" >
					<p>手机:</p>
				</div>
				<div class="col-md-8 " >
					<input class="form-control input-sm form-group" type="text" id="phone"  readonly="readonly" placeholder="手机" data-toggle="popover" data-placement="right" data-content="" maxlength="11" />
				</div>
			</div>
			<div class="col-md-12" style="text-align:center;">
				<div class="col-md-4 gc-info-label" >
					<p>公司:</p>
				</div>
				<div class="col-md-8" >
					<input class="form-control input-sm form-group" type="text" id="company"   readonly="readonly" placeholder="公司" data-toggle="popover" data-placement="right" data-content="" />
				</div>
			</div>
			<div class="col-md-12" style="text-align:center;">
			   	<input type="button" value="修改" class="btn-search search" id="changeBtn" onclick="persionInfoMenu.changeBtn();" />
			   	<input type="button" value="保存" class="btn-search search" id="saveBtn" onclick="persionInfoMenu.saveBtn(); "  data-toggle="popover" data-placement="bottom" data-content="" />
			</div>
		</div>
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
<script src="${pageContext.request.contextPath}/resource/js/uc/persionInfo.js"></script>

<!-- <script src="${pageContext.request.contextPath}/resource/js/logic/goods.js"></script> -->

<!--
<script type="text/javascript">
	$(function() {
		$('.nav li').click(function(e) {
			$('.nav li').removeClass('active');
			//$(e.target).addClass('active');
			$(this).addClass('active');
		});
	});

	</script>
	-->
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>买卖钢材网maisteel.com-个人中心-修改密码</title>
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
		<!-- 修改密码界面开始 -->
			<div id="for" style="width: 300px;text-align: center;">
					<div class="col-md-12" style="text-align:center;">
						<p class="uc_title">修改密码</p>
					</div>
					<div class="col-md-12" style="text-align:center;padding-top:15px;">
						<input class="form-control  input-sm form-group" type="password" autocomplete="off" placeholder="当前密码" name="password" id="currpwd" data-toggle="popover" data-placement="left" data-content="" />
					</div>
					<div class="col-md-12" style="text-align:center;">
						<input class="form-control  input-sm form-group" type="password" autocomplete="off" placeholder="新密码" name="password" id="pwd" data-toggle="popover" data-placement="left" data-content="" />
					</div>
					<div class="col-md-12" style="text-align:center;">
						<input class="form-control  input-sm form-group" type="password" autocomplete="off" placeholder="确认新密码" name="password" id="newpwd"  data-toggle="popover" data-placement="left" data-content="" />
					</div>
					<div class="col-md-12" style="text-align:center;">
						<button class="btn-search search"  type="button"  id="changePwdBtn">确定</button>
					</div>
			</div>
	<!-- 修改密码界面结束 -->
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
<script src="${pageContext.request.contextPath}/resource/js/uc/changePwd.js"></script>

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

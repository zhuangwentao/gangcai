<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>买卖钢材网maisteel.com-个人中心-用户管理</title>
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
		   <!--检索栏开始-->
				 <div class="search">
			    	<div class="col-md-12 col-sm-12">
				    	<div class="col-md-2 col-sm-2"> 
				    		<label>
								<select  id="userManagerPage-sel-pagesize" class="form-control input-sm input-xsmall input-inline">
								    <option value="10">10条/页</option>
								    <option value="15" selected>15条/页</option>
								    <option value="20">20条/页</option>
								</select>
							</label>
					    </div>

					    <div class="col-md-1 col-sm-1"> 
								 <input type="text" class="form-control input-sm input-small  input-inline" style="display: inline;" placeholder="用户名" id="loginName-serch-key" onkeyup="userManagerPage.keyEnter(event)">
					    </div>
					    <div class="col-md-1 col-sm-1"> 
<!-- 								 <input type="text" class="form-control input-sm input-small  input-inline" style="display: inline;" placeholder="角色" id="role-serch-key" onkeyup="userManagerPage.keyEnter(event)"> -->
					    
								<select  id="role-serch-key" class="form-control input-sm input-xsmall input-inline">
								    <option value="">所有</option>
								    <option value="user">用户</option>
								    <option value="admin">管理员</option>
								</select>
					    
					    </div>
					    <div class="col-md-2 col-sm-2"> 
								 <input type="text" class="form-control input-sm input-small  input-inline" style="display: inline;" placeholder="手机" id="phone-serch-key" onkeyup="userManagerPage.keyEnter(event)">
					    </div>
					    <div class="col-md-2 col-sm-2"> 
								 <input type="text" class="form-control input-sm input-small  input-inline" style="display: inline;" placeholder="邮箱" id="email-serch-key" onkeyup="userManagerPage.keyEnter(event)">
					    </div>
					    <div class="col-md-1 col-sm-1"> 
								 <input type="text" class="form-control input-sm input-small  input-inline" style="display: inline;" placeholder="公司" id="company-serch-key" onkeyup="userManagerPage.keyEnter(event)">
					    </div>
					    <div class="col-md-2 col-sm-2"> 
								 <input type="text" class="form-control input-sm input-small  input-inline" style="display: inline;" placeholder="创建时间" id="createDate-serch-key" maxlength="8" onFocus="WdatePicker({dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear: false,width: 240})" onkeyup="userManagerPage.keyEnter(event)">
					    </div>
					    <div class="col-md-1 col-sm-1" style="text-align: center;"> 
							  <a onclick="userManagerPage.queryByEnter()" class="btn-search search" type="button">搜索</a>
					    </div>
				     </div>
			     </div>
				 <!--检索栏结束-->
				 <!--table表格开始-->
				 <div class="col-md-12 col-sm-12"  style="padding-top:10px">
				      <table class="table table-striped table-bordered table-hover  dt-responsive" width="100%" id="goods-list-table">
			              <thead>
			                  <tr class="tableHeader">
			                 	<th>用户名</th>
			                    <th>角色 </th>
			                    <th>手机</th>
								<th>邮箱</th>
								<th>公司</th>
								<th>创建时间</th>
								<th>操作</th>
			                  </tr>
			              </thead>
			              <tbody>
			                </tbody>
			            </table>
				 </div>
				 <!--table表格结束-->
				 <!--表格底部开始-->
				<div class="row">
				   <div class="col-md-5 col-sm-12">
					 <div class="dataTables_info" id="table-foot-num" role="status" aria-live="polite">显示： <span class="from">0</span> 到 <span class="to">0</span>  总数： <span class="total">0</span> </div>
				   </div>
				   <div  class="col-md-7 col-sm-12" style="text-align:right">
					 <div class="dataTables_paginate paging_bootstrap_number" >
					   <ul class="pagination" style="visibility: visible;" id="goods-list-table-footer">
			<!-- 			 <li class="prev disabled"><a href="#" title="Prev"><i class="fa fa-angle-left"></i></a></li> -->
			<!-- 			 <li class="active"><a href="#">1</a></li> -->
			<!-- 			 <li><a href="#">2</a></li> -->
			<!-- 			 <li><a href="#">3</a></li> -->
			<!-- 			 <li><a href="#">4</a></li> -->
			<!-- 			 <li><a href="#">5</a></li><li class="next"><a href="#" title="Next"><i class="fa fa-angle-right"></i></a></li> -->
					   </ul>
					 </div>
				   </div>
				</div>  
				 <!--表格底部结束-->
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
<script src="${pageContext.request.contextPath}/resource/js/uc/userManager.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/utils/My97DatePicker/WdatePicker.js"></script>

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

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>买卖钢材网maisteel.com-首页</title>
<meta name="description" content="">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/ico.png"/>
<link rel="bookmark" href="${pageContext.request.contextPath}/resource/images/ico.png"/>
</head>

<body class="homepage">
<jsp:include page="common/head.jsp"></jsp:include>

<section id="main-slider" class="no-margin">
  <div class="carousel slide">
    <ol class="carousel-indicators">
      <li data-target="#main-slider" data-slide-to="0" class="active"></li>
      <li data-target="#main-slider" data-slide-to="1"></li>
      <li data-target="#main-slider" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="item active" style="background-image: url(resource/images/slider/bg1.jpg)">
        <div class="container">
          <div class="row slide-margin">
            <div class="col-sm-12">
              <div class="carousel-content">
                <h1>每日特价 </h1>
                <h2>每日更新特价产品，点击进入</h2>
                <a class="btn-slide" href="${pageContext.request.contextPath}/system/hotsale">进入</a>
                </div>
            </div>
          </div>
        </div>
      </div>
      <!--/.item-->
      
      <div class="item" style="background-image: url(resource/images/slider/bg2.jpg)">
        <div class="container">
          <div class="row slide-margin">
            <div class="col-sm-12">
              <div class="carousel-content">
                <h1>资源单</h1>
                <h2>查看和发布您的资源信息，点击进入</h2>
                <a class="btn-slide" href="${pageContext.request.contextPath}/system/resourcelist">进入</a> </div>
            </div>
          </div>
        </div>
      </div>
      <!--/.item-->
      
      <div class="item" style="background-image: url(resource/images/slider/bg3.jpg)">
        <div class="container">
          <div class="row slide-margin">
            <div class="col-sm-12">
              <div class="carousel-content">
                <h1>现货搜索</h1>
                <h2>快速找到您所需要的现货，点击进入</h2>
                <a class="btn-slide" href="${pageContext.request.contextPath}/system/goods">进入</a> </div>
            </div>
          </div>
        </div>
      </div>
      <!--/.item--> 
    </div>
    <!--/.carousel-inner--> 
  </div>
  <!--/.carousel--> 
  <a class="prev hidden-xs" href="#main-slider" data-slide="prev"> <i class="fa fa-chevron-left"></i> </a> <a class="next hidden-xs" href="#main-slider" data-slide="next"> <i class="fa fa-chevron-right"></i> </a> </section>
<!--/#main-slider-->


<footer id="footer">
  <div class="container">
    <div class="row" style="text-align: center;">
      <div class="col-sm-12">
	      <div>
		       <div class="col-sm-3">
		         <p><i class="glyphicon glyphicon-comment"></i>&nbsp;QQ:xxxxx</p>
		       </div>
		       <div class="col-sm-3">
		         <p><i class="glyphicon glyphicon-phone"></i>&nbsp;电话:xxxx</p>
		       </div>
		       <div class="col-sm-3">
		         <p><i class="glyphicon glyphicon-map-marker"></i>&nbsp;地址:xxxx</p>
		       </div>
		       <div class="col-sm-3">
		         <p><i class="glyphicon glyphicon-envelope"></i>&nbsp;邮件:xxx@qq.com</p>
		       </div>
		  </div>
	      <div class="col-sm-12">
	        <p>&copy;2016 xxxxx实业有限公司 All Rights Reserved </p>
	      </div>
	  </div>
      <div class="col-sm-12"><i class="glyphicon glyphicon-warning-sign"></i>警告：本网站仅提供免费发布渠道，并不对资源发布作任何审查。使用资源单频道进行交易所存在的风险及产生的后果由您与发布者自行承担。</div>
    </div>
  </div>
</footer>
<!--/#footer--> 

<script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/jquery.prettyPhoto.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/jquery.isotope.min.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/main.js"></script>
<script type="text/javascript">
	$(function() {
		$("#head_home").addClass('active_leftNav');
	});
</script>
</body>
</html>

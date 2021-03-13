<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>买卖钢材网maisteel.com-上传资源单</title>
<meta name="description" content="">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/ico.png"/>
<link rel="bookmark" href="${pageContext.request.contextPath}/resource/images/ico.png"/>
</head>

<!--/head-->
<body>
<jsp:include page="../common/head.jsp"></jsp:include>
  <div class="container" style="width:900px" >
    <!--发布资源单界面开始-->
    <div>
      <div class="col-md-12" style="text-align:center;">
		<p class="up_title">上传资源单</p>
	  </div>
    </div>
	<div id="post_resource" style="width: 75%;float:left;">
		<form action="javascript:;" class="upload-rl-form" method="post" enctype="multipart/form-data" >
			<div class="col-md-12" style="text-align:left;padding-bottom: 3px; ">
			    <div class="leftTip label label-info ">第一步:选择要上传的资源单</div>
			</div>
			<div class="col-md-12" style="text-align:center;">
				<input class="form-control form-group" type="file" id="xlsfile" name="xlsfile" data-toggle="popover" data-placement="left" data-content=""/> 
			</div>
            <div class="col-md-12" style="text-align:left;padding-bottom: 3px; ">
				<div class="label label-info leftTip">第二步:填写资源单信息</div>
			</div>
			<div class="col-md-12" style="text-align:center;">
				<input class="form-control form-group" type="text"  placeholder="公司名称" name="company" id="company" data-toggle="popover" data-placement="left" data-content="" />
			</div>
			<div class="col-md-12" style="text-align:center;">
				<input class="form-control form-group" type="text"  placeholder="地区" name="area"  id="area"  data-toggle="popover" data-placement="left" data-content=""/>
			</div>
			<div class="col-md-12" style="text-align:center;">
				<textarea class="form-control form-group" rows="4"  placeholder="资源单描述" name="description"  id="description"  data-toggle="popover" data-placement="left" data-content=""></textarea>
			</div>
            <div class="col-md-12"  style="text-align:left;padding-bottom: 3px;">
				<div class="label label-info leftTip">第三步:选择分类</div>
			</div>
			<div class="col-md-12" style="text-align:center;">
			    <!--分类1-->
				<div class="col-md-2" style="text-align:right;">
				   冷轧<input type="checkbox" class="rem-checkbox" id="classification_0" value="0" name="classification"/>
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营品种" id="main_varieties_0" />
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营钢厂" id="main_steelfactory_0" />
				</div>
			    <!--分类2-->
				<div class="col-md-2" style="text-align:right;">
				   涂镀<input type="checkbox" class="rem-checkbox" id="classification_1" value="1" name="classification"/>
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营品种" id="main_varieties_1"  />
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营钢厂" id="main_steelfactory_1"  />
				</div>
			    <!--分类3-->
				<div class="col-md-2" style="text-align:right;">
				   热轧<input type="checkbox" class="rem-checkbox" id="classification_2" value="2" name="classification"/>
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营品种" id="main_varieties_2"  />
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营钢厂" id="main_steelfactory_2"  />
				</div>
			    <!--分类4-->
				<div class="col-md-2" style="text-align:right;">
				   酸洗<input type="checkbox" class="rem-checkbox" id="classification_3" value="3" name="classification"/>
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营品种" id="main_varieties_3"  />
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营钢厂" id="main_steelfactory_3"  />
				</div>
			    <!--分类5-->
				<div class="col-md-2" style="text-align:right;">
				   中厚板<input type="checkbox" class="rem-checkbox" id="classification_4" value="4" name="classification"/>
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营品种" id="main_varieties_4"  />
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营钢厂" id="main_steelfactory_4"  />
				</div>
			    <!--分类6-->
				<div class="col-md-2" style="text-align:right;">
				   螺线<input type="checkbox" class="rem-checkbox" id="classification_5" value="5" name="classification"/>
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营品种" id="main_varieties_5"  />
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营钢厂" id="main_steelfactory_5"  />
				</div>
			    <!--分类7-->
				<div class="col-md-2" style="text-align:right;">
				   型材<input type="checkbox" class="rem-checkbox" id="classification_6" value="6" name="classification"/>
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营品种" id="main_varieties_6"  />
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营钢厂" id="main_steelfactory_6"  />
				</div>
			    <!--分类8-->
				<div class="col-md-2" style="text-align:right;">
				   其他<input type="checkbox" class="rem-checkbox" id="classification_7" value="7" name="classification"/>
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营品种" id="main_varieties_7"  />
				</div>
				<div class="col-md-5" style="text-align:center;">
				    <input class="form-control " type="text"  placeholder="主营钢厂" id="main_steelfactory_7"  />
				</div>
			</div>
		</form>
	</div>
	<div id="comment" style="width: 25%;float:right;">
		<div class="col-md-12" style="text-align:left;padding-top:15px;padding-left: 10px;">
			<div style="padding-top: 15px;">
				<div class="comment_u">		
					<p class="comment_title">资源单说明:<p>
					1、本站仅支持以xlsx结尾的EXCEL文档（即MS Office 2007版本以后的EXCEL文档）。<br>
					如果您是以xls结尾的EXCEL文档,请选择文件->另存为*.xlsx文件。
					<br><br>2、请严格遵守本站EXCEL文档内容提取规则，否则您的文档内容将不会被提取，【现货搜索】栏目将无法看到您的文档内容。
					<br>具体模板请参照<a href="${pageContext.request.contextPath}/resource/ExcelTemplate/rl_sample.xlsx">《EXCEL文档模板》</a>
				</div>
			</div>
			<div style="padding-top: 15px;">
				<div class="comment_u">		
					<p class="comment_title">温馨提示:<p>
					1、您上传的资源单会在【资源单】栏目展示。同时网站会自动解析您上传的资源单，其他用户可以在【现货搜索】栏目直接搜索到您资源单里的内容。
					<br><br>2、请准确填写品种的分类，分类会出现在【资源单】栏目，有助于其他用户快速搜索到您的资源单。
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-12" style="text-align:center;padding-bottom:40px">
		<button class="btn-search search" style="width:100%;height:35px;"  id="uploadBtn" >发布资源单</button>
	</div>
	<!--发布资源单结束-->
  </div>
</div>

<script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/jquery.prettyPhoto.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/jquery.isotope.min.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/main.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/utils/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/utils/util.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/logic/uploadResourceList.js"></script>
<script src="${pageContext.request.contextPath}/resource/plugin/jquery.blockui.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function(){
        //设置全局ajax开始时 遮罩层开始
        $(document).ajaxStart(function () {
            $.blockUI({ message: '<h1 style="font-size: 12px!important;">页 面 加 载 中···</h1>' , css: { backgroundColor: '#cecece', color: '#3498db' ,border: 'none'}  , baseZ: 20000});
            });

           //设置全局ajax结束时 遮罩层结束
        $(document).ajaxStop(function () {
            $.unblockUI();
        });
    });
</script>
</body>
</html>

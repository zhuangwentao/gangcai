<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>买卖钢材网maisteel.com-个人中心-特卖管理</title>
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
							<select  id="hsGoodsPage-sel-pagesize" class="form-control input-sm input-xsmall input-inline">
							    <option value="10">10条/页</option>
							    <option value="15" selected>15条/页</option>
							    <option value="20">20条/页</option>
							</select>
						</label>
				    </div>
				    <div class="col-md-7 col-sm-7"> 
				    </div>
				    
				    <div class="col-md-1 col-sm-1" > 
						  <a onclick="hsGoodsPage.queryClear()" class="btn-search search" type="button">清空</a>
			 		</div>
				    <div class="col-md-1 col-sm-1" > 
						  <a onclick="hsGoodsPage.queryByEnter()" class="btn-search search" type="button">搜索</a>
				    </div>
				    <div class="col-md-1 col-sm-1" > 
						  <a onclick="hsGoodsPage.importXlsMenu()" class="btn-search search" type="button">导入</a>
				 	</div>
			 </div>
			 <div class="col-md-12 col-sm-12">
				    <div class="col-md-1 col-sm-1"> 
						<input type="text" class="form-control input-sm" placeholder="品名" id="productName-serch-key" onkeyup="hsGoodsPage.keyEnter(event)">
				    </div>
				    <div class="col-md-1 col-sm-1"> 
						<input type="text" class="form-control input-sm" placeholder="规格" id="specifications-serch-key" onkeyup="hsGoodsPage.keyEnter(event)">
				   </div>
				    <div class="col-md-1 col-sm-1"> 
						<input type="text" class="form-control input-sm" placeholder="材质" id="material-serch-key" onkeyup="hsGoodsPage.keyEnter(event)">
				    </div>
				    <div class="col-md-1 col-sm-1"> 
						<input type="text" class="form-control input-sm" placeholder="钢厂" id="origin-serch-key" onkeyup="hsGoodsPage.keyEnter(event)">
				   </div>
				    <div class="col-md-1 col-sm-1"> 
						<input type="text" class="form-control input-sm" placeholder="地区" id="area-serch-key" onkeyup="hsGoodsPage.keyEnter(event)">
				   </div>
				    <div class="col-md-1 col-sm-1"> 
						<input type="text" class="form-control input-sm" placeholder="仓库" id="wearhouse-serch-key" onkeyup="hsGoodsPage.keyEnter(event)">
				    </div>
				    <div class="col-md-1 col-sm-1"> 
						<input type="text" class="form-control input-sm" placeholder="供应商" id="company-serch-key" onkeyup="hsGoodsPage.keyEnter(event)">
				    </div>
				    <div class="col-md-1 col-sm-1"> 
						<input type="text" class="form-control input-sm" placeholder="价格下限" id="price-start-serch-key" onkeyup="hsGoodsPage.keyEnter(event)">
				    </div>
				    <div class="col-md-1 col-sm-1"> 
					    <input type="text" class="form-control input-sm" placeholder="价格上限" id="price-end-serch-key" onkeyup="hsGoodsPage.keyEnter(event)">
				    </div>
				    <div class="col-md-2 col-sm-2"> 
						<input type="text" class="form-control input-sm input-small  input-inline" style="display: inline;" placeholder="生效时间" id="effectiveDateStr-serch-key" maxlength="8" onFocus="WdatePicker({dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear: false,width: 240})" onkeyup="hsGoodsPage.keyEnter(event)">
				    </div>
				    <div class="col-md-1 col-sm-1"> 
				    </div>
			     </div>
		     </div>
			 <!--检索栏结束-->
			 <!--table表格开始-->
			 <div class="col-md-12 col-sm-12"  style="padding-top:10px">
			      <table class="table table-striped table-bordered table-hover  dt-responsive" width="100%" id="goods-list-table">
		              <thead>
		                  <tr class="tableHeader">
		                    <th>品名</th>
		                    <th>规格</th>
		                    <th>材质</th>
		                    <th>钢厂 </th>
		                    <th>地区</th>
		                    <th>数量</th>
							<th>仓库</th>
							<th>供应商</th>
							<th>价格</th>
							<th>热卖日期</th>
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

<!-- 导入modal开始 -->
<a data-target="#stack-add" data-toggle="modal" style="display: none;" id="stack-add-hs"></a>
<div class="modal fade" id="stack-add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-body">
               <form  id="excelImportForm" method="post" action="#" enctype="multipart/form-data" >
               		<div class="col-md-12 col-sm-12">
	               		<div class="col-md-6 col-sm-6">
	                       <input type="text" disabled style="display:block; width:100%;height: 30px;float: left;padding-right: 5px;" id="importFileShow" data-toggle="popover" data-placement="bottom" data-content="">
	                     </div>
	                     <div class="col-md-6 col-sm-6">
	                       <a href="javascript:hsGoodsPage.clickFileBtn();" class="btn btn-warning" style="position: relative;float: left;width: 70px;height: 30px;line-height: 16px;padding-left: 6px; ">选择文件 </a><input type="file" id="xlsfile" name="xlsfile" style="width: 140px;height: 30px; display: none;" >
	                       <a class="btn btn-success" id="menu-import-sample"  href="${pageContext.request.contextPath}/resource/ExcelTemplate/hs_sample.xlsx" style="float: left;height: 30px;margin: 0 0 0 8px; width: 65px;font-size: 12px;line-height: 16px;padding-left: 6px;" >下载模板</a>
	           			</div>
	           		</div>
	           </form>
         </div>
         <div class="modal-footer">
	         <button type="button" data-dismiss="modal" class="btn btn-outline dark no">取消</button>
	         <button type="button" class="btn green yes" >导入</button>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!--/header-->

<script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/jquery.prettyPhoto.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/jquery.isotope.min.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/main.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/utils/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/utils/util.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/utils/My97DatePicker/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/uc/hsManager.js"></script>

<script type="text/javascript">
    $(function(){
    	hsGoodsPage.queryHsGoodsListForPage(1);
        $("#hsGoodsPage-sel-pagesize").change(function(){
        	hsGoodsPage.queryHsGoodsListForPage(1);
        });
    });
</script>
</body>
</html>

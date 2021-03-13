<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>买卖钢材网maisteel.com-资源单</title>
<meta name="description" content="">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/ico.png"/>
<link rel="bookmark" href="${pageContext.request.contextPath}/resource/images/ico.png"/>
        
</head>

<!--/head-->
<body>
<jsp:include page="../common/head.jsp"></jsp:include>
<!--/header-->
<div class="container">
<!--     <div style="padding-top:5px;padding-bottom:10px; text-align:center;"> -->
<!--       <p  class="label label-warning" style="width:100%!important;">警告：本网站仅提供免费发布渠道，并不对资源发布作任何审查。使用资源单频道进行交易所存在的风险及产生的后果由您与发布者自行承担。</p> -->
<!--     </div> -->
	
	 <!--检索栏开始-->
	 <div class="search">
    	<div class="col-md-12 col-sm-12">
	    	<div class="col-md-2 col-sm-2"> 
	    		<label>
					<select  id="resourcelistPage-sel-pagesize" class="form-control input-sm input-xsmall input-inline">
					    <option value="10">10条/页</option>
					    <option value="15" selected>15条/页</option>
					    <option value="20">20条/页</option>
					</select>
				</label>
		    </div>
		    <div class="col-md-1 col-sm-1"> 
					 <input type="text" class="form-control input-sm input-small  input-inline" style="display: inline;" placeholder="大类" id="classification-serch-key" onkeyup="resourcelistPage.keyEnter(event)">
		    </div>
		    <div class="col-md-2 col-sm-2"> 
					 <input type="text" class="form-control input-sm input-small  input-inline" style="display: inline;" placeholder="钢厂" id="mainSteelfactory-serch-key" onkeyup="resourcelistPage.keyEnter(event)">
		    </div>
		    <div class="col-md-2 col-sm-2"> 
					 <input type="text" class="form-control input-sm input-small  input-inline" style="display: inline;" placeholder="主营品种" id="mainVarieties-serch-key" onkeyup="resourcelistPage.keyEnter(event)">
		    </div>
		    <div class="col-md-2 col-sm-2"> 
					 <input type="text" class="form-control input-sm input-small  input-inline" style="display: inline;" placeholder="公司名称" id="company-serch-key" onkeyup="resourcelistPage.keyEnter(event)">
		    </div>
		    <div class="col-md-1 col-sm-1" style="text-align: center;"> 
				  <a onclick="resourcelistPage.queryByEnter()" class="btn-search search" type="button">搜索</a>
		    </div>
		    <div class="col-md-2 col-sm-2" > 
				  <a href="${pageContext.request.contextPath}/system/uploadResourceList" class="btn-search search" type="button">上传资源单</a>
		    </div>
	     </div>
     </div>
	 <!--检索栏结束-->
	 <!--table表格开始-->
	 <div class="col-md-12 col-sm-12"  style="padding-top:10px">
	      <table class="table table-striped table-bordered table-hover  dt-responsive" width="100%" id="resource-list-table">
              <thead>
                  <tr class="tableHeader">
                    <th>公司</th>
                    <th>上传者</th>
                    <th>大类</th>
                    <th>主营品种 </th>
                    <th>主营钢厂</th>
<!-- 					<th>资源单说明</th> -->
					<th>上传时间</th>
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
		   <ul class="pagination" style="visibility: visible;" id="resource-list-table-footer">
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
<!--/inner-page-->

<!-- 模态框（Modal） -->
<a class="btn btn-primary btn-lg" id="description_modal_btn" style="display: none;" data-toggle="modal" data-target="#description_modal"></a>
<div class="modal fade" id="description_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">资源单说明</h4>
         </div>
         <div class="modal-body">
            <p id="description_in_modal"></p>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/jquery.prettyPhoto.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/jquery.isotope.min.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/main.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/utils/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/utils/util.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/logic/resourcelist.js"></script>

<script type="text/javascript">
    $(function(){
    	resourcelistPage.queryResourcelistPageListForPage(1);
        $("#resourcelistPage-sel-pagesize").change(function(){
        	resourcelistPage.queryResourcelistPageListForPage(1);
        });
    });
</script>
</body>
</html>
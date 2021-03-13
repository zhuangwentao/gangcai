$(function() {
	$("#hsManager").addClass('active_leftNav');
	hsGoodsPage.queryHsGoodsListForPage(1);
});

var hsGoodsPage = {

//    checkCurrentIndex :function(index){
//        $(":input[name='hsGoodsPage-index-check'][value=\""+index+"\"]").prop("checked", true);
//    },
		
    keyEnter :function(event){
        //按键处理
    	//获取用户单击键盘的“键值”
        var k = event.keyCode?event.keyCode:event.which?event.which:event.charCode;
        if(k == 13){
        	hsGoodsPage.queryHsGoodsListForPage(1);
        }
    },
    queryByEnter : function(){
        hsGoodsPage. queryHsGoodsListForPage(1);
    },

    queryHsGoodsListForPage : function(currentPage){
        var pageSize =$("#hsGoodsPage-sel-pagesize").find("option:selected").val();
//    	var pageSize =10;
    	//检索信息
        var productName = $("#productName-serch-key").val();
        var specifications  = $("#specifications-serch-key").val();
        var material = $("#material-serch-key").val();
        var origin = $("#origin-serch-key").val();
        var area = $("#area-serch-key").val();
        var wearhouse = $("#wearhouse-serch-key").val();
        var company = $("#company-serch-key").val();
        var price = $("#price-start-serch-key").val();
        var priceEnd = $("#price-end-serch-key").val();
        
        
        if(!checkDouble(price)){
        	price=null;
        	$("#price-start-serch-key").val("")
        }
        if(!checkDouble(priceEnd)){
        	priceEnd=null;
        	$("#price-end-serch-key").val("")
        }
        
        var effectiveDateStr= $("#effectiveDateStr-serch-key").val();
        //资源单页面查询资源单
        $.ajax({
            url:getRootPath()+"/logic/hsGoodsPage",
            type:"get",
            data:{"currentPage":currentPage,"pageSize":pageSize,
            	"area":area,
            	"specifications":specifications,
            	"material":material,
            	"origin":origin,
            	"productName":productName,
            	"wearhouse":wearhouse,
            	"price":price,
            	"priceEnd":priceEnd,
            	"company":company,
            	"effectiveDateStr":effectiveDateStr},
            success:function(data){
                if(data !=null && data.content != null){
                	hsGoodsPage.appendListBodyHtml(data.content);
                	hsGoodsPage.appendPageFooterHtml(data.currentPage,data.totalPage,data.totalCount,data.currentIndex,data.content.length);
                }else{
                    $("#goods-list-table tbody").html("");
                    $("#goods-list-table-footer").html("");
                }
            },
            error: function(){
            }
        });
    },
    //将用户信息添加到表格中显示
    appendListBodyHtml : function(data){
        var html = [];
        var length = data.length;
        for(var i = 0 ; i < length ; i++){
          if(i%2 ==1){
        	html.push("<tr role='row' class='odd' > ");
	        }else{
	        	html.push("<tr role='row' class='even' > ");
	        }
	      //品名
	        html.push("<td > "+data[i].productName+" </td>");
	      //规格
	        html.push("<td > "+data[i].specifications+" </td>");
	      //材质
	        html.push("<td > "+data[i].material+" </td>");
	      //钢厂
	        html.push("<td > "+data[i].origin+" </td>");
	      //地区
	        html.push("<td > "+data[i].area+" </td>");
	        //num
	        html.push("<td > "+data[i].num+" </td>");
	      //仓库
	        html.push("<td > "+data[i].wearhouse+" </td>");
	      //供应商
	        html.push("<td > "+data[i].company+" </td>");
	      //价格
	        html.push("<td > "+data[i].price+" </td>");
	      //热卖日期
	        html.push("<td > "+moment(data[i].effectiveDate).format("YYYY-MM-DD")+" </td>");
	        //操作
	        html.push("<td > " +
        			" <a  class='btn-gc cell' title='删除' onclick='hsGoodsPage.deleteHs(\""+data[i].id+"\")' ><i class='glyphicon glyphicon-trash'></i></a>" +
        			"</td>");
            html.push("</tr>");
        }
        $("#goods-list-table tbody").html(html.join(""));
    },
 // 删除
    deleteHs:function(id){
    	swal({   
    		title: "确定删除吗？",   
    		text: "",   
    		type: "warning",   
    		showCancelButton: true,   
    		confirmButtonColor: "#DD6B55",   
    		confirmButtonText: "确认删除!",   
    		closeOnConfirm: false 
    		}, function(){
    			$.ajax({
                    url:getRootPath()+"/logic/deleteHs",
                    type:"post",
                    data:{"_method":"delete","id":id},
                    success:function(data){
                        if(data == "success"){
                        	swal("删除成功!", "", "success"); 
                        	hsGoodsPage.queryHsGoodsListForPage(1);
                        }else if(data == "fail"){
                        }
                    },
                    error: function(){
                    	swal("删除失败", "", "error");
                    }
                });
    			
    	});
    	
    },
    //append page foot
    appendPageFooterHtml : function(currentPage,totalPage,totalCount,currentIndex,pageSize){
        $("#table-foot-num").show();
        if (totalCount != 0) {
            $("#table-foot-num .from").text(currentIndex + 1);
        }else{
            $("#table-foot-num .from").text(0);
        }
        $("#table-foot-num .to").text(currentIndex + pageSize);
        $("#table-foot-num .total").text(totalCount);

        var html = [];
        html.push("<li ><a title='首页' onclick='hsGoodsPage.queryHsGoodsListForPage(1)'>首页</a></li>");
        if(currentPage==1) {
            html.push("<li class='prev disabled'><a title='Prev' href='#'><i class='fa fa-angle-left'></i></a></li>");
        }else {
            html.push("<li class='prev'><a title='Prev' onclick='hsGoodsPage.queryHsGoodsListForPage("+(currentPage-1)+")'><i class='fa fa-angle-left'></i></a></li>");
        }
        if(totalPage <= 5) {
            for(var k=1;k<=totalPage;k++) {
                if(k==currentPage) {
                    html.push("<li class='active'><a onclick='hsGoodsPage.queryHsGoodsListForPage("+k+")'>"+k+"</a></li>");

                }else {
                    html.push("<li class=''><a onclick='hsGoodsPage.queryHsGoodsListForPage("+k+")'>"+k+"</a></li>");
                }
            }
        }else {
            if(currentPage <= 3) {
                for(var k=1;k<= 5;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='hsGoodsPage.queryHsGoodsListForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='hsGoodsPage.queryHsGoodsListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
                html.push("<li class=''><a>...</a></li>");
            }else if(currentPage+2<totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-2;k<=currentPage+2;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='hsGoodsPage.queryHsGoodsListForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='hsGoodsPage.queryHsGoodsListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
                html.push("<li class=''><a>...</a></li>");
            }else if(currentPage+2==totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-2;k<=totalPage;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='hsGoodsPage.queryHsGoodsListForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='hsGoodsPage.queryHsGoodsListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
            }else if(currentPage+1==totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-3;k<=totalPage;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='hsGoodsPage.queryHsGoodsListForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='hsGoodsPage.queryHsGoodsListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
            }else if(currentPage==totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-4;k<=totalPage;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='hsGoodsPage.queryHsGoodsListForPage("+k+")'>"+k+"</a></li>");
                    }else {
                        html.push("<li class=''><a onclick='hsGoodsPage.queryHsGoodsListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
            }
        }

        if(currentPage==totalPage) {
            html.push("<li class='next disabled'><a title='Next' href='#'><i class='fa fa-angle-right'></i></a></li>");
        }else {
            html.push("<li class='next'><a title='Next' onclick='hsGoodsPage.queryHsGoodsListForPage("+(currentPage+1)+")'><i class='fa fa-angle-right'></i></a></li>");
        }
        html.push("<li ><a title='尾页' onclick='hsGoodsPage.queryHsGoodsListForPage("+(totalPage == 0 ? 1 : totalPage)+")'>尾页</a></li>");

        $("#goods-list-table-footer").html(html.join(""));
        
    }, 
    queryClear:function(){
    	$(".search input").val("");
    	hsGoodsPage.queryHsGoodsListForPage(1);
    },
    
	clickFileBtn : function(){
		$("#xlsfile").click();
	},
	
	// 导入
    importXlsMenu :function(){
    	$("#stack-add-hs").trigger("click");
    	//清空页面数据
    	$("#importFileShow").val("");
    	$("#importxls-error").html("");
    	$("#importFileShow").attr("data-content","");
    	$("#importFileShow").popover('hide');
    	
        //File控件填充显示框
        $("#xlsfile").change(function(){
        	var xls = $("#xlsfile").val();
        	$("#importFileShow").val(xls);
        });
        // 确定按钮按下事件
        $("#stack-add .modal-footer .yes").unbind("click").bind("click",function(){
        	//需要检查file控件
            var xls = $("#xlsfile").val();
            if(xls==null || xls=="" || xls == $("#importFileShow").attr("placeholder")){
            	$("#importFileShow").attr("data-content","请选择");
            	$("#importFileShow").popover('show');
            	return false;
            }
            //格式检查
            if(!(xls.lastIndexOf(".xlsx")>-1)){
            	$("#importFileShow").attr("data-content","请参照模板使用xlsx格式的Excel文档");
            	$("#importFileShow").popover('show');
            	return false;
            }

            $("#stack-add .modal-footer .no").trigger("click");
            var oMyForm = new FormData();
			oMyForm.append("file", xlsfile.files[0]);
			$.ajax({
				url : getRootPath()+"/logic/uploadHs",
				data : oMyForm,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data) {
					swal("上传成功!", "", "success"); 
                    hsGoodsPage.queryHsGoodsListForPage(1);
				},
				error : function() {
				}
			});
        });
    },
};

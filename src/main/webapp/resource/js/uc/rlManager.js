$(function() {

	$("#rlManager").addClass('active_leftNav');
	rlManagerPage.queryrlManagerPageListForPage(1);
});
var rlManagerPage = {

//	    checkCurrentIndex :function(index){
//	        $(":input[name='rlManagerPage-index-check'][value=\""+index+"\"]").prop("checked", true);
//	    },
			
	    keyEnter :function(event){
	        //按键处理
	    	//获取用户单击键盘的“键值”
	        var k = event.keyCode?event.keyCode:event.which?event.which:event.charCode;
	        if(k == 13){
	        	rlManagerPage.queryrlManagerPageListForPage(1);
	        }
	    },
	    queryByEnter : function(){
	        rlManagerPage. queryrlManagerPageListForPage(1);
	    },

	    queryrlManagerPageListForPage : function(currentPage){
//	        var pageSize =$("#rlManagerPage-sel-pagesize").find("option:selected").val();
	    	var pageSize =10;
	    	//检索信息
	        var classification = $("#classification-serch-key").val();
	        var mainVarieties  = $("#mainVarieties-serch-key").val();
	        var uploderTimeStr = $("#date-serch-key").val();
	        //资源单页面查询资源单
	        $.ajax({
	            url:getRootPath()+"/uc/rlManagerPage",
	            type:"get",
	            data:{"currentPage":currentPage,"pageSize":pageSize,
	            	"classification":classification,
	            	"mainVarieties":mainVarieties,
	            	"uploderTimeStr":uploderTimeStr},
	            success:function(data){
	                if(data !=null && data.content != null){
	                	rlManagerPage.appendListBodyHtml(data.content);
	                	rlManagerPage.appendPageFooterHtml(data.currentPage,data.totalPage,data.totalCount,data.currentIndex,data.content.length);
	                }else{
	                    $("#resource-list-table tbody").html("");
	                    $("#resource-list-table-footer").html("");
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

	        	//大类
	        	html.push("<td > "+data[i].classification+" </td>");
	        	//主营品种
	        	html.push("<td > "+data[i].mainVarieties+" </td>");
	        	//主营钢厂
	        	html.push("<td > "+data[i].mainSteelfactory+" </td>");
	        	//上传时间
	        	html.push("<td > "+moment(data[i].uploderTime).format("YYYY-MM-DD HH:mm:ss")+" </td>");
	        	//操作
	        	html.push("<td > " +
	        			" <a  class='btn-gc cell' title='删除' onclick='rlManagerPage.deleteReoustceList(\""+ data[i].url +"\",\""+data[i].id+"\")' ><i class='glyphicon glyphicon-trash'></i></a>" +
	        			"</td>");
	            html.push("</tr>");
	        }
	        $("#resource-list-table tbody").html(html.join(""));
	    },
	    // 删除url对应的文件和id对应的数据和现货数据
	    deleteReoustceList:function(url,id){
	    	swal({   
	    		title: "确定删除吗？",   
	    		text: "将要删除资源单文件和导入的数据!",   
	    		type: "warning",   
	    		showCancelButton: true,   
	    		confirmButtonColor: "#DD6B55",   
	    		confirmButtonText: "确认删除!",   
	    		closeOnConfirm: false 
	    		}, function(){
	    			$.ajax({
	                    url:getRootPath()+"/logic/deleteRl",
	                    type:"post",
	                    data:{"_method":"delete","id":id,"url":url},
	                    success:function(data){
	                        if(data == "success"){
	                        	swal("删除成功!", "", "success"); 
	                        	rlManagerPage.queryrlManagerPageListForPage(1);
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
	        html.push("<li ><a title='首页' onclick='rlManagerPage.queryrlManagerPageListForPage(1)'>首页</a></li>");
	        if(currentPage==1) {
	            html.push("<li class='prev disabled'><a title='Prev' href='#'><i class='fa fa-angle-left'></i></a></li>");
	        }else {
	            html.push("<li class='prev'><a title='Prev' onclick='rlManagerPage.queryrlManagerPageListForPage("+(currentPage-1)+")'><i class='fa fa-angle-left'></i></a></li>");
	        }
	        if(totalPage <= 5) {
	            for(var k=1;k<=totalPage;k++) {
	                if(k==currentPage) {
	                    html.push("<li class='active'><a onclick='rlManagerPage.queryrlManagerPageListForPage("+k+")'>"+k+"</a></li>");

	                }else {
	                    html.push("<li class=''><a onclick='rlManagerPage.queryrlManagerPageListForPage("+k+")'>"+k+"</a></li>");
	                }
	            }
	        }else {
	            if(currentPage <= 3) {
	                for(var k=1;k<= 5;k++) {
	                    if(k==currentPage) {
	                        html.push("<li class='active'><a onclick='rlManagerPage.queryrlManagerPageListForPage("+k+")'>"+k+"</a></li>");

	                    }else {
	                        html.push("<li class=''><a onclick='rlManagerPage.queryrlManagerPageListForPage("+k+")'>"+k+"</a></li>");
	                    }
	                }
	                html.push("<li class=''><a>...</a></li>");
	            }else if(currentPage+2<totalPage) {
	                html.push("<li class=''><a>...</a></li>");
	                for(var k=currentPage-2;k<=currentPage+2;k++) {
	                    if(k==currentPage) {
	                        html.push("<li class='active'><a onclick='rlManagerPage.queryrlManagerPageListForPage("+k+")'>"+k+"</a></li>");

	                    }else {
	                        html.push("<li class=''><a onclick='rlManagerPage.queryrlManagerPageListForPage("+k+")'>"+k+"</a></li>");
	                    }
	                }
	                html.push("<li class=''><a>...</a></li>");
	            }else if(currentPage+2==totalPage) {
	                html.push("<li class=''><a>...</a></li>");
	                for(var k=currentPage-2;k<=totalPage;k++) {
	                    if(k==currentPage) {
	                        html.push("<li class='active'><a onclick='rlManagerPage.queryrlManagerPageListForPage("+k+")'>"+k+"</a></li>");

	                    }else {
	                        html.push("<li class=''><a onclick='rlManagerPage.queryrlManagerPageListForPage("+k+")'>"+k+"</a></li>");
	                    }
	                }
	            }else if(currentPage+1==totalPage) {
	                html.push("<li class=''><a>...</a></li>");
	                for(var k=currentPage-3;k<=totalPage;k++) {
	                    if(k==currentPage) {
	                        html.push("<li class='active'><a onclick='rlManagerPage.queryrlManagerPageListForPage("+k+")'>"+k+"</a></li>");

	                    }else {
	                        html.push("<li class=''><a onclick='rlManagerPage.queryrlManagerPageListForPage("+k+")'>"+k+"</a></li>");
	                    }
	                }
	            }else if(currentPage==totalPage) {
	                html.push("<li class=''><a>...</a></li>");
	                for(var k=currentPage-4;k<=totalPage;k++) {
	                    if(k==currentPage) {
	                        html.push("<li class='active'><a onclick='rlManagerPage.queryrlManagerPageListForPage("+k+")'>"+k+"</a></li>");
	                    }else {
	                        html.push("<li class=''><a onclick='rlManagerPage.queryrlManagerPageListForPage("+k+")'>"+k+"</a></li>");
	                    }
	                }
	            }
	        }

	        if(currentPage==totalPage) {
	            html.push("<li class='next disabled'><a title='Next' href='#'><i class='fa fa-angle-right'></i></a></li>");
	        }else {
	            html.push("<li class='next'><a title='Next' onclick='rlManagerPage.queryrlManagerPageListForPage("+(currentPage+1)+")'><i class='fa fa-angle-right'></i></a></li>");
	        }
	        html.push("<li ><a title='尾页' onclick='rlManagerPage.queryrlManagerPageListForPage("+(totalPage == 0 ? 1 : totalPage)+")'>尾页</a></li>");

	        $("#resource-list-table-footer").html(html.join(""));
	        
	    },
	};
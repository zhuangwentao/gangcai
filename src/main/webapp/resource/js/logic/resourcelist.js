$(function() {
	$("#head_rl").addClass('active_leftNav');
});

var resourcelistPage = {

//    checkCurrentIndex :function(index){
//        $(":input[name='resourcelistPage-index-check'][value=\""+index+"\"]").prop("checked", true);
//    },
		
    keyEnter :function(event){
        //按键处理
    	//获取用户单击键盘的“键值”
        var k = event.keyCode?event.keyCode:event.which?event.which:event.charCode;
        if(k == 13){
        	resourcelistPage.queryResourcelistPageListForPage(1);
        }
    },
    queryByEnter : function(){
        resourcelistPage. queryResourcelistPageListForPage(1);
    },

    queryResourcelistPageListForPage : function(currentPage){
        var pageSize =$("#resourcelistPage-sel-pagesize").find("option:selected").val();
//    	var pageSize =10;
    	//检索信息
        var classification = $("#classification-serch-key").val();
        var mainVarieties  = $("#mainVarieties-serch-key").val();
        var mainSteelfactory = $("#mainSteelfactory-serch-key").val();
        var company = $("#company-serch-key").val();
        //资源单页面查询资源单
        $.ajax({
            url:getRootPath()+"/logic/resourceListPage",
            type:"get",
            data:{"currentPage":currentPage,"pageSize":pageSize,"classification":classification,"mainVarieties":mainVarieties,"mainSteelfactory":mainSteelfactory,"company":company},
            success:function(data){
                if(data !=null && data.content != null){
                	resourcelistPage.appendListBodyHtml(data.content);
                	resourcelistPage.appendPageFooterHtml(data.currentPage,data.totalPage,data.totalCount,data.currentIndex,data.content.length);
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
        	html.push("<tr role='row' class='odd'> ");
	        }else{
	        	html.push("<tr role='row' class='even'> ");
	        }
        	//公司
        	html.push("<td > "+data[i].company+" </td>");
        	//上传者
        	html.push("<td > "+data[i].user.loginName+" </td>");
        	//大类
        	html.push("<td > "+data[i].classification+" </td>");
        	//主营品种
        	html.push("<td > "+data[i].mainVarieties+" </td>");
        	//主营钢厂
        	html.push("<td > "+data[i].mainSteelfactory+" </td>");
        	//资源单说明
//        	html.push("<td > "+data[i].description+" </td>");
        	//上传时间
        	html.push("<td > "+moment(data[i].uploderTime).format("YYYY-MM-DD HH:mm:ss")+" </td>");
        	//操作
        	html.push("<td > " +
        			"<a  class='btn-gc cell' title='详情 ' onclick='resourcelistPage.showDescription(\""+data[i].description+"\");' ><span><i class='glyphicon glyphicon-info-sign'></i>&nbsp;详情</span></a>"  +
        			"<a  class='btn-gc cell' href='javascript:void(0)' onclick='resourcelistPage.downloadRl(\""+data[i].url+"\")' title='下载' ><i class='glyphicon glyphicon-download-alt'></i>&nbsp;下载</a>" );
        	if(data[i].opeRole=="admin"){
        		html.push(	" <a  class='btn-gc cell' title='删除' onclick='resourcelistPage.deleteReoustceList(\""+ data[i].url +"\",\""+data[i].id+"\")' ><i class='glyphicon glyphicon-trash'></i>&nbsp;删除</a>" );
        	}
        			html.push(	"</td>");
            html.push("</tr>");
        }
        $("#resource-list-table tbody").html(html.join(""));
    },
    
    downloadRl:function(fileName){
    	if(fileName==null || fileName==""){
    		swal("不存在 或者已经被删除", "", "error");
    		return false;
    	}
    	window.location.href=getRootPath()+"/logic/downloadRl/"+fileName;
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
                        	resourcelistPage.queryResourcelistPageListForPage(1);
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
        html.push("<li ><a title='首页' onclick='resourcelistPage.queryResourcelistPageListForPage(1)'>首页</a></li>");
        if(currentPage==1) {
            html.push("<li class='prev disabled'><a title='Prev' href='#'><i class='fa fa-angle-left'></i></a></li>");
        }else {
            html.push("<li class='prev'><a title='Prev' onclick='resourcelistPage.queryResourcelistPageListForPage("+(currentPage-1)+")'><i class='fa fa-angle-left'></i></a></li>");
        }
        if(totalPage <= 5) {
            for(var k=1;k<=totalPage;k++) {
                if(k==currentPage) {
                    html.push("<li class='active'><a onclick='resourcelistPage.queryResourcelistPageListForPage("+k+")'>"+k+"</a></li>");

                }else {
                    html.push("<li class=''><a onclick='resourcelistPage.queryResourcelistPageListForPage("+k+")'>"+k+"</a></li>");
                }
            }
        }else {
            if(currentPage <= 3) {
                for(var k=1;k<= 5;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='resourcelistPage.queryResourcelistPageListForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='resourcelistPage.queryResourcelistPageListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
                html.push("<li class=''><a>...</a></li>");
            }else if(currentPage+2<totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-2;k<=currentPage+2;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='resourcelistPage.queryResourcelistPageListForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='resourcelistPage.queryResourcelistPageListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
                html.push("<li class=''><a>...</a></li>");
            }else if(currentPage+2==totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-2;k<=totalPage;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='resourcelistPage.queryResourcelistPageListForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='resourcelistPage.queryResourcelistPageListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
            }else if(currentPage+1==totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-3;k<=totalPage;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='resourcelistPage.queryResourcelistPageListForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='resourcelistPage.queryResourcelistPageListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
            }else if(currentPage==totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-4;k<=totalPage;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='resourcelistPage.queryResourcelistPageListForPage("+k+")'>"+k+"</a></li>");
                    }else {
                        html.push("<li class=''><a onclick='resourcelistPage.queryResourcelistPageListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
            }
        }

        if(currentPage==totalPage) {
            html.push("<li class='next disabled'><a title='Next' href='#'><i class='fa fa-angle-right'></i></a></li>");
        }else {
            html.push("<li class='next'><a title='Next' onclick='resourcelistPage.queryResourcelistPageListForPage("+(currentPage+1)+")'><i class='fa fa-angle-right'></i></a></li>");
        }
        html.push("<li ><a title='尾页' onclick='resourcelistPage.queryResourcelistPageListForPage("+(totalPage == 0 ? 1 : totalPage)+")'>尾页</a></li>");

        $("#resource-list-table-footer").html(html.join(""));
        
    },
    showDescription:function(description){
    	$("#description_in_modal").html(description);
    	$("#description_modal_btn").trigger("click");
    }
};

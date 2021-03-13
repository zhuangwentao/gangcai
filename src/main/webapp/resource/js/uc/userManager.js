$(function() {
	$("#userManager").addClass('active_leftNav');
	userManagerPage.queryUserMangerForPage(1);
});

var userManagerPage = {

//    checkCurrentIndex :function(index){
//        $(":input[name='userManagerPage-index-check'][value=\""+index+"\"]").prop("checked", true);
//    },
		
    keyEnter :function(event){
        //按键处理
    	//获取用户单击键盘的“键值”
        var k = event.keyCode?event.keyCode:event.which?event.which:event.charCode;
        if(k == 13){
        	userManagerPage.queryUserMangerForPage(1);
        }
    },
    queryByEnter : function(){
        userManagerPage. queryUserMangerForPage(1);
    },

    queryUserMangerForPage : function(currentPage){
        var pageSize =$("#userManagerPage-sel-pagesize").find("option:selected").val();
//    	var pageSize =10;
    	//检索信息
        var loginName = $("#loginName-serch-key").val();
        var role  = $("#role-serch-key").val();
        var phone = $("#phone-serch-key").val();
        var email = $("#email-serch-key").val();
        var company = $("#company-serch-key").val();
        var createDateStr = $("#createDate-serch-key").val();
        //资源单页面查询资源单
        $.ajax({
            url:getRootPath()+"/auth/userManagerPage",
            type:"get",
            data:{"currentPage":currentPage,"pageSize":pageSize,
            	"loginName":loginName,
            	"role":role,
            	"phone":phone,
            	"email":email,
            	"company":company,
            	"createDateStr":createDateStr},
            success:function(data){
                if(data !=null && data.content != null){
                	userManagerPage.appendListBodyHtml(data.content);
                	userManagerPage.appendPageFooterHtml(data.currentPage,data.totalPage,data.totalCount,data.currentIndex,data.content.length);
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
	        //用户名
	          html.push("<td > "+data[i].loginName+" </td>");
	        //角色
	          if(data[i].role=="user"){
	        	  html.push("<td > 一般用户 </td>");
       	   }else if(data[i].role=="admin"){
       		   html.push("<td > 管理员 </td>");
       	   }
	        //手机
	          html.push("<td > "+data[i].phone+" </td>");
	        //邮箱
	          html.push("<td > "+data[i].email+" </td>");
	        //公司
	          html.push("<td > "+data[i].company+" </td>");
	        //创建时间
	          html.push("<td > "+moment(data[i].createDate).format("YYYY-MM-DD")+" </td>");
	        //操作
	        html.push("<td > " +
        			" <a  class='btn-gc cell' onclick='userManagerPage.changeRole(\""+data[i].id+"\")' ><i class='glyphicon glyphicon-refresh'></i>&nbsp;权限变更</a>" +
        			"</td>");
            html.push("</tr>");
        }
        $("#goods-list-table tbody").html(html.join(""));
    },
    // 更改权限
    changeRole:function(id){
    	swal({   
    		title: "确定要更改权限吗？",   
    		text: "【管理员】和【一般用户】 权限切换",   
    		type: "warning",   
    		showCancelButton: true,   
    		confirmButtonColor: "#DD6B55",   
    		confirmButtonText: "确认修改!",   
    		closeOnConfirm: false 
    		}, function(){
    			$.ajax({
                    url:getRootPath()+"/auth/changeRole/"+id,
                    type:"post",
                    data:{"id":id},
                    success:function(data){
                        if(data == "success"){
                        	swal("权限变更成功!", "", "success"); 
                        	userManagerPage.queryUserMangerForPage(1);
                        }else if(data == "fail"){
                        	swal("权限变更失败", "", "error");
                        }else if(data == "self"){
                        	swal("不能更改个人或超级管理员用户", "", "error");
                        }
                    },
                    error: function(){
                    	swal("权限变更失败", "", "error");
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
        html.push("<li ><a title='首页' onclick='userManagerPage.queryUserMangerForPage(1)'>首页</a></li>");
        if(currentPage==1) {
            html.push("<li class='prev disabled'><a title='Prev' href='#'><i class='fa fa-angle-left'></i></a></li>");
        }else {
            html.push("<li class='prev'><a title='Prev' onclick='userManagerPage.queryUserMangerForPage("+(currentPage-1)+")'><i class='fa fa-angle-left'></i></a></li>");
        }
        if(totalPage <= 5) {
            for(var k=1;k<=totalPage;k++) {
                if(k==currentPage) {
                    html.push("<li class='active'><a onclick='userManagerPage.queryUserMangerForPage("+k+")'>"+k+"</a></li>");

                }else {
                    html.push("<li class=''><a onclick='userManagerPage.queryUserMangerForPage("+k+")'>"+k+"</a></li>");
                }
            }
        }else {
            if(currentPage <= 3) {
                for(var k=1;k<= 5;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='userManagerPage.queryUserMangerForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='userManagerPage.queryUserMangerForPage("+k+")'>"+k+"</a></li>");
                    }
                }
                html.push("<li class=''><a>...</a></li>");
            }else if(currentPage+2<totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-2;k<=currentPage+2;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='userManagerPage.queryUserMangerForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='userManagerPage.queryUserMangerForPage("+k+")'>"+k+"</a></li>");
                    }
                }
                html.push("<li class=''><a>...</a></li>");
            }else if(currentPage+2==totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-2;k<=totalPage;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='userManagerPage.queryUserMangerForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='userManagerPage.queryUserMangerForPage("+k+")'>"+k+"</a></li>");
                    }
                }
            }else if(currentPage+1==totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-3;k<=totalPage;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='userManagerPage.queryUserMangerForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='userManagerPage.queryUserMangerForPage("+k+")'>"+k+"</a></li>");
                    }
                }
            }else if(currentPage==totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-4;k<=totalPage;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='userManagerPage.queryUserMangerForPage("+k+")'>"+k+"</a></li>");
                    }else {
                        html.push("<li class=''><a onclick='userManagerPage.queryUserMangerForPage("+k+")'>"+k+"</a></li>");
                    }
                }
            }
        }

        if(currentPage==totalPage) {
            html.push("<li class='next disabled'><a title='Next' href='#'><i class='fa fa-angle-right'></i></a></li>");
        }else {
            html.push("<li class='next'><a title='Next' onclick='userManagerPage.queryUserMangerForPage("+(currentPage+1)+")'><i class='fa fa-angle-right'></i></a></li>");
        }
        html.push("<li ><a title='尾页' onclick='userManagerPage.queryUserMangerForPage("+(totalPage == 0 ? 1 : totalPage)+")'>尾页</a></li>");

        $("#goods-list-table-footer").html(html.join(""));
        
    }, 
    queryClear:function(){
    	$(".search input").val("");
    	userManagerPage.queryUserMangerForPage(1);
    },
};

$(function() {
	$("#changePwd").addClass('active_leftNav');
	
	//密码
    $("#currpwd").bind("blur",function(){
    	// 1 验证是否输入
        var currpwd = $("#currpwd").val();
        if(currpwd==null || currpwd=="" || currpwd == $("#currpwd").attr("placeholder")){
        	$("#currpwd").attr("data-content","请输入");
        	$("#currpwd").popover('show');
            return false;
        }
        //2 验证密码
        var params = {};
        params.password = currpwd;
   	 	$.ajax({
         url:getRootPath()+"/uc/checkPwd",
         type:"post",
         data:params,
         success:function(data){
             if(data == "success"){
             }else if(data == "fail"){
            	$("#currpwd").attr("data-content","密码输入错误");
             	$("#currpwd").popover('show');
             	return false;
             }
         },
	         error: function(){
	         }
	     });
        $("#currpwd").attr("data-content","");
        $("#currpwd").popover('hide');
    });
	
      
    //新密码验证
    $("#pwd").bind("blur",function(){
    	// 1 验证是否输入
        var pwd = $("#pwd").val();
        if(pwd==null || pwd=="" || pwd == $("#pwd").attr("placeholder")){
        	$("#pwd").attr("data-content","请输入");
        	$("#pwd").popover('show');
            return false;
        }
        $("#pwd").attr("data-content","");
        $("#pwd").popover('hide');
    });
    //密码2
    $("#newpwd").bind("blur",function(){
    	// 1 验证是否输入
        var newpwd = $("#newpwd").val();
        if(newpwd==null || newpwd=="" || newpwd == $("#newpwd").attr("placeholder")){
        	$("#newpwd").attr("data-content","请输入");
        	$("#newpwd").popover('show');
            return false;
        }
        //2 两次密码不一致检查
        var pwd = $("#pwd").val();
        if(newpwd !=pwd){
        	$("#newpwd").attr("data-content","两次输入不一致");
        	$("#newpwd").popover('show');
            return false;
        }
        $("#newpwd").attr("data-content","");
    	$("#newpwd").popover('hide');
    });
    
    
    $("#changePwdBtn").unbind("click").bind("click",function(){
		$('#currpwd').trigger("blur");
		$('#pwd').trigger("blur");
		$('#newpwd').trigger("blur");
		
    	if(($("#currpwd").attr("data-content")!=null &&$("#currpwd").attr("data-content")!="")||
        		($("#pwd").attr("data-content")!=null &&$("#pwd").attr("data-content")!="") ||
        		($("#newpwd").attr("data-content")!=null &&$("#newpwd").attr("data-content")!="")){
        		return false;
        	}
    	
    	
	    //提交到后台修改密码
	    var params = {};
	    params.password = $("#pwd").val();
		   $.ajax({
	           url:getRootPath()+"/uc/changePwd",
	           type:"post",
	           data:params,
	           success:function(data){
	               if(data !=null ){
	            	   if(data=="success"){
//	            		   alert("修改成功");
	            		   swal("修改成功!", "", "success")
	            	   }else if(data=="fail"){
//	            		   alert("修改失败");
	            		   swal("修改失败", "", "error");
	            	   }
	               }else{
	               }
	           },
	           error: function(){
	           }
	       });
    });
});

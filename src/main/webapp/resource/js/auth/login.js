$(function() {
    //设置全局ajax开始时 遮罩层开始
//    $(document).ajaxStart(function () {
//       
//    $.blockUI({ message: '<h1 style="font-size: 12px!important;">页 面 加 载 中···</h1>' , css: { backgroundColor: '#cecece', color: '#3498db' ,border: 'none'}  , baseZ: 20000});
    
//    });
//
//       //设置全局ajax结束时 遮罩层结束
//    $(document).ajaxStop(function () {
//        $.unblockUI();
//    });
	//刚进入该界面需要登录
	//清空页面数据
	$(".container input:not(:radio)").val("");
	$(".container input:not(:radio)").popover('hide');
	
	//登录页面事件结束------------start
	//用户名
    $("#username").bind("blur",function(){
    	// 1 验证是否输入
        var username = $("#username").val();
        if(username==null || username=="" || username == $("#username").attr("placeholder")){
        	$("#username").attr("data-content","请输入");
        	$("#username").popover('show');
            return false;
        }
        $("#username").attr("data-content","");
        $("#username").popover('hide');
    });
	//密码
    $("#password").bind("blur",function(){
    	// 1 验证是否输入
        var password = $("#password").val();
        if(password==null || password=="" || password == $("#password").attr("placeholder")){
        	$("#password").attr("data-content","请输入");
        	$("#password").popover('show');
            return false;
        }
        $("#password").attr("data-content","");
        $("#password").popover('hide');
    });
    //登录按钮按下
    $("#login_btn").unbind("click").bind("click",function(){
    	$('#username').trigger("blur");
    	$('#password').trigger("blur");
    	
    	if(($("#username").attr("data-content")!=null &&$("#username").attr("data-content")!="")||
    		($("#password").attr("data-content")!=null &&$("#password").attr("data-content")!="")){
    		return false;
    	}
    	// 登录验证 
    	var params = {};
        params.loginName = $("#username").val();
        params.password = $("#password").val();
        
        if($('#isRememberMe').is(':checked')) {
        	params.isRememberMe="Y";
        }else{
        	params.isRememberMe="";
        }
        
    	 $.ajax({
             url:getRootPath()+"/auth/login",
             type:"post",
             data:params,
             success:function(data){
                 if(data == "success"){
                	 //应该跳转到个人主页
                	 window.location.href = getRootPath()+"/system/usercenter";
                 }else if(data == "fail"){
                	$("#username").attr("data-content","登录失败");
                 	$("#username").popover('show');
                 }
             },
             error: function(){
             }
         });
    	
    });
    //创建账户按钮按下
    $("#create_account_ref").unbind("click").bind("click",function(){
    	$(".container input:not(:radio)").val("");
    	$(".container input:not(:radio)").popover('hide');
    	$("#login_form").hide();
    	$("#create_form").show();
    });
    //登录页面事件结束------------end
    
    //创建页面事件结束------------start
    //创建页面返回登录按钮
    $("#create_return_login_btn").unbind("click").bind("click",function(){
    	$(".container input:not(:radio)").val("");
    	$(".container input:not(:radio)").popover('hide');
    	$("#create_form").hide();
    	$("#login_form").show();
    });
    //用户名
    $("#create_username").bind("blur",function(){
    	// 1 验证是否输入
        var loginName = $("#create_username").val();
        if(loginName==null || loginName=="" || loginName == $("#create_username").attr("placeholder")){
        	$("#create_username").attr("data-content","请输入");
        	$("#create_username").popover('show');
            return false;
        }
        //2 重复校验
        // 根据loginName查询库位信息
        $.ajax({
        	url:getRootPath()+"/auth/checkLoginName/"+loginName,
            type:"get",
            success:function(data){
            	if(data=="fail"){
                	$("#create_username").attr("data-content","用户已存在");
                	$("#create_username").popover('show');
                    return false;
                }
            },
            error: function(){
            }
        });
        $("#create_username").attr("data-content","");
        $("#create_username").popover('hide');
    });
    //密码
    $("#create_password").bind("blur",function(){
    	// 1 验证是否输入
        var create_password = $("#create_password").val();
        if(create_password==null || create_password=="" || create_password == $("#create_password").attr("placeholder")){
        	$("#create_password").attr("data-content","请输入");
        	$("#create_password").popover('show');
            return false;
        }
        $("#create_password").attr("data-content","");
        $("#create_password").popover('hide');
    });
    //密码2
    $("#create_password2").bind("blur",function(){
    	// 1 验证是否输入
        var create_password2 = $("#create_password2").val();
        if(create_password2==null || create_password2=="" || create_password2 == $("#create_password2").attr("placeholder")){
        	$("#create_password2").attr("data-content","请输入");
        	$("#create_password2").popover('show');
            return false;
        }
        //2 两次密码不一致检查
        var create_password = $("#create_password").val();
        if(create_password !=create_password2){
        	$("#create_password2").attr("data-content","两次输入不一致");
        	$("#create_password2").popover('show');
            return false;
        }
        $("#create_password2").attr("data-content","");
        $("#create_password2").popover('hide');
    });
    //电话
    $("#create_phone").bind("blur",function(){
    	// 1 验证是否输入
        var create_phone = $("#create_phone").val();
        if(create_phone==null || create_phone=="" || create_phone == $("#create_phone").attr("placeholder")){
        	$("#create_phone").attr("data-content","请输入");
        	$("#create_phone").popover('show');
            return false;
        }
        //2 验证是手机号码 
        if(!mobilePhone(create_phone)){
        	$("#create_phone").attr("data-content","错误的手机号");
        	$("#create_phone").popover('show');
            return false;
        }
        $("#create_phone").attr("data-content","");
        $("#create_phone").popover('hide');
    });
    //公司名称
    $("#create_companyName").bind("blur",function(){
    	// 1 验证是否输入
        var create_companyName = $("#create_companyName").val();
        if(create_companyName==null || create_companyName=="" || create_companyName == $("#create_companyName").attr("placeholder")){
        	$("#create_companyName").attr("data-content","请输入");
        	$("#create_companyName").popover('show');
            return false;
        }
        $("#create_companyName").attr("data-content","");
        $("#create_companyName").popover('hide');
    });
    //邮箱
    $("#create_email").bind("blur",function(){
    	// 1 验证是否输入
        var create_email = $("#create_email").val();
        if(create_email==null || create_email=="" || create_email == $("#create_email").attr("placeholder")){
        	$("#create_email").attr("data-content","请输入");
        	$("#create_email").popover('show');
            return false;
        }
        if(!checkEmail(create_email)){
        	$("#create_email").attr("data-content","错误的邮箱");
        	$("#create_email").popover('show');
            return false;
        }
        $("#create_email").attr("data-content","");
        $("#create_email").popover('hide');
    });
    //创建账户按钮执行
    $("#create_account_btn").unbind("click").bind("click",function(){
    	$('#create_username').trigger("blur");
    	$('#create_password').trigger("blur");
    	$('#create_password2').trigger("blur");
    	$('#create_phone').trigger("blur");
    	$('#create_companyName').trigger("blur");
    	$('#create_email').trigger("blur");
    	
    	if(($("#create_username").attr("data-content")!=null &&$("#create_username").attr("data-content")!="")||
    		($("#create_password").attr("data-content")!=null &&$("#create_password").attr("data-content")!="")||
    		($("#create_password2").attr("data-content")!=null &&$("#create_password2").attr("data-content")!="")||
    		($("#create_phone").attr("data-content")!=null &&$("#create_phone").attr("data-content")!="")||
    		($("#create_companyName").attr("data-content")!=null &&$("#create_companyName").attr("data-content")!="")||
    		($("#create_email").attr("data-content")!=null &&$("#create_email").attr("data-content")!="")){
    		return false;
    	}
    	// 创建账户后台 
    	var params = {};
        params.loginName = $("#create_username").val();
        params.password = $("#create_password").val();
        params.phone = $("#create_phone").val();
        params.company = $("#create_companyName").val();
        params.email = $("#create_email").val();
        params.role = "user";
    	 $.ajax({
             url:getRootPath()+"/auth/add",
             type:"post",
             data:params,
             success:function(data){
                 if(data == "success"){
                	 //应该跳转到个人主页
                	 window.location.href = getRootPath()+"/system/usercenter";
                 }else if(data == "fail"){
                	 $("#create_username").attr("data-content","注册失败");
                 	$("#create_username").popover('show');
                 }
             },
             error: function(){
             }
         });
    });
    //创建页面事件结束------------start
    
    
    
    
    //找回密码开始
  //check 开始。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
	//用户名
    $("#pwd-fgt-loginName").bind("blur",function(){
    	var loginName=$("#pwd-fgt-loginName").val();
		// 1 验证是否输入
	    if(loginName==null || loginName=="" || loginName == $("#pwd-fgt-loginName").attr("placeholder")){
	    	$("#pwd-fgt-loginName").attr("data-content","请输入用户名");
	    	$("#pwd-fgt-loginName").popover('show');
	        return false;
	    }
	    $("#pwd-fgt-loginName").attr("data-content","");
	    $("#pwd-fgt-loginName").popover('hide');
    });
    //邮箱
    $("#pwd-fgt-email").bind("blur",function(){
    	var email=$("#pwd-fgt-email").val();
		// 1 验证是否输入
	    if(email==null || email=="" || email == $("#pwd-fgt-email").attr("placeholder")){
	    	$("#pwd-fgt-email").attr("data-content","请输入邮箱");
	    	$("#pwd-fgt-email").popover('show');
	        return false;
	    }
	    if(!checkEmail(email)){
	    	$("#pwd-fgt-email").attr("data-content","错误的邮箱");
	    	$("#pwd-fgt-email").popover('show');
	        return false;
	    }
	    var params={};
	    params.loginName=$("#pwd-fgt-loginName").val();
	    params.email=$("#pwd-fgt-email").val();
	    //用户名和邮箱是否匹配
	    $.ajax({
	    	url:getRootPath()+"/auth/checkLoginNameAndEmail",
	        type:"post",
	        data:params,
	        success:function(data){
	        	if(data=="success"){
	            	$("#pwd-fgt-email").attr("data-content","");
	            	$("#pwd-fgt-email").popover('hide');
	            }else{
	            	$("#pwd-fgt-email").attr("data-content","用户名和邮箱不匹配");
	            	$("#pwd-fgt-email").popover('show');
	                return false;
	            }
	        },
	        error: function(){
	        	$("#pwd-fgt-email").attr("data-content","内部错误");
	        	$("#pwd-fgt-email").popover('show');
	            return false;
	        }
	    });
	    
	    $("#pwd-fgt-email").attr("data-content","");
	    $("#pwd-fgt-email").popover('hide');
    });
	//check 结束。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
    
    
   //找回密码结束
});

var loginPage = {
		fgtPwd : function(){
	    	//清空页面数据
			$("#post_email_pwd").trigger("click");
			$("#pwd-fgt-loginName").val("");
	    	$("#pwd-fgt-email").val("");
	    	$("#pwd-fgt-loginName").attr("data-content","");
	    	$("#pwd-fgt-loginName").popover('hide');
	    	$("#pwd-fgt-email").attr("data-content","");
	    	$("#pwd-fgt-email").popover('hide');
	    	
	        // 发送邮件按钮按下事件
        	$("#sendMail").unbind("click").bind("click",function(){
		    
			    $('#pwd-fgt-loginName').trigger("blur");
	        	$('#pwd-fgt-email').trigger("blur");
	        	
	        	if(($("#pwd-fgt-loginName").attr("data-content")!=null && $("#pwd-fgt-loginName").attr("data-content")!="")||
	        		($("#pwd-fgt-email").attr("data-content")!=null && $("#pwd-fgt-email").attr("data-content")!="")
	        		){
	        		return false;
	        	}
	        	
	        	var params={};
			    params.loginName=$("#pwd-fgt-loginName").val();
			    params.email=$("#pwd-fgt-email").val();
			    $("#post_email .modal-footer .no").trigger("click");
            	swal("新密码已经发送至您的邮箱!", "", "success")
		        //进入后台发送密码
		        $.ajax({
		        	url:getRootPath()+"/auth/resetPwdAndPost",
		            type:"post",
		            data:params,
		            success:function(data){
//		            	if(data=="fail"){
//		                	$("#pwd-fgt-email").attr("data-content","内部错误，请联系网站管理员重置密码");
//		                	$("#pwd-fgt-email").popover('show');
//		                    return false;
//		                }else if(data=="success"){
//		                	$("#post_email .modal-footer .no").trigger("click");
//		                	swal("新密码已经发送至您的邮箱!", "", "success")
//		                }
		            },
		            error: function(){
		            	 $("#post_email .modal-footer .no").trigger("click");
		             	swal("内部错误，请联系网站管理员重置密码!", "", "fail")
		            }
		        });
	        	
	        });
	    	
		},
		
};

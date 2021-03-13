$(function() {
	$("#persionInfo").addClass('active_leftNav');
	
	$("#changeBtn").show();
	$("#saveBtn").hide();
	   $.ajax({
           url:getRootPath()+"/uc/persionInfo",
           type:"get",
           success:function(data){
               if(data !=null ){
            	   $("#id").val(data.id);
            	   $("#loginName").val(data.loginName);
            	   $("#email").val(data.email);
            	   $("#phone").val(data.phone);
            	   $("#company").val(data.company);
            	   if(data.role=="user"){
            		   $("#role").val("一般用户");
            	   }else if(data.role=="admin"){
            		   $("#role").val("管理员");
            	   }
               }else{
               }
           },
           error: function(){
           }
       });
	   
	   
	 //check开始！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
	   $("#email").bind("blur",function(){
		   var email = $("#email").val();
		   if(email==null || email=="" || email == $("#email").attr("placeholder")){
	        	$("#email").attr("data-content","请输入");
	        	$("#email").popover('show');
	            return false;
	        }
	        if(!checkEmail(email)){
	        	$("#email").attr("data-content","错误的邮箱");
	        	$("#email").popover('show');
	            return false;
	        }
	        $("#email").attr("data-content","");
	        $("#email").popover('hide');
	   });
		$("#phone").bind("blur",function(){
			var phone = $("#phone").val();
			// 1 验证是否输入
	        if(phone==null || phone=="" || phone == $("#phone").attr("placeholder")){
	        	$("#phone").attr("data-content","请输入");
	        	$("#phone").popover('show');
	            return false;
	        }
	        //2 验证是手机号码 
	        if(!mobilePhone(phone)){
	        	$("#phone").attr("data-content","错误的手机号");
	        	$("#phone").popover('show');
	            return false;
	        }
	        $("#phone").attr("data-content","");
	        $("#phone").popover('hide');   
			   });
		$("#company").bind("blur",function(){
			var company = $("#company").val();
			// 1 验证是否输入
	        if(company==null || company=="" || company == $("#company").attr("placeholder")){
	        	$("#company").attr("data-content","请输入");
	        	$("#company").popover('show');
	            return false;
	        }
	        $("#company").attr("data-content","");
	        $("#company").popover('hide');
		});
	 //check结束！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
});

persionInfoMenu={
		changeBtn:function(){
			$("#email").removeAttr("readonly");
			$("#phone").removeAttr("readonly");
			$("#company").removeAttr("readonly");
			$("#changeBtn").hide();
			$("#saveBtn").show();
			
			$("#saveBtn").attr("data-content","可修改项：邮箱、手机号、公司名");
			$("#saveBtn").popover('show');
		},
		saveBtn:function(){
			var id = $("#id").val();
			var email = $("#email").val();
			var phone = $("#phone").val();
			var company = $("#company").val();
			$("#saveBtn").attr("data-content","");
			$("#saveBtn").popover('hide');
			
			//check开始！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
			$('#email').trigger("blur");
			$('#phone').trigger("blur");
			$('#company').trigger("blur");
			if(($("#email").attr("data-content")!=null &&$("#email").attr("data-content")!="")||
		    		($("#phone").attr("data-content")!=null &&$("#phone").attr("data-content")!="")||
		    		($("#company").attr("data-content")!=null &&$("#company").attr("data-content")!=""))
				{
		    		return false;
		    	}
	      //check结束！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
			var param={};
			param.id=id;
			param.email=email;
			param.phone=phone;
			param.company=company;
			
			$.ajax({
		           url:getRootPath()+"/uc/pInfoUpdate",
		           type:"post",
		           data:param,
		           success:function(data){
		               if(data =="success" ){
		            	   swal("更新成功!", "", "success");
		            	   $(".confirm").unbind("click").bind("click",function(){
		            		   window.location.href = getRootPath()+"/system/persionInfo";
		            	   });
		               }else if(data =="fail" ){
		            	   swal("更新失败", "", "error");
		               }
		           },
		           error: function(){
		           }
		       });
		}
}

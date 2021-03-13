$(function() {

	$("#head_rl").addClass('active_leftNav');
	
    $("#company").bind("blur",function(){
    	// 1 验证是否输入
        var company = $("#company").val();
        if(company==null || company=="" || company == $("#company").attr("placeholder")){
        	$("#company").attr("data-content","请输入");
        	$("#company").popover('show');
            return false;
        }
        $("#company").attr("data-content","");
        $("#company").popover('hide');
    });
    $("#area").bind("blur",function(){
    	// 1 验证是否输入
        var area = $("#area").val();
        if(area==null || area=="" || area == $("#area").attr("placeholder")){
        	$("#area").attr("data-content","请输入");
        	$("#area").popover('show');
            return false;
        }
        $("#area").attr("data-content","");
        $("#area").popover('hide');
    });
    
    $("#description").bind("blur",function(){
    	// 1 验证是否输入
        var description = $("#description").val();
        if(description==null || description=="" || description == $("#area").attr("placeholder")){
        	$("#description").attr("data-content","请输入");
        	$("#description").popover('show');
            return false;
        }
        $("#description").attr("data-content","");
        $("#description").popover('hide');
    });
	
	$("#uploadBtn").unbind("click").bind("click",function(){
    	//提交到后台的方法
		//1 选择文件check 仅支持xlsx
		var xls = $("#xlsfile").val();
		  //简单的是否选中，以及格式检查
		if(xls!=null && xls!="" && xls!=" "){
			if(!(xls.lastIndexOf(".xlsx")>-1)){
	        	$("#xlsfile").attr("data-content","文件类型不正确，请使用模板excel");
	        	$("#xlsfile").popover('show');
	            return false;
	        }
		}
    	$("#xlsfile").attr("data-content","");
        $("#xlsfile").popover('hide');
		
    	//2 页面输入
		$('#company').trigger("blur");
		$('#area').trigger("blur");
		$('#description').trigger("blur");
		
    	if(($("#company").attr("data-content")!=null &&$("#company").attr("data-content")!="")||
        		($("#area").attr("data-content")!=null &&$("#area").attr("data-content")!="") ||
        		($("#description").attr("data-content")!=null &&$("#description").attr("data-content")!="")){
        		return false;
        	}
		
    	
    	//3 提交到后台
    	//公司名 company
    	var company=$("#company").val();
    	//地区 area
    	var area=$("#area").val();
    	//资源单描述 description
    	var description=$("#description").val();
    	//分类 classification_0
    	//主营品种 main_varieties_0
    	//主营钢厂 main_steelfactory_0
    	var classification="";//分类
    	var mainVarieties="";//主营品种
    	var mainSteelfactory="";//主营钢厂
            $('input[name="classification"]:checked').each(function(){
                var idx=$(this).val();
           	mainVarieties =mainVarieties +" "+ $("#main_varieties_"+idx).val();
           	mainSteelfactory =mainSteelfactory +" "+ $("#main_steelfactory_"+idx).val();
			if(idx=="0"){
				classification=classification+" "+"冷轧";
			}else if(idx=="1"){
				classification=classification+" "+"涂镀";
			}else if(idx=="2"){
				classification=classification+" "+"热轧";
			}else if(idx=="3"){
				classification=classification+" "+"酸洗";
			}else if(idx=="4"){
				classification=classification+" "+"中厚板";
			}else if(idx=="5"){
				classification=classification+" "+"螺线";
			}else if(idx=="6"){
				classification=classification+" "+"型材";
			}else if(idx=="7"){
				classification=classification+" "+"其他";
			}
     	});
    	
    	//如果没有选择一个分类也让报一个错 TODO
    	if(mainVarieties==""){
//    		alert("请选择一个分类");
    		swal("请选择一个分类!")
    		return false;
    	}

    	 var oMyForm = new FormData();
			oMyForm.append("file", xlsfile.files[0]);
			oMyForm.append("company", company);
			oMyForm.append("area", area);
			oMyForm.append("description", description);
			oMyForm.append("classification", classification);
			oMyForm.append("mainVarieties", mainVarieties);
			oMyForm.append("mainSteelfactory", mainSteelfactory);
			$.ajax({
				url : getRootPath()+"/logic/uploadRl",
				data : oMyForm,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'post',
				success : function(data) {
					swal(data);
					$(".confirm").unbind("click").bind("click",function(){
						window.location.href = getRootPath()+"/system/resourcelist";
					});
				},
				error : function() {
				}
			});
	});
});



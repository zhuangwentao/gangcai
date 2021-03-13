$(function() {
	   //设置全局ajax开始时 遮罩层开始
    $(document).ajaxStart(function () {
        $.blockUI({ message: '<h1 style="font-size: 12px!important;">页 面 加 载 中···</h1>' , css: { backgroundColor: '#cecece', color: '#3498db' ,border: 'none'}  , baseZ: 20000});
        });

       //设置全局ajax结束时 遮罩层结束
    $(document).ajaxStop(function () {
        $.unblockUI();
    });
	
	
	$("#head_goods").addClass('active_leftNav');
});

var goodsPage = {

//    checkCurrentIndex :function(index){
//        $(":input[name='goodsPage-index-check'][value=\""+index+"\"]").prop("checked", true);
//    },
		
    keyEnter :function(event){
        //按键处理
    	//获取用户单击键盘的“键值”
        var k = event.keyCode?event.keyCode:event.which?event.which:event.charCode;
        if(k == 13){
        	goodsPage.queryGoodsListForPage(1);
        }
    },
    queryByEnter : function(){
        goodsPage. queryGoodsListForPage(1);
    },

    queryGoodsListForPage : function(currentPage){
        var pageSize =$("#goodsPage-sel-pagesize").find("option:selected").val();
//    	var pageSize =10;
    	//检索信息
        var area = $("#area-serch-key").val();
        var specifications  = $("#specifications-serch-key").val();
        var material = $("#material-serch-key").val();
        var origin = $("#origin-serch-key").val();
        var productName = $("#productName-serch-key").val();
        var wearhouse = $("#wearhouse-serch-key").val();
        var price = $("#price-start-serch-key").val();
        var priceEnd = $("#price-end-serch-key").val();
        var company = $("#company-serch-key").val();
        
        if(!checkDouble(price)){
        	price=null;
        	$("#price-start-serch-key").val("")
        }
        if(!checkDouble(priceEnd)){
        	priceEnd=null;
        	$("#price-end-serch-key").val("")
        }
        
        //资源单页面查询资源单
        $.ajax({
            url:getRootPath()+"/logic/goodsPage",
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
            	"company":company},
            success:function(data){
                if(data !=null && data.content != null){
                	goodsPage.appendListBodyHtml(data.content);
                	goodsPage.appendPageFooterHtml(data.currentPage,data.totalPage,data.totalCount,data.currentIndex,data.content.length);
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

//            if(i%2 ==1){
//            	html.push("<tr role='row' class='odd' onclick='goodsPage.checkCurrentIndex(\""+data[i].id+"\")'>");
//            }else{
//            	html.push("<tr role='row' class='even' onclick='goodsPage.checkCurrentIndex(\""+data[i].id+"\")'>");
//            }
//            //ID->radio
//            html.push("<td class='' align='center'> <input type='radio' name='goodsPage-index-check' value='"+data[i].id+"'></td>");
          if(i%2 ==1){
        	html.push("<tr role='row' class='odd' ");
	        }else{
	        	html.push("<tr role='row' class='even' ");
	        }
	        //ID->radio
	        html.push("<td class='' align='center'> <input type='radio' name='goodsPage-index-check' value='"+data[i].id+"'></td>");

	      //品名
	        html.push("<td > "+data[i].productName+" </td>");
	      //规格
	        html.push("<td > "+data[i].specifications+" </td>");
	      //材质
	        html.push("<td > "+data[i].material+" </td>");
	      //钢厂
	        html.push("<td > "+data[i].origin+" </td>");
	      //地区
	        html.push("<td > "+data[i].resourceList.area+" </td>");
	      //仓库
	        html.push("<td > "+data[i].wearhouse+" </td>");
	      //供应商
	        html.push("<td > "+data[i].resourceList.company+" </td>");
	      //价格
	        html.push("<td > "+data[i].price+" </td>");
	        
            html.push("</tr>");
        }
        $("#goods-list-table tbody").html(html.join(""));
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
        html.push("<li ><a title='首页' onclick='goodsPage.queryGoodsListForPage(1)'>首页</a></li>");
        if(currentPage==1) {
            html.push("<li class='prev disabled'><a title='Prev' href='#'><i class='fa fa-angle-left'></i></a></li>");
        }else {
            html.push("<li class='prev'><a title='Prev' onclick='goodsPage.queryGoodsListForPage("+(currentPage-1)+")'><i class='fa fa-angle-left'></i></a></li>");
        }
        if(totalPage <= 5) {
            for(var k=1;k<=totalPage;k++) {
                if(k==currentPage) {
                    html.push("<li class='active'><a onclick='goodsPage.queryGoodsListForPage("+k+")'>"+k+"</a></li>");

                }else {
                    html.push("<li class=''><a onclick='goodsPage.queryGoodsListForPage("+k+")'>"+k+"</a></li>");
                }
            }
        }else {
            if(currentPage <= 3) {
                for(var k=1;k<= 5;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='goodsPage.queryGoodsListForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='goodsPage.queryGoodsListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
                html.push("<li class=''><a>...</a></li>");
            }else if(currentPage+2<totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-2;k<=currentPage+2;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='goodsPage.queryGoodsListForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='goodsPage.queryGoodsListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
                html.push("<li class=''><a>...</a></li>");
            }else if(currentPage+2==totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-2;k<=totalPage;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='goodsPage.queryGoodsListForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='goodsPage.queryGoodsListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
            }else if(currentPage+1==totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-3;k<=totalPage;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='goodsPage.queryGoodsListForPage("+k+")'>"+k+"</a></li>");

                    }else {
                        html.push("<li class=''><a onclick='goodsPage.queryGoodsListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
            }else if(currentPage==totalPage) {
                html.push("<li class=''><a>...</a></li>");
                for(var k=currentPage-4;k<=totalPage;k++) {
                    if(k==currentPage) {
                        html.push("<li class='active'><a onclick='goodsPage.queryGoodsListForPage("+k+")'>"+k+"</a></li>");
                    }else {
                        html.push("<li class=''><a onclick='goodsPage.queryGoodsListForPage("+k+")'>"+k+"</a></li>");
                    }
                }
            }
        }

        if(currentPage==totalPage) {
            html.push("<li class='next disabled'><a title='Next' href='#'><i class='fa fa-angle-right'></i></a></li>");
        }else {
            html.push("<li class='next'><a title='Next' onclick='goodsPage.queryGoodsListForPage("+(currentPage+1)+")'><i class='fa fa-angle-right'></i></a></li>");
        }
        html.push("<li ><a title='尾页' onclick='goodsPage.queryGoodsListForPage("+(totalPage == 0 ? 1 : totalPage)+")'>尾页</a></li>");

        $("#goods-list-table-footer").html(html.join(""));
        
    }, 
    queryClear:function(){
    	$(".search input").val("");
    	goodsPage.queryGoodsListForPage(1);
    }
};

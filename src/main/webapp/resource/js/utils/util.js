/**
 * 工具类
 * @author zhuangwentao
 */


/*Get root path */
function getRootPath(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
//	var pos=strFullPath.indexOf(strPath);
//	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	return postPath;
}


/**
 * @param time
 * @returns {String}
 * 
 */
function timeStamp2String(time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}

function isValidDateGrap(d1,d2){
	if(d1!=""&&d2!=""){
		if(!isValidDate(d1)||!isValidDate(d2)){//日期格式YYYY-MM-DD校验
			jAlert("请输入正确的日期：YYYY-MM-DD或YYYYMMDD");
	       return false;
	    }
		var sd1=d1.split("-");
		var sd2=d2.split("-");
		var now1 = new Date(sd1[0],sd1[1]-1,sd1[2]);
		var now2 = new Date(sd2[0],sd2[1]-1,sd2[2]);
		now1.setMonth(now1.getMonth()+2);
		if(now2>now1){
			jAlert("时间范围不得超过两个月");
		  return false;
		}
	}
	return true;
}

/**校验是否日期格式*/
function isValidDate(date){  
  var strValue = date.split("-");
  var isNum = /^[-]?\d*[.]?\d*$/;
  if(strValue.length!=3){
	  return false;
  }else if(!isNum.test(strValue[0]) || !isNum.test(strValue[1]) || !isNum.test(strValue[2])){
	  return false;
  }
  var intYear  = parseInt(strValue[0],10);
  var intMonth = parseInt(strValue[1],10)-1;
  var intDay   = parseInt(strValue[2],10);  
  var isDate = new Date(intYear,intMonth,intDay);
  if(isDate.getFullYear()!=intYear || isDate.getMonth()!=intMonth || isDate.getDate()!=intDay){
    return false;
  }
  return true;
}

//手机号码
function mobilePhone(value){
    return this.isBlank(value) || /^(1)\d{10}$/.test(value);
}
//email
function checkEmail(value){
    return this.isBlank(value) || /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
}
//double
function checkDouble(value){
    return this.isBlank(value) || /^\d+\.?\d*$/.test(value);
}
//int
function checkInt(value){
    return this.isBlank(value) || /^[0-9]*$/.test(value);
}
function isBlank(obj){

    if(obj==null||obj==""||obj=="undefined"||obj=="null"){
        return true;
    }else {
        if($.trim(obj)==null||$.trim(obj)==""||$.trim(obj)=="undefined"||$.trim(obj)=="null")
            return true;
        else
            return false;
    }
}

function winOpen (strURL,strName,width,height)
{
    theWindow = window.open (strURL,strName,"width="+width+" height="+height+" scrollbars=yes left="+(1024-width)/2+" top="+(768-height)/2);	
    if (theWindow.opener == null) theWindow.opener = window;
    if (window.focus) theWindow.focus();
}
//验证邮件
function verifyEmailAddress(strEmail){
  var myReg = /^[_a-zA-Z0-9_-_._-]+@([_a-zA-Z0-9_-]+\.)+[a-zA-Z]{2,3}$/;
  return myReg.test(strEmail);
}
/*****************************************************************
****                     判断是否为日期数据  (lhm)       例子:itIsDate("2009-10-7" , "-")    *****
*****************************************************************/
function itIsDate(DateString , Dilimeter) 
{ 
  if (DateString==null) return false; 
  if (Dilimeter=='' || Dilimeter==null) 
   Dilimeter = '-'; 
  var tempy=''; 
  var tempm=''; 
  var tempd=''; 
  var tempArray; 
  if (DateString.length<8 && DateString.length>10) 
    return false;    
  tempArray = DateString.split(Dilimeter); 
  if (tempArray.length!=3) 
   return false; 
  if (tempArray[0].length==4) 
  { 
   tempy = tempArray[0]; 
   tempd = tempArray[2]; 
  } 
  else 
  { 
   tempy = tempArray[2]; 
   tempd = tempArray[1]; 
  } 
  tempm = tempArray[1]; 
  var tDateString = tempy + '/'+tempm + '/'+tempd+' 8:0:0';//加八小时是因为我们处于东八区 
  var tempDate = new Date(tDateString); 
  if (isNaN(tempDate)) 
   return false; 
 if (((tempDate.getUTCFullYear()).toString()==tempy) && (tempDate.getMonth()==parseInt(tempm)-1) && (tempDate.getDate()==parseInt(tempd))) 
  { 
   return true; 
  } 
  else 
  { 
   return false; 
  } 
} 

/*****************************************************************
****                   求字符串的字节长度     (lhm)          *****
*****************************************************************/
function byteLength(paraString) 
{
 var strValue =new String(paraString);
 var strLength = strValue.length;
 var numLength =0;
  for (globle_i =0 ; globle_i<strLength;globle_i++){
    var ASCIIValue =strValue.charCodeAt(globle_i);
    if ( ASCIIValue > 0 && ASCIIValue < 127 )  
      numLength = numLength + 1 
    else
     numLength = numLength + 2
  }
  return numLength;
}

/*****************************************************************
****                     去除空格     (lhm)                 *****
*****************************************************************/
function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}
	
function ltrim(stringToTrim) {
	return stringToTrim.replace(/^\s+/,"");
}
		
function rtrim(stringToTrim) {
	return stringToTrim.replace(/\s+$/,"");
}

String.prototype.trim = function() {return this.replace(/^\s+|\s+$/g,"");}
String.prototype.ltrim = function() {return this.replace(/^\s+/,"");}
String.prototype.rtrim = function() {return this.replace(/\s+$/,"");}
/*****************************************************************
****               复选框的全选与取消     (LHM)              *****
*****************************************************************/
function CheckAll(form){
	var length = form.itemId.length;
	var tocheck = form.chkall.checked;
	if (length)
		for (var i=0; i<length; i++){ 
			if (form.itemId[i].disabled != true){
				form.itemId[i].checked = tocheck;
			} 			
		}
	else {
		if (form.itemId.disabled !=true){
			form.itemId.checked = tocheck;
		}
	}
}

/*****************************************************************
****                     删除处理     (LHM)                  *****
*****************************************************************/
function del_btn (form,strMsg,actionurl){
  	var result = false;
  	var length = form.itemId.length;	
	if (form.itemId.checked) { //只有一条记录时执行此语句
		result = true;	
	}  		
	for (var i=0; i<length; i++){ 
		if (form.itemId[i].checked){
		  result = true;
		  break;
		}		 		
	}
    if (!result){
		alert ("没有选择任何项目!");
		return false;
    }else{
		if (confirm('\n'+strMsg)){
			form.action = actionurl;
			return true;
		}
	    return false;
	} 	
}

/*****************************************************************
****                    转化字符串     (LHM)                 *****
*****************************************************************/
function conversion_code(paraString)
{
	strResult = "";
	j=0;
	for (i=0;i<paraString.length;i++){ 
		Char = String1.charAt(i);
		if (Char=="'"){
		    strResult = strResult + paraString.substring(j,i)+"\\"+"\'";
		    j=i+1;
		 } 
	return strResult;
	}
}

/*****************************************************************
****                 数字输入控制处理     (LHM)              *****
*****************************************************************/
function InputIntNumberCheck(){
	//为支持IE 或 Netscape
	var theEvent=window.event || arguments.callee.caller.arguments[0]; 
	var elm ;
	var ver = navigator.appVersion;
	if (ver.indexOf("MSIE") != -1){  // IE
		if ( !((theEvent.keyCode >=48)&&(theEvent.keyCode<=57))){
			theEvent.keyCode=0;
		}
	}else{ // Netscape
		if ( !((theEvent.which >=48)&&(theEvent.which<=57))){
			theEvent.stopPropagation();
			theEvent.preventDefault();
		}
	}
	//
}

/*****************************************************************
****          有小数点数字输入控制处理     (LHM)             *****
*****************************************************************/
function InputLongNumberCheck(){
	if ( !((window.event.keyCode >=48)&&(window.event.keyCode<=57) || window.event.keyCode ==46)){
		window.event.keyCode=0;
	}

	var theEvent=window.event || arguments.callee.caller.arguments[0]; 
	var elm ;
	var ver = navigator.appVersion;
	if (ver.indexOf("MSIE") != -1){  // IE
		if (!((theEvent.keyCode>=48)&&(theEvent.keyCode<=57) || theEvent.keyCode ==46)){
			theEvent.keyCode=0;
		}
	}else{ // Netscape
		if ( !((theEvent.which >=48)&&(theEvent.which<=57) || theEvent.which ==46)){
			theEvent.stopPropagation();
			theEvent.preventDefault();
		}
	}
}

/*****************************************************************
****                        换页处理                         *****
*****************************************************************/
function toWhichPage(objform, whichPage){
    objform.whichPage.value = whichPage;
    objform.submit();
}

/*************************liuxch   *******************************
****                        获取cookie内容                   *****
*****************************************************************/
function getCookie( name ){
	var nameOfCookie = name + "=";
	var x = 0;
	while ( x <= document.cookie.length ){
		var y = (x+nameOfCookie.length);
		if ( document.cookie.substring( x, y ) == nameOfCookie ) {
			if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
			endOfCookie = document.cookie.length;
			return unescape( document.cookie.substring( y, endOfCookie ) );
		}
		x = document.cookie.indexOf( " ", x ) + 1;	
		if ( x == 0 ) break;
	}
	return "";
}

/*****************************************************************
****                    设置cookie内容、过期时间              *****
*****************************************************************/
function setCookie( name, value, expiredays ) { 
	var todayDate = new Date(); 
	todayDate.setDate( todayDate.getDate() + expiredays ); 
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";" 
} 
/*****************************************************************
****                      检查输入字符    (lhm)              *****
'//		 islegality：输入的字符是否为给定的字符
'//返回值：bool
*****************************************************************/
function islegality(checkstrpass){
var checkokpass="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
for (i=0; i<checkstrpass.length; i++) {
       ch=checkstrpass.charAt(i);
       for (j=0;j<checkokpass.length; j++){
        if (ch==checkokpass.charAt(j))
        break;
        }
      if (j==checkokpass.length){
	  return false; //函有特别字符时返回false
      break;
        }
  }
   return true;
}
/**
* 检查输入是否中文
*/
function ck_chinese(value_) {
  return escape(value_).indexOf("%u")!=-1 
}
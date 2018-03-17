<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title> 类别选择 </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<SCRIPT language=JavaScript src="/js/xmlhttp.js"></SCRIPT>

<SCRIPT language=JavaScript>
function checkIt(){
	var objForm = document.forms[0];
	var form = opener.document.forms[0];
	if (form){
		form.typeid.value = objForm.dicId.value;
		form.v_type_name.value = objForm.dicName.value;
	}
	window.close();
}
function getDicName(dicId,strDicName){
	var objForm = document.forms[0];
	objForm.dicId.value = dicId;
	objForm.dicName.value = strDicName;
}
function getTypeList(typeid){
		var typecontent = document.getElementById('typecontent');
		if(typecontent){
			typecontent.innerHTML= "数据正在加载...";
			send_request(function(value){typecontent.innerHTML=value}, '<html:rewrite action="/control/product/type/manage"/>?method=gettypelist&typeid='+ typeid, true);
		}
}
</SCRIPT>
<style>
<!--
.inputText{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #666666;
	border: 1px solid #999999;
}
body {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size: 12px;
	color: #666666;
}
-->
</style>
</head>

<body>
<c:set var="menuout" value=""/><c:forEach items="${menutypes}" var="menu">
	<c:set var="menuout" value="&gt;&gt;<a href='/control/product/manage.do?method=selectUI&typeid=${menu.typeid}'>${menu.name}</a> ${menuout}"/>
</c:forEach>
产品类别列表,请选择分类:<br>
导航:<a href='<html:rewrite action="/control/product/manage"/>?method=selectUI'>顶级目录</a> <c:out value="${menuout}" escapeXml="false"/>
<form method="post" name="main" action="">
<table width="100%" border="0" cellspacing="1" cellpadding="1">
  <input type="hidden" name="dicId">
  <input type="hidden" name="dicName">
  
	<tr><td id="typecontent">
	<table width="100%" border="0">
	<tr>
	<c:forEach items="${types}" var="type" varStatus="loop">		
	    <td>
		<c:if test="${fn:length(type.childtypes)>0}">
		<a href="<html:rewrite action="/control/product/manage"/>?method=selectUI&typeid=${type.typeid}"><b>${type.name}</b></a></c:if>
		<c:if test="${fn:length(type.childtypes)==0}"> <INPUT TYPE="radio" NAME="type" onclick="getDicName('${type.typeid}','${type.name}')">${type.name}</c:if>
		</td>
		<c:if test="${loop.count%5==0}"></tr><tr></c:if>
	</c:forEach>
	</tr></table>
	</td></tr>
	<tr><td colspan="2" align="center">
		<input type='button' name='create' value=" 确 认 " onClick="javascript:checkIt()">
		<input type='button' name="cancel" onClick="javaScript:window.close()" value=" 取 消 "> 
    </td></tr>
</table>
</form>
</body>
</html>
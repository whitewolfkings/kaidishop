<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片上传结束</title>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function writefile(){
		var imagepath="${pageContext.request.contextPath}${imagepath}";
		window.opener.writimage(imagepath);
		window.close();
	}
//-->
</SCRIPT>
</head>
<body onload="writefile()">
<center>${message}</center>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>订单完成 巴巴运动网</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META http-equiv=Content-Language content=zh-CN>
<LINK href="/css/new_cart.css" rel="stylesheet" type="text/css">
<link href="/css/global/header01.css" rel="stylesheet" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
</HEAD>
<BODY>
<jsp:include page="/WEB-INF/page/share/Head.jsp"/>
<BR>
<h1>订单号:${param.orderid },应付金额:${param.payablefee }元</h1>
<br>
你选择的付款方式为"邮局汇款",在汇款单上务必写上您的订单号.如下是汇款信息:<br>
收款人:黎活明
<br>地址:北京市朝阳区左家庄
<br/>
<jsp:include page="/WEB-INF/page/share/Foot.jsp" />
</BODY>
</HTML>

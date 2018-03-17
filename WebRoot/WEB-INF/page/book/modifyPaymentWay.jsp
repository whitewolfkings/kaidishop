<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>修改订单的支付方式</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<script language="JavaScript">
function checkfm(form){
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="/control/order/manage" method="post" onsubmit="return checkfm(this)">
<html:hidden property="orderid"/>
<input type="hidden" name="method" value="modifyPaymentWay">
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td > <font color="#FFFFFF">修改订单的支付方式：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td><html:radio property="paymentWay" value="NET"/>网上支付</td>
    </tr>
    <c:if test="${deliverWay!='GENERALPOST' && deliverWay!='EMS' }">
    <tr bgcolor="f5f5f5"> 
      <td><html:radio property="paymentWay" value="COD"/>货到付款</td>
    </tr></c:if>
    <tr bgcolor="f5f5f5"> 
      <td><html:radio property="paymentWay" value="BANKREMITTANCE"/>银行电汇</td>
    </tr>
     <tr bgcolor="f5f5f5"> 
      <td><html:radio property="paymentWay" value="POSTOFFICEREMITTANCE"/>邮局汇款</td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确 定 " class="frm_btn">
        </div></td>
    </tr>
  </table>
</html:form>
<br>
</body>
</html>
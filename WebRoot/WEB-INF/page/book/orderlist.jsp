<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>订单列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<script language="JavaScript">
<!--
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
	
	function allSelect(items){
		var form = document.forms[0];
		var state = form.allselectbox.checked;
		var length = items.length;
		if(length){
			for(var i=0;i<length;i++){
				items[i].checked=state;
			}
		}else{
			items.checked=state;
		}
	}
	function _action(methodName){
		var form = document.forms[0];
		form.action='<html:rewrite action="/control/order/manage"/>?method='+ methodName;
		form.submit();
	}
	function selectItem(items){
		var form = document.forms[0];
		var length = items.length;
		if(length){
			for(var i=0;i<length;i++){
				if(items[i].checked) return true;
			}
		}else{
			return items.checked;
		}
		return false;
	}
//-->
</script>
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
<html:form action="/control/order/list" method="post">
<html:hidden property="page"/>
<html:hidden property="query"/>
<html:hidden property="orderid"/>
<html:hidden property="state"/>
<html:hidden property="username"/>
<html:hidden property="recipients"/>
<html:hidden property="buyer"/>
<input name="method" type="hidden">
  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="12" bgcolor="6f8ac4" align="right">
    	<%@ include file="/WEB-INF/page/share/fenye.jsp" %>
   </td></tr>
    <tr>
      <td width="4%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">选择</font></div></td>
      <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">订单号</font></div></td>
      <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">商品总金额</font></div></td>
      <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">订单总金额</font></div></td>
	  <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">配送费</font></div></td>
	  <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">应付费用</font></div></td>
	  <td width="15%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">下单时间</font></div></td>
	  <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">支付状态</font></div></td>
	  <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">支付方式</font></div></td>
	  <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">顾客</font></div></td>
	  <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">状态</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pageView.records}" var="entry">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"><input type="checkbox" name="orderids" value="${entry.orderid}"></div></td>
      <td bgcolor="f5f5f5"> <div align="center">${entry.orderid}</div></td>
      <td bgcolor="f5f5f5"> <div align="center">${entry.productTotalPrice }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center"> ${entry.totalPrice}</div></td>
	  <td bgcolor="f5f5f5"> <div align="center"> ${entry.deliverFee }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center"> ${entry.payablefee }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center"> ${entry.createDate }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center"> <c:if test="${entry.paymentstate}">已支付</c:if><c:if test="${!entry.paymentstate}">未支付</c:if></div></td>
	  <td bgcolor="f5f5f5"> <div align="center"> ${entry.paymentWay.name }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${entry.buyer.username }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${entry.state.name }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">
	  <c:if test="${empty entry.lockuser || entry.lockuser==employee.username}">
		 <a href="<html:rewrite action="/control/order/view"/>?orderid=${entry.orderid}">载入订单</a>
	 </c:if>
	  <c:if test="${!empty entry.lockuser && entry.lockuser!=employee.username}">
		 <font color="red">订单已锁定</font>
	 </c:if>
	  </div></td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr>
      <td bgcolor="f5f5f5" colspan="12" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="10%"><input type="checkbox" onclick="javascript:allSelect(this.form.orderids)" name="allselectbox">全选</td>
              <td width="85%"><c:if test="${!empty showButton}">
             	 <input type="button" class="frm_btn" value="解锁订单" onclick="_action('allUnLock')"> &nbsp;&nbsp;</c:if>
              </td>
          </tr>
        </table></td>
    </tr>
  </table>
</html:form>
</body>
</html>
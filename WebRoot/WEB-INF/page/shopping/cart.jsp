<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>购物车 凯帝商城</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META http-equiv=Content-Language content=zh-CN>
<LINK href="/css/new_cart.css" rel="stylesheet" type="text/css">
<link href="/css/global/header01.css" rel="stylesheet" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
/** 获取以指定字符串为前缀的输入字段集合 **/
/** 数字输入格式是否正确(长度1-4位，第一个数字必须是1-9) **/
function numericFormat(strNumber){   
  var newPar=/^[1-9]\d{0,3}$/;
  return newPar.test(strNumber);
} 

function getInputsByname(name, etype){//
	var inputs = document.getElementsByTagName("input");
	var texts = new Array();
	var y = 0;
	for (var i = 0; i < inputs.length; i++) {
	  if (inputs[i].type == etype && inputs[i].name!=null && inputs[i].name.substring(0, name.length) == name) {
			texts[y] = inputs[i];
			y++;
		}
	}
	return texts;
}
function settleAccounts(){
	if(validateAmount()){		
		var form = document.forms["buycart"];
		form.method.value="settleAccounts";
		form.submit();
	}
}

function modifyAmount(){
	if(validateAmount()){		
		var form = document.forms["buycart"];
		form.method.value="updateAmount";
		form.submit();
	}
}

/** 验证购买数量字段 **/
function validateAmount(){
	var amounts = getInputsByname("amount_", "text");
	if(amounts.length==0){
		alert("您还没有购买商品");
		return false;
	}else{
		for (var i = 0; i < amounts.length; i++) {
			var amount = amounts[i];
			if(amount.value==null || amount.value.trim()==""){
				alert("\n您购买的商品中,有的商品购买数量为空,请填写购买数量");
				amount.focus();
				return false;
			}else if(amount.value=="0"){
				alert("\n您购买的商品中,有的商品购买数量为0,如果您不需要该商品,可以删除它");
				amount.select();
				return false;
			}else if(!numericFormat(amount.value)){
				alert("\n购买数量含有非数字数据,请更正");
				amount.select();
				return false;
			}
		}
	}
	return true;
}
//-->
</SCRIPT>
</HEAD>
<BODY>
<jsp:include page="/WEB-INF/page/share/Head.jsp"/>
<BR>
<TABLE cellSpacing=0 cellPadding=5 width="98%" border="0" align="center">
  <TR>
    <TD><TABLE cellSpacing=0 cellPadding=0 width="96%" border=0>
      <TBODY>
        <TR>
          <TD width="24%"><IMG height=31 src="/images/buy/shop-cart-header-blue.gif" width="218" border=0></TD>
          <TD width="34%">如果您修改了商品数量，请点击 
             <img style="CURSOR: hand; " alt="修改数量" src="/images/buy/update-t-sm.gif" value="修改数量" border="0" onClick="javascript:modifyAmount()"></TD>
          <TD width="14%" align="left"><a href="<html:rewrite action="/shopping/cart/manage" />?method=deleteAll&directUrl=${param.directUrl}"><img style="CURSOR:hand;" alt="清空购物车" src="/images/buy/az-empty-shoppingcard.gif" border="0"></a></TD>
          <TD width="15%" align=left><a href="/"><img src="/images/buy/as-s-continus.gif" width="116" height="22" border="0"></a></TD>
          <TD width="13%" align=right><img style="CURSOR:hand;" src="/images/buy/az-by-split.gif" width="116" height="22" onClick="javascript:settleAccounts()"></TD>
        </TR>
      </TBODY>
    </TABLE></TD>
  </TR>

  <TR>
    <TD><FORM id="buycart" name="buycart" action="<html:rewrite action="/shopping/cart/manage" />" method="post">
    <INPUT TYPE="hidden" NAME="method" value="">
     <INPUT TYPE="hidden" NAME="directUrl" value="${param.directUrl}">
    <TABLE cellSpacing=0 cellPadding=6 width="100%" border=0> 
      <TR bgColor=#d7ebff>
        <TD width="457"><STRONG>我的购物车里的商品--马上购买</STRONG></TD>
        <TD width=112><DIV align=center><STRONG>市场价</STRONG></DIV></TD>
        <TD width=181><DIV align=center><STRONG>价格</STRONG></DIV></TD>
        <TD width=73><DIV align=center><STRONG>数量</STRONG></DIV></TD>
        <TD width=66>&nbsp;</TD>
      </TR>
<!-- loop begin -->
<c:forEach items="${buyCart.items}" var="item"> 
       <TR vAlign="top">
        <TD><STRONG><A href="" target="_blank">${item.product.name}</A></STRONG> <span class="h3color">[颜色/样式：<c:forEach items="${item.product.styles}" var="style">${style.name}</c:forEach>]</span><BR><BR></TD>
        <TD width="112" align="center"><SPAN class="price" title="￥${item.product.marketprice}元"><FONT color="black"><S><B>￥${item.product.marketprice}元</B></S></FONT></SPAN></TD>
        <TD width="181"><P align="center"><SPAN class="price"><B>￥${item.product.sellprice} 元</B></SPAN> <BR>
          为您节省：<SPAN class=price>￥${item.product.savedPrice}元 </SPAN><BR> </P></TD>
        <TD align="middle" width="73"><INPUT type="text" style="WIDTH: 30px" maxLength="3" value="${item.amount}"  name="amount_${item.product.id}_<c:forEach items="${item.product.styles}" var="style">${style.id}</c:forEach>" onkeypress="javascript:InputIntNumberCheck()"></TD>
        <TD align="middle" width="66"><a href="<html:rewrite action="/shopping/cart/manage" />?method=delete&directUrl=${param.directUrl }&buyitemid=${item.product.id}-<c:forEach items="${item.product.styles}" var="style">${style.id}</c:forEach>"><img height="17" src="/images/buy/delete.gif" width="45" border="0"></a></TD>
      </TR>
      <TR vAlign="top">
        <TD colSpan="5"><IMG height=1 src="/images/buy/green-pixel.gif" width="100%" border="0"></TD>
      </TR>
</c:forEach>
<!-- loop end -->	  
    </TABLE></FORM>
      <table width="96%" border="0" align="left">
        <tr>
          <td width="60%" align="right">如果您修改了商品数量，请点击
          <img style="CURSOR:hand;" alt="修改数量" src="/images/buy/update-t-sm.gif" value="修改数量" border="0" onClick="javascript:modifyAmount()"></td>
          <td width="9%" align="right"><DIV align="right"><SPAN class="price"><STRONG><B><FONT color="black">共计:</FONT></B></STRONG></SPAN></DIV></td>
          <td width="11%" align="right"><DIV align="center"><SPAN class="price"><STRONG><B class="price"><FONT color="black">${buyCart.totalSellPrice } 元</FONT></B></STRONG></SPAN></DIV></td>
          <td width="8%" align="right"><DIV align="right"><SPAN class="price"><STRONG><B><FONT color="black">节省:</FONT></B></STRONG></SPAN></DIV></td>
          <td width="12%" align="right"><DIV align="center"><SPAN class="price"><STRONG><B class="price">${buyCart.totalSavePrice } 元</B></STRONG></SPAN></DIV></td>
        </tr>
        <tr>
          <td colspan="3" align="right">&nbsp;</td>
          <td colspan="2" align="right"><img style="CURSOR:hand;" src="/images/buy/az-by-split.gif" width="116" height="22" onClick="javascript:settleAccounts()"></td>
        </tr>
      </table></TD>
  </TR>
</TABLE>
<br>
<jsp:include page="/WEB-INF/page/share/Foot.jsp" />
</BODY>
</HTML>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv="pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache, must-revalidate">
<META http-equiv="expires" content="Wed, 26 Feb 2006 08:21:57 GMT">
<META http-equiv="Content-TYPE" content="text/html; charset=UTF-8">
<TITLE>结算中心：选择支付方式 巴巴运动网</TITLE>
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<link href="/css/global/paymentway.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript">
<!--
// 表单验证
function validateForm(){
	var form = document.forms[0];
	if(""==getradiovalue(form.deliverway)){
		alert("\n请选择 '送货方式'");
		window.location="#deliverway";
		return false;
	}
	if(""==getradiovalue(form.paymentway)){
		alert("\n请选择 '支付方式'");
		window.location="#paymentway";
		return false;
	}
	return true;
}
function sendForm(){
	var form = document.forms[0];
	if(validateForm()) form.submit();
}
/*
 * 功能：取单选框的值
 */
function getradiovalue(obj){
	var result = "";
	try{		
		if (obj!=null){
			result = obj.value;
			if(typeof(result)=="undefined"){
				result="";
				for (i=0;i<obj.length;i++){
					if (obj[i].checked){
						result = obj[i].value;
						break;
					}
				}
			}
		}
	}catch(e){result="";}
	return result;
}

function paymentwaySelect(paymentwayValue){
	var paymentway_COD = document.getElementById('paymentway_COD');
	var timerequirement = document.getElementById('timerequirement');
	if(paymentway_COD!=null){
		if("GENERALPOST"==paymentwayValue || "EMS"==paymentwayValue){
			paymentway_COD.style.display="none";
			timerequirement.style.display="none";
			try{
				var form = document.forms[0];
				for (var i=0; i<form.paymentway.length; i++){
					if(form.paymentway[i].checked && "COD"==form.paymentway[i].value){
						if(i>0) form.paymentway[0].checked=true;
						form.paymentway[i].checked=false;						
						break;
					}
				}
			}catch(e){;}
		}else{
			paymentway_COD.style.display="block";
			timerequirement.style.display="block";
		}
	}
}

function showcashticket(){
	var cashticketlib = document.getElementById('cashticketlib');
	if(cashticketlib.style.display!="none"){
		cashticketlib.style.display="none";
	}else{
		cashticketlib.style.display="block";
	}
}

function selectcashticket(cardno, cardpwd){
	document.forms[0].cardno.value = cardno;
	document.forms[0].cardpassword.value = cardpwd;
	showcashticket();
	checkCashticket();//代金券验证
}

/** 根据值设置对象checked状态为true **/
	function setSelectRadioByValue(radioObject, value){
		if(typeof(radioObject.value)=="undefined"){
			for(var i=0;i<radioObject.length;i++){
				if(radioObject[i].value==value){
					radioObject[i].checked=true;
					break;
				}
			}
		}else{
			if(radioObject.value==value) radioObject.checked=true;
		}
	}

function pageinit(){	
	var form = document.forms[0];
	var deliverway = getradiovalue(form.deliverway);
	if(""!=deliverway){
		var timerequirement = document.getElementById('timerequirement');
		if("EXPRESSDELIVERY"==deliverway || "EXIGENCEEXPRESSDELIVERY"==deliverway){
			timerequirement.style.display="block";
		}
		var requirement = getradiovalue(form.requirement);
		if(""==requirement){
			if(form.delivernote.value.trim()!="") setSelectRadioByValue(form.requirement, "other");
		}else if("other"!=requirement){
			form.delivernote.value="";
		}
	}
}

//-->
</SCRIPT>
</HEAD>

<BODY onload="pageinit()">
<TABLE cellSpacing=0 cellPadding=0 align=center border=0>
  <TBODY>
  <TR>
    <TD><IMG src="/images/global/logo.gif" > 
	&nbsp;&nbsp;<IMG height=36 src="/images/buy/az-s-checkout-payment-banne.gif" > 
	</TD>
  </TR>
  </TBODY>
</TABLE>
<BR>
<html:form action="/customer/shopping/manage" method="post">
<INPUT TYPE="hidden" NAME="method" value="savePaymentway">
<TABLE cellSpacing=0 cellPadding=0 width="90%" align="center" border=0>
  <TBODY>
  <TR>
    <TD>
<SPAN class=h1><STRONG>请选择您的送货与支付方式:</STRONG></SPAN> 
      <TABLE height=31 cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD>
            <DIV align=right><IMG onClick="javascript:sendForm()" height="22" src="/images/buy/az-sfl-shipping-to-this-boo.gif" vspace=5 border=0 style="CURSOR: hand;">
        </DIV></TD></TR>
	 </TBODY></TABLE>
<A name="deliverway"></A>
      <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor="#eeeecc" border=0><TBODY>
        <TR>
          <TD bgColor="#ffffff">
            <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
              <TBODY>
              <TR bgColor="#eeeecc">
                <TD colSpan=2><STRONG>&nbsp;送货方式</STRONG></TD></TR>
				<tr>
				 <TD class="big14" vAlign="middle" align="right" width="10%"><html:radio property="deliverway" value="GENERALPOST" onclick="javascript:paymentwaySelect(this.value)"/></TD>
				 <TD vAlign="middle" ><B>平邮</B> (费用:0.0元)&nbsp;&nbsp;不支持货到付款，注:费用最低，需要到附近邮局自提，时间稍长</TD>
				</tr>
				<tr>
				<TD class="big14" vAlign="middle" align="right" width="10%"><html:radio property="deliverway" value="EXPRESSDELIVERY" onclick="javascript:paymentwaySelect(this.value)"/></TD>
				 <TD vAlign="middle" ><B>快递送货上门 </B> (费用:0.0元)&nbsp;&nbsp;支持货到付款 &nbsp;&nbsp;注:200个城市可以到达，部分城市不能到达</TD>
				</tr>
				<tr>
				<TD class="big14" vAlign="middle" align="right" width="10%"><html:radio property="deliverway" value="EXIGENCEEXPRESSDELIVERY" onclick="javascript:paymentwaySelect(this.value)" /></TD>
				 <TD vAlign="middle" ><B>加急快递送货上门</B> (费用:0.0元)&nbsp;&nbsp;支持货到付款&nbsp;&nbsp;注:200个城市可以到达，部分城市不能到达</TD>
				</tr>
				<tr>
				 <TD class="big14" vAlign="middle" align="right" width="10%"><html:radio property="deliverway" value="EMS" onclick="javascript:paymentwaySelect(this.value)"/></TD>
				 <TD vAlign="middle" ><B>国内特快专递EMS</B> (费用:0.0
				 元)&nbsp;&nbsp;不支持货到付款&nbsp;&nbsp;注:适合其他快运无法到达的城市，时间3-5个工作日</TD>
				</tr>
				<tr>
				  <TD colspan="2" vAlign="middle" class="big14">
				  
				  <TABLE cellSpacing=0 cellPadding=3 width="86%" align="center" id="timerequirement" border=0 style="display:none">
                    <TBODY>
                      <TR>
                        <TD align=left colSpan=2 style="FONT-WEIGHT: bold; PADDING-BOTTOM: 2px; PADDING-TOP: 2px; BORDER-BOTTOM: #000000 1px solid">时间要求(注:如对送货时间有特别要求请注明)</TD>
                      </TR>
                      <TR>
                        <TD align=right><html:radio property="requirement" value="工作日、双休日与假日均可送货"/></TD>
                        <TD align=left width="96%">工作日、双休日与假日均可送货</TD>
                      </TR>
                      <TR class=category-row-shaded>
                        <TD align=right><html:radio property="requirement" value="只双休日、假日送货"/></TD>
                        <TD align=left>只双休日、假日送货(工作日不用送)</TD>
                      </TR>
                      <TR>
                        <TD align=right><html:radio property="requirement" value="只工作日送货(双休日、假日不用送)"/></TD>
                        <TD align=left>只工作日送货(双休日、假日不用送) (注：写字楼/商用地址客户请选择)</TD>
                      </TR>
                      <TR class=category-row-shaded>
                        <TD align=right><html:radio property="requirement" value="学校地址/地址白天没人，请尽量安排其他时间送货"/></TD>
                        <TD align=left>学校地址/地址白天没人，请尽量安排其他时间送货 (注：特别安排可能会超出预计送货天数)</TD>
                      </TR>
                      <TR>
                        <TD align=right><input type="radio" name="requirement" value="other"></TD>
                        <TD align=left><P>特殊说明：
                           <html:text property="delivernote" maxlength="100" size="40" onfocus="javascript:setSelectRadioByValue(this.form.requirement,'other')"/>
                        </P></TD>
                      </TR>
                    </TBODY>
                  </TABLE></TD>
				  </tr>
			</TBODY></TABLE>
		  </TD>
		</TR>
	 </TBODY></TABLE>
<br><A name="paymentway"></A>
      <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#eeeecc border=0><TBODY>
        <TR>
          <TD bgColor=#ffffff>
            <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
              <TBODY>
              <TR bgColor=#eeeecc>
                <TD colSpan=2><STRONG>&nbsp;支付方式</STRONG></TD>
			 </TR>
			 <TR>
                <TD class="big14" vAlign="middle" align="right" width="10%"><html:radio property="paymentway" value="NET" /> </TD>
                <TD vAlign="middle" ><B>网上支付</B>  易宝支付</TD>
			  </TR>
              <TR id="paymentway_COD" >
                <TD class="big14" vAlign="middle" align="right" width="10%"><html:radio property="paymentway" value="COD"/> </TD>
                <TD><B>货到付款</B></TD>
			  </TR>
			  <TR>
                <TD class="big14" vAlign="middle" align="right" width="10%"><html:radio property="paymentway" value="BANKREMITTANCE"/> </TD> 
                <TD><B>银行电汇</B>  开户名: 北京惠利至易高科技发展有限公司<BR>开户行名称: 
                  交通银行上地支行<BR>银行帐号: 110060974018001084072</TD></TR>
              <TR>
                <TD class="big14" vAlign="middle" align="right" width="10%"><html:radio property="paymentway" value="POSTOFFICEREMITTANCE"/></TD> 
                <TD><B>邮局汇款</B><BR>收款人地址：<FONT COLOR="#FF9900">北京市朝阳区酒仙桥十街坊2号楼巨擎达物业428室</FONT>&nbsp;&nbsp;收款人姓名：<FONT COLOR="#FF9900">赵跳芳</FONT>&nbsp;&nbsp;收款人邮编：<FONT COLOR="#FF9900">100016</FONT><BR>请在汇款人简短留言中注明您的订单号/用户名(非常重要)<BR></TD></TR>
			 </TBODY></TABLE>
  </TABLE>
			</TD></TR></TBODY></TABLE><BR>
      <TABLE height=31 cellSpacing=0 cellPadding=0 width="90%" border=0 align="center">
        <TBODY>
        <TR>
          <TD>
            <DIV align=right><IMG onClick="javascript:sendForm()" height="22" src="/images/buy/az-sfl-shipping-to-this-boo.gif" vspace=5 border=0 style="CURSOR: hand;"> 
        </DIV></TD></TR></TBODY></TABLE>
</html:form>

</BODY></HTML>
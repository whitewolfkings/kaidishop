<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv="pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache, must-revalidate">
<META http-equiv="expires" content="Wed, 26 Feb 2006 08:21:57 GMT">
<META http-equiv="Content-TYPE" content="text/html; charset=UTF-8">
<link href="/css/global/address.css" rel="stylesheet" type="text/css">
<link href="/css/global/bottom.css" rel="stylesheet" type="text/css">
<TITLE>凯帝商城：结算中心：填写收货地址</TITLE>
<SCRIPT LANGUAGE="JavaScript" src="/js/Country.js" type="text/javascript"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" src="/js/provinceArea.js" type="text/javascript"></SCRIPT>
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
/*
 * 功能：取单选框的值
 */
function getradiovalue(objradio){
		var result = "";
		try{
			if(typeof(objradio.value)=="undefined"){
				for(var i=0;i<objradio.length;i++){
					if(objradio[i].checked){
						return objradio[i].value;
					}
				}
			}else{
				if(objradio.checked) result = objradio.value;
			}
		}catch(e){result = "";}
		return result;
}

function buyerinfoSelect(issame){
	if("true"==issame){
		document.getElementById('buyerinfoInput').style.display="none";
	}else{
		document.getElementById('buyerinfoInput').style.display="";
	}
}
//email验证
function isValidEmail(inEmail){
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return filter.test(inEmail);
}
	/**
	* FormField类,描述表单字段,第一个参数为字段的name属性值,第二个参数为字段的中文名
	*/
	function FormField(fieldName, fieldLabel){
		this.fieldName = fieldName;
		this.fieldLabel = fieldLabel;
	}
	//验证表单数据
	function validateFormData(){
		var form = document.forms[0];
		var fields = new Array(new FormField("recipients","收货人姓名"), new FormField("address","收货人地址"),
						new FormField("email","电子邮件"), new FormField("postalcode","邮政编码"));
		for(var i=0;i<fields.length;i++){
			var field = eval("form."+ fields[i].fieldName);
			if(field.value.trim()==""){
				alert(fields[i].fieldLabel+ " 不能为空");
				return false;
			}
		}
		if(getradiovalue(form.gender)==""){
			alert("请选择收货人的性别");
			return false;
		}		
		if(!isValidEmail(form.email.value)){
			alert("收货人的email格式不正确");
			return false;
		}
		if(!/^\d{6}$/.test(form.postalcode.value.trim())){
			alert("收货人的邮政编码必须是6位数字");
			return false;
		}
		if(form.mobile.value.trim()!=""){
			if(!/^1[35]\d{9}$/.test(form.mobile.value.trim())){
				alert("收货人的手机号格式不正确");
				return false;
			}
		}
		form.tel.value = "";
		if(form.maintel.value.trim()!=""){
			if(/^\d{7,8}$/.test(form.maintel.value.trim())){
				form.tel.value = form.forepart.value+"-"+ form.maintel.value;
				if(form.extension.value.trim()!="") form.tel.value += "-"+ form.extension.value;
			}else{
				alert("收货人的电话号码至少7位");
				return false;
			}
		}
	
		if(form.tel.value=="" && form.mobile.value.trim()==""){
			alert("收货人的手机和电话至少有一项必填");
			return false;
		}
		
		if(getradiovalue(form.buyerIsrecipients)=="false"){
			var buyerfields = new Array(new FormField("buyer","购买者的姓名"), new FormField("buyer_address","购买者的地址"),
						new FormField("buyer_postalcode","购买者的邮政编码"));
			for(var i=0;i<buyerfields.length;i++){
				var buyerfield = eval("form."+ buyerfields[i].fieldName);
				if(buyerfield.value.trim()==""){
					alert(buyerfields[i].fieldLabel+ " 不能为空");
					return false;
				}
			}
			if(getradiovalue(form.buyer_gender)==""){
				alert("请选择购买者的性别");
				return false;
			}			
			form.buyer_tel.value = "";
			if(/^\d{7,8}$/.test(form.buyer_maintel.value.trim())){
				form.buyer_tel.value = form.buyer_forepart.value+"-"+ form.buyer_maintel.value;
				if(form.buyer_extension.value.trim()!="") form.buyer_tel.value += "-"+ form.buyer_extension.value;
			}
			if(form.buyer_tel.value=="" && form.buyer_mobile.value.trim()==""){
				alert("购买者的手机和电话至少有一项必填");
				return false;
			}
		}
		return true;
	}
function sendForm(){
	var form = document.forms[0];
	if(validateFormData()) form.submit();
}

	function pageinit(){
		initPhone();
		showBuyInfo();
	}
	
	function showBuyInfo(){
		var form = document.forms[0];
		if(getradiovalue(form.buyerIsrecipients)=="false") 
			document.getElementById('buyerinfoInput').style.display="";
	}
	
	function initPhone(){
		var form = document.forms[0];
		var phone = form.tel.value;
		if(phone!=""){
			var tels = phone.split("-");
			if(tels.length>=2){
				form.forepart.value = tels[0];
				form.maintel.value = tels[1];
				if(tels.length==3) form.extension.value = tels[2];
			}
		}
		var buyerphone = form.buyer_tel.value;
		if(buyerphone!=""){
			var tels = buyerphone.split("-");
			if(tels.length>=2){
				form.buyer_forepart.value = tels[0];
				form.buyer_maintel.value = tels[1];
				if(tels.length==3) form.buyer_extension.value = tels[2];
			}
		}
	}
//-->
</SCRIPT>

<META content="MSHTML 6.00.2900.2769" name="GENERATOR">
</HEAD>
<BODY onload="pageinit()">
<html:form action="/customer/shopping/manage" method="post">
<input type="hidden" name="directUrl" value="${param.directUrl }">
<INPUT TYPE="hidden" NAME="method" value="saveDeliverInfo">
<TABLE cellSpacing=0 cellPadding=0 align="center" border=0>
  <TBODY>
  <TR>
    <TD><IMG src="/images/global/logo.gif" >   &nbsp;&nbsp;<IMG height=36 src="/images/buy/az-s-checkout-shipping-bann.gif" > </TD></TR></TBODY></TABLE><BR>

<TABLE cellSpacing=0 cellPadding=0 width="90%" align=center border=0>
  <TBODY>
  <TR>
    <TD>
      <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#eeeecc 
        border=0><TBODY>
        <TR>
          <TD bgColor="#ffffff">
            <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
              <TBODY>
              <TR bgColor="#eeeecc">
                <TD><strong><span 
            class=h1><strong>请输入配送地址</strong>:</span></strong></TD>
                <TD bgColor="#eeeecc">&nbsp;</TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
      <DIV id="cndivaddress">
      <TABLE cellSpacing=1 cellPadding=4 width="100%" border=0>
        <TBODY>
        <TR>
          <TD colSpan=2>&nbsp;</TD>
        </TR>
        <TR>
          <TD width=214>
            <DIV align="right">收货人姓名<span id="NameLable"></span>：</DIV></TD>
          <TD><html:text property="recipients" maxlength="8" size="30"/><FONT color="#ff0000">*</FONT>&nbsp;<html:radio property="gender" value="MAN"/>先生 <html:radio property="gender" value="WOMEN"/>女士</TD></TR>
        <TR>
          <TD height="27">
            <DIV align="right">收货人地址<span id="AddressLable"></span>： </DIV></TD>
          <TD><html:text property="address" maxlength="100" size="60"/><FONT 
            color="#ff0000">*</FONT></TD></TR>
        <TR>
          <TD height="27">
            <DIV align="right">电子邮件<span id="EmailLable"></span>：</DIV></TD>
          <TD><html:text property="email" maxlength="45" size="30"/><FONT color="#ff0000">*</FONT></TD></TR>
        <TR>
          <TD height="27">
            <DIV align="right">邮政编码<span id="PostalcodeLable"></span>：</DIV></TD>
          <TD><html:text property="postalcode" maxlength="6" size="20"/><FONT 
            color="#ff0000">*</FONT> <font color="#484848">请正确填写邮政编码，以免延误您的订单送达时间。不知道邮编？<a href="http://www.cpdc.com.cn/webmodules/postcode/CPDC_03G01.aspx" target="_blank">请进这里查询</a></font></TD></TR>
        <TR>
          <TD><html:hidden property="tel" />
            <DIV align=right>电话<span id="TelLable"></span>：</DIV></TD>
          <TD><table width="30%" border="0" cellpadding="0" cellspacing="2">
            <tr>
              <td><input value="010" size="4" name="forepart" maxlength="4" onkeypress="javascript:InputIntNumberCheck()"></td>
              <td><input name="maintel" size="8" maxlength="8" onkeypress="javascript:InputIntNumberCheck()"></td>
              <td><input name="extension" size="4" maxlength="4" onkeypress="javascript:InputIntNumberCheck()"></td>
            </tr>
            <tr>
              <td><span class="STYLE1">区号</span></td>
              <td><span class="STYLE1">电话号码</span></td>
              <td><span class="STYLE1">分机</span></td>
            </tr>
          </table></TD>
        </TR>
        <TR>
          <TD>
            <DIV align=right>手机<span id="MobileLable"></span>：</DIV></TD>
          <TD><html:text property="mobile" maxlength="15" size="20"/> 
<font color="#484848">（手机和电话至少有一项必填）</font></TD></TR>
        <TR>
          <TD>
            <DIV align="right">购买人与收货人是否相同<FONT color="#ff0000">*</FONT>：</DIV></TD>
          <TD><html:radio property="buyerIsrecipients" value="true" onclick="javascript:buyerinfoSelect(this.value)"/> <b>相同</b>
		  <html:radio property="buyerIsrecipients" value="false" onclick="javascript:buyerinfoSelect(this.value)"/> <b>不相同</b> </TD>
        </TR>
		  <!---------------------------->
        <TR id="buyerinfoInput" style="DISPLAY: none">
          <TD></TD>
          <TD nowrap><div class="OkMsg" >
		  <TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
            <TR>
              <TD align="right" height="25"><FONT color="#f47a22">*</FONT> 购买者姓名：</TD>
              <TD align="left"><html:text property="buyer" maxlength="8" size="30" />
              &nbsp;<html:radio property="buyer_gender" value="MAN" />先生 <html:radio property="buyer_gender" value="WOMEN"/>女士</TD>
            </TR>
            <TR>
              <TD align="right" height="25"><FONT color="#f47a22">*</FONT> 详细地址：</TD>
              <TD align="left"><html:text property="buyer_address" maxlength="100" size="60" />
              </TD>
            </TR>
            <TR>
              <TD align="right" height="25"><FONT color="#f47a22">*</FONT> 邮政编码：</TD>
              <TD><TABLE cellSpacing="0" cellPadding="0" border="0">
                  <TR>
                    <TD align="left" height="20"><html:text property="buyer_postalcode" maxlength="6" size="20"/>
                    </TD>
                    <TD align="left">&nbsp;&nbsp;<font color="#484848">请正确填写邮政编码，以免延误您的订单送达时间。</font> </TD>
                  </TR>
                </TABLE></TD>
            </TR>
            <TR>
              <TD align="right" height="25"><FONT color="#f47a22">*</FONT> 电话：</TD>
              <TD><TABLE cellSpacing="0" cellPadding="0" border="0">
                  <TR>
                    <TD align="left" colSpan="3"><html:text property="buyer_mobile" maxlength="15" size="20"/> </TD>
                    <TD height="25">&nbsp;移动电话</TD>
                  </TR>
                  <TR><html:hidden property="buyer_tel" />
					<td><input value="010" size="4" name="buyer_forepart" maxlength="4" onkeypress="javascript:InputIntNumberCheck()"></td>
					<td><input name="buyer_maintel" size="8" maxlength="8" onkeypress="javascript:InputIntNumberCheck()"></td>
					<td><input name="buyer_extension" size="4" maxlength="4" onkeypress="javascript:InputIntNumberCheck()"></td>
                    <TD height="25">&nbsp;固定电话</TD>
                  </TR>
                  <TR>
                    <TD align="center"><font color="#484848">区号</font></TD>
                    <TD align="center"><font color="#484848">电话</font></TD>
                    <TD align="center"><font color="#484848">分机</font></TD>
                    <TD>&nbsp;</TD>
                  </TR>
                </TABLE></TD>
            </TR>
            <TR>
              <TD height="20">&nbsp;</TD>
              <TD align="left"> <font color="#484848">（请留下您的联系电话，必要时，我们会通过电话向您确认相关信息。）</font></TD>
            </TR>
          </TABLE></div>
		  </TD>
        </TR>
		  <!---------------------------->

        <TR>
          <TD colSpan=2 align="center"><IMG onClick="javascript:sendForm()" src="/images/buy/az-sfl-shipping-to-this-boo.gif" vspace=5 border=0 style="CURSOR: hand;"></TD></TR>

		  </TBODY></TABLE></DIV>
      </TD></TR></TBODY></TABLE>
</html:form>

<jsp:include page="/WEB-INF/page/share/Foot.jsp"/>
</BODY></HTML>
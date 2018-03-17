<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户登录 巴巴运动网</title>
<link href="/css/global/header.css" rel="stylesheet" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
  <SCRIPT LANGUAGE="JavaScript">
  <!--
	function checkForm(){
		var form = document.forms[0];
		if(form.username.value=="" || form.username.value.length<6){
			alert("请输入长度6位以上的用户名");
			return false;
		}
		if(form.password.value=="" || form.password.value.length<6){
			alert("请输入长度6位以上的密码");
			return false;
		}
		return true;
	}
  //-->
  </SCRIPT>
<style type="text/css">
<!--
.STYLE1 {color: #666666}
.logintitle H2 {
	DISPLAY: block; FONT-WEIGHT: bold; FONT-SIZE: 14px; MARGIN: 0px 0px 0px 0px;PADDING:0 0 0 10px;color:#525252
}
.ablue2{
	FONT-SIZE: 14px; color:#0066FF
}
-->
</style>
</head>

<body>
<jsp:include page="/WEB-INF/page/share/simpleHead.jsp"/>
<!-- Head End -->
<TABLE cellSpacing=0 cellPadding=0 width=770 align=center border=0>
  <TBODY>
  <TR><TD background="/images/login/login_03.jpg">&nbsp;</TD></TR>
 </TBODY>
</TABLE>
<br/><c:if test="${!empty message}">
<DIV id="errorinfo">
<TABLE cellSpacing=1 cellPadding=8 width="600" align="center" bgColor="#dd9988" border=0>
  <TBODY>
  <TR>
    <TD bgColor="#ffffd5" align="left"><IMG height="17" src="/images/buy/exclamation-error-red.gif" width="17"> <FONT color="#990000"><STRONG><SPAN class="font14">错误提示<BR></SPAN></STRONG></FONT>
      <DIV id="errorMessage">${message }</DIV>
	</TD>
  </TR>
  </TBODY>
</TABLE>
</DIV></c:if>
<html:form action="/user/logon" method="post" onsubmit="javascript:return checkForm()">
 <INPUT TYPE="hidden" NAME="directUrl" value="${param.directUrl}"/>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="center"><table width="80%" border="0" align="center" cellpadding="10" cellspacing="0">
					<tr>
						<td width="100%" height="50" valign="top"><table width="95%" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width="3%"><img src="/images/global/loginicon1.gif" width="16" height="15" align="absBottom"></td>
									<td width="97%" align="left" class="logintitle"><H2>用户登录</H2></td>
								</tr>
							</table>						</td>
					</tr>
					<tr>
						<td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
								  <td width="40%" align="right" class="c5"><strong>您的用户账号：</strong></td>
								  <td width="27%"> <input name="username" id="username" type="text" size="20" maxlength="20" /></td>
								  <td align="left">&nbsp;</td>
						  </tr>
								<tr>
								  <td colspan="3" align="right" height="15"></td>
						  </tr>
								<tr>
									<td align="right" class="c5"><strong>您的密码：</strong></td>
									<td > <input name="password" type="password" size="20" maxLength="16">								</td>
									<td width="33%" align="left"><a href="/user/findpassword.do" class="ablue" target="_blank">忘记密码了？</a></td>
								</tr>
							</table>						</td>
					</tr>
					<tr>
						<td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td class="STYLE1">注意：如果你在网吧等公众场合的机器上登录本网，请在离开机器前退出，以免账户被他人冒用造成不必要的麻烦！</td>
								</tr>								
							</table>						</td>
					</tr>
					<tr>
						<td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width="34%" align="right">&nbsp;</td>
									<td width="8%" align="left" valign="middle"><input type="image" name="ImageButton1" src="/images/global/regloginbutton2.jpg" alt="" border="0" />&nbsp;</td>
								    <td width="58%" align="left" valign="middle">如果你还未注册，<span style="font-size:14px"><a href='<html:rewrite action="/user/reg"/>?method=regUI&directUrl=${param.directUrl }' class="ablue2">请免费注册</a>！</span></td>
								</tr>
							</table>						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table></html:form>
<jsp:include page="/WEB-INF/page/share/Foot.jsp"/>
</body>
</html>

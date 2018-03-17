<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>网站业务系统_管理平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<frameset framespacing="0" border="0" rows="60,*,15" frameborder="0">
  <frame name="banner" scrolling="no" noresize target="contents" src="<html:rewrite action='/control/center/top'/>" marginwidth="0" marginheight="0">
  <frameset cols="168,*">
    <frame name="menuframe" target="mainframe" src="<html:rewrite action='/control/center/left'/>" scrolling="no" marginwidth="0" marginheight="0">
    <frame name="mainframe" scrolling="auto" noresize src="<html:rewrite action='/control/center/right'/>" marginwidth="0" marginheight="0">
  </frameset>
  <frame name="menuframe" target="mainframe" src="<html:rewrite action='/control/center/end'/>" scrolling="no" marginwidth="0" marginheight="0">
</frameset><noframes></noframes>
</html>
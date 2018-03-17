<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<% 
response.setHeader("Cache-Control", "no-store"); //HTTP1.1
response.setHeader("Pragma", "no-cache"); //HTTP1.0
response.setDateHeader("Expires", 0);
%>
<c:if test="${exist}">
<font color="red">该用户名已经存在</font>
</c:if>
<c:if test="${!exist}">
<font color="green">该用户名可以使用</font>
</c:if>

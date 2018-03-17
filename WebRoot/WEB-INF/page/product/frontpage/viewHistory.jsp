<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
	<c:forEach items="${viewHistory}" var="viewproduct" varStatus="statu">
		<li class="bj_blue"><a href="<html:rewrite action="/product/view"/>?productid=${viewproduct.id}" target="_blank" title="${viewproduct.name}">${viewproduct.name}</a></li></c:forEach>			
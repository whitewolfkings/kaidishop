<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/share/taglib.jsp" %>
<div id="Head">
  <div id="HeadTop">
    <div id="Logo"><a href="http://www.babasport.com/" target=_top></a> </div>
    <div id="HeadNavBar">
      <ul>
        <li class="NoSep"><a id="MyBuyCar"  href="/shopping/cart.do" ><font color="blue"><Strong>购物车</Strong></font></a> </li>
        <li><a href="/user/reg.do?method=regUI" >新用户注册</a> </li>
        <li><a href="/user/logon.do" >用户登录</a> </li>
        <c:if test="${!empty user}"> <li><a href="/user/logout.do" >退出登录</a> </li></c:if>
        <li><a href="">帮助中心</a> </li>
        <li class="phone">服务热线：010-64663070</li>
      </ul>
    </div>
  </div>
</div>
<!-- Head End -->
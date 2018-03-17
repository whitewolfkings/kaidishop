<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/css/global/header01.css" rel="stylesheet" type="text/css">
<link href="/css/product/product.css" rel="stylesheet" type="text/css">
<link href="/css/global/topcommend.css" rel="stylesheet" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<SCRIPT language=JavaScript src="/js/xmlhttp.js"></SCRIPT>
<title>${product.name}-巴巴运动网</title>
<meta name="Keywords" content="${product.name}">
<META name="description" content="">
  <SCRIPT LANGUAGE="JavaScript">
  <!--
	function styleEvent(styleid){
		var productImage = document.getElementById('productImage_'+ styleid);
		if(productImage){
		    var main_image = document.getElementById("main_image");
			main_image.style.background="url("+ productImage.value+") center center no-repeat";
		}
	}
	
	function bigImageBrowse(){
		var form = document.forms["cart"];
		var stypeid = form.styleid.value;
		var productPrototypeImage = document.getElementById('productPrototypeImage_'+ stypeid);		
		if(productPrototypeImage){
			var path = "<html:rewrite action="/product/switch"/>?method=showimage&path="+ productPrototypeImage.value;			
			window.open (path ,"显示图片");
		}
	}
  //-->
  </SCRIPT>
</head>

<body>
<jsp:include page="/WEB-INF/page/share/Head.jsp"/>
<div id="ContentBody"><!-- 页面主体 -->
    <c:set var="out" value="&gt;&gt; <em>${product.name}</em>"/><c:forEach items="${stypes}" var="type" varStatus="statu">
		<c:set var="out" value=" &gt;&gt; <a href='/product/list.do?typeid=${type.typeid}'>${type.name}</a> ${out}"/>
	</c:forEach>
	 	 <div id="position"> 您现在的位置：<a href="/" name="linkHome">凯商城</a> <span id="uc_cat_spnPath"><c:out value="${out}" escapeXml="false"></c:out></span></div>
 <div class="browse_left"><!-- 页面主体 左边 --> 
        <!-- 浏览过的商品 -->
	 <div class="browse">
	      <div class="browse_t">您浏览过的商品</div>
	      <ul><div id="historyaccess"></div></ul>
	 </div>
	<!--精品推荐 start -->
	<DIV id="topcommend" align="left">
	       <DIV id="newtop"><IMG height=13 src="/images/global/sy2.gif" width=192></DIV>
	       <DIV id="newlist">
		  <DIV id="newmore">
		    <DIV class="title">精品推荐</DIV>
		  </DIV>
			<span id="commenddetail">
			</span>
		</DIV>
	</DIV>
</div><!-- 页面主体 左边end -->
	
 <div id="Right" ><!-- 页面主体 右边 -->
<form action="<html:rewrite action="/shopping/cart"/>" method="post" name="cart">
<INPUT TYPE="hidden" NAME="productid" value="${product.id}">
<div id="browse_left"><c:set var="currentimage"/><c:set var="imagecount" value="0"/>
<c:forEach items="${product.styles}" var="style"><c:if test="${style.visible}"><c:set var="currentimage" value="${style}"/><c:set var="imagecount" value="${imagecount+1}"/></c:if></c:forEach>
							<div class="right_left">
								<div id="main_image" onclick="JavaScript:bigImageBrowse()" style="cursor:hand;background:url(${currentimage.image140FullPath}) center center no-repeat"><img src="/images/global/product_blank.gif" WIDTH="200" HEIGHT="240"/></div>
								<img src="/images/global/zoom+.gif" onclick="JavaScript:bigImageBrowse()" style="cursor:hand;"/>
							</div>
							
							<div class="right_right">									
									<div class="right_title"><b>${product.name}</b></div>
									<div class="right_desc">
											<ul>
												<li class="li2">商品编号：A${product.id}<font color="#CC0000">（电话订购专用）</font></li>
												<c:if test="${!empty product.brand}"><li>品牌：${product.brand.name} </li>	</c:if>																																		</ul>
									</div>
									<div class="right_desc">
											<c:if test="${imagecount==1}">
											<INPUT TYPE="hidden" NAME="styleid" value="${currentimage.id }">
											<li>颜色：${currentimage.name }</li>
											<INPUT TYPE="hidden" id="productImage_${currentimage.id }" value="${currentimage.imagename}">
											<INPUT TYPE="hidden" id="productPrototypeImage_${currentimage.id }" value="${currentimage.imageFullPath}">
											</c:if>
											<c:if test="${imagecount>1}">
												<img src="/images/global/init.gif" width="0" height="0">
												<li>颜色：<SELECT name="styleid" onchange="javascript:styleEvent(this.value)">
												<c:forEach items="${product.styles}" var="style"><c:if test="${style.visible}">
												<option value="${style.id }" <c:if test="${style.id==currentimage.id}">selected </c:if>>${style.name }</option></c:if></c:forEach>
												</SELECT></li>
												<c:forEach items="${product.styles}" var="style"><c:if test="${style.visible}">
												<INPUT TYPE="hidden" id="productImage_${style.id }" value="${style.image140FullPath}">
											    <INPUT TYPE="hidden" id="productPrototypeImage_${style.id }" value="${style.imageFullPath}"></c:if></c:forEach>
											</c:if>
											  
											 </div>
									<ul>
										<li>市场价：<s>${product.marketprice}</s> 元 <font color='#ff6f02'>本站价：<b>${product.sellprice} 元</b></font> 节省：<font color='#ff6f02'>${product.savedPrice }</font> 元										</li>
									  	<li class="right_img"><INPUT TYPE="image" SRC="/images/global/sale.gif"></li>
																				<li class="guopiprice">
										[ <IMG src="/images/global/2j4.gif" border="0">&nbsp;<A href="http://www.babasport.com/cache/news/6/9.shtml" target="_blank">配送说明</A> ]&nbsp;&nbsp;&nbsp;&nbsp;[ <IMG src="/images/global/2j4.gif" border="0">&nbsp;<A href="http://www.babasport.com/cache/news/4/24.shtml" target="_blank">付款方式</A> ]
										</li>
									</ul>									
							</div>
</div>
							<div id="browse_right"><div id="sy_biankuang">
										<div class="sy_xinpintuijian_font">本站尚未开张</div>
										<div class="sy_dianhua" style="line-height:150%"><font color="#FF0000">
											全国：010-6466 3070</font><br>MSN在线客服：babasport@sohu.com<br>	
										<font color="#3A8FAF">QQ在线客服：523429525</font></div>
							</div></div>
</form>
							<div class='right_blank'></div><div class='right_title1'>商品说明</div><div class='right_content'>${product.description}</div>

 </div><!-- 页面主体 右边 end -->
</div><!-- 页面主体 end -->

<jsp:include page="/WEB-INF/page/share/Foot.jsp" />
</body>
</html>
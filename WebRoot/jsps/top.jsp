<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		background: #4682B4; 
	}
	a {
		text-transform:none;
		text-decoration:none;
	} 
	a:hover {
		text-decoration:underline;
	}
</style>
  </head>
  
  <body>
<h1 style="text-align: center;">猫的天空之城</h1>
<div style="font-size: 10pt;">
	<br />
	<c:if test="${not empty user }">
		您好：${user.username }&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="${ctp }/jsps/cart/list.jsp" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="${ctp }/orderServlet?method=findByUser" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="${ctp }/userServlet?method=exitLogin" target="_parent">退出</a>
	</c:if>
	<c:if test="${empty user }">
		<a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">登录</a> |&nbsp; 
		<a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册</a>
	</c:if>
</div>
  </body>
</html>

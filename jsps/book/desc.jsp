<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书详细</title>
    
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
		font-size: 10pt;
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	a {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -70px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	a:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -106px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
  <div>
    <img src="${ctp}/${book.image }" border="0" width="130" height="140" />
  </div>
  <ul>
    <li>书名：${book.bname }</li>
    <li>作者：${book.author }</li>
    <li>单价：${book.price }元</li>
  </ul>
  <c:if test="${book.isdel == 0}">
  <form id="form" action="${ctp }/cartServlet" method="post">
  	<input type="hidden" name="method" value="addCart" />
  	<input type="hidden" name="bid" value="${book.bid }" />
  	<input type="text" size="3" name="count" value="1" />
  </form>
  <a href="javascript:document.getElementById('form').submit();"></a>
  </c:if>
  <c:if test="${book.isdel == 1 }">
  	<h3><font color="red">缺货</font></h3>
  </c:if>
  <h3>${msg }</h3>
  </body>
</html>

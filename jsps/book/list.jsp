<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书列表</title>
    
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
	.icon {
		margin:10px;
		border: solid 2px gray;
		width: 160px;
		height: 180px;
		text-align: center;
		float: left;
	}
</style>
  </head>
  
  <body>
	<c:forEach items="${list }" var="book">
	  <div class="icon">
	    <a href="<c:url value='/bookServlet?method=bookDesc&bid=${book.bid }'/>"><img src="${ctp }/${book.image }" width="130" height="140" /></a>
	      <br/>
	   	<a href="<c:url value='/bookServlet?method=bookDesc&bid=${book.bid }'/>">${book.bname }</a>
	   	<c:if test="${book.isdel == 1 }"><font color="red" >缺货</font></c:if>
	  </div>
  </c:forEach>
  
  </body>
 
</html>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>分类列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {background: rgb(254,238,189);}
	table {font-family: 宋体; font-size: 11pt;   width: 60%;}
</style>
  </head>
  
  <body>
    <h2 style="text-align: center;">分类列表</h2>
    <table align="center" border="1" cellpadding="0" cellspacing="0">
    	<tr id="th" bordercolor="rgb(78,78,78)">
    		<th>分类名称</th>
    		<th>操作</th>
    	</tr>
    	<c:forEach items="${list}" var="c"  >
	    	<tr bordercolor="rgb(78,78,78)">
	    		<td align="center">${c.cname }</td>
	    		<td>
	    		  <a href="<c:url value='adminCategoryServlet?method=edit&cid=${c.cid }'/>">修改</a> |
	    		  <a href="<c:url value='/adminCategoryServlet?method=delete&cid=${c.cid }'/>">删除</a>
	    		</td>
	    	</tr>
    	</c:forEach>
    </table>
  </body>
</html>

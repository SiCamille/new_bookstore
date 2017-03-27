<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
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
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px gray;
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>我的订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">

	<c:if test="${not empty list }">
	<c:forEach items="${list }" var="order" >
		<tr bgcolor="white" bordercolor="gray">
			<td colspan="6">
				订单编号：${order.oid }　成交时间：${order.ordertime }　金额：<font color="red"><b>${order.total }元</b></font>　
				<c:choose>
					<c:when test="${order.state == 1 }">
						<a href="${ctp }/orderServlet?method=findByOid&oid=${order.oid}">付款</a>
						<a href="${ctp }/orderServlet?method=delete&oid=${order.oid}" target="body" >取消订单</a>
					</c:when>
					<c:when test="${order.state == 2 }">等待发货</c:when>
					<c:when test="${order.state == 3 }"><a href="javascript:alert('已确认收货！');">确认收货</a></c:when>
					<c:when test="${order.state == 4 }">订单结束</c:when>
				</c:choose>
			</td>
		</tr>
		<c:forEach items="${order.orderItems }" var="orderitem">
			<tr bordercolor="gray" align="center">
				<td width="15%">
					<div><img src="${ctp }/${orderitem.book.image}" width="60" height="75"/></div>
				</td>
				<td>书名：${orderitem.book.bname }</td>
				<td>单价：${orderitem.book.price }元</td>
				<td>作者：${orderitem.book.author }</td>
				<td>数量：${orderitem.count }</td>
				<td>小计：${orderitem.subtotal }元</td>
			</tr>
			<br />
		</c:forEach>
	</c:forEach>
	</c:if>
	<c:if test="${empty list }">
		<h3>暂无订单,请先去<a href="${ctp }/bookServlet?method=findAll">购物</a></h3>
	</c:if>
</table>
  </body>
</html>

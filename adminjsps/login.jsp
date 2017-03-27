<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理员登录页面</title>
	<script type = "text/javascript">
		window.onload = function() {
			var username = document.getElementById("username");
			var password = document.getElementById("password");
			var code = document.getElementById("code");
			username.onfocus = function() {
				if(username.placeholder == "请输入用户名") {
					username.placeholder = "";
				}
			};
			username.onblur = function() {
				if(username.value == "") {
					username.placeholder = "请输入用户名";
				}
			};
			password.onfocus = function() {
				if(password.placeholder == "请输入密码") {
					password.placeholder = "";
				}
			};
			password.onblur = function() {
				if(password.value == "") {
					password.placeholder = "请输入密码";
				}
			};
		};
	
		function check() {
			freeSpan();
			return (checkNull("username") && checkNull("password") && checkNull("code"));
		}
		
		function checkNull(temp) {
			var str = "";
			if(temp == "username") {
				str = "用户名";
			} else if(temp == "password") {
				str = "密码";
			}
			var tempEvent = document.getElementById(temp);
			var regex = /^\s*$/;
			if(regex.test(tempEvent.value)) {
				document.getElementById(temp + "_msg").innerHTML = "&nbsp;&nbsp;&lt;--&nbsp;" + str + "不能为空";
				return false;
			} else {
			return true;
			}
		}
		
		function freeSpan() {
			var spanArr = document.getElementsByTagName("span");
			for(var i = 0 ; i < spanArr.length ; i++) {
				spanArr[i].innerHTML = "";
			}
		}
		
	</script>
	<link rel = "stylesheet" type = "text/css" href = "${ctp }/sum.css">
	<style type="text/css">
		.enter {
			width:310px;
		}
		
		body {
			padding:0;
			background-image: url(../images/star.jpg);
		}
		
		.copy {
			margin: 180px 0px 0px 0px;
		}
	</style>
</head>


<body class="loginBack">
	<%-- 2016-12-19 13:45:22 --%>
	<h1 class = "welcome" align="center">大人,欢迎回来</h1>
	<div class = "back">
		<form action="${ctp }/adminUserServlet" method="post" onsubmit="return check()" >
			<input type="hidden" name="method" value="login" />
			<div class = "loginDiv">
				<input type = "text" id = "username" class="password" name = "username" placeholder = "请输入用户名" >
			</div>
			<div class = "loginDiv">
				<input type = "password" id = "password" class="username" name = "password" placeholder = "请输入密码" >
			</div>
			<div class = "loginDiv">
				<input type = "submit" value = "进入" class = "enter">
			</div>
			<div class = "mistake_msg">${msg}</div>
			<div class = "copy">&copy;2016 Camille All Rights Revered.</div>
		</form>
	</div>
</body>
</html>
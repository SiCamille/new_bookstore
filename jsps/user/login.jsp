<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel = "stylesheet" type = "text/css" href = "${ctp }/sum.css" />
<title>CAMILLE书城</title>
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
			code.onfocus = function() {
				if(code.placeholder == "请输入右侧验证码") {
					code.placeholder = "";
				}
			};
			code.onblur = function() {
				if(code.value == "") {
					code.placeholder = "请输入右侧验证码";
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
			} else {
				str = "验证码";
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
		
		function change() {
			document.getElementById("img").src = "${ctp}/imgCode?time="
				+ new Date().getTime();
		}
		
		function registerClick() {
			window.location.href="${ctp}/jsps/user/regist.jsp";
		}
		
	</script>
	<style type="text/css">
		body {
			background-image: url(../../images/star.jpg);
		}
	</style>
</head>
<body class="loginBack">
	<%-- 2016-11-30 8:23:14 --%>
	<h1 class = "welcome" align="center">欢迎来到猫的天空之城</h1>
	<div class = "back">
		<form action="${ctp }/userServlet" method="post" onsubmit="return check()" >
			<input type="hidden" name="method" value="login" />
			<div class = "loginDiv">
				<input type = "text" id = "username" class="password" name = "username" placeholder = "请输入用户名" >
				<span id = "username_msg" ></span>
			</div>
			<div class = "loginDiv">
				<input type = "password" id = "password" class="username" name = "password" placeholder = "请输入密码" >
				<span id = "password_msg" ></span>
			</div>
			<div class = "loginDiv" id = "codeDiv">
				<input type = "text" class = "code" id = "code" name = "code" placeholder = "请输入右侧验证码">
				<img src = "${ctp }/imgCode" class = "img" id = "img" style="vertical-align: middle;" onclick = "change()" />
				<span id = "code_msg" >&nbsp;&nbsp;&lt;--亲,点击图片可更换验证码哦</span>
			</div>
			<div class = "loginDiv">
				<input type = "submit" value = "进入" class = "enter">
				<input type = "button" value = "注册" class = "register" onclick="registerClick()" >
			</div>
			<div class = "mistake_msg">${msg}</div>
			<div class = "copy">&copy;2016 Camille All Rights Revered.</div>
		</form>
	</div>
</body>
</html>
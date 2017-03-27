<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../taglibs.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 对用户名进行异步检查 -->
	<script type="text/javascript">
		
	window.onload = function() {
		var username = document.getElementById("registerUsername");
		var password = document.getElementById("registerPassword");
		var rePassword = document.getElementById("reRegisterPassword");
		var code = document.getElementById("registerCode");
		var email = document.getElementById("registerName");
		
		username.onfocus = function() {
			if(username.placeholder == "请输入用户名(必填)") {
				username.placeholder = "";
			}
		};
		username.onblur = function() {
			if(username.value == "") {
				username.placeholder = "请输入用户名(必填)";
			} else {
				checkUsername();
			}
		};
		password.onfocus = function() {
			if(password.placeholder == "请输入密码(必填)") {
				password.placeholder = "";
			}
		};
		password.onblur = function() {
			if(password.value == "") {
				password.placeholder = "请输入密码(必填)";
			}
		};
		rePassword.onfocus = function() {
			if(rePassword.placeholder == "请确认密码(必填)") {
				rePassword.placeholder = "";
			}
		};
		rePassword.onblur = function() {
			if(rePassword.value == "") {
				rePassword.placeholder = "请确认密码(必填)";
			}
		};
		email.onblur = function() {
			if(email.value == "") {
				email.placeholder = "请输入邮箱(必填)";
			}
		};
		email.onfocus = function() {
			if(email.placeholder == "请输入邮箱(必填)") {
				email.placeholder = "";
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
		if(checkEmail()) {
			return checkNull("registerUsername") && checkNull("registerPassword") && 
					checkNull("reRegisterPassword") && checkNull("reGisterCode") && 
					checkNull("registerName") && checkEqual();
		} else {
			return false;
		}
	}
	
	function checkEqual() {
		var password = document.getElementById("registerPassword").value;
		var rePassword = document.getElementById("reRegisterPassword").value;
		alert(password);
		alert(rePassword);
		if(password == rePassword) {
			return true;
		}
		document.getElementById("reRegisterPassword_msg").innerHTML = "&nbsp;&nbsp;&lt;==&nbsp;密码输入不一致";
		return false;
	}
	
	function checkEmail() {
		var email = document.getElementById("registerName").value;
		var rex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(rex.test(email)) {
			return true;
		} else {
			document.getElementById("registerName_msg").innerHTML = "&nbsp;&nbsp;&lt;--&nbsp;邮箱格式有误";
			return false;
		}
	}
	
	function checkNull(temp) {
		var str = "";
		if(temp == "registerUsername") {
			str = "用户名";
		} else if(temp == "registerPassword") {
			str = "密码";
		} else if(temp == "reRegisterPassword") {
			str = "确认密码";
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
		document.getElementById("reImg").src = "${ctp}/imgCode?time="
			+ new Date().getTime();
	}
	
	function registerGoBack() {
		window.location.href="${ctp}/jsps/user/login.jsp";
	}
	
	function checkUsername() {
		var xhr = getXMLHttpRequest();
		var username = document.getElementById("registerUsername").value;
		var span1 = document.getElementById("registerUsername_msg");
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200) {
				var data = xhr.responseText;
				if(data == 0) {
					span1.innerHTML = "<font color='green'>用户名可以使用</font>";
				} else if(data == 1) {
					span1.innerHTML = "<font color='red'>用户名已被占用</font>";
				}
			}
		}
		xhr.open("GET","${ctp}/userServlet?method=checkUsername&username=" + username, true);
		xhr.send(null);
	}

	function getXMLHttpRequest() {
		var xmlHttpRequest;
		try{
			xmlHttpRequest = new XMLHttpRequest();
		} catch(e) {
			try{
				xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e) {
				try{
					xmlHttpRequest = new ActiveXObject("Misrosoft.XMLHTTP");
				} catch(e) {
					
				}
			}
		}
		return xmlHttpRequest;
	}
	</script>
	<link rel = "stylesheet" type = "text/css" href = "${ctp }/sum.css">
	<style type="text/css">
		body {
			background-image: url(../../images/star.jpg);
		}
		
		.copy {
			margin: -30px 0px 0px 0px;
		}
		
		.welcome {
			margin: 20px 0px 0px 0px;
		}
	</style>
  </head>
  
  <body class="loginBack">
<%--   <h1>注册</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="${pageContext.request.contextPath }/userServlet" method="post">
	<input type="hidden" name="method" value="regist"/>
	用户名：<input type="text" name="username" id="username" value="" onblur="checkUsername()" /><span id="span1"></span><br/>
	密　码：<input type="password" name="password"/><br/>
	邮　箱：<input type="text" name="email" value=""/><br/>
	<div id="d1"><input type="submit" value="注册"/></div>
</form> --%>
<h1 class = "welcome" align="center">加入我们吧!</h1>
	<div class = "back">
		<form action="${ctp }/userServlet" method="post" onsubmit="return check()" >
		<input type="hidden" name="method" value="regist" />
			<div class = "loginDiv">
				<input type = "text" class = "username" id = "registerUsername" name = "username" placeholder = "请输入用户名(必填)"  />
				<span id = "registerUsername_msg" ></span>
			</div>
			<div class = "loginDiv">
				<input type = "password" class = "password" id = "registerPassword" name = "password" placeholder = "请输入密码(必填)" >
				<span id = "registerPassword_msg" ></span>
			</div>
			<div class = "loginDiv"><input type = "password" class = "password" 
				id = "reRegisterPassword" name = "rePassword" placeholder = "请确认密码(必填)">
				<span id = "reRegisterPassword_msg" ></span>
			</div>
			<div class = "loginDiv">
				<input type = "text" name="email" class = "name" id = "registerName" placeholder = "请输入邮箱(必填)">
				<span id = "registerName_msg" ></span>
			</div>
			<div class = "loginDiv" class = "codeDiv">
				<input type = "text" class = "code" id = "registerCode" name = "code" placeholder = "请输入右侧验证码">
				<img src = "${ctp }/imgCode" class = "img" id = "reImg" style="vertical-align: middle;" onclick = "change()">
				<span id = "registerCode_msg"  >&nbsp;&nbsp;&lt;--亲,点击图片可更换验证码哦</span>
			</div>
			<div class = "loginDiv">
				<input type = "submit" value = "注册" class = "enter">
				<input type = "button" value = "返回" class = "goBack" onclick = "registerGoBack()" />
			</div>
			<div class = "mistake_msg">${msg}</div>
			<div class = "copy">&copy;2016 Camille All Rights Revered.</div>
		</form>
	</div>
  </body>
</html>

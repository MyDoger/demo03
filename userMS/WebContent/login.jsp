<%@ page language="java"    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="js/jquery-3.4.1.js"></script>
<script>
	$(document).ready(function(){
		$("#reg").click(function(){
				location.href="reg.jsp"
			});
	});
	
</script>
</head>
<body>
<h3>User Login</h3>
<form action="user.do?method=login" method="post">
登录名：<input name="uname"><br>
密码：<input type="password" name="upwd"></br>
<button>login</button>
<button type="reset" >reset</button>
<button type="button" id="reg">reg</button>
</form>
<font color="red">${message}</font>
</body>
</html>
<%@ page language="java"    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="js/jquery-3.4.1.js"></script>
<script>
	$(document).ready(function(){
		
	});
	
</script>
</head>
<body>
<h3>User Register</h3>
<form action="user.do?method=reg" method="post">
登录名：<input id="uname"><br>
密码：<input type="password" id="upwd"></br>
<button>reg</button>
<button type="reset" >reset</button>

</form>
</body>
</html>
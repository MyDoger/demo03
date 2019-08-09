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
<h3>User Edit</h3>
<form action="user.do?method=UserEdit" method="post">
<input type="hidden" name="uid" value="${u.uid }">
登录名：<input name="uname" value="${u.uname }"><br>
密码：<input type="password" name="upwd" value="${u.uid }"></br>
<button>edit</button>
<button type="reset" >reset</button>
</form>
</body>
</html>
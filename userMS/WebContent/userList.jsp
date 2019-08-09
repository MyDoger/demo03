<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		$(".delete").click(function(){
			var sel = window.confirm("是否删除该用户?");
			if(sel){
				$(location).attr("href","user.do?method=userDel&uid="+$(this).val());
			}
		});
		$(".edit").click(function(){
		
				$(location).attr("href","user.do?method=userGet&uid="+$(this).val());
			
		});
	});

</script>
</head>
<body>
<table width="800" border="1">
<caption><h3>用户信息管理</h3></caption>
	<tr>
		<th>num</th>
		<th>uname</th>
		<th>upwd</th>
		<th>edit</th>
		<th>delete</th>
	</tr>
	<c:forEach items="${userList}" var="u"  varStatus="i">
		<tr>
			<th>${i.index+1}</th>
			<th>${u.uname}</th>
			<th>${u.upwd}</th>
			<th><button type="button" class="edit" value="${u.uid }">edit</button></th>
			<th><button type="button" class="delete" value="${u.uid }">delete</button></th>
		</tr>
	</c:forEach>
</table>
</body>
</html>
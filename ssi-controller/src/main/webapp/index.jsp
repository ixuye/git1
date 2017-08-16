<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery/jquery-1.10.2.js"></script>
</head>
<body>
		<a href="<%=request.getContextPath() %>/queryUserInfo.jhtml">展示用户信息</a><p/>
		<form id="loginCheckForm" method="post">
		用户名:<input type="text" name="userName"><br>
		密码:<input type="password" name="userPassword"><br>
		<input type="button" id="login" value="登录">
		<input type="button" id="register" value="注册">
		
		</form>
		<script type="text/javascript">
			$(function(){
				$("#login").click(function(){
					$.ajax({
						type:"post",
						url:"<%=request.getContextPath()%>/checkUserInfo.jhtml",
						data:$("#loginCheckForm").serialize(),
						datatype:"json",
						success:function(result){
							location.href="<%=request.getContextPath()%>/queryUserInfo.jhtml";
						}
					})
				})
			})
		
		</script>
</body>
</html>
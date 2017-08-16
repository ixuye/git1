<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form id="addMenuForms">
	<input type="hidden" name="menuID" value="${menu.id }">
		菜单模块:<select name="pid">
			<option value="0">新建模块</option>
			<c:forEach items="${menuFirstList }" var="m">
				<option value="${m.id }" ${menu.pid==m.id?'selected':'' }>${m.name }</option>
			</c:forEach>
		</select><br>
		菜单名称:<input name="menuName" value="${menu.name }"><br>
		菜单链接:<input name="url" value="${menu.url }"><br>
		菜单类型：<input name="menuType" type="radio" value="0" checked="checked">常规功能
		<input name="menuType" type="radio" value="1">ajax请求
		<input name="menuType" type="radio" value="2">基本功能
		<input name="menuType" type="radio" value="3">按钮功能<br>
	</form>

</body>
</html>
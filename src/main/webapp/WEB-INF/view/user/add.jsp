<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add 用户</title>
</head>
<body>

<!-- 此时没有写action，直接提交给/add>  -->
<!--  br 服务器端验证 -->
<sf:form method ="post" modelAttribute="user" enctype="multipart/form-data">
	Username:<sf:input path ="username"/><sf:errors path="username"/><br/>
	Password:<sf:password path="password"/><sf:errors path="password"/><br/>
	Nickname:<sf:input path="nickname"/><br/>
	Email:	<sf:input path="email"/><sf:errors path="email"/><br/>
<!-- 	Attach:<input type="file" name="attachs"><br/>	
	Attach:<input type="file" name="attachs"><br/>	
	Attach:<input type="file" name="attachs"><br/>	
 -->			
	<input type="submit" value="添加用户"><br/>
</sf:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css" type="text/css">
</head>
<body>
<br>
<a href="add">添加</a><br>  ---${loginUser.nickname}
<table width = "700" align="center" border="1">
	<tr>
	<td>用户标识</td><td>用户名</td><td>用户昵称</td><td>密码</td><td>邮箱</td>
	</tr>
	
	<tr>
	<c:if test="${pages.total le 0}">
	<tr>
	<td colspan="6">目前还没有数据</td>
	</tr>
	</c:if>
	<c:if test="${pages.total gt 0}">
	<c:forEach items="{pages.datas}" var = "u">	
	<tr>
	<td>${u.id}</td><td>${u.username}</td><td>${u.nickname}</td>
	<td>${u.password}</td><td>${u.emal}</td><td><a href = "">更新</a></td>
	<td><a href="">删除</a></td>
	</tr>
	</c:forEach>
	
	<tr>
	<td colspan="6">
	<jsp:include page="/WEB-INF/inc/pager.jsp">
		<jsp:param value="user_list.action" name="url"/>
		<jsp:param value="${us.totalRecord}" name="items"/>
	</jsp:include>
	</td>
	</tr>
	</c:if>

	</table>
	
	<!--  <c:forEach items="${users}" var ="um" >
	 ${um.value.username} 
----<a href="${um.value.username}">${um.value.nickname}</a>
---- ${um.value.password} 
---- ${um.value.email} 
	 <a href="${um.value.username}/update">修改</a>
	  <a href="${um.value.username}/delete">删除</a><br>
	  </c:forEach>	 -->
	  
	  
</body>
</html>
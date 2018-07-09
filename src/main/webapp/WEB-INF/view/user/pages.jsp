<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <script type="text/javascript">
 
        function validate()
        {
            var pageBean = document.getElementsByName("pageBean")[0].value;          
            if(pageBean > <s:property value="#request.pageBean.tp"/>)
            {
                alert("你输入的页数大于最大页数，页面将跳转到首页！");   
                window.document.location.href = "PlaecAction";   
                return false;
            }        
            return true;
        }
    </script>
  </head>
  
  <body>
    <br>
    <h3 align="center">景区列表</h3>
<table border="1" width="70%" bordercolor="yellow" align="center">
	<tr>		
		<th>姓名</th>
		<th>密码</th>
		<th>昵称</th>
		<th>邮箱</th>
	</tr>
    <c:forEach items="${pageBean.list}" var="user">
	<tr>
		<td>${user.username}</td>
		<td>${user.password}</td>
		<td>${user.nickname}</td>
		<td>${user.email} </td>
	</tr>
</c:forEach>
   <tr>
            <td colspan="6" align="center" bgcolor="#5BA8DE">共${pageBean.totalRecords}条记录 共${pageBean.totalPages}页 当前第${pageBean.pageNo}页<br/>                
                <a href="${pageContext.request.contextPath}/user/pages.do?pageNo=${pageBean.topPageNo}"><input type="button" name="fristPage" value="首页" /></a>
                <c:choose>
                  <c:when test="${pageBean.pageNo!=1}">
                    
                      <a href="${pageContext.request.contextPath}/user/pages.do?pageNo=${pageBean.previousPageNo}"><input type="button" name="previousPage" value="上一页" /></a>
                    
                  </c:when>
                  
                  <c:otherwise>                    
                      <input type="button" disabled="disabled" name="previousPage" value="上一页" />                 
                  </c:otherwise>
                  
                </c:choose>
                
                <c:choose>
                  <c:when test="${pageBean.pageNo != pageBean.totalPages}">
                    <a href="${pageContext.request.contextPath}/user/pages.do?pageNo=${pageBean.nextPageNo}"><input type="button" name="nextPage" value="下一页" /></a>
                  </c:when>
                  <c:otherwise>
                 
                      <input type="button" disabled="disabled" name="nextPage" value="下一页" />
                   
                  </c:otherwise>
                </c:choose>
                <a href="${pageContext.request.contextPath}/user/pages.do?pageNo=${pageBean.bottomPageNo}"><input type="button" name="lastPage" value="尾页" /></a>
            </td>
   </tr>
  </table>
  </body>
</html>

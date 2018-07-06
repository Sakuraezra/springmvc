<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
            var page = document.getElementsByName("page")[0].value;          
            if(page > <s:property value="#request.pageBean.tp"/>)
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
    </table>
    <br/>
    共[<B>${pageBean.totalCount}</B>]条记录,共[<B>${pageBean.totalPage}</B>]页，当前第[<b>${pageBean.currentPage}</b>]页
    
    <!--  
    		前一页: 当前页-1
    		如果第一页 没有前一页
     -->    
     <c:if test="${pageBean.currentPage!=1}">
    [<A href ="${pageContext.request.contextPath}/place_listpage.action?currentPage=${pageBean.currentPage-1}">上一页</A>]
    </c:if>
     <!--  
    		后一页： 当前页+1
    		如果最后最后一页 没有后一页
     -->
      <c:if test="${pageBean.currentPage!=pageBean.totalPage}">
     [<A href ="${pageContext.request.contextPath}/place_listpage.action?currentPage=${pageBean.currentPage+1}">下一页</A>]
     
  	  </c:if>
  	  
  	     <a href="${pageContext.request.contextPath}/jsp/travel.jsp">返回</a>
     <%-- <center>
    
        <font size="5">共<font color="red"><s:property value="#request.pageBean.tp"/></font>页 </font>&nbsp;&nbsp;
        <font size="5">共<font color="red"><s:property value="#request.pageBean.tr"/></font>条记录</font><br><br>
        
       <s:if test="#request.pageBean.pc == 1">
            首页&nbsp;&nbsp;&nbsp;上一页
        </s:if>
        
        <s:else>
            <a href="PlaceAction_page.action">首页</a>
            &nbsp;&nbsp;&nbsp;
            <a href="personAction_page.action?page=<s:property value="#request.pageBean.pc - 1"/>">上一页</a>
        </s:else>
        
        <s:if test="#request.pageBean.pc != #request.pageBean.tp">
            <a href="personAction.action?page=<s:property value="#request.pageBean.pc + 1"/>">下一页</a>
            &nbsp;&nbsp;&nbsp;
            <a href="personAction.action?page=<s:property value="#request.pageBean.tp"/>">尾页</a>
        </s:if>
        
        <s:else>
            下一页&nbsp;&nbsp;&nbsp;尾页
        </s:else>
    
    </center><br>
    
    <center>
        
        <form action="personAction" onsubmit="return validate();">
            <font size="4">跳转至</font>
            <input type="text" size="2" name="page">页
            <input type="submit" value="跳转">
        </form>
        
    </center>
     --%>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath}/servlet/TestServlet" method="GET">
		用户名：<input type="text" name="username"><br>
		地址:<input type="text" name="addr"><br>
		<input type="submit" value="提交">    
    </form>
  </body>
</html>

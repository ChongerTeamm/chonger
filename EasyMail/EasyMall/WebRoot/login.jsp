<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${app}/css/login.css"/>
		<title>EasyMall欢迎您登录</title>
	</head>
	<body>
		<%-- <%
			// 获取用户携带的所有Cookie
			Cookie[] cs=request.getCookies();
			// 找到remname的Cookie
			Cookie findC=null;
			if(cs!=null){
				for(Cookie c:cs){
					if("remname".equals(c.getName())){
						findC=c;
					}
				}
			}
			// 将remname的Cookie的值添加到username的input中
			String username="";//用来保存用户名的变量
			if(findC!=null){
				// 使用remname的Cookie的值给username赋值				
				username=findC.getValue();
				// 利用URLDecorder将%E5%BC%A0%E9%A3%9E变成张飞
				username=URLDecoder.decode(username,"utf-8");
			}
		%> --%>
		<h1>欢迎登录EasyMall</h1>
		<form action="${app}/LoginServlet" method="POST">
			<table>
				<tr>
					<td colspan="2" style='text-align:center;color:red'>
						${requestScope.msg}
					</td>
				</tr>
				<tr>
					<td class="tdx">用户名：</td>
					<td><input type="text" name="username" value="${cookie.remname.value}"/></td>
				</tr>
				<tr>
					<td class="tdx">密&nbsp;&nbsp; 码：</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="remname" value="true"
							${(empty cookie.remname)?"":"checked='checked'" }
						/>记住用户名
						<input type="checkbox" name="autologin" value="true"/>30天内自动登录
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="登录"/>
					</td>
				</tr>
			</table>
		</form>		
	</body>
</html>


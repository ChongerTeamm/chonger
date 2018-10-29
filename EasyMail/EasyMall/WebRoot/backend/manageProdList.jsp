<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
  	<style type="text/css">
  		body{
  			text-align: center;
  		}
		table {
			text-align: center;
		}
		th{
			background-color: silver;
		}
  	</style>
  	<script type="text/javascript" src="${app}/js/jquery-1.4.2.js"></script>
  	<script type="text/javascript">
  		function changeNum(inp,pid){
  			// 获取对应input的value属性的值-》修改后的商品数量
  			var val=inp.value;
  			var url="${app}/ManageUpdatePNumServlet";
  			// 发送AJAX请求，修改商品数量
  			$.post(url,{"pid":pid,"pnum":val},function(result){
  				alert(result);
  			});
  		}
  	</script>
  	
  	
  </head>
  <body>
  	<h1>商品管理</h1>
  	<a href="${pageContext.request.contextPath }/backend/manageAddProd.jsp">添加商品</a>
  	<hr>
  	<table align="center" bordercolor="black" border="1" width="90%" cellspacing="0px" cellpadding="5px">
  	<tr>
  		<th>商品图片</th>
  		<th>商品id</th>
  		<th>商品名称</th>
		<th>商品种类</th>
		<th>商品单价</th>
		<th>库存数量</th>
		<th>描述信息</th>
		<th>操&nbsp;作</th>
  	</tr>
  	<c:forEach items="${requestScope.list }" var="prod" >
  		<tr>
  			<td><img width="120px" height="120px" src="${app}/ProdImageServlet?imgurl=${prod.imgurl}"/></td>
  			<td>${prod.id }</td>
  			<td>${prod.name }</td>
  			<td>${prod.cname }</td>
  			<td>${prod.price }</td>
  			<td><input type="text" value="${prod.pnum }" style="width:40px" onBlur="changeNum(this,${prod.id})"></td>
  			<td>${prod.description }</td>
  			<td><a href="${app}/ManageDelProdServlet?pid=${prod.id}" style="text-decoration: none;">删除</a></td>
  		</tr>
  	</c:forEach>
  	</table>
  </body>
</html>

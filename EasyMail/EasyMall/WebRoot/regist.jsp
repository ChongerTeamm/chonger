<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>欢迎注册EasyMall</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${app}/css/regist.css"/>
		<script type="text/javascript" src="${app}/js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
			var formObj={
				//添加checkForm方法
				"checkForm":function(){
					var flag=true;//控制表单是否提交的变量
					// 非空验证
					flag=this.checkNull("username","用户名不能为空")&&flag;
					flag=this.checkNull("password","密码不能为空")&&flag;
					flag=this.checkNull("password2","确认密码不能为空")&&flag;
					flag=this.checkNull("nickname","昵称不能为空")&&flag;
					flag=this.checkNull("email","邮箱不能为空")&&flag;
					flag=this.checkNull("valistr","验证码不能为空")&&flag;
					// 两次密码一致验证
					flag=this.checkPassword("password","两次密码应该一致")&&flag;
					// 邮箱格式验证
					flag=this.checkEmail("email","邮箱格式不正确")&&flag;
					return flag;
				},
				"checkEmail":function(name,msg){
					// 拿到邮箱input的值
					var email=$("input[name='"+name+"']").val();
					if($.trim(email)!=""){
						//当邮箱不为空时进行格式的验证
						var regex=/^\w+@\w+(\.\w+)+$/;
						if(!regex.test(email)){
							// 邮箱格式不符合正则表达式，提示错误信息，返回false
							this.setMsg(name,msg);
							return false;
						}else{
							this.setMsg(name,"");
							return true;
						}
					}
					// 默认返回false，说明这项验证没有通过
					return false;
				},
				"checkPassword":function(name,msg){
					// 验证的是密码和确认密码的内容一致
					var password=$("input[name='"+name+"']").val();
					var password2=$("input[name='"+name+"2']").val();
					if($.trim(password)!=""&&$.trim(password2)!=""){
						//当且仅当密码和验证密码都不为空时，再进行是否一致的判断
						if(password!=password2){
							//两次密码不一致，设置错误提示信息并返回false
							this.setMsg(name+"2",msg);
							return false;
						}else{
							this.setMsg(name+"2","");
							return true;
						}
					}
					// 默认当前验证没有通过
					return false;
				},
				"checkNull":function(name,msg){
					//通过jQuery获取传入的name属性指定的input输入框的值
					var value=$("input[name='"+name+"']").val();
					// 判断该input输入框的值是否为空
					if($.trim(value)==""){
						// 如果为空，则设置提示消息
						this.setMsg(name,msg);
						// 如果为空，返回false,表单不提交
						return false;
					}else{
						// 当前input不为空，取消之前设置的提示消息
						this.setMsg(name,"");
						return true;
					}
				},
				"setMsg":function(name,msg){
					// 获取name指定的input后面的span，并设置其内容
					$("input[name='"+name+"']").nextAll("span").html(msg).css("color","red");
				}
			};

			//为当前文档添加一个就绪事件
			$(function(){
				// 为每一个input添加一个失去焦点的事件
				$("input[name='password']").blur(function(){
					// 执行非空验证
					formObj.checkNull("password","密码不能为空");
				});
				$("input[name='password2']").blur(function(){
					// 执行非空验证
					formObj.checkNull("password2","确认密码不能为空");
					formObj.checkPassword("password","两次密码应该一致");
				});
				$("input[name='nickname']").blur(function(){
					// 执行非空验证
					formObj.checkNull("nickname","昵称不能为空");
				});
				$("input[name='email']").blur(function(){
					// 执行非空验证
					formObj.checkNull("email","邮箱不能为空");
					formObj.checkEmail("email","邮箱格式不正确");
				});
				$("input[name='valistr']").blur(function(){
					// 执行非空验证
					formObj.checkNull("valistr","验证码不能为空");
				});
				
				// 为验证码图片标签添加一个click事件
				$("#vali_img").click(function(){
					// 每次点击，获取当前事件的毫秒值
					var dateStr=new Date().getTime();
					// 修改组件的src属性的值
					$(this).attr("src","/EasyMall/ValiImageServlet.action?time="+dateStr);
				});
				
				
				// 为username输入框添加失去鼠标焦点的事件
				$("input[name='username']").blur(function(){
					// 执行非空验证
					var username=$(this).val();
					var flag=formObj.checkNull("username","用户名不能为空");
					// flag为true，说明username不为空
					if(flag){
						// 发送一个AJAX请求
						var url="${app}/AjaxCheckUsernameServlet";
						// 获取当前input框中的值
						// $(selector).load，会将返回的内容直接添加到selector内部
						//$("#msg_username").load(url,{"username":username});
						// $.get(url,data,callback)
						// function就是callback，当页面收到服务器返回的应答后，会自动调用
						// function方法，其参数result，就是服务器返回的内容
						$.get(url,{"username":username},function(result){
							$("#msg_username").html(result);
							// alert(result);
						});
					}
				});
				
			});
		</script>
	</head>
	<body>
		<form action="${app}/RegistServlet" method="POST"  onSubmit="return formObj.checkForm()">
			<h1>欢迎注册EasyMall</h1>
			<table>
				<tr>
					<td colspan="2" style="text-align: center;color: red">
						${requestScope.msg}
					</td>
				</tr>
				<tr>
					<td class="tds">用户名：</td>
					<td>
						<input type="text" name="username" value="${param.username}" />
						<span id="msg_username"></span>
					</td>
				</tr>
				<tr>
					<td class="tds">密码：</td>
					<td>
						<input type="password" name="password"/><span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">确认密码：</td>
					<td>
						<input type="password" name="password2"/><span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">昵称：</td>
					<td>
						<input type="text" name="nickname" value="${param.nickname}"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">邮箱：</td>
					<td>
						<input type="text" name="email" value="${param.email}" />
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">验证码：</td>
					<td>
						<input type="text" name="valistr" value="${param.valistr}"/>
						<img id="vali_img" src="${app}/ValiImageServlet.action" width="" height="" alt="" /><span></span>
					</td>
				</tr>
				<tr>
					<td class="sub_td" colspan="2" class="tds">
						<input type="submit" value="注册用户"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

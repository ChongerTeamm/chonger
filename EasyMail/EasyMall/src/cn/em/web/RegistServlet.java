package cn.em.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.em.dao.UserDaoImpl;
import cn.em.domain.User;
import cn.em.factory.BaseFactory;
import cn.em.factory.UserServiceFactory;
import cn.em.service.UserService;
import cn.em.service.UserServiceImpl;
import cn.em.util.JDBCUtils;
import cn.em.util.MD5Util;
import cn.em.util.WebUtils;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 处理乱码的工作已经在EncodingFilter中完成
		// 1. 处理乱码
		// 请求乱码 POST
		//req.setCharacterEncoding("utf-8");
		// 处理应答乱码
		//resp.setContentType("text/html;charset=utf-8");
		
		// 2. 接收表单参数
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String password2=req.getParameter("password2");
		String nickname=req.getParameter("nickname");
		String email=req.getParameter("email");
		String valistr=req.getParameter("valistr");
		// 3. 表单验证
		// 首先验证验证码
		if(WebUtils.isEmpty(valistr)){
			// 验证出问题-向request作用域添加错误提示信息
			req.setAttribute("msg", "验证码不能为空");
			// 将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			//转发之后的代码会继续执行,添加return让后面的代码不执行
			return;
		}else{
			//进行验证码是否正确的验证
			String code=(String) req.getSession().getAttribute("code");
			if(!valistr.equalsIgnoreCase(code)){
				req.setAttribute("msg", "验证码不正确");
				// 将请求转发给regist.jsp
				req.getRequestDispatcher("/regist.jsp").forward(req, resp);
				//转发之后的代码会继续执行,添加return让后面的代码不执行
				return;
			}
		}

			//1) 非空验证
		if(WebUtils.isEmpty(username)){
			// 验证出问题-向request作用域添加错误提示信息
			req.setAttribute("msg", "用户名不能为空");
			// 将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			//转发之后的代码会继续执行,添加return让后面的代码不执行
			return;
		}
		if(WebUtils.isEmpty(password)){
			// 验证出问题-向request作用域添加错误提示信息
			req.setAttribute("msg", "密码不能为空");
			// 将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			//转发之后的代码会继续执行,添加return让后面的代码不执行
			return;
		}
		if(WebUtils.isEmpty(password2)){
			// 验证出问题-向request作用域添加错误提示信息
			req.setAttribute("msg", "确认密码不能为空");
			// 将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			//转发之后的代码会继续执行,添加return让后面的代码不执行
			return;
		}
		if(WebUtils.isEmpty(nickname)){
			// 验证出问题-向request作用域添加错误提示信息
			req.setAttribute("msg", "昵称不能为空");
			// 将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			//转发之后的代码会继续执行,添加return让后面的代码不执行
			return;
		}
		if(WebUtils.isEmpty(email)){
			// 验证出问题-向request作用域添加错误提示信息
			req.setAttribute("msg", "邮箱不能为空");
			// 将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			//转发之后的代码会继续执行,添加return让后面的代码不执行
			return;
		}
		
			//2) 两次密码一致验证
		if(!password.equals(password2)){
			req.setAttribute("msg", "两次密码应该一致");
			// 将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			//转发之后的代码会继续执行,添加return让后面的代码不执行
			return;
		}
			//3) 邮箱格式验证
		// 123@tedu.com.cn
		// \w+@\w+(\.\w+)+
		String regex="^\\w+@\\w+(\\.\\w+)+$";
		if(!email.matches(regex)){
			req.setAttribute("msg", "邮箱格式不正确");
			// 将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			//转发之后的代码会继续执行,添加return让后面的代码不执行
			return;
		}
			//4) 用户名不重复验证-使用用户名查数据库
		UserService service=BaseFactory.getFactory().getInstance(UserService.class);
		boolean hasUsername=service.hasUsername(username);
		if(hasUsername){
			req.setAttribute("msg", "用户名已存在");
			// 将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			//转发之后的代码会继续执行,添加return让后面的代码不执行
			return;
		}
	
		// 4. 执行业务逻辑-添加数据到数据库
		// 先对密码进行MD5加密
		password=MD5Util.md5(password);
		
		// 将散装的表单数据封装成User对象
		// id给-1是因为后面的sql语句中用不到该属性，给多少都行
		User user=new User(-1, username, password, nickname, email);
		boolean flag=service.registUser(user);
		if(flag){
			//注册成功-》提示成功，3秒后定时刷新首页
			resp.getWriter().write("<h1 style='text-align:center;color:red'>恭喜您，注册成功，3秒后自动跳转首页</h1>");
			// 如果当前应用是默认web应用，req.getContextPath返回空字符串
			resp.setHeader("refresh", "3;url="+req.getContextPath()+"/index.jsp");
		}else{
			req.setAttribute("msg", "注册失败，请稍后重试");
			// 将请求转发给regist.jsp
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
		}
		
		
		
		
//		String sql2="insert into user values(null,?,?,?,?)";
//		Connection conn2=null;
//		PreparedStatement ps2=null;
//		try {
//			conn2=JDBCUtils.getConn();
//			ps2=conn2.prepareStatement(sql2);
//			ps2.setString(1, username);
//			ps2.setString(2, password);
//			ps2.setString(3, nickname);
//			ps2.setString(4, email);
//			int i=ps2.executeUpdate();
//			if(i>0){//插入成功
//				// 5. 根据业务逻辑执行结果，返回对应的页面
//				//注册成功-》提示成功，3秒后定时刷新首页
//				resp.getWriter().write("<h1 style='text-align:center;color:red'>恭喜您，注册成功，3秒后自动跳转首页</h1>");
//				// 如果当前应用是默认web应用，req.getContextPath返回空字符串
//				resp.setHeader("refresh", "3;url="+req.getContextPath()+"/index.jsp");
//			}else{
//				req.setAttribute("msg", "注册失败，请稍后重试");
//				// 将请求转发给regist.jsp
//				req.getRequestDispatcher("/regist.jsp").forward(req, resp);
//				//转发之后的代码会继续执行,添加return让后面的代码不执行
//				return;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			req.setAttribute("msg", "注册失败，请稍后重试");
//			// 将请求转发给regist.jsp
//			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
//			//转发之后的代码会继续执行,添加return让后面的代码不执行
//			return;
//		}finally{
//			JDBCUtils.close(conn2, ps2, null);
//		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

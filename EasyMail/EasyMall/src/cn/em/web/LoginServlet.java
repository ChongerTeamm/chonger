package cn.em.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.em.domain.User;
import cn.em.exception.MsgException;
import cn.em.factory.BaseFactory;
import cn.em.factory.UserServiceFactory;
import cn.em.service.UserService;
import cn.em.service.UserServiceImpl;
import cn.em.util.JDBCUtils;
import cn.em.util.MD5Util;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String encode=this.getServletContext().getInitParameter("encode");
		// 1.处理乱码
			//req.setCharacterEncoding(encode);
			//resp.setContentType("text/html;charset="+encode);
		// 2.获取请求参数
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			String remname=req.getParameter("remname");
			String autologin=req.getParameter("autologin");
		// 3.表单验证(略)
			
		
		//表单验证之后，需要对密码进行md5加密
			password=MD5Util.md5(password);
		
		// 4.实现记住用户名
			if("true".equals(remname)){
				// 用户勾选了记住用户名
				// 创建一个Cookie，保存用户传入的用户名
				// Cookie中不允许出现中文字符，因此需要将中文字符转成其他表示
				// 可以通过UrlEncoder这个工具类，实现快捷的将中文转成对应的utf-8表示
				// 将张飞-》%E5%BC%A0%E9%A3%9E
				Cookie cookie=new Cookie("remname",URLEncoder.encode(username,encode));
				// 设定一个时长，关闭浏览器后该Cookie依旧有效
				cookie.setMaxAge(60*60*24*7);//7天
				// 设定一个path
				cookie.setPath(req.getContextPath()+"/");
				// 将Cookie添加到resp对象中
				resp.addCookie(cookie);
			}else{
				// 没有勾选记住用户名，需要删除之前保存用户名的Cookie
				Cookie cookie=new Cookie("remname","");
				cookie.setMaxAge(0);
				cookie.setPath(req.getContextPath()+"/");
				resp.addCookie(cookie);
			}
		// 5.实现登录逻辑
			UserService service=BaseFactory.getFactory().getInstance(UserService.class);
			User user=null;
			try {
				user = service.login(username, password);
			} catch (MsgException e) {
				// 登录失败，添加错误提示信息，返回登录页面
				req.setAttribute("msg", e.getMessage());
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
				return;
			}
			if(user!=null){
				// 用户名和密码正确
				// 6.保存用户的登录状态 获取Session对象
				HttpSession session=req.getSession();
				// session中保存的user对象，封装了用户的全部信息
				session.setAttribute("user", user);
				// 保证关闭浏览器再开后，session依旧生效
				Cookie c=new Cookie("JSESSIONID", session.getId());
				c.setMaxAge(60*30);//30分钟
				c.setPath(req.getContextPath()+"/");
				resp.addCookie(c);
				
				// 实现30天自动登录-将用户名和密码保存在cookie中返回给用户
				if("true".equals(autologin)){
					//说明勾选了30天自动登录
					Cookie cookie=new Cookie("autologin",URLEncoder.encode(username,encode)+"#"+password);
					cookie.setMaxAge(60*60*24*30);//30天
					cookie.setPath(req.getContextPath()+"/");
					resp.addCookie(cookie);
				}
				// 7.根据登录结果返回对应的内容
				// 成功-》提示成功+2秒后跳转首页
				resp.getWriter().write("<h1 style='text-align:center;color:red'>恭喜您!登录成功!2秒后跳转首页</h1>");
				resp.setHeader("refresh", "2;url="+req.getContextPath()+"/index.jsp");
			}else{
				// 登录失败，添加错误提示信息，返回登录页面
				req.setAttribute("msg", "用户名或密码错误");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
			
//			String sql="select * from user where username=? and password=?";
//			Connection conn=null;
//			PreparedStatement ps=null;
//			ResultSet rs=null;
//			
//			try {
//				conn=JDBCUtils.getConn();
//				ps=conn.prepareStatement(sql);
//				ps.setString(1, username);
//				ps.setString(2, password);
//				rs=ps.executeQuery();
//				if(rs.next()){
//					// 用户名和密码正确
//					// 6.保存用户的登录状态
//					// 获取Session对象
//					HttpSession session=req.getSession();
//					session.setAttribute("user", username);
//					// 保证关闭浏览器再开后，session依旧生效
//					Cookie c=new Cookie("JSESSIONID", session.getId());
//					c.setMaxAge(60*30);//30分钟
//					c.setPath(req.getContextPath()+"/");
//					resp.addCookie(c);
//					// 7.根据登录结果返回对应的内容
//					// 成功-》提示成功+2秒后跳转首页
//					resp.getWriter().write("<h1 style='text-align:center;color:red'>恭喜您!登录成功!2秒后跳转首页</h1>");
//					resp.setHeader("refresh", "2;url="+req.getContextPath()+"/index.jsp");
//				}else{
//					// 登录失败，添加错误提示信息，返回登录页面
//					req.setAttribute("msg", "用户名或密码错误");
//					req.getRequestDispatcher("/login.jsp").forward(req, resp);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				// 登录失败，添加错误提示信息，返回登录页面
//				req.setAttribute("msg", "登录异常，请稍后重试");
//				req.getRequestDispatcher("/login.jsp").forward(req, resp);
//			}finally{
//				JDBCUtils.close(conn, ps, rs);
//			}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

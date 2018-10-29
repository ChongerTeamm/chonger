package cn.em.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.em.factory.BaseFactory;
import cn.em.factory.UserServiceFactory;
import cn.em.service.UserService;
import cn.em.service.UserServiceImpl;
import cn.em.util.JDBCUtils;

public class AjaxCheckUsernameServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取全局的字符集编码
		//String encode=this.getServletContext().getInitParameter("encode");
		// 1. 处理乱码
		// 请求乱码 post
		// req.setCharacterEncoding(encode);
		// 应答乱码
		//resp.setContentType("text/html;charset="+encode);
		// 2. 获取请求参数
		String username=req.getParameter("username");
		// 手动编解码处理get请求乱码
		//username=new String(username.getBytes("iso8859-1"),encode);
		// 3. 表单验证(略)
		// 4. 执行逻辑
		UserService service=BaseFactory.getFactory().getInstance(UserService.class);
		boolean hasUsername=service.hasUsername(username);
		if(hasUsername){
			// 数据库中存在该用户名
			resp.getWriter().write("很抱歉，用户名已存在");
		}else{
			resp.getWriter().write("恭喜您，用户名可以使用");
		}
		
//		String sql="select * from user where username=?";
//		Connection conn=null;
//		PreparedStatement ps=null;
//		ResultSet rs=null;
//		
//		try {
//			conn=JDBCUtils.getConn();
//			ps=conn.prepareStatement(sql);
//			ps.setString(1, username);
//			rs=ps.executeQuery();
//			if(rs.next()){
//				// 数据库中存在该用户名
//				// 5. 根据执行结果，返回应答内容
//				resp.getWriter().write("很抱歉，用户名已存在");
//			}else{
//				resp.getWriter().write("恭喜您，用户名可以使用");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			JDBCUtils.close(conn, ps, rs);
//		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

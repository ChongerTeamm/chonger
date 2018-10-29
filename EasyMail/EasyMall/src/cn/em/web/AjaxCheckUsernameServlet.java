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
		// ��ȡȫ�ֵ��ַ�������
		//String encode=this.getServletContext().getInitParameter("encode");
		// 1. ��������
		// �������� post
		// req.setCharacterEncoding(encode);
		// Ӧ������
		//resp.setContentType("text/html;charset="+encode);
		// 2. ��ȡ�������
		String username=req.getParameter("username");
		// �ֶ�����봦��get��������
		//username=new String(username.getBytes("iso8859-1"),encode);
		// 3. ����֤(��)
		// 4. ִ���߼�
		UserService service=BaseFactory.getFactory().getInstance(UserService.class);
		boolean hasUsername=service.hasUsername(username);
		if(hasUsername){
			// ���ݿ��д��ڸ��û���
			resp.getWriter().write("�ܱ�Ǹ���û����Ѵ���");
		}else{
			resp.getWriter().write("��ϲ�����û�������ʹ��");
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
//				// ���ݿ��д��ڸ��û���
//				// 5. ����ִ�н��������Ӧ������
//				resp.getWriter().write("�ܱ�Ǹ���û����Ѵ���");
//			}else{
//				resp.getWriter().write("��ϲ�����û�������ʹ��");
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

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
		// 1.��������
			//req.setCharacterEncoding(encode);
			//resp.setContentType("text/html;charset="+encode);
		// 2.��ȡ�������
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			String remname=req.getParameter("remname");
			String autologin=req.getParameter("autologin");
		// 3.����֤(��)
			
		
		//����֤֮����Ҫ���������md5����
			password=MD5Util.md5(password);
		
		// 4.ʵ�ּ�ס�û���
			if("true".equals(remname)){
				// �û���ѡ�˼�ס�û���
				// ����һ��Cookie�������û�������û���
				// Cookie�в�������������ַ��������Ҫ�������ַ�ת��������ʾ
				// ����ͨ��UrlEncoder��������࣬ʵ�ֿ�ݵĽ�����ת�ɶ�Ӧ��utf-8��ʾ
				// ���ŷ�-��%E5%BC%A0%E9%A3%9E
				Cookie cookie=new Cookie("remname",URLEncoder.encode(username,encode));
				// �趨һ��ʱ�����ر���������Cookie������Ч
				cookie.setMaxAge(60*60*24*7);//7��
				// �趨һ��path
				cookie.setPath(req.getContextPath()+"/");
				// ��Cookie��ӵ�resp������
				resp.addCookie(cookie);
			}else{
				// û�й�ѡ��ס�û�������Ҫɾ��֮ǰ�����û�����Cookie
				Cookie cookie=new Cookie("remname","");
				cookie.setMaxAge(0);
				cookie.setPath(req.getContextPath()+"/");
				resp.addCookie(cookie);
			}
		// 5.ʵ�ֵ�¼�߼�
			UserService service=BaseFactory.getFactory().getInstance(UserService.class);
			User user=null;
			try {
				user = service.login(username, password);
			} catch (MsgException e) {
				// ��¼ʧ�ܣ���Ӵ�����ʾ��Ϣ�����ص�¼ҳ��
				req.setAttribute("msg", e.getMessage());
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
				return;
			}
			if(user!=null){
				// �û�����������ȷ
				// 6.�����û��ĵ�¼״̬ ��ȡSession����
				HttpSession session=req.getSession();
				// session�б����user���󣬷�װ���û���ȫ����Ϣ
				session.setAttribute("user", user);
				// ��֤�ر�������ٿ���session������Ч
				Cookie c=new Cookie("JSESSIONID", session.getId());
				c.setMaxAge(60*30);//30����
				c.setPath(req.getContextPath()+"/");
				resp.addCookie(c);
				
				// ʵ��30���Զ���¼-���û��������뱣����cookie�з��ظ��û�
				if("true".equals(autologin)){
					//˵����ѡ��30���Զ���¼
					Cookie cookie=new Cookie("autologin",URLEncoder.encode(username,encode)+"#"+password);
					cookie.setMaxAge(60*60*24*30);//30��
					cookie.setPath(req.getContextPath()+"/");
					resp.addCookie(cookie);
				}
				// 7.���ݵ�¼������ض�Ӧ������
				// �ɹ�-����ʾ�ɹ�+2�����ת��ҳ
				resp.getWriter().write("<h1 style='text-align:center;color:red'>��ϲ��!��¼�ɹ�!2�����ת��ҳ</h1>");
				resp.setHeader("refresh", "2;url="+req.getContextPath()+"/index.jsp");
			}else{
				// ��¼ʧ�ܣ���Ӵ�����ʾ��Ϣ�����ص�¼ҳ��
				req.setAttribute("msg", "�û������������");
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
//					// �û�����������ȷ
//					// 6.�����û��ĵ�¼״̬
//					// ��ȡSession����
//					HttpSession session=req.getSession();
//					session.setAttribute("user", username);
//					// ��֤�ر�������ٿ���session������Ч
//					Cookie c=new Cookie("JSESSIONID", session.getId());
//					c.setMaxAge(60*30);//30����
//					c.setPath(req.getContextPath()+"/");
//					resp.addCookie(c);
//					// 7.���ݵ�¼������ض�Ӧ������
//					// �ɹ�-����ʾ�ɹ�+2�����ת��ҳ
//					resp.getWriter().write("<h1 style='text-align:center;color:red'>��ϲ��!��¼�ɹ�!2�����ת��ҳ</h1>");
//					resp.setHeader("refresh", "2;url="+req.getContextPath()+"/index.jsp");
//				}else{
//					// ��¼ʧ�ܣ���Ӵ�����ʾ��Ϣ�����ص�¼ҳ��
//					req.setAttribute("msg", "�û������������");
//					req.getRequestDispatcher("/login.jsp").forward(req, resp);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				// ��¼ʧ�ܣ���Ӵ�����ʾ��Ϣ�����ص�¼ҳ��
//				req.setAttribute("msg", "��¼�쳣�����Ժ�����");
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

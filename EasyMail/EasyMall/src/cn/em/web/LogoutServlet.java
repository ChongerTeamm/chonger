package cn.em.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// �����û��󶨵�session����
		HttpSession session=req.getSession(false);
		if(session!=null){
			// ���ٵ�ǰsession
			session.invalidate();
		}
		// ɾ��30���Զ���¼��Cookie
		Cookie cookie=new Cookie("autologin", "");
		cookie.setMaxAge(0);
		cookie.setPath(req.getContextPath()+"/");
		resp.addCookie(cookie);
		
		// �ض�����ҳ
		resp.sendRedirect(req.getContextPath()+"/index.jsp");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

package cn.em.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username=req.getParameter("username");
		String addr=req.getParameter("addr");
		// 从parameterMap中获取请求参数
//		String username=req.getParameterMap().get("username")[0];
//		String addr=req.getParameterMap().get("addr")[0];
//		
		System.out.println("username="+username);
		System.out.println("addr="+addr);
		resp.getWriter().write("收到的用户名是:"+username);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

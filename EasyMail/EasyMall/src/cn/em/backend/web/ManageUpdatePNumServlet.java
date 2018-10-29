package cn.em.backend.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.em.factory.BaseFactory;
import cn.em.service.ProdService;
public class ManageUpdatePNumServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取请求参数
		Integer pid=Integer.parseInt(req.getParameter("pid"));
		Integer pnum=Integer.parseInt(req.getParameter("pnum"));
		// 调用service执行修改的逻辑
		ProdService service=BaseFactory.getFactory().getInstance(ProdService.class);
		boolean flag=service.changePnum(pid,pnum);
		if(flag){
			//修改成功
			resp.getWriter().write("修改成功！");			
		}else{
			resp.getWriter().write("修改失败！");			
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

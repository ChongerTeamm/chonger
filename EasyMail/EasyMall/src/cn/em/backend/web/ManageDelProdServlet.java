package cn.em.backend.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.em.factory.BaseFactory;
import cn.em.service.ProdService;

public class ManageDelProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// ��ȡ�������-��pid
		Integer pid=Integer.parseInt(req.getParameter("pid"));
		// ����serviceִ��ɾ����Ʒ���߼�
		ProdService service=BaseFactory.getFactory().getInstance(ProdService.class);
		boolean flag=service.deleteProd(pid);
		if(flag){
			resp.getWriter().write("��Ʒɾ���ɹ�");
		}else{
			resp.getWriter().write("��Ʒɾ��ʧ��");
		}
		// ��ʱˢ�µ��޸���Ʒ��ҳ��
		resp.setHeader("refresh", "3;url="+req.getContextPath()+"/ManageProdListServlet");

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

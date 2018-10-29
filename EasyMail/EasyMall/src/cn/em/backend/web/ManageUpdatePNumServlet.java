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
		// ��ȡ�������
		Integer pid=Integer.parseInt(req.getParameter("pid"));
		Integer pnum=Integer.parseInt(req.getParameter("pnum"));
		// ����serviceִ���޸ĵ��߼�
		ProdService service=BaseFactory.getFactory().getInstance(ProdService.class);
		boolean flag=service.changePnum(pid,pnum);
		if(flag){
			//�޸ĳɹ�
			resp.getWriter().write("�޸ĳɹ���");			
		}else{
			resp.getWriter().write("�޸�ʧ�ܣ�");			
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

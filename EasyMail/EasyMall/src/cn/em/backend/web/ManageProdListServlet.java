package cn.em.backend.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.em.domain.Prod;
import cn.em.factory.BaseFactory;
import cn.em.service.ProdService;

public class ManageProdListServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// ��������
		// ֱ�ӵ���service�ķ�������ѯȫ����Ʒ����Ϣ
		ProdService service=BaseFactory.getFactory().getInstance(ProdService.class);
		List<Prod> list=service.listAllProd();
		// �����ݴ���request������
		req.setAttribute("list", list);
		// ������ת����
		req.getRequestDispatcher("/backend/manageProdList.jsp").forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

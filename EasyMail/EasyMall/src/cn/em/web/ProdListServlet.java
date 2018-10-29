package cn.em.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.em.domain.Prod;
import cn.em.factory.BaseFactory;
import cn.em.service.ProdService;

public class ProdListServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// ����service��Ӧ�ķ�������ȡ������Ʒ������
		ProdService service=BaseFactory.getFactory().getInstance(ProdService.class);
		List<Prod> list=service.listAllProd();
		// ����Ʒ���ݴ���request������
		req.setAttribute("prods", list);
		// ������ת����prodList.jsp
		req.getRequestDispatcher("/prodList.jsp").forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

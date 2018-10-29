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
		// 调用service对应的方法，获取所有商品的数据
		ProdService service=BaseFactory.getFactory().getInstance(ProdService.class);
		List<Prod> list=service.listAllProd();
		// 将商品数据存入request作用域
		req.setAttribute("prods", list);
		// 将请求转发给prodList.jsp
		req.getRequestDispatcher("/prodList.jsp").forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

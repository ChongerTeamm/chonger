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
		// 接收请求
		// 直接调用service的方法，查询全部商品的信息
		ProdService service=BaseFactory.getFactory().getInstance(ProdService.class);
		List<Prod> list=service.listAllProd();
		// 将数据存入request作用域
		req.setAttribute("list", list);
		// 将请求转发给
		req.getRequestDispatcher("/backend/manageProdList.jsp").forward(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

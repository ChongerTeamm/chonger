package cn.em.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.em.util.VerifyCode;

public class ValiImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("cs");
		// 控制浏览器不要缓存验证码图片
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("cache-control", "no-cache");
		// 创建一个VerifyCode工具类的实例
		VerifyCode vc=new VerifyCode();
		// 生成一张验证码图片，并返回给用户
		// resp.getOutputStream获取向应答实体中添加内容的字节流
		// 直接将绘制的图片存入response的缓冲区
		// web容器会在应答离开容器前，从response缓冲区中拿出图片的内容，放入应答实体中
		vc.drawImage(resp.getOutputStream());
		// 将生成的验证码图片的正确内容存入session作用域
		req.getSession().setAttribute("code", vc.getCode());
		System.out.println("验证码的内容是："+vc.getCode());
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

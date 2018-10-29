package cn.em.web;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProdImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取ServletContext对象
		ServletContext sc=this.getServletContext();
		// 1. 获取请求参数 imgurl
		String imgurl=req.getParameter("imgurl");
		// 2. 打开一个输入流，从服务器上读取对应图片的字节流
		FileInputStream fis=null;
		ServletOutputStream sos=null;
		try {
			fis=new FileInputStream(sc.getRealPath(imgurl));
			sos=resp.getOutputStream();
			byte[] array=new byte[100];
			int len=fis.read(array);
			while(len!=-1){
				// 3. 将图片写入应答缓冲区，后续web容器会从缓冲区
				// 中拿出该内容，添加到应答实体中，返回给浏览器
				sos.write(array, 0, len);
				len=fis.read(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fis!=null){
				fis.close();
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

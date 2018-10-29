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
		// ��ȡServletContext����
		ServletContext sc=this.getServletContext();
		// 1. ��ȡ������� imgurl
		String imgurl=req.getParameter("imgurl");
		// 2. ��һ�����������ӷ������϶�ȡ��ӦͼƬ���ֽ���
		FileInputStream fis=null;
		ServletOutputStream sos=null;
		try {
			fis=new FileInputStream(sc.getRealPath(imgurl));
			sos=resp.getOutputStream();
			byte[] array=new byte[100];
			int len=fis.read(array);
			while(len!=-1){
				// 3. ��ͼƬд��Ӧ�𻺳���������web������ӻ�����
				// ���ó������ݣ���ӵ�Ӧ��ʵ���У����ظ������
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

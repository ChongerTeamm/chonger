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
		// �����������Ҫ������֤��ͼƬ
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("cache-control", "no-cache");
		// ����һ��VerifyCode�������ʵ��
		VerifyCode vc=new VerifyCode();
		// ����һ����֤��ͼƬ�������ظ��û�
		// resp.getOutputStream��ȡ��Ӧ��ʵ����������ݵ��ֽ���
		// ֱ�ӽ����Ƶ�ͼƬ����response�Ļ�����
		// web��������Ӧ���뿪����ǰ����response���������ó�ͼƬ�����ݣ�����Ӧ��ʵ����
		vc.drawImage(resp.getOutputStream());
		// �����ɵ���֤��ͼƬ����ȷ���ݴ���session������
		req.getSession().setAttribute("code", vc.getCode());
		System.out.println("��֤��������ǣ�"+vc.getCode());
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

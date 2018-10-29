package cn.em.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// ͨ���¼������ȡServletContext����
		ServletContext sc=sce.getServletContext();
		// ͨ��ServletContext�����ȡwebӦ��ӳ�������·��
		String contextPath=sc.getContextPath();
		// ������·������ServletContext��������
		sc.setAttribute("app", contextPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}

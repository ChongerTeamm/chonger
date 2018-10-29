package cn.em.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 通过事件对象获取ServletContext对象
		ServletContext sc=sce.getServletContext();
		// 通过ServletContext对象获取web应用映射的虚拟路径
		String contextPath=sc.getContextPath();
		// 将虚拟路径存入ServletContext作用域中
		sc.setAttribute("app", contextPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}

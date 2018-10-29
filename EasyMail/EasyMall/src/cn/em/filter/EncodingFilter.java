package cn.em.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingFilter implements Filter {
	
	private String encode=null;//�û�����webӦ�����õı���

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// ��ȡwebӦ�����õı���
		String encode=filterConfig.getServletContext().getInitParameter("encode");
		// ʹ�û�ȡ���ı���Ϊ�����ʵ����Ա��ֵ
		this.encode=encode;
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		ServletContext sc=request.getServletContext();
		// �����������
		// ���Ӧ������
		response.setContentType("text/html;charset="+encode);
		
		// �����������
		// POST����-֪ͨ������ʹ��utf-8������
		// request.setCharacterEncoding(encode);
		
		// GET��������-�ֶ������
		// ����һ ֱ����Filter�б����ʧ��-��requestû��setParameter����
		// ���±����֮��������޷����û�ȥ
//		String username=new String(request.getParameter("username").getBytes("iso8859-1"),encode);
//		request.setAttribute("username", username);
		
		// ������ ��map�����е�����value�����ֶ������
		// ���Բ��ֽ�����⣬������Ҫ���е�Servletʹ��req.getParameterMap����ȡ����
		// ���Servlet��ʹ��req.getParameter��������Ч
		
		// ������ ʹ��װ����ģʽ���޸�request�Ļ�ȡ��������ķ���
		MyRequest req=new MyRequest((HttpServletRequest)request);

		// ��������
		chain.doFilter(req, response);
	}


	@Override
	public void destroy() {
		
	}
	
	class MyRequest extends HttpServletRequestWrapper{
		private boolean hasEncode=false;//Ĭ�ϻ�û�н��б����

		public MyRequest(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			// ���ñ���ķ�������map�����л�ȡname��Ӧ��value
			// ��map�����Ѿ������ֶ�����룬���Կ��Խ����������
			String[] values=this.getParameterMap().get(name);
			if(values!=null){
				return values[0];
			}
			return null;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			// ���ñ�װ���߶���ķ��������������map����
			Map<String, String[]> map=this.getRequest().getParameterMap();
			if(!hasEncode){
				// ��map�����е�value�����ֶ������
				for(Entry<String, String[]> entry:map.entrySet()){
					String[] values=entry.getValue();
					if(values!=null){
						for(int i=0;i<values.length;i++){
							try {
								values[i]=new String(values[i].getBytes("iso8859-1"),encode);
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}
					}
				}
				hasEncode=true;//�����Ѿ����������
			}
			//���ص����Ѿ�����ֶ�������map����
			return map;
		}

		@Override
		public String[] getParameterValues(String name) {
			// ���ñ��෽������map�����л�ȡ����Ĳ���
			return this.getParameterMap().get(name);
		}
	
	}

}

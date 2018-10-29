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
	
	private String encode=null;//用户保存web应用配置的编码

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 获取web应用配置的编码
		String encode=filterConfig.getServletContext().getInitParameter("encode");
		// 使用获取到的编码为本类的实例成员赋值
		this.encode=encode;
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		ServletContext sc=request.getServletContext();
		// 解决乱码问题
		// 解决应答乱码
		response.setContentType("text/html;charset="+encode);
		
		// 解决请求乱码
		// POST请求-通知服务器使用utf-8来解码
		// request.setCharacterEncoding(encode);
		
		// GET请求乱码-手动编解码
		// 方案一 直接在Filter中编解码失败-》request没有setParameter方法
		// 导致辩解码之后的内容无法设置回去
//		String username=new String(request.getParameter("username").getBytes("iso8859-1"),encode);
//		request.setAttribute("username", username);
		
		// 方案二 对map集合中的所有value进行手动编解码
		// 可以部分解决问题，但是需要所有的Servlet使用req.getParameterMap来获取参数
		// 如果Servlet中使用req.getParameter，则不能生效
		
		// 方案三 使用装饰者模式，修改request的获取请求参数的方法
		MyRequest req=new MyRequest((HttpServletRequest)request);

		// 放行请求
		chain.doFilter(req, response);
	}


	@Override
	public void destroy() {
		
	}
	
	class MyRequest extends HttpServletRequestWrapper{
		private boolean hasEncode=false;//默认还没有进行编解码

		public MyRequest(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			// 调用本类的方法，从map集合中获取name对应的value
			// 该map集合已经做过手动编解码，所以可以解决乱码问题
			String[] values=this.getParameterMap().get(name);
			if(values!=null){
				return values[0];
			}
			return null;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			// 调用被装饰者对象的方法，返回最初的map集合
			Map<String, String[]> map=this.getRequest().getParameterMap();
			if(!hasEncode){
				// 对map中所有的value进行手动编解码
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
				hasEncode=true;//设置已经做过编解码
			}
			//返回的是已经完成手动编解码的map集合
			return map;
		}

		@Override
		public String[] getParameterValues(String name) {
			// 调用本类方法，从map集合中获取请求的参数
			return this.getParameterMap().get(name);
		}
	
	}

}

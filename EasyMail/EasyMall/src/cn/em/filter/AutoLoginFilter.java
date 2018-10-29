package cn.em.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.em.domain.User;
import cn.em.exception.MsgException;
import cn.em.factory.BaseFactory;
import cn.em.service.UserService;

public class AutoLoginFilter implements Filter{
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpSession session=req.getSession(false);
		//1.用户没有登录
		if(session==null||session.getAttribute("user")==null){
			//2. 用户携带了autologin的Cookie
			Cookie[] cs=req.getCookies();
			Cookie autoLogin=null;
			if(cs!=null){
				for(Cookie c:cs){
					if("autologin".equals(c.getName())){
						autoLogin=c;
					}
				}
			}
			if(autoLogin!=null){//真的携带了自动登录的Cookie
				//3. Cookie中保存的用户名和密码是正确的
				String username=autoLogin.getValue().split("#")[0];
				// 进行urlDecode
				username=URLDecoder.decode(username, request.getServletContext().getInitParameter("encode"));
				String password=autoLogin.getValue().split("#")[1];
				UserService service=BaseFactory.getFactory().getInstance(UserService.class);
				try {
					User user=service.login(username, password);
					if(user!=null){
						//说明用户名和密码是正确的
						//执行登录逻辑
						req.getSession().setAttribute("user", user);
					}
				} catch (MsgException e) {
					e.printStackTrace();
				}
			}
		}
		// 放行请求
		chain.doFilter(request, response);
	}
	
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

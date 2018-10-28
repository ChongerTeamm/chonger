package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	//www.jt.com/index.html
	@RequestMapping("index")
	public String goIndex(){
		return "index";//WEB-INF/views/index.jsp
	}
	
	//登录,注册页面动态跳转
	@RequestMapping("user/{pageName}")
	public String goSSO(@PathVariable String pageName){
		return pageName;
	}
}











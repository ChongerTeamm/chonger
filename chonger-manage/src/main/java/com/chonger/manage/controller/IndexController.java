package com.chonger.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	//访问后台的首页index.jsp 动态获取页面名称
	@RequestMapping("/page/{pageName}")
	public String goIndex(@PathVariable String pageName){
		//page/item-add WEB-INF/views/item-add.jsp
		return pageName;
	}
}

package com.chonger.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chonger.common.util.CookieUtils;
import com.chonger.common.vo.SysResult;
import com.chonger.web.pojo.User;
import com.chonger.web.service.UserService;


@Controller
public class UserController {

	/*
	 * equest URL: http://www.jt.com/service/user/doRegister
	 */
	//注册表单
	@Autowired
	UserService userService;
	@RequestMapping("service/user/doRegister")
	@ResponseBody
	public SysResult doRegister(User user){
		try{
			userService.doRegister(user);
			return SysResult.oK(user.getUsername());
		}catch(Exception e){
			return SysResult.build(200, "注册失败");
		}
	}
	
	@RequestMapping("service/user/doLogin")
	@ResponseBody
	public SysResult doLogin(User user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String ticket=userService.doLogin(user);
		if("".equals(ticket)||"null".equals(ticket)||ticket==null){
			return SysResult.build(201, "错误");
		}else{
			CookieUtils.setCookie(request, response, "JT_TICKET", ticket);
			return SysResult.oK();
		}
	}

}

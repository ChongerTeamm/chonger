package com.chonger.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chonger.common.vo.SysResult;
import com.chonger.sso.service.UserService;


@Controller
public class LoginController {
	/*请求方法	POST
	URL	http://sso.jt.com/user/login
	参数	u 用户名
	p 密码
	示例	http://sso.jt.com/user/login

	u：	chenchen
	p：	123456

	返回值	{
	status: 200 /200成功,201失败
	msg: “OK” 
	data:” e10adc3949ba59abbe56e057f20f883e” //登录成功，返回ticket
	}
	备注	登录完成，返回ticket，前台系统写入cookie
	ticket算法	唯一标识每个用户：动态唯一
	安全：混淆md5加密
	md5（“JT_TICKET_” + System.currentTime + username）*/
	@Autowired
	UserService userService;
	@RequestMapping("user/login")
	@ResponseBody
	public SysResult login(@RequestParam(value="u")String username,@RequestParam(value="p")String password) throws Exception{
		String ticket=userService.doLogin(username,password);
      return SysResult.oK(ticket);
		
	}
}

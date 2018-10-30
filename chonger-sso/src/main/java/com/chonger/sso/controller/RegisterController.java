package com.chonger.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chonger.common.vo.SysResult;
import com.chonger.sso.pojo.User;
import com.chonger.sso.service.UserService;

@Controller
public class RegisterController {
	
	
/*	请求方法	POST
	URL	http://sso.jt.com/user/register
	参数	username 用户名
	password 密码
	phone 手机号
	email 邮箱


	返回值	{
	status: 200  //200 成功，201 没有查到
	msg: “OK”  //返回信息消息
	data:username  //返回数据username值
	}
*/
	@Autowired
	UserService userService;
	@RequestMapping("/user/register")
	@ResponseBody
	public SysResult insertUser(User user){
		try{
			userService.saveUser(user);
			return SysResult.oK(user.getUsername());
		}catch(Exception e){
			return SysResult.build(201, e.getMessage());
		}
	}
	
}

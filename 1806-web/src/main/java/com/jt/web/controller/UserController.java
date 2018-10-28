package com.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	//用户注册数据提交
	@RequestMapping("service/user/doRegister")
	@ResponseBody
	public SysResult doRegister(User user){
		try{
			userService.doRegister(user);
			return SysResult.oK(user.getUsername());
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, e.getMessage());
		}
	}
}

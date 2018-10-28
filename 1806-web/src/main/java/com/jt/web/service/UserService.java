package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.User;

@Service
public class UserService {
	@Autowired
	private HttpClientService client;
	public void doRegister(User user) throws Exception {
		String url="http://sso.jt.com/user/register";
		//调用的doPost(url,map)
		//username=haha&password=124&phone=185,添加到请求体中传递
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		map.put("phone", user.getPhone());
		map.put("email", user.getUsername());
		
		client.doPost(url, map);
	}

}

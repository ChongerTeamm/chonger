package com.chonger.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chonger.common.service.HttpClientService;
import com.chonger.common.util.ObjectUtil;
import com.chonger.common.vo.HttpResult;
import com.chonger.common.vo.SysResult;
import com.chonger.web.pojo.User;


@Service 
public class UserService {
	@Autowired
	HttpClientService client;
	/**
	 * 注册用户
	 * @param user
	 * @param code 
	 * @throws Exception
	 */
	public void doRegister(User user) throws Exception {
		String url="http://sso.chonger.com/user/register";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		map.put("phone", user.getPhone());
		map.put("email", user.getUsername());
		map.put("validate",user.getValidate());
		HttpResult result = client.doPost(url, map);
	System.out.println(	"doRegisterService"+result.getCode());
		
	}

	public String doLogin(User user) throws Exception {
		String url="http://sso.chonger.com/user/login";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("u", user.getUsername());
		map.put("p", user.getPassword());
		
		HttpResult response = client.doPost(url, map);
		String userJson = response.getBody();
		SysResult result = ObjectUtil.mapper.readValue(userJson, SysResult.class);
		String ticket=(String) result.getData();
		return ticket;
	}

	
	
	public void doValidate(String phone) throws Exception {
	String url="http://sso.jt.com/send/validate/"+phone;
	client.doGet(url);
	
	
	}

	
}

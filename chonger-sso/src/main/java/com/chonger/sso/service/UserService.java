package com.chonger.sso.service;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chonger.common.redis.RedisService;
import com.chonger.common.vo.SysResult;
import com.chonger.sso.mapper.UserMapper;
import com.chonger.sso.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	public SysResult checkParam(String param, Integer type) {

		User _user = new User();
		Integer count = 0;
		if (type == 1) {
			_user.setUsername(param);
			count = userMapper.selectCount(_user);
		} else {
			_user.setPhone(param);
			count = userMapper.selectCount(_user);
		}
		if (count == 0) {
			return SysResult.oK(false);
		} else {
			return SysResult.oK(true);
		}
	}

	public void saveUser(User user) throws Exception {
		user.setEmail(user.getUsername());
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		if (user.getValidate().equals(redis.get(user.getValidate()))) {
			userMapper.insertSelective(user);
		} else {
			throw new Exception("验证码错误");
		}

	}

	@Autowired
	RedisService redis;

	public String doLogin(String username, String password) throws Exception {
		User _user = new User();
		_user.setUsername(username);
		_user.setPassword(DigestUtils.md5Hex(password));
		User user = userMapper.selectOne(_user);
		if (user != null) {
			String ticket = DigestUtils.md5Hex("JT_TICKET_" + System.currentTimeMillis() + username);

			String userJson = new ObjectMapper().writeValueAsString(user);

			redis.set(ticket, userJson);
			return ticket;

		}
		return "";
	}

	public String queryUserJson(String ticket) {
		return redis.get(ticket);

	}

}

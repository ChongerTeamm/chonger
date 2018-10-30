package com.chonger.sso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chonger.common.redis.RedisService;
import com.chonger.sso.utils.RandomCode;
import com.chonger.sso.utils.SendValidateCode;
@Service
public class ValidateService {
	@Autowired
	RedisService redis;
	/**
	 * 给传入的电话发送一条四位数随机验证码
	 * @param phone
	 * @return 状态码
	 * @throws Exception
	 */
	public int sendValidate(String phone) throws Exception {
		String code=RandomCode.getValidateCode();
		int status=SendValidateCode.send(code, phone);
		//将生成的验证码存在redis缓存中
		redis.set(code,code);
		return 200;
	}

}

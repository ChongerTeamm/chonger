package com.chonger.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chonger.common.vo.SysResult;
import com.chonger.sso.service.ValidateService;
@RestController
public class ValidateController {
	@Autowired
	ValidateService service;
//	http://sso.jt.com/send/validate&mobile=18725263634&r=0.86884369651377
	@RequestMapping("send/validate/{phone}")
	public SysResult Validate(@PathVariable String phone) throws Exception{
		
		int returnValue=service.sendValidate(phone);
		if(returnValue==200){
			return SysResult.oK();
		}else{
			return SysResult.build(201, "发送错误");
		}
		
	}
}
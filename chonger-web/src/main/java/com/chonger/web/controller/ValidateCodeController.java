package com.chonger.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chonger.common.vo.SysResult;
import com.chonger.web.service.ValiataCodeService;



@Controller
public class ValidateCodeController {
	@Autowired
	ValiataCodeService validateCodeService;
	/**
	 * 传入手机号，给手机号发送随机验证码；
	 * @param phone
	 * @return SysResult 的Json字符串
	 */
	@RequestMapping("user/send/validate/{phone}")
	@ResponseBody
	public SysResult doValidate(@PathVariable String phone) {
		try {
			validateCodeService.doValidate(phone);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "验证码发送失败，稍后重试");
		}

	}
}

package com.chonger.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chonger.common.service.HttpClientService;
@Service
public class ValiataCodeService {
	@Autowired
	HttpClientService client;
	public void doValidate(String phone) throws Exception {
		String url = "http://sso.chonger.com/send/validate/" + phone;
		client.doGet(url);

	}

}

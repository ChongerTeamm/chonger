package com.chonger.test;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chonger.common.service.HttpClientService;
import com.chonger.manage.pojo.ItemCatDesc;

public class TestQueryItemCatByCatId {
	@Autowired
	private HttpClientService httpClientService;
	@Test
	public void test01() throws Exception {
		String url = "http://manage.chonger.com/catDescs/81";
		String itemCatDesc = httpClientService.doGet(url);
		System.out.println(itemCatDesc);
	}
}

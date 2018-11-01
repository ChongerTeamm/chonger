package com.chonger.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chonger.common.service.HttpClientService;
import com.chonger.common.util.ObjectUtil;
import com.chonger.web.pojo.ItemCat;
import com.chonger.web.pojo.ItemCatDesc;
import com.chonger.web.pojo.ItemDesc;

@Service
public class ItemCatService {

	// 获取后台数据
	@Autowired
	private HttpClientService client;

	public ItemCat queryCatById(Long catId) throws Exception {
		String url="http://manage.chonger.com/cats/"+catId;
		//返回响应体中的字符串
		String itemJson = client.doGet(url);
		System.out.println("web"+itemJson);
		//转化json成item对象 readValue是将单个对象的json转化成对象的方法
		//对象转成json writeValueAsString
		ItemCat itemCat=ObjectUtil.mapper.readValue(itemJson, ItemCat.class);
		return itemCat;
	}

	public ItemCatDesc queryCatDescById(Long catId) throws Exception {
		String url="http://manage.chonger.com/itemCatDescs/"+catId;
		String itemDescJson = client.doGet(url);
		//转化json成item对象 readValue是将单个对象的json转化成对象的方法
		//对象转成json writeValueAsString
		ItemCatDesc itemCatDesc=ObjectUtil.mapper.
				readValue(itemDescJson, ItemCatDesc.class);
		return itemCatDesc;
	}

}

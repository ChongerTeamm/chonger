package com.jt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.HttpClientService;
import com.jt.common.util.ObjectUtil;
import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;

@Service
public class ItemService {
	//获取后台数据
	@Autowired
	private HttpClientService client;
	public Item queryItem(Long itemId) throws Exception {
		//业务层代码,需要发起夸系统的访问,使用的是httpclient
		/*请求url:"manage.jt.com/items/{itemId}"
		请求方式:get
		参数:Long itemId 
返回数据: item的json字符串返回放到响应体中;*/
		//url地址
		String url="http://manage.jt.com/items/"+itemId;
		//返回响应体中的字符串
		String itemJson = client.doGet(url);
		//转化json成item对象 readValue是将单个对象的json转化成对象的方法
		//对象转成json writeValueAsString
		Item item=ObjectUtil.mapper.readValue(itemJson, Item.class);
		return item;
	}

	public ItemDesc queryItemDesc(Long itemId) throws Exception {
		/*
		 * 	商品详情
		请求url:"manage.jt.com/itemDescs/{itemId}"
		请求方式:get
		参数:Long itemId
		返回数据:itemDesc的json字符串
		 */
		String url="http://manage.jt.com/itemDescs/"+itemId;
		String itemDescJson = client.doGet(url);
		//转化json成item对象 readValue是将单个对象的json转化成对象的方法
		//对象转成json writeValueAsString
		ItemDesc itemDesc=ObjectUtil.mapper.
				readValue(itemDescJson, ItemDesc.class);
		return itemDesc;

	}

}

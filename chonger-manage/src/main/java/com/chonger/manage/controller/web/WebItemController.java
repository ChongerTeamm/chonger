package com.chonger.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chonger.manage.pojo.Item;
import com.chonger.manage.pojo.ItemDesc;
import com.chonger.manage.service.ItemService;

@Controller
public class WebItemController {
	@Autowired
	private ItemService itemService;
	//景区信息
	@RequestMapping("items/{itemId}")
	@ResponseBody
	public Item queryItemById(@PathVariable Long itemId){
		Item item=itemService.queryItemById(itemId);
		return item;
	}
	//景区详情
	@RequestMapping("itemDescs/{itemId}")
	@ResponseBody
	public ItemDesc queryItemDescById(@PathVariable Long itemId){
		ItemDesc itemDesc=itemService.queryItemDesc(itemId);
		return itemDesc;
	}
}













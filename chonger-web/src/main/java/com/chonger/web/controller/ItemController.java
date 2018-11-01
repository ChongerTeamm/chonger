package com.chonger.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chonger.web.pojo.Item;
import com.chonger.web.pojo.ItemDesc;
import com.chonger.web.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	//查询一个商品的信息和详情
	@RequestMapping("items/{itemId}")
	public String queryItemInfo(@PathVariable Long itemId,Model model) throws Exception{
		Item item=itemService.queryItem(itemId);
		ItemDesc itemDesc=itemService.queryItemDesc(itemId);
		
		//携带数据到页面展示
		model.addAttribute("item",item);
		model.addAttribute("itemDesc", itemDesc);
		
		return "item2";
	}
}

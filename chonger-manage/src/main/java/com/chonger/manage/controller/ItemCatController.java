package com.chonger.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chonger.manage.service.ItemCatService;

@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemService;
	//页面传递参数Long id作为查询条件parent_id
	//获取下级list对象
	//item/cat/list
	@RequestMapping("item/cat/list")
	@ResponseBody
	public List queryItemList(@RequestParam(defaultValue="0")Long id){
		//传入的id值,有可能是第一次查询,初始化状态,id为空
	
		List itemCatList=itemService.queryItemList(id);
		return itemCatList;
	}
}

package com.chonger.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chonger.web.pojo.Item;
import com.chonger.web.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService searcher;
		//接收前台的数据,查询q对应的title名称的数据,分页返回
	@RequestMapping("search")
	public String search(String q,Model model,
			@RequestParam(defaultValue="1")Integer page){
		List<Item> itemList=searcher.findItems(q,page);
		model.addAttribute("itemList", itemList);
		model.addAttribute("query", q);
		return "search";
	}
}

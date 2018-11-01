package com.chonger.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chonger.common.util.ObjectUtil;
import com.chonger.web.pojo.ItemCat;
import com.chonger.web.pojo.ItemCatDesc;
import com.chonger.web.service.ItemCatService;

@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	// 查询类别信息和类别详细信息
	@RequestMapping("cats/{catId}")
	public String queryItemCatById(@PathVariable Long catId, Model model) throws Exception {
		System.out.println(catId);
		ItemCat itemCat = itemCatService.queryCatById(catId);
		ItemCatDesc itemCatDesc = itemCatService.queryCatDescById(catId);

		// 携带数据到页面展示
		model.addAttribute("itemCat", itemCat);
		model.addAttribute("itemCatDesc", itemCatDesc);
		return "cat";
	}
}

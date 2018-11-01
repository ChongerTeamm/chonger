package com.chonger.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chonger.common.util.ObjectUtil;
import com.chonger.manage.pojo.Item;
import com.chonger.manage.pojo.ItemCat;
import com.chonger.manage.pojo.ItemCatDesc;
import com.chonger.manage.pojo.ItemCatResult;
import com.chonger.manage.service.ItemCatService;

@Controller
public class WebItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	// 前台三级分类树封装
	@RequestMapping("web/itemcat/all")
	@ResponseBody
	public String queryAllItemCat(String callback) throws Exception {
		// 调用service先封装数据,返回对象,controller将字符串拼接
		ItemCatResult result = itemCatService.queryAllItemCat();
		// 转化成字符串
		return callback + "(" + ObjectUtil.mapper.writeValueAsString(result) + ")";
	}
	
	//类别信息
	@RequestMapping("cats/{catId}")
	@ResponseBody
	public ItemCat queryItemCatById(@PathVariable Long catId) {
		//System.out.println("controller+++++"+catId);
		ItemCat itemCat = itemCatService.queryCatById(catId);
		//System.out.println("controller"+itemCat);
		return itemCat;
	}
	
	// 类别详细信息
	@RequestMapping("/itemCatDescs/{catId}")
	@ResponseBody
	public ItemCatDesc queryItemCatDescById(@PathVariable Long catId) {
		ItemCatDesc itemCatDesc = itemCatService.queryCatDescById(catId);
		return itemCatDesc;
	}

}

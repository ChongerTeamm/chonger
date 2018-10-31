package com.chonger.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chonger.common.util.ObjectUtil;
import com.chonger.manage.pojo.ItemCatResult;
import com.chonger.manage.service.ItemCatService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class WebItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	//前台三级分类树封装
	@RequestMapping("web/itemcat/all")
	@ResponseBody
	public String queryAllItemCat(String callback) throws Exception{
		//调用service先封装数据,返回对象,controller将字符串拼接
		ItemCatResult result=itemCatService.queryAllItemCat();
		//转化成字符串
		return callback+"("+ObjectUtil.mapper.writeValueAsString(result)+")";
	}
}












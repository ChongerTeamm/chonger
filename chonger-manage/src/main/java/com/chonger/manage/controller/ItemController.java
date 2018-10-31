package com.chonger.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chonger.common.vo.EasyUIResult;
import com.chonger.common.vo.SysResult;
import com.chonger.manage.pojo.Item;
import com.chonger.manage.pojo.ItemDesc;
import com.chonger.manage.service.ItemService;

@Controller//=controller+responseBody
public class ItemController {
	/*
	 * 	请求url:http://manage.jt.com/item/query?page=1&rows=20
	请求方式:get
	请求参数: Integer page 页数,Integer rows 条数
	Start=(page-1)*rows
	第一页:select * from tb_item limit 0,20
	第二页:select * from tb_item limit 20,20
	返回数据 EasyUIResult 的json字符
	对象中包含2个属性
	Integer total 对应前端页面计算总页数
	Select count(*) from tb_item
	List<Item> rows 当前分页的list数据

	 */
	@Autowired
	private ItemService itemService;
	//查询景点列表的分页查询
	@RequestMapping("item/query")
	@ResponseBody
	public EasyUIResult queryItemList(Integer page,Integer rows){
		EasyUIResult result=itemService.queryItemList(page,rows);
		return result;
	}
	
	//新增景点和景点详情
	@RequestMapping("item/save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc){
		try{
			itemService.saveItem(item,desc);
			return SysResult.oK();
		}catch(Exception e){
			//新增数据失败
			return SysResult.build(201, "新增失败");
		}
	}
	
	//查询详情
	@RequestMapping("item/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult queryItemDesc(@PathVariable Long itemId){
		ItemDesc itemDesc=itemService.queryItemDesc(itemId);
		//select * from tb_item_desc where item_id=#{itemId}
		return SysResult.oK(itemDesc);
	}
	
	//景点和详情的修改
	@RequestMapping("item/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc){
		itemService.updateItem(item,desc);
		return SysResult.oK();
	}
	
	//景点的删除
	@RequestMapping("item/delete")
	@ResponseBody
	public SysResult deleteItem(@RequestParam(value="ids")Long id){
		itemService.deleteItem(id);
		return SysResult.oK();
	}
}










package com.chonger.manage.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chonger.manage.pojo.ItemCat;
import com.chonger.manage.pojo.ItemCatData;
import com.chonger.manage.pojo.ItemCatResult;

public class ItemCatUtil {

	public static ItemCatResult allItemCat(List<ItemCat> cats){
			//声明一个存储的对象,然后构建对象
			ItemCatResult result=new ItemCatResult();
			//	List<ItemCat> cats=itemCatMapper.selectAll();//查询所有菜单
			//这里我们引入一个map+list循环拼接的方式完成这个结构
			//获取当前菜单下的所有的子菜单行程一个数组,可以仔细研究一下思路,借鉴
			Map<Long,List<ItemCat>> map=new HashMap<Long,List<ItemCat>>();
			for(ItemCat itemCat:cats){
				//从当前一个itemcat中获取parentId
				if(!map.containsKey(itemCat.getParentId())){
					//创建一个元素,元素内容,也就是说没有值的时候,创建一个
					map.put(itemCat.getParentId(), new ArrayList<ItemCat>());
				}
				map.get(itemCat.getParentId()).add(itemCat);
			}
			//构建三级菜单结构
			List<ItemCat> itemCatList1=map.get(0L);
			//为一级菜单构建它的所有子菜单
			List<ItemCatData> itemCatDataList1 = new ArrayList<ItemCatData>();
			for(ItemCat itemCat1:itemCatList1){//遍历一级菜单
				ItemCatData itemCatData1=new ItemCatData();
				itemCatData1.setUrl("/products/"+itemCat1.getId()+"/.html");
				itemCatData1.setName("<a href='/products/"+itemCat1.getId()+".html'>"+itemCat1.getName()+"</a>");
				
				//遍历二级菜单,也需要拼接
				List<ItemCatData> itemCatDataList2=new ArrayList<ItemCatData>();
				for(ItemCat itemCat2:map.get(itemCat1.getId())){
					ItemCatData itemCatData2=new ItemCatData();
					itemCatData2.setUrl("/products/"+itemCat2.getId()+"/.html");
					itemCatData2.setName("<a href='/products/"+itemCat2.getId()+".html'>"+itemCat2.getName()+"</a>");
					//遍历三级菜单
					
					List<String> itemCatDataList3=new ArrayList<String>();
					for (ItemCat itemCat3 : map.get(itemCat2.getId())) {
						//三级菜单只是一个字符串和1,2级结构不同
						itemCatDataList3.add("/produts/"+itemCat3.getId()+".html|"+itemCat3.getName());
					}
					itemCatData2.setItems(itemCatDataList3);
					itemCatDataList2.add(itemCatData2);
				}
				itemCatData1.setItems(itemCatDataList2);
				itemCatDataList1.add(itemCatData1);	//首页的菜单要求只返回14条
				if(itemCatDataList1.size()>=14){
					break;
				}
			}
			result.setItemCats(itemCatDataList1);
			return result;
			
		}
}	

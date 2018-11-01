package com.chonger.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chonger.common.redis.RedisService;
import com.chonger.common.util.ObjectUtil;
import com.chonger.manage.mapper.ItemCatDescMapper;
import com.chonger.manage.mapper.ItemCatMapper;
import com.chonger.manage.pojo.ItemCat;
import com.chonger.manage.pojo.ItemCatDesc;
import com.chonger.manage.pojo.ItemCatResult;
import com.chonger.manage.util.ItemCatUtil;

@Service
public class ItemCatService {
	@Autowired
	private ItemCatMapper itemCatMapper;

	// 使用景区分类的mapper对象调用方法获取parent_id对应的下级分类list
	public List queryItemList(Long id) {
		// 利用id查询下级list
		// 参数是pojo对象,通用mapper会根据非空属性拼接where条件
		// where parent_id=#{id}
		ItemCat itemCat = new ItemCat();// 如果使用空对象,调用select方法
		// selectALl()
		itemCat.setParentId(id);
		List<ItemCat> itemCatList = itemCatMapper.select(itemCat);// parent_id=id
		return itemCatList;
	}

	public ItemCatResult queryAllItemCat() {
		// 查询所有景区分类的list
		List<ItemCat> list = itemCatMapper.selectAll();
		ItemCatResult result = ItemCatUtil.allItemCat(list);
		return result;
	}

	@Autowired
	private RedisService redis;
	@Autowired
	private ItemCatDescMapper itemCatDescMapper;

	// 查询类别详细信息
	public ItemCatDesc queryCatDescById(Long catId) {
		// 生成key
		String key = "ITEM_CAT_DESC_" + catId;
		ItemCatDesc itemCatDesc = new ItemCatDesc();
		if (redis.exists(key)) {// 存在
			// itemDesc对象中的desc属性字符串
			String desc = redis.get(key);

			itemCatDesc.setCatDesc(desc);
			return itemCatDesc;
		}
		itemCatDesc = itemCatDescMapper.selectByPrimaryKey(catId);
		String desc = itemCatDesc.getCatDesc();
		redis.set(key, desc);
		return itemCatDesc;
	}

	// 查询类别信息
	public ItemCat queryCatById(Long catId) {
		// 生成key
		/*String key1 = "ITEM_CAT_PARENTID" + catId;
		String key2 = "ITEM_CAT_NAME" + catId;
		String key3 = "ITEM_CAT_STATUS" + catId;
		String key4 = "ITEM_CAT_SORTORDER" + catId;
		String key5 = "ITEM_CAT_ISPARENT" + catId;
		ItemCat itemCat = new ItemCat();
		if (redis.exists(key1)) {// 存在
			// itemCat对象中的属性字符串
			String parentid = redis.get(key1);
			if (redis.exists(key2)){
				String name = redis.get(key2);
				if (redis.exists(key3)){
					String status = redis.get(key3);
					if (redis.exists(key4)){
						String sortorder = redis.get(key4);
						if (redis.exists(key5)){
							String isparent = redis.get(key5);
							return itemCat;
						}
					}
				}
			}
		}*/
		ItemCat itemCat = itemCatMapper.selectByPrimaryKey(catId);
		System.out.println("cs"+itemCat);
		/*String parentid = itemCat.getParentId().toString();
		String name = itemCat.getName();
		String status = itemCat.getStatus().toString();
		String sortrorder = itemCat.getSortOrder().toString();
		String isparent = itemCat.getIsParent().toString();*/
		/*redis.set(key1, parentid);
		redis.set(key2, name);
		redis.set(key3, status);
		redis.set(key4, sortrorder);
		redis.set(key5, isparent);*/
		return itemCat;
	}

}

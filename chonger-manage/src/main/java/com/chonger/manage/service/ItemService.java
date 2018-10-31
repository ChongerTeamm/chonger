package com.chonger.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chonger.common.redis.RedisService;
import com.chonger.common.vo.EasyUIResult;
import com.chonger.manage.mapper.ItemDescMapper;
import com.chonger.manage.mapper.ItemMapper;
import com.chonger.manage.pojo.Item;
import com.chonger.manage.pojo.ItemDesc;

@Service
public class ItemService {
	@Autowired
	private ItemMapper itemMapper;

	public EasyUIResult queryItemList(Integer page, Integer rows) {
		// item 的总数从通用mapper方法获取
		int total = itemMapper.selectCount(null);
		// 通用mapper不支持排序,分页,自定义xml映射文件的sql
		// 起始位置 start计算
		int start = (page - 1) * rows;
		List<Item> itemList = itemMapper.queryItemList(start, rows);
		// 封装返回数据
		EasyUIResult result = new EasyUIResult();
		result.setTotal(total);
		result.setRows(itemList);
		return result;
	}

	@Autowired
	private ItemDescMapper itemDescMapper;

	public void saveItem(Item item, String desc) {
		// 封装完整对象
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		// 如果使用insert方法,对于参数对象的所有属性进行插入拼接,空属性也做
		// insert into tb_item (id,title,status,sell_point,**) values
		// insertSelective,只判断非空属性,进行插入
		itemMapper.insertSelective(item);// id自增完成,

		// 封装desc对象
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getCreated());
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getId());// 能,持久层框架
		// 在每次插入数据后,select_last_insert() 获取主键
		itemDescMapper.insertSelective(itemDesc);

	}

	@Autowired
	private RedisService redis;

	public ItemDesc queryItemDesc(Long itemId) {
		// 生成key
		String key = "ITEM_DESC_" + itemId;
		ItemDesc itemDesc = new ItemDesc();
		if (redis.exists(key)) {// 存在
			// itemDesc对象中的desc属性字符串
			String desc = redis.get(key);

			itemDesc.setItemDesc(desc);
			return itemDesc;
		}
		itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		String desc = itemDesc.getItemDesc();
		redis.set(key, desc);
		return itemDesc;
	}

	public void updateItem(Item item, String desc) {
		// 封装缺少的数据
		item.setUpdated(new Date());
		// update set tb_item title="",sell_point="",*** where id=#{id}
		itemMapper.updateByPrimaryKeySelective(item);
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setUpdated(new Date());
		itemDesc.setItemDesc(desc);
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);

	}

	public Item queryItemById(Long itemId) {

		return itemMapper.selectByPrimaryKey(itemId);
	}

	// 景点删除
	public void deleteItem(Long id) {
		itemMapper.deleteByPrimaryKey(id);
		itemDescMapper.deleteByPrimaryKey(id);
	}

}

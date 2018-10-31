package com.chonger.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chonger.common.mapper.MyMapper;
import com.chonger.manage.pojo.Item;

public interface ItemMapper extends MyMapper<Item>{

	List<Item> queryItemList(@Param("start")int start,@Param("rows")Integer rows);

}

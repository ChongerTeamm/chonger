package com.chonger.web.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chonger.web.pojo.Item;

@Service
public class SearchService {
	@Autowired
	private TransportClient client;
	public List<Item> findItems(String q,Integer page) {
		//构造查询条件,matchQuery
		MatchQueryBuilder query = QueryBuilders.
				matchQuery("title", q).
				operator(Operator.AND);
		SearchResponse response = client.prepareSearch("cedb_item").//关联的分片的名字
		setQuery(query).setFrom(60*(page-1)).setSize(60).get();
		
		SearchHits hits = response.getHits();
		//从hits中封装 itemList
		List<Item> itemList=new ArrayList<Item>();
		for (SearchHit hit: hits) {
			//封装数据 price title sellpoint image id
			Item item=new Item();
			item.setId((int)hit.getSource().get("id")+0L);
			item.setPrice((int)hit.getSource().get("price")+0l);
			item.setTitle(hit.getSource().get("title")+"");
			item.setImage(hit.getSource().get("image")+"");
			item.setSellPoint(hit.getSource().get("focus_point")+"");
			itemList.add(item);
		}
		return itemList;
	}

}
package com.chonger.web.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tb_cat_desc")
public class ItemCatDesc {

	@Id
	private Long catId;
	private String catDesc;
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	public String getCatDesc() {
		return catDesc;
	}
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	@Override
	public String toString() {
		return "ItemCatDesc [catId=" + catId + ", catDesc=" + catDesc + "]";
	}
	
}

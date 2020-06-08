package com.kdj.mlink.ezup3.shop.domesin;

public class CategoryItemDomesinDto {
	
	public CategoryItemDomesinDto() {}
	
	public CategoryItemDomesinDto(String cid, String name) {
		this.cid =cid; //  ∑Á∆Æ .. 
		this.name = name;
	}
	
	public String cid;
	public String name;
	public String deep;

	@Override
	public String toString() {
		return name;
	}

	public String parent_cid;
	public String ca_qty;
	public String fullcat;
	public String ec;
}

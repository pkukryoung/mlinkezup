package com.kdj.mlink.ezup3.shop.dao;

public class ShopCateDto {
	private String shopcd ;
	private String code ;
	private String name ;
	private String parent_code ;
	private Integer level ;
	private Integer sort_idx;
	private String comment;
	private String use_yn;
	
	public String getShopcd() {
		return shopcd;
	}
	public void setShopcd(String shopcd) {
		this.shopcd = shopcd;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent_code() {
		return parent_code;
	}
	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getSort_idx() {
		return sort_idx;
	}
	public void setSort_idx(Integer sort_idx) {
		this.sort_idx = sort_idx;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	
	
}

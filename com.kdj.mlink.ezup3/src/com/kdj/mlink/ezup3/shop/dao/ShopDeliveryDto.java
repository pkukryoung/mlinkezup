package com.kdj.mlink.ezup3.shop.dao;

public class ShopDeliveryDto {
	private String  shopCd ;      // ���ڵ� .. 
	private String  dlvID ;  // �ù�� �ڵ� 
	private String  divNM ;    // �ù�� �̸�..
	private String urlcode;
	
	
	public String getUrlcode() {
		return urlcode;
	}
	public void setUrlcode(String urlcode) {
		this.urlcode = urlcode;
	}
	public String getShopCd() {
		return shopCd;
	}
	public void setShopCd(String shopCd) {
		this.shopCd = shopCd;
	}
	public String getDlvID() {
		return dlvID;
	}
	public void setDlvID(String dlvID) {
		this.dlvID = dlvID;
	}
	public String getDivNM() {
		return divNM;
	}
	public void setDivNM(String divNM) {
		this.divNM = divNM;
	}
	 
	
	
	
}

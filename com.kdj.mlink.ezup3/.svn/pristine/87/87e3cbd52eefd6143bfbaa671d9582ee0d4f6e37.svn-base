package com.kdj.mlink.ezup3.shop.dao;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopOrderMstDtoList {
	
	
	@XmlElement(name = "result_code")
	private String result_code = "";

	@XmlElement(name = "result_text")
	private String result_text = "";

	private String ShopCd = "";
	private String ShopNm = "";
	private String ShoppingID = "";
    
	private String start_dt;
	private String end_dt;
	private String ShopPw;
	
	public String getStart_dt() {
		return start_dt;
	}

	public void setStart_dt(String start_dt) {
		this.start_dt = start_dt;
	}

	public String getEnd_dt() {
		return end_dt;
	}

	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}

	@XmlElement(name = "order")
	private List<ShopOrderMstDto> list;
	
	public ShopOrderMstDtoList() {
		list = new ArrayList<ShopOrderMstDto>();
	}
	
	public String getShopCd() {
		return ShopCd;
	}

	public void setShopCd(String shopCd) {
		ShopCd = shopCd;
	}

	public String getShopNm() {
		return ShopNm;
	}

	
	public String getShopPw() {
		return ShopPw;
	}

	public void setShopPw(String shopPw) {
		ShopPw = shopPw;
	}

	public void setShopNm(String shopNm) {
		ShopNm = shopNm;
	}

	public String getShoppingID() {
		return ShoppingID;
	}

	public void setShoppingID(String shoppingID) {
		ShoppingID = shoppingID;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getResult_text() {
		return result_text;
	}

	public void setResult_text(String result_text) {
		this.result_text = result_text;
	}

	public List<ShopOrderMstDto> getList() {
		return list;
	}

	public void setList(List<ShopOrderMstDto> list) {
		this.list = list;
	}
	
}
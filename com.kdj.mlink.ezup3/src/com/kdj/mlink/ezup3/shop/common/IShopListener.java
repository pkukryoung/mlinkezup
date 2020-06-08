package com.kdj.mlink.ezup3.shop.common;

public interface IShopListener {
	
	public void addEventListner(IShopListener listener );
	public void onEvent(String val);
}

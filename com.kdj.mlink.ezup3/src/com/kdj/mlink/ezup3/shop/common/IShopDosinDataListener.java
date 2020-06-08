package com.kdj.mlink.ezup3.shop.common;

import java.util.Map;

import com.kdj.mlink.ezup3.shop.domesin.ProductResult;

public interface IShopDosinDataListener {
	
	public void onDataBind(Map<String, ProductResult> datalist,Object listener);
}

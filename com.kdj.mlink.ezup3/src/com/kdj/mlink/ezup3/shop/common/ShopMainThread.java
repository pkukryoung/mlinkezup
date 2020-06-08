package com.kdj.mlink.ezup3.shop.common;

import java.util.LinkedList;
import java.util.List;

import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductSendDto;

public class ShopMainThread {
	private boolean isRunning = false;
	
	
	
	
	private static ShopMainThread instance = new ShopMainThread();
	private ShopMainThread() {}
	
	public static ShopMainThread get() {
		return instance ;
	}
	
	
	public void start(String shopinfo, Integer count, ShopProductSendDto sendDto, boolean flag, String sendshop) {
		if(flag) {
			isRunning = true;
			Thread t = new Thread(new ShopMainDaemonWork(shopinfo, count, sendDto,flag,sendshop));
			t.setDaemon(true);	
			t.start();	
		}
	}
	
	
}

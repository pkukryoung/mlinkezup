package com.kdj.mlink.ezup3.shop.common;

import java.util.LinkedList;
import java.util.List;

import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;

public class WorkTaskHelper {
	private boolean isRunning = false;
	
	
	
	
	private static WorkTaskHelper instance = new WorkTaskHelper();
	private WorkTaskHelper() {}
	
	public static WorkTaskHelper get() {
		return instance ;
	}
	
	
	public void start() {
		if(!isRunning) {
			isRunning = true;
			Thread t = new Thread(new WorkThread());
			t.setDaemon(true);	
			t.start();	
			System.out.println("스레드이름:" + t.currentThread().getName());
		}
	}
	
	
}

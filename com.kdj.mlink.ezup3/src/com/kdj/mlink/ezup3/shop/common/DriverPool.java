package com.kdj.mlink.ezup3.shop.common;

import org.openqa.selenium.chrome.ChromeDriver;

public class DriverPool {
	private ChromeDriver chromeDriver;
	private boolean isUse;   // 현재 사용중인가.. 
	private boolean isComplete;
	private Integer key;
	public DriverPool(ChromeDriver chromeDriver, Integer key) {
		this.chromeDriver = chromeDriver;
		isComplete = false;
		isUse = false; 
		this.key = key;
	}
	
	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public ChromeDriver getChromeDriver() {
		return chromeDriver;
	}
	public void setChromeDriver(ChromeDriver chromeDriver) {
		this.chromeDriver = chromeDriver;
	}
	public boolean isUse() {
		return isUse;
	}
	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}
}

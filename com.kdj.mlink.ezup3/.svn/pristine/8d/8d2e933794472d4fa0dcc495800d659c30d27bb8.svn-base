package com.kdj.mlink.ezup3.shop.common;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.swt.widgets.Display;
import org.openqa.selenium.chrome.ChromeDriver;

import com.kdj.mlink.ezup3.shop.common.DriverPool;
import com.kdj.mlink.ezup3.shop.common.IProductMultiple;
import com.kdj.mlink.ezup3.shop.dao.ShopCommonDao;
import com.kdj.mlink.ezup3.shop.dao.ShopProductAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductAuctionAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDao;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductSendDto;
import com.kdj.mlink.ezup3.shop.dao.ShopWorkDao;
import com.kdj.mlink.ezup3.shop.dao.ShopWorkDto;
import com.kdj.mlink.ezup3.shop.dao.ShoppingMallDetailDto;
import com.kdj.mlink.ezup3.ui.ShopTaskManager;

public class ShopMainDaemonWork implements Runnable {

	ShopProductDto dto;
	ShopProductAuctionAdditionDto dtllist;
	ShoppingMallDetailDto idlist;
	DriverPool driverPool;
	ChromeDriver driver;
	int count;
	String shopinfo;
	GridTableViewer gtv_all;
	GridTableViewer gtv_prod;
	GridTableViewer gtv_order;
	GridTableViewer gtv_quest;
	ShopProductSendDto sendDto;
	boolean flag;
	String sendshop;
	/*
	 * »ý¼ºÀÚ..
	 */
	public ShopMainDaemonWork(String shopinfo, int count, ShopProductSendDto sendDto, boolean flag, String sendshop) {
		this.shopinfo = shopinfo;
		this.count = count;
		this.sendDto = sendDto;
		this.flag = flag;
		this.sendshop = sendshop;
	}

	@Override
	public synchronized void run() {
		try {
			while (flag) {			
				ProductService service = new ProductService(ProductFactory.getRegisterBean(sendshop));
				sendDto = service.excute(sendDto);

				flag = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

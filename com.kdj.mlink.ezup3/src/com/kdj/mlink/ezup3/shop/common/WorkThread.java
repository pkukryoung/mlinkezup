package com.kdj.mlink.ezup3.shop.common;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.openqa.selenium.chrome.ChromeDriver;

import com.kdj.mlink.ezup3.shop.dao.ShopProductAuctionAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.shop.dao.ShopWorkDao;
import com.kdj.mlink.ezup3.shop.dao.ShopWorkDto;
import com.kdj.mlink.ezup3.shop.dao.ShoppingMallDetailDto;
import com.kdj.mlink.ezup3.ui.ShopTaskManager;

public class WorkThread implements Runnable {

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

	/*
	 * ������..
	 */
	public WorkThread() {

	}

	@Override
	public void run() {
		try {
			while (true) {

				List<ShopWorkDto> list = ShopWorkDao.get().getWorkAllList();
				List<ShopWorkDto> gtv_prod = list.stream().filter(p -> p.getWorkcd().equals(WorkStatus.��ǰ���))
						.collect(Collectors.toList());
				List<ShopWorkDto> gtv_order = list.stream().filter(p -> p.getWorkcd().equals(WorkStatus.�ֹ�����))
						.collect(Collectors.toList());
				List<ShopWorkDto> gtv_quest = list.stream().filter(p -> p.getWorkcd().equals(WorkStatus.���Ǽ���))
						.collect(Collectors.toList());
				if (list.size() > 0) {
					ShopTaskManager.setGridSetting(list, gtv_prod, gtv_order, gtv_quest);
				}

				// System.out.println("��ӵ�??");
				Thread.sleep(10000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import com.kdj.mlink.ezup3.shop.dao.ShopProductAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.shop.dao.ShoppingMallDetailDto;
//import com.kdj.mlink.ezup3.shop.data.auction.AuctionProductRegService;
//import com.kdj.mlink.ezup3.shop.data.cafe24.Cafe24ProductRegService;
//import com.kdj.mlink.ezup3.shop.data.elevenst.ElevenstProductRegService;
//import com.kdj.mlink.ezup3.shop.data.interpark.InterParkProductRegService;
//import com.kdj.mlink.ezup3.shop.data.naverstore.NaverStoreProductRegService;

public class ProductFactory {

	public static Runnable getRegisterRunalbeList(List<ShopProductDto> list, ShopProductAdditionDto dtllist,
			ShoppingMallDetailDto idlist, DriverPool driverPool, int cnt, String shopinfo,
			IShopProductListener listener) {

		String shopcd = idlist.getShopcd();

		return null;
	}

	public static Runnable getRegisterRunalbe(ShopProductDto dto, ShopProductAdditionDto dtllist,
			ShoppingMallDetailDto idlist, DriverPool driverPool, int cnt, String shopinfo,
			IShopProductListener listener) {

		String shopcd = idlist.getShopcd();
		switch (shopcd) {
		case ShopCommon.옥션:
			// return new AuctionProductRegService(dto,dtllist, idlist, driverPool,cnt,
			// shopinfo, listener);
		case ShopCommon.지마켓:
			// return new AuctionProductRegService(dto,dtllist, idlist, driverPool,cnt,
			// shopinfo, listener);
		case ShopCommon.인터파크:
			// return new InterParkProductRegService(dto,dtllist, idlist, driverPool,cnt,
			// shopinfo, listener);
		case ShopCommon.스토어팜:
			// return new NaverStoreProductRegService(dto,dtllist, idlist, driverPool,cnt,
			// shopinfo, listener);
		case ShopCommon.카페24:
		}
		return null;
	}

	public static IProduct getRegisterBean(String shopcd) {

		return null;
	}

	/*
	 * 수정
	 */
	public static IProduct getModifyBean(String shopcd) {
		return null;
	}

}

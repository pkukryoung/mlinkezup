package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;

public class ShopOrderContext {
	
	private static ShopOrderContext instance = new ShopOrderContext();
	
	private ShopOrderContext() {}
	
	public static ShopOrderContext get() {
		return instance;
	}
	
	IShopOrderCommand command = null;
	
	public ShopOrderContext  setContext(IShopOrderCommand command) {
		this.command = command;
		return this;
	}
	
	/*
	 * 실행.. 
	 */
	public List<ShopOrderMstDto> excute(List<ShopOrderMstDto> param) throws Exception {
		String id = param.get(0).getShop_userid();
		String pw = param.get(0).getShopPw();
		String shopid = param.get(0).getShopid();
		String authKey = param.get(0).getAuthkey1();
		String gubun = "";
		
		
		
		//옥션  : SHOP0067    지마켓.. :  SHOP0068
		if(shopid.toUpperCase().equals("SHOP0067")) gubun = "0";
				
		if(shopid.toUpperCase().equals("SHOP0068")) gubun= "1";
		
		if(shopid.toUpperCase().equals("SHOP0004")) gubun= authKey  ;
			
		command.Login(id, pw,gubun);
		return command.Completed(param);
	}


}

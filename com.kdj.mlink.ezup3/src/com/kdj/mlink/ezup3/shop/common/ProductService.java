package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;

import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductSendDto;
import com.kdj.mlink.ezup3.shop.dao.ShoppingMallDetailDto;

public class ProductService {
	 IProduct product;
     public  ProductService(IProduct product){
         this.product = product;
     }

     
     /*
      *  실행.. 
      */
     public  ShopProductSendDto excute(ShopProductSendDto list) throws Exception {
    	 
    	 if(list.getMode().isEmpty()) {
    		return  insert(list);
    	 }
    	 
    	 return modify(list);
     }
     /*
      *  로그인.. 
      */
     public  void  urlLogin(List<String> selData) throws Exception {
    	 String loginId = selData.get(1);
    	 String password = selData.get(2);
    	 String shopid = selData.get(3);
    	 
    	 this.product.login(loginId, password , shopid,true);
    
     }
     /*
      *  상품 등록.. 
      */
     public  ShopProductSendDto insert(ShopProductSendDto list) throws Exception {
    	 ShoppingMallDetailDto  shoppingMallDetailDto = list.getShoppingMallDetailDto();
    	 String loginId = shoppingMallDetailDto.getShoppingid();
    	 String password = shoppingMallDetailDto.getPassword();
    	 String shopid = shoppingMallDetailDto.getShopcd();
    	// this.product.login(loginId, password , shopid);
         return this.product.excute(list);
     }
     
     public IProduct getProduct() {
		return product;
	}


	public void setProduct(IProduct product) {
		this.product = product;
	}


	/*
      *  상품수정.. 
      */
     public  ShopProductSendDto modify(ShopProductSendDto list) throws Exception {
    	 ShoppingMallDetailDto  shoppingMallDetailDto = list.getShoppingMallDetailDto();
    	 String loginId = shoppingMallDetailDto.getShoppingid();
    	 String password = shoppingMallDetailDto.getPassword();
    	 String shopid = shoppingMallDetailDto.getShopcd();
    	 
    	 this.product.login(loginId, password , shopid,false);
         return this.product.excute(list);
     }
     
     
}

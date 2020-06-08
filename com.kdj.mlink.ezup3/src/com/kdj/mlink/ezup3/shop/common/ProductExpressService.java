package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;

public class ProductExpressService {
	IShopExpress express;
     public  ProductExpressService(IShopExpress express){
         this.express = express;
     }
     /*
      *  ½ÇÇà.. 
      */
     public  List<ShopOrderMstDto> excute(List<ShopOrderMstDto> map) throws Exception {
         return this.express.excute(map);
     }
     
}

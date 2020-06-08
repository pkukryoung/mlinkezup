
package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import com.kdj.mlink.ezup3.shop.dao.ShopCategoryDto;


public interface IShopCategoryCommand {
	
	/*
	 *  로그인. 
	 */
	public  void Login(String loginID, String loginPw,String shopcd) throws Exception;
	
	/*
	 *  조회..
	 *  리턴값 조회했을때 값이 있는지 없는지.. 
	 */
	public  int Search(String URL,String startdt, String enddt) throws Exception;
	
	/*
	 *   처리  결과 .. 
	 */
	public  List<ShopCategoryDto> Completed(List<ShopCategoryDto> param) throws Exception;
	
	
}

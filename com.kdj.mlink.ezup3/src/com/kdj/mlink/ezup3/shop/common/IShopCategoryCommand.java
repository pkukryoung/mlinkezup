
package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import com.kdj.mlink.ezup3.shop.dao.ShopCategoryDto;


public interface IShopCategoryCommand {
	
	/*
	 *  �α���. 
	 */
	public  void Login(String loginID, String loginPw,String shopcd) throws Exception;
	
	/*
	 *  ��ȸ..
	 *  ���ϰ� ��ȸ������ ���� �ִ��� ������.. 
	 */
	public  int Search(String URL,String startdt, String enddt) throws Exception;
	
	/*
	 *   ó��  ��� .. 
	 */
	public  List<ShopCategoryDto> Completed(List<ShopCategoryDto> param) throws Exception;
	
	
}

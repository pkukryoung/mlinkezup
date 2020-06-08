package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import com.google.gson.JsonElement;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;

public interface IShopOrderCommand {

	/*
	 * �α���.
	 */
	void Login(String loginID, String loginPw, String gubun) throws Exception;

	/*
	 * ��ȸ.. ���ϰ� ��ȸ������ ���� �ִ��� ������..
	 */
	int Search(String URL, String startdt, String enddt) throws Exception;

	/*
	 * ��ȸ.. ���ϰ� ��ȸ������ ���� �ִ��� ������..
	 */
	int Search_conf(String URL, String startdt, String enddt, String searchkeyword) throws Exception;

	/*
	 * ó�� ��� ..
	 */
	List<ShopOrderMstDto> Completed(List<ShopOrderMstDto> param) throws Exception;

	/*
	 * json ��Ʈ��..
	 */
	default String jsonElementNullStr(JsonElement val) {

		if (val.isJsonNull())
			return "";
		return val.getAsString();
	}

	/*
	 *
	 */
	static IShopOrderCommand getContext(String shopid, String command) throws Exception {

		IShopOrderCommand instance = null;

		return instance;
	}

}

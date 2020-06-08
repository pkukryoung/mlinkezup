package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import com.google.gson.JsonElement;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;

public interface IShopOrderCommand {

	/*
	 * 로그인.
	 */
	void Login(String loginID, String loginPw, String gubun) throws Exception;

	/*
	 * 조회.. 리턴값 조회했을때 값이 있는지 없는지..
	 */
	int Search(String URL, String startdt, String enddt) throws Exception;

	/*
	 * 조회.. 리턴값 조회했을때 값이 있는지 없는지..
	 */
	int Search_conf(String URL, String startdt, String enddt, String searchkeyword) throws Exception;

	/*
	 * 처리 결과 ..
	 */
	List<ShopOrderMstDto> Completed(List<ShopOrderMstDto> param) throws Exception;

	/*
	 * json 스트링..
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

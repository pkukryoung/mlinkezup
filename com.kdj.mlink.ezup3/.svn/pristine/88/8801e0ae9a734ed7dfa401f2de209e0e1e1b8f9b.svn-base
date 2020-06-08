package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import com.kdj.mlink.ezup3.data.dao.QuestListDto;

public interface IShopQuestion {
	/*
	 * 로그인.
	 */
	void login(String loginID, String loginPw, String shopnm, String authkey) throws Exception;

	/*
	 * 조회.. 리턴값 조회했을때 값이 있는지 없는지..
	 */
	boolean search(String URL, String startdt, String enddt) throws Exception;

	/*
	 * 처리 결과 ..
	 */
	List<QuestListDto> excute(List<QuestListDto> args) throws Exception;

	static IShopQuestion getSearchBean(String shopid) {

		return null;
	}

	static IShopQuestion getEnrollmentBean(String shopid) {

		return null;
	}
}

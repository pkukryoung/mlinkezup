package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import com.kdj.mlink.ezup3.data.dao.QuestListDto;

public interface IShopQuestion {
	/*
	 * �α���.
	 */
	void login(String loginID, String loginPw, String shopnm, String authkey) throws Exception;

	/*
	 * ��ȸ.. ���ϰ� ��ȸ������ ���� �ִ��� ������..
	 */
	boolean search(String URL, String startdt, String enddt) throws Exception;

	/*
	 * ó�� ��� ..
	 */
	List<QuestListDto> excute(List<QuestListDto> args) throws Exception;

	static IShopQuestion getSearchBean(String shopid) {

		return null;
	}

	static IShopQuestion getEnrollmentBean(String shopid) {

		return null;
	}
}

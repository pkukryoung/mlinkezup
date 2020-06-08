package com.kdj.mlink.ezup3.shop.domesin;

import java.util.Arrays;
import java.util.List;

import com.kdj.mlink.ezup3.shop.common.ShopCommon;



public class DomesinSessonUtil {
	
	public static List<String>  ManagerShop = 
	Arrays.asList(ShopCommon.����, ShopCommon.������ũ, ShopCommon.������, ShopCommon.����,ShopCommon.���Ϲ���
			,ShopCommon.�������);
	
	private static DomesinSessonUtil instance = new DomesinSessonUtil();
	
	private DomesinSessonUtil() {}
	
	
	public static DomesinSessonUtil get() {
		return instance;
	}
	
	//  �α��� �� �޽���.. 
	public  String ResultLoginText;
	
	// �α��� ����.. 
	LoginResponseDto userInfo;
    
	//  ī�װ� ���� .
	List<CategoryItemDomesinDto> largeCategory;
	List<CategoryItemDomesinDto> midCategory;
	List<CategoryItemDomesinDto> smallCategory;
	
	List<CategoryItemDomesinDto> allCategory;
	
	public List<CategoryItemDomesinDto> getAllCategory() {
		return allCategory;
	}


	public void setAllCategory(List<CategoryItemDomesinDto> allCategory) {
		this.allCategory = allCategory;
	}


	//  ����� ����.. 
	public LoginResponseDto getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(LoginResponseDto userInfo) {
		this.userInfo = userInfo;
	}
	
	
	public String getUserId() {
		return this.userInfo.m_id;
	}
	
	public String getApiKey() {
		return this.userInfo.api_key;
	}


	public List<CategoryItemDomesinDto> getLargeCategory() {
		return largeCategory;
	}


	public void setLargeCategory(List<CategoryItemDomesinDto> largeCategory) {
		this.largeCategory = largeCategory;
	}


	public List<CategoryItemDomesinDto> getMidCategory() {
		return midCategory;
	}


	public void setMidCategory(List<CategoryItemDomesinDto> midCategory) {
		this.midCategory = midCategory;
	}


	public List<CategoryItemDomesinDto> getSmallCategory() {
		return smallCategory;
	}


	public void setSmallCategory(List<CategoryItemDomesinDto> smallCategory) {
		this.smallCategory = smallCategory;
	}
	
	
	public String getParentClassCd(String cid) {
		return     this.allCategory.stream().filter(p -> p.cid.equals(cid)).map(p -> p.parent_cid).findAny().orElse("");
	}

	public String getCateName(String cid) {
		return this.allCategory.stream().filter(p -> p.cid.equals(cid)).map(p -> p.name).findAny().orElse("");
	}

	public String getFullCateName(String cid) {
		return this.allCategory.stream().filter(p -> p.cid.equals(cid)).map(p -> p.fullcat).findAny().orElse("");
	}
	
	
	

}

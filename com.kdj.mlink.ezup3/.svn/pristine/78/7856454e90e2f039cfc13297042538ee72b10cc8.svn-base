package com.kdj.mlink.ezup3.shop.dao;

import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.shop.domesin.DomesinMarginDto;

public class ShopProductSendDto {
	List<ShopProductDto>  shopProductDto;    // 전송 쇼핑몰 정보.. 
	ShopProductAdditionDto  productAdditionDto; //  부가정보.
	ShoppingMallDetailDto shoppingMallDetailDto;  // 사용자 인증정보.. 
	DomesinMarginDto domesinMarginDto;//마켓판매가
	
	private int multicnt;//동시갯수
	
	
	
	public int getMulticnt() {
		return multicnt;
	}

	public void setMulticnt(int multicnt) {
		this.multicnt = multicnt;
	}

	private boolean Isshopprodin;	// shopprodinfo = '0' , shopprodin = '1'
	public boolean getIsshopprodin() {
		return Isshopprodin;
	}

	public void setIsshopprodin(boolean isshopprodin) {
		Isshopprodin = isshopprodin;
	}
	
	private String mode;   // 인서트 일경우 빈값 .. 업데이트 일경우 에 U 로 전송한다..  
	private List<String> updateFlag;   // 업데이트 플래그.. 
	public String getMode() {
		return mode;
	}

	
	public void addUpdateFlag(String flag) {
		if(updateFlag == null) {
			updateFlag = new ArrayList<>();
		}
		updateFlag.add(flag);
	}
	
	public List<String> getUpdateFlag(){
		return updateFlag;
	}
	
	

	public void setMode(String mode) {
		this.mode = mode;
	}


	public ShopProductSendDto() {
		mode = "";
	}
	
	
	public List<ShopProductDto> getShopProductDto() {
		return shopProductDto;
	}
	
	public void setShopProductDto(List<ShopProductDto> shopProductDto) {
		this.shopProductDto = shopProductDto;
	}
	
	public ShopProductAdditionDto getProductAdditionDto() {
		return productAdditionDto;
	}
	public void setProductAdditionDto(ShopProductAdditionDto productAdditionDto) {
		this.productAdditionDto = productAdditionDto;
	}
	public ShoppingMallDetailDto getShoppingMallDetailDto() {
		return shoppingMallDetailDto;
	}
	public void setShoppingMallDetailDto(ShoppingMallDetailDto shoppingMallDetailDto) {
		this.shoppingMallDetailDto = shoppingMallDetailDto;
	}
	public void setDomesinMarginDto(DomesinMarginDto domesinMarginDto) {
		this.domesinMarginDto = domesinMarginDto;
	}
	public DomesinMarginDto getDomesinMarginDto() {
		return domesinMarginDto;
	}
	
}

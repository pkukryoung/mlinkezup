package com.kdj.mlink.ezup3.shop.domesin;

public class DomesinOption {
	 private  String optionName1 ;     // �ɼ� �̸�..
     private  String optionName2 ;     // �ɼ� �̸�..
     private  int optionPrice ;   // �ɼǰ���
     private  int compliancePrice ;  // �ؼ���
     private  int retailPrice ; //  �Һ��ڰ���
     private  int soldOut;    //  ǰ������   0 �Ǹ��� , 1, ǰ��, 2 ����. 
	public String getOptionName1() {
		return optionName1;
	}
	public void setOptionName1(String optionName1) {
		this.optionName1 = optionName1;
	}
	public String getOptionName2() {
		return optionName2;
	}
	public void setOptionName2(String optionName2) {
		this.optionName2 = optionName2;
	}
	public int getOptionPrice() {
		return optionPrice;
	}
	public void setOptionPrice(int optionPrice) {
		this.optionPrice = optionPrice;
	}
	public int getCompliancePrice() {
		return compliancePrice;
	}
	public void setCompliancePrice(int compliancePrice) {
		this.compliancePrice = compliancePrice;
	}
	public int getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(int retailPrice) {
		this.retailPrice = retailPrice;
	}
	public int getSoldOut() {
		return soldOut;
	}
	public void setSoldOut(int soldOut) {
		this.soldOut = soldOut;
	}
     
     
}

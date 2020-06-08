package com.kdj.mlink.ezup3.shop.dao;

public class ShopOptionDto {
	private  String name;  // 상품명..
	private  String cnt;  // 옵션 갯수
	private  Integer rowNum; 
	
	
	public ShopOptionDto() {}
	
	@Override
	public String toString() {
		return "Option{" +
			"name='" + name + '\'' +
			", cnt='" + cnt + '\'' +
			", amt='" + amt + '\'' +
		'}';
	}

	public int compareTo(ShopOptionDto o) {
		return Integer.compare(this.rowNum, o.rowNum);
	}
      
	public ShopOptionDto( String name, String cnt, String amt) {
		this.name = name;
		this.cnt = cnt;
		this.amt = amt;
	}
      
      
	public ShopOptionDto(int rowNum, String name, String cnt, String amt) {
		this.rowNum = rowNum;
		this.name = name;
		this.cnt = cnt;
		this.amt = amt;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public String getName() {
          return name;
      }

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCnt() {
		return cnt;
	}
	
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	
	public String getAmt() {
		return amt;
	}
	
	public void setAmt(String amt) {
		this.amt = amt;
	}
	
	private  String amt;
}

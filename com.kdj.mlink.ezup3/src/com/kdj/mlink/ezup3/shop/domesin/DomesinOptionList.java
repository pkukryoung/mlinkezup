package com.kdj.mlink.ezup3.shop.domesin;

import java.util.List;

public class DomesinOptionList {
	int dan; 
	 private  String char_1_nm ;  // 옵션이 없을경우엔는 단품 이라고 입력합니다.
     private  String char_1_val;  // 컬러 옵션  옵션항목명1^^재고수량^^추가금액^^별칭^^EA^^옵션공급상태^^옵션모음전여부^^옵션모음전연결상품코드^^안전재고수량
     private  String char_2_nm;   //  예) 사이즈
     private  String char_2_val;  //  예 : 44,55,66,77 (각 항목은 반드시 콤마로 구분함,기술된 순서대로 일련된 코드 부여됨)
	
    public List<DomesinOption> options;
	public int getDan() {
		return dan;
	}
	public void setDan(int dan) {
		this.dan = dan;
	}
	public String getChar_1_nm() {
		return char_1_nm;
	}
	public void setChar_1_nm(String char_1_nm) {
		this.char_1_nm = char_1_nm;
	}
	public String getChar_1_val() {
		return char_1_val;
	}
	public void setChar_1_val(String char_1_val) {
		this.char_1_val = char_1_val;
	}
	public String getChar_2_nm() {
		return char_2_nm;
	}
	public void setChar_2_nm(String char_2_nm) {
		this.char_2_nm = char_2_nm;
	}
	public String getChar_2_val() {
		return char_2_val;
	}
	public void setChar_2_val(String char_2_val) {
		this.char_2_val = char_2_val;
	}
	public List<DomesinOption> getOptions() {
		return options;
	}
	public void setOptions(List<DomesinOption> options) {
		this.options = options;
	}
	
	
	
}

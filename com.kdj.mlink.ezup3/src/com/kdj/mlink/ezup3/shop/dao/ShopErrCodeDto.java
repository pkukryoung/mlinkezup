package com.kdj.mlink.ezup3.shop.dao;

import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.data.dao.ProductAdditionDto;

public class ShopErrCodeDto {

	private String code;//상품명
	private String content;//상품명
	private String treatcontent;//상품명
	private String use_yn;//상품명
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTreatcontent() {
		return treatcontent;
	}
	public void setTreatcontent(String treatcontent) {
		this.treatcontent = treatcontent;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	
}

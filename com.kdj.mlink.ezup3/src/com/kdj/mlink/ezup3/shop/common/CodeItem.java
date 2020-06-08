package com.kdj.mlink.ezup3.shop.common;

public class CodeItem {
	private String code;
	private String fieldName;
	private String name;
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public CodeItem() {}
	
	public CodeItem(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public CodeItem(String code, String name, String fieldName) {
		this.code = code;
		this.name = name;
		this.fieldName = fieldName;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

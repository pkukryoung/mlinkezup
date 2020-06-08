package com.kdj.mlink.ezup3.shop.dao;

import javax.xml.bind.annotation.XmlElement;

/*
 *  결과 dto. 
 */
public class ResultDto {
	private int resultCode;  // = 0 이면 성공 -1 이면 실패..
	private String resultMessage;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
}

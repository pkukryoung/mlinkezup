package com.kdj.mlink.ezup3.shop.dao;

import javax.xml.bind.annotation.XmlElement;

/*
 *  ��� dto. 
 */
public class ResultDto {
	private int resultCode;  // = 0 �̸� ���� -1 �̸� ����..
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

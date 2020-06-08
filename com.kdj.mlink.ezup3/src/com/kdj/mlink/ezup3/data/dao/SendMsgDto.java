package com.kdj.mlink.ezup3.data.dao;

import java.sql.Timestamp;

public class SendMsgDto {

	String CMID;
	String MSG_TYPE;
	Timestamp REQUEST_TIME;
	Timestamp SEND_TIME;
	String DEST_PHONE;
	String SEND_PHONE;
	String MSG_BODY;
	
	public SendMsgDto() {
		
	}

	public String getCMID() {
		return CMID;
	}

	public void setCMID(String cMID) {
		CMID = cMID;
	}

	public String getMSG_TYPE() {
		return MSG_TYPE;
	}

	public void setMSG_TYPE(String mSG_TYPE) {
		MSG_TYPE = mSG_TYPE;
	}

	public Timestamp getREQUEST_TIME() {
		return REQUEST_TIME;
	}

	public void setREQUEST_TIME(Timestamp rEQUEST_TIME) {
		REQUEST_TIME = rEQUEST_TIME;
	}

	public Timestamp getSEND_TIME() {
		return SEND_TIME;
	}

	public void setSEND_TIME(Timestamp sEND_TIME) {
		SEND_TIME = sEND_TIME;
	}

	public String getDEST_PHONE() {
		return DEST_PHONE;
	}

	public void setDEST_PHONE(String dEST_PHONE) {
		DEST_PHONE = dEST_PHONE;
	}

	public String getSEND_PHONE() {
		return SEND_PHONE;
	}

	public void setSEND_PHONE(String sEND_PHONE) {
		SEND_PHONE = sEND_PHONE;
	}

	public String getMSG_BODY() {
		return MSG_BODY;
	}

	public void setMSG_BODY(String mSG_BODY) {
		MSG_BODY = mSG_BODY;
	}
	public void printMsg() {
		System.out.println("CMID " + CMID + " MSG_TYPE " + MSG_TYPE+ " REQUEST_TIME " + REQUEST_TIME+ " SEND_TIME " + SEND_TIME+ " DEST_PHONE " + DEST_PHONE+ " SEND_PHONE " + SEND_PHONE + " MSG_BODY " + MSG_BODY);
			
	}
}

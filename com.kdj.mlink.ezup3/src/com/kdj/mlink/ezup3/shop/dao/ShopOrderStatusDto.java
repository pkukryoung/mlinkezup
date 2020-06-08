package com.kdj.mlink.ezup3.shop.dao;

public class ShopOrderStatusDto {
	private int ordseq;
	private int seq;
	private int compono;
	private int shopid;
	private String shopnm;
	private String shopuserid;
	private String order_status;
	private String statusname;
	private String resultMessage;
	private String senddt;
	public int getOrdseq() {
		return ordseq;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public String getShopnm() {
		return shopnm;
	}
	public void setShopnm(String shopnm) {
		this.shopnm = shopnm;
	}
	public String getStatusname() {
		return statusname;
	}
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public void setOrdseq(int ordseq) {
		this.ordseq = ordseq;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getCompono() {
		return compono;
	}
	public void setCompono(int compono) {
		this.compono = compono;
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public String getShopuserid() {
		return shopuserid;
	}
	public void setShopuserid(String shopuserid) {
		this.shopuserid = shopuserid;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getSenddt() {
		return senddt;
	}
	public void setSenddt(String senddt) {
		this.senddt = senddt;
	}
	
}

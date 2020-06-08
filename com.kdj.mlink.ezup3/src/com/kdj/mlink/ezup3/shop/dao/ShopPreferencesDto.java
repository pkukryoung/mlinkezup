package com.kdj.mlink.ezup3.shop.dao;

import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.data.dao.ProductAdditionDto;

public class ShopPreferencesDto {
	private String compno;
	private String suggprocnt;
	private String ordperiod;
	private String ordstatus;
	private String ordimptper;
	private String prodmng;
	private String prodlink;
	private String prodfail;
	private String excepkeywdyn;
	private String excepkeywd;
	private String prodnm_lenmngyn;
	private String stscperiod;//통계기간
	
	
	
	public String getStscperiod() {
		return stscperiod;
	}
	public void setStscperiod(String stscperiod) {
		this.stscperiod = stscperiod;
	}
	public String getCompno() {
		return compno;
	}
	public void setCompno(String compno) {
		this.compno = compno;
	}
	public String getSuggprocnt() {
		return suggprocnt;
	}
	public void setSuggprocnt(String suggprocnt) {
		this.suggprocnt = suggprocnt;
	}
	public String getOrdperiod() {
		return ordperiod;
	}
	public void setOrdperiod(String ordperiod) {
		this.ordperiod = ordperiod;
	}
	public String getOrdstatus() {
		return ordstatus;
	}
	public void setOrdstatus(String ordstatus) {
		this.ordstatus = ordstatus;
	}
	public String getOrdimptper() {
		return ordimptper;
	}
	public void setOrdimptper(String ordimptper) {
		this.ordimptper = ordimptper;
	}
	public String getProdmng() {
		return prodmng;
	}
	public void setProdmng(String prodmng) {
		this.prodmng = prodmng;
	}
	public String getProdlink() {
		return prodlink;
	}
	public void setProdlink(String prodlink) {
		this.prodlink = prodlink;
	}
	public String getProdfail() {
		return prodfail;
	}
	public void setProdfail(String prodfail) {
		this.prodfail = prodfail;
	}
	public String getExcepkeywdyn() {
		return excepkeywdyn;
	}
	public void setExcepkeywdyn(String excepkeywdyn) {
		this.excepkeywdyn = excepkeywdyn;
	}
	public String getExcepkeywd() {
		return excepkeywd;
	}
	public void setExcepkeywd(String excepkeywd) {
		this.excepkeywd = excepkeywd;
	}
	public String getProdnm_lenmngyn() {
		return prodnm_lenmngyn;
	}
	public void setProdnm_lenmngyn(String prodnm_lenmngyn) {
		this.prodnm_lenmngyn = prodnm_lenmngyn;
	}
	
	
	
	
	

}

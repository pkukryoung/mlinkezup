package com.kdj.mlink.ezup3.shop.dao;

public abstract class ShopProductAdditionDto {
	//11¹ø°¡
	private String compno;//
	private String shopcd;//
	private String seq;//
	private String shopid;//
	private String title;//
	private String memo;//
	private String repimage;//
	private String minorselcnyn;//
	private String useyn;//
	public String getCompno() {
		return compno;
	}
	public void setCompno(String compno) {
		this.compno = compno;
	}
	public String getShopcd() {
		return shopcd;
	}
	public void setShopcd(String shopcd) {
		this.shopcd = shopcd;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getRepimage() {
		return repimage;
	}
	public void setRepimage(String repimage) {
		this.repimage = repimage;
	}
	public String getMinorselcnyn() {
		return minorselcnyn;
	}
	public void setMinorselcnyn(String minorselcnyn) {
		this.minorselcnyn = minorselcnyn;
	}
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
}

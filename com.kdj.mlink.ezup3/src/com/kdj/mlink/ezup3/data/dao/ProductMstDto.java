package com.kdj.mlink.ezup3.data.dao;

public class ProductMstDto {

	private String rowno; // UI 그리드 로 순번 (디비컬럼x)

	private String prodcd; // 상품코드 PK
	private String img; /// 대표 이미지명
	private String prodnm; // 상품명
	private String specdes; // 규격
	private String ecountcd; // 이카운트 erp상품코드
	private String flagset;
	private String flagplus;
	private String flagout;
	private String price; // 단가

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	private String tagprice; // 택단가
	private String remark; // 상세설명
	private String ea; // EA

	private String sabcd;
	private String useyn; // 품절여부
	private String delyn; // 삭제여부

	private String insertdt; // 입력일자
	private String insertid; // 입력자
	private String modifydt; // 수정일자
	private String modifyid; // 수정자
	private String cusprice; // 사업자공급가
	private String aproinvt; // 적정재고
	private String optchk;
	private String repstt; // 대표이미지
	private String addtn1; // 부가이미지1
	private String addtn2; // 부가이미지2
	private String infornum; // 부가이미지1
	private String propertynum; // 부가이미지2
	private String searchwd; // 사이트검색어
	private String lagcategory;
	private String midcategory;
	private String smlcategory;
	private String detcategory;
	private String orderprice; // 발주단가
	private String shopcateg;
	private String shopcode; // 발주단가
	private String image; // 이미지
	private String categlagcd; // 이미지
	private String categmidcd; // 이미지
	private String categsmlcd; // 이미지
	private String categdetcd; // 이미지

	// prodinfor
	private String infornm;
	private String brandnm;
	private String prodgubun;
	private String manfnm;
	private String orgnm;
	private String season;
	private String mfgubun;
	private String prodstat;
	private String locarea;
	private String expgubun;
	private String expcost;
	private String taxgubun;
	private String flagopt;
	private String optnm1;
	private String optnm2;
	private String flaginvt;
	private String flagoptchg;
	private String flagoptset;
	private String orgname;
	private String supplier;
	private String businessno;

	private String attrnm;
	private String neccd1;
	private String neccd2;
	private String neccd3;
	private String optprodcd;
	private String delivamt;
	private String dellivtabno;
	private String expinvqty;
	private String expfile;

	public String getExpfile() {
		return expfile;
	}

	public void setExpfile(String expfile) {
		this.expfile = expfile;
	}

	public String getExpinvqty() {
		return expinvqty;
	}

	public void setExpinvqty(String expinvqty) {
		this.expinvqty = expinvqty;
	}

	public String getOptchk() {
		return optchk;
	}

	public void setOptchk(String optchk) {
		this.optchk = optchk;
	}

	public String getDellivtabno() {
		return dellivtabno;
	}

	public void setDellivtabno(String dellivtabno) {
		this.dellivtabno = dellivtabno;
	}

	public String getAttrnm() {
		return attrnm;
	}

	public void setAttrnm(String attrnm) {
		this.attrnm = attrnm;
	}

	public String getNeccd1() {
		return neccd1;
	}

	public void setNeccd1(String neccd1) {
		this.neccd1 = neccd1;
	}

	public String getNeccd2() {
		return neccd2;
	}

	public void setNeccd2(String neccd2) {
		this.neccd2 = neccd2;
	}

	public String getNeccd3() {
		return neccd3;
	}

	public void setNeccd3(String neccd3) {
		this.neccd3 = neccd3;
	}

	public String getOptprodcd() {
		return optprodcd;
	}

	public void setOptprodcd(String optprodcd) {
		this.optprodcd = optprodcd;
	}

	public String getDelivamt() {
		return delivamt;
	}

	public void setDelivamt(String delivamt) {
		this.delivamt = delivamt;
	}

	public String getInfornm() {
		return infornm;
	}

	public void setInfornm(String infornm) {
		this.infornm = infornm;
	}

	public String getBrandnm() {
		return brandnm;
	}

	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
	}

	public String getProdgubun() {
		return prodgubun;
	}

	public void setProdgubun(String prodgubun) {
		this.prodgubun = prodgubun;
	}

	public String getManfnm() {
		return manfnm;
	}

	public void setManfnm(String manfnm) {
		this.manfnm = manfnm;
	}

	public String getOrgnm() {
		return orgnm;
	}

	public void setOrgnm(String orgnm) {
		this.orgnm = orgnm;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getMfgubun() {
		return mfgubun;
	}

	public void setMfgubun(String mfgubun) {
		this.mfgubun = mfgubun;
	}

	public String getProdstat() {
		return prodstat;
	}

	public void setProdstat(String prodstat) {
		this.prodstat = prodstat;
	}

	public String getLocarea() {
		return locarea;
	}

	public void setLocarea(String locarea) {
		this.locarea = locarea;
	}

	public String getExpgubun() {
		return expgubun;
	}

	public void setExpgubun(String expgubun) {
		this.expgubun = expgubun;
	}

	public String getExpcost() {
		return expcost;
	}

	public void setExpcost(String expcost) {
		this.expcost = expcost;
	}

	public String getTaxgubun() {
		return taxgubun;
	}

	public void setTaxgubun(String taxgubun) {
		this.taxgubun = taxgubun;
	}

	public String getFlagopt() {
		return flagopt;
	}

	public void setFlagopt(String flagopt) {
		this.flagopt = flagopt;
	}

	public String getOptnm1() {
		return optnm1;
	}

	public void setOptnm1(String optnm1) {
		this.optnm1 = optnm1;
	}

	public String getOptnm2() {
		return optnm2;
	}

	public void setOptnm2(String optnm2) {
		this.optnm2 = optnm2;
	}

	public String getFlaginvt() {
		return flaginvt;
	}

	public void setFlaginvt(String flaginvt) {
		this.flaginvt = flaginvt;
	}

	public String getFlagoptchg() {
		return flagoptchg;
	}

	public void setFlagoptchg(String flagoptchg) {
		this.flagoptchg = flagoptchg;
	}

	public String getFlagoptset() {
		return flagoptset;
	}

	public void setFlagoptset(String flagoptset) {
		this.flagoptset = flagoptset;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getBusinessno() {
		return businessno;
	}

	public void setBusinessno(String businessno) {
		this.businessno = businessno;
	}

	public String getCateglagcd() {
		return categlagcd;
	}

	public void setCateglagcd(String categlagcd) {
		this.categlagcd = categlagcd;
	}

	public String getCategmidcd() {
		return categmidcd;
	}

	public void setCategmidcd(String categmidcd) {
		this.categmidcd = categmidcd;
	}

	public String getCategsmlcd() {
		return categsmlcd;
	}

	public void setCategsmlcd(String categsmlcd) {
		this.categsmlcd = categsmlcd;
	}

	public String getCategdetcd() {
		return categdetcd;
	}

	public void setCategdetcd(String categdetcd) {
		this.categdetcd = categdetcd;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRowno() {
		return rowno;
	}

	public void setRowno(String rowno) {
		this.rowno = rowno; // YDMAStringUtil.replaceNullvalue(rowno);
	}

	public String getProdcd() {
		return prodcd;
	}

	public void setProdcd(String prodcd) {
		this.prodcd = prodcd; // YDMAStringUtil.replaceNullvalue(prodcd);
	}

	public String getProdnm() {
		return prodnm;
	}

	public void setProdnm(String prodnm) {
		this.prodnm = prodnm; // YDMAStringUtil.replaceNullvalue(prodnm);
	}

	public String getSpecdes() {
		return specdes;
	}

	public void setSpecdes(String specdes) {
		this.specdes = specdes; // YDMAStringUtil.replaceNullvalue(specdes);
	}

	public String getEcountcd() {
		return ecountcd;
	}

	public void setEcountcd(String ecountcd) {
		this.ecountcd = ecountcd; // YDMAStringUtil.replaceNullvalue(ecountcd);
	}

	public String getFlagset() {
		return flagset;
	}

	public void setFlagset(String flagset) {
		this.flagset = flagset; // YDMAStringUtil.replaceNullvalue(flagset);
	}

	public String getFlagplus() {
		return flagplus;
	}

	public void setFlagplus(String flagplus) {
		this.flagplus = flagplus; // YDMAStringUtil.replaceNullvalue(flagplus);
	}

	public String getFlagout() {
		return flagout;
	}

	public void setFlagout(String flagout) {
		this.flagout = flagout; // YDMAStringUtil.replaceNullvalue(flagout);
	}

	public String getUseyn() {
		return useyn;
	}

	public void setUseyn(String useyn) {
		this.useyn = useyn; // YDMAStringUtil.replaceNullvalue(useyn);
	}

	public String getDelyn() {
		return delyn;
	}

	public void setDelyn(String delyn) {
		this.delyn = delyn; // YDMAStringUtil.replaceNullvalue(delyn);
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price; // YDMAStringUtil.replaceNullvalue(price);
	}

	public String getTagprice() {
		return tagprice;
	}

	public void setTagprice(String tagprice) {
		this.tagprice = tagprice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEa() {
		return ea;
	}

	public void setEa(String ea) {
		this.ea = ea;
	}

	public String getSabcd() {
		return sabcd;
	}

	public void setSabcd(String sabcd) {
		this.sabcd = sabcd;
	}

	public String getInsertdt() {
		return insertdt;
	}

	public void setInsertdt(String insertdt) {
		this.insertdt = insertdt; // YDMAStringUtil.replaceNullvalue(insertdt);
	}

	public String getInsertid() {
		return insertid;
	}

	public void setInsertid(String insertid) {
		this.insertid = insertid; // YDMAStringUtil.replaceNullvalue(insertid);
	}

	public String getModifydt() {
		return modifydt;
	}

	public void setModifydt(String modifydt) {
		this.modifydt = modifydt; // YDMAStringUtil.replaceNullvalue(modifydt);
	}

	public String getModifyid() {
		return modifyid;
	}

	public void setModifyid(String modifyid) {
		this.modifyid = modifyid; // YDMAStringUtil.replaceNullvalue(modifyid);
	}

	public String getCusprice() {
		return cusprice;
	}

	public void setCusprice(String cusprice) {
		this.cusprice = cusprice;
	}

	public String getAproinvt() {
		return aproinvt;
	}

	public void setAproinvt(String aproinvt) {
		this.aproinvt = aproinvt;
	}

	public String getRepstt() {
		return repstt;
	}

	public void setRepstt(String repstt) {
		this.repstt = repstt;
	}

	public String getAddtn1() {
		return addtn1;
	}

	public void setAddtn1(String addtn1) {
		this.addtn1 = addtn1;
	}

	public String getAddtn2() {
		return addtn2;
	}

	public void setAddtn2(String addtn2) {
		this.addtn2 = addtn2;
	}

	public String getInfornum() {
		return infornum;
	}

	public void setInfornum(String infornum) {
		this.infornum = infornum;
	}

	public String getPropertynum() {
		return propertynum;
	}

	public void setPropertynum(String propertynum) {
		this.propertynum = propertynum;
	}

	public String getSearchwd() {
		return searchwd;
	}

	public void setSearchwd(String searchwd) {
		this.searchwd = searchwd;
	}

	public String getLagcategory() {
		return lagcategory;
	}

	public void setLagcategory(String lagcategory) {
		this.lagcategory = lagcategory;
	}

	public String getMidcategory() {
		return midcategory;
	}

	public void setMidcategory(String midcategory) {
		this.midcategory = midcategory;
	}

	public String getSmlcategory() {
		return smlcategory;
	}

	public void setSmlcategory(String smlcategory) {
		this.smlcategory = smlcategory;
	}

	public String getDetcategory() {
		return detcategory;
	}

	public void setDetcategory(String detcategory) {
		this.detcategory = detcategory;
	}

	public String getOrderprice() {
		return orderprice;
	}

	public void setOrderprice(String orderprice) {
		this.orderprice = orderprice;
	}

	public String getShopcateg() {
		return shopcateg;
	}

	public void setShopcateg(String shopcateg) {
		this.shopcateg = shopcateg;
	}

	public String getShopcode() {
		return shopcode;
	}

	public void setShopcode(String shopcode) {
		this.shopcode = shopcode;
	}

}

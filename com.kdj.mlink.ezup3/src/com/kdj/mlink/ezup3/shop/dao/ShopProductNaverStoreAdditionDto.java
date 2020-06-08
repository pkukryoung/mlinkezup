package com.kdj.mlink.ezup3.shop.dao;

public class ShopProductNaverStoreAdditionDto extends ShopProductAdditionDto {
	private String seq;//일련번호
	private String compno;//회사코드
	private String shopcd;//쇼핑몰코드
	private String title;//제목
	private String memo;//메모
	private String shopid;//쇼핑몰아이디
	private String prodstats;//상품상태
	private String minortyp;//미성년자여부
	private String image;//대표이미지
	private String astelno;//A/S전화번호
	private String asdtl;//A/S정보
	private String pricecomparison;//가격비교여부
	private String review;//구매평노출여부
	private String minimumtyp;//최소수량여부
	private String minimumqty;//최소수량
	private String maximumtyp;//최대수량여부
	private String maximumval;//최대수량조건
	private String maximumqty;//최대수량
	private String generaldelvtyp;//배송속성여부
	private String customordtyp;//주문후맞춤제작여부
	private String senddate;//발송예정일
	private String shipmethod;//배송방법
	private String bundledshiptyp;//묶음배송여부
	private String bundledshipcd;//묶음배송코드
	private String shipdelvtyp;//배송비유형
	private String delvcost;//기본배송비
	private String conditionaltyp;//조건부무료시무료여부체크
	private String qtydivisiontyp;//수량별무료시조건
	private String delvcost2;//배송비상세금액들
	private String prepayment;//선결제여부
	private String areadelvtyp;//지역별배송비부과여부
	private String jejucost;//제주배송비
	private String islandcost;//제주및도서산간배송비
	private String retncngexp;//교환/반품택배사
	private String retcost;//반품배송비
	private String cngcost;//교환배송비
	private String outaddr;//출고지주소
	private String retaddr;//반품지주소
	private String prodcdtyp;//상품코드타입
	private String prodcdinput;//상품코드
	private String childcert;//어린이인증
	private String kccert;//KC인증제외여부
	private String ecocert;//친환경인증여부
	private String kcexemption;//KC면제인증여부
	private String salesperiodtyp;//판매기간설정여부
	private String salesfrom;//판매시작일
	private String salesto;//판매종료일
	private String optiontyp;//옵션타입
	private String custommade;//맞춤제작여부
	private String summarytyp1;//요약정보1
	private String summaryval1;//요약내용1
	private String summarytyp2;//요약정보2
	private String summaryval2;//요약내용2
	private String summarytyp3;//요약정보3
	private String summaryval3;//요약내용3
	private String summarytyp4;//요약정보4
	private String summaryval4;//요약내용4
	private String summarytyp5;//요약정보5
	private String summaryval5;//요약내용5
	private String useyn;//사용유무
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
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
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getProdstats() {
		return prodstats;
	}
	public void setProdstats(String prodstats) {
		this.prodstats = prodstats;
	}
	public String getMinortyp() {
		return minortyp;
	}
	public void setMinortyp(String minortyp) {
		this.minortyp = minortyp;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAstelno() {
		return astelno;
	}
	public void setAstelno(String astelno) {
		this.astelno = astelno;
	}
	public String getAsdtl() {
		return asdtl;
	}
	public void setAsdtl(String asdtl) {
		this.asdtl = asdtl;
	}
	public String getPricecomparison() {
		return pricecomparison;
	}
	public void setPricecomparison(String pricecomparison) {
		this.pricecomparison = pricecomparison;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getMinimumtyp() {
		return minimumtyp;
	}
	public void setMinimumtyp(String minimumtyp) {
		this.minimumtyp = minimumtyp;
	}
	public String getMinimumqty() {
		return minimumqty;
	}
	public void setMinimumqty(String minimumqty) {
		this.minimumqty = minimumqty;
	}
	public String getMaximumtyp() {
		return maximumtyp;
	}
	public void setMaximumtyp(String maximumtyp) {
		this.maximumtyp = maximumtyp;
	}
	public String getMaximumval() {
		return maximumval;
	}
	public void setMaximumval(String maximumval) {
		this.maximumval = maximumval;
	}
	public String getMaximumqty() {
		return maximumqty;
	}
	public void setMaximumqty(String maximumqty) {
		this.maximumqty = maximumqty;
	}
	public String getGeneraldelvtyp() {
		return generaldelvtyp;
	}
	public void setGeneraldelvtyp(String generaldelvtyp) {
		this.generaldelvtyp = generaldelvtyp;
	}
	public String getCustomordtyp() {
		return customordtyp;
	}
	public void setCustomordtyp(String customordtyp) {
		this.customordtyp = customordtyp;
	}
	public String getSenddate() {
		return senddate;
	}
	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}
	public String getShipmethod() {
		return shipmethod;
	}
	public void setShipmethod(String shipmethod) {
		this.shipmethod = shipmethod;
	}
	public String getBundledshiptyp() {
		return bundledshiptyp;
	}
	public void setBundledshiptyp(String bundledshiptyp) {
		this.bundledshiptyp = bundledshiptyp;
	}
	public String getBundledshipcd() {
		return bundledshipcd;
	}
	public void setBundledshipcd(String bundledshipcd) {
		this.bundledshipcd = bundledshipcd;
	}
	public String getShipdelvtyp() {
		return shipdelvtyp;
	}
	public void setShipdelvtyp(String shipdelvtyp) {
		this.shipdelvtyp = shipdelvtyp;
	}
	public String getDelvcost() {
		return delvcost;
	}
	public void setDelvcost(String delvcost) {
		this.delvcost = delvcost;
	}
	public String getConditionaltyp() {
		return conditionaltyp;
	}
	public void setConditionaltyp(String conditionaltyp) {
		this.conditionaltyp = conditionaltyp;
	}
	public String getQtydivisiontyp() {
		return qtydivisiontyp;
	}
	public void setQtydivisiontyp(String qtydivisiontyp) {
		this.qtydivisiontyp = qtydivisiontyp;
	}
	public String getDelvcost2() {
		return delvcost2;
	}
	public void setDelvcost2(String delvcost2) {
		this.delvcost2 = delvcost2;
	}
	public String getPrepayment() {
		return prepayment;
	}
	public void setPrepayment(String prepayment) {
		this.prepayment = prepayment;
	}
	public String getAreadelvtyp() {
		return areadelvtyp;
	}
	public void setAreadelvtyp(String areadelvtyp) {
		this.areadelvtyp = areadelvtyp;
	}
	public String getJejucost() {
		return jejucost;
	}
	public void setJejucost(String jejucost) {
		this.jejucost = jejucost;
	}
	public String getIslandcost() {
		return islandcost;
	}
	public void setIslandcost(String islandcost) {
		this.islandcost = islandcost;
	}
	public String getRetncngexp() {
		return retncngexp;
	}
	public void setRetncngexp(String retncngexp) {
		this.retncngexp = retncngexp;
	}
	public String getRetcost() {
		return retcost;
	}
	public void setRetcost(String retcost) {
		this.retcost = retcost;
	}
	public String getCngcost() {
		return cngcost;
	}
	public void setCngcost(String cngcost) {
		this.cngcost = cngcost;
	}
	public String getOutaddr() {
		return outaddr;
	}
	public void setOutaddr(String outaddr) {
		this.outaddr = outaddr;
	}
	public String getRetaddr() {
		return retaddr;
	}
	public void setRetaddr(String retaddr) {
		this.retaddr = retaddr;
	}
	public String getProdcdtyp() {
		return prodcdtyp;
	}
	public void setProdcdtyp(String prodcdtyp) {
		this.prodcdtyp = prodcdtyp;
	}
	public String getProdcdinput() {
		return prodcdinput;
	}
	public void setProdcdinput(String prodcdinput) {
		this.prodcdinput = prodcdinput;
	}
	public String getChildcert() {
		return childcert;
	}
	public void setChildcert(String childcert) {
		this.childcert = childcert;
	}
	public String getKccert() {
		return kccert;
	}
	public void setKccert(String kccert) {
		this.kccert = kccert;
	}
	public String getEcocert() {
		return ecocert;
	}
	public void setEcocert(String ecocert) {
		this.ecocert = ecocert;
	}
	public String getKcexemption() {
		return kcexemption;
	}
	public void setKcexemption(String kcexemption) {
		this.kcexemption = kcexemption;
	}
	public String getSalesperiodtyp() {
		return salesperiodtyp;
	}
	public void setSalesperiodtyp(String salesperiodtyp) {
		this.salesperiodtyp = salesperiodtyp;
	}
	public String getSalesfrom() {
		return salesfrom;
	}
	public void setSalesfrom(String salesfrom) {
		this.salesfrom = salesfrom;
	}
	public String getSalesto() {
		return salesto;
	}
	public void setSalesto(String salesto) {
		this.salesto = salesto;
	}
	public String getOptiontyp() {
		return optiontyp;
	}
	public void setOptiontyp(String optiontyp) {
		this.optiontyp = optiontyp;
	}
	public String getCustommade() {
		return custommade;
	}
	public void setCustommade(String custommade) {
		this.custommade = custommade;
	}
	public String getSummarytyp1() {
		return summarytyp1;
	}
	public void setSummarytyp1(String summarytyp1) {
		this.summarytyp1 = summarytyp1;
	}
	public String getSummaryval1() {
		return summaryval1;
	}
	public void setSummaryval1(String summaryval1) {
		this.summaryval1 = summaryval1;
	}
	public String getSummarytyp2() {
		return summarytyp2;
	}
	public void setSummarytyp2(String summarytyp2) {
		this.summarytyp2 = summarytyp2;
	}
	public String getSummaryval2() {
		return summaryval2;
	}
	public void setSummaryval2(String summaryval2) {
		this.summaryval2 = summaryval2;
	}
	public String getSummarytyp3() {
		return summarytyp3;
	}
	public void setSummarytyp3(String summarytyp3) {
		this.summarytyp3 = summarytyp3;
	}
	public String getSummaryval3() {
		return summaryval3;
	}
	public void setSummaryval3(String summaryval3) {
		this.summaryval3 = summaryval3;
	}
	public String getSummarytyp4() {
		return summarytyp4;
	}
	public void setSummarytyp4(String summarytyp4) {
		this.summarytyp4 = summarytyp4;
	}
	public String getSummaryval4() {
		return summaryval4;
	}
	public void setSummaryval4(String summaryval4) {
		this.summaryval4 = summaryval4;
	}
	public String getSummarytyp5() {
		return summarytyp5;
	}
	public void setSummarytyp5(String summarytyp5) {
		this.summarytyp5 = summarytyp5;
	}
	public String getSummaryval5() {
		return summaryval5;
	}
	public void setSummaryval5(String summaryval5) {
		this.summaryval5 = summaryval5;
	}
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
	public ShopProductNaverStoreAdditionDto() {
		
		this.seq = "";
		this.compno = "";
		this.shopcd = "";
		this.title = "";
		this.memo = "";
		this.shopid = "";
		this.prodstats = "";
		this.minortyp = "";
		this.image = "";
		this.astelno = "";
		this.asdtl = "";
		this.pricecomparison = "";
		this.review = "";
		this.minimumtyp = "";
		this.minimumqty = "";
		this.maximumtyp = "";
		this.maximumval = "";
		this.maximumqty = "";
		this.generaldelvtyp = "";
		this.customordtyp = "";
		this.senddate = "";
		this.shipmethod = "";
		this.bundledshiptyp = "";
		this.bundledshipcd = "";
		this.shipdelvtyp = "";
		this.delvcost = "";
		this.conditionaltyp = "";
		this.qtydivisiontyp = "";
		this.delvcost2 = "";
		this.prepayment = "";
		this.areadelvtyp = "";
		this.jejucost = "";
		this.islandcost = "";
		this.retncngexp = "";
		this.retcost = "";
		this.cngcost = "";
		this.outaddr = "";
		this.retaddr = "";
		this.prodcdtyp = "";
		this.prodcdinput = "";
		this.childcert = "";
		this.kccert = "";
		this.ecocert = "";
		this.kcexemption = "";
		this.salesperiodtyp = "";
		this.salesfrom = "";
		this.salesto = "";
		this.optiontyp = "";
		this.custommade = "";
		this.summarytyp1 = "";
		this.summaryval1 = "";
		this.summarytyp2 = "";
		this.summaryval2 = "";
		this.summarytyp3 = "";
		this.summaryval3 = "";
		this.summarytyp4 = "";
		this.summaryval4 = "";
		this.summarytyp5 = "";
		this.summaryval5 = "";
		this.useyn = "";
	}

	
	
	
	
	
		
}

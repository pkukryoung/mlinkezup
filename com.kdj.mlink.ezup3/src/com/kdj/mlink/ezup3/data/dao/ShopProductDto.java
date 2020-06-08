package com.kdj.mlink.ezup3.data.dao;

import java.util.ArrayList;
import java.util.List;

public class ShopProductDto {
	
	List<ProductAdditionDto>  productAdditionDto = new ArrayList<ProductAdditionDto>();
	private String rowno;//
	private String prodseq;//품번코드
	private String compno;//
	private String goods_nm;//상품명
	private String goods_keyword;//상품약어
	private String model_nm;//모델명
	private String model_no;//모델No
	public List<ProductAdditionDto> getProductAdditionDto() {
		return productAdditionDto;
	}
	public void setProductAdditionDto(List<ProductAdditionDto> productAdditionDto) {
		this.productAdditionDto = productAdditionDto;
	}
	private String brand_nm;//브랜드명
	private String compayny_goods_cd;//자체상품코드
	private String goods_search;//사이트검색어
	private String goods_gubun;//상품구분
	private String class_cd1;//대분류코드
	private String class_cd2;//중분류코드
	private String class_cd3;//소분류코드
	private String class_cd4;//세분류코드
	private String partner_id;//매입처ID
	private String dpartner_id;//물류처ID
	private String maker;//제조사
	private String origin;//원산지(제조국)
	private String make_year;//생산연도
	private String make_dm;//제조일자
	private String goods_season;//시즌
	private String sex;//남녀구분
	private String status;//상품상태
	private String deliv_able_region;//판매지역
	private String tax_yn;//세금구분
	private String delv_type;//배송비구분
	private String delv_cost;//배송비
	private String banpum_area;//반품지구분
	private String goods_cost;//원가
	private String goods_price;//판매가
	private String goods_consumer_price;//TAG가(소비자가)
	private String char_1_nm;//옵션제목(1)
	private String char_1_val;//옵션상세명칭(1)
	private String char_2_nm;//옵션제목(2)
	private String char_2_val;//옵션상세명칭(2)
	private String img_path;//대표이미지
	private String img_path1;//종합몰(JPG)이미지
	private String img_path2;//부가이미지2
	private String img_path3;//부가이미지3
	private String img_path4;//부가이미지4
	private String img_path5;//부가이미지5
	private String img_path6;//부가이미지6
	private String img_path7;//부가이미지7
	private String img_path8;//부가이미지8
	private String img_path9;//부가이미지9
	private String img_path10;//부가이미지10
	private String img_path11;//부가이미지11
	private String img_path12;//부가이미지12
	private String img_path13;//부가이미지13
	private String img_path14;//부가이미지14
	private String img_path15;//부가이미지15
	private String img_path16;//부가이미지16
	private String img_path17;//부가이미지17
	private String img_path18;//부가이미지18
	private String img_path19;//부가이미지19
	private String img_path20;//부가이미지20
	private String img_path21;//부가이미지21
	private String img_path22;//부가이미지22
	private String img_path23;//인증서이미지
	private String img_path24;//수입면장이미지
	private String goods_remarks;//상품상세설명
	private String certno;//인증번호
	private String avlst_dm;//인증유효 시작일
	private String avled_dm;//인증유효 마지막일
	private String issuedate;//발급일자
	private String certdate;//인증일자
	private String cert_agency;//인증기관
	private String certfield;//인증분야
	private String material;//식품재료/원산지
	private String stock_use_yn;//재고관리사용여부
	private String opt_type;//옵션수정여부
	private String prop_edit_yn;//속성수정여부
	private String prop1_cd;//속성분류코드
	private String prop_val1;//속성값1
	private String prop_val2;//속성값2
	private String prop_val3;//속성값3
	private String prop_val4;//속성값4
	private String prop_val5;//속성값5
	private String prop_val6;//속성값6
	private String prop_val7;//속성값7
	private String prop_val8;//속성값8
	private String prop_val9;//속성값9
	private String prop_val10;//속성값10
	private String prop_val11;//속성값11
	private String prop_val12;//속성값12
	private String prop_val13;//속성값13
	private String prop_val14;//속성값14
	private String prop_val15;//속성값15
	private String prop_val16;//속성값16
	private String prop_val17;//속성값17
	private String prop_val18;//속성값18
	private String prop_val19;//속성값19
	private String prop_val20;//속성값20
	private String prop_val21;//속성값21
	private String prop_val22;//속성값22
	private String prop_val23;//속성값23
	private String prop_val24;//속성값24
	private String prop_val25;//속성값25
	private String prop_val26;//속성값26
	private String prop_val27;//속성값27
	private String prop_val28;//속성값28
	private String pack_code_str;//추가상품그룹코드
	private String goods_nm_en;//영문 상품명
	private String goods_nm_pr;//출력 상품명
	private String goods_remarks2;//추가 상품상세설명_1
	private String goods_remarks3;//추가 상품상세설명_2
	private String goods_remarks4;//추가 상품상세설명_3
	private String importno;//수입신고번호
	private String goods_cost2;//원가2
	private String origin2;//원산지 상세지역
	private String expire_dm;//유효일자
	private String supply_save_yn;//합포제외여부
	private String descrition;//관리자메모
	private String shopprodno;//쇼핑몰코드
	private String insertdt;//입력일자
	private String insertid;//입력자아이디
	private String modifydt;//수정일자
	private String modifyid;//수정아이디
	private String img;
	
	//prodin에 관련된거
	private String sendseq;//송신번호
	private String shopcd;//쇼핑몰코드
	private String shopseq;//순번
	private String sendstats;//송신구분임의로 만듬
	private String seq;//송신구분임의로 만듬
	private String shopid;
	private String shoppw;
	
	//error
	private String failcontent;//에러메시지
	public String getRowno() {
		return rowno;
	}
	public void setRowno(String rowno) {
		this.rowno = rowno;
	}
	public String getProdseq() {
		return prodseq;
	}
	public void setProdseq(String prodseq) {
		this.prodseq = prodseq;
	}
	public String getCompno() {
		return compno;
	}
	public void setCompno(String compno) {
		this.compno = compno;
	}
	public String getGoods_nm() {
		return goods_nm;
	}
	public void setGoods_nm(String goods_nm) {
		this.goods_nm = goods_nm;
	}
	public String getGoods_keyword() {
		return goods_keyword;
	}
	public void setGoods_keyword(String goods_keyword) {
		this.goods_keyword = goods_keyword;
	}
	public String getModel_nm() {
		return model_nm;
	}
	public void setModel_nm(String model_nm) {
		this.model_nm = model_nm;
	}
	public String getModel_no() {
		return model_no;
	}
	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}
	public String getBrand_nm() {
		return brand_nm;
	}
	public void setBrand_nm(String brand_nm) {
		this.brand_nm = brand_nm;
	}
	public String getCompayny_goods_cd() {
		return compayny_goods_cd;
	}
	public void setCompayny_goods_cd(String compayny_goods_cd) {
		this.compayny_goods_cd = compayny_goods_cd;
	}
	public String getGoods_search() {
		return goods_search;
	}
	public void setGoods_search(String goods_search) {
		this.goods_search = goods_search;
	}
	public String getGoods_gubun() {
		return goods_gubun;
	}
	public void setGoods_gubun(String goods_gubun) {
		this.goods_gubun = goods_gubun;
	}
	public String getClass_cd1() {
		return class_cd1;
	}
	public void setClass_cd1(String class_cd1) {
		this.class_cd1 = class_cd1;
	}
	public String getClass_cd2() {
		return class_cd2;
	}
	public void setClass_cd2(String class_cd2) {
		this.class_cd2 = class_cd2;
	}
	public String getClass_cd3() {
		return class_cd3;
	}
	public void setClass_cd3(String class_cd3) {
		this.class_cd3 = class_cd3;
	}
	public String getClass_cd4() {
		return class_cd4;
	}
	public void setClass_cd4(String class_cd4) {
		this.class_cd4 = class_cd4;
	}
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public String getDpartner_id() {
		return dpartner_id;
	}
	public void setDpartner_id(String dpartner_id) {
		this.dpartner_id = dpartner_id;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getMake_year() {
		return make_year;
	}
	public void setMake_year(String make_year) {
		this.make_year = make_year;
	}
	public String getMake_dm() {
		return make_dm;
	}
	public void setMake_dm(String make_dm) {
		this.make_dm = make_dm;
	}
	public String getGoods_season() {
		return goods_season;
	}
	public void setGoods_season(String goods_season) {
		this.goods_season = goods_season;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeliv_able_region() {
		return deliv_able_region;
	}
	public void setDeliv_able_region(String deliv_able_region) {
		this.deliv_able_region = deliv_able_region;
	}
	public String getTax_yn() {
		return tax_yn;
	}
	public void setTax_yn(String tax_yn) {
		this.tax_yn = tax_yn;
	}
	public String getDelv_type() {
		return delv_type;
	}
	public void setDelv_type(String delv_type) {
		this.delv_type = delv_type;
	}
	public String getDelv_cost() {
		return delv_cost;
	}
	public void setDelv_cost(String delv_cost) {
		this.delv_cost = delv_cost;
	}
	public String getBanpum_area() {
		return banpum_area;
	}
	public void setBanpum_area(String banpum_area) {
		this.banpum_area = banpum_area;
	}
	public String getGoods_cost() {
		return goods_cost;
	}
	public void setGoods_cost(String goods_cost) {
		this.goods_cost = goods_cost;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_consumer_price() {
		return goods_consumer_price;
	}
	public void setGoods_consumer_price(String goods_consumer_price) {
		this.goods_consumer_price = goods_consumer_price;
	}
	public String getChar_1_nm() {
		return char_1_nm;
	}
	public void setChar_1_nm(String char_1_nm) {
		this.char_1_nm = char_1_nm;
	}
	public String getChar_1_val() {
		return char_1_val;
	}
	public void setChar_1_val(String char_1_val) {
		this.char_1_val = char_1_val;
	}
	public String getChar_2_nm() {
		return char_2_nm;
	}
	public void setChar_2_nm(String char_2_nm) {
		this.char_2_nm = char_2_nm;
	}
	public String getChar_2_val() {
		return char_2_val;
	}
	public void setChar_2_val(String char_2_val) {
		this.char_2_val = char_2_val;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getImg_path1() {
		return img_path1;
	}
	public void setImg_path1(String img_path1) {
		this.img_path1 = img_path1;
	}
	public String getImg_path2() {
		return img_path2;
	}
	public void setImg_path2(String img_path2) {
		this.img_path2 = img_path2;
	}
	public String getImg_path3() {
		return img_path3;
	}
	public void setImg_path3(String img_path3) {
		this.img_path3 = img_path3;
	}
	public String getImg_path4() {
		return img_path4;
	}
	public void setImg_path4(String img_path4) {
		this.img_path4 = img_path4;
	}
	public String getImg_path5() {
		return img_path5;
	}
	public void setImg_path5(String img_path5) {
		this.img_path5 = img_path5;
	}
	public String getImg_path6() {
		return img_path6;
	}
	public void setImg_path6(String img_path6) {
		this.img_path6 = img_path6;
	}
	public String getImg_path7() {
		return img_path7;
	}
	public void setImg_path7(String img_path7) {
		this.img_path7 = img_path7;
	}
	public String getImg_path8() {
		return img_path8;
	}
	public void setImg_path8(String img_path8) {
		this.img_path8 = img_path8;
	}
	public String getImg_path9() {
		return img_path9;
	}
	public void setImg_path9(String img_path9) {
		this.img_path9 = img_path9;
	}
	public String getImg_path10() {
		return img_path10;
	}
	public void setImg_path10(String img_path10) {
		this.img_path10 = img_path10;
	}
	public String getImg_path11() {
		return img_path11;
	}
	public void setImg_path11(String img_path11) {
		this.img_path11 = img_path11;
	}
	public String getImg_path12() {
		return img_path12;
	}
	public void setImg_path12(String img_path12) {
		this.img_path12 = img_path12;
	}
	public String getImg_path13() {
		return img_path13;
	}
	public void setImg_path13(String img_path13) {
		this.img_path13 = img_path13;
	}
	public String getImg_path14() {
		return img_path14;
	}
	public void setImg_path14(String img_path14) {
		this.img_path14 = img_path14;
	}
	public String getImg_path15() {
		return img_path15;
	}
	public void setImg_path15(String img_path15) {
		this.img_path15 = img_path15;
	}
	public String getImg_path16() {
		return img_path16;
	}
	public void setImg_path16(String img_path16) {
		this.img_path16 = img_path16;
	}
	public String getImg_path17() {
		return img_path17;
	}
	public void setImg_path17(String img_path17) {
		this.img_path17 = img_path17;
	}
	public String getImg_path18() {
		return img_path18;
	}
	public void setImg_path18(String img_path18) {
		this.img_path18 = img_path18;
	}
	public String getImg_path19() {
		return img_path19;
	}
	public void setImg_path19(String img_path19) {
		this.img_path19 = img_path19;
	}
	public String getImg_path20() {
		return img_path20;
	}
	public void setImg_path20(String img_path20) {
		this.img_path20 = img_path20;
	}
	public String getImg_path21() {
		return img_path21;
	}
	public void setImg_path21(String img_path21) {
		this.img_path21 = img_path21;
	}
	public String getImg_path22() {
		return img_path22;
	}
	public void setImg_path22(String img_path22) {
		this.img_path22 = img_path22;
	}
	public String getImg_path23() {
		return img_path23;
	}
	public void setImg_path23(String img_path23) {
		this.img_path23 = img_path23;
	}
	public String getImg_path24() {
		return img_path24;
	}
	public void setImg_path24(String img_path24) {
		this.img_path24 = img_path24;
	}
	public String getGoods_remarks() {
		return goods_remarks;
	}
	public void setGoods_remarks(String goods_remarks) {
		this.goods_remarks = goods_remarks;
	}
	public String getCertno() {
		return certno;
	}
	public void setCertno(String certno) {
		this.certno = certno;
	}
	public String getAvlst_dm() {
		return avlst_dm;
	}
	public void setAvlst_dm(String avlst_dm) {
		this.avlst_dm = avlst_dm;
	}
	public String getAvled_dm() {
		return avled_dm;
	}
	public void setAvled_dm(String avled_dm) {
		this.avled_dm = avled_dm;
	}
	public String getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}
	public String getCertdate() {
		return certdate;
	}
	public void setCertdate(String certdate) {
		this.certdate = certdate;
	}
	public String getCert_agency() {
		return cert_agency;
	}
	public void setCert_agency(String cert_agency) {
		this.cert_agency = cert_agency;
	}
	public String getCertfield() {
		return certfield;
	}
	public void setCertfield(String certfield) {
		this.certfield = certfield;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getStock_use_yn() {
		return stock_use_yn;
	}
	public void setStock_use_yn(String stock_use_yn) {
		this.stock_use_yn = stock_use_yn;
	}
	public String getOpt_type() {
		return opt_type;
	}
	public void setOpt_type(String opt_type) {
		this.opt_type = opt_type;
	}
	public String getProp_edit_yn() {
		return prop_edit_yn;
	}
	public void setProp_edit_yn(String prop_edit_yn) {
		this.prop_edit_yn = prop_edit_yn;
	}
	public String getProp1_cd() {
		return prop1_cd;
	}
	public void setProp1_cd(String prop1_cd) {
		this.prop1_cd = prop1_cd;
	}
	public String getProp_val1() {
		return prop_val1;
	}
	public void setProp_val1(String prop_val1) {
		this.prop_val1 = prop_val1;
	}
	public String getProp_val2() {
		return prop_val2;
	}
	public void setProp_val2(String prop_val2) {
		this.prop_val2 = prop_val2;
	}
	public String getProp_val3() {
		return prop_val3;
	}
	public void setProp_val3(String prop_val3) {
		this.prop_val3 = prop_val3;
	}
	public String getProp_val4() {
		return prop_val4;
	}
	public void setProp_val4(String prop_val4) {
		this.prop_val4 = prop_val4;
	}
	public String getProp_val5() {
		return prop_val5;
	}
	public void setProp_val5(String prop_val5) {
		this.prop_val5 = prop_val5;
	}
	public String getProp_val6() {
		return prop_val6;
	}
	public void setProp_val6(String prop_val6) {
		this.prop_val6 = prop_val6;
	}
	public String getProp_val7() {
		return prop_val7;
	}
	public void setProp_val7(String prop_val7) {
		this.prop_val7 = prop_val7;
	}
	public String getProp_val8() {
		return prop_val8;
	}
	public void setProp_val8(String prop_val8) {
		this.prop_val8 = prop_val8;
	}
	public String getProp_val9() {
		return prop_val9;
	}
	public void setProp_val9(String prop_val9) {
		this.prop_val9 = prop_val9;
	}
	public String getProp_val10() {
		return prop_val10;
	}
	public void setProp_val10(String prop_val10) {
		this.prop_val10 = prop_val10;
	}
	public String getProp_val11() {
		return prop_val11;
	}
	public void setProp_val11(String prop_val11) {
		this.prop_val11 = prop_val11;
	}
	public String getProp_val12() {
		return prop_val12;
	}
	public void setProp_val12(String prop_val12) {
		this.prop_val12 = prop_val12;
	}
	public String getProp_val13() {
		return prop_val13;
	}
	public void setProp_val13(String prop_val13) {
		this.prop_val13 = prop_val13;
	}
	public String getProp_val14() {
		return prop_val14;
	}
	public void setProp_val14(String prop_val14) {
		this.prop_val14 = prop_val14;
	}
	public String getProp_val15() {
		return prop_val15;
	}
	public void setProp_val15(String prop_val15) {
		this.prop_val15 = prop_val15;
	}
	public String getProp_val16() {
		return prop_val16;
	}
	public void setProp_val16(String prop_val16) {
		this.prop_val16 = prop_val16;
	}
	public String getProp_val17() {
		return prop_val17;
	}
	public void setProp_val17(String prop_val17) {
		this.prop_val17 = prop_val17;
	}
	public String getProp_val18() {
		return prop_val18;
	}
	public void setProp_val18(String prop_val18) {
		this.prop_val18 = prop_val18;
	}
	public String getProp_val19() {
		return prop_val19;
	}
	public void setProp_val19(String prop_val19) {
		this.prop_val19 = prop_val19;
	}
	public String getProp_val20() {
		return prop_val20;
	}
	public void setProp_val20(String prop_val20) {
		this.prop_val20 = prop_val20;
	}
	public String getProp_val21() {
		return prop_val21;
	}
	public void setProp_val21(String prop_val21) {
		this.prop_val21 = prop_val21;
	}
	public String getProp_val22() {
		return prop_val22;
	}
	public void setProp_val22(String prop_val22) {
		this.prop_val22 = prop_val22;
	}
	public String getProp_val23() {
		return prop_val23;
	}
	public void setProp_val23(String prop_val23) {
		this.prop_val23 = prop_val23;
	}
	public String getProp_val24() {
		return prop_val24;
	}
	public void setProp_val24(String prop_val24) {
		this.prop_val24 = prop_val24;
	}
	public String getProp_val25() {
		return prop_val25;
	}
	public void setProp_val25(String prop_val25) {
		this.prop_val25 = prop_val25;
	}
	public String getProp_val26() {
		return prop_val26;
	}
	public void setProp_val26(String prop_val26) {
		this.prop_val26 = prop_val26;
	}
	public String getProp_val27() {
		return prop_val27;
	}
	public void setProp_val27(String prop_val27) {
		this.prop_val27 = prop_val27;
	}
	public String getProp_val28() {
		return prop_val28;
	}
	public void setProp_val28(String prop_val28) {
		this.prop_val28 = prop_val28;
	}
	public String getPack_code_str() {
		return pack_code_str;
	}
	public void setPack_code_str(String pack_code_str) {
		this.pack_code_str = pack_code_str;
	}
	public String getGoods_nm_en() {
		return goods_nm_en;
	}
	public void setGoods_nm_en(String goods_nm_en) {
		this.goods_nm_en = goods_nm_en;
	}
	public String getGoods_nm_pr() {
		return goods_nm_pr;
	}
	public void setGoods_nm_pr(String goods_nm_pr) {
		this.goods_nm_pr = goods_nm_pr;
	}
	public String getGoods_remarks2() {
		return goods_remarks2;
	}
	public void setGoods_remarks2(String goods_remarks2) {
		this.goods_remarks2 = goods_remarks2;
	}
	public String getGoods_remarks3() {
		return goods_remarks3;
	}
	public void setGoods_remarks3(String goods_remarks3) {
		this.goods_remarks3 = goods_remarks3;
	}
	public String getGoods_remarks4() {
		return goods_remarks4;
	}
	public void setGoods_remarks4(String goods_remarks4) {
		this.goods_remarks4 = goods_remarks4;
	}
	public String getImportno() {
		return importno;
	}
	public void setImportno(String importno) {
		this.importno = importno;
	}
	public String getGoods_cost2() {
		return goods_cost2;
	}
	public void setGoods_cost2(String goods_cost2) {
		this.goods_cost2 = goods_cost2;
	}
	public String getOrigin2() {
		return origin2;
	}
	public void setOrigin2(String origin2) {
		this.origin2 = origin2;
	}
	public String getExpire_dm() {
		return expire_dm;
	}
	public void setExpire_dm(String expire_dm) {
		this.expire_dm = expire_dm;
	}
	public String getSupply_save_yn() {
		return supply_save_yn;
	}
	public void setSupply_save_yn(String supply_save_yn) {
		this.supply_save_yn = supply_save_yn;
	}
	public String getDescrition() {
		return descrition;
	}
	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}
	public String getInsertdt() {
		return insertdt;
	}
	public void setInsertdt(String insertdt) {
		this.insertdt = insertdt;
	}
	public String getInsertid() {
		return insertid;
	}
	public void setInsertid(String insertid) {
		this.insertid = insertid;
	}
	public String getModifydt() {
		return modifydt;
	}
	public void setModifydt(String modifydt) {
		this.modifydt = modifydt;
	}
	public String getModifyid() {
		return modifyid;
	}
	public void setModifyid(String modifyid) {
		this.modifyid = modifyid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getShopprodno() {
		return shopprodno;
	}
	public void setShopprodno(String shopprodno) {
		this.shopprodno = shopprodno;
	}
	public String getSendseq() {
		return sendseq;
	}
	public void setSendseq(String sendseq) {
		this.sendseq = sendseq;
	}
	public String getShopcd() {
		return shopcd;
	}
	public void setShopcd(String shopcd) {
		this.shopcd = shopcd;
	}
	public String getShopseq() {
		return shopseq;
	}
	public void setShopseq(String shopseq) {
		this.shopseq = shopseq;
	}
	public String getSendstats() {
		return sendstats;
	}
	public void setSendstats(String sendstats) {
		this.sendstats = sendstats;
	}
	public String getFailcontent() {
		return failcontent;
	}
	public void setFailcontent(String failcontent) {
		this.failcontent = failcontent;
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
	public String getShoppw() {
		return shoppw;
	}
	public void setShoppw(String shoppw) {
		this.shoppw = shoppw;
	}

	
	
}

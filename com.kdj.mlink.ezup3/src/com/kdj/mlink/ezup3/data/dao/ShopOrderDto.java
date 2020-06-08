package com.kdj.mlink.ezup3.data.dao;

import java.util.ArrayList;
import java.util.List;

public class ShopOrderDto {
	private String no;
	private String ordseq; // 일련번호
	private String compno; //
	private String reg_date; // 수집일자
	private String order_id; // 주문번호(쇼핑몰)
	private String shopid; // 쇼핑몰명
	private String shop_userid; // 쇼핑몰ID
	public String getNo() {
		return no;
	}



	public void setNo(String no) {
		this.no = no;
	}

	private String shopPw;      // 쇼핑몰 패스워드
	public String getShopPw() {
		return shopPw;
	}



	public void setShopPw(String shopPw) {
		this.shopPw = shopPw;
	}

	private String order_status; // 주문상태
	private String user_id; // 주문자ID
	private String user_name; // 주문자명
	private String user_tel; // 주문자전화번호
	private String user_cel; // 주문자핸드폰번호
	private String user_email; // 주문자이메일주소
	private String receive_tel; // 수취인전화번호
	private String receive_cel; // 수취인핸드폰번호
	private String receive_email; // 수취인이메일주소
	private String delv_msg; // 배송메세지
	private String receive_name; // 수취인명
	private String receive_zipcode; // 수취인우편번호
	private String receive_addr; // 수취인주소
	private String total_cost; // 주문금액
	private String pay_cost; // 결제금액
	private String order_date; // 주문일자
	private String partner_id; // 매입처명
	private String dpartner_id; // 물류처명
	private String mall_product_id; // 상품코드(쇼핑몰)
	private String product_id; // 품번코드(엠링크)
	private String sku_id; // 단품코드(엠링크)
	private String p_product_name; // 상품명(확정)
	private String p_sku_value; // 옵션(확정)
	private String product_name; // 상품명(수집)
	private String sale_cost; // 판매가(수집)
	private String mall_won_cost; // 공급단가
	private String won_cost; // 원가
	private String sku_value; // 옵션(수집)
	private String sale_cnt; // 수량
	private String delivery_method_str; // 배송구분
	private String delv_cost; // 배송비(수집)
	private String compayny_goods_cd; // 자체상품코드
	private String sku_alias; // 옵션별칭
	private String box_ea; // EA(상품)
	private String jung_chk_yn; // 정산대조확인여부
	private String mall_order_seq; // 주문순번
	private String mall_order_id; // 부주문번호
	private String etc_field3; // 수정된 수집옵션명
	private String order_gubun; // 주문구분
	private String p_ea; // EA(확정)
	private String ord_field2; // 세트분리주문구분
	private String copy_idx; // 원주문번호(사방넷)
	private String goods_nm_pr; // 출력상품명
	private String goods_keyword; // 상품약어
	private String ord_confirm_date; // 주문 확인일자
	private String rtn_dt; // 반품 완료일자
	private String chng_dt; // 교환 완료일자
	private String delivery_confirm_date; // 출고 완료일자
	private String cancel_dt; // 취소 완료일자
	private String class_cd1; // 대분류코드
	private String class_cd2; // 중분류코드
	private String class_cd3; // 소분류코드
	private String class_cd4; // 세분류코드
	private String brand_nm; // 브랜드명
	private String delivery_id; // 택배사코드
	private String invoice_no; // 송장번호
	private String hope_delv_date; // 배송희망일자
	private String fld_dsp; // 주문엑셀용
	private String inv_send_msg; // 운송장 전송 결과 메시지
	private String model_no; // 모델NO
	private String set_gubun; // 상품구분
	private String etc_msg; // 기타메세지
	private String delv_msg1; // 배송메세지
	private String mul_delv_msg; // 물류메세지
	private String barcode; // 바코드
	private String inv_send_dm; // 송장전송일자
	private String delivery_method_str2; // 배송구분(배송비반영)
	private String order_etc_1; // 자사몰필드
	private String order_etc_2; //
	private String order_etc_3; //
	private String order_etc_4; //
	private String order_etc_5; //
	private String order_etc_6; //
	private String order_etc_7; //
	private String order_etc_8; //
	private String order_etc_9; //
	private String order_etc_10; //
	private String order_etc_11; //
	private String order_etc_12; //
	private String order_etc_13; //
	private String order_etc_14; //
	private String label;   // 라벨부
	private String apikey;
	
	public String getApikey() {
		return apikey;
	}



	public void setApikey(String apikey) {
		this.apikey = apikey;
	}



	public ShopOrderDto() {
		
	}
	
	
	
	public ShopOrderDto(String ordseq, String compno, String reg_date, String order_id, String shopid,
			String shop_userid, String order_status, String user_id, String user_name, String user_tel, String user_cel,
			String user_email, String receive_tel, String receive_cel, String receive_email, String delv_msg,
			String receive_name, String receive_zipcode, String receive_addr, String total_cost, String pay_cost,
			String order_date, String partner_id, String dpartner_id, String mall_product_id, String product_id,
			String sku_id, String p_product_name, String p_sku_value, String product_name, String sale_cost,
			String mall_won_cost, String won_cost, String sku_value, String sale_cnt, String delivery_method_str,
			String delv_cost, String compayny_goods_cd, String sku_alias, String box_ea, String jung_chk_yn,
			String mall_order_seq, String mall_order_id, String etc_field3, String order_gubun, String p_ea,
			String ord_field2, String copy_idx, String goods_nm_pr, String goods_keyword, String ord_confirm_date,
			String rtn_dt, String chng_dt, String delivery_confirm_date, String cancel_dt, String class_cd1,
			String class_cd2, String class_cd3, String class_cd4, String brand_nm, String delivery_id,
			String invoice_no, String hope_delv_date, String fld_dsp, String inv_send_msg, String model_no,
			String set_gubun, String etc_msg, String delv_msg1, String mul_delv_msg, String barcode, String inv_send_dm,
			String delivery_method_str2, String order_etc_1, String order_etc_2, String order_etc_3, String order_etc_4,
			String order_etc_5, String order_etc_6, String order_etc_7, String order_etc_8, String order_etc_9,
			String order_etc_10, String order_etc_11, String order_etc_12, String order_etc_13, String order_etc_14,
			String label) {
		super();
		this.ordseq = ordseq;
		this.compno = compno;
		this.reg_date = reg_date;
		this.order_id = order_id;
		this.shopid = shopid;
		this.shop_userid = shop_userid;
		this.order_status = order_status;
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_tel = user_tel;
		this.user_cel = user_cel;
		this.user_email = user_email;
		this.receive_tel = receive_tel;
		this.receive_cel = receive_cel;
		this.receive_email = receive_email;
		this.delv_msg = delv_msg;
		this.receive_name = receive_name;
		this.receive_zipcode = receive_zipcode;
		this.receive_addr = receive_addr;
		this.total_cost = total_cost;
		this.pay_cost = pay_cost;
		this.order_date = order_date;
		this.partner_id = partner_id;
		this.dpartner_id = dpartner_id;
		this.mall_product_id = mall_product_id;
		this.product_id = product_id;
		this.sku_id = sku_id;
		this.p_product_name = p_product_name;
		this.p_sku_value = p_sku_value;
		this.product_name = product_name;
		this.sale_cost = sale_cost;
		this.mall_won_cost = mall_won_cost;
		this.won_cost = won_cost;
		this.sku_value = sku_value;
		this.sale_cnt = sale_cnt;
		this.delivery_method_str = delivery_method_str;
		this.delv_cost = delv_cost;
		this.compayny_goods_cd = compayny_goods_cd;
		this.sku_alias = sku_alias;
		this.box_ea = box_ea;
		this.jung_chk_yn = jung_chk_yn;
		this.mall_order_seq = mall_order_seq;
		this.mall_order_id = mall_order_id;
		this.etc_field3 = etc_field3;
		this.order_gubun = order_gubun;
		this.p_ea = p_ea;
		this.ord_field2 = ord_field2;
		this.copy_idx = copy_idx;
		this.goods_nm_pr = goods_nm_pr;
		this.goods_keyword = goods_keyword;
		this.ord_confirm_date = ord_confirm_date;
		this.rtn_dt = rtn_dt;
		this.chng_dt = chng_dt;
		this.delivery_confirm_date = delivery_confirm_date;
		this.cancel_dt = cancel_dt;
		this.class_cd1 = class_cd1;
		this.class_cd2 = class_cd2;
		this.class_cd3 = class_cd3;
		this.class_cd4 = class_cd4;
		this.brand_nm = brand_nm;
		this.delivery_id = delivery_id;
		this.invoice_no = invoice_no;
		this.hope_delv_date = hope_delv_date;
		this.fld_dsp = fld_dsp;
		this.inv_send_msg = inv_send_msg;
		this.model_no = model_no;
		this.set_gubun = set_gubun;
		this.etc_msg = etc_msg;
		this.delv_msg1 = delv_msg1;
		this.mul_delv_msg = mul_delv_msg;
		this.barcode = barcode;
		this.inv_send_dm = inv_send_dm;
		this.delivery_method_str2 = delivery_method_str2;
		this.order_etc_1 = order_etc_1;
		this.order_etc_2 = order_etc_2;
		this.order_etc_3 = order_etc_3;
		this.order_etc_4 = order_etc_4;
		this.order_etc_5 = order_etc_5;
		this.order_etc_6 = order_etc_6;
		this.order_etc_7 = order_etc_7;
		this.order_etc_8 = order_etc_8;
		this.order_etc_9 = order_etc_9;
		this.order_etc_10 = order_etc_10;
		this.order_etc_11 = order_etc_11;
		this.order_etc_12 = order_etc_12;
		this.order_etc_13 = order_etc_13;
		this.order_etc_14 = order_etc_14;
		this.label = label;
	}



	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getOrdseq() {
		return ordseq;
	}

	public void setOrdseq(String ordseq) {
		this.ordseq = ordseq;
	}

	public String getCompno() {
		return compno;
	}

	public void setCompno(String compno) {
		this.compno = compno;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getShop_userid() {
		return shop_userid;
	}

	public void setShop_userid(String shop_userid) {
		this.shop_userid = shop_userid;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_cel() {
		return user_cel;
	}

	public void setUser_cel(String user_cel) {
		this.user_cel = user_cel;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getReceive_tel() {
		return receive_tel;
	}

	public void setReceive_tel(String receive_tel) {
		this.receive_tel = receive_tel;
	}

	public String getReceive_cel() {
		return receive_cel;
	}

	public void setReceive_cel(String receive_cel) {
		this.receive_cel = receive_cel;
	}

	public String getReceive_email() {
		return receive_email;
	}

	public void setReceive_email(String receive_email) {
		this.receive_email = receive_email;
	}

	public String getDelv_msg() {
		return delv_msg;
	}

	public void setDelv_msg(String delv_msg) {
		this.delv_msg = delv_msg;
	}

	public String getReceive_name() {
		return receive_name;
	}

	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}

	public String getReceive_zipcode() {
		return receive_zipcode;
	}

	public void setReceive_zipcode(String receive_zipcode) {
		this.receive_zipcode = receive_zipcode;
	}

	public String getReceive_addr() {
		return receive_addr;
	}

	public void setReceive_addr(String receive_addr) {
		this.receive_addr = receive_addr;
	}

	public String getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(String total_cost) {
		this.total_cost = total_cost;
	}

	public String getPay_cost() {
		return pay_cost;
	}

	public void setPay_cost(String pay_cost) {
		this.pay_cost = pay_cost;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
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

	public String getMall_product_id() {
		return mall_product_id;
	}

	public void setMall_product_id(String mall_product_id) {
		this.mall_product_id = mall_product_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getSku_id() {
		return sku_id;
	}

	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}

	public String getP_product_name() {
		return p_product_name;
	}

	public void setP_product_name(String p_product_name) {
		this.p_product_name = p_product_name;
	}

	public String getP_sku_value() {
		return p_sku_value;
	}

	public void setP_sku_value(String p_sku_value) {
		this.p_sku_value = p_sku_value;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getSale_cost() {
		return sale_cost;
	}

	public void setSale_cost(String sale_cost) {
		this.sale_cost = sale_cost;
	}

	public String getMall_won_cost() {
		return mall_won_cost;
	}

	public void setMall_won_cost(String mall_won_cost) {
		this.mall_won_cost = mall_won_cost;
	}

	public String getWon_cost() {
		return won_cost;
	}

	public void setWon_cost(String won_cost) {
		this.won_cost = won_cost;
	}

	public String getSku_value() {
		return sku_value;
	}

	public void setSku_value(String sku_value) {
		this.sku_value = sku_value;
	}

	public String getSale_cnt() {
		return sale_cnt;
	}

	public void setSale_cnt(String sale_cnt) {
		this.sale_cnt = sale_cnt;
	}

	public String getDelivery_method_str() {
		return delivery_method_str;
	}

	public void setDelivery_method_str(String delivery_method_str) {
		this.delivery_method_str = delivery_method_str;
	}

	public String getDelv_cost() {
		return delv_cost;
	}

	public void setDelv_cost(String delv_cost) {
		this.delv_cost = delv_cost;
	}

	public String getCompayny_goods_cd() {
		return compayny_goods_cd;
	}

	public void setCompayny_goods_cd(String compayny_goods_cd) {
		this.compayny_goods_cd = compayny_goods_cd;
	}

	public String getSku_alias() {
		return sku_alias;
	}

	public void setSku_alias(String sku_alias) {
		this.sku_alias = sku_alias;
	}

	public String getBox_ea() {
		return box_ea;
	}

	public void setBox_ea(String box_ea) {
		this.box_ea = box_ea;
	}

	public String getJung_chk_yn() {
		return jung_chk_yn;
	}

	public void setJung_chk_yn(String jung_chk_yn) {
		this.jung_chk_yn = jung_chk_yn;
	}

	public String getMall_order_seq() {
		return mall_order_seq;
	}

	public void setMall_order_seq(String mall_order_seq) {
		this.mall_order_seq = mall_order_seq;
	}

	public String getMall_order_id() {
		return mall_order_id;
	}

	public void setMall_order_id(String mall_order_id) {
		this.mall_order_id = mall_order_id;
	}

	public String getEtc_field3() {
		return etc_field3;
	}

	public void setEtc_field3(String etc_field3) {
		this.etc_field3 = etc_field3;
	}

	public String getOrder_gubun() {
		return order_gubun;
	}

	public void setOrder_gubun(String order_gubun) {
		this.order_gubun = order_gubun;
	}

	public String getP_ea() {
		return p_ea;
	}

	public void setP_ea(String p_ea) {
		this.p_ea = p_ea;
	}

	public String getOrd_field2() {
		return ord_field2;
	}

	public void setOrd_field2(String ord_field2) {
		this.ord_field2 = ord_field2;
	}

	public String getCopy_idx() {
		return copy_idx;
	}

	public void setCopy_idx(String copy_idx) {
		this.copy_idx = copy_idx;
	}

	public String getGoods_nm_pr() {
		return goods_nm_pr;
	}

	public void setGoods_nm_pr(String goods_nm_pr) {
		this.goods_nm_pr = goods_nm_pr;
	}

	public String getGoods_keyword() {
		return goods_keyword;
	}

	public void setGoods_keyword(String goods_keyword) {
		this.goods_keyword = goods_keyword;
	}

	public String getOrd_confirm_date() {
		return ord_confirm_date;
	}

	public void setOrd_confirm_date(String ord_confirm_date) {
		this.ord_confirm_date = ord_confirm_date;
	}

	public String getRtn_dt() {
		return rtn_dt;
	}

	public void setRtn_dt(String rtn_dt) {
		this.rtn_dt = rtn_dt;
	}

	public String getChng_dt() {
		return chng_dt;
	}

	public void setChng_dt(String chng_dt) {
		this.chng_dt = chng_dt;
	}

	public String getDelivery_confirm_date() {
		return delivery_confirm_date;
	}

	public void setDelivery_confirm_date(String delivery_confirm_date) {
		this.delivery_confirm_date = delivery_confirm_date;
	}

	public String getCancel_dt() {
		return cancel_dt;
	}

	public void setCancel_dt(String cancel_dt) {
		this.cancel_dt = cancel_dt;
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

	public String getBrand_nm() {
		return brand_nm;
	}

	public void setBrand_nm(String brand_nm) {
		this.brand_nm = brand_nm;
	}

	public String getDelivery_id() {
		return delivery_id;
	}

	public void setDelivery_id(String delivery_id) {
		this.delivery_id = delivery_id;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public String getHope_delv_date() {
		return hope_delv_date;
	}

	public void setHope_delv_date(String hope_delv_date) {
		this.hope_delv_date = hope_delv_date;
	}

	public String getFld_dsp() {
		return fld_dsp;
	}

	public void setFld_dsp(String fld_dsp) {
		this.fld_dsp = fld_dsp;
	}

	public String getInv_send_msg() {
		return inv_send_msg;
	}

	public void setInv_send_msg(String inv_send_msg) {
		this.inv_send_msg = inv_send_msg;
	}

	public String getModel_no() {
		return model_no;
	}

	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}

	public String getSet_gubun() {
		return set_gubun;
	}

	public void setSet_gubun(String set_gubun) {
		this.set_gubun = set_gubun;
	}

	public String getEtc_msg() {
		return etc_msg;
	}

	public void setEtc_msg(String etc_msg) {
		this.etc_msg = etc_msg;
	}

	public String getDelv_msg1() {
		return delv_msg1;
	}

	public void setDelv_msg1(String delv_msg1) {
		this.delv_msg1 = delv_msg1;
	}

	public String getMul_delv_msg() {
		return mul_delv_msg;
	}

	public void setMul_delv_msg(String mul_delv_msg) {
		this.mul_delv_msg = mul_delv_msg;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getInv_send_dm() {
		return inv_send_dm;
	}

	public void setInv_send_dm(String inv_send_dm) {
		this.inv_send_dm = inv_send_dm;
	}

	public String getDelivery_method_str2() {
		return delivery_method_str2;
	}

	public void setDelivery_method_str2(String delivery_method_str2) {
		this.delivery_method_str2 = delivery_method_str2;
	}

	public String getOrder_etc_1() {
		return order_etc_1;
	}

	public void setOrder_etc_1(String order_etc_1) {
		this.order_etc_1 = order_etc_1;
	}

	public String getOrder_etc_2() {
		return order_etc_2;
	}

	public void setOrder_etc_2(String order_etc_2) {
		this.order_etc_2 = order_etc_2;
	}

	public String getOrder_etc_3() {
		return order_etc_3;
	}

	public void setOrder_etc_3(String order_etc_3) {
		this.order_etc_3 = order_etc_3;
	}

	public String getOrder_etc_4() {
		return order_etc_4;
	}

	public void setOrder_etc_4(String order_etc_4) {
		this.order_etc_4 = order_etc_4;
	}

	public String getOrder_etc_5() {
		return order_etc_5;
	}

	public void setOrder_etc_5(String order_etc_5) {
		this.order_etc_5 = order_etc_5;
	}

	public String getOrder_etc_6() {
		return order_etc_6;
	}

	public void setOrder_etc_6(String order_etc_6) {
		this.order_etc_6 = order_etc_6;
	}

	public String getOrder_etc_7() {
		return order_etc_7;
	}

	public void setOrder_etc_7(String order_etc_7) {
		this.order_etc_7 = order_etc_7;
	}

	public String getOrder_etc_8() {
		return order_etc_8;
	}

	public void setOrder_etc_8(String order_etc_8) {
		this.order_etc_8 = order_etc_8;
	}

	public String getOrder_etc_9() {
		return order_etc_9;
	}

	public void setOrder_etc_9(String order_etc_9) {
		this.order_etc_9 = order_etc_9;
	}

	public String getOrder_etc_10() {
		return order_etc_10;
	}

	public void setOrder_etc_10(String order_etc_10) {
		this.order_etc_10 = order_etc_10;
	}

	public String getOrder_etc_11() {
		return order_etc_11;
	}

	public void setOrder_etc_11(String order_etc_11) {
		this.order_etc_11 = order_etc_11;
	}

	public String getOrder_etc_12() {
		return order_etc_12;
	}

	public void setOrder_etc_12(String order_etc_12) {
		this.order_etc_12 = order_etc_12;
	}

	public String getOrder_etc_13() {
		return order_etc_13;
	}

	public void setOrder_etc_13(String order_etc_13) {
		this.order_etc_13 = order_etc_13;
	}

	public String getOrder_etc_14() {
		return order_etc_14;
	}

	public void setOrder_etc_14(String order_etc_14) {
		this.order_etc_14 = order_etc_14;
	}
	
}

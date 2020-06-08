package com.kdj.mlink.ezup3.shop.dao;

public class DomesinShopProductDto {
	private String icode;//도매의신상품코드
	private String iname;//상품명
	private String cid;//카테고리아이디
	private String shop_cid_info;//전송쇼핑몰정보
	private String icountry;//원산지정보
	private String maker;//제조사
	private String brand;//브랜드명
	private String tax;//과세여부 0:과세, 1:면세
	private String vender_code;//공급사코드
	private String delivery_type;//배송구분 0: 기본배송, 1: 무료, 2:착불, 3:수량별
	private String delivery_amount;//배송비
	private String delivery_qty;//묶음배송수량
	private String keyword;//검색키워드
	private String price;//판매가
	private String price_consumer;//일반소비자 가격
	private String islimit;//소비자 준수가격
	private String limit_price;//가격준수가
	private String content;//상품상세설명
	private String img;//상품이미지배열
	private String select_option;//상품선택옵션
	private String text_option;//상품입력옵션
	private String notice;//상품개별공지이상품은 해외배송으로 7~15일의 배송기간이 소요 됩니다.
	private String itype;//상품상태타입  0:새상품, 1:중고상품
	private String status;//상품판매상태  0:판매중, 1:품절, 2:단종
	private String adult;//0:전체이용가, 1:성인전용상품
	private String reg_datetime;//최초상품등록일
	private String up_datetime;//상품수정일
	private String isreturn;//1:반품가능, 0:반품불가
	private String cert_type;//0:인증대상아님, 1:인증대상, 2:상세설명에 표기
	private String cert;//상품인증코드 C20
	private String cert_no;//상품인증번호
	private String gosi_code;//
	private String gosi_info;//상품고시 항목

}

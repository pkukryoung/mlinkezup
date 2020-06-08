package com.kdj.mlink.ezup3.shop.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.swt.widgets.Shell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kdj.mlink.ezup3.shop.common.ChromeExtention;
import com.kdj.mlink.ezup3.shop.common.ChromeScript;
import com.kdj.mlink.ezup3.shop.common.OrderStatus;
import com.kdj.mlink.ezup3.shop.common.ShopCommon;
import com.kdj.mlink.ezup3.common.YDMAProgressBar;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.AlimTalkChargeDao;
import com.kdj.mlink.ezup3.data.dao.CompInfoDao;
import com.kdj.mlink.ezup3.data.dao.CustomerDao;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;
import com.kdj.mlink.ezup3.data.dao.OrderDao;
import com.kdj.mlink.ezup3.data.dao.ProductIforDao;
import com.kdj.mlink.ezup3.data.dao.ProductMstDao;
import com.kdj.mlink.ezup3.data.dao.ShopOrderDto;
import com.kdj.mlink.ezup3.data.dao.ShopProductDto;
import com.kdj.mlink.ezup3.data.dao.ShoppingMallDto;

public class ShopOrderDao {
	ChromeExtention chrome = ChromeExtention.getInstace();
	ChromeDriver driver = null;
	private static ShopOrderDao daoInstance = new ShopOrderDao(); // dao 싱글톤으로 생성 한다.

	public ShopOrderDao() {
	}

	public static ShopOrderDao get() {
		return daoInstance;
	}

	public void SendMLinkAutoProcessing(int ordSeq, String user) throws Exception {
		Connection connection = null;
		CallableStatement cStmt = null;
		ResultSet rs = null;

		try {

			connection = DBCPInit.getInstance().getConnection();

			cStmt = connection.prepareCall("{call YWM_SHOPORDAUTO_PROC(?, ?)}");

			cStmt.setInt(1, ordSeq);
			cStmt.setString(2, user);

			System.out.println("[SendMLinkAutoProcessing]" + cStmt.toString());

			cStmt.execute();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, cStmt, rs);
		}

	}

	/*
	 * 송장번호를 업데이트 한다..
	 */
	public int UpdateInvoiceOrder(int ordSeq, int wonCost, int mallWonCost, String deliveryCode, String invoNo)
			throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE shopordmst \r\n");
			sql.append("SET WON_COST=? \r\n");
			sql.append(",MALL_WON_COST=? \r\n");
			sql.append(",ORDER_STATUS =? \r\n");
			sql.append(",DELIVERY_ID=? \r\n");
			sql.append(",INVOICE_NO=? \r\n");
			sql.append("WHERE ORDSEQ=?");

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());

			int rowIdx = 0;
			pstmt.setInt(++rowIdx, wonCost);
			pstmt.setInt(++rowIdx, mallWonCost);
			pstmt.setString(++rowIdx, OrderStatus.송장입력);
			pstmt.setString(++rowIdx, deliveryCode);
			pstmt.setString(++rowIdx, invoNo);
			pstmt.setInt(++rowIdx, ordSeq);

			System.out.println("[INVOIC UPDATE]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	/// <summary>
	/// 주문 오더 가져오기.
	/// 1. 시작일 2. 종료일
	/// 3.
	/// </summary>
	/// <returns></returns>
	public List<ShopOrderMstDto> getShopOrderList(String startdt, String enddt, String searchText, String search1,
			String search2, String search3) throws Exception {
		List<ShopOrderMstDto> list = new ArrayList<ShopOrderMstDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT \r\n");
			sql.append("IFNULL(M.ORDSEQ,  '' ) AS ORDSEQ,  \r\n");// 일련번호
			sql.append("IFNULL(M.COMPNO,  '' ) AS COMPNO,  \r\n");// 밴더코드
			sql.append("IFNULL(M.REG_DATE,  '' ) AS REG_DATE,  \r\n");// 수집일자
			sql.append("IFNULL(M.ORDER_ID,  '' ) AS ORDER_ID,  \r\n");// 주문번호(쇼핑몰)
			sql.append("IFNULL(M.SHOPID,  '' ) AS SHOPID,  \r\n");// 쇼핑몰명
			sql.append("IFNULL(M.SHOP_USERID,  '' ) AS SHOP_USERID,  \r\n");// 쇼핑몰ID
			sql.append("IFNULL(M.ORDER_STATUS,  '' ) AS ORDER_STATUS,  \r\n");// 주문상태
			sql.append("IFNULL(M.USER_ID,  '' ) AS USER_ID,  \r\n");// 주문자ID
			sql.append("IFNULL(M.USER_NAME,  '' ) AS USER_NAME,  \r\n");// 주문자명
			sql.append("IFNULL(M.USER_TEL,  '' ) AS USER_TEL,  \r\n");// 주문자전화번호
			sql.append("IFNULL(M.USER_CEL,  '' ) AS USER_CEL,  \r\n");// 주문자핸드폰번호
			sql.append("IFNULL(M.USER_EMAIL,  '' ) AS USER_EMAIL,  \r\n");// 주문자이메일주소
			sql.append("IFNULL(M.RECEIVE_TEL,  '' ) AS RECEIVE_TEL,  \r\n");// 수취인전화번호
			sql.append("IFNULL(M.RECEIVE_CEL,  '' ) AS RECEIVE_CEL,  \r\n");// 수취인핸드폰번호
			sql.append("IFNULL(M.RECEIVE_EMAIL,  '' ) AS RECEIVE_EMAIL,  \r\n");// 수취인이메일주소
			sql.append("IFNULL(M.DELV_MSG,  '' ) AS DELV_MSG,  \r\n");// 배송메세지
			sql.append("IFNULL(M.RECEIVE_NAME,  '' ) AS RECEIVE_NAME,  \r\n");// 수취인명
			sql.append("IFNULL(M.RECEIVE_ZIPCODE,  '' ) AS RECEIVE_ZIPCODE,  \r\n");// 수취인우편번호
			sql.append("IFNULL(M.RECEIVE_ADDR,  '' ) AS RECEIVE_ADDR,  \r\n");// 수취인주소
			sql.append("IFNULL(M.TOTAL_COST,  '' ) AS TOTAL_COST,  \r\n");// 주문금액
			sql.append("IFNULL(M.PAY_COST,  '' ) AS PAY_COST,  \r\n");// 결제금액
			sql.append("IFNULL(M.ORDER_DATE,  '' ) AS ORDER_DATE,  \r\n");// 주문일자
			sql.append("IFNULL(M.PARTNER_ID,  '' ) AS PARTNER_ID,  \r\n");// 매입처명
			sql.append("IFNULL(M.DPARTNER_ID,  '' ) AS DPARTNER_ID,  \r\n");// 물류처명
			sql.append("IFNULL(M.MALL_PRODUCT_ID,  '' ) AS MALL_PRODUCT_ID,  \r\n");// 상품코드(쇼핑몰)
			sql.append("IFNULL(M.PRODUCT_ID,  '' ) AS PRODUCT_ID,  \r\n");// 품번코드(엠링크)
			sql.append("IFNULL(M.SKU_ID,  '' ) AS SKU_ID,  \r\n");// 단품코드(엠링크)
			sql.append("IFNULL(M.P_PRODUCT_NAME,  '' ) AS P_PRODUCT_NAME,  \r\n");// 상품명(확정)
			sql.append("IFNULL(M.P_SKU_VALUE,  '' ) AS P_SKU_VALUE,  \r\n");// 옵션(확정)
			sql.append("IFNULL(M.PRODUCT_NAME,  '' ) AS PRODUCT_NAME,  \r\n");// 상품명(수집)
			sql.append("IFNULL(M.SALE_COST,  '' ) AS SALE_COST,  \r\n");// 판매가(수집)
			sql.append("IFNULL(M.MALL_WON_COST,  '0' ) AS MALL_WON_COST,  \r\n");// 공급단가
			sql.append("IFNULL(M.WON_COST,  '0' ) AS WON_COST,  \r\n");// 원가
			sql.append("IFNULL(M.SKU_VALUE,  '' ) AS SKU_VALUE,  \r\n");// 옵션(수집)
			sql.append("IFNULL(M.SALE_CNT,  '' ) AS SALE_CNT,  \r\n");// 수량
			sql.append("IFNULL(M.DELIVERY_METHOD_STR,  '' ) AS DELIVERY_METHOD_STR,  \r\n");// 배송구분
			sql.append("IFNULL(M.DELV_COST,  '' ) AS DELV_COST,  \r\n");// 배송비(수집)
			sql.append("IFNULL(M.COMPAYNY_GOODS_CD,  '' ) AS COMPAYNY_GOODS_CD,  \r\n");// 자체상품코드
			sql.append("IFNULL(M.SKU_ALIAS,  '' ) AS SKU_ALIAS,  \r\n");// 옵션별칭
			sql.append("IFNULL(M.BOX_EA,  '' ) AS BOX_EA,  \r\n");// EA(상품)
			sql.append("IFNULL(M.JUNG_CHK_YN,  '' ) AS JUNG_CHK_YN,  \r\n");// 정산대조확인여부
			sql.append("IFNULL(M.MALL_ORDER_SEQ,  '' ) AS MALL_ORDER_SEQ,  \r\n");// 주문순번
			sql.append("IFNULL(M.MALL_ORDER_ID,  '' ) AS MALL_ORDER_ID,  \r\n");// 부주문번호
			sql.append("IFNULL(M.ETC_FIELD3,  '' ) AS ETC_FIELD3,  \r\n");// 수정된 수집옵션명
			sql.append("IFNULL(M.ORDER_GUBUN,  '' ) AS ORDER_GUBUN,  \r\n");// 주문구분
			sql.append("IFNULL(M.P_EA,  '' ) AS P_EA,  \r\n");// EA(확정)
			sql.append("IFNULL(M.ORD_FIELD2,  '' ) AS ORD_FIELD2,  \r\n");// 세트분리주문구분
			sql.append("IFNULL(M.COPY_IDX,  '' ) AS COPY_IDX,  \r\n");// 원주문번호(사방넷)
			sql.append("IFNULL(M.GOODS_NM_PR,  '' ) AS GOODS_NM_PR,  \r\n");// 출력상품명
			sql.append("IFNULL(M.GOODS_KEYWORD,  '' ) AS GOODS_KEYWORD,  \r\n");// 상품약어
			sql.append("IFNULL(M.ORD_CONFIRM_DATE,  '' ) AS ORD_CONFIRM_DATE,  \r\n");// 주문 확인일자
			sql.append("IFNULL(M.RTN_DT,  '' ) AS RTN_DT,  \r\n");// 반품 완료일자
			sql.append("IFNULL(M.CHNG_DT,  '' ) AS CHNG_DT,  \r\n");// 교환 완료일자
			sql.append("IFNULL(M.DELIVERY_CONFIRM_DATE,  '' ) AS DELIVERY_CONFIRM_DATE,  \r\n");// 출고 완료일자
			sql.append("IFNULL(M.CANCEL_DT,  '' ) AS CANCEL_DT,  \r\n");// 취소 완료일자
			sql.append("IFNULL(M.CLASS_CD1,  '' ) AS CLASS_CD1,  \r\n");// 대분류코드
			sql.append("IFNULL(M.CLASS_CD2,  '' ) AS CLASS_CD2,  \r\n");// 중분류코드
			sql.append("IFNULL(M.CLASS_CD3,  '' ) AS CLASS_CD3,  \r\n");// 소분류코드
			sql.append("IFNULL(M.CLASS_CD4,  '' ) AS CLASS_CD4,  \r\n");// 세분류코드
			sql.append("IFNULL(M.BRAND_NM,  '' ) AS BRAND_NM,  \r\n");// 브랜드명
			sql.append("IFNULL(M.DELIVERY_ID,  '' ) AS DELIVERY_ID,  \r\n");// 택배사코드
			sql.append("IFNULL(M.INVOICE_NO,  '' ) AS INVOICE_NO,  \r\n");// 송장번호
			sql.append("IFNULL(M.HOPE_DELV_DATE,  '' ) AS HOPE_DELV_DATE,  \r\n");// 배송희망일자
			sql.append("IFNULL(M.FLD_DSP,  '' ) AS FLD_DSP,  \r\n");// 주문엑셀용
			sql.append("IFNULL(M.INV_SEND_MSG,  '' ) AS INV_SEND_MSG,  \r\n");// 운송장 전송 결과 메시지
			sql.append("IFNULL(M.MODEL_NO,  '' ) AS MODEL_NO,  \r\n");// 모델NO
			sql.append("IFNULL(M.SET_GUBUN,  '' ) AS SET_GUBUN,  \r\n");// 상품구분
			sql.append("IFNULL(M.ETC_MSG,  '' ) AS ETC_MSG,  \r\n");// 기타메세지
			sql.append("IFNULL(M.DELV_MSG1,  '' ) AS DELV_MSG1,  \r\n");// 배송메세지
			sql.append("IFNULL(M.MUL_DELV_MSG,  '' ) AS MUL_DELV_MSG,  \r\n");// 물류메세지
			sql.append("IFNULL(M.BARCODE,  '' ) AS BARCODE,  \r\n");// 바코드
			sql.append("IFNULL(M.INV_SEND_DM,  '' ) AS INV_SEND_DM,  \r\n");// 송장전송일자
			sql.append("IFNULL(M.DELIVERY_METHOD_STR2,  '' ) AS DELIVERY_METHOD_STR2,  \r\n");// 배송구분(배송비반영)
			sql.append("IFNULL(M.ORDER_ETC_1,  '' ) AS ORDER_ETC_1,  \r\n");// 자사몰필드
			sql.append("IFNULL(M.ORDER_ETC_2,  '' ) AS ORDER_ETC_2,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_3,  '' ) AS ORDER_ETC_3,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_4,  '' ) AS ORDER_ETC_4,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_5,  '' ) AS ORDER_ETC_5,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_6,  '' ) AS ORDER_ETC_6,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_7,  '' ) AS ORDER_ETC_7,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_8,  '' ) AS ORDER_ETC_8,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_9,  '' ) AS ORDER_ETC_9,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_10,  '' ) AS ORDER_ETC_10,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_11,  '' ) AS ORDER_ETC_11,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_12,  '' ) AS ORDER_ETC_12,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_13,  '' ) AS ORDER_ETC_13,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_14,  '' ) AS ORDER_ETC_14,  \r\n");// 임시필드
			sql.append("IFNULL(M.LABEL,  '' ) AS LABEL,  \r\n");// 라벨
			sql.append("IFNULL(D.APIKEY,  '' ) AS APIKEY,  \r\n");// 라벨
			sql.append("IFNULL(D.PASSWORD,  '' ) AS PASSWORD,  \r\n");// 라벨
			sql.append("IFNULL(D.AUTHKEY1,  '' ) AS AUTHKEY1,  \r\n");// 라벨
			sql.append("IFNULL(D.AUTHKEY2,  '' ) AS AUTHKEY2, \r\n");// 라벨
			sql.append("IFNULL(D.NICKNM2 ,'') AS VENDORID  ,    \r\n");
			sql.append("IFNULL(M.RECEIPTID ,'') AS RECEIPTID,      \r\n");
			sql.append("IFNULL(M.EXCHANGEID  ,'') AS EXCHANGEID  ,    \r\n");
			sql.append("IFNULL(M.DOSINORDCD  ,'') AS DOSINORDCD  ,    \r\n");// 도신코드
			sql.append("IFNULL(M.DS_ORDER_STATUS  ,'') AS DS_ORDER_STATUS  ,    \r\n");// 도신코드
			sql.append("(SELECT COUNT(ORDSEQ) FROM SHOPORDCS WHERE ORDSEQ =M.ORDSEQ AND CSCLOSE='Y' ) AS CSCLOSE \r\n");
			sql.append(" FROM shopordmst AS M \r\n");
			sql.append(" INNER JOIN shopdtl AS D \r\n");
			sql.append(" ON M.COMPNO = D.COMPNO \r\n");
			sql.append(" AND M.SHOPID = D.SHOPCD \r\n");
			sql.append(" AND M.SHOP_USERID = D.SHOPPINGID AND M.AUTHKEY1=D.AUTHKEY1 \r\n");
			sql.append(" WHERE M.COMPNO = ?  ");

			boolean isNum = searchText.matches("-?\\d+(\\.\\d+)?");
			List<String> cols = null;

			if (isNum) { // 숫자만..
				cols = Arrays.asList("  AND ( ", "  (SALE_CNT %s)", " OR (WON_COST %s)", " OR (MALL_WON_COST %s)",
						" OR (SALE_COST  %s)", " OR (DELV_COST %s) )");
			} else {
				cols = Arrays.asList("  AND ( ", "  (ORDER_STATUS %s)", " OR (SHOPID %s)", " OR (SHOP_USERID %s)",
						" OR (RECEIVE_NAME %s)", " OR (MALL_PRODUCT_ID %s)", " OR (COMPAYNY_GOODS_CD %s)",
						" OR (P_PRODUCT_NAME %s)", " OR (P_SKU_VALUE %s)", " OR (DELIVERY_METHOD_STR2 %s)",
						" OR (DELV_MSG %s)", " OR (DELIVERY_ID %s)", " OR (INVOICE_NO %s)", " OR (LABEL %s) ) ");
			}

			if (!searchText.trim().equals("") && !search2.trim().equals("")) // 검색 조건이 없거나 검색조건 2 전체가 아닌경우 search3 날짜로만
																				// 검색한다..
			{
				if (search1.equals("부분일치")) {
					sql.append(String.format(" AND M.%s LIKE '%s%s%s' ", search2, "%", searchText, "%"));
				} else { // 완전일치..
					sql.append(String.format(" AND M.%s = '%s' ", search2, searchText));
				}
			} else {
				String query = "";
				if (search1.equals("부분일치")) {
					query = cols.stream().map(p -> String.format(p, " LIKE '%" + searchText + "%'"))
							.collect(Collectors.joining());
					sql.append(query);

				} else {
					query = cols.stream().map(p -> String.format(p, " = '" + searchText + "'"))
							.collect(Collectors.joining());
					sql.append(query);
				}
			}

			sql.append(String.format(" AND (M.%s >= ? AND  M.%s <= ?)  ORDER BY %s DESC ", search3, search3, search3));

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, YDMATimeUtil.convertSabangNetOrderDateToOrddt(startdt, false));
			pstmt.setString(3, YDMATimeUtil.convertSabangNetOrderDateToOrddt(enddt, false));

			System.out.println("[ShopOrderList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			Integer rowNum = 0;

			while (rs.next()) {
				ShopOrderMstDto dto = new ShopOrderMstDto();
				++rowNum;
				dto.setNo(rowNum.toString());
				dto.setReg_date(rs.getString("REG_DATE"));// 수집일자
				dto.setOrder_date(rs.getString("ORDER_DATE"));// 주문일자
				dto.setOrder_status(rs.getString("ORDER_STATUS"));// 주문상태
				dto.setShopid(rs.getString("SHOPID"));// 쇼핑몰명
				dto.setShop_userid(rs.getString("SHOP_USERID"));// 쇼핑몰ID
				dto.setReceive_name(rs.getString("RECEIVE_NAME"));// 수령자
				dto.setMall_product_id(rs.getString("MALL_PRODUCT_ID"));// 상품코드(쇼핑몰)
				dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD"));// 자체상품코드
				dto.setP_product_name(rs.getString("P_PRODUCT_NAME"));// 상품명(확정)
				dto.setP_sku_value(rs.getString("P_SKU_VALUE"));// 옵션(확정)
				dto.setSale_cnt(rs.getString("SALE_CNT"));// 수량
				dto.setWon_cost(rs.getString("WON_COST"));// 원가
				dto.setMall_won_cost(rs.getString("MALL_WON_COST"));// 공급단가
				dto.setSale_cost(rs.getString("SALE_COST"));// 판매가(수집)
				dto.setDelv_cost(rs.getString("DELV_COST"));// 배송비(수집)
				dto.setDelivery_method_str2(rs.getString("DELIVERY_METHOD_STR2"));// 배송구분(배송비반영)
				dto.setDelv_msg(rs.getString("DELV_MSG"));// 배송메세지
				dto.setDelivery_id(rs.getString("DELIVERY_ID"));// 택배사코드 (배송사)
				dto.setInvoice_no(rs.getString("INVOICE_NO"));// 송장번호
				dto.setLabel(rs.getString("LABEL"));// 라벨부
				dto.setDs_order_status(rs.getString("DS_ORDER_STATUS"));//
				// --------------------- 아직 안쓰는 필드들 ----------------------------------//
				dto.setProduct_id(rs.getString("PRODUCT_ID"));// 품번코드(엠링크)
				dto.setOrdseq(rs.getString("ORDSEQ"));// 일련번호
				dto.setCompno(rs.getString("COMPNO"));// 밴더코드
				dto.setOrder_id(rs.getString("ORDER_ID"));// 주문번호(쇼핑몰)
				dto.setUser_id(rs.getString("USER_ID"));// 주문자ID
				dto.setUser_name(rs.getString("USER_NAME"));// 주문자명
				dto.setUser_tel(rs.getString("USER_TEL"));// 주문자전화번호
				dto.setUser_cel(rs.getString("USER_CEL"));// 주문자핸드폰번호
				dto.setUser_email(rs.getString("USER_EMAIL"));// 주문자이메일주소
				dto.setReceive_tel(rs.getString("RECEIVE_TEL"));// 수취인전화번호
				dto.setReceive_cel(rs.getString("RECEIVE_CEL"));// 수취인핸드폰번호
				dto.setReceive_email(rs.getString("RECEIVE_EMAIL"));// 수취인이메일주소
				dto.setReceive_zipcode(rs.getString("RECEIVE_ZIPCODE"));// 수취인우편번호
				dto.setReceive_addr(rs.getString("RECEIVE_ADDR"));// 수취인주소
				dto.setTotal_cost(rs.getString("TOTAL_COST"));// 주문금액
				dto.setPay_cost(rs.getString("PAY_COST"));// 결제금액
				dto.setPartner_id(rs.getString("PARTNER_ID"));// 매입처명
				dto.setDpartner_id(rs.getString("DPARTNER_ID"));// 물류처명
				dto.setSku_id(rs.getString("SKU_ID"));// 단품코드(엠링크)
				dto.setProduct_name(rs.getString("PRODUCT_NAME"));// 상품명(수집)
				dto.setSku_value(rs.getString("SKU_VALUE"));// 옵션(수집)
				dto.setDelivery_method_str(rs.getString("DELIVERY_METHOD_STR"));// 배송구분

				dto.setSku_alias(rs.getString("SKU_ALIAS"));// 옵션별칭
				dto.setBox_ea(rs.getString("BOX_EA"));// EA(상품)
				dto.setJung_chk_yn(rs.getString("JUNG_CHK_YN"));// 정산대조확인여부
				dto.setMall_order_seq(rs.getString("MALL_ORDER_SEQ"));// 주문순번
				dto.setMall_order_id(rs.getString("MALL_ORDER_ID"));// 부주문번호
				dto.setEtc_field3(rs.getString("ETC_FIELD3"));// 수정된 수집옵션명
				dto.setOrder_gubun(rs.getString("ORDER_GUBUN"));// 주문구분
				dto.setP_ea(rs.getString("P_EA"));// EA(확정)
				dto.setOrd_field2(rs.getString("ORD_FIELD2"));// 세트분리주문구분
				dto.setCopy_idx(rs.getString("COPY_IDX"));// 원주문번호(사방넷)
				dto.setGoods_nm_pr(rs.getString("GOODS_NM_PR"));// 출력상품명
				dto.setGoods_keyword(rs.getString("GOODS_KEYWORD"));// 상품약어
				dto.setOrd_confirm_date(rs.getString("ORD_CONFIRM_DATE"));// 주문 확인일자
				dto.setRtn_dt(rs.getString("RTN_DT"));// 반품 완료일자
				dto.setChng_dt(rs.getString("CHNG_DT"));// 교환 완료일자
				dto.setOrd_confirm_date(rs.getString("DELIVERY_CONFIRM_DATE"));// 출고 완료일자
				dto.setCancel_dt(rs.getString("CANCEL_DT"));// 취소 완료일자
				dto.setClass_cd1(rs.getString("CLASS_CD1"));// 대분류코드
				dto.setClass_cd2(rs.getString("CLASS_CD2"));// 중분류코드
				dto.setClass_cd3(rs.getString("CLASS_CD3"));// 소분류코드
				dto.setClass_cd4(rs.getString("CLASS_CD4"));// 세분류코드
				dto.setBrand_nm(rs.getString("BRAND_NM"));// 브랜드명

				dto.setHope_delv_date(rs.getString("HOPE_DELV_DATE"));// 배송희망일자
				dto.setFld_dsp(rs.getString("FLD_DSP"));// 주문엑셀용
				dto.setInv_send_msg(rs.getString("INV_SEND_MSG"));// 운송장 전송 결과 메시지
				dto.setModel_no(rs.getString("MODEL_NO"));// 모델NO
				dto.setSet_gubun(rs.getString("SET_GUBUN"));// 상품구분
				dto.setEtc_msg(rs.getString("ETC_MSG"));// 기타메세지
				dto.setDelv_msg1(rs.getString("DELV_MSG1"));// 배송메세지
				dto.setMul_delv_msg(rs.getString("MUL_DELV_MSG"));// 물류메세지
				dto.setBarcode(rs.getString("BARCODE"));// 바코드
				dto.setInv_send_dm(rs.getString("INV_SEND_DM"));// 송장전송일자

				dto.setOrder_etc_1(rs.getString("ORDER_ETC_1"));// 자사몰필드
				dto.setOrder_etc_2(rs.getString("ORDER_ETC_2"));// 임시필드
				dto.setOrder_etc_3(rs.getString("ORDER_ETC_3"));// 임시필드
				dto.setOrder_etc_4(rs.getString("ORDER_ETC_4"));// 임시필드
				dto.setOrder_etc_5(rs.getString("ORDER_ETC_5"));// 임시필드
				dto.setOrder_etc_6(rs.getString("ORDER_ETC_6"));// 임시필드
				dto.setOrder_etc_7(rs.getString("ORDER_ETC_7"));// 임시필드
				dto.setOrder_etc_8(rs.getString("ORDER_ETC_8"));// 임시필드
				dto.setOrder_etc_9(rs.getString("ORDER_ETC_9"));// 임시필드
				dto.setOrder_etc_10(rs.getString("ORDER_ETC_10"));// 임시필드
				dto.setOrder_etc_11(rs.getString("ORDER_ETC_11"));// 임시필드
				dto.setOrder_etc_12(rs.getString("ORDER_ETC_12"));// 임시필드
				dto.setOrder_etc_13(rs.getString("ORDER_ETC_13"));// 임시필드
				dto.setOrder_etc_14(rs.getString("ORDER_ETC_14"));// 임시필드
				dto.setShopPw(rs.getString("PASSWORD"));
				dto.setApikey(rs.getString("APIKEY"));

				dto.setAuthkey1(rs.getString("AUTHKEY1"));
				dto.setAuthKey2(rs.getString("AUTHKEY2"));
				dto.setVendorId(rs.getString("VENDORID"));

				dto.setReceiptid(rs.getString("RECEIPTID")); // 반품 접수 아이디..
				dto.setExchangeid(rs.getString("EXCHANGEID")); // 교환 접수 아이디..
				dto.setDosinOrder_code(rs.getString("DOSINORDCD")); // 도신코드
				dto.setCsClose(rs.getString("CSCLOSE"));
				list.add(dto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return list;
	}

	/// <summary>
	/// 주문 오더 가져오기.
	/// 1. 시작일 2. 종료일
	/// 3.
	/// </summary>
	/// <returns></returns>
	// isShopOrderMstExits(dto.getShopid(), dto.getOrder_id());
	public List<ShopOrderMstDto> getShopOrderID(String Shopid, String Order_id) throws Exception {
		List<ShopOrderMstDto> list = new ArrayList<ShopOrderMstDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT \r\n");
			sql.append("IFNULL(M.ORDSEQ,  '' ) AS ORDSEQ,  \r\n");// 일련번호
			sql.append("IFNULL(M.COMPNO,  '' ) AS COMPNO,  \r\n");// 밴더코드
			sql.append("IFNULL(M.REG_DATE,  '' ) AS REG_DATE,  \r\n");// 수집일자
			sql.append("IFNULL(M.ORDER_ID,  '' ) AS ORDER_ID,  \r\n");// 주문번호(쇼핑몰)
			sql.append("IFNULL(M.SHOPID,  '' ) AS SHOPID,  \r\n");// 쇼핑몰명
			sql.append("IFNULL(M.SHOP_USERID,  '' ) AS SHOP_USERID,  \r\n");// 쇼핑몰ID
			sql.append("IFNULL(M.ORDER_STATUS,  '' ) AS ORDER_STATUS,  \r\n");// 주문상태
			sql.append("IFNULL(M.USER_ID,  '' ) AS USER_ID,  \r\n");// 주문자ID
			sql.append("IFNULL(M.USER_NAME,  '' ) AS USER_NAME,  \r\n");// 주문자명
			sql.append("IFNULL(M.USER_TEL,  '' ) AS USER_TEL,  \r\n");// 주문자전화번호
			sql.append("IFNULL(M.USER_CEL,  '' ) AS USER_CEL,  \r\n");// 주문자핸드폰번호
			sql.append("IFNULL(M.USER_EMAIL,  '' ) AS USER_EMAIL,  \r\n");// 주문자이메일주소
			sql.append("IFNULL(M.RECEIVE_TEL,  '' ) AS RECEIVE_TEL,  \r\n");// 수취인전화번호
			sql.append("IFNULL(M.RECEIVE_CEL,  '' ) AS RECEIVE_CEL,  \r\n");// 수취인핸드폰번호
			sql.append("IFNULL(M.RECEIVE_EMAIL,  '' ) AS RECEIVE_EMAIL,  \r\n");// 수취인이메일주소
			sql.append("IFNULL(M.DELV_MSG,  '' ) AS DELV_MSG,  \r\n");// 배송메세지
			sql.append("IFNULL(M.RECEIVE_NAME,  '' ) AS RECEIVE_NAME,  \r\n");// 수취인명
			sql.append("IFNULL(M.RECEIVE_ZIPCODE,  '' ) AS RECEIVE_ZIPCODE,  \r\n");// 수취인우편번호
			sql.append("IFNULL(M.RECEIVE_ADDR,  '' ) AS RECEIVE_ADDR,  \r\n");// 수취인주소
			sql.append("IFNULL(M.TOTAL_COST,  '' ) AS TOTAL_COST,  \r\n");// 주문금액
			sql.append("IFNULL(M.PAY_COST,  '' ) AS PAY_COST,  \r\n");// 결제금액
			sql.append("IFNULL(M.ORDER_DATE,  '' ) AS ORDER_DATE,  \r\n");// 주문일자
			sql.append("IFNULL(M.PARTNER_ID,  '' ) AS PARTNER_ID,  \r\n");// 매입처명
			sql.append("IFNULL(M.DPARTNER_ID,  '' ) AS DPARTNER_ID,  \r\n");// 물류처명
			sql.append("IFNULL(M.MALL_PRODUCT_ID,  '' ) AS MALL_PRODUCT_ID,  \r\n");// 상품코드(쇼핑몰)
			sql.append("IFNULL(M.PRODUCT_ID,  '' ) AS PRODUCT_ID,  \r\n");// 품번코드(엠링크)
			sql.append("IFNULL(M.SKU_ID,  '' ) AS SKU_ID,  \r\n");// 단품코드(엠링크)
			sql.append("IFNULL(M.P_PRODUCT_NAME,  '' ) AS P_PRODUCT_NAME,  \r\n");// 상품명(확정)
			sql.append("IFNULL(M.P_SKU_VALUE,  '' ) AS P_SKU_VALUE,  \r\n");// 옵션(확정)
			sql.append("IFNULL(M.PRODUCT_NAME,  '' ) AS PRODUCT_NAME,  \r\n");// 상품명(수집)
			sql.append("IFNULL(M.SALE_COST,  '' ) AS SALE_COST,  \r\n");// 판매가(수집)
			sql.append("IFNULL(M.MALL_WON_COST,  '' ) AS MALL_WON_COST,  \r\n");// 공급단가
			sql.append("IFNULL(M.WON_COST,  '' ) AS WON_COST,  \r\n");// 원가
			sql.append("IFNULL(M.SKU_VALUE,  '' ) AS SKU_VALUE,  \r\n");// 옵션(수집)
			sql.append("IFNULL(M.SALE_CNT,  '' ) AS SALE_CNT,  \r\n");// 수량
			sql.append("IFNULL(M.DELIVERY_METHOD_STR,  '' ) AS DELIVERY_METHOD_STR,  \r\n");// 배송구분
			sql.append("IFNULL(M.DELV_COST,  '' ) AS DELV_COST,  \r\n");// 배송비(수집)
			sql.append("IFNULL(M.COMPAYNY_GOODS_CD,  '' ) AS COMPAYNY_GOODS_CD,  \r\n");// 자체상품코드
			sql.append("IFNULL(M.SKU_ALIAS,  '' ) AS SKU_ALIAS,  \r\n");// 옵션별칭
			sql.append("IFNULL(M.BOX_EA,  '' ) AS BOX_EA,  \r\n");// EA(상품)
			sql.append("IFNULL(M.JUNG_CHK_YN,  '' ) AS JUNG_CHK_YN,  \r\n");// 정산대조확인여부
			sql.append("IFNULL(M.MALL_ORDER_SEQ,  '' ) AS MALL_ORDER_SEQ,  \r\n");// 주문순번
			sql.append("IFNULL(M.MALL_ORDER_ID,  '' ) AS MALL_ORDER_ID,  \r\n");// 부주문번호
			sql.append("IFNULL(M.ETC_FIELD3,  '' ) AS ETC_FIELD3,  \r\n");// 수정된 수집옵션명
			sql.append("IFNULL(M.ORDER_GUBUN,  '' ) AS ORDER_GUBUN,  \r\n");// 주문구분
			sql.append("IFNULL(M.P_EA,  '' ) AS P_EA,  \r\n");// EA(확정)
			sql.append("IFNULL(M.ORD_FIELD2,  '' ) AS ORD_FIELD2,  \r\n");// 세트분리주문구분
			sql.append("IFNULL(M.COPY_IDX,  '' ) AS COPY_IDX,  \r\n");// 원주문번호(사방넷)
			sql.append("IFNULL(M.GOODS_NM_PR,  '' ) AS GOODS_NM_PR,  \r\n");// 출력상품명
			sql.append("IFNULL(M.GOODS_KEYWORD,  '' ) AS GOODS_KEYWORD,  \r\n");// 상품약어
			sql.append("IFNULL(M.ORD_CONFIRM_DATE,  '' ) AS ORD_CONFIRM_DATE,  \r\n");// 주문 확인일자
			sql.append("IFNULL(M.RTN_DT,  '' ) AS RTN_DT,  \r\n");// 반품 완료일자
			sql.append("IFNULL(M.CHNG_DT,  '' ) AS CHNG_DT,  \r\n");// 교환 완료일자
			sql.append("IFNULL(M.DELIVERY_CONFIRM_DATE,  '' ) AS DELIVERY_CONFIRM_DATE,  \r\n");// 출고 완료일자
			sql.append("IFNULL(M.CANCEL_DT,  '' ) AS CANCEL_DT,  \r\n");// 취소 완료일자
			sql.append("IFNULL(M.CLASS_CD1,  '' ) AS CLASS_CD1,  \r\n");// 대분류코드
			sql.append("IFNULL(M.CLASS_CD2,  '' ) AS CLASS_CD2,  \r\n");// 중분류코드
			sql.append("IFNULL(M.CLASS_CD3,  '' ) AS CLASS_CD3,  \r\n");// 소분류코드
			sql.append("IFNULL(M.CLASS_CD4,  '' ) AS CLASS_CD4,  \r\n");// 세분류코드
			sql.append("IFNULL(M.BRAND_NM,  '' ) AS BRAND_NM,  \r\n");// 브랜드명
			sql.append("IFNULL(M.DELIVERY_ID,  '' ) AS DELIVERY_ID,  \r\n");// 택배사코드
			sql.append("IFNULL(M.INVOICE_NO,  '' ) AS INVOICE_NO,  \r\n");// 송장번호
			sql.append("IFNULL(M.HOPE_DELV_DATE,  '' ) AS HOPE_DELV_DATE,  \r\n");// 배송희망일자
			sql.append("IFNULL(M.FLD_DSP,  '' ) AS FLD_DSP,  \r\n");// 주문엑셀용
			sql.append("IFNULL(M.INV_SEND_MSG,  '' ) AS INV_SEND_MSG,  \r\n");// 운송장 전송 결과 메시지
			sql.append("IFNULL(M.MODEL_NO,  '' ) AS MODEL_NO,  \r\n");// 모델NO
			sql.append("IFNULL(M.SET_GUBUN,  '' ) AS SET_GUBUN,  \r\n");// 상품구분
			sql.append("IFNULL(M.ETC_MSG,  '' ) AS ETC_MSG,  \r\n");// 기타메세지
			sql.append("IFNULL(M.DELV_MSG1,  '' ) AS DELV_MSG1,  \r\n");// 배송메세지
			sql.append("IFNULL(M.MUL_DELV_MSG,  '' ) AS MUL_DELV_MSG,  \r\n");// 물류메세지
			sql.append("IFNULL(M.BARCODE,  '' ) AS BARCODE,  \r\n");// 바코드
			sql.append("IFNULL(M.INV_SEND_DM,  '' ) AS INV_SEND_DM,  \r\n");// 송장전송일자
			sql.append("IFNULL(M.DELIVERY_METHOD_STR2,  '' ) AS DELIVERY_METHOD_STR2,  \r\n");// 배송구분(배송비반영)
			sql.append("IFNULL(M.ORDER_ETC_1,  '' ) AS ORDER_ETC_1,  \r\n");// 자사몰필드
			sql.append("IFNULL(M.ORDER_ETC_2,  '' ) AS ORDER_ETC_2,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_3,  '' ) AS ORDER_ETC_3,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_4,  '' ) AS ORDER_ETC_4,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_5,  '' ) AS ORDER_ETC_5,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_6,  '' ) AS ORDER_ETC_6,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_7,  '' ) AS ORDER_ETC_7,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_8,  '' ) AS ORDER_ETC_8,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_9,  '' ) AS ORDER_ETC_9,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_10,  '' ) AS ORDER_ETC_10,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_11,  '' ) AS ORDER_ETC_11,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_12,  '' ) AS ORDER_ETC_12,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_13,  '' ) AS ORDER_ETC_13,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_14,  '' ) AS ORDER_ETC_14,  \r\n");// 임시필드
			sql.append("IFNULL(M.LABEL,  '' ) AS LABEL,  \r\n");// 라벨
//			sql.append("IFNULL(D.APIKEY,  '' ) AS APIKEY,  \r\n");// 라벨
//			sql.append("IFNULL(D.PASSWORD,  '' ) AS PASSWORD,  \r\n");// 라벨
//			sql.append("IFNULL(D.AUTHKEY1,  '' ) AS AUTHKEY1,  \r\n");// 라벨
//			sql.append("IFNULL(D.AUTHKEY2,  '' ) AS AUTHKEY2, \r\n");// 라벨
//			sql.append("IFNULL(D.NICKNM2 ,'') AS VENDORID  ,    \r\n");
			sql.append("IFNULL(M.RECEIPTID ,'') AS RECEIPTID,      \r\n");
			sql.append("IFNULL(M.EXCHANGEID  ,'') AS EXCHANGEID      \r\n");
			sql.append(" FROM SHOPORDMST AS M \r\n");
//			sql.append(" INNER JOIN shopdtl AS D \r\n");
//			sql.append(" ON M.COMPNO = D.COMPNO \r\n");
//			sql.append(" AND M.SHOPID = D.SHOPCD \r\n");
//			sql.append(" AND M.SHOP_USERID = D.SHOPPINGID \r\n");
			sql.append(" WHERE M.COMPNO = ?  ");
			sql.append("   and M.SHOPID = ?  ");
			sql.append("   and M.ORDER_ID = ?  ");

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, Shopid);
			pstmt.setString(3, Order_id);

			System.out.println("[ShopOrderID]" + pstmt.toString());

			rs = pstmt.executeQuery();

			Integer rowNum = 0;

			while (rs.next()) {
				ShopOrderMstDto dto = new ShopOrderMstDto();
				++rowNum;
				dto.setNo(rowNum.toString());
				dto.setReg_date(rs.getString("REG_DATE"));// 수집일자
				dto.setOrder_date(rs.getString("ORDER_DATE"));// 주문일자
				dto.setOrder_status(rs.getString("ORDER_STATUS"));// 주문상태
				dto.setShopid(rs.getString("SHOPID"));// 쇼핑몰명
				dto.setShop_userid(rs.getString("SHOP_USERID"));// 쇼핑몰ID
				dto.setReceive_name(rs.getString("RECEIVE_NAME"));// 수령자
				dto.setMall_product_id(rs.getString("MALL_PRODUCT_ID"));// 상품코드(쇼핑몰)
				dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD"));// 자체상품코드
				dto.setP_product_name(rs.getString("P_PRODUCT_NAME"));// 상품명(확정)
				dto.setP_sku_value(rs.getString("P_SKU_VALUE"));// 옵션(확정)
				dto.setSale_cnt(rs.getString("SALE_CNT"));// 수량
				dto.setWon_cost(rs.getString("WON_COST"));// 원가
				dto.setMall_won_cost(rs.getString("MALL_WON_COST"));// 공급단가
				dto.setSale_cost(rs.getString("SALE_COST"));// 판매가(수집)
				dto.setDelv_cost(rs.getString("DELV_COST"));// 배송비(수집)
				dto.setDelivery_method_str2(rs.getString("DELIVERY_METHOD_STR2"));// 배송구분(배송비반영)
				dto.setDelv_msg(rs.getString("DELV_MSG"));// 배송메세지
				dto.setDelivery_id(rs.getString("DELIVERY_ID"));// 택배사코드 (배송사)
				dto.setInvoice_no(rs.getString("INVOICE_NO"));// 송장번호
				dto.setLabel(rs.getString("LABEL"));// 라벨부

				// --------------------- 아직 안쓰는 필드들 ----------------------------------//
				dto.setProduct_id(rs.getString("PRODUCT_ID"));// 품번코드(엠링크)
				dto.setOrdseq(rs.getString("ORDSEQ"));// 일련번호
				dto.setCompno(rs.getString("COMPNO"));// 밴더코드
				dto.setOrder_id(rs.getString("ORDER_ID"));// 주문번호(쇼핑몰)
				dto.setUser_id(rs.getString("USER_ID"));// 주문자ID
				dto.setUser_name(rs.getString("USER_NAME"));// 주문자명
				dto.setUser_tel(rs.getString("USER_TEL"));// 주문자전화번호
				dto.setUser_cel(rs.getString("USER_CEL"));// 주문자핸드폰번호
				dto.setUser_email(rs.getString("USER_EMAIL"));// 주문자이메일주소
				dto.setReceive_tel(rs.getString("RECEIVE_TEL"));// 수취인전화번호
				dto.setReceive_cel(rs.getString("RECEIVE_CEL"));// 수취인핸드폰번호
				dto.setReceive_email(rs.getString("RECEIVE_EMAIL"));// 수취인이메일주소
				dto.setReceive_zipcode(rs.getString("RECEIVE_ZIPCODE"));// 수취인우편번호
				dto.setReceive_addr(rs.getString("RECEIVE_ADDR"));// 수취인주소
				dto.setTotal_cost(rs.getString("TOTAL_COST"));// 주문금액
				dto.setPay_cost(rs.getString("PAY_COST"));// 결제금액
				dto.setPartner_id(rs.getString("PARTNER_ID"));// 매입처명
				dto.setDpartner_id(rs.getString("DPARTNER_ID"));// 물류처명
				dto.setSku_id(rs.getString("SKU_ID"));// 단품코드(엠링크)
				dto.setProduct_name(rs.getString("PRODUCT_NAME"));// 상품명(수집)
				dto.setSku_value(rs.getString("SKU_VALUE"));// 옵션(수집)
				dto.setDelivery_method_str(rs.getString("DELIVERY_METHOD_STR"));// 배송구분

				dto.setSku_alias(rs.getString("SKU_ALIAS"));// 옵션별칭
				dto.setBox_ea(rs.getString("BOX_EA"));// EA(상품)
				dto.setJung_chk_yn(rs.getString("JUNG_CHK_YN"));// 정산대조확인여부
				dto.setMall_order_seq(rs.getString("MALL_ORDER_SEQ"));// 주문순번
				dto.setMall_order_id(rs.getString("MALL_ORDER_ID"));// 부주문번호
				dto.setEtc_field3(rs.getString("ETC_FIELD3"));// 수정된 수집옵션명
				dto.setOrder_gubun(rs.getString("ORDER_GUBUN"));// 주문구분
				dto.setP_ea(rs.getString("P_EA"));// EA(확정)
				dto.setOrd_field2(rs.getString("ORD_FIELD2"));// 세트분리주문구분
				dto.setCopy_idx(rs.getString("COPY_IDX"));// 원주문번호(사방넷)
				dto.setGoods_nm_pr(rs.getString("GOODS_NM_PR"));// 출력상품명
				dto.setGoods_keyword(rs.getString("GOODS_KEYWORD"));// 상품약어
				dto.setOrd_confirm_date(rs.getString("ORD_CONFIRM_DATE"));// 주문 확인일자
				dto.setRtn_dt(rs.getString("RTN_DT"));// 반품 완료일자
				dto.setChng_dt(rs.getString("CHNG_DT"));// 교환 완료일자
				dto.setOrd_confirm_date(rs.getString("DELIVERY_CONFIRM_DATE"));// 출고 완료일자
				dto.setCancel_dt(rs.getString("CANCEL_DT"));// 취소 완료일자
				dto.setClass_cd1(rs.getString("CLASS_CD1"));// 대분류코드
				dto.setClass_cd2(rs.getString("CLASS_CD2"));// 중분류코드
				dto.setClass_cd3(rs.getString("CLASS_CD3"));// 소분류코드
				dto.setClass_cd4(rs.getString("CLASS_CD4"));// 세분류코드
				dto.setBrand_nm(rs.getString("BRAND_NM"));// 브랜드명

				dto.setHope_delv_date(rs.getString("HOPE_DELV_DATE"));// 배송희망일자
				dto.setFld_dsp(rs.getString("FLD_DSP"));// 주문엑셀용
				dto.setInv_send_msg(rs.getString("INV_SEND_MSG"));// 운송장 전송 결과 메시지
				dto.setModel_no(rs.getString("MODEL_NO"));// 모델NO
				dto.setSet_gubun(rs.getString("SET_GUBUN"));// 상품구분
				dto.setEtc_msg(rs.getString("ETC_MSG"));// 기타메세지
				dto.setDelv_msg1(rs.getString("DELV_MSG1"));// 배송메세지
				dto.setMul_delv_msg(rs.getString("MUL_DELV_MSG"));// 물류메세지
				dto.setBarcode(rs.getString("BARCODE"));// 바코드
				dto.setInv_send_dm(rs.getString("INV_SEND_DM"));// 송장전송일자

				dto.setOrder_etc_1(rs.getString("ORDER_ETC_1"));// 자사몰필드
				dto.setOrder_etc_2(rs.getString("ORDER_ETC_2"));// 임시필드
				dto.setOrder_etc_3(rs.getString("ORDER_ETC_3"));// 임시필드
				dto.setOrder_etc_4(rs.getString("ORDER_ETC_4"));// 임시필드
				dto.setOrder_etc_5(rs.getString("ORDER_ETC_5"));// 임시필드
				dto.setOrder_etc_6(rs.getString("ORDER_ETC_6"));// 임시필드
				dto.setOrder_etc_7(rs.getString("ORDER_ETC_7"));// 임시필드
				dto.setOrder_etc_8(rs.getString("ORDER_ETC_8"));// 임시필드
				dto.setOrder_etc_9(rs.getString("ORDER_ETC_9"));// 임시필드
				dto.setOrder_etc_10(rs.getString("ORDER_ETC_10"));// 임시필드
				dto.setOrder_etc_11(rs.getString("ORDER_ETC_11"));// 임시필드
				dto.setOrder_etc_12(rs.getString("ORDER_ETC_12"));// 임시필드
				dto.setOrder_etc_13(rs.getString("ORDER_ETC_13"));// 임시필드
				dto.setOrder_etc_14(rs.getString("ORDER_ETC_14"));// 임시필드
//				dto.setShopPw(rs.getString("PASSWORD"));
//				dto.setApikey(rs.getString("APIKEY"));
//
//				dto.setAuthkey1(rs.getString("AUTHKEY1"));
//				dto.setAuthKey2(rs.getString("AUTHKEY2"));
//				dto.setVendorId(rs.getString("VENDORID"));

				dto.setReceiptid(rs.getString("RECEIPTID")); // 반품 접수 아이디..
				dto.setExchangeid(rs.getString("EXCHANGEID")); // 교환 접수 아이디..
//				dto.setCsClose(rs.getString("CSCLOSE"));
				list.add(dto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return list;
	}

	/// <summary>
	/// 신규 주문건만 가져온다.
	/// getShoppingid(),dto.getShopid()
	/// </summary>
	/// <returns></returns>
	public List<ShopOrderMstDto> getShopOrderNew(String strdt, String endDt) throws Exception {
		List<ShopOrderMstDto> list = new ArrayList<ShopOrderMstDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT \r\n");
			sql.append("IFNULL(M.ORDSEQ,  '' ) AS ORDSEQ,  \r\n");// 일련번호
			sql.append("IFNULL(M.COMPNO,  '' ) AS COMPNO,  \r\n");// 밴더코드
			sql.append("IFNULL(M.REG_DATE,  '' ) AS REG_DATE,  \r\n");// 수집일자
			sql.append("IFNULL(M.ORDER_ID,  '' ) AS ORDER_ID,  \r\n");// 주문번호(쇼핑몰)
			sql.append("IFNULL(M.SHOPID,  '' ) AS SHOPID,  \r\n");// 쇼핑몰명
			sql.append("IFNULL(M.SHOP_USERID,  '' ) AS SHOP_USERID,  \r\n");// 쇼핑몰ID
			sql.append("IFNULL(M.ORDER_STATUS,  '' ) AS ORDER_STATUS,  \r\n");// 주문상태
			sql.append("IFNULL(M.USER_ID,  '' ) AS USER_ID,  \r\n");// 주문자ID
			sql.append("IFNULL(M.USER_NAME,  '' ) AS USER_NAME,  \r\n");// 주문자명
			sql.append("IFNULL(M.USER_TEL,  '' ) AS USER_TEL,  \r\n");// 주문자전화번호
			sql.append("IFNULL(M.USER_CEL,  '' ) AS USER_CEL,  \r\n");// 주문자핸드폰번호
			sql.append("IFNULL(M.USER_EMAIL,  '' ) AS USER_EMAIL,  \r\n");// 주문자이메일주소
			sql.append("IFNULL(M.RECEIVE_TEL,  '' ) AS RECEIVE_TEL,  \r\n");// 수취인전화번호
			sql.append("IFNULL(M.RECEIVE_CEL,  '' ) AS RECEIVE_CEL,  \r\n");// 수취인핸드폰번호
			sql.append("IFNULL(M.RECEIVE_EMAIL,  '' ) AS RECEIVE_EMAIL,  \r\n");// 수취인이메일주소
			sql.append("IFNULL(M.DELV_MSG,  '' ) AS DELV_MSG,  \r\n");// 배송메세지
			sql.append("IFNULL(M.RECEIVE_NAME,  '' ) AS RECEIVE_NAME,  \r\n");// 수취인명
			sql.append("IFNULL(M.RECEIVE_ZIPCODE,  '' ) AS RECEIVE_ZIPCODE,  \r\n");// 수취인우편번호
			sql.append("IFNULL(M.RECEIVE_ADDR,  '' ) AS RECEIVE_ADDR,  \r\n");// 수취인주소
			sql.append("IFNULL(M.TOTAL_COST,  '' ) AS TOTAL_COST,  \r\n");// 주문금액
			sql.append("IFNULL(M.PAY_COST,  '' ) AS PAY_COST,  \r\n");// 결제금액
			sql.append("IFNULL(M.ORDER_DATE,  '' ) AS ORDER_DATE,  \r\n");// 주문일자
			sql.append("IFNULL(M.PARTNER_ID,  '' ) AS PARTNER_ID,  \r\n");// 매입처명
			sql.append("IFNULL(M.DPARTNER_ID,  '' ) AS DPARTNER_ID,  \r\n");// 물류처명
			sql.append("IFNULL(M.MALL_PRODUCT_ID,  '' ) AS MALL_PRODUCT_ID,  \r\n");// 상품코드(쇼핑몰)
			sql.append("IFNULL(M.PRODUCT_ID,  '' ) AS PRODUCT_ID,  \r\n");// 품번코드(엠링크)
			sql.append("IFNULL(M.SKU_ID,  '' ) AS SKU_ID,  \r\n");// 단품코드(엠링크)
			sql.append("IFNULL(M.P_PRODUCT_NAME,  '' ) AS P_PRODUCT_NAME,  \r\n");// 상품명(확정)
			sql.append("IFNULL(M.P_SKU_VALUE,  '' ) AS P_SKU_VALUE,  \r\n");// 옵션(확정)
			sql.append("IFNULL(M.PRODUCT_NAME,  '' ) AS PRODUCT_NAME,  \r\n");// 상품명(수집)
			sql.append("IFNULL(M.SALE_COST,  '' ) AS SALE_COST,  \r\n");// 판매가(수집)
			sql.append("IFNULL(M.MALL_WON_COST,  '' ) AS MALL_WON_COST,  \r\n");// 공급단가
			sql.append("IFNULL(M.WON_COST,  '' ) AS WON_COST,  \r\n");// 원가
			sql.append("IFNULL(M.SKU_VALUE,  '' ) AS SKU_VALUE,  \r\n");// 옵션(수집)
			sql.append("IFNULL(M.SALE_CNT,  '' ) AS SALE_CNT,  \r\n");// 수량
			sql.append("IFNULL(M.DELIVERY_METHOD_STR,  '' ) AS DELIVERY_METHOD_STR,  \r\n");// 배송구분
			sql.append("IFNULL(M.DELV_COST,  '' ) AS DELV_COST,  \r\n");// 배송비(수집)
			sql.append("IFNULL(M.COMPAYNY_GOODS_CD,  '' ) AS COMPAYNY_GOODS_CD,  \r\n");// 자체상품코드
			sql.append("IFNULL(M.SKU_ALIAS,  '' ) AS SKU_ALIAS,  \r\n");// 옵션별칭
			sql.append("IFNULL(M.BOX_EA,  '' ) AS BOX_EA,  \r\n");// EA(상품)
			sql.append("IFNULL(M.JUNG_CHK_YN,  '' ) AS JUNG_CHK_YN,  \r\n");// 정산대조확인여부
			sql.append("IFNULL(M.MALL_ORDER_SEQ,  '' ) AS MALL_ORDER_SEQ,  \r\n");// 주문순번
			sql.append("IFNULL(M.MALL_ORDER_ID,  '' ) AS MALL_ORDER_ID,  \r\n");// 부주문번호
			sql.append("IFNULL(M.ETC_FIELD3,  '' ) AS ETC_FIELD3,  \r\n");// 수정된 수집옵션명
			sql.append("IFNULL(M.ORDER_GUBUN,  '' ) AS ORDER_GUBUN,  \r\n");// 주문구분
			sql.append("IFNULL(M.P_EA,  '' ) AS P_EA,  \r\n");// EA(확정)
			sql.append("IFNULL(M.ORD_FIELD2,  '' ) AS ORD_FIELD2,  \r\n");// 세트분리주문구분
			sql.append("IFNULL(M.COPY_IDX,  '' ) AS COPY_IDX,  \r\n");// 원주문번호(사방넷)
			sql.append("IFNULL(M.GOODS_NM_PR,  '' ) AS GOODS_NM_PR,  \r\n");// 출력상품명
			sql.append("IFNULL(M.GOODS_KEYWORD,  '' ) AS GOODS_KEYWORD,  \r\n");// 상품약어
			sql.append("IFNULL(M.ORD_CONFIRM_DATE,  '' ) AS ORD_CONFIRM_DATE,  \r\n");// 주문 확인일자
			sql.append("IFNULL(M.RTN_DT,  '' ) AS RTN_DT,  \r\n");// 반품 완료일자
			sql.append("IFNULL(M.CHNG_DT,  '' ) AS CHNG_DT,  \r\n");// 교환 완료일자
			sql.append("IFNULL(M.DELIVERY_CONFIRM_DATE,  '' ) AS DELIVERY_CONFIRM_DATE,  \r\n");// 출고 완료일자
			sql.append("IFNULL(M.CANCEL_DT,  '' ) AS CANCEL_DT,  \r\n");// 취소 완료일자
			sql.append("IFNULL(M.CLASS_CD1,  '' ) AS CLASS_CD1,  \r\n");// 대분류코드
			sql.append("IFNULL(M.CLASS_CD2,  '' ) AS CLASS_CD2,  \r\n");// 중분류코드
			sql.append("IFNULL(M.CLASS_CD3,  '' ) AS CLASS_CD3,  \r\n");// 소분류코드
			sql.append("IFNULL(M.CLASS_CD4,  '' ) AS CLASS_CD4,  \r\n");// 세분류코드
			sql.append("IFNULL(M.BRAND_NM,  '' ) AS BRAND_NM,  \r\n");// 브랜드명
			sql.append("IFNULL(M.DELIVERY_ID,  '' ) AS DELIVERY_ID,  \r\n");// 택배사코드
			sql.append("IFNULL(M.INVOICE_NO,  '' ) AS INVOICE_NO,  \r\n");// 송장번호
			sql.append("IFNULL(M.HOPE_DELV_DATE,  '' ) AS HOPE_DELV_DATE,  \r\n");// 배송희망일자
			sql.append("IFNULL(M.FLD_DSP,  '' ) AS FLD_DSP,  \r\n");// 주문엑셀용
			sql.append("IFNULL(M.INV_SEND_MSG,  '' ) AS INV_SEND_MSG,  \r\n");// 운송장 전송 결과 메시지
			sql.append("IFNULL(M.MODEL_NO,  '' ) AS MODEL_NO,  \r\n");// 모델NO
			sql.append("IFNULL(M.SET_GUBUN,  '' ) AS SET_GUBUN,  \r\n");// 상품구분
			sql.append("IFNULL(M.ETC_MSG,  '' ) AS ETC_MSG,  \r\n");// 기타메세지
			sql.append("IFNULL(M.DELV_MSG1,  '' ) AS DELV_MSG1,  \r\n");// 배송메세지
			sql.append("IFNULL(M.MUL_DELV_MSG,  '' ) AS MUL_DELV_MSG,  \r\n");// 물류메세지
			sql.append("IFNULL(M.BARCODE,  '' ) AS BARCODE,  \r\n");// 바코드
			sql.append("IFNULL(M.INV_SEND_DM,  '' ) AS INV_SEND_DM,  \r\n");// 송장전송일자
			sql.append("IFNULL(M.DELIVERY_METHOD_STR2,  '' ) AS DELIVERY_METHOD_STR2,  \r\n");// 배송구분(배송비반영)
			sql.append("IFNULL(M.ORDER_ETC_1,  '' ) AS ORDER_ETC_1,  \r\n");// 자사몰필드
			sql.append("IFNULL(M.ORDER_ETC_2,  '' ) AS ORDER_ETC_2,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_3,  '' ) AS ORDER_ETC_3,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_4,  '' ) AS ORDER_ETC_4,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_5,  '' ) AS ORDER_ETC_5,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_6,  '' ) AS ORDER_ETC_6,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_7,  '' ) AS ORDER_ETC_7,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_8,  '' ) AS ORDER_ETC_8,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_9,  '' ) AS ORDER_ETC_9,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_10,  '' ) AS ORDER_ETC_10,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_11,  '' ) AS ORDER_ETC_11,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_12,  '' ) AS ORDER_ETC_12,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_13,  '' ) AS ORDER_ETC_13,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_14,  '' ) AS ORDER_ETC_14,  \r\n");// 임시필드
			sql.append("IFNULL(M.LABEL,  '' ) AS LABEL,  \r\n");// 라벨
			sql.append("IFNULL(D.APIKEY,  '' ) AS APIKEY,  \r\n");// 라벨
			sql.append("IFNULL(D.PASSWORD,  '' ) AS PASSWORD,  \r\n");// 라벨
			sql.append("IFNULL(D.AUTHKEY1,  '' ) AS AUTHKEY1,  \r\n");// 라벨
			sql.append("IFNULL(D.AUTHKEY2,  '' ) AS AUTHKEY2, \r\n");// 라벨
			sql.append("IFNULL(D.NICKNM2 ,'') AS VENDORID,      \r\n");

			sql.append("IFNULL(M.RECEIPTID ,'') AS RECEIPTID,      \r\n");
			sql.append("IFNULL(M.EXCHANGEID  ,'') AS EXCHANGEID       \r\n");

			sql.append(" FROM shopordmst AS M \r\n");
			sql.append(" INNER JOIN shopdtl AS D \r\n");
			sql.append(" ON M.COMPNO = D.COMPNO \r\n");
			sql.append(" AND M.SHOPID = D.SHOPCD \r\n");
			sql.append(" AND M.SHOP_USERID = D.SHOPPINGID \r\n");
			sql.append(
					" WHERE M.COMPNO = ?  AND (ORDER_DATE >= ? AND  ORDER_DATE <= ?) AND ORDER_STATUS='100'   ORDER BY REG_DATE DESC");
			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, YDMATimeUtil.convertSabangNetOrderDateToOrddt(strdt, false));
			pstmt.setString(3, YDMATimeUtil.convertSabangNetOrderDateToOrddt(endDt, false));

			System.out.println("[ShopOrderList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 0;

			while (rs.next()) {
				ShopOrderMstDto dto = new ShopOrderMstDto();
				dto.setReg_date(rs.getString("REG_DATE"));// 수집일자
				dto.setOrder_date(rs.getString("ORDER_DATE"));// 주문일자
				dto.setOrder_status(rs.getString("ORDER_STATUS"));// 주문상태
				dto.setShopid(rs.getString("SHOPID"));// 쇼핑몰명
				dto.setShop_userid(rs.getString("SHOP_USERID"));// 쇼핑몰ID
				dto.setReceive_name(rs.getString("RECEIVE_NAME"));// 수령자
				dto.setMall_product_id(rs.getString("MALL_PRODUCT_ID"));// 상품코드(쇼핑몰)
				dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD"));// 자체상품코드
				dto.setP_product_name(rs.getString("P_PRODUCT_NAME"));// 상품명(확정)
				dto.setP_sku_value(rs.getString("P_SKU_VALUE"));// 옵션(확정)
				dto.setSale_cnt(rs.getString("SALE_CNT"));// 수량
				dto.setWon_cost(rs.getString("WON_COST"));// 원가
				dto.setMall_won_cost(rs.getString("MALL_WON_COST"));// 공급단가
				dto.setSale_cost(rs.getString("SALE_COST"));// 판매가(수집)
				dto.setDelv_cost(rs.getString("DELV_COST"));// 배송비(수집)
				dto.setDelivery_method_str2(rs.getString("DELIVERY_METHOD_STR2"));// 배송구분(배송비반영)
				dto.setDelv_msg(rs.getString("DELV_MSG"));// 배송메세지
				dto.setDelivery_id(rs.getString("DELIVERY_ID"));// 택배사코드 (배송사)
				dto.setInvoice_no(rs.getString("INVOICE_NO"));// 송장번호
				dto.setLabel(rs.getString("LABEL"));// 라벨부

				// --------------------- 아직 안쓰는 필드들 ----------------------------------//
				dto.setProduct_id(rs.getString("PRODUCT_ID"));// 품번코드(엠링크)
				dto.setOrdseq(rs.getString("ORDSEQ"));// 일련번호
				dto.setCompno(rs.getString("COMPNO"));// 밴더코드
				dto.setOrder_id(rs.getString("ORDER_ID"));// 주문번호(쇼핑몰)
				dto.setUser_id(rs.getString("USER_ID"));// 주문자ID
				dto.setUser_name(rs.getString("USER_NAME"));// 주문자명
				dto.setUser_tel(rs.getString("USER_TEL"));// 주문자전화번호
				dto.setUser_cel(rs.getString("USER_CEL"));// 주문자핸드폰번호
				dto.setUser_email(rs.getString("USER_EMAIL"));// 주문자이메일주소
				dto.setReceive_tel(rs.getString("RECEIVE_TEL"));// 수취인전화번호
				dto.setReceive_cel(rs.getString("RECEIVE_CEL"));// 수취인핸드폰번호
				dto.setReceive_email(rs.getString("RECEIVE_EMAIL"));// 수취인이메일주소
				dto.setReceive_zipcode(rs.getString("RECEIVE_ZIPCODE"));// 수취인우편번호
				dto.setReceive_addr(rs.getString("RECEIVE_ADDR"));// 수취인주소
				dto.setTotal_cost(rs.getString("TOTAL_COST"));// 주문금액
				dto.setPay_cost(rs.getString("PAY_COST"));// 결제금액
				dto.setPartner_id(rs.getString("PARTNER_ID"));// 매입처명
				dto.setDpartner_id(rs.getString("DPARTNER_ID"));// 물류처명
				dto.setSku_id(rs.getString("SKU_ID"));// 단품코드(엠링크)
				dto.setProduct_name(rs.getString("PRODUCT_NAME"));// 상품명(수집)
				dto.setSku_value(rs.getString("SKU_VALUE"));// 옵션(수집)
				dto.setDelivery_method_str(rs.getString("DELIVERY_METHOD_STR"));// 배송구분

				dto.setSku_alias(rs.getString("SKU_ALIAS"));// 옵션별칭
				dto.setBox_ea(rs.getString("BOX_EA"));// EA(상품)
				dto.setJung_chk_yn(rs.getString("JUNG_CHK_YN"));// 정산대조확인여부
				dto.setMall_order_seq(rs.getString("MALL_ORDER_SEQ"));// 주문순번
				dto.setMall_order_id(rs.getString("MALL_ORDER_ID"));// 부주문번호
				dto.setEtc_field3(rs.getString("ETC_FIELD3"));// 수정된 수집옵션명
				dto.setOrder_gubun(rs.getString("ORDER_GUBUN"));// 주문구분
				dto.setP_ea(rs.getString("P_EA"));// EA(확정)
				dto.setOrd_field2(rs.getString("ORD_FIELD2"));// 세트분리주문구분
				dto.setCopy_idx(rs.getString("COPY_IDX"));// 원주문번호(사방넷)
				dto.setGoods_nm_pr(rs.getString("GOODS_NM_PR"));// 출력상품명
				dto.setGoods_keyword(rs.getString("GOODS_KEYWORD"));// 상품약어
				dto.setOrd_confirm_date(rs.getString("ORD_CONFIRM_DATE"));// 주문 확인일자
				dto.setRtn_dt(rs.getString("RTN_DT"));// 반품 완료일자
				dto.setChng_dt(rs.getString("CHNG_DT"));// 교환 완료일자
				dto.setOrd_confirm_date(rs.getString("DELIVERY_CONFIRM_DATE"));// 출고 완료일자
				dto.setCancel_dt(rs.getString("CANCEL_DT"));// 취소 완료일자
				dto.setClass_cd1(rs.getString("CLASS_CD1"));// 대분류코드
				dto.setClass_cd2(rs.getString("CLASS_CD2"));// 중분류코드
				dto.setClass_cd3(rs.getString("CLASS_CD3"));// 소분류코드
				dto.setClass_cd4(rs.getString("CLASS_CD4"));// 세분류코드
				dto.setBrand_nm(rs.getString("BRAND_NM"));// 브랜드명

				dto.setHope_delv_date(rs.getString("HOPE_DELV_DATE"));// 배송희망일자
				dto.setFld_dsp(rs.getString("FLD_DSP"));// 주문엑셀용
				dto.setInv_send_msg(rs.getString("INV_SEND_MSG"));// 운송장 전송 결과 메시지
				dto.setModel_no(rs.getString("MODEL_NO"));// 모델NO
				dto.setSet_gubun(rs.getString("SET_GUBUN"));// 상품구분
				dto.setEtc_msg(rs.getString("ETC_MSG"));// 기타메세지
				dto.setDelv_msg1(rs.getString("DELV_MSG1"));// 배송메세지
				dto.setMul_delv_msg(rs.getString("MUL_DELV_MSG"));// 물류메세지
				dto.setBarcode(rs.getString("BARCODE"));// 바코드
				dto.setInv_send_dm(rs.getString("INV_SEND_DM"));// 송장전송일자

				dto.setOrder_etc_1(rs.getString("ORDER_ETC_1"));// 자사몰필드
				dto.setOrder_etc_2(rs.getString("ORDER_ETC_2"));// 임시필드
				dto.setOrder_etc_3(rs.getString("ORDER_ETC_3"));// 임시필드
				dto.setOrder_etc_4(rs.getString("ORDER_ETC_4"));// 임시필드
				dto.setOrder_etc_5(rs.getString("ORDER_ETC_5"));// 임시필드
				dto.setOrder_etc_6(rs.getString("ORDER_ETC_6"));// 임시필드
				dto.setOrder_etc_7(rs.getString("ORDER_ETC_7"));// 임시필드
				dto.setOrder_etc_8(rs.getString("ORDER_ETC_8"));// 임시필드
				dto.setOrder_etc_9(rs.getString("ORDER_ETC_9"));// 임시필드
				dto.setOrder_etc_10(rs.getString("ORDER_ETC_10"));// 임시필드
				dto.setOrder_etc_11(rs.getString("ORDER_ETC_11"));// 임시필드
				dto.setOrder_etc_12(rs.getString("ORDER_ETC_12"));// 임시필드
				dto.setOrder_etc_13(rs.getString("ORDER_ETC_13"));// 임시필드
				dto.setOrder_etc_14(rs.getString("ORDER_ETC_14"));// 임시필드
				dto.setShopPw(rs.getString("PASSWORD"));
				dto.setApikey(rs.getString("APIKEY"));
				dto.setAuthkey1(rs.getString("AUTHKEY1"));
				dto.setAuthKey2(rs.getString("AUTHKEY2"));
				dto.setVendorId(rs.getString("VENDORID"));

				dto.setReceiptid(rs.getString("RECEIPTID")); // 반품 접수 아이디..
				dto.setExchangeid(rs.getString("EXCHANGEID")); // 교환 접수 아이디..

				list.add(dto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return list;
	}

	/// <summary>
	/// 주문 오더 가져오기.
	/// getShoppingid(),dto.getShopid()
	/// </summary>
	/// <returns></returns>
	public List<ShopOrderMstDto> getShopOrder(String shopCd, String shoppingID, String strdt, String endDt)
			throws Exception {
		List<ShopOrderMstDto> list = new ArrayList<ShopOrderMstDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT \r\n");
			sql.append("IFNULL(M.ORDSEQ,  '' ) AS ORDSEQ,  \r\n");// 일련번호
			sql.append("IFNULL(M.COMPNO,  '' ) AS COMPNO,  \r\n");// 밴더코드
			sql.append("IFNULL(M.REG_DATE,  '' ) AS REG_DATE,  \r\n");// 수집일자
			sql.append("IFNULL(M.ORDER_ID,  '' ) AS ORDER_ID,  \r\n");// 주문번호(쇼핑몰)
			sql.append("IFNULL(M.SHOPID,  '' ) AS SHOPID,  \r\n");// 쇼핑몰명
			sql.append("IFNULL(M.SHOP_USERID,  '' ) AS SHOP_USERID,  \r\n");// 쇼핑몰ID
			sql.append("IFNULL(M.ORDER_STATUS,  '' ) AS ORDER_STATUS,  \r\n");// 주문상태
			sql.append("IFNULL(M.USER_ID,  '' ) AS USER_ID,  \r\n");// 주문자ID
			sql.append("IFNULL(M.USER_NAME,  '' ) AS USER_NAME,  \r\n");// 주문자명
			sql.append("IFNULL(M.USER_TEL,  '' ) AS USER_TEL,  \r\n");// 주문자전화번호
			sql.append("IFNULL(M.USER_CEL,  '' ) AS USER_CEL,  \r\n");// 주문자핸드폰번호
			sql.append("IFNULL(M.USER_EMAIL,  '' ) AS USER_EMAIL,  \r\n");// 주문자이메일주소
			sql.append("IFNULL(M.RECEIVE_TEL,  '' ) AS RECEIVE_TEL,  \r\n");// 수취인전화번호
			sql.append("IFNULL(M.RECEIVE_CEL,  '' ) AS RECEIVE_CEL,  \r\n");// 수취인핸드폰번호
			sql.append("IFNULL(M.RECEIVE_EMAIL,  '' ) AS RECEIVE_EMAIL,  \r\n");// 수취인이메일주소
			sql.append("IFNULL(M.DELV_MSG,  '' ) AS DELV_MSG,  \r\n");// 배송메세지
			sql.append("IFNULL(M.RECEIVE_NAME,  '' ) AS RECEIVE_NAME,  \r\n");// 수취인명
			sql.append("IFNULL(M.RECEIVE_ZIPCODE,  '' ) AS RECEIVE_ZIPCODE,  \r\n");// 수취인우편번호
			sql.append("IFNULL(M.RECEIVE_ADDR,  '' ) AS RECEIVE_ADDR,  \r\n");// 수취인주소
			sql.append("IFNULL(M.TOTAL_COST,  '' ) AS TOTAL_COST,  \r\n");// 주문금액
			sql.append("IFNULL(M.PAY_COST,  '' ) AS PAY_COST,  \r\n");// 결제금액
			sql.append("IFNULL(M.ORDER_DATE,  '' ) AS ORDER_DATE,  \r\n");// 주문일자
			sql.append("IFNULL(M.PARTNER_ID,  '' ) AS PARTNER_ID,  \r\n");// 매입처명
			sql.append("IFNULL(M.DPARTNER_ID,  '' ) AS DPARTNER_ID,  \r\n");// 물류처명
			sql.append("IFNULL(M.MALL_PRODUCT_ID,  '' ) AS MALL_PRODUCT_ID,  \r\n");// 상품코드(쇼핑몰)
			sql.append("IFNULL(M.PRODUCT_ID,  '' ) AS PRODUCT_ID,  \r\n");// 품번코드(엠링크)
			sql.append("IFNULL(M.SKU_ID,  '' ) AS SKU_ID,  \r\n");// 단품코드(엠링크)
			sql.append("IFNULL(M.P_PRODUCT_NAME,  '' ) AS P_PRODUCT_NAME,  \r\n");// 상품명(확정)
			sql.append("IFNULL(M.P_SKU_VALUE,  '' ) AS P_SKU_VALUE,  \r\n");// 옵션(확정)
			sql.append("IFNULL(M.PRODUCT_NAME,  '' ) AS PRODUCT_NAME,  \r\n");// 상품명(수집)
			sql.append("IFNULL(M.SALE_COST,  '' ) AS SALE_COST,  \r\n");// 판매가(수집)
			sql.append("IFNULL(M.MALL_WON_COST,  '' ) AS MALL_WON_COST,  \r\n");// 공급단가
			sql.append("IFNULL(M.WON_COST,  '' ) AS WON_COST,  \r\n");// 원가
			sql.append("IFNULL(M.SKU_VALUE,  '' ) AS SKU_VALUE,  \r\n");// 옵션(수집)
			sql.append("IFNULL(M.SALE_CNT,  '' ) AS SALE_CNT,  \r\n");// 수량
			sql.append("IFNULL(M.DELIVERY_METHOD_STR,  '' ) AS DELIVERY_METHOD_STR,  \r\n");// 배송구분
			sql.append("IFNULL(M.DELV_COST,  '' ) AS DELV_COST,  \r\n");// 배송비(수집)
			sql.append("IFNULL(M.COMPAYNY_GOODS_CD,  '' ) AS COMPAYNY_GOODS_CD,  \r\n");// 자체상품코드
			sql.append("IFNULL(M.SKU_ALIAS,  '' ) AS SKU_ALIAS,  \r\n");// 옵션별칭
			sql.append("IFNULL(M.BOX_EA,  '' ) AS BOX_EA,  \r\n");// EA(상품)
			sql.append("IFNULL(M.JUNG_CHK_YN,  '' ) AS JUNG_CHK_YN,  \r\n");// 정산대조확인여부
			sql.append("IFNULL(M.MALL_ORDER_SEQ,  '' ) AS MALL_ORDER_SEQ,  \r\n");// 주문순번
			sql.append("IFNULL(M.MALL_ORDER_ID,  '' ) AS MALL_ORDER_ID,  \r\n");// 부주문번호
			sql.append("IFNULL(M.ETC_FIELD3,  '' ) AS ETC_FIELD3,  \r\n");// 수정된 수집옵션명
			sql.append("IFNULL(M.ORDER_GUBUN,  '' ) AS ORDER_GUBUN,  \r\n");// 주문구분
			sql.append("IFNULL(M.P_EA,  '' ) AS P_EA,  \r\n");// EA(확정)
			sql.append("IFNULL(M.ORD_FIELD2,  '' ) AS ORD_FIELD2,  \r\n");// 세트분리주문구분
			sql.append("IFNULL(M.COPY_IDX,  '' ) AS COPY_IDX,  \r\n");// 원주문번호(사방넷)
			sql.append("IFNULL(M.GOODS_NM_PR,  '' ) AS GOODS_NM_PR,  \r\n");// 출력상품명
			sql.append("IFNULL(M.GOODS_KEYWORD,  '' ) AS GOODS_KEYWORD,  \r\n");// 상품약어
			sql.append("IFNULL(M.ORD_CONFIRM_DATE,  '' ) AS ORD_CONFIRM_DATE,  \r\n");// 주문 확인일자
			sql.append("IFNULL(M.RTN_DT,  '' ) AS RTN_DT,  \r\n");// 반품 완료일자
			sql.append("IFNULL(M.CHNG_DT,  '' ) AS CHNG_DT,  \r\n");// 교환 완료일자
			sql.append("IFNULL(M.DELIVERY_CONFIRM_DATE,  '' ) AS DELIVERY_CONFIRM_DATE,  \r\n");// 출고 완료일자
			sql.append("IFNULL(M.CANCEL_DT,  '' ) AS CANCEL_DT,  \r\n");// 취소 완료일자
			sql.append("IFNULL(M.CLASS_CD1,  '' ) AS CLASS_CD1,  \r\n");// 대분류코드
			sql.append("IFNULL(M.CLASS_CD2,  '' ) AS CLASS_CD2,  \r\n");// 중분류코드
			sql.append("IFNULL(M.CLASS_CD3,  '' ) AS CLASS_CD3,  \r\n");// 소분류코드
			sql.append("IFNULL(M.CLASS_CD4,  '' ) AS CLASS_CD4,  \r\n");// 세분류코드
			sql.append("IFNULL(M.BRAND_NM,  '' ) AS BRAND_NM,  \r\n");// 브랜드명
			sql.append("IFNULL(M.DELIVERY_ID,  '' ) AS DELIVERY_ID,  \r\n");// 택배사코드
			sql.append("IFNULL(M.INVOICE_NO,  '' ) AS INVOICE_NO,  \r\n");// 송장번호
			sql.append("IFNULL(M.HOPE_DELV_DATE,  '' ) AS HOPE_DELV_DATE,  \r\n");// 배송희망일자
			sql.append("IFNULL(M.FLD_DSP,  '' ) AS FLD_DSP,  \r\n");// 주문엑셀용
			sql.append("IFNULL(M.INV_SEND_MSG,  '' ) AS INV_SEND_MSG,  \r\n");// 운송장 전송 결과 메시지
			sql.append("IFNULL(M.MODEL_NO,  '' ) AS MODEL_NO,  \r\n");// 모델NO
			sql.append("IFNULL(M.SET_GUBUN,  '' ) AS SET_GUBUN,  \r\n");// 상품구분
			sql.append("IFNULL(M.ETC_MSG,  '' ) AS ETC_MSG,  \r\n");// 기타메세지
			sql.append("IFNULL(M.DELV_MSG1,  '' ) AS DELV_MSG1,  \r\n");// 배송메세지
			sql.append("IFNULL(M.MUL_DELV_MSG,  '' ) AS MUL_DELV_MSG,  \r\n");// 물류메세지
			sql.append("IFNULL(M.BARCODE,  '' ) AS BARCODE,  \r\n");// 바코드
			sql.append("IFNULL(M.INV_SEND_DM,  '' ) AS INV_SEND_DM,  \r\n");// 송장전송일자
			sql.append("IFNULL(M.DELIVERY_METHOD_STR2,  '' ) AS DELIVERY_METHOD_STR2,  \r\n");// 배송구분(배송비반영)
			sql.append("IFNULL(M.ORDER_ETC_1,  '' ) AS ORDER_ETC_1,  \r\n");// 자사몰필드
			sql.append("IFNULL(M.ORDER_ETC_2,  '' ) AS ORDER_ETC_2,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_3,  '' ) AS ORDER_ETC_3,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_4,  '' ) AS ORDER_ETC_4,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_5,  '' ) AS ORDER_ETC_5,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_6,  '' ) AS ORDER_ETC_6,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_7,  '' ) AS ORDER_ETC_7,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_8,  '' ) AS ORDER_ETC_8,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_9,  '' ) AS ORDER_ETC_9,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_10,  '' ) AS ORDER_ETC_10,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_11,  '' ) AS ORDER_ETC_11,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_12,  '' ) AS ORDER_ETC_12,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_13,  '' ) AS ORDER_ETC_13,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_14,  '' ) AS ORDER_ETC_14,  \r\n");// 임시필드
			sql.append("IFNULL(M.LABEL,  '' ) AS LABEL,  \r\n");// 라벨
			sql.append("IFNULL(D.APIKEY,  '' ) AS APIKEY,  \r\n");// 라벨
			sql.append("IFNULL(D.PASSWORD,  '' ) AS PASSWORD,  \r\n");// 라벨
			sql.append("IFNULL(D.AUTHKEY1,  '' ) AS AUTHKEY1,  \r\n");// 라벨
			sql.append("IFNULL(D.AUTHKEY2,  '' ) AS AUTHKEY2, \r\n");// 라벨
			sql.append("IFNULL(D.NICKNM2 ,'') AS VENDORID,      \r\n");

			sql.append("IFNULL(M.RECEIPTID ,'') AS RECEIPTID,      \r\n");
			sql.append("IFNULL(M.EXCHANGEID  ,'') AS EXCHANGEID       \r\n");

			sql.append(" FROM shopordmst AS M \r\n");
			sql.append(" INNER JOIN shopdtl AS D \r\n");
			sql.append(" ON M.COMPNO = D.COMPNO \r\n");
			sql.append(" AND M.SHOPID = D.SHOPCD \r\n");
			sql.append(" AND M.SHOP_USERID = D.SHOPPINGID \r\n");
			sql.append(
					" WHERE M.COMPNO = ?  AND M.SHOPID= ? AND  M.SHOP_USERID = ? AND (REG_DATE >= ? AND  REG_DATE <= ?)   ORDER BY REG_DATE DESC");
			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopCd);
			pstmt.setString(3, shoppingID);
			pstmt.setString(4, YDMATimeUtil.convertSabangNetOrderDateToOrddt(strdt, false));
			pstmt.setString(5, YDMATimeUtil.convertSabangNetOrderDateToOrddt(endDt, false));

			System.out.println("[ShopOrderList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 0;

			while (rs.next()) {
				ShopOrderMstDto dto = new ShopOrderMstDto();
				dto.setReg_date(rs.getString("REG_DATE"));// 수집일자
				dto.setOrder_date(rs.getString("ORDER_DATE"));// 주문일자
				dto.setOrder_status(rs.getString("ORDER_STATUS"));// 주문상태
				dto.setShopid(rs.getString("SHOPID"));// 쇼핑몰명
				dto.setShop_userid(rs.getString("SHOP_USERID"));// 쇼핑몰ID
				dto.setReceive_name(rs.getString("RECEIVE_NAME"));// 수령자
				dto.setMall_product_id(rs.getString("MALL_PRODUCT_ID"));// 상품코드(쇼핑몰)
				dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD"));// 자체상품코드
				dto.setP_product_name(rs.getString("P_PRODUCT_NAME"));// 상품명(확정)
				dto.setP_sku_value(rs.getString("P_SKU_VALUE"));// 옵션(확정)
				dto.setSale_cnt(rs.getString("SALE_CNT"));// 수량
				dto.setWon_cost(rs.getString("WON_COST"));// 원가
				dto.setMall_won_cost(rs.getString("MALL_WON_COST"));// 공급단가
				dto.setSale_cost(rs.getString("SALE_COST"));// 판매가(수집)
				dto.setDelv_cost(rs.getString("DELV_COST"));// 배송비(수집)
				dto.setDelivery_method_str2(rs.getString("DELIVERY_METHOD_STR2"));// 배송구분(배송비반영)
				dto.setDelv_msg(rs.getString("DELV_MSG"));// 배송메세지
				dto.setDelivery_id(rs.getString("DELIVERY_ID"));// 택배사코드 (배송사)
				dto.setInvoice_no(rs.getString("INVOICE_NO"));// 송장번호
				dto.setLabel(rs.getString("LABEL"));// 라벨부

				// --------------------- 아직 안쓰는 필드들 ----------------------------------//
				dto.setProduct_id(rs.getString("PRODUCT_ID"));// 품번코드(엠링크)
				dto.setOrdseq(rs.getString("ORDSEQ"));// 일련번호
				dto.setCompno(rs.getString("COMPNO"));// 밴더코드
				dto.setOrder_id(rs.getString("ORDER_ID"));// 주문번호(쇼핑몰)
				dto.setUser_id(rs.getString("USER_ID"));// 주문자ID
				dto.setUser_name(rs.getString("USER_NAME"));// 주문자명
				dto.setUser_tel(rs.getString("USER_TEL"));// 주문자전화번호
				dto.setUser_cel(rs.getString("USER_CEL"));// 주문자핸드폰번호
				dto.setUser_email(rs.getString("USER_EMAIL"));// 주문자이메일주소
				dto.setReceive_tel(rs.getString("RECEIVE_TEL"));// 수취인전화번호
				dto.setReceive_cel(rs.getString("RECEIVE_CEL"));// 수취인핸드폰번호
				dto.setReceive_email(rs.getString("RECEIVE_EMAIL"));// 수취인이메일주소
				dto.setReceive_zipcode(rs.getString("RECEIVE_ZIPCODE"));// 수취인우편번호
				dto.setReceive_addr(rs.getString("RECEIVE_ADDR"));// 수취인주소
				dto.setTotal_cost(rs.getString("TOTAL_COST"));// 주문금액
				dto.setPay_cost(rs.getString("PAY_COST"));// 결제금액
				dto.setPartner_id(rs.getString("PARTNER_ID"));// 매입처명
				dto.setDpartner_id(rs.getString("DPARTNER_ID"));// 물류처명
				dto.setSku_id(rs.getString("SKU_ID"));// 단품코드(엠링크)
				dto.setProduct_name(rs.getString("PRODUCT_NAME"));// 상품명(수집)
				dto.setSku_value(rs.getString("SKU_VALUE"));// 옵션(수집)
				dto.setDelivery_method_str(rs.getString("DELIVERY_METHOD_STR"));// 배송구분

				dto.setSku_alias(rs.getString("SKU_ALIAS"));// 옵션별칭
				dto.setBox_ea(rs.getString("BOX_EA"));// EA(상품)
				dto.setJung_chk_yn(rs.getString("JUNG_CHK_YN"));// 정산대조확인여부
				dto.setMall_order_seq(rs.getString("MALL_ORDER_SEQ"));// 주문순번
				dto.setMall_order_id(rs.getString("MALL_ORDER_ID"));// 부주문번호
				dto.setEtc_field3(rs.getString("ETC_FIELD3"));// 수정된 수집옵션명
				dto.setOrder_gubun(rs.getString("ORDER_GUBUN"));// 주문구분
				dto.setP_ea(rs.getString("P_EA"));// EA(확정)
				dto.setOrd_field2(rs.getString("ORD_FIELD2"));// 세트분리주문구분
				dto.setCopy_idx(rs.getString("COPY_IDX"));// 원주문번호(사방넷)
				dto.setGoods_nm_pr(rs.getString("GOODS_NM_PR"));// 출력상품명
				dto.setGoods_keyword(rs.getString("GOODS_KEYWORD"));// 상품약어
				dto.setOrd_confirm_date(rs.getString("ORD_CONFIRM_DATE"));// 주문 확인일자
				dto.setRtn_dt(rs.getString("RTN_DT"));// 반품 완료일자
				dto.setChng_dt(rs.getString("CHNG_DT"));// 교환 완료일자
				dto.setOrd_confirm_date(rs.getString("DELIVERY_CONFIRM_DATE"));// 출고 완료일자
				dto.setCancel_dt(rs.getString("CANCEL_DT"));// 취소 완료일자
				dto.setClass_cd1(rs.getString("CLASS_CD1"));// 대분류코드
				dto.setClass_cd2(rs.getString("CLASS_CD2"));// 중분류코드
				dto.setClass_cd3(rs.getString("CLASS_CD3"));// 소분류코드
				dto.setClass_cd4(rs.getString("CLASS_CD4"));// 세분류코드
				dto.setBrand_nm(rs.getString("BRAND_NM"));// 브랜드명

				dto.setHope_delv_date(rs.getString("HOPE_DELV_DATE"));// 배송희망일자
				dto.setFld_dsp(rs.getString("FLD_DSP"));// 주문엑셀용
				dto.setInv_send_msg(rs.getString("INV_SEND_MSG"));// 운송장 전송 결과 메시지
				dto.setModel_no(rs.getString("MODEL_NO"));// 모델NO
				dto.setSet_gubun(rs.getString("SET_GUBUN"));// 상품구분
				dto.setEtc_msg(rs.getString("ETC_MSG"));// 기타메세지
				dto.setDelv_msg1(rs.getString("DELV_MSG1"));// 배송메세지
				dto.setMul_delv_msg(rs.getString("MUL_DELV_MSG"));// 물류메세지
				dto.setBarcode(rs.getString("BARCODE"));// 바코드
				dto.setInv_send_dm(rs.getString("INV_SEND_DM"));// 송장전송일자

				dto.setOrder_etc_1(rs.getString("ORDER_ETC_1"));// 자사몰필드
				dto.setOrder_etc_2(rs.getString("ORDER_ETC_2"));// 임시필드
				dto.setOrder_etc_3(rs.getString("ORDER_ETC_3"));// 임시필드
				dto.setOrder_etc_4(rs.getString("ORDER_ETC_4"));// 임시필드
				dto.setOrder_etc_5(rs.getString("ORDER_ETC_5"));// 임시필드
				dto.setOrder_etc_6(rs.getString("ORDER_ETC_6"));// 임시필드
				dto.setOrder_etc_7(rs.getString("ORDER_ETC_7"));// 임시필드
				dto.setOrder_etc_8(rs.getString("ORDER_ETC_8"));// 임시필드
				dto.setOrder_etc_9(rs.getString("ORDER_ETC_9"));// 임시필드
				dto.setOrder_etc_10(rs.getString("ORDER_ETC_10"));// 임시필드
				dto.setOrder_etc_11(rs.getString("ORDER_ETC_11"));// 임시필드
				dto.setOrder_etc_12(rs.getString("ORDER_ETC_12"));// 임시필드
				dto.setOrder_etc_13(rs.getString("ORDER_ETC_13"));// 임시필드
				dto.setOrder_etc_14(rs.getString("ORDER_ETC_14"));// 임시필드
				dto.setShopPw(rs.getString("PASSWORD"));
				dto.setApikey(rs.getString("APIKEY"));
				dto.setAuthkey1(rs.getString("AUTHKEY1"));
				dto.setAuthKey2(rs.getString("AUTHKEY2"));
				dto.setVendorId(rs.getString("VENDORID"));

				dto.setReceiptid(rs.getString("RECEIPTID")); // 반품 접수 아이디..
				dto.setExchangeid(rs.getString("EXCHANGEID")); // 교환 접수 아이디..

				list.add(dto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return list;
	}

	/*
	 * 해당 주문번호가 존재 하는지 체크
	 */
	public int isShopOrderMstExits(String shoopid, String orderid) {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "SELECT COUNT(ORDSEQ) AS CNT FROM shopordmst WHERE SHOPID=? AND ORDER_ID=?";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);
			pstmt_orddtl.setString(1, shoopid);
			pstmt_orddtl.setString(2, orderid);

			System.out.println("[존재 유무]" + pstmt_orddtl.toString());

			rs = pstmt_orddtl.executeQuery();

			while (rs.next()) {
				result = rs.getInt("CNT");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				DBCPInit.getInstance().freeConnection(connection, pstmt_orddtl, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * 해당 주문번호가 존재 하는지 체크
	 */
	public int isShopOrderMstExitsWithisClaim(String shoopid, String orderid, String isCliam) {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "SELECT COUNT(ORDSEQ) AS CNT FROM shopordmst WHERE SHOPID=? AND ORDER_ID=? AND LEFT(ORDER_STATUS,1)=?";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);
			pstmt_orddtl.setString(1, shoopid);
			pstmt_orddtl.setString(2, orderid);
			pstmt_orddtl.setString(3, isCliam);

			System.out.println("[존재 유무]" + pstmt_orddtl.toString());

			rs = pstmt_orddtl.executeQuery();

			while (rs.next()) {
				result = rs.getInt("CNT");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				DBCPInit.getInstance().freeConnection(connection, pstmt_orddtl, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	private int findIndex(List<ShopOptionProductInDto> list, Predicate<ShopOptionProductInDto> predicate) {

		for (int i = 0; i < list.size(); ++i) {
			ShopOptionProductInDto dto = list.get(i);
			if (predicate.test(dto)) {
				return i;
			}
		}
		return -1;
	}

	// 상품코드를 가져와서 저장한다.
	// 단품코드
	public String getCompanyOptGoodsCd(String shopid, String productId, String pSkuID, final String pSkuValue)
			throws Exception {
		ShopOptionProductInDao dao = new ShopOptionProductInDao();

		ShopOptionProductInDto resultDto = null;
		// 1. skuvale (상품코드 ) 를 가져와서 있으면 리턴한다.
		String valueCode = analyze(pSkuValue);

		if (!valueCode.isEmpty())
			return valueCode;

		// 2. shopprodin 테이블을 읽는다..
		List<String> optCompanyCodeList = getCompanyGoodsCd(shopid, productId);

		// 3. shopprodin 테이블에 없으면 공백을 리턴한다.
		if (optCompanyCodeList == null || optCompanyCodeList.size() == 0)
			return "";

		// 4. shopoptprodin 을 읽는다.
		List<ShopOptionProductInDto> list = dao.getShopOptProdInListBySendseq(productId);
		if (list.size() > 0) { // shopopt prodin 에 존재한다면..
			// 쇼핑몰에서 넘어온 코드가 존재 하면 shopoptprodin Shopprodsku 필드와 비교하고 동일하면 리턴..
			int index = -1;
			index = findIndex(list, d -> d.getShopprodsku().equals(pSkuID)); // 쇼핑몰에서 코드가 넘어옴..

			if (index > -1) { // 코드를 찾음.
				return list.get(index).getOptprodcd();
			} ///

			// pskuvalue 와 shopoptprodin Shopprodskunm 비교 해서 동일하면 optprodcd 리턴..
			index = findIndex(list, d -> d.getShopprodskunm().equals(pSkuValue));
			if (index > -1) { // skuvalue 와 동일 한가..
				return list.get(index).getOptprodcd();

			}

		}
		// shopprodin 테이블에 존재 하면 companygoodscd 를 리턴한다.
		int sendseq = YDMAStringUtil.convertToInt(optCompanyCodeList.get(0)); // sendseq 정보를 가져옴..
		return optCompanyCodeList.get(1);
	}

	private String analyze(String cellStr) {
		String result = "";
		try {
			if (cellStr != null && cellStr.length() < 5) {
				return result;
			}
			if (!cellStr.contains("(") || !cellStr.contains(")")) {
				return result; // 상품코드는 () 내에 존재하는 경우가 대부분임.
			}

			int beginIndex = cellStr.indexOf("(");
			int endIndex = cellStr.indexOf(")");

			if (((endIndex - beginIndex) - 1) >= 5) {
				result = cellStr.substring(beginIndex + 1, endIndex);
			}
		} catch (Exception e) {
			return "";
		}
		return result;
	}

	public List<String> getCompanyGoodsCd(String shoopid, String productId) {
		List<String> result = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String SQL = "SELECT SENDSEQ AS SENDSEQ, COMPAYNY_GOODS_CD AS COMPAYNY_GOODS_CD, \r\n" + "		  PRODSEQ"
					+ "  FROM SHOPPRODIN  \r\n" + " WHERE COMPNO=? " + "   AND SHOPCD=? " + "   AND SHOPPRODNO= ?";

			SQL = SQL.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(SQL);
			pstmt_orddtl.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt_orddtl.setString(2, shoopid);
			pstmt_orddtl.setString(3, productId);

			System.out.println("[존재 유무]" + pstmt_orddtl.toString());

			rs = pstmt_orddtl.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("SENDSEQ"));
				result.add(rs.getString("COMPAYNY_GOODS_CD"));
				result.add(rs.getString("PRODSEQ"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				DBCPInit.getInstance().freeConnection(connection, pstmt_orddtl, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * sql 저장 쿼리..
	 */
	private String getSqlSave() {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into shopordmst ( ");
		sb.append("COMPNO ,\r\n "); //
		sb.append("REG_DATE ,\r\n "); // 수집일자
		sb.append("ORDER_ID ,\r\n "); // 주문번호(쇼핑몰)
		sb.append("SHOPID ,\r\n "); // 쇼핑몰명
		sb.append("SHOP_USERID ,\r\n "); // 쇼핑몰ID
		sb.append("ORDER_STATUS ,\r\n "); // 주문상태
		sb.append("USER_ID ,\r\n "); // 주문자ID
		sb.append("USER_NAME ,\r\n "); // 주문자명
		sb.append("USER_TEL ,\r\n "); // 주문자전화번호
		sb.append("USER_CEL ,\r\n "); // 주문자핸드폰번호
		sb.append("USER_EMAIL ,\r\n "); // 주문자이메일주소
		sb.append("RECEIVE_TEL ,\r\n "); // 수취인전화번호
		sb.append("RECEIVE_CEL ,\r\n "); // 수취인핸드폰번호
		sb.append("RECEIVE_EMAIL ,\r\n "); // 수취인이메일주소
		sb.append("DELV_MSG ,\r\n "); // 배송메세지
		sb.append("RECEIVE_NAME ,\r\n "); // 수취인명
		sb.append("RECEIVE_ZIPCODE ,\r\n "); // 수취인우편번호
		sb.append("RECEIVE_ADDR ,\r\n "); // 수취인주소
		sb.append("TOTAL_COST ,\r\n "); // 주문금액
		sb.append("PAY_COST ,\r\n "); // 결제금액
		sb.append("ORDER_DATE ,\r\n "); // 주문일자
		sb.append("PARTNER_ID ,\r\n "); // 매입처명
		sb.append("DPARTNER_ID ,\r\n "); // 물류처명
		sb.append("MALL_PRODUCT_ID ,\r\n "); // 상품코드(쇼핑몰)
		sb.append("PRODUCT_ID ,\r\n "); // 품번코드(엠링크)
		sb.append("SKU_ID ,\r\n "); // 단품코드(엠링크)
		sb.append("P_PRODUCT_NAME ,\r\n "); // 상품명(확정)
		sb.append("P_SKU_VALUE ,\r\n "); // 옵션(확정)
		sb.append("PRODUCT_NAME ,\r\n "); // 상품명(수집)
		sb.append("SALE_COST ,\r\n "); // 판매가(수집)
		sb.append("MALL_WON_COST ,\r\n "); // 공급단가
		sb.append("WON_COST ,\r\n "); // 원가
		sb.append("SKU_VALUE ,\r\n "); // 옵션(수집)
		sb.append("SALE_CNT ,\r\n "); // 수량
		sb.append("DELIVERY_METHOD_STR ,\r\n "); // 배송구분
		sb.append("DELV_COST ,\r\n "); // 배송비(수집)
		sb.append("COMPAYNY_GOODS_CD ,\r\n "); // 자체상품코드
		sb.append("SKU_ALIAS ,\r\n "); // 옵션별칭
		sb.append("BOX_EA ,\r\n "); // EA(상품)
		sb.append("JUNG_CHK_YN ,\r\n "); // 정산대조확인여부
		sb.append("MALL_ORDER_SEQ ,\r\n "); // 주문순번
		sb.append("MALL_ORDER_ID ,\r\n "); // 부주문번호
		sb.append("ETC_FIELD3 ,\r\n "); // 수정된 수집옵션명
		sb.append("ORDER_GUBUN ,\r\n "); // 주문구분
		sb.append("P_EA ,\r\n "); // EA(확정)
		sb.append("ORD_FIELD2 ,\r\n "); // 세트분리주문구분
		sb.append("COPY_IDX ,\r\n "); // 원주문번호(사방넷)
		sb.append("GOODS_NM_PR ,\r\n "); // 출력상품명
		sb.append("GOODS_KEYWORD ,\r\n "); // 상품약어
		sb.append("ORD_CONFIRM_DATE ,\r\n "); // 주문 확인일자
		sb.append("RTN_DT ,\r\n "); // 반품 완료일자
		sb.append("CHNG_DT ,\r\n "); // 교환 완료일자
		sb.append("DELIVERY_CONFIRM_DATE ,\r\n "); // 출고 완료일자
		sb.append("CANCEL_DT ,\r\n "); // 취소 완료일자
		sb.append("CLASS_CD1 ,\r\n "); // 대분류코드
		sb.append("CLASS_CD2 ,\r\n "); // 중분류코드
		sb.append("CLASS_CD3 ,\r\n "); // 소분류코드
		sb.append("CLASS_CD4 ,\r\n "); // 세분류코드
		sb.append("BRAND_NM ,\r\n "); // 브랜드명
		sb.append("DELIVERY_ID ,\r\n "); // 택배사코드
		sb.append("INVOICE_NO ,\r\n "); // 송장번호
		sb.append("HOPE_DELV_DATE ,\r\n "); // 배송희망일자
		sb.append("FLD_DSP ,\r\n "); // 주문엑셀용
		sb.append("INV_SEND_MSG ,\r\n "); // 운송장 전송 결과 메시지
		sb.append("MODEL_NO ,\r\n "); // 모델NO
		sb.append("SET_GUBUN ,\r\n "); // 상품구분
		sb.append("ETC_MSG ,\r\n "); // 기타메세지
		sb.append("DELV_MSG1 ,\r\n "); // 배송메세지
		sb.append("MUL_DELV_MSG ,\r\n "); // 물류메세지
		sb.append("BARCODE ,\r\n "); // 바코드
		sb.append("INV_SEND_DM ,\r\n "); // 송장전송일자
		sb.append("DELIVERY_METHOD_STR2 ,\r\n "); // 배송구분(배송비반영)
		sb.append("LABEL ,\r\n "); // 라벨
		sb.append("ORDER_ETC_1 ,\r\n "); // 자사몰필드
		sb.append("ORDER_ETC_2 ,\r\n "); //
		sb.append("ORDER_ETC_3 ,\r\n "); //
		sb.append("ORDER_ETC_4 ,\r\n "); //
		sb.append("ORDER_ETC_5 ,\r\n "); //
		sb.append("ORDER_ETC_6 ,\r\n "); //
		sb.append("ORDER_ETC_7 ,\r\n "); //
		sb.append("ORDER_ETC_8 ,\r\n "); //
		sb.append("ORDER_ETC_9 ,\r\n "); //
		sb.append("ORDER_ETC_10 ,\r\n "); //
		sb.append("ORDER_ETC_11 ,\r\n "); //
		sb.append("ORDER_ETC_12 ,\r\n "); //
		sb.append("ORDER_ETC_13 ,\r\n "); //
		sb.append("ORDER_ETC_14 ,\r\n "); //

		sb.append("RECEIPTID ,\r\n "); //
		sb.append("EXCHANGEID, \r\n "); //
		sb.append("AUTHKEY1 \r\n "); //

		sb.append(") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" + ")");
		return sb.toString();
	}

	private String getSqlLog() {
		StringBuilder sb = new StringBuilder();

		sb.append("INSERT INTO  shopordstatuslog  \r\n");
		sb.append(
				"(COMPNO, SHOPID, ORDER_ID, SHOPUSERID, PRODUCT_NAME, ORDER_STATUS,ORDER_STATUSNM, RESULTMESSAGE, SENDDT) \r\n");
		sb.append(" VALUES(?,?,?,?,?,?,?,?,?); \r\n");
		return sb.toString();
	}

	/*
	 * 저장 또는 업데이트..
	 */
	public int SaveOrderCreate(List<ShopOrderMstDto> parms) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			PreparedStatement pstmtSave = connection.prepareStatement(getSqlSave().toUpperCase());
			PreparedStatement pstmtSaveLog = connection.prepareStatement(getSqlLog().toUpperCase());

			statementlist.add(pstmtSave);
			statementlist.add(pstmtSaveLog);

			boolean isSave = false;
			boolean isLog = false;

			for (ShopOrderMstDto dto : parms) {

				int isOrder = 0;

				String isClaim = dto.getOrder_status().substring(0, 1);
				if (isClaim.equals("R") || isClaim.equals("E") || isClaim.equals("C")) {
					isOrder = isShopOrderMstExitsWithisClaim(dto.getShopid(), dto.getOrder_id(), isClaim);
				} else {
					isOrder = isShopOrderMstExits(dto.getShopid(), dto.getOrder_id());
				}

				if (isOrder > 0)
					continue;

				if (dto.getResult_code().equals(OrderStatus.정상처리)) { /// 정상처리일 경우에만 데이타를 넣어준다..

					isSave = true;
					String goodProdCd = getCompanyOptGoodsCd(dto.getShopid(), dto.getMall_product_id(),
							dto.getP_sku_id() == null ? "" : dto.getP_sku_id(), dto.getP_sku_value() + "");

					if (!goodProdCd.isEmpty()) {
						dto.setCompayny_goods_cd(goodProdCd);
					}
					dto = ShopOrderDao.get().getWonCost(dto);

					Save(dto, connection, statementlist, pstmtSave);

				}

				if (dto.getResult_code().equals(OrderStatus.정상처리) || dto.getResult_code().equals(OrderStatus.에러발생)) {
					isLog = true;
					SaveLog(dto, connection, statementlist, pstmtSaveLog);
				}
			}

			if (isSave) {
				pstmtSave.executeBatch();
				pstmtSave.clearBatch();
			}
			if (isLog) {
				pstmtSaveLog.executeBatch();
				pstmtSaveLog.clearBatch();
			}

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
		return parms.size();
	}

	/*
	 * 저장 또는 업데이트..
	 */
	public int SaveOrUpdate(List<ShopOrderMstDto> parms) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);
			String sqlUpdateNomal = "UPDATE SHOPORDMST \r\n ";
			sqlUpdateNomal += "SET ORDER_STATUS = ? , \r\n ";
			sqlUpdateNomal += " ORDER_ETC_1 = ?,   \r\n ";
//			sqlUpdateNomal += " RECEIPTID = ? ,  \r\n ";
//			sqlUpdateNomal += " EXCHANGEID = ?   \r\n ";
			sqlUpdateNomal += " DOSINORDCD = ?,   \r\n ";
			sqlUpdateNomal += " DELIVERY_ID = ?,   \r\n ";
			sqlUpdateNomal += " INVOICE_NO = ?   \r\n ";
			sqlUpdateNomal += "WHERE \r\n "; // 수집일자
			sqlUpdateNomal += "COMPNO = ? AND SHOPID = ? AND SHOP_USERID = ? AND ORDER_ID =? \r\n ";
			sqlUpdateNomal += "AND left(ORDER_STATUS,1) not in('E','R','C') \r\n ";

			String sqlUpdateClaim = "UPDATE SHOPORDMST \r\n ";
			sqlUpdateClaim += "SET ORDER_STATUS = ? , \r\n ";
			sqlUpdateClaim += " ORDER_ETC_1 = ?   \r\n ";
//			sqlUpdateClaim += " RECEIPTID = ? ,  \r\n ";
//			sqlUpdateClaim += " EXCHANGEID = ?   \r\n ";
			sqlUpdateClaim += "WHERE \r\n "; // 수집일자
			sqlUpdateClaim += "COMPNO = ? AND SHOPID = ? AND SHOP_USERID = ? AND ORDER_ID =? \r\n ";
			sqlUpdateClaim += "  AND left(ORDER_STATUS,1) =? \r\n ";

			PreparedStatement pstmtUpdateNomal = connection.prepareStatement(sqlUpdateNomal.toUpperCase());
			PreparedStatement pstmtUpdateClaim = connection.prepareStatement(sqlUpdateClaim.toUpperCase());
			PreparedStatement pstmtSaveLog = connection.prepareStatement(getSqlLog().toUpperCase());

			statementlist.add(pstmtUpdateNomal);
			statementlist.add(pstmtUpdateClaim);
			statementlist.add(pstmtSaveLog);
			/* 반품 취소 크레임일 경우에는 주문번호가 하나 더 생긴다.. */

			boolean isUpdateNomal = false;
			boolean isUpdateClaim = false;
			boolean isLog = false;
			for (ShopOrderMstDto dto : parms) {

				if (dto.getResult_code().equals(OrderStatus.정상처리)) { /// 정상처리일 경우에만 데이타를 넣어준다..

					String isClaim = dto.getOrder_status().substring(0, 1);
					if (isClaim.equals("R") || isClaim.equals("E") || isClaim.equals("C")) {
						isUpdateClaim = true;
						UpdateClaim(dto, connection, statementlist, pstmtUpdateClaim);
						System.out.println(pstmtUpdateClaim.toString());
					} else {
						isUpdateNomal = true;
						UpdateNormal(dto, connection, statementlist, pstmtUpdateNomal);
						System.out.println(pstmtUpdateNomal.toString());
					}
				}

				if (dto.getResult_code().equals(OrderStatus.정상처리) || dto.getResult_code().equals(OrderStatus.에러발생)) {
					isLog = true;
					SaveLog(dto, connection, statementlist, pstmtSaveLog);
				}
			}

			if (isUpdateClaim) {
				pstmtUpdateClaim.executeBatch();
				pstmtUpdateClaim.clearBatch();
			}
			if (isUpdateNomal) {
				pstmtUpdateNomal.executeBatch();
				pstmtUpdateNomal.clearBatch();
			}
			if (isLog) {
				pstmtSaveLog.executeBatch();
				pstmtSaveLog.clearBatch();
			}

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
		return parms.size();
	}

	/*
	 * 업데이트...
	 */
	private PreparedStatement UpdateNormal(ShopOrderMstDto dto, Connection connection,
			List<PreparedStatement> statementlist, PreparedStatement pstmt) throws Exception {

		int j = 0;
		pstmt.setString(++j, dto.getOrder_status());
		pstmt.setString(++j, dto.getOrder_etc_1());
		pstmt.setString(++j, dto.getDosinOrder_code() == null ? "" : dto.getDosinOrder_code());// 도신코드
		pstmt.setString(++j, dto.getDelivery_id() == null ? "" : dto.getDelivery_id());// 택배업체
		pstmt.setString(++j, dto.getInvoice_no() == null ? "" : dto.getInvoice_no());// 택배번호
//		pstmt.setString(++j, dto.getReceiptid()); // 반품 아이디 .. 추가..
//		pstmt.setString(++j, dto.getExchangeid()); // 교환 아이디..

		pstmt.setString(++j, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setString(++j, dto.getShopid());
		pstmt.setString(++j, dto.getShop_userid());
		pstmt.setString(++j, dto.getOrder_id());

		pstmt.addBatch();
		pstmt.clearParameters();
		return pstmt;
	}

	private PreparedStatement UpdateClaim(ShopOrderMstDto dto, Connection connection,
			List<PreparedStatement> statementlist, PreparedStatement pstmt) throws Exception {

		int j = 0;
		pstmt.setString(++j, dto.getOrder_status());
		pstmt.setString(++j, dto.getOrder_etc_1());

//		pstmt.setString(++j, dto.getReceiptid()); // 반품 아이디 .. 추가..
//		pstmt.setString(++j, dto.getExchangeid()); // 교환 아이디..

		pstmt.setString(++j, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setString(++j, dto.getShopid());
		pstmt.setString(++j, dto.getShop_userid());
		pstmt.setString(++j, dto.getOrder_id());
		pstmt.setString(++j, dto.getOrder_status().substring(0, 1));

		pstmt.addBatch();
		pstmt.clearParameters();
		return pstmt;
	}

	/*
	 * 저장..
	 */
	private PreparedStatement Save(ShopOrderMstDto dto, Connection connection, List<PreparedStatement> statementlist,
			PreparedStatement pstmt) throws Exception {

		try {

			int j = 0;
			pstmt.setString(++j, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++j, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++j, dto.getOrder_id());
			pstmt.setString(++j, dto.getShopid());
			pstmt.setString(++j, dto.getShop_userid());
			pstmt.setString(++j, dto.getOrder_status());
			pstmt.setString(++j, dto.getUser_id());
			pstmt.setString(++j, dto.getUser_name());
			pstmt.setString(++j, dto.getUser_tel());
			pstmt.setString(++j, dto.getUser_cel());
			pstmt.setString(++j, dto.getUser_email());
			pstmt.setString(++j, dto.getReceive_tel());
			pstmt.setString(++j, dto.getReceive_cel());
			pstmt.setString(++j, dto.getReceive_email());
			pstmt.setString(++j, dto.getDelv_msg());
			pstmt.setString(++j, dto.getReceive_name());
			pstmt.setString(++j, dto.getReceive_zipcode());
			if (dto.getShopid().equals(ShopCommon.십일번가)) {
				pstmt.setString(++j, dto.getReceive_addr() + " " + dto.getReceive_detail());
			} else {
				pstmt.setString(++j, dto.getReceive_addr());
			}
			pstmt.setInt(++j, YDMAStringUtil.convertToInt(dto.getTotal_cost()));
			pstmt.setInt(++j, YDMAStringUtil.convertToInt(dto.getPay_cost()));
			pstmt.setString(++j, dto.getOrder_date());
			pstmt.setString(++j, dto.getPartner_id());
			pstmt.setString(++j, dto.getDpartner_id());
			pstmt.setString(++j, dto.getMall_product_id());
			pstmt.setString(++j, dto.getProduct_id());
			pstmt.setString(++j, dto.getSku_id());
			pstmt.setString(++j, dto.getP_product_name());
			pstmt.setString(++j, dto.getP_sku_value());
			pstmt.setString(++j, dto.getProduct_name());
			pstmt.setInt(++j, YDMAStringUtil.convertToInt(dto.getSale_cost()));
			pstmt.setInt(++j, YDMAStringUtil.convertToInt(dto.getMall_won_cost()));
			pstmt.setInt(++j, YDMAStringUtil.convertToInt(dto.getWon_cost()));
			pstmt.setString(++j, dto.getSku_value());
			pstmt.setInt(++j, YDMAStringUtil.convertToInt(dto.getSale_cnt()));
			pstmt.setString(++j, dto.getDelivery_method_str());
			pstmt.setInt(++j, YDMAStringUtil.convertToInt(dto.getDelv_cost()));
			pstmt.setString(++j, dto.getCompayny_goods_cd()); // COMPAYNY_GOODS_CD
			pstmt.setString(++j, dto.getSku_alias()); // SKU_ALIAS
			pstmt.setInt(++j, YDMAStringUtil.convertToInt(dto.getBox_ea()));
			pstmt.setString(++j, dto.getJung_chk_yn()); // JUNG_CHK_YN
			pstmt.setString(++j, dto.getMall_order_seq()); // MALL_ORDER_SEQ
			pstmt.setString(++j, dto.getMall_order_id()); // MALL_ORDER_ID
			pstmt.setString(++j, dto.getEtc_field3()); // ETC_FIELD3
			pstmt.setString(++j, dto.getOrder_gubun()); // ORDER_GUBUN
			pstmt.setInt(++j, YDMAStringUtil.convertToInt(dto.getP_ea())); // P_EA
			pstmt.setString(++j, dto.getOrd_field2()); // ORD_FIELD2
			pstmt.setString(++j, dto.getCopy_idx()); // COPY_IDX
			pstmt.setString(++j, dto.getGoods_nm_pr()); // GOODS_NM_PR
			pstmt.setString(++j, dto.getGoods_keyword()); // GOODS_KEYWORD
			pstmt.setString(++j, dto.getOrd_confirm_date()); // ORD_CONFIRM_DATE
			pstmt.setString(++j, dto.getRtn_dt());
			pstmt.setString(++j, dto.getChng_dt()); // CHNG_DT
			pstmt.setString(++j, dto.getDelivery_confirm_date()); // DELIVERY_CONFIRM_DATE
			pstmt.setString(++j, dto.getCancel_dt()); // CANCEL_DT
			pstmt.setString(++j, dto.getClass_cd1()); // CLASS_CD1
			pstmt.setString(++j, dto.getClass_cd2()); // CLASS_CD2
			pstmt.setString(++j, dto.getClass_cd3());
			pstmt.setString(++j, dto.getClass_cd4());
			pstmt.setString(++j, dto.getBrand_nm());
			pstmt.setString(++j, dto.getDelivery_id());
			pstmt.setString(++j, dto.getInvoice_no()); // INVOICE_NO
			pstmt.setString(++j, dto.getHope_delv_date()); // HOPE_DELV_DATE
			pstmt.setString(++j, dto.getFld_dsp()); // FLD_DSP
			pstmt.setString(++j, dto.getInv_send_msg()); // INV_SEND_MSG
			pstmt.setString(++j, dto.getModel_no()); // MODEL_NO
			pstmt.setString(++j, dto.getSet_gubun()); // SET_GUBUN
			pstmt.setString(++j, dto.getEtc_msg()); // ETC_MSG
			pstmt.setString(++j, dto.getDelv_msg1()); // DELV_MSG1
			pstmt.setString(++j, dto.getMul_delv_msg()); // MUL_DELV_MSG
			pstmt.setString(++j, dto.getBarcode()); // BARCODE
			pstmt.setString(++j, dto.getInv_send_dm()); // INV_SEND_DM

			pstmt.setString(++j, dto.getDelivery_method_str2()); // DELIVERY_METHOD_STR2
			pstmt.setString(++j, dto.getLabel()); // LABEL
			pstmt.setString(++j, "N"); // 로그값와 동기화를 맞추기 위해서 컬럼 사용
			pstmt.setString(++j, dto.getOrder_etc_2());
			pstmt.setString(++j, dto.getOrder_etc_3());
			pstmt.setString(++j, dto.getOrder_etc_4());
			pstmt.setString(++j, dto.getOrder_etc_5());
			pstmt.setString(++j, dto.getOrder_etc_6());
			pstmt.setString(++j, dto.getOrder_etc_7());
			pstmt.setString(++j, dto.getOrder_etc_8());
			pstmt.setString(++j, dto.getOrder_etc_9());
			pstmt.setString(++j, dto.getOrder_etc_10());
			pstmt.setString(++j, dto.getOrder_etc_11());
			pstmt.setString(++j, dto.getOrder_etc_12());
			pstmt.setString(++j, dto.getOrder_etc_13());
			pstmt.setString(++j, dto.getOrder_etc_14());

			pstmt.setString(++j, dto.getReceiptid()); // 반품..
			pstmt.setString(++j, dto.getExchangeid()); // 교품..
			pstmt.setString(++j, dto.getAuthkey1()); // 인터파크 사업자별 아이디 다중인경우 사용함..

			dto.setResult_code("0");
			String msg = String.format("사용자아이디 %s 주문번호 %s 저장되었습니다.", dto.getUser_id(), dto.getOrder_id());
			dto.setResult_text(msg);

			pstmt.addBatch();
			pstmt.clearParameters();
			return pstmt;
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * 로그를 저장한다...
	 */
	public PreparedStatement SaveLog(ShopOrderMstDto dto, Connection connection, List<PreparedStatement> statementlist,
			PreparedStatement pstmt) throws Exception {

		int j = 0;

		pstmt.setString(++j, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setString(++j, dto.getShopid());
		pstmt.setString(++j, dto.getOrder_id());
		pstmt.setString(++j, dto.getShop_userid());
		pstmt.setString(++j, dto.getP_product_name());
		pstmt.setString(++j, dto.getOrder_status());
		pstmt.setString(++j, OrderStatus.getInstance().findCode(dto.getOrder_status()));
		pstmt.setString(++j, dto.getResult_text());
		pstmt.setString(++j, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt.addBatch();
		pstmt.clearParameters();
		return pstmt;

	}

	public List<ShopOrderStatusDto> getOrderLog(String shopid, String orderID) throws Exception {
		List<ShopOrderStatusDto> list = new ArrayList<ShopOrderStatusDto>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = " SELECT  A.SHOPID AS  SHOPID, B.SHOPNM  AS SHOPNM, A.SHOPUSERID AS  SHOPUSERID,  A.ORDER_STATUSNM AS ORDER_STATUSNM ,IFNULL(A.RESULTMESSAGE,'')  AS RESULTMESSAGE, A.SENDDT AS SENDDT\r\n"
					+ "  FROM SHOPORDSTATUSLOG  AS A\r\n" + "  INNER JOIN shopinfo AS B\r\n"
					+ "  ON A.SHOPID = B.SHOPCD\r\n" + "  WHERE A.COMPNO = ? AND A.SHOPID= ? AND A.ORDER_ID = ?";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopid);
			pstmt.setString(3, orderID);

			System.out.println("[getOrderLog]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				int i = 0;

				ShopOrderStatusDto dto = new ShopOrderStatusDto();
				dto.setOrdseq(++rowno);

				dto.setShopnm(rs.getString("SHOPNM"));
				dto.setShopuserid(rs.getString("SHOPUSERID"));
				dto.setOrder_status(rs.getString("ORDER_STATUSNM"));

				dto.setResultMessage(rs.getString("RESULTMESSAGE"));
				dto.setSenddt(rs.getString("SENDDT"));
				list.add(dto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<ShopOrderStatusDto> getOrderStatus(String date) throws Exception {

		List<ShopOrderStatusDto> list = null;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT \r\n" + "A.ORDSEQ AS ORDSEQ, \r\n" + "A.SEQ AS SEQ, \r\n" + "A.COMPNO AS COMPNO,\r\n"
					+ "B.SHOPNM AS SHOPNM,\r\n" + "A.SHOPID AS SHOPID, \r\n" + "A.SHOPUSERID AS SHOPUSERID,\r\n"
					+ "A.ORDER_STATUS AS ORDER_STATUS,\r\n" + "C.NAME AS STATUSNAME,\r\n"
					+ "RESULTMESSAGE AS RESULTMESSAGE, \r\n" + "SENDDT AS SENDDT\r\n" + "FROM SHOPORDSTATUSLOG AS A\r\n"
					+ "INNER JOIN SHOPINFO AS B\r\n" + "ON A.SHOPID = B.SHOPCD\r\n"
					+ "INNER JOIN shopordstatus AS C\r\n" + "ON C.SHOPID = 'shop0003'\r\n"
					+ "AND A.ORDER_STATUS = C.CODE \r\n" + " WHERE A.COMPNO =? AND SENDDT > ? AND  SENDDT < ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, date.concat(" 00:00:00"));
			pstmt.setString(3, date.concat(" 23:59:00"));

			System.out.println("[getOrderStatus]" + pstmt.toString());

			rs = pstmt.executeQuery();
			rs.last();
			int rowcount = rs.getRow();

			if (rowcount > 0) {
				rs.beforeFirst();
				list = new ArrayList<ShopOrderStatusDto>();
				int rowno = 0;
				while (rs.next()) {
					int i = 0;

					ShopOrderStatusDto dto = new ShopOrderStatusDto();
					dto.setOrdseq(++rowno);

					dto.setShopnm(rs.getString("SHOPNM"));
					dto.setShopuserid(rs.getString("SHOPUSERID"));
					dto.setStatusname(rs.getString("STATUSNAME"));
					dto.setOrder_status(rs.getString("ORDER_STATUS"));

					dto.setResultMessage(rs.getString("RESULTMESSAGE"));
					dto.setSenddt(rs.getString("SENDDT"));
					list.add(dto);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public ShopOrderMstDto getShopOrderExpressList(String ordseq) throws Exception {
		ShopOrderMstDto dto = new ShopOrderMstDto();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT \r\n");
			sql.append("IFNULL(M.ORDSEQ,  '' ) AS ORDSEQ,  \r\n");// 일련번호
			sql.append("IFNULL(M.COMPNO,  '' ) AS COMPNO,  \r\n");// 밴더코드
			sql.append("IFNULL(M.REG_DATE,  '' ) AS REG_DATE,  \r\n");// 수집일자
			sql.append("IFNULL(M.ORDER_ID,  '' ) AS ORDER_ID,  \r\n");// 주문번호(쇼핑몰)
			sql.append("IFNULL(M.SHOPID,  '' ) AS SHOPID,  \r\n");// 쇼핑몰명
			sql.append("IFNULL(M.SHOP_USERID,  '' ) AS SHOP_USERID,  \r\n");// 쇼핑몰ID
			sql.append("IFNULL(M.ORDER_STATUS,  '' ) AS ORDER_STATUS,  \r\n");// 주문상태
			sql.append("IFNULL(M.USER_ID,  '' ) AS USER_ID,  \r\n");// 주문자ID
			sql.append("IFNULL(M.USER_NAME,  '' ) AS USER_NAME,  \r\n");// 주문자명
			sql.append("IFNULL(M.USER_TEL,  '' ) AS USER_TEL,  \r\n");// 주문자전화번호
			sql.append("IFNULL(M.USER_CEL,  '' ) AS USER_CEL,  \r\n");// 주문자핸드폰번호
			sql.append("IFNULL(M.USER_EMAIL,  '' ) AS USER_EMAIL,  \r\n");// 주문자이메일주소
			sql.append("IFNULL(M.RECEIVE_TEL,  '' ) AS RECEIVE_TEL,  \r\n");// 수취인전화번호
			sql.append("IFNULL(M.RECEIVE_CEL,  '' ) AS RECEIVE_CEL,  \r\n");// 수취인핸드폰번호
			sql.append("IFNULL(M.RECEIVE_EMAIL,  '' ) AS RECEIVE_EMAIL,  \r\n");// 수취인이메일주소
			sql.append("IFNULL(M.DELV_MSG,  '' ) AS DELV_MSG,  \r\n");// 배송메세지
			sql.append("IFNULL(M.RECEIVE_NAME,  '' ) AS RECEIVE_NAME,  \r\n");// 수취인명
			sql.append("IFNULL(M.RECEIVE_ZIPCODE,  '' ) AS RECEIVE_ZIPCODE,  \r\n");// 수취인우편번호
			sql.append("IFNULL(M.RECEIVE_ADDR,  '' ) AS RECEIVE_ADDR,  \r\n");// 수취인주소
			sql.append("IFNULL(M.TOTAL_COST,  '' ) AS TOTAL_COST,  \r\n");// 주문금액
			sql.append("IFNULL(M.PAY_COST,  '' ) AS PAY_COST,  \r\n");// 결제금액
			sql.append("IFNULL(M.ORDER_DATE,  '' ) AS ORDER_DATE,  \r\n");// 주문일자
			sql.append("IFNULL(M.PARTNER_ID,  '' ) AS PARTNER_ID,  \r\n");// 매입처명
			sql.append("IFNULL(M.DPARTNER_ID,  '' ) AS DPARTNER_ID,  \r\n");// 물류처명
			sql.append("IFNULL(M.MALL_PRODUCT_ID,  '' ) AS MALL_PRODUCT_ID,  \r\n");// 상품코드(쇼핑몰)
			sql.append("IFNULL(M.PRODUCT_ID,  '' ) AS PRODUCT_ID,  \r\n");// 품번코드(엠링크)
			sql.append("IFNULL(M.SKU_ID,  '' ) AS SKU_ID,  \r\n");// 단품코드(엠링크)
			sql.append("IFNULL(M.P_PRODUCT_NAME,  '' ) AS P_PRODUCT_NAME,  \r\n");// 상품명(확정)
			sql.append("IFNULL(M.P_SKU_VALUE,  '' ) AS P_SKU_VALUE,  \r\n");// 옵션(확정)
			sql.append("IFNULL(M.PRODUCT_NAME,  '' ) AS PRODUCT_NAME,  \r\n");// 상품명(수집)
			sql.append("IFNULL(M.SALE_COST,  '' ) AS SALE_COST,  \r\n");// 판매가(수집)
			sql.append("IFNULL(M.MALL_WON_COST,  '' ) AS MALL_WON_COST,  \r\n");// 공급단가
			sql.append("IFNULL(M.WON_COST,  '' ) AS WON_COST,  \r\n");// 원가
			sql.append("IFNULL(M.SKU_VALUE,  '' ) AS SKU_VALUE,  \r\n");// 옵션(수집)
			sql.append("IFNULL(M.SALE_CNT,  '' ) AS SALE_CNT,  \r\n");// 수량
			sql.append("IFNULL(M.DELIVERY_METHOD_STR,  '' ) AS DELIVERY_METHOD_STR,  \r\n");// 배송구분
			sql.append("IFNULL(M.DELV_COST,  '' ) AS DELV_COST,  \r\n");// 배송비(수집)
			sql.append("IFNULL(M.COMPAYNY_GOODS_CD,  '' ) AS COMPAYNY_GOODS_CD,  \r\n");// 자체상품코드
			sql.append("IFNULL(M.SKU_ALIAS,  '' ) AS SKU_ALIAS,  \r\n");// 옵션별칭
			sql.append("IFNULL(M.BOX_EA,  '' ) AS BOX_EA,  \r\n");// EA(상품)
			sql.append("IFNULL(M.JUNG_CHK_YN,  '' ) AS JUNG_CHK_YN,  \r\n");// 정산대조확인여부
			sql.append("IFNULL(M.MALL_ORDER_SEQ,  '' ) AS MALL_ORDER_SEQ,  \r\n");// 주문순번
			sql.append("IFNULL(M.MALL_ORDER_ID,  '' ) AS MALL_ORDER_ID,  \r\n");// 부주문번호
			sql.append("IFNULL(M.ETC_FIELD3,  '' ) AS ETC_FIELD3,  \r\n");// 수정된 수집옵션명
			sql.append("IFNULL(M.ORDER_GUBUN,  '' ) AS ORDER_GUBUN,  \r\n");// 주문구분
			sql.append("IFNULL(M.P_EA,  '' ) AS P_EA,  \r\n");// EA(확정)
			sql.append("IFNULL(M.ORD_FIELD2,  '' ) AS ORD_FIELD2,  \r\n");// 세트분리주문구분
			sql.append("IFNULL(M.COPY_IDX,  '' ) AS COPY_IDX,  \r\n");// 원주문번호(사방넷)
			sql.append("IFNULL(M.GOODS_NM_PR,  '' ) AS GOODS_NM_PR,  \r\n");// 출력상품명
			sql.append("IFNULL(M.GOODS_KEYWORD,  '' ) AS GOODS_KEYWORD,  \r\n");// 상품약어
			sql.append("IFNULL(M.ORD_CONFIRM_DATE,  '' ) AS ORD_CONFIRM_DATE,  \r\n");// 주문 확인일자
			sql.append("IFNULL(M.RTN_DT,  '' ) AS RTN_DT,  \r\n");// 반품 완료일자
			sql.append("IFNULL(M.CHNG_DT,  '' ) AS CHNG_DT,  \r\n");// 교환 완료일자
			sql.append("IFNULL(M.DELIVERY_CONFIRM_DATE,  '' ) AS DELIVERY_CONFIRM_DATE,  \r\n");// 출고 완료일자
			sql.append("IFNULL(M.CANCEL_DT,  '' ) AS CANCEL_DT,  \r\n");// 취소 완료일자
			sql.append("IFNULL(M.CLASS_CD1,  '' ) AS CLASS_CD1,  \r\n");// 대분류코드
			sql.append("IFNULL(M.CLASS_CD2,  '' ) AS CLASS_CD2,  \r\n");// 중분류코드
			sql.append("IFNULL(M.CLASS_CD3,  '' ) AS CLASS_CD3,  \r\n");// 소분류코드
			sql.append("IFNULL(M.CLASS_CD4,  '' ) AS CLASS_CD4,  \r\n");// 세분류코드
			sql.append("IFNULL(M.BRAND_NM,  '' ) AS BRAND_NM,  \r\n");// 브랜드명
			sql.append("IFNULL(M.DELIVERY_ID,  '' ) AS DELIVERY_ID,  \r\n");// 택배사코드
			sql.append("IFNULL(M.INVOICE_NO,  '' ) AS INVOICE_NO,  \r\n");// 송장번호
			sql.append("IFNULL(M.HOPE_DELV_DATE,  '' ) AS HOPE_DELV_DATE,  \r\n");// 배송희망일자
			sql.append("IFNULL(M.FLD_DSP,  '' ) AS FLD_DSP,  \r\n");// 주문엑셀용
			sql.append("IFNULL(M.INV_SEND_MSG,  '' ) AS INV_SEND_MSG,  \r\n");// 운송장 전송 결과 메시지
			sql.append("IFNULL(M.MODEL_NO,  '' ) AS MODEL_NO,  \r\n");// 모델NO
			sql.append("IFNULL(M.SET_GUBUN,  '' ) AS SET_GUBUN,  \r\n");// 상품구분
			sql.append("IFNULL(M.ETC_MSG,  '' ) AS ETC_MSG,  \r\n");// 기타메세지
			sql.append("IFNULL(M.DELV_MSG1,  '' ) AS DELV_MSG1,  \r\n");// 배송메세지
			sql.append("IFNULL(M.MUL_DELV_MSG,  '' ) AS MUL_DELV_MSG,  \r\n");// 물류메세지
			sql.append("IFNULL(M.BARCODE,  '' ) AS BARCODE,  \r\n");// 바코드
			sql.append("IFNULL(M.INV_SEND_DM,  '' ) AS INV_SEND_DM,  \r\n");// 송장전송일자
			sql.append("IFNULL(M.DELIVERY_METHOD_STR2,  '' ) AS DELIVERY_METHOD_STR2,  \r\n");// 배송구분(배송비반영)
			sql.append("IFNULL(M.ORDER_ETC_1,  '' ) AS ORDER_ETC_1,  \r\n");// 자사몰필드
			sql.append("IFNULL(M.ORDER_ETC_2,  '' ) AS ORDER_ETC_2,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_3,  '' ) AS ORDER_ETC_3,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_4,  '' ) AS ORDER_ETC_4,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_5,  '' ) AS ORDER_ETC_5,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_6,  '' ) AS ORDER_ETC_6,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_7,  '' ) AS ORDER_ETC_7,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_8,  '' ) AS ORDER_ETC_8,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_9,  '' ) AS ORDER_ETC_9,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_10,  '' ) AS ORDER_ETC_10,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_11,  '' ) AS ORDER_ETC_11,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_12,  '' ) AS ORDER_ETC_12,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_13,  '' ) AS ORDER_ETC_13,  \r\n");// 임시필드
			sql.append("IFNULL(M.ORDER_ETC_14,  '' ) AS ORDER_ETC_14,  \r\n");// 임시필드
			sql.append("IFNULL(M.LABEL,  '' ) AS LABEL,  \r\n");// 라벨

			sql.append("IFNULL(b.COMPAYNY_GOODS_CD,  '' ) AS COMPAYNY_GOODS_CD, \r\n");
			sql.append("IFNULL(c.DLVID,  '' ) AS DLVID, \r\n");
			sql.append("ifnull(c.DLVNM,'') AS DLVNM,  \r\n");
			sql.append("IFNULL(d.URL,  '' ) AS URL, \r\n");
			sql.append("IFNULL(d.USER_ID,  '' ) AS EXPID, \r\n");
			sql.append("IFNULL(d.PASSWD,  '' ) AS PASSWD,");

			sql.append("IFNULL(M.RECEIPTID ,'') AS RECEIPTID,      \r\n");
			sql.append("IFNULL(M.EXCHANGEID  ,'') AS EXCHANGEID       \r\n");

			sql.append(" FROM shopordmst m JOIN shopprodin b ON m.compno = b.compno \r\n"
					+ "AND m.MALL_PRODUCT_ID = b.SHOPPRODNO \r\n"
					+ "JOIN expprodmst a ON m.compno = a.compno AND b.COMPAYNY_GOODS_CD = a.PRODCD \r\n"
					+ "JOIN deliverys c ON a.expcd = c.DLVNM \r\n"
					+ "JOIN dvlinfo d ON m.COMPNO = d.compno AND c.DLVID = d.DVLID ");
			sql.append(" WHERE M.COMPNO = ? AND M.ORDSEQ= ? ");
			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, ordseq);

			System.out.println("[ShopOrderList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 0;

			while (rs.next()) {
				dto.setReg_date(rs.getString("REG_DATE"));// 수집일자
				dto.setOrder_date(rs.getString("ORDER_DATE"));// 주문일자
				dto.setOrder_status(rs.getString("ORDER_STATUS"));// 주문상태
				dto.setShopid(rs.getString("SHOPID"));// 쇼핑몰명
				dto.setShop_userid(rs.getString("SHOP_USERID"));// 쇼핑몰ID
				dto.setReceive_name(rs.getString("RECEIVE_NAME"));// 수령자
				dto.setMall_product_id(rs.getString("MALL_PRODUCT_ID"));// 상품코드(쇼핑몰)
				dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD"));// 자체상품코드
				dto.setP_product_name(rs.getString("P_PRODUCT_NAME"));// 상품명(확정)
				dto.setP_sku_value(rs.getString("P_SKU_VALUE"));// 옵션(확정)
				dto.setSale_cnt(rs.getString("SALE_CNT"));// 수량
				dto.setWon_cost(rs.getString("WON_COST"));// 원가
				dto.setMall_won_cost(rs.getString("MALL_WON_COST"));// 공급단가
				dto.setSale_cost(rs.getString("SALE_COST"));// 판매가(수집)
				dto.setDelv_cost(rs.getString("DELV_COST"));// 배송비(수집)
				dto.setDelivery_method_str2(rs.getString("DELIVERY_METHOD_STR2"));// 배송구분(배송비반영)
				dto.setDelv_msg(rs.getString("DELV_MSG"));// 배송메세지
				dto.setDelivery_id(rs.getString("DELIVERY_ID"));// 택배사코드 (배송사)
				dto.setInvoice_no(rs.getString("INVOICE_NO"));// 송장번호
				dto.setLabel(rs.getString("LABEL"));// 라벨부

				// --------------------- 아직 안쓰는 필드들 ----------------------------------//
				dto.setProduct_id(rs.getString("PRODUCT_ID"));// 품번코드(엠링크)
				dto.setOrdseq(rs.getString("ORDSEQ"));// 일련번호
				dto.setCompno(rs.getString("COMPNO"));// 밴더코드
				dto.setOrder_id(rs.getString("ORDER_ID"));// 주문번호(쇼핑몰)
				dto.setUser_id(rs.getString("USER_ID"));// 주문자ID
				dto.setUser_name(rs.getString("USER_NAME"));// 주문자명
				dto.setUser_tel(rs.getString("USER_TEL"));// 주문자전화번호
				dto.setUser_cel(rs.getString("USER_CEL"));// 주문자핸드폰번호
				dto.setUser_email(rs.getString("USER_EMAIL"));// 주문자이메일주소
				dto.setReceive_tel(rs.getString("RECEIVE_TEL"));// 수취인전화번호
				dto.setReceive_cel(rs.getString("RECEIVE_CEL"));// 수취인핸드폰번호
				dto.setReceive_email(rs.getString("RECEIVE_EMAIL"));// 수취인이메일주소
				dto.setReceive_zipcode(rs.getString("RECEIVE_ZIPCODE"));// 수취인우편번호
				dto.setReceive_addr(rs.getString("RECEIVE_ADDR"));// 수취인주소
				dto.setTotal_cost(rs.getString("TOTAL_COST"));// 주문금액
				dto.setPay_cost(rs.getString("PAY_COST"));// 결제금액
				dto.setPartner_id(rs.getString("PARTNER_ID"));// 매입처명
				dto.setDpartner_id(rs.getString("DPARTNER_ID"));// 물류처명
				dto.setSku_id(rs.getString("SKU_ID"));// 단품코드(엠링크)
				dto.setProduct_name(rs.getString("PRODUCT_NAME"));// 상품명(수집)
				dto.setSku_value(rs.getString("SKU_VALUE"));// 옵션(수집)
				dto.setDelivery_method_str(rs.getString("DELIVERY_METHOD_STR"));// 배송구분

				dto.setSku_alias(rs.getString("SKU_ALIAS"));// 옵션별칭
				dto.setBox_ea(rs.getString("BOX_EA"));// EA(상품)
				dto.setJung_chk_yn(rs.getString("JUNG_CHK_YN"));// 정산대조확인여부
				dto.setMall_order_seq(rs.getString("MALL_ORDER_SEQ"));// 주문순번
				dto.setMall_order_id(rs.getString("MALL_ORDER_ID"));// 부주문번호
				dto.setEtc_field3(rs.getString("ETC_FIELD3"));// 수정된 수집옵션명
				dto.setOrder_gubun(rs.getString("ORDER_GUBUN"));// 주문구분
				dto.setP_ea(rs.getString("P_EA"));// EA(확정)
				dto.setOrd_field2(rs.getString("ORD_FIELD2"));// 세트분리주문구분
				dto.setCopy_idx(rs.getString("COPY_IDX"));// 원주문번호(사방넷)
				dto.setGoods_nm_pr(rs.getString("GOODS_NM_PR"));// 출력상품명
				dto.setGoods_keyword(rs.getString("GOODS_KEYWORD"));// 상품약어
				dto.setOrd_confirm_date(rs.getString("ORD_CONFIRM_DATE"));// 주문 확인일자
				dto.setRtn_dt(rs.getString("RTN_DT"));// 반품 완료일자
				dto.setChng_dt(rs.getString("CHNG_DT"));// 교환 완료일자
				dto.setOrd_confirm_date(rs.getString("DELIVERY_CONFIRM_DATE"));// 출고 완료일자
				dto.setCancel_dt(rs.getString("CANCEL_DT"));// 취소 완료일자
				dto.setClass_cd1(rs.getString("CLASS_CD1"));// 대분류코드
				dto.setClass_cd2(rs.getString("CLASS_CD2"));// 중분류코드
				dto.setClass_cd3(rs.getString("CLASS_CD3"));// 소분류코드
				dto.setClass_cd4(rs.getString("CLASS_CD4"));// 세분류코드
				dto.setBrand_nm(rs.getString("BRAND_NM"));// 브랜드명

				dto.setHope_delv_date(rs.getString("HOPE_DELV_DATE"));// 배송희망일자
				dto.setFld_dsp(rs.getString("FLD_DSP"));// 주문엑셀용
				dto.setInv_send_msg(rs.getString("INV_SEND_MSG"));// 운송장 전송 결과 메시지
				dto.setModel_no(rs.getString("MODEL_NO"));// 모델NO
				dto.setSet_gubun(rs.getString("SET_GUBUN"));// 상품구분
				dto.setEtc_msg(rs.getString("ETC_MSG"));// 기타메세지
				dto.setDelv_msg1(rs.getString("DELV_MSG1"));// 배송메세지
				dto.setMul_delv_msg(rs.getString("MUL_DELV_MSG"));// 물류메세지
				dto.setBarcode(rs.getString("BARCODE"));// 바코드
				dto.setInv_send_dm(rs.getString("INV_SEND_DM"));// 송장전송일자

				dto.setOrder_etc_1(rs.getString("ORDER_ETC_1"));// 자사몰필드
				dto.setOrder_etc_2(rs.getString("ORDER_ETC_2"));// 임시필드
				dto.setOrder_etc_3(rs.getString("ORDER_ETC_3"));// 임시필드
				dto.setOrder_etc_4(rs.getString("ORDER_ETC_4"));// 임시필드
				dto.setOrder_etc_5(rs.getString("ORDER_ETC_5"));// 임시필드
				dto.setOrder_etc_6(rs.getString("ORDER_ETC_6"));// 임시필드
				dto.setOrder_etc_7(rs.getString("ORDER_ETC_7"));// 임시필드
				dto.setOrder_etc_8(rs.getString("ORDER_ETC_8"));// 임시필드
				dto.setOrder_etc_9(rs.getString("ORDER_ETC_9"));// 임시필드
				dto.setOrder_etc_10(rs.getString("ORDER_ETC_10"));// 임시필드
				dto.setOrder_etc_11(rs.getString("ORDER_ETC_11"));// 임시필드
				dto.setOrder_etc_12(rs.getString("ORDER_ETC_12"));// 임시필드
				dto.setOrder_etc_13(rs.getString("ORDER_ETC_13"));// 임시필드
				dto.setOrder_etc_14(rs.getString("ORDER_ETC_14"));// 임시필드

				dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD"));
				dto.setDlvid(rs.getString("DLVID"));
				dto.setDlvnm(rs.getString("DLVNM"));
				dto.setUrl(rs.getString("URL"));
				dto.setExpid(rs.getString("EXPID"));
				dto.setPasswd(rs.getString("PASSWD"));

				dto.setReceiptid(rs.getString("RECEIPTID")); // 반품 접수 아이디..
				dto.setExchangeid(rs.getString("EXCHANGEID")); // 교환 접수 아이디..
				dto.setAuthkey1(rs.getString("AUTHKEY1")); // 교환 접수 아이디..

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	public List<String> getNaverAddress(List<ShopOrderMstDto> map, Shell shell) {
		List<String> list = new ArrayList<>();
		ChromeExtention chrome = ChromeExtention.getInstace();
		ChromeDriver driver = null;
		try {
			YDMAProgressBar.get().start("택배사 전송중입니다.", 1);
			YDMAProgressBar.get().setValue("택배사전송중입니다.", 0, false);
			driver = chrome.setFileDown(false).setHeadlessMode(true).getDriver();

			// TODO Auto-generated method stub
			String URL = "https://search.naver.com/search.naver";
			driver.get(URL);
			// driver.executeScript("window.open();",null );
			WebDriverWait wait = new WebDriverWait(driver, 10);
			for (ShopOrderMstDto dto : map) {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nx_query\"]"))).clear();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nx_query\"]")))
						.sendKeys(dto.getReceive_addr());
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//*[@id=\"nx_search_form\"]/fieldset/button"))).click();
				String address = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//*[@id=\"lcs_region_section\"]/div[2]/div[2]/div[1]/h3")))
						.getText();
				list.add(address);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			driver.quit();
		}
		return list;
	}

	// 송장전송
	public List<ShopOrderMstDto> sendPickupExpressCrawl(List<ShopOrderMstDto> list, Shell shell, List<String> addr) {

		List<ShopOrderMstDto> express = new ArrayList<>();
		ChromeExtention chrome = ChromeExtention.getInstace();
		ChromeDriver driver = null;
		try {

			driver = chrome.setFileDown(false).setHeadlessMode(false).getDriver();

			// TODO Auto-generated method stub
			String URL = list.get(0).getUrl();
			// driver.executeScript("window.open();",null );
			WebDriverWait wait = new WebDriverWait(driver, 30);
			driver.get(URL);
			Thread.sleep(1000);
			WebElement elementloginBox = driver.findElement(By.xpath("/html/body/div[3]/div[2]"));
			elementloginBox.findElement(By.xpath("//*[@id=\"principal\"]/input")).sendKeys(list.get(0).getExpid());
			elementloginBox.findElement(By.xpath("//*[@id=\"credential\"]/input")).sendKeys(list.get(0).getPasswd());
			elementloginBox.findElement(By.xpath("//*[@id=\"btn-login\"]")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/header/nav/div[1]")));
			// 거래처관리
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[3]/header/nav/div[1]/div[3]/i-button"))).click();
			Thread.sleep(1000);
			// 건별접수
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/div[3]/header/nav/div[3]/div/div[2]/div[2]/div[2]/ul/li[1]/a"))).click();
			// elementsearchBox.findElement(By.xpath("/html/body/div[3]/header/nav/div[3]/div/div[2]/div[2]/div[2]/ul/li[2]/a")).click();

			driver.switchTo().frame(driver.findElement(By.id("workframe_10781")));

			WebElement elementbodyBox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]")));
			for (ShopOrderMstDto dto : list) {
				YDMAProgressBar.get().setValue("택배사전송중입니다.", 1, false);
				List<String> output = new ArrayList<>();
				Thread.sleep(1000);
				StringBuffer sbInput = new StringBuffer();

				driver.executeScript(String.format("$('#edtAcperTel').val('%s'); ",
						dto.getReceive_tel().equals("") ? dto.getReceive_cel() : dto.getReceive_tel()), "");
				driver.executeScript(String.format("$('#edtAcperNm').val('%s'); ", dto.getReceive_name()), "");

				driver.executeScript("$('#btnAcperBadr').click(); ", "");
				String strExcute = "(()=>{ "
						+ String.format("$('#edtSrchAdrMultiPopGrid').val('%s'); \r\n", addr.get(0))
						+ "$('#btnAdrMultiSrchGrid').click();  \r\n" + " setTimeout( ()=>{ \r\n "
						+ "    if(dsAdrMultiPopGrid.getRowCount() > 0) \r\n" + "    { "
						+ String.format(
								"dsAdrMultiPopGrid.setRowPosition(dsAdrMultiPopGrid.getRows().findIndex((p)=>p.basAreaNo === '%s'  || p.zipcd === '%s')); \r\n ",
								dto.getReceive_zipcode(), dto.getReceive_zipcode())
						+ "$.popCallBack.popupCallBack_fn(dsAdrMultiPopGrid.getRow(dsAdrMultiPopGrid.getRowPosition())); \r\n"
						+ "dsAdrMultiPopGrid.clearData(); \r\n"
						+ "$('body > div:nth-child(34) > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)').trigger('click');"
						+ "}"

						+ "},500);" + "})();";

				driver.executeScript(strExcute, "");

				Thread.sleep(1000);
				driver.executeScript(
						String.format("$('#edtAcperEtcAdr').val('%s'); ",
								dto.getReceive_addr().substring(addr.get(0).length(), dto.getReceive_addr().length())),
						"");
				driver.executeScript(String.format("$('#edtGdsNm').val('%s');  ", dto.getP_product_name()), "");
				driver.executeScript(String.format("$('#edtDlvMsgCont').val('%s');", dto.getDelv_msg()), "");
				driver.executeScript(String.format("$('#maeQty').val('%s'); ", dto.getSale_cnt()), "");

				// 운임구분
				if (dto.getDelivery_method_str().equals("착불")) {
					driver.executeScript("$('#cboFareSctCd').val('02')", "");
				} else {
					driver.executeScript("$('#cboFareSctCd').val('03')", "");
				}
				//
				// 배송비
				driver.executeScript(String.format("$('#maeDlvFare').val('%s')", dto.getDelv_cost()), "");

				driver.executeScript("fnSave()", "");
				Thread.sleep(1000);
				driver.executeScript("$('.msgButton').click()", "");
				Thread.sleep(1500);
				driver.executeScript("$('.msgButton').click()", "");

				express.add(dto);
				Thread.sleep(1000);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			YDMAProgressBar.get().end();
			driver.quit();
		}
		return express;
	}

	// 송장값가져오기
	public List<ShopOrderMstDto> getPickupExpressCrawl(List<ShopOrderMstDto> list, Shell shell) {
		List<ShopOrderMstDto> express = new ArrayList<>();

		try {
			driver = chrome.setFileDown(false).setHeadlessMode(false).getDriver();

			// TODO Auto-generated method stub
			String URL = list.get(0).getUrl();
			// driver.executeScript("window.open();",null );
			WebDriverWait wait = new WebDriverWait(driver, 30);
			ChromeScript.get().get(driver, URL);
			Thread.sleep(1000);
			WebElement elementloginBox = driver.findElement(By.xpath("/html/body/div[3]/div[2]"));
			elementloginBox.findElement(By.xpath("//*[@id=\"principal\"]/input")).sendKeys(list.get(0).getExpid());
			elementloginBox.findElement(By.xpath("//*[@id=\"credential\"]/input")).sendKeys(list.get(0).getPasswd());
			elementloginBox.findElement(By.xpath("//*[@id=\"btn-login\"]")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/header/nav/div[1]")));
			// 집배달
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[3]/header/nav/div[1]/div[5]/i-button"))).click();
			Thread.sleep(1000);
			// 통합관리운송장출력
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html/body/div[3]/header/nav/div[3]/div/div[2]/div[4]/div[1]/ul/li[1]/a"))).click();
			driver.switchTo().frame(driver.findElement(By.id("workframe_21847")));
			for (ShopOrderMstDto dto : list) {
				ChromeScript.get().addScript("$('#cboSrchPrntSctCd').val('');").waitTiem(2000).executeScripter(driver);
				// driver.executeScript("$('#cboSrchCond').val('40');","");
				ChromeScript.get().addScript("$('#cboSrchCond').val('40');").waitTiem(2000).executeScripter(driver);
				// driver.executeScript(String.format("$('#edtSrchCondVal').val('%s');
				// ",output.get(7).equals("")?output.get(8):output.get(7)),"");
				ChromeScript.get()
						.addScript(String.format("$('#edtSrchCondVal').val('%s'); ",
								dto.getReceive_tel().equals("") ? dto.getReceive_cel() : dto.getReceive_tel()))
						.waitTiem(2000).executeScripter(driver);
				// driver.executeScript("fnBtnSearch();","");
				// ChromeScript.get().addScript(String.format("fnBtnSearch();")).waitTiem(2000).executeScripter(driver);
				ChromeScript.get().until(driver, () -> ((ChromeDriver) driver).findElementByCssSelector(
						"body > div.container-fluid.frameArea > div.frameHeader > div.panelBox.searchBox.detailSearch > div.searchBtnGroup > i-button.funcBtn.ico.iconSearch.White.searchBtn"))
						.click();
				// 검색
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div[2]/div[3]/i-button[2]"))).click();
				// 글가지고오기
//				Thread.sleep(1000);
//				//String invoice = (String)driver.executeScript("return grdRsrv.getRowData(0)[8];","");				
//            	String invoice  = (String) ChromeScript.get().returnCallbackScripter("return grdRsrv.getRowData(0)[8];",driver);
//				driver.executeScript("$('#cboSrchPrntSctCd').val('');", "");
//				driver.executeScript("$('#cboSrchCond').val('40');", "");
//				driver.executeScript(String.format("$('#edtSrchCondVal').val('%s'); ",
//						dto.getReceive_tel().equals("") ? dto.getReceive_cel() : dto.getReceive_tel()), "");
//				driver.executeScript("fnBtnSearch();", "");
				// 검색
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div[2]/div[3]/i-button[2]"))).click();
				// 글가지고오기
				Thread.sleep(1000);
				String invoice = (String) ChromeScript.get().returnCallbackScripter("return grdRsrv.getRowData(0)[8];",
						driver);
				// String invoice = (String) driver.executeScript("return
				// grdRsrv.getRowData(0)[8];", "");
				//
				System.out.println(invoice);
				dto.setInvoice_no(invoice);
				express.add(dto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			driver.quit();
		}
		return express;
	}

	// 송장업데이트
	public void invoiceUpdate(List<ShopOrderMstDto> sendResult) throws Exception {
		PreparedStatement pstmt = null;
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "update shopordmst set  DELIVERY_ID = ?, INVOICE_NO = ? where compno = ? and ordseq = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			for (ShopOrderMstDto dto : sendResult) {
				int i = 0;
				pstmt.setString(++i, "롯데택배_" + dto.getDlvid());
				pstmt.setString(++i, dto.getInvoice_no());
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++i, dto.getOrdseq());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}
			System.out.println("[invoiceUpdate]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearBatch();// Batch 초기화
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	public String REQ_METHOD = "POST";
	public String ACCEPT = "application/json";
	public String CONTENT_TYPE = "application/json";

	public String splitMark(String text) {
		String[] split = text.split("-| ");
		String complite = "";
		for (int j = 0; j < split.length; j++) {
			complite += split[j];
			complite = complite.trim();
		}
		return complite;
	}

	public List<List<String>> sendKakao(Shell shell, List<ShopOrderMstDto> list, String template, int len) {

		List<List<String>> responContents = new ArrayList<List<String>>();
		try {
			CompInfoDao comdao = new CompInfoDao();
			List<String> comlist = comdao.getCompNoImage();
			String userid = "kdjsystem";
			String real_profile_key = comlist.get(21);
			List<String> msg = new ArrayList<>();
			List<String> tel = new ArrayList<>();

			String url = "https://alimtalk-api.sweettracker.net/v2/" + real_profile_key + "/sendMessage";
			// String url = "https://dev-alimtalk-api.sweettracker.net/v2/" +
			// test_profile_key + "/sendMessage";

			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();

			AlimTalkChargeDao aldao = new AlimTalkChargeDao();
			List<String> allist = aldao.getAlimTalkChargeList(comlist.get(0));
			List<String> templatecode = new ArrayList<>();
			;

			templatecode = aldao.getTemplateCodeList(comlist.get(0), template);

			// add reuqest header
			httpConnection.setRequestMethod(REQ_METHOD);
			httpConnection.setRequestProperty("Accept", ACCEPT);
			httpConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
			httpConnection.setRequestProperty("userid", userid);
			// Send post request
			httpConnection.setDoOutput(true);

			int seq = Integer.parseInt(allist.get(7));
			StringBuffer payload = new StringBuffer();
			int idx = 1;
			payload.append("[");
			for (int i = 0; i < list.size(); i++) {
				if (template.equals("sms")) {
					String num = String.format("%011d", seq + idx);
					msg.add(i, list.get(i).getMsg());
					payload.append("{"); //
					payload.append("\"msgid\"").append(":\"").append(comlist.get(24) + num).append("\","); // 계정
					// payload.append("\"msgid\"").append(":\"").append(comlist.get(1)+orddt+ordseq+list.get(0)).append("\",");
					// //계정
					payload.append("\"profile_key\"").append(":\"").append(real_profile_key).append("\","); // 발신프로필키

					payload.append("\"template_code\"").append(":\"").append(templatecode.get(2)).append("\","); // 템플릿코드

					// payload.append("\"template_code\"").append(":\"").append("alimtalktest_001").append("\",");
					// //템플릿코드
					payload.append("\"receiver_num\"").append(":\"").append(splitMark(list.get(i).getTelno()))
							.append("\","); // 전화번호
					tel.add(i, list.get(i).getTelno());
					// payload.append("\"receiver_num\"").append(":\"").append("01024426116").append("\",");
					// //전화번호
					payload.append("\"message\"").append(":\"").append(msg.get(i)).append("\","); // 메세지
					payload.append("\"reserved_time\"").append(":\"").append("00000000000000").append("\","); // 즉시전송
					payload.append("\"sms_message\"").append(":\"").append(msg.get(i)).append("\","); // 비즈메세지실패시문자대체메세지
					// payload.append("\"sms_message\"").append(":\"").append("\",");
					// //비즈메세지실패시문자대체메세지
					payload.append("\"sms_title\"").append(":\"").append("\","); //
					if (len < 90) {
						payload.append("\"sms_kind\"").append(":\"").append("S").append("\","); // 발송하지않음
					} else {
						payload.append("\"sms_kind\"").append(":\"").append("L").append("\","); // 발송하지않음
					}

				} else {
					String num = String.format("%011d", seq + idx);
					// true = 상담접수 flase = 상담처리,이카운트
					msg.add(i, list.get(i).getMsg());

					// String msg = "하늘다원 이름으로 직발송 부탁 합니다.\r\n\r\n"+list.get(10)+"
					// "+list.get(7)+"개\r\n"+list.get(2)+"\r\n["+list.get(3)+"]"+list.get(4)+"\r\n연락처1:"+list.get(5)+"\r\n연락처2:"+list.get(6)+
					// "\r\n배송메시지:"+list.get(12);
					payload.append("{"); //
					payload.append("\"msgid\"").append(":\"").append(comlist.get(24) + num).append("\","); // 계정
					// payload.append("\"msgid\"").append(":\"").append(comlist.get(1)+orddt+ordseq+list.get(0)).append("\",");
					// //계정
					payload.append("\"profile_key\"").append(":\"").append(real_profile_key).append("\","); // 발신프로필키
					// payload.append("\"template_code\"").append(":\"").append("kdj002").append("\",");
					// //템플릿코드
					payload.append("\"template_code\"").append(":\"").append(templatecode.get(2)).append("\","); // 템플릿코드
					// payload.append("\"template_code\"").append(":\"").append("alimtalktest_001").append("\",");
					// //템플릿코드
					payload.append("\"receiver_num\"").append(":\"").append(splitMark(list.get(i).getTelno()))
							.append("\","); // 전화번호
					tel.add(i, list.get(i).getTelno());
					// payload.append("\"receiver_num\"").append(":\"").append("01024426116").append("\",");
					// //전화번호
					payload.append("\"message\"").append(":\"").append(msg.get(i)).append("\","); // 메세지
					payload.append("\"reserved_time\"").append(":\"").append("00000000000000").append("\","); // 즉시전송
					payload.append("\"sms_message\"").append(":\"").append(msg.get(i)).append("\","); // 비즈메세지실패시문자대체메세지
					// payload.append("\"sms_message\"").append(":\"").append("\",");
					// //비즈메세지실패시문자대체메세지
					payload.append("\"sms_title\"").append(":\"").append("\","); //

					payload.append("\"sms_kind\"").append(":\"").append("N").append("\","); // 발송하지않음

				}

				payload.append("\"sender_num\"").append(":\"").append("0326751102").append("\","); // 발신번호
				payload.append("\"parcel_company\"").append(":\"").append("08").append("\","); // 택배코드
				payload.append("\"parcel_invoice\"").append(":\"").append("").append("\""); // 송장

				payload.append("} ");

				if (i < list.size() - 1) {
					payload.append(", ");
				}
				payload.append("\n"); // --지워야함
				idx++;
			}
			payload.append("] ");

			System.out.println(payload);

			DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
			out.write(payload.toString().getBytes("UTF-8"));
			out.flush();
			out.close();

			int responseCode = httpConnection.getResponseCode();

			System.out.println("카카오톡: " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
			String inputLine = null;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			httpConnection.disconnect();

			System.out.println(response.toString());

			JsonParser jsonParser = new JsonParser();
			JsonElement jsonObject = (JsonElement) jsonParser.parse(response.toString());
//			String statusCode = jsonObject.getAsJsonObject().get("result").getAsString();
//			if(statusCode.equals("Y")) {
			String result;
			JsonElement dataObject = (JsonElement) jsonObject.getAsJsonArray();
			for (int i = 0; i < dataObject.getAsJsonArray().size(); i++) {
				JsonElement jsonElement = dataObject.getAsJsonArray().get(i);
				result = jsonElement.getAsJsonObject().get("result").getAsString();
				if (result.equals("Y")) {
					// for (int i = 0; i < contents_target.size(); i++) {
					// JsonElement jsonElement = (JsonElement) jsonArray_result.get(i);
					String msgid = jsonElement.getAsJsonObject().get("msgid").getAsString();
					String code = jsonElement.getAsJsonObject().get("code").getAsString();
					String error = jsonElement.getAsJsonObject().get("error").getAsString();
					String kind = jsonElement.getAsJsonObject().get("kind").getAsString();
					String sendtime = jsonElement.getAsJsonObject().get("sendtime").getAsString();
					List<String> listadd = new ArrayList<String>();
					listadd.add(result);
					listadd.add(msgid);
					listadd.add(code);
					listadd.add(error);
					listadd.add(kind);
					listadd.add(tel.get(i));
					listadd.add(msg.get(i));
					listadd.add(sendtime);

					responContents.add(listadd);
					// }
				} else {
					List<String> listadd = new ArrayList<String>();
					listadd.add(jsonElement.getAsJsonObject().get("result").getAsString());
					listadd.add(jsonElement.getAsJsonObject().get("msgid").getAsString());
					listadd.add(jsonElement.getAsJsonObject().get("code").getAsString());
					listadd.add(jsonElement.getAsJsonObject().get("error").getAsString());
					listadd.add(jsonElement.getAsJsonObject().get("kind").getAsString());
					listadd.add(jsonElement.getAsJsonObject().get("sendtime").getAsString());

					responContents.add(listadd);
				}
			}

			// String result = dataObject.getAsJsonArray().get(0).getAsString();
			// JsonArray jsonArray_result = dataObject.getAsJsonArray();

		} catch (Exception e) {
			e.getMessage();
		}
		return responContents;
	}

	// 조회
	public List<List<String>> getCSsearchList(String ordseq, String search, int dt, String fromdt, String todt)
			throws Exception {
		List<List<String>> contents = new ArrayList<List<String>>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(ORDSEQ,''),ifnull(CSSEQ,''),ifnull(CSMEMO,''),ifnull(CSCLOSE,''),ifnull(INSERTDT,''),ifnull(INSERTID,''),ifnull(UPDATEDT,''),ifnull(UPDATEID,'') "
					+ " from shopordcs where ORDSEQ = ? ";
			if (dt == 0) {
				sql += "and insertdt >= ? and insertdt <= ? ";
			} else {
				sql += "and UPDATEDT >= ? and UPDATEDT <= ? ";
			}
			sql += "and csmemo like ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ordseq);
			pstmt.setString(2, fromdt);
			pstmt.setString(3, todt);
			pstmt.setString(4, "%" + search + "%");
			System.out.println("[getCSList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				int columnIndex = 0;
				rowNum++;
				list.add(String.valueOf(rowNum));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

				contents.add(list);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	// 조회
	public List<List<String>> getCSList(String ordseq) throws Exception {
		List<List<String>> contents = new ArrayList<List<String>>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(ORDSEQ,''),ifnull(CSSEQ,''),ifnull(CSMEMO,''),ifnull(CSCLOSE,''),ifnull(INSERTDT,''),ifnull(INSERTID,''),ifnull(UPDATEDT,''),ifnull(UPDATEID,'') "
					+ " from shopordcs where ORDSEQ = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ordseq);

			System.out.println("[getCSList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				int columnIndex = 0;
				rowNum++;
				list.add(String.valueOf(rowNum));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

				contents.add(list);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	// 삭제하기
	public int deleteCSList(List<List<String>> contents) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		int result = 0;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		try {

			String sql = "delete from shopordcs where ordseq = ? and csseq = ? ";
			sql = sql.toUpperCase();
			connection = DBCPInit.getInstance().getConnection();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			for (List<String> list : contents) {
				int i = 0;
				pstmt.setString(++i, list.get(1));
				pstmt.setString(++i, list.get(2));

				System.out.println("[ProductErrordataDelete]" + pstmt.toString());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}

			pstmt.executeBatch();
			pstmt.clearBatch();// Batch 초기화
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return result;

	}

	// 처리완료
	public void updateCsClose(List<List<String>> contents) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		try {

			String sql = "update shopordcs set csclose = 'Y' where ordseq = ? and csseq = ? ";
			sql = sql.toUpperCase();
			connection = DBCPInit.getInstance().getConnection();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			for (List<String> list : contents) {
				int i = 0;
				pstmt.setString(++i, list.get(1));
				pstmt.setString(++i, list.get(2));

				System.out.println("[ProductErrordataDelete]" + pstmt.toString());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}

			pstmt.executeBatch();
			pstmt.clearBatch();// Batch 초기화
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	// 처리완료 철회
	public void updateCsCloseback(List<List<String>> contents) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		try {

			String sql = "update shopordcs set csclose = 'N' where ordseq = ? and csseq = ? ";
			sql = sql.toUpperCase();
			connection = DBCPInit.getInstance().getConnection();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			for (List<String> list : contents) {
				int i = 0;
				pstmt.setString(++i, list.get(1));
				pstmt.setString(++i, list.get(2));

				System.out.println("[ProductErrordataDelete]" + pstmt.toString());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}

			pstmt.executeBatch();
			pstmt.clearBatch();// Batch 초기화
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	public List<ShopOrderMstDto> statisticalInfo(String startdt, String enddt, String statis) throws Exception {
		List<ShopOrderMstDto> contents = new ArrayList<ShopOrderMstDto>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " CALL YWM_SALES_STATISTICS(?, ?, ?, ?) ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, startdt);
			pstmt.setString(3, enddt);
			pstmt.setString(4, statis);
			System.out.println("[statisticalInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ShopOrderMstDto dto = new ShopOrderMstDto();
				int columnIndex = 0;
				if (statis.equals("1")) {
					dto.setCompayny_goods_cd(rs.getString(++columnIndex));
					dto.setP_product_name(rs.getString(++columnIndex));
				} else if (statis.equals("2") || statis.equals("3")) {
					dto.setReg_date(rs.getString(++columnIndex));
				} else if (statis.equals("4")) {
					dto.setShopnm(rs.getString(++columnIndex));
				} else if (statis.equals("5")) {
					dto.setUser_name(rs.getString(++columnIndex));
				}
				dto.setOrd_cnt(rs.getString(++columnIndex));
				dto.setSale_cnt(rs.getString(++columnIndex));
				dto.setSale_cost(rs.getString(++columnIndex));
				dto.setRet_cnt(rs.getString(++columnIndex));
				dto.setRet_cost(rs.getString(++columnIndex));
				dto.setCnc_cnt(rs.getString(++columnIndex));
				dto.setCnc_cost(rs.getString(++columnIndex));
				dto.setGoods_cost(rs.getString(++columnIndex));
				dto.setMall_won_cost(rs.getString(++columnIndex));
				dto.setDelv_cost(rs.getString(++columnIndex));
				dto.setProfit(rs.getString(++columnIndex));

				contents.add(dto);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public ShopOrderMstDto getWonCost(ShopOrderMstDto dto) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(GOODS_COST,'0') from shopprodinfo where COMPAYNY_GOODS_CD = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, dto.getCompayny_goods_cd());
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			int rowNum = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				int columnIndex = 0;
				dto.setWon_cost(rs.getString(++columnIndex));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	public List<ShopOrderMstDto> getShopOrderOneList(List<List<String>> filteredList) throws Exception {
		List<ShopOrderMstDto> list = new ArrayList<ShopOrderMstDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = DBCPInit.getInstance().getConnection();

			for(int i = 0; i<filteredList.size();i++) {
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT \r\n");
				sql.append("IFNULL(M.ORDSEQ,  '' ) AS ORDSEQ,  \r\n");// 일련번호
				sql.append("IFNULL(M.COMPNO,  '' ) AS COMPNO,  \r\n");// 밴더코드
				sql.append("IFNULL(M.REG_DATE,  '' ) AS REG_DATE,  \r\n");// 수집일자
				sql.append("IFNULL(M.ORDER_ID,  '' ) AS ORDER_ID,  \r\n");// 주문번호(쇼핑몰)
				sql.append("IFNULL(M.SHOPID,  '' ) AS SHOPID,  \r\n");// 쇼핑몰명
				sql.append("IFNULL(M.SHOP_USERID,  '' ) AS SHOP_USERID,  \r\n");// 쇼핑몰ID
				sql.append("IFNULL(M.ORDER_STATUS,  '' ) AS ORDER_STATUS,  \r\n");// 주문상태
				sql.append("IFNULL(M.USER_ID,  '' ) AS USER_ID,  \r\n");// 주문자ID
				sql.append("IFNULL(M.USER_NAME,  '' ) AS USER_NAME,  \r\n");// 주문자명
				sql.append("IFNULL(M.USER_TEL,  '' ) AS USER_TEL,  \r\n");// 주문자전화번호
				sql.append("IFNULL(M.USER_CEL,  '' ) AS USER_CEL,  \r\n");// 주문자핸드폰번호
				sql.append("IFNULL(M.USER_EMAIL,  '' ) AS USER_EMAIL,  \r\n");// 주문자이메일주소
				sql.append("IFNULL(M.RECEIVE_TEL,  '' ) AS RECEIVE_TEL,  \r\n");// 수취인전화번호
				sql.append("IFNULL(M.RECEIVE_CEL,  '' ) AS RECEIVE_CEL,  \r\n");// 수취인핸드폰번호
				sql.append("IFNULL(M.RECEIVE_EMAIL,  '' ) AS RECEIVE_EMAIL,  \r\n");// 수취인이메일주소
				sql.append("IFNULL(M.DELV_MSG,  '' ) AS DELV_MSG,  \r\n");// 배송메세지
				sql.append("IFNULL(M.RECEIVE_NAME,  '' ) AS RECEIVE_NAME,  \r\n");// 수취인명
				sql.append("IFNULL(M.RECEIVE_ZIPCODE,  '' ) AS RECEIVE_ZIPCODE,  \r\n");// 수취인우편번호
				sql.append("IFNULL(M.RECEIVE_ADDR,  '' ) AS RECEIVE_ADDR,  \r\n");// 수취인주소
				sql.append("IFNULL(M.TOTAL_COST,  '' ) AS TOTAL_COST,  \r\n");// 주문금액
				sql.append("IFNULL(M.PAY_COST,  '' ) AS PAY_COST,  \r\n");// 결제금액
				sql.append("IFNULL(M.ORDER_DATE,  '' ) AS ORDER_DATE,  \r\n");// 주문일자
				sql.append("IFNULL(M.PARTNER_ID,  '' ) AS PARTNER_ID,  \r\n");// 매입처명
				sql.append("IFNULL(M.DPARTNER_ID,  '' ) AS DPARTNER_ID,  \r\n");// 물류처명
				sql.append("IFNULL(M.MALL_PRODUCT_ID,  '' ) AS MALL_PRODUCT_ID,  \r\n");// 상품코드(쇼핑몰)
				sql.append("IFNULL(M.PRODUCT_ID,  '' ) AS PRODUCT_ID,  \r\n");// 품번코드(엠링크)
				sql.append("IFNULL(M.SKU_ID,  '' ) AS SKU_ID,  \r\n");// 단품코드(엠링크)
				sql.append("IFNULL(M.P_PRODUCT_NAME,  '' ) AS P_PRODUCT_NAME,  \r\n");// 상품명(확정)
				sql.append("IFNULL(M.P_SKU_VALUE,  '' ) AS P_SKU_VALUE,  \r\n");// 옵션(확정)
				sql.append("IFNULL(M.PRODUCT_NAME,  '' ) AS PRODUCT_NAME,  \r\n");// 상품명(수집)
				sql.append("IFNULL(M.SALE_COST,  '' ) AS SALE_COST,  \r\n");// 판매가(수집)
				sql.append("IFNULL(M.MALL_WON_COST,  '0' ) AS MALL_WON_COST,  \r\n");// 공급단가
				sql.append("IFNULL(M.WON_COST,  '0' ) AS WON_COST,  \r\n");// 원가
				sql.append("IFNULL(M.SKU_VALUE,  '' ) AS SKU_VALUE,  \r\n");// 옵션(수집)
				sql.append("IFNULL(M.SALE_CNT,  '' ) AS SALE_CNT,  \r\n");// 수량
				sql.append("IFNULL(M.DELIVERY_METHOD_STR,  '' ) AS DELIVERY_METHOD_STR,  \r\n");// 배송구분
				sql.append("IFNULL(M.DELV_COST,  '' ) AS DELV_COST,  \r\n");// 배송비(수집)
				sql.append("IFNULL(M.COMPAYNY_GOODS_CD,  '' ) AS COMPAYNY_GOODS_CD,  \r\n");// 자체상품코드
				sql.append("IFNULL(M.SKU_ALIAS,  '' ) AS SKU_ALIAS,  \r\n");// 옵션별칭
				sql.append("IFNULL(M.BOX_EA,  '' ) AS BOX_EA,  \r\n");// EA(상품)
				sql.append("IFNULL(M.JUNG_CHK_YN,  '' ) AS JUNG_CHK_YN,  \r\n");// 정산대조확인여부
				sql.append("IFNULL(M.MALL_ORDER_SEQ,  '' ) AS MALL_ORDER_SEQ,  \r\n");// 주문순번
				sql.append("IFNULL(M.MALL_ORDER_ID,  '' ) AS MALL_ORDER_ID,  \r\n");// 부주문번호
				sql.append("IFNULL(M.ETC_FIELD3,  '' ) AS ETC_FIELD3,  \r\n");// 수정된 수집옵션명
				sql.append("IFNULL(M.ORDER_GUBUN,  '' ) AS ORDER_GUBUN,  \r\n");// 주문구분
				sql.append("IFNULL(M.P_EA,  '' ) AS P_EA,  \r\n");// EA(확정)
				sql.append("IFNULL(M.ORD_FIELD2,  '' ) AS ORD_FIELD2,  \r\n");// 세트분리주문구분
				sql.append("IFNULL(M.COPY_IDX,  '' ) AS COPY_IDX,  \r\n");// 원주문번호(사방넷)
				sql.append("IFNULL(M.GOODS_NM_PR,  '' ) AS GOODS_NM_PR,  \r\n");// 출력상품명
				sql.append("IFNULL(M.GOODS_KEYWORD,  '' ) AS GOODS_KEYWORD,  \r\n");// 상품약어
				sql.append("IFNULL(M.ORD_CONFIRM_DATE,  '' ) AS ORD_CONFIRM_DATE,  \r\n");// 주문 확인일자
				sql.append("IFNULL(M.RTN_DT,  '' ) AS RTN_DT,  \r\n");// 반품 완료일자
				sql.append("IFNULL(M.CHNG_DT,  '' ) AS CHNG_DT,  \r\n");// 교환 완료일자
				sql.append("IFNULL(M.DELIVERY_CONFIRM_DATE,  '' ) AS DELIVERY_CONFIRM_DATE,  \r\n");// 출고 완료일자
				sql.append("IFNULL(M.CANCEL_DT,  '' ) AS CANCEL_DT,  \r\n");// 취소 완료일자
				sql.append("IFNULL(M.CLASS_CD1,  '' ) AS CLASS_CD1,  \r\n");// 대분류코드
				sql.append("IFNULL(M.CLASS_CD2,  '' ) AS CLASS_CD2,  \r\n");// 중분류코드
				sql.append("IFNULL(M.CLASS_CD3,  '' ) AS CLASS_CD3,  \r\n");// 소분류코드
				sql.append("IFNULL(M.CLASS_CD4,  '' ) AS CLASS_CD4,  \r\n");// 세분류코드
				sql.append("IFNULL(M.BRAND_NM,  '' ) AS BRAND_NM,  \r\n");// 브랜드명
				sql.append("IFNULL(M.DELIVERY_ID,  '' ) AS DELIVERY_ID,  \r\n");// 택배사코드
				sql.append("IFNULL(M.INVOICE_NO,  '' ) AS INVOICE_NO,  \r\n");// 송장번호
				sql.append("IFNULL(M.HOPE_DELV_DATE,  '' ) AS HOPE_DELV_DATE,  \r\n");// 배송희망일자
				sql.append("IFNULL(M.FLD_DSP,  '' ) AS FLD_DSP,  \r\n");// 주문엑셀용
				sql.append("IFNULL(M.INV_SEND_MSG,  '' ) AS INV_SEND_MSG,  \r\n");// 운송장 전송 결과 메시지
				sql.append("IFNULL(M.MODEL_NO,  '' ) AS MODEL_NO,  \r\n");// 모델NO
				sql.append("IFNULL(M.SET_GUBUN,  '' ) AS SET_GUBUN,  \r\n");// 상품구분
				sql.append("IFNULL(M.ETC_MSG,  '' ) AS ETC_MSG,  \r\n");// 기타메세지
				sql.append("IFNULL(M.DELV_MSG1,  '' ) AS DELV_MSG1,  \r\n");// 배송메세지
				sql.append("IFNULL(M.MUL_DELV_MSG,  '' ) AS MUL_DELV_MSG,  \r\n");// 물류메세지
				sql.append("IFNULL(M.BARCODE,  '' ) AS BARCODE,  \r\n");// 바코드
				sql.append("IFNULL(M.INV_SEND_DM,  '' ) AS INV_SEND_DM,  \r\n");// 송장전송일자
				sql.append("IFNULL(M.DELIVERY_METHOD_STR2,  '' ) AS DELIVERY_METHOD_STR2,  \r\n");// 배송구분(배송비반영)
				sql.append("IFNULL(M.ORDER_ETC_1,  '' ) AS ORDER_ETC_1,  \r\n");// 자사몰필드
				sql.append("IFNULL(M.ORDER_ETC_2,  '' ) AS ORDER_ETC_2,  \r\n");// 임시필드
				sql.append("IFNULL(M.ORDER_ETC_3,  '' ) AS ORDER_ETC_3,  \r\n");// 임시필드
				sql.append("IFNULL(M.ORDER_ETC_4,  '' ) AS ORDER_ETC_4,  \r\n");// 임시필드
				sql.append("IFNULL(M.ORDER_ETC_5,  '' ) AS ORDER_ETC_5,  \r\n");// 임시필드
				sql.append("IFNULL(M.ORDER_ETC_6,  '' ) AS ORDER_ETC_6,  \r\n");// 임시필드
				sql.append("IFNULL(M.ORDER_ETC_7,  '' ) AS ORDER_ETC_7,  \r\n");// 임시필드
				sql.append("IFNULL(M.ORDER_ETC_8,  '' ) AS ORDER_ETC_8,  \r\n");// 임시필드
				sql.append("IFNULL(M.ORDER_ETC_9,  '' ) AS ORDER_ETC_9,  \r\n");// 임시필드
				sql.append("IFNULL(M.ORDER_ETC_10,  '' ) AS ORDER_ETC_10,  \r\n");// 임시필드
				sql.append("IFNULL(M.ORDER_ETC_11,  '' ) AS ORDER_ETC_11,  \r\n");// 임시필드
				sql.append("IFNULL(M.ORDER_ETC_12,  '' ) AS ORDER_ETC_12,  \r\n");// 임시필드
				sql.append("IFNULL(M.ORDER_ETC_13,  '' ) AS ORDER_ETC_13,  \r\n");// 임시필드
				sql.append("IFNULL(M.ORDER_ETC_14,  '' ) AS ORDER_ETC_14,  \r\n");// 임시필드
				sql.append("IFNULL(M.LABEL,  '' ) AS LABEL,  \r\n");// 라벨
				sql.append("IFNULL(D.APIKEY,  '' ) AS APIKEY,  \r\n");// 라벨
				sql.append("IFNULL(D.PASSWORD,  '' ) AS PASSWORD,  \r\n");// 라벨
				sql.append("IFNULL(D.AUTHKEY1,  '' ) AS AUTHKEY1,  \r\n");// 라벨
				sql.append("IFNULL(D.AUTHKEY2,  '' ) AS AUTHKEY2, \r\n");// 라벨
				sql.append("IFNULL(D.NICKNM2 ,'') AS VENDORID  ,    \r\n");
				sql.append("IFNULL(M.RECEIPTID ,'') AS RECEIPTID,      \r\n");
				sql.append("IFNULL(M.EXCHANGEID  ,'') AS EXCHANGEID  ,    \r\n");
				sql.append("IFNULL(M.DOSINORDCD  ,'') AS DOSINORDCD  ,    \r\n");// 도신코드
				sql.append("(SELECT COUNT(ORDSEQ) FROM SHOPORDCS WHERE ORDSEQ =M.ORDSEQ AND CSCLOSE='Y' ) AS CSCLOSE \r\n");
				sql.append(" FROM shopordmst AS M \r\n");
				sql.append(" INNER JOIN shopdtl AS D \r\n");
				sql.append(" ON M.COMPNO = D.COMPNO \r\n");
				sql.append(" AND M.SHOPID = D.SHOPCD \r\n");
				sql.append(" AND M.SHOP_USERID = D.SHOPPINGID AND M.AUTHKEY1=D.AUTHKEY1 \r\n");
				sql.append(" WHERE M.COMPNO = ?  and ORDSEQ = ? ");


				pstmt = connection.prepareStatement(sql.toString().toUpperCase());
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, filteredList.get(i).get(0));

				rs = pstmt.executeQuery();

				Integer rowNum = 0;

				while (rs.next()) {
					ShopOrderMstDto dto = new ShopOrderMstDto();
					++rowNum;
					dto.setNo(rowNum.toString());
					dto.setReg_date(rs.getString("REG_DATE"));// 수집일자
					dto.setOrder_date(rs.getString("ORDER_DATE"));// 주문일자
					dto.setOrder_status(rs.getString("ORDER_STATUS"));// 주문상태
					dto.setShopid(rs.getString("SHOPID"));// 쇼핑몰명
					dto.setShop_userid(rs.getString("SHOP_USERID"));// 쇼핑몰ID
					dto.setReceive_name(rs.getString("RECEIVE_NAME"));// 수령자
					dto.setMall_product_id(rs.getString("MALL_PRODUCT_ID"));// 상품코드(쇼핑몰)
					dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD"));// 자체상품코드
					dto.setP_product_name(rs.getString("P_PRODUCT_NAME"));// 상품명(확정)
					dto.setP_sku_value(rs.getString("P_SKU_VALUE"));// 옵션(확정)
					dto.setSale_cnt(rs.getString("SALE_CNT"));// 수량
					dto.setWon_cost(rs.getString("WON_COST"));// 원가
					dto.setMall_won_cost(rs.getString("MALL_WON_COST"));// 공급단가
					dto.setSale_cost(rs.getString("SALE_COST"));// 판매가(수집)
					dto.setDelv_cost(rs.getString("DELV_COST"));// 배송비(수집)
					dto.setDelivery_method_str2(rs.getString("DELIVERY_METHOD_STR2"));// 배송구분(배송비반영)
					dto.setDelv_msg(rs.getString("DELV_MSG"));// 배송메세지
					dto.setDelivery_id(rs.getString("DELIVERY_ID"));// 택배사코드 (배송사)
					dto.setInvoice_no(rs.getString("INVOICE_NO"));// 송장번호
					dto.setLabel(rs.getString("LABEL"));// 라벨부

					// --------------------- 아직 안쓰는 필드들 ----------------------------------//
					dto.setProduct_id(rs.getString("PRODUCT_ID"));// 품번코드(엠링크)
					dto.setOrdseq(rs.getString("ORDSEQ"));// 일련번호
					dto.setCompno(rs.getString("COMPNO"));// 밴더코드
					dto.setOrder_id(rs.getString("ORDER_ID"));// 주문번호(쇼핑몰)
					dto.setUser_id(rs.getString("USER_ID"));// 주문자ID
					dto.setUser_name(rs.getString("USER_NAME"));// 주문자명
					dto.setUser_tel(rs.getString("USER_TEL"));// 주문자전화번호
					dto.setUser_cel(rs.getString("USER_CEL"));// 주문자핸드폰번호
					dto.setUser_email(rs.getString("USER_EMAIL"));// 주문자이메일주소
					dto.setReceive_tel(rs.getString("RECEIVE_TEL"));// 수취인전화번호
					dto.setReceive_cel(rs.getString("RECEIVE_CEL"));// 수취인핸드폰번호
					dto.setReceive_email(rs.getString("RECEIVE_EMAIL"));// 수취인이메일주소
					dto.setReceive_zipcode(rs.getString("RECEIVE_ZIPCODE"));// 수취인우편번호
					dto.setReceive_addr(rs.getString("RECEIVE_ADDR"));// 수취인주소
					dto.setTotal_cost(rs.getString("TOTAL_COST"));// 주문금액
					dto.setPay_cost(rs.getString("PAY_COST"));// 결제금액
					dto.setPartner_id(rs.getString("PARTNER_ID"));// 매입처명
					dto.setDpartner_id(rs.getString("DPARTNER_ID"));// 물류처명
					dto.setSku_id(rs.getString("SKU_ID"));// 단품코드(엠링크)
					dto.setProduct_name(rs.getString("PRODUCT_NAME"));// 상품명(수집)
					dto.setSku_value(rs.getString("SKU_VALUE"));// 옵션(수집)
					dto.setDelivery_method_str(rs.getString("DELIVERY_METHOD_STR"));// 배송구분

					dto.setSku_alias(rs.getString("SKU_ALIAS"));// 옵션별칭
					dto.setBox_ea(rs.getString("BOX_EA"));// EA(상품)
					dto.setJung_chk_yn(rs.getString("JUNG_CHK_YN"));// 정산대조확인여부
					dto.setMall_order_seq(rs.getString("MALL_ORDER_SEQ"));// 주문순번
					dto.setMall_order_id(rs.getString("MALL_ORDER_ID"));// 부주문번호
					dto.setEtc_field3(rs.getString("ETC_FIELD3"));// 수정된 수집옵션명
					dto.setOrder_gubun(rs.getString("ORDER_GUBUN"));// 주문구분
					dto.setP_ea(rs.getString("P_EA"));// EA(확정)
					dto.setOrd_field2(rs.getString("ORD_FIELD2"));// 세트분리주문구분
					dto.setCopy_idx(rs.getString("COPY_IDX"));// 원주문번호(사방넷)
					dto.setGoods_nm_pr(rs.getString("GOODS_NM_PR"));// 출력상품명
					dto.setGoods_keyword(rs.getString("GOODS_KEYWORD"));// 상품약어
					dto.setOrd_confirm_date(rs.getString("ORD_CONFIRM_DATE"));// 주문 확인일자
					dto.setRtn_dt(rs.getString("RTN_DT"));// 반품 완료일자
					dto.setChng_dt(rs.getString("CHNG_DT"));// 교환 완료일자
					dto.setOrd_confirm_date(rs.getString("DELIVERY_CONFIRM_DATE"));// 출고 완료일자
					dto.setCancel_dt(rs.getString("CANCEL_DT"));// 취소 완료일자
					dto.setClass_cd1(rs.getString("CLASS_CD1"));// 대분류코드
					dto.setClass_cd2(rs.getString("CLASS_CD2"));// 중분류코드
					dto.setClass_cd3(rs.getString("CLASS_CD3"));// 소분류코드
					dto.setClass_cd4(rs.getString("CLASS_CD4"));// 세분류코드
					dto.setBrand_nm(rs.getString("BRAND_NM"));// 브랜드명

					dto.setHope_delv_date(rs.getString("HOPE_DELV_DATE"));// 배송희망일자
					dto.setFld_dsp(rs.getString("FLD_DSP"));// 주문엑셀용
					dto.setInv_send_msg(rs.getString("INV_SEND_MSG"));// 운송장 전송 결과 메시지
					dto.setModel_no(rs.getString("MODEL_NO"));// 모델NO
					dto.setSet_gubun(rs.getString("SET_GUBUN"));// 상품구분
					dto.setEtc_msg(rs.getString("ETC_MSG"));// 기타메세지
					dto.setDelv_msg1(rs.getString("DELV_MSG1"));// 배송메세지
					dto.setMul_delv_msg(rs.getString("MUL_DELV_MSG"));// 물류메세지
					dto.setBarcode(rs.getString("BARCODE"));// 바코드
					dto.setInv_send_dm(rs.getString("INV_SEND_DM"));// 송장전송일자

					dto.setOrder_etc_1(rs.getString("ORDER_ETC_1"));// 자사몰필드
					dto.setOrder_etc_2(rs.getString("ORDER_ETC_2"));// 임시필드
					dto.setOrder_etc_3(rs.getString("ORDER_ETC_3"));// 임시필드
					dto.setOrder_etc_4(rs.getString("ORDER_ETC_4"));// 임시필드
					dto.setOrder_etc_5(rs.getString("ORDER_ETC_5"));// 임시필드
					dto.setOrder_etc_6(rs.getString("ORDER_ETC_6"));// 임시필드
					dto.setOrder_etc_7(rs.getString("ORDER_ETC_7"));// 임시필드
					dto.setOrder_etc_8(rs.getString("ORDER_ETC_8"));// 임시필드
					dto.setOrder_etc_9(rs.getString("ORDER_ETC_9"));// 임시필드
					dto.setOrder_etc_10(rs.getString("ORDER_ETC_10"));// 임시필드
					dto.setOrder_etc_11(rs.getString("ORDER_ETC_11"));// 임시필드
					dto.setOrder_etc_12(rs.getString("ORDER_ETC_12"));// 임시필드
					dto.setOrder_etc_13(rs.getString("ORDER_ETC_13"));// 임시필드
					dto.setOrder_etc_14(rs.getString("ORDER_ETC_14"));// 임시필드
					dto.setShopPw(rs.getString("PASSWORD"));
					dto.setApikey(rs.getString("APIKEY"));

					dto.setAuthkey1(rs.getString("AUTHKEY1"));
					dto.setAuthKey2(rs.getString("AUTHKEY2"));
					dto.setVendorId(rs.getString("VENDORID"));

					dto.setReceiptid(rs.getString("RECEIPTID")); // 반품 접수 아이디..
					dto.setExchangeid(rs.getString("EXCHANGEID")); // 교환 접수 아이디..
					dto.setDosinOrder_code(rs.getString("DOSINORDCD")); // 도신코드
					dto.setCsClose(rs.getString("CSCLOSE"));
					list.add(dto);
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return list;
		
	}

}

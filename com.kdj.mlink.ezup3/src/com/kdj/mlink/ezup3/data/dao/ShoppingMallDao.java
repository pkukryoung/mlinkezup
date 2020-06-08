package com.kdj.mlink.ezup3.data.dao;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.coupang.openapi.sdk.Hmac;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kdj.mlink.ezup3.common.FtpUtil;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.xml.YDWMXmlManager;
import com.kdj.mlink.ezup3.shop.common.OrderStatus;
import com.kdj.mlink.ezup3.shop.dao.ShopProduct11stAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductAuctionAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductGmarketAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductInterParkAdditionDto;

public class ShoppingMallDao {
	// 거래여부전체가지고오기
	public List<List<String>> getAllDealTradeList() throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(b.SHOPCD,''),ifnull(b.SHOPNM,''),ifnull(b.CUSTNM,''),ifnull(b.PRODREGIS,''),ifnull(b.PRODMODIFY,''),ifnull(b.PRODSOLDOUT,''),ifnull(b.STOCKSEND,''),ifnull(b.ORDERCOLLECT,''),"
					+ " ifnull(b.INVOICSEND,''),ifnull(b.CLAIMCOLLECT,''),ifnull(b.QUESTION,''),ifnull(b.MARKETCATEG,''),ifnull(a.IDNUM,''),ifnull(a.DEALTRADE,''),ifnull(b.shopurl,'') "
					+ "from shopmst a join shopinfo b on a.shopcd = b.shopcd where a.compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getAllDealTradeList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<>();
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

	public List<List<String>> getDealTradeList(String deal) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(a.SHOPCD,''),ifnull(b.SHOPNM,''),ifnull(b.CUSTNM,''),ifnull(b.PRODREGIS,''),ifnull(b.PRODMODIFY,''),ifnull(b.PRODSOLDOUT,''),ifnull(b.STOCKSEND,''),ifnull(b.ORDERCOLLECT,''),"
					+ " ifnull(b.INVOICSEND,''),ifnull(b.CLAIMCOLLECT,''),ifnull(b.QUESTION,''),ifnull(b.MARKETCATEG,''),ifnull(a.IDNUM,''),ifnull(a.DEALTRADE,''), ifnull(b.shopurl,'') "
					+ "from shopmst a join shopinfo b on a.shopcd = b.shopcd where a.DEALTRADE = ? and a.compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, deal);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getDealTradeList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<>();
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

	// 디테일 인설트
	public int ShoppingMallDetail(String shopcode, String shoppingid, String password, String nicknm2, String nicknm1,
			String authkey1, String authkey2, String orderexcel1, String orderexcel2, String orderexcel3, String apikey,
			int num, String dealtrade, String supply, String supplyprc, String prccomp) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql = "insert into shopdtl(compno, shopcd,shopseq,shoppingid, password, nicknm2, nicknm1,authkey1, authkey2, orderexcel1, orderexcel2,orderexcel3, apikey, SUPPSTDITEM,SUPPSTDRATI,"
					+ " dealtread,SHOPCOMPAR ) " + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?)";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, shopcode);
			pstmt.setInt(++rowIdx, num);
			pstmt.setString(++rowIdx, shoppingid);
			pstmt.setString(++rowIdx, password);
			pstmt.setString(++rowIdx, nicknm2);
			pstmt.setString(++rowIdx, nicknm1);
			pstmt.setString(++rowIdx, authkey1);
			pstmt.setString(++rowIdx, authkey2);
			pstmt.setString(++rowIdx, orderexcel1);
			pstmt.setString(++rowIdx, orderexcel2);
			pstmt.setString(++rowIdx, orderexcel3);
			pstmt.setString(++rowIdx, apikey);
			pstmt.setString(++rowIdx, supply);
			pstmt.setString(++rowIdx, supplyprc);
			pstmt.setString(++rowIdx, dealtrade);
			pstmt.setString(++rowIdx, prccomp);

			System.out.println("[ShoppingMallDetail]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	// 쇼핑몰코드로 1개 가지고 오기
	public List<String> getShopcodeList(String shopcode) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(a.SHOPCD,''),ifnull(b.SHOPNM,''),ifnull(b.CUSTNM,''),ifnull(b.PRODREGIS,''),ifnull(b.PRODMODIFY,''),ifnull(b.PRODSOLDOUT,''),ifnull(b.STOCKSEND,''),ifnull(b.ORDERCOLLECT,''),"
					+ " ifnull(b.INVOICSEND,''),ifnull(b.CLAIMCOLLECT,''),ifnull(b.QUESTION,''),ifnull(b.MARKETCATEG,''),ifnull(a.IDNUM,'0'),ifnull(a.DEALTRADE,'') "
					+ " from shopmst a join shopinfo b on a.shopcd = b.shopcd where a.shopcd = ? and a.compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, shopcode);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getShopcodeList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 0;
			while (rs.next()) {
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
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	// 디테일에서저장할때 인서트후에 아이디수랑 업데이트
	public int ShoppingMallManagerUpdate(String idnum, String dealtrade, String shopcode, boolean isNew)
			throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();
			String sql = "update shopmst set IDNUM=?, DEALTRADE=? where SHOPCD=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int rowIdx = 0;

			pstmt.setString(++rowIdx, idnum);
			pstmt.setString(++rowIdx, dealtrade);
			pstmt.setString(++rowIdx, shopcode);
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[ShoppingMallDetail]" + pstmt.toString());

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
	public List<ShopOrderDto> getShopOrderList(String startdt, String enddt, String searchText, String search1,
			String search2, String search3) throws Exception {
		List<ShopOrderDto> list = new ArrayList<>();
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
			sql.append("IFNULL(D.PASSWORD,  '' ) AS PASSWORD  \r\n");// 라벨
			sql.append(" FROM shopordmst AS M \r\n");
			sql.append(" INNER JOIN shopdtl AS D \r\n");
			sql.append(" ON M.COMPNO = D.COMPNO \r\n");
			sql.append(" AND M.SHOPID = D.SHOPCD \r\n");
			sql.append(" AND M.SHOP_USERID = D.SHOPPINGID \r\n");
			sql.append(" WHERE M.COMPNO = ?  ");

			if (!searchText.trim().equals("") && !search2.trim().equals("")) // 검색 조건이 없거나 검색조건 2 전체가 아닌경우 search3 날짜로만
																				// 검색한다..
			{
				if (search1.equals("부분일치")) {
					sql.append(String.format(" AND M.%s LIKE '%s%s%s' ", search2, "%", searchText, "%"));
				} else { // 완전일치..
					sql.append(String.format(" AND M.%s = '%s' ", search2, searchText));
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
				ShopOrderDto dto = new ShopOrderDto();
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
	/// 쇼핑몰 디테일 리스트
	/// </summary>
	/// <returns></returns>
	public List<ShoppingMallDetailDto> ShoppingMallDetailList() throws Exception {
		List<ShoppingMallDetailDto> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT IFNULL(m.COMPNO, '') AS COMPNO, \r\n");
			sql.append("IFNULL(m.SHOPCD,'')  AS SHOPCD, \r\n");
			sql.append("IFNULL(b.SHOPNM,'')  AS SHOPNM, \r\n");

			// sql.append("IFNULL(d.SHOPID,'') AS SHOPID, \r\n");
			sql.append("IFNULL(d.SHOPPINGID,'') AS SHOPPINGID, \r\n ");
			sql.append("IFNULL(D.NICKNM1,D.NICKNM2) AS NICKNM, \r\n");
			sql.append("IFNULL(D.APIKEY,'') AS APIKEY, \r\n");
			sql.append("IFNULL(D.DEALTREAD,'') AS DEALTREAD, \r\n");
			sql.append("IFNULL(d.PASSWORD, '') AS PASSWORD, \r\n");
			sql.append("IFNULL(d.AUTHKEY1, '') AS AUTHKEY1, \r\n");
			sql.append("IFNULL(d.AUTHKEY2, '') AS AUTHKEY2 \r\n");
			// sql.append("IFNULL(d.APIKEY, '') AS APIKEY \r\n");

			sql.append(" FROM shopmst AS m");
			sql.append(" JOIN shopdtl AS d");
			sql.append(" ON m.COMPNO = d.COMPNO AND m.SHOPCD = d.SHOPCD join shopinfo b on m.shopcd = b.shopcd ");
			sql.append(" WHERE m.COMPNO =? \r\n");
			sql.append(" ORDER BY m.SHOPCD, d.SHOPSEQ");

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[ShoppingMallDetailDto]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ShoppingMallDetailDto dto = new ShoppingMallDetailDto();
				dto.setCompno(rs.getString("COMPNO"));
				dto.setShopcd(rs.getString("SHOPCD"));
				dto.setShoppingid(rs.getString("SHOPPINGID"));
				dto.setShopnm(rs.getString("SHOPNM"));
				dto.setNicknm1(rs.getString("NICKNM"));
				dto.setApikey(rs.getString("APIKEY"));
				dto.setDealtread(rs.getString("DEALTREAD"));
				dto.setPassword(rs.getString("PASSWORD"));
				dto.setAuthkey1(rs.getString("AUTHKEY1"));
				dto.setAuthkey2(rs.getString("AUTHKEY2"));
				// dto.setPassword(rs.getString("PASSWORD"));
				// dto.setApikey(rs.getString("APIKEY"));
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

	// 디테일아이디별가지고 오기
	public List<List<String>> ShoppingMallDetailList(String shopcode) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(shopseq,'0'),ifnull(SHOPCD,''),ifnull(SHOPPINGID,''),ifnull(PASSWORD,''),ifnull(NICKNM2,''),ifnull(NICKNM1,''),ifnull(AUTHKEY1,''),ifnull(AUTHKEY2,''),ifnull(ORDEREXCEL1,''),ifnull(ORDEREXCEL2,''),"
					+ " ifnull(ORDEREXCEL3,''),ifnull(APIKEY,''),ifnull(DEALTREAD,''),ifnull(SUPPSTDITEM,'0'),ifnull(SUPPSTDRATI,''),ifnull(SHOPCOMPAR,'') from shopdtl where SHOPCD = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, shopcode);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[ShoppingMallDetailList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
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

	public boolean isExistShoppingID(String shoppingid, String shopcd) throws Exception {
		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select SHOPPINGID from shopdtl where SHOPPINGID = ? and compno = ? and shopcd = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, shoppingid);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(3, shopcd);
			System.out.println("[isExistShoppingID]" + pstmt.toString());

			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return flag;
	}

	// 선택삭제
	public int shoppingMallDetailDelete(String shopid, String shopcode) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "delete from shopdtl where shoppingid = ? and compno = ? and shopcd = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, shopid);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(3, shopcode);

			System.out.println("[shoppingMallDetailDelete]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	// 거래여부변경
	public void setDealTradeUpdate(String shopcode, String dealtrade) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "update shopmst set DEALTRADE=? where SHOPCd=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int rowIdx = 0;

			pstmt.setString(++rowIdx, dealtrade);
			pstmt.setString(++rowIdx, shopcode);
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[ShoppingMallDetail]" + pstmt.toString());

			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	// 검색하기
	public List<List<String>> getSearchcondition(int result, String search, String deal) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(a.SHOPCD,''),ifnull(b.SHOPNM,''),ifnull(b.CUSTNM,''),ifnull(b.PRODREGIS,''),ifnull(b.PRODMODIFY,''),ifnull(b.PRODSOLDOUT,''),ifnull(b.STOCKSEND,''),ifnull(b.ORDERCOLLECT,''),"
					+ " ifnull(b.INVOICSEND,''),ifnull(b.CLAIMCOLLECT,''),ifnull(b.QUESTION,''),ifnull(b.MARKETCATEG,''),ifnull(a.IDNUM,''),ifnull(a.DEALTRADE,''), ifnull(b.shopurl,'') "
					+ " from shopmst a join shopinfo b on a.shopcd = b.shopcd where a.compno = ? ";
			if (result == 1) {
				sql += "and b.shopnm like ? ";
			} else if (result == 2) {
				sql += "and b.shopcd like ? ";
			} else {
				sql += "and b.marketcateg like ? ";
			}

			// 임시처리
//			if(deal.equals("거래중")) sql += "and a.dealtrade = '거래중' ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, "%" + search + "%");
			System.out.println("[getDealTradeList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<>();
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

	// 출고지/반품지 주소 가지고 오기
	public List<List<String>> getFactoryAddress(boolean flag) {
		List<List<String>> responseContents = new ArrayList<>();
		StringBuffer response = new StringBuffer();
		try {

			CompInfoDao dao = new CompInfoDao();
			List<String> list1 = dao.getCompNoImage();
			String text = "text/xml";
			String api = "";
			if (list1.get(0).equals("2")) {
				api = "eb7130106803ed06caca9f67228b17e5";
			} else {
				api = "b043773df271a0a504cf0405a6bcdec5";
			}
			String url = "";
			if (flag) {
				url = "http://api.11st.co.kr/rest/areaservice/outboundarea";
			} else {
				url = "http://api.11st.co.kr/rest/areaservice/inboundarea";
			}

			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Content-Type", text);
			httpConnection.setRequestProperty("openapikey", api);

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}

			httpConnection.disconnect();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(response.toString().getBytes());
			org.w3c.dom.Document doc = builder.parse(is);
			org.w3c.dom.Element ele = doc.getDocumentElement();
			NodeList list = ele.getElementsByTagName("addr");
			for (int i = 0; i < list.getLength(); i++) {
				Node dataNode = list.item(i);
				List<String> lcist = new ArrayList<>();
				NodeList dataChildnodeList = dataNode.getChildNodes();
				for (int j = 0; j < dataChildnodeList.getLength(); j++) {
					Node dataChilNode = dataChildnodeList.item(j);
					lcist.add(dataChilNode.getTextContent());

				}
				responseContents.add(lcist);

			}

			// responseContents = getFactoryAddress11st(response.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return responseContents;

	}

	public List<List<String>> getLargeCategory(String api) {
		List<List<String>> responseContents = new ArrayList<>();
		StringBuffer response = new StringBuffer();
		try {

			CompInfoDao dao = new CompInfoDao();
			List<String> list1 = dao.getCompNoImage();
			String text = "text/xml";
//	        String api = "";
//	        if(list1.get(0).equals("2")) {
//	        	api = "eb7130106803ed06caca9f67228b17e5";
//	        }else {
//	        	api = "b043773df271a0a504cf0405a6bcdec5";
//	        }

			String url = "http://api.11st.co.kr/rest/cateservice/category";

			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Content-Type", text);
			httpConnection.setRequestProperty("openapikey", api);

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}

			httpConnection.disconnect();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(response.toString().getBytes());
			org.w3c.dom.Document doc = builder.parse(is);
			org.w3c.dom.Element ele = doc.getDocumentElement();
			NodeList list = ele.getElementsByTagName("ns2:category");
			for (int i = 0; i < list.getLength(); i++) {
				Node dataNode = list.item(i);
				List<String> lcist = new ArrayList<>();
				NodeList dataChildnodeList = dataNode.getChildNodes();
				for (int j = 0; j < dataChildnodeList.getLength(); j++) {
					Node dataChilNode = dataChildnodeList.item(j);
					lcist.add(dataChilNode.getTextContent());
				}
				responseContents.add(lcist);
			}

			// responseContents = getFactoryAddress11st(response.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return responseContents;
	}

//	public List<List<String>> getShoppingMallCategory(String shopnm) throws Exception {
//		List<List<String>> contents = new ArrayList<List<String>>();
//
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			connection = DBCPInit.getInstance().getConnection();
//
//			String sql = "select ifnull(SHOPCD,''),ifnull(SHOPNM,''),ifnull(CATEGTITLE,''),ifnull(CATEGLARGE,''),ifnull(CATEGMIDIUM,''),ifnull(CATEGSMALL,''),ifnull(CATEGDETAIL,''),ifnull(WHETHER,'')"
//					+ " from categ11st where compno = ? and SHOPNM like ? ";
//
//			sql = sql.toUpperCase();
//
//			pstmt = connection.prepareStatement(sql);
//			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
//			pstmt.setString(2, "%"+shopnm+"%");
//			System.out.println("[getDealTradeList]" + pstmt.toString());
//
//			rs = pstmt.executeQuery();
//
//			int rowNum = 0;
//			while (rs.next()) {
//				List<String> list = new ArrayList<String>();
//				int columnIndex = 0;
//				rowNum++;
//				list.add(String.valueOf(rowNum));
//				list.add(rs.getString(++columnIndex));
//				list.add(rs.getString(++columnIndex));
//				list.add(rs.getString(++columnIndex));
//				list.add(rs.getString(++columnIndex));
//				list.add(rs.getString(++columnIndex));
//				list.add(rs.getString(++columnIndex));
//				list.add(rs.getString(++columnIndex));
//				list.add(rs.getString(++columnIndex));
//				contents.add(list);
//
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw ex;
//		} finally {
//			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
//		}
//
//		return contents;
//	}
	// 매핑선택 클릭시
	public int setProductMstShopCategoryUpdate(List<String> list, ShoppingMallDto catelist, String managercode)
			throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();
		try {

			connection = DBCPInit.getInstance().getConnection();
			String sql = "";
			if (managercode.equals("1")) {
				sql = "insert into categlgmap(compno, CODE,SHOPCD,SHOPCATNO) VALUES(?, ?, ?, ?)";
			} else if (managercode.equals("2")) {
				sql = "insert into categmdmap(compno, CODE,SHOPCD,SHOPCATNO) VALUES(?, ?, ?, ?)";
			} else if (managercode.equals("3")) {
				sql = "insert into categsmmap(compno, CODE,SHOPCD,SHOPCATNO) VALUES(?, ?, ?, ?)";
			} else {
				sql = "insert into categdtlmap(compno, CODE,SHOPCD,SHOPCATNO) VALUES(?, ?, ?, ?)";
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, list.get(0));
			pstmt.setString(++rowIdx, catelist.getShopcd());
			pstmt.setString(++rowIdx, catelist.getShopcatno());

			result = pstmt.executeUpdate();

			System.out.println("[setProductMstShopCategoryUpdate]" + pstmt.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;

	}

	public List<List<String>> getSearchShoppingProduct(String shopnm, String recvdtFrom, String recvdtTo, String num,
			String search, String type, String prodcd, Shell shell) {
		List<List<String>> responseContents = new ArrayList<>();
		StringBuffer response = new StringBuffer();
		try {
			YDWMXmlManager.updateSendShoppingMallSearchProduct(shopnm, recvdtFrom, recvdtTo, num, search, type, prodcd,
					shell);
			FtpUtil.uploadXmlFileToWeb('M');
			StringBuffer fileData = new StringBuffer();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(YDMASessonUtil.getAppPath()
									+ "\\YDwmsData\\03.SabangNet\\template\\SendShoppingMallSearchProduct.xml"),
							"EUC-KR"));

			char[] buf = new char[1024];

			int numRead = 0;

			while ((numRead = reader.read(buf)) != -1) {
				fileData.append(buf, 0, numRead);
			}

			reader.close();

			String xml_string_to_send = fileData.toString();
			String text = "text/xml";
			CompInfoDao dao = new CompInfoDao();
			List<String> list1 = dao.getCompNoImage();
			String api = "";
			if (list1.get(0).equals("2")) {
				api = "eb7130106803ed06caca9f67228b17e5";
			} else {
				api = "b043773df271a0a504cf0405a6bcdec5";
			}
			String url = "http://api.11st.co.kr/rest/prodmarketservice/prodmarket";

			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", text);
			httpConnection.setRequestProperty("openapikey", api);
			httpConnection.setRequestProperty("Content-Length", Integer.toString(xml_string_to_send.length()));

			os = httpConnection.getOutputStream();
			os.write(xml_string_to_send.getBytes("euc-kr"));
			os.flush();
			os.close();

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}

			httpConnection.disconnect();
			// 24,25,26
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(response.toString().getBytes());
			org.w3c.dom.Document doc = builder.parse(is);
			org.w3c.dom.Element ele = doc.getDocumentElement();
			NodeList list = ele.getElementsByTagName("ns2:product");
			for (int i = 0; i < list.getLength(); i++) {
				Node dataNode = list.item(i);
				List<String> lcist = new ArrayList<>();
				NodeList dataChildnodeList = dataNode.getChildNodes();
				for (int j = 0; j < dataChildnodeList.getLength(); j++) {
					Node dataChilNode = dataChildnodeList.item(j);
					lcist.add(dataChilNode.getTextContent());

				}
				if (lcist.size() == 42) {
					lcist.add(25, "");
				}
				responseContents.add(lcist);

			}

			// responseContents = getFactoryAddress11st(response.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return responseContents;

	}

	// 가져오기
	public List<List<String>> getOrderListUpload11st(String apiKey, String dateFrom, String dateTo) {
		List<List<String>> responseContents = new ArrayList<>();
		StringBuffer response = new StringBuffer();

		try {
			CompInfoDao dao = new CompInfoDao();
			List<String> list1 = dao.getCompNoImage();
			String text = "text/xml";
//	        String api = "";
//	        if(list1.get(0).equals("2")) {
//	        	api = "eb7130106803ed06caca9f67228b17e5";
//	        }else {
//	        	api = "b043773df271a0a504cf0405a6bcdec5";
//	        }
			String url = "https://api.11st.co.kr/rest/ordservices/complete/" + dateFrom + "0000/"
					+ dateTo.substring(0, 12);
			// String url =
			// "https://api.11st.co.kr/rest/ordservices/packaging/"+dateFrom+"0000/"+dateTo.substring(0,12);
			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Content-Type", text);
			httpConnection.setRequestProperty("openapikey", apiKey);

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}

			httpConnection.disconnect();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(response.toString().getBytes());
			org.w3c.dom.Document doc = builder.parse(is);
			doc.getDocumentElement().getNodeName();
			NodeList list = doc.getElementsByTagName("ns2:order");
			for (int i = 0; i < list.getLength(); i++) {
				Node dataNode = list.item(i);
				List<String> lcist = new ArrayList<>();
				NodeList dataChildnodeList = dataNode.getChildNodes();
				for (int j = 0; j < dataChildnodeList.getLength(); j++) {
					Node dataChilNode = dataChildnodeList.item(j);

					lcist.add(dataChilNode.getTextContent());
				}
				responseContents.add(lcist);
			}

			// responseContents = getFactoryAddress11st(response.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return responseContents;
	}

	// 가져오기
	public List<List<String>> getOrderReqPackaging(List<List<String>> contents) {
		List<List<String>> responseContents = new ArrayList<>();
		StringBuffer response = new StringBuffer();
		try {
			// 포문돌려야함
			CompInfoDao dao = new CompInfoDao();
			List<String> list1 = dao.getCompNoImage();
			String text = "text/xml";
			String api = "";
			if (list1.get(0).equals("2")) {
				api = "eb7130106803ed06caca9f67228b17e5";
			} else {
				api = "b043773df271a0a504cf0405a6bcdec5";
			}
			String url = "https://api.11st.co.kr/rest/ordservices/reqpackaging/[ordNo]/[ordPrdSeq]/[addPrdYn]/[addPrdNo]/[dlvNo]";

			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Content-Type", text);
			httpConnection.setRequestProperty("openapikey", api);

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}

			httpConnection.disconnect();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(response.toString().getBytes());
			org.w3c.dom.Document doc = builder.parse(is);
			doc.getDocumentElement().getNodeName();
			NodeList list = doc.getElementsByTagName("ResultOrder");
			for (int i = 0; i < list.getLength(); i++) {
				Node dataNode = list.item(i);
				List<String> lcist = new ArrayList<>();
				NodeList dataChildnodeList = dataNode.getChildNodes();
				for (int j = 0; j < dataChildnodeList.getLength(); j++) {
					Node dataChilNode = dataChildnodeList.item(j);
					lcist.add(dataChilNode.getTextContent());
				}
				responseContents.add(lcist);

			}

			// responseContents = getFactoryAddress11st(response.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return responseContents;
	}

	// 발송처리
	public List<List<String>> getOrderList11stSend(List<List<String>> contents, String dateTo) {
		List<List<String>> responseContents = new ArrayList<>();
		StringBuffer response = new StringBuffer();

		try {
			// 포문돌려야함
			CompInfoDao dao = new CompInfoDao();
			List<String> list1 = dao.getCompNoImage();
			String text = "text/xml";
			String api = "";
			if (list1.get(0).equals("2")) {
				api = "eb7130106803ed06caca9f67228b17e5";
			} else {
				api = "b043773df271a0a504cf0405a6bcdec5";
			}
			String url = "https://api.11st.co.kr/rest/ordservices/reqdelivery/" + dateTo.substring(0, 12)
					+ "/[dlvMthdCd]/[dlvEtprsCd]/[invcNo]/[dlvNo]";

			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Content-Type", text);
			httpConnection.setRequestProperty("openapikey", api);

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}

			httpConnection.disconnect();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(response.toString().getBytes());
			org.w3c.dom.Document doc = builder.parse(is);
			doc.getDocumentElement().getNodeName();
			NodeList list = doc.getElementsByTagName("ResultOrder");
			for (int i = 0; i < list.getLength(); i++) {
				Node dataNode = list.item(i);
				List<String> lcist = new ArrayList<>();
				NodeList dataChildnodeList = dataNode.getChildNodes();
				for (int j = 0; j < dataChildnodeList.getLength(); j++) {
					Node dataChilNode = dataChildnodeList.item(j);
					lcist.add(dataChilNode.getTextContent());
				}
				responseContents.add(lcist);

			}

			// responseContents = getFactoryAddress11st(response.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return responseContents;
	}

	public List<List<String>> getSalesCompleted(String dateFrom, String dateTo) {
		List<List<String>> responseContents = new ArrayList<>();
		StringBuffer response = new StringBuffer();

		try {
			// 포문돌려야함
			CompInfoDao dao = new CompInfoDao();
			List<String> list1 = dao.getCompNoImage();
			String text = "text/xml";
			String api = "";
			if (list1.get(0).equals("2")) {
				api = "eb7130106803ed06caca9f67228b17e5";
			} else {
				api = "b043773df271a0a504cf0405a6bcdec5";
			}
			String url = "https://api.11st.co.kr/rest/ordservices/completed/" + dateFrom + "0000/"
					+ dateTo.substring(0, 12);

			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Content-Type", text);
			httpConnection.setRequestProperty("openapikey", api);

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}

			httpConnection.disconnect();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(response.toString().getBytes());
			org.w3c.dom.Document doc = builder.parse(is);
			doc.getDocumentElement().getNodeName();
			NodeList list = doc.getElementsByTagName("ns2:order");
			for (int i = 0; i < list.getLength(); i++) {
				Node dataNode = list.item(i);
				List<String> lcist = new ArrayList<>();
				NodeList dataChildnodeList = dataNode.getChildNodes();
				for (int j = 0; j < dataChildnodeList.getLength(); j++) {
					Node dataChilNode = dataChildnodeList.item(j);
					lcist.add(dataChilNode.getTextContent());
				}
				responseContents.add(lcist);

			}

			// responseContents = getFactoryAddress11st(response.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return responseContents;
	}

	// 취소&교환&반품
	public List<List<String>> getOrderCancelNChangeNReturn11st(String dateFrom, String dateTo, String gubun) {
		List<List<String>> responseContents = new ArrayList<>();
		StringBuffer response = new StringBuffer();

		try {
			CompInfoDao dao = new CompInfoDao();
			List<String> list1 = dao.getCompNoImage();
			String text = "text/xml";
			String api = "";
			if (list1.get(0).equals("2")) {
				api = "eb7130106803ed06caca9f67228b17e5";
			} else {
				api = "b043773df271a0a504cf0405a6bcdec5";
			}
			String url = "";
			if (gubun.equals("CANCEL")) {// 취소
				url = "http://api.11st.co.kr/rest/claimservice/cancelorders/" + dateFrom + "0000/"
						+ dateTo.substring(0, 12);
			} else if (gubun.equals("CHANGE")) {// 교환
				url = "http://api.11st.co.kr/rest/claimservice/exchangeorders/" + dateFrom + "0000/"
						+ dateTo.substring(0, 12);
			} else {// 반품
				url = "http://api.11st.co.kr/rest/claimservice/returnorders/" + dateFrom + "0000/"
						+ dateTo.substring(0, 12);
			}

			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Content-Type", text);
			httpConnection.setRequestProperty("openapikey", api);

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}

			httpConnection.disconnect();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(response.toString().getBytes());
			org.w3c.dom.Document doc = builder.parse(is);
			doc.getDocumentElement().getNodeName();
			NodeList list = doc.getElementsByTagName("ns2:order");
			for (int i = 0; i < list.getLength(); i++) {
				Node dataNode = list.item(i);
				List<String> lcist = new ArrayList<>();
				if (dataNode.getNodeName().equals("ns2:order")) {
					NodeList dataChildnodeList = dataNode.getChildNodes();
					for (int j = 0; j < dataChildnodeList.getLength(); j++) {
						Node dataChilNode = dataChildnodeList.item(j);
						if (j == 11) {
							lcist.add(dataChilNode.getTextContent().equals("01") ? "취소요청" : "취소완료");
						}
						if (j == 11) {
							lcist.add(dataChilNode.getTextContent().equals("01") ? "취소요청" : "취소완료");
						}
						lcist.add(dataChilNode.getTextContent());
					}
				} else {
					NodeList dataChildnodeList = dataNode.getChildNodes();
					for (int j = 0; j < dataChildnodeList.getLength(); j++) {
						Node dataChilNode = dataChildnodeList.item(j);
						lcist.add(dataChilNode.getTextContent());
					}
				}
				responseContents.add(lcist);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return responseContents;
	}

	// 부가정보등록
	public int ShopAddrDtlInsert(int seq, ShopProduct11stAdditionDto dto, List<String> list) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "insert into shopaddr11stdtl(SEQ, COMPNO,SHOPCD,TITLE, MEMO,SHOPID,SELMTHDCD, PRODTYPCD, MALL_VAR_1,MALL_VAR_2,MALL_VAR_3,MALL_VAR_4,MALL_VAR_5,MALL_VAR_6,MALL_VAR_7,MALL_VAR_8,"
					+ " MALL_VAR_9,PRODSTATCD, MINORSELCNYN, MALL_VAR_10,MALL_VAR_11,NICKNM, MALL_VAR_12,MALL_VAR_13,MALL_VAR_94,MALL_VAR_14,MALL_VAR_15,MALL_VAR_16,FPSELTERMYN, PRODFPSELCD,MALL_VAR_17,"
					+ " MALL_VAR_18,MALL_VAR_19,MALL_VAR_20,MALL_VAR_21,MALL_VAR_22,MALL_VAR_23,MALL_VAR_24,MALL_VAR_25,MALL_VAR_26,MALL_VAR_27,MALL_VAR_28,MALL_VAR_29,MALL_VAR_30,MALL_VAR_31,MALL_VAR_32,"
					+ " MALL_VAR_33,MALL_VAR_34,MALL_VAR_35,MALL_VAR_36,MALL_VAR_37,MALL_VAR_38,MALL_VAR_39,MALL_VAR_40,MALL_VAR_41,MALL_VAR_42,MALL_VAR_43,MALL_VAR_44,MALL_VAR_45,MALL_VAR_46,MALL_VAR_47,"
					+ " MALL_VAR_49,MALL_VAR_50,MALL_VAR_51,MALL_VAR_52,EXPWYCD, MALL_VAR_95,SENDEXP, MALL_VAR_53,MALL_VAR_54,GBLDIVYN, GBLWGHT,HSCD, MALL_VAR_55,ADDROUT, MALL_VAR_56,ADDRIN, "
					+ " SHIPTYPCD, SHIPPRC, CONDIPRICE, MALL_VAR_57, MALL_VAR_58,DIFFPRICE1, MALL_VAR_59, JEJUPRC,ISLANDPRC, PRCTYPCD, BNDYN, RETPRC, EXCPRC, RUDCD, ASDTL,RTEXCDTL,MALL_VAR_60,MALL_VAR_61,"
					+ " MALL_VAR_62,MALL_VAR_63,MALL_VAR_64,MALL_VAR_65,MALL_VAR_66,MALL_VAR_67,MALL_VAR_68,MALL_VAR_69,MALL_VAR_70,MALL_VAR_71,MALL_VAR_72,MALL_VAR_73,MALL_VAR_74,MALL_VAR_75,MALL_VAR_76,"
					+ " MALL_VAR_77,MALL_VAR_78,MALL_VAR_79,MALL_VAR_80,MALL_VAR_81,MALL_VAR_82,MALL_VAR_83,MALL_VAR_84,MALL_VAR_85,MALL_VAR_86,MALL_VAR_87,MALL_VAR_88,MALL_VAR_89,MALL_VAR_90,MALL_VAR_91,"
					+ " MALL_VAR_92,MALL_VAR_93,USEYN) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "// 57
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,"// 117
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setInt(++rowIdx, seq); // 일련번호
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno()); // 회사코드
			pstmt.setString(++rowIdx, list.get(0)); // 쇼핑몰코드
			pstmt.setString(++rowIdx, dto.getTitle()); // 제목
			pstmt.setString(++rowIdx, dto.getMemo()); // 메모
			pstmt.setString(++rowIdx, dto.getShopid()); // 아이디
			pstmt.setString(++rowIdx, dto.getSelmthdcd()); // 판매방식
			pstmt.setString(++rowIdx, dto.getProdtypcd()); // 서비스상품
			pstmt.setString(++rowIdx, dto.getMall_var_1()); // 상품홍보문구
			pstmt.setString(++rowIdx, dto.getMall_var_2()); // 원산지물품
			pstmt.setString(++rowIdx, dto.getMall_var_3()); // 원산지사용여부
			pstmt.setString(++rowIdx, dto.getMall_var_4()); // 원산지사용여부에따른내용
			pstmt.setString(++rowIdx, dto.getMall_var_5()); // 원산지다중등록여부
			pstmt.setString(++rowIdx, dto.getMall_var_6()); // 상품모델
			pstmt.setString(++rowIdx, dto.getMall_var_7()); // 상품수정모델
			pstmt.setString(++rowIdx, dto.getMall_var_8()); // 판매자상품코드
			pstmt.setString(++rowIdx, dto.getMall_var_9()); // 셀러캐시사용여부
			pstmt.setString(++rowIdx, dto.getProdstatcd()); // 상품상태
			pstmt.setString(++rowIdx, dto.getMinorselcnyn()); // 미성년자구매여부
			pstmt.setString(++rowIdx, dto.getMall_var_10()); // 지점선택
			pstmt.setString(++rowIdx, dto.getMall_var_11()); // 디즈니시리즈선택
			pstmt.setString(++rowIdx, dto.getNicknm()); // 닉네임
			pstmt.setString(++rowIdx, dto.getMall_var_12()); // 브랜드
			pstmt.setString(++rowIdx, dto.getMall_var_13()); // 가입신청URL
			pstmt.setString(++rowIdx, dto.getMall_var_94()); // 휴대폰약정여부
			pstmt.setString(++rowIdx, dto.getMall_var_14()); // 휴대폰요금제
			pstmt.setString(++rowIdx, dto.getMall_var_15()); // 유효일자
			pstmt.setString(++rowIdx, dto.getMall_var_16()); // 가격비교등록여부
			pstmt.setString(++rowIdx, dto.getFpseltermyn()); // 판매기간설정여부
			pstmt.setString(++rowIdx, dto.getProdfpselcd()); // 판매기간코드
			pstmt.setString(++rowIdx, dto.getMall_var_17()); // 1원폰상품여부
			pstmt.setString(++rowIdx, dto.getMall_var_18()); // 옵션설정여부
			pstmt.setString(++rowIdx, dto.getMall_var_19()); // 2단옵션적용여부
			pstmt.setString(++rowIdx, dto.getMall_var_20()); // 옵션값노출방식
			pstmt.setString(++rowIdx, dto.getMall_var_21()); // 작성형옵션여부
			pstmt.setString(++rowIdx, dto.getMall_var_22()); // 옵션명1
			pstmt.setString(++rowIdx, dto.getMall_var_23()); // 옵션명2
			pstmt.setString(++rowIdx, dto.getMall_var_24()); // 옵션명3
			pstmt.setString(++rowIdx, dto.getMall_var_25()); // 옵션명4
			pstmt.setString(++rowIdx, dto.getMall_var_26()); // 옵션명5
			pstmt.setString(++rowIdx, dto.getMall_var_27()); // 옵션명6
			pstmt.setString(++rowIdx, dto.getMall_var_28()); // 옵션명7
			pstmt.setString(++rowIdx, dto.getMall_var_29()); // 옵션명8
			pstmt.setString(++rowIdx, dto.getMall_var_30()); // 옵션명9
			pstmt.setString(++rowIdx, dto.getMall_var_31()); // 옵션명10
			pstmt.setString(++rowIdx, dto.getMall_var_32()); // 제휴가상품URL
			pstmt.setString(++rowIdx, dto.getMall_var_33()); // 최초출발일
			pstmt.setString(++rowIdx, dto.getMall_var_34()); // 마지막출발일
			pstmt.setString(++rowIdx, dto.getMall_var_35()); // 베네피아할인여부
			pstmt.setString(++rowIdx, dto.getMall_var_36()); // 베네피아할인금액및율
			pstmt.setString(++rowIdx, dto.getMall_var_37()); // 베네피아기간설정여부
			pstmt.setString(++rowIdx, dto.getMall_var_38()); // 베네피아시작일
			pstmt.setString(++rowIdx, dto.getMall_var_39()); // 베네피아종료일
			pstmt.setString(++rowIdx, dto.getMall_var_40()); // 최소구매수량제한여부
			pstmt.setString(++rowIdx, dto.getMall_var_41()); // 최소구매제한시수량
			pstmt.setString(++rowIdx, dto.getMall_var_42()); // 최대구매수량제한여부
			pstmt.setString(++rowIdx, dto.getMall_var_43()); // 1회제한시수량
			pstmt.setString(++rowIdx, dto.getMall_var_44()); // 기간제한시기간
			pstmt.setString(++rowIdx, dto.getMall_var_45()); // 기간제한시수량
			pstmt.setString(++rowIdx, dto.getMall_var_46()); // 사은품명
			pstmt.setString(++rowIdx, dto.getMall_var_47()); // 사은품기간시작일
			pstmt.setString(++rowIdx, dto.getMall_var_49()); // 사은품기간종료일
			pstmt.setString(++rowIdx, dto.getMall_var_50()); // 사은품정보
			pstmt.setString(++rowIdx, dto.getMall_var_51()); // 배송정보템플릿
			pstmt.setString(++rowIdx, dto.getMall_var_52()); // 배송가능지역
			pstmt.setString(++rowIdx, dto.getExpwycd()); // 배송방법
			pstmt.setString(++rowIdx, dto.getMall_var_95()); // 방문수령
			pstmt.setString(++rowIdx, dto.getSendexp()); // 발송택배사
			pstmt.setString(++rowIdx, dto.getMall_var_53()); // 발송예정일
			pstmt.setString(++rowIdx, dto.getMall_var_54()); // 방문수령주소
			pstmt.setString(++rowIdx, dto.getGbldivyn()); // 전세계배송여부
			pstmt.setString(++rowIdx, dto.getGblwght()); // 전세계배송무게
			pstmt.setString(++rowIdx, dto.getHscd()); // HS코드
			pstmt.setString(++rowIdx, dto.getMall_var_55()); // 출고지주소해외여부
			pstmt.setString(++rowIdx, dto.getAddrout()); // 출고주소
			pstmt.setString(++rowIdx, dto.getMall_var_56()); // 교환반품주소해외여부
			pstmt.setString(++rowIdx, dto.getAddrin()); // 교환/반품주소
			pstmt.setString(++rowIdx, dto.getShiptypcd()); // 배송비조건
			pstmt.setString(++rowIdx, dto.getShipprc()); // 기본배송비
			pstmt.setString(++rowIdx, dto.getCondiprice()); // 상품조건별시 뒤에금액
			pstmt.setString(++rowIdx, dto.getMall_var_57()); // 수량차등별수량1
			pstmt.setString(++rowIdx, dto.getMall_var_58()); // 수량차등별수량2
			pstmt.setString(++rowIdx, dto.getDiffprice1()); // 수량차등별금액1
			pstmt.setString(++rowIdx, dto.getMall_var_59()); // 수량차등별금액2
			pstmt.setString(++rowIdx, dto.getJejuprc()); // 제주배송비
			pstmt.setString(++rowIdx, dto.getIslandprc()); // 도서산간배송비
			pstmt.setString(++rowIdx, dto.getPrctypcd()); // 선결제여부
			pstmt.setString(++rowIdx, dto.getBndyn()); // 묶음배송여부
			pstmt.setString(++rowIdx, dto.getRetprc()); // 반품배송비
			pstmt.setString(++rowIdx, dto.getExcprc()); // 교환배송비
			pstmt.setString(++rowIdx, dto.getRudcd()); // 왕복편도여부
			pstmt.setString(++rowIdx, dto.getAsdtl()); // A/S안내
			pstmt.setString(++rowIdx, dto.getRtexcdtl()); // 교환/반품안내
			pstmt.setString(++rowIdx, dto.getMall_var_60()); // 복수구매할인여부
			pstmt.setString(++rowIdx, dto.getMall_var_61()); // 복수할인기준
			pstmt.setString(++rowIdx, dto.getMall_var_62()); // 복수할인금액및갯수1
			pstmt.setString(++rowIdx, dto.getMall_var_63()); // 복수할인금액및갯수2
			pstmt.setString(++rowIdx, dto.getMall_var_64()); // 복수할인방법기준
			pstmt.setString(++rowIdx, dto.getMall_var_65()); // 구입처선택
			pstmt.setString(++rowIdx, dto.getMall_var_66()); // 대표이미지
			pstmt.setString(++rowIdx, dto.getMall_var_67()); // 모바일상세설명이미지선택
			pstmt.setString(++rowIdx, dto.getMall_var_68()); // 모바일쿠폰선택여부
			pstmt.setString(++rowIdx, dto.getMall_var_69()); // 해외구매대행상품여부
			pstmt.setString(++rowIdx, dto.getMall_var_70()); // 상품명앞추가문구
			pstmt.setString(++rowIdx, dto.getMall_var_71()); // 상품명뒷추가문구
			pstmt.setString(++rowIdx, dto.getMall_var_72()); // 상품설명상단추가문구
			pstmt.setString(++rowIdx, dto.getMall_var_73()); // 상품설명하단추가문구
			pstmt.setString(++rowIdx, dto.getMall_var_74()); // 기본즉시할인여부
			pstmt.setString(++rowIdx, dto.getMall_var_75()); // 기본즉시할인금액
			pstmt.setString(++rowIdx, dto.getMall_var_76()); // 기본즉시할인기준
			pstmt.setString(++rowIdx, dto.getMall_var_77()); // 쿠폰지급기간설정여부
			pstmt.setString(++rowIdx, dto.getMall_var_78()); // 쿠폰지급기간종료일
			pstmt.setString(++rowIdx, dto.getMall_var_79()); // 무이자할부제공여부
			pstmt.setString(++rowIdx, dto.getMall_var_80()); // 무이자할부개월수
			pstmt.setString(++rowIdx, dto.getMall_var_81()); // 무이자할부회수제한
			pstmt.setString(++rowIdx, dto.getMall_var_82()); // 희망할인설정여부
			pstmt.setString(++rowIdx, dto.getMall_var_83()); // 희망후원금액
			pstmt.setString(++rowIdx, dto.getMall_var_84()); // 희망후원할인기준
			pstmt.setString(++rowIdx, dto.getMall_var_85()); // SK포인트지급여부
			pstmt.setString(++rowIdx, dto.getMall_var_86()); // SK포인트금액
			pstmt.setString(++rowIdx, dto.getMall_var_87()); // SK포인트할인기준
			pstmt.setString(++rowIdx, dto.getMall_var_88()); // 상품리뷰여부
			pstmt.setString(++rowIdx, dto.getMall_var_89()); // 플러스광고여부
			pstmt.setString(++rowIdx, dto.getMall_var_90()); // 플러스UP광고여부
			pstmt.setString(++rowIdx, dto.getMall_var_91()); // GIF광고여부
			pstmt.setString(++rowIdx, dto.getMall_var_92()); // 볼드체광고여부
			pstmt.setString(++rowIdx, dto.getMall_var_93()); // 배경색광고여부
			pstmt.setString(++rowIdx, dto.getUseyn()); // 사용여부

			pstmt.executeUpdate();

			System.out.println("[insertdtl-ShopAddrDtlInsert]" + pstmt.toString());

			String sql_mst = " insert into shopaddrmst (compno, SHOPCD, SEQ,  TITLE, SELMTHDCD, USEYN, INSERTDT) "
					+ "  VALUES (?, ?,?,?,?,?,?)";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, list.get(0));
			pstmt.setInt(3, seq);
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getSelmthdcd());
			pstmt.setString(6, dto.getUseyn());
			pstmt.setString(7, YDMATimeUtil.getCurrentTimeByYDFormat());

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;

	}
//	//쇼핑명이랑타이틀있는지 체크
//	public boolean isShopnmNTitle(String shopnm, String title) throws Exception {
//		boolean flag = false;
//
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//
//		ResultSet rs = null;
//		try {
//			connection = DBCPInit.getInstance().getConnection();
//
//			String sql = "select shopnm from shopaddrdtl where shopnm = ? and title = ? ";
//			sql = sql.toUpperCase();
//
//			pstmt = connection.prepareStatement(sql);
//			pstmt.setString(1, shopnm);
//			pstmt.setString(2, title);
//
//			System.out.println("[isExistShoppingID]"+pstmt.toString());
//
//			rs = pstmt.executeQuery();
//			flag = rs.next();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw ex;
//		} finally {
//			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
//		}
//
//		return flag;
//	}

	public List<List<String>> getAdditionalInforMation(int senddt, String prodFrom, String prodTo, int select,
			int interlock, String useyn, int result, String search, String shopcd) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT ifnull(mst.SEQ,''),ifnull(a.SHOPNM,''),ifnull(a.MARKETCATEG,''),ifnull(mst.TITLE,''),ifnull(mst.SELMTHDCD,''),ifnull(mst.USEYN,''),ifnull(mst.insertdt,''),"
					+ " ifnull(mst.updatedt,''), ifnull(mst.shopcd,'') "
					+ " FROM shopaddrmst mst JOIN shopmst addr ON mst.SHOPCD=addr.SHOPCD and mst.compno = addr.compno join shopinfo a on addr.shopcd = a.shopcd "
					+ "where mst.compno = ? and mst.useyn = ? ";
			if (senddt == 0) {
				sql += " and mst.insertdt >= ? and mst.insertdt <= ? ";
			} else {
				sql += " and mst.updatedt >= ? and mst.updatedt <= ? ";
			}
			// 고정가판매
			if (interlock == 1) {
				sql += " and mst.SELMTHDCD = '01' ";
			} else if (interlock == 2) {
				sql += " and mst.SELMTHDCD = '02' ";
			}
			// 조건선택
			if (result == 1) {
				sql += " and mst.title like ? ";
			} else if (result == 2) {
				sql += " and mst.seq like ? ";
			}
			// 쇼핑몰코드
			if (select != 0) {
				sql += " and mst.shopcd = ? ";
			}
			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, useyn);
			pstmt.setString(3, prodFrom);
			pstmt.setString(4, prodTo);
			pstmt.setString(5, "%" + search + "%");
			if (select != 0) {
				pstmt.setString(6, shopcd);
			}
			System.out.println("[getAdditionalInforMation]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
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

	public List<List<String>> getShopMstSelectList() throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(a.SHOPCD,''),ifnull(b.SHOPNM,''),ifnull(b.CUSTNM,''),ifnull(b.PRODREGIS,''),ifnull(b.PRODMODIFY,''),ifnull(b.PRODSOLDOUT,''),ifnull(b.STOCKSEND,''),ifnull(b.ORDERCOLLECT,''),"
					+ " ifnull(b.INVOICSEND,''),ifnull(b.CLAIMCOLLECT,''),ifnull(b.QUESTION,''),ifnull(b.MARKETCATEG,''),ifnull(a.IDNUM,''),ifnull(a.DEALTRADE,'') "
					+ " from shopmst a join shopinfo b on a.shopcd = b.shopcd " + "where a.compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
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

	public List<String> getShopMstOneSelect(String shopnm) throws Exception {
		List<String> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(a.SHOPCD,''),ifnull(b.SHOPNM,''),ifnull(b.CUSTNM,''),ifnull(b.PRODREGIS,''),ifnull(b.PRODMODIFY,''),ifnull(b.PRODSOLDOUT,''),ifnull(b.STOCKSEND,''),ifnull(b.ORDERCOLLECT,''),"
					+ " ifnull(b.INVOICSEND,''),ifnull(b.CLAIMCOLLECT,''),ifnull(b.QUESTION,''),ifnull(b.MARKETCATEG,''),ifnull(a.IDNUM,''),ifnull(a.DEALTRADE,'') "
					+ " from shopmst a join shopinfo b on a.shopcd = b.shopcd " + "where b.shopnm = ? "
					+ "  and a.compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, shopnm);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getShopMstOneSelect]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public int getSeqNumber(String shopcd) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(max(seq),0) from shopaddrmst ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);

			System.out.println("[getSeqNumber]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				result = rs.getInt(++columnIndex);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return result;

	}

	public ShopProduct11stAdditionDto shopDetailjoinList(List<String> list) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShopProduct11stAdditionDto dto = new ShopProduct11stAdditionDto();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT IFNULL(dtl.SEQ,''),IFNULL(dtl.COMPNO,''),IFNULL(dtl.SHOPCD,''),IFNULL(dtl.TITLE,''),IFNULL(dtl.MEMO,''),IFNULL(dtl.SHOPID,''),IFNULL(dtl.SELMTHDCD,''),"
					+ " IFNULL(dtl.PRODTYPCD,''),IFNULL(dtl.MALL_VAR_1,''),IFNULL(dtl.MALL_VAR_2,''),IFNULL(dtl.MALL_VAR_3,''),IFNULL(dtl.MALL_VAR_4,''),IFNULL(dtl.MALL_VAR_5,''),IFNULL(dtl.MALL_VAR_6,''),"
					+ " IFNULL(dtl.MALL_VAR_7,''),IFNULL(dtl.MALL_VAR_8,''),IFNULL(dtl.MALL_VAR_9,''),IFNULL(dtl.PRODSTATCD,''),IFNULL(dtl.MINORSELCNYN,''), IFNULL(dtl.MALL_VAR_10,''),"
					+ " IFNULL(dtl.MALL_VAR_11,''),IFNULL(dtl.NICKNM,''), IFNULL(dtl.MALL_VAR_12,''),IFNULL(dtl.MALL_VAR_13,''),IFNULL(dtl.MALL_VAR_94,'0'),IFNULL(dtl.MALL_VAR_14,''), "
					+ " IFNULL(dtl.MALL_VAR_15,''),IFNULL(dtl.MALL_VAR_16,''), ifnull(dtl.FPSELTERMYN,''), ifnull(dtl.PRODFPSELCD,''), ifnull(dtl.MALL_VAR_17,''), ifnull(dtl.MALL_VAR_18,''), "
					+ " IFNULL(dtl.MALL_VAR_19,''),IFNULL(dtl.MALL_VAR_20,''),IFNULL(dtl.MALL_VAR_21,''),IFNULL(dtl.MALL_VAR_22,''),IFNULL(dtl.MALL_VAR_23,''),IFNULL(dtl.MALL_VAR_24,''), "
					+ " IFNULL(dtl.MALL_VAR_25,''), IFNULL(dtl.MALL_VAR_26,''),IFNULL(dtl.MALL_VAR_27,''),IFNULL(dtl.MALL_VAR_28,''),IFNULL(dtl.MALL_VAR_29,''),IFNULL(dtl.MALL_VAR_30,''), "
					+ " IFNULL(dtl.MALL_VAR_31,''),IFNULL(dtl.MALL_VAR_32,''), IFNULL(dtl.MALL_VAR_33,''),IFNULL(dtl.MALL_VAR_34,''),IFNULL(dtl.MALL_VAR_35,''),IFNULL(dtl.MALL_VAR_36,''), "
					+ " IFNULL(dtl.MALL_VAR_37,''), IFNULL(dtl.MALL_VAR_38,''),IFNULL(dtl.MALL_VAR_39,''),IFNULL(dtl.MALL_VAR_40,''), IFNULL(dtl.MALL_VAR_41,'0'),IFNULL(dtl.MALL_VAR_42,''), "
					+ " IFNULL(dtl.MALL_VAR_43,'0'),IFNULL(dtl.MALL_VAR_44,'0'), IFNULL(dtl.MALL_VAR_45,'0'),IFNULL(dtl.MALL_VAR_46,''), ifnull(dtl.MALL_VAR_47,''), "
					+ " ifnull(dtl.MALL_VAR_49,''), ifnull(dtl.MALL_VAR_50,''),IFNULL(dtl.MALL_VAR_51,''),IFNULL(dtl.MALL_VAR_52,''),IFNULL(dtl.EXPWYCD,''),IFNULL(dtl.MALL_VAR_95,''),IFNULL(dtl.SENDEXP,''),"
					+ " IFNULL(dtl.MALL_VAR_53,''),IFNULL(dtl.MALL_VAR_54,''), IFNULL(dtl.GBLDIVYN,''),IFNULL(dtl.GBLWGHT,''),IFNULL(dtl.HSCD,''),IFNULL(dtl.MALL_VAR_55,''),IFNULL(dtl.ADDROUT,''), "
					+ " IFNULL(dtl.MALL_VAR_56,''),IFNULL(dtl.ADDRIN,''), ifnull(dtl.SHIPTYPCD,''), IFNULL(dtl.SHIPPRC,'0'),IFNULL(dtl.CONDIPRICE,'0'),IFNULL(dtl.MALL_VAR_57,'0'),IFNULL(dtl.MALL_VAR_58,'0'),IFNULL(dtl.DIFFPRICE1,'0'),"
					+ " IFNULL(dtl.MALL_VAR_59,'0'),IFNULL(dtl.JEJUPRC,'0'),IFNULL(dtl.ISLANDPRC,'0'), IFNULL(dtl.PRCTYPCD,''),IFNULL(dtl.BNDYN,''),IFNULL(dtl.RETPRC,'0'),IFNULL(dtl.EXCPRC,'0'), "
					+ " IFNULL(dtl.RUDCD,''),IFNULL(dtl.ASDTL,''), ifnull(dtl.RTEXCDTL,''), ifnull(dtl.MALL_VAR_60,''), ifnull(dtl.MALL_VAR_61,''), ifnull(dtl.MALL_VAR_62,'0'),IFNULL(dtl.MALL_VAR_63,'0'),"
					+ " IFNULL(dtl.MALL_VAR_64,''),IFNULL(dtl.MALL_VAR_65,''),IFNULL(dtl.MALL_VAR_66,''),IFNULL(dtl.MALL_VAR_67,''),IFNULL(dtl.MALL_VAR_68,''),IFNULL(dtl.MALL_VAR_69,''),"
					+ " IFNULL(dtl.MALL_VAR_70,''),IFNULL(dtl.MALL_VAR_71,''),IFNULL(dtl.MALL_VAR_72,''),IFNULL(dtl.MALL_VAR_73,''),IFNULL(dtl.MALL_VAR_74,''),IFNULL(dtl.MALL_VAR_75,'0'),"
					+ " IFNULL(dtl.MALL_VAR_76,''), IFNULL(dtl.MALL_VAR_77,''),IFNULL(dtl.MALL_VAR_78,''),IFNULL(dtl.MALL_VAR_79,''),IFNULL(dtl.MALL_VAR_80,''),IFNULL(dtl.MALL_VAR_81,'0'), "
					+ " IFNULL(dtl.MALL_VAR_82,''),IFNULL(dtl.MALL_VAR_83,'0'),IFNULL(dtl.MALL_VAR_84,''), IFNULL(dtl.MALL_VAR_85,''),IFNULL(dtl.MALL_VAR_86,'0'),IFNULL(dtl.MALL_VAR_87,'0'),"
					+ " IFNULL(dtl.MALL_VAR_88,''), IFNULL(dtl.MALL_VAR_89,''),IFNULL(dtl.MALL_VAR_90,''), ifnull(dtl.MALL_VAR_91,''), ifnull(dtl.MALL_VAR_92,''), ifnull(dtl.MALL_VAR_93,''), ifnull(dtl.USEYN,'')"
					+ "   FROM SHOPADDRMST addr "
					+ "	 	  JOIN SHOPMST MST ON MST.SHOPCD=ADDR.SHOPCD and MST.compno = ADDR.compno "
					+ "		  JOIN shopaddr11stdtl dtl ON ADDR.seq = dtl.seq and ADDR.compno = dtl.compno "
					+ "		  join shopinfo a on " + " 			   mst.shopcd = a.shopcd " + "  WHERE addr.seq = ? "
					+ "    AND a.shopnm = ? " + "    and mst.compno = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, list.get(0));
			pstmt.setString(2, list.get(1));
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[shopDetailjoinList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				dto.setSeq(rs.getString(++columnIndex)); // 일련번호
				dto.setCompno(rs.getString(++columnIndex)); // 회사코드
				dto.setShopcd(rs.getString(++columnIndex)); // 쇼핑몰코드
				dto.setTitle(rs.getString(++columnIndex)); // 제목
				dto.setMemo(rs.getString(++columnIndex)); // 메모
				dto.setShopid(rs.getString(++columnIndex)); // 아이디
				dto.setSelmthdcd(rs.getString(++columnIndex)); // 판매방식
				dto.setProdtypcd(rs.getString(++columnIndex)); // 서비스상품
				dto.setMall_var_1(rs.getString(++columnIndex)); // 상품홍보문구
				dto.setMall_var_2(rs.getString(++columnIndex)); // 원산지물품
				dto.setMall_var_3(rs.getString(++columnIndex)); // 원산지사용여부
				dto.setMall_var_4(rs.getString(++columnIndex)); // 원산지사용여부에따른내용
				dto.setMall_var_5(rs.getString(++columnIndex)); // 원산지다중등록여부
				dto.setMall_var_6(rs.getString(++columnIndex)); // 상품모델
				dto.setMall_var_7(rs.getString(++columnIndex)); // 상품수정모델
				dto.setMall_var_8(rs.getString(++columnIndex)); // 판매자상품코드
				dto.setMall_var_9(rs.getString(++columnIndex)); // 셀러캐시사용여부
				dto.setProdstatcd(rs.getString(++columnIndex)); // 상품상태
				dto.setMinorselcnyn(rs.getString(++columnIndex)); // 미성년자구매여부
				dto.setMall_var_10(rs.getString(++columnIndex)); // 지점선택
				dto.setMall_var_11(rs.getString(++columnIndex)); // 디즈니시리즈선택
				dto.setNicknm(rs.getString(++columnIndex)); // 닉네임
				dto.setMall_var_12(rs.getString(++columnIndex)); // 브랜드
				dto.setMall_var_13(rs.getString(++columnIndex)); // 가입신청URL
				dto.setMall_var_94(rs.getString(++columnIndex)); // 휴대폰약정여부
				dto.setMall_var_14(rs.getString(++columnIndex)); // 휴대폰요금제
				dto.setMall_var_15(rs.getString(++columnIndex)); // 유효일자
				dto.setMall_var_16(rs.getString(++columnIndex)); // 가격비교등록여부
				dto.setFpseltermyn(rs.getString(++columnIndex)); // 판매기간설정여부
				dto.setProdfpselcd(rs.getString(++columnIndex)); // 판매기간코드
				dto.setMall_var_17(rs.getString(++columnIndex)); // 1원폰상품여부
				dto.setMall_var_18(rs.getString(++columnIndex)); // 옵션설정여부
				dto.setMall_var_19(rs.getString(++columnIndex)); // 2단옵션적용여부
				dto.setMall_var_20(rs.getString(++columnIndex)); // 옵션값노출방식
				dto.setMall_var_21(rs.getString(++columnIndex)); // 작성형옵션여부
				dto.setMall_var_22(rs.getString(++columnIndex)); // 옵션명1
				dto.setMall_var_23(rs.getString(++columnIndex)); // 옵션명2
				dto.setMall_var_24(rs.getString(++columnIndex)); // 옵션명3
				dto.setMall_var_25(rs.getString(++columnIndex)); // 옵션명4
				dto.setMall_var_26(rs.getString(++columnIndex)); // 옵션명5
				dto.setMall_var_27(rs.getString(++columnIndex)); // 옵션명6
				dto.setMall_var_28(rs.getString(++columnIndex)); // 옵션명7
				dto.setMall_var_29(rs.getString(++columnIndex)); // 옵션명8
				dto.setMall_var_30(rs.getString(++columnIndex)); // 옵션명9
				dto.setMall_var_31(rs.getString(++columnIndex)); // 옵션명10
				dto.setMall_var_32(rs.getString(++columnIndex)); // 제휴가상품URL
				dto.setMall_var_33(rs.getString(++columnIndex)); // 최초출발일
				dto.setMall_var_34(rs.getString(++columnIndex)); // 마지막출발일
				dto.setMall_var_35(rs.getString(++columnIndex)); // 베네피아할인여부
				dto.setMall_var_36(rs.getString(++columnIndex)); // 베네피아할인금액및율
				dto.setMall_var_37(rs.getString(++columnIndex)); // 베네피아기간설정여부
				dto.setMall_var_38(rs.getString(++columnIndex)); // 베네피아시작일
				dto.setMall_var_39(rs.getString(++columnIndex)); // 베네피아종료일
				dto.setMall_var_40(rs.getString(++columnIndex)); // 최소구매수량제한여부
				dto.setMall_var_41(rs.getString(++columnIndex)); // 최소구매제한시수량
				dto.setMall_var_42(rs.getString(++columnIndex)); // 최대구매수량제한여부
				dto.setMall_var_43(rs.getString(++columnIndex)); // 1회제한시수량
				dto.setMall_var_44(rs.getString(++columnIndex)); // 기간제한시기간
				dto.setMall_var_45(rs.getString(++columnIndex)); // 기간제한시수량
				dto.setMall_var_46(rs.getString(++columnIndex)); // 사은품명
				dto.setMall_var_47(rs.getString(++columnIndex)); // 사은품기간시작일
				dto.setMall_var_49(rs.getString(++columnIndex)); // 사은품기간종료일
				dto.setMall_var_50(rs.getString(++columnIndex)); // 사은품정보
				dto.setMall_var_51(rs.getString(++columnIndex)); // 배송정보템플릿
				dto.setMall_var_52(rs.getString(++columnIndex)); // 배송가능지역
				dto.setExpwycd(rs.getString(++columnIndex)); // 배송방법
				dto.setMall_var_95(rs.getString(++columnIndex)); // 방문수령
				dto.setSendexp(rs.getString(++columnIndex)); // 발송택배사
				dto.setMall_var_53(rs.getString(++columnIndex)); // 발송예정일
				dto.setMall_var_54(rs.getString(++columnIndex)); // 방문수령주소
				dto.setGbldivyn(rs.getString(++columnIndex)); // 전세계배송여부
				dto.setGblwght(rs.getString(++columnIndex)); // 전세계배송무게
				dto.setHscd(rs.getString(++columnIndex)); // HS코드
				dto.setMall_var_55(rs.getString(++columnIndex)); // 출고지주소해외여부
				dto.setAddrout(rs.getString(++columnIndex)); // 출고주소
				dto.setMall_var_56(rs.getString(++columnIndex)); // 교환반품주소해외여부
				dto.setAddrin(rs.getString(++columnIndex)); // 교환/반품주소
				dto.setShiptypcd(rs.getString(++columnIndex)); // 배송비조건
				dto.setShipprc(rs.getString(++columnIndex)); // 기본배송비
				dto.setCondiprice(rs.getString(++columnIndex)); // 상품조건별시 뒤에금액
				dto.setMall_var_57(rs.getString(++columnIndex)); // 수량차등별수량1
				dto.setMall_var_58(rs.getString(++columnIndex)); // 수량차등별수량2
				dto.setDiffprice1(rs.getString(++columnIndex)); // 수량차등별금액1
				dto.setMall_var_59(rs.getString(++columnIndex)); // 수량차등별금액2
				dto.setJejuprc(rs.getString(++columnIndex)); // 제주배송비
				dto.setIslandprc(rs.getString(++columnIndex)); // 도서산간배송비
				dto.setPrctypcd(rs.getString(++columnIndex)); // 선결제여부
				dto.setBndyn(rs.getString(++columnIndex)); // 묶음배송여부
				dto.setRetprc(rs.getString(++columnIndex)); // 반품배송비
				dto.setExcprc(rs.getString(++columnIndex)); // 교환배송비
				dto.setRudcd(rs.getString(++columnIndex)); // 왕복편도여부
				dto.setAsdtl(rs.getString(++columnIndex)); // A/S안내
				dto.setRtexcdtl(rs.getString(++columnIndex)); // 교환/반품안내
				dto.setMall_var_60(rs.getString(++columnIndex)); // 복수구매할인여부
				dto.setMall_var_61(rs.getString(++columnIndex)); // 복수할인기준
				dto.setMall_var_62(rs.getString(++columnIndex)); // 복수할인금액및갯수1
				dto.setMall_var_63(rs.getString(++columnIndex)); // 복수할인금액및갯수2
				dto.setMall_var_64(rs.getString(++columnIndex)); // 복수할인방법기준
				dto.setMall_var_65(rs.getString(++columnIndex)); // 구입처선택
				dto.setMall_var_66(rs.getString(++columnIndex)); // 대표이미지
				dto.setMall_var_67(rs.getString(++columnIndex)); // 모바일상세설명이미지선택
				dto.setMall_var_68(rs.getString(++columnIndex)); // 모바일쿠폰선택여부
				dto.setMall_var_69(rs.getString(++columnIndex)); // 해외구매대행상품여부
				dto.setMall_var_70(rs.getString(++columnIndex)); // 상품명앞추가문구
				dto.setMall_var_71(rs.getString(++columnIndex)); // 상품명뒷추가문구
				dto.setMall_var_72(rs.getString(++columnIndex)); // 상품설명상단추가문구
				dto.setMall_var_73(rs.getString(++columnIndex)); // 상품설명하단추가문구
				dto.setMall_var_74(rs.getString(++columnIndex)); // 기본즉시할인여부
				dto.setMall_var_75(rs.getString(++columnIndex)); // 기본즉시할인금액
				dto.setMall_var_76(rs.getString(++columnIndex)); // 기본즉시할인기준
				dto.setMall_var_77(rs.getString(++columnIndex)); // 쿠폰지급기간설정여부
				dto.setMall_var_78(rs.getString(++columnIndex)); // 쿠폰지급기간종료일
				dto.setMall_var_79(rs.getString(++columnIndex)); // 무이자할부제공여부
				dto.setMall_var_80(rs.getString(++columnIndex)); // 무이자할부개월수
				dto.setMall_var_81(rs.getString(++columnIndex)); // 무이자할부회수제한
				dto.setMall_var_82(rs.getString(++columnIndex)); // 희망할인설정여부
				dto.setMall_var_83(rs.getString(++columnIndex)); // 희망후원금액
				dto.setMall_var_84(rs.getString(++columnIndex)); // 희망후원할인기준
				dto.setMall_var_85(rs.getString(++columnIndex)); // SK포인트지급여부
				dto.setMall_var_86(rs.getString(++columnIndex)); // SK포인트금액
				dto.setMall_var_87(rs.getString(++columnIndex)); // SK포인트할인기준
				dto.setMall_var_88(rs.getString(++columnIndex)); // 상품리뷰여부
				dto.setMall_var_89(rs.getString(++columnIndex)); // 플러스광고여부
				dto.setMall_var_90(rs.getString(++columnIndex)); // 플러스UP광고여부
				dto.setMall_var_91(rs.getString(++columnIndex)); // GIF광고여부
				dto.setMall_var_92(rs.getString(++columnIndex)); // 볼드체광고여부
				dto.setMall_var_93(rs.getString(++columnIndex)); // 배경색광고여부
				dto.setUseyn(rs.getString(++columnIndex)); // 사용여부

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return dto;
	}

	public int getShopdelSeq(String shopcode) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select count(*) from shopdtl where compno = ? and shopcd = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcode);

			System.out.println("[getSeqNumber]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				result = rs.getInt(++columnIndex);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return result;
	}

	public List<String> getShopMstOneSelectAllList(String shopnm) throws Exception {
		List<String> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(a.compno,''), ifnull(a.SHOPCD,''),ifnull(b.SHOPNM,''),ifnull(b.CUSTNM,''),ifnull(b.PRODREGIS,''),ifnull(b.PRODMODIFY,''),ifnull(b.PRODSOLDOUT,''),ifnull(b.STOCKSEND,''),"
					+ " ifnull(b.ORDERCOLLECT,''), ifnull(b.INVOICSEND,''),ifnull(b.CLAIMCOLLECT,''),ifnull(b.QUESTION,''),ifnull(b.MARKETCATEG,''),ifnull(a.IDNUM,''),ifnull(a.DEALTRADE,'') "
					+ " from shopmst a join shopinfo b on a.shopcd = b.shopcd where b.shopnm = ? and a.compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, shopnm);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getShopMstOneSelectAllList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public int ShoppingMallDetailUpdate(String shopcode, String shoppingid, String password, String nicknm2,
			String nicknm1, String authkey1, String authkey2, String orderexcel1, String orderexcel2,
			String orderexcel3, String apikey, String dealtrade, String shopseq, String supply, String supplyprc,
			String prccomp) throws Exception {
		int result = 0;
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

//			updateProduct(connection, statementlist, prodcd,  prodnm,  specdes,  flagset,  flagplus,
//					 flagout,  price,  tagprice,  sabcd,  remark,  ea,  useyn,  delyn, cusprice, aproinvt);

			String sql = "update shopdtl set SHOPPINGID = ?, PASSWORD = ?, NICKNM2 = ?, NICKNM1 = ?, AUTHKEY1 = ?, AUTHKEY2 = ?, ORDEREXCEL1 = ?, ORDEREXCEL2 = ?, ORDEREXCEL3 = ?, APIKEY = ?, SUPPSTDITEM = ?, "
					+ " SUPPSTDRATI = ?, DEALTREAD = ? ,SHOPCOMPAR = ? "
					+ " where COMPNO = ? and SHOPCD = ? and SHOPSEQ = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, shoppingid);
			pstmt.setString(++i, password);
			pstmt.setString(++i, nicknm2);
			pstmt.setString(++i, nicknm1);
			pstmt.setString(++i, authkey1);
			pstmt.setString(++i, authkey2);
			pstmt.setString(++i, orderexcel1);
			pstmt.setString(++i, orderexcel2);
			pstmt.setString(++i, orderexcel3);
			pstmt.setString(++i, apikey);
			pstmt.setString(++i, supply);
			pstmt.setFloat(++i, Float.parseFloat(supplyprc) / 100);
			pstmt.setString(++i, dealtrade);
			pstmt.setString(++i, prccomp);

			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, shopcode);
			pstmt.setString(++i, shopseq);
			System.out.println("[ShoppingMallDetailUpdate]" + pstmt.toString());
			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
		return result;
	}

	// 거래중인 쇼핑몰만 가지고 오기
	public List<List<String>> getShopDealTrade() throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(a.SHOPCD,''),ifnull(b.SHOPNM,''),ifnull(b.CUSTNM,''),ifnull(b.PRODREGIS,''),ifnull(b.PRODMODIFY,''),ifnull(b.PRODSOLDOUT,''),ifnull(b.STOCKSEND,''),ifnull(b.ORDERCOLLECT,''),"
					+ " 		 ifnull(b.INVOICSEND,''),ifnull(b.CLAIMCOLLECT,''),ifnull(b.QUESTION,''),ifnull(b.MARKETCATEG,''),ifnull(a.IDNUM,''),ifnull(a.DEALTRADE,'') "
					+ "  from shopmst a join shopinfo b on a.shopcd = b.shopcd " + " where a.compno = ? "
					+ "   and a.DEALTRADE = '거래중' " + "   and b.prodregis = '서비스' ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
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

	public List<List<String>> getShopidList(String shopcd) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(SHOPCD,''),ifnull(SHOPPINGID,''),ifnull(DEALTREAD,'') " + "  from shopdtl "
					+ " where compno = ? " + "   and shopcd = ? " + "   and DEALTREAD = '사용중' ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);

			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
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

	// 아이디가지고오기 웨어절추가
	public List<List<String>> getShopidListCheck(String shopcd) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(SHOPCD,''),ifnull(SHOPPINGID,''),ifnull(DEALTREAD,'') from shopdtl where compno = ? and DEALTREAD = '사용중' and shopcd = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
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

	public List<List<String>> getExpressAll() throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(SHOPCD,''),ifnull(DLVID,''),ifnull(DLVNM,'') from shopdeliverys order by SHOPCD,DLVID  ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int j = 0;
			String shopCd = "";
			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				if (!shopCd.equals(rs.getString(columnIndex + 1))) {
					shopCd = rs.getString(columnIndex + 1);
					j = 0;
				}

				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

				list.add(Integer.toString(++j));
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

	public List<List<String>> getExpress(String shopcode) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(SHOPCD,''),ifnull(DLVID,''),ifnull(DLVNM,'') from shopdeliverys where shopcd = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, shopcode);

			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int j = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

				list.add(Integer.toString(++j));
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

	public int ShopAddrDtlUpdate(ShopProduct11stAdditionDto dto, ShopProduct11stAdditionDto joinlist, String shopseq)
			throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update shopaddr11stdtl set TITLE = ?, MEMO = ?, SHOPID = ?, SELMTHDCD = ?, PRODTYPCD = ?, MALL_VAR_1 = ? ,MALL_VAR_2 = ? ,MALL_VAR_3 = ? ,MALL_VAR_4 = ? ,MALL_VAR_5 = ? , "
					+ " MALL_VAR_6 = ? ,MALL_VAR_7 = ? ,MALL_VAR_8 = ? , MALL_VAR_9 = ? ,PRODSTATCD = ? , MINORSELCNYN = ? , MALL_VAR_10 = ? ,MALL_VAR_11 = ? ,NICKNM = ? , MALL_VAR_12 = ? ,MALL_VAR_13 = ?,"
					+ " MALL_VAR_94 = ? ,MALL_VAR_14 = ? ,MALL_VAR_15 = ? ,MALL_VAR_16 = ? ,FPSELTERMYN = ? , PRODFPSELCD = ? ,MALL_VAR_17 = ? , MALL_VAR_18 = ? ,MALL_VAR_19 = ? ,MALL_VAR_20 = ? ,"
					+ " MALL_VAR_21 = ? ,MALL_VAR_22 = ? ,MALL_VAR_23 = ? ,MALL_VAR_24 = ? ,MALL_VAR_25 = ? ,MALL_VAR_26 = ? ,MALL_VAR_27 = ? ,MALL_VAR_28 = ? ,MALL_VAR_29 = ? ,MALL_VAR_30 = ? ,MALL_VAR_31 = ?,"
					+ " MALL_VAR_32 = ? , MALL_VAR_33 = ? ,MALL_VAR_34 = ? ,MALL_VAR_35 = ? ,MALL_VAR_36 = ? ,MALL_VAR_37 = ? ,MALL_VAR_38 = ? ,MALL_VAR_39 = ? ,MALL_VAR_40 = ? ,MALL_VAR_41 = ? ,"
					+ " MALL_VAR_42 = ? ,MALL_VAR_43 = ? ,MALL_VAR_44 = ? ,MALL_VAR_45 = ? ,MALL_VAR_46 = ? ,MALL_VAR_47 = ? ,MALL_VAR_49 = ? ,MALL_VAR_50 = ? ,MALL_VAR_51 = ? , "
					+ " MALL_VAR_52 = ? ,EXPWYCD = ? , MALL_VAR_95 = ? ,SENDEXP = ? , MALL_VAR_53 = ? ,MALL_VAR_54 = ? ,GBLDIVYN = ? , GBLWGHT = ? ,HSCD = ? , MALL_VAR_55 = ? ,ADDROUT = ? , MALL_VAR_56 = ? ,"
					+ " ADDRIN = ? ,SHIPTYPCD = ? , SHIPPRC = ? , CONDIPRICE = ? , MALL_VAR_57 = ? , MALL_VAR_58 = ? ,DIFFPRICE1= ? , MALL_VAR_59 = ? , JEJUPRC = ? ,ISLANDPRC = ? , PRCTYPCD = ? , BNDYN = ? ,"
					+ " RETPRC = ? , EXCPRC = ? , RUDCD = ? , ASDTL = ? ,RTEXCDTL = ? ,MALL_VAR_60 = ? ,MALL_VAR_61 = ? ,MALL_VAR_62 = ? ,MALL_VAR_63 = ? ,MALL_VAR_64 = ? ,MALL_VAR_65 = ? ,MALL_VAR_66 = ? ,"
					+ " MALL_VAR_67 = ? ,MALL_VAR_68 = ? ,MALL_VAR_69 = ? ,MALL_VAR_70 = ? ,MALL_VAR_71 = ? ,MALL_VAR_72 = ? ,MALL_VAR_73 = ? ,MALL_VAR_74 = ? ,MALL_VAR_75 = ? ,MALL_VAR_76 = ? ,MALL_VAR_77 = ?,"
					+ " MALL_VAR_78 = ? ,MALL_VAR_79 = ? ,MALL_VAR_80 = ? ,MALL_VAR_81 = ? ,MALL_VAR_82 = ? ,MALL_VAR_83 = ? ,MALL_VAR_84 = ? ,MALL_VAR_85 = ? ,MALL_VAR_86 = ? ,MALL_VAR_87 = ? ,"
					+ " MALL_VAR_88 = ? ,MALL_VAR_89 = ? ,MALL_VAR_90 = ? ,MALL_VAR_91 = ? ,MALL_VAR_92 = ? ,MALL_VAR_93 = ? ,USEYN  = ? "
					+ " where SEQ = ? and COMPNO = ? and SHOPCD =? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, dto.getTitle()); // 제목
			pstmt.setString(++rowIdx, dto.getMemo()); // 메모
			pstmt.setString(++rowIdx, dto.getShopid()); // 아이디
			pstmt.setString(++rowIdx, dto.getSelmthdcd()); // 판매방식
			pstmt.setString(++rowIdx, dto.getProdtypcd()); // 서비스상품
			pstmt.setString(++rowIdx, dto.getMall_var_1()); // 상품홍보문구
			pstmt.setString(++rowIdx, dto.getMall_var_2()); // 원산지물품
			pstmt.setString(++rowIdx, dto.getMall_var_3()); // 원산지사용여부
			pstmt.setString(++rowIdx, dto.getMall_var_4()); // 원산지사용여부에따른내용
			pstmt.setString(++rowIdx, dto.getMall_var_5()); // 원산지다중등록여부
			pstmt.setString(++rowIdx, dto.getMall_var_6()); // 상품모델
			pstmt.setString(++rowIdx, dto.getMall_var_7()); // 상품수정모델
			pstmt.setString(++rowIdx, dto.getMall_var_8()); // 판매자상품코드
			pstmt.setString(++rowIdx, dto.getMall_var_9()); // 셀러캐시사용여부
			pstmt.setString(++rowIdx, dto.getProdstatcd()); // 상품상태
			pstmt.setString(++rowIdx, dto.getMinorselcnyn()); // 미성년자구매여부
			pstmt.setString(++rowIdx, dto.getMall_var_10()); // 지점선택
			pstmt.setString(++rowIdx, dto.getMall_var_11()); // 디즈니시리즈선택
			pstmt.setString(++rowIdx, dto.getNicknm()); // 닉네임
			pstmt.setString(++rowIdx, dto.getMall_var_12()); // 브랜드
			pstmt.setString(++rowIdx, dto.getMall_var_13()); // 가입신청URL
			pstmt.setString(++rowIdx, dto.getMall_var_94()); // 휴대폰약정여부
			pstmt.setString(++rowIdx, dto.getMall_var_14()); // 휴대폰요금제
			pstmt.setString(++rowIdx, dto.getMall_var_15()); // 유효일자
			pstmt.setString(++rowIdx, dto.getMall_var_16()); // 가격비교등록여부
			pstmt.setString(++rowIdx, dto.getFpseltermyn()); // 판매기간설정여부
			pstmt.setString(++rowIdx, dto.getProdfpselcd()); // 판매기간코드
			pstmt.setString(++rowIdx, dto.getMall_var_17()); // 1원폰상품여부
			pstmt.setString(++rowIdx, dto.getMall_var_18()); // 옵션설정여부
			pstmt.setString(++rowIdx, dto.getMall_var_19()); // 2단옵션적용여부
			pstmt.setString(++rowIdx, dto.getMall_var_20()); // 옵션값노출방식
			pstmt.setString(++rowIdx, dto.getMall_var_21()); // 작성형옵션여부
			pstmt.setString(++rowIdx, dto.getMall_var_22()); // 옵션명1
			pstmt.setString(++rowIdx, dto.getMall_var_23()); // 옵션명2
			pstmt.setString(++rowIdx, dto.getMall_var_24()); // 옵션명3
			pstmt.setString(++rowIdx, dto.getMall_var_25()); // 옵션명4
			pstmt.setString(++rowIdx, dto.getMall_var_26()); // 옵션명5
			pstmt.setString(++rowIdx, dto.getMall_var_27()); // 옵션명6
			pstmt.setString(++rowIdx, dto.getMall_var_28()); // 옵션명7
			pstmt.setString(++rowIdx, dto.getMall_var_29()); // 옵션명8
			pstmt.setString(++rowIdx, dto.getMall_var_30()); // 옵션명9
			pstmt.setString(++rowIdx, dto.getMall_var_31()); // 옵션명10
			pstmt.setString(++rowIdx, dto.getMall_var_32()); // 제휴가상품URL
			pstmt.setString(++rowIdx, dto.getMall_var_33()); // 최초출발일
			pstmt.setString(++rowIdx, dto.getMall_var_34()); // 마지막출발일
			pstmt.setString(++rowIdx, dto.getMall_var_35()); // 베네피아할인여부
			pstmt.setString(++rowIdx, dto.getMall_var_36()); // 베네피아할인금액및율
			pstmt.setString(++rowIdx, dto.getMall_var_37()); // 베네피아기간설정여부
			pstmt.setString(++rowIdx, dto.getMall_var_38()); // 베네피아시작일
			pstmt.setString(++rowIdx, dto.getMall_var_39()); // 베네피아종료일
			pstmt.setString(++rowIdx, dto.getMall_var_40()); // 최소구매수량제한여부
			pstmt.setString(++rowIdx, dto.getMall_var_41()); // 최소구매제한시수량
			pstmt.setString(++rowIdx, dto.getMall_var_42()); // 최대구매수량제한여부
			pstmt.setString(++rowIdx, dto.getMall_var_43()); // 1회제한시수량
			pstmt.setString(++rowIdx, dto.getMall_var_44()); // 기간제한시기간
			pstmt.setString(++rowIdx, dto.getMall_var_45()); // 기간제한시수량
			pstmt.setString(++rowIdx, dto.getMall_var_46()); // 사은품명
			pstmt.setString(++rowIdx, dto.getMall_var_47()); // 사은품기간시작일
			pstmt.setString(++rowIdx, dto.getMall_var_49()); // 사은품기간종료일
			pstmt.setString(++rowIdx, dto.getMall_var_50()); // 사은품정보
			pstmt.setString(++rowIdx, dto.getMall_var_51()); // 배송정보템플릿
			pstmt.setString(++rowIdx, dto.getMall_var_52()); // 배송가능지역
			pstmt.setString(++rowIdx, dto.getExpwycd()); // 배송방법
			pstmt.setString(++rowIdx, dto.getMall_var_95()); // 방문수령
			pstmt.setString(++rowIdx, dto.getSendexp()); // 발송택배사
			pstmt.setString(++rowIdx, dto.getMall_var_53()); // 발송예정일
			pstmt.setString(++rowIdx, dto.getMall_var_54()); // 방문수령주소
			pstmt.setString(++rowIdx, dto.getGbldivyn()); // 전세계배송여부
			pstmt.setString(++rowIdx, dto.getGblwght()); // 전세계배송무게
			pstmt.setString(++rowIdx, dto.getHscd()); // HS코드
			pstmt.setString(++rowIdx, dto.getMall_var_55()); // 출고지주소해외여부
			pstmt.setString(++rowIdx, dto.getAddrout()); // 출고주소
			pstmt.setString(++rowIdx, dto.getMall_var_56()); // 교환반품주소해외여부
			pstmt.setString(++rowIdx, dto.getAddrin()); // 교환/반품주소
			pstmt.setString(++rowIdx, dto.getShiptypcd()); // 배송비조건
			pstmt.setString(++rowIdx, dto.getShipprc()); // 기본배송비
			pstmt.setString(++rowIdx, dto.getCondiprice()); // 상품조건별시 뒤에금액
			pstmt.setString(++rowIdx, dto.getMall_var_57()); // 수량차등별수량1
			pstmt.setString(++rowIdx, dto.getMall_var_58()); // 수량차등별수량2
			pstmt.setString(++rowIdx, dto.getDiffprice1()); // 수량차등별금액1
			pstmt.setString(++rowIdx, dto.getMall_var_59()); // 수량차등별금액2
			pstmt.setString(++rowIdx, dto.getJejuprc()); // 제주배송비
			pstmt.setString(++rowIdx, dto.getIslandprc()); // 도서산간배송비
			pstmt.setString(++rowIdx, dto.getPrctypcd()); // 선결제여부
			pstmt.setString(++rowIdx, dto.getBndyn()); // 묶음배송여부
			pstmt.setString(++rowIdx, dto.getRetprc()); // 반품배송비
			pstmt.setString(++rowIdx, dto.getExcprc()); // 교환배송비
			pstmt.setString(++rowIdx, dto.getRudcd()); // 왕복편도여부
			pstmt.setString(++rowIdx, dto.getAsdtl()); // A/S안내
			pstmt.setString(++rowIdx, dto.getRtexcdtl()); // 교환/반품안내
			pstmt.setString(++rowIdx, dto.getMall_var_60()); // 복수구매할인여부
			pstmt.setString(++rowIdx, dto.getMall_var_61()); // 복수할인기준
			pstmt.setString(++rowIdx, dto.getMall_var_62()); // 복수할인금액및갯수1
			pstmt.setString(++rowIdx, dto.getMall_var_63()); // 복수할인금액및갯수2
			pstmt.setString(++rowIdx, dto.getMall_var_64()); // 복수할인방법기준
			pstmt.setString(++rowIdx, dto.getMall_var_65()); // 구입처선택
			pstmt.setString(++rowIdx, dto.getMall_var_66()); // 대표이미지
			pstmt.setString(++rowIdx, dto.getMall_var_67()); // 모바일상세설명이미지선택
			pstmt.setString(++rowIdx, dto.getMall_var_68()); // 모바일쿠폰선택여부
			pstmt.setString(++rowIdx, dto.getMall_var_69()); // 해외구매대행상품여부
			pstmt.setString(++rowIdx, dto.getMall_var_70()); // 상품명앞추가문구
			pstmt.setString(++rowIdx, dto.getMall_var_71()); // 상품명뒷추가문구
			pstmt.setString(++rowIdx, dto.getMall_var_72()); // 상품설명상단추가문구
			pstmt.setString(++rowIdx, dto.getMall_var_73()); // 상품설명하단추가문구
			pstmt.setString(++rowIdx, dto.getMall_var_74()); // 기본즉시할인여부
			pstmt.setString(++rowIdx, dto.getMall_var_75()); // 기본즉시할인금액
			pstmt.setString(++rowIdx, dto.getMall_var_76()); // 기본즉시할인기준
			pstmt.setString(++rowIdx, dto.getMall_var_77()); // 쿠폰지급기간설정여부
			pstmt.setString(++rowIdx, dto.getMall_var_78()); // 쿠폰지급기간종료일
			pstmt.setString(++rowIdx, dto.getMall_var_79()); // 무이자할부제공여부
			pstmt.setString(++rowIdx, dto.getMall_var_80()); // 무이자할부개월수
			pstmt.setString(++rowIdx, dto.getMall_var_81()); // 무이자할부회수제한
			pstmt.setString(++rowIdx, dto.getMall_var_82()); // 희망할인설정여부
			pstmt.setString(++rowIdx, dto.getMall_var_83()); // 희망후원금액
			pstmt.setString(++rowIdx, dto.getMall_var_84()); // 희망후원할인기준
			pstmt.setString(++rowIdx, dto.getMall_var_85()); // SK포인트지급여부
			pstmt.setString(++rowIdx, dto.getMall_var_86()); // SK포인트금액
			pstmt.setString(++rowIdx, dto.getMall_var_87()); // SK포인트할인기준
			pstmt.setString(++rowIdx, dto.getMall_var_88()); // 상품리뷰여부
			pstmt.setString(++rowIdx, dto.getMall_var_89()); // 플러스광고여부
			pstmt.setString(++rowIdx, dto.getMall_var_90()); // 플러스UP광고여부
			pstmt.setString(++rowIdx, dto.getMall_var_91()); // GIF광고여부
			pstmt.setString(++rowIdx, dto.getMall_var_92()); // 볼드체광고여부
			pstmt.setString(++rowIdx, dto.getMall_var_93()); // 배경색광고여부
			pstmt.setString(++rowIdx, dto.getUseyn()); // 사용여부

			pstmt.setString(++rowIdx, joinlist.getSeq());
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, joinlist.getShopcd());
			pstmt.executeUpdate();

			System.out.println("[insertdtl-ShopAddrDtlInsert]" + pstmt.toString());

			String sql_mst = " update shopaddrmst set TITLE = ? , SELMTHDCD = ? , USEYN = ? , UPDATEDT = ? where compno = ? and shopcd = ? and seq = ? ";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getSelmthdcd());
			pstmt.setString(3, dto.getUseyn());
			pstmt.setString(4, YDMATimeUtil.getCurrentTimeByYDFormat());

			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(6, joinlist.getShopcd());
			pstmt.setString(7, shopseq);

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;

	}

	public List<List<String>> getShopAddrList(String shopcd) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT IFNULL(b.title,''),IFNULL(b.seq,''),IFNULL(a.insertdt,''),IFNULL(a.updatedt,'') "
					+ " FROM SHOPADDRMST a JOIN shopaddr11stdtl b ON a.SHOPCD=b.SHOPCD and a.compno = b.compno and a.seq = b.seq "
					+ " WHERE b.shopcd = ? and b.compno = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, shopcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(++rowno + "");
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add("카테고리매핑을 가져와야될꺼같음");
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

	public List<List<String>> getShopAddrListCheck(String shopcd, int result, String search, String title, String addr)
			throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT IFNULL(b.title,''),IFNULL(b.seq,''),IFNULL(a.insertdt,''),IFNULL(a.updatedt,'') "
					+ " FROM SHOPADDRMST a JOIN shopaddr11stdtl b ON a.SHOPCD=b.SHOPCD and a.compno = b.compno and a.seq = b.seq "
					+ " WHERE b.shopcd = ? and b.compno = ? ";
			if (search.equals("1")) {
				sql += " and b.title LIKE ? ";
			} else if (search.equals("2")) {
				sql += " and b.seq LIKE ? ";
			}
			if (result == 0) {
				sql += " order by b.title";
			} else if (result == 1) {
				sql += " order by a.insertdt ";
			} else {
				sql += " order by a.updatedt ";
			}

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, shopcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			if (search.equals("1")) {
				pstmt.setString(3, "%" + title + "%");
			} else if (search.equals("2")) {
				pstmt.setString(3, "%" + addr + "%");
			}

			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(++rowno + "");
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add("카테고리매핑을 가져와야될꺼같음");
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

	public List<ShopProductAdditionDto> getAdditionaOneList() throws Exception {
		List<ShopProductAdditionDto> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(compno,''), ifnull(SHOPCD,''),ifnull(SEQ,''),ifnull(SHOPID,''),ifnull(TITLE,''),ifnull(SELMTHDCD,''),ifnull(PRODTYPCD,''),ifnull(PRODSTATCD,''),ifnull(MINORSELCNYN,''),"
					+ " ifnull(NICKNM,''),ifnull(FPSELTERMYN,''),ifnull(PRODFPSELCD,''),ifnull(EXPWYCD,''),ifnull(SENDEXP,''),ifnull(GBLDIVYN,''),ifnull(GBLWGHT,''),ifnull(HSCD,''),ifnull(ADDROUT,''),ifnull(ADDRIN,''),"
					+ " ifnull(PRCTYPCD,''),ifnull(BNDYN,''), ifnull(RUDCD,''),ifnull(RETPRC,''),ifnull(EXCPRC,''),ifnull(SHIPTYPCD,''),ifnull(SHIPPRC,''),ifnull(JEJUPRC,''),ifnull(ISLANDPRC,''),ifnull(ASDTL,''),"
					+ " ifnull(RTEXCDTL,''),ifnull(USEYN,''), ifnull(DIFFPRICE,''),ifnull(CONDIPRICE,'') "
					+ " from shopaddr11stdtl where compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getShopMstOneSelectAllList]" + pstmt.toString());

			rs = pstmt.executeQuery();

//			while (rs.next()) {
//				ShopProductAdditionDto dto = new ShopProductAdditionDto();
//				int columnIndex = 0;
//				dto.setCompno(rs.getString(++columnIndex));
//				dto.setShopcd(rs.getString(++columnIndex));
//				dto.setSeq(rs.getString(++columnIndex));
//				dto.setShopid(rs.getString(++columnIndex));
//				dto.setTitle(rs.getString(++columnIndex));
//				dto.setSelmthdcd(rs.getString(++columnIndex));
//				dto.setProdtypcd(rs.getString(++columnIndex));
//				dto.setProdstatcd(rs.getString(++columnIndex));
//				dto.setMinorselcnyn(rs.getString(++columnIndex));
//				dto.setNicknm(rs.getString(++columnIndex));
//				dto.setFpseltermyn(rs.getString(++columnIndex));
//				dto.setProdfpselcd(rs.getString(++columnIndex));
//				dto.setExpwycd(rs.getString(++columnIndex));
//				dto.setSendexp(rs.getString(++columnIndex));
//				dto.setGbldivyn(rs.getString(++columnIndex));
//				dto.setGblwght(rs.getString(++columnIndex));
//				dto.setHscd(rs.getString(++columnIndex));
//				dto.setAddrout(rs.getString(++columnIndex));
//				dto.setAddrin(rs.getString(++columnIndex));
//				dto.setPrctypcd(rs.getString(++columnIndex));
//				dto.setBndyn(rs.getString(++columnIndex));
//				dto.setRudcd(rs.getString(++columnIndex));
//				dto.setRetprc(rs.getString(++columnIndex));
//				dto.setExcprc(rs.getString(++columnIndex));
//				dto.setShiptypcd(rs.getString(++columnIndex));
//				dto.setShipprc(rs.getString(++columnIndex));
//				dto.setJejuprc(rs.getString(++columnIndex));
//				dto.setIslandprc(rs.getString(++columnIndex));
//				dto.setAsdtl(rs.getString(++columnIndex));
//				dto.setRtexcdtl(rs.getString(++columnIndex));
//				dto.setUseyn(rs.getString(++columnIndex));
//				dto.setDiffprice(rs.getString(++columnIndex));
//				dto.setCondiprice(rs.getString(++columnIndex));
//
//			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<String> getShopdtlOneList(String id, String shopcd) throws Exception {
		// ShopCategoryDto dto = new ShopCategoryDto();
		List<String> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(COMPNO,''), ifnull(SHOPCD,''),ifnull(SHOPSEQ,''),ifnull(SHOPPINGID,''),ifnull(PASSWORD,''),ifnull(NICKNM2,''),ifnull(NICKNM1,''),ifnull(AUTHKEY1,''),ifnull(AUTHKEY2,''),"
					+ " ifnull(ORDEREXCEL1,''),ifnull(ORDEREXCEL2,''),ifnull(ORDEREXCEL3,''),ifnull(APIKEY,''),ifnull(DEALTREAD,'') "
					+ " from shopdtl where compno = ? and shopcd = ? and SHOPPINGID = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setString(3, id);
			System.out.println("[getShopMstOneSelectAllList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
//				dto.setShopcd(shopcd);
//				dto.setShopseq(shopseq);
//				dto.setShoppingid(shoppingid);
//				dto.setPassword(password);
//				dto.setNicknm2(nicknm2);
//				dto.setNicknm1(nicknm1);
//				dto.setAuthkey1(authkey1);
//				dto.setAuthkey2(authkey2);
//				dto.setOrderexcel1(orderexcel1);
//				dto.setOrderexcel2(orderexcel2);
//				dto.setOrderexcel3(orderexcel3);
//				dto.setApikey(apikey);
//				dto.dealt
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<List<String>> getShopidAlllist() throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(COMPNO,'0'), ifnull(SHOPCD,''), ifnull(SHOPSEQ,''), ifnull(SHOPPINGID,''), ifnull(PASSWORD,''), ifnull(NICKNM2,''), ifnull(NICKNM1,''), ifnull(AUTHKEY1,''), ";
			sql += " ifnull(AUTHKEY2,''), ifnull(ORDEREXCEL1,''), ifnull(ORDEREXCEL2,''), ifnull(ORDEREXCEL3,''),  ifnull(APIKEY,''), ifnull(DEALTREAD,'' )";
			sql += " FROM shopdtl ";
			sql += " where compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));

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

	// 샵코드전부 가지고 오기
	public List<List<String>> getShopCodeAllList() throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(a.COMPNO,'0'), ifnull(a.SHOPCD,''), ifnull(b.SHOPNM,''), ifnull(b.CUSTNM,''), ifnull(b.PRODREGIS,''), ifnull(b.PRODMODIFY,''), ifnull(b.PRODSOLDOUT,''), ifnull(b.STOCKSEND,''), ";
			sql += " ifnull(b.ORDERCOLLECT,''), ifnull(b.INVOICSEND,''), ifnull(b.CLAIMCOLLECT,''), ifnull(b.QUESTION,''),  ifnull(b.MARKETCATEG,''), ifnull(a.DEALTRADE,'' ), ifnull(a.IDNUM,''), ifnull(b.SHOPURL,'')";
			sql += " FROM shopmst a join shopinfo b on a.shopcd = b.shopcd ";
			sql += " where a.compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));

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

	public List<String> getShopCategory(String shopcatno, String shopcd) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(COMPNO,'0'), ifnull(SHOPCD,''), ifnull(SHOPCATNO,'0'), ifnull(SHOPCATNM,''), ifnull(SHOPLAGCATCD,''), ifnull(SHOPMIDCATCD,''), ifnull(SHOPSMLCATCD,''), ifnull(SHOPDETCATCD,''), ";
			sql += " ifnull(SERVICEPROD,''), ifnull(USE_YN,''), ifnull(SHOPGENERAL,''), ifnull(SHOPID,''),  ifnull(SHOPCOMMIS,'0'), ifnull(INSERTDT,''), ifnull(MODIFYDT,'' ) ";
			sql += " FROM shopcatinf ";
			sql += " where compno = ? and shopcd = ? and shopcatno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setString(3, shopcatno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;

				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	// 검색클릭시 전체 가지고 오기
	public List<ShoppingMallDto> getCategoryList(String shopcd, int data, String prodFrom, String prodTo, String use_yn,
			int searchcode, String serchnm) throws Exception {
		List<ShoppingMallDto> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// FROM SHOPADDRMST a JOIN shopaddr11stdtl b ON a.SHOPCD=b.SHOPCD and a.compno =
			// b.compno and a.seq = b.seq "
			String sql = "select ifnull(a.SHOPCD,'') , ifnull(a.SHOPCATNO,'0'), ifnull(c.MARKETCATEG,''), ifnull(a.SHOPCATNM,''), ifnull(a.SHOPLAGCATCD,''), ifnull(a.SHOPMIDCATCD,''), ifnull(a.SHOPSMLCATCD,''), ifnull(a.SHOPDETCATCD,''), "
					+ " ifnull(a.SERVICEPROD,''), ifnull(a.USE_YN,'') ,ifnull(a.SHOPGENERAL,''), ifnull(a.SHOPID,''), ifnull(a.INSERTDT,''), ifnull(a.MODIFYDT,'')  "
					+ " from shopcatinf a join shopmst b on a.compno = b.compno and a.shopcd = b.shopcd join shopinfo c on b.shopcd = c.shopcd where a.compno = ? and a.shopcd = ? ";
			if (data == 0) {
				sql += " and a.INSERTDT >= ? and a.INSERTDT <= ? ";
			} else {
				sql += " and a.MODIFYDT >= ? and a.MODIFYDT <= ? ";
			}
			if (searchcode == 0) {
				sql += " and a.SHOPCATNM like ?  ";
			} else {
				sql += " and a.SHOPCATNO like ?  ";
			}

			if (!use_yn.equals("")) {
				sql += " and a.USE_YN => ? ";
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setString(3, prodFrom);
			pstmt.setString(4, prodTo);
			pstmt.setString(5, "%" + serchnm + "%");
			if (!use_yn.equals("")) {
				pstmt.setString(6, use_yn);
			}
			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ShoppingMallDto dto = new ShoppingMallDto();
				dto.setRowno("" + (++rowno));
				dto.setShopcd(rs.getString(++i));
				dto.setShopcatno(rs.getString(++i));
				dto.setMargetcateg(rs.getString(++i));
				dto.setShopcatnm(rs.getString(++i));
				dto.setShoplagcatcd(rs.getString(++i));
				dto.setShopmidcatcd(rs.getString(++i));
				dto.setShopsmlcatcd(rs.getString(++i));
				dto.setShopdetcatcd(rs.getString(++i));
				dto.setServiceprod(rs.getString(++i));
				dto.setUse_yn(rs.getString(++i));
				dto.setShopgeneral(rs.getString(++i));
				dto.setShopid(rs.getString(++i));
				dto.setInsertdt(rs.getString(++i));
				dto.setModifydt(rs.getString(++i));

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
	 * 마스터 주문 송장 번호 업데이트 ..
	 *
	 */
	// 디테일 인설트
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

	public List<List<String>> getCategMapping(String code, String managercode) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "";
			if (managercode.equals("1")) {
				sql = " select ifnull(CODE,''), ifnull(SHOPCD,''), ifnull(SHOPCATNO,'0')  FROM categlgmap ";
			} else if (managercode.equals("2")) {
				sql = " select ifnull(CODE,''), ifnull(SHOPCD,''), ifnull(SHOPCATNO,'0')  FROM categmdmap ";
			} else if (managercode.equals("3")) {
				sql = " select ifnull(CODE,''), ifnull(SHOPCD,''), ifnull(SHOPCATNO,'0')  FROM categsmmap ";
			} else {
				sql = " select ifnull(CODE,''), ifnull(SHOPCD,''), ifnull(SHOPCATNO,'0')  FROM categdtlmap ";
			}

			sql += " where compno = ? and CODE = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, code);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));

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

	public List<ShoppingMallDto> getShopCategoryCheck(List<List<String>> contents) throws Exception {
		List<ShoppingMallDto> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// FROM SHOPADDRMST a JOIN shopaddr11stdtl b ON a.SHOPCD=b.SHOPCD and a.compno =
			// b.compno and a.seq = b.seq "
			String sql = "select ifnull(SHOPCD,'') , ifnull(SHOPCATNO,'0'), ifnull(SHOPCATNM,''), ifnull(SHOPLAGCATCD,''), ifnull(SHOPMIDCATCD,''), ifnull(SHOPSMLCATCD,''), ifnull(SHOPDETCATCD,''), "
					+ " ifnull(SERVICEPROD,''), ifnull(USE_YN,'') ,ifnull(SHOPGENERAL,''), ifnull(SHOPID,''), ifnull(INSERTDT,''), ifnull(MODIFYDT,'')  "
					+ " from shopcatinf  where compno = ? and SHOPCATNO = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			for (List<String> catelist : contents) {
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, catelist.get(2));

				rs = pstmt.executeQuery();
				int rowno = 0;
				while (rs.next()) {
					int i = 0;
					ShoppingMallDto dto = new ShoppingMallDto();
					dto.setRowno("" + (++rowno));
					dto.setShopcd(rs.getString(++i));
					dto.setShopcatno(rs.getString(++i));
					dto.setShopcatnm(rs.getString(++i));
					dto.setShoplagcatcd(rs.getString(++i));
					dto.setShopmidcatcd(rs.getString(++i));
					dto.setShopsmlcatcd(rs.getString(++i));
					dto.setShopdetcatcd(rs.getString(++i));
					dto.setServiceprod(rs.getString(++i));
					dto.setUse_yn(rs.getString(++i));
					dto.setShopgeneral(rs.getString(++i));
					dto.setShopid(rs.getString(++i));
					dto.setInsertdt(rs.getString(++i));
					dto.setModifydt(rs.getString(++i));

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

	// 카테고리선택삭제
	public int CategMappingDelete(ShoppingMallDto catelist, String managercode) throws Exception {
		List<ShoppingMallDto> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// FROM SHOPADDRMST a JOIN shopaddr11stdtl b ON a.SHOPCD=b.SHOPCD and a.compno =
			// b.compno and a.seq = b.seq "
			String sql = "delete from  categdtlmap where compno = ? and SHOPCATNO = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, catelist.getShopcatno());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public List<String> getShopHelpinfo(String shopcd) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(LOGININF,''), ifnull(ORDERINF,''), ifnull(INVOICEINF,''), ifnull(PRODININF,''), ifnull(PRODMODINF,''), ifnull(QUESTINF,'') ";
			sql += " FROM shopinfo where shopcd = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, shopcd);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public boolean isExistCategoryMap(List<String> list, ShoppingMallDto catelist, String managercode)
			throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "";
			if (managercode.equals("1")) {
				sql = " select ifnull(CODE,'')  FROM categlgmap ";
			} else if (managercode.equals("2")) {
				sql = " select ifnull(CODE,'') FROM categmdmap ";
			} else if (managercode.equals("3")) {
				sql = " select ifnull(CODE,'') FROM categsmmap ";
			} else {
				sql = " select ifnull(CODE,'')  FROM categdtlmap ";
			}

			sql += " where compno = ? and CODE = ? and shopcd = ? and shopcatno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, list.get(0));
			pstmt.setString(3, catelist.getShopcd());
			pstmt.setString(4, catelist.getShopcatno());
			rs = pstmt.executeQuery();
			flag = rs.next();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return flag;
	}

	// 쿠팡카테고리
	public List<List<String>> getCoupangCategory(String apiKey, String authkey1, String authkey2) {
		List<List<String>> content = new ArrayList<>();
		// replace prod url when you need
		String HOST = "api-gateway.coupang.com";
		int PORT = 443;
		String SCHEMA = "https";
		// replace with your own accessKey
		String ACCESS_KEY = authkey1;
		// replace with your own secretKey
		String SECRET_KEY = authkey2;

		// params
		String method = "GET";
		// replace with your own vendorId
		String path = "/v2/providers/seller_api/apis/api/v1/marketplace/meta/display-categories";
		CloseableHttpClient client = null;
		try {
			// create client
			client = HttpClients.createDefault();

			// build uri
			URIBuilder uriBuilder = new URIBuilder().setPath(path);

			/********************************************************/
			// authorize, demonstrate how to generate hmac signature here
			String authorization = Hmac.generate(method, uriBuilder.build().toString(), SECRET_KEY, ACCESS_KEY);
			// print out the hmac key
			System.out.println(authorization);
			/********************************************************/

			uriBuilder.setScheme(SCHEMA).setHost(HOST).setPort(PORT);
			HttpGet get = new HttpGet(uriBuilder.build().toString());
			/********************************************************/
			// set header, demonstarte how to use hmac signature here
			get.addHeader("Authorization", authorization);
			/********************************************************/
			get.addHeader("content-type", "application/json");
			CloseableHttpResponse response = null;

			try {
				// execute get request
				response = client.execute(get);
				System.out.println(response.getStatusLine().getStatusCode());
				// print result
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();
					BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
					JsonParser parser = new JsonParser();
					JsonObject resObject = (JsonObject) parser.parse(br);
					JsonObject memberArray = (JsonObject) resObject.get("data");
					JsonArray memberArray1 = (JsonArray) memberArray.get("child");
					for (int i = 0; i < memberArray1.size(); i++) {
						List<String> list1 = new ArrayList<>();
						JsonObject object1 = (JsonObject) memberArray1.get(i);
						list1.add("1");
						list1.add(object1.get("name").getAsString());
						list1.add(object1.get("displayItemCategoryCode").getAsString());
						list1.add("");
						list1.add("");
						list1.add("");
						list1.add("0");
						content.add(list1);
						JsonArray memberArray2 = (JsonArray) object1.get("child");
						if (memberArray2.size() > 0) {
							for (int k = 0; k < memberArray2.size(); k++) {
								List<String> list2 = new ArrayList<>();
								JsonObject object2 = (JsonObject) memberArray2.get(k);
								list2.add("2");
								list2.add(object2.get("name").getAsString());
								list2.add(object2.get("displayItemCategoryCode").getAsString());
								list2.add("");
								list2.add("");
								list2.add("");
								list2.add(object1.get("displayItemCategoryCode").getAsString());
								content.add(list2);
								JsonArray memberArray3 = (JsonArray) object2.get("child");
								if (memberArray3.size() > 0) {
									for (int j = 0; j < memberArray3.size(); j++) {
										List<String> list3 = new ArrayList<>();
										JsonObject object3 = (JsonObject) memberArray3.get(j);
										list3.add("3");
										list3.add(object3.get("name").getAsString());
										list3.add(object3.get("displayItemCategoryCode").getAsString());
										list3.add("");
										list3.add("");
										list3.add("");
										list3.add(object2.get("displayItemCategoryCode").getAsString());
										content.add(list3);
										JsonArray memberArray4 = (JsonArray) object3.get("child");
										if (memberArray4.size() > 0) {
											for (int l = 0; l < memberArray4.size(); l++) {
												List<String> list4 = new ArrayList<>();
												JsonObject object4 = (JsonObject) memberArray4.get(l);
												list4.add("4");
												list4.add(object4.get("name").getAsString());
												list4.add(object4.get("displayItemCategoryCode").getAsString());
												list4.add("");
												list4.add("");
												list4.add("");
												list4.add(object3.get("displayItemCategoryCode").getAsString());
												content.add(list4);
											}
										}

									}
								}

							}
						}

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (response != null) {
					try {
						response.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return content;
	}

	// 쿠팡저장
	public int ShopAddrDtlCoupangInsert(String shopcd, String title, String memo, String selmthdcd, String shopid,
			String chargemd, String saleagencyfee, String offercondition, String originalPricd, String company_Goods_cd,
			String emptybarcode, String emptybarcodereason, String adultonly, String parallelimported,
			String overseaspurchased, String pccneeded, String receipt, String message, String outboundShippingTimeDay,
			String maximumbuyforperson, String maximumbuyforpersonperiod, String repimage, String rectimage,
			String carimage, String noticeimage, String ptimage, String otherimage, String incomeimage, String prodnm,
			String deliverymethod, String deliverycompanycd, String deliverychagetype, String deliverycharge,
			String freeshipoveramount, String deliverychargeonreturn, String remotearea, String uniondeliverytype,
			String remoteareadeliverable, String outboundshippingplacecode, String outaddress, String outaddressdetail,
			String returncentercode, String companycontactnumber, String returnzipcode, String returnaddress,
			String returnaddressdetail, String returncharge, String returnchargevendor, String afterserviceinformation,
			String afterservicecontactnumber, String salesstdate, String salesendate, String prodnmfirst,
			String prodnmend, String prodnmdetailtop, String prodnmdetailbottom, String useyn, int seq)
			throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "insert into shopaddrcoupangdtl(compno,seq, shopcd,TITLE, memo, SELMTHDCD, shopid, CHARGEMD, SALEAGENCYFEE, OFFERCONDITION, ORIGINALPRICD, COMPANY_GOODS_CD, barcd, barcdreason,"
					+ " ADULTONLY, PARALLELIMPORT, OVERSEASPURCHASED,PCCNEEDED, OVERSEASRECEIPT, MESSAGE, OUTBOUNDSHOPTIMEDAY, MAXIMUMBUYPERSON, MAXIMUMBUYPERSONPERIOD, REPIMAGE, RECTIMAGE,CARIMAGE, "
					+ " NOTICEIMAGE, PAINTIMAGE, OTHERIMAGE, INCOMEIMAGE, PRODNM, DELIVERYMETHOD, DELIVERYCOMPANYCD, DELIVERYCHARGETYPE, DELIVERYCHARGE,FREESHIPOVERCHARGE,DELIVERYRETCHARGE,"
					+ " REMOTEAREACHARGE,UNIONDELIVERYTYPE, REMOTEAREADELIVERABLE,OUTADDRCD,OUTADDRESS,OUTADDRESSDTL,RETURNADDRCD,RETURNADDRCONTACTNUM,RETURNADDRZIPCD,RETURNADDRESS,RETURNADDRESSDEL,"
					+ " RETURNCHARGE,RETURNCHARGEVENDOR, ASINFORMATION,ASCONTACTNUM,SALESSTDATE, SALESENDATE, PRODNMFIRST,PRODNMEND,PRODNMDTLTOP,PRODNMDTLBOTTOM,USEYN) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ? , ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,?,?, ?, ?, ? )";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setInt(++rowIdx, seq);
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, title);
			pstmt.setString(++rowIdx, memo);
			pstmt.setString(++rowIdx, selmthdcd);
			pstmt.setString(++rowIdx, shopid);
			pstmt.setString(++rowIdx, chargemd);
			pstmt.setString(++rowIdx, saleagencyfee);
			pstmt.setString(++rowIdx, offercondition);
			pstmt.setString(++rowIdx, originalPricd);
			pstmt.setString(++rowIdx, company_Goods_cd);
			pstmt.setString(++rowIdx, emptybarcode);
			pstmt.setString(++rowIdx, emptybarcodereason);
			pstmt.setString(++rowIdx, adultonly);
			pstmt.setString(++rowIdx, parallelimported);
			pstmt.setString(++rowIdx, overseaspurchased);
			pstmt.setString(++rowIdx, pccneeded);
			pstmt.setString(++rowIdx, receipt);
			pstmt.setString(++rowIdx, message);
			pstmt.setString(++rowIdx, outboundShippingTimeDay);
			pstmt.setString(++rowIdx, maximumbuyforperson);
			pstmt.setString(++rowIdx, maximumbuyforpersonperiod);
			pstmt.setString(++rowIdx, repimage);
			pstmt.setString(++rowIdx, rectimage);
			pstmt.setString(++rowIdx, carimage);
			pstmt.setString(++rowIdx, noticeimage);
			pstmt.setString(++rowIdx, ptimage);
			pstmt.setString(++rowIdx, otherimage);
			pstmt.setString(++rowIdx, incomeimage);
			pstmt.setString(++rowIdx, prodnm);
			pstmt.setString(++rowIdx, deliverymethod);
			pstmt.setString(++rowIdx, deliverycompanycd);
			pstmt.setString(++rowIdx, deliverychagetype);
			pstmt.setString(++rowIdx, deliverycharge);
			pstmt.setString(++rowIdx, freeshipoveramount);
			pstmt.setString(++rowIdx, deliverychargeonreturn);
			pstmt.setString(++rowIdx, remotearea);
			pstmt.setString(++rowIdx, uniondeliverytype);
			pstmt.setString(++rowIdx, remoteareadeliverable);
			pstmt.setString(++rowIdx, outboundshippingplacecode);
			pstmt.setString(++rowIdx, outaddress);
			pstmt.setString(++rowIdx, outaddressdetail);
			pstmt.setString(++rowIdx, returncentercode);
			pstmt.setString(++rowIdx, companycontactnumber);
			pstmt.setString(++rowIdx, returnzipcode);
			pstmt.setString(++rowIdx, returnaddress);
			pstmt.setString(++rowIdx, returnaddressdetail);
			pstmt.setString(++rowIdx, returncharge);
			pstmt.setString(++rowIdx, returnchargevendor);
			pstmt.setString(++rowIdx, afterserviceinformation);
			pstmt.setString(++rowIdx, afterservicecontactnumber);
			pstmt.setString(++rowIdx, salesstdate);
			pstmt.setString(++rowIdx, salesendate);
			pstmt.setString(++rowIdx, prodnmfirst);
			pstmt.setString(++rowIdx, prodnmend);
			pstmt.setString(++rowIdx, prodnmdetailtop);
			pstmt.setString(++rowIdx, prodnmdetailbottom);
			pstmt.setString(++rowIdx, useyn);

			pstmt.executeUpdate();

			System.out.println("[insertdtl-ShopAddrDtlInsert]" + pstmt.toString());

			String sql_mst = " insert into shopaddrmst (compno, SHOPCD, SEQ,  TITLE, SELMTHDCD, USEYN, INSERTDT) "
					+ "  VALUES (?, ?,?,?,?,?,?)";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setInt(3, seq);
			pstmt.setString(4, title);
			pstmt.setString(5, selmthdcd);
			pstmt.setString(6, useyn);
			pstmt.setString(7, YDMATimeUtil.getCurrentTimeByYDFormat());

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public List<String> shopCoupangDetailjoinList(List<String> list) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> listjoin = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT IFNULL(dtl.shopcd,''),IFNULL(dtl.seq,''),IFNULL(dtl.title,''), ifnull(memo,''), IFNULL(dtl.selmthdcd,''),IFNULL(dtl.SHOPID,''),"
					+ " IFNULL(dtl.CHARGEMD,''),IFNULL(dtl.SALEAGENCYFEE,'0'),IFNULL(dtl.OFFERCONDITION,''),"
					+ " IFNULL(dtl.ORIGINALPRICD,''), IFNULL(dtl.COMPANY_GOODS_CD,''),IFNULL(dtl.BARCD,''),IFNULL(dtl.BARCDREASON,''),IFNULL(dtl.ADULTONLY,''),IFNULL(dtl.PARALLELIMPORT,''),"
					+ " IFNULL(dtl.OVERSEASPURCHASED,''),IFNULL(dtl.PCCNEEDED,''), IFNULL(dtl.OVERSEASRECEIPT,''),IFNULL(dtl.MESSAGE,''),IFNULL(dtl.OUTBOUNDSHOPTIMEDAY,'0'),IFNULL(dtl.MAXIMUMBUYPERSON,'0'),"
					+ " IFNULL(dtl.MAXIMUMBUYPERSONPERIOD,'0'), IFNULL(dtl.REPIMAGE,''),IFNULL(dtl.RECTIMAGE,''),IFNULL(dtl.CARIMAGE,''), IFNULL(dtl.NOTICEIMAGE,''),IFNULL(dtl.PAINTIMAGE,''), "
					+ " IFNULL(dtl.OTHERIMAGE,''),IFNULL(dtl.INCOMEIMAGE,''), IFNULL(dtl.PRODNM,''),IFNULL(dtl.DELIVERYMETHOD,''), ifnull(dtl.DELIVERYCOMPANYCD,''), ifnull(dtl.DELIVERYCHARGETYPE,''), "
					+ " ifnull(dtl.DELIVERYCHARGE,'0'), ifnull(dtl.FREESHIPOVERCHARGE,'0'), ifnull(dtl.DELIVERYRETCHARGE,'0'), ifnull(dtl.REMOTEAREACHARGE,'0'),ifnull(dtl.UNIONDELIVERYTYPE,''), "
					+ " ifnull(dtl.REMOTEAREADELIVERABLE,''),ifnull(dtl.OUTADDRCD,''),ifnull(dtl.OUTADDRESS,''),ifnull(dtl.OUTADDRESSDTL,''),ifnull(dtl.RETURNADDRCD,''), ifnull(RETURNADDRCONTACTNUM,''), "
					+ " ifnull(dtl.RETURNADDRZIPCD,''),ifnull(dtl.RETURNADDRESS,''),ifnull(dtl.RETURNADDRESSDEL,''),ifnull(dtl.RETURNCHARGE,'0'), ifnull(dtl.RETURNCHARGEVENDOR,''),ifnull(dtl.ASINFORMATION,''), "
					+ " ifnull(dtl.ASCONTACTNUM,''),ifnull(dtl.SALESSTDATE,''),ifnull(dtl.SALESENDATE,''),"
					+ " ifnull(dtl.PRODNMFIRST,''),ifnull(dtl.PRODNMEND,''),ifnull(dtl.PRODNMDTLTOP,''), ifnull(dtl.PRODNMDTLBOTTOM,''),ifnull(dtl.USEYN,'') "
					+ " FROM SHOPADDRMST addr JOIN SHOPMST MST ON MST.SHOPCD=ADDR.SHOPCD and MST.compno = ADDR.compno JOIN shopaddrcoupangdtl dtl ON ADDR.seq = dtl.seq and ADDR.compno = dtl.compno join shopinfo a on "
					+ " mst.shopcd = a.shopcd " + " WHERE addr.seq = ? AND a.shopnm = ? and mst.compno = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, list.get(0));
			pstmt.setString(2, list.get(1));
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[shopDetailjoinList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return listjoin;
	}

	public int ShopAddrDtlCoupangUpdate(String shopcd, String title, String memo, String selmthdcd, String shopid,
			String chargemd, String saleagencyfee, String offercondition, String originalPricd, String company_Goods_cd,
			String emptybarcode, String emptybarcodereason, String adultonly, String parallelimported,
			String overseaspurchased, String pccneeded, String receipt, String message, String outboundShippingTimeDay,
			String maximumbuyforperson, String maximumbuyforpersonperiod, String repimage, String rectimage,
			String carimage, String noticeimage, String ptimage, String otherimage, String incomeimage, String prodnm,
			String deliverymethod, String deliverycompanycd, String deliverychagetype, String deliverycharge,
			String freeshipoveramount, String deliverychargeonreturn, String remotearea, String uniondeliverytype,
			String remoteareadeliverable, String outboundshippingplacecode, String outaddress, String outaddressdetail,
			String returncentercode, String companycontactnumber, String returnzipcode, String returnaddress,
			String returnaddressdetail, String returncharge, String returnchargevendor, String afterserviceinformation,
			String afterservicecontactnumber, String salesstdate, String salesendate, String prodnmfirst,
			String prodnmend, String prodnmdetailtop, String prodnmdetailbottom, String useyn, String seq)
			throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update shopaddrcoupangdtl set TITLE = ?, memo = ?, SELMTHDCD = ?, shopid = ?, CHARGEMD = ?, SALEAGENCYFEE = ?, OFFERCONDITION = ?, ORIGINALPRICD = ?, COMPANY_GOODS_CD = ?, "
					+ " BARCD = ?, BARCDREASON = ?, ADULTONLY = ?, "
					+ " PARALLELIMPORT = ?, OVERSEASPURCHASED = ?, PCCNEEDED = ?, OVERSEASRECEIPT = ?, MESSAGE = ?, OUTBOUNDSHOPTIMEDAY = ?, MAXIMUMBUYPERSON = ?, MAXIMUMBUYPERSONPERIOD = ?, "
					+ " REPIMAGE = ?, RECTIMAGE = ?, CARIMAGE = ?, NOTICEIMAGE = ?, PAINTIMAGE = ?, OTHERIMAGE = ?, INCOMEIMAGE = ?, PRODNM = ?,DELIVERYMETHOD = ?,DELIVERYCOMPANYCD = ?,DELIVERYCHARGETYPE = ?, "
					+ " DELIVERYCHARGE = ?, FREESHIPOVERCHARGE = ?, DELIVERYRETCHARGE = ?, REMOTEAREACHARGE = ?, UNIONDELIVERYTYPE = ?, REMOTEAREADELIVERABLE = ?, OUTADDRCD = ?, OUTADDRESS = ?, "
					+ " OUTADDRESSDTL = ?, RETURNADDRCD = ?, RETURNADDRCONTACTNUM = ?, RETURNADDRZIPCD = ?, RETURNADDRESS = ?, RETURNADDRESSDEL = ?, "
					+ " RETURNCHARGE = ?, RETURNCHARGEVENDOR = ?, ASINFORMATION = ?, ASCONTACTNUM = ?, SALESSTDATE = ?, SALESENDATE = ?, PRODNMFIRST = ?, PRODNMEND = ? , PRODNMDTLTOP = ?, PRODNMDTLBOTTOM = ?, "
					+ " USEYN = ? " + " where COMPNO = ? and SHOPCD = ? and SEQ = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, title);
			pstmt.setString(++rowIdx, memo);
			pstmt.setString(++rowIdx, selmthdcd);
			pstmt.setString(++rowIdx, shopid);
			pstmt.setString(++rowIdx, chargemd);
			pstmt.setString(++rowIdx, saleagencyfee);
			pstmt.setString(++rowIdx, offercondition);
			pstmt.setString(++rowIdx, originalPricd);
			pstmt.setString(++rowIdx, company_Goods_cd);
			pstmt.setString(++rowIdx, emptybarcode);
			pstmt.setString(++rowIdx, emptybarcodereason);
			pstmt.setString(++rowIdx, adultonly);
			pstmt.setString(++rowIdx, parallelimported);
			pstmt.setString(++rowIdx, overseaspurchased);
			pstmt.setString(++rowIdx, pccneeded);
			pstmt.setString(++rowIdx, receipt);
			pstmt.setString(++rowIdx, message);
			pstmt.setString(++rowIdx, outboundShippingTimeDay);
			pstmt.setString(++rowIdx, maximumbuyforperson);
			pstmt.setString(++rowIdx, maximumbuyforpersonperiod);
			pstmt.setString(++rowIdx, repimage);
			pstmt.setString(++rowIdx, rectimage);
			pstmt.setString(++rowIdx, carimage);
			pstmt.setString(++rowIdx, noticeimage);
			pstmt.setString(++rowIdx, ptimage);
			pstmt.setString(++rowIdx, otherimage);
			pstmt.setString(++rowIdx, incomeimage);
			pstmt.setString(++rowIdx, prodnm);
			pstmt.setString(++rowIdx, deliverymethod);
			pstmt.setString(++rowIdx, deliverycompanycd);
			pstmt.setString(++rowIdx, deliverychagetype);
			pstmt.setString(++rowIdx, deliverycharge);
			pstmt.setString(++rowIdx, freeshipoveramount);
			pstmt.setString(++rowIdx, deliverychargeonreturn);
			pstmt.setString(++rowIdx, remotearea);
			pstmt.setString(++rowIdx, uniondeliverytype);
			pstmt.setString(++rowIdx, remoteareadeliverable);
			pstmt.setString(++rowIdx, outboundshippingplacecode);
			pstmt.setString(++rowIdx, outaddress);
			pstmt.setString(++rowIdx, outaddressdetail);
			pstmt.setString(++rowIdx, returncentercode);
			pstmt.setString(++rowIdx, companycontactnumber);
			pstmt.setString(++rowIdx, returnzipcode);
			pstmt.setString(++rowIdx, returnaddress);
			pstmt.setString(++rowIdx, returnaddressdetail);
			pstmt.setString(++rowIdx, returncharge);
			pstmt.setString(++rowIdx, returnchargevendor);
			pstmt.setString(++rowIdx, afterserviceinformation);
			pstmt.setString(++rowIdx, afterservicecontactnumber);
			pstmt.setString(++rowIdx, salesstdate);
			pstmt.setString(++rowIdx, salesendate);
			pstmt.setString(++rowIdx, prodnmfirst);
			pstmt.setString(++rowIdx, prodnmend);
			pstmt.setString(++rowIdx, prodnmdetailtop);
			pstmt.setString(++rowIdx, prodnmdetailbottom);
			pstmt.setString(++rowIdx, useyn);

			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, seq);
			pstmt.executeUpdate();

			System.out.println("[insertdtl-ShopAddrDtlInsert]" + pstmt.toString());

			String sql_mst = " update shopaddrmst set TITLE = ? , SELMTHDCD = ? , USEYN = ? , UPDATEDT = ? where compno = ? and shopcd = ? and seq = ? ";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, title);
			pstmt.setString(2, selmthdcd);
			pstmt.setString(3, useyn);
			pstmt.setString(4, YDMATimeUtil.getCurrentTimeByYDFormat());

			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(6, shopcd);
			pstmt.setString(7, seq);

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	// 인터파크저장
	public int ShopAddrDtlInterparkInsert(String shopcd, ShopProductInterParkAdditionDto dto, int seq)
			throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "insert into shopaddrinterparkdtl(SEQ,COMPNO, shopcd,TITLE, memo,VALUE1, SELMTHDCD, CONTRACTTYPCD, PRODTYPCD, PRODSTATCD,VALUE2,VALUE3,VALUE4,VALUE5,VALUE6,VALUE7,VALUE8, "
					+ " VALUE9,VALUE10,VALUE11,VALUE12,VALUE13,VALUE14, KEYWORD, PRODNO, OVERSEASPURCHASED,MINORSELCNYN, VALUE15,VALUE16,HEALTHFUNCFOOD, "
					+ " SALESREPORTING,SALESREPORTINGNO, ADREVIEW, ADREVIEWNO, ASDTL, UNIQUENESSYN, UNIQUENESSVAL,VALUE66, VALUE17,VALUE18,VALUE19,VALUE20,VALUE21,VALUE22,VALUE23,SALESPERIODYN, "
					+ " SALESPERIODTYP,SALESPERIODFROM, SALESPERIODTO, VALUE24,VALUE25,VALUE65, VALUE26,QUANTITYYN,"
					+ " QUANTITYCNT, EXPRESSTYP, DELVTYPE,VALUE27,DELVMETHOD, DELVFREECHK, VALUE28,SHIPPRC,SHIPPRC2,DELVQTY,VALUE29,VALUE30,VALUE31,VALUE32,VALUE33,VALUE34,DELVQTYCOST,VALUE35,"
					+ " VALUE36,VALUE37,PREPAYMENT,SHIPUNIQUENE, JEJUPRC, ISLANDPRC,ADDRIN,RETURNSHIPPINGYN,RETNCNGPRC,VALUE38,VALUE39,VALUE40,VALUE41,VALUE42,VALUE43,VALUE44,VALUE45,VALUE46,"
					+ " VALUE47,VALUE48,VALUE49,VALUE50,VALUE51,VALUE52,VALUE53,VALUE54,VALUE55,VALUE56,VALUE57,VALUE58,VALUE59,VALUE60,VALUE61,VALUE62,VALUE63,VALUE64,USEYN ) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ? , ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "// 54
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";// 109
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setInt(++rowIdx, seq);
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, dto.getTitle()); // 제목
			pstmt.setString(++rowIdx, dto.getMemo()); // 메모
			pstmt.setString(++rowIdx, dto.getValue1()); // 쇼핑몰아이디
			pstmt.setString(++rowIdx, dto.getSelmthdcd()); // 형태구분
			pstmt.setString(++rowIdx, dto.getContracttypcd()); // 계약유형
			pstmt.setString(++rowIdx, dto.getProdtypcd()); // 판매방식
			pstmt.setString(++rowIdx, dto.getProdstatcd()); // 상품상태
			pstmt.setString(++rowIdx, dto.getValue2()); // 카테고리수수료율
			pstmt.setString(++rowIdx, dto.getValue3()); // 브랜드카테고리
			pstmt.setString(++rowIdx, dto.getValue4()); // 테마카테고리1
			pstmt.setString(++rowIdx, dto.getValue5()); // 테마카테고리2
			pstmt.setString(++rowIdx, dto.getValue6()); // 해외배송여부
			pstmt.setString(++rowIdx, dto.getValue7()); // 대표이미지
			pstmt.setString(++rowIdx, dto.getValue8()); // 스토어디추가이미지
			pstmt.setString(++rowIdx, dto.getValue9()); // 상품명추가앞문구
			pstmt.setString(++rowIdx, dto.getValue10()); // 상품명추가뒷문구
			pstmt.setString(++rowIdx, dto.getValue11()); // 브랜드1
			pstmt.setString(++rowIdx, dto.getValue12()); // 브랜드2
			pstmt.setString(++rowIdx, dto.getValue13()); // 모델명/품번선택
			pstmt.setString(++rowIdx, dto.getValue14()); // 수입신고번호
			pstmt.setString(++rowIdx, dto.getKeyword()); // 검색키워드
			pstmt.setString(++rowIdx, dto.getProdno()); // 판매자상품번호
			pstmt.setString(++rowIdx, dto.getOverseaspurchased()); // 해외구매대형여부
			pstmt.setString(++rowIdx, dto.getMinorselcnyn()); // 미성년자구매가능여부
			pstmt.setString(++rowIdx, dto.getValue15()); // 상품사용개월수
			pstmt.setString(++rowIdx, dto.getValue16()); // 도서상품ISBN
			pstmt.setString(++rowIdx, dto.getHealthfuncfood()); // 건강기능식품여부
			pstmt.setString(++rowIdx, dto.getSalesreporting()); // 판매업신고기관
			pstmt.setString(++rowIdx, dto.getSalesreportingno()); // 판매업신고번호
			pstmt.setString(++rowIdx, dto.getAdreview()); // 사전광고심의여부
			pstmt.setString(++rowIdx, dto.getAdreviewno()); // 광고심의번호
			pstmt.setString(++rowIdx, dto.getAsdtl()); // A/S가능여부
			pstmt.setString(++rowIdx, dto.getUniquenessyn()); // 특이사항여부
			pstmt.setString(++rowIdx, dto.getUniquenessval()); // 특이사항내용
			pstmt.setString(++rowIdx, dto.getValue66()); // 소비자가적용여부
			pstmt.setString(++rowIdx, dto.getValue17()); // 렌탈의무사용기간
			pstmt.setString(++rowIdx, dto.getValue18()); // 렌탈설치비여부
			pstmt.setString(++rowIdx, dto.getValue19()); // 렌탈설치비유료금액
			pstmt.setString(++rowIdx, dto.getValue20()); // 렌탈등록비여부
			pstmt.setString(++rowIdx, dto.getValue21()); // 렌탈등록비유료금액
			pstmt.setString(++rowIdx, dto.getValue22()); // 렌탈소비자가여부
			pstmt.setString(++rowIdx, dto.getValue23()); // 렌탈소비자가유료금액
			pstmt.setString(++rowIdx, dto.getSalesperiodyn()); // 판매기간설정여부
			pstmt.setString(++rowIdx, dto.getSalesperiodtyp()); // 기간설정타입
			pstmt.setString(++rowIdx, dto.getSalesperiodfrom()); // 판매기간시작일
			pstmt.setString(++rowIdx, dto.getSalesperiodto()); // 판매기간종료일
			pstmt.setString(++rowIdx, dto.getValue24()); // 입력형옵션사용여부
			pstmt.setString(++rowIdx, dto.getValue25()); // 입력형옵셩내용
			pstmt.setString(++rowIdx, dto.getValue65()); // 옵션정렬방법
			pstmt.setString(++rowIdx, dto.getValue26()); // 2단옵션적용여부
			pstmt.setString(++rowIdx, dto.getQuantityyn()); // 주문당판매수량제한여부
			pstmt.setString(++rowIdx, dto.getQuantitycnt()); // 판매제한수량
			pstmt.setString(++rowIdx, dto.getExpresstyp()); // 배송방법타입
			pstmt.setString(++rowIdx, dto.getDelvtype()); // 배송비설정여부
			pstmt.setString(++rowIdx, dto.getValue27()); // 배송비기본배송비시금액
			pstmt.setString(++rowIdx, dto.getDelvmethod()); // 배송비종류
			pstmt.setString(++rowIdx, dto.getDelvfreechk()); // 배송비안에 무료및조건부무료체크여부
			pstmt.setString(++rowIdx, dto.getValue28()); // 배송비개당시무료여부
			pstmt.setString(++rowIdx, dto.getShipprc()); // 기본배송비
			pstmt.setString(++rowIdx, dto.getShipprc2()); // 기본배송비2
			pstmt.setString(++rowIdx, dto.getDelvqty()); // 수량별체크시수량들1
			pstmt.setString(++rowIdx, dto.getValue29()); // 수량2
			pstmt.setString(++rowIdx, dto.getValue30()); // 수량3
			pstmt.setString(++rowIdx, dto.getValue31()); // 수량4
			pstmt.setString(++rowIdx, dto.getValue32()); // 수량5
			pstmt.setString(++rowIdx, dto.getValue33()); // 수량6
			pstmt.setString(++rowIdx, dto.getValue34()); // 수량7
			pstmt.setString(++rowIdx, dto.getDelvqtycost()); // 수량별체크시금액들
			pstmt.setString(++rowIdx, dto.getValue35()); // 금액2
			pstmt.setString(++rowIdx, dto.getValue36()); // 금액3
			pstmt.setString(++rowIdx, dto.getValue37()); // 금액4
			pstmt.setString(++rowIdx, dto.getPrepayment()); // 착불,선불결제여부
			pstmt.setString(++rowIdx, dto.getShipuniquene()); // 배송비특이사항
			pstmt.setString(++rowIdx, dto.getJejuprc()); // 제주추가배송비
			pstmt.setString(++rowIdx, dto.getIslandprc()); // 도서산간추가배송비
			pstmt.setString(++rowIdx, dto.getAddrin()); // 반품배송지주소
			pstmt.setString(++rowIdx, dto.getReturnshippingyn()); // 반품교환배송비타입
			pstmt.setString(++rowIdx, dto.getRetncngprc()); // 반품교환배송비
			pstmt.setString(++rowIdx, dto.getValue38()); // 가격비교등록여부
			pstmt.setString(++rowIdx, dto.getValue39()); // 단말기할부여부
			pstmt.setString(++rowIdx, dto.getValue40()); // 핸드폰가입비여부
			pstmt.setString(++rowIdx, dto.getValue41()); // 핸드폰유심비여부
			pstmt.setString(++rowIdx, dto.getValue42()); // 핸드폰가입신청URL1
			pstmt.setString(++rowIdx, dto.getValue43()); // 핸드폰가입신청내용적용1
			pstmt.setString(++rowIdx, dto.getValue44()); // 핸드폰가입신청URL2
			pstmt.setString(++rowIdx, dto.getValue45()); // 핸드폰가입신청내용적용2
			pstmt.setString(++rowIdx, dto.getValue46()); // 판매권사용갯수
			pstmt.setString(++rowIdx, dto.getValue47()); // 완구사용자성별여부
			pstmt.setString(++rowIdx, dto.getValue48()); // 완구사용자연령여부
			pstmt.setString(++rowIdx, dto.getValue49()); // 완구일부연령시작나이
			pstmt.setString(++rowIdx, dto.getValue50()); // 완구일부연령종료나이
			pstmt.setString(++rowIdx, dto.getValue51()); // 완구일부연령개월및나이선택
			pstmt.setString(++rowIdx, dto.getValue52()); // 완구특정연령시작나이
			pstmt.setString(++rowIdx, dto.getValue53()); // 완구특정연령개월및나이선택
			pstmt.setString(++rowIdx, dto.getValue54()); // 완구특정연령이상및이하선택
			pstmt.setString(++rowIdx, dto.getValue55()); // 상품설명상단추가문구
			pstmt.setString(++rowIdx, dto.getValue56()); // 상품설명하단추가문구
			pstmt.setString(++rowIdx, dto.getValue57()); // 판매자부담즉시할인여부
			pstmt.setString(++rowIdx, dto.getValue58()); // 판매자부담정액정률여부
			pstmt.setString(++rowIdx, dto.getValue59()); // 판매자부담할인금액
			pstmt.setString(++rowIdx, dto.getValue60()); // 할인기간시작일
			pstmt.setString(++rowIdx, dto.getValue61()); // 할인기간종료일
			pstmt.setString(++rowIdx, dto.getValue62()); // I-포인트선택여부
			pstmt.setString(++rowIdx, dto.getValue63()); // I-포인트할인
			pstmt.setString(++rowIdx, dto.getValue64()); // 무이자할부여부
			pstmt.setString(++rowIdx, dto.getUseyn()); // 사용여부

			pstmt.executeUpdate();

			System.out.println("[insertdtl-ShopAddrDtlInsert]" + pstmt.toString());

			String sql_mst = " insert into shopaddrmst (compno, SHOPCD, SEQ,  TITLE, SELMTHDCD, USEYN, INSERTDT) "
					+ "  VALUES (?, ?,?,?,?,?,?)";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setInt(3, seq);
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getSelmthdcd());
			pstmt.setString(6, dto.getUseyn());
			pstmt.setString(7, YDMATimeUtil.getCurrentTimeByYDFormat());

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public ShopProductInterParkAdditionDto shopInterparkDetailjoinList(List<String> list) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShopProductInterParkAdditionDto dto = new ShopProductInterParkAdditionDto();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT IFNULL(dtl.seq,''),ifnull(dtl.compno,''), IFNULL(dtl.shopcd,''),IFNULL(dtl.title,''), ifnull(memo,''), ifnull(VALUE1,''),IFNULL(dtl.selmthdcd,''),IFNULL(dtl.CONTRACTTYPCD,''),"
					+ " IFNULL(dtl.PRODTYPCD,''),IFNULL(dtl.PRODSTATCD,''), IFNULL(dtl.VALUE2,'0'),IFNULL(dtl.VALUE3,''),IFNULL(dtl.VALUE4,''),IFNULL(dtl.VALUE5,''),IFNULL(dtl.VALUE6,''),IFNULL(dtl.VALUE7,''),"
					+ " IFNULL(dtl.VALUE8,''),IFNULL(dtl.VALUE9,''),IFNULL(dtl.VALUE10,''),IFNULL(dtl.VALUE11,''),IFNULL(dtl.VALUE12,''),IFNULL(dtl.VALUE13,''),IFNULL(dtl.VALUE14,''),"
					+ " IFNULL(dtl.KEYWORD,''),IFNULL(dtl.PRODNO,''),IFNULL(dtl.OVERSEASPURCHASED,''),IFNULL(dtl.MINORSELCNYN,''),IFNULL(dtl.VALUE15,'0'),IFNULL(dtl.VALUE16,''),IFNULL(dtl.HEALTHFUNCFOOD,''),"
					+ " IFNULL(dtl.SALESREPORTING,''),IFNULL(dtl.SALESREPORTINGNO,''), IFNULL(dtl.ADREVIEW,''),IFNULL(dtl.ADREVIEWNO,''),IFNULL(dtl.ASDTL,''),IFNULL(dtl.UNIQUENESSYN,''),"
					+ " IFNULL(dtl.UNIQUENESSVAL,''), IFNULL(dtl.VALUE66,''),IFNULL(dtl.VALUE17,'0'),IFNULL(dtl.VALUE18,''),IFNULL(dtl.VALUE19,'0'),IFNULL(dtl.VALUE20,''),IFNULL(dtl.VALUE21,'0'),"
					+ " IFNULL(dtl.VALUE22,''),IFNULL(dtl.VALUE23,'0'),"
					+ " IFNULL(dtl.SALESPERIODYN,''),IFNULL(dtl.SALESPERIODTYP,''),IFNULL(dtl.SALESPERIODFROM,''), IFNULL(dtl.SALESPERIODTO,''),IFNULL(dtl.VALUE24,''),IFNULL(dtl.VALUE25,''),"
					+ " IFNULL(dtl.VALUE65,''),IFNULL(dtl.VALUE26,''),IFNULL(dtl.QUANTITYYN,''), "
					+ " IFNULL(dtl.QUANTITYCNT,'0'),IFNULL(dtl.EXPRESSTYP,''), IFNULL(dtl.DELVTYPE,''),IFNULL(dtl.VALUE27,''),IFNULL(dtl.DELVMETHOD,''),IFNULL(dtl.DELVFREECHK,''),IFNULL(dtl.VALUE28,''),"
					+ " IFNULL(dtl.SHIPPRC,'0'), IFNULL(dtl.SHIPPRC2,'0'), IFNULL(dtl.DELVQTY,'0'),IFNULL(dtl.VALUE29,'0'),IFNULL(dtl.VALUE30,'0'),IFNULL(dtl.VALUE31,'0'),IFNULL(dtl.VALUE32,'0'),"
					+ " IFNULL(dtl.VALUE33,'0'), IFNULL(dtl.VALUE34,'0'),IFNULL(dtl.DELVQTYCOST,'0'),IFNULL(dtl.VALUE35,'0'),IFNULL(dtl.VALUE36,'0'),IFNULL(dtl.VALUE37,'0'),IFNULL(dtl.PREPAYMENT,''), "
					+ " ifnull(dtl.SHIPUNIQUENE,''), ifnull(dtl.JEJUPRC,'0'), "
					+ " ifnull(dtl.ISLANDPRC,'0'), ifnull(dtl.ADDRIN,''), ifnull(dtl.RETURNSHIPPINGYN,''), ifnull(dtl.RETNCNGPRC,''),IFNULL(dtl.VALUE38,''),IFNULL(dtl.VALUE39,''),IFNULL(dtl.VALUE40,''),"
					+ " IFNULL(dtl.VALUE41,''),IFNULL(dtl.VALUE42,''),IFNULL(dtl.VALUE43,''),IFNULL(dtl.VALUE44,''),IFNULL(dtl.VALUE45,''),IFNULL(dtl.VALUE46,'0'),IFNULL(dtl.VALUE47,''),"
					+ " IFNULL(dtl.VALUE48,''),IFNULL(dtl.VALUE49,'0'),IFNULL(dtl.VALUE50,'0'),IFNULL(dtl.VALUE51,''),IFNULL(dtl.VALUE52,'0'),IFNULL(dtl.VALUE53,''),IFNULL(dtl.VALUE54,''),"
					+ " IFNULL(dtl.VALUE55,''),IFNULL(dtl.VALUE56,''),IFNULL(dtl.VALUE57,''),IFNULL(dtl.VALUE58,''),IFNULL(dtl.VALUE59,'0'),IFNULL(dtl.VALUE60,''),IFNULL(dtl.VALUE61,''),"
					+ " IFNULL(dtl.VALUE62,''),IFNULL(dtl.VALUE63,'0'),IFNULL(dtl.VALUE64,''),ifnull(dtl.USEYN,'') "
					+ " FROM SHOPADDRMST addr JOIN SHOPMST MST ON MST.SHOPCD=ADDR.SHOPCD and MST.compno = ADDR.compno JOIN shopaddrinterparkdtl dtl ON ADDR.seq = dtl.seq and ADDR.compno = dtl.compno join shopinfo a on "
					+ " mst.shopcd = a.shopcd " + " WHERE addr.seq = ? AND a.shopnm = ? and mst.compno = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, list.get(0));
			pstmt.setString(2, list.get(1));
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[shopDetailjoinList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				dto.setSeq(rs.getString(++columnIndex)); // 일련번호
				dto.setCompno(rs.getString(++columnIndex)); // 회사코드
				dto.setShopcd(rs.getString(++columnIndex)); // 쇼핑몰코드
				dto.setTitle(rs.getString(++columnIndex)); // 제목
				dto.setMemo(rs.getString(++columnIndex)); // 메모
				dto.setValue1(rs.getString(++columnIndex)); // 쇼핑몰아이디
				dto.setSelmthdcd(rs.getString(++columnIndex)); // 형태구분
				dto.setContracttypcd(rs.getString(++columnIndex)); // 계약유형
				dto.setProdtypcd(rs.getString(++columnIndex)); // 판매방식
				dto.setProdstatcd(rs.getString(++columnIndex)); // 상품상태
				dto.setValue2(rs.getString(++columnIndex)); // 카테고리수수료율
				dto.setValue3(rs.getString(++columnIndex)); // 브랜드카테고리
				dto.setValue4(rs.getString(++columnIndex)); // 테마카테고리1
				dto.setValue5(rs.getString(++columnIndex)); // 테마카테고리2
				dto.setValue6(rs.getString(++columnIndex)); // 해외배송여부
				dto.setValue7(rs.getString(++columnIndex)); // 대표이미지
				dto.setValue8(rs.getString(++columnIndex)); // 스토어디추가이미지
				dto.setValue9(rs.getString(++columnIndex)); // 상품명추가앞문구
				dto.setValue10(rs.getString(++columnIndex)); // 상품명추가뒷문구
				dto.setValue11(rs.getString(++columnIndex)); // 브랜드1
				dto.setValue12(rs.getString(++columnIndex)); // 브랜드2
				dto.setValue13(rs.getString(++columnIndex)); // 모델명/품번선택
				dto.setValue14(rs.getString(++columnIndex)); // 수입신고번호
				dto.setKeyword(rs.getString(++columnIndex)); // 검색키워드
				dto.setProdno(rs.getString(++columnIndex)); // 판매자상품번호
				dto.setOverseaspurchased(rs.getString(++columnIndex)); // 해외구매대형여부
				dto.setMinorselcnyn(rs.getString(++columnIndex)); // 미성년자구매가능여부
				dto.setValue15(rs.getString(++columnIndex)); // 상품사용개월수
				dto.setValue16(rs.getString(++columnIndex)); // 도서상품ISBN
				dto.setHealthfuncfood(rs.getString(++columnIndex)); // 건강기능식품여부
				dto.setSalesreporting(rs.getString(++columnIndex)); // 판매업신고기관
				dto.setSalesreportingno(rs.getString(++columnIndex)); // 판매업신고번호
				dto.setAdreview(rs.getString(++columnIndex)); // 사전광고심의여부
				dto.setAdreviewno(rs.getString(++columnIndex)); // 광고심의번호
				dto.setAsdtl(rs.getString(++columnIndex)); // A/S가능여부
				dto.setUniquenessyn(rs.getString(++columnIndex)); // 특이사항여부
				dto.setUniquenessval(rs.getString(++columnIndex)); // 특이사항내용
				dto.setValue66(rs.getString(++columnIndex)); // 소비자가적용여부
				dto.setValue17(rs.getString(++columnIndex)); // 렌탈의무사용기간
				dto.setValue18(rs.getString(++columnIndex)); // 렌탈설치비여부
				dto.setValue19(rs.getString(++columnIndex)); // 렌탈설치비유료금액
				dto.setValue20(rs.getString(++columnIndex)); // 렌탈등록비여부
				dto.setValue21(rs.getString(++columnIndex)); // 렌탈등록비유료금액
				dto.setValue22(rs.getString(++columnIndex)); // 렌탈소비자가여부
				dto.setValue23(rs.getString(++columnIndex)); // 렌탈소비자가유료금액
				dto.setSalesperiodyn(rs.getString(++columnIndex)); // 판매기간설정여부
				dto.setSalesperiodtyp(rs.getString(++columnIndex)); // 기간설정타입
				dto.setSalesperiodfrom(rs.getString(++columnIndex)); // 판매기간시작일
				dto.setSalesperiodto(rs.getString(++columnIndex)); // 판매기간종료일
				dto.setValue24(rs.getString(++columnIndex)); // 입력형옵션사용여부
				dto.setValue25(rs.getString(++columnIndex)); // 입력형옵셩내용
				dto.setValue65(rs.getString(++columnIndex)); // 옵션정렬방법
				dto.setValue26(rs.getString(++columnIndex)); // 2단옵션적용여부
				dto.setQuantityyn(rs.getString(++columnIndex)); // 주문당판매수량제한여부
				dto.setQuantitycnt(rs.getString(++columnIndex)); // 판매제한수량
				dto.setExpresstyp(rs.getString(++columnIndex)); // 배송방법타입
				dto.setDelvtype(rs.getString(++columnIndex)); // 배송비설정여부
				dto.setValue27(rs.getString(++columnIndex)); // 배송비기본배송비시금액
				dto.setDelvmethod(rs.getString(++columnIndex)); // 배송비종류
				dto.setDelvfreechk(rs.getString(++columnIndex)); // 배송비안에 무료및조건부무료체크여부
				dto.setValue28(rs.getString(++columnIndex)); // 배송비개당시무료여부
				dto.setShipprc(rs.getString(++columnIndex)); // 기본배송비
				dto.setShipprc2(rs.getString(++columnIndex)); // 기본배송비2
				dto.setDelvqty(rs.getString(++columnIndex)); // 수량별체크시수량들1
				dto.setValue29(rs.getString(++columnIndex)); // 수량2
				dto.setValue30(rs.getString(++columnIndex)); // 수량3
				dto.setValue31(rs.getString(++columnIndex)); // 수량4
				dto.setValue32(rs.getString(++columnIndex)); // 수량5
				dto.setValue33(rs.getString(++columnIndex)); // 수량6
				dto.setValue34(rs.getString(++columnIndex)); // 수량7
				dto.setDelvqtycost(rs.getString(++columnIndex)); // 수량별체크시금액들
				dto.setValue35(rs.getString(++columnIndex)); // 금액2
				dto.setValue36(rs.getString(++columnIndex)); // 금액3
				dto.setValue37(rs.getString(++columnIndex)); // 금액4
				dto.setPrepayment(rs.getString(++columnIndex)); // 착불,선불결제여부
				dto.setShipuniquene(rs.getString(++columnIndex)); // 배송비특이사항
				dto.setJejuprc(rs.getString(++columnIndex)); // 제주추가배송비
				dto.setIslandprc(rs.getString(++columnIndex)); // 도서산간추가배송비
				dto.setAddrin(rs.getString(++columnIndex)); // 반품배송지주소
				dto.setReturnshippingyn(rs.getString(++columnIndex)); // 반품교환배송비타입
				dto.setRetncngprc(rs.getString(++columnIndex)); // 반품교환배송비
				dto.setValue38(rs.getString(++columnIndex)); // 가격비교등록여부
				dto.setValue39(rs.getString(++columnIndex)); // 단말기할부여부
				dto.setValue40(rs.getString(++columnIndex)); // 핸드폰가입비여부
				dto.setValue41(rs.getString(++columnIndex)); // 핸드폰유심비여부
				dto.setValue42(rs.getString(++columnIndex)); // 핸드폰가입신청URL1
				dto.setValue43(rs.getString(++columnIndex)); // 핸드폰가입신청내용적용1
				dto.setValue44(rs.getString(++columnIndex)); // 핸드폰가입신청URL2
				dto.setValue45(rs.getString(++columnIndex)); // 핸드폰가입신청내용적용2
				dto.setValue46(rs.getString(++columnIndex)); // 판매권사용갯수
				dto.setValue47(rs.getString(++columnIndex)); // 완구사용자성별여부
				dto.setValue48(rs.getString(++columnIndex)); // 완구사용자연령여부
				dto.setValue49(rs.getString(++columnIndex)); // 완구일부연령시작나이
				dto.setValue50(rs.getString(++columnIndex)); // 완구일부연령종료나이
				dto.setValue51(rs.getString(++columnIndex)); // 완구일부연령개월및나이선택
				dto.setValue52(rs.getString(++columnIndex)); // 완구특정연령시작나이
				dto.setValue53(rs.getString(++columnIndex)); // 완구특정연령개월및나이선택
				dto.setValue54(rs.getString(++columnIndex)); // 완구특정연령이상및이하선택
				dto.setValue55(rs.getString(++columnIndex)); // 상품설명상단추가문구
				dto.setValue56(rs.getString(++columnIndex)); // 상품설명하단추가문구
				dto.setValue57(rs.getString(++columnIndex)); // 판매자부담즉시할인여부
				dto.setValue58(rs.getString(++columnIndex)); // 판매자부담정액정률여부
				dto.setValue59(rs.getString(++columnIndex)); // 판매자부담할인금액
				dto.setValue60(rs.getString(++columnIndex)); // 할인기간시작일
				dto.setValue61(rs.getString(++columnIndex)); // 할인기간종료일
				dto.setValue62(rs.getString(++columnIndex)); // I-포인트선택여부
				dto.setValue63(rs.getString(++columnIndex)); // I-포인트할인
				dto.setValue64(rs.getString(++columnIndex)); // 무이자할부여부
				dto.setUseyn(rs.getString(++columnIndex)); // 사용여부

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return dto;
	}

	public int ShopAddrDtlInterparkUpdate(String shopcd, ShopProductInterParkAdditionDto dto, String seq)
			throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update shopaddrinterparkdtl set TITLE = ?, memo = ?, VALUE1 = ?, SELMTHDCD = ?, CONTRACTTYPCD = ?, PRODTYPCD = ?, PRODSTATCD = ?, VALUE2 = ?,VALUE3 = ?,VALUE4 = ?,VALUE5 = ?,"
					+ " VALUE6 = ?, VALUE7 = ?,VALUE8 = ?,VALUE9 = ?,VALUE10 = ?,VALUE11 = ?,VALUE12 = ?,VALUE13 = ?,VALUE14 = ?,KEYWORD = ?, PRODNO = ?, OVERSEASPURCHASED = ?, MINORSELCNYN = ?, "
					+ " VALUE15 = ?, VALUE16 = ?,HEALTHFUNCFOOD = ?, SALESREPORTING = ?, SALESREPORTINGNO = ?, ADREVIEW = ?, ADREVIEWNO = ?, ASDTL = ?, UNIQUENESSYN = ?, UNIQUENESSVAL = ?, "
					+ " VALUE66 = ?, VALUE17 = ?,VALUE18 = ?,VALUE19 = ?,VALUE20 = ?,VALUE21 = ?,VALUE22 = ?,VALUE23 = ?,SALESPERIODYN = ?, SALESPERIODTYP = ?, SALESPERIODFROM = ?, SALESPERIODTO = ?, "
					+ " VALUE24 = ?, VALUE25 = ?,VALUE65 = ?,VALUE26 = ?,QUANTITYYN = ?, QUANTITYCNT = ?, EXPRESSTYP = ?, DELVTYPE = ?,VALUE27 = ?,DELVMETHOD = ?, "
					+ " DELVFREECHK = ?, VALUE28 = ?, SHIPPRC = ?,SHIPPRC2 = ?, DELVQTY = ?,VALUE29 = ?,VALUE30 = ?,VALUE31 = ?,VALUE32 = ?,VALUE33 = ?,VALUE34 = ?, DELVQTYCOST = ?, VALUE35 = ?,"
					+ " VALUE36 = ?, VALUE37 = ?,PREPAYMENT = ?, SHIPUNIQUENE = ?,JEJUPRC = ?, ISLANDPRC = ?, ADDRIN = ?, RETURNSHIPPINGYN = ?, RETNCNGPRC = ?, VALUE38 = ?, VALUE39 = ?,VALUE40 = ?,"
					+ " VALUE41 = ?,VALUE42 = ?,VALUE43 = ?,VALUE44 = ?,VALUE45 = ?,VALUE46 = ?,VALUE47 = ?,"
					+ " VALUE48 = ?,VALUE49 = ?,VALUE50 = ?,VALUE51 = ?,VALUE52 = ?,VALUE53 = ?,VALUE54 = ?,VALUE55 = ?,"
					+ " VALUE56 = ?,VALUE57 = ?,VALUE58 = ?,VALUE59 = ?,VALUE60 = ?,VALUE61 = ?,VALUE62 = ?,VALUE63 = ?,VALUE64 = ?,USEYN = ? "
					+ " where COMPNO = ? and SHOPCD = ? and SEQ = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, dto.getTitle()); // 제목
			pstmt.setString(++rowIdx, dto.getMemo()); // 메모
			pstmt.setString(++rowIdx, dto.getValue1()); // 쇼핑몰아이디
			pstmt.setString(++rowIdx, dto.getSelmthdcd()); // 형태구분
			pstmt.setString(++rowIdx, dto.getContracttypcd()); // 계약유형
			pstmt.setString(++rowIdx, dto.getProdtypcd()); // 판매방식
			pstmt.setString(++rowIdx, dto.getProdstatcd()); // 상품상태
			pstmt.setString(++rowIdx, dto.getValue2()); // 카테고리수수료율
			pstmt.setString(++rowIdx, dto.getValue3()); // 브랜드카테고리
			pstmt.setString(++rowIdx, dto.getValue4()); // 테마카테고리1
			pstmt.setString(++rowIdx, dto.getValue5()); // 테마카테고리2
			pstmt.setString(++rowIdx, dto.getValue6()); // 해외배송여부
			pstmt.setString(++rowIdx, dto.getValue7()); // 대표이미지
			pstmt.setString(++rowIdx, dto.getValue8()); // 스토어디추가이미지
			pstmt.setString(++rowIdx, dto.getValue9()); // 상품명추가앞문구
			pstmt.setString(++rowIdx, dto.getValue10()); // 상품명추가뒷문구
			pstmt.setString(++rowIdx, dto.getValue11()); // 브랜드1
			pstmt.setString(++rowIdx, dto.getValue12()); // 브랜드2
			pstmt.setString(++rowIdx, dto.getValue13()); // 모델명/품번선택
			pstmt.setString(++rowIdx, dto.getValue14()); // 수입신고번호
			pstmt.setString(++rowIdx, dto.getKeyword()); // 검색키워드
			pstmt.setString(++rowIdx, dto.getProdno()); // 판매자상품번호
			pstmt.setString(++rowIdx, dto.getOverseaspurchased()); // 해외구매대형여부
			pstmt.setString(++rowIdx, dto.getMinorselcnyn()); // 미성년자구매가능여부
			pstmt.setString(++rowIdx, dto.getValue15()); // 상품사용개월수
			pstmt.setString(++rowIdx, dto.getValue16()); // 도서상품ISBN
			pstmt.setString(++rowIdx, dto.getHealthfuncfood()); // 건강기능식품여부
			pstmt.setString(++rowIdx, dto.getSalesreporting()); // 판매업신고기관
			pstmt.setString(++rowIdx, dto.getSalesreportingno()); // 판매업신고번호
			pstmt.setString(++rowIdx, dto.getAdreview()); // 사전광고심의여부
			pstmt.setString(++rowIdx, dto.getAdreviewno()); // 광고심의번호
			pstmt.setString(++rowIdx, dto.getAsdtl()); // A/S가능여부
			pstmt.setString(++rowIdx, dto.getUniquenessyn()); // 특이사항여부
			pstmt.setString(++rowIdx, dto.getUniquenessval()); // 특이사항내용
			pstmt.setString(++rowIdx, dto.getValue66()); // 소비자가적용여부
			pstmt.setString(++rowIdx, dto.getValue17()); // 렌탈의무사용기간
			pstmt.setString(++rowIdx, dto.getValue18()); // 렌탈설치비여부
			pstmt.setString(++rowIdx, dto.getValue19()); // 렌탈설치비유료금액
			pstmt.setString(++rowIdx, dto.getValue20()); // 렌탈등록비여부
			pstmt.setString(++rowIdx, dto.getValue21()); // 렌탈등록비유료금액
			pstmt.setString(++rowIdx, dto.getValue22()); // 렌탈소비자가여부
			pstmt.setString(++rowIdx, dto.getValue23()); // 렌탈소비자가유료금액
			pstmt.setString(++rowIdx, dto.getSalesperiodyn()); // 판매기간설정여부
			pstmt.setString(++rowIdx, dto.getSalesperiodtyp()); // 기간설정타입
			pstmt.setString(++rowIdx, dto.getSalesperiodfrom()); // 판매기간시작일
			pstmt.setString(++rowIdx, dto.getSalesperiodto()); // 판매기간종료일
			pstmt.setString(++rowIdx, dto.getValue24()); // 입력형옵션사용여부
			pstmt.setString(++rowIdx, dto.getValue25()); // 입력형옵셩내용
			pstmt.setString(++rowIdx, dto.getValue65()); // 옵션정렬방법
			pstmt.setString(++rowIdx, dto.getValue26()); // 2단옵션적용여부
			pstmt.setString(++rowIdx, dto.getQuantityyn()); // 주문당판매수량제한여부
			pstmt.setString(++rowIdx, dto.getQuantitycnt()); // 판매제한수량
			pstmt.setString(++rowIdx, dto.getExpresstyp()); // 배송방법타입
			pstmt.setString(++rowIdx, dto.getDelvtype()); // 배송비설정여부
			pstmt.setString(++rowIdx, dto.getValue27()); // 배송비기본배송비시금액
			pstmt.setString(++rowIdx, dto.getDelvmethod()); // 배송비종류
			pstmt.setString(++rowIdx, dto.getDelvfreechk()); // 배송비안에 무료및조건부무료체크여부
			pstmt.setString(++rowIdx, dto.getValue28()); // 배송비개당시무료여부
			pstmt.setString(++rowIdx, dto.getShipprc()); // 기본배송비
			pstmt.setString(++rowIdx, dto.getShipprc2()); // 기본배송비2
			pstmt.setString(++rowIdx, dto.getDelvqty()); // 수량별체크시수량들1
			pstmt.setString(++rowIdx, dto.getValue29()); // 수량2
			pstmt.setString(++rowIdx, dto.getValue30()); // 수량3
			pstmt.setString(++rowIdx, dto.getValue31()); // 수량4
			pstmt.setString(++rowIdx, dto.getValue32()); // 수량5
			pstmt.setString(++rowIdx, dto.getValue33()); // 수량6
			pstmt.setString(++rowIdx, dto.getValue34()); // 수량7
			pstmt.setString(++rowIdx, dto.getDelvqtycost()); // 수량별체크시금액들
			pstmt.setString(++rowIdx, dto.getValue35()); // 금액2
			pstmt.setString(++rowIdx, dto.getValue36()); // 금액3
			pstmt.setString(++rowIdx, dto.getValue37()); // 금액4
			pstmt.setString(++rowIdx, dto.getPrepayment()); // 착불,선불결제여부
			pstmt.setString(++rowIdx, dto.getShipuniquene()); // 배송비특이사항
			pstmt.setString(++rowIdx, dto.getJejuprc()); // 제주추가배송비
			pstmt.setString(++rowIdx, dto.getIslandprc()); // 도서산간추가배송비
			pstmt.setString(++rowIdx, dto.getAddrin()); // 반품배송지주소
			pstmt.setString(++rowIdx, dto.getReturnshippingyn()); // 반품교환배송비타입
			pstmt.setString(++rowIdx, dto.getRetncngprc()); // 반품교환배송비
			pstmt.setString(++rowIdx, dto.getValue38()); // 가격비교등록여부
			pstmt.setString(++rowIdx, dto.getValue39()); // 단말기할부여부
			pstmt.setString(++rowIdx, dto.getValue40()); // 핸드폰가입비여부
			pstmt.setString(++rowIdx, dto.getValue41()); // 핸드폰유심비여부
			pstmt.setString(++rowIdx, dto.getValue42()); // 핸드폰가입신청URL1
			pstmt.setString(++rowIdx, dto.getValue43()); // 핸드폰가입신청내용적용1
			pstmt.setString(++rowIdx, dto.getValue44()); // 핸드폰가입신청URL2
			pstmt.setString(++rowIdx, dto.getValue45()); // 핸드폰가입신청내용적용2
			pstmt.setString(++rowIdx, dto.getValue46()); // 판매권사용갯수
			pstmt.setString(++rowIdx, dto.getValue47()); // 완구사용자성별여부
			pstmt.setString(++rowIdx, dto.getValue48()); // 완구사용자연령여부
			pstmt.setString(++rowIdx, dto.getValue49()); // 완구일부연령시작나이
			pstmt.setString(++rowIdx, dto.getValue50()); // 완구일부연령종료나이
			pstmt.setString(++rowIdx, dto.getValue51()); // 완구일부연령개월및나이선택
			pstmt.setString(++rowIdx, dto.getValue52()); // 완구특정연령시작나이
			pstmt.setString(++rowIdx, dto.getValue53()); // 완구특정연령개월및나이선택
			pstmt.setString(++rowIdx, dto.getValue54()); // 완구특정연령이상및이하선택
			pstmt.setString(++rowIdx, dto.getValue55()); // 상품설명상단추가문구
			pstmt.setString(++rowIdx, dto.getValue56()); // 상품설명하단추가문구
			pstmt.setString(++rowIdx, dto.getValue57()); // 판매자부담즉시할인여부
			pstmt.setString(++rowIdx, dto.getValue58()); // 판매자부담정액정률여부
			pstmt.setString(++rowIdx, dto.getValue59()); // 판매자부담할인금액
			pstmt.setString(++rowIdx, dto.getValue60()); // 할인기간시작일
			pstmt.setString(++rowIdx, dto.getValue61()); // 할인기간종료일
			pstmt.setString(++rowIdx, dto.getValue62()); // I-포인트선택여부
			pstmt.setString(++rowIdx, dto.getValue63()); // I-포인트할인
			pstmt.setString(++rowIdx, dto.getValue64()); // 무이자할부여부
			pstmt.setString(++rowIdx, dto.getUseyn()); // 사용여부

			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, seq);
			pstmt.executeUpdate();

			System.out.println("[insertdtl-ShopAddrDtlInsert]" + pstmt.toString());

			String sql_mst = " update shopaddrmst set TITLE = ? , SELMTHDCD = ? , USEYN = ? , UPDATEDT = ? where compno = ? and shopcd = ? and seq = ? ";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getSelmthdcd());
			pstmt.setString(3, dto.getUseyn());
			pstmt.setString(4, YDMATimeUtil.getCurrentTimeByYDFormat());

			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(6, shopcd);
			pstmt.setString(7, seq);

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public int ShopAddrDtlCafe24Insert(String shopcd, String title, String memo, String shopid, String supply,
			String consumers, String manfnm, String supplier, String brandnm, String trendnm, String repimage,
			String listimage, String imagemethod, String addiimageyn, String addiimagecnt, String shippinginfo,
			String shippingmethod, String dmstNovershipping, String shippingarea, String delvperiodfrom,
			String delvperiodto, String delvcostmethod, String delvdetailcost1, String delvdetailcost2,
			String delvdetailcost3, String delvfeesetup, String prodstats, String uesdperiod, String shippingtype,
			String otherval1, String otherval2, String minorselcnyn, String orderqtytype, String minimumqty,
			String maximumtype, String maximumqty, String useyn, int seq) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "insert into shopaddrcafe24dtl(SEQ,COMPNO, shopcd,TITLE, memo, SHOPID, SUPPLY, CONSUMERS, MANFNM, SUPPLIER, BRANDNM, TRENDNM,REPIMAGE, LISTIMAGE, "
					+ " IMAGEMETHOD,ADDIIMAGEYN, ADDIIMAGECNT, SHIPPINGINFO, SHIPPINGMETHOD, HOMENABROSHIP, SHIPPINGAREA, DELVPERIODFROM, DELVPERIODTO,DELVCOSTMETHOD,DELVCOST1, DELVCOST2, DELVCOST3,"
					+ " DELVFEESETUP, PRODSTATS, USEDPERIOD, SHIPPINGTYPE, OTHERVAL1, OTHERVAL2, MINORSELCNYN,ORDERQTYTYPE,MINIMUMQTY,MAXIMUMTYPE,MAXIMUMQTY,USEYN ) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ? , ?, ?, ?, ?, ?, ? ,? , ?, ?, ?)";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setInt(++rowIdx, seq);
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, title);
			pstmt.setString(++rowIdx, memo);
			pstmt.setString(++rowIdx, shopid);
			pstmt.setString(++rowIdx, supply);
			pstmt.setString(++rowIdx, consumers);
			pstmt.setString(++rowIdx, manfnm);
			pstmt.setString(++rowIdx, supplier);
			pstmt.setString(++rowIdx, brandnm);
			pstmt.setString(++rowIdx, trendnm);
			pstmt.setString(++rowIdx, repimage);
			pstmt.setString(++rowIdx, listimage);
			pstmt.setString(++rowIdx, imagemethod);
			pstmt.setString(++rowIdx, addiimageyn);
			pstmt.setString(++rowIdx, addiimagecnt);
			pstmt.setString(++rowIdx, shippinginfo);
			pstmt.setString(++rowIdx, shippingmethod);
			pstmt.setString(++rowIdx, dmstNovershipping);
			pstmt.setString(++rowIdx, shippingarea);
			pstmt.setString(++rowIdx, delvperiodfrom);
			pstmt.setString(++rowIdx, delvperiodto);
			pstmt.setString(++rowIdx, delvcostmethod);
			pstmt.setString(++rowIdx, delvdetailcost1);
			pstmt.setString(++rowIdx, delvdetailcost2);
			pstmt.setString(++rowIdx, delvdetailcost3);
			pstmt.setString(++rowIdx, delvfeesetup);
			pstmt.setString(++rowIdx, prodstats);
			pstmt.setString(++rowIdx, uesdperiod);
			pstmt.setString(++rowIdx, shippingtype);
			pstmt.setString(++rowIdx, otherval1);
			pstmt.setString(++rowIdx, otherval2);
			pstmt.setString(++rowIdx, minorselcnyn);
			pstmt.setString(++rowIdx, orderqtytype);
			pstmt.setString(++rowIdx, minimumqty);
			pstmt.setString(++rowIdx, maximumtype);
			pstmt.setString(++rowIdx, maximumqty);
			pstmt.setString(++rowIdx, useyn);

			pstmt.executeUpdate();

			System.out.println("[insertdtl-ShopAddrDtlInsert]" + pstmt.toString());

			String sql_mst = " insert into shopaddrmst (compno, SHOPCD, SEQ,  TITLE, SELMTHDCD, USEYN, INSERTDT) "
					+ "  VALUES (?, ?,?,?,?,?,?)";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setInt(3, seq);
			pstmt.setString(4, title);
			pstmt.setString(5, "");
			pstmt.setString(6, useyn);
			pstmt.setString(7, YDMATimeUtil.getCurrentTimeByYDFormat());

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public List<String> shopCafe24DetailjoinList(List<String> list) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> listjoin = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT IFNULL(dtl.shopcd,''),IFNULL(dtl.seq,''),IFNULL(dtl.title,''), ifnull(memo,''), IFNULL(dtl.SHOPID,''),IFNULL(dtl.SUPPLY,''),IFNULL(dtl.CONSUMERS,''),"
					+ " IFNULL(dtl.MANFNM,''), IFNULL(dtl.SUPPLIER,''),IFNULL(dtl.BRANDNM,''),IFNULL(dtl.TRENDNM,''),IFNULL(dtl.REPIMAGE,''),IFNULL(dtl.LISTIMAGE,''),"
					+ " IFNULL(dtl.IMAGEMETHOD,''),IFNULL(dtl.ADDIIMAGEYN,''), IFNULL(dtl.ADDIIMAGECNT,''),IFNULL(dtl.SHIPPINGINFO,''),IFNULL(dtl.SHIPPINGMETHOD,''),IFNULL(dtl.HOMENABROSHIP,''),"
					+ " IFNULL(dtl.SHIPPINGAREA,''), IFNULL(dtl.DELVPERIODFROM,''),IFNULL(dtl.DELVPERIODTO,''),IFNULL(dtl.DELVCOSTMETHOD,''), IFNULL(dtl.DELVCOST1,'0'),IFNULL(dtl.DELVCOST2,'0'), "
					+ " IFNULL(dtl.DELVCOST3,'0'),IFNULL(dtl.DELVFEESETUP,''), IFNULL(dtl.PRODSTATS,''),IFNULL(dtl.USEDPERIOD,'0'), ifnull(dtl.SHIPPINGTYPE,''), ifnull(dtl.OTHERVAL1,''), "
					+ " ifnull(dtl.OTHERVAL2,''), ifnull(dtl.MINORSELCNYN,''), ifnull(dtl.ORDERQTYTYPE,''), ifnull(dtl.MINIMUMQTY,'0'),ifnull(dtl.MAXIMUMTYPE,''),ifnull(dtl.MAXIMUMQTY,'0'),ifnull(dtl.USEYN,'') "
					+ " FROM SHOPADDRMST addr JOIN SHOPMST MST ON MST.SHOPCD=ADDR.SHOPCD and MST.compno = ADDR.compno JOIN shopaddrcafe24dtl dtl ON ADDR.seq = dtl.seq and ADDR.compno = dtl.compno join shopinfo a on "
					+ " mst.shopcd = a.shopcd " + " WHERE addr.seq = ? AND a.shopnm = ? and mst.compno = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, list.get(0));
			pstmt.setString(2, list.get(1));
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[shopDetailjoinList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return listjoin;
	}

	public int ShopAddrDtlCafe24Update(String shopcd, String title, String memo, String shopid, String supply,
			String consumers, String manfnm, String supplier, String brandnm, String trendnm, String repimage,
			String listimage, String imagemethod, String addiimageyn, String addiimagecnt, String shippinginfo,
			String shippingmethod, String dmstNovershipping, String shippingarea, String delvperiodfrom,
			String delvperiodto, String delvcostmethod, String delvdetailcost1, String delvdetailcost2,
			String delvdetailcost3, String delvfeesetup, String prodstats, String uesdperiod, String shippingtype,
			String otherval1, String otherval2, String minorselcnyn, String orderqtytype, String minimumqty,
			String maximumtype, String maximumqty, String useyn, String seq) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update shopaddrcafe24dtl set TITLE = ?, memo = ?, SHOPID = ?, SUPPLY = ?, CONSUMERS = ?, MANFNM = ?, SUPPLIER = ?, BRANDNM = ?, TRENDNM = ?, REPIMAGE = ?, "
					+ " LISTIMAGE = ?, IMAGEMETHOD = ?, ADDIIMAGEYN = ?, ADDIIMAGECNT = ?, SHIPPINGINFO = ?, SHIPPINGMETHOD = ?, HOMENABROSHIP = ?, SHIPPINGAREA = ?, "
					+ " DELVPERIODFROM = ?, DELVPERIODTO = ?, DELVCOSTMETHOD = ?, DELVCOST1 = ?, DELVCOST2 = ?, DELVCOST3 = ?, DELVFEESETUP = ?, PRODSTATS = ?,USEDPERIOD = ?,SHIPPINGTYPE = ?,OTHERVAL1 = ?, "
					+ " OTHERVAL2 = ?, MINORSELCNYN = ?, ORDERQTYTYPE = ?, MINIMUMQTY = ?, MAXIMUMTYPE = ?, MAXIMUMQTY = ?, USEYN = ? "
					+ " where COMPNO = ? and SHOPCD = ? and SEQ = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, title);
			pstmt.setString(++rowIdx, memo);
			pstmt.setString(++rowIdx, shopid);
			pstmt.setString(++rowIdx, supply);
			pstmt.setString(++rowIdx, consumers);
			pstmt.setString(++rowIdx, manfnm);
			pstmt.setString(++rowIdx, supplier);
			pstmt.setString(++rowIdx, brandnm);
			pstmt.setString(++rowIdx, trendnm);
			pstmt.setString(++rowIdx, repimage);
			pstmt.setString(++rowIdx, listimage);
			pstmt.setString(++rowIdx, imagemethod);
			pstmt.setString(++rowIdx, addiimageyn);
			pstmt.setString(++rowIdx, addiimagecnt);
			pstmt.setString(++rowIdx, shippinginfo);
			pstmt.setString(++rowIdx, shippingmethod);
			pstmt.setString(++rowIdx, dmstNovershipping);
			pstmt.setString(++rowIdx, shippingarea);
			pstmt.setString(++rowIdx, delvperiodfrom);
			pstmt.setString(++rowIdx, delvperiodto);
			pstmt.setString(++rowIdx, delvcostmethod);
			pstmt.setString(++rowIdx, delvdetailcost1);
			pstmt.setString(++rowIdx, delvdetailcost2);
			pstmt.setString(++rowIdx, delvdetailcost3);
			pstmt.setString(++rowIdx, delvfeesetup);
			pstmt.setString(++rowIdx, prodstats);
			pstmt.setString(++rowIdx, uesdperiod);
			pstmt.setString(++rowIdx, shippingtype);
			pstmt.setString(++rowIdx, otherval1);
			pstmt.setString(++rowIdx, otherval2);
			pstmt.setString(++rowIdx, minorselcnyn);
			pstmt.setString(++rowIdx, orderqtytype);
			pstmt.setString(++rowIdx, minimumqty);
			pstmt.setString(++rowIdx, maximumtype);
			pstmt.setString(++rowIdx, maximumqty);
			pstmt.setString(++rowIdx, useyn);

			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, seq);
			pstmt.executeUpdate();

			System.out.println("[insertdtl-ShopAddrDtlInsert]" + pstmt.toString());

			String sql_mst = " update shopaddrmst set TITLE = ? , SELMTHDCD = ? , USEYN = ? , UPDATEDT = ? where compno = ? and shopcd = ? and seq = ? ";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, title);
			pstmt.setString(2, "");
			pstmt.setString(3, useyn);
			pstmt.setString(4, YDMATimeUtil.getCurrentTimeByYDFormat());

			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(6, shopcd);
			pstmt.setString(7, seq);

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public int ShopAddrDtlAuctionInsert(String shopcd, ShopProductAuctionAdditionDto dto, int seq) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "insert into shopaddrauctiondtl(SEQ,COMPNO, shopcd,TITLE, SHOPID,memo, SELMTHDCD, VALUE1,VALUE2,VALUE3,VALUE4,VALUE5,VALUE6,VALUE7,VALUE8,VALUE9,VALUE10,VALUE11,VALUE12, "
					+ " PRODSTATCD, USEDPERIOD, VALUE13, MAXIMUMQTYTYP, LIMITQTY, LIMITTIME, OPTEXHIBITION, VALUE14, INPUTTYPOPT, VALUE15, SALESPERIOD, PRE_SALE, VALUE16, IMAGE, PRODINFOAREA, "
					+ " PRODADDITIONAL, PROMOTION, ADULTGOODS, CHILDPROD, HOMEHOLDPROD, ELECTRICAL,CHEMISTRY,ECO, MEDICAL, ORIGIN, MULTIORIGIN, ASDTL, SHIPPINGPOLICY, SHIPPINGMETHOD, EXPRESS, "
					+ " VALUE17, VALUE69, VALUE70, VALUE18, VALUE19, VALUE20, VALUE21, VALUE22, VALUE23, OUTADDR, BUNDLEDYN,BUNDLEMAXNMIN,TEMPLATE,DELVCOSTTYP,DELVCOST,DELVFREECOST, COSTDISCOUNT,VALUE24, "
					+ " VALUE25, VALUE26, VALUE27, VALUE28, VALUE29, VALUE30, VALUE31, VALUE32, VALUE33, PREPAYMENT, RETNEXCHANGEADDR,RETNEXCHANGECOST, ORDERDELAYTIME, VALUE34, VALUE35, VALUE36, "
					+ " VALUE37, VALUE38, VALUE39, VALUE40, VALUE41, VALUE42, VALUE43, VALUE44, VALUE45, VALUE46, VALUE47, VALUE48, VALUE49, VALUE50, VALUE51, VALUE52, VALUE53, VALUE54, VALUE55, "
					+ " VALUE56, VALUE57, VALUE58, VALUE59, VALUE60, VALUE61, VALUE62, VALUE63, VALUE64, VALUE65, VALUE66, VALUE67, VALUE68, USEYN) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ? , ?, ?, ?, ?, ?, ? ,? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "// 56
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? )";// 116
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setInt(++rowIdx, seq); // 일련번호
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno()); // 회사코드
			pstmt.setString(++rowIdx, shopcd); // 쇼핑몰코드
			pstmt.setString(++rowIdx, dto.getTitle()); // 제목
			pstmt.setString(++rowIdx, dto.getShopid()); // 쇼핑몰아이디
			pstmt.setString(++rowIdx, dto.getMemo()); // 메모
			pstmt.setString(++rowIdx, dto.getSelmthdcd()); // 형태구분
			pstmt.setString(++rowIdx, dto.getValue1()); // 필수속성정보
			pstmt.setString(++rowIdx, dto.getValue2()); // 브랜드카테고리선택
			pstmt.setString(++rowIdx, dto.getValue3()); // 소호노출범위여부
			pstmt.setString(++rowIdx, dto.getValue4()); // 소호노출카테고리
			pstmt.setString(++rowIdx, dto.getValue5()); // 소호노출스타일
			pstmt.setString(++rowIdx, dto.getValue6()); // 소호노출샤프
			pstmt.setString(++rowIdx, dto.getValue7()); // 소호노출칼라
			pstmt.setString(++rowIdx, dto.getValue8()); // 판매자닉네임여부
			pstmt.setString(++rowIdx, dto.getValue9()); // 모델명적용여부
			pstmt.setString(++rowIdx, dto.getValue10()); // 모델명
			pstmt.setString(++rowIdx, dto.getValue11()); // 모델명수정여부
			pstmt.setString(++rowIdx, dto.getValue12()); // ESM브랜드
			pstmt.setString(++rowIdx, dto.getProdstatcd()); // 상품상태
			pstmt.setString(++rowIdx, dto.getUsedperiod()); // 중고개월수
			pstmt.setString(++rowIdx, dto.getValue13()); // 쿠폰유효기간
			pstmt.setString(++rowIdx, dto.getMaximumqtytyp()); // 최대구매허용수량여부
			pstmt.setString(++rowIdx, dto.getLimitqty()); // 제한수량
			pstmt.setString(++rowIdx, dto.getLimittime()); // 제한기간
			pstmt.setString(++rowIdx, dto.getOptexhibition()); // 옵션재고전시방법
			pstmt.setString(++rowIdx, dto.getValue14()); // 옵션노출정렬여부
			pstmt.setString(++rowIdx, dto.getInputtypopt()); // 입력형옵션
			pstmt.setString(++rowIdx, dto.getValue15()); // 추가상품전시여부
			pstmt.setString(++rowIdx, dto.getSalesperiod()); // 판매기간
			pstmt.setString(++rowIdx, dto.getPre_sale()); // 예약판매여부
			pstmt.setString(++rowIdx, dto.getValue16()); // 예약판매배송일
			pstmt.setString(++rowIdx, dto.getImage()); // 대표이미지
			pstmt.setString(++rowIdx, dto.getProdinfoarea()); // 상품정보입력영역
			pstmt.setString(++rowIdx, dto.getProdadditional()); // 추가구성입력영역
			pstmt.setString(++rowIdx, dto.getPromotion()); // 광고/홍보입력영역
			pstmt.setString(++rowIdx, dto.getAdultgoods()); // 성인용품여부
			pstmt.setString(++rowIdx, dto.getChildprod()); // 어린이제품
			pstmt.setString(++rowIdx, dto.getHomeholdprod()); // 생활용품
			pstmt.setString(++rowIdx, dto.getElectrical()); // 전기용품
			pstmt.setString(++rowIdx, dto.getChemistry()); // 생활화학/살생물제품
			pstmt.setString(++rowIdx, dto.getEco()); // 친환경
			pstmt.setString(++rowIdx, dto.getMedical()); // 의료기기
			pstmt.setString(++rowIdx, dto.getOrigin()); // 원산지선택여부
			pstmt.setString(++rowIdx, dto.getMultiorigin()); // 복수원산지여부
			pstmt.setString(++rowIdx, dto.getAsdtl()); // A/S정보
			pstmt.setString(++rowIdx, dto.getShippingpolicy()); // 발송정책
			pstmt.setString(++rowIdx, dto.getShippingmethod()); // 배송방법
			pstmt.setString(++rowIdx, dto.getExpress()); // 택배사
			pstmt.setString(++rowIdx, dto.getValue17()); // 방문수령여부
			pstmt.setString(++rowIdx, dto.getValue69()); // 방문수령가격할인금액
			pstmt.setString(++rowIdx, dto.getValue70()); // 방문수령사은품
			pstmt.setString(++rowIdx, dto.getValue18()); // 방문수령주소
			pstmt.setString(++rowIdx, dto.getValue19()); // 퀵서비스업체명
			pstmt.setString(++rowIdx, dto.getValue20()); // 퀵서비스연락처
			pstmt.setString(++rowIdx, dto.getValue21()); // 퀵서비스배송가능지역
			pstmt.setString(++rowIdx, dto.getValue22()); // 일반우편여부
			pstmt.setString(++rowIdx, dto.getValue23()); // 일반우폄금액
			pstmt.setString(++rowIdx, dto.getOutaddr()); // 출하지
			pstmt.setString(++rowIdx, dto.getBundledyn()); // 묶음배송여부
			pstmt.setString(++rowIdx, dto.getBundlemaxnmin()); // 묶음배송시배송비
			pstmt.setString(++rowIdx, dto.getTemplate()); // 묶음배송시템플릿
			pstmt.setString(++rowIdx, dto.getDelvcosttyp()); // 택배비무료여부
			pstmt.setString(++rowIdx, dto.getDelvcost()); // 배송기본배송비
			pstmt.setString(++rowIdx, dto.getDelvfreecost()); // 기본배송비2
			pstmt.setString(++rowIdx, dto.getCostdiscount()); // 배송비할인여부
			pstmt.setString(++rowIdx, dto.getValue24()); // 수량별차등수량1
			pstmt.setString(++rowIdx, dto.getValue25()); // 수량별차등수량2
			pstmt.setString(++rowIdx, dto.getValue26()); // 수량별차등수량3
			pstmt.setString(++rowIdx, dto.getValue27()); // 수량별차등수량4
			pstmt.setString(++rowIdx, dto.getValue28()); // 수량별차등수량5
			pstmt.setString(++rowIdx, dto.getValue29()); // 수량별차등배송비1
			pstmt.setString(++rowIdx, dto.getValue30()); // 수량별차등배송비2
			pstmt.setString(++rowIdx, dto.getValue31()); // 수량별차등배송비3
			pstmt.setString(++rowIdx, dto.getValue32()); // 수량별차등배송비4
			pstmt.setString(++rowIdx, dto.getValue33()); // 수량별차등배송비5
			pstmt.setString(++rowIdx, dto.getPrepayment()); // 착/선불여부
			pstmt.setString(++rowIdx, dto.getRetnexchangeaddr()); // 교환/반품주소
			pstmt.setString(++rowIdx, dto.getRetnexchangecost()); // 교환/반품배송비
			pstmt.setString(++rowIdx, dto.getOrderdelaytime()); // 주문후예상배송기간
			pstmt.setString(++rowIdx, dto.getValue34()); // 건강식품신고기관명
			pstmt.setString(++rowIdx, dto.getValue35()); // 건강식품신고번호
			pstmt.setString(++rowIdx, dto.getValue36()); // 건강식품심의번호
			pstmt.setString(++rowIdx, dto.getValue37()); // 판매자관리코드
			pstmt.setString(++rowIdx, dto.getValue38()); // 판매자직접입력관리코드
			pstmt.setString(++rowIdx, dto.getValue39()); // 판매자카테고리
			pstmt.setString(++rowIdx, dto.getValue40()); // 사은품
			pstmt.setString(++rowIdx, dto.getValue41()); // 포털가격비교사이트등록여부
			pstmt.setString(++rowIdx, dto.getValue42()); // 포털가격비교사이트쿠폰적용여부
			pstmt.setString(++rowIdx, dto.getValue43()); // 사이트부담지원할인
			pstmt.setString(++rowIdx, dto.getValue44()); // 휴대폰가입신청서URL
			pstmt.setString(++rowIdx, dto.getValue45()); // 렌탈설치비
			pstmt.setString(++rowIdx, dto.getValue46()); // 렌탈의무사용기간
			pstmt.setString(++rowIdx, dto.getValue47()); // 렌탈등록비
			pstmt.setString(++rowIdx, dto.getValue48()); // 옵션관리코드여부
			pstmt.setString(++rowIdx, dto.getValue49()); // 기타특이사항
			pstmt.setString(++rowIdx, dto.getValue50()); // 상품명앞추가문구
			pstmt.setString(++rowIdx, dto.getValue51()); // 상품명뒷추가문구
			pstmt.setString(++rowIdx, dto.getValue52()); // 상품설명상단추가문구
			pstmt.setString(++rowIdx, dto.getValue53()); // 상품설명하단추가문구
			pstmt.setString(++rowIdx, dto.getValue54()); // 판매자부담할인여부
			pstmt.setString(++rowIdx, dto.getValue55()); // 판매자부담할인금액
			pstmt.setString(++rowIdx, dto.getValue56()); // 판매자부담할인시작일
			pstmt.setString(++rowIdx, dto.getValue57()); // 판매자부담할인종료일
			pstmt.setString(++rowIdx, dto.getValue58()); // 특별할인여부
			pstmt.setString(++rowIdx, dto.getValue59()); // 특별할인시작일
			pstmt.setString(++rowIdx, dto.getValue60()); // 특별할인종료일
			pstmt.setString(++rowIdx, dto.getValue61()); // 특별할인회원할인금액
			pstmt.setString(++rowIdx, dto.getValue62()); // 특별할인복수구매주문수량
			pstmt.setString(++rowIdx, dto.getValue63()); // 특별할인복수구매할인금액
			pstmt.setString(++rowIdx, dto.getValue64()); // 판매자스마일캐시여부
			pstmt.setString(++rowIdx, dto.getValue65()); // 스마일캐시적립률
			pstmt.setString(++rowIdx, dto.getValue66()); // 나눔쇼핑시작일
			pstmt.setString(++rowIdx, dto.getValue67()); // 나눔쇼핑종료일
			pstmt.setString(++rowIdx, dto.getValue68()); // 나눔쇼핑금액
			pstmt.setString(++rowIdx, dto.getUseyn()); // 사용여부

			pstmt.executeUpdate();

			System.out.println("[insertdtl-ShopAddrDtlInsert]" + pstmt.toString());

			String sql_mst = " insert into shopaddrmst (compno, SHOPCD, SEQ,  TITLE, SELMTHDCD, USEYN, INSERTDT) "
					+ "  VALUES (?, ?,?,?,?,?,?)";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setInt(3, seq);
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getSelmthdcd());
			pstmt.setString(6, dto.getUseyn());
			pstmt.setString(7, YDMATimeUtil.getCurrentTimeByYDFormat());

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public ShopProductAuctionAdditionDto shopAuctionDetailjoinList(List<String> list) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShopProductAuctionAdditionDto dto = new ShopProductAuctionAdditionDto();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT ifnull(dtl.seq,'0'), IFNULL(dtl.compno,'0'),IFNULL(dtl.shopcd,''),IFNULL(dtl.title,''), ifnull(dtl.SHOPID,''), IFNULL(dtl.MEMO,''),IFNULL(dtl.SELMTHDCD,''),IFNULL(dtl.VALUE1,''),"
					+ " IFNULL(dtl.VALUE2,''), IFNULL(dtl.VALUE3,''),IFNULL(dtl.VALUE4,''),IFNULL(dtl.VALUE5,''),IFNULL(dtl.VALUE6,''),IFNULL(dtl.VALUE7,''),IFNULL(dtl.VALUE8,''),IFNULL(dtl.VALUE9,''),"
					+ " IFNULL(dtl.VALUE10,''),IFNULL(dtl.VALUE11,''), IFNULL(dtl.VALUE12,''),IFNULL(dtl.PRODSTATCD,''),IFNULL(dtl.USEDPERIOD,'0'),IFNULL(dtl.VALUE13,'0'),ifnull(dtl.MAXIMUMQTYTYP,''), "
					+ " IFNULL(dtl.LIMITQTY,'0'), IFNULL(dtl.LIMITTIME,'0'),IFNULL(dtl.OPTEXHIBITION,''),IFNULL(dtl.VALUE14,''), IFNULL(dtl.INPUTTYPOPT,''),IFNULL(dtl.VALUE15,''), ifnull(dtl.SALESPERIOD,''),"
					+ " IFNULL(dtl.PRE_SALE,''), IFNULL(dtl.VALUE16,''),IFNULL(dtl.IMAGE,''),IFNULL(dtl.PRODINFOAREA,''), IFNULL(dtl.PRODADDITIONAL,''),IFNULL(dtl.PROMOTION,''), "
					+ " IFNULL(dtl.ADULTGOODS,''), IFNULL(dtl.CHILDPROD,''),IFNULL(dtl.HOMEHOLDPROD,''),IFNULL(dtl.ELECTRICAL,''), IFNULL(dtl.CHEMISTRY,''),IFNULL(dtl.ECO,''), "
					+ " IFNULL(dtl.MEDICAL,''),IFNULL(dtl.ORIGIN,''), IFNULL(dtl.MULTIORIGIN,''),IFNULL(dtl.ASDTL,''), ifnull(dtl.SHIPPINGPOLICY,''), ifnull(dtl.SHIPPINGMETHOD,''), "
					+ " ifnull(dtl.EXPRESS,''), ifnull(dtl.VALUE17,''), ifnull(dtl.VALUE69,'0'), ifnull(dtl.VALUE70,''),ifnull(dtl.VALUE18,''),ifnull(dtl.VALUE19,''),ifnull(dtl.VALUE20,''), ifnull(dtl.VALUE21,''),"
					+ " ifnull(dtl.VALUE22,''), ifnull(dtl.VALUE23,'0'), ifnull(dtl.OUTADDR,''), ifnull(dtl.BUNDLEDYN,''), ifnull(dtl.BUNDLEMAXNMIN,''),ifnull(dtl.TEMPLATE,''),ifnull(dtl.DELVCOSTTYP,''),"
					+ " ifnull(dtl.DELVCOST,''), ifnull(dtl.DELVFREECOST,''), ifnull(dtl.COSTDISCOUNT,''), ifnull(dtl.VALUE24,'0'), ifnull(dtl.VALUE25,'0'), ifnull(dtl.VALUE26,'0'), ifnull(dtl.VALUE27,'0'), "
					+ " ifnull(dtl.VALUE28,'0'), ifnull(dtl.VALUE29,'0'), ifnull(dtl.VALUE30,'0'), ifnull(dtl.VALUE31,'0'),ifnull(dtl.VALUE32,'0'), ifnull(dtl.VALUE33,'0'),"
					+ " ifnull(dtl.PREPAYMENT,''), ifnull(dtl.RETNEXCHANGEADDR,''),ifnull(dtl.RETNEXCHANGECOST,'0'), ifnull(dtl.ORDERDELAYTIME,''),ifnull(dtl.VALUE34,''), ifnull(dtl.VALUE35,''), "
					+ " ifnull(dtl.VALUE36,''), ifnull(dtl.VALUE37,''),ifnull(dtl.VALUE38,''), ifnull(dtl.VALUE39,''),ifnull(dtl.VALUE40,''), ifnull(dtl.VALUE41,''),ifnull(dtl.VALUE42,''), "
					+ " ifnull(dtl.VALUE43,''),ifnull(dtl.VALUE44,''), ifnull(dtl.VALUE45,'0'),ifnull(dtl.VALUE46,'0'), ifnull(dtl.VALUE47,'0'),ifnull(dtl.VALUE48,'0'), ifnull(dtl.VALUE49,''), "
					+ " ifnull(dtl.VALUE50,''), ifnull(dtl.VALUE51,''), ifnull(dtl.VALUE52,''),ifnull(dtl.VALUE53,''), ifnull(dtl.VALUE54,''),ifnull(dtl.VALUE55,''), ifnull(dtl.VALUE56,''), ifnull(dtl.VALUE57,''), "
					+ " ifnull(dtl.VALUE58,''), ifnull(dtl.VALUE59,''), ifnull(dtl.VALUE60,''),ifnull(dtl.VALUE61,'0'), ifnull(dtl.VALUE62,''),ifnull(dtl.VALUE63,'0'), ifnull(dtl.VALUE64,''), "
					+ " ifnull(dtl.VALUE65,'0'), ifnull(dtl.VALUE66,''),ifnull(dtl.VALUE67,''),ifnull(dtl.VALUE68,''), ifnull(dtl.USEYN,'') "
					+ " FROM SHOPADDRMST addr JOIN SHOPMST MST ON MST.SHOPCD=ADDR.SHOPCD and MST.compno = ADDR.compno JOIN shopaddrauctiondtl dtl ON ADDR.seq = dtl.seq and ADDR.compno = dtl.compno join shopinfo a on "
					+ " mst.shopcd = a.shopcd WHERE addr.seq = ? AND a.shopnm = ? and mst.compno = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, list.get(0));
			pstmt.setString(2, list.get(1));
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[shopAuctionDetailjoinList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				dto.setSeq(rs.getString(++columnIndex)); // 일련번호
				dto.setCompno(rs.getString(++columnIndex)); // 회사코드
				dto.setShopcd(rs.getString(++columnIndex)); // 쇼핑몰코드
				dto.setTitle(rs.getString(++columnIndex)); // 제목
				dto.setShopid(rs.getString(++columnIndex)); // 쇼핑몰아이디
				dto.setMemo(rs.getString(++columnIndex)); // 메모
				dto.setSelmthdcd(rs.getString(++columnIndex)); // 형태구분
				dto.setValue1(rs.getString(++columnIndex)); // 필수속성정보
				dto.setValue2(rs.getString(++columnIndex)); // 브랜드카테고리선택
				dto.setValue3(rs.getString(++columnIndex)); // 소호노출범위여부
				dto.setValue4(rs.getString(++columnIndex)); // 소호노출카테고리
				dto.setValue5(rs.getString(++columnIndex)); // 소호노출스타일
				dto.setValue6(rs.getString(++columnIndex)); // 소호노출샤프
				dto.setValue7(rs.getString(++columnIndex)); // 소호노출칼라
				dto.setValue8(rs.getString(++columnIndex)); // 판매자닉네임여부
				dto.setValue9(rs.getString(++columnIndex)); // 모델명적용여부
				dto.setValue10(rs.getString(++columnIndex)); // 모델명
				dto.setValue11(rs.getString(++columnIndex)); // 모델명수정여부
				dto.setValue12(rs.getString(++columnIndex)); // ESM브랜드
				dto.setProdstatcd(rs.getString(++columnIndex)); // 상품상태
				dto.setUsedperiod(rs.getString(++columnIndex)); // 중고개월수
				dto.setValue13(rs.getString(++columnIndex)); // 쿠폰유효기간
				dto.setMaximumqtytyp(rs.getString(++columnIndex)); // 최대구매허용수량여부
				dto.setLimitqty(rs.getString(++columnIndex)); // 제한수량
				dto.setLimittime(rs.getString(++columnIndex)); // 제한기간
				dto.setOptexhibition(rs.getString(++columnIndex)); // 옵션재고전시방법
				dto.setValue14(rs.getString(++columnIndex)); // 옵션노출정렬여부
				dto.setInputtypopt(rs.getString(++columnIndex)); // 입력형옵션
				dto.setValue15(rs.getString(++columnIndex)); // 추가상품전시여부
				dto.setSalesperiod(rs.getString(++columnIndex)); // 판매기간
				dto.setPre_sale(rs.getString(++columnIndex)); // 예약판매여부
				dto.setValue16(rs.getString(++columnIndex)); // 예약판매배송일
				dto.setImage(rs.getString(++columnIndex)); // 대표이미지
				dto.setProdinfoarea(rs.getString(++columnIndex)); // 상품정보입력영역
				dto.setProdadditional(rs.getString(++columnIndex)); // 추가구성입력영역
				dto.setPromotion(rs.getString(++columnIndex)); // 광고/홍보입력영역
				dto.setAdultgoods(rs.getString(++columnIndex)); // 성인용품여부
				dto.setChildprod(rs.getString(++columnIndex)); // 어린이제품
				dto.setHomeholdprod(rs.getString(++columnIndex)); // 생활용품
				dto.setElectrical(rs.getString(++columnIndex)); // 전기용품
				dto.setChemistry(rs.getString(++columnIndex)); // 생활화학/살생물제품
				dto.setEco(rs.getString(++columnIndex)); // 친환경
				dto.setMedical(rs.getString(++columnIndex)); // 의료기기
				dto.setOrigin(rs.getString(++columnIndex)); // 원산지선택여부
				dto.setMultiorigin(rs.getString(++columnIndex)); // 복수원산지여부
				dto.setAsdtl(rs.getString(++columnIndex)); // A/S정보
				dto.setShippingpolicy(rs.getString(++columnIndex)); // 발송정책
				dto.setShippingmethod(rs.getString(++columnIndex)); // 배송방법
				dto.setExpress(rs.getString(++columnIndex)); // 택배사
				dto.setValue17(rs.getString(++columnIndex)); // 방문수령여부
				dto.setValue69(rs.getString(++columnIndex)); // 방문수령가격할인금액
				dto.setValue70(rs.getString(++columnIndex)); // 방문수령사은품
				dto.setValue18(rs.getString(++columnIndex)); // 방문수령주소
				dto.setValue19(rs.getString(++columnIndex)); // 퀵서비스업체명
				dto.setValue20(rs.getString(++columnIndex)); // 퀵서비스연락처
				dto.setValue21(rs.getString(++columnIndex)); // 퀵서비스배송가능지역
				dto.setValue22(rs.getString(++columnIndex)); // 일반우편여부
				dto.setValue23(rs.getString(++columnIndex)); // 일반우폄금액
				dto.setOutaddr(rs.getString(++columnIndex)); // 출하지
				dto.setBundledyn(rs.getString(++columnIndex)); // 묶음배송여부
				dto.setBundlemaxnmin(rs.getString(++columnIndex)); // 묶음배송시배송비
				dto.setTemplate(rs.getString(++columnIndex)); // 묶음배송시템플릿
				dto.setDelvcosttyp(rs.getString(++columnIndex)); // 택배비무료여부
				dto.setDelvcost(rs.getString(++columnIndex)); // 배송기본배송비
				dto.setDelvfreecost(rs.getString(++columnIndex)); // 기본배송비2
				dto.setCostdiscount(rs.getString(++columnIndex)); // 배송비할인여부
				dto.setValue24(rs.getString(++columnIndex)); // 수량별차등수량1
				dto.setValue25(rs.getString(++columnIndex)); // 수량별차등수량2
				dto.setValue26(rs.getString(++columnIndex)); // 수량별차등수량3
				dto.setValue27(rs.getString(++columnIndex)); // 수량별차등수량4
				dto.setValue28(rs.getString(++columnIndex)); // 수량별차등수량5
				dto.setValue29(rs.getString(++columnIndex)); // 수량별차등배송비1
				dto.setValue30(rs.getString(++columnIndex)); // 수량별차등배송비2
				dto.setValue31(rs.getString(++columnIndex)); // 수량별차등배송비3
				dto.setValue32(rs.getString(++columnIndex)); // 수량별차등배송비4
				dto.setValue33(rs.getString(++columnIndex)); // 수량별차등배송비5
				dto.setPrepayment(rs.getString(++columnIndex)); // 착/선불여부
				dto.setRetnexchangeaddr(rs.getString(++columnIndex)); // 교환/반품주소
				dto.setRetnexchangecost(rs.getString(++columnIndex)); // 교환/반품배송비
				dto.setOrderdelaytime(rs.getString(++columnIndex)); // 주문후예상배송기간
				dto.setValue34(rs.getString(++columnIndex)); // 건강식품신고기관명
				dto.setValue35(rs.getString(++columnIndex)); // 건강식품신고번호
				dto.setValue36(rs.getString(++columnIndex)); // 건강식품심의번호
				dto.setValue37(rs.getString(++columnIndex)); // 판매자관리코드
				dto.setValue38(rs.getString(++columnIndex)); // 판매자직접입력관리코드
				dto.setValue39(rs.getString(++columnIndex)); // 판매자카테고리
				dto.setValue40(rs.getString(++columnIndex)); // 사은품
				dto.setValue41(rs.getString(++columnIndex)); // 포털가격비교사이트등록여부
				dto.setValue42(rs.getString(++columnIndex)); // 포털가격비교사이트쿠폰적용여부
				dto.setValue43(rs.getString(++columnIndex)); // 사이트부담지원할인
				dto.setValue44(rs.getString(++columnIndex)); // 휴대폰가입신청서URL
				dto.setValue45(rs.getString(++columnIndex)); // 렌탈설치비
				dto.setValue46(rs.getString(++columnIndex)); // 렌탈의무사용기간
				dto.setValue47(rs.getString(++columnIndex)); // 렌탈등록비
				dto.setValue48(rs.getString(++columnIndex)); // 옵션관리코드여부
				dto.setValue49(rs.getString(++columnIndex)); // 기타특이사항
				dto.setValue50(rs.getString(++columnIndex)); // 상품명앞추가문구
				dto.setValue51(rs.getString(++columnIndex)); // 상품명뒷추가문구
				dto.setValue52(rs.getString(++columnIndex)); // 상품설명상단추가문구
				dto.setValue53(rs.getString(++columnIndex)); // 상품설명하단추가문구
				dto.setValue54(rs.getString(++columnIndex)); // 판매자부담할인여부
				dto.setValue55(rs.getString(++columnIndex)); // 판매자부담할인금액
				dto.setValue56(rs.getString(++columnIndex)); // 판매자부담할인시작일
				dto.setValue57(rs.getString(++columnIndex)); // 판매자부담할인종료일
				dto.setValue58(rs.getString(++columnIndex)); // 특별할인여부
				dto.setValue59(rs.getString(++columnIndex)); // 특별할인시작일
				dto.setValue60(rs.getString(++columnIndex)); // 특별할인종료일
				dto.setValue61(rs.getString(++columnIndex)); // 특별할인회원할인금액
				dto.setValue62(rs.getString(++columnIndex)); // 특별할인복수구매주문수량
				dto.setValue63(rs.getString(++columnIndex)); // 특별할인복수구매할인금액
				dto.setValue64(rs.getString(++columnIndex)); // 판매자스마일캐시여부
				dto.setValue65(rs.getString(++columnIndex)); // 스마일캐시적립률
				dto.setValue66(rs.getString(++columnIndex)); // 나눔쇼핑시작일
				dto.setValue67(rs.getString(++columnIndex)); // 나눔쇼핑종료일
				dto.setValue68(rs.getString(++columnIndex)); // 나눔쇼핑금액
				dto.setUseyn(rs.getString(++columnIndex)); // 사용여부

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return dto;
	}

	public int ShopAddrDtlAuctionUpdate(String shopcd, ShopProductAuctionAdditionDto dto, String seq) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update shopaddrauctiondtl set TITLE = ?,  SHOPID = ?, memo = ?,SELMTHDCD = ?, VALUE1 = ?, VALUE2 = ?, VALUE3 = ?, VALUE4 = ?, VALUE5 = ?, VALUE6 = ?, VALUE7 = ?, "
					+ " VALUE8= ? , VALUE9 = ?, VALUE10 = ?, VALUE11= ? , VALUE12 = ?, PRODSTATCD = ?, USEDPERIOD = ?, VALUE13 = ?, MAXIMUMQTYTYP = ?, LIMITQTY = ?, LIMITTIME = ?, OPTEXHIBITION = ?, "
					+ " VALUE14 = ?, INPUTTYPOPT = ?, VALUE15 = ?, SALESPERIOD = ?, PRE_SALE = ?, VALUE16 = ?, IMAGE = ?, PRODINFOAREA = ?, PRODADDITIONAL = ?, PROMOTION = ?, ADULTGOODS = ?, CHILDPROD = ?, "
					+ " HOMEHOLDPROD = ?, ELECTRICAL = ?, CHEMISTRY = ?, ECO = ?, MEDICAL = ?, ORIGIN = ?, MULTIORIGIN = ?, ASDTL = ?, SHIPPINGPOLICY = ?, SHIPPINGMETHOD = ?,EXPRESS = ?, VALUE17 = ?, "
					+ " VALUE69 = ?, VALUE70 = ?, VALUE18 = ?, VALUE19 = ?, VALUE20 = ?, VALUE21 = ?, VALUE22 = ?, VALUE23 = ?, OUTADDR = ?,BUNDLEDYN = ?, BUNDLEMAXNMIN = ?, "
					+ " TEMPLATE = ?, DELVCOSTTYP = ?, DELVCOST = ?, DELVFREECOST = ?, COSTDISCOUNT = ?, VALUE24 = ?, VALUE25 = ?, VALUE26 = ?, VALUE27 = ?, VALUE28 = ?, VALUE29 = ?, VALUE30 = ?, "
					+ " VALUE31 = ?, VALUE32 = ?, VALUE33 = ?, PREPAYMENT = ?, RETNEXCHANGEADDR = ?,RETNEXCHANGECOST=?,ORDERDELAYTIME=?, VALUE34 = ? , VALUE35 = ? , VALUE36 = ? , "
					+ " VALUE37 = ? , VALUE38 = ?, VALUE39 = ? , VALUE40 = ?, VALUE41 = ?, VALUE42 = ?, VALUE43 = ?, VALUE44 = ?, VALUE45 = ?, VALUE46 = ?, VALUE47 = ?, VALUE48 = ?, VALUE49 =? , "
					+ " VALUE50 =? , VALUE51 = ?, VALUE52 = ?, VALUE53 = ?, VALUE54 = ?, VALUE55 = ?, VALUE56 = ?, VALUE57 = ?, VALUE58 = ?, VALUE59 = ?, VALUE60 = ?, VALUE61 = ?, VALUE62 = ?, "
					+ " VALUE63 = ?, VALUE64 = ?, VALUE65 = ?, VALUE66 = ?, VALUE67 = ?, VALUE68 = ?, USEYN=? "
					+ " where COMPNO = ? and SHOPCD = ? and SEQ = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, dto.getTitle()); // 제목
			pstmt.setString(++rowIdx, dto.getShopid()); // 쇼핑몰아이디
			pstmt.setString(++rowIdx, dto.getMemo()); // 메모
			pstmt.setString(++rowIdx, dto.getSelmthdcd()); // 형태구분
			pstmt.setString(++rowIdx, dto.getValue1()); // 필수속성정보
			pstmt.setString(++rowIdx, dto.getValue2()); // 브랜드카테고리선택
			pstmt.setString(++rowIdx, dto.getValue3()); // 소호노출범위여부
			pstmt.setString(++rowIdx, dto.getValue4()); // 소호노출카테고리
			pstmt.setString(++rowIdx, dto.getValue5()); // 소호노출스타일
			pstmt.setString(++rowIdx, dto.getValue6()); // 소호노출샤프
			pstmt.setString(++rowIdx, dto.getValue7()); // 소호노출칼라
			pstmt.setString(++rowIdx, dto.getValue8()); // 판매자닉네임여부
			pstmt.setString(++rowIdx, dto.getValue9()); // 모델명적용여부
			pstmt.setString(++rowIdx, dto.getValue10()); // 모델명
			pstmt.setString(++rowIdx, dto.getValue11()); // 모델명수정여부
			pstmt.setString(++rowIdx, dto.getValue12()); // ESM브랜드
			pstmt.setString(++rowIdx, dto.getProdstatcd()); // 상품상태
			pstmt.setString(++rowIdx, dto.getUsedperiod()); // 중고개월수
			pstmt.setString(++rowIdx, dto.getValue13()); // 쿠폰유효기간
			pstmt.setString(++rowIdx, dto.getMaximumqtytyp()); // 최대구매허용수량여부
			pstmt.setString(++rowIdx, dto.getLimitqty()); // 제한수량
			pstmt.setString(++rowIdx, dto.getLimittime()); // 제한기간
			pstmt.setString(++rowIdx, dto.getOptexhibition()); // 옵션재고전시방법
			pstmt.setString(++rowIdx, dto.getValue14()); // 옵션노출정렬여부
			pstmt.setString(++rowIdx, dto.getInputtypopt()); // 입력형옵션
			pstmt.setString(++rowIdx, dto.getValue15()); // 추가상품전시여부
			pstmt.setString(++rowIdx, dto.getSalesperiod()); // 판매기간
			pstmt.setString(++rowIdx, dto.getPre_sale()); // 예약판매여부
			pstmt.setString(++rowIdx, dto.getValue16()); // 예약판매배송일
			pstmt.setString(++rowIdx, dto.getImage()); // 대표이미지
			pstmt.setString(++rowIdx, dto.getProdinfoarea()); // 상품정보입력영역
			pstmt.setString(++rowIdx, dto.getProdadditional()); // 추가구성입력영역
			pstmt.setString(++rowIdx, dto.getPromotion()); // 광고/홍보입력영역
			pstmt.setString(++rowIdx, dto.getAdultgoods()); // 성인용품여부
			pstmt.setString(++rowIdx, dto.getChildprod()); // 어린이제품
			pstmt.setString(++rowIdx, dto.getHomeholdprod()); // 생활용품
			pstmt.setString(++rowIdx, dto.getElectrical()); // 전기용품
			pstmt.setString(++rowIdx, dto.getChemistry()); // 생활화학/살생물제품
			pstmt.setString(++rowIdx, dto.getEco()); // 친환경
			pstmt.setString(++rowIdx, dto.getMedical()); // 의료기기
			pstmt.setString(++rowIdx, dto.getOrigin()); // 원산지선택여부
			pstmt.setString(++rowIdx, dto.getMultiorigin()); // 복수원산지여부
			pstmt.setString(++rowIdx, dto.getAsdtl()); // A/S정보
			pstmt.setString(++rowIdx, dto.getShippingpolicy()); // 발송정책
			pstmt.setString(++rowIdx, dto.getShippingmethod()); // 배송방법
			pstmt.setString(++rowIdx, dto.getExpress()); // 택배사
			pstmt.setString(++rowIdx, dto.getValue17()); // 방문수령여부
			pstmt.setString(++rowIdx, dto.getValue69()); // 방문수령가격할인금액
			pstmt.setString(++rowIdx, dto.getValue70()); // 방문수령사은품
			pstmt.setString(++rowIdx, dto.getValue18()); // 방문수령주소
			pstmt.setString(++rowIdx, dto.getValue19()); // 퀵서비스업체명
			pstmt.setString(++rowIdx, dto.getValue20()); // 퀵서비스연락처
			pstmt.setString(++rowIdx, dto.getValue21()); // 퀵서비스배송가능지역
			pstmt.setString(++rowIdx, dto.getValue22()); // 일반우편여부
			pstmt.setString(++rowIdx, dto.getValue23()); // 일반우폄금액
			pstmt.setString(++rowIdx, dto.getOutaddr()); // 출하지
			pstmt.setString(++rowIdx, dto.getBundledyn()); // 묶음배송여부
			pstmt.setString(++rowIdx, dto.getBundlemaxnmin()); // 묶음배송시배송비
			pstmt.setString(++rowIdx, dto.getTemplate()); // 묶음배송시템플릿
			pstmt.setString(++rowIdx, dto.getDelvcosttyp()); // 택배비무료여부
			pstmt.setString(++rowIdx, dto.getDelvcost()); // 배송기본배송비
			pstmt.setString(++rowIdx, dto.getDelvfreecost()); // 기본배송비2
			pstmt.setString(++rowIdx, dto.getCostdiscount()); // 배송비할인여부
			pstmt.setString(++rowIdx, dto.getValue24()); // 수량별차등수량1
			pstmt.setString(++rowIdx, dto.getValue25()); // 수량별차등수량2
			pstmt.setString(++rowIdx, dto.getValue26()); // 수량별차등수량3
			pstmt.setString(++rowIdx, dto.getValue27()); // 수량별차등수량4
			pstmt.setString(++rowIdx, dto.getValue28()); // 수량별차등수량5
			pstmt.setString(++rowIdx, dto.getValue29()); // 수량별차등배송비1
			pstmt.setString(++rowIdx, dto.getValue30()); // 수량별차등배송비2
			pstmt.setString(++rowIdx, dto.getValue31()); // 수량별차등배송비3
			pstmt.setString(++rowIdx, dto.getValue32()); // 수량별차등배송비4
			pstmt.setString(++rowIdx, dto.getValue33()); // 수량별차등배송비5
			pstmt.setString(++rowIdx, dto.getPrepayment()); // 착/선불여부
			pstmt.setString(++rowIdx, dto.getRetnexchangeaddr()); // 교환/반품주소
			pstmt.setString(++rowIdx, dto.getRetnexchangecost()); // 교환/반품배송비
			pstmt.setString(++rowIdx, dto.getOrderdelaytime()); // 주문후예상배송기간
			pstmt.setString(++rowIdx, dto.getValue34()); // 건강식품신고기관명
			pstmt.setString(++rowIdx, dto.getValue35()); // 건강식품신고번호
			pstmt.setString(++rowIdx, dto.getValue36()); // 건강식품심의번호
			pstmt.setString(++rowIdx, dto.getValue37()); // 판매자관리코드
			pstmt.setString(++rowIdx, dto.getValue38()); // 판매자직접입력관리코드
			pstmt.setString(++rowIdx, dto.getValue39()); // 판매자카테고리
			pstmt.setString(++rowIdx, dto.getValue40()); // 사은품
			pstmt.setString(++rowIdx, dto.getValue41()); // 포털가격비교사이트등록여부
			pstmt.setString(++rowIdx, dto.getValue42()); // 포털가격비교사이트쿠폰적용여부
			pstmt.setString(++rowIdx, dto.getValue43()); // 사이트부담지원할인
			pstmt.setString(++rowIdx, dto.getValue44()); // 휴대폰가입신청서URL
			pstmt.setString(++rowIdx, dto.getValue45()); // 렌탈설치비
			pstmt.setString(++rowIdx, dto.getValue46()); // 렌탈의무사용기간
			pstmt.setString(++rowIdx, dto.getValue47()); // 렌탈등록비
			pstmt.setString(++rowIdx, dto.getValue48()); // 옵션관리코드여부
			pstmt.setString(++rowIdx, dto.getValue49()); // 기타특이사항
			pstmt.setString(++rowIdx, dto.getValue50()); // 상품명앞추가문구
			pstmt.setString(++rowIdx, dto.getValue51()); // 상품명뒷추가문구
			pstmt.setString(++rowIdx, dto.getValue52()); // 상품설명상단추가문구
			pstmt.setString(++rowIdx, dto.getValue53()); // 상품설명하단추가문구
			pstmt.setString(++rowIdx, dto.getValue54()); // 판매자부담할인여부
			pstmt.setString(++rowIdx, dto.getValue55()); // 판매자부담할인금액
			pstmt.setString(++rowIdx, dto.getValue56()); // 판매자부담할인시작일
			pstmt.setString(++rowIdx, dto.getValue57()); // 판매자부담할인종료일
			pstmt.setString(++rowIdx, dto.getValue58()); // 특별할인여부
			pstmt.setString(++rowIdx, dto.getValue59()); // 특별할인시작일
			pstmt.setString(++rowIdx, dto.getValue60()); // 특별할인종료일
			pstmt.setString(++rowIdx, dto.getValue61()); // 특별할인회원할인금액
			pstmt.setString(++rowIdx, dto.getValue62()); // 특별할인복수구매주문수량
			pstmt.setString(++rowIdx, dto.getValue63()); // 특별할인복수구매할인금액
			pstmt.setString(++rowIdx, dto.getValue64()); // 판매자스마일캐시여부
			pstmt.setString(++rowIdx, dto.getValue65()); // 스마일캐시적립률
			pstmt.setString(++rowIdx, dto.getValue66()); // 나눔쇼핑시작일
			pstmt.setString(++rowIdx, dto.getValue67()); // 나눔쇼핑종료일
			pstmt.setString(++rowIdx, dto.getValue68()); // 나눔쇼핑금액
			pstmt.setString(++rowIdx, dto.getUseyn()); // 사용여부

			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, seq);
			pstmt.executeUpdate();

			System.out.println("[ShopAddrDtlAuctionUpdate]" + pstmt.toString());

			String sql_mst = " update shopaddrmst set TITLE = ? , SELMTHDCD = ? , USEYN = ? , UPDATEDT = ? where compno = ? and shopcd = ? and seq = ? ";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getSelmthdcd());
			pstmt.setString(3, dto.getUseyn());
			pstmt.setString(4, YDMATimeUtil.getCurrentTimeByYDFormat());

			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(6, shopcd);
			pstmt.setString(7, seq);

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public int ShopAddrDtlNaverStoreInsert(String shopcd, String title, String memo, String shopid, String prodstat,
			String minor, String image, String astelno, String asdtl, String pricecomparison, String review,
			String minimumtyp, String minimumqty, String maximumtyp, String maximumval, String maximumqty,
			String generaldelvtyp, String customordtyp, String senddate, String shipmethod, String bundledshiptyp,
			String bundledshipcd, String shiptyp, String delvcost, String conditionalfreetyp, String qtydivisiontyp,
			String delvcost2, String prepayment, String areadelvtyp, String jejucost, String islandcost,
			String retNcngexp, String retcost, String changecost, String outaddr, String retaddr, String prodcd,
			String prodcdinput, String childcert, String kccert, String ecocert, String kcexemption, String salesperiod,
			String salesfrom, String salesto, String optiontyp, String custommade, String summaryinfotyp1,
			String summaryinfoval1, String summaryinfotyp2, String summaryinfoval2, String summaryinfotyp3,
			String summaryinfoval3, String summaryinfotyp4, String summaryinfoval4, String summaryinfotyp5,
			String summaryinfoval5, String useyn, int seq) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "insert into shopaddrnaverstoredtl(SEQ,COMPNO, shopcd,TITLE, MEMO,SHOPID, PRODSTATS, MINORTYP, IMAGE, ASTELNO, ASDTL, PRICECOMPARISON, REVIEW,MINIMUMTYP, MINIMUMQTY,MAXIMUMTYP, "
					+ " MAXIMUMVAL, MAXIMUMQTY, GENERALDELVTYP, CUSTOMORDTYP, SENDDATE, SHIPMETHOD, BUNDLEDSHIPTYP, BUNDLEDSHIPCD,SHIPDELVTYP,DELVCOST, CONDITIONALTYP, QTYDIVISIONTYP,"
					+ " DELVCOST2, PREPAYMENT, AREADELVTYP, JEJUCOST, ISLANDCOST, RETNCNGEXP, RETCOST,CNGCOST,OUTADDR,RETADDR,PRODCDTYP,PRODCDINPUT, CHILDCERT,KCCERT,ECOCERT,KCEXEMPTION,"
					+ " SALESPERIODTYP, SALESFROM, SALESTO, OPTIONTYP, CUSTOMMADE,SUMMARYTYP1,SUMMARYVAL1,SUMMARYTYP2,SUMMARYVAL2,SUMMARYTYP3,SUMMARYVAL3,SUMMARYTYP4,SUMMARYVAL4,SUMMARYTYP5,"
					+ " SUMMARYVAL5 ,USEYN )"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ? , ?, ?, ?, ?, ?, ? ,? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,"
					+ " ?, ?, ?, ? )";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setInt(++rowIdx, seq);
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, title);
			pstmt.setString(++rowIdx, memo);
			pstmt.setString(++rowIdx, shopid);
			pstmt.setString(++rowIdx, prodstat);
			pstmt.setString(++rowIdx, minor);
			pstmt.setString(++rowIdx, image);
			pstmt.setString(++rowIdx, astelno);
			pstmt.setString(++rowIdx, asdtl);
			pstmt.setString(++rowIdx, pricecomparison);
			pstmt.setString(++rowIdx, review);
			pstmt.setString(++rowIdx, minimumtyp);
			pstmt.setString(++rowIdx, minimumqty);
			pstmt.setString(++rowIdx, maximumtyp);
			pstmt.setString(++rowIdx, maximumval);
			pstmt.setString(++rowIdx, maximumqty);
			pstmt.setString(++rowIdx, generaldelvtyp);
			pstmt.setString(++rowIdx, customordtyp);
			pstmt.setString(++rowIdx, senddate);
			pstmt.setString(++rowIdx, shipmethod);
			pstmt.setString(++rowIdx, bundledshiptyp);
			pstmt.setString(++rowIdx, bundledshipcd);
			pstmt.setString(++rowIdx, shiptyp);
			pstmt.setString(++rowIdx, delvcost);
			pstmt.setString(++rowIdx, conditionalfreetyp);
			pstmt.setString(++rowIdx, qtydivisiontyp);
			pstmt.setString(++rowIdx, delvcost2);
			pstmt.setString(++rowIdx, prepayment);
			pstmt.setString(++rowIdx, areadelvtyp);
			pstmt.setString(++rowIdx, jejucost);
			pstmt.setString(++rowIdx, islandcost);
			pstmt.setString(++rowIdx, retNcngexp);
			pstmt.setString(++rowIdx, retcost);
			pstmt.setString(++rowIdx, changecost);
			pstmt.setString(++rowIdx, outaddr);
			pstmt.setString(++rowIdx, retaddr);
			pstmt.setString(++rowIdx, prodcd);
			pstmt.setString(++rowIdx, prodcdinput);
			pstmt.setString(++rowIdx, childcert);
			pstmt.setString(++rowIdx, kccert);
			pstmt.setString(++rowIdx, ecocert);
			pstmt.setString(++rowIdx, kcexemption);
			pstmt.setString(++rowIdx, salesperiod);
			pstmt.setString(++rowIdx, salesfrom);
			pstmt.setString(++rowIdx, salesto);
			pstmt.setString(++rowIdx, optiontyp);
			pstmt.setString(++rowIdx, custommade);
			pstmt.setString(++rowIdx, summaryinfotyp1);
			pstmt.setString(++rowIdx, summaryinfoval1);
			pstmt.setString(++rowIdx, summaryinfotyp2);
			pstmt.setString(++rowIdx, summaryinfoval2);
			pstmt.setString(++rowIdx, summaryinfotyp3);
			pstmt.setString(++rowIdx, summaryinfoval3);
			pstmt.setString(++rowIdx, summaryinfotyp4);
			pstmt.setString(++rowIdx, summaryinfoval4);
			pstmt.setString(++rowIdx, summaryinfotyp5);
			pstmt.setString(++rowIdx, summaryinfoval5);
			pstmt.setString(++rowIdx, useyn);

			pstmt.executeUpdate();

			System.out.println("[ShopAddrDtlNaverStoreInsert]" + pstmt.toString());

			String sql_mst = " insert into shopaddrmst (compno, SHOPCD, SEQ,  TITLE, SELMTHDCD, USEYN, INSERTDT) "
					+ "  VALUES (?, ?,?,?,?,?,?)";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setInt(3, seq);
			pstmt.setString(4, title);
			pstmt.setString(5, "");
			pstmt.setString(6, useyn);
			pstmt.setString(7, YDMATimeUtil.getCurrentTimeByYDFormat());

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public List<String> shopNaverStoreDetailjoinList(List<String> list) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> listjoin = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT IFNULL(dtl.shopcd,''),IFNULL(dtl.seq,''),IFNULL(dtl.title,''), ifnull(dtl.SHOPID,''), IFNULL(dtl.MEMO,''),IFNULL(dtl.PRODSTATS,''),IFNULL(dtl.MINORTYP,''),"
					+ " IFNULL(dtl.IMAGE,''), IFNULL(dtl.ASTELNO,''),IFNULL(dtl.ASDTL,''),IFNULL(dtl.PRICECOMPARISON,''),IFNULL(dtl.REVIEW,''),IFNULL(dtl.MINIMUMTYP,''),IFNULL(dtl.MINIMUMQTY,'0'),"
					+ " IFNULL(dtl.MAXIMUMTYP,''),IFNULL(dtl.MAXIMUMVAL,''), IFNULL(dtl.MAXIMUMQTY,'0'),IFNULL(dtl.GENERALDELVTYP,''),IFNULL(dtl.CUSTOMORDTYP,''),IFNULL(dtl.SENDDATE,''),"
					+ " IFNULL(dtl.SHIPMETHOD,''), IFNULL(dtl.BUNDLEDSHIPTYP,''),IFNULL(dtl.BUNDLEDSHIPCD,''),IFNULL(dtl.SHIPDELVTYP,''), IFNULL(dtl.DELVCOST,'0'),IFNULL(dtl.CONDITIONALTYP,''), "
					+ " IFNULL(dtl.QTYDIVISIONTYP,''),IFNULL(dtl.DELVCOST2,''), IFNULL(dtl.PREPAYMENT,''),IFNULL(dtl.AREADELVTYP,''), ifnull(dtl.JEJUCOST,'0'), ifnull(dtl.ISLANDCOST,'0'), "
					+ " ifnull(dtl.RETNCNGEXP,''), ifnull(dtl.RETCOST,'0'), ifnull(dtl.CNGCOST,'0'), ifnull(dtl.OUTADDR,''),ifnull(dtl.RETADDR,''),ifnull(dtl.PRODCDTYP,''),ifnull(dtl.PRODCDINPUT,''),"
					+ " ifnull(dtl.CHILDCERT,''), ifnull(dtl.KCCERT,''), ifnull(dtl.ECOCERT,''), ifnull(dtl.KCEXEMPTION,''),ifnull(dtl.SALESPERIODTYP,''),ifnull(dtl.SALESFROM,''),ifnull(dtl.SALESTO,''),"
					+ " ifnull(dtl.OPTIONTYP,''),ifnull(dtl.CUSTOMMADE,''),ifnull(dtl.SUMMARYTYP1,''),ifnull(dtl.SUMMARYVAL1,''),ifnull(dtl.SUMMARYTYP2,''),ifnull(dtl.SUMMARYVAL2,''),ifnull(dtl.SUMMARYTYP3,''),"
					+ " ifnull(dtl.SUMMARYVAL3,''),ifnull(dtl.SUMMARYTYP4,''),ifnull(dtl.SUMMARYVAL4,''),ifnull(dtl.SUMMARYTYP5,''),ifnull(dtl.SUMMARYVAL5,''),ifnull(dtl.USEYN,'') "
					+ " FROM SHOPADDRMST addr JOIN SHOPMST MST ON MST.SHOPCD=ADDR.SHOPCD and MST.compno = ADDR.compno JOIN shopaddrnaverstoredtl dtl ON ADDR.seq = dtl.seq and ADDR.compno = dtl.compno join shopinfo a on "
					+ " mst.shopcd = a.shopcd WHERE addr.seq = ? AND a.shopnm = ? and mst.compno = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, list.get(0));
			pstmt.setString(2, list.get(1));
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[shopAuctionDetailjoinList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return listjoin;
	}

	public int ShopAddrDtlNaverStoreUpdate(String shopcd, String title, String memo, String shopid, String prodstat,
			String minor, String image, String astelno, String asdtl, String pricecomparison, String review,
			String minimumtyp, String minimumqty, String maximumtyp, String maximumval, String maximumqty,
			String generaldelvtyp, String customordtyp, String senddate, String shipmethod, String bundledshiptyp,
			String bundledshipcd, String shiptyp, String delvcost, String conditionalfreetyp, String qtydivisiontyp,
			String delvcost2, String prepayment, String areadelvtyp, String jejucost, String islandcost,
			String retNcngexp, String retcost, String changecost, String outaddr, String retaddr, String prodcd,
			String prodcdinput, String childcert, String kccert, String ecocert, String kcexemption, String salesperiod,
			String salesfrom, String salesto, String optiontyp, String custommade, String summaryinfotyp1,
			String summaryinfoval1, String summaryinfotyp2, String summaryinfoval2, String summaryinfotyp3,
			String summaryinfoval3, String summaryinfotyp4, String summaryinfoval4, String summaryinfotyp5,
			String summaryinfoval5, String useyn, String seq) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update shopaddrnaverstoredtl set TITLE = ?, memo = ?, SHOPID = ?, PRODSTATS = ?, MINORTYP = ?, IMAGE = ?, ASTELNO = ?, ASDTL = ?, PRICECOMPARISON = ?, REVIEW = ?, "
					+ " MINIMUMTYP = ?, MINIMUMQTY = ?, MAXIMUMTYP = ?, MAXIMUMVAL = ?, MAXIMUMQTY = ?, GENERALDELVTYP = ?, CUSTOMORDTYP = ?, SENDDATE = ?, SHIPMETHOD = ?, BUNDLEDSHIPTYP = ?, "
					+ " BUNDLEDSHIPCD = ?, SHIPDELVTYP = ?,DELVCOST = ?,CONDITIONALTYP = ?,QTYDIVISIONTYP = ?,DELVCOST2 = ?,PREPAYMENT = ?,AREADELVTYP = ?,JEJUCOST = ?,ISLANDCOST = ? , "
					+ " RETNCNGEXP = ?, RETCOST = ?, CNGCOST = ?, OUTADDR = ?, RETADDR = ?, PRODCDTYP = ?, PRODCDINPUT = ?, CHILDCERT = ?,KCCERT = ?,ECOCERT = ?,KCEXEMPTION = ?, SALESPERIODTYP = ?, "
					+ " SALESFROM = ?, SALESTO = ?, OPTIONTYP = ?, CUSTOMMADE = ?, SUMMARYTYP1 = ?, SUMMARYVAL1 = ?, SUMMARYTYP2 = ?,SUMMARYVAL2=?,SUMMARYTYP3=?,SUMMARYVAL3 = ?, "
					+ " SUMMARYTYP4 = ?, SUMMARYVAL4 = ?, SUMMARYTYP5 = ?, SUMMARYVAL5 = ?, USEYN = ? "
					+ " where COMPNO = ? and SHOPCD = ? and SEQ = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, title);
			pstmt.setString(++rowIdx, memo);
			pstmt.setString(++rowIdx, shopid);
			pstmt.setString(++rowIdx, prodstat);
			pstmt.setString(++rowIdx, minor);
			pstmt.setString(++rowIdx, image);
			pstmt.setString(++rowIdx, astelno);
			pstmt.setString(++rowIdx, asdtl);
			pstmt.setString(++rowIdx, pricecomparison);
			pstmt.setString(++rowIdx, review);
			pstmt.setString(++rowIdx, minimumtyp);
			pstmt.setString(++rowIdx, minimumqty);
			pstmt.setString(++rowIdx, maximumtyp);
			pstmt.setString(++rowIdx, maximumval);
			pstmt.setString(++rowIdx, maximumqty);
			pstmt.setString(++rowIdx, generaldelvtyp);
			pstmt.setString(++rowIdx, customordtyp);
			pstmt.setString(++rowIdx, senddate);
			pstmt.setString(++rowIdx, shipmethod);
			pstmt.setString(++rowIdx, bundledshiptyp);
			pstmt.setString(++rowIdx, bundledshipcd);
			pstmt.setString(++rowIdx, shiptyp);
			pstmt.setString(++rowIdx, delvcost);
			pstmt.setString(++rowIdx, conditionalfreetyp);
			pstmt.setString(++rowIdx, qtydivisiontyp);
			pstmt.setString(++rowIdx, delvcost2);
			pstmt.setString(++rowIdx, prepayment);
			pstmt.setString(++rowIdx, areadelvtyp);
			pstmt.setString(++rowIdx, jejucost);
			pstmt.setString(++rowIdx, islandcost);
			pstmt.setString(++rowIdx, retNcngexp);
			pstmt.setString(++rowIdx, retcost);
			pstmt.setString(++rowIdx, changecost);
			pstmt.setString(++rowIdx, outaddr);
			pstmt.setString(++rowIdx, retaddr);
			pstmt.setString(++rowIdx, prodcd);
			pstmt.setString(++rowIdx, prodcdinput);
			pstmt.setString(++rowIdx, childcert);
			pstmt.setString(++rowIdx, kccert);
			pstmt.setString(++rowIdx, ecocert);
			pstmt.setString(++rowIdx, kcexemption);
			pstmt.setString(++rowIdx, salesperiod);
			pstmt.setString(++rowIdx, salesfrom);
			pstmt.setString(++rowIdx, salesto);
			pstmt.setString(++rowIdx, optiontyp);
			pstmt.setString(++rowIdx, custommade);
			pstmt.setString(++rowIdx, summaryinfotyp1);
			pstmt.setString(++rowIdx, summaryinfoval1);
			pstmt.setString(++rowIdx, summaryinfotyp2);
			pstmt.setString(++rowIdx, summaryinfoval2);
			pstmt.setString(++rowIdx, summaryinfotyp3);
			pstmt.setString(++rowIdx, summaryinfoval3);
			pstmt.setString(++rowIdx, summaryinfotyp4);
			pstmt.setString(++rowIdx, summaryinfoval4);
			pstmt.setString(++rowIdx, summaryinfotyp5);
			pstmt.setString(++rowIdx, summaryinfoval5);
			pstmt.setString(++rowIdx, useyn);

			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, seq);
			pstmt.executeUpdate();

			System.out.println("[ShopAddrDtlNaverStoreUpdate]" + pstmt.toString());

			String sql_mst = " update shopaddrmst set TITLE = ? , SELMTHDCD = ? , USEYN = ? , UPDATEDT = ? where compno = ? and shopcd = ? and seq = ? ";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, title);
			pstmt.setString(2, "");
			pstmt.setString(3, useyn);
			pstmt.setString(4, YDMATimeUtil.getCurrentTimeByYDFormat());

			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(6, shopcd);
			pstmt.setString(7, seq);

			System.out.println("[ShopAddrDtlNaverStoreUpdate]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public int ShopAddrDtlTmonInsert(String shopcd, String title, String memo, String shopid, String selmthdcd,
			String shippolicy, String promotiontitle, String saleenddate, String maximumqty, String adultcert,
			String express, String expprodtyp, String prodstats, String parallelimport, String certitem,
			String certimgpath, String regdiffcosttyp, String regdiffcontent, String remorserefundtyp,
			String remorserefundcontent, String addphrasestyp, String addphrasescontent, String repimg, String useyn,
			int seq) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "insert into shopaddrtmondtl(SEQ,COMPNO, shopcd,TITLE, MEMO,SHOPID, SELMTHDCD, SHIPPOLICY, PRODPROMTITLE, SALEENDDATE, MAXIMUMCNT, ADULTTYP, EXPRESS,SHIPPRODTYP, PRODSTATS, "
					+ " PARALLELIMPORT, REPORTTARGETTYP, REPORTTARGETIMG, REGDIFFCOSTTYP, REGDIFFCONTENT, REMORSETYP, REMORSECONTENT, PHRASESTYP, PHRASESCONTENT,REPIMG ,USEYN )"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setInt(++rowIdx, seq);
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, title);
			pstmt.setString(++rowIdx, memo);
			pstmt.setString(++rowIdx, shopid);
			pstmt.setString(++rowIdx, selmthdcd);
			pstmt.setString(++rowIdx, shippolicy);
			pstmt.setString(++rowIdx, promotiontitle);
			pstmt.setString(++rowIdx, saleenddate);
			pstmt.setString(++rowIdx, maximumqty);
			pstmt.setString(++rowIdx, adultcert);
			pstmt.setString(++rowIdx, express);
			pstmt.setString(++rowIdx, expprodtyp);
			pstmt.setString(++rowIdx, prodstats);
			pstmt.setString(++rowIdx, parallelimport);
			pstmt.setString(++rowIdx, certitem);
			pstmt.setString(++rowIdx, certimgpath);
			pstmt.setString(++rowIdx, regdiffcosttyp);
			pstmt.setString(++rowIdx, regdiffcontent);
			pstmt.setString(++rowIdx, remorserefundtyp);
			pstmt.setString(++rowIdx, remorserefundcontent);
			pstmt.setString(++rowIdx, addphrasestyp);
			pstmt.setString(++rowIdx, addphrasescontent);
			pstmt.setString(++rowIdx, repimg);
			pstmt.setString(++rowIdx, useyn);

			pstmt.executeUpdate();

			System.out.println("[ShopAddrDtlTmonInsert]" + pstmt.toString());

			String sql_mst = " insert into shopaddrmst (compno, SHOPCD, SEQ,  TITLE, SELMTHDCD, USEYN, INSERTDT) "
					+ "  VALUES (?, ?,?,?,?,?,?)";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setInt(3, seq);
			pstmt.setString(4, title);
			pstmt.setString(5, selmthdcd);
			pstmt.setString(6, useyn);
			pstmt.setString(7, YDMATimeUtil.getCurrentTimeByYDFormat());

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public List<String> shopTmonDetailjoinList(List<String> list) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> listjoin = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT IFNULL(dtl.shopcd,''),IFNULL(dtl.seq,''),IFNULL(dtl.title,''), ifnull(dtl.SHOPID,''), IFNULL(dtl.MEMO,''),IFNULL(dtl.SELMTHDCD,''),IFNULL(dtl.SHIPPOLICY,''),"
					+ " IFNULL(dtl.PRODPROMTITLE,''), IFNULL(dtl.SALEENDDATE,''),IFNULL(dtl.MAXIMUMCNT,'0'),IFNULL(dtl.ADULTTYP,''),IFNULL(dtl.EXPRESS,''),IFNULL(dtl.SHIPPRODTYP,''),IFNULL(dtl.PRODSTATS,''),"
					+ " IFNULL(dtl.PARALLELIMPORT,''),IFNULL(dtl.REPORTTARGETTYP,''), IFNULL(dtl.REPORTTARGETIMG,''),IFNULL(dtl.REGDIFFCOSTTYP,''),IFNULL(dtl.REGDIFFCONTENT,''),IFNULL(dtl.REMORSETYP,''),"
					+ " IFNULL(dtl.REMORSECONTENT,''), IFNULL(dtl.PHRASESTYP,''),IFNULL(dtl.PHRASESCONTENT,''),IFNULL(dtl.REPIMG,''), IFNULL(dtl.USEYN,'') "
					+ " FROM SHOPADDRMST addr JOIN SHOPMST MST ON MST.SHOPCD=ADDR.SHOPCD and MST.compno = ADDR.compno JOIN shopaddrtmondtl dtl ON ADDR.seq = dtl.seq and ADDR.compno = dtl.compno join shopinfo a on "
					+ " mst.shopcd = a.shopcd WHERE addr.seq = ? AND a.shopnm = ? and mst.compno = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, list.get(0));
			pstmt.setString(2, list.get(1));
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[shopTmonDetailjoinList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));
				listjoin.add(rs.getString(++columnIndex));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return listjoin;
	}

	public int ShopAddrDtlTmonUpdate(String shopcd, String title, String memo, String shopid, String selmthdcd,
			String shippolicy, String promotiontitle, String saleenddate, String maximumqty, String adultcert,
			String express, String expprodtyp, String prodstats, String parallelimport, String certitem,
			String certimgpath, String regdiffcosttyp, String regdiffcontent, String remorserefundtyp,
			String remorserefundcontent, String addphrasestyp, String addphrasescontent, String repimg, String useyn,
			String seq) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update shopaddrtmondtl set TITLE = ?, memo = ?, SHOPID = ?, SELMTHDCD = ?, SHIPPOLICY = ?, PRODPROMTITLE = ?, SALEENDDATE = ?, MAXIMUMCNT = ?, ADULTTYP = ?, EXPRESS = ?, "
					+ " SHIPPRODTYP = ?, PRODSTATS = ?, PARALLELIMPORT = ?, REPORTTARGETTYP = ?, REPORTTARGETIMG = ?, REGDIFFCOSTTYP = ?, REGDIFFCONTENT = ?, REMORSETYP = ?, REMORSECONTENT = ?, "
					+ " PHRASESTYP = ?, PHRASESCONTENT = ?, REPIMG = ?,USEYN = ? "
					+ " where COMPNO = ? and SHOPCD = ? and SEQ = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, title);
			pstmt.setString(++rowIdx, memo);
			pstmt.setString(++rowIdx, shopid);
			pstmt.setString(++rowIdx, selmthdcd);
			pstmt.setString(++rowIdx, shippolicy);
			pstmt.setString(++rowIdx, promotiontitle);
			pstmt.setString(++rowIdx, saleenddate);
			pstmt.setString(++rowIdx, maximumqty);
			pstmt.setString(++rowIdx, adultcert);
			pstmt.setString(++rowIdx, express);
			pstmt.setString(++rowIdx, expprodtyp);
			pstmt.setString(++rowIdx, prodstats);
			pstmt.setString(++rowIdx, parallelimport);
			pstmt.setString(++rowIdx, certitem);
			pstmt.setString(++rowIdx, certimgpath);
			pstmt.setString(++rowIdx, regdiffcosttyp);
			pstmt.setString(++rowIdx, regdiffcontent);
			pstmt.setString(++rowIdx, remorserefundtyp);
			pstmt.setString(++rowIdx, remorserefundcontent);
			pstmt.setString(++rowIdx, addphrasestyp);
			pstmt.setString(++rowIdx, addphrasescontent);
			pstmt.setString(++rowIdx, repimg);
			pstmt.setString(++rowIdx, useyn);

			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, seq);
			pstmt.executeUpdate();

			System.out.println("[ShopAddrDtlTmonUpdate]" + pstmt.toString());

			String sql_mst = " update shopaddrmst set TITLE = ? , SELMTHDCD = ? , USEYN = ? , UPDATEDT = ? where compno = ? and shopcd = ? and seq = ? ";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, title);
			pstmt.setString(2, selmthdcd);
			pstmt.setString(3, useyn);
			pstmt.setString(4, YDMATimeUtil.getCurrentTimeByYDFormat());

			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(6, shopcd);
			pstmt.setString(7, seq);

			System.out.println("[ShopAddrDtlUpdate]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public int ShopAddrDtlGmarketInsert(String shopcd, ShopProductGmarketAdditionDto dto, int seq) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "insert into shopaddrgmarketdtl(SEQ,COMPNO, shopcd,TITLE, SHOPID,memo, SELMTHDCD, VALUE1,VALUE2,VALUE71,VALUE4,VALUE5,VALUE6,VALUE7,VALUE9,VALUE10,VALUE11,VALUE12, "
					+ " PRODSTATCD, USEDPERIOD, MAXIMUMQTYTYP, LIMITQTY, LIMITTIME, OPTEXHIBITION, VALUE14, INPUTTYPOPT, VALUE15, SALESPERIOD, IMAGE, PRODINFOAREA, "
					+ " PRODADDITIONAL, PROMOTION, VALUE72,VALUE73,ADULTGOODS, CHILDPROD, HOMEHOLDPROD, ELECTRICAL,CHEMISTRY,ECO, MEDICAL, ORIGIN, MULTIORIGIN, ASDTL, SHIPPINGPOLICY, SHIPPINGMETHOD, EXPRESS, "
					+ " VALUE17, VALUE69, VALUE70, VALUE18, VALUE19, VALUE20, VALUE21, VALUE22, OUTADDR, BUNDLEDYN,BUNDLEMAXNMIN,TEMPLATE,DELVCOSTTYP,DELVCOST,DELVFREECOST, COSTDISCOUNT,VALUE24, "
					+ " VALUE25, VALUE26, VALUE27, VALUE28, VALUE29, VALUE30, VALUE31, VALUE32, VALUE33, PREPAYMENT, RETNEXCHANGEADDR,RETNEXCHANGECOST, ORDERDELAYTIME, "
					+ " VALUE37, VALUE38, VALUE39, VALUE40, VALUE41, VALUE42, VALUE74, VALUE43, VALUE44, VALUE45, VALUE46, VALUE47, VALUE48, VALUE49, VALUE50, VALUE51, VALUE52, VALUE53, VALUE54, VALUE55, "
					+ " VALUE56, VALUE57, VALUE58, VALUE59, VALUE60, VALUE61, VALUE62, VALUE63, VALUE64, VALUE75, VALUE65, VALUE66, VALUE67, VALUE68, VALUE76, VALUE77, VALUE78, VALUE79, VALUE80, VALUE81,"
					+ " VALUE82, VALUE83, VALUE84, VALUE85, VALUE86, VALUE87, VALUE88, VALUE89, VALUE90, VALUE91, USEYN) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ? , ?, ?, ?, ?, ?, ? ,? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "// 56
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,"// 116
					+ " ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? )";// 129
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setInt(++rowIdx, seq); // 일련번호
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno()); // 회사코드
			pstmt.setString(++rowIdx, shopcd); // 쇼핑몰코드
			pstmt.setString(++rowIdx, dto.getTitle()); // 제목
			pstmt.setString(++rowIdx, dto.getShopid()); // 쇼핑몰아이디
			pstmt.setString(++rowIdx, dto.getMemo()); // 메모
			pstmt.setString(++rowIdx, dto.getSelmthdcd()); // 형태구분
			pstmt.setString(++rowIdx, dto.getValue1()); // 필수속성정보
			pstmt.setString(++rowIdx, dto.getValue2()); // 브랜드ON카테고리선택
			pstmt.setString(++rowIdx, dto.getValue71()); // BIZON카테고리선택
			pstmt.setString(++rowIdx, dto.getValue4()); // 소호노출카테고리
			pstmt.setString(++rowIdx, dto.getValue5()); // 소호노출스타일
			pstmt.setString(++rowIdx, dto.getValue6()); // 소호노출샤프
			pstmt.setString(++rowIdx, dto.getValue7()); // 소호노출칼라
			pstmt.setString(++rowIdx, dto.getValue9()); // 모델명적용여부
			pstmt.setString(++rowIdx, dto.getValue10()); // 모델명
			pstmt.setString(++rowIdx, dto.getValue11()); // 모델명수정여부
			pstmt.setString(++rowIdx, dto.getValue12()); // ESM브랜드
			pstmt.setString(++rowIdx, dto.getProdstatcd()); // 상품상태
			pstmt.setString(++rowIdx, dto.getUsedperiod()); // 중고개월수
			pstmt.setString(++rowIdx, dto.getMaximumqtytyp()); // 최대구매허용수량여부
			pstmt.setString(++rowIdx, dto.getLimitqty()); // 제한수량
			pstmt.setString(++rowIdx, dto.getLimittime()); // 제한기간
			pstmt.setString(++rowIdx, dto.getOptexhibition()); // 옵션재고전시방법
			pstmt.setString(++rowIdx, dto.getValue14()); // 옵션노출정렬여부
			pstmt.setString(++rowIdx, dto.getInputtypopt()); // 입력형옵션
			pstmt.setString(++rowIdx, dto.getValue15()); // 추가상품전시여부
			pstmt.setString(++rowIdx, dto.getSalesperiod()); // 판매기간
			pstmt.setString(++rowIdx, dto.getImage()); // 대표이미지
			pstmt.setString(++rowIdx, dto.getProdinfoarea()); // 상품정보입력영역
			pstmt.setString(++rowIdx, dto.getProdadditional()); // 추가구성입력영역
			pstmt.setString(++rowIdx, dto.getPromotion()); // 광고/홍보입력영역
			pstmt.setString(++rowIdx, dto.getValue72()); // 상품정보입력영역중문
			pstmt.setString(++rowIdx, dto.getValue73()); // 상품정보입력영역영문
			pstmt.setString(++rowIdx, dto.getAdultgoods()); // 성인용품여부
			pstmt.setString(++rowIdx, dto.getChildprod()); // 어린이제품
			pstmt.setString(++rowIdx, dto.getHomeholdprod()); // 생활용품
			pstmt.setString(++rowIdx, dto.getElectrical()); // 전기용품
			pstmt.setString(++rowIdx, dto.getChemistry()); // 생활화학/살생물제품
			pstmt.setString(++rowIdx, dto.getEco()); // 친환경
			pstmt.setString(++rowIdx, dto.getMedical()); // 의료기기
			pstmt.setString(++rowIdx, dto.getOrigin()); // 원산지선택여부
			pstmt.setString(++rowIdx, dto.getMultiorigin()); // 복수원산지여부
			pstmt.setString(++rowIdx, dto.getAsdtl()); // A/S정보
			pstmt.setString(++rowIdx, dto.getShippingpolicy()); // 발송정책
			pstmt.setString(++rowIdx, dto.getShippingmethod()); // 배송방법
			pstmt.setString(++rowIdx, dto.getExpress()); // 택배사
			pstmt.setString(++rowIdx, dto.getValue17()); // 방문수령여부
			pstmt.setString(++rowIdx, dto.getValue69()); // 방문수령가격할인금액
			pstmt.setString(++rowIdx, dto.getValue70()); // 방문수령사은품
			pstmt.setString(++rowIdx, dto.getValue18()); // 방문수령주소
			pstmt.setString(++rowIdx, dto.getValue19()); // 퀵서비스업체명
			pstmt.setString(++rowIdx, dto.getValue20()); // 퀵서비스연락처
			pstmt.setString(++rowIdx, dto.getValue21()); // 퀵서비스배송가능지역
			pstmt.setString(++rowIdx, dto.getValue22()); // 오늘출발여부
			pstmt.setString(++rowIdx, dto.getOutaddr()); // 출하지
			pstmt.setString(++rowIdx, dto.getBundledyn()); // 묶음배송여부
			pstmt.setString(++rowIdx, dto.getBundlemaxnmin()); // 묶음배송시배송비
			pstmt.setString(++rowIdx, dto.getTemplate()); // 묶음배송시템플릿
			pstmt.setString(++rowIdx, dto.getDelvcosttyp()); // 택배비무료여부
			pstmt.setString(++rowIdx, dto.getDelvcost()); // 배송기본배송비
			pstmt.setString(++rowIdx, dto.getDelvfreecost()); // 기본배송비2
			pstmt.setString(++rowIdx, dto.getCostdiscount()); // 배송비할인여부
			pstmt.setString(++rowIdx, dto.getValue24()); // 수량별차등수량1
			pstmt.setString(++rowIdx, dto.getValue25()); // 수량별차등수량2
			pstmt.setString(++rowIdx, dto.getValue26()); // 수량별차등수량3
			pstmt.setString(++rowIdx, dto.getValue27()); // 수량별차등수량4
			pstmt.setString(++rowIdx, dto.getValue28()); // 수량별차등수량5
			pstmt.setString(++rowIdx, dto.getValue29()); // 수량별차등배송비1
			pstmt.setString(++rowIdx, dto.getValue30()); // 수량별차등배송비2
			pstmt.setString(++rowIdx, dto.getValue31()); // 수량별차등배송비3
			pstmt.setString(++rowIdx, dto.getValue32()); // 수량별차등배송비4
			pstmt.setString(++rowIdx, dto.getValue33()); // 수량별차등배송비5
			pstmt.setString(++rowIdx, dto.getPrepayment()); // 착/선불여부
			pstmt.setString(++rowIdx, dto.getRetnexchangeaddr()); // 교환/반품주소
			pstmt.setString(++rowIdx, dto.getRetnexchangecost()); // 교환/반품배송비
			pstmt.setString(++rowIdx, dto.getOrderdelaytime()); // 주문후예상배송기간
			pstmt.setString(++rowIdx, dto.getValue37()); // 판매자관리코드
			pstmt.setString(++rowIdx, dto.getValue38()); // 판매자직접입력관리코드
			pstmt.setString(++rowIdx, dto.getValue39()); // 판매자카테고리
			pstmt.setString(++rowIdx, dto.getValue40()); // 사은품
			pstmt.setString(++rowIdx, dto.getValue41()); // 포털가격비교사이트등록여부
			pstmt.setString(++rowIdx, dto.getValue42()); // 포털가격비교사이트쿠폰적용여부
			pstmt.setString(++rowIdx, dto.getValue74()); // 해외판매여부
			pstmt.setString(++rowIdx, dto.getValue43()); // 사이트부담지원할인
			pstmt.setString(++rowIdx, dto.getValue44()); // 휴대폰가입신청서URL
			pstmt.setString(++rowIdx, dto.getValue45()); // 렌탈설치비
			pstmt.setString(++rowIdx, dto.getValue46()); // 렌탈의무사용기간
			pstmt.setString(++rowIdx, dto.getValue47()); // 렌탈등록비
			pstmt.setString(++rowIdx, dto.getValue48()); // 옵션관리코드여부
			pstmt.setString(++rowIdx, dto.getValue49()); // 기타특이사항
			pstmt.setString(++rowIdx, dto.getValue50()); // 상품명앞추가문구
			pstmt.setString(++rowIdx, dto.getValue51()); // 상품명뒷추가문구
			pstmt.setString(++rowIdx, dto.getValue52()); // 상품설명상단추가문구
			pstmt.setString(++rowIdx, dto.getValue53()); // 상품설명하단추가문구
			pstmt.setString(++rowIdx, dto.getValue54()); // 판매자부담할인여부
			pstmt.setString(++rowIdx, dto.getValue55()); // 판매자부담할인금액
			pstmt.setString(++rowIdx, dto.getValue56()); // 판매자부담할인시작일
			pstmt.setString(++rowIdx, dto.getValue57()); // 판매자부담할인종료일
			pstmt.setString(++rowIdx, dto.getValue58()); // 복수구매여부
			pstmt.setString(++rowIdx, dto.getValue59()); // 복수구매시작일
			pstmt.setString(++rowIdx, dto.getValue60()); // 복수구매종료일
			pstmt.setString(++rowIdx, dto.getValue61()); // 구매수량금액
			pstmt.setString(++rowIdx, dto.getValue62()); // 구매수량할인율
			pstmt.setString(++rowIdx, dto.getValue63()); // 구매수량상품별금액
			pstmt.setString(++rowIdx, dto.getValue64()); // 구매수량상품별할인율
			pstmt.setString(++rowIdx, dto.getValue75()); // 구매수량별무료갯수
			pstmt.setString(++rowIdx, dto.getValue65()); // 스마일캐시적립률
			pstmt.setString(++rowIdx, dto.getValue66()); // 후원쇼핑시작일
			pstmt.setString(++rowIdx, dto.getValue67()); // 후원쇼핑종료일
			pstmt.setString(++rowIdx, dto.getValue68()); // 후원쇼핑금액
			pstmt.setString(++rowIdx, dto.getValue76()); // 후원쇼핑누적적립한도액
			pstmt.setString(++rowIdx, dto.getValue77()); // 후원분야
			pstmt.setString(++rowIdx, dto.getValue78()); // 포커스등록여부
			pstmt.setString(++rowIdx, dto.getValue79()); // 포커스기간연장등록여부
			pstmt.setString(++rowIdx, dto.getValue80()); // 포커스기간연장일자
			pstmt.setString(++rowIdx, dto.getValue81()); // 포커스자동연장여부
			pstmt.setString(++rowIdx, dto.getValue82()); // 포커스무제한시종료일
			pstmt.setString(++rowIdx, dto.getValue83()); // 포커스기간시종료일
			pstmt.setString(++rowIdx, dto.getValue84()); // 포커스기간시종료일2
			pstmt.setString(++rowIdx, dto.getValue85()); // 포커스플러스등록여부
			pstmt.setString(++rowIdx, dto.getValue86()); // 포커스플러스기간연장등록여부
			pstmt.setString(++rowIdx, dto.getValue87()); // 포커스플러스기간연장일자
			pstmt.setString(++rowIdx, dto.getValue88()); // 포커스플러스자동연장여부
			pstmt.setString(++rowIdx, dto.getValue89()); // 포커스플러스무제한시종료일
			pstmt.setString(++rowIdx, dto.getValue90()); // 포커스플러스기간시종료일
			pstmt.setString(++rowIdx, dto.getValue91()); // 포커스플러스기간시종료일2
			pstmt.setString(++rowIdx, dto.getUseyn()); // 사용여부

			pstmt.executeUpdate();

			System.out.println("[insertdtl-ShopAddrDtlInsert]" + pstmt.toString());

			String sql_mst = " insert into shopaddrmst (compno, SHOPCD, SEQ,  TITLE, SELMTHDCD, USEYN, INSERTDT) "
					+ "  VALUES (?, ?,?,?,?,?,?)";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setInt(3, seq);
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getSelmthdcd());
			pstmt.setString(6, dto.getUseyn());
			pstmt.setString(7, YDMATimeUtil.getCurrentTimeByYDFormat());

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public ShopProductGmarketAdditionDto shopGmarketDetailjoinList(List<String> list) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShopProductGmarketAdditionDto dto = new ShopProductGmarketAdditionDto();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT ifnull(dtl.seq,'0'), IFNULL(dtl.compno,'0'),IFNULL(dtl.shopcd,''),IFNULL(dtl.title,''), ifnull(dtl.SHOPID,''), IFNULL(dtl.MEMO,''),IFNULL(dtl.SELMTHDCD,''),IFNULL(dtl.VALUE1,''),"
					+ " IFNULL(dtl.VALUE2,''), IFNULL(dtl.VALUE71,''),IFNULL(dtl.VALUE4,''),IFNULL(dtl.VALUE5,''),IFNULL(dtl.VALUE6,''),IFNULL(dtl.VALUE7,''),IFNULL(dtl.VALUE9,''),"
					+ " IFNULL(dtl.VALUE10,''),IFNULL(dtl.VALUE11,''), IFNULL(dtl.VALUE12,''),IFNULL(dtl.PRODSTATCD,''),IFNULL(dtl.USEDPERIOD,'0'),ifnull(dtl.MAXIMUMQTYTYP,''), "
					+ " IFNULL(dtl.LIMITQTY,'0'), IFNULL(dtl.LIMITTIME,'0'),IFNULL(dtl.OPTEXHIBITION,''),IFNULL(dtl.VALUE14,''), IFNULL(dtl.INPUTTYPOPT,''),IFNULL(dtl.VALUE15,''), ifnull(dtl.SALESPERIOD,''),"
					+ " IFNULL(dtl.IMAGE,''),IFNULL(dtl.PRODINFOAREA,''), IFNULL(dtl.PRODADDITIONAL,''),IFNULL(dtl.PROMOTION,''), IFNULL(dtl.VALUE72,''),IFNULL(dtl.VALUE73,''),"
					+ " IFNULL(dtl.ADULTGOODS,''), IFNULL(dtl.CHILDPROD,''),IFNULL(dtl.HOMEHOLDPROD,''),IFNULL(dtl.ELECTRICAL,''), IFNULL(dtl.CHEMISTRY,''),IFNULL(dtl.ECO,''), "
					+ " IFNULL(dtl.MEDICAL,''),IFNULL(dtl.ORIGIN,''), IFNULL(dtl.MULTIORIGIN,''),IFNULL(dtl.ASDTL,''), ifnull(dtl.SHIPPINGPOLICY,''), ifnull(dtl.SHIPPINGMETHOD,''), "
					+ " ifnull(dtl.EXPRESS,''), ifnull(dtl.VALUE17,''), ifnull(dtl.VALUE69,'0'), ifnull(dtl.VALUE70,''),ifnull(dtl.VALUE18,''),ifnull(dtl.VALUE19,''),ifnull(dtl.VALUE20,''), ifnull(dtl.VALUE21,''),"
					+ " ifnull(dtl.VALUE22,''), ifnull(dtl.OUTADDR,''), ifnull(dtl.BUNDLEDYN,''), ifnull(dtl.BUNDLEMAXNMIN,''),ifnull(dtl.TEMPLATE,''),ifnull(dtl.DELVCOSTTYP,''),"
					+ " ifnull(dtl.DELVCOST,''), ifnull(dtl.DELVFREECOST,''), ifnull(dtl.COSTDISCOUNT,''), ifnull(dtl.VALUE24,'0'), ifnull(dtl.VALUE25,'0'), ifnull(dtl.VALUE26,'0'), ifnull(dtl.VALUE27,'0'), "
					+ " ifnull(dtl.VALUE28,'0'), ifnull(dtl.VALUE29,'0'), ifnull(dtl.VALUE30,'0'), ifnull(dtl.VALUE31,'0'),ifnull(dtl.VALUE32,'0'), ifnull(dtl.VALUE33,'0'),"
					+ " ifnull(dtl.PREPAYMENT,''), ifnull(dtl.RETNEXCHANGEADDR,''),ifnull(dtl.RETNEXCHANGECOST,'0'), ifnull(dtl.ORDERDELAYTIME,''),"
					+ " ifnull(dtl.VALUE37,''),ifnull(dtl.VALUE38,''), ifnull(dtl.VALUE39,''),ifnull(dtl.VALUE40,''), ifnull(dtl.VALUE41,''),ifnull(dtl.VALUE42,''), IFNULL(dtl.VALUE74,''),"
					+ " ifnull(dtl.VALUE43,''),ifnull(dtl.VALUE44,''), ifnull(dtl.VALUE45,'0'),ifnull(dtl.VALUE46,'0'), ifnull(dtl.VALUE47,'0'),ifnull(dtl.VALUE48,'0'), ifnull(dtl.VALUE49,''), "
					+ " ifnull(dtl.VALUE50,''), ifnull(dtl.VALUE51,''), ifnull(dtl.VALUE52,''),ifnull(dtl.VALUE53,''), ifnull(dtl.VALUE54,''),ifnull(dtl.VALUE55,''), ifnull(dtl.VALUE56,''), ifnull(dtl.VALUE57,''), "
					+ " ifnull(dtl.VALUE58,''), ifnull(dtl.VALUE59,''), ifnull(dtl.VALUE60,''),ifnull(dtl.VALUE61,'0'), ifnull(dtl.VALUE62,''),ifnull(dtl.VALUE63,'0'), ifnull(dtl.VALUE64,''), IFNULL(dtl.VALUE75,''),"
					+ " ifnull(dtl.VALUE65,'0'), ifnull(dtl.VALUE66,''),ifnull(dtl.VALUE67,''),ifnull(dtl.VALUE68,''), IFNULL(dtl.VALUE76,''),IFNULL(dtl.VALUE77,''),IFNULL(dtl.VALUE78,''),IFNULL(dtl.VALUE79,''),"
					+ " IFNULL(dtl.VALUE80,''),IFNULL(dtl.VALUE81,''),IFNULL(dtl.VALUE82,''),IFNULL(dtl.VALUE83,''),IFNULL(dtl.VALUE84,''),IFNULL(dtl.VALUE85,''),IFNULL(dtl.VALUE86,''),IFNULL(dtl.VALUE87,''),"
					+ " IFNULL(dtl.VALUE88,''),IFNULL(dtl.VALUE89,''),IFNULL(dtl.VALUE90,''),IFNULL(dtl.VALUE91,''),ifnull(dtl.USEYN,'') "
					+ " FROM SHOPADDRMST addr JOIN SHOPMST MST ON MST.SHOPCD=ADDR.SHOPCD and MST.compno = ADDR.compno JOIN shopaddrgmarketdtl dtl ON ADDR.seq = dtl.seq and ADDR.compno = dtl.compno join shopinfo a on "
					+ " mst.shopcd = a.shopcd WHERE addr.seq = ? AND a.shopnm = ? and mst.compno = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, list.get(0));
			pstmt.setString(2, list.get(1));
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[shopAuctionDetailjoinList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				dto.setSeq(rs.getString(++columnIndex)); // 일련번호
				dto.setCompno(rs.getString(++columnIndex)); // 회사코드
				dto.setShopcd(rs.getString(++columnIndex)); // 쇼핑몰코드
				dto.setTitle(rs.getString(++columnIndex)); // 제목
				dto.setShopid(rs.getString(++columnIndex)); // 쇼핑몰아이디
				dto.setMemo(rs.getString(++columnIndex)); // 메모
				dto.setSelmthdcd(rs.getString(++columnIndex)); // 형태구분
				dto.setValue1(rs.getString(++columnIndex)); // 필수속성정보
				dto.setValue2(rs.getString(++columnIndex)); // 브랜드ON카테고리선택
				dto.setValue71(rs.getString(++columnIndex)); // BIZON카테고리선택
				dto.setValue4(rs.getString(++columnIndex)); // 소호노출카테고리
				dto.setValue5(rs.getString(++columnIndex)); // 소호노출스타일
				dto.setValue6(rs.getString(++columnIndex)); // 소호노출샤프
				dto.setValue7(rs.getString(++columnIndex)); // 소호노출칼라
				dto.setValue9(rs.getString(++columnIndex)); // 모델명적용여부
				dto.setValue10(rs.getString(++columnIndex)); // 모델명
				dto.setValue11(rs.getString(++columnIndex)); // 모델명수정여부
				dto.setValue12(rs.getString(++columnIndex)); // ESM브랜드
				dto.setProdstatcd(rs.getString(++columnIndex)); // 상품상태
				dto.setUsedperiod(rs.getString(++columnIndex)); // 중고개월수
				dto.setMaximumqtytyp(rs.getString(++columnIndex)); // 최대구매허용수량여부
				dto.setLimitqty(rs.getString(++columnIndex)); // 제한수량
				dto.setLimittime(rs.getString(++columnIndex)); // 제한기간
				dto.setOptexhibition(rs.getString(++columnIndex)); // 옵션재고전시방법
				dto.setValue14(rs.getString(++columnIndex)); // 옵션노출정렬여부
				dto.setInputtypopt(rs.getString(++columnIndex)); // 입력형옵션
				dto.setValue15(rs.getString(++columnIndex)); // 추가상품전시여부
				dto.setSalesperiod(rs.getString(++columnIndex)); // 판매기간
				dto.setImage(rs.getString(++columnIndex)); // 대표이미지
				dto.setProdinfoarea(rs.getString(++columnIndex)); // 상품정보입력영역
				dto.setProdadditional(rs.getString(++columnIndex)); // 추가구성입력영역
				dto.setPromotion(rs.getString(++columnIndex)); // 광고/홍보입력영역
				dto.setValue72(rs.getString(++columnIndex)); // 상품정보입력영역중문
				dto.setValue73(rs.getString(++columnIndex)); // 상품정보입력영역영문
				dto.setAdultgoods(rs.getString(++columnIndex)); // 성인용품여부
				dto.setChildprod(rs.getString(++columnIndex)); // 어린이제품
				dto.setHomeholdprod(rs.getString(++columnIndex)); // 생활용품
				dto.setElectrical(rs.getString(++columnIndex)); // 전기용품
				dto.setChemistry(rs.getString(++columnIndex)); // 생활화학/살생물제품
				dto.setEco(rs.getString(++columnIndex)); // 친환경
				dto.setMedical(rs.getString(++columnIndex)); // 의료기기
				dto.setOrigin(rs.getString(++columnIndex)); // 원산지선택여부
				dto.setMultiorigin(rs.getString(++columnIndex)); // 복수원산지여부
				dto.setAsdtl(rs.getString(++columnIndex)); // A/S정보
				dto.setShippingpolicy(rs.getString(++columnIndex)); // 발송정책
				dto.setShippingmethod(rs.getString(++columnIndex)); // 배송방법
				dto.setExpress(rs.getString(++columnIndex)); // 택배사
				dto.setValue17(rs.getString(++columnIndex)); // 방문수령여부
				dto.setValue69(rs.getString(++columnIndex)); // 방문수령가격할인금액
				dto.setValue70(rs.getString(++columnIndex)); // 방문수령사은품
				dto.setValue18(rs.getString(++columnIndex)); // 방문수령주소
				dto.setValue19(rs.getString(++columnIndex)); // 퀵서비스업체명
				dto.setValue20(rs.getString(++columnIndex)); // 퀵서비스연락처
				dto.setValue21(rs.getString(++columnIndex)); // 퀵서비스배송가능지역
				dto.setValue22(rs.getString(++columnIndex)); // 오늘출발여부
				dto.setOutaddr(rs.getString(++columnIndex)); // 출하지
				dto.setBundledyn(rs.getString(++columnIndex)); // 묶음배송여부
				dto.setBundlemaxnmin(rs.getString(++columnIndex)); // 묶음배송시배송비
				dto.setTemplate(rs.getString(++columnIndex)); // 묶음배송시템플릿
				dto.setDelvcosttyp(rs.getString(++columnIndex)); // 택배비무료여부
				dto.setDelvcost(rs.getString(++columnIndex)); // 배송기본배송비
				dto.setDelvfreecost(rs.getString(++columnIndex)); // 기본배송비2
				dto.setCostdiscount(rs.getString(++columnIndex)); // 배송비할인여부
				dto.setValue24(rs.getString(++columnIndex)); // 수량별차등수량1
				dto.setValue25(rs.getString(++columnIndex)); // 수량별차등수량2
				dto.setValue26(rs.getString(++columnIndex)); // 수량별차등수량3
				dto.setValue27(rs.getString(++columnIndex)); // 수량별차등수량4
				dto.setValue28(rs.getString(++columnIndex)); // 수량별차등수량5
				dto.setValue29(rs.getString(++columnIndex)); // 수량별차등배송비1
				dto.setValue30(rs.getString(++columnIndex)); // 수량별차등배송비2
				dto.setValue31(rs.getString(++columnIndex)); // 수량별차등배송비3
				dto.setValue32(rs.getString(++columnIndex)); // 수량별차등배송비4
				dto.setValue33(rs.getString(++columnIndex)); // 수량별차등배송비5
				dto.setPrepayment(rs.getString(++columnIndex)); // 착/선불여부
				dto.setRetnexchangeaddr(rs.getString(++columnIndex)); // 교환/반품주소
				dto.setRetnexchangecost(rs.getString(++columnIndex)); // 교환/반품배송비
				dto.setOrderdelaytime(rs.getString(++columnIndex)); // 주문후예상배송기간
				dto.setValue37(rs.getString(++columnIndex)); // 판매자관리코드
				dto.setValue38(rs.getString(++columnIndex)); // 판매자직접입력관리코드
				dto.setValue39(rs.getString(++columnIndex)); // 판매자카테고리
				dto.setValue40(rs.getString(++columnIndex)); // 사은품
				dto.setValue41(rs.getString(++columnIndex)); // 포털가격비교사이트등록여부
				dto.setValue42(rs.getString(++columnIndex)); // 포털가격비교사이트쿠폰적용여부
				dto.setValue74(rs.getString(++columnIndex)); // 해외판매여부
				dto.setValue43(rs.getString(++columnIndex)); // 사이트부담지원할인
				dto.setValue44(rs.getString(++columnIndex)); // 휴대폰가입신청서URL
				dto.setValue45(rs.getString(++columnIndex)); // 렌탈설치비
				dto.setValue46(rs.getString(++columnIndex)); // 렌탈의무사용기간
				dto.setValue47(rs.getString(++columnIndex)); // 렌탈등록비
				dto.setValue48(rs.getString(++columnIndex)); // 옵션관리코드여부
				dto.setValue49(rs.getString(++columnIndex)); // 기타특이사항
				dto.setValue50(rs.getString(++columnIndex)); // 상품명앞추가문구
				dto.setValue51(rs.getString(++columnIndex)); // 상품명뒷추가문구
				dto.setValue52(rs.getString(++columnIndex)); // 상품설명상단추가문구
				dto.setValue53(rs.getString(++columnIndex)); // 상품설명하단추가문구
				dto.setValue54(rs.getString(++columnIndex)); // 판매자부담할인여부
				dto.setValue55(rs.getString(++columnIndex)); // 판매자부담할인금액
				dto.setValue56(rs.getString(++columnIndex)); // 판매자부담할인시작일
				dto.setValue57(rs.getString(++columnIndex)); // 판매자부담할인종료일
				dto.setValue58(rs.getString(++columnIndex)); // 복수구매여부
				dto.setValue59(rs.getString(++columnIndex)); // 복수구매시작일
				dto.setValue60(rs.getString(++columnIndex)); // 복수구매종료일
				dto.setValue61(rs.getString(++columnIndex)); // 구매수량금액
				dto.setValue62(rs.getString(++columnIndex)); // 구매수량할인율
				dto.setValue63(rs.getString(++columnIndex)); // 구매수량상품별금액
				dto.setValue64(rs.getString(++columnIndex)); // 구매수량상품별할인율
				dto.setValue75(rs.getString(++columnIndex)); // 구매수량별무료갯수
				dto.setValue65(rs.getString(++columnIndex)); // 스마일캐시적립률
				dto.setValue66(rs.getString(++columnIndex)); // 후원쇼핑시작일
				dto.setValue67(rs.getString(++columnIndex)); // 후원쇼핑종료일
				dto.setValue68(rs.getString(++columnIndex)); // 후원쇼핑금액
				dto.setValue76(rs.getString(++columnIndex)); // 후원쇼핑누적적립한도액
				dto.setValue77(rs.getString(++columnIndex)); // 후원분야
				dto.setValue78(rs.getString(++columnIndex)); // 포커스등록여부
				dto.setValue79(rs.getString(++columnIndex)); // 포커스기간연장등록여부
				dto.setValue80(rs.getString(++columnIndex)); // 포커스기간연장일자
				dto.setValue81(rs.getString(++columnIndex)); // 포커스자동연장여부
				dto.setValue82(rs.getString(++columnIndex)); // 포커스무제한시종료일
				dto.setValue83(rs.getString(++columnIndex)); // 포커스기간시종료일
				dto.setValue84(rs.getString(++columnIndex)); // 포커스기간시종료일2
				dto.setValue85(rs.getString(++columnIndex)); // 포커스플러스등록여부
				dto.setValue86(rs.getString(++columnIndex)); // 포커스플러스기간연장등록여부
				dto.setValue87(rs.getString(++columnIndex)); // 포커스플러스기간연장일자
				dto.setValue88(rs.getString(++columnIndex)); // 포커스플러스자동연장여부
				dto.setValue89(rs.getString(++columnIndex)); // 포커스플러스무제한시종료일
				dto.setValue90(rs.getString(++columnIndex)); // 포커스플러스기간시종료일
				dto.setValue91(rs.getString(++columnIndex)); // 포커스플러스기간시종료일2
				dto.setUseyn(rs.getString(++columnIndex)); // 사용여부

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return dto;
	}

	public int ShopAddrDtlGmarketUpdate(String shopcd, ShopProductGmarketAdditionDto dto, String seq) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update shopaddrgmarketdtl set TITLE = ?,  SHOPID = ?, memo = ?,SELMTHDCD = ?, VALUE1 = ?, VALUE2 = ?, VALUE71 = ?,VALUE4 = ?, VALUE5 = ?, VALUE6 = ?, VALUE7 = ?, "
					+ " VALUE9 = ?, VALUE10 = ?, VALUE11= ? , VALUE12 = ?, PRODSTATCD = ?, USEDPERIOD = ?, MAXIMUMQTYTYP = ?, LIMITQTY = ?, LIMITTIME = ?, OPTEXHIBITION = ?, "
					+ " VALUE14 = ?, INPUTTYPOPT = ?, VALUE15 = ?, SALESPERIOD = ?, IMAGE = ?, PRODINFOAREA = ?, PRODADDITIONAL = ?, PROMOTION = ?,VALUE72 = ?,VALUE73 = ?, ADULTGOODS = ?, CHILDPROD = ?, "
					+ " HOMEHOLDPROD = ?, ELECTRICAL = ?, CHEMISTRY = ?, ECO = ?, MEDICAL = ?, ORIGIN = ?, MULTIORIGIN = ?, ASDTL = ?, SHIPPINGPOLICY = ?, SHIPPINGMETHOD = ?,EXPRESS = ?, VALUE17 = ?, "
					+ " VALUE69 = ?, VALUE70 = ?, VALUE18 = ?, VALUE19 = ?, VALUE20 = ?, VALUE21 = ?, VALUE22 = ?, OUTADDR = ?,BUNDLEDYN = ?, BUNDLEMAXNMIN = ?, "
					+ " TEMPLATE = ?, DELVCOSTTYP = ?, DELVCOST = ?, DELVFREECOST = ?, COSTDISCOUNT = ?, VALUE24 = ?, VALUE25 = ?, VALUE26 = ?, VALUE27 = ?, VALUE28 = ?, VALUE29 = ?, VALUE30 = ?, "
					+ " VALUE31 = ?, VALUE32 = ?, VALUE33 = ?, PREPAYMENT = ?, RETNEXCHANGEADDR = ?,RETNEXCHANGECOST=?,ORDERDELAYTIME=?, "
					+ " VALUE37 = ? , VALUE38 = ?, VALUE39 = ? , VALUE40 = ?, VALUE41 = ?, VALUE42 = ?, VALUE74 = ?,VALUE43 = ?, VALUE44 = ?, VALUE45 = ?, VALUE46 = ?, VALUE47 = ?, VALUE48 = ?, VALUE49 =? , "
					+ " VALUE50 =? , VALUE51 = ?, VALUE52 = ?, VALUE53 = ?, VALUE54 = ?, VALUE55 = ?, VALUE56 = ?, VALUE57 = ?, VALUE58 = ?, VALUE59 = ?, VALUE60 = ?, VALUE61 = ?, VALUE62 = ?, "
					+ " VALUE63 = ?, VALUE64 = ?, VALUE75 = ?,VALUE65 = ?, VALUE66 = ?, VALUE67 = ?, VALUE68 = ?, VALUE76 = ?,VALUE77 = ?,VALUE78 = ?,VALUE79 = ?,VALUE80 = ?,VALUE81 = ?,VALUE82 = ?, "
					+ " VALUE83 = ?,VALUE84 = ?,VALUE85 = ?,VALUE86 = ?,VALUE87 = ?,VALUE88 = ?,VALUE89 = ?,VALUE90 = ?,VALUE91 = ?,USEYN=? "
					+ " where COMPNO = ? and SHOPCD = ? and SEQ = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, dto.getTitle()); // 제목
			pstmt.setString(++rowIdx, dto.getShopid()); // 쇼핑몰아이디
			pstmt.setString(++rowIdx, dto.getMemo()); // 메모
			pstmt.setString(++rowIdx, dto.getSelmthdcd()); // 형태구분
			pstmt.setString(++rowIdx, dto.getValue1()); // 필수속성정보
			pstmt.setString(++rowIdx, dto.getValue2()); // 브랜드ON카테고리선택
			pstmt.setString(++rowIdx, dto.getValue71()); // BIZON카테고리선택
			pstmt.setString(++rowIdx, dto.getValue4()); // 소호노출카테고리
			pstmt.setString(++rowIdx, dto.getValue5()); // 소호노출스타일
			pstmt.setString(++rowIdx, dto.getValue6()); // 소호노출샤프
			pstmt.setString(++rowIdx, dto.getValue7()); // 소호노출칼라
			pstmt.setString(++rowIdx, dto.getValue9()); // 모델명적용여부
			pstmt.setString(++rowIdx, dto.getValue10()); // 모델명
			pstmt.setString(++rowIdx, dto.getValue11()); // 모델명수정여부
			pstmt.setString(++rowIdx, dto.getValue12()); // ESM브랜드
			pstmt.setString(++rowIdx, dto.getProdstatcd()); // 상품상태
			pstmt.setString(++rowIdx, dto.getUsedperiod()); // 중고개월수
			pstmt.setString(++rowIdx, dto.getMaximumqtytyp()); // 최대구매허용수량여부
			pstmt.setString(++rowIdx, dto.getLimitqty()); // 제한수량
			pstmt.setString(++rowIdx, dto.getLimittime()); // 제한기간
			pstmt.setString(++rowIdx, dto.getOptexhibition()); // 옵션재고전시방법
			pstmt.setString(++rowIdx, dto.getValue14()); // 옵션노출정렬여부
			pstmt.setString(++rowIdx, dto.getInputtypopt()); // 입력형옵션
			pstmt.setString(++rowIdx, dto.getValue15()); // 추가상품전시여부
			pstmt.setString(++rowIdx, dto.getSalesperiod()); // 판매기간
			pstmt.setString(++rowIdx, dto.getImage()); // 대표이미지
			pstmt.setString(++rowIdx, dto.getProdinfoarea()); // 상품정보입력영역
			pstmt.setString(++rowIdx, dto.getProdadditional()); // 추가구성입력영역
			pstmt.setString(++rowIdx, dto.getPromotion()); // 광고/홍보입력영역
			pstmt.setString(++rowIdx, dto.getValue72()); // 상품정보입력영역중문
			pstmt.setString(++rowIdx, dto.getValue73()); // 상품정보입력영역영문
			pstmt.setString(++rowIdx, dto.getAdultgoods()); // 성인용품여부
			pstmt.setString(++rowIdx, dto.getChildprod()); // 어린이제품
			pstmt.setString(++rowIdx, dto.getHomeholdprod()); // 생활용품
			pstmt.setString(++rowIdx, dto.getElectrical()); // 전기용품
			pstmt.setString(++rowIdx, dto.getChemistry()); // 생활화학/살생물제품
			pstmt.setString(++rowIdx, dto.getEco()); // 친환경
			pstmt.setString(++rowIdx, dto.getMedical()); // 의료기기
			pstmt.setString(++rowIdx, dto.getOrigin()); // 원산지선택여부
			pstmt.setString(++rowIdx, dto.getMultiorigin()); // 복수원산지여부
			pstmt.setString(++rowIdx, dto.getAsdtl()); // A/S정보
			pstmt.setString(++rowIdx, dto.getShippingpolicy()); // 발송정책
			pstmt.setString(++rowIdx, dto.getShippingmethod()); // 배송방법
			pstmt.setString(++rowIdx, dto.getExpress()); // 택배사
			pstmt.setString(++rowIdx, dto.getValue17()); // 방문수령여부
			pstmt.setString(++rowIdx, dto.getValue69()); // 방문수령가격할인금액
			pstmt.setString(++rowIdx, dto.getValue70()); // 방문수령사은품
			pstmt.setString(++rowIdx, dto.getValue18()); // 방문수령주소
			pstmt.setString(++rowIdx, dto.getValue19()); // 퀵서비스업체명
			pstmt.setString(++rowIdx, dto.getValue20()); // 퀵서비스연락처
			pstmt.setString(++rowIdx, dto.getValue21()); // 퀵서비스배송가능지역
			pstmt.setString(++rowIdx, dto.getValue22()); // 오늘출발여부
			pstmt.setString(++rowIdx, dto.getOutaddr()); // 출하지
			pstmt.setString(++rowIdx, dto.getBundledyn()); // 묶음배송여부
			pstmt.setString(++rowIdx, dto.getBundlemaxnmin()); // 묶음배송시배송비
			pstmt.setString(++rowIdx, dto.getTemplate()); // 묶음배송시템플릿
			pstmt.setString(++rowIdx, dto.getDelvcosttyp()); // 택배비무료여부
			pstmt.setString(++rowIdx, dto.getDelvcost()); // 배송기본배송비
			pstmt.setString(++rowIdx, dto.getDelvfreecost()); // 기본배송비2
			pstmt.setString(++rowIdx, dto.getCostdiscount()); // 배송비할인여부
			pstmt.setString(++rowIdx, dto.getValue24()); // 수량별차등수량1
			pstmt.setString(++rowIdx, dto.getValue25()); // 수량별차등수량2
			pstmt.setString(++rowIdx, dto.getValue26()); // 수량별차등수량3
			pstmt.setString(++rowIdx, dto.getValue27()); // 수량별차등수량4
			pstmt.setString(++rowIdx, dto.getValue28()); // 수량별차등수량5
			pstmt.setString(++rowIdx, dto.getValue29()); // 수량별차등배송비1
			pstmt.setString(++rowIdx, dto.getValue30()); // 수량별차등배송비2
			pstmt.setString(++rowIdx, dto.getValue31()); // 수량별차등배송비3
			pstmt.setString(++rowIdx, dto.getValue32()); // 수량별차등배송비4
			pstmt.setString(++rowIdx, dto.getValue33()); // 수량별차등배송비5
			pstmt.setString(++rowIdx, dto.getPrepayment()); // 착/선불여부
			pstmt.setString(++rowIdx, dto.getRetnexchangeaddr()); // 교환/반품주소
			pstmt.setString(++rowIdx, dto.getRetnexchangecost()); // 교환/반품배송비
			pstmt.setString(++rowIdx, dto.getOrderdelaytime()); // 주문후예상배송기간
			pstmt.setString(++rowIdx, dto.getValue37()); // 판매자관리코드
			pstmt.setString(++rowIdx, dto.getValue38()); // 판매자직접입력관리코드
			pstmt.setString(++rowIdx, dto.getValue39()); // 판매자카테고리
			pstmt.setString(++rowIdx, dto.getValue40()); // 사은품
			pstmt.setString(++rowIdx, dto.getValue41()); // 포털가격비교사이트등록여부
			pstmt.setString(++rowIdx, dto.getValue42()); // 포털가격비교사이트쿠폰적용여부
			pstmt.setString(++rowIdx, dto.getValue74()); // 해외판매여부
			pstmt.setString(++rowIdx, dto.getValue43()); // 사이트부담지원할인
			pstmt.setString(++rowIdx, dto.getValue44()); // 휴대폰가입신청서URL
			pstmt.setString(++rowIdx, dto.getValue45()); // 렌탈설치비
			pstmt.setString(++rowIdx, dto.getValue46()); // 렌탈의무사용기간
			pstmt.setString(++rowIdx, dto.getValue47()); // 렌탈등록비
			pstmt.setString(++rowIdx, dto.getValue48()); // 옵션관리코드여부
			pstmt.setString(++rowIdx, dto.getValue49()); // 기타특이사항
			pstmt.setString(++rowIdx, dto.getValue50()); // 상품명앞추가문구
			pstmt.setString(++rowIdx, dto.getValue51()); // 상품명뒷추가문구
			pstmt.setString(++rowIdx, dto.getValue52()); // 상품설명상단추가문구
			pstmt.setString(++rowIdx, dto.getValue53()); // 상품설명하단추가문구
			pstmt.setString(++rowIdx, dto.getValue54()); // 판매자부담할인여부
			pstmt.setString(++rowIdx, dto.getValue55()); // 판매자부담할인금액
			pstmt.setString(++rowIdx, dto.getValue56()); // 판매자부담할인시작일
			pstmt.setString(++rowIdx, dto.getValue57()); // 판매자부담할인종료일
			pstmt.setString(++rowIdx, dto.getValue58()); // 복수구매여부
			pstmt.setString(++rowIdx, dto.getValue59()); // 복수구매시작일
			pstmt.setString(++rowIdx, dto.getValue60()); // 복수구매종료일
			pstmt.setString(++rowIdx, dto.getValue61()); // 구매수량금액
			pstmt.setString(++rowIdx, dto.getValue62()); // 구매수량할인율
			pstmt.setString(++rowIdx, dto.getValue63()); // 구매수량상품별금액
			pstmt.setString(++rowIdx, dto.getValue64()); // 구매수량상품별할인율
			pstmt.setString(++rowIdx, dto.getValue75()); // 구매수량별무료갯수
			pstmt.setString(++rowIdx, dto.getValue65()); // 스마일캐시적립률
			pstmt.setString(++rowIdx, dto.getValue66()); // 후원쇼핑시작일
			pstmt.setString(++rowIdx, dto.getValue67()); // 후원쇼핑종료일
			pstmt.setString(++rowIdx, dto.getValue68()); // 후원쇼핑금액
			pstmt.setString(++rowIdx, dto.getValue76()); // 후원쇼핑누적적립한도액
			pstmt.setString(++rowIdx, dto.getValue77()); // 후원분야
			pstmt.setString(++rowIdx, dto.getValue78()); // 포커스등록여부
			pstmt.setString(++rowIdx, dto.getValue79()); // 포커스기간연장등록여부
			pstmt.setString(++rowIdx, dto.getValue80()); // 포커스기간연장일자
			pstmt.setString(++rowIdx, dto.getValue81()); // 포커스자동연장여부
			pstmt.setString(++rowIdx, dto.getValue82()); // 포커스무제한시종료일
			pstmt.setString(++rowIdx, dto.getValue83()); // 포커스기간시종료일
			pstmt.setString(++rowIdx, dto.getValue84()); // 포커스기간시종료일2
			pstmt.setString(++rowIdx, dto.getValue85()); // 포커스플러스등록여부
			pstmt.setString(++rowIdx, dto.getValue86()); // 포커스플러스기간연장등록여부
			pstmt.setString(++rowIdx, dto.getValue87()); // 포커스플러스기간연장일자
			pstmt.setString(++rowIdx, dto.getValue88()); // 포커스플러스자동연장여부
			pstmt.setString(++rowIdx, dto.getValue89()); // 포커스플러스무제한시종료일
			pstmt.setString(++rowIdx, dto.getValue90()); // 포커스플러스기간시종료일
			pstmt.setString(++rowIdx, dto.getValue91()); // 포커스플러스기간시종료일2
			pstmt.setString(++rowIdx, dto.getUseyn()); // 사용여부

			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, seq);
			pstmt.executeUpdate();

			System.out.println("[ShopAddrDtlAuctionUpdate]" + pstmt.toString());

			String sql_mst = " update shopaddrmst set TITLE = ? , SELMTHDCD = ? , USEYN = ? , UPDATEDT = ? where compno = ? and shopcd = ? and seq = ? ";

			sql_mst = sql_mst.toUpperCase();

			pstmt = connection.prepareStatement(sql_mst);
			statementlist.add(pstmt);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getSelmthdcd());
			pstmt.setString(3, dto.getUseyn());
			pstmt.setString(4, YDMATimeUtil.getCurrentTimeByYDFormat());

			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(6, shopcd);
			pstmt.setString(7, seq);

			System.out.println("[insertmst-ShopAddrDtlInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

}/////////////

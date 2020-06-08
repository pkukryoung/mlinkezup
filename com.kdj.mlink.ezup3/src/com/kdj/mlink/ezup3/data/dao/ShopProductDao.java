package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class ShopProductDao {

	// 11번가 전송누를시 전부가지고오기
	public List<List<String>> getShopprodAllList(List<ShopProductDto> list) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(PRODSEQ,'0'), ifnull(COMPNO,''), ifnull(GOODS_NM,''), ifnull(GOODS_KEYWORD,''), ifnull(MODEL_NM,''), ifnull(MODEL_NO,''), ifnull(BRAND_NM,''), ifnull(COMPAYNY_GOODS_CD,''), ";
			sql += " ifnull(GOODS_SEARCH,''), ifnull(GOODS_GUBUN,''), ifnull(CLASS_CD1,''), ifnull(CLASS_CD2,''),  ifnull(CLASS_CD3,''), ifnull(CLASS_CD4,''), ifnull(PARTNER_ID,''), ifnull(DPARTNER_ID,''),";// 물류처아이디
			sql += " ifnull(MAKER,''), ifnull(ORIGIN,''), ifnull(MAKE_YEAR,''), ifnull(MAKE_DM,''), ifnull(GOODS_SEASON,''), ifnull(SEX,''), ifnull(STATUS,''), ifnull(DELIV_ABLE_REGION,''), ifnull(TAX_YN,''),";// 세금구분
			sql += " ifnull(DELV_TYPE,''), ifnull(DELV_COST,0), ifnull(BANPUM_AREA,''), ifnull(GOODS_COST,0), ifnull(GOODS_PRICE,0), ifnull(GOODS_CONSUMER_PRICE,0), ifnull(CHAR_1_NM,''), ifnull(CHAR_1_VAL,''), ";// 옵션상세명칭1
			sql += " ifnull(CHAR_2_NM,''), ifnull(CHAR_2_VAL,''), ifnull(IMG_PATH, ''), ifnull(IMG_PATH1,''), ifnull(IMG_PATH2,''), ifnull(IMG_PATH3,''), ifnull(IMG_PATH4,''), ifnull(IMG_PATH5,''), ifnull(IMG_PATH6,''), ";
			sql += " ifnull(IMG_PATH7,''), ifnull(IMG_PATH8,''), ifnull(IMG_PATH9, ''), ifnull(IMG_PATH10,''), ifnull(IMG_PATH11,''), ifnull(IMG_PATH12,''), ifnull(IMG_PATH13,''), ifnull(IMG_PATH14,''), "
					+ " ifnull(IMG_PATH15,''),ifnull(IMG_PATH16,''), ifnull(IMG_PATH17,''), ifnull(IMG_PATH18, ''), ifnull(IMG_PATH19,''), ifnull(IMG_PATH20,''), ifnull(IMG_PATH21,''), ifnull(IMG_PATH22,''), "
					+ " ifnull(IMG_PATH23,''), ifnull(IMG_PATH24,''), replace(ifnull(GOODS_REMARKS,''),\"\"\"\",''''), ifnull(CERTNO, ''), ifnull(AVLST_DM,''), ifnull(AVLED_DM,''), ifnull(ISSUEDATE,''), ifnull(CERTDATE,'')," // 인증일자
					+ " ifnull(CERT_AGENCY,''), ifnull(CERTFIELD,''), ifnull(MATERIAL,''), ifnull(STOCK_USE_YN, ''), ifnull(OPT_TYPE,''), ifnull(PROP_EDIT_YN,''), ifnull(PROP1_CD,''), ifnull(PROP_VAL1,''), "// 속성값1
					+ " ifnull(PROP_VAL2,''), ifnull(PROP_VAL3,''), ifnull(PROP_VAL4,''), ifnull(PROP_VAL5, ''), ifnull(PROP_VAL6,''), ifnull(PROP_VAL7,''), ifnull(PROP_VAL8,''), ifnull(PROP_VAL9,''), ifnull(PROP_VAL10,''), "
					+ " ifnull(PROP_VAL11,''), ifnull(PROP_VAL12,''), ifnull(PROP_VAL13,''), ifnull(PROP_VAL14, ''), ifnull(PROP_VAL15,''), ifnull(PROP_VAL16,''), ifnull(PROP_VAL17,''), ifnull(PROP_VAL18,''), "
					+ " ifnull(PROP_VAL19,''), ifnull(PROP_VAL20,''), ifnull(PROP_VAL21,''), ifnull(PROP_VAL22, ''), ifnull(PROP_VAL23,''), ifnull(PROP_VAL24,''), ifnull(PROP_VAL25,''), ifnull(PROP_VAL26,''), "
					+ " ifnull(PROP_VAL27,''), ifnull(PROP_VAL28,''), ifnull(PACK_CODE_STR,''), ifnull(GOODS_NM_EN, ''), ifnull(GOODS_NM_PR,''), ifnull(GOODS_REMARKS2,''), ifnull(GOODS_REMARKS3,''), "// 추가상품상세설명2
					+ " ifnull(GOODS_REMARKS4,''), ifnull(IMPORTNO,''), ifnull(GOODS_COST2,''), ifnull(ORIGIN2, ''), ifnull(EXPIRE_DM,''), ifnull(SUPPLY_SAVE_YN,''), ifnull(DESCRITION,''),  "// 관리자메모
					+ " ifnull(shopprodno,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), ifnull(MODIFYDT,''), ifnull(MODIFYID, '')";
			sql += " FROM shopprodinfo ";
			sql += " where compno = ? and prodseq = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			for (int k = 0; k < list.size(); k++) {
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.get(k).getProdseq());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					int i = 0;
					List<String> listarr = new ArrayList<>();
					for (int j = 1; j <= 118; j++) {
						listarr.add(rs.getString(j));
					}

					contents.add(listarr);

				}
			}
//			for(List<String> prodlist : list) {
//
//
//			System.out.println("[getShopprodAllList]"+pstmt.toString());
//
//
//			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public List<ShopProductDto> getShopProductList(int option, String prodFrom, String prodTo, String lagcateg,
			String midcateg, String smlcateg, String detcateg, String purch, String logis, int supply, String orderIvtr,
			String shopfee, String sex, String season, String prod, String searchgubun) throws Exception {

		// opt == {상품코드, 상품명}
		// List<List<String>> contents = new ArrayList<>();
		List<ShopProductDto> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// YWM_FUNC_BOSSPRODCD(",V_COMPNO, ",a.prodcd) as img
			String sql = "select prodseq , ifnull(COMPAYNY_GOODS_CD,''), "
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,COMPAYNY_GOODS_CD) as img,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ " ifnull(GOODS_NM,''), ifnull(CLASS_CD1,''), ifnull(CLASS_CD2,''), ifnull(CLASS_CD3,''), ifnull(CLASS_CD4,''),ifnull(GOODS_GUBUN,''), "
					+ "	ifnull(PARTNER_ID,'') ,ifnull(CHAR_1_NM,''), ifnull(CHAR_2_NM,''), ifnull(MODEL_NM,''), ifnull(ORIGIN,''), ifnull(MAKER,''), ifnull(BRAND_NM,''), ifnull(STATUS,''), ifnull(DELV_TYPE,''), "
					+ "ifnull(DELV_COST,'0'), ifnull(GOODS_PRICE,'0'), ifnull(GOODS_COST,'0'), ifnull(INSERTDT,''), ifnull(MODIFYDT,'') "// DELV_COST뒤에-
																																			// 단품수,단품수판매,재고수량넣어야함
					+ " from shopprodinfo where compno = ? ";

			if (option == 0) {
				sql += " and insertdt >= ? and insertdt <= ? ";
			} else {
				sql += " and MODIFYDT >= ? and MODIFYDT <= ? ";
			}
			// 카테고리분류코드넣어야함

//			//공급여부
//			if(supply!=0) {
//				sql += " and STATUS = ? ";
//			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, prodFrom);
			pstmt.setString(3, prodTo);

			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ShopProductDto dto = new ShopProductDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setProdseq(rs.getString(++i));
				dto.setCompayny_goods_cd(rs.getString(++i));
				dto.setImg(rs.getString(++i));
				dto.setGoods_nm(rs.getString(++i));
				dto.setClass_cd1(rs.getString(++i));
				dto.setClass_cd2(rs.getString(++i));
				dto.setClass_cd3(rs.getString(++i));
				dto.setClass_cd4(rs.getString(++i));
				dto.setGoods_gubun(rs.getString(++i));
				dto.setPartner_id(rs.getString(++i));
				dto.setChar_1_nm(rs.getString(++i));
				dto.setChar_2_nm(rs.getString(++i));
				dto.setModel_nm(rs.getString(++i));
				dto.setOrigin(rs.getString(++i));
				dto.setMaker(rs.getString(++i));
				dto.setBrand_nm(rs.getString(++i));
				dto.setStatus(rs.getString(++i));
				dto.setDelv_type(rs.getString(++i));
				dto.setDelv_cost(rs.getString(++i));
//					dto.//단품수
//					dto.//단품판매수
//					dto.//총재고량
				dto.setGoods_price(rs.getString(++i));
				dto.setGoods_cost(rs.getString(++i));
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

	public void setProductMstShopcode(List<List<String>> contents, List<List<String>> allContents) throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodinfo 송신쇼핑몰번호 업데이트
			String sql_prodmst = " Update shopprodin set MODIFYDT = ? , MODIFYID = ? where SENDSEQ = ? and compno = ? ";

			PreparedStatement pstmt = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt);
			// [SUCCESS, 수정, sabang_prodcdcd, ydms_prodcd]
			// [FAIL, 수정, ydms_prodcd]
			for (int i = 0; i < contents.size(); i++) {
				List<String> alllist = allContents.get(i);
				int idx = 0;
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());

				pstmt.setString(++idx, alllist.get(0));
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}

			pstmt.executeBatch();
			pstmt.clearParameters();
			// pstmt.clearBatch();// Batch 초기화

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public void setProductInInsert(List<List<String>> contents, List<List<String>> allContents, List<String> dtllist,
			List<String> idlist) throws Exception {

		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = " insert into shopprodin (compno, SHOPCD, SHOPSEQ,  PRODSEQ, seq,GOODS_NM, GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, "
					+ " CLASS_CD2,CLASS_CD3, CLASS_CD4, PARTNER_ID,  DPARTNER_ID, MAKER, ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, STATUS, DELIV_ABLE_REGION, TAX_YN,DELV_TYPE,DELV_COST,BANPUM_AREA, "
					+ " GOODS_COST,GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM,  CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3, IMG_PATH4, IMG_PATH5, IMG_PATH6,IMG_PATH7,IMG_PATH8, "
					+ " IMG_PATH9,IMG_PATH10, IMG_PATH11, IMG_PATH12,  IMG_PATH13, IMG_PATH14, IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22,IMG_PATH23,IMG_PATH24, "
					+ " GOODS_REMARKS,CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, PROP1_CD, PROP_VAL1,PROP_VAL2,PROP_VAL3, PROP_VAL4, "
					+ " PROP_VAL5,PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10, PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18,PROP_VAL19,PROP_VAL20, PROP_VAL21, "
					+ " PROP_VAL22,PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN, GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, "
					+ " GOODS_COST2,ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN,DESCRITION, SHOPPRODNO, INSERTDT,INSERTID ) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,"// 23
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, "// 86
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ) ";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (int i = 0; i < contents.size(); i++) {
				List<String> alllist = allContents.get(i);
				int idx = 0;
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++idx, idlist.get(1));
				pstmt.setString(++idx, idlist.get(2));
				pstmt.setString(++idx, alllist.get(0));
				pstmt.setString(++idx, dtllist.get(2));
				for (int k = 3; k <= 114; k++) {
					if (k == 114) {
						pstmt.setString(++idx, contents.get(i).get(3));
					} else {
						pstmt.setString(++idx, alllist.get(k - 1));
					}
				}
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());
				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			System.out.println("[setProductInInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public List<ShopProductDto> getShopProdInList(int option, String prodFrom, String prodTo, String lagcateg,
			String midcateg, String smlcateg, String detcateg, String shopnm, String shopid, String purch, String logis,
			String orderIvtr, String shopfee, String sex, String season, String prod, String prodststsd,
			String prodststpr, String price, String prodnm, String yorN, String bundle, String searchgubun,
			String search) throws Exception {
		// List<List<String>> contents = new ArrayList<>();
		List<ShopProductDto> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT IFNULL(A.shopcd,'') , IFNULL(A.shopseq,''), IFNULL(A.sendseq,'0'), IFNULL(A.INSERTDT,''),IFNULL(A.MODIFYDT,''), IFNULL(A.seq,'0'),IFNULL(A.SHOPPRODNO,''),IFNULL(A.prodseq,''), "
					+ " IFNULL(A.COMPAYNY_GOODS_CD,''), IFNULL(A.GOODS_NM,''), IFNULL(A.CLASS_CD1,''), IFNULL(A.CLASS_CD2,''), IFNULL(A.CLASS_CD3,''), IFNULL(A.CLASS_CD4,''),IFNULL(A.GOODS_GUBUN,''), "
					+ " IFNULL(A.PARTNER_ID,'') ,IFNULL(A.MODEL_NM,''), IFNULL(A.BRAND_NM,''), IFNULL(A.MAKER,''), IFNULL(A.DELV_TYPE,''), IFNULL(A.DELV_COST,'0'), IFNULL(A.STATUS,''), "
					+ " IFNULL(A.GOODS_PRICE,'0'), IFNULL(A.GOODS_COST,'0'),IFNULL(B.SHOPPINGID,''), IFNULL(B.PASSWORD,'') "
					+ " FROM shopprodin AS A INNER JOIN shopdtl AS B ON A.COMPNO = B.COMPNO AND A.SHOPCD = B.SHOPCD AND A.SHOPSEQ = B.SHOPSEQ "
					+ " where A.compno = ? ";

			if (option == 0) {
				sql += " and A.insertdt >= ? and A.insertdt <= ? ";
			} else {
				sql += " and A.MODIFYDT >= ? and A.MODIFYDT <= ? ";
			}
			// 카테고리분류코드넣어야함
			sql += " ORDER by A.INSERTDT desc ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, prodFrom);
			pstmt.setString(3, prodTo);

			System.out.println("[getShopProdInList]" + pstmt.toString());
			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ShopProductDto dto = new ShopProductDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setSendstats("연동생성");// 임의로만듬
				dto.setShopcd(rs.getString(++i));
				dto.setShopseq(rs.getString(++i));
				dto.setSendseq(rs.getString(++i));
				dto.setInsertdt(rs.getString(++i));
				// 재송신일시
				dto.setModifydt(rs.getString(++i));
				dto.setSeq(rs.getString(++i));
				// 쇼핑몰카테고리코드
				dto.setShopprodno(rs.getString(++i));
				dto.setProdseq(rs.getString(++i));
				dto.setCompayny_goods_cd(rs.getString(++i));
				dto.setGoods_nm(rs.getString(++i));
				dto.setClass_cd1(rs.getString(++i));
				dto.setClass_cd2(rs.getString(++i));
				dto.setClass_cd3(rs.getString(++i));
				dto.setClass_cd4(rs.getString(++i));
				dto.setGoods_gubun(rs.getString(++i));
				dto.setPartner_id(rs.getString(++i));
				dto.setModel_nm(rs.getString(++i));
				dto.setBrand_nm(rs.getString(++i));
				dto.setMaker(rs.getString(++i));
				dto.setDelv_type(rs.getString(++i));
				dto.setDelv_cost(rs.getString(++i));
				dto.setStatus(rs.getString(++i));
				dto.setGoods_price(rs.getString(++i));
				dto.setGoods_cost(rs.getString(++i));
				// 판매기간
				// 체크박스3개
				dto.setShopid(rs.getString(++i));// 아이디
				dto.setShoppw(rs.getString(++i));// 비밀번호
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

	public void setProductErrInsert(List<List<String>> contents, List<List<String>> allContents, List<String> dtllist,
			List<String> idlist) throws Exception {

		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = " insert into shopproderr (compno, SHOPCD, SHOPSEQ,  PRODSEQ, seq,GOODS_NM, GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, "
					+ " CLASS_CD2,CLASS_CD3, CLASS_CD4, PARTNER_ID,  DPARTNER_ID, MAKER, ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, STATUS, DELIV_ABLE_REGION, TAX_YN,DELV_TYPE,DELV_COST,BANPUM_AREA, "
					+ " GOODS_COST,GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM,  CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3, IMG_PATH4, IMG_PATH5, IMG_PATH6,IMG_PATH7,IMG_PATH8, "
					+ " IMG_PATH9,IMG_PATH10, IMG_PATH11, IMG_PATH12,  IMG_PATH13, IMG_PATH14, IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22,IMG_PATH23,IMG_PATH24, "
					+ " GOODS_REMARKS,CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, PROP1_CD, PROP_VAL1,PROP_VAL2,PROP_VAL3, PROP_VAL4, "
					+ " PROP_VAL5,PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10, PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18,PROP_VAL19,PROP_VAL20, PROP_VAL21, "
					+ " PROP_VAL22,PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN, GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, "
					+ " GOODS_COST2,ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN,DESCRITION, FAILCONTENT, INSERTDT,INSERTID ) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,"// 23
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, "// 86
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ) ";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (int i = 0; i < contents.size(); i++) {
				List<String> alllist = allContents.get(i);
				int idx = 0;
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++idx, idlist.get(1));
				pstmt.setString(++idx, idlist.get(2));
				pstmt.setString(++idx, alllist.get(0));
				pstmt.setString(++idx, dtllist.get(2));
				for (int k = 3; k <= 114; k++) {
					if (k == 114) {
						pstmt.setString(++idx, contents.get(i).get(1));
					} else {
						pstmt.setString(++idx, alllist.get(k - 1));
					}
				}
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());
				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			System.out.println("[setProductInInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public List<ShopProductDto> getShopProdErrorList(int option, String prodFrom, String prodTo, String lagcateg,
			String midcateg, String smlcateg, String detcateg, String shopnm, String shopid, String purch, String logis,
			String orderIvtr, String shopfee) throws Exception {
		// List<List<String>> contents = new ArrayList<>();
		List<ShopProductDto> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(shopcd,'') , ifnull(shopseq,''), ifnull(sendseq,'0'), ifnull(INSERTDT,''),ifnull(MODIFYDT,''), ifnull(seq,'0'), ifnull(prodseq,''), "// 쇼핑몰사진,재송신일시,카테고리코드
					// + String.format("YWM_FUNC_BOSSPRODCD(%s,COMPAYNY_GOODS_CD) as img,",
					// YDMASessonUtil.getCompnoInfo().getCompno())
					+ " ifnull(COMPAYNY_GOODS_CD,''), ifnull(GOODS_NM,''), ifnull(CLASS_CD1,''), ifnull(CLASS_CD2,''), ifnull(CLASS_CD3,''), ifnull(CLASS_CD4,''),ifnull(GOODS_GUBUN,''), "
					+ "	ifnull(PARTNER_ID,'') ,ifnull(MODEL_NM,''), ifnull(BRAND_NM,''), ifnull(MAKER,''), ifnull(DELV_TYPE,''), ifnull(DELV_COST,'0'), ifnull(STATUS,''), "
					+ "ifnull(GOODS_PRICE,'0'), ifnull(GOODS_COST,'0'), ifnull(FAILCONTENT,'')"// 판매기간,쇼핑몰별판매가적용,쇼핑몰별상세설명적용,쇼핑몰별상품명적용
					+ " from shopproderr where compno = ? ";

			if (option == 0) {
				sql += " and insertdt >= ? and insertdt <= ? order by insertdt desc";
			} else {
				sql += " and MODIFYDT >= ? and MODIFYDT <= ? order by MODIFYDT desc ";
			}

			// 카테고리분류코드넣어야함

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, prodFrom);
			pstmt.setString(3, prodTo);

			rs = pstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ShopProductDto dto = new ShopProductDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setSendstats("연동생성");// 임의로만듬
				dto.setShopcd(rs.getString(++i));
				dto.setShopseq(rs.getString(++i));
				dto.setSendseq(rs.getString(++i));
				dto.setInsertdt(rs.getString(++i));
				// 재송신일시
				dto.setModifydt(rs.getString(++i));
				dto.setSeq(rs.getString(++i));
				// 쇼핑몰카테고리코드
				dto.setProdseq(rs.getString(++i));
				dto.setCompayny_goods_cd(rs.getString(++i));
				dto.setGoods_nm(rs.getString(++i));
				dto.setClass_cd1(rs.getString(++i));
				dto.setClass_cd2(rs.getString(++i));
				dto.setClass_cd3(rs.getString(++i));
				dto.setClass_cd4(rs.getString(++i));
				dto.setGoods_gubun(rs.getString(++i));
				dto.setPartner_id(rs.getString(++i));
				dto.setModel_nm(rs.getString(++i));
				dto.setBrand_nm(rs.getString(++i));
				dto.setMaker(rs.getString(++i));
				dto.setDelv_type(rs.getString(++i));
				dto.setDelv_cost(rs.getString(++i));
				dto.setStatus(rs.getString(++i));
				dto.setGoods_price(rs.getString(++i));
				dto.setGoods_cost(rs.getString(++i));
				dto.setFailcontent(rs.getString(++i));
				// 판매기간
				// 체크박스3개
				list.add(dto);

				// list.add(dto);
			}
			System.out.println("[getShopProdInList]" + pstmt.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	// 에러테이블삭제
	public int[] ProductErrordataDelete(List<com.kdj.mlink.ezup3.shop.dao.ShopProductDto> listdto) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		int[] result;

		String sql = "DELETE FROM shopproderr where compno=? and sendseq=? ";
		sql = sql.toUpperCase();
		connection = DBCPInit.getInstance().getConnection();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		for (com.kdj.mlink.ezup3.shop.dao.ShopProductDto dto : listdto) {
			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, dto.getSendseq());

			System.out.println("[ProductErrordataDelete]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화
		}

		result = pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

		return result;

	}

	// prodin가지고오기prodinfo만큼
	public List<List<String>> getShopprodInList(List<ShopProductDto> list) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(PRODSEQ,'0'),ifnull(COMPNO,''),ifnull(GOODS_NM,''), ifnull(GOODS_KEYWORD,''), ifnull(MODEL_NM,''), ifnull(MODEL_NO,''), ifnull(BRAND_NM,''), ifnull(COMPAYNY_GOODS_CD,''), ";
			sql += " ifnull(GOODS_SEARCH,''), ifnull(GOODS_GUBUN,''), ifnull(CLASS_CD1,''), ifnull(CLASS_CD2,''),  ifnull(CLASS_CD3,''), ifnull(CLASS_CD4,''), ifnull(PARTNER_ID,''), ifnull(DPARTNER_ID,''),";// 물류처아이디
			sql += " ifnull(MAKER,''), ifnull(ORIGIN,''), ifnull(MAKE_YEAR,''), ifnull(MAKE_DM,''), ifnull(GOODS_SEASON,''), ifnull(SEX,''), ifnull(STATUS,''), ifnull(DELIV_ABLE_REGION,''), ifnull(TAX_YN,''),";// 세금구분
			sql += " ifnull(DELV_TYPE,''), ifnull(DELV_COST,0), ifnull(BANPUM_AREA,''), ifnull(GOODS_COST,0), ifnull(GOODS_PRICE,0), ifnull(GOODS_CONSUMER_PRICE,0), ifnull(CHAR_1_NM,''), ifnull(CHAR_1_VAL,''), ";// 옵션상세명칭1
			sql += " ifnull(CHAR_2_NM,''), ifnull(CHAR_2_VAL,''), ifnull(IMG_PATH, ''), ifnull(IMG_PATH1,''), ifnull(IMG_PATH2,''), ifnull(IMG_PATH3,''), ifnull(IMG_PATH4,''), ifnull(IMG_PATH5,''), ifnull(IMG_PATH6,''), ";
			sql += " ifnull(IMG_PATH7,''), ifnull(IMG_PATH8,''), ifnull(IMG_PATH9, ''), ifnull(IMG_PATH10,''), ifnull(IMG_PATH11,''), ifnull(IMG_PATH12,''), ifnull(IMG_PATH13,''), ifnull(IMG_PATH14,''), "
					+ " ifnull(IMG_PATH15,''),ifnull(IMG_PATH16,''), ifnull(IMG_PATH17,''), ifnull(IMG_PATH18, ''), ifnull(IMG_PATH19,''), ifnull(IMG_PATH20,''), ifnull(IMG_PATH21,''), ifnull(IMG_PATH22,''), "
					+ " ifnull(IMG_PATH23,''), ifnull(IMG_PATH24,''), ifnull(GOODS_REMARKS,''), ifnull(CERTNO, ''), ifnull(AVLST_DM,''), ifnull(AVLED_DM,''), ifnull(ISSUEDATE,''), ifnull(CERTDATE,'')," // 인증일자
					+ " ifnull(CERT_AGENCY,''), ifnull(CERTFIELD,''), ifnull(MATERIAL,''), ifnull(STOCK_USE_YN, ''), ifnull(OPT_TYPE,''), ifnull(PROP_EDIT_YN,''), ifnull(PROP1_CD,''), ifnull(PROP_VAL1,''), "// 속성값1
					+ " ifnull(PROP_VAL2,''), ifnull(PROP_VAL3,''), ifnull(PROP_VAL4,''), ifnull(PROP_VAL5, ''), ifnull(PROP_VAL6,''), ifnull(PROP_VAL7,''), ifnull(PROP_VAL8,''), ifnull(PROP_VAL9,''), ifnull(PROP_VAL10,''), "
					+ " ifnull(PROP_VAL11,''), ifnull(PROP_VAL12,''), ifnull(PROP_VAL13,''), ifnull(PROP_VAL14, ''), ifnull(PROP_VAL15,''), ifnull(PROP_VAL16,''), ifnull(PROP_VAL17,''), ifnull(PROP_VAL18,''), "
					+ " ifnull(PROP_VAL19,''), ifnull(PROP_VAL20,''), ifnull(PROP_VAL21,''), ifnull(PROP_VAL22, ''), ifnull(PROP_VAL23,''), ifnull(PROP_VAL24,''), ifnull(PROP_VAL25,''), ifnull(PROP_VAL26,''), "
					+ " ifnull(PROP_VAL27,''), ifnull(PROP_VAL28,''), ifnull(PACK_CODE_STR,''), ifnull(GOODS_NM_EN, ''), ifnull(GOODS_NM_PR,''), ifnull(GOODS_REMARKS2,''), ifnull(GOODS_REMARKS3,''), "// 추가상품상세설명2
					+ " ifnull(GOODS_REMARKS4,''), ifnull(IMPORTNO,''), ifnull(GOODS_COST2,''), ifnull(ORIGIN2, ''), ifnull(EXPIRE_DM,''), ifnull(SUPPLY_SAVE_YN,''), ifnull(DESCRITION,''),  "// 관리자메모
					+ " ifnull(shopprodno,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), ifnull(MODIFYDT,''), ifnull(MODIFYID, '')";
			sql += " FROM shopprodin ";
			sql += " where compno = ? and SENDSEQ = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			for (int k = 0; k < list.size(); k++) {
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.get(k).getSendseq());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					int i = 0;
					List<String> listarr = new ArrayList<>();
					for (int j = 1; j <= 118; j++) {
						listarr.add(rs.getString(j));
					}

					contents.add(listarr);

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public void setProductInNErrInsert(List<List<String>> contents, List<List<String>> allContents) throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = " insert into shopproderr (compno, SHOPCD, SHOPSEQ,  PRODSEQ, seq,GOODS_NM, GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, "
					+ " CLASS_CD2,CLASS_CD3, CLASS_CD4, PARTNER_ID,  DPARTNER_ID, MAKER, ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, STATUS, DELIV_ABLE_REGION, TAX_YN,DELV_TYPE,DELV_COST,BANPUM_AREA, "
					+ " GOODS_COST,GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM,  CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3, IMG_PATH4, IMG_PATH5, IMG_PATH6,IMG_PATH7,IMG_PATH8, "
					+ " IMG_PATH9,IMG_PATH10, IMG_PATH11, IMG_PATH12,  IMG_PATH13, IMG_PATH14, IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22,IMG_PATH23,IMG_PATH24, "
					+ " GOODS_REMARKS,CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, PROP1_CD, PROP_VAL1,PROP_VAL2,PROP_VAL3, PROP_VAL4, "
					+ " PROP_VAL5,PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10, PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18,PROP_VAL19,PROP_VAL20, PROP_VAL21, "
					+ " PROP_VAL22,PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN, GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, "
					+ " GOODS_COST2,ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN,DESCRITION, FAILCONTENT, INSERTDT,INSERTID ) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,"// 23
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, "// 86
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ) ";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (int i = 0; i < contents.size(); i++) {
				List<String> alllist = allContents.get(i);
				int idx = 0;
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
				for (int k = 3; k <= 118; k++) {
					pstmt.setString(++idx, alllist.get(k - 1));
				}
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());
				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			System.out.println("[setProductInInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public List<List<String>> getShopprodInAllList(List<ShopProductDto> list) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(sendseq,'0'), ifnull(COMPNO,''),ifnull(shopcd,''),ifnull(shopseq,'0'),ifnull(PRODSEQ,'0'),ifnull(seq,'0'),ifnull(GOODS_NM,''), ifnull(GOODS_KEYWORD,''), ifnull(MODEL_NM,''), ifnull(MODEL_NO,''), ifnull(BRAND_NM,''), ifnull(COMPAYNY_GOODS_CD,''), ";
			sql += " ifnull(GOODS_SEARCH,''), ifnull(GOODS_GUBUN,''), ifnull(CLASS_CD1,''), ifnull(CLASS_CD2,''),  ifnull(CLASS_CD3,''), ifnull(CLASS_CD4,''), ifnull(PARTNER_ID,''), ifnull(DPARTNER_ID,''),";// 물류처아이디
			sql += " ifnull(MAKER,''), ifnull(ORIGIN,''), ifnull(MAKE_YEAR,''), ifnull(MAKE_DM,''), ifnull(GOODS_SEASON,''), ifnull(SEX,''), ifnull(STATUS,''), ifnull(DELIV_ABLE_REGION,''), ifnull(TAX_YN,''),";// 세금구분
			sql += " ifnull(DELV_TYPE,''), ifnull(DELV_COST,0), ifnull(BANPUM_AREA,''), ifnull(GOODS_COST,0), ifnull(GOODS_PRICE,0), ifnull(GOODS_CONSUMER_PRICE,0), ifnull(CHAR_1_NM,''), ifnull(CHAR_1_VAL,''), ";// 옵션상세명칭1
			sql += " ifnull(CHAR_2_NM,''), ifnull(CHAR_2_VAL,''), ifnull(IMG_PATH, ''), ifnull(IMG_PATH1,''), ifnull(IMG_PATH2,''), ifnull(IMG_PATH3,''), ifnull(IMG_PATH4,''), ifnull(IMG_PATH5,''), ifnull(IMG_PATH6,''), ";
			sql += " ifnull(IMG_PATH7,''), ifnull(IMG_PATH8,''), ifnull(IMG_PATH9, ''), ifnull(IMG_PATH10,''), ifnull(IMG_PATH11,''), ifnull(IMG_PATH12,''), ifnull(IMG_PATH13,''), ifnull(IMG_PATH14,''), "
					+ " ifnull(IMG_PATH15,''),ifnull(IMG_PATH16,''), ifnull(IMG_PATH17,''), ifnull(IMG_PATH18, ''), ifnull(IMG_PATH19,''), ifnull(IMG_PATH20,''), ifnull(IMG_PATH21,''), ifnull(IMG_PATH22,''), "
					+ " ifnull(IMG_PATH23,''), ifnull(IMG_PATH24,''), ifnull(GOODS_REMARKS,''), ifnull(CERTNO, ''), ifnull(AVLST_DM,''), ifnull(AVLED_DM,''), ifnull(ISSUEDATE,''), ifnull(CERTDATE,'')," // 인증일자
					+ " ifnull(CERT_AGENCY,''), ifnull(CERTFIELD,''), ifnull(MATERIAL,''), ifnull(STOCK_USE_YN, ''), ifnull(OPT_TYPE,''), ifnull(PROP_EDIT_YN,''), ifnull(PROP1_CD,''), ifnull(PROP_VAL1,''), "// 속성값1
					+ " ifnull(PROP_VAL2,''), ifnull(PROP_VAL3,''), ifnull(PROP_VAL4,''), ifnull(PROP_VAL5, ''), ifnull(PROP_VAL6,''), ifnull(PROP_VAL7,''), ifnull(PROP_VAL8,''), ifnull(PROP_VAL9,''), ifnull(PROP_VAL10,''), "
					+ " ifnull(PROP_VAL11,''), ifnull(PROP_VAL12,''), ifnull(PROP_VAL13,''), ifnull(PROP_VAL14, ''), ifnull(PROP_VAL15,''), ifnull(PROP_VAL16,''), ifnull(PROP_VAL17,''), ifnull(PROP_VAL18,''), "
					+ " ifnull(PROP_VAL19,''), ifnull(PROP_VAL20,''), ifnull(PROP_VAL21,''), ifnull(PROP_VAL22, ''), ifnull(PROP_VAL23,''), ifnull(PROP_VAL24,''), ifnull(PROP_VAL25,''), ifnull(PROP_VAL26,''), "
					+ " ifnull(PROP_VAL27,''), ifnull(PROP_VAL28,''), ifnull(PACK_CODE_STR,''), ifnull(GOODS_NM_EN, ''), ifnull(GOODS_NM_PR,''), ifnull(GOODS_REMARKS2,''), ifnull(GOODS_REMARKS3,''), "// 추가상품상세설명2
					+ " ifnull(GOODS_REMARKS4,''), ifnull(IMPORTNO,''), ifnull(GOODS_COST2,''), ifnull(ORIGIN2, ''), ifnull(EXPIRE_DM,''), ifnull(SUPPLY_SAVE_YN,''), ifnull(DESCRITION,''),  "// 관리자메모
					+ " ifnull(shopprodno,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), ifnull(MODIFYDT,''), ifnull(MODIFYID, '')";
			sql += " FROM shopprodin ";
			sql += " where compno = ? and SENDSEQ = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			for (int k = 0; k < list.size(); k++) {
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.get(k).getSendseq());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					int i = 0;
					List<String> listarr = new ArrayList<>();
					for (int j = 1; j <= 122; j++) {
						listarr.add(rs.getString(j));
					}

					contents.add(listarr);

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	// proderror가지고오기prodinfo만큼
	public List<List<String>> getShopprodErrorList(List<ShopProductDto> list) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(PRODSEQ,'0'),ifnull(COMPNO,''),ifnull(GOODS_NM,''), ifnull(GOODS_KEYWORD,''), ifnull(MODEL_NM,''), ifnull(MODEL_NO,''), ifnull(BRAND_NM,''), ifnull(COMPAYNY_GOODS_CD,''), ";
			sql += " ifnull(GOODS_SEARCH,''), ifnull(GOODS_GUBUN,''), ifnull(CLASS_CD1,''), ifnull(CLASS_CD2,''),  ifnull(CLASS_CD3,''), ifnull(CLASS_CD4,''), ifnull(PARTNER_ID,''), ifnull(DPARTNER_ID,''),";// 물류처아이디
			sql += " ifnull(MAKER,''), ifnull(ORIGIN,''), ifnull(MAKE_YEAR,''), ifnull(MAKE_DM,''), ifnull(GOODS_SEASON,''), ifnull(SEX,''), ifnull(STATUS,''), ifnull(DELIV_ABLE_REGION,''), ifnull(TAX_YN,''),";// 세금구분
			sql += " ifnull(DELV_TYPE,''), ifnull(DELV_COST,0), ifnull(BANPUM_AREA,''), ifnull(GOODS_COST,0), ifnull(GOODS_PRICE,0), ifnull(GOODS_CONSUMER_PRICE,0), ifnull(CHAR_1_NM,''), ifnull(CHAR_1_VAL,''), ";// 옵션상세명칭1
			sql += " ifnull(CHAR_2_NM,''), ifnull(CHAR_2_VAL,''), ifnull(IMG_PATH, ''), ifnull(IMG_PATH1,''), ifnull(IMG_PATH2,''), ifnull(IMG_PATH3,''), ifnull(IMG_PATH4,''), ifnull(IMG_PATH5,''), ifnull(IMG_PATH6,''), ";
			sql += " ifnull(IMG_PATH7,''), ifnull(IMG_PATH8,''), ifnull(IMG_PATH9, ''), ifnull(IMG_PATH10,''), ifnull(IMG_PATH11,''), ifnull(IMG_PATH12,''), ifnull(IMG_PATH13,''), ifnull(IMG_PATH14,''), "
					+ " ifnull(IMG_PATH15,''),ifnull(IMG_PATH16,''), ifnull(IMG_PATH17,''), ifnull(IMG_PATH18, ''), ifnull(IMG_PATH19,''), ifnull(IMG_PATH20,''), ifnull(IMG_PATH21,''), ifnull(IMG_PATH22,''), "
					+ " ifnull(IMG_PATH23,''), ifnull(IMG_PATH24,''), ifnull(GOODS_REMARKS,''), ifnull(CERTNO, ''), ifnull(AVLST_DM,''), ifnull(AVLED_DM,''), ifnull(ISSUEDATE,''), ifnull(CERTDATE,'')," // 인증일자
					+ " ifnull(CERT_AGENCY,''), ifnull(CERTFIELD,''), ifnull(MATERIAL,''), ifnull(STOCK_USE_YN, ''), ifnull(OPT_TYPE,''), ifnull(PROP_EDIT_YN,''), ifnull(PROP1_CD,''), ifnull(PROP_VAL1,''), "// 속성값1
					+ " ifnull(PROP_VAL2,''), ifnull(PROP_VAL3,''), ifnull(PROP_VAL4,''), ifnull(PROP_VAL5, ''), ifnull(PROP_VAL6,''), ifnull(PROP_VAL7,''), ifnull(PROP_VAL8,''), ifnull(PROP_VAL9,''), ifnull(PROP_VAL10,''), "
					+ " ifnull(PROP_VAL11,''), ifnull(PROP_VAL12,''), ifnull(PROP_VAL13,''), ifnull(PROP_VAL14, ''), ifnull(PROP_VAL15,''), ifnull(PROP_VAL16,''), ifnull(PROP_VAL17,''), ifnull(PROP_VAL18,''), "
					+ " ifnull(PROP_VAL19,''), ifnull(PROP_VAL20,''), ifnull(PROP_VAL21,''), ifnull(PROP_VAL22, ''), ifnull(PROP_VAL23,''), ifnull(PROP_VAL24,''), ifnull(PROP_VAL25,''), ifnull(PROP_VAL26,''), "
					+ " ifnull(PROP_VAL27,''), ifnull(PROP_VAL28,''), ifnull(PACK_CODE_STR,''), ifnull(GOODS_NM_EN, ''), ifnull(GOODS_NM_PR,''), ifnull(GOODS_REMARKS2,''), ifnull(GOODS_REMARKS3,''), "// 추가상품상세설명2
					+ " ifnull(GOODS_REMARKS4,''), ifnull(IMPORTNO,''), ifnull(GOODS_COST2,''), ifnull(ORIGIN2, ''), ifnull(EXPIRE_DM,''), ifnull(SUPPLY_SAVE_YN,''), ifnull(DESCRITION,''),  "// 관리자메모
					+ " ifnull(FAILCONTENT,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), ifnull(MODIFYDT,''), ifnull(MODIFYID, '')";
			sql += " FROM shopproderr ";
			sql += " where compno = ? and SENDSEQ = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			for (int k = 0; k < list.size(); k++) {
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.get(k).getSendseq());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					int i = 0;
					List<String> listarr = new ArrayList<>();
					for (int j = 1; j <= 118; j++) {
						listarr.add(rs.getString(j));
					}

					contents.add(listarr);

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	// 에러테이블 다가지고오기
	public List<List<String>> getShopprodErrorAllList(List<ShopProductDto> list) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(sendseq,'0'), ifnull(COMPNO,''),ifnull(shopcd,''),ifnull(shopseq,'0'),ifnull(PRODSEQ,'0'),ifnull(seq,'0'),ifnull(GOODS_NM,''), ifnull(GOODS_KEYWORD,''), ifnull(MODEL_NM,''), ifnull(MODEL_NO,''), ifnull(BRAND_NM,''), ifnull(COMPAYNY_GOODS_CD,''), ";
			sql += " ifnull(GOODS_SEARCH,''), ifnull(GOODS_GUBUN,''), ifnull(CLASS_CD1,''), ifnull(CLASS_CD2,''),  ifnull(CLASS_CD3,''), ifnull(CLASS_CD4,''), ifnull(PARTNER_ID,''), ifnull(DPARTNER_ID,''),";// 물류처아이디
			sql += " ifnull(MAKER,''), ifnull(ORIGIN,''), ifnull(MAKE_YEAR,''), ifnull(MAKE_DM,''), ifnull(GOODS_SEASON,''), ifnull(SEX,''), ifnull(STATUS,''), ifnull(DELIV_ABLE_REGION,''), ifnull(TAX_YN,''),";// 세금구분
			sql += " ifnull(DELV_TYPE,''), ifnull(DELV_COST,0), ifnull(BANPUM_AREA,''), ifnull(GOODS_COST,0), ifnull(GOODS_PRICE,0), ifnull(GOODS_CONSUMER_PRICE,0), ifnull(CHAR_1_NM,''), ifnull(CHAR_1_VAL,''), ";// 옵션상세명칭1
			sql += " ifnull(CHAR_2_NM,''), ifnull(CHAR_2_VAL,''), ifnull(IMG_PATH, ''), ifnull(IMG_PATH1,''), ifnull(IMG_PATH2,''), ifnull(IMG_PATH3,''), ifnull(IMG_PATH4,''), ifnull(IMG_PATH5,''), ifnull(IMG_PATH6,''), ";
			sql += " ifnull(IMG_PATH7,''), ifnull(IMG_PATH8,''), ifnull(IMG_PATH9, ''), ifnull(IMG_PATH10,''), ifnull(IMG_PATH11,''), ifnull(IMG_PATH12,''), ifnull(IMG_PATH13,''), ifnull(IMG_PATH14,''), "
					+ " ifnull(IMG_PATH15,''),ifnull(IMG_PATH16,''), ifnull(IMG_PATH17,''), ifnull(IMG_PATH18, ''), ifnull(IMG_PATH19,''), ifnull(IMG_PATH20,''), ifnull(IMG_PATH21,''), ifnull(IMG_PATH22,''), "
					+ " ifnull(IMG_PATH23,''), ifnull(IMG_PATH24,''), ifnull(GOODS_REMARKS,''), ifnull(CERTNO, ''), ifnull(AVLST_DM,''), ifnull(AVLED_DM,''), ifnull(ISSUEDATE,''), ifnull(CERTDATE,'')," // 인증일자
					+ " ifnull(CERT_AGENCY,''), ifnull(CERTFIELD,''), ifnull(MATERIAL,''), ifnull(STOCK_USE_YN, ''), ifnull(OPT_TYPE,''), ifnull(PROP_EDIT_YN,''), ifnull(PROP1_CD,''), ifnull(PROP_VAL1,''), "// 속성값1
					+ " ifnull(PROP_VAL2,''), ifnull(PROP_VAL3,''), ifnull(PROP_VAL4,''), ifnull(PROP_VAL5, ''), ifnull(PROP_VAL6,''), ifnull(PROP_VAL7,''), ifnull(PROP_VAL8,''), ifnull(PROP_VAL9,''), ifnull(PROP_VAL10,''), "
					+ " ifnull(PROP_VAL11,''), ifnull(PROP_VAL12,''), ifnull(PROP_VAL13,''), ifnull(PROP_VAL14, ''), ifnull(PROP_VAL15,''), ifnull(PROP_VAL16,''), ifnull(PROP_VAL17,''), ifnull(PROP_VAL18,''), "
					+ " ifnull(PROP_VAL19,''), ifnull(PROP_VAL20,''), ifnull(PROP_VAL21,''), ifnull(PROP_VAL22, ''), ifnull(PROP_VAL23,''), ifnull(PROP_VAL24,''), ifnull(PROP_VAL25,''), ifnull(PROP_VAL26,''), "
					+ " ifnull(PROP_VAL27,''), ifnull(PROP_VAL28,''), ifnull(PACK_CODE_STR,''), ifnull(GOODS_NM_EN, ''), ifnull(GOODS_NM_PR,''), ifnull(GOODS_REMARKS2,''), ifnull(GOODS_REMARKS3,''), "// 추가상품상세설명2
					+ " ifnull(GOODS_REMARKS4,''), ifnull(IMPORTNO,''), ifnull(GOODS_COST2,''), ifnull(ORIGIN2, ''), ifnull(EXPIRE_DM,''), ifnull(SUPPLY_SAVE_YN,''), ifnull(DESCRITION,''),  "// 관리자메모
					+ " ifnull(FAILCONTENT,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), ifnull(MODIFYDT,''), ifnull(MODIFYID, '')";
			sql += " FROM shopproderr ";
			sql += " where compno = ? and SENDSEQ = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			for (int k = 0; k < list.size(); k++) {
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.get(k).getSendseq());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					int i = 0;
					List<String> listarr = new ArrayList<>();
					for (int j = 1; j <= 122; j++) {
						listarr.add(rs.getString(j));
					}

					contents.add(listarr);

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	// 에러에서 실패했을때
	public void setProductErrorUpdate(List<List<String>> sendResult, List<List<String>> prodContents_target)
			throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodinfo 송신쇼핑몰번호 업데이트
			String sql_prodmst = " Update shopproderr set MODIFYDT = ? , MODIFYID = ? where SENDSEQ = ? and compno = ? ";

			PreparedStatement pstmt = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt);
			// [SUCCESS, 수정, sabang_prodcdcd, ydms_prodcd]
			// [FAIL, 수정, ydms_prodcd]
			for (int i = 0; i < sendResult.size(); i++) {
				List<String> alllist = prodContents_target.get(i);
				int idx = 0;
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());

				pstmt.setString(++idx, alllist.get(0));
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}

			pstmt.executeBatch();
			pstmt.clearParameters();
			// pstmt.clearBatch();// Batch 초기화

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public void setProductErrorNIninsert(List<List<String>> sendResult, List<List<String>> prodContents_target)
			throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = " insert into shopprodin (compno, SHOPCD, SHOPSEQ,  PRODSEQ, seq,GOODS_NM, GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, "
					+ " CLASS_CD2,CLASS_CD3, CLASS_CD4, PARTNER_ID,  DPARTNER_ID, MAKER, ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, STATUS, DELIV_ABLE_REGION, TAX_YN,DELV_TYPE,DELV_COST,BANPUM_AREA, "
					+ " GOODS_COST,GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM,  CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3, IMG_PATH4, IMG_PATH5, IMG_PATH6,IMG_PATH7,IMG_PATH8, "
					+ " IMG_PATH9,IMG_PATH10, IMG_PATH11, IMG_PATH12,  IMG_PATH13, IMG_PATH14, IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22,IMG_PATH23,IMG_PATH24, "
					+ " GOODS_REMARKS,CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, PROP1_CD, PROP_VAL1,PROP_VAL2,PROP_VAL3, PROP_VAL4, "
					+ " PROP_VAL5,PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10, PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18,PROP_VAL19,PROP_VAL20, PROP_VAL21, "
					+ " PROP_VAL22,PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN, GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, "
					+ " GOODS_COST2,ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN,DESCRITION, SHOPPRODNO, INSERTDT,INSERTID ) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,"// 23
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, "// 86
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ) ";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (int i = 0; i < sendResult.size(); i++) {
				List<String> alllist = prodContents_target.get(i);
				int idx = 0;
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
				for (int k = 3; k <= 118; k++) {
					if (k == 118) {
						pstmt.setString(++idx, sendResult.get(i).get(3));
					} else {
						pstmt.setString(++idx, alllist.get(k - 1));
					}
				}
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());
				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			System.out.println("[setProductErrorNIninsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public int ShopProductUpdate(com.kdj.mlink.ezup3.shop.dao.ShopProductDto dto, String prodnm, String price)
			throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "update shopprodin set GOODS_NM=?, GOODS_PRICE=? where SENDSEQ=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, prodnm);
			pstmt.setInt(++i, Integer.parseInt(price));

			pstmt.setString(++i, dto.getSendseq()); // 조건 key
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[larCategoryUpdate]" + pstmt.toString());
			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

}

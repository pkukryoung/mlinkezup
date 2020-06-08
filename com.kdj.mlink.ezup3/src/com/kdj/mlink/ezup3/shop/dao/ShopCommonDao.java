package com.kdj.mlink.ezup3.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.shop.common.CodeItem;
import com.kdj.mlink.ezup3.shop.common.OrderStatus;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;

/*
 *  SHOP 공통 DB CRUD
 */
public class ShopCommonDao {
	private static ShopCommonDao daoInstance = new ShopCommonDao(); // dao 싱글톤으로 생성 한다.

	private ShopCommonDao() {
	}

	public static ShopCommonDao get() {
		return daoInstance;
	}

	// 시즌에 값받아오기
	public String getAttrName(String attrcd) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String ret = "";
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = String.format(" SELECT attrnm as attrnm from prodattr where attrcd = '%s' limit 1 ", attrcd);

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			System.out.println("[getSeasonItems]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ret = rs.getString("attrnm");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return ret;
	}

	public List<ShopErrCodeDto> getErrorCode() throws Exception {
		List<ShopErrCodeDto> contents = new ArrayList<ShopErrCodeDto>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(code,''), ifnull(content,''), ifnull(treatcontent,''), ifnull(use_yn,'') from shoperrcode ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);

			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				int j = 0;
				ShopErrCodeDto dto = new ShopErrCodeDto();
				dto.setCode(rs.getString(++j));
				dto.setContent(rs.getString(++j));
				dto.setTreatcontent(rs.getString(++j));
				dto.setUse_yn(rs.getString(++j));
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

	/*
	 * 해당 주문번호가 존재 하는지 체크
	 */
	public boolean isCrawling() {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "SELECT EXISTS(\r\n" + "	SELECT 1 FROM shopdtl\r\n" + "	WHERE COMPNO= ?\r\n"
					+ "	AND  DEALTREAD ='사용중'\r\n" + ")AS ISCHK";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);
			pstmt_orddtl.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[존재 유무]" + pstmt_orddtl.toString());

			rs = pstmt_orddtl.executeQuery();

			while (rs.next()) {
				result = (rs.getInt("ISCHK") > 0);
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

	public List<ShopDeliveryDto> getExpress(String shopcode) throws Exception {
		List<ShopDeliveryDto> contents = new ArrayList<ShopDeliveryDto>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(SHOPCD,'') as  SHOPCD,ifnull(DLVID,'') as DLVID,ifnull(DLVNM,'') as DLVNM , ifnull(EXPRESS_URL_ID,'') as URL from shopdeliverys where shopcd = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, shopcode);

			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int j = 0;
			while (rs.next()) {
				ShopDeliveryDto dto = new ShopDeliveryDto();
				dto.setShopCd(rs.getString("SHOPCD"));
				dto.setDlvID(rs.getString("DLVID"));
				dto.setDivNM(rs.getString("DLVNM"));
				dto.setUrlcode(rs.getString("URL"));
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

	public List<ShopDeliveryDto> getExpressAll() throws Exception {
		List<ShopDeliveryDto> contents = new ArrayList<ShopDeliveryDto>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(SHOPCD,'') as  SHOPCD,ifnull(DLVID,'') as DLVID ,ifnull(DLVNM,'')  as DLVNM from shopdeliverys order by SHOPCD,DLVID  ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int j = 0;

			while (rs.next()) {
				ShopDeliveryDto dto = new ShopDeliveryDto();
				dto.setShopCd(rs.getString("SHOPCD"));
				dto.setDlvID(rs.getString("DLVID"));
				dto.setDivNM(rs.getString("DLVNM"));
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

	/*
	 * 
	 */
	public List<ShoppingMallDetailDto> ShoppingMallDetailList() throws Exception {
		List<ShoppingMallDetailDto> list = new ArrayList<ShoppingMallDetailDto>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT IFNULL(m.COMPNO, '') AS COMPNO, \r\n");
			sql.append("IFNULL(m.SHOPCD,'')  AS SHOPCD, \r\n");
			sql.append("IFNULL(b.SHOPNM,'')  AS SHOPNM, \r\n");
			sql.append("IFNULL(b.SHOPURL,'')  AS SHOPURL, \r\n");
			// sql.append("IFNULL(d.SHOPID,'') AS SHOPID, \r\n");
			sql.append("IFNULL(d.SHOPPINGID,'') AS SHOPPINGID, \r\n ");
			sql.append("IFNULL(D.NICKNM1,D.NICKNM2) AS NICKNM, \r\n");
			sql.append("IFNULL(D.APIKEY,'') AS APIKEY, \r\n");
			sql.append("IFNULL(D.DEALTREAD,'') AS DEALTREAD, \r\n");
			sql.append("IFNULL(d.PASSWORD, '') AS PASSWORD , \r\n");

			sql.append("IFNULL(d.AUTHKEY1, '') AS AUTHKEY1  ,   \r\n");
			sql.append("IFNULL(d.AUTHKEY2, '') AS AUTHKEY2 ,   \r\n");
			sql.append("IFNULL(D.NICKNM2, '') AS VENDORID ,      \r\n");
			sql.append("IFNULL(D.SHOPSEQ, '') AS SHOPSEQ      \r\n");
			sql.append(" FROM shopmst AS m");
			sql.append(" JOIN shopdtl AS d");
			sql.append(" ON m.COMPNO = d.COMPNO AND m.SHOPCD = d.SHOPCD join shopinfo b on m.shopcd = b.shopcd ");
			sql.append(" WHERE m.COMPNO =?  AND D.DEALTREAD = '사용중' \r\n");
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
				dto.setVendorId(rs.getString("VENDORID"));
				dto.setShopseq(rs.getString("SHOPSEQ"));
				dto.setShopUrl(rs.getString("SHOPURL"));
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

	/*
	 * shopcategory insert
	 */
	public void setShopCategoryInsertNUpdate(List<ShopCateDto> list) throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = "insert into categlarge (SHOPCD,CODE,NAME, PARENT_CODE, LEVEL, SORT_IDX,COMMENT,USE_YN )  \r\n"
					+ " values (?, ?, ?, ?, ?, ? , ?, ?) ON  DUPLICATE KEY  UPDATE NAME = ? , PARENT_CODE = ? , LEVEL = ? , SORT_IDX = ? , COMMENT = ?, USE_YN = ?  ";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (ShopCateDto dto : list) {
				int idx = 0;
				pstmt.setString(++idx, dto.getShopcd());
				pstmt.setString(++idx, dto.getCode());
				pstmt.setString(++idx, dto.getName());
				pstmt.setString(++idx, dto.getParent_code());
				pstmt.setInt(++idx, dto.getLevel());
				pstmt.setInt(++idx, dto.getSort_idx());
				pstmt.setString(++idx, dto.getComment());
				pstmt.setString(++idx, dto.getUse_yn());

				pstmt.setString(++idx, dto.getName());
				pstmt.setString(++idx, dto.getParent_code());
				pstmt.setInt(++idx, dto.getLevel());
				pstmt.setInt(++idx, dto.getSort_idx());
				pstmt.setString(++idx, dto.getComment());
				pstmt.setString(++idx, dto.getUse_yn());

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

	/*
	 * 상품수정에서 선택된상품 수정하기 버튼
	 */
	public void setShopProdInfoUpdate(List<ShopProductDto> list) {
		// TODO Auto-generated method stub

	}

	/*
	 * compno있는지 없는지 체크
	 */
	public boolean isCompno() {

		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "SELECT count(*) as cnt from systemconf WHERE COMPNO= ? ";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);
			pstmt_orddtl.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[존재 유무]" + pstmt_orddtl.toString());

			rs = pstmt_orddtl.executeQuery();

			while (rs.next()) {
				result = (rs.getInt("cnt") > 0);
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

	public int systemConfInsert(ShopPreferencesDto dto) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql = "insert into systemconf(compno, SUGGPROCNT,ORDPERIOD,ORDSTATUS, ORDIMPTPER, PRODMNG, PRODLINK,PRODFAIL, EXCEPKEYWDYN, EXCEPKEYWD, PRODNM_LENMNGYN, STSCPERIOD ) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, dto.getSuggprocnt());
			pstmt.setString(++rowIdx, dto.getOrdperiod());
			pstmt.setString(++rowIdx, dto.getOrdstatus());
			pstmt.setString(++rowIdx, dto.getOrdimptper());
			pstmt.setString(++rowIdx, dto.getProdmng());
			pstmt.setString(++rowIdx, dto.getProdlink());
			pstmt.setString(++rowIdx, dto.getProdfail());
			pstmt.setString(++rowIdx, dto.getExcepkeywdyn());
			pstmt.setString(++rowIdx, dto.getExcepkeywd() == null ? "" : dto.getExcepkeywd());
			pstmt.setString(++rowIdx, dto.getProdnm_lenmngyn());
			pstmt.setString(++rowIdx, dto.getStscperiod());
			
			System.out.println("[systemConfInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;

	}

	public int systemConfUpdate(ShopPreferencesDto dto) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql = " update systemconf set SUGGPROCNT = ?, ORDPERIOD = ?, ORDSTATUS = ?, ORDIMPTPER = ?, PRODMNG = ?, PRODLINK = ?, PRODFAIL = ?, EXCEPKEYWDYN = ?, EXCEPKEYWD = ?, PRODNM_LENMNGYN = ?, "
					+ " STSCPERIOD = ?  "
					+ " where compno = ? ";

			pstmt = connection.prepareStatement(sql.toUpperCase());

			int rowIdx = 0;
			pstmt.setString(++rowIdx, dto.getSuggprocnt());
			pstmt.setString(++rowIdx, dto.getOrdperiod());
			pstmt.setString(++rowIdx, dto.getOrdstatus());
			pstmt.setString(++rowIdx, dto.getOrdimptper());
			pstmt.setString(++rowIdx, dto.getProdmng());
			pstmt.setString(++rowIdx, dto.getProdlink());
			pstmt.setString(++rowIdx, dto.getProdfail());
			pstmt.setString(++rowIdx, dto.getExcepkeywdyn());
			pstmt.setString(++rowIdx, dto.getExcepkeywd());
			pstmt.setString(++rowIdx, dto.getProdnm_lenmngyn());
			pstmt.setString(++rowIdx, dto.getStscperiod());
			
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[systemConfUpdate]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public ShopPreferencesDto getSystemConf() throws Exception {
		ShopPreferencesDto dto = new ShopPreferencesDto();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(COMPNO,''), ifnull(SUGGPROCNT,''), ifnull(ORDPERIOD,''), ifnull(ORDSTATUS,''), ifnull(ORDIMPTPER,''), ifnull(PRODMNG,''), ifnull(PRODLINK,''), ifnull(PRODFAIL,'')"
					+ " , ifnull(EXCEPKEYWDYN,''), ifnull(EXCEPKEYWD,''), ifnull(PRODNM_LENMNGYN,''), ifnull(STSCPERIOD,'') from systemconf where compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getShopMstSelectList]" + pstmt.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int j = 0;
				dto.setCompno(rs.getString(++j));
				dto.setSuggprocnt(rs.getString(++j));
				dto.setOrdperiod(rs.getString(++j));
				dto.setOrdstatus(rs.getString(++j));
				dto.setOrdimptper(rs.getString(++j));
				dto.setProdmng(rs.getString(++j));
				dto.setProdlink(rs.getString(++j));
				dto.setProdfail(rs.getString(++j));
				dto.setExcepkeywdyn(rs.getString(++j));
				dto.setExcepkeywd(rs.getString(++j));
				dto.setProdnm_lenmngyn(rs.getString(++j));
				dto.setStscperiod(rs.getString(++j));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}
	public boolean isJobScheduler() {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "SELECT EXISTS(\r\n" + "	SELECT 1 FROM scheduinfo\r\n" + "	WHERE COMPNO= ?\r\n"
					+ "	AND  JOBSTAT ='Y'\r\n" + ")AS ISCHK";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);
			pstmt_orddtl.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[존재 유무]" + pstmt_orddtl.toString());

			rs = pstmt_orddtl.executeQuery();

			while (rs.next()) {
				result = (rs.getInt("ISCHK") > 0);
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

	public ShopOrderMstDto ShoppingMallDetailOneChoice(ShopOrderMstDto dtoItem) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(SUPPSTDITEM,'') AS SUPPSTDITEM, ifnull(SUPPSTDRATI,'0') AS SUPPSTDRATI from shopdtl where compno = ? and shopcd = ? and shoppingid = ? ";

			pstmt = connection.prepareStatement(sql.toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, dtoItem.getShopid());
			pstmt.setString(3, dtoItem.getShop_userid());
			System.out.println("[ShoppingMallDetailOneChoice]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dtoItem.setSuppstditem(rs.getString("SUPPSTDITEM"));
				dtoItem.setSuppstdrati(rs.getString("SUPPSTDRATI"));

				//list.add(dto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dtoItem;
	}

	public String getDeliveryName(String shopid, String delivery_id) throws Exception {
		String delvnm = "";
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select dlvnm from shopdeliverys where shopcd = ? and dlvid = ? ";

			pstmt = connection.prepareStatement(sql.toUpperCase());
			pstmt.setString(1, shopid);
			pstmt.setString(2, delivery_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				delvnm = rs.getString(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return delvnm;
	}
}

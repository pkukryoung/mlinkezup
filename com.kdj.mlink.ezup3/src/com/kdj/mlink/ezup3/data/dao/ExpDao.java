package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class ExpDao {

	public boolean checkExpInfo(Connection connection, List<PreparedStatement> pstmtList, String prodcd)
			throws Exception {

		boolean flag = false;
//		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM expprodmst where prodcd = ? and compno = ? ";// ProdMst와 조인하면 안됨.
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmtList.add(pstmt);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			rs = pstmt.executeQuery();

			System.out.println("[checkExpInfo]" + pstmt.toString());

			int i = 0;

			flag = rs.next();

		} finally {
			if (rs != null) {
				rs.close();
			}
		}

		return flag;
	}

	public List<List<String>> getProdcutListforExp(int opt, String optStr) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT  A.prodcd, "
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,A.prodcd) as img,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ "A.prodnm, ifnull(A.specdes,''),  ifnull(B.EXPOUTNM, ''), " + " ifnull(B.expcd,''), "
					+ " ifnull(B.EXPSUMQTY, 0), ifnull(B.EXPBUNDCD,''), ifnull(B.EXPINVQTY, 0), "
					+ " ifnull(B.EXPKINDNM,''), ifnull(B.EXPFILE,''), ifnull(B.EXPMINBUNQTY, 0), "
					+ " ifnull(B.flagrack,''), ifnull(B.rackconts,'0'), "
					+ " ifnull(B.expinner,''), ifnull(B.expcostnm,'0'), ifnull(B.EXPTABLEFLAG,'0') "
					+ "  FROM prodmst A LEFT join expprodmst B ON A.PRODCD = B.PRODCD and A.compno = B.compno where A.compno = ? ";

			if (opt == 0) {
				if (optStr.length() != 0) {
					sql += " and  A.prodcd like ? ";
				}
				sql += "  order by A.prodcd ";
			} else if (opt == 1) {
				if (optStr.length() != 0) {
					sql += " and  A.prodnm like ? ";
				}
				sql += "  order by A.prodnm ";
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			if (optStr.length() != 0) {
				pstmt.setString(2, "%" + optStr + "%");
			}

			System.out.println("[getProdcutListforExp]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add("" + (++rowno)); // ui grid 상의 순번 , 디비데이타 아님
				list.add(rs.getString(++i)); // prodcd
				list.add(rs.getString(++i)); // IMG
				list.add(rs.getString(++i)); // prodnm
				list.add(rs.getString(++i)); // specdes
				String expoutStr = rs.getString(++i);
				list.add(expoutStr.equals("1") ? "v" : ""); // EXPOUTNM
				list.add(rs.getString(++i)); //
				list.add(rs.getString(++i)); //
				list.add(rs.getString(++i)); //
				list.add(rs.getString(++i)); //
				list.add(rs.getString(++i)); //
				list.add(rs.getString(++i)); //
				list.add(rs.getString(++i)); //
				String flagrackStr = rs.getString(++i);
				list.add(flagrackStr.equals("1") ? "v" : ""); // flagrack
				list.add(rs.getString(++i)); // rackconts
				String expinnerStr = rs.getString(++i);
				String expcostnmStr = rs.getString(++i);
				list.add(expinnerStr.equals("1") ? "v" : ""); // expinner
				list.add(expcostnmStr.equals("1") ? "v" : ""); // expcostnm
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

	public List<String> getExpMstInfo(String prodcd) throws Exception {

		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT  A.prodcd, ifnull(A.prodnm,''), ifnull(A.specdes,''), ifnull(B.EXPOUTNM, ''), "
					+ "            ifnull(B.expcd,''),  ifnull(B.EXPSUMQTY, 0), ifnull(B.EXPBUNDCD,''), ifnull(B.EXPINVQTY, 0), "
					+ "           ifnull(B.EXPKINDNM,''), ifnull(B.EXPFILE, ''), ifnull(B.EXPMINBUNQTY, 0), "
					+ "           ifnull(B.flagrack,''), ifnull(B.rackconts, '0'), "
					+ "           ifnull(B.expinner,''), ifnull(B.expcostnm, '0'), ifnull(B.EXPTABLEFLAG,'0') "
					+ "    FROM prodmst A LEFT join expprodmst B ON A.PRODCD = B.PRODCD and A.compno = B.compno "
					+ "    Where A.prodcd = ? and A.compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getExpMstInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int i = 0;
			if (rs.next()) {
				list.add(rs.getString(++i)); // prodcd
				list.add(rs.getString(++i)); // prodnm
				list.add(rs.getString(++i)); // specdes
				String expoutStr = rs.getString(++i);
				list.add(expoutStr.equals("1") ? "v" : ""); // EXPOUTNM
				list.add(rs.getString(++i)); // expcd
				list.add(rs.getString(++i)); // EXPSUMQTY
				list.add(rs.getString(++i)); // EXPBUNDCD
				list.add(rs.getString(++i)); // EXPINVQTY
				list.add(rs.getString(++i)); // EXPKINDNM
				list.add(rs.getString(++i)); // EXPFILE
				list.add(rs.getString(++i)); // EXPMINBUNQTY
				String flagrackStr = rs.getString(++i);
				list.add(flagrackStr.equals("1") ? "v" : ""); // EXPOUTNM
				list.add(rs.getString(++i)); // EXPMINBUNQTY
				String expinnerStr = rs.getString(++i);
				String expcostnmStr = rs.getString(++i);
				list.add(expinnerStr.equals("1") ? "v" : "");
				list.add(expcostnmStr.equals("1") ? "v" : "");
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

	public boolean checkExpQtyInfo(Connection connection, List<PreparedStatement> pstmtList, String prodcd)
			throws Exception {

		boolean flag = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT prodcd FROM expordqtytab  where prodcd = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmtList.add(pstmt);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[checkExpQtyInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();
			flag = rs.next();

		} finally {
			if (rs != null) {
				rs.close();
			}
		}

		return flag;
	}

	public List<List<String>> getExpQtyInfo(String prodcd) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT ifnull(delivtabno, 0) FROM expordqtytab  where prodcd = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getExpQtyInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				// list.add("" + (++rowno)); // ui grid 상의 순번 , 디비데이타 아님
				// list.add(rs.getString(++i)); // ordqty
				list.add(rs.getString(++i)); // delivtabno

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

	public boolean checkExpCostInfo(Connection connection, List<PreparedStatement> pstmtList, String prodcd)
			throws Exception {

		boolean flag = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = " SELECT prodcd FROM expcost  where prodcd = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmtList.add(pstmt);

			System.out.println("[checkExpCostInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();
			flag = rs.next();

		} finally {
			if (rs != null) {
				rs.close();
			}
		}

		return flag;
	}

	public List<List<String>> getExpCostInfo(String prodcd) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT ifnull(DELIVAMT, 0) FROM expcost  where prodcd = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getExpCostInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				// list.add("" + (++rowno)); // ui grid 상의 순번 , 디비데이타 아님
				// list.add(rs.getString(++i)); // TABNO
				list.add(rs.getString(++i)); // DELIVAMT

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

	public int[] processUpdateExpInfo(String prodcd, List<String> contentsExpInfo, List<List<String>> contentsQty,
			List<List<String>> contentsCost) throws Exception {

		int[] result = null;
		Connection connection = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			if (!checkExpInfo(connection, statementlist, prodcd)) {
				insertExpInfo(connection, statementlist, prodcd, contentsExpInfo);
			} else {
				updateExpInfo(connection, statementlist, prodcd, contentsExpInfo);
			}
			// 체크 qty
			if (!checkExpQtyInfo(connection, statementlist, prodcd)) {
				insertQty(connection, statementlist, prodcd, contentsQty);
			} else {
				updateQty(connection, statementlist, prodcd, contentsQty);
			}
			// 체크 Cost
			if (!checkExpCostInfo(connection, statementlist, prodcd)) {
				insertCost(connection, statementlist, prodcd, contentsCost);
			} else {
				updateCost(connection, statementlist, prodcd, contentsCost);
			}

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

	public void updateExpInfo(Connection connection, List<PreparedStatement> statementlist, String prodcd,
			List<String> contentsExpInfo) throws Exception {

		String sql = "update expprodmst set EXPCD=?, EXPSUMQTY=?, EXPBUNDCD=? ";
		sql += " , EXPINVQTY=?, EXPKINDNM=? , EXPFILE=?, EXPMINBUNQTY=?, EXPOUTNM=? ";
		sql += " , flagrack=?, rackconts=? ";
		sql += " , expinner=?, expcostnm=? , exptableflag = ? ";
		sql += " , modifydt=?, modifyid=? ";
		sql += " where prodcd=? and compno = ? ";

		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		System.out.println("[updateExpInfo]" + pstmt.toString());

		int i = 0;
		int index = 0;
		pstmt.setString(++i, contentsExpInfo.get(index++));
		pstmt.setString(++i, YDMAStringUtil.replaceEmptyStringToNull(contentsExpInfo.get(index++)));// EXPSUMQTY
		pstmt.setString(++i, contentsExpInfo.get(index++));
		pstmt.setString(++i, YDMAStringUtil.replaceEmptyStringToNull(contentsExpInfo.get(index++)));// EXPINVQTY
		pstmt.setString(++i, contentsExpInfo.get(index++));
		pstmt.setString(++i, contentsExpInfo.get(index++));
		pstmt.setString(++i, YDMAStringUtil.replaceEmptyStringToNull(contentsExpInfo.get(index++)));// EXPMINBUNQTY
		pstmt.setString(++i, contentsExpInfo.get(index++)); // EXPOUTNM

		pstmt.setString(++i, contentsExpInfo.get(index++)); // flagrack
		pstmt.setString(++i, contentsExpInfo.get(index++)); // rackconts
		pstmt.setString(++i, contentsExpInfo.get(index++)); // expinner
		pstmt.setString(++i, contentsExpInfo.get(index++)); // expcostnm
		pstmt.setString(++i, contentsExpInfo.get(index++)); // exptableflag

		pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUserId());
		pstmt.setString(++i, prodcd);
		pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.executeUpdate();
	}

	public void updateQty(Connection connection, List<PreparedStatement> pstmtList, String prodcd,
			List<List<String>> contentsExpInfo) throws Exception {

		// contentsQty -- 주문수량/테이블 번호 순
		String sql = "update EXPORDQTYTAB set DELIVTABNO =?  where PRODCD=? and ORDQTY=? and compno = ? ";
		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmtList.add(pstmt);

		System.out.println("[updateQty]" + pstmt.toString());

		for (List<String> list : contentsExpInfo) {
			pstmt.setString(1, YDMAStringUtil.replaceEmptyStringToNull(list.get(1))); // DELIVTABNO
			pstmt.setString(2, prodcd);
			pstmt.setString(3, YDMAStringUtil.replaceEmptyStringToNull(list.get(0))); // ORDQTY
			pstmt.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

	}

	public void updateCost(Connection connection, List<PreparedStatement> pstmtList, String prodcd,
			List<List<String>> contentsCost) throws Exception {
		// contentsCost - 테이블 번호, 택배비
		String sql = "update EXPCOST set DELIVAMT=?  where PRODCD=? and TABNO=? and compno = ? ";
		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmtList.add(pstmt);
		System.out.println("[updateCost]" + pstmt.toString());
		for (List<String> list : contentsCost) {
			pstmt.setString(1, YDMAStringUtil.replaceEmptyStringToNull(list.get(1))); // DELIVAMT
			pstmt.setString(2, prodcd);
			pstmt.setString(3, YDMAStringUtil.replaceEmptyStringToNull(list.get(0))); // TABNO
			pstmt.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화
	}

	public void insertExpInfo(Connection connection, List<PreparedStatement> statementlist, String prodcd,
			List<String> contentsExpInfo) throws Exception {

		String sql = "insert expprodmst (compno, PRODCD, EXPCD, EXPSUMQTY, EXPBUNDCD ";
		sql += " , EXPINVQTY, EXPKINDNM , EXPFILE, EXPMINBUNQTY , EXPOUTNM ";
		sql += " , flagrack, rackconts, expinner, expcostnm, exptableflag ";
		sql += " , insertdt, insertid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		int idx = 0;
		int i = 0;
		pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setString(++idx, prodcd);
		pstmt.setString(++idx, contentsExpInfo.get(i++));
		pstmt.setString(++idx, YDMAStringUtil.replaceEmptyStringToNull(contentsExpInfo.get(i++)));
		pstmt.setString(++idx, contentsExpInfo.get(i++));
		pstmt.setString(++idx, YDMAStringUtil.replaceEmptyStringToNull(contentsExpInfo.get(i++)));
		pstmt.setString(++idx, contentsExpInfo.get(i++));
		pstmt.setString(++idx, contentsExpInfo.get(i++));
		pstmt.setString(++idx, YDMAStringUtil.replaceEmptyStringToNull(contentsExpInfo.get(i++)));
		pstmt.setString(++idx, contentsExpInfo.get(i++)); // expout

		pstmt.setString(++idx, contentsExpInfo.get(i++)); // flagrack
		pstmt.setString(++idx, contentsExpInfo.get(i++)); // rackconts
		pstmt.setString(++idx, contentsExpInfo.get(i++)); // expinner
		pstmt.setString(++idx, contentsExpInfo.get(i++)); // expcostnm
		pstmt.setString(++idx, contentsExpInfo.get(i++)); // exptableflag

		pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());

		System.out.println("[insertExpInfo]" + pstmt.toString());

		pstmt.executeUpdate();

	}

	public void insertQty(Connection connection, List<PreparedStatement> pstmtList, String prodcd,
			List<List<String>> contentsExpInfo) throws Exception {
		// contentsQty 주문수량/테이블번호 순임
		String sql = "insert EXPORDQTYTAB (compno, PRODCD, ORDQTY, DELIVTABNO)  values (?, ?, ?, ?) ";
		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmtList.add(pstmt);

		System.out.println("[insertQty]" + pstmt.toString());

		for (List<String> list : contentsExpInfo) {
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, prodcd);
			pstmt.setString(3, YDMAStringUtil.replaceEmptyStringToNull(list.get(0))); // ORDQTY
			pstmt.setString(4, YDMAStringUtil.replaceEmptyStringToNull(list.get(1))); // DELIVTABNO
			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

	}

	public void insertCost(Connection connection, List<PreparedStatement> pstmtList, String prodcd,
			List<List<String>> contentsCost) throws Exception {
		// contentsCost - 테이블 번호, 택배비
		String sql = "insert EXPCOST (compno, PRODCD, TABNO, DELIVAMT) values (?, ?, ?, ?) ";
		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmtList.add(pstmt);

		System.out.println("[insertCost]" + pstmt.toString());

		for (List<String> list : contentsCost) {
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, prodcd);
			pstmt.setString(3, YDMAStringUtil.replaceEmptyStringToNull(list.get(0))); // TABNO
			pstmt.setString(4, YDMAStringUtil.replaceEmptyStringToNull(list.get(1))); // DELIVAMT
			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화
	}

	public void setExpSiteInsertNdelete(Connection connection, List<PreparedStatement> statementlist, String prodcd,
			List<String> shopseq, List<String> shopcd) throws Exception {
		PreparedStatement pstmt = null;

		String sql = "insert into expsite (COMPNO,PRODCD,SHOPCD,SHOPSEQ) values(?, ?, ?, ?)";
		sql = sql.toUpperCase();
		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		for (int k = 0; k < shopseq.size(); k++) {
			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, prodcd);
			pstmt.setString(++i, shopcd.get(k));
			pstmt.setString(++i, shopseq.get(k));
			System.out.println("[deleteOrder]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

	}

	// 있는지없는지 유무
	private boolean isExistExpress(Connection connection, List<PreparedStatement> statementlist, String prodcd)
			throws Exception {
		boolean flag = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select PRODCD from expsite where COMPNO = ? and PRODCD = ? ";
		sql = sql.toUpperCase();

		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setString(2, prodcd);
		System.out.println("[isExistExpress]" + pstmt.toString());

		rs = pstmt.executeQuery();
		flag = rs.next();

		return flag;
	}

	// 삭제하기
	private void scheduSiteDelete(Connection connection, List<PreparedStatement> statementlist, String prodcd)
			throws Exception {

		String sql = "delete from expsite where COMPNO = ? and PRODCD = ? ";
		sql = sql.toUpperCase();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setString(2, prodcd);

		pstmt.executeUpdate();

	}

	public void setExpressSite(String prodcd, List<String> contentsExpInfo, List<List<String>> contentsQty,
			List<List<String>> contentsCost, List<String> shopseq, List<String> shopcd) throws Exception {

		int[] result = null;
		Connection connection = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			if (contentsExpInfo.get(13).equals("1")) {
				if (isExistExpress(connection, statementlist, prodcd)) {
					scheduSiteDelete(connection, statementlist, prodcd);
					setExpSiteInsertNdelete(connection, statementlist, prodcd, shopseq, shopcd);
				} else {
					setExpSiteInsertNdelete(connection, statementlist, prodcd, shopseq, shopcd);
				}
			}

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	// expsite 정보가지고 오기
	public List<List<String>> getExpSite(String prodcd) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT compno,prodcd,shopcd,shopseq FROM expsite  where prodcd = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getExpCostInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
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
}

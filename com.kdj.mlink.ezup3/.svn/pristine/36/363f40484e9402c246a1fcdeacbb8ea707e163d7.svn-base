package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class HousingDao {

	public void refreshWarehouseProductList() throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		PreparedStatement pstmt_sum = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			pstmt_sum = connection.prepareCall("delete from SUM_PRODWAREQTY");
			pstmt_sum.execute();

			pstmt = connection.prepareCall("{call YWM_PRODWAREQTY_LIST_REFRESH(?)}");
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			rs = pstmt.executeQuery();

			String sql_orddtl = " insert into SUM_PRODWAREQTY values(?, ?,?,?,?,?,?,?,?) ";
			pstmt_orddtl = connection.prepareStatement(sql_orddtl);
			statementlist.add(pstmt_orddtl);

			while (rs.next()) {
				int rowIdx = 0;
				pstmt_orddtl.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt_orddtl.setString(++rowIdx, rs.getString(1));
				pstmt_orddtl.setString(++rowIdx, rs.getString(4));
				pstmt_orddtl.setString(++rowIdx, rs.getString(5));
				pstmt_orddtl.setString(++rowIdx, rs.getString(6));
				pstmt_orddtl.setString(++rowIdx, rs.getString(7));
				pstmt_orddtl.setString(++rowIdx, rs.getString(8));
				pstmt_orddtl.setString(++rowIdx, rs.getString(9));
				pstmt_orddtl.setString(++rowIdx, rs.getString(10));

				pstmt_orddtl.addBatch();
				pstmt_orddtl.clearParameters(); // 파라미터 초기화

			}

			pstmt_orddtl.executeBatch();
			pstmt_orddtl.clearParameters(); // Batch 초기화

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public List<List<String>> getSaleWareQtyList(String strdt) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			connection = DBCPInit.getInstance().getConnection();

			pstmt = connection.prepareCall("{call YWM_EXCEL_ORDER(?,?)}");
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, strdt);

			rs = pstmt.executeQuery();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				List<String> list = new ArrayList<>();
				list.add(String.valueOf(rowNum++)); // UI NO.
				list.add(rs.getString(++columnIndex)); // prodcd
				list.add(rs.getString(++columnIndex)); // prodnm
				list.add(rs.getString(++columnIndex)); // specdes
				list.add(rs.getString(++columnIndex)); // sale3qty
				list.add(rs.getString(++columnIndex)); // sale25qty
				list.add(rs.getString(++columnIndex)); // sale2qty
				list.add(rs.getString(++columnIndex)); // sale15qty
				list.add(rs.getString(++columnIndex)); // saleAvqty
				list.add(rs.getString(++columnIndex)); // qty
				list.add(rs.getString(++columnIndex)); // befqty
				list.add(rs.getString(++columnIndex)); // 반품미확인
				list.add(rs.getString(++columnIndex)); // warqty

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

	public List<List<String>> getWarehouseProductList(int opt, String optStr) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			connection = DBCPInit.getInstance().getConnection();

			pstmt = connection.prepareCall("{call YWM_PRODWAREQTY_LIST_WSUM(?, ?,?)}");
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setInt(2, opt);
			pstmt.setString(3, "%" + optStr + "%");

			rs = pstmt.executeQuery();

			contents = new ArrayList<>();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				List<String> list = new ArrayList<>();
				list.add(String.valueOf(rowNum++)); // UI NO.
				list.add(rs.getString(++columnIndex)); // prodcd
				list.add(rs.getString(++columnIndex)); // img
				list.add(rs.getString(++columnIndex)); // prodnm
				list.add(rs.getString(++columnIndex)); // specdes
				list.add(rs.getString(++columnIndex)); // baseqty
				list.add(rs.getString(++columnIndex)); // recvqty
				list.add(rs.getString(++columnIndex)); // saleqty
				list.add(rs.getString(++columnIndex)); // inwareqty
				list.add(rs.getString(++columnIndex)); // befqty
				list.add(rs.getString(++columnIndex)); // day3befqty
				list.add(rs.getString(++columnIndex)); // retnqty
				list.add(rs.getString(++columnIndex)); // warqty
				list.add(rs.getString(++columnIndex)); //
				list.add(rs.getString(++columnIndex)); //
				list.add(rs.getString(++columnIndex)); //
				list.add(rs.getString(++columnIndex)); //
				list.add(rs.getString(++columnIndex)); //

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

	public List<List<String>> getInventoryDetail(String prodcd, String sDate, String eDate) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			connection = DBCPInit.getInstance().getConnection();

			pstmt = connection.prepareCall("{call YWM_INVENTORY_DETAIL(?, ?,?,?)}");
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, prodcd);
			pstmt.setString(3, sDate);
			pstmt.setString(4, eDate);

			rs = pstmt.executeQuery();

			contents = new ArrayList<>();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				List<String> list = new ArrayList<>();
				list.add(String.valueOf(rowNum++)); // UI NO.
				list.add(rs.getString(++columnIndex)); // 일자
				list.add(rs.getString(++columnIndex)); // 기초재고
				list.add(rs.getString(++columnIndex)); // 입고수량
				list.add(rs.getString(++columnIndex)); // 판매수량
				list.add(rs.getString(++columnIndex)); // 창고재고

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

	public List<List<String>> getWarePeriodProdList(String bDate, String sDate, String eDate, int opt, String optStr)
			throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			connection = DBCPInit.getInstance().getConnection();

			pstmt = connection.prepareCall("{call YWM_PERIOD_PRODWARE_LIST(?,?,?,?,?,?)}");
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, bDate);
			pstmt.setString(3, sDate);
			pstmt.setString(4, eDate);
			pstmt.setInt(5, opt);
			pstmt.setString(6, "%" + optStr + "%");

			rs = pstmt.executeQuery();

			contents = new ArrayList<>();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				List<String> list = new ArrayList<>();
				list.add(String.valueOf(rowNum++)); // UI NO.
				list.add(rs.getString(++columnIndex)); // prodcd
				list.add(rs.getString(++columnIndex)); // image
				list.add(rs.getString(++columnIndex)); // prodnm
				list.add(rs.getString(++columnIndex)); // specdes
				list.add(rs.getString(++columnIndex)); // baseqty
				list.add(rs.getString(++columnIndex)); // recvqty
				list.add(rs.getString(++columnIndex)); // saleqty
				list.add(rs.getString(++columnIndex)); // inwareqty
				list.add(rs.getString(++columnIndex)); // befqty
				list.add(rs.getString(++columnIndex)); // day3befqty
				list.add(rs.getString(++columnIndex)); // retnqty
				list.add(rs.getString(++columnIndex)); // warqty

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

	public List<List<String>> getRecvMstByOption(String recvdtFrom, String recvdtTo, String recvgubun, String extra,
			String optStr) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "Select recvgubun, recvdt, recvseq, ifnull(recvdesc,''), ifnull(befqty,0), ifnull(realdt,''), ifnull(aftqty,0), "
					+ " ifnull(confnam,'') , ifnull(contno,'') , ifnull(autochk, '') " + " From recvmst "
					+ " where compno = ? and recvdt between ? and ? ";

			if (recvgubun.trim().length() != 0) {
				sql += "	and recvgubun=? ";
			}

			if (extra.trim().equals("확정")) {
				sql += "	and ifnull(aftqty,0) <> 0 and ifnull(realdt,'') <>'' and ifnull(confnam,'')<>''";
			} else if (extra.trim().equals("예정")) {
				// sql += " and ifnull(aftqty,0) = 0 ";
			}

			if (optStr.length() != 0) {
				sql += " and recvdesc like ?";
			}

			sql += " order by recvdt desc, recvseq desc ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, recvdtFrom);
			pstmt.setString(3, recvdtTo);

			if (recvgubun.trim().length() != 0) {
				pstmt.setString(4, recvgubun);
			}
			if (optStr.length() != 0) {
				pstmt.setString(5, "%" + optStr + "%");
			}

			System.out.println("[getRecvMstByOption]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				List<String> list = new ArrayList<>();
				list.add(String.valueOf(rowNum++)); // UI NO.
				list.add(rs.getString(++columnIndex));// recvgubun
				list.add(rs.getString(++columnIndex)); // recvdt
				list.add(rs.getString(++columnIndex));// recvseq ---
				list.add(rs.getString(++columnIndex)); // recvdesc
				list.add(rs.getString(++columnIndex)); // befqty
				list.add(rs.getString(++columnIndex)); // realdt
				list.add(rs.getString(++columnIndex)); // aftqty
				list.add(rs.getString(++columnIndex)); // confnam
				list.add(rs.getString(++columnIndex)); // contno
				list.add(rs.getString(++columnIndex));// AUTOCHK---
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

	public List<String> getRecvMstInfo(String recvdt, String recvseq) throws Exception {

		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "Select A.recvgubun, A.recvdt,  ifnull(A.befqty,0), ifnull(A.realdt,''), ifnull(A.aftqty,0), "
					+ " ifnull(A.confnam,'') , ifnull(A.contno,'') " + ",  A.recvdt, A.recvseq " // key
					+ ", ifnull(AUTOCHK,'') " + " From recvmst A " + " where recvdt=? and recvseq=? and A.compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, recvdt);
			pstmt.setString(2, recvseq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			// pstmt.setString(4, ordidTo);

			System.out.println("[getRecvMstInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));// recvgubun
				list.add(rs.getString(++columnIndex)); // recvdt
				list.add(rs.getString(++columnIndex)); // befqty
				list.add(rs.getString(++columnIndex)); // realdt
				list.add(rs.getString(++columnIndex)); // aftqty
				list.add(rs.getString(++columnIndex)); // confnam
				list.add(rs.getString(++columnIndex)); // contno
				list.add(rs.getString(++columnIndex));// recvdt
				list.add(rs.getString(++columnIndex));// recvseq
				list.add(rs.getString(++columnIndex));// AUTOCHK
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<List<String>> getRecvDtl(String recvdt, String recvseq) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "Select A.seq,  A.prodcd, "
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,A.prodcd) as img,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ "ifnull(B.prodnm,''), ifnull(B.specdes,''), "
					+ " ifnull(A.befqty,0), ifnull(A.aftqty, 0), ifnull(A.sparerack1,''),ifnull(A.sparerack2,''),ifnull(A.sparerack3,''),ifnull(A.sparerack4,''),ifnull(A.sparerack5,''), ifnull(A.remark,'') , "
					+ " ifnull(A.realdt, ''), ifnull(A.plandt,'') From recvdtl A Left Join v_products B on A.prodcd = B.prodcd and A.compno = B.compno "
					+ " where A.compno = ? and A.recvdt=? and A.recvseq=? " + " ORDER BY seq ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, recvdt);
			pstmt.setString(3, recvseq);

			System.out.println("[getRecvDtl]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				List<String> list = new ArrayList<>();
				// list.add(String.valueOf(rowNum++)); // UI NO.
				list.add(rs.getString(++columnIndex));// seq
				list.add(rs.getString(++columnIndex));// prodcd
				list.add(rs.getString(++columnIndex));// img
				list.add(rs.getString(++columnIndex)); // prodmn
				list.add(rs.getString(++columnIndex)); // specdes
				list.add(rs.getString(++columnIndex)); // befqty
				list.add(rs.getString(++columnIndex).equals("0") ? "" : rs.getString(columnIndex)); // aftqty
				list.add(rs.getString(++columnIndex)); //
				list.add(rs.getString(++columnIndex)); //
				list.add(rs.getString(++columnIndex)); //
				list.add(rs.getString(++columnIndex)); //
				list.add(rs.getString(++columnIndex)); //
				list.add(rs.getString(++columnIndex)); // remark
				list.add("T"); // 콘테이너에서 불러온 경우에 상품코드 유효성 플래그추가함으로 여기서도 똑같은 위치에 해준다.
				list.add(rs.getString(++columnIndex)); // realdt
				list.add(rs.getString(++columnIndex)); // plandt

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

	public List<List<String>> getRecvDtl_AftqtyNozero(String recvdt, String recvseq, String gubun, String aftqty)
			throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "Select recvdt,  A.seq, " + "  '', '', '', '', '', '', '' , "
					+ "  ifnull(B.ecountcd,''), ifnull(B.prodnm,''), ifnull(B.specdes,''), " + "  ifnull(A.aftqty,0),  "
					+ "  '', '', '', '', '', ''   "
					+ " From recvdtl A Left Join prodmst B on A.prodcd = B.prodcd and A.compno = B.compno "
					+ " where A.compno = ? and A.recvdt=? and A.recvseq=?  and ifnull(A.aftqty,0) > 0"
					+ " ORDER BY seq ";

//			{ { "일자", "65" }, { "순번", "150" },
//			{ "거래처코드", "160" }, { "거래처명", "400" },	{ "담당자", "80" }, { "입고창고", "160" }, { "거래유형", "80" }, { "통화", "100" }, { "환율 ", "200" },
//			{ "품목코드", "65" }, { "품목명", "150" }, { "규격명", "160" },
//		    { "수량", "400" },
//			{ "단가", "80" },   { "외화금액", "160" }, { "공급가액", "80" }, { "부가세", "100" }, { "적요 No.", "200" }, { "부대비용", "200" } };

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, recvdt);
			pstmt.setString(3, recvseq);

			System.out.println("[getRecvDtl_AftqtyNozero]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				List<String> list = new ArrayList<>();
				// list.add(String.valueOf(rowNum++)); // UI NO.
				list.add(rs.getString(++columnIndex)); // recvdt
				list.add(rs.getString(++columnIndex)); // seq

				list.add(rs.getString(++columnIndex)); // 거래처코드
				list.add(rs.getString(++columnIndex)); // 거래처명
				list.add(rs.getString(++columnIndex)); // 담당자
				list.add(rs.getString(++columnIndex)); // 입고창고
				list.add(rs.getString(++columnIndex)); // 거래유형
				list.add(rs.getString(++columnIndex)); // 통화
				list.add(rs.getString(++columnIndex)); // 환율

				list.add(rs.getString(++columnIndex));// 품목코드 ecountcd
				list.add(rs.getString(++columnIndex)); // 품목명 prodmn
				list.add(rs.getString(++columnIndex)); // 규격명 specdes

				list.add(rs.getString(++columnIndex)); // 수량 aftqty

				list.add(rs.getString(++columnIndex)); // 단가
				list.add(rs.getString(++columnIndex)); // 외화금액
				list.add(rs.getString(++columnIndex)); // 공급가액
				list.add(rs.getString(++columnIndex)); // 부가세
				list.add(rs.getString(++columnIndex)); // 적요
				list.add(rs.getString(++columnIndex)); // 부대비용

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

	public void processRecvInsert(List<List<String>> contents, String recvdt, String gubun, String recvdesc,
			int beforeQty, int afterQty, String confnam, String contno, String realdt, String plandt) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);
			int recvseq = getMaxRecvSeq(connection, statementlist);
			insertRecvMst(connection, statementlist, contents, recvdt, recvseq, gubun, recvdesc, beforeQty, afterQty,
					confnam, contno, realdt);
			insertRecvDtl(connection, statementlist, contents, recvdt, recvseq, realdt, plandt);

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public int get3DayInventoryCnt(String strDate, String prodcd) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT YWM_FUNC_TOTAL_WEEKDAYS(?,min(A.plandt))" + "  FROM RECVDTL A, RECVMST B "
					+ " WHERE ifnull(A.befqty,0) <> 0" + "   AND ifnull(A.aftqty,0) = 0" + "   AND A.RECVDT =B.RECVDT"
					+ "   AND A.RECVSEQ=B.RECVSEQ" + "   AND B.RECVGUBUN <> '반품'" + "   AND A.plandt >= ?"
					+ "   AND A.plandt <= YWM_FUNC_WORKING_DAY(?,3) " + "   AND A.prodcd = ? and A.compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, strDate);
			pstmt.setString(2, strDate);
			pstmt.setString(3, strDate);
			pstmt.setString(4, prodcd);
			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[get3DayInventoryCnt]" + pstmt.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);

			} else {
				result = 0;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public int getMaxRecvSeq(Connection connection, List<PreparedStatement> statementlist) throws Exception {

		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT ifnull(MAX(RECVSEQ), 0)+1 FROM RECVMST WHERE RECVDT=? and compno = ? "; // '20190406'
		sql = sql.toUpperCase();
		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		pstmt.setString(1, YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd"));
		pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
		System.out.println("[getMaxRecvSeq]" + pstmt.toString());

		rs = pstmt.executeQuery();

		if (rs.next()) {
			result = rs.getInt(1);
		} else {
			result = 1;
		}

		return result;
	}

	public void insertRecvMst(Connection connection, List<PreparedStatement> statementlist, List<List<String>> contents,
			String recvdt, int recvseq, String gubun, String recvdesc, int befqty, int aftqty, String confnam,
			String contno, String realdt) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql_orddtl = " insert into recvmst (compno, recvdt, recvseq, entdt, recvgubun, recvdesc, befqty, aftqty, confnam, contno, realdt, INSERTDT, INSERTID)  "
				+ " VALUES (?, ?, ?, ? , ? , ? , ? , ? , ?, ?, ?, ?, ?)";

		sql_orddtl = sql_orddtl.toUpperCase();

		pstmt = connection.prepareStatement(sql_orddtl);
		statementlist.add(pstmt);

		int rowIdx = 0;
		pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setString(++rowIdx, recvdt);// recvdt
		pstmt.setInt(++rowIdx, recvseq);
		pstmt.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMddHHmm"));// entdt
		pstmt.setString(++rowIdx, gubun);// gubun
		pstmt.setString(++rowIdx, recvdesc);// recvdesc
		pstmt.setInt(++rowIdx, befqty);// befqty
		pstmt.setInt(++rowIdx, aftqty);// aftqty
		pstmt.setString(++rowIdx, confnam);// confnam
		pstmt.setString(++rowIdx, contno);// contno
		pstmt.setString(++rowIdx, realdt);// realdt
		pstmt.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId());

		System.out.println("[insertRecvMst]" + pstmt.toString());

		pstmt.execute();
	}

	public int insertRecvDtl(Connection connection, List<PreparedStatement> statementlist, List<List<String>> contents,
			String recvdt, int recvseq, String realdt, String plandt) throws Exception {

		int result = 0;
		PreparedStatement pstmt = null;

		String sql_orddtl = " insert into recvdtl ("
				+ " compno, recvdt, recvseq,  seq, prodcd, befqty, AFTQTY, realdt, remark, plandt,sparerack1,sparerack2,sparerack3,sparerack4,sparerack5, INSERTDT, INSERTID)  VALUES ( "
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";

		sql_orddtl = sql_orddtl.toUpperCase();

		pstmt = connection.prepareStatement(sql_orddtl);
		statementlist.add(pstmt);

		for (List<String> claim : contents) {
			// [1, YWC2080-BK, 로니의자, 블랙, 180, 0, 잘나간다, T]
			int rowIdx = 0;
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, recvdt);// recvdt
			pstmt.setInt(++rowIdx, recvseq);// recvseq
			pstmt.setInt(++rowIdx, Integer.parseInt(claim.get(0)));// seq
			pstmt.setString(++rowIdx, claim.get(1));// prodcd
			pstmt.setInt(++rowIdx, Integer.parseInt(claim.get(5)));// befqty
			pstmt.setInt(++rowIdx, Integer.parseInt(claim.get(6)));// AFTQTY
			pstmt.setString(++rowIdx, realdt); // real date
			pstmt.setString(++rowIdx, claim.get(12));// remark
			pstmt.setString(++rowIdx, plandt);// plandt
			pstmt.setString(++rowIdx, claim.get(7));
			pstmt.setString(++rowIdx, claim.get(8));
			pstmt.setString(++rowIdx, claim.get(9));//
			pstmt.setString(++rowIdx, claim.get(10));//
			pstmt.setString(++rowIdx, claim.get(11));//
			pstmt.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());//
			pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId());//

			System.out.println("[insertRecvDtl]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화
		}

		pstmt.executeBatch();
		pstmt.clearParameters(); // Batch 초기화

		return result;
	}

	public List<List<String>> getHousingListByOption(int opt, String optStr) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT ifnull(A.recvdt,''), ifnull(A.recvseq,''), ifnull(A.recvgubun,''), ifnull(A.entdt,''), "
					+ " ifnull(A.recvdesc,''), ifnull(A.befqty,''),  ifnull(A.realdt,''), ifnull(A.aftqty,''), "
					+ " ifnull(A.confnam,''), ifnull(A.contno,''), " + " ifnull(A.autochk,'') "
					+ " FROM recvmst A where A.compno = ? ";
			// + " WHERE A.clmdt >= ? ANd A.clmdt <= ? ";
			// + " ORDER BY A.clmdt DESC, A.regdt DESC, A.ordid DESC";

			// 성명, 구매일, 상품명
			if (opt == 0) {
				if (optStr.length() != 0) {
					sql += " and  A.rcvnam like ? ";
				}
				sql += " order by A.rcvnam ";
			} else if (opt == 1) {

			} else if (opt == 2) {
				if (optStr.length() != 0) {
					sql += " and B.prodnm like ? ";
				}
				sql += "  order by B.prodnm ";
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			if (optStr.length() != 0) {
				pstmt.setString(2, "%" + optStr + "%");
			}

			System.out.println("[getHousingListByOption]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				List<String> list = new ArrayList<>();
				list.add(String.valueOf(rowNum++)); // UI NO.
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

	public void processRecvUpdate(List<List<String>> contents, String recvdt, int recvseq, String gubun,
			String recvdesc, int beforeQty, int afterQty, String confnam, String contno, String realdt, String plandt)
			throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			updateRecvMst(connection, statementlist, contents, recvdt, recvseq, gubun, recvdesc, beforeQty, afterQty,
					confnam, contno, realdt);
			deleteRecvDtl(connection, statementlist, recvdt, recvseq);
			insertUpdateRecvDtl(connection, statementlist, contents, recvdt, recvseq, realdt, plandt);

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public void updateRecvMst(Connection connection, List<PreparedStatement> statementlist, List<List<String>> contents,
			String recvdt, int recvseq, String gubun, String recvdesc, int befqty, int aftQty, String confnam,
			String contno, String realdt) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql_orddtl = "update recvmst set entdt=?, recvgubun=?, recvdesc=?, befqty=?, aftQty=?, confnam=?, contno=?, realdt=?,"
				+ " UPDATEDT=?, UPDATEID=? " + " where recvdt=? and recvseq=? and compno = ? ";

		sql_orddtl = sql_orddtl.toUpperCase();

		pstmt = connection.prepareStatement(sql_orddtl);
		statementlist.add(pstmt);

		int rowIdx = 0;

		pstmt.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMddHHmm"));// entdt
		pstmt.setString(++rowIdx, gubun);
		pstmt.setString(++rowIdx, recvdesc);
		pstmt.setInt(++rowIdx, befqty);
		pstmt.setInt(++rowIdx, aftQty);
		pstmt.setString(++rowIdx, confnam);
		pstmt.setString(++rowIdx, contno);
		pstmt.setString(++rowIdx, realdt);
		pstmt.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId());
		pstmt.setString(++rowIdx, recvdt);
		pstmt.setInt(++rowIdx, recvseq);
		pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
		System.out.println("[updateRecvMst]" + pstmt.toString());

		pstmt.execute();
	}

	public int insertUpdateRecvDtl(Connection connection, List<PreparedStatement> statementlist,
			List<List<String>> contents, String recvdt, int recvseq, String realdt, String plandt) throws Exception {

		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql_orddtl = " insert into recvdtl ("
				+ " compno ,recvdt, recvseq,  seq, prodcd, befqty, AFTQTY, remark, plandt, realdt,sparerack1,sparerack2,sparerack3,sparerack4,sparerack5, UPDATEDT, UPDATEID)  VALUES ( "
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		sql_orddtl = sql_orddtl.toUpperCase();

		pstmt = connection.prepareStatement(sql_orddtl);
		statementlist.add(pstmt);

		for (List<String> claim : contents) {
			// [1, YWC2080-BK, 로니의자, 블랙, 180, 0, 잘나간다, T]
			int rowIdx = 0;
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, recvdt);// recvdt
			pstmt.setInt(++rowIdx, recvseq);// recvseq
			pstmt.setInt(++rowIdx, Integer.parseInt(claim.get(0)));// seq
			pstmt.setString(++rowIdx, claim.get(1));// prodcd
			pstmt.setInt(++rowIdx, Integer.parseInt(claim.get(5)));// befqty
			pstmt.setInt(++rowIdx, Integer.parseInt(claim.get(6)));// AFTQTY
			pstmt.setString(++rowIdx, claim.get(12));// remark
			pstmt.setString(++rowIdx, plandt);// plandt
			pstmt.setString(++rowIdx, realdt);// realdt
			pstmt.setString(++rowIdx, claim.get(7));// remark
			pstmt.setString(++rowIdx, claim.get(8));// remark
			pstmt.setString(++rowIdx, claim.get(9));// remark
			pstmt.setString(++rowIdx, claim.get(10));// remark
			pstmt.setString(++rowIdx, claim.get(11));// remark
			pstmt.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());//
			pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId());//

			System.out.println("[insertUpdateRecvDtl]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화
		}

		pstmt.executeBatch();
		pstmt.clearParameters(); // Batch 초기화

		return result;
	}

	public void deleteRecvDtl(Connection connection, List<PreparedStatement> statementlist, String recvdt, int recvseq)
			throws Exception {

		String sql = " delete from recvdtl where recvdt=? and recvseq=? and compno = ? ";
		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		pstmt.setString(1, recvdt);// recvdt
		pstmt.setInt(2, recvseq);// recvseq
		pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
		System.out.println("[deleteRecvDtl]" + pstmt.toString());

		pstmt.execute();

	}

	public void deleteRecvMst(Connection connection, List<PreparedStatement> statementlist, String recvdt, int recvseq)
			throws Exception {

		String sql = " delete from recvmst where recvdt=? and recvseq=? and compno = ? ";
		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		pstmt.setString(1, recvdt);// recvdt
		pstmt.setInt(2, recvseq);// recvseq
		pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
		System.out.println("[deleteRecvMst]" + pstmt.toString());

		pstmt.execute();

	}

	public void updateBasstkMstFromRecvDel(Connection connection, List<PreparedStatement> statementlist, String recvdt,
			int recvseq) throws Exception {
		// update basstkmst set recvdt=null,recvseq=null where recvdt='그리드 선택된 입고일자' and
		// recvseq='그리드 선택된 일련번호
		String sql = "update basstkmst set recvdt=null, recvseq=null where recvdt=? and recvseq=? and compno = ? ";
		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		pstmt.setString(1, recvdt);
		pstmt.setInt(2, recvseq);
		pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
		System.out.println("[updateBasstkMstFromRecvDel]" + pstmt.toString());

		pstmt.executeUpdate();

	}

	public void updateClaimFromRecvDel(Connection connection, List<PreparedStatement> statementlist, String recvdt,
			int recvseq) throws Exception {

		// update clmlist set recvdt=null,recvseq=null where recvdt='그리드 선택된 입고일자' and
		// recvseq='그리드 선택된 일련번호'

		String sql = "update clmlist set recvdt=null, recvseq=null where recvdt=? and recvseq=? and compno = ? ";
		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		pstmt.setString(1, recvdt);
		pstmt.setInt(2, recvseq);
		pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
		System.out.println("[updateClaimFromRecvDel]" + pstmt.toString());

		pstmt.executeUpdate();

	}

	public void processRecvDelete(String recvdt, int recvseq, String autochk, String gubun) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			deleteRecvDtl(connection, statementlist, recvdt, recvseq);
			deleteRecvMst(connection, statementlist, recvdt, recvseq);

			if (autochk.equals("1") && gubun.equals("재고차이")) {
				updateBasstkMstFromRecvDel(connection, statementlist, recvdt, recvseq);

			}
			if (autochk.equals("1") && gubun.equals("반품")) {
				updateClaimFromRecvDel(connection, statementlist, recvdt, recvseq);
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

	public List<String> getSpareRack(String prodcd, String date, String qty) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT ifnull(sparerack1,''), ifnull(sparerack2,''), ifnull(sparerack3,''), ifnull(sparerack4,''), ifnull(sparerack5,'') from recvdtl where prodcd = ? and realdt = ? and aftqty = ? "
					+ " and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, prodcd);
			pstmt.setString(2, date);
			pstmt.setInt(3, Integer.parseInt(qty));
			pstmt.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getHousingListByOption]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
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

	public int processRecvDtlUpdate(String recvdt, int recvseq, String gubun, String seq, String sp1, String sp2,
			String sp3, String sp4, String sp5) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql_orddtl = " update recvdtl set sparerack1 = ?, sparerack2 = ? ,  sparerack3=?, sparerack4=?, sparerack5=?,updatedt = ?, updateid = ? where recvdt = ? and recvseq = ? and seq =? ";
			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt = connection.prepareStatement(sql_orddtl);
			statementlist.add(pstmt);

			// [1, YWC2080-BK, 로니의자, 블랙, 180, 0, 잘나간다, T]
			int rowIdx = 0;
			pstmt.setString(++rowIdx, sp1);// remark
			pstmt.setString(++rowIdx, sp2);// remark
			pstmt.setString(++rowIdx, sp3);// remark
			pstmt.setString(++rowIdx, sp4);// remark
			pstmt.setString(++rowIdx, sp5);// remark
			pstmt.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());//
			pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId());//

			pstmt.setString(++rowIdx, recvdt);// recvdt
			pstmt.setInt(++rowIdx, recvseq);// recvseq
			pstmt.setInt(++rowIdx, Integer.parseInt(seq));// seq
			result = pstmt.executeUpdate();
			System.out.println("[processRecvDtlUpdate]" + pstmt.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public List<String> getSpareRackList(List<String> list) throws Exception {
		List<String> rack = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT distinct recvdt, ifnull(sparerack1,''), ifnull(sparerack2,''), ifnull(sparerack3,''), ifnull(sparerack4,''), ifnull(sparerack5,'') FROM RECVDTL "
					+ " WHERE compno = ? and prodcd= ?  AND ifnull(sparerack1,'') <> '' ORDER BY recvdt DESC LIMIT 1";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, list.get(1));

			System.out.println("[getSpareRackList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				rack.add(rs.getString(++columnIndex));
				rack.add(rs.getString(++columnIndex));
				rack.add(rs.getString(++columnIndex));
				rack.add(rs.getString(++columnIndex));
				rack.add(rs.getString(++columnIndex));
				rack.add(rs.getString(++columnIndex));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return rack;
	}

	public List<String> getSafetyStock(List<List<String>> contents) throws Exception {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		List<String> con = new ArrayList<>();
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();
			String param = contents.stream().map(p -> "'" + p.get(1) + "'").collect(Collectors.joining(","));

			// for(int k =1;k<=list.size()-1;k++) {
			String sql = String.format("SELECT ifnull(APROINVT,'0') FROM prodmst where prodcd in(%s)", param)
					+ " AND compno = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getProductMstList]" + pstmt.toString());
			rs = pstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				con.add(rs.getString(1));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return con;
	}
}

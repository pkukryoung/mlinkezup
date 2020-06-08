package com.kdj.mlink.ezup3.data.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class BaseStockDao {

	/**
	 * 이름으로 상품검색 이름이 없으면 전체 검색이됨
	 *
	 * @param optStr
	 * @return
	 * @throws Exception
	 */

	public List<BaseStockDto> getProdcutListByDate(int opt, String name, String date) throws Exception {

		List<BaseStockDto> list = new ArrayList<>();

		Connection connection = null;
		CallableStatement callstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			System.out.println("date : " + date);

			callstmt = connection.prepareCall("{call YWM_BASSTK_CRTNQURY(?,?,?,?)}");
			callstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			callstmt.setInt(2, opt);
			callstmt.setString(3, "%" + name + "%");
			callstmt.setString(4, date);

			rs = callstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				BaseStockDto dto = new BaseStockDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setProdcd(rs.getString(++i));
				dto.setImg(rs.getString(++i)); //
				dto.setProdnm(rs.getString(++i));
				if (rs.getString(++i) == null) {
					dto.setSpecdes("");
				} else {
					dto.setSpecdes(rs.getString(i));
				}
				dto.setQty(rs.getString(++i));
				dto.setRealQty(rs.getString(++i));
				dto.setDiffQty(rs.getString(++i));

				dto.setReason(rs.getString(++i));
				list.add(dto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, callstmt, rs);
		}

		return list;
	}

	public void insertBaseStockByDateID(String date) throws Exception {

		Connection connection = null;
		CallableStatement callstmt = null;
		ResultSet rs = null;
		String id = YDMASessonUtil.getUserInfo().getUserId();

		try {
			connection = DBCPInit.getInstance().getConnection();

			callstmt = connection.prepareCall("{call YWM_BASESTOCKAUTO_PROC(?,?,?)}");
			callstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			callstmt.setString(2, date);
			callstmt.setString(3, id);

			callstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, callstmt, rs);
		}

	}

	public void processBaseStkMstDtlInsert(List<BaseStockDto> dto, String date) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			insertBaseStkDtl(connection, statementlist, dto, date);
			insertBaseStkMst(connection, statementlist, date);

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public void insertBaseStkDtl(Connection connection, List<PreparedStatement> statementlist, List<BaseStockDto> dto,
			String date) throws Exception {

		PreparedStatement pstmt_prodmst = null;

		String sql_prodmst = "insert into basstkdtl (compno, stkdt, stkseq, prodcd, qty, realqty, diffqty, reason)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?) ";

		int max = maxNumberInDay(date);
		sql_prodmst = sql_prodmst.toUpperCase();

		pstmt_prodmst = connection.prepareStatement(sql_prodmst);
		statementlist.add(pstmt_prodmst);

		for (int i = 0; i < dto.size(); i++) {
			int j = 0;
			pstmt_prodmst.setString(++j, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt_prodmst.setString(++j, date);
			pstmt_prodmst.setString(++j, Integer.toString(++max));
			pstmt_prodmst.setString(++j, dto.get(i).getProdcd());
			pstmt_prodmst.setString(++j, dto.get(i).getQty());
			pstmt_prodmst.setString(++j, dto.get(i).getRealQty());
			pstmt_prodmst.setString(++j, dto.get(i).getDiffQty());
			pstmt_prodmst.setString(++j, dto.get(i).getReason());

			pstmt_prodmst.addBatch();
			pstmt_prodmst.clearParameters();
		}
		pstmt_prodmst.executeBatch();
		pstmt_prodmst.clearBatch();// Batch 초기화

	}

	public void insertBaseStkMst(Connection connection, List<PreparedStatement> statementlist, String date)
			throws Exception {

		PreparedStatement pstmt_prodmst = null;
		ResultSet rs = null;

		String sql_prodmst = "insert into basstkmst (compno, stkdt, recvdt, recvseq, insertdt, insertid, updatedt, updateid)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?) ";

		sql_prodmst = sql_prodmst.toUpperCase();

		pstmt_prodmst = connection.prepareStatement(sql_prodmst);
		statementlist.add(pstmt_prodmst);
		pstmt_prodmst.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt_prodmst.setString(2, date);
		pstmt_prodmst.setString(3, "");
		pstmt_prodmst.setInt(4, 0);
		pstmt_prodmst.setString(5, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt_prodmst.setString(6, YDMASessonUtil.getUserInfo().getUserId());
		pstmt_prodmst.setString(7, "");
		pstmt_prodmst.setString(8, "");

		pstmt_prodmst.executeUpdate();
	}

	public void insertRealStock(List<BaseStockDto> dto, String date) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			PreparedStatement pstmt_prodmst = null;

			String sql_prodmst = "insert into basstkdtl (compno, stkdt, stkseq, prodcd, qty, realqty, diffqty, reason)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ? ) ";

			int max = maxNumberInDay(date);
			sql_prodmst = sql_prodmst.toUpperCase();

			pstmt_prodmst = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt_prodmst);

			for (int i = 0; i < dto.size(); i++) {
				int j = 0;
				pstmt_prodmst.setString(++j, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt_prodmst.setString(++j, date);
				pstmt_prodmst.setString(++j, Integer.toString(++max));
				pstmt_prodmst.setString(++j, dto.get(i).getProdcd());
				pstmt_prodmst.setString(++j, dto.get(i).getQty());
				pstmt_prodmst.setString(++j, dto.get(i).getRealQty());
				pstmt_prodmst.setString(++j, dto.get(i).getDiffQty());
				pstmt_prodmst.setString(++j, dto.get(i).getReason());

				pstmt_prodmst.addBatch();
				pstmt_prodmst.clearParameters();
			}
			pstmt_prodmst.executeBatch();
			pstmt_prodmst.clearBatch();// Batch 초기화

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public void insertRealStock(BaseStockDto dto, String date) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			PreparedStatement pstmt_prodmst = null;

			String sql_prodmst = "insert into basstkdtl (compno, stkdt, stkseq, prodcd, qty, realqty, diffqty, reason)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?) ";

			int max = maxNumberInDay(date);
			sql_prodmst = sql_prodmst.toUpperCase();

			pstmt_prodmst = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt_prodmst);
			int j = 0;
			pstmt_prodmst.setString(j, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt_prodmst.setString(++j, date);
			pstmt_prodmst.setString(++j, Integer.toString(++max));
			pstmt_prodmst.setString(++j, dto.getProdcd());
			pstmt_prodmst.setString(++j, dto.getQty());
			pstmt_prodmst.setString(++j, dto.getRealQty());
			pstmt_prodmst.setString(++j, dto.getDiffQty());
			pstmt_prodmst.setString(++j, dto.getReason());

			pstmt_prodmst.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public void updateBaseStock(BaseStockDto dto, String date) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			updateBaseStockDtl(connection, statementlist, dto, date);

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public void updateBaseStockDtl(Connection connection, List<PreparedStatement> statementlist, BaseStockDto dto,
			String date) throws Exception {

		PreparedStatement pstmt_prodmst = null;

		String sql_prodmst = "update basstkdtl set realqty=?, diffqty=?, reason=? where prodcd=? and compno = ?";

		sql_prodmst = sql_prodmst.toUpperCase();

		pstmt_prodmst = connection.prepareStatement(sql_prodmst);
		statementlist.add(pstmt_prodmst);

		pstmt_prodmst.setString(1, dto.getRealQty());
		pstmt_prodmst.setString(2, dto.getDiffQty());
		pstmt_prodmst.setString(3, dto.getReason());
		pstmt_prodmst.setString(4, dto.getProdcd());
		pstmt_prodmst.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt_prodmst.executeUpdate();

	}

	public void updateRealStock(List<BaseStockDto> dtoList, String date) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			updateRealStockDtl(connection, statementlist, dtoList, date);

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public void updateRealStockDtl(Connection connection, List<PreparedStatement> statementlist,
			List<BaseStockDto> dtoList, String date) throws Exception {

		PreparedStatement pstmt_prodmst = null;

		String sql_prodmst = "update basstkdtl set realqty=?, diffqty=?, reason=? where prodcd=? and compno = ?";

		sql_prodmst = sql_prodmst.toUpperCase();

		pstmt_prodmst = connection.prepareStatement(sql_prodmst);
		statementlist.add(pstmt_prodmst);

		for (int i = 0; i < dtoList.size(); i++) {
			BaseStockDto dto = dtoList.get(i);

			pstmt_prodmst.setString(1, dto.getRealQty());
			pstmt_prodmst.setString(2, dto.getDiffQty());
			pstmt_prodmst.setString(3, dto.getReason());
			pstmt_prodmst.setString(4, dto.getProdcd());
			pstmt_prodmst.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());

			pstmt_prodmst.addBatch();
			pstmt_prodmst.clearParameters();
		}
		pstmt_prodmst.executeBatch();
		pstmt_prodmst.clearBatch();// Batch 초기화

	}

	public String getRecvDtAndSeq(String date) throws Exception {

		String result = "";

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select recvdt, recvseq from basstkmst where stkdt=? and compno = ?";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, date);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getString(1) + "-" + rs.getString(2);
				if (rs.getString(1) == null || rs.getString(2) == null) {
					result = "";
				}
			} else {
				System.out.println("???");
				result = "???";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return result;
	}

	public boolean isBlockSaveButton() throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select count(*) from basstkmst where recvdt is not null and compno = ?";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (Integer.parseInt(rs.getString(1)) > 0) {
					flag = true;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return flag;
	}

	public boolean isExistrecvdt(String date) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select 1 from basstkmst where recvdt =? and stkdt=? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, date);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (Integer.parseInt(rs.getString(1)) == 1) {
					flag = true;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return flag;
	}

	public boolean isExistBasstkmst(String date) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select 1 from basstkmst where stkdt=? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (Integer.parseInt(rs.getString(1)) == 1) {
					flag = true;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return flag;
	}

	public boolean isExistBasstkdtl(String date) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select 1 from basstkdtl where stkdt=? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (Integer.parseInt(rs.getString(1)) == 1) {
					flag = true;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return flag;
	}

	public int maxNumberInDay(String date) throws Exception {

		int max = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT max(stkseq) FROM basstkdtl WHERE stkdt = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				max = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return max;
	}

	public String getMaxStkdt() throws Exception {

		String max = "";

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT max(stkdt) FROM basstkmst where compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				max = rs.getString(1);
				if (max == null) {
					max = "20181231";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return max;
	}

	public List<String> getBaseStockDateList() throws Exception {

		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT DISTINCT STKDT FROM BASSTKMST where compno = ? ORDER BY 1 DESC ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getBaseStockDateList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString(1));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public String getMaxRecvdt() throws Exception {

		String max = "";

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT max(recvdt) FROM basstkmst where compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				max = rs.getString(1);
				if (max == null) {
					max = "20181231";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return max;
	}

	public int maxRecvseqInDay(String date) throws Exception {

		int max = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT max(recvseq) FROM recvmst WHERE recvdt = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				max = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return max;
	}

	public boolean isExistRealStock(String date, String prodcd) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "select 1 from basstkdtl where stkdt=? and prodcd=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, prodcd);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (Integer.parseInt(rs.getString(1)) == 1) {
					flag = true;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return flag;
	}

	public boolean isExistRealStockInProdmst(String prodcd) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "select 1 from prodmst where prodcd=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (Integer.parseInt(rs.getString(1)) == 1) {
					flag = true;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return flag;
	}

	public void updateBaseStockSameDate(BaseStockDto dto, String date) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			PreparedStatement pstmt_prodmst = null;

			String sql_prodmst = "update basstkdtl set realqty=?, reason=? where prodcd=? and stkdt=? and compno = ? ";

			sql_prodmst = sql_prodmst.toUpperCase();

			pstmt_prodmst = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt_prodmst);

			pstmt_prodmst.setString(1, dto.getRealQty());
			pstmt_prodmst.setString(2, dto.getDiffQty());
			pstmt_prodmst.setString(3, dto.getReason());
			pstmt_prodmst.setString(4, dto.getProdcd());
			pstmt_prodmst.setString(5, date);
			pstmt_prodmst.setString(6, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt_prodmst.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public void deleteBaseStkMst(String date) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String sql = "DELETE FROM basstkmst where stkdt=? and compno = ? ";
			sql = sql.toUpperCase();

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, date);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			pstmt.executeUpdate();

			connection.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

	public void deleteBaseStkDtl(String date) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String sql = "DELETE FROM basstkdtl where stkdt=? and compno = ? ";
			sql = sql.toUpperCase();

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, date);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			pstmt.executeUpdate();

			connection.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

}

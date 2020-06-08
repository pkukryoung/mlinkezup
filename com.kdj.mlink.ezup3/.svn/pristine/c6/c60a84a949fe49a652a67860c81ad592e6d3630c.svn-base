package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class AutomationDao {

	public List<String> checkEcountStockApi(String progcd) throws Exception {

		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT autoExe  FROM apiinf  where PROGCD = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, progcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			rs = pstmt.executeQuery();

			System.out.println("[checkEcountStockApi]" + pstmt.toString());

			int i = 0;
			if (rs.next()) {
				list.add(rs.getString(++i)); // AUTOEXE
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<List<String>> getEcountApiInfo(String progcd) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT exeseq, ifnull(AUTOEXE, '0'), ifnull(autoexe_seq, '0'), ifnull(exeweek, '0000000'), ifnull(exetime, '00:00:00') "
					+ " FROM apiinf  where PROGCD = ? and compno = ? order by exeseq ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, progcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			rs = pstmt.executeQuery();

			System.out.println("[getEcountApiInfo]" + pstmt.toString());

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
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

	public int updateApiInfo(String progcd, String exeseq, String autoexe, String autoexe_seq, String exeweek,
			String exetime) throws Exception {

		int result = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "update apiinf set AUTOEXE=?, autoexe_seq=?, exeweek=?, exetime=? "
					+ " where PROGCD=?  and  exeseq=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, autoexe);
			pstmt.setString(2, autoexe_seq);
			pstmt.setString(3, exeweek);
			pstmt.setString(4, exetime);
			pstmt.setString(5, progcd);
			pstmt.setString(6, exeseq);
			pstmt.setString(7, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[updateApiInfo]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public int insertApiInfo(String progcd, String exeseq/* 차수 */, String autoexe/* 사용여부 */,
			String autoexe_seq/* 차수의 사용여부 */, String exeweek, String exetime) throws Exception {

		int result = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "insert apiinf (PROGCD, EXESEQ, AUTOEXE, AUTOEXE_SEQ, EXEWEEK, EXETIME, compno ) values (?,?,?,?,?,?,?) ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, progcd);
			pstmt.setString(2, exeseq);
			pstmt.setString(3, autoexe);
			pstmt.setString(4, autoexe_seq);
			pstmt.setString(5, exeweek);
			pstmt.setString(6, exetime);
			pstmt.setString(7, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[insertApiInfo]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

}

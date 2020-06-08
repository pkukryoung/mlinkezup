package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class RackDao {

	public boolean checkRackInfo(String prodcd) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "select prodcd from NECPRODMST where prodcd = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[selectRackInfo]" + pstmt.toString());

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

	public int insertRackInfo(String prodcd, String neccd1, String floor, String cell) throws Exception {

		int result = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "insert necprodmst (compno, prodcd, neccd1, neccd2, neccd3, insertdt, insertid) values (?, ?,?,?,?,?,?) ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, prodcd);
			pstmt.setString(++i, neccd1);
			pstmt.setString(++i, floor);
			pstmt.setString(++i, cell);
			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUserId());

			System.out.println("[insertRackInfo]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public int updateRackInfo(String prodcd, String neccd1, String floor, String cell) throws Exception {

		int result = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "update necprodmst set neccd1=?, neccd2=?, neccd3=? , modifydt=?, modifyid=?  where prodcd=? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, neccd1);
			pstmt.setString(++i, floor);
			pstmt.setString(++i, cell);
			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUserId());
			pstmt.setString(++i, prodcd);
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[updateRackInfo]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public List<String> selectRackInfo(String prodcd) throws Exception {

		List<String> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "select p.prodcd, p.prodnm, ifnull(n.neccd1,''), ifnull(n.neccd2,''), ifnull(n.neccd3,'')   "
					+ " from necprodmst as n left join prodmst as p  on p.prodcd=n.prodcd and p.compno = n.compno "
					+ " where p.prodcd = ? and p.compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[selectRackInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(String.valueOf(rs.getInt(++i))); // Ãþ
				list.add(String.valueOf(rs.getInt(++i))); // ¼¿
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<List<String>> getProdcutListforRack(int opt, String optStr) throws Exception {

		List<List<String>> contents = null;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "select A.prodcd, "
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,A.prodcd) as img,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ "case ifnull(A.SPECDES,'') when '' then A.ProdNm ELSE CONCAT(A.prodnm ,'_',A.SPECDES) end, "
					+ "ifnull(B.neccd1, ''), ifnull(B.neccd2, '') , ifnull(B.neccd3, '') "
					+ " from prodmst as A  left join necprodmst B  on A.prodcd=B.prodcd and A.compno = B.compno where A.compno = ? ";

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

			System.out.println("[getProdcutListforRack]" + pstmt.toString());

			rs = pstmt.executeQuery();
			rs.last();
			int rowcount = rs.getRow();

			if (rowcount > 0) {
				rs.beforeFirst();

				contents = new ArrayList<>();

				int rowno = 0;

				while (rs.next()) {
					int i = 0;
					List<String> list = new ArrayList<>();
					list.add("" + (++rowno)); // ui grid »óÀÇ ¼ø¹ø , µðºñµ¥ÀÌÅ¸ ¾Æ´Ô
					list.add(rs.getString(++i)); // prodcd
					list.add(rs.getString(++i)); // iMAGE Ãß°¡
					list.add(rs.getString(++i)); // prodnm
					list.add(rs.getString(++i)); // nec1
					list.add(rs.getString(++i)); // nec3 - ¼¿
					list.add(rs.getString(++i)); // nec2 - Ãþ

					contents.add(list);
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

}

package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class VProductsDao {

	public static String GUBUN_E = "E";
	public static String GUBUN_S = "S";

	/**
	 * 이름으로 상품검색 이름이 없으면 전체 검색이됨
	 *
	 * @param optStr
	 * @return
	 * @throws Exception
	 */
	public List<ProductMstDto> getVProdcutListByOption(int opt, String optStr) throws Exception {

		// opt == {상품코드, 상품명}
		List<ProductMstDto> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select prodcd, ifnull(p.prodnm,''), ifnull(p.specdes,'')  "
					+ " from prodmst as p  where compno = ? ";

			if (opt == 0) {
				if (optStr.length() != 0) {
					sql += " and  p.prodcd like ? ";
				}
				sql += " order by p.prodcd ";
			} else if (opt == 1) {
				if (optStr.length() != 0) {
					sql += " and  p.prodnm like ? ";
				}
				sql += " order by p.prodnm ";
			} else {

			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			if (opt == 0 || opt == 1) {
				if (optStr.length() != 0) {
					pstmt.setString(2, "%" + optStr + "%");
				}
			}

			System.out.println("[getProdcutListByOption]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ProductMstDto dto = new ProductMstDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setProdcd(rs.getString(++i));
				dto.setProdnm(rs.getString(++i));
				dto.setSpecdes(rs.getString(++i));

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

	public List<String> getVProdcutInfoByProdcd(String prodcd) throws Exception {

		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select prodcd, ifnull(prodnm,''), ifnull(specdes,'') " + " from v_products "
					+ " where prodcd=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getVProdcutInfoByProdcd]" + pstmt.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int i = 0;
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

	/**
	 * 상품코드 중복체크
	 *
	 * @param prodcd
	 * @return
	 * @throws Exception
	 */
	public boolean isExistProduct(String prodcd) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select prodcd from v_products where prodcd = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[isExistVProduct]" + pstmt.toString());

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

}

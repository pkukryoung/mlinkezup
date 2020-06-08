package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class CustomerDao {

	public List<List<String>> getCustListForClaimManager(int opt, String optStr) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

//			{  { "사업자번호", "100" }, { "사업자명", "350" } , { "대표", "100" } }

			String sql = " select  ifnull(A.CUSTNO,''), ifnull(A.CUSTNM,''), "
					+ " ifnull(A.CEONM,''),ifnull(A.TELNO,''),ifnull(A.ADDRESS,'')  " + " FROM  CUSTMST A";

			// {상품코드", "상품명", "수취인명", "주문자명"})
			if (opt == 0) {
				sql += " where  custno like ? and A.compno = ? ";
				sql += "  order by A.CUSTNO ";
			} else if (opt == 1) {
				sql += " where  CUSTNM like ? and A.compno = ? ";
				sql += "  order by A.CUSTNM ";
			}

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%" + optStr + "%");
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getCustListForClaimManager]" + pstmt.toString());

			rs = pstmt.executeQuery();

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

	public List<List<String>> getCustomerListByName(int opt, String optStr) throws Exception {

		List<List<String>> contents = null;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT custno, custnm, ceonm, telno, ifnull(faxno,''),cphon,ifnull(cphonaddr,''), ifnull(email,''),address, "
					+ " ifnull(direct,''), ifnull(USEYN,'') FROM custmst where compno = ? ";

			if (opt == 0) {
				if (optStr.length() != 0) {
					sql += " and  custno like ? ";
				}
				sql += "  order by custno ";
			} else if (opt == 1) {
				if (optStr.length() != 0) {
					sql += " and  custnm like ? ";
				}
				sql += "  order by custnm ";
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			if (optStr.length() != 0) {
				pstmt.setString(2, "%" + optStr + "%");
			}

			System.out.println("[getCustomerListByName]" + pstmt.toString());

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
					list.add("" + (++rowno));
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
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public boolean checkExistCustomer(String custno) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select  custno from custmst where custno = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, custno);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[checkExistCustomer]" + pstmt.toString());

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

	public int insertCustomer(String custno, String custnm, String ceonm, String telno, String cphon, String address,
			String useyn, String faxno, String kakao, String email, String jiksong) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "insert into custmst (compno, custno, custnm, ceonm, telno, faxno,cphon,cphonaddr,email, address, direct,USEYN) "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?)";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, custno);
			pstmt.setString(3, custnm);
			pstmt.setString(4, ceonm);
			pstmt.setString(5, telno);
			pstmt.setString(6, faxno);
			pstmt.setString(7, cphon);
			pstmt.setString(8, kakao);
			pstmt.setString(9, email);
			pstmt.setString(10, address);
			pstmt.setString(11, jiksong);
			pstmt.setString(12, useyn);

			System.out.println("[insertUser]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public int updateCustomer(String custno, String custnm, String ceonm, String telno, String cphon, String address,
			String useyn, String faxno, String kakao, String email, String jiksong) throws Exception {

		int result = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "update custmst set custnm=?, ceonm=?, telno=?, cphon=?, address=?, useyn=?, faxno = ?, cphonaddr = ?, email=?, direct =?";
			sql += " where custno=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, custnm);
			pstmt.setString(2, ceonm);
			pstmt.setString(3, telno);
			pstmt.setString(4, cphon);
			pstmt.setString(5, address);
			pstmt.setString(6, useyn);
			pstmt.setString(7, faxno);
			pstmt.setString(8, kakao);
			pstmt.setString(9, email);
			pstmt.setString(10, jiksong);

			pstmt.setString(11, custno);
			pstmt.setString(12, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[updateCustomer]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;

	}

	public List<String> getCustomerInfoByCustno(String custno) throws Exception {

		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "select ifnull(custno,''), ifnull(custnm,''), ifnull(ceonm,''),  ifnull(telno,''), ifnull(faxno,''),"
					+ " ifnull(cphon,''), ifnull(cphonaddr,''), ifnull(email,''),ifnull(address,''), ifnull(direct,''),ifnull(USEYN,'')"
					+ " from custmst ";

			sql += " Where custno = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, custno);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getCustomerInfoByCustno]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int i = 0;
			if (rs.next()) {
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

	public List<List<String>> getCustListForProductOrderList(int select, String search) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select  ifnull(compno,''), ifnull(custno,''), ifnull(custnm,''), ifnull(ceonm,''), ifnull(telno,''),ifnull(faxno,''),ifnull(cphon,''), ifnull(cphonaddr,''), ifnull(email,''), "
					+ "ifnull(address,''), ifnull(direct,'N'), ifnull(useyn,'N') FROM  CUSTMST ";

			// {상품코드", "상품명", "수취인명", "주문자명"})
			if (select == 0) {
				sql += " where  custnm like ? and compno = ? ";
				sql += "  order by custnm ";
			} else if (select == 1) {
				sql += " where  ceonm like ? and compno = ? ";
				sql += "  order by ceonm ";
			}

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getCustListForClaimManager]" + pstmt.toString());

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

package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class CommonDao {

	public List<String> getAllProductCode() throws Exception {

		List<String> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT prodcd  FROM V_PRODUCTS where compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getAllProductCode]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String prodcd = rs.getString(1);
				if (prodcd.trim().length() > 0) {
					contents.add(prodcd);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public List<String> getAllProductCodeForRecv() throws Exception {

		List<String> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT prodcd  FROM prodmst where ifnull(delyn,'N')='N' and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getAllProductCodeForRecv]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String prodcd = rs.getString(1);
				if (prodcd.trim().length() > 0) {
					contents.add(prodcd);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public List<String> getAllProductCodeFoDelyn() throws Exception {

		List<String> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT prodcd  FROM V_PRODUCTS where ifnull(delyn,'N')='N' and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getAllProductCodeFoDelyn]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String prodcd = rs.getString(1);
				if (prodcd.trim().length() > 0) {
					contents.add(prodcd);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public List<String> getAllRackProductCode() throws Exception {

		List<String> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT prodcd  FROM V_PRODUCTS where  ifnull(neccd1, '') != '' and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getAllRackProductCode]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String prodcd = rs.getString(1);
				if (prodcd.trim().length() > 0) {
					contents.add(prodcd);
				}
				// contents.add(rs.getString(++i));
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	/**
	 * ��(V_PRODUCTS)���� ��۰����� ���������� ó���� ��ǰ�ڵ� ����� �ҷ��´�.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getAllExpProductCode() throws Exception {

		List<String> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT prodcd  FROM V_PRODUCTS where  ifnull(expfile, '') != '' and compno = ? ";
			sql = sql.toUpperCase(); // expfile �� ������ ������ �������� ��´�.

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getAllExpProductCode]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String prodcd = rs.getString(1);
				if (prodcd.trim().length() > 0) {
					contents.add(prodcd);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	/**
	 * ��(V_PRODUCTS)���� �ù�� �Ⱦ�����Ʈ�� '���� ��Ʈ��'�� �Ǵ� �ù�� �̸� ��ȸ
	 * 
	 * @return �յ��ù�, õ��4, �������, ��
	 * @throws Exception
	 */
	public List<String> getAllExpfile() throws Exception {

		List<String> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT ifnull(expfile, '��Ÿ')  FROM V_PRODUCTS where compno = ? group by expfile  order by expfile ";
			sql = sql.toUpperCase(); // expfile �� ������ ������ �������� ��´�.

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getAllExpfile]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String prodcd = rs.getString(1);
				if (prodcd.trim().length() > 0) {
					contents.add(prodcd);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public List<String> getAllExpCD() throws Exception {

		List<String> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT trim(ifnull(expcd, '��Ÿ')) aaa FROM V_PRODUCTS where compno = ? group by expcd ";
			sql = sql.toUpperCase(); // expfile �� ������ ������ �������� ��´�.

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getAllExpCD]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String prodcd = rs.getString(1);
				if (prodcd.trim().length() > 0) {
					contents.add(prodcd);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	/**
	 * õ���ù� db �� �����Ͽ� �ù�Ұ� �ּ����� �ľ��Ѵ�.
	 * 
	 * @param address
	 * @return
	 */
	public String checkExpLocation(String address) {

		String str = "";

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@121.172.114.136:1521:TAEKBAE";
		String user = "yd";
		String password = "chunilps1001";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			// System.out.println("jdbc driver �ε� ����");
			con = DriverManager.getConnection(url, user, password);
			// System.out.println("����Ŭ ���� ����");

			String sql = "SELECT GET_ADDGBN_YD(?) N_SEQNUM FROM DUAL ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, address);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				str = rs.getString(1);
				// str : PASS, FALSE, TRUE
			}

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver �ε� ����");
		} catch (SQLException e) {
			System.out.println("����Ŭ ���� ����");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {

				}

			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {

				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {

				}
			}
		}

		return str;
	}

	public List<List<String>> checkExpLocationcontents(List<List<String>> sheetContents) {
		List<List<String>> contents = new ArrayList<>();

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@121.172.114.136:1521:TAEKBAE";
		String user = "yd";
		String password = "chunilps1001";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			// System.out.println("jdbc driver �ε� ����");
			con = DriverManager.getConnection(url, user, password);
			// System.out.println("����Ŭ ���� ����");
			for (List<String> list : sheetContents) {
				String sql = "SELECT GET_ADDGBN_YD(?) N_SEQNUM FROM DUAL ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, list.get(6));

				rs = pstmt.executeQuery();

				if (rs.next()) {
					list.add(rs.getString(1));
					// str : PASS, FALSE, TRUE
				}
				contents.add(list);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver �ε� ����");
		} catch (SQLException e) {
			System.out.println("����Ŭ ���� ����");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {

				}

			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {

				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {

				}
			}
		}

		return contents;
	}

}

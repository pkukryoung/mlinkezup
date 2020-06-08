package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class UserDao {

	public boolean checkExistUser(String id) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select  userid from userinf where userid = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, id);
			System.out.println("[checkExistUser]" + pstmt.toString());

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

	public UserDto getUserInfoLogin(String id, String pw) throws Exception {

		UserDto dto = null;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select  userid, usernam, userpwd, email, PROGCD, useyn, compno from userinf  where userid = ?  and userpwd = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			System.out.println("[getUserInfoLogin]" + pstmt.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int i = 0;
				dto = new UserDto();
				dto.setUserId(rs.getString(++i));
				dto.setUsernam(rs.getString(++i));
				dto.setUserpwd(rs.getString(++i));
				dto.setEmail(rs.getString(++i));
				dto.setProgcd(rs.getString(++i));
				dto.setUseyn(rs.getString(++i));
				dto.setCompno(rs.getString(++i));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	public List<UserDto> getUserListByName(String username) throws Exception {

		List<UserDto> list = null;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT USERID, USERNAM, USERPWD, EMAIL, PROGCD, USEYN, COMPNO FROM USERINF ";
			if (username.length() != 0) {
				sql += " where USERNAM like ?";
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			if (username.length() != 0) {
				pstmt.setString(1, "%" + username + "%");
			}

			System.out.println("[getUserListByName]" + pstmt.toString());

			rs = pstmt.executeQuery();
			rs.last();
			int rowcount = rs.getRow();

			if (rowcount > 0) {
				rs.beforeFirst();
				list = new ArrayList<>();
				int rowno = 0;
				while (rs.next()) {
					int i = 0;
					UserDto dto = new UserDto();
					dto.setRowno(String.valueOf(++rowno));// 디비에서 조회한 값 세팅하는 거 아님
					dto.setUserId(rs.getString(++i));
					dto.setUsernam(rs.getString(++i));
					dto.setUserpwd(rs.getString(++i));
					dto.setEmail(rs.getString(++i));
					dto.setProgcd(rs.getString(++i));
					dto.setUseyn(rs.getString(++i));
					dto.setCompno(rs.getString(++i));
					list.add(dto);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public UserDto getUserInfoById(String userId) throws Exception {

		UserDto dto = null;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "select userid, usernam, userpwd,  email, PROGCD, useyn, compno from userinf ";
			sql += " Where userid = ?";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userId);

			System.out.println("[getUserInfoById]" + pstmt.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int i = 0;
				dto = new UserDto();
				dto.setUserId(rs.getString(++i));
				dto.setUsernam(rs.getString(++i));
				dto.setUserpwd(rs.getString(++i));
				dto.setEmail(rs.getString(++i));
				dto.setProgcd(rs.getString(++i));
				dto.setUseyn(rs.getString(++i));
				dto.setCompno(rs.getString(++i));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	public int insertUser(String username, String userId, String userPw, String email, String useyn, String compno)
			throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "insert into userinf (compno, usernam, userid, userpwd, email, useyn) values (?, ?, ?, ?, ?, ?) ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, username);
			pstmt.setString(3, userId);
			pstmt.setString(4, userPw);
			pstmt.setString(5, email);
			pstmt.setString(6, useyn);

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

	public int updateUser(String username, String userId, String userPw, String email, String useyn, String compno)
			throws Exception {

		int result = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "update userinf set usernam=?, userpwd=?, email=?, useyn=?, compno=?";
			sql += " where userid=?";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, userPw);
			pstmt.setString(3, email);
			pstmt.setString(4, useyn);
			pstmt.setString(5, compno);
			pstmt.setString(6, userId);

			System.out.println("[updateUser]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public int deleteUser(String userId) throws Exception {

		int result = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
//			connection = DBCPInit.getInstance().getConnection();
//			String sql = "delete from userinf ";
//			sql += " where userid=?";
//
//			sql = sql.toUpperCase();
//
//			pstmt = connection.prepareStatement(sql);
//			pstmt.setString(1, userId);
//
//			System.out.println("[deleteUser]"+pstmt.toString());
//
//			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public int updateUseyn(String userId, String userPw, String useyn) throws Exception {

		int result = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "update userinf set useyn=?";
			sql += " where userid=? and userpwd=? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, useyn);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPw);

			System.out.println("[updateUseyn]" + pstmt.toString());

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

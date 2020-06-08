package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PopularSearchDao {

	public List<List<String>> hotSearch() throws Exception {
		//opt == {상품코드, 상품명}
		List<List<String>> contents = new ArrayList<List<String>>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			
			String sql = "select ifnull(SEARCHNM,'') from hotsearch";
						
						
			sql = sql.toUpperCase();
			
			pstmt = connection.prepareStatement(sql);

			System.out.println("[hotSearch]"+pstmt.toString());
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				int i = 0;
				list.add(rs.getString(1));
				
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

	public void deleteHotSearch() throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		List <PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
				
		try {
			connection = DBCPInit.getInstance().getConnection();
			
			
			
			String sql_ordmst = " delete from hotsearch ";			
			sql_ordmst = sql_ordmst.toUpperCase();
			
			pstmt = connection.prepareStatement(sql_ordmst);
			statementlist.add(pstmt);			
			
			pstmt.executeUpdate();	
						

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public List<String> getlarcode(String categlagcd, String categmidcd, String categsmlcd, String categdetcd) throws Exception {
		//opt == {상품코드, 상품명}
		List<String> list = new ArrayList<String>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			
			String sql = "select ifnull(largecode,''),ifnull(midiumcode,''), ifnull(smallcode,''), ifnull(detailcode,'') from hotsearch "
					+ "where largecode = ? and midiumcode = ? and smallcode = ? and detailcode = ? ";
						
						
			sql = sql.toUpperCase();
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,categlagcd);
			pstmt.setString(2,categmidcd);
			pstmt.setString(3,categsmlcd);
			pstmt.setString(4,categdetcd);
			
			System.out.println("[getlarcode]"+pstmt.toString());
			
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


}///////////

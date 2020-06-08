package com.kdj.mlink.ezup3.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;

public class ShopOrderCSDao {
	private static ShopOrderCSDao daoInstance = new ShopOrderCSDao(); // dao 싱글톤으로 생성 한다.

	private ShopOrderCSDao() {
	}

	public static ShopOrderCSDao get() {
		return daoInstance;
	}
	
	
	public List<List<String>> getShopordcsListByOrdseq(int ordseq)  throws  Exception {

        List<List<String>> contents = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "SELECT csseq, csmemo, csclose, insertdt, insertid "
            		      + " FROM shopordcs "
            		      + "WHERE ordseq = ? ";

            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, ordseq);

            System.out.println("[getShopordcsListByShopOrderNo]"+pstmt.toString());
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
				int rowIdx = 0;
				List<String> list = new ArrayList<String>();
				list.add(rs.getString(++rowIdx));
				list.add(rs.getString(++rowIdx));
				list.add(rs.getString(++rowIdx));
				list.add(rs.getString(++rowIdx));
				list.add(rs.getString(++rowIdx));
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
    
    public boolean checkExistShopordcs(int ordseq) throws Exception {

        boolean flag = false;

        Connection connection = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();

            String sql = "select 1 from shopordcs where ordseq = ?";
            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, ordseq);
            System.out.println("[checkExistShopordcs]"+pstmt.toString());
            
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
    
    public int insertShopordcs(int ordseq, String csmemo, String csclose) throws Exception {

        int result = 0;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
        
        try {
        	
	        connection = DBCPInit.getInstance().getConnection();
			//connection.setAutoCommit(false);
	    
			int maxSeq = getShopordcsMaxSeq(connection, statementlist, ordseq); 
            String sql = "insert into shopordcs (ordseq,csseq, csmemo, csclose, insertdt, insertid) "
            			  + " values (?, ?, ?, ?, ?, ?)" ;
            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, ordseq);
            pstmt.setInt(2, maxSeq);
            pstmt.setString(3, csmemo);
            pstmt.setString(4, csclose);
            pstmt.setString(5, YDMATimeUtil.getCurrentTimeByYDFormat());
            pstmt.setString(6, YDMASessonUtil.getUserInfo().getUserId());

            System.out.println("[ShopOrdCS]"+pstmt.toString());

            result = pstmt.executeUpdate();
            result = maxSeq;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
        }
        
        return result;
        
    }
    
    public int getShopordcsMaxSeq(Connection connection, List<PreparedStatement> statementlist, int ordseq)  throws Exception { 
    	PreparedStatement pstmt_shopordcs = null;
		ResultSet rs = null;
		
		int maxSeq = 0;
		
		String sql_shopordcs = " SELECT a.csseq from (SELECT ifnull(MAX(csseq), 0)+1 as csseq FROM shopordcs where ordseq=? ) a  ";
		sql_shopordcs = sql_shopordcs.toUpperCase();

		pstmt_shopordcs = connection.prepareStatement(sql_shopordcs);
		statementlist.add(pstmt_shopordcs);

		pstmt_shopordcs.setInt(1, ordseq);
		System.out.println("[getShopordcsMaxSeq]" + pstmt_shopordcs.toString());

		rs = pstmt_shopordcs.executeQuery();
		if(rs.next()) {
			maxSeq = rs.getInt(1);
		}
		
		if(rs!=null) {
			DBCPInit.getInstance().freeResultSet(rs);
		}
		
		return maxSeq;
    	
    }
    
    public int updateShopordcsByMemo(int ordseq, int csseq, String csmemo) throws Exception {

        int result = 0;

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "update shopordcs set csmemo=?"
             			  + " where ordseq=? "
             			  + "    and csseq = ? ";
            
            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, csmemo);
            pstmt.setInt(2, ordseq);
            pstmt.setInt(3, csseq);
            
            System.out.println("[updateShopordcsByMemo]"+pstmt.toString());

            result = pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
        }

        return result;
        
    }
    
    public int updateShopordcsByCsclose(int ordseq, int csseq, String csclose) throws Exception {

        int result = 0;

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "update shopordcs set csclose=?"
             			  + " where ordseq=? "
             			  + "    and csseq = ? ";
            
            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, csclose);
            pstmt.setInt(2, ordseq);
            pstmt.setInt(3, csseq);
            
            System.out.println("[updateShopordcsByCsclose]"+pstmt.toString());

            result = pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
        }

        return result;
        
    }

	public List<List<String>> getShopordcsListByCsMemo(String searchText, String search1) throws Exception {

        List<List<String>> contents = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "SELECT ordseq "
            		      + " FROM shopordcs "
            		      + "WHERE ";
            if(search1.equals("부분일치")) {
            	sql += "csmemo LIKE '%"+searchText+"%'";
            }else {
            	sql += "csmemo = '"+searchText+"'";
            }
            
            
            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
 
            System.out.println("[getShopordcsListByShopOrderNo]"+pstmt.toString());
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
				int rowIdx = 0;
				List<String> list = new ArrayList<String>();
				list.add(rs.getString(++rowIdx));
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

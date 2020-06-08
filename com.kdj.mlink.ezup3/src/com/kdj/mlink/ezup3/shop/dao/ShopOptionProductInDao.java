package com.kdj.mlink.ezup3.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.shop.dao.ShopOptionProductInDto;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;

public class ShopOptionProductInDao {
	
	public List<ShopOptionProductInDto> getShopOptProdInListBySendseq(String sendseq)  throws  Exception {

        List<ShopOptionProductInDto> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql  = "SELECT SENDSEQ,ifnull(SKUSEQ,0),COMPNO,ifnull(SHOPPRODNO,''),ifnull(SHOPPRODSKU,''),"
            			+ "		  ifnull(SHOPPRODSKU_NM,''),"
            		    + "		  ifnull(PRODSEQ,'0'),ifnull(OPTPRODCD,''),ifnull(OPTPRODNM,''),ifnull(OPTSPECDES,''),"
            		    + "		  ifnull(OPTEA,0),ifnull(OPTSALE,''),ifnull(OPTSALEOUT,''),"
            		    + "		  ifnull(OPTNOTUSE,''), ifnull(OPTSAFESTOCK,'0'),ifnull(OPTVERTSTOCK,'0'),"
            		    + "		  ifnull(OPTADDAMT,0),ifnull(OPTDELYN,'N'),ifnull(BARCODE,'') "
            		    + "  FROM Shop_OptProdIn "
            		    + " WHERE SENDSEQ = ? "
            		    + "   AND COMPNO = ?";

            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, sendseq);
            pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

            System.out.println("[getShopOptProdInListBySendseq]"+pstmt.toString());
            
            rs = pstmt.executeQuery();
            
            int idx = 0;
            while (rs.next()) {
				int rowIdx = 0;
				ShopOptionProductInDto dto = new ShopOptionProductInDto();
				dto.setRow(++idx+"");
				dto.setSendseq(Integer.parseInt(rs.getString(++rowIdx)));
				dto.setSkuseq(Integer.parseInt(rs.getString(++rowIdx)));
				dto.setCompno(Integer.parseInt(rs.getString(++rowIdx)));
				dto.setShopprodno(rs.getString(++rowIdx));
				dto.setShopprodsku(rs.getString(++rowIdx));
				dto.setShopprodskunm(rs.getString(++rowIdx));
				dto.setProdseq(rs.getString(++rowIdx));
				dto.setOptprodcd(rs.getString(++rowIdx));
				dto.setOptprodnm(rs.getString(++rowIdx));
				dto.setOptspecdes(rs.getString(++rowIdx));
				dto.setOptea(Integer.parseInt(rs.getString(++rowIdx)));
				dto.setOptsale(rs.getString(++rowIdx));
				dto.setOptsaleout(rs.getString(++rowIdx));
				dto.setOptnotuse(rs.getString(++rowIdx));
				dto.setOptsafestock(rs.getString(++rowIdx));
				dto.setOptvertstock(rs.getString(++rowIdx));
				dto.setOptaddamt(Integer.parseInt(rs.getString(++rowIdx)));
				dto.setOptdelyn(rs.getString(++rowIdx));
				dto.setBarcode(rs.getString(++rowIdx));
				
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
    
    public boolean checkExistShopOptProdIn(int sendseq) throws Exception {

        boolean flag = false;

        Connection connection = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();

            String sql = "select 1 from Shop_OptProdIn where sendseq = ?";
            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, sendseq);
            System.out.println("[checkExistShopOptProdIn]"+pstmt.toString());
            
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
    
    public void insertShopOptProdIn(int sendseq,  List<ShopOptionProductInDto> list) throws Exception {

    	Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
        
        try {
        	connection = DBCPInit.getInstance().getConnection();
            String sql = "insert into Shop_OptProdIn "
            		   + "(sendseq, skuseq, shopprodno, shopprodsku, shopprodskunm, "
            		   + " compno, prodseq, optprodcd, optprodnm, optspecdes, optea, "
            		   + " optsale, optsaleout, optnotuse, optsafestock, optvertstock,"
            		   + " optaddamt, optdelyn, barcode, insertdt, insertid) "
            		   + " values "
            		   + "(?, ?, ?, ?, ?, "
            		   + " ?, ?, ?, ?, ?, ?,"
            		   + " ?, ?, ?, ?, ?,"
            		   + " ?, ?, ?, ?, ?)" ;
            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for(ShopOptionProductInDto dto : list) {
				int rowIdx = 0;	
				pstmt.setInt(++rowIdx, dto.getSendseq());//
				pstmt.setInt(++rowIdx, dto.geSkuseq());
				pstmt.setString(++rowIdx, dto.getShopprodno());
				pstmt.setString(++rowIdx, dto.getShopprodsku());
				pstmt.setString(++rowIdx, dto.getShopprodskunm());
				pstmt.setInt(++rowIdx, dto.getCompno());
				pstmt.setString(++rowIdx, dto.getProdseq());
				pstmt.setString(++rowIdx, dto.getOptprodcd());
				pstmt.setString(++rowIdx, dto.getOptprodnm());
				pstmt.setString(++rowIdx, dto.getOptspecdes());
				pstmt.setInt(++rowIdx, dto.getOptea());
				pstmt.setString(++rowIdx, dto.getOptsale());
				pstmt.setString(++rowIdx, dto.getOptsaleout());
				pstmt.setString(++rowIdx, dto.getOptnotuse());
				pstmt.setString(++rowIdx, dto.getOptsafestock());
				pstmt.setString(++rowIdx, dto.getOptvertstock());
				pstmt.setInt(++rowIdx, dto.getOptaddamt());
				pstmt.setString(++rowIdx, dto.getOptdelyn());
				pstmt.setString(++rowIdx, dto.getBarcode()); 
				pstmt.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId());
				
				pstmt.addBatch();
				pstmt.clearParameters();
			}
			System.out.println("[ShopOptProdIn]"+pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters(); 
			
			} catch (Exception ex) {
				ex.printStackTrace();

			} finally {
				DBCPInit.getInstance().freeConnection(connection, pstmt, null);
			}

    }
    
    public int updateShopOptProdInBySku(int sendseq, int skuseq, String shopprodsku, String shopprodsku_nm) throws Exception {

        int result = 0;

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql  = "update Shop_OptProdIn "
            			+ "   set shopprodsku=?, shopprodsku_nm=?, updatedt=?, updateid=?"
             		    + " where sendseq= ? "
             		    + "   and skuseq = ? ";
            
            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, shopprodsku);
            pstmt.setString(2, shopprodsku_nm);

            pstmt.setString(3, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(4, YDMASessonUtil.getUserInfo().getUserId());
			
            pstmt.setInt(5, sendseq);
            pstmt.setInt(6, skuseq);
            
            System.out.println("[updateShopOptProdInBySku]"+pstmt.toString());

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

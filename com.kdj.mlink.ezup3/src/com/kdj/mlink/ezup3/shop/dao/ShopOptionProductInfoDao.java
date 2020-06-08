package com.kdj.mlink.ezup3.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;

public class ShopOptionProductInfoDao {
	
	public List<ShopOptionProductInDto> getShopOptProdInfoListByProdseq(String prodseq)  throws  Exception {

        List<ShopOptionProductInDto> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "SELECT ifnull(PRODSEQ,''),ifnull(SKUSEQ,0),COMPNO,ifnull(PRODCD,''),ifnull(OPTPRODCD,''),ifnull(OPTPRODNM,''),ifnull(OPTSPECDES,''),ifnull(OPTEA,0),"
            		   + " ifnull(OPTSALE,''),ifnull(OPTSALEOUT,''),ifnull(OPTNOTUSE,''),ifnull(OPTSAFESTOCK,''),ifnull(OPTVERTSTOCK,''),ifnull(OPTADDAMT,0),ifnull(OPTDELYN,''),"
            		   + " ifnull(BARCODE,'') "
            		   + "  FROM Shop_OptProdInfo "
            		   + " WHERE prodseq = ? ";

            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, prodseq);

            System.out.println("[getShopOptProdInfoListByProdseq]"+pstmt.toString());
            
            rs = pstmt.executeQuery();
            int idx = 0;
            while (rs.next()) {
				int rowIdx = 0;
				ShopOptionProductInDto dto = new ShopOptionProductInDto();
				dto.setRow(++idx+"");
				dto.setProdseq(rs.getString(++rowIdx));
				dto.setSkuseq(Integer.parseInt(rs.getString(++rowIdx)));
				dto.setCompno(Integer.parseInt(rs.getString(++rowIdx)));
				dto.setProdcd(rs.getString(++rowIdx));
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

	public String getProductseq(String productid) throws Exception {
		String prodseq = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql  = "SELECT b.prodseq "
            			+ "  FROM SHOPPRODIN b "
            			+ "		  join shopordmst a ON a.MALL_PRODUCT_ID = b.SHOPPRODNO "
            			+ " where a.MALL_PRODUCT_ID = ? ";

            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, productid);

            System.out.println("[getShopOptProdInfoListByProdseq]"+pstmt.toString());
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
				int rowIdx = 0;
				prodseq = rs.getString(++rowIdx);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;

        } finally {
            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
        }

		return prodseq;
	}
	
	public void setOptionProductInUpdate(List<ShopOptionProductInDto> list) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		
		String sql  = "update Shop_OptProdInfo "
					+ "	  set OPTSPECDES=?, OPTEA=?, OPTSALE=?, OPTSALEOUT=?, "
					+ "		  OPTNOTUSE = ?, OPTSAFESTOCK = ?, OPTVERTSTOCK = ?, OPTADDAMT = ?, "
					+ "		  BARCODE = ?, MODIFYDT = ?, MODIFYID =? "
      		   		+ " where PRODSEQ= ?  "
      		   		+ "   and SKUSEQ = ? "
      		   		+ "   and compno = ? ";
		sql = sql.toUpperCase();
		connection = DBCPInit.getInstance().getConnection();
		
		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);
		
		for (ShopOptionProductInDto dto : list) {
			int i = 0;
			pstmt.setString(++i, dto.getOptprodnm());
			pstmt.setInt(++i, dto.getOptea());
			pstmt.setString(++i, dto.getOptsale());
			pstmt.setString(++i, dto.getOptsaleout());
			pstmt.setString(++i, dto.getOptnotuse());
			pstmt.setString(++i, dto.getOptsafestock().equals("")?"0":dto.getOptsafestock());
			pstmt.setString(++i, dto.getOptvertstock().equals("")?"0":dto.getOptvertstock());
			pstmt.setInt(++i, dto.getOptaddamt());
			pstmt.setString(++i, dto.getBarcode());
			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUsernam());
			
			pstmt.setString(++i, dto.getProdseq());
			pstmt.setInt(++i, dto.getSkuseq());
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[setOptionProductInUpdate]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화
		
	}
	
}

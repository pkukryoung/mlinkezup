package com.kdj.mlink.ezup3.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;

public class ShopDeliveryMapDao {
	
//	public List<ShopOptionProductInDto> getShopOptProdInfoListByProdseq(String prodseq)  throws  Exception {
//
//        List<ShopOptionProductInDto> list = new ArrayList<>();
//
//        Connection connection = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            connection = DBCPInit.getInstance().getConnection();
//            String sql = "SELECT ifnull(PRODSEQ,''),ifnull(SKUSEQ,0),COMPNO,ifnull(PRODCD,''),ifnull(OPTPRODCD,''),ifnull(OPTPRODNM,''),ifnull(OPTSPECDES,''),ifnull(OPTEA,0),"
//            		   + " ifnull(OPTSALE,''),ifnull(OPTSALEOUT,''),ifnull(OPTNOTUSE,''),ifnull(OPTSAFESTOCK,''),ifnull(OPTVERTSTOCK,''),ifnull(OPTADDAMT,0),ifnull(OPTDELYN,''),"
//            		   + " ifnull(BARCODE,'') "
//            		   + "  FROM Shop_OptProdInfo "
//            		   + " WHERE prodseq = ? ";
//
//            sql = sql.toUpperCase();
//            
//            pstmt = connection.prepareStatement(sql);
//            pstmt.setString(1, prodseq);
//
//            System.out.println("[getShopOptProdInfoListByProdseq]"+pstmt.toString());
//            
//            rs = pstmt.executeQuery();
//            int idx = 0;
//            while (rs.next()) {
//				int rowIdx = 0;
//				ShopOptionProductInDto dto = new ShopOptionProductInDto();
//				dto.setRow(++idx+"");
//				dto.setProdseq(rs.getString(++rowIdx));
//				dto.setSkuseq(Integer.parseInt(rs.getString(++rowIdx)));
//				dto.setCompno(Integer.parseInt(rs.getString(++rowIdx)));
//				dto.setProdcd(rs.getString(++rowIdx));
//				dto.setOptprodcd(rs.getString(++rowIdx));
//				dto.setOptprodnm(rs.getString(++rowIdx));
//				dto.setOptspecdes(rs.getString(++rowIdx));
//				dto.setOptea(Integer.parseInt(rs.getString(++rowIdx)));
//				dto.setOptsale(rs.getString(++rowIdx));
//				dto.setOptsaleout(rs.getString(++rowIdx));
//				dto.setOptnotuse(rs.getString(++rowIdx));
//				dto.setOptsafestock(rs.getString(++rowIdx));
//				dto.setOptvertstock(rs.getString(++rowIdx));
//				dto.setOptaddamt(Integer.parseInt(rs.getString(++rowIdx)));
//				dto.setOptdelyn(rs.getString(++rowIdx));
//				dto.setBarcode(rs.getString(++rowIdx));
//                list.add(dto);
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw ex;
//
//        } finally {
//            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
//        }
//
//        return list;
//    }

	public String getProductseq(String dvlid, String shopcd, String dlvnm) throws Exception {
		String dlvid = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "SELECT ifnull(dlvid,'') FROM shopdlvmap a,shopdeliverys b" 
            		   + " WHERE a.DVLID = ? " 
            		   + "   AND a.shopcd = ? " 
            		   + "   AND a.shopcd = b.SHOPCD" 
            		   + "   AND b.dlvnm LIKE ? " 
            		   + "	AND a.SHOPDLV = b.dlvid";

            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, dvlid);
            pstmt.setString(2, shopcd);
            pstmt.setString(3, "%"+dlvnm+"%");

            System.out.println("[getShopOptProdInfoListByProdseq]"+pstmt.toString());
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
				int rowIdx = 0;
				dlvid = rs.getString(++rowIdx);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;

        } finally {
            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
        }

		return dlvid;
	}

}

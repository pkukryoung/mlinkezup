package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class ProductSubDao {

//	public boolean isExistProductSub(String prodcd, String proddtcd) throws Exception {
//
//		boolean flag = false;
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		ResultSet rs = null;
//		try {
//			conn = DBCPInit.getInstance().getConnection();
//
//			String sql = "select proddtcd from proddtl where prodcd=? and proddtcd=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, prodcd);
//			pstmt.setString(2, proddtcd);
//
//			rs = pstmt.executeQuery();
//			flag = rs.next();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw ex;
//		} finally {
//			DBCPInit.getInstance().freeConnection(conn, pstmt, rs);
//		}
//
//		return flag;
//	}

	/**
	 * ProdcutMst Code 로 ProdDtl 데이타 조회해서 Dto 에 담는 함수
	 * 
	 * @param productCode
	 * @return
	 * @throws Exception
	 */
//	public List<ProductSubDto> getProdcutSubListByProductCode(String productCode) throws Exception {
//
//		List<ProductSubDto> list = null;
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = DBCPInit.getInstance().getConnection();
//
//			String sql = "select prodcd, proddtcd, proddtnm, specdtdes, flagset, expdtcd ";
//			sql+=" from proddtl ";
//			sql+=" where prodcd =? ";
//
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, productCode);
//
//			rs = pstmt.executeQuery();
//			rs.last();
//
//			int rowcount = rs.getRow();
//			if (rowcount > 0) {
//				rs.beforeFirst();
//				list = new ArrayList<ProductSubDto>();
//				int rowno = 0;
//				while (rs.next()) {
//					int i=0;
//					ProductSubDto dto = new ProductSubDto();
//					dto.setRowno(""+(++rowno));//디비에서 조회한 값 세팅하는 거 아님
//					dto.setProdcd(rs.getString(++i));
//					dto.setProddtcd(rs.getString(++i));
//					dto.setProddtnm(rs.getString(++i));
//					dto.setSpecdtdes(rs.getString(++i));
//					dto.setFalgset(rs.getString(++i));
//					dto.setExpdtcd(rs.getString(++i));
//					list.add(dto);
//				}
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw ex;
//		} finally {
//			DBCPInit.getInstance().freeConnection(conn, pstmt, rs);
//		}
//
//		return list;
//	}

	/**
	 * 종속상품리스트 조회해서 스트링 리스트로 담는 함수
	 * 
	 * @param productCode
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getProdcutSubListByProductCode(String productCode) throws Exception {

		List<List<String>> list = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select proddtcd, ifnull(proddtnm, ''), ifnull(specdtdes, ''), ifnull(flagset, ''), ifnull(qtyset, ''), ifnull(levset, ''), ";
			sql += " ifnull(expdtcd,''), ifnull(useyn, ''), ifnull(delyn,''), ifnull(insertdt,''), ifnull(insertid,''), ifnull(modifydt, ''), ifnull(modifyid,'') ";
			sql += " from proddtl ";
			sql += " where prodcd =? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, productCode);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getProdcutSubListByProductCode]" + pstmt.toString());

			rs = pstmt.executeQuery();
			rs.last();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			int rowcount = rs.getRow();
			if (rowcount > 0) {
				rs.beforeFirst();
				list = new ArrayList<>();
				int rowno = 0;
				while (rs.next()) {
					List<String> rowList = new ArrayList<>();
					rowList.add("" + (++rowno));// 그리드에 출력할 순번 디비에서 조회한 값 세팅하는 거 아님
					for (int i = 1; i <= columnCount; i++) {
						rowList.add(rs.getString(i));
					}
					list.add(rowList);
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

	public ProductSubDto getProdcutSubInfoByMsteAndSubCode(String productMstCode, String productSubCode)
			throws Exception {

		ProductSubDto dto = null;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select prodcd, proddtcd, proddtnm, specdtdes, flagset, qtyset, levset , expdtcd ";
			sql += " , useyn, delyn, insertdt, insertid, modifydt, modifyid ";
			sql += " from proddtl ";
			sql += " where prodcd =? ";
			sql += " and proddtcd = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, productMstCode);
			pstmt.setString(2, productSubCode);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getProdcutSubInfoByMsteAndSubCode]" + pstmt.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int i = 0;
				dto = new ProductSubDto();
				dto.setProdcd(rs.getString(++i));
				dto.setProddtcd(rs.getString(++i));
				dto.setProddtnm(rs.getString(++i));
				dto.setSpecdtdes(rs.getString(++i));
				dto.setFalgset(rs.getString(++i));
				dto.setQtyset(rs.getString(++i));
				dto.setLevset(rs.getString(++i));
				dto.setExpdtcd(rs.getString(++i));
				dto.setUseyn(rs.getString(++i));
				dto.setDelyn(rs.getString(++i));
				dto.setInsertdt(rs.getString(++i));
				dto.setInsertid(rs.getString(++i));
				dto.setModifydt(rs.getString(++i));
				dto.setModifyid(rs.getString(++i));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	/**
	 * 종속상품코드 등록
	 *
	 * @throws Exception
	 */
	public int insertProdcutDtl(String prodcd, String proddtcd, String proddtnm, String specdtdes, String flagset,
			String qtyset, String levset, String expdtcd, String useyn, String delyn) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "insert into proddtl (compno, prodcd, proddtcd, proddtnm, specdtdes, ";
			sql += " flagset, qtyset, levset, expdtcd, useyn, delyn,  insertdt, insertid) ";
			sql += " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, prodcd);
			pstmt.setString(++i, proddtcd);
			pstmt.setString(++i, proddtnm);
			pstmt.setString(++i, specdtdes);
			pstmt.setString(++i, flagset);
			pstmt.setString(++i, qtyset); // pstmt.setInt(++i, Integer.parseInt(qtyset));
			pstmt.setString(++i, levset);
			pstmt.setString(++i, expdtcd);
			pstmt.setString(++i, useyn);
			pstmt.setString(++i, delyn);
			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUserId());

			System.out.println("[insertProdcutDtl]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public int deleteProductSub(String prodcd, String proddtcd, String flag) throws Exception {

		int result = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "update proddtl set delyn = ? ";
			sql += " where prodcd = ?";
			sql += " and prodcddtcd = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, flag);
			pstmt.setString(2, prodcd);
			pstmt.setString(3, proddtcd);
			pstmt.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[deleteProductSub]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public int updateProductSub(String prodcd, String proddtcd, String proddtnm, String specdtdes, String flagset,
			String qtyset, String levset, String expdtcd, String useyn, String delyn) throws Exception {

		int result = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "update proddtl set proddtnm=?, specdtdes=?, flagset=?, qtyset=?, levset=?, expdtcd=?, ";
			sql += "  useyn=?, delyn=?, modifydt=?, modifyid=?";
			sql += "  where prodcd=? and proddtcd=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, proddtnm);
			pstmt.setString(++i, specdtdes);
			pstmt.setString(++i, flagset);
			pstmt.setInt(++i, Integer.parseInt(qtyset));
			pstmt.setString(++i, levset);
			pstmt.setString(++i, expdtcd);
			pstmt.setString(++i, useyn);
			pstmt.setString(++i, delyn);
			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUserId());
			pstmt.setString(++i, prodcd); // 조건 key
			pstmt.setString(++i, proddtcd); // 조건 key
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[updateProductSub]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public boolean isExistOptProduct(String optprodcd) throws Exception {
		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select prodcd from proddtl where proddtcd = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, optprodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[isExistProduct]" + pstmt.toString());

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

	public boolean isExistProduct(String prodcd) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select prodcd from prodmst where prodcd = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[isExistProduct]" + pstmt.toString());

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

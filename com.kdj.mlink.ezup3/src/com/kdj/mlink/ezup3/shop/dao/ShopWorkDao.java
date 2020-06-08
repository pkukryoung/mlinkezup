package com.kdj.mlink.ezup3.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kdj.mlink.ezup3.shop.common.WorkStatus;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;

/*
 *  ��ǰ ������ �׼��� �Ѵ�. 
 */
public class ShopWorkDao {
	private static ShopWorkDao daoInstance = new ShopWorkDao(); // dao �̱������� ���� �Ѵ�.

	Map<String, String> mapAddrList = new HashMap<>();

	private ShopWorkDao() {
//		mapAddrList.put("shop0003", "shopaddr11stdtl");
//		mapAddrList.put("shop0004", "shopaddrinterparkdtl");
//		mapAddrList.put("shop0075", "shopaddrcoupangdtl");
//		mapAddrList.put("shop0110", "shopaddrcafe24dtl");
//		mapAddrList.put("shop0055", "shopaddrnaverstoredtl");
//		mapAddrList.put("shop0067", "shopaddrauctiondtl");
//		mapAddrList.put("shop0068", "shopaddrauctiondtl");
	}

	public static ShopWorkDao get() {
		return daoInstance;
	}

	public List<ShopWorkDto> getWorkAllList() throws Exception {

		List<ShopWorkDto> list = new ArrayList<ShopWorkDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append(" SELECT                           \r\n");
			sql.append("MAX(WORKSEQ) AS WORKSEQ,  \r\n "); // int,�۾������Ϸù�ȣ
			sql.append("IFNULL(SHOPCD,'' ) AS SHOPCD,  \r\n "); // ���θ��ڵ�
			sql.append("IFNULL(COMPNO,'' ) AS COMPNO,  \r\n "); // int,ȸ���ڵ�
			sql.append("IFNULL(WORKCD,'' ) AS WORKCD,  \r\n "); // �۾��ڵ�
			sql.append("IFNULL(WORKDETCD,'' ) AS WORKDETCD,  \r\n "); // �۾����ڵ�
			sql.append("IFNULL(INSERTID,'' ) AS INSERTID,  \r\n "); // �۾��ھ��̵�
			sql.append("IFNULL(WORKDATEFROM,'' ) AS WORKDATEFROM,  \r\n "); // �۾����۽ð�
			sql.append("IFNULL(WORKDATETO,'' ) AS WORKDATETO,  \r\n "); // �۾�����ð�
			sql.append("SUM(WORKDLAPSED) AS WORKDLAPSED,  \r\n "); // ����ð�
			sql.append("(CASE WHEN  MAX(WORKSEQ) = (COUNT(CASE WHEN WORKDETCD='W06' OR WORKDETCD='W05' OR WORKDETCD='W04'  OR WORKDETCD='W02' THEN '1'  END)) THEN '�Ϸ�' ELSE '������' END)  AS WORKSTATS,    \r\n "); // �ϷῩ��
			sql.append("FlOOR((COUNT(CASE WHEN WORKDETCD='W06' OR WORKDETCD='W05' OR WORKDETCD='W04'  OR WORKDETCD='W02' THEN '1'  END) / MAX(WORKSEQ)) * 100) AS WORKCURRRATE,  \r\n "); // ������
			sql.append("IFNULL(WORKMESSAGE,'' ) AS WORKMESSAGE,  \r\n "); // �޼���
			sql.append("IFNULL(GROUP_CONCAT( WORKLOG), '') AS WORKLOG,   \r\n "); // �󼼷ΰ�

			sql.append(" IFNULL(GROUP_CONCAT(PRODSEQ) ,'')  AS PRODSEQ,  \r\n "); // int,ǰ���ڵ�

			sql.append("IFNULL(SHOPSEQ,'' ) AS SHOPSEQ,  \r\n "); // SHOPDTL�Ϸù�ȣ
			sql.append("IFNULL(USER_ID,'' ) AS USER_ID,  \r\n "); // �α��ξ��̵�
			sql.append("IFNULL(ADDRSEQ,'' ) AS ADDRSEQ,  \r\n "); // �ΰ������ڵ�
			sql.append("IFNULL(SHOPCATNO,'' ) AS SHOPCATNO  \r\n "); // ī�װ��ڵ�

			sql.append(" FROM workmngr  where compno = ?  \r\n");
			sql.append(" GROUP BY workgrp ");

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getShopProductInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ShopWorkDto dto = new ShopWorkDto();
				dto.setRowno("" + (++rowno));// ��񿡼� ��ȸ�� �� �����ϴ� �� �ƴ�
				dto.setWorkseq(rs.getString("WORKSEQ")); // �۾������Ϸù�ȣ
				dto.setShopcd(rs.getString("SHOPCD")); // ���θ��ڵ�
				dto.setCompno(rs.getString("COMPNO")); // ȸ���ڵ�
				dto.setWorkcd(rs.getString("WORKCD")); // �۾��ڵ�
				dto.setWorkdetcd(rs.getString("WORKDETCD")); // �۾����ڵ�
				dto.setInsertid(rs.getString("INSERTID")); // �۾���
				dto.setWorkdatefrom(rs.getString("WORKDATEFROM")); // �۾����۽ð�
				dto.setWorkdateto(rs.getString("WORKDATETO")); // �۾�����ð�
				dto.setWorkdlapsed(rs.getString("WORKDLAPSED")); // ����ð�
				dto.setWorkstats(rs.getString("WORKSTATS")); // ����
				dto.setWorkcurrrate(rs.getString("WORKCURRRATE")); // �����
				dto.setWorkmessage(rs.getString("WORKMESSAGE")); // �޼���
				dto.setWorklog(rs.getString("WORKLOG")); // �󼼷ΰ�

				dto.setProdseq(rs.getString("PRODSEQ")); // ǰ���ڵ� Ÿ��:int

				dto.setShopseq(rs.getString("SHOPSEQ")); // SHOPDTL�Ϸù�ȣ
				dto.setUser_id(rs.getString("USER_ID")); //

				dto.setAddrseq(rs.getString("ADDRSEQ")); // �ΰ������ڵ�
				dto.setShopcatno(rs.getString("SHOPCATNO")); // ī�װ��ڵ�

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
	
	
	// workmngr�� ����ϱ�
		public void setWorkListInsert(List<ShopProductDto> prodList, ShopProductAdditionDto shopProductAdditionDto, ShoppingMallDetailDto shoppingMallDetailDto,  String ��ǰ���, String ���) throws Exception {
			Connection connection = null;
			List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

			ResultSet rs = null;
			try {
				connection = DBCPInit.getInstance().getConnection();
				// prodin�� �μ�Ʈ�ϱ�
				String sql = "INSERT INTO workmngr\r\n"
						+ " (WORKGRP,WORKSEQ, SHOPCD, COMPNO, WORKCD, WORKDETCD, INSERTID, WORKDATEFROM, WORKDATETO, WORKDLAPSED, WORKSTATS, WORKCURRRATE, WORKMESSAGE, WORKLOG, \r\n "// workmngr
																																										// ������
						+ " PRODSEQ, SHOPSEQ, USER_ID, ADDRSEQ, SHOPCATNO ) \r\n"// ī�װ��ڵ����
						+ " VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "// 12��
						+ " 	   ?, ?, ?, ?, ? ) ";// 13��

				PreparedStatement pstmt = connection.prepareStatement(sql);
				statementlist.add(pstmt);

				for (ShopProductDto dto : prodList) {
					int rowIdx = 0;

					pstmt.setString(++rowIdx, dto.getWorkgrp());
					pstmt.setString(++rowIdx, dto.getWorkseq());
					pstmt.setString(++rowIdx, shoppingMallDetailDto.getShopcd()); // ���θ��ڵ�
					pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno()); // ȸ���ڵ�
					pstmt.setString(++rowIdx, ��ǰ���); // �۾��ڵ�
					pstmt.setString(++rowIdx, ���); // �۾����ڵ�
					pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId()); // �۾���
					pstmt.setString(++rowIdx, ""); // �۾����۽ð�
					pstmt.setString(++rowIdx, ���.equals(WorkStatus.����) ? YDMATimeUtil.getCurrentTimeByYDFormat() : ""); // �۾�����ð�
					pstmt.setString(++rowIdx, ���.equals(WorkStatus.����) ? "0" : ""); // ����ð�
					pstmt.setString(++rowIdx, ���.equals(WorkStatus.����) ? "1�ǽ���" : ""); // ����
					pstmt.setString(++rowIdx, ���.equals(WorkStatus.����) ? "100%" : "0%"); // �����
					pstmt.setString(++rowIdx, ""); // �޼���
					pstmt.setString(++rowIdx, dto.getResult_text()); // �󼼷ΰ�
					pstmt.setString(++rowIdx, dto.getProdseq()); // ǰ���ڵ�

					pstmt.setString(++rowIdx, shoppingMallDetailDto.getShopseq()); // SHOPDTL�Ϸù�ȣ
					pstmt.setString(++rowIdx, shoppingMallDetailDto.getShoppingid()); //

					pstmt.setString(++rowIdx, shopProductAdditionDto.getSeq()); // �ΰ������ڵ�
					pstmt.setString(++rowIdx, dto.getShopCatInDto() == null ? "0" : dto.getShopCatInDto().getShopcatno()); // ī�װ��ڵ�

					pstmt.addBatch();
					pstmt.clearParameters(); // �Ķ���� �ʱ�ȭ
				}

				System.out.println("[setWorkListInsert]" + pstmt.toString());

				pstmt.executeBatch();
				pstmt.clearParameters();// Batch �ʱ�ȭ

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
			}

		}
	
	

	// workmngr�� ����ϱ�
	public void setWorkListInsert(ShopProductSendDto sendDto, String ��ǰ���, String ���) throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin�� �μ�Ʈ�ϱ�
			String sql = "INSERT INTO workmngr\r\n"
					+ " ( SHOPCD, COMPNO, WORKCD, WORKDETCD, INSERTID, WORKDATEFROM, WORKDATETO, WORKDLAPSED, WORKSTATS, WORKCURRRATE, WORKMESSAGE, WORKLOG, \r\n "// workmngr
																																									// ������
					+ " PRODSEQ, SHOPSEQ, USER_ID, ADDRSEQ, SHOPCATNO ) \r\n"// ī�װ��ڵ����
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "// 12��
					+ " 	   ?, ?, ?, ?, ? ) ";// 13��

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			List<ShopProductDto> prodList = sendDto.getShopProductDto();
			ShopProductAdditionDto shopProductAdditionDto = sendDto.getProductAdditionDto();
			ShoppingMallDetailDto shoppingMallDetailDto = sendDto.getShoppingMallDetailDto();

			for (ShopProductDto dto : prodList) {
				int rowIdx = 0;

				pstmt.setString(++rowIdx, shoppingMallDetailDto.getShopcd()); // ���θ��ڵ�
				pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno()); // ȸ���ڵ�
				pstmt.setString(++rowIdx, ��ǰ���); // �۾��ڵ�
				pstmt.setString(++rowIdx, ���); // �۾����ڵ�
				pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId()); // �۾���
				pstmt.setString(++rowIdx, ""); // �۾����۽ð�
				pstmt.setString(++rowIdx, ���.equals(WorkStatus.����) ? YDMATimeUtil.getCurrentTimeByYDFormat() : ""); // �۾�����ð�
				pstmt.setString(++rowIdx, ���.equals(WorkStatus.����) ? "0" : ""); // ����ð�
				pstmt.setString(++rowIdx, ���.equals(WorkStatus.����) ? "1�ǽ���" : ""); // ����
				pstmt.setString(++rowIdx, ���.equals(WorkStatus.����) ? "100%" : "0%"); // �����
				pstmt.setString(++rowIdx, ""); // �޼���
				pstmt.setString(++rowIdx, dto.getResult_text()); // �󼼷ΰ�
				pstmt.setString(++rowIdx, dto.getProdseq()); // ǰ���ڵ�

				pstmt.setString(++rowIdx, shoppingMallDetailDto.getShopseq()); // SHOPDTL�Ϸù�ȣ
				pstmt.setString(++rowIdx, shoppingMallDetailDto.getShoppingid()); //

				pstmt.setString(++rowIdx, shopProductAdditionDto.getSeq()); // �ΰ������ڵ�
				pstmt.setString(++rowIdx, dto.getShopCatInDto() == null ? "0" : dto.getShopCatInDto().getShopcatno()); // ī�װ��ڵ�

				pstmt.addBatch();
				pstmt.clearParameters(); // �Ķ���� �ʱ�ȭ
			}

			System.out.println("[setWorkListInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch �ʱ�ȭ

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public int getMultiThreadCnt() throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = " select ifnull(multithreadcnt,'0') from preferences ";

			pstmt = connection.prepareStatement(sql.toUpperCase());

			System.out.println("[getShopProductInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				result = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public ShopProductSendDto getWorkChoiceList(ShopProductSendDto sendDto) throws Exception {

		for (int i = 0; i < sendDto.getShopProductDto().size(); ++i) {
			PreparedStatement pstmt = null;
			Connection connection = null;
			ResultSet rs = null;
			List<List<String>> con = new ArrayList<>();
			List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
			try {
				connection = DBCPInit.getInstance().getConnection();
				StringBuffer with_cte = new StringBuffer();

//				with_cte.append("WITH PRODCDTMP AS ( \r\n");
//
//				if (i == sendDto.getShopProductDto().size() - 1) {
//					with_cte.append(String.format("SELECT %s AS PRODCD \r\n", params.get(i)));
//					with_cte.append(")");
//				} else {
//					with_cte.append(String.format("SELECT %s AS shopcd, %s AS shopseq, %s AS prodseq, %s AS USER_ID, %s AS addrseq UNION all", params.get(i)));
//				}

				// for(int k =1;k<=list.size()-1;k++) {
				String sql = String.format(" SELECT ifnull(WORKSEQ,'') from workmngr where shopcd = ? and workcd = '%s' and workdetcd = '%s' and prodseq = ? and shopseq = ? "
						+ " and user_id = ? and addrseq = ? and compno = ? ",WorkStatus.��ǰ���,WorkStatus.���);
				
				with_cte.append(sql);

				sql = with_cte.toString().toUpperCase();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, sendDto.getShoppingMallDetailDto().getShopcd());
				pstmt.setString(2, sendDto.getShopProductDto().get(i).getProdseq());
				pstmt.setString(3, sendDto.getShoppingMallDetailDto().getShopseq());
				pstmt.setString(4, sendDto.getShoppingMallDetailDto().getShoppingid());
				pstmt.setString(5, sendDto.getProductAdditionDto().getSeq());
				pstmt.setString(6, YDMASessonUtil.getCompnoInfo().getCompno());
				System.out.println(pstmt.toString());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					sendDto.getShopProductDto().get(i).setWorkseq(rs.getString(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
			}
		}
		return sendDto;
	}
	//������Ʈ�ϱ�
	public void setWorkstatsUpdate(ShopProductDto dto, String ������, String rate) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "update workmngr " + "   set WORKDETCD=?, WORKCURRRATE=?, WORKDATEFROM = ? "
					+ " where compno= ?  AND WORKGRP=? and WORKSEQ = ?   ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, ������);
			pstmt.setString(++rowIdx, rate);
			pstmt.setString(++rowIdx, dto.getWorkdatefrom());
			
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, dto.getWorkgrp());
			pstmt.setString(++rowIdx, dto.getWorkseq());

			System.out.println("[setWorkstatsUpdate]" + pstmt.toString());

			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	
	}

	public void setWorkstatsSuccessUpdate(ShopProductDto dto, String �Ϸ�, String rate, String workstats, String worklog) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "update workmngr set WORKDETCD=?, WORKCURRRATE=?, WORKDATETO = ?, WORKDLAPSED = ?, WORKSTATS = ?, WORKLOG = ? "
					+ " where compno= ? and WORKSEQ = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, �Ϸ�);
			pstmt.setString(++rowIdx, rate);
			pstmt.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++rowIdx, YDMATimeUtil.getOffset(dto.getWorkdatefrom(),YDMATimeUtil.getCurrentTimeByYDFormat()));
			pstmt.setString(++rowIdx, workstats);
			pstmt.setString(++rowIdx, worklog);
			
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, dto.getWorkseq());

			System.out.println("[setWorkstatsSuccessUpdate]" + pstmt.toString());

			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

}

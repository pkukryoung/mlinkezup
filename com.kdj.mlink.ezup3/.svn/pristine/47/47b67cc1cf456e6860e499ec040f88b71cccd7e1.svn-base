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
 *  상품 정보를 액세스 한다. 
 */
public class ShopWorkDao {
	private static ShopWorkDao daoInstance = new ShopWorkDao(); // dao 싱글톤으로 생성 한다.

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
			sql.append("MAX(WORKSEQ) AS WORKSEQ,  \r\n "); // int,작업관리일련번호
			sql.append("IFNULL(SHOPCD,'' ) AS SHOPCD,  \r\n "); // 쇼핑몰코드
			sql.append("IFNULL(COMPNO,'' ) AS COMPNO,  \r\n "); // int,회사코드
			sql.append("IFNULL(WORKCD,'' ) AS WORKCD,  \r\n "); // 작업코드
			sql.append("IFNULL(WORKDETCD,'' ) AS WORKDETCD,  \r\n "); // 작업상세코드
			sql.append("IFNULL(INSERTID,'' ) AS INSERTID,  \r\n "); // 작업자아이디
			sql.append("IFNULL(WORKDATEFROM,'' ) AS WORKDATEFROM,  \r\n "); // 작업시작시간
			sql.append("IFNULL(WORKDATETO,'' ) AS WORKDATETO,  \r\n "); // 작업종료시간
			sql.append("SUM(WORKDLAPSED) AS WORKDLAPSED,  \r\n "); // 경과시간
			sql.append("(CASE WHEN  MAX(WORKSEQ) = (COUNT(CASE WHEN WORKDETCD='W06' OR WORKDETCD='W05' OR WORKDETCD='W04'  OR WORKDETCD='W02' THEN '1'  END)) THEN '완료' ELSE '진행중' END)  AS WORKSTATS,    \r\n "); // 완료여부
			sql.append("FlOOR((COUNT(CASE WHEN WORKDETCD='W06' OR WORKDETCD='W05' OR WORKDETCD='W04'  OR WORKDETCD='W02' THEN '1'  END) / MAX(WORKSEQ)) * 100) AS WORKCURRRATE,  \r\n "); // 진행율
			sql.append("IFNULL(WORKMESSAGE,'' ) AS WORKMESSAGE,  \r\n "); // 메세지
			sql.append("IFNULL(GROUP_CONCAT( WORKLOG), '') AS WORKLOG,   \r\n "); // 상세로고

			sql.append(" IFNULL(GROUP_CONCAT(PRODSEQ) ,'')  AS PRODSEQ,  \r\n "); // int,품번코드

			sql.append("IFNULL(SHOPSEQ,'' ) AS SHOPSEQ,  \r\n "); // SHOPDTL일련번호
			sql.append("IFNULL(USER_ID,'' ) AS USER_ID,  \r\n "); // 로그인아이디
			sql.append("IFNULL(ADDRSEQ,'' ) AS ADDRSEQ,  \r\n "); // 부가정보코드
			sql.append("IFNULL(SHOPCATNO,'' ) AS SHOPCATNO  \r\n "); // 카테고리코드

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
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setWorkseq(rs.getString("WORKSEQ")); // 작업관리일련번호
				dto.setShopcd(rs.getString("SHOPCD")); // 쇼핑몰코드
				dto.setCompno(rs.getString("COMPNO")); // 회사코드
				dto.setWorkcd(rs.getString("WORKCD")); // 작업코드
				dto.setWorkdetcd(rs.getString("WORKDETCD")); // 작업상세코드
				dto.setInsertid(rs.getString("INSERTID")); // 작업자
				dto.setWorkdatefrom(rs.getString("WORKDATEFROM")); // 작업시작시간
				dto.setWorkdateto(rs.getString("WORKDATETO")); // 작업종료시간
				dto.setWorkdlapsed(rs.getString("WORKDLAPSED")); // 경과시간
				dto.setWorkstats(rs.getString("WORKSTATS")); // 상태
				dto.setWorkcurrrate(rs.getString("WORKCURRRATE")); // 진행률
				dto.setWorkmessage(rs.getString("WORKMESSAGE")); // 메세지
				dto.setWorklog(rs.getString("WORKLOG")); // 상세로고

				dto.setProdseq(rs.getString("PRODSEQ")); // 품번코드 타입:int

				dto.setShopseq(rs.getString("SHOPSEQ")); // SHOPDTL일련번호
				dto.setUser_id(rs.getString("USER_ID")); //

				dto.setAddrseq(rs.getString("ADDRSEQ")); // 부가정보코드
				dto.setShopcatno(rs.getString("SHOPCATNO")); // 카테고리코드

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
	
	
	// workmngr에 등록하기
		public void setWorkListInsert(List<ShopProductDto> prodList, ShopProductAdditionDto shopProductAdditionDto, ShoppingMallDetailDto shoppingMallDetailDto,  String 상품등록, String 대기) throws Exception {
			Connection connection = null;
			List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

			ResultSet rs = null;
			try {
				connection = DBCPInit.getInstance().getConnection();
				// prodin에 인설트하기
				String sql = "INSERT INTO workmngr\r\n"
						+ " (WORKGRP,WORKSEQ, SHOPCD, COMPNO, WORKCD, WORKDETCD, INSERTID, WORKDATEFROM, WORKDATETO, WORKDLAPSED, WORKSTATS, WORKCURRRATE, WORKMESSAGE, WORKLOG, \r\n "// workmngr
																																										// 고유값
						+ " PRODSEQ, SHOPSEQ, USER_ID, ADDRSEQ, SHOPCATNO ) \r\n"// 카테고리코드까지
						+ " VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "// 12개
						+ " 	   ?, ?, ?, ?, ? ) ";// 13개

				PreparedStatement pstmt = connection.prepareStatement(sql);
				statementlist.add(pstmt);

				for (ShopProductDto dto : prodList) {
					int rowIdx = 0;

					pstmt.setString(++rowIdx, dto.getWorkgrp());
					pstmt.setString(++rowIdx, dto.getWorkseq());
					pstmt.setString(++rowIdx, shoppingMallDetailDto.getShopcd()); // 쇼핑몰코드
					pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno()); // 회사코드
					pstmt.setString(++rowIdx, 상품등록); // 작업코드
					pstmt.setString(++rowIdx, 대기); // 작업상세코드
					pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId()); // 작업자
					pstmt.setString(++rowIdx, ""); // 작업시작시간
					pstmt.setString(++rowIdx, 대기.equals(WorkStatus.실패) ? YDMATimeUtil.getCurrentTimeByYDFormat() : ""); // 작업종료시간
					pstmt.setString(++rowIdx, 대기.equals(WorkStatus.실패) ? "0" : ""); // 경과시간
					pstmt.setString(++rowIdx, 대기.equals(WorkStatus.실패) ? "1건실패" : ""); // 상태
					pstmt.setString(++rowIdx, 대기.equals(WorkStatus.실패) ? "100%" : "0%"); // 진행률
					pstmt.setString(++rowIdx, ""); // 메세지
					pstmt.setString(++rowIdx, dto.getResult_text()); // 상세로고
					pstmt.setString(++rowIdx, dto.getProdseq()); // 품번코드

					pstmt.setString(++rowIdx, shoppingMallDetailDto.getShopseq()); // SHOPDTL일련번호
					pstmt.setString(++rowIdx, shoppingMallDetailDto.getShoppingid()); //

					pstmt.setString(++rowIdx, shopProductAdditionDto.getSeq()); // 부가정보코드
					pstmt.setString(++rowIdx, dto.getShopCatInDto() == null ? "0" : dto.getShopCatInDto().getShopcatno()); // 카테고리코드

					pstmt.addBatch();
					pstmt.clearParameters(); // 파라미터 초기화
				}

				System.out.println("[setWorkListInsert]" + pstmt.toString());

				pstmt.executeBatch();
				pstmt.clearParameters();// Batch 초기화

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
			}

		}
	
	

	// workmngr에 등록하기
	public void setWorkListInsert(ShopProductSendDto sendDto, String 상품등록, String 대기) throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = "INSERT INTO workmngr\r\n"
					+ " ( SHOPCD, COMPNO, WORKCD, WORKDETCD, INSERTID, WORKDATEFROM, WORKDATETO, WORKDLAPSED, WORKSTATS, WORKCURRRATE, WORKMESSAGE, WORKLOG, \r\n "// workmngr
																																									// 고유값
					+ " PRODSEQ, SHOPSEQ, USER_ID, ADDRSEQ, SHOPCATNO ) \r\n"// 카테고리코드까지
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "// 12개
					+ " 	   ?, ?, ?, ?, ? ) ";// 13개

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			List<ShopProductDto> prodList = sendDto.getShopProductDto();
			ShopProductAdditionDto shopProductAdditionDto = sendDto.getProductAdditionDto();
			ShoppingMallDetailDto shoppingMallDetailDto = sendDto.getShoppingMallDetailDto();

			for (ShopProductDto dto : prodList) {
				int rowIdx = 0;

				pstmt.setString(++rowIdx, shoppingMallDetailDto.getShopcd()); // 쇼핑몰코드
				pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno()); // 회사코드
				pstmt.setString(++rowIdx, 상품등록); // 작업코드
				pstmt.setString(++rowIdx, 대기); // 작업상세코드
				pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId()); // 작업자
				pstmt.setString(++rowIdx, ""); // 작업시작시간
				pstmt.setString(++rowIdx, 대기.equals(WorkStatus.실패) ? YDMATimeUtil.getCurrentTimeByYDFormat() : ""); // 작업종료시간
				pstmt.setString(++rowIdx, 대기.equals(WorkStatus.실패) ? "0" : ""); // 경과시간
				pstmt.setString(++rowIdx, 대기.equals(WorkStatus.실패) ? "1건실패" : ""); // 상태
				pstmt.setString(++rowIdx, 대기.equals(WorkStatus.실패) ? "100%" : "0%"); // 진행률
				pstmt.setString(++rowIdx, ""); // 메세지
				pstmt.setString(++rowIdx, dto.getResult_text()); // 상세로고
				pstmt.setString(++rowIdx, dto.getProdseq()); // 품번코드

				pstmt.setString(++rowIdx, shoppingMallDetailDto.getShopseq()); // SHOPDTL일련번호
				pstmt.setString(++rowIdx, shoppingMallDetailDto.getShoppingid()); //

				pstmt.setString(++rowIdx, shopProductAdditionDto.getSeq()); // 부가정보코드
				pstmt.setString(++rowIdx, dto.getShopCatInDto() == null ? "0" : dto.getShopCatInDto().getShopcatno()); // 카테고리코드

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}

			System.out.println("[setWorkListInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화

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
						+ " and user_id = ? and addrseq = ? and compno = ? ",WorkStatus.상품등록,WorkStatus.대기);
				
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
	//업데이트하기
	public void setWorkstatsUpdate(ShopProductDto dto, String 진행중, String rate) throws Exception {

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
			pstmt.setString(++rowIdx, 진행중);
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

	public void setWorkstatsSuccessUpdate(ShopProductDto dto, String 완료, String rate, String workstats, String worklog) throws Exception {

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
			pstmt.setString(++rowIdx, 완료);
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

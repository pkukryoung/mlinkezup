package com.kdj.mlink.ezup3.data.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class OrderDao {

	/**
	 * 해당 업로드일자, 차수의 주문내역중에 이카운트에 전송된 것이 있는지 확인
	 *
	 * @return
	 * @throws Exception
	 */
	public int getEcountSentOrderNum(String orddt, String ordseq) throws Exception {

		int count = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT count(ECOUNTSNDYN)  FROM ORDDTL where orddt=? and ordseq=? and ECOUNTSNDYN='Y' and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, orddt);
			pstmt.setString(2, ordseq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[checkEcountSentOrder]" + pstmt.toString());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return count;
	}

	/**
	 * ORDMST 에 날짜 차수가 존재하는지 확인.
	 *
	 * @param orddt
	 * @param ordseq
	 * @return
	 * @throws Exception
	 */
	public boolean existOrdmst(String orddt, String ordseq) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select * from ORDMST where orddt = ? and ordseq=? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, orddt);
			pstmt.setString(2, ordseq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[isExistOrder]" + pstmt.toString());

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

	//// 품목별판매순위
	public List<List<String>> getSalesRankItemList(String clmdtFrom, String clmdtTo, int opt, String optStr,
			String gunun) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "CALL YWM_PERIOD_SALE_LIST(?, ? ,? ,? , ?,?)";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, clmdtFrom);
			pstmt.setString(3, clmdtTo);
			pstmt.setInt(4, opt);
			pstmt.setString(5, "%" + optStr + "%");
			pstmt.setString(6, gunun);
			System.out.println("[getSalesRankItemList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 1;
			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add(String.valueOf(rowNum++));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
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

	public int[] processDeleteOrder(String orddt, int ordseq, int[] seqs) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		int[] result;

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			result = deleteOrder(connection, statementlist, orddt, ordseq, seqs);
			updateClaimFromOrderDel(connection, statementlist, orddt, ordseq, seqs);

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public int[] deleteOrder(Connection connection, List<PreparedStatement> statementlist, String orddt, int ordseq,
			int[] seqs) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int[] result;

		String sql = "DELETE FROM ORDDTL where orddt=? and ordseq=? and seq=? and compno = ? ";
		sql = sql.toUpperCase();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		for (int seq : seqs) {
			int i = 0;
			pstmt.setString(++i, orddt);
			pstmt.setInt(++i, ordseq);
			pstmt.setInt(++i, seq);
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[deleteOrder]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화
		}

		result = pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

		return result;
	}

	public void updateClaimFromOrderDel(Connection connection, List<PreparedStatement> statementlist, String orddt,
			int ordseq, int[] seqs) throws Exception {

		// update clmlist set orddt=null, ordseq=null, seq=null
		// where orddt='선택된 그리드' and ordseq=선택된그리드 and seq=선택된그리드

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql_orddtl = "update clmlist " + " set orddt=null,  ordseq=null, seq=null "
				+ " , UPDATEDT =? , UPDATEID=? " + " where orddt=? and ordseq=? and seq=? and compno = ? ";
		sql_orddtl = sql_orddtl.toUpperCase();

		pstmt = connection.prepareStatement(sql_orddtl);
		statementlist.add(pstmt);

		for (int seq : seqs) {
			int i = 0;
			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUserId());
			pstmt.setString(++i, orddt);
			pstmt.setInt(++i, ordseq);
			pstmt.setInt(++i, seq);
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[updateClaimFromOrderDel]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

	}

	/**
	 * 주문일자 차수 를 키로하여 ordmst , orddtl 에 주문내역을 인서트함
	 *
	 * @param orddt
	 * @param ordseq
	 * @param orderList
	 * @return
	 * @throws Exception
	 */
	public int insertOrderList(String orddt, String ordseq, List<List<String>> orderList) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_ordmst = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql_ordmst = " insert into ordmst (compno, orddt, ordseq, insertdt, insertid)  VALUES (?, ?, ?, ?, ?) ";
			sql_ordmst = sql_ordmst.toUpperCase();

			pstmt_ordmst = connection.prepareStatement(sql_ordmst);
			statementlist.add(pstmt_ordmst);
			pstmt_ordmst.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt_ordmst.setString(2, orddt);
			pstmt_ordmst.setString(3, ordseq);
			pstmt_ordmst.setString(4, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt_ordmst.setString(5, YDMASessonUtil.getUserInfo().getUserId());
			pstmt_ordmst.executeUpdate();

			System.out.println("[insertOrder-ordmst]" + pstmt_ordmst.toString());

//				String[][] column_name = {
//				{"No.","60"}, {"주문일시","160"}, {"수취인명","150"}, {"우편번호","90"}, {"주소","410"}, {"핸드폰","150"}, {"일반전화","150"},
//				{"수량","60"}, {"배송비","80"}, {"신용","100"}, {"옵션(수집)","400"}, {"상품코드","180"}, {"메시지","350"}, {"포장구분","100"}, {"배송구분","100"},
//				{"사방넷주문번호","150"}, {"쇼핑몰아이디","350"}, {"주문자명(예금주)","150"}, {"기타메시지","300"}, {"자사몰코드","300"}, {"주문합계","100"}
//		};

			String sql_orddtl = " insert into orddtl (compno, orddt, ordseq,  seq, rorddt, rcvnam, pstno, addr, clpno, telno, qty, shpfee, credit, "
					+ "  optdesc, prodcd, messge, pkgclss, shipcls, sabordno, shopid, ordnm, etcmsg, mallcd, ordamt)   "
					+ "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);
			statementlist.add(pstmt_orddtl);

			int rowIdx = 0;
			for (List<String> order : orderList) {
				pstmt_orddtl.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt_orddtl.setString(2, orddt);
				pstmt_orddtl.setString(3, ordseq);
				pstmt_orddtl.setInt(4, ++rowIdx); // order 의 seq 는 무시하고 새로 구성함. 아래 for문 i=1 부터 시작함

				for (int i = 1; i < order.size() - 1; i++) { // order의 마지막 값은 구분자임으로 포함시키지 않음.
					// System.out.println(" 주문 인서트 order 의 인덱스 사이즈 "+i+" "+ order.size());
					pstmt_orddtl.setString((i + 4), order.get(i));
				}

				// [[15, 2020-04-19 01:32, 진윤미, 08543, 서울특별시 금천구 시흥대로142길 44 (독산동) 보성팰리스 501호,
				// 010-7704-2544, 010-7704-2544, 1, 4000, 선결제, 몽글이하우스세트(DWH127) , DWH127, 집 앞에
				// 놓아주세요., 롯데택배, 택배, 20230552, 위메프(신) win182, 진윤미, , 174713071, 113900, N, 1,
				// 93]]

				// --사방넷

				// [78, 2019-03-26 00:00, 전용봉(올콕), 16528, 경기도 수원시 영통구 동탄원천로1109번길 92 (매탄동)
				// 동수원중학교 미술실, 010-8646-6598, 010-8646-6598, 1, 0, , 상품선택피노테이블_1200x600;
				// 색상망펄비치(상판)+블랙(프레임)_YYWD3012-BK , YWD5004-WN, , , 택배, 18869572, Cafe24 v1.9
				// ydinc, 주식회사 리퍼스, , , 0, T]
				// -- 주문서
				// [361, 2019-03-25 12:54, 김세옥, 617-15, 광주광역시 남구 진월동 484 진월3차 삼익세라믹아파트
				// 303동1603호, 0503-8236-1934, 0503-8236-1934, 1, , 착불, [와이디] 피노 테이블,
				// 800x600:망펄비치(상판)+블랙(프레임)_YWD3011-BK, YWD3011-BK, 경비실, , 택배, 18867588, 쿠팡
				// ydinc2014, 김세옥, 피노테이블 800x600:망펄비치(상판)+블랙(프레임)_YWD3011-BK 안드로이드앱, 피노테이블,
				// 38200, 102038-1485, T]
				// 주문서 최대인덱스[22] ([15]는 사방넷, 가격은 [20]) --[361, 2019-03-25 12:54, 김세옥, 617-15,
				// 광주광역시 남구 진월동 484 진월3차 삼익세라믹아파트 303동1603호,
				// 0503-8236-1934, 0503-8236-1934, 1, , 착불, [와이디] 피노 테이블,
				// 800x600:망펄비치(상판)+블랙(프레임)_YWD3011-BK, YWD3011-BK,
				// 경비실, , 택배, 18867588, 쿠팡 ydinc2014, 김세옥, 피노테이블
				// 800x600:망펄비치(상판)+블랙(프레임)_YWD3011-BK 안드로이드앱, 피노테이블, 38200, 102038-1485, T]

				System.out.println("[insertOrder-orddtl]" + pstmt_orddtl.toString());

				pstmt_orddtl.addBatch();
				pstmt_orddtl.clearParameters(); // 파라미터 초기화
			}

			pstmt_orddtl.executeBatch();
			pstmt_orddtl.clearParameters(); // Batch 초기화

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	/**
	 * orddtl 에서 기존 주문일자 차수 의 주문내역을 삭제하고 새로운 주문내역으로 갱신함.
	 *
	 * @param orddt
	 * @param ordseq
	 * @param orderList
	 * @return
	 * @throws Exception
	 */
	public int updateloadOrderList(String orddt, String ordseq, List<List<String>> orderList) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_delete_orddtl = null;
		PreparedStatement pstmt_insert_orddtl = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql_ordmst = " delete from orddtl where orddt=? and ordseq=? and compno = ? ";
			sql_ordmst = sql_ordmst.toUpperCase();

			pstmt_delete_orddtl = connection.prepareStatement(sql_ordmst);
			statementlist.add(pstmt_delete_orddtl);

			pstmt_delete_orddtl.setString(1, orddt);
			pstmt_delete_orddtl.setString(2, ordseq);
			pstmt_delete_orddtl.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[updateloadOrderList-ordmst]" + pstmt_delete_orddtl.toString());

			pstmt_delete_orddtl.executeUpdate();

//				String[][] column_name = {
//				{"No.","60"}, {"주문일시","160"}, {"수취인명","150"}, {"우편번호","90"}, {"주소","410"}, {"핸드폰","150"}, {"일반전화","150"},
//				{"수량","60"}, {"배송비","80"}, {"신용","100"}, {"옵션(수집)","400"}, {"상품코드","180"}, {"메시지","350"}, {"포장구분","100"}, {"배송구분","100"},
//				{"사방넷주문번호","150"}, {"쇼핑몰아이디","350"}, {"주문자명(예금주)","150"}, {"기타메시지","300"}, {"자사몰코드","300"}, {"주문합계","100"}
//		};

			String sql_orddtl = " insert into orddtl (compno, orddt, ordseq, seq, rorddt, rcvnam, pstno, addr, clpno, telno, qty, shpfee, credit, "
					+ "  optdesc, prodcd, messge, pkgclss, shipcls, sabordno, shopid, ordnm, etcmsg, mallcd, ordamt)   "
					+ "  VALUES (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_insert_orddtl = connection.prepareStatement(sql_orddtl);
			statementlist.add(pstmt_insert_orddtl);

			int rowIdx = 0;
			for (List<String> order : orderList) {
				pstmt_insert_orddtl.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt_insert_orddtl.setString(2, orddt);
				pstmt_insert_orddtl.setString(3, ordseq);
				pstmt_insert_orddtl.setInt(4, ++rowIdx);// order 의 seq 는 무시하고 새로 구성함. 아래 for문 i=1 부터 시작함
				for (int i = 1; i < order.size() - 1; i++) { // order의 마지막 값은 구분자임으로 포함시키지 않음.
					pstmt_insert_orddtl.setString((i + 4), order.get(i));
				}

				System.out.println("[updateloadOrderList-orddtl]" + pstmt_insert_orddtl.toString());

				pstmt_insert_orddtl.addBatch();
				pstmt_insert_orddtl.clearParameters(); // 파라미터 초기화
			}

			pstmt_insert_orddtl.executeBatch();
			pstmt_insert_orddtl.clearParameters(); // Batch 초기화

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public List<List<String>> getOrderListForOrderManage(String orddt, String ordseq) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select seq, ifnull(rorddt,''), ifnull(rcvnam,''), ifnull(pstno,''),  ";
			sql += " ifnull(addr,''), ifnull(clpno,''), ifnull(telno,''), ifnull(qty,0),  ifnull(shpfee,''), ifnull(credit,''),";
			sql += " ifnull(optdesc,''), ifnull(prodcd,'') prodcd, ifnull(messge,''), ifnull((SELECT max(expcd) FROM V_PRODUCTS b where b.prodcd=a.prodcd),'') as pkgclss, ";
			sql += " ifnull(shipcls,''), ifnull(sabordno,''), ifnull(shopid,''), ifnull(ordnm,''),  ";
			sql += " ifnull(etcmsg,''), ifnull(mallcd,''), ifnull(ordamt, 0),  ifnull(ECOUNTSNDYN,'N') ";
			sql += " FROM ORDDTL a";
			sql += " where orddt=? and ordseq=? and a.compno = ? ";
			sql += " order by seq ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, orddt);
			pstmt.setString(2, ordseq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getOrderListForOrderManage]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i)); // ECOUNTSNDYN
				list.add(ordseq);// 전체로 인한 차수 넣기

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

	public List<List<String>> getOrderListForOrderManageByCondition(String orddt, String ordseq, int opt, String optStr)
			throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select seq, ifnull(rorddt,''), ifnull(rcvnam,'') as rcvnam, ifnull(pstno,''),  ";
			sql += " ifnull(addr,''), ifnull(clpno,''), ifnull(telno,''), ifnull(qty,0),  ifnull(shpfee,''), ifnull(credit,''),";
			sql += " ifnull(optdesc,'') as optdesc, ifnull(prodcd,'') as prodcd, ifnull(messge,''), ifnull((SELECT max(expcd) FROM V_PRODUCTS b where b.prodcd=a.prodcd and b.compno = a.compno ),'') as pkgclss, ";
			sql += " ifnull(shipcls,''), ifnull(sabordno,''), ifnull(shopid,''), ifnull(ordnm,'') as ordnm,  ";
			sql += " ifnull(etcmsg,''), ifnull(mallcd,''), ifnull(ordamt,0),  ifnull(ECOUNTSNDYN,'N') ";
			sql += " FROM ORDDTL a";
			sql += " where orddt=? and ordseq=? and a.compno = ? ";

			// {상품코드", "상품명", "수취인명", "주문자명"})
			if (opt == 0) {
				if (optStr.length() != 0) {
					sql += " and  prodcd like ? ";
				}
				sql += "  order by prodcd ";
			} else if (opt == 1) {
				if (optStr.length() != 0) {
					sql += " and  optdesc like ? ";
				}
				sql += "  order by optdesc ";
			} else if (opt == 2) {
				if (optStr.length() != 0) {
					sql += " and  rcvnam like ? ";
				}
				sql += "  order by rcvnam ";
			} else if (opt == 3) {
				if (optStr.length() != 0) {
					sql += " and  ordnm like ? ";
				}
				sql += "  order by ordnm ";
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, orddt);
			pstmt.setString(2, ordseq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
//			if(opt==0 || opt==1) {
//				if(optStr.length() != 0) {
//					pstmt.setString(3, "%" + optStr + "%");
//				}
//			}
			if (optStr.length() != 0) {
				pstmt.setString(4, "%" + optStr + "%");
			}

			System.out.println("[getOrderListForOrderManageByCondition]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i)); // ECOUNTSNDYN
				list.add(ordseq);

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

	public List<List<String>> getOrderListEcountSend(String orddt, String ordseq, String chksend) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		CallableStatement cStmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

//			String sql = " select seq, ifnull(rorddt,''), ifnull(rcvnam,''), ifnull(pstno,''),  ";
//			sql += " ifnull(addr,''), ifnull(clpno,''), ifnull(telno,''), ifnull(qty,''),  ifnull(shpfee,''), ifnull(credit,''),";
//			sql += " IFNULL(b.prodnm,''), ifnull(ecountcd,'') AS prodcd, ifnull(messge,''), ifnull(pkgclss,''), ";
//			sql += " ifnull(shipcls,''), ifnull(sabordno,''), ifnull(shopid,''), ifnull(ordnm,''),  ";
//			sql += " ifnull(etcmsg,''), ifnull(mallcd,''), ifnull(ordamt,''), ifnull(ECOUNTSNDYN,'N') ";
//			sql += " FROM ORDDTL a , V_PRODUCTS b  "; //이카운트 주문 차감은 prodmst(상품마스타)와 proddtl(상품서브)를 통합한 V_PRODUCTS 사용함
//			sql += " where orddt=? and ordseq=? ";
//			//sql += " AND a.ordamt !=0 "; //-- 주문금액이 0 인것은 뺌
//			sql += " AND ifnull(a.ECOUNTSNDYN,'N') = 'N' ";
//			sql += " AND a.PRODCD=b.prodcd  ";
//			sql += " order by seq ";

			cStmt = connection.prepareCall("{call YWM_WAREHOUSE_QTY(?, ?, ?, ?)}");
			cStmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			cStmt.setString(2, orddt);
			cStmt.setString(3, ordseq);
			cStmt.setString(4, chksend);

			System.out.println("[getOrderListForEcount]" + cStmt.toString());

			rs = cStmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));//
				list.add(rs.getString(i));// 이미지
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));// ECOUNTSNDYN
				list.add(rs.getString(++i));
				contents.add(list);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, cStmt, rs);
		}

		return contents;
	}

	/**
	 * 주문내역관리에서 주문 추가시 호출.
	 *
	 * @param orddt
	 * @param ordseq
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public List<String> insertOrdmst_Orddtl(String orddt, String ordseq, List<String> order) throws Exception {

		List<String> insertdNewOrder = new ArrayList<>();
		Connection connection = null;

		PreparedStatement pstmt_select_ordmst = null;
		PreparedStatement pstmt_select_seq = null;
		PreparedStatement pstmt_insert_orddtl = null;
		PreparedStatement pstmt_select_orddtl = null;

		List<PreparedStatement> pstmtlist = new ArrayList<>();
		List<ResultSet> rslist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql_insert_ordmst = "insert into ORDMST (compno, orddt, ordseq, insertdt, insertid) values (?, ?,?,?,?) ";
			sql_insert_ordmst = sql_insert_ordmst.toUpperCase();
			pstmt_select_ordmst = connection.prepareStatement(sql_insert_ordmst);
			pstmtlist.add(pstmt_select_ordmst);
			pstmt_select_ordmst.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt_select_ordmst.setString(2, orddt);
			pstmt_select_ordmst.setString(3, ordseq);
			pstmt_select_ordmst.setString(4, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt_select_ordmst.setString(5, YDMASessonUtil.getUserInfo().getUserId());
			System.out.println("[insertOrdmst_Orddtl-ordmst]" + pstmt_select_ordmst.toString());

			pstmt_select_ordmst.executeUpdate();

			int SEQ = -1;
			String sql_seq = "SELECT ifnull(MAX(SEQ)+1, 1)  FROM ORDDTL  WHERE ORDDT=? AND ORDSEQ=? and compno = ? ";
			sql_seq = sql_seq.toUpperCase();
			pstmt_select_seq = connection.prepareStatement(sql_seq);
			pstmtlist.add(pstmt_select_seq);
			pstmt_select_seq.setString(1, orddt);
			pstmt_select_seq.setString(2, ordseq);
			pstmt_select_seq.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[insertOrdmst_Orddtl-seq]" + pstmt_select_seq.toString());

			ResultSet rs_select_seq = pstmt_select_seq.executeQuery();
			rslist.add(rs_select_seq);

			if (rs_select_seq.next()) {
				SEQ = rs_select_seq.getInt(1);
			} else {
				SEQ = 1;
			}

			String sql_insert = " insert into orddtl (compno, orddt, ordseq,  seq, rorddt, rcvnam, pstno, addr, "
					+ " clpno, telno, qty, shpfee, credit,  optdesc, prodcd, messge, pkgclss, shipcls, "
					+ " sabordno, shopid, ordnm, etcmsg, mallcd, ordamt) "
					+ " VALUES (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			sql_insert = sql_insert.toUpperCase();
			pstmt_insert_orddtl = connection.prepareStatement(sql_insert);
			pstmtlist.add(pstmt_insert_orddtl);
			pstmt_insert_orddtl.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt_insert_orddtl.setString(2, orddt);
			pstmt_insert_orddtl.setString(3, ordseq);
			pstmt_insert_orddtl.setInt(4, SEQ);
			for (int i = 0; i < order.size(); i++) {
				pstmt_insert_orddtl.setString((i + 5), order.get(i));
			}
			System.out.println("[insertOrdmst_Orddtl-insert]" + pstmt_insert_orddtl.toString());

			pstmt_insert_orddtl.executeUpdate();

			String sql_select = " select seq, ifnull(rorddt,''), ifnull(rcvnam,''), ifnull(pstno,''),  "
					+ " ifnull(addr,''), ifnull(clpno,''), ifnull(telno,''), ifnull(qty,''),  ifnull(shpfee,''), ifnull(credit,''),"
					+ " ifnull(optdesc,''), ifnull(prodcd,''), ifnull(messge,''), ifnull((SELECT max(expcd) FROM V_PRODUCTS b where b.prodcd=prodcd),'') as pkgclss, "
					+ " ifnull(shipcls,''), ifnull(sabordno,''), ifnull(shopid,''), ifnull(ordnm,''),  "
					+ " ifnull(etcmsg,''), ifnull(mallcd,''), ifnull(ordamt,''),  ifnull(ECOUNTSNDYN,'N') "
					+ " FROM ORDDTL " + " where orddt=? and ordseq=? and seq=? and compno = ? ";
			sql_select = sql_select.toUpperCase();
			pstmt_select_orddtl = connection.prepareStatement(sql_select);
			pstmtlist.add(pstmt_select_orddtl);

			pstmt_select_orddtl.setString(1, orddt);
			pstmt_select_orddtl.setString(2, ordseq);
			pstmt_select_orddtl.setInt(3, SEQ);
			pstmt_select_orddtl.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[insertOrdmst_Orddtl-select]" + pstmt_insert_orddtl.toString());
			ResultSet rs_select_orddtl = pstmt_select_orddtl.executeQuery();
			rslist.add(rs_select_orddtl);

			int i = 0;
			if (rs_select_orddtl.next()) {
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i)); // ECOUNTSNDYN
			}
			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmtlist, rslist);
		}

		return insertdNewOrder;
	}

	public List<String> insertOrddtl(String orddt, String ordseq, List<String> order) throws Exception {

		List<String> insertdNewOrder = new ArrayList<>();
		Connection connection = null;

		PreparedStatement pstmt_select_seq = null;
		PreparedStatement pstmt_insert_orddtl = null;
		PreparedStatement pstmt_select_orddtl = null;

		List<PreparedStatement> pstmtlist = new ArrayList<>();
		List<ResultSet> rslist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			int SEQ = -1;
			String sql_seq = "SELECT ifnull(MAX(SEQ)+1, 1)  FROM ORDDTL  WHERE ORDDT=? AND ORDSEQ=? and compno = ? ";
			sql_seq = sql_seq.toUpperCase();
			pstmt_select_seq = connection.prepareStatement(sql_seq);
			pstmtlist.add(pstmt_select_seq);
			pstmt_select_seq.setString(1, orddt);
			pstmt_select_seq.setString(2, ordseq);
			pstmt_select_seq.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[insertOrddtl-seq]" + pstmt_select_seq.toString());

			ResultSet rs_select_seq = pstmt_select_seq.executeQuery();
			rslist.add(rs_select_seq);

			if (rs_select_seq.next()) {
				SEQ = rs_select_seq.getInt(1);
			} else {
				SEQ = 1;
			}

			String sql_insert = " insert into orddtl (compno, orddt, ordseq,  seq, rorddt, rcvnam, pstno, addr, "
					+ " clpno, telno, qty, shpfee, credit,  optdesc, prodcd, messge, pkgclss, shipcls, "
					+ " sabordno, shopid, ordnm, etcmsg, mallcd, ordamt) "
					+ " VALUES (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			sql_insert = sql_insert.toUpperCase();
			pstmt_insert_orddtl = connection.prepareStatement(sql_insert);
			pstmtlist.add(pstmt_insert_orddtl);
			pstmt_insert_orddtl.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt_insert_orddtl.setString(2, orddt);
			pstmt_insert_orddtl.setString(3, ordseq);
			pstmt_insert_orddtl.setInt(4, SEQ);
			for (int i = 0; i < order.size(); i++) {
				pstmt_insert_orddtl.setString((i + 5), order.get(i));
			}
			System.out.println("[insertOrddtl-insert]" + pstmt_insert_orddtl.toString());

			pstmt_insert_orddtl.executeUpdate();

			String sql_select = " select seq, ifnull(rorddt,''), ifnull(rcvnam,''), ifnull(pstno,''),  "
					+ " ifnull(addr,''), ifnull(clpno,''), ifnull(telno,''), ifnull(qty,''),  ifnull(shpfee,''), ifnull(credit,''),"
					+ " ifnull(optdesc,''), ifnull(prodcd,''), ifnull(messge,''), ifnull((SELECT max(expcd) FROM V_PRODUCTS b where b.prodcd=prodcd and b.compno = compno ),'') as pkgclss, "
					+ " ifnull(shipcls,''), ifnull(sabordno,''), ifnull(shopid,''), ifnull(ordnm,''),  "
					+ " ifnull(etcmsg,''), ifnull(mallcd,''), ifnull(ordamt,''),  ifnull(ECOUNTSNDYN,'N') "
					+ " FROM ORDDTL " + " where orddt=? and ordseq=? and seq=? and compno = ? ";
			sql_select = sql_select.toUpperCase();
			pstmt_select_orddtl = connection.prepareStatement(sql_select);
			pstmtlist.add(pstmt_select_orddtl);

			pstmt_select_orddtl.setString(1, orddt);
			pstmt_select_orddtl.setString(2, ordseq);
			pstmt_select_orddtl.setInt(3, SEQ);
			pstmt_select_orddtl.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[insertOrddtl-select]" + pstmt_insert_orddtl.toString());
			ResultSet rs_select_orddtl = pstmt_select_orddtl.executeQuery();
			rslist.add(rs_select_orddtl);

			int i = 0;
			if (rs_select_orddtl.next()) {
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i));
				insertdNewOrder.add(rs_select_orddtl.getString(++i)); // ECOUNTSNDYN
			}
			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmtlist, rslist);
		}

		return insertdNewOrder;
	}

	public List<String> updateOrderDetail(String orddt, String ordseq, int seq, List<String> order) throws Exception {

		List<String> insertdNewOrder = new ArrayList<>();
		Connection connection = null;

		PreparedStatement pstmt_update = null;
		PreparedStatement pstmt_select = null;

		List<PreparedStatement> pstmtlist = new ArrayList<>();
		ResultSet rs = null;

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql_update = " update orddtl set rorddt=?, rcvnam=?, pstno=?, addr=?, "
					+ " clpno=?, telno=?, qty=?, shpfee=?, credit=?,  optdesc=?, prodcd=?, "
					+ " messge=?, pkgclss=?, shipcls=?, sabordno=?, shopid=?, ordnm=?, etcmsg=?, mallcd=?, ordamt=? "
					+ " where orddt =? and ordseq=? and seq=? and compno = ? ";
			sql_update = sql_update.toUpperCase();
			pstmt_update = connection.prepareStatement(sql_update);
			pstmtlist.add(pstmt_update);
			int idx = 0;
			for (String value : order) {
				++idx;
				pstmt_update.setString((idx), value);
			}

			pstmt_update.setString(++idx, orddt);
			pstmt_update.setString(++idx, ordseq);
			pstmt_update.setInt(++idx, seq);
			pstmt_update.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[updateOrderDetail-update]" + pstmt_update.toString());
			pstmt_update.executeLargeUpdate();

			String sql_select = " select seq, ifnull(rorddt,''), ifnull(rcvnam,''), ifnull(pstno,''),  "
					+ " ifnull(addr,''), ifnull(clpno,''), ifnull(telno,''), ifnull(qty,''),  ifnull(shpfee,''), ifnull(credit,''),"
					+ " ifnull(optdesc,''), ifnull(prodcd,''), ifnull(messge,''), ifnull((SELECT max(expcd) FROM V_PRODUCTS b where b.prodcd=prodcd and b.compno = compno ),'') as pkgclss, "
					+ " ifnull(shipcls,''), ifnull(sabordno,''), ifnull(shopid,''), ifnull(ordnm,''),  "
					+ " ifnull(etcmsg,''), ifnull(mallcd,''), ifnull(ordamt,''),  ifnull(ECOUNTSNDYN,'N') "
					+ " FROM ORDDTL " + " where orddt=? and ordseq=? and seq=? and compno = ? ";

			sql_select = sql_select.toUpperCase();
			pstmt_select = connection.prepareStatement(sql_select);
			pstmtlist.add(pstmt_select);

			pstmt_select.setString(1, orddt);
			pstmt_select.setString(2, ordseq);
			pstmt_select.setInt(3, seq);
			pstmt_select.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[updateOrderDetail-select]" + pstmt_select.toString());

			rs = pstmt_select.executeQuery();

			if (rs.next()) {
				int i = 0;
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i));
				insertdNewOrder.add(rs.getString(++i)); // ECOUNTSNDYN
			}

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmtlist, rs);
		}

		return insertdNewOrder;
	}

//	public List<String> checkStock(List<String> list) throws Exception{
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			String sql = "SELECT YWM_FUNC_PRODWAREQTY(?, ?) FROM DUAL;";
//			sql = sql.toUpperCase();
//
//			connection = DBCPInit.getInstance().getConnection();
//			pstmt = connection.prepareStatement(sql);
//
//			String orddt =  list.get(1);
//			String prodcd = list.get(2);
//
//			pstmt.setString(1, orddt);
//			pstmt.setString(2, prodcd);
//
//			System.out.println("[checkStock]" + pstmt.toString());
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				int cont = rs.getInt(1);
//				list.add(String.valueOf(cont));
//				System.out.println("재고 :" + cont + " : " + list.get(0));
//			} else {
//				list.add("0");
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw ex;
//		} finally {
//			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
//		}
//
//		return list;
//	}

	public int checkStock1(String orddt, String prodcd) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int stockCnt = 0;

		try {

			String sql = "SELECT YWM_FUNC_PRODWAREQTY(?, ?, ?) FROM DUAL;";
			sql = sql.toUpperCase();

			connection = DBCPInit.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, orddt);
			pstmt.setString(3, prodcd);

			// System.out.println("[checkStock1]"+pstmt.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				stockCnt = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return stockCnt;
	}

	// 택배사별코드가져오기
	public List<List<String>> getExpressCodeList() throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(DLVID,''), ifnull(DLVNM,''), ifnull(EXPCD_LOTT,'') from deliverys ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			System.out.println("[getExpressCodeList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int rowno = 0;
				List<String> list = new ArrayList<>();
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));

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

	public boolean isPhonenumber(String string) throws Exception {
		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select errno from errphone where errno = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, string);
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

	// 입고확정 자동화
	public void setOrderAutomation(List<String> list) throws Exception {
		Connection connection = null;
		CallableStatement cStmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			cStmt = connection.prepareCall("{CALL YWM_ORDERAUTO_PROC(?, ?,?,?,?,?)}");
			cStmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			cStmt.setString(2, list.get(0));
			cStmt.setString(3, list.get(1));
			cStmt.setString(4, list.get(2));
			cStmt.setInt(5, Integer.parseInt(list.get(3)));
			cStmt.setString(6, list.get(4));

			System.out.println("[setOrderAutomation]" + cStmt.toString());

			rs = cStmt.executeQuery();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, cStmt, rs);
		}

	}

	public List<List<String>> getMoveSeqList(String orddt, String ordseq, String seq) throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(SABORDNO,'')  from ORDDTL where orddt = ? and ordseq = ? and moveseq = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, orddt);
			pstmt.setString(2, ordseq);
			pstmt.setString(3, seq);
			pstmt.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getMoveSeqList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int rowno = 0;
				List<String> list = new ArrayList<>();
				list.add("");// 그리드 컬럼맞추려고
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add(rs.getString(++rowno));
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");

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

	public void setOrderAutoUpdate(String seq, String orddt, String ordseq) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

//			updateProduct(connection, statementlist, prodcd,  prodnm,  specdes,  flagset,  flagplus,
//					 flagout,  price,  tagprice,  sabcd,  remark,  ea,  useyn,  delyn, cusprice, aproinvt);

			String sql = "update orddtl set COMPLETEyn = 'Y' where ORDDT = ? and SEQ = ? and ordseq = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, orddt);
			pstmt.setString(++i, seq);
			pstmt.setString(++i, ordseq);
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[setOrderAutoUpdate]" + pstmt.toString());
			pstmt.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public List<String> isExistComplate(String seq, String ordseq, String orddt) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(completeyn,'N') from orddtl where orddt = ? and ordseq = ? and seq =? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, orddt);
			pstmt.setString(2, ordseq);
			pstmt.setString(3, seq);
			pstmt.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[isExistComplate]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int rowno = 0;
				list.add(rs.getString(++rowno));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<String> getExpcd_Lott(String exp) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(DLVID,''), ifnull(DLVNM,''), ifnull(EXPCD_LOTT,'') from deliverys where DLVNM = ?";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, exp);
			System.out.println("[isExistComplate]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int rowno = 0;
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<List<String>> getCompleteyn(String orddt, String chkordseq) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(ORDDT,''), ifnull(ORDSEQ,'0'), ifnull(SEQ,'0'), ifnull(COMPLETEYN,'N'), ifnull(prodcd,'') from ORDDTL where ORDDT = ? and ORDSEQ=? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, orddt);
			pstmt.setString(2, chkordseq);
			System.out.println("[isExistComplate]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int rowno = 0;
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
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

	public List<String> getDvlInfo(String dvlid) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(DVLID,''), ifnull(URL,''), ifnull(USER_ID,''),ifnull(PASSWD,'') from dvlinfo where compno = ? and dvlid = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, dvlid);
			System.out.println("[isExistComplate]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int rowno = 0;
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

//	public List<List<String>> checkStock2(List<List<String>> checkContent) throws Exception{
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int[] result;
//		try {
//
//			String sql = "SELECT YWM_FUNC_PRODWAREQTY(?, ?) FROM DUAL;";
//			sql = sql.toUpperCase();
//
//			connection = DBCPInit.getInstance().getConnection();
//			pstmt = connection.prepareStatement(sql);
//
//			for (List<String> list  : checkContent) {
//
//				pstmt.setString(1, list.get(1));
//				pstmt.setString(2, list.get(2));
//
////				System.out.println("[checkStock]" + pstmt.toString());
////				if(list.get(0).equals("170")) {
////					break;
////				}
//				rs = pstmt.executeQuery();
//				int aaa = rs.getMetaData().getColumnCount();
//				ResultSetMetaData rsMa = rs.getMetaData();
//				if(rs.next()) {
//					int cont = rs.getInt(1);
//					list.add(String.valueOf(cont));
//					//System.out.println("재고 :"+cont + " : "+list.get(0));
//				}else {
//					list.add("0");
//				}
//				DBCPInit.getInstance().freeResultSet(rs);
//
//				pstmt.clearParameters();
//			}
//
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw ex;
//		} finally {
//			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
//		}
//
//		return checkContent;
//	}

}

package com.kdj.mlink.ezup3.data.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class ClaimDao {

	public void claimAutoProcessing(String clmdt, String clmseq, String user) throws Exception {
		Connection connection = null;
		CallableStatement cStmt = null;
		ResultSet rs = null;

		try {

			connection = DBCPInit.getInstance().getConnection();

			cStmt = connection.prepareCall("{call YWM_CLAIMAUTO_PROC(?, ?, ?,?)}");
			cStmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			cStmt.setString(2, clmdt);
			cStmt.setString(3, clmseq);
			cStmt.setString(4, user);

			System.out.println("[claimAutoProcessing]" + cStmt.toString());

			cStmt.execute();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, cStmt, rs);
		}

	}

	public List<String> getClaimInfo(String clmdt, String clmseq) throws Exception {

		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT A.REGDT,  A.ORDID, IFNULL(A.EXPCD,''), IFNULL(A.MALLCD,''), IFNULL(A.RCVNAM,''), IFNULL(A.CLPNO,''), "
					+ " IFNULL(A.RESALNAM,''), IFNULL(A.RESALTEL,''), IFNULL(A.RESALINV,''),  IFNULL(A.PROCTYPE,''), IFNULL(A.PROCDESC,''), IFNULL(A.CLMUSER,''),"
					+ " IFNULL(A.ADDR,''), IFNULL(A.PRODCD,''),  "
					+ " case ifnull((select SPECDES from V_PRODUCTS B where A.PRODCD = B.PRODCD and A.compno = B.compno ),'') when '' then concat(ifnull((select prodnm from V_PRODUCTS B where A.PRODCD = B.PRODCD and A.compno = B.compno ),'')) "
					+ " ELSE concat(ifnull((select prodnm from V_PRODUCTS B where A.PRODCD = B.PRODCD and A.compno = B.compno ),''), '_',(select SPECDES from V_PRODUCTS B where A.PRODCD = B.PRODCD and A.compno = B.compno )) end as PRODNM, "
					+ " IFNULL(A.QTY,0),  IFNULL(A.CLMCONTS,''),  IFNULL(A.DIRBNK,''),  "
					+ " IFNULL(A.DIRACNO,''), IFNULL(A.DIRNAM,''),  IFNULL(A.DIRAMT,'0'), IFNULL(a.SUPPAKNM,'0'), IFNULL(A.SUPPNAM,''), IFNULL(A.SUPPAMT,'0'), "
					+ " IFNULL(A.RETPAKNM,'0'), IFNULL(A.RETNAM,''), IFNULL(A.RETAMT,'0'),   IFNULL(A.EXPPROCNM,''), IFNULL(A.REWDNAM,''), IFNULL(A.REWDAMT,'0'), "
					+ " IFNULL(A.REWDFARE,'0'), IFNULL(A.REWDPAKAMT,'0'),  CLMDT, CLMSEQ  , CONCAT(IFNULL(RECVDT,''),IFNULL(RECVSEQ,'')) ,  "
					+ " CONCAT(IFNULL(ORDDT,''), IFNULL(ORDSEQ,''), IFNULL(SEQ,'')) " + " FROM CLMLIST A  "
					+ " WHERE A.CLMDT = ? " + " AND A.CLMSEQ = ? and A.compno = ? "
					+ " ORDER BY A.CLMDT DESC, A.REGDT DESC, A.ORDID DESC ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, clmdt);
			pstmt.setString(2, clmseq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getClaimInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int columnIndex = 0;
				list.add("9999");// list의 인덱스숫자를 맞추기 위해 삽입
				// list.add(YDMATimeUtil.convertSabangNetOrderDateToOrddt(rs.getString(++columnIndex)));
				// // regdt
				list.add(rs.getString(++columnIndex)); // regdt
				list.add(rs.getString(++columnIndex)); // ordid
				list.add(rs.getString(++columnIndex)); // expcd
				list.add(rs.getString(++columnIndex)); // mallcd
				list.add(rs.getString(++columnIndex)); // rcvnam
				list.add(rs.getString(++columnIndex)); // clpno
				list.add(rs.getString(++columnIndex)); // resalnam
				list.add(rs.getString(++columnIndex)); // resaltel
				list.add(rs.getString(++columnIndex)); // resalinv
				list.add(rs.getString(++columnIndex)); // proctype
				list.add(rs.getString(++columnIndex)); // procdesc
				list.add(rs.getString(++columnIndex)); // clmuser
				list.add(rs.getString(++columnIndex)); // addr
				list.add(rs.getString(++columnIndex)); // prodcd 1 //13
				list.add(rs.getString(++columnIndex)); // prodnm 1 //14
				list.add(rs.getString(++columnIndex)); // QTY 1 //15
				list.add(rs.getString(++columnIndex)); // clmconts
				list.add(rs.getString(++columnIndex)); // 은행
				list.add(rs.getString(++columnIndex)); // 계좌
				list.add(rs.getString(++columnIndex)); // 이름
				list.add(rs.getString(++columnIndex)); // 금액

				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));// clmdt
				list.add(rs.getString(++columnIndex));// clmseq
				// list.add(rs.getString(++columnIndex)); // prodcd 2 //32
				// list.add(rs.getString(++columnIndex)); // prodnm 2 //33
				// list.add(rs.getString(++columnIndex)); // QTY 2 //34
				// list.add(rs.getString(++columnIndex)); // prodcd 2 //35
				// list.add(rs.getString(++columnIndex)); // prodnm 2 //36
				// list.add(rs.getString(++columnIndex)); // QTY 2 //37

				// --자동화 처리 결과
				list.add(rs.getString(++columnIndex));// concat(recvdt, recvseq)
				list.add(rs.getString(++columnIndex));// concat(orddt, ordseq,seq)

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<List<String>> getClaimListDtlInfo(String clmdt, String clmseq) throws Exception {

		List<List<String>> content = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			// String sql = " select gubun, prodcd, qty FROM clmlistdtl WHERE clmdt=? AND
			// clmseq=? ";
			String sql = "SELECT A.gubun, A.prodcd, " + " concat(B.prodnm,'_',ifnull(B.specdes,'')) , "
					+ " A.price, A.qty, ifnull(A.cusprice,'0') "
					+ " FROM clmlistdtl A LEFT JOIN  v_products B On  A.PRODCD = B.PRODCD and A.compno = B.compno "
					+ "	WHERE A.clmdt=?  AND A.clmseq=? and A.compno = ? " + "	Order by seq asc ";

			// String[][] column_name = { {"구분", "80"}, { "상품코드", "160" }, { "상품명", "350" },
			// { "규격", "150" }, { "수량", "60" } };

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, clmdt);
			pstmt.setString(2, clmseq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getClaimListDtlInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add("" + rs.getInt(++columnIndex));
				list.add("" + rs.getInt(++columnIndex));
				content.add(list);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return content;
	}

	public List<List<String>> getClaimListDtl2Info(String clmdt, String clmseq) throws Exception {

		List<List<String>> content = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT clmindt, clmusernm, clmcontents " + " FROM clmlistdtl2 "
					+ "	WHERE clmdt=?  AND clmseq=? and compno = ? " + "	Order by clmindt desc ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, clmdt);
			pstmt.setString(2, clmseq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getClaimListDtl2Info]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				content.add(list);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return content;
	}

	public List<List<String>> getClaimListByClmDt(String dateFrom, String dateTo, String clmSeqFrom, String clmSeqTo)
			throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			if (!dateFrom.equals(dateTo)) {
				String sql = "SELECT k.* "
						+ "  FROM ( SELECT A.clmseq, A.regdt, A.ordid, ifnull(A.EXPCD,'') EXPCD, ifnull(A.mallcd,'') mallcd, ifnull(A.rcvnam,'') rcvnam, ifnull(A.clpno,'') clpno, "
						+ "					ifnull(A.resalnam,'') resalnam, ifnull(A.resaltel,'') resaltel, ifnull(A.resalinv,'') resalinv, "
						+ "					ifnull(A.proctype,'') proctype, ifnull(A.procdesc,'') procdesc, ifnull(A.clmuser,'') clmuser, ifnull(A.addr,'') addr, ifnull(A.prodcd,'') prodcd,"
//						   + "					concat(ifnull(B.prodnm,''), '_', ifnull(B.SPECDES,'') ) as prodnm," 
						+ "					CASE WHEN IFNULL(A.PRODCD,'')='' THEN '' ELSE  "
						+ "					(SELECT CASE WHEN IFNULL(B.SPECDES,'') = '' THEN IFNULL(B.PRODNM,'') ELSE CONCAT(IFNULL(B.PRODNM,''),'_',IFNULL(B.SPECDES,'')) END FROM V_PRODUCTS B WHERE B.COMPNO = A.COMPNO AND B.PRODCD = A.PRODCD)"
						+ "					END AS PRODNM," + "					ifnull(A.qty,0) qty, "
						+ "					ifnull(A.clmconts,'') clmconts,"
						+ "					ifnull(A.dirbnk,'') dirbnk, ifnull(A.diracno,'') diracno, ifnull(A.dirnam,'') dirnam,  ifnull(A.diramt,0) diramt,"
						+ "					ifnull(A.suppaknm,0) suppaknm, ifnull(A.suppnam,'') suppnam, ifnull(A.suppamt,0) suppamt, ifnull(A.retpaknm,0) retpaknm, ifnull(A.retnam,'') retnam, ifnull(A.retamt,0) retamt, "
						+ "					ifnull(A.expprocnm,'') expprocnm, ifnull(A.rewdnam,'') rewdnam, ifnull(A.rewdamt,0) rewdamt, ifnull(A.rewdfare,0) rewdfare, ifnull(A.rewdpakamt,0) rewdpakamt, "
						+ "					clmdt , clmseq clmseq2, "
						+ "					concat(ifnull(recvdt,''),ifnull(recvseq,'')) recvno, concat(ifnull(orddt,''), ifnull(ordseq,''), ifnull(seq,''))  ordno"
						+ "			 FROM clmlist A "
						+ "			WHERE A.clmdt = ? ANd A.clmseq >= ? and A.compno = ? " + "			union ALL "
						+ "			SELECT A.clmseq, A.regdt, A.ordid, ifnull(A.EXPCD,'') EXPCD, ifnull(A.mallcd,'') mallcd, ifnull(A.rcvnam,'') rcvnam, ifnull(A.clpno,'') clpno, "
						+ "					ifnull(A.resalnam,'') resalnam, ifnull(A.resaltel,'') resaltel, ifnull(A.resalinv,'') resalinv, "
						+ "					ifnull(A.proctype,'') proctype, ifnull(A.procdesc,'') procdesc, ifnull(A.clmuser,'') clmuser, ifnull(A.addr,'') addr, ifnull(A.prodcd,'') prodcd,"
//						   + "					concat(ifnull(B.prodnm,''), '_', ifnull(B.SPECDES,'') ) as prodnm,"  
						+ "					CASE WHEN IFNULL(A.PRODCD,'')='' THEN '' ELSE  "
						+ "					(SELECT CASE WHEN IFNULL(B.SPECDES,'') = '' THEN IFNULL(B.PRODNM,'') ELSE CONCAT(IFNULL(B.PRODNM,''),'_',IFNULL(B.SPECDES,'')) END FROM V_PRODUCTS B WHERE B.COMPNO = A.COMPNO AND B.PRODCD = A.PRODCD)"
						+ "					END AS PRODNM," + "					ifnull(A.qty,0) qty, "
						+ "					ifnull(A.clmconts,'') clmconts,"
						+ "					ifnull(A.dirbnk,'') dirbnk, ifnull(A.diracno,'') diracno, ifnull(A.dirnam,'') dirnam,  ifnull(A.diramt,0) diramt,"
						+ "					ifnull(A.suppaknm,0) suppaknm, ifnull(A.suppnam,'') suppnam, ifnull(A.suppamt,0) suppamt, ifnull(A.retpaknm,0) retpaknm, ifnull(A.retnam,'') retnam, ifnull(A.retamt,0) retamt, "
						+ "					ifnull(A.expprocnm,'') expprocnm, ifnull(A.rewdnam,'') rewdnam, ifnull(A.rewdamt,0) rewdamt, ifnull(A.rewdfare,0) rewdfare, ifnull(A.rewdpakamt,0) rewdpakamt, 				"
						+ "					clmdt , clmseq clmseq2, "
						+ "					concat(ifnull(recvdt,''),ifnull(recvseq,'')) recvno, concat(ifnull(orddt,''), ifnull(ordseq,''), ifnull(seq,''))  ordno"
						+ "			 FROM clmlist A " + "			WHERE A.clmdt > ? ANd A.clmdt < ? and A.compno = ? "
						+ "			union all"
						+ "			SELECT A.clmseq, A.regdt, A.ordid, ifnull(A.EXPCD,'') EXPCD, ifnull(A.mallcd,'') mallcd, ifnull(A.rcvnam,'') rcvnam, ifnull(A.clpno,'') clpno, "
						+ "					ifnull(A.resalnam,'') resalnam, ifnull(A.resaltel,'') resaltel, ifnull(A.resalinv,'') resalinv, "
						+ "					ifnull(A.proctype,'') proctype, ifnull(A.procdesc,'') procdesc, ifnull(A.clmuser,'') clmuser, ifnull(A.addr,'') addr, ifnull(A.prodcd,'') prodcd,"
//						   + "					concat(ifnull(B.prodnm,''), '_', ifnull(B.SPECDES,'') ) as prodnm," 
						+ "					CASE WHEN IFNULL(A.PRODCD,'')='' THEN '' ELSE  "
						+ "					(SELECT CASE WHEN IFNULL(B.SPECDES,'') = '' THEN IFNULL(B.PRODNM,'') ELSE CONCAT(IFNULL(B.PRODNM,''),'_',IFNULL(B.SPECDES,'')) END FROM V_PRODUCTS B WHERE B.COMPNO = A.COMPNO AND B.PRODCD = A.PRODCD)"
						+ "					END AS PRODNM," + "					ifnull(A.qty,0) qty, "
						+ "					ifnull(A.clmconts,'') clmconts,"
						+ "					ifnull(A.dirbnk,'') dirbnk, ifnull(A.diracno,'') diracno, ifnull(A.dirnam,'') dirnam,  ifnull(A.diramt,0) diramt,"
						+ "					ifnull(A.suppaknm,0) suppaknm, ifnull(A.suppnam,'') suppnam, ifnull(A.suppamt,0) suppamt, ifnull(A.retpaknm,0) retpaknm, ifnull(A.retnam,'') retnam, ifnull(A.retamt,0) retamt, "
						+ "					ifnull(A.expprocnm,'') expprocnm, ifnull(A.rewdnam,'') rewdnam, ifnull(A.rewdamt,0) rewdamt, ifnull(A.rewdfare,0) rewdfare, ifnull(A.rewdpakamt,0) rewdpakamt, 				"
						+ "					clmdt , clmseq clmseq2, "
						+ "					concat(ifnull(recvdt,''),ifnull(recvseq,'')) recvno, concat(ifnull(orddt,''), ifnull(ordseq,''), ifnull(seq,''))  ordno"
						+ "			 FROM clmlist A  "
						+ "			WHERE A.clmdt = ? AND A.clmseq <= ? and A.compno = ? ) k "
						+ "ORDER BY k.clmdt DESC, k.regdt DESC, k.ordid DESC";
				sql = sql.toUpperCase();

				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, dateFrom);
				pstmt.setString(2, clmSeqFrom);
				pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(4, dateFrom);
				pstmt.setString(5, dateTo);
				pstmt.setString(6, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(7, dateTo);
				pstmt.setString(8, clmSeqTo);
				pstmt.setString(9, YDMASessonUtil.getCompnoInfo().getCompno());
			} else {
				String sql = "SELECT A.CLMSEQ, A.REGDT, A.ORDID, IFNULL(A.EXPCD,''), IFNULL(A.MALLCD,''), IFNULL(A.RCVNAM,''), IFNULL(A.CLPNO,''),   	\r\n"
						+ "		IFNULL(A.RESALNAM,''), IFNULL(A.RESALTEL,''), IFNULL(A.RESALINV,''), IFNULL(A.PROCTYPE,''), IFNULL(A.PROCDESC,''), \r\n"
						+ "		IFNULL(A.CLMUSER,''), IFNULL(A.ADDR,''),IFNULL(A.PRODCD,''),  		 \r\n"
						+ "		CASE WHEN IFNULL(A.PRODCD,'')='' THEN '' ELSE  \r\n"
						+ "			(SELECT CASE WHEN IFNULL(B.SPECDES,'') = '' THEN IFNULL(B.PRODNM,'') ELSE CONCAT(IFNULL(B.PRODNM,''),'_',IFNULL(B.SPECDES,'')) END FROM V_PRODUCTS B WHERE B.COMPNO = A.COMPNO AND B.PRODCD = A.PRODCD)\r\n"
						+ "		END AS PRODNM,\r\n"
						+ "		IFNULL(A.QTY,0),  IFNULL(A.CLMCONTS,''),  IFNULL(A.DIRBNK,''),\r\n"
						+ "		IFNULL(A.DIRACNO,''), IFNULL(A.DIRNAM,''),  IFNULL(A.DIRAMT,0), IFNULL(A.SUPPAKNM,0),\r\n"
						+ "		IFNULL(A.SUPPNAM,''), IFNULL(A.SUPPAMT,0), IFNULL(A.RETPAKNM,0), IFNULL(A.RETNAM,''), \r\n"
						+ "		IFNULL(A.RETAMT,0), IFNULL(A.EXPPROCNM,''), IFNULL(A.REWDNAM,''), IFNULL(A.REWDAMT,0),\r\n"
						+ "		IFNULL(A.REWDFARE,0), IFNULL(A.REWDPAKAMT,0), CLMDT, CLMSEQ,  		 \r\n"
						+ "		CONCAT(IFNULL(RECVDT,''),IFNULL(RECVSEQ,'')), CONCAT(IFNULL(ORDDT,''), IFNULL(ORDSEQ,''), IFNULL(SEQ,''))   \r\n"
						+ "  FROM CLMLIST A \r\n" + " WHERE A.CLMDT = ? AND A.CLMSEQ >= ? "
						+ "   AND A.CLMSEQ <= ? \r\n" + "	AND A.COMPNO = ? \r\n"
						+ " ORDER BY A.CLMDT DESC, A.REGDT DESC, A.ORDID DESC";

				sql = sql.toUpperCase();

				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, dateFrom);
				pstmt.setString(2, clmSeqFrom);
				pstmt.setString(3, clmSeqTo);
				pstmt.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());
			}

			System.out.println("[getClaimListByClmDt]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				List<String> list = new ArrayList<>();
				// list.add(String.valueOf(rowNum++)); //UI NO.
				list.add(rs.getString(++columnIndex)); // clmseq (1)
				list.add(rs.getString(++columnIndex)); // regdt
				list.add(rs.getString(++columnIndex)); // ordid
				list.add(rs.getString(++columnIndex)); // EXPCD
				list.add(rs.getString(++columnIndex)); // mallcd
				list.add(rs.getString(++columnIndex)); // rcvnam
				list.add(rs.getString(++columnIndex)); // clpno
				list.add(rs.getString(++columnIndex)); // resalnam
				list.add(rs.getString(++columnIndex)); // resaltel
				list.add(rs.getString(++columnIndex)); // resalinv
				list.add(rs.getString(++columnIndex)); // proctype
				list.add(rs.getString(++columnIndex)); // procdesc
				list.add(rs.getString(++columnIndex)); // clmuser
				list.add(rs.getString(++columnIndex)); // addr
				list.add(rs.getString(++columnIndex)); // prodcd
				list.add(rs.getString(++columnIndex)); // prodnm
				list.add(rs.getString(++columnIndex)); // QTY(17)

				list.add(rs.getString(++columnIndex)); // clmconts

				list.add(rs.getString(++columnIndex)); // 은행
				list.add(rs.getString(++columnIndex)); // 계좌
				list.add(rs.getString(++columnIndex)); // 이름
				list.add(rs.getString(++columnIndex)); // 총액

				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex)); // suppnam(23)
				list.add(rs.getString(++columnIndex)); // suppamt
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex)); // retnam
				list.add(rs.getString(++columnIndex)); // retamt
				list.add(rs.getString(++columnIndex)); // expprocnm
				list.add(rs.getString(++columnIndex)); // rewdnam
				list.add(rs.getString(++columnIndex)); // rewdamt
				list.add(rs.getString(++columnIndex)); // rewdfare
				list.add(rs.getString(++columnIndex)); // rewdpakamt(31)

				list.add(rs.getString(++columnIndex)); // clmdt
				list.add(rs.getString(++columnIndex)); // clmseq

				// --자동화 처리 결과
				list.add(rs.getString(++columnIndex)); // concat(recvdt, recvseq)(34)
				list.add(rs.getString(++columnIndex)); // concat(orddt, ordseq,seq)(35)

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

	public List<List<String>> getClaimListByOption(int opt, String optStr, String clmdtFrom, String clmdtTo, int opt2,
			String optStr2) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT A.clmseq,  A.regdt, A.ordid, ifnull(A.EXPCD,''), ifnull(A.mallcd,''), ifnull(A.rcvnam,''), ifnull(A.clpno,''),  " // 크레임등록일,
																																					// 주문번호,
																																					// 택배사,
																																					// 쇼핑몰,
																																					// 수취인,
																																					// 폰번호
					+ " ifnull(A.resalnam,''), ifnull(A.resaltel,''), ifnull(A.resalinv,''), " // 리셀러 이름, 전화, 송장
					+ " ifnull(A.proctype,''), ifnull(A.procdesc,''), ifnull(A.clmuser,''), ifnull(A.addr,''), ifnull(A.prodcd,''), "
					+ " concat(ifnull(B.prodnm,''), '_', ifnull(B.SPECDES,'') )  as prodnm, " + " ifnull(A.qty,0), " // 처리유형,
																														// 처리내용,
																														// 주소,
																														// 상품코드,
																														// 상품명,
																														// 수량
					+ " ifnull(A.clmconts,''), " // 접수내용
					+ " ifnull(A.dirbnk,''), ifnull(A.diracno,''), ifnull(A.dirnam,''),  ifnull(A.diramt,0), " // 은행,
																												// ,계좌,
																												// 이름,
																												// 금액
					+ " ifnull(A.suppaknm,0), ifnull(A.suppnam,''), ifnull(A.suppamt,0), ifnull(A.retpaknm,0), ifnull(A.retnam,''), ifnull(A.retamt,0),  " // 출고부담업체,
																																							// 출고부담운임,
																																							// 반품부답업체,
																																							// 반품부담금액
					+ " ifnull(A.expprocnm,''), ifnull(A.rewdnam,''), ifnull(A.rewdamt,0), ifnull(A.rewdfare,0), ifnull(A.rewdpakamt,0), " // 처리(택배회사),
																																			// 보상업체,
																																			// 보상품값,
																																			// 보상운임,보상포장비
					+ " clmdt, clmseq "
					+ " , concat(ifnull(recvdt,''),ifnull(recvseq,'')), concat(ifnull(orddt,''), ifnull(ordseq,''), ifnull(seq,'')) " // 자동화
																																		// 처리
																																		// 결과
					+ " FROM clmlist A LEFT JOIN  v_products B On  A.PRODCD = B.PRODCD and A.compno = B.compno "
					+ " WHERE A.clmdt >= ? ANd A.clmdt <= ? and A.compno = ? ";
			// + " ORDER BY A.clmdt DESC, A.regdt DESC, A.ordid DESC";
			if (opt2 == 0) {
				sql += " and  A.addr like ? ";
			} else if (opt2 == 1) {
				sql += " and  A.resaltel like ? ";
			} else {
				sql += " and A.rcvnam like ? ";
			}

			// 성명, 구매일, 상품명
			if (opt == 0) {
				if (optStr.length() != 0) {
					sql += " and  A.rcvnam like ? ";
				}
				sql += " order by A.rcvnam ";
			} else if (opt == 1) {

			} else if (opt == 2) {
				if (optStr.length() != 0) {
					sql += " and B.prodnm like ? ";
				}
				sql += "  order by B.prodnm ";
			} else if (opt == 3) {
				if (optStr.length() != 0) {
					sql += " and A.clmuser like ? ";
				}
				sql += "  order by A.clmuser ";
			} else if (opt == 4) {
				if (optStr.length() != 0) {
					sql += " and A.procdesc like ? ";
				}
				sql += "  order by A.procdesc ";
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, clmdtFrom.substring(0, 8));
			pstmt.setString(2, clmdtTo.substring(0, 8));
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(4, "%" + optStr2 + "%");
			if (optStr.length() != 0) {
				pstmt.setString(5, "%" + optStr + "%");

			}

			System.out.println("[getClaimListByOption]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				List<String> list = new ArrayList<>();
				// list.add(String.valueOf(rowNum++)); //UI NO.
				list.add(rs.getString(++columnIndex));// clmseq
				list.add(rs.getString(++columnIndex));// clmdt
				list.add(rs.getString(++columnIndex)); // regdt
				list.add(rs.getString(++columnIndex)); // ordid
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex)); // QTY

				list.add(rs.getString(++columnIndex)); // 은행
				list.add(rs.getString(++columnIndex)); // 계좌
				list.add(rs.getString(++columnIndex)); // 이름
				list.add(rs.getString(++columnIndex)); // 총액

				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

				list.add(rs.getString(++columnIndex));// clmdt
				list.add(rs.getString(++columnIndex));// clmseq

				// --자동화 처리 결과
				list.add(rs.getString(++columnIndex));// concat(recvdt, recvseq)
				list.add(rs.getString(++columnIndex));// concat(orddt, ordseq,seq)

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

	public List<List<String>> getClaimListByOrdid(String ordidFrom, String ordidTo, String clmdtFrom, String clmdtTo)
			throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT A.clmseq, A.regdt, A.ordid, ifnull(A.EXPCD,''), ifnull(A.mallcd,''), ifnull(A.rcvnam,''), ifnull(A.clpno,''),  " // 크레임등록일,
																																					// 주문번호,
																																					// 택배사,
																																					// 쇼핑몰,
																																					// 수취인,
																																					// 폰번호
					+ " ifnull(A.resalnam,''), ifnull(A.resaltel,''), ifnull(A.resalinv,''), " // 리셀러 이름, 전화, 송장
					+ " ifnull(A.proctype,''), ifnull(A.procdesc,''), ifnull(A.clmuser,''), ifnull(A.addr,''), ifnull(A.prodcd,''), "
					+ " concat(ifnull(B.prodnm,''), '_', ifnull(B.SPECDES,'') )  as prodnm, " + " ifnull(A.qty,0), " // 처리유형,
																														// 처리내용,
																														// 주소,
																														// 상품코드,
																														// 상품명,
																														// 수량
					+ " ifnull(A.clmconts,''), " // 접수내용
					+ " ifnull(A.dirbnk,''), ifnull(A.diracno,''), ifnull(A.dirnam,''),  ifnull(A.diramt,0), " // 은행,
																												// ,계좌,
																												// 이름,
																												// 금액
					+ " ifnull(A.suppaknm,0), ifnull(A.suppnam,''), ifnull(A.suppamt,0), ifnull(A.retpaknm,0), ifnull(A.retnam,''), ifnull(A.retamt,0),  " // 출고부담업체,
																																							// 출고부담운임,
																																							// 반품부답업체,
																																							// 반품부담금액
					+ " ifnull(A.expprocnm,''), ifnull(A.rewdnam,''), ifnull(A.rewdamt,0), ifnull(A.rewdfare,0), ifnull(A.rewdpakamt,0), " // 처리(택배회사),
																																			// 보상업체,
																																			// 보상품값,
																																			// 보상운임,보상포장비
					+ " clmdt, clmseq "
					+ " , concat(ifnull(recvdt,''),ifnull(recvseq,'')), concat(ifnull(orddt,''), ifnull(ordseq,''), ifnull(seq,'')) " // 자동화
																																		// 처리
																																		// 결과
					+ " FROM clmlist A LEFT JOIN  v_products B On  A.PRODCD = B.PRODCD and A.compno = B.compno "
					+ " WHERE A.clmdt >= ? ANd A.clmdt <= ? " + " And ordid >=? and ordid <=? and A.compno = ? "
					+ " ORDER BY A.CLMDT DESC, A.REGDT DESC, A.ORDID DESC ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, clmdtFrom);
			pstmt.setString(2, clmdtTo);
			pstmt.setString(3, ordidFrom);
			pstmt.setString(4, ordidTo);
			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getClaimListByOrdid]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				List<String> list = new ArrayList<>();
				// list.add(String.valueOf(rowNum++)); //UI NO.
				list.add(rs.getString(++columnIndex)); // clmseq
				list.add(rs.getString(++columnIndex)); // clmdt
				list.add(rs.getString(++columnIndex)); // regdt
				list.add(rs.getString(++columnIndex)); // ordid
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex)); // QTY

				list.add(rs.getString(++columnIndex)); // 은행
				list.add(rs.getString(++columnIndex)); // 계좌
				list.add(rs.getString(++columnIndex)); // 이름
				list.add(rs.getString(++columnIndex)); // 총액

				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

				list.add(rs.getString(++columnIndex));// clmdt
				list.add(rs.getString(++columnIndex));// clmseq

				// --자동화 처리 결과
				list.add(rs.getString(++columnIndex));// concat(recvdt, recvseq)
				list.add(rs.getString(++columnIndex));// concat(orddt, ordseq,seq)

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

	public int insertClaimFromSabangNet(List<List<String>> claims) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			int maxSeq = getClmMaxSeq(connection, statementlist, YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd"));

			String sql_orddtl = " insert into clmlist (compno, clmdt, clmseq, regdt, ordid, mallcd, rcvnam, clpno, "
					+ "  procdesc, addr,  clmconts, clmno, sabordno, qty, EXPCD, RESALINV, PRODCD, "
					+ "  INSERTDT, INSERTID)  VALUES (?, ?, ?,  ?, " + "  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);
			statementlist.add(pstmt_orddtl);

			for (List<String> claim : claims) {
				int rowIdx = 0;
				pstmt_orddtl.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt_orddtl.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd"));// clmdt
				pstmt_orddtl.setInt(++rowIdx, maxSeq);

				for (int i = 0; i < claim.size(); i++) {
					pstmt_orddtl.setString(++rowIdx, claim.get(i));
				}

				insertClmlistDtlFromUser(connection, statementlist, YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd"),
						maxSeq, claim.get(12), claim.get(9));

				pstmt_orddtl.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt_orddtl.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId());

				System.out.println("[insertClaimFromSabangNet]" + pstmt_orddtl.toString());

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

	public void processClaimInfoFromUser(String proctype, String ordid, String expcd, String mallcd, String rcvnam,
			String clpno, String resalnam, String resaltel, String resalinv, String procdesc, String clmuser,
			String addr, String clmconts, String dirbnk, String diracno, String dirnam, String diramt, String suppaknm,
			String suppnam, String suppamt, String retpaknm, String retnam, String retamt, String expprocnm,
			String rewdnam, String rewdamt, String rewdfare, String rewdpakamt, List<List<String>> prodList,
			List<List<String>> clmcontents, String oworddt, String owordseq, String owseq) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String clmdt = YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd");

			int maxSeq = getClmMaxSeq(connection, statementlist, clmdt);

			insertClaimInfoFromUser(connection, statementlist, proctype, ordid, expcd, mallcd, rcvnam, clpno, resalnam,
					resaltel, resalinv, procdesc, clmuser, addr, clmconts, dirbnk, diracno, dirnam, diramt, suppaknm,
					suppnam, suppamt, retpaknm, retnam, retamt, expprocnm, rewdnam, rewdamt, rewdfare, rewdpakamt,
					maxSeq, prodList);

			if (oworddt.length() > 0) {
				insertOrderClmlist(connection, statementlist, clmdt, maxSeq, oworddt, owordseq, owseq);
			}

			if (prodList == null || prodList.size() == 0) {
			} else {
				insertClmlistDtlFromUser(connection, statementlist, clmdt, maxSeq, prodList);
			}

			if (clmcontents == null || clmcontents.size() == 0) {
			} else {
				insertClmlistDtl2FromUser(connection, statementlist, clmdt, maxSeq, clmcontents);
			}

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public void processClaimInfoFromUser2(String proctype, String ordid, String expcd, String mallcd, String rcvnam,
			String clpno, String resalnam, String resaltel, String resalinv, String procdesc, String clmuser,
			String addr, String clmconts, String dirbnk, String diracno, String dirnam, String diramt, String suppaknm,
			String suppnam, String suppamt, String retpaknm, String retnam, String retamt, String expprocnm,
			String rewdnam, String rewdamt, String rewdfare, String rewdpakamt, List<List<String>> prodList,
			List<List<String>> clmcontents, String oworddt, String owordseq, String owseq) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String clmdt = YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd");

			int maxSeq = getClmMaxSeq(connection, statementlist, clmdt);

			insertClaimInfoFromUser(connection, statementlist, proctype, ordid, expcd, mallcd, rcvnam, clpno, resalnam,
					resaltel, resalinv, procdesc, clmuser, addr, clmconts, dirbnk, diracno, dirnam, diramt, suppaknm,
					suppnam, suppamt, retpaknm, retnam, retamt, expprocnm, rewdnam, rewdamt, rewdfare, rewdpakamt,
					maxSeq, prodList);

			insertOrderClmlist(connection, statementlist, clmdt, maxSeq, oworddt, owordseq, owseq);

			if (prodList == null || prodList.size() == 0) {
			} else {
				insertClmlistDtlFromUser(connection, statementlist, clmdt, maxSeq, prodList);
			}

			if (clmcontents == null || clmcontents.size() == 0) {
			} else {
				insertClmlistDtl2FromUser(connection, statementlist, clmdt, maxSeq, clmcontents);
			}

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public int processClaimInfoFromUser3(String proctype, String ordid, String expcd, String mallcd, String rcvnam,
			String clpno, String resalnam, String resaltel, String resalinv, String procdesc, String clmuser,
			String addr, String clmconts, String dirbnk, String diracno, String dirnam, String diramt, String suppaknm,
			String suppnam, String suppamt, String retpaknm, String retnam, String retamt, String expprocnm,
			String rewdnam, String rewdamt, String rewdfare, String rewdpakamt, List<List<String>> prodList,
			List<List<String>> clmcontents) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String clmdt = YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd");

			int maxSeq = getClmMaxSeq(connection, statementlist, clmdt);

			insertClaimInfoFromUser(connection, statementlist, proctype, ordid, expcd, mallcd, rcvnam, clpno, resalnam,
					resaltel, resalinv, procdesc, clmuser, addr, clmconts, dirbnk, diracno, dirnam, diramt, suppaknm,
					suppnam, suppamt, retpaknm, retnam, retamt, expprocnm, rewdnam, rewdamt, rewdfare, rewdpakamt,
					maxSeq, prodList);

			if (prodList == null || prodList.size() == 0) {
			} else {
				insertClmlistDtlFromUser(connection, statementlist, clmdt, maxSeq, prodList);
			}

			if (clmcontents == null || clmcontents.size() == 0) {
			} else {
				insertClmlistDtl2FromUser(connection, statementlist, clmdt, maxSeq, clmcontents);
			}

			connection.commit();

			return maxSeq;

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public int getClmMaxSeq(Connection connection, List<PreparedStatement> statementlist, String clmdt)
			throws Exception {

		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;

		int maxSeq = 0;

		String sql_orddtl = " SELECT A.maxseq from (SELECT ifnull(MAX(clmseq), 0)+1 as maxseq FROM clmlist where clmdt=? and compno = ? ) A  ";
		sql_orddtl = sql_orddtl.toUpperCase();

		pstmt_orddtl = connection.prepareStatement(sql_orddtl);
		statementlist.add(pstmt_orddtl);

		int rowIdx = 0;
		pstmt_orddtl.setString(1, clmdt);
		pstmt_orddtl.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
		System.out.println("[getClmMaxSeq]" + pstmt_orddtl.toString());

		rs = pstmt_orddtl.executeQuery();
		if (rs.next()) {
			maxSeq = rs.getInt(1);
		}

		if (rs != null) {
			DBCPInit.getInstance().freeResultSet(rs);
		}

		return maxSeq;
	}

	public void insertClaimInfoFromUser(Connection connection, List<PreparedStatement> statementlist, String proctype,
			String ordid, String expcd, String mallcd, String rcvnam, String clpno, String resalnam, String resaltel,
			String resalinv, String procdesc, String clmuser, String addr, String clmconts, String dirbnk,
			String diracno, String dirnam, String diramt, String suppaknm, String suppnam, String suppamt,
			String retpaknm, String retnam, String retamt, String expprocnm, String rewdnam, String rewdamt,
			String rewdfare, String rewdpakamt, int maxSeq, List<List<String>> content) throws Exception {

		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;

		String sql_orddtl = " insert into clmlist (compno, clmdt, clmseq,  " + " regdt, ordid, " // 클레임등록일, 주문일
				+ " expcd, mallcd, rcvnam, clpno, resalnam, resaltel, resalinv,"
				+ " proctype,  procdesc, clmuser, addr, prodcd, qty, CLMCONTS, " + " dirbnk, diracno, dirnam, diramt,  "
				+ " suppaknm, suppnam, suppamt, retpaknm, retnam, retamt, expprocnm, rewdnam, rewdamt, rewdfare, rewdpakamt, "
				+ " INSERTDT, INSERTID)  VALUES (?, ?, " + " ?, ?, ?, " + " ?, ?, ?, ?, ?, ?, ?, "
				+ " ?, ?, ?, ?, ?, ?, ?, " + " ?, ?, ?, ?, " + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " + " ?, ?)";

		sql_orddtl = sql_orddtl.toUpperCase();

		pstmt_orddtl = connection.prepareStatement(sql_orddtl);
		statementlist.add(pstmt_orddtl);

		int rowIdx = 0;
		pstmt_orddtl.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt_orddtl.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd")); // clmdt
		pstmt_orddtl.setInt(++rowIdx, maxSeq); // seq
		pstmt_orddtl.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMddHHmmss")); // regdt
		pstmt_orddtl.setString(++rowIdx, ordid); // ordid
		pstmt_orddtl.setString(++rowIdx, expcd);
		pstmt_orddtl.setString(++rowIdx, mallcd);
		pstmt_orddtl.setString(++rowIdx, rcvnam);
		pstmt_orddtl.setString(++rowIdx, clpno);
		pstmt_orddtl.setString(++rowIdx, resalnam);
		pstmt_orddtl.setString(++rowIdx, resaltel);
		pstmt_orddtl.setString(++rowIdx, resalinv);
		pstmt_orddtl.setString(++rowIdx, proctype);
		pstmt_orddtl.setString(++rowIdx, procdesc);
		pstmt_orddtl.setString(++rowIdx, clmuser);
		pstmt_orddtl.setString(++rowIdx, addr);

		if (content == null || content.size() == 0) {
			pstmt_orddtl.setString(++rowIdx, "");
			pstmt_orddtl.setInt(++rowIdx, 0);
		} else {
			List<String> lit = content.get(0); // 상품코드 1번째 껏을 상담테이블(CLMLIST) 에 넣어준다.
			pstmt_orddtl.setString(++rowIdx, lit.get(1));
			pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(lit.get(4)));
		}

		pstmt_orddtl.setString(++rowIdx, clmconts);
		pstmt_orddtl.setString(++rowIdx, dirbnk);//
		pstmt_orddtl.setString(++rowIdx, diracno);//
		pstmt_orddtl.setString(++rowIdx, dirnam);//
		pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(diramt));//

		pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(suppaknm));//
		pstmt_orddtl.setString(++rowIdx, suppnam);//
		pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(suppamt));//
		pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(retpaknm));//
		pstmt_orddtl.setString(++rowIdx, retnam);//
		pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(retamt));//
		pstmt_orddtl.setString(++rowIdx, expprocnm);//
		pstmt_orddtl.setString(++rowIdx, rewdnam);//
		pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(rewdamt));//
		pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(rewdfare));//
		pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(rewdpakamt));//

		pstmt_orddtl.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt_orddtl.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId());

		System.out.println("[insertClaimInfoFromUser]" + pstmt_orddtl.toString());

		pstmt_orddtl.execute();
	}

	// 여기부터
	public void insertClmlistDtlFromUser(Connection connection, List<PreparedStatement> statementlist, String clmdt,
			int clmseq, List<List<String>> content) throws Exception {

		int result = 0;

		String sql_orddtl = "insert into clmlistdtl (compno, clmdt, clmseq, seq, gubun, prodcd, qty, price, cusprice) values ("
				+ "?, ?,?"
				+ ",(SELECT A.maxseq from (SELECT ifnull(MAX(seq), 0)+1 as maxseq FROM clmlistdtl where clmdt=? and clmseq=? and compno = ? ) A)"
				+ ",?,?,?,?,?) ";
		sql_orddtl = sql_orddtl.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql_orddtl);
		statementlist.add(pstmt);

		for (int i = 0; i < content.size(); i++) {
			List<String> lit = content.get(i);
			int rowIdx = 0;
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, clmdt);
			pstmt.setInt(++rowIdx, clmseq);
			pstmt.setString(++rowIdx, clmdt);
			pstmt.setInt(++rowIdx, clmseq);
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, lit.get(0));
			pstmt.setString(++rowIdx, lit.get(1));
			pstmt.setInt(++rowIdx, Integer.parseInt(lit.get(4)));
			pstmt.setInt(++rowIdx, Integer.parseInt(lit.get(3)));
			pstmt.setInt(++rowIdx, Integer.parseInt(lit.get(5).length() == 0 ? "0" : lit.get(5)));

			System.out.println("[insertClmlistDtlFromUser]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화

		}
		pstmt.executeBatch();
		pstmt.clearParameters(); // Batch 초기화

	}

	public void insertClmlistDtl2FromUser(Connection connection, List<PreparedStatement> statementlist, String clmdt,
			int clmseq, List<List<String>> content2) throws Exception {

		int result = 0;

		String sql_orddtl = "insert into clmlistdtl2 (compno, clmdt, clmseq, CLMINDT, CLMUSERNM, CLMCONTENTS) values ("
				+ "?, ?,?,?,?,?) ";

		sql_orddtl = sql_orddtl.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql_orddtl);
		statementlist.add(pstmt);

		for (int i = 0; i < content2.size(); i++) {
			List<String> lit = content2.get(i);
			int rowIdx = 0;
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, clmdt);
			pstmt.setInt(++rowIdx, clmseq);
			;
			pstmt.setString(++rowIdx, lit.get(0));
			pstmt.setString(++rowIdx, lit.get(1));
			pstmt.setString(++rowIdx, lit.get(2));

			System.out.println("[insertClmlistDtl2FromUser]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화

		}
		pstmt.executeBatch();
		pstmt.clearParameters(); // Batch 초기화

	}

	public void insertClmlistDtlFromUser(Connection connection, List<PreparedStatement> statementlist, String clmdt,
			int clmseq, String prodcd, String qty) throws Exception {

		int result = 0;

		String sql_orddtl = "insert into clmlistdtl (compno, clmdt, clmseq, seq, gubun, prodcd, qty) values ("
				+ "?, ?,?"
				+ ",(SELECT A.maxseq from (SELECT ifnull(MAX(seq), 0)+1 as maxseq FROM clmlistdtl where clmdt=? and clmseq=? and compno = ? ) A)"
				+ ",?,?,?) ";
		sql_orddtl = sql_orddtl.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql_orddtl);
		statementlist.add(pstmt);

		int rowIdx = 0;
		pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setString(++rowIdx, clmdt);
		pstmt.setInt(++rowIdx, clmseq);
		pstmt.setString(++rowIdx, clmdt);
		pstmt.setInt(++rowIdx, clmseq);
		pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setInt(++rowIdx, 1);
		pstmt.setString(++rowIdx, prodcd);
		pstmt.setString(++rowIdx, qty);
		// pstmt.setString(++rowIdx, cusprice);

		System.out.println("[insertClmlistDtlFromUser SabangNet]" + pstmt.toString());

		pstmt.addBatch();
		pstmt.clearParameters(); // 파라미터 초기화

		pstmt.executeBatch();
		pstmt.clearParameters(); // Batch 초기화

	}

	public void insertClmlistDtlFromUser(String clmdt, String clmseq, List<List<String>> content) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			// int result = 0;

			String sql_orddtl = "insert into clmlistdtl (compno, clmdt, clmseq, seq, gubun, prodcd, qty, price, cusprice) values ("
					+ "?, ?,?"
					+ ",(SELECT A.maxseq from (SELECT ifnull(MAX(seq), 0)+1 as maxseq FROM clmlistdtl where clmdt=? and clmseq=? and compno = ? ) A)"
					+ ",?,?,?,?,?) ";
			sql_orddtl = sql_orddtl.toUpperCase();

			PreparedStatement pstmt = connection.prepareStatement(sql_orddtl);
			statementlist.add(pstmt);

			for (int i = 0; i < content.size(); i++) {
				List<String> lit = content.get(i);
				int rowIdx = 0;
				pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++rowIdx, clmdt);
				pstmt.setString(++rowIdx, clmseq);
				pstmt.setString(++rowIdx, clmdt);
				pstmt.setString(++rowIdx, clmseq);
				pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++rowIdx, lit.get(0));
				pstmt.setString(++rowIdx, lit.get(1));
				pstmt.setInt(++rowIdx, Integer.parseInt(lit.get(4)));
				pstmt.setInt(++rowIdx, Integer.parseInt(lit.get(3)));
				pstmt.setInt(++rowIdx, Integer.parseInt(lit.get(5)));

				System.out.println("[insertClmlistDtlFromUser]" + pstmt.toString());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			pstmt.executeBatch();
			pstmt.clearParameters(); // Batch 초기화

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public void insertClmlistDtl22FromUser(String clmdt, String clmseq, List<List<String>> content2) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			// int result = 0;

			String sql_orddtl = "insert into clmlistdtl2 (compno, clmdt, clmseq, CLMINDT, CLMUSERNM, CLMCONTENTS) values ("
					+ "?, ?,?,?,?,?) ";
			sql_orddtl = sql_orddtl.toUpperCase();

			PreparedStatement pstmt = connection.prepareStatement(sql_orddtl);
			statementlist.add(pstmt);

			for (int i = 0; i < content2.size(); i++) {
				List<String> lit = content2.get(i);
				int rowIdx = 0;
				pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++rowIdx, clmdt);
				pstmt.setString(++rowIdx, clmseq);
				pstmt.setString(++rowIdx, lit.get(0));
				pstmt.setString(++rowIdx, lit.get(1));
				pstmt.setString(++rowIdx, lit.get(2));

				System.out.println("[insertClmlistDtl22FromUser]" + pstmt.toString());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			pstmt.executeBatch();
			pstmt.clearParameters(); // Batch 초기화

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public void insertClmlistDtlFromUser(String clmdt, int clmseq, List<List<String>> content) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql_orddtl = "insert into clmlistdtl (compno, clmdt, clmseq, seq, gubun, prodcd, qty, cusprice) values ("
					+ "?, ?,?"
					+ ",(SELECT A.maxseq from (SELECT ifnull(MAX(seq), 0)+1 as maxseq FROM clmlistdtl where clmdt=? and clmseq=? and compno = ? ) A)"
					+ ",?,?,?,?) ";
			sql_orddtl = sql_orddtl.toUpperCase();

			PreparedStatement pstmt = connection.prepareStatement(sql_orddtl);
			statementlist.add(pstmt);

			for (int i = 0; i < content.size(); i++) {
				List<String> lit = content.get(i);
				int rowIdx = 0;
				pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++rowIdx, clmdt);
				pstmt.setInt(++rowIdx, clmseq);
				pstmt.setString(++rowIdx, clmdt);
				pstmt.setInt(++rowIdx, clmseq);
				pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++rowIdx, lit.get(0));
				pstmt.setString(++rowIdx, lit.get(1));
				pstmt.setInt(++rowIdx, Integer.parseInt(lit.get(4)));
				pstmt.setInt(++rowIdx, Integer.parseInt(lit.get(5)));

				System.out.println("[insertClmlistDtlFromUser]" + pstmt.toString());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}

			pstmt.executeBatch();
			pstmt.clearParameters(); // Batch 초기화

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public void insertOrderClmlist(Connection connection, List<PreparedStatement> statementlist, String clmdt,
			int clmseq, String oworddt, String owordseq, String owseq) throws Exception {

		String sql_orddtl = "insert into ordclmlist (compno, orddt,ordseq,seq,clmdt,clmseq) values ("
				+ "?, ?,?,?,?,?) ";

		sql_orddtl = sql_orddtl.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql_orddtl);
		statementlist.add(pstmt);

		int rowIdx = 0;
		pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setString(++rowIdx, oworddt);
		pstmt.setString(++rowIdx, owordseq);
		pstmt.setString(++rowIdx, owseq);
		pstmt.setString(++rowIdx, clmdt);
		pstmt.setInt(++rowIdx, clmseq);

		System.out.println("[insertOrderClmlist]" + pstmt.toString());

		pstmt.executeUpdate();

	}

	public int updateClaimFromReceive(String clmdt, String clmseq, String proctype, String ordid, String expcd,
			String mallcd, String rcvnam, String clpno, String resalnam, String resaltel, String resalinv,
			String procdesc, String clmuser, String addr, String prodcd_1, String qty_1, String clmcontents,
			String dirbnk, String diracno, String dirnam, String diramt) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "update clmlist " + " set proctype=? , ordid=?, expcd=?, "
					+ " mallcd=?, rcvnam=?, clpno=? ,	" + " resalnam=?, resaltel=?, resalinv=?,	"
					+ " procdesc=?,  clmuser=?, addr=?,	 " + " prodcd=?, qty=?, "
					+ " CLMCONTS=?,	 dirbnk=? ,	diracno=? ,		dirnam=? ,	diramt=?, " + " updatedt=?, updateid=?   "
					+ " where clmdt=? and clmseq=? and compno = ? ";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt = connection.prepareStatement(sql_orddtl);

			int rowIdx = 0;

			pstmt.setString(++rowIdx, proctype);
			pstmt.setString(++rowIdx, ordid);
			pstmt.setString(++rowIdx, expcd);
			pstmt.setString(++rowIdx, mallcd);
			pstmt.setString(++rowIdx, rcvnam);
			pstmt.setString(++rowIdx, clpno);
			pstmt.setString(++rowIdx, resalnam);
			pstmt.setString(++rowIdx, resaltel);
			pstmt.setString(++rowIdx, resalinv);
			pstmt.setString(++rowIdx, procdesc);
			pstmt.setString(++rowIdx, clmuser);
			pstmt.setString(++rowIdx, addr);

			pstmt.setString(++rowIdx, prodcd_1);
			pstmt.setInt(++rowIdx, Integer.parseInt(qty_1)); // 수량 1

			pstmt.setString(++rowIdx, clmcontents);
			pstmt.setString(++rowIdx, dirbnk);
			pstmt.setString(++rowIdx, diracno);
			pstmt.setString(++rowIdx, dirnam);
			pstmt.setInt(++rowIdx, Integer.parseInt(diramt));

			pstmt.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId());

			pstmt.setString(++rowIdx, clmdt);
			pstmt.setInt(++rowIdx, Integer.parseInt(clmseq));
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[updateClaimFromReceive]" + pstmt.toString());

			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public int updateOrderClaim(String clmdt, String clmseq, String oworddt, String owordseq, String owseq)
			throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "update ordclmlist " + " set orddt=?, ordseq=?, seq=?  "
					+ " where clmdt=? and clmseq=? and compno = ? ";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);

			int rowIdx = 0;

			pstmt_orddtl.setString(++rowIdx, oworddt);
			pstmt_orddtl.setString(++rowIdx, owordseq);
			pstmt_orddtl.setString(++rowIdx, owseq);
			pstmt_orddtl.setString(++rowIdx, clmdt);
			pstmt_orddtl.setString(++rowIdx, clmseq);
			pstmt_orddtl.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[updateOrderClaim]" + pstmt_orddtl.toString());

			pstmt_orddtl.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt_orddtl, rs);
		}

		return result;
	}

	public int updateClaimFromSolve(String clmdt, String clmseq, String suppaknm, String suppnam, String suppamt,
			String retpaknm, String retnam, String retamt, String expprocnm, String rewdnam, String rewdamt,
			String rewdfare, String rewdpakamt) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "update clmlist " + " set suppaknm=?, suppnam=?, suppamt=?, "
					+ " retpaknm=?, retnam=?, retamt=?,	" + " expprocnm=?, "
					+ " rewdnam=?, rewdamt=?, rewdfare=?, rewdpakamt=?," + " updatedt=?, updateid=?   "
					+ " where clmdt=? and clmseq=? and compno = ? ";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);

			int rowIdx = 0;

			pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(suppaknm));
			pstmt_orddtl.setString(++rowIdx, suppnam);
			pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(suppamt));
			pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(retpaknm));
			pstmt_orddtl.setString(++rowIdx, retnam);
			pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(retamt));
			pstmt_orddtl.setString(++rowIdx, expprocnm);
			pstmt_orddtl.setString(++rowIdx, rewdnam);
			pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(rewdamt));
			pstmt_orddtl.setString(++rowIdx, rewdfare);
			pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(rewdpakamt));

			pstmt_orddtl.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt_orddtl.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId());

			pstmt_orddtl.setString(++rowIdx, clmdt);
			pstmt_orddtl.setInt(++rowIdx, Integer.parseInt(clmseq));
			pstmt_orddtl.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[updateClaimFromSolve]" + pstmt_orddtl.toString());

			pstmt_orddtl.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt_orddtl, rs);
		}

		return result;
	}

	public List<List<String>> getOrderListForClaimManager(String rorddtFrom, String rorddtTo, int opt, String optStr)
			throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

//			{ { "주문일시", "160" }, { "수취인명", "150" } , { "주소", "600" },
//				{ "핸드폰", "150" }, { "일반전화", "150" }, { "수량", "80" }, 	{ "상품코드", "200" }, { "상품명", "260" },	{ "쇼핑몰아이디", "250" } }; 

			String sql = " select  ifnull(A.rorddt,''), "
					+ " ifnull(A.rcvnam,''),  ifnull(A.addr,''), ifnull(A.clpno,''), ifnull(A.telno,''), "
					+ " ifnull(A.qty,''),  ifnull(A.prodcd,''), "
					+ " CONCAT(ifnull(B.PRODNM, ''),'_', ifnull(B.SPECDES,'')) , "
					+ " ifnull(b.tagprice,0), ifnull(A.shopid,''), ifnull(A.dlvid,''), ifnull(ordnm,''), "
					+ " ifnull(invno,'') , ifnull(USERCEL,''), ifnull(B.cusprice,''), " + " A.orddt, A.ordseq, A.seq, "
					+ " ifnull(C.clmdt,'') , ifnull(C.clmseq,'') "
					+ " FROM  ORDDTL A  LEFT JOIN V_PRODUCTS B On  A.PRODCD = B.PRODCD and A.compno = B.compno "
					+ " LEFT JOIN ORDCLMLIST C On (A.orddt=C.orddt and A.ordseq=C.ordseq and A.seq=C.seq and A.compno = C.compno ) "
					+ " where rorddt >= ?  and  rorddt <= ? and A.compno = ? ";

			// {상품코드", "상품명", "수취인명", "주문자명"})
			if (opt == 0) {
				if (optStr.length() != 0) {
					sql += " and  A.prodcd like ? ";
				}
				sql += "  order by A.prodcd ";
			} else if (opt == 1) {
				if (optStr.length() != 0) {
					sql += " and B.PRODNM like ? ";
				}
				sql += "  order by B.PRODNM ";
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

			pstmt.setString(1, rorddtFrom);
			pstmt.setString(2, rorddtTo);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			if (optStr.length() != 0) {
				pstmt.setString(4, "%" + optStr + "%");
			}

			System.out.println("[getOrderListForClaimManager]" + pstmt.toString());

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

	public List<List<String>> getOrderListForClaimSolveManager(String rorddtFrom, String rorddtTo, int opt,
			String optStr) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select  ifnull(A.rorddt,''), "
					+ " ifnull(A.rcvnam,''),  ifnull(A.addr,''), ifnull(A.clpno,''), ifnull(A.telno,''), "
					+ " ifnull(A.qty,''),  ifnull(A.prodcd,''), "
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,A.prodcd) as img,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ " CONCAT(ifnull(B.PRODNM, ''),'_', ifnull(B.SPECDES,'')) , "
					+ " ifnull(b.tagprice,0), ifnull(A.shopid,''), ifnull(A.dlvid,''), ifnull(ordnm,''), "
					+ " ifnull(invno,'') , ifnull(USERCEL,'') , " + " ifnull(C.clmdt,'') , ifnull(C.clmseq,'') , "
					+ " A.orddt, A.ordseq, A.seq "
					+ " FROM ORDDTL A  LEFT JOIN V_PRODUCTS B On (A.PRODCD = B.PRODCD and A.compno = B.compno ) "
					+ " LEFT JOIN ORDCLMLIST C On (A.orddt=C.orddt and A.ordseq=C.ordseq and A.seq=C.seq and A.compno = C.compno ) "
					+ " where rorddt >= ?  and  rorddt <= ? and A.compno = ? ";

			// {상품코드", "상품명", "수취인명", "주문자명"})
			if (opt == 0) {
				if (optStr.length() != 0) {
					sql += " and  A.prodcd like ? ";
				}
				sql += "  order by A.prodcd ";
			} else if (opt == 1) {
				if (optStr.length() != 0) {
					sql += " and B.PRODNM like ? ";
				}
				sql += "  order by B.PRODNM ";
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

			pstmt.setString(1, rorddtFrom);
			pstmt.setString(2, rorddtTo);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			if (optStr.length() != 0) {
				pstmt.setString(4, "%" + optStr + "%");
			}

			System.out.println("[getOrderListForClaimSolveManager]" + pstmt.toString());

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

	public List<List<String>> getOrderListForClaimSolveManager2(String oworddt, String owordseq, String owseq)
			throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select  ifnull(A.rorddt,''), "
					+ " ifnull(A.rcvnam,''),  ifnull(A.addr,''), ifnull(A.clpno,''), ifnull(A.telno,''), "
					+ " ifnull(A.qty,''),  ifnull(A.prodcd,''), "
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,A.prodcd) as img,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ "CONCAT(ifnull(B.PRODNM, ''),'_', ifnull(B.SPECDES,'')) , "
					+ " ifnull(b.tagprice,0), ifnull(A.shopid,''), ifnull(A.dlvid,''), ifnull(ordnm,''), "
					+ " ifnull(invno,'') , ifnull(USERCEL,'') , " + " ifnull(C.clmdt,'') , ifnull(C.clmseq,'') , "
					+ " A.orddt, A.ordseq, A.seq "
					+ " FROM ORDDTL A  LEFT JOIN V_PRODUCTS B On (A.compno = B.compno and A.PRODCD = B.PRODCD) "
					+ " LEFT JOIN ORDCLMLIST C On (A.compno = C.compno and A.orddt=C.orddt and A.ordseq=C.ordseq and A.seq=C.seq) "
					+ " where a.orddt = ?  and  a.ordseq = ? and  a.seq = ? and a.compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, oworddt);
			pstmt.setString(2, owordseq);
			pstmt.setString(3, owseq);
			pstmt.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getOrderListForClaimSolveManager2]" + pstmt.toString());

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

	public List<List<String>> getOrderDlvid_InvnoForClaimManager(List<List<String>> claimList) throws Exception {

		List<List<String>> orderList = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			StringBuffer sql = new StringBuffer(
					"SELECT SABORDNO,  DLVID ,  INVNO, PRODCD  FROM ORDDTL	WHERE compno = ? and SABORDNO IN(");
			int i = 0;
			for (List<String> claim : claimList) {
				sql.append("?");
				if (i < claimList.size() - 1) {
					sql.append(",");
				}
				i++;
			}
			sql.append(")");

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			int rowIdx = 1;
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			for (List<String> claim : claimList) {
				pstmt.setString(++rowIdx, claim.get(9));
			}

			System.out.println("[getOrderDlvid_InvnoForClaimManager]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int x = 0;
				List<String> order = new ArrayList<>();
				order.add(rs.getString(++x));
				order.add(rs.getString(++x));
				order.add(rs.getString(++x));
				order.add(rs.getString(++x));
				orderList.add(order);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return orderList;
	}

	public List<String> getSabangNetClaimIdxs(String dateFrom, String dateTo) throws Exception {

		List<String> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT CLMNO FROM clmlist  WHERE regdt >= ? and regdt <= ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, dateFrom + "0000");
			pstmt.setString(2, dateTo + "9999");
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getSabangNetClaimIdx]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				contents.add(rs.getString(1));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public void deleteClmlist(String[] clmdt, int[] clmseq) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "delete from clmlist where clmdt=? and clmseq=? and compno = ? ";
			sql = sql.toUpperCase();

			connection = DBCPInit.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);

			for (int i = 0; i < clmdt.length; i++) {

				pstmt.setString(1, clmdt[i]);
				pstmt.setInt(2, clmseq[i]);
				pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
				System.out.println("[deleteClmlist]" + pstmt.toString());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}

			pstmt.executeBatch();
			pstmt.clearBatch();// Batch 초기화

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

	public void deleteProdFromClimListDtl(String[] clmdt, int[] clmseq) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "delete from clmlistdtl where clmdt=? and clmseq=? and compno = ? ";
			sql = sql.toUpperCase();

			connection = DBCPInit.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);

			for (int i = 0; i < clmdt.length; i++) {

				pstmt.setString(1, clmdt[i]);
				pstmt.setInt(2, clmseq[i]);
				pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
				System.out.println("[deleteClmlistDtl]" + pstmt.toString());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}

			pstmt.executeBatch();
			pstmt.clearBatch();// Batch 초기화

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

	public void deleteProdFromClimListDtl2(String[] clmdt, int[] clmseq) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "delete from clmlistdtl2 where clmdt=? and clmseq=? and compno = ? ";
			sql = sql.toUpperCase();

			connection = DBCPInit.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);

			for (int i = 0; i < clmdt.length; i++) {

				pstmt.setString(1, clmdt[i]);
				pstmt.setInt(2, clmseq[i]);
				pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
				System.out.println("[deleteClmlistDtl2]" + pstmt.toString());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}

			pstmt.executeBatch();
			pstmt.clearBatch();// Batch 초기화

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

	public void deleteProdFromClimListDtl(String clmdt, String clmseq) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "delete from clmlistdtl where clmdt=? and clmseq=? and compno = ? ";
			sql = sql.toUpperCase();

			connection = DBCPInit.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, clmdt);
			pstmt.setString(2, clmseq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[deleteClmlistDtl]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화

			pstmt.executeBatch();
			pstmt.clearBatch();// Batch 초기화

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

	public void deleteProdFromClimListDtl2(String clmdt, String clmseq) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "delete from clmlistdtl2 where clmdt=? and clmseq=? and compno = ? ";
			sql = sql.toUpperCase();

			connection = DBCPInit.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, clmdt);
			pstmt.setString(2, clmseq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[deleteClmlistDtl2]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화

			pstmt.executeBatch();
			pstmt.clearBatch();// Batch 초기화

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

	public void deleteOrderClimList(String oworddt, String owordseq, String owseq) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "delete from ordclmlist where orddt=? and ordseq=? and seq=? and compno = ? ";
			sql = sql.toUpperCase();

			connection = DBCPInit.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);

			int rowIdx = 0;

			pstmt.setString(++rowIdx, oworddt);
			pstmt.setString(++rowIdx, owordseq);
			pstmt.setString(++rowIdx, owseq);
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[deleteOrderClimList with orderNo.]" + pstmt.toString());

			pstmt.execute();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

	public void deleteOrderClimList(String[] clmdt, int[] clmseq) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "delete from ordclmlist where clmdt=? and clmseq=? and compno = ? ";
			sql = sql.toUpperCase();

			connection = DBCPInit.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);

			for (int i = 0; i < clmdt.length; i++) {

				pstmt.setString(1, clmdt[i]);
				pstmt.setInt(2, clmseq[i]);
				pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
				System.out.println("[deleteOrderClimList with claimNo.]" + pstmt.toString());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}

			pstmt.executeBatch();
			pstmt.clearBatch();// Batch 초기화

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

}

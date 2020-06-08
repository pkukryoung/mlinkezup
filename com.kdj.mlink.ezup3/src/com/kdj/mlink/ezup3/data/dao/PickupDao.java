package com.kdj.mlink.ezup3.data.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class PickupDao {

	public List<List<String>> getRackPickupList(String dateStr, String seq) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		CallableStatement cStmt = null;
		ResultSet rs = null;

		List<PreparedStatement> pstmtlist = new ArrayList<>();

		List<List<String>> sheetContents = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			// System.out.println("-- MySQL Connection \n");

			// --mysql excelmst table Update
			cStmt = connection.prepareCall("{call YWM_EXCEL_MAP(?, ?, ?)}");
			cStmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			cStmt.setString(2, dateStr);
			cStmt.setString(3, seq);

			pstmtlist.add(cStmt);

			cStmt.execute();

			String sql = "SELECT * FROM excelmst  WHERE orddt = ?  and ordseq = ? and compno = ? ORDER BY neccd1, neccd2 DESC ";
			// SELECT * FROM excelmst WHERE orddt = '20190118' and ordseq = '1' ORDER BY
			// neccd1, neccd2 DESC ;
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, dateStr);
			pstmt.setString(2, seq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			pstmtlist.add(pstmt);

			System.out.println("[getRackPickupList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			// 컬럼의 갯수가 바뀔 수 있고, 컬럼이 너무 많아서 이름을 열거 할 수 없어 컬럼 갯수를 구해서
			// 고정컬럼 이외의 컬럼은 for 문으로 값을 구한다.
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				List<String> rowContent = new ArrayList<>();
				rowContent.add(rs.getString(1));// 주문일
				rowContent.add(rs.getString(2));// 차수
				rowContent.add(rs.getString(3));// 랙번호
				rowContent.add(rs.getString(4));// 층번

				int offset = 3;// 5번째 컬럼부터 3개씩 동일한 조합임.

				StringBuffer buff = new StringBuffer();

				// i 는 쿼리된 컬럼의 인덱스 1~4번은 고정 5번부터 가변적임.
				for (int i = 5; i <= columnCount; i++) {
					String val = rs.getString(i);
					if (val == null) {
						val = "";
					}

					if (offset % 3 == 0) {
						rowContent.add(val);

					} else if (offset % 3 == 1) {
						buff.append("(");
						buff.append(val);
					} else if (offset % 3 == 2) {
						buff.append(", ");
						buff.append(val);
						buff.append(")");
						rowContent.add(buff.toString());
						buff = new StringBuffer();
					}
					offset++;

				}

				sheetContents.add(rowContent);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			// MessageDialog.openInformation(getShell(), "랙별픽업리스트출력", "Error :
			// "+ex.getMessage());
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmtlist, rs);
		}

		return sheetContents;
	}

	public List<List<String>> getRackProductList(String dateStr, String seq) throws Exception {

		List<List<String>> content = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

//			String sql = "SELECT a.NecCd1, a.NecCd2, a.NecCd3, "
//					  + "case ifnull(a.SPECDES,'') when '' then CONCAT(ifnull(a.ProdNm,''),'\n') ELSE CONCAT(a.prodnm ,'_',ifnull(a.SPECDES,''),'\n') end, "
//					  + "concat(SUM(b.qty),'\n')  "
//					  + "FROM V_PRODUCTS a, ORDDTL b "
//					  + "WHERE a.ProdCd = b.ProdCd "
//					  + "AND b.OrdDt  = ? "
//					  + "AND b.OrdSeq = ? "
//					  + "AND IFNULL(a.NecCd1, '') <> '' "
//					  + "AND IFNULL(a.ProdCd, '') <> '' "
//					  + "AND IFNULL(a.NecCd2, 0) between 1 and 2 "
//					  + "AND IFNULL(a.NecCd3, 0) <> 0 "
//					  + "GROUP BY a.NecCd1, a.NecCd2, a.NecCd3, a.SPECDES, a.ProdNm "
//					  + "ORDER BY a.NecCd1, a.NecCd2 DESC, a.NecCd3, a.ProdNm " ;

			String sql = "CALL YWM_RACKPICUP_LIST(?, ?,?)";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, dateStr);
			pstmt.setString(3, seq);

			System.out.println("[getRackProductList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
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

	public List<List<String>> getExpPickupList(String dateStr, String seq) throws Exception {

		Connection connection = null;
		// PreparedStatement pstmt = null;
		CallableStatement cStmt = null;
		ResultSet rs = null;

		List<List<String>> sheetContents = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			cStmt = connection.prepareCall("{call YWM_EXCEL_EXPPICUP(?, ?, ?)}");
			cStmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			cStmt.setString(2, dateStr);
			cStmt.setString(3, seq);

			// System.out.println("[getExpPickupList]"+cStmt.toString());

			rs = cStmt.executeQuery();

			// 컬럼의 갯수가 바뀔 수 있고, 컬럼이 너무 많아서 이름을 열거 할 수 없어 컬럼 갯수를 구해서
			// 고정컬럼 이외의 컬럼은 for 문으로 값을 구한다.
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				// System.out.println(rs.getString(1));
				List<String> rowContent = new ArrayList<>();
				for (int i = 1; i <= columnCount; i++) {

					rowContent.add(rs.getString(i));
				}

				sheetContents.add(rowContent);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, cStmt, rs);
		}

		return sheetContents;
	}

	// 엑셀화면출력의 생성
	public void setPickupExp(String dateStr, String seq, List<List<String>> contents) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt_prodmst = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

//			insertProductMst(connection, statementlist, prodcd,  prodnm,  specdes,  flagset,  flagplus,
//					 flagout,  price,  tagprice,  sabcd,  remark,  ea,  useyn,  delyn, cusprice, aproinvt);

			// OPTCHK 는 상품마스터 등록시에만 넣어줌
			String sql_prodmst = "insert into pickupexp (compno, orddt, ordseq, seq, rorddt, rcvnam, pstno, addr, clpno, telno, qty, shpfee, credit,"
					+ " expprodnm, messge, PKGCLSS, SHIPCLS, SABORDNO,  SHOPID, ORDNM, ETCMSG, MALLCD, ORDAMT, PRODCD,OPTDESC, EXPBUNDNM, BOXCNT, "
					+ " EXPFILE, EXPCD,FLAGSET,PRODCDM,EXPINVQTY,PRODNM,SPECDES,QTY2, RACKNM, OPTPRODCD,EXPINNER,EXPCOSTNM,NECCD1, NECCD2, NECCD3)  "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ? ,?, ? ) ";

			sql_prodmst = sql_prodmst.toUpperCase();

			pstmt_prodmst = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt_prodmst);
			for (List<String> list : contents) {
				int idx = 0;
				int i = 0;
				pstmt_prodmst.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt_prodmst.setString(++i, dateStr);
				pstmt_prodmst.setString(++i, seq);
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(idx++));
				pstmt_prodmst.setString(++i, list.get(39));
				pstmt_prodmst.setString(++i, list.get(40));
				pstmt_prodmst.setString(++i, list.get(41));

				pstmt_prodmst.addBatch();
				pstmt_prodmst.clearParameters();
			}

			pstmt_prodmst.executeBatch();
			pstmt_prodmst.clearParameters(); // Batch 초기화

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	// 생성시 삭제
	public void ExpPickUpListReset(String dateStr, String seq) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " delete from pickupexp where orddt =? and ordseq = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, dateStr);
			pstmt.setString(2, seq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			statementlist.add(pstmt);
			System.out.println("[ExpPickUpListReset]" + pstmt.toString());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public List<List<String>> getExpPickupListView(String dateStr, String seq) throws Exception {
		List<List<String>> sheetContents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(ORDDT,''), ifnull(ORDSEQ,'0'), ifnull(SEQ,'0'),ifnull(RORDDT,''), ifnull(RCVNAM,''),  "
					+ " ifnull(PSTNO,''), ifnull(ADDR,''), ifnull(CLPNO,''), ifnull(TELNO,''), ifnull(QTY,'0'), ifnull(SHPFEE,'0'), ifnull(CREDIT,''),"
					+ " ifnull(EXPPRODNM,''), ifnull(MESSGE,''), ifnull(PKGCLSS,''), ifnull(SHIPCLS,''), ifnull(SABORDNO,''), ifnull(SHOPID,''),"
					+ " ifnull(ORDNM,''), ifnull(ETCMSG,''), ifnull(MALLCD,''), ifnull(ORDAMT,'0'), ifnull(PRODCD,''), ifnull(OPTDESC,''),"
					+ " ifnull(EXPBUNDNM,''), ifnull(BOXCNT,'0'), ifnull(EXPFILE,''), ifnull(EXPCD,''), ifnull(FLAGSET,''), ifnull(PRODCDM,''),"
					+ " ifnull(EXPINVQTY,'0'),ifnull(PRODNM,''), ifnull(SPECDES,''), ifnull(QTY2,'0'), ifnull(RACKNM,''), ifnull(OPTPRODCD,''),"
					+ " ifnull(EXPINNER,''), ifnull(EXPCOSTNM,''), ifnull(EXPNM,''), ifnull(INVNO,''), ifnull(expsenddt,''), ifnull(exprecvdt,''),"
					+ " ifnull(NECCD1,''), ifnull(NECCD2,''), ifnull(NECCD3,''), ifnull(FLAG,'') " + " from pickupexp "
					+ " where ORDDT = ? and ordseq = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, dateStr);
			pstmt.setString(2, seq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			// System.out.println("[getExpPickupListView]"+pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int rowno = 0;
				List<String> list = new ArrayList<>();
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add("");
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));
				list.add(rs.getString(++rowno));

				sheetContents.add(list);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return sheetContents;
	}

	// 송장번호수정
	public void ExpCodeNExpnmUpdate(String seq, String ordseq, String rcvnam, String prodcd, String prodnm,
			String invno, String expnm, String orddt, String addr, String tel, String qty, String shpfee, String credit,
			String message, String sabordno, String ordnm, String ordamt, String expcd, String etcmsg, String zipcode)
			throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

//			updateProduct(connection, statementlist, prodcd,  prodnm,  specdes,  flagset,  flagplus,
//					 flagout,  price,  tagprice,  sabcd,  remark,  ea,  useyn,  delyn, cusprice, aproinvt);

			String sql = "update pickupexp set expnm=?, invno=?, pstno = ?, addr = ?, clpno = ?, qty = ?, shpfee =?, credit = ?, MESSGE = ?, SABORDNO = ? , ORDNM=?, ORDAMT=?, EXPCD=?, ETCMSG=?, EXPPRODNM = ?, "
					+ "RCVNAM = ?, prodcd = ? where seq = ? and ordseq = ? and orddt = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, expnm);
			pstmt.setString(++i, invno);
			pstmt.setString(++i, zipcode);
			pstmt.setString(++i, addr);
			pstmt.setString(++i, tel);
			pstmt.setString(++i, qty);
			pstmt.setString(++i, shpfee);
			pstmt.setString(++i, credit);
			pstmt.setString(++i, message);
			pstmt.setString(++i, sabordno);
			pstmt.setString(++i, ordnm);
			pstmt.setString(++i, ordamt);
			pstmt.setString(++i, expcd);
			pstmt.setString(++i, etcmsg);
			pstmt.setString(++i, prodnm);
			pstmt.setString(++i, rcvnam);
			pstmt.setString(++i, prodcd);

			pstmt.setString(++i, seq);
			pstmt.setString(++i, ordseq);
			pstmt.setString(++i, orddt);
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[updateProduct]" + pstmt.toString());
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

	public boolean isExistExpress(String dateStr, String seq) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ORDDT,ORDSEQ from pickupexp where ORDDT = ? and ORDSEQ = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, dateStr);
			pstmt.setString(2, seq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[isExistExpress]" + pstmt.toString());

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

	public void pickupExpressUpdate(String data, String seq, String bizno) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update pickupexp set invno=? where seq = ? and orddt = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, bizno);
			pstmt.setString(++i, seq);
			pstmt.setString(++i, data);
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[updateProduct]" + pstmt.toString());
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

	public int getSumExpress(String dateStr, String seq, String revnam) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT sum(qty*shpfee) FROM pickupexp WHERE ORDDT = ? and ORDSEQ = ? and rcvnam = ? and compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, dateStr);
			pstmt.setString(2, seq);
			pstmt.setString(3, revnam);
			pstmt.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());

			// System.out.println("[getSumExpress]" + pstmt.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);

			} else {
				result = 0;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public int PickupListInsert(String orddt, String seq, String ordseq, String rorddt, String rcvnam, String prodcd,
			String prodnm, String zipcode, String addr, String tel, String qty, String shpfee, String credit,
			String message, String ordnm, String ordamt, String expfile, String expcd, String etcmsg, String invno,
			String expnm, String expkindnm, String shipcls) throws Exception {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into pickupexp(COMPNO, ORDDT, seq, ORDSEQ,rorddt, RCVNAM,prodcd, EXPPRODNM, prodnm, PSTNO, ADDR, CLPNO, "
				+ " QTY, SHPFEE, CREDIT, ORDNM, ORDAMT,  EXPFILE, " + " EXPCD, ETCMSG, MESSGE, EXPNM, INVNO,SHIPCLS) "
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?,? ) ";

		sql = sql.toUpperCase();

		try {
			connection = DBCPInit.getInstance().getConnection();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, orddt);
			pstmt.setString(++i, seq);
			pstmt.setString(++i, ordseq);
			pstmt.setString(++i, rorddt);
			pstmt.setString(++i, rcvnam);
			pstmt.setString(++i, prodcd);
			pstmt.setString(++i, expkindnm + "◆" + prodnm);
			pstmt.setString(++i, prodnm);
			pstmt.setString(++i, zipcode);
			pstmt.setString(++i, addr);
			pstmt.setString(++i, tel);
			pstmt.setString(++i, qty);
			pstmt.setString(++i, shpfee);
			pstmt.setString(++i, credit);
			pstmt.setString(++i, ordnm);
			pstmt.setString(++i, ordamt);
			pstmt.setString(++i, expfile);
			pstmt.setString(++i, expcd);
			pstmt.setString(++i, etcmsg);
			pstmt.setString(++i, message);
			pstmt.setString(++i, expnm);
			pstmt.setString(++i, invno);
			pstmt.setString(++i, shipcls);

			System.out.println("[insertProdIFM]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return result;
	}

	// 택배사가지고 오기
	public List<String> ExpFileList(String prodcd) throws Exception {
		List<String> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT ifnull(expfile,''), ifnull(expcd,''), ifnull(expkindnm,'') FROM expprodmst WHERE compno = ? and prodcd = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, prodcd);
			System.out.println("[ExpFileList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public void setExpress(List<List<String>> hapDongDeasin) {
		// TODO Auto-generated method stub

	}

}

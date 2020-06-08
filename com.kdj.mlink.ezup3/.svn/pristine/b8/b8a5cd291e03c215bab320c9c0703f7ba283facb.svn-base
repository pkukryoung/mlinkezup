package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class QuestListDao {

	// 문의수집 리스트
	public List<List<String>> getQuestsManagerList(int dateint, int sortOrder, int search, String clmdtFrom,
			String clmdtTo, String name, String shopping, String shopid, String btntotal, String orderlist)
			throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();
			// String sql = " SELECT qnastat,shopnm ,shopid,insdm,regdm,
			// prodid,mprodid,ordid,subject,prodnm,quests,answs,insnm,updnm,upddm,senddt ";
			String sql = " SELECT qnastat,shopnm ,shopid,insdm,regdm,qgubun, prodid,mprodid,ordid,subject,prodnm,quests,answs,insnm,updnm,upddm,senddt,goodcd,qnaseq "
					+ "FROM qnalist " + " where compno = ? and ";

			// 일자선택
			switch (dateint) {
			case 0:
				sql += " regdm >= ? and regdm <= ? ";
				break; // 수집일자
			case 1:
				sql += " insdm >= ? and insdm <= ? ";
				break; // 고객등록일
			case 2:
				sql += " senddt >= ? and senddt <= ? ";
				break; // 송신일
			}
			if (name.equals("")) {
				sql += "";
			} else {
				// 검색조건
				switch (search) {
				case 0:
					sql += " and mprodid like ? ";
					break;
				case 1:
					sql += " and prodid like ? ";
					break;
				case 2:
					sql += " and ordid like ? ";
					break;
				case 3:
					sql += " and prodnm like ? ";
					break;
				case 4:
					sql += " and insnm like ? ";
					break;
				case 5:
					sql += " and shopid like ? ";
					break;
				case 6:
					sql += " and updnm like ? ";
					break;
				}
			}

			// 처리구분
			if (btntotal.equals("")) {
				sql += " ";
			} else {
				sql += " and qnastat = ?";
			}
			// 선택사항
			if (!shopping.equals("쇼핑몰선택") && shopid.equals("쇼핑몰ID")) {
				sql += " and shopnm = ? ";
			} else if (!shopping.equals("쇼핑몰선택") && !shopid.equals("쇼핑몰ID")) {
				sql += " and shopnm = ? and shopid = ? ";
			} else if (shopping.equals("쇼핑몰선택") && !shopid.equals("쇼핑몰ID")) {
				sql += " and shopid = ? ";
			}
			// 정력순서
			switch (sortOrder) {
			case 0:
				sql += " order by regdm ";
				break;
			case 1:
				sql += " order by insdm ";
				break;
			case 2:
				sql += " order by senddt ";
				break;
			case 3:
				sql += " order by senddt ";
				break;
			}
			// 정렬 방법
			switch (orderlist) {
			case "내림차순":
				sql += "desc";
				break;
			case "오름차순":
				sql += "asc";
				break;
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			int ind = 1;

			switch (dateint) {
			case 0:
				pstmt.setString(++ind, clmdtFrom);
				pstmt.setString(++ind, clmdtTo);
				break; // 수집일자
			case 1:
				pstmt.setString(++ind, clmdtFrom);
				pstmt.setString(++ind, clmdtTo);
				break; // 고객등록일
			case 2:
				pstmt.setString(++ind, clmdtFrom);
				pstmt.setString(++ind, clmdtTo);
				break; // 송신일
			}
			if (name.equals("")) {

			} else {
				switch (search) {
				case 0:
					pstmt.setString(++ind, "%" + name + "%");
					break;
				case 1:
					pstmt.setString(++ind, "%" + name + "%");
					break;
				case 2:
					pstmt.setString(++ind, "%" + name + "%");
					break;
				case 3:
					pstmt.setString(++ind, "%" + name + "%");
					break;
				case 4:
					pstmt.setString(++ind, "%" + name + "%");
					break;
				case 5:
					pstmt.setString(++ind, "%" + name + "%");
					break;
				case 6:
					pstmt.setString(++ind, "%" + name + "%");
					break;
				}
			}

			if (!btntotal.equals(""))
				pstmt.setString(++ind, btntotal);
			if (!shopping.equals("쇼핑몰선택") && shopid.equals("쇼핑몰ID")) {
				pstmt.setString(++ind, shopping);
			} else if (!shopping.equals("쇼핑몰선택") && !shopid.equals("쇼핑몰ID")) {
				pstmt.setString(++ind, shopping);
				pstmt.setString(++ind, shopid);
			} else if (shopping.equals("쇼핑몰선택") && !shopid.equals("쇼핑몰ID")) {
				pstmt.setString(++ind, shopid);
			}

			System.out.println("[getQuestsManagerList]" + pstmt.toString());

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

	// 답변쓰기
	public int getQuestsManagerUpdate(String num, String qnaansws) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();
			String sql = "  update qnalist set answs = ?,qnastat = '002'  where qnaseq = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, qnaansws);
			pstmt.setString(2, num);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getQuestsManagerUpdate]" + pstmt.toString());

			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}

		return result;
	}

	// 자료일괄수정
	public Object getEditdataUpdate(String answs, List<List<String>> editdata) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			for (List<String> edit : editdata) {
				connection = DBCPInit.getInstance().getConnection();
				String sql = "  update qnalist set answs = ? where qnaseq = ? and compno = ? ";

				sql = sql.toUpperCase();

				pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, answs);
				pstmt.setString(2, edit.get(19));
				pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.executeUpdate();
			}

			System.out.println("[getEditdataUpdate]" + pstmt.toString());

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}

		return null;

	}

	// 강제전환
	public void getTransSwitchUpdate(List<String> list) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "  update qnalist set qnastat = '004'  where qnaseq = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, list.get(17));
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.executeUpdate();

			System.out.println("[getTransSwitchUpdate]" + pstmt.toString());

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}

	}

	// 답변송신
	public void getSendreplyUpdate(List<String> list) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "  update qnalist set qnastat = '003'  where qnaseq = ? and compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, list.get(17));
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.executeUpdate();

			System.out.println("[getSendreplyUpdate]" + pstmt.toString());

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}

	}

	// 꼬리말/머리말등록
	public void getPreFtInsert(String subject, String content, String gubun) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "insert into qnainner(compno, gubun,subject,contents) values(?, ? , ? , ?) ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, gubun);
			pstmt.setString(3, subject);
			pstmt.setString(4, content);
			pstmt.executeUpdate();

			System.out.println("[getPreFtInsert]" + pstmt.toString());

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}

	}

	// 머리말꼬리말관리 및 상세보기용처리
	public List<List<String>> PreFtList() throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();
			String sql = " SELECT * from qnainner where compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[PreFtList]" + pstmt.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));

				contents.add(list);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	// 문구내용가져오기(머리말)
	public String getheadSelect(String subject) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String content = "";
		try {

			connection = DBCPInit.getInstance().getConnection();
			String sql = " SELECT contents from qnainner where gubun = '머리말' and subject = ? and compno = ? ";
			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getheadSelect]" + pstmt.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				content = rs.getString(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return content;

	}

	// 문구내용가져오기(꼬리말)
	public String getFooterSelect(String subject) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String content = "";
		try {

			connection = DBCPInit.getInstance().getConnection();
			String sql = " SELECT contents from qnainner where gubun = '꼬리말' and subject = ? and compno = ? ";
			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getFooterSelect]" + pstmt.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				content = rs.getString(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return content;
	}

	public List<String> questionsApi(String string) {

		return null;
	}

	// 머리말/꼬리말삭제
	public int commentDelete(String content) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result;
		connection = DBCPInit.getInstance().getConnection();
		String sql = " delete from qnainner where seq = ? and compno = ? ";
		sql = sql.toUpperCase();
		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, content);
		pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
		System.out.println("[commentDelete]" + pstmt.toString());
		result = pstmt.executeUpdate();
		return result;
	}

	/// 관리에서 등록으로 가면 업데이트하는거
	public void getPreFtUpdate(String subject, String content, String gubun, String seq) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "update qnainner set gubun = ? ,subject=?,contents=? where seq= ? and compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, gubun);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			pstmt.setString(4, seq);
			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.executeUpdate();

			System.out.println("[getPreFtInsert]" + pstmt.toString());

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}

	}

	public void ShopQuestionListSave(List<List<String>> sheetContents, String questTo) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "insert into qnalist(compno,qnaseq,qnastat,shopnm,regdm,mprodid,prodnm,quests,insnm,insdm,senddt,qgubun) "
					+ " values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (List<String> list : sheetContents) {
				int i = 0;
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++i, "SHOP2");
				pstmt.setString(++i, "000");
				pstmt.setString(++i, list.get(1));
				pstmt.setString(++i, questTo);
				pstmt.setString(++i, list.get(2));
				pstmt.setString(++i, list.get(3));
				pstmt.setString(++i, list.get(6));
				pstmt.setString(++i, list.get(8));
				pstmt.setString(++i, splitMark(list.get(9)));
				pstmt.setString(++i, splitMark(list.get(10)));
				pstmt.setString(++i, list.get(4));

				pstmt.addBatch();
				pstmt.clearParameters();
			}
			System.out.println("[ShopQuestionListSave]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}

	}

	// 문의내용 일자랑 검색된거내용 가지고 오기
	public List<QuestListDto> getShopQuestionsList(String search, int datetime, String from, String to, int sccode)
			throws Exception {
		List<QuestListDto> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT IFNULL(a.shopnm,''), IFNULL(a.regdm,''), IFNULL(a.mprodid,''), IFNULL(a.PRODNM,''), IFNULL(a.subject,''), IFNULL(a.quests,''), IFNULL(a.insnm,''), IFNULL(a.insdm,''),"
					+ " IFNULL(a.senddt,''),IFNULL(a.qgubun,''),IFNULL(a.answs,''),IFNULL(b.PASSWORD,''), IFNULL(b.APIKEY,''),ifnull(a.shopid,''),a.qnaseq,ifnull(a.prodid,''),ifnull(a.qnastat,''), "
					+ " ifnull(a.oldstat,''),  ifnull(a.answstitle,''), ifnull(a.stat,''), ifnull(a.prodnm,''), ifnull(a.ordid,''),ifnull( a.insnm2,'') "
					+ " from qnalist a join " + " (\r\n" + "	SELECT COMPNO,SHOPCD,SHOPPINGID, PASSWORD,APIKEY\r\n"
					+ "   FROM  SHOPDTL GROUP BY COMPNO,SHOPCD,SHOPPINGID\r\n" + ") B " + " ON a.COMPNO = b.COMPNO AND "
					+ " a.shopnm = b.SHOPCD AND a.SHOPID = b.SHOPPINGID WHERE a.COMPNO = ? ";// and qnastat = ? ";
			if (datetime == 0) {
				sql += " and insdm >= ? and insdm <= ? ";
			} else {
				from = from + "000000";
				to = to + "235959";
				sql += " and regdm >= ? and regdm <= ? ";
			}
			// 검색조건
			switch (sccode) {
			case 0:
				sql += " and mprodid like ? ";
				break;
			case 1:
				sql += " and PRODNM like ? ";
				break;
			case 2:
				sql += " and quests like ? ";
				break;
			case 3:
				sql += " and insnm like ? ";
				break;
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			// pstmt.setString(2, "000");
			pstmt.setString(2, from);
			pstmt.setString(3, to);
			pstmt.setString(4, "%" + search + "%");
			System.out.println("[getSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				QuestListDto dto = new QuestListDto();
				int columnIndex = 0;
				dto.setShopnm(rs.getString(++columnIndex));
				dto.setRegdm(rs.getString(++columnIndex));
				dto.setMprodid(rs.getString(++columnIndex));
				dto.setProdnm(rs.getString(++columnIndex));
				dto.setSubject(rs.getString(++columnIndex));
				dto.setQuests(rs.getString(++columnIndex));
				dto.setInsnm(rs.getString(++columnIndex));
				dto.setInsdm(rs.getString(++columnIndex));
				dto.setSenddt(rs.getString(++columnIndex));
				dto.setQgubun(rs.getString(++columnIndex));
				dto.setAnsws(rs.getString(++columnIndex));
				dto.setShopPw(rs.getString(++columnIndex));
				dto.setApikey(rs.getString(++columnIndex));
				dto.setShopid(rs.getString(++columnIndex));
				dto.setQnaseq(rs.getString(++columnIndex));
				dto.setProdid(rs.getString(++columnIndex));
				dto.setQnastat(rs.getString(++columnIndex));
				dto.setOldstat(rs.getString(++columnIndex));
				dto.setAnswstitle(rs.getString(++columnIndex));
				dto.setStat(rs.getString(++columnIndex));
				dto.setProdnm(rs.getString(++columnIndex));
				dto.setOrdid(rs.getString(++columnIndex));
				dto.setInsnm2(rs.getString(++columnIndex));

				contents.add(dto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;

	}

	public int AnswerContentSave(QuestListDto list, String answer, String title) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		int result = 0;

		connection = DBCPInit.getInstance().getConnection();
		String sql = "update qnalist set qnastat = ?, ANSWS=?, answstitle = ? where qnaseq = ? and compno = ? ";
		sql = sql.toUpperCase();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		int i = 0;
		pstmt.setString(++i, list.getQnastat());
		pstmt.setString(++i, answer);
		pstmt.setString(++i, title);
		pstmt.setString(++i, list.getQnaseq());
		pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

		System.out.println("[AnswerContentSave]" + pstmt.toString());
		result = pstmt.executeUpdate();

		return result;
	}

	// 문의내용가지고올때 파이썬이랑 연결
	public int QuestionOrderingyn(String questFrom, String questTo) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		int result = 0;

		connection = DBCPInit.getInstance().getConnection();
		String sql = "insert into questionmst(sendfrom, sendto, orderingyn) values(?,?,?)";
		sql = sql.toUpperCase();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		int i = 0;
		pstmt.setString(++i, questFrom);
		pstmt.setString(++i, questTo);
		pstmt.setString(++i, "Y");

		System.out.println("[QuestionOrderingyn]" + pstmt.toString());
		result = pstmt.executeUpdate();

		return result;

	}

	public void QuestionDelete() throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		connection = DBCPInit.getInstance().getConnection();
		String sql = " delete from questionmst ";
		sql = sql.toUpperCase();
		pstmt = connection.prepareStatement(sql);

		System.out.println("[QuestionDelete]" + pstmt.toString());
		pstmt.executeUpdate();

	}

	public String getPythonToJava() throws Exception {

		String result = "";
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT ifnull(ORDERINGYN,'Y') from questionmst";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			System.out.println("[getPythonToJava]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int columnIndex = 0;
				result = rs.getString(++columnIndex);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public void setPythonToJava() throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " update set questionmst  pytojava = 'N' ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			System.out.println("[getPythonToJava]" + pstmt.toString());

			rs = pstmt.executeQuery();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	public String splitMark(String text) {
		String[] split = text.split("/");
		String complite = "";
		for (String element : split) {
			complite += element;
			complite = complite.trim();
		}
		return complite;
	}

	// 문의내용전체가지고오기
	public List<QuestListDto> getShopAllQuestionsList() throws Exception {
		List<QuestListDto> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT IFNULL(a.shopnm,''), IFNULL(a.regdm,''), IFNULL(a.mprodid,''), IFNULL(a.PRODNM,''), IFNULL(a.subject,''), IFNULL(a.quests,''), IFNULL(a.insnm,''), IFNULL(a.insdm,''), "
					+ " IFNULL(a.senddt,''),IFNULL(a.qgubun,''),IFNULL(a.answs,''),IFNULL(b.PASSWORD,''), IFNULL(b.APIKEY,''),ifnull(a.shopid,''),a.qnaseq,ifnull(prodid,'')"
					+ " from qnalist a join shopdtl b ON a.COMPNO = b.COMPNO AND "
					+ " a.shopnm = b.SHOPCD AND a.SHOPID = b.SHOPPINGID WHERE a.COMPNO = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				QuestListDto dto = new QuestListDto();
				int columnIndex = 0;
				dto.setShopnm(rs.getString(++columnIndex));
				dto.setRegdm(rs.getString(++columnIndex));
				dto.setMprodid(rs.getString(++columnIndex));
				dto.setProdnm(rs.getString(++columnIndex));
				dto.setSubject(rs.getString(++columnIndex));
				dto.setQuests(rs.getString(++columnIndex));
				dto.setInsnm(rs.getString(++columnIndex));
				dto.setInsdm(rs.getString(++columnIndex));
				dto.setSenddt(rs.getString(++columnIndex));
				dto.setQgubun(rs.getString(++columnIndex));
				dto.setAnsws(rs.getString(++columnIndex));
				dto.setShopPw(rs.getString(++columnIndex));
				dto.setApikey(rs.getString(++columnIndex));
				dto.setShopid(rs.getString(++columnIndex));
				dto.setQnaseq(rs.getString(++columnIndex));
				dto.setProdid(rs.getString(++columnIndex));

				contents.add(dto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public void setAllListAnswer(List<QuestListDto> list, String title, String answs) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "update qnalist set qnastat = ?, ANSWS=?, ANSWSTITLE = ? where qnaseq = ? and compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (QuestListDto dto : list) {
				int i = 0;
				pstmt.setString(++i, dto.getQnastat());
				pstmt.setString(++i, answs);
				pstmt.setString(++i, title);

				pstmt.setString(++i, dto.getQnaseq());
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				pstmt.clearParameters();
			}
			System.out.println("[ShopQuestionListSave]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}
	}

	public void QuestionListDelete(List<QuestListDto> contents) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "delete from qnalist where qnaseq = ? and compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (QuestListDto dto : contents) {
				int i = 0;
				pstmt.setString(++i, dto.getQnaseq());
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				pstmt.clearParameters();
			}
			System.out.println("[ShopQuestionListSave]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}

	}

}///////////

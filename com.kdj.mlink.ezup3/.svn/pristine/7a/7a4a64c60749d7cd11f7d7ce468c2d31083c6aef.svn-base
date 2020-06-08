package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class AlimTalkChargeDao {
	// 전체조회
	public List<String> getAlimTalkChargeList(String compnm) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(COMPNO,''),ifnull(smsaliase,''), ifnull(SMSNO,''),ifnull(smschgamt,'0'), ifnull(smsuseamt,'0'),ifnull(SMSBALANCE,'0'), "
					+ " ifnull(CONTPRICE,'0'), ifnull(SERIALno,''),ifnull(userid,''),ifnull(SMSPRICE,''), ifnull(lmsprice,'') from smschargemst where COMPNO=? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
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

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	// 알림톡보낸후 마스터수정
	public void updateMoneyKakaoInfo(String compNo, int price, int total, String seq) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			PreparedStatement pstmt_prodmst = null;

			String sql_prodmst = "update smschargemst set SMSUSEAMT=?, SMSBALANCE=?, SERIALno=? where COMPNo=?";

			sql_prodmst = sql_prodmst.toUpperCase();

			pstmt_prodmst = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt_prodmst);

			pstmt_prodmst.setInt(1, price);
			pstmt_prodmst.setInt(2, total);
			pstmt_prodmst.setString(3, seq);
			pstmt_prodmst.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());

			pstmt_prodmst.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public void insertMoneyKakaoDetail(String aliaseseq, String tocontact, String contents, String sentdate,
			String timespent, String type) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "insert into smschargedtl(compno,ALIASEseq, TOCONTACT,CONTENTS, SENTDATE, TIMESPENT,smstype) values(?, ?, ?, ?, ?, ?, ?) ";

		sql = sql.toUpperCase();

		try {
			connection = DBCPInit.getInstance().getConnection();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, aliaseseq);
			pstmt.setString(++i, tocontact);
			pstmt.setString(++i, contents);
			pstmt.setString(++i, sentdate);
			pstmt.setString(++i, timespent);
			pstmt.setString(++i, type);

			System.out.println("[insertMoneyKakaoDetail]" + pstmt.toString());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	public List<String> getTemplateCodeList(String compno, String template) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(COMPNO,''), ifnull(AREAOFUSE,''),ifnull(TEMPLATECODE,'') from kakaotemp where COMPNO=? and areaofuse = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, template);

			rs = pstmt.executeQuery();
			System.out.println("[getTemplateCodeList]" + pstmt.toString());
			while (rs.next()) {
				int i = 0;
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	// 내용전체가지고 가기
	public List<List<String>> getKakaoAllListSelect(List<String> list2) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<List<String>> contents = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(ALIASEseq,''), ifnull(TOCONTACT,''),ifnull(CONTENTS,'') ,ifnull(SENTDATE,''),ifnull(TIMESPENT,''),ifnull(smstype,'') from smschargedtl where compno = ?"
					+ " order by sentdate desc, aliaseseq desc";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();
			System.out.println("[getKakaoAllListSelect]" + pstmt.toString());
			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int i = 0;
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				if (rs.getString(i).equals("KAKAO")) {
					list.add(list2.get(6));
				} else if (rs.getString(i).equals("MMS")) {
					list.add(list2.get(9));
				} else if (rs.getString(i).equals("LMS")) {
					list.add(list2.get(10));
				} else {
					list.add("0");
				}
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

	// 검색내용 가지고 오기
	public List<List<String>> getKakaoSearchListSelect(int select, String orddtFrom, String orddtTo, String content,
			List<String> list2) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<List<String>> contents = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(ALIASEseq,''), ifnull(TOCONTACT,''),ifnull(CONTENTS,'') ,ifnull(SENTDATE,''),ifnull(TIMESPENT,''),ifnull(smstype,'') from smschargedtl where compno = ? and "
					+ "SENTDATE >= ? and SENTDATE <= ? ";

			switch (select) {
			case 0:
				sql += " and aliaseseq like ? ";
				break;
			case 1:
				sql += " and  tocontact like ? ";
				break;
			case 2:
				sql += " and contents like ? ";
				break;
			}

			sql += " order by sentdate desc ,aliaseseq desc";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, orddtFrom);
			pstmt.setString(3, orddtTo);
			switch (select) {
			case 0:
				pstmt.setString(4, "%" + content + "%");
				break;
			case 1:
				pstmt.setString(4, "%" + content + "%");
				break;
			case 2:
				pstmt.setString(4, "%" + content + "%");
				break;

			}

			rs = pstmt.executeQuery();
			System.out.println("[getKakaoSearchListSelect]" + pstmt.toString());
			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int i = 0;
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				if (rs.getString(i).equals("KAKAO")) {
					list.add(list2.get(5));
				} else if (rs.getString(i).equals("MMS")) {
					list.add(list2.get(8));
				} else if (rs.getString(i).equals("LMS")) {
					list.add(list2.get(9));
				} else {
					list.add("0");
				}

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

	// 카카오마스터내용다 가지고 오기
	public List<String> getSmsChargeMstList() throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(COMPNO,''),ifnull(SMSNO,'') ,ifnull(SMSCHGAMT,'0'),ifnull(SMSUSEAMT,'0') ,ifnull(SMSBALANCE,'0'),ifnull(CONTPRICE,''),ifnull(SERIALNO,'0'),"
					+ " ifnull(USERID,'0') from smschargemst where compno = ?";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			rs = pstmt.executeQuery();
			System.out.println("[getSmsChargeMstList]" + pstmt.toString());

			while (rs.next()) {

				int i = 0;
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<List<String>> getSmsTemplateList() throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<List<String>> contents = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(COMPNO,''), ifnull(AREAOFUSE,''),ifnull(TEMPLATECODE,''), ifnull(CONTENT,'') from kakaotemp where compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			rs = pstmt.executeQuery();
			System.out.println("[getSmsTemplateList]" + pstmt.toString());
			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int i = 0;
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

	public void updateMoneyKakaoSeqUpdate(String string, String num) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			PreparedStatement pstmt_prodmst = null;

			String sql_prodmst = "update smschargemst set SERIALno=? where COMPNo=?";

			sql_prodmst = sql_prodmst.toUpperCase();

			pstmt_prodmst = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt_prodmst);

			pstmt_prodmst.setString(1, num);
			pstmt_prodmst.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			pstmt_prodmst.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public List<String> getTemplist() throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(AREAOFUSE,'') from kakaotemp where compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			rs = pstmt.executeQuery();
			System.out.println("[getSmsTemplateList]" + pstmt.toString());
			while (rs.next()) {
				int i = 0;
				list.add(rs.getString(++i));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public void updateseqNumber(String compno, String num) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			PreparedStatement pstmt_prodmst = null;

			String sql_prodmst = "update SMSCHARGEMST set SERIALno=? where COMPNo=?";

			sql_prodmst = sql_prodmst.toUpperCase();

			pstmt_prodmst = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt_prodmst);
			pstmt_prodmst.setInt(1, Integer.parseInt(num));
			pstmt_prodmst.setString(2, compno);

			pstmt_prodmst.executeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

}

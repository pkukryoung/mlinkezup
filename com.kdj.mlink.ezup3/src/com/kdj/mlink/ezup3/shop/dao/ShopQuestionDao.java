package com.kdj.mlink.ezup3.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.shop.common.OrderStatus;
import com.kdj.mlink.ezup3.shop.common.QuestionStatus;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;
import com.kdj.mlink.ezup3.data.dao.QuestListDao;
import com.kdj.mlink.ezup3.data.dao.QuestListDto;

public class ShopQuestionDao {
	private static ShopQuestionDao daoInstance = new ShopQuestionDao(); // dao 싱글톤으로 생성 한다.

	private ShopQuestionDao() {
	}

	public static ShopQuestionDao get() {
		return daoInstance;
	}

	private String getSqlSave() {
		String sb = " insert into qnalist(qnaseq, compno,qnastat, shopnm, shopid, regdm, MPRODID, prodnm, subject, quests, insnm,insdm, qgubun,ordid,senddt,prodid,answs,stat,infono,GOODCD,insnm2,ANSWSTITLE,insertdt,insertid) "
				+ " values(?, ?, ?, ? ,? ,? ,? ,? ,? ,? , ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?)";
		return sb;
	}

	// 저장및 업데이트
	public void SaveOrUpdate(List<QuestListDto> list) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);
			// 등록구분,고객명,답변상태,문의제목,상품번호,접수일시,답변일시,쇼핑몰아이디,쇼핑몰이름,수집일자
			String sqlUpdate = " update qnalist set qnastat = ?, shopid = ?, mprodid = ?, prodnm = ?, subject = ?, quests = ?, insnm = ? ,insdm = ?, qgubun = ?,ordid =?,"
					+ " senddt=?,prodid=?, stat = ?, infono = ?, GOODCD = ?, insnm2 = ?, ANSWSTITLE = ?, modifydt = ? , modifyid = ? where compno = ? and qnaseq = ? and shopnm = ? ";
			sqlUpdate = sqlUpdate.toUpperCase();
			pstmt = connection.prepareStatement(sqlUpdate);

			PreparedStatement pstmtUpdate = connection.prepareStatement(sqlUpdate.toUpperCase());
			PreparedStatement pstmtSave = connection.prepareStatement(getSqlSave().toUpperCase());

			statementlist.add(pstmtUpdate);
			statementlist.add(pstmtSave);
			/* 반품 취소 크레임일 경우에는 주문번호가 하나 더 생긴다.. */

			boolean isSave = false;
			boolean isUpdate = false;
			boolean isLog = false;
			for (QuestListDto dto : list) {
//				int isExits = isShopQuestionExits(dto.getMprodid(), dto.getShopnm(), dto.getShopid(),dto.getOrdid(),
//						splitMark(dto.getInsdm()));
				int isExits = isShopQuestionExits(dto.getQnaseq(),dto.getShopnm());
				if (isExits > 0) {
					isUpdate = true;
//					dto.setQnaseq(String.valueOf(isExits));
					Update(dto, connection, statementlist, pstmtUpdate);

				} else {
					isSave = true;
					Save(dto, connection, statementlist, pstmtSave);
				}
				if (isUpdate) {
					pstmtUpdate.executeBatch();
					pstmtUpdate.clearBatch();
				}
				if (isSave) {
					pstmtSave.executeBatch();
					pstmtSave.clearBatch();
				}
			}			
			connection.commit();
		} catch (Exception ex) {
			connection.rollback();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	// 있는지 체크여부
	public int isShopQuestionExits(String qnaseq, String shopcd) {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "SELECT qnaseq AS CNT FROM qnalist WHERE COMPNO= ? AND qnaseq = ? and shopnm = ? ";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);
			pstmt_orddtl.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt_orddtl.setString(2, qnaseq==null?"":qnaseq);			
			pstmt_orddtl.setString(3, shopcd);
//			pstmt_orddtl.setString(4, shopid);
//			pstmt_orddtl.setString(5, insdm.substring(0, 8));
//			pstmt_orddtl.setString(6, ordid==null?"":ordid);


			rs = pstmt_orddtl.executeQuery();

			while (rs.next()) {
				result = rs.getInt("CNT");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				DBCPInit.getInstance().freeConnection(connection, pstmt_orddtl, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * 업데이트...
	 */
	private PreparedStatement Update(QuestListDto dto, Connection connection, List<PreparedStatement> statementlist,
			PreparedStatement pstmt) throws Exception {

		int i = 0;
		pstmt.setString(++i, dto.getQnastat());//
		pstmt.setString(++i, dto.getShopid());//
		pstmt.setString(++i, dto.getMprodid() == null ? "" : dto.getMprodid());
		pstmt.setString(++i, dto.getProdnm());//
		pstmt.setString(++i, dto.getSubject() == null ? "" : dto.getSubject());//
		pstmt.setString(++i, dto.getQuests());//
		pstmt.setString(++i, dto.getInsnm());//
		pstmt.setString(++i, splitMark(dto.getInsdm()).substring(0, 8));//
		pstmt.setString(++i, dto.getQgubun());//
		pstmt.setString(++i, dto.getOrdid() == null ? "" : dto.getOrdid());//
		pstmt.setString(++i, dto.getSenddt() == null ? "" : splitMark(dto.getSenddt()));//
		pstmt.setString(++i, dto.getProdid() == null ? "" : dto.getProdid());//
		//pstmt.setString(++i, dto.getAnsws() == null ? "" : dto.getAnsws());//
		pstmt.setString(++i, dto.getStat() == null ? "" : dto.getStat());//
		pstmt.setString(++i, dto.getInfono() == null ? "" : dto.getInfono());//
		pstmt.setString(++i, dto.getGoodcd() == null ? "" : dto.getGoodcd());//
		pstmt.setString(++i, dto.getInsnm2() == null ? "" : dto.getInsnm2());//
		pstmt.setString(++i, dto.getAnswstitle() == null ? "" : dto.getAnswstitle());//
		pstmt.setString(++i, YDMATimeUtil.getCurrentTime());//
		pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUsernam());//

		pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());//
		pstmt.setString(++i, dto.getQnaseq());//
		pstmt.setString(++i, dto.getShopnm());//
		
		pstmt.addBatch();
		pstmt.clearParameters();
		return pstmt;
	}

	/*
	 * 저장..
	 */
	private PreparedStatement Save(QuestListDto dto, Connection connection, List<PreparedStatement> statementlist,
			PreparedStatement pstmt) throws Exception {
		try {
			int j = 0;
			int i = 0;

			pstmt.setString(++i, dto.getQnaseq());// 
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());//
			pstmt.setString(++i, dto.getQnastat());//
			pstmt.setString(++i, dto.getShopnm());
			pstmt.setString(++i, dto.getShopid());//
			pstmt.setString(++i, dto.getRegdm());//
			pstmt.setString(++i, dto.getMprodid() == null ? "" : dto.getMprodid());
			pstmt.setString(++i, dto.getProdnm());//
			pstmt.setString(++i, dto.getSubject() == null ? "" : dto.getSubject());//
			pstmt.setString(++i, dto.getQuests());//
			pstmt.setString(++i, dto.getInsnm());//
			pstmt.setString(++i, splitMark(dto.getInsdm()).substring(0, 8));//
			pstmt.setString(++i, dto.getQgubun());//
			pstmt.setString(++i, dto.getOrdid() == null ? "" : dto.getOrdid());//
			pstmt.setString(++i, dto.getSenddt() == null ? "" : splitMark(dto.getSenddt()));//
			pstmt.setString(++i, dto.getProdid() == null ? "" : dto.getProdid());//
			pstmt.setString(++i, dto.getAnsws() == null ? "" : dto.getAnsws());//
			pstmt.setString(++i, dto.getStat() == null ? "" : dto.getStat());//
			pstmt.setString(++i, dto.getInfono() == null ? "" : dto.getInfono());//
			pstmt.setString(++i, dto.getGoodcd() == null ? "" : dto.getGoodcd());//
			pstmt.setString(++i, dto.getInsnm2() == null ? "" : dto.getInsnm2());//
			pstmt.setString(++i, dto.getAnswstitle() == null ? "" : dto.getAnswstitle());//
			pstmt.setString(++i, YDMATimeUtil.getCurrentTime());//
			pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUsernam());//

			pstmt.addBatch();
			pstmt.clearParameters();
			return pstmt;
		} catch (Exception e) {
			throw e;
		}
	}

	public String splitMark(String text) {
		String[] split = text.split("-| |:|\\.|/");
		String complite = "";
		for (int j = 0; j < split.length; j++) {
			complite += split[j];
			complite = complite.trim();
		}
		return complite;
	}

	public void SenddtUpdate(List<QuestListDto> list) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			// 등록구분,고객명,답변상태,문의제목,상품번호,접수일시,답변일시,쇼핑몰아이디,쇼핑몰이름,수집일자
			String sqlinsert = " update qnalist set qnastat = ?, senddt = ? where qnaseq = ? and compno = ? ";
			sqlinsert = sqlinsert.toUpperCase();
			pstmt = connection.prepareStatement(sqlinsert);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (QuestListDto dto : list) {
				int i = 0;
				pstmt.setString(++i, QuestionStatus.전송완료);//
				pstmt.setString(++i, dto.getSenddt());//
				pstmt.setString(++i, dto.getQnaseq());//
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

	public void statsUpdate(List<QuestListDto> list) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			// 등록구분,고객명,답변상태,문의제목,상품번호,접수일시,답변일시,쇼핑몰아이디,쇼핑몰이름,수집일자
			String sqlinsert = " update qnalist set qnastat = ?, oldstat = ? where qnaseq = ? and compno = ? ";
			sqlinsert = sqlinsert.toUpperCase();
			pstmt = connection.prepareStatement(sqlinsert);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (QuestListDto dto : list) {
				int i = 0;
				pstmt.setString(++i, dto.getQnastat());//
				pstmt.setString(++i, dto.getOldstat());//
				pstmt.setString(++i, dto.getQnaseq());//
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				pstmt.clearParameters();
			}
			System.out.println("[statsUpdate]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}

	}

	// 답변완료인것만 가지고 오기
	public List<QuestListDto> QuestionAnswer(String startdt, String enddt, String opt) throws Exception {
		List<QuestListDto> contents = new ArrayList<QuestListDto>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			if(opt.equals("INSDM")) {
				startdt = startdt.substring(0, 8);
				enddt = enddt.substring(0, 8);
			}
//			String sql = " SELECT IFNULL(a.shopnm,''), IFNULL(a.regdm,''), IFNULL(a.mprodid,''), IFNULL(a.PRODNM,''), IFNULL(a.subject,''), IFNULL(a.quests,''), IFNULL(a.insnm,''), IFNULL(a.insdm,''), "
//					+ " IFNULL(a.senddt,''),IFNULL(a.qgubun,''),IFNULL(a.answs,''),IFNULL(b.PASSWORD,''), IFNULL(b.APIKEY,''),ifnull(a.shopid,''),a.qnaseq,ifnull(a.prodid,''),ifnull(a.qnastat,''), ifnull(a.oldstat,'')"
//					+ " from qnalist a join shopdtl b ON a.COMPNO = b.COMPNO AND "
//					+ " a.shopnm = b.SHOPCD AND a.SHOPID = b.SHOPPINGID WHERE a.COMPNO = ? and A.regdm between ? and ? and a.qnastat = '101' ";
//			
			String sql = " SELECT IFNULL(a.shopnm,''), IFNULL(a.regdm,''), IFNULL(a.mprodid,''), IFNULL(a.PRODNM,''), IFNULL(a.subject,''), IFNULL(a.quests,''), IFNULL(a.insnm,''), IFNULL(a.insdm,''),"
					+ " IFNULL(a.senddt,''),IFNULL(a.qgubun,''),IFNULL(a.answs,''),IFNULL(b.PASSWORD,''), IFNULL(b.APIKEY,''),ifnull(a.shopid,''),a.qnaseq,ifnull(a.prodid,''),ifnull(a.qnastat,''), "
					+ " ifnull(a.oldstat,''),  ifnull(a.answstitle,''), ifnull(a.stat,'') , ifnull(a.infono,''), ifnull(a.GOODCD,'')"
					+ " from qnalist a join "
					+ " (\r\n" 
					+ "	SELECT COMPNO,SHOPCD,SHOPPINGID, PASSWORD,APIKEY\r\n" 
					+ "   FROM  SHOPDTL GROUP BY COMPNO,SHOPCD,SHOPPINGID\r\n" 
					+ ") B "
					+ " ON a.COMPNO = b.COMPNO AND "
					+ String.format(" a.shopnm = b.SHOPCD AND a.SHOPID = b.SHOPPINGID WHERE  a.COMPNO = ? and A.%s between ? and ? and a.qnastat = ? ", opt);
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, startdt);
			pstmt.setString(3, enddt);
			pstmt.setString(4, QuestionStatus.답변완료);
			System.out.println("[QuestionAnswer]" + pstmt.toString());

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
				dto.setInfono(rs.getString(++columnIndex));
				dto.setGoodcd(rs.getString(++columnIndex));
				
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

	// 템플릿 저장
	public void qnaTemplateSave(String temptitle, String tempansws) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "insert into qnatemp(compno,TEMPTITLE,TEMPANSWS) " + " values( ?, ?, ?) ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, temptitle);
			pstmt.setString(++i, tempansws);

			pstmt.executeUpdate();

			System.out.println("[qnaTemplateSave]" + pstmt.toString());

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}

	}

	// 템플릿 목록가지고 오기
	public List<QuestListDto> getTemplateList() throws Exception {
		List<QuestListDto> contents = new ArrayList<QuestListDto>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT ifnull(TEMPSEQ,'0'), IFNULL(TEMPTITLE,''), IFNULL(TEMPANSWS,'') "
					+ " from qnatemp where compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getTemplateList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				QuestListDto dto = new QuestListDto();
				int columnIndex = 0;
				dto.setTempseq(rs.getString(++columnIndex));
				dto.setTemptitle(rs.getString(++columnIndex));
				dto.setTempansws(rs.getString(++columnIndex));

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

	public void qnaTemplateUpdate(String temptitle, String tempansws, List<QuestListDto> content) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			// 등록구분,고객명,답변상태,문의제목,상품번호,접수일시,답변일시,쇼핑몰아이디,쇼핑몰이름,수집일자
			String sqlinsert = " update qnatemp set TEMPTITLE = ?, TEMPANSWS = ? where TEMPSEQ = ? ";
			sqlinsert = sqlinsert.toUpperCase();
			pstmt = connection.prepareStatement(sqlinsert);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (QuestListDto dto : content) {
				int i = 0;
				pstmt.setString(++i, temptitle);//
				pstmt.setString(++i, tempansws);//
				pstmt.setString(++i, dto.getTempseq());//

				pstmt.addBatch();
				pstmt.clearParameters();
			}
			System.out.println("[qnaTemplateUpdate]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}

	}

	public void templateDelete(List<QuestListDto> content) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			// 등록구분,고객명,답변상태,문의제목,상품번호,접수일시,답변일시,쇼핑몰아이디,쇼핑몰이름,수집일자
			String sqlinsert = " delete from qnatemp where tempseq = ? ";
			sqlinsert = sqlinsert.toUpperCase();
			pstmt = connection.prepareStatement(sqlinsert);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (QuestListDto dto : content) {
				int i = 0;
				pstmt.setString(++i, dto.getTempseq());//

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

}

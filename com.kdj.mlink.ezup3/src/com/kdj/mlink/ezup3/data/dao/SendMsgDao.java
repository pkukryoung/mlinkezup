package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SendMsgDao {

	public boolean sendMessgae(List<SendMsgDto> dto) throws Exception {
		boolean isSend=true;
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			insertMessage(connection, statementlist, dto);

			connection.commit();
		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			isSend=false;
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
		return isSend;
	}

	public void insertMessage(Connection connection, List<PreparedStatement> statementlist, List<SendMsgDto> dto) throws Exception {

		PreparedStatement pstmt_prodmst = null;
		
		
		String sql_prodmst = "insert into biz_msg (msg_type, cmid, request_time, send_time, send_phone, dest_phone, msg_body)"
				+ " values (?, ?, ?, ?, ?, ?, ?) ";

		sql_prodmst = sql_prodmst.toUpperCase();

		pstmt_prodmst = connection.prepareStatement(sql_prodmst);
		statementlist.add(pstmt_prodmst);

		for (int i = 0; i < dto.size(); i++) {
			//System.out.print("dao ");dto.get(i).printMsg();
			int j = 0;		
			pstmt_prodmst.setString(++j, dto.get(i).getMSG_TYPE());
			pstmt_prodmst.setString(++j, dto.get(i).getCMID());
//			pstmt_prodmst.setTimestamp(++j, dto.get(i).getREQUEST_TIME());
			pstmt_prodmst.setTimestamp(++j, dto.get(i).getREQUEST_TIME(), Calendar.getInstance());
			pstmt_prodmst.setTimestamp(++j, dto.get(i).getSEND_TIME(), Calendar.getInstance());
			pstmt_prodmst.setString(++j, dto.get(i).getSEND_PHONE());
			pstmt_prodmst.setString(++j, dto.get(i).getDEST_PHONE());
			pstmt_prodmst.setString(++j, dto.get(i).getMSG_BODY());

			pstmt_prodmst.addBatch();
			pstmt_prodmst.clearParameters();
		}
		pstmt_prodmst.executeBatch();
		pstmt_prodmst.clearBatch();// Batch 초기화
	}
	public boolean sendFax(List<SendMsgDto> dto) throws Exception {
		boolean isSend=true;
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			insertFax(connection, statementlist, dto);

			connection.commit();
		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			isSend=false;
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
		return isSend;
	}

	public void insertFax(Connection connection, List<PreparedStatement> statementlist, List<SendMsgDto> dto) throws Exception {

		PreparedStatement pstmt_prodmst = null;

		String sql_prodmst= "INSERT INTO biz_msg (MSG_TYPE, CMID, REQUEST_TIME, SEND_TIME, DEST_PHONE, SEND_PHONE, SUBJECT, MSG_BODY, ATTACHED_FILE, COVER_FLAG) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)" ;

		sql_prodmst = sql_prodmst.toUpperCase();

		pstmt_prodmst = connection.prepareStatement(sql_prodmst);
		statementlist.add(pstmt_prodmst);

		for (int i = 0; i < dto.size(); i++) {
			//System.out.print("dao ");dto.get(i).printMsg();
			int j = 0;		
			pstmt_prodmst.setString(++j, dto.get(i).getMSG_TYPE());
			pstmt_prodmst.setString(++j, dto.get(i).getCMID());
//			pstmt_prodmst.setTimestamp(++j, dto.get(i).getREQUEST_TIME());
			pstmt_prodmst.setTimestamp(++j, dto.get(i).getREQUEST_TIME(), Calendar.getInstance());
			pstmt_prodmst.setTimestamp(++j, dto.get(i).getSEND_TIME(), Calendar.getInstance());
			pstmt_prodmst.setString(++j, dto.get(i).getSEND_PHONE());
			pstmt_prodmst.setString(++j, dto.get(i).getDEST_PHONE());
			pstmt_prodmst.setString(++j, "하늘다원");
			pstmt_prodmst.setString(++j, dto.get(i).getMSG_BODY());
			pstmt_prodmst.setString(++j, "");
			pstmt_prodmst.setString(++j, "1");
			
			pstmt_prodmst.addBatch();
			pstmt_prodmst.clearParameters();
		}
		pstmt_prodmst.executeBatch();
		pstmt_prodmst.clearBatch();// Batch 초기화
	}
		
}

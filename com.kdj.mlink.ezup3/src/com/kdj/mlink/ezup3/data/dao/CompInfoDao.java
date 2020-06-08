package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class CompInfoDao {
	// 필요무
	public int getSMSSafeMoney(String companyNo) throws Exception {

		int charge = 0;
		int use = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select smschgamt, smsuseamt from companyinfo where compno=?";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				charge = rs.getInt(1);
				use = rs.getInt(2);
				System.out.println("charge " + charge + " use " + use);
			} else {
				System.out.println("Error");
				charge = 0;
				use = 0;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return charge - use;
	}

	// 필요무
	public String getSMSNo(String compNo) throws Exception {

		String result = "";

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select smsno from companyinfo where compno=?";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getString(1);
				System.out.println("sms no " + result);
			} else {
				System.out.println("Error");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	// 필요무
	public SMSInfoDto getCompInfo(String compNo) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SMSInfoDto dto = new SMSInfoDto();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select smsno, smschgamt, smsuseamt from companyinfo where compno=?";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setSmsno(rs.getString(1));
				dto.setSmschgamt(rs.getInt(2));
				dto.setSmsuseamt(rs.getInt(3));
				System.out.println("Success:CompanyInfo.getCompInfo  " + rs.getString(1) + " " + rs.getInt(2) + " "
						+ rs.getInt(3));
			} else {
				System.out.println("Error : CompanyInfo.getCompInfo");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	public List<String> getCompanyInfoByAll() throws Exception {

		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select COMPNM,SB_CMP_ID,SB_AUTH_KEY, " + "EC_COM_CD,EC_USR_ID,EC_API_KEY "
					+ "from companyinfo where compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getCompanyInfoByAll]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 1;
			while (rs.next()) {
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));// 0 COMPNM
				list.add(rs.getString(++columnIndex)); // 1 SB_CMP_ID
				list.add(rs.getString(++columnIndex)); // 2 SB_AUTH_KEY
				list.add(rs.getString(++columnIndex)); // 6 EC_COM_CD
				list.add(rs.getString(++columnIndex));// 7 EC_USR_ID
				list.add(rs.getString(++columnIndex));// 8 EC_API_KEY

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	// 필요무
	public void updateMoneyInfo(String compNo, SMSInfoDto dto) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			PreparedStatement pstmt_prodmst = null;

			String sql_prodmst = "update companyinfo set smsuseamt=? where compno=?";

			sql_prodmst = sql_prodmst.toUpperCase();

			pstmt_prodmst = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt_prodmst);

			pstmt_prodmst.setInt(1, dto.getSmsuseamt());
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

	public List<String> getCompno() throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select COMPNO, IMGFOLDER from companyinfo where compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getCompno]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));// 0 COMPNM
				list.add(rs.getString(++columnIndex));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	// 로그인시 이미지 사용
	public List<String> getCompNoImage() throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(compno,''), ifnull(compnm,''),ifnull(SB_CMP_ID,''),ifnull(SB_AUTH_KEY,''),ifnull(SB_PROD_FILE,''),ifnull(SB_ORD_FILE,''),ifnull(SB_CLAIM_FILE,''),"
					+ " ifnull(EC_COM_CD,'') ,ifnull(EC_USR_ID,''),ifnull(EC_API_KEY,''),ifnull(IMGFOLDER,''),ifnull(CUSTNO,''),ifnull(CEONM,''),ifnull(zipcode,''),ifnull(ADDRESS,'') ,ifnull(BUSINESS,''),ifnull(EVENT,'')"
					+ " ,ifnull(TELNO,''),ifnull(CPHON,''),ifnull(FAXNO,''),ifnull(EMAIL,''),ifnull(kakao_key,''),ifnull(sabangid,''),ifnull(sabangpw,''), ifnull(compals,'') from companyinfo where compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getCompNoImage]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));// 0 COMPNo
				list.add(rs.getString(++columnIndex));// compnm
				list.add(rs.getString(++columnIndex));// sb_cmp_id
				list.add(rs.getString(++columnIndex));// sb_auth_key
				list.add(rs.getString(++columnIndex));// sb_prod_file
				list.add(rs.getString(++columnIndex));// sb_ord_file
				list.add(rs.getString(++columnIndex));// sb_claim_file
				list.add(rs.getString(++columnIndex));// ec_com_cd
				list.add(rs.getString(++columnIndex));// ec_usr_id
				list.add(rs.getString(++columnIndex));// ec_api_key
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));// compals

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;

	}

	// 필요무
	public SMSInfoDto getCompFaxInfo() throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SMSInfoDto dto = new SMSInfoDto();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select smsno, smschgamt, smsuseamt from companyinfo where compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setSmsno(rs.getString(1));
				dto.setSmschgamt(rs.getInt(2));
				dto.setSmsuseamt(rs.getInt(3));
				System.out.println("Success:CompanyInfo.getCompInfo  " + rs.getString(1) + " " + rs.getInt(2) + " "
						+ rs.getInt(3));
			} else {
				System.out.println("Error : CompanyInfo.getCompInfo");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

}

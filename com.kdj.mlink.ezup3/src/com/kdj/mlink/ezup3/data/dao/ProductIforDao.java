package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.shop.dao.ShopProdAttrnmDto;

public class ProductIforDao {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 조회
	public List<String> getOpen(String number) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT seq,ifnull(infornm,''),ifnull(brandnm,''),ifnull(prodgubun,''),ifnull(manfnm,''),ifnull(orgnm,''),ifnull(season,''),"
					+ " ifnull(mfgubun,''),ifnull(prodstat,''),ifnull(locarea,''),ifnull(expgubun,''),ifnull(expcost,0),"
					+ " ifnull(taxgubun,''), ifnull(flagopt,''), ifnull(optnm1,''), ifnull(optnm2,''), ifnull(flaginvt,''),ifnull(flagoptchg,''), "
					+ " ifnull(flagoptset,''), ifnull(orgname,''),ifnull(supplier,''),ifnull(businessno,'') from prodinfor where seq = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(number));
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getOpen]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
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
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
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

	// 최대값가져오기
	public int getInfornum() throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "SELECT MAX(seq) FROM prodinfor where compno = ? ";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);
			pstmt_orddtl.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getInfornum]" + pstmt_orddtl.toString());

			rs = pstmt_orddtl.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt_orddtl, rs);
		}
		return result;
	}

	// 등록하기
	public void insertProdIFM(int seq, String infornm, String brandnm, String prodgubun, String manfnm, String orgnm,
			String season, String mfgubun, String prodstat, String locarea, String expgubun, String expcost,
			String taxgubun, String flagopt, String optnm1, String optnm2, String flaginvt, String flagoptchg,
			String flagoptset, String orgname, String supplier, String businessno) throws Exception {

		pstmt = null;
		connection = null;

		String sql = "insert into prodinfor(compno, seq, infornm, brandnm,prodgubun, manfnm, orgnm, "
				+ " season, mfgubun, prodstat, locarea, expgubun,  expcost, "
				+ " taxgubun, flagopt, optnm1, optnm2, flaginvt, flagoptchg, flagoptset,orgname,supplier,businessno ) "
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";

		sql = sql.toUpperCase();

		try {
			connection = DBCPInit.getInstance().getConnection();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setInt(++i, seq);
			pstmt.setString(++i, infornm);
			pstmt.setString(++i, brandnm);
			pstmt.setString(++i, prodgubun);
			pstmt.setString(++i, manfnm);
			pstmt.setString(++i, orgnm);
			pstmt.setString(++i, season);
			pstmt.setString(++i, mfgubun);
			pstmt.setString(++i, prodstat);
			pstmt.setString(++i, locarea);
			pstmt.setString(++i, expgubun);
			pstmt.setInt(++i, expcost.equals("") ? 0 : Integer.parseInt(expcost));
			pstmt.setString(++i, taxgubun);
			pstmt.setString(++i, flagopt);
			pstmt.setString(++i, optnm1);
			pstmt.setString(++i, optnm2);
			pstmt.setString(++i, flaginvt);
			pstmt.setString(++i, flagoptchg);
			pstmt.setString(++i, flagoptset);
			pstmt.setString(++i, orgname);
			pstmt.setString(++i, supplier);
			pstmt.setString(++i, businessno);

			System.out.println("[insertProdIFM]" + pstmt.toString());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	// 수정
	public void updateProdIFM(String seq, String infornm, String brandnm, String prodgubun, String manfnm, String orgnm,
			String season, String mfgubun, String prodstat, String locarea, String expgubun, String expcost,
			String taxgubun, String flagopt, String optnm1, String optnm2, String flaginvt, String flagoptchg,
			String flagoptset, String orgname, String supplier, String businessno) throws Exception {

		pstmt = null;
		connection = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "update prodinfor set infornm=?, brandnm=?, prodgubun=?, manfnm=?, orgnm=?,"
					+ " season=?, mfgubun=?, prodstat=?, locarea=?, expgubun=?, expcost=?,"
					+ " taxgubun=?, flagopt=?, optnm1=?, optnm2=?, flaginvt=?, flagoptchg=?, flagoptset=?, orgname=? ,supplier = ? ,businessno=? where seq=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, infornm);
			pstmt.setString(++i, brandnm);
			pstmt.setString(++i, prodgubun);
			pstmt.setString(++i, manfnm);
			pstmt.setString(++i, orgnm);
			pstmt.setString(++i, season);
			pstmt.setString(++i, mfgubun);
			pstmt.setString(++i, prodstat);
			pstmt.setString(++i, locarea);
			pstmt.setString(++i, expgubun);
			pstmt.setInt(++i, expcost.equals("") ? 0 : Integer.parseInt(expcost));
			pstmt.setString(++i, taxgubun);
			pstmt.setString(++i, flagopt);
			pstmt.setString(++i, optnm1);
			pstmt.setString(++i, optnm2);
			pstmt.setString(++i, flaginvt);
			pstmt.setString(++i, flagoptchg);
			pstmt.setString(++i, flagoptset);
			pstmt.setString(++i, orgname);
			pstmt.setString(++i, supplier);
			pstmt.setString(++i, businessno);

			pstmt.setInt(++i, Integer.parseInt(seq));
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[updateProdIFM]" + pstmt.toString());

			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	// 상품속성값 인설트
	public void prodAttrvalInsert(List<String> list, int seq) throws Exception {
		pstmt = null;
		connection = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();
			// for(int k =1;k<=list.size()-1;k++) {
			String sql = "insert into prodattrval(compno, attrcd,colseq,attrvl) values(?, ?, ?, ?) ";

			sql = sql.toUpperCase();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (int k = 1; k <= seq; k++) {
				int i = 0;
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++i, list.get(0));
				pstmt.setInt(++i, k);
				pstmt.setString(++i, list.get(k));

				pstmt.addBatch();
				pstmt.clearParameters();
			}
			System.out.println("[prodAttrvalInsert]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	// 상품속성값 업데이트
	public void prodAttrvalUpdate(List<String> list, int seq) throws Exception {
		pstmt = null;
		connection = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "update prodattrval set attrvl = ? where attrcd = ? and colseq =? and compno = ? ";

			sql = sql.toUpperCase();

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (int k = 1; k <= seq; k++) {
				int i = 0;
				pstmt.setString(++i, list.get(k));
				pstmt.setString(++i, list.get(0));
				pstmt.setInt(++i, k);
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				pstmt.clearParameters();

				// pstmt.executeUpdate();
			}
			System.out.println("[prodAttrvalUpdate]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	// 속성정보화면에 뿌리기
	public List<List<String>> getSelectList(String attrcd) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT attrcd, colseq, attrnm from prodattrnm where attrcd = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, attrcd);

			System.out.println("[getSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

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

	// api전송
	public List<List<String>> getSbapiSelect(String attrcd) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT attrcd, colseq, attrvl from prodattrval where attrcd = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, attrcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

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

	// 시즌에 값받아오기
	public List<List<String>> getSeasonItems() throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT attrcd, attrnm, flagdeft from prodattr ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			System.out.println("[getSeasonItems]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
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

	public int getpropertynum() throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "SELECT attrcd FROM prodattr ";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);

			System.out.println("[getpropertynum]" + pstmt_orddtl.toString());

			rs = pstmt_orddtl.executeQuery();
			while (rs.next()) {
				result = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt_orddtl, rs);
		}
		return result;
	}

	public List<List<String>> getSelectViewList(String attrcd) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT attrcd, colseq, attrvl from prodattrval where attrcd = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, attrcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getSelectViewList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
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

	public List<String> InsertNupdate(String attrcd) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT attrcd, colseq, attrvl from prodattrval where attrcd = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, attrcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getSelectViewList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
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

	public List<String> getInfonm(String attrcd) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT attrcd, attrnm, flagdeft from prodattr where attrcd = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, attrcd);

			System.out.println("[getInfonm]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
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

	public List<List<String>> getProdInforName() throws Exception {
		List<List<String>> contents = new ArrayList<>();
		// List<String> list = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT seq,infornm from prodinfor where compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
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

	public List<List<String>> getPropername() throws Exception {
		List<List<String>> contents = new ArrayList<>();
		// List<String> list = new ArrayList<String>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT attrcd, attrnm from prodattr ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
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

	public int getProdAttrNm(String num) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "SELECT MAX(colseq) FROM prodattrnm where attrcd = ? ";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);
			pstmt_orddtl.setString(1, num);
			System.out.println("[getInfornum]" + pstmt_orddtl.toString());

			rs = pstmt_orddtl.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt_orddtl, rs);
		}
		return result;
	}

	public List<List<String>> getProductAttrnm(String attrcd) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT attrcd, colseq, attrnm, ifnull(noticetype,''), ifnull(itemcode,'') from prodattrnm where attrcd = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, attrcd);

			System.out.println("[getSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

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

	public List<ShopProdAttrnmDto> getProductNotice(String attrcd) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ShopProdAttrnmDto> contents = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT IFNULL(A.attrcd,'') as ATTRCD , A.colseq as COLSEQ, IFNULL(A.attrnm,'') as ATTRNM,\r\n"
					+ " IFNULL(A.NOTICETYPE,'') as NOTICETYPE, IFNULL(A.ITEMCODE,'') as ITEMCODE,\r\n"
					+ " IFNULL(A.intercode,'') as  INTERCODE,  IFNULL(B.ATTRVL,'') as  ATTRVL , b.compno AS COMPNO\r\n"
					+ " from prodattrnm AS A\r\n" + " INNER JOIN prodattrval AS B\r\n"
					+ "  ON A.attrcd = B.attrcd AND A.COLSEQ = B.COLSEQ  \r\n" + "  WHERE A.attrcd= ? \r\n"
					+ "  AND B.compno=?";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, attrcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ShopProdAttrnmDto dto = new ShopProdAttrnmDto();
				dto.setAttrcd(rs.getString("ATTRCD"));
				dto.setColseq(rs.getString("COLSEQ"));
				dto.setAttrnm(rs.getString("ATTRNM"));
				dto.setNoticetype(rs.getString("NOTICETYPE"));
				dto.setItemcode(rs.getString("ITEMCODE"));
				dto.setIntercode(rs.getString("INTERCODE"));
				dto.setAttrvl(rs.getString("ATTRVL"));
				contents.add(dto);
			}

			List<ShopProdAttrnmDto> copy_contents = new ArrayList<>();

			for (ShopProdAttrnmDto d : contents) {
				List<String> interCodes = Arrays.asList(d.getIntercode().split("//"));
				List<String> itemCodes = Arrays.asList(d.getItemcode().split(","));

				for (int i = 0; i < itemCodes.size(); ++i) {
					if (i == 0) {
						d.setIntercode(interCodes.get(i));
						d.setItemcode(itemCodes.get(i));
					} else {
						ShopProdAttrnmDto newDto = new ShopProdAttrnmDto(d);
						newDto.setItemcode(itemCodes.get(i));
						newDto.setIntercode(interCodes.get(i));
						copy_contents.add(newDto);
					}
				}
			}

			if (copy_contents.size() > 0) {
				contents.addAll(copy_contents);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public List<List<String>> getInterCodeNProdattrNm(String attrcd) throws Exception {
		List<List<String>> contents = new ArrayList<>();
		List<List<String>> contents_target = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT ifnull(attrcd,''), colseq, ifnull(attrnm,''), ifnull(NOTICETYPE,''), ifnull(ITEMCODE,''), ifnull(intercode,'') from prodattrnm where attrcd = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, attrcd);

			System.out.println("[getSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));

				contents.add(list);
			}
			contents_target = getProductattrNM(contents);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents_target;
	}

	public List<List<String>> getProductattrNM(List<List<String>> prodnm) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT ifnull(attrcd,''), colseq, ifnull(ATTRVL,'') from prodattrval where compno = ? and attrcd = ? and colseq = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			for (List<String> prod : prodnm) {
				if (prod.get(5).trim().length() != 0) {
					pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
					pstmt.setString(2, prod.get(0));
					pstmt.setString(3, prod.get(1));

					rs = pstmt.executeQuery();

					while (rs.next()) {
						List<String> list = new ArrayList<>();
						int columnIndex = 0;
						list.add(prod.get(5));
						list.add(rs.getString(++columnIndex));
						list.add(rs.getString(++columnIndex));
						list.add(rs.getString(++columnIndex));

						contents.add(list);
					}

				}

			}

			System.out.println("[getSelectList]" + pstmt.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

}/////////////

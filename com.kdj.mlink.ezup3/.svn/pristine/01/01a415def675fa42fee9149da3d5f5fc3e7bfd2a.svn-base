package com.kdj.mlink.ezup3.shop.domesin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;

public class CateDao {
	private static CateDao instance = new CateDao();

	public static CateDao get() {
		return instance;
	}

	// 매핑선택 클릭시
	public int setProductMstShopCategoryUpdate(String code, String shopcd, String catno, String managercode)
			throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();
		try {

			connection = DBCPInit.getInstance().getConnection();
			String sql = "";
			if (managercode.equals("1")) {
				sql = "insert into categlgmap(compno, CODE,SHOPCD,SHOPCATNO) VALUES(?, ?, ?, ?) ON  DUPLICATE KEY  UPDATE SHOPCATNO= ? ";
			} else if (managercode.equals("2")) {
				sql = "insert into categmdmap(compno, CODE,SHOPCD,SHOPCATNO) VALUES(?, ?, ?, ?) ON  DUPLICATE KEY  UPDATE SHOPCATNO= ?";
			} else if (managercode.equals("3")) {
				sql = "insert into categsmmap(compno, CODE,SHOPCD,SHOPCATNO) VALUES(?, ?, ?, ?) ON  DUPLICATE KEY  UPDATE SHOPCATNO= ?";
			} else {
				sql = "insert into categdtlmap(compno, CODE,SHOPCD,SHOPCATNO) VALUES(?, ?, ?, ?) ON  DUPLICATE KEY  UPDATE SHOPCATNO= ?";
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;
			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, code);
			pstmt.setString(++rowIdx, shopcd);
			pstmt.setString(++rowIdx, catno);
			pstmt.setString(++rowIdx, catno);

			result = pstmt.executeUpdate();

			System.out.println("[setProductMstShopCategoryUpdate]" + pstmt.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;

	}

	/*
	 * 카테고리 대분류..
	 */
	public void categoryLargeBatch(List<CategoryItemDomesinDto> largeCategory) throws Exception {

		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "insert into categlarge (compno,code,category, disp, use_yn, comment,navercode )  "
					+ " values (?, ?, ?, ?, ?, ? , ?) ON  DUPLICATE KEY  UPDATE category = ? , disp = ? , use_yn = ? , comment = ? , navercode = ? ";
			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (CategoryItemDomesinDto dto : largeCategory) {
				int i = 0;
				pstmt.setString(++i, "4"); // YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++i, dto.cid);
				pstmt.setString(++i, dto.name);
				pstmt.setInt(++i, 0);
				pstmt.setString(++i, "1");
				pstmt.setString(++i, "도매의신카테고리");
				pstmt.setString(++i, "");

				pstmt.setString(++i, dto.name);
				pstmt.setInt(++i, 0);
				pstmt.setString(++i, "1");
				pstmt.setString(++i, "도매의신카테고리");
				pstmt.setString(++i, "");

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

	/*
	 * 카테고리 중분류..
	 */
	public void categoryMidiumBatch(List<CategoryItemDomesinDto> midiumCategory) throws Exception {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "insert into categmidium (compno,code, lrgcode, category, disp, use_yn, comment, navercode)  "
					+ " values (?, ?, ?, ?, ? ,? ,?, ?) ON  DUPLICATE KEY  UPDATE lrgcode = ? , category = ? , disp = ? , use_yn = ? , comment = ?, navercode = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (CategoryItemDomesinDto dto : midiumCategory) {
				int i = 0;
				pstmt.setString(++i, "4");
				pstmt.setString(++i, dto.cid);
				pstmt.setString(++i, dto.parent_cid);
				pstmt.setString(++i, dto.name);
				pstmt.setInt(++i, 0);
				pstmt.setString(++i, "1");
				pstmt.setString(++i, "도매의신카테고리");
				pstmt.setString(++i, "");

				pstmt.setString(++i, dto.parent_cid);
				pstmt.setString(++i, dto.name);
				pstmt.setInt(++i, 0);
				pstmt.setString(++i, "1");
				pstmt.setString(++i, "도매의신카테고리");
				pstmt.setString(++i, "");

				pstmt.addBatch();
				pstmt.clearParameters();
			}
			System.out.println("[categmidium]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

	public void categorySmallBatch(List<CategoryItemDomesinDto> smallCategory) throws Exception {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "insert into categsmall (compno, code, midcode, category, disp, use_yn, comment)  "
					+ " values (?, ?, ?, ?, ? ,?, ? ) ON  DUPLICATE KEY  UPDATE midcode = ? , category = ? , disp = ? , use_yn = ? , comment = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (CategoryItemDomesinDto dto : smallCategory) {
				int i = 0;
				pstmt.setString(++i, "4");
				pstmt.setString(++i, dto.cid);
				pstmt.setString(++i, dto.parent_cid);
				pstmt.setString(++i, dto.name);
				pstmt.setInt(++i, 0);
				pstmt.setString(++i, "1");
				pstmt.setString(++i, "도매의신카테고리");

				pstmt.setString(++i, dto.parent_cid);
				pstmt.setString(++i, dto.name);
				pstmt.setInt(++i, 0);
				pstmt.setString(++i, "1");
				pstmt.setString(++i, "도매의신카테고리");

				pstmt.addBatch();
				pstmt.clearParameters();
			}
			System.out.println("[categmidium]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

	// 대분류인설트
	public void categoryLargeInsert(String code, String category, String disp, String use_yn, String comment,
			String nvcategory) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "insert into categlarge (compno,code,category, disp, use_yn, comment,navercode )  "
					+ " values (?, ?, ?, ?, ?, ? , ?) ON  DUPLICATE KEY  UPDATE category = ? , disp = ? , use_yn = ? , comment = ? , navercode = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno()); // YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, code);
			pstmt.setString(++i, category);
			pstmt.setInt(++i, Integer.parseInt(disp));
			pstmt.setString(++i, use_yn);
			pstmt.setString(++i, comment);
			pstmt.setString(++i, nvcategory);

			pstmt.setString(++i, category);
			pstmt.setInt(++i, Integer.parseInt(disp));
			pstmt.setString(++i, use_yn);
			pstmt.setString(++i, comment);
			pstmt.setString(++i, nvcategory);
			System.out.println("[CategoryLargeInsert]" + pstmt.toString());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	// 중분류인설트
	public void categoryMidiumInsert(String differcode, String code, String category, String disp, String use_yn,
			String comment, String nvcategory) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "	INSERT INTO categmidium(COMPNO, CODE, LRGCODE, CATEGORY, DISP, USE_YN, `COMMENT`, NAVERCODE)\r\n"
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON  DUPLICATE KEY  UPDATE category = ?  ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, code);
			pstmt.setString(++i, differcode);
			pstmt.setString(++i, category);
			pstmt.setInt(++i, Integer.parseInt(disp));
			pstmt.setString(++i, use_yn);
			pstmt.setString(++i, comment);
			pstmt.setString(++i, nvcategory);

			pstmt.setString(++i, category);
			System.out.println("[midCategoryInsert]" + pstmt.toString());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	// 소분류인설트
	public void categorySmallInsert(String differcode, String code, String category, String disp, String use_yn,
			String comment, String nvcategory, String ecoupang) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "insert into categsmall (compno, code, midcode, category, disp, use_yn, comment, navercode,ecoupang)  "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?,?) ON  DUPLICATE KEY  UPDATE  category = ?  ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, code);
			pstmt.setString(++i, differcode);
			pstmt.setString(++i, category);
			pstmt.setInt(++i, Integer.parseInt(disp));
			pstmt.setString(++i, use_yn);
			pstmt.setString(++i, comment);
			pstmt.setString(++i, nvcategory);
			pstmt.setString(++i, ecoupang);

			pstmt.setString(++i, category);

			System.out.println("[smlCategoryInsert]" + pstmt.toString());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	// 세분류인설트
	public void categoryDetailInsert(String differcode, String code, String category, String disp, String use_yn,
			String comment, String nvcategory) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "insert into categdetail (compno, code, smlcode, category, disp, use_yn, comment,navercode)  "
					+ " values (?, ?, ?, ?, ?, ?, ? , ?) ON  DUPLICATE KEY  UPDATE smlcode = ? , category = ? , disp = ? , use_yn = ? , comment = ?, navercode = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, code);
			pstmt.setString(++i, differcode);
			pstmt.setString(++i, category);
			pstmt.setInt(++i, Integer.parseInt(disp));
			pstmt.setString(++i, use_yn);
			pstmt.setString(++i, comment);
			pstmt.setString(++i, nvcategory);

			pstmt.setString(++i, differcode);
			pstmt.setString(++i, category);
			pstmt.setInt(++i, Integer.parseInt(disp));
			pstmt.setString(++i, use_yn);
			pstmt.setString(++i, comment);
			pstmt.setString(++i, nvcategory);

			System.out.println("[detCategoryInsert]" + pstmt.toString());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	// mst에서 대분류가지고 올때
	public List<List<String>> getLargeCategory() throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code, category, disp, use_yn, comment " + "  from categlarge " + " where compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getLargeCategory]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//

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

	// mst에서 중분류 가져오기
	public List<List<String>> getMidiumCategory(String code) throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code, lrgcode, category, disp, use_yn, comment " + "  from categmidium "
					+ " where lrgcode = ? " + "   and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getMidCategory]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int i = 0;
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

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	// mst에서 소분류 가져오기
	public List<List<String>> getSmallCategory(String code) throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code, midcode, category, disp, use_yn, comment " + "  from categsmall "
					+ " where midcode = ? " + "   and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getSmlCategory]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int i = 0;
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

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	// mst에서 세분류 가져오기
	public List<List<String>> getDetailCategory(String code) throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code, smlcode, category, disp, use_yn, comment " + "  from categdetail "
					+ " where smlcode = ? " + "   and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getDetCategory]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int i = 0;
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

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public List<List<String>> largeCategory() throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code, disp,category, use_yn, ifnull(comment,''),ifnull(navercode,''), "
					+ " (SELECT COUNT(*) FROM categlgmap b WHERE b.COMPNO=a.compno AND b.CODE = a.CODE)  "
					+ "  from categlarge a " + " where compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[largeCategory]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex).equals("1") ? "사용중" : "미사용");
				list.add(rs.getString(++columnIndex));
				list.add("");// 다른것들과 컬럼수를 맞추기 위해서
				list.add(rs.getString(++columnIndex));
				list.add("수정");
				list.add("매핑");
				list.add("(" + rs.getString(++columnIndex) + ")");

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

	public List<List<String>> midiumCategory(String categ) throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code, disp,category, use_yn, ifnull(comment,''),lrgcode,ifnull(navercode,''), "
					+ " (SELECT COUNT(*) FROM categmdmap b WHERE b.COMPNO=a.compno AND b.CODE = a.CODE)  "
					+ "  from categmidium a " + " where lrgcode = ? " + "   and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, categ);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[midiumCategory]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex).equals("1") ? "사용중" : "미사용");
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add("");
				list.add("");
				list.add("(" + rs.getString(++columnIndex) + ")");

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

	public List<List<String>> smallCategory(String categ) throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code, disp,category, use_yn, ifnull(comment,''),midcode,ifnull(navercode,''), "
					+ " (SELECT COUNT(*) FROM categsmmap b WHERE b.COMPNO=a.compno AND b.CODE = a.CODE)  "
					+ "  from categsmall a " + " where midcode = ? " + "   and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, categ);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[smallCategory]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex).equals("1") ? "사용중" : "미사용");
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add("");
				list.add("");
				list.add("(" + rs.getString(++columnIndex) + ")");

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

	public List<List<String>> detailCategory(String categ) throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code, disp,category, use_yn, ifnull(comment,''),smlcode,ifnull(navercode,''), "
					+ " (SELECT COUNT(*) FROM categdtlmap b WHERE b.COMPNO=a.compno AND b.CODE = a.CODE)  "
					+ "  from categdetail a " + " where smlcode = ? " + "   and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, categ);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[detailCategory]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex).equals("1") ? "사용중" : "미사용");
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add("");
				list.add("");
				list.add("(" + rs.getString(++columnIndex) + ")");

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

	public void categoryLargeUpdate(String code, String category, String disp, String use_yn, String comment,
			String nvcategory) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update categlarge set category=?, disp=?, use_yn=?, comment=?, navercode = ? where code=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, category);
			pstmt.setInt(++i, Integer.parseInt(disp));
			pstmt.setString(++i, use_yn);
			pstmt.setString(++i, comment);
			pstmt.setString(++i, nvcategory);

			pstmt.setString(++i, code); // 조건 key
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[larCategoryUpdate]" + pstmt.toString());
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

	public void categoryMidiumUpdate(String differcode, String code, String category, String disp, String use_yn,
			String comment, String nvcategory) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update categmidium set category=?, disp=?, use_yn=?, comment=?, lrgcode=?, navercode = ? where code=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, category);
			pstmt.setInt(++i, Integer.parseInt(disp));
			pstmt.setString(++i, use_yn);
			pstmt.setString(++i, comment);
			pstmt.setString(++i, differcode);
			pstmt.setString(++i, nvcategory);

			pstmt.setString(++i, code); // 조건 key
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[midCategoryUpdate]" + pstmt.toString());
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

	public void categorySmallUpdate(String differcode, String code, String category, String disp, String use_yn,
			String comment, String nvcategory) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update categsmall set category=?, disp=?, use_yn=?, comment=?, midcode=?, navercode =? where code=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, category);
			pstmt.setInt(++i, Integer.parseInt(disp));
			pstmt.setString(++i, use_yn);
			pstmt.setString(++i, comment);
			pstmt.setString(++i, differcode);
			pstmt.setString(++i, nvcategory);

			pstmt.setString(++i, code); // 조건 key
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[smlCategoryUpdate]" + pstmt.toString());
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

	public void categoryDetailUpdate(String differcode, String code, String category, String disp, String use_yn,
			String comment, String nvcategory) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "update categdetail set category=?, disp=?, use_yn=?, comment=?, smlcode=?,navercode =? where code=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, category);
			pstmt.setInt(++i, Integer.parseInt(disp));
			pstmt.setString(++i, use_yn);
			pstmt.setString(++i, comment);
			pstmt.setString(++i, differcode);
			pstmt.setString(++i, nvcategory);

			pstmt.setString(++i, code); // 조건 key
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[detCategoryUpdate]" + pstmt.toString());
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

	public List<List<String>> getNaverLargeCategory() throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select nvcode, nvcategory from naverlagcateg ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			System.out.println("[nvlagCategory]" + pstmt.toString());

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

	public List<List<String>> getNaverMidiumCategory(String nacate) throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select nvcode,nvlarge, nvcategory from navermidcateg where nvlarge = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, nacate); // 조건 key

			System.out.println("[nvmidCategory]" + pstmt.toString());

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

	public List<List<String>> getNaverSmallCategory(String nacate) throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select nvcode,nvmidium, nvcategory from naversmlcateg where nvmidium = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, nacate); // 조건 key

			System.out.println("[nvsmlCategory]" + pstmt.toString());

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

	public List<List<String>> getNaverDetailCategory(String nacate) throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select nvcode,nvsmall, nvcategory from naverdetcateg where nvsmall = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, nacate); // 조건 key

			System.out.println("[nvsmlCategory]" + pstmt.toString());

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

	public List<String> naverLargeCategory(String categlagcd) throws Exception {
		List<String> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code, category, disp, use_yn, comment,ifnull(navercode,'') from categlarge where code = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, categlagcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[nvlarcategory]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {

				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<String> naverMidiumCategory(String categlagcd) throws Exception {
		List<String> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code, lrgcode,category, disp, use_yn, comment,ifnull(navercode,'') from categmidium where code = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, categlagcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[nvmidcategory]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {

				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<String> naverSmallCategory(String categlagcd) throws Exception {
		List<String> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code, midcode,category, disp, use_yn, comment,ifnull(navercode,'') from categsmall where code = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, categlagcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[nvsmlcategory]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {

				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<String> naverDetailCategory(String categlagcd) throws Exception {
		List<String> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code, smlcode,category, disp, use_yn, comment,ifnull(navercode,'') from categdetail where code = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, categlagcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[nvdetcategory]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {

				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public boolean isExistLarge(String code) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code from categlarge where code = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, code);
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

	public boolean isExistMidium(String mycode) throws Exception {
		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code from categmidium where code = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, mycode);
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

	public boolean isExistSmall(String mycode) throws Exception {
		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code from categsmall where code = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, mycode);
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

	public boolean isExistDetail(String mycode) throws Exception {
		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select code from categdetail where code = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, mycode);
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

	public int getShopcatinfMaxseq() throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt_orddtl = null;
		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql_orddtl = "SELECT ifnull(MAX(SHOPCATNO),0) FROM shopcatinf where compno = ? ";

			sql_orddtl = sql_orddtl.toUpperCase();

			pstmt_orddtl = connection.prepareStatement(sql_orddtl);
			pstmt_orddtl.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getShopcatinfMaxseq]" + pstmt_orddtl.toString());

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

	// 쇼핑몰카테고리등록
	public int setShopcatinfInsert(String shopcode, int seq, String title, String allcate, String lagcd, String midcd,
			String smlcd, String detcd, String service, String useyn, String nomal, String shopid, String comm)
			throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		int result = 0;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "insert into shopcatinf (compno, shopcd, shopcatno, shopcatnm, shopcatsitenm, shoplagcatcd, shopmidcatcd, shopsmlcatcd, shopdetcatcd, serviceprod, use_yn, SHOPGENERAL, SHOPID, insertdt,"
					+ " SHOPCOMMIS )  " + " values (?, ?, ?, ?, ? ,?, ? , ?, ?, ?, ?, ?, ?, ?, ? ) ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, shopcode);
			pstmt.setInt(++i, seq);
			pstmt.setString(++i, title);
			pstmt.setString(++i, allcate);
			pstmt.setString(++i, lagcd);
			pstmt.setString(++i, midcd);
			pstmt.setString(++i, smlcd);
			pstmt.setString(++i, detcd);
			pstmt.setString(++i, service);
			pstmt.setString(++i, useyn);
			pstmt.setString(++i, nomal);
			pstmt.setString(++i, shopid);
			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setFloat(++i, Float.parseFloat(comm) / 100);

			System.out.println("[smlCategoryInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public List<List<String>> getCategorySearchList(int date, int result, String search, String prodFrom, String prodTo,
			int shopcd, int useyn, List<String> shoplist) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT ifnull(b.SHOPCATNO,''),ifnull(c.SHOPNM,''),ifnull(c.MARKETCATEG,''),ifnull(b.SHOPCATNM,''),ifnull(b.SHOPCATSITENM,''),ifnull(b.SHOPLAGCATCD,''),ifnull(b.SHOPMIDCATCD,''),ifnull(b.SHOPSMLCATCD,''),"
					+ " ifnull(b.SHOPDETCATCD,''), ifnull(b.SERVICEPROD,''),ifnull(b.USE_YN,''),ifnull(b.INSERTDT,''),ifnull(b.MODIFYDT,'') "
					+ " FROM shopmst a JOIN shopcatinf b ON a.SHOPCD=b.SHOPCD and a.COMPNO = b.COMPNO join shopinfo c ON a.shopcd = c.shopcd where b.compno = ? ";
			if (date == 0) {
				sql += " and b.insertdt >= ? and b.insertdt <= ? ";
			} else {
				sql += " and b.updatedt >= ? and b.updatedt <= ? ";
			}

			if (useyn == 1) {
				sql += " and b.USE_YN = 'Y' ";
			} else {
				sql += " and b.USE_YN = 'N' ";
			}
			if (result == 1) {
				sql += " and b.shopcatnm like ? ";
			} else {
				sql += " and b.SHOPCATNO like ? ";
			}

			if (shopcd != 0) {
				sql += " and b.shopcd = ? ";
			}
			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			if (date == 0) {
				pstmt.setString(2, prodFrom);
				pstmt.setString(3, prodTo);
			} else {
				pstmt.setString(2, prodFrom);
				pstmt.setString(3, prodTo);
			}
			if (result == 1) {
				pstmt.setString(4, "%" + search + "%");
			} else {
				pstmt.setString(4, "%" + search + "%");
			}
			if (shopcd != 0) {
				pstmt.setString(5, shoplist.get(0));
			}

			System.out.println("[getCategorySearchList]" + pstmt.toString());
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
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
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

	public List<List<String>> getAllCategoryList() throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(a.CODE,''),ifnull(a.CATEGORY,''),ifnull(b.CODE,''),ifnull(b.LRGCODE,''),ifnull(b.CATEGORY,''),ifnull(C.CODE,''),ifnull(c.MIDCODE,''),ifnull(C.CATEGORY,''),"
					+ " ifnull(D.CODE,''),ifnull(D.SMLCODE,''),ifnull(D.CATEGORY,'') "
					+ " FROM CATEGLARGE a JOIN CATEGMIDIUM b ON a.COMPNO=b.COMPNO AND a.CODE = b.LRGCODE JOIN CATEGSMALL C ON a.COMPNO = C.COMPNO AND b.CODE = C.MIDCODE JOIN CATEGDETAIL D ON "
					+ " a.COMPNO = D.COMPNO AND C.CODE = D.SMLCODE WHERE a.compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getShopMstSelectList]" + pstmt.toString());

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

	public int setShopcatinfUpdate(String shopcode, String shopcatno, String title, String allcate, String lagcd,
			String midcd, String smlcd, String detcd, String service, String useyn, String nomal, String shopid,
			String comm) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "update shopcatinf set SHOPCATNM=?, SHOPCATSITENM=?, SHOPLAGCATCD=?, SHOPMIDCATCD=?, SHOPSMLCATCD=?, SHOPDETCATCD=?, SERVICEPROD =? , use_yn = ?,SHOPGENERAL =?, SHOPID =?, "
					+ " MODIFYDT = ?, SHOPCOMMIS = ? " + " where SHOPCATNO = ? and SHOPCD=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, title);
			pstmt.setString(++i, allcate);
			pstmt.setString(++i, lagcd);
			pstmt.setString(++i, midcd);
			pstmt.setString(++i, smlcd);
			pstmt.setString(++i, detcd);
			pstmt.setString(++i, service);
			pstmt.setString(++i, useyn);
			pstmt.setString(++i, nomal);
			pstmt.setString(++i, shopid);
			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setFloat(++i, Float.parseFloat(comm) / 100);

			pstmt.setString(++i, shopcatno); // 조건 key
			pstmt.setString(++i, shopcode); // 조건 key
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[detCategoryUpdate]" + pstmt.toString());
			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
		return result;

	}

	// 카테고리선택삭제하기
	public void catinfoDelete(List<List<String>> contents_target) throws Exception {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "delete from shopcatinf where compno = ? and shopcatno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (List<String> list : contents_target) {
				int i = 0;
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++i, list.get(0));

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
}

package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class ProductOptDao {

	public List<List<String>> getProdcutOptList(String prodcd) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select optprodcd, optprodnm, optspecdes, optea, optsale, optsaleout "
					+ ", optnotuse , optsafestock, optvertstock, optaddamt, optdelyn " + "  from optprodmst  "
					+ " where prodcd = ? " + "   and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getProdcutOptList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int idx = 0;
				list.add("" + (++rowno)); // ui grid 상의 순번 , 디비데이타 아님
				list.add(rs.getString(++idx)); // optprodcd
				list.add(rs.getString(++idx)); // optprodnm
				list.add(rs.getString(++idx)); // optspecdes
				list.add(rs.getString(++idx)); // optea
				list.add(rs.getString(++idx)); // optsale
				list.add(rs.getString(++idx)); // optsaleout
				list.add(rs.getString(++idx)); // optnotuse
				list.add(rs.getString(++idx)); // optsafestock
				list.add(rs.getString(++idx)); // optvertstock
				list.add(rs.getString(++idx)); // optaddamt
				list.add(rs.getString(++idx)); // optdelyn

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

	public int getProdcutOptCount(Connection connection, List<PreparedStatement> statementlist, String prodcd,
			String optprodcd) throws Exception {

		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String sql = "select prodcd  from optprodmst  where prodcd=? and optprodcd=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			pstmt.setString(1, prodcd);
			pstmt.setString(2, optprodcd);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getProdcutOptCount]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
			}
		} finally {
			// rs 만 release 시킴
			DBCPInit.getInstance().freeResultSet(rs);
		}

		return count;
	}

	public List<String> getProdcutOptInfo(String prodcd, String optprodcd) throws Exception {

		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select optprodcd, optprodnm, optspecdes, optea, optsale, optsaleout "
					+ ", optnotuse , optsafestock, optvertstock, optaddamt, optdelyn " + " from optprodmst"
					+ " where prodcd =? and optprodcd =? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			pstmt.setString(1, prodcd);
			pstmt.setString(2, optprodcd);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getProdcutOptInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int idx = 0;
				list.add(""); // No.
				list.add(rs.getString(++idx)); // optprodcd
				list.add(rs.getString(++idx)); // optprodnm
				list.add(rs.getString(++idx)); // optspecdes
				list.add(rs.getString(++idx)); // optea
				list.add(rs.getString(++idx)); // optsale
				list.add(rs.getString(++idx)); // optsaleout
				list.add(rs.getString(++idx)); // optnotuse
				list.add(rs.getString(++idx)); // optsafestock
				list.add(rs.getString(++idx)); // optvertstock
				list.add(rs.getString(++idx)); // optaddamt
				list.add(rs.getString(++idx)); // optdelyn
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public String makeSubCode_Y(String code) {// YWD12345 --> YYWD12345
		String code_new = ("Y" + code);
		return code_new;
	}

	public String makeSubCode_W(String code) {// YWD12345 --> WWD12345, YWS0004-AA--> 'WWS0004-AA'
		String code_new = code.substring(1, code.length());
		code_new = ("W" + code_new);
		return code_new;
	}

	public void insertProductSubAuto(Connection connection, List<PreparedStatement> statementlist, String prodcd,
			String proddtcd) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "insert into proddtl (compno, prodcd, proddtcd, insertdt, insertid) ";
		sql += " values (?, ?, ?, ?, ?) ";

		sql = sql.toUpperCase();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		int i = 0;
		pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setString(++i, prodcd);
		pstmt.setString(++i, proddtcd);
		pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUserId());

		System.out.println("[insertProductSubAuto]" + pstmt.toString());

		pstmt.executeUpdate();

	}

	public void processProductOptInsert(String prodcd, String ecount, String optprodcd, String optprodnm,
			String optspecdes, String optea, String optsale, String optsaleout, String optnotuse, String optsafestock,
			String optvertstock, String optaddamt, String optdelyn, boolean isNew) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			optcheckProdcutMst(connection, statementlist, prodcd);

			insertProdcutOpt(connection, statementlist, prodcd, optprodcd, optprodnm, optspecdes, optea, optsale,
					optsaleout, optnotuse, optsafestock, optvertstock, optaddamt, optdelyn);
			if (!isNew) {
				copyProductOpt(connection, statementlist, prodcd, ecount, optprodcd, optprodnm, optspecdes, optdelyn);
			}

			// 특정 optprodcd 가 등록될 경우 해당 옵션상품의 종속코드를 자동으로 생성시켜준다.
			if (optprodcd.startsWith("YWD") || optprodcd.startsWith("YWC") || optprodcd.startsWith("YWT")
					|| optprodcd.startsWith("YWS") || optprodcd.startsWith("YWL") || optprodcd.startsWith("YWH")
					|| optprodcd.startsWith("YWM") || optprodcd.startsWith("YWR")) {

//				YWD -> YYWD , WWD
//				YWC -> YYWC , WWC
//				YWT -> YYWT , WWT
//				YWS -> YYWS , WWS
//				YWL -> YYWL , WWL
//				YWH -> YYWH , WWH
//				YWM -> YYWM , WWM
//				YWR -> YYWR , WWR

				String subCodeY = makeSubCode_Y(optprodcd);
				insertProductSubAuto(connection, statementlist, optprodcd, subCodeY);

				String subCodeW = makeSubCode_W(optprodcd);
				insertProductSubAuto(connection, statementlist, optprodcd, subCodeW);

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

	/**
	 * 옵션상품을 가지는 마스터상품은 optchk 컬럼 1로 세팅함.
	 * 
	 * @param connection
	 * @param statementlist
	 * @param prodcd
	 * @throws Exception
	 */
	public void optcheckProdcutMst(Connection connection, List<PreparedStatement> statementlist, String prodcd)
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "update prodmst set optchk=? where prodcd=? and compno = ? ";
		sql = sql.toUpperCase();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		int idx = 0;
		pstmt.setString(++idx, "1");
		pstmt.setString(++idx, prodcd);
		pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());

		System.out.println("[optcheckProdcutMst]" + pstmt.toString());

		pstmt.executeUpdate();
	}

	public void insertProdcutOpt(Connection connection, List<PreparedStatement> statementlist, String prodcd,
			String optprodcd, String optprodnm, String optspecdes, String optea, String optsale, String optsaleout,
			String optnotuse, String optsafestock, String optvertstock, String optaddamt, String optdelyn)
			throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "insert into optprodmst (compno, prodcd, optprodcd, optprodnm, optspecdes, ";
		sql += " optea, optsale, optsaleout, optnotuse, optsafestock, optvertstock,  optaddamt, optdelyn, insertdt, insertid) ";
		sql += " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) ";

		sql = sql.toUpperCase();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		int idx = 0;
		pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setString(++idx, prodcd);
		pstmt.setString(++idx, optprodcd);
		pstmt.setString(++idx, optprodnm);
		pstmt.setString(++idx, optspecdes);
		pstmt.setString(++idx, optea);
		pstmt.setString(++idx, optsale);
		pstmt.setString(++idx, optsaleout);
		pstmt.setString(++idx, optnotuse);
		pstmt.setString(++idx, optsafestock);
		pstmt.setString(++idx, optvertstock);
		pstmt.setString(++idx, optaddamt);
		pstmt.setString(++idx, optdelyn);
		pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());

		System.out.println("[insertProdcutOpt]" + pstmt.toString());

		pstmt.executeUpdate();

	}

	public void copyProductOpt(Connection connection, List<PreparedStatement> statementlist, String prodcd,
			String ecount, String optprodcd, String optprodnm, String optspecdes, String optdelyn) throws Exception {

		PreparedStatement pstmt = null;

		// --상품마스타에 옵션상품을 추가시 옵션상품의 컬럼값들은 상품마스터의 값을 그데로 사용함.
		// --상품마스터의 값 파라미터로 들고 다니지 않고 디비에서 조회하여 복사함.
		ProductMstDao dao = new ProductMstDao();
		ProductMstDto pmdto = dao.getProdcutInfoByProdcd(prodcd);

		// 옵션상품의 경우 상품마스터에 인서트시에 OPTCHK 컬럼의 값은 넣지 않음
		// 하위에 옵션상품을 갖는 마스터상품만 OPTCHK='1' 로 세팅함.
		String sql = "insert into prodmst (compno, prodcd, ecountcd,prodnm, specdes, flagset, flagplus, flagout, "
				+ " price, tagprice, sabcd, remark, ea,  useyn, delyn, insertdt, insertid ,cusprice)  "
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? ) ";

		sql = sql.toUpperCase();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		int idx = 0;
		pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt.setString(++idx, optprodcd);
		pstmt.setString(++idx, ecount);
		pstmt.setString(++idx, optprodnm);
		pstmt.setString(++idx, optspecdes);
		pstmt.setString(++idx, pmdto.getFlagset());
		pstmt.setString(++idx, pmdto.getFlagplus());
		pstmt.setString(++idx, pmdto.getFlagout());
		pstmt.setString(++idx, pmdto.getPrice());
		pstmt.setString(++idx, pmdto.getTagprice());
		pstmt.setString(++idx, pmdto.getSabcd());
		pstmt.setString(++idx, pmdto.getRemark());
		pstmt.setString(++idx, pmdto.getEa());
		pstmt.setString(++idx, pmdto.getUseyn());
		pstmt.setString(++idx, pmdto.getDelyn());
		pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());
		pstmt.setInt(++idx, 0);

		System.out.println("[copyProductOpt]" + pstmt.toString());

		pstmt.executeUpdate();

	}

	public void deleteProductFromOptProdmst(Connection connection, List<PreparedStatement> statementlist, String prodcd,
			String optprodcd) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "update optprodmst set OPTDELYN=?, modifydt=?, modifyid=? where prodcd=? and optprodcd=? and compno = ? ";
		sql = sql.toUpperCase();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		int idx = 0;
		pstmt.setString(++idx, "N");
		pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());
		pstmt.setString(++idx, prodcd);
		pstmt.setString(++idx, optprodcd);
		pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());

		System.out.println("[deleteProductFromOptProdmst]" + pstmt.toString());

		pstmt.executeUpdate();

	}

	public void processProductOptUpdate(String prodcd, String optprodcd, String optprodnm, String optspecdes,
			String optea, String optsale, String optsaleout, String optnotuse, String optsafestock, String optvertstock,
			String optaddamt, String optdelyn) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);
			// 옵션의 업데이트는 마스타에는 적용안함.
			// updateProdmst(connection, statementlist, optprodcd, optdelyn);

			updateOptProdmst(connection, statementlist, prodcd, optprodcd, optprodnm, optspecdes, optea, optsale,
					optsaleout, optnotuse, optsafestock, optvertstock, optaddamt, optdelyn);

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public void updateProdmst(Connection connection, List<PreparedStatement> statementlist, String optprodcd,
			String optdelyn) throws Exception {

		PreparedStatement pstmt = null;
		// 옵션을 수정한 경우 prodmst 에서 변경되는 것은 삭제여부와 변경자정보이다. 나머지는 optprodmst에 적용됨.
		String sql = "update prodmst set delyn=?, modifydt=?, MODIFYID=? where prodcd=? and compno = ? ";
		sql = sql.toUpperCase();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		int idx = 0;
		pstmt.setString(++idx, optdelyn);
		pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());
		pstmt.setString(++idx, optprodcd);
		pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
		System.out.println("[updateProdmst-opt]" + pstmt.toString());

		pstmt.executeUpdate();

	}

	public void updateOptProdmst(Connection connection, List<PreparedStatement> statementlist, String prodcd,
			String optprodcd, String optprodnm, String optspecdes, String optea, String optsale, String optsaleout,
			String optnotuse, String optsafestock, String optvertstock, String optaddamt, String optdelyn)
			throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "update optprodmst set optprodnm=?, optspecdes=?, optea=?, optsale=?, optsaleout=?, "
				+ "   optnotuse=?, optsafestock=?, optvertstock=?,  optaddamt=?, optdelyn=? , modifydt=?, modifyid=? "
				+ "   where prodcd=? and  optprodcd=? and compno = ? ";

		sql = sql.toUpperCase();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		int idx = 0;
		pstmt.setString(++idx, optprodnm);
		pstmt.setString(++idx, optspecdes);
		pstmt.setString(++idx, optea);
		pstmt.setString(++idx, optsale);
		pstmt.setString(++idx, optsaleout);
		pstmt.setString(++idx, optnotuse);
		pstmt.setString(++idx, optsafestock);
		pstmt.setString(++idx, optvertstock);
		pstmt.setString(++idx, optaddamt);
		pstmt.setString(++idx, optdelyn);
		pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());
		pstmt.setString(++idx, prodcd);
		pstmt.setString(++idx, optprodcd);
		pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
		System.out.println("[updateOptProdmst]" + pstmt.toString());

		pstmt.executeUpdate();

	}

	public boolean isExistProductNProdcd(String optprodcd) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select optprodcd from optprodmst where optprodcd = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, optprodcd);
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

}

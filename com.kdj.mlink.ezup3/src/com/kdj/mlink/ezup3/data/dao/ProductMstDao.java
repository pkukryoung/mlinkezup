package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class ProductMstDao {

	public static String GUBUN_E = "E";
	public static String GUBUN_S = "S";

	/**
	 * 이름으로 상품검색 이름이 없으면 전체 검색이됨
	 *
	 * @param optStr
	 * @return
	 * @throws Exception
	 */
	public List<ProductMstDto> getProdcutListByOption(int opt, String optStr) throws Exception {

		// opt == {상품코드, 상품명}
		List<ProductMstDto> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// YWM_FUNC_BOSSPRODCD(",V_COMPNO, ",a.prodcd) as img
			String sql = "select prodcd, "
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,p.prodcd) as img,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ " ifnull(p.prodnm,'') , ifnull(specdes,''), " + " ifnull(p.ecountcd,''),  "
					+ " ifnull(p.flagset,''), ifnull(p.flagplus,''), ifnull(p.flagout,''), ifnull(p.price,''),  "
					+ " ifnull(p.TAGPRICE,''), ifnull(p.sabcd,''), " + " ifnull(p.remark,''), ifnull(p.ea,''), "
					+ " ifnull(p.useyn,''), ifnull(p.delyn,''), "
					+ " ifnull(p.insertdt,''), ifnull(u.usernam,''), ifnull(p.modifydt,''), ifnull(u.usernam,''),  "
					+ " ifnull(p.cusprice,''), ifnull(p.aproinvt,''), ifnull(p.repstt,''), ifnull(p.addtn1,''), ifnull(p.addtn2,''), ifnull(p.searchwd,''),ifnull(p.orderprice,'0') "
					+ " from prodmst as p left join userinf as u  on p.insertid=u.userid and p.compno = u.compno where p.compno = ? ";

			if (opt == 0) {
				if (optStr.length() != 0) {
					sql += " and  p.prodcd like ? ";
				}
				sql += " order by p.prodcd ";
			} else if (opt == 1) {
				if (optStr.length() != 0) {
					sql += " and  p.prodnm like ? ";
				}
				sql += " order by p.prodnm ";
			} else {

			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			if (opt == 0 || opt == 1) {
				if (optStr.length() != 0) {
					pstmt.setString(2, "%" + optStr + "%");
				}
			}

			System.out.println("[getProdcutListByOption]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ProductMstDto dto = new ProductMstDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setProdcd(rs.getString(++i));
				dto.setImg(rs.getString(++i)); // 이미지.
				dto.setProdnm(rs.getString(++i));
				dto.setSpecdes(rs.getString(++i));
				dto.setEcountcd(rs.getString(++i));
				dto.setFlagset(rs.getString(++i));
				dto.setFlagplus(rs.getString(++i));
				dto.setFlagout(rs.getString(++i));
				dto.setPrice(rs.getString(++i));
				dto.setTagprice(rs.getString(++i));
				dto.setSabcd(rs.getString(++i));
				dto.setRemark(rs.getString(++i));
				dto.setEa(rs.getString(++i));
				dto.setUseyn(rs.getString(++i));
				dto.setDelyn(rs.getString(++i));
				dto.setInsertdt(rs.getString(++i));
				dto.setInsertid(rs.getString(++i));
				dto.setModifydt(rs.getString(++i));
				dto.setModifyid(rs.getString(++i));
				dto.setCusprice(rs.getString(++i));
				dto.setAproinvt(rs.getString(++i));
				dto.setRepstt(rs.getString(++i));
				dto.setAddtn1(rs.getString(++i));
				dto.setAddtn2(rs.getString(++i));
				dto.setSearchwd(rs.getString(++i));
				dto.setOrderprice(rs.getString(++i));

				list.add(dto);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<ProductMstDto> getProdcutListByOptionSearch(int opt, String optStr) throws Exception {

		// opt == {상품코드, 상품명}
		List<ProductMstDto> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// YWM_FUNC_BOSSPRODCD(",V_COMPNO, ",a.prodcd) as img
			String sql = "select p.prodcd, "
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,p.prodcd) as img,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ " ifnull(p.prodnm,'') , ifnull(specdes,''), " + " ifnull(p.ecountcd,''),  "
					+ " ifnull(p.flagset,''), ifnull(p.flagplus,''), ifnull(p.flagout,''), ifnull(p.price,''),  "
					+ " ifnull(p.TAGPRICE,''), ifnull(p.sabcd,''), " + " ifnull(p.remark,''), ifnull(p.ea,''), "
					+ " ifnull(p.useyn,''), ifnull(p.delyn,''), "
					+ " ifnull(p.insertdt,''), ifnull(u.usernam,''), ifnull(p.modifydt,''), ifnull(u.usernam,''),  "
					+ " ifnull(p.cusprice,''), ifnull(p.aproinvt,''), ifnull(p.repstt,''), ifnull(p.addtn1,''), ifnull(p.addtn2,''), ifnull(p.searchwd,''),ifnull(p.orderprice,'0'), "
					+ " ifnull(p.categlagcd,''), ifnull(p.CATEGMIDCD,''), ifnull(p.CATEGSMLCD,''), ifnull(p.CATEGDETCD,''), ifnull(p.INFORNUM,''), ifnull(p.PROPERTYNUM,''), ifnull(r.neccd1,''), "
					+ " ifnull(r.neccd2,''), ifnull(r.neccd3,''), "
					+ " ifnull(c.DELIVAMT,0), ifnull(t.DELIVTABNO,0), ifnull(b.EXPINVQTY,''), ifnull(b.expfile,'') "
					+ " FROM PRODMST AS P LEFT JOIN EXPPRODMST AS B ON P.COMPNO = B.COMPNO AND P.PRODCD = B.PRODCD "
					+ " LEFT JOIN USERINF AS U  ON P.INSERTID=U.USERID AND P.COMPNO = U.COMPNO "
					+ " LEFT JOIN EXPORDQTYTAB AS T ON P.COMPNO = T.COMPNO AND P.PRODCD = T.PRODCD AND T.ORDQTY = 1"
					+ " LEFT JOIN EXPCOST AS C ON P.COMPNO = C.COMPNO AND P.PRODCD = C.PRODCD AND C.TABNO = 1 "
					+ " LEFT JOIN NECPRODMST AS R ON P.COMPNO = R.COMPNO AND P.PRODCD = R.PRODCD "
					+ " WHERE P.COMPNO = ? ";

			if (opt == 0) {
				if (optStr.length() != 0) {
					sql += " and  p.prodcd like ? ";
				}
				sql += " order by p.prodcd ";
			} else if (opt == 1) {
				if (optStr.length() != 0) {
					sql += " and  p.prodnm like ? ";
				}
				sql += " order by p.prodnm ";
			} else {

			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			if (opt == 0 || opt == 1) {
				if (optStr.length() != 0) {
					pstmt.setString(2, "%" + optStr + "%");
				}
			}

			System.out.println("[getProdcutListByOption]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ProductMstDto dto = new ProductMstDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setProdcd(rs.getString(++i));
				dto.setImg(rs.getString(++i)); // 이미지.
				dto.setProdnm(rs.getString(++i));
				dto.setSpecdes(rs.getString(++i));
				dto.setEcountcd(rs.getString(++i));
				dto.setFlagset(rs.getString(++i));
				dto.setFlagplus(rs.getString(++i));
				dto.setFlagout(rs.getString(++i));
				dto.setPrice(rs.getString(++i));
				dto.setTagprice(rs.getString(++i));
				dto.setSabcd(rs.getString(++i));
				dto.setRemark(rs.getString(++i));
				dto.setEa(rs.getString(++i));
				dto.setUseyn(rs.getString(++i));
				dto.setDelyn(rs.getString(++i));
				dto.setInsertdt(rs.getString(++i));
				dto.setInsertid(rs.getString(++i));
				dto.setModifydt(rs.getString(++i));
				dto.setModifyid(rs.getString(++i));
				dto.setCusprice(rs.getString(++i));
				dto.setAproinvt(rs.getString(++i));
				dto.setRepstt(rs.getString(++i));
				dto.setAddtn1(rs.getString(++i));
				dto.setAddtn2(rs.getString(++i));
				dto.setSearchwd(rs.getString(++i));
				dto.setOrderprice(rs.getString(++i));
				dto.setCateglagcd(rs.getString(++i));
				dto.setCategmidcd(rs.getString(++i));
				dto.setCategsmlcd(rs.getString(++i));
				dto.setCategdetcd(rs.getString(++i));
				dto.setInfornum(rs.getString(++i));
				dto.setPropertynum(rs.getString(++i));
//				dto.setOptchk(rs.getString(++i));
//				dto.setInfornm(rs.getString(++i));
//				dto.setBrandnm(rs.getString(++i));
//				dto.setProdgubun(rs.getString(++i));
//				dto.setManfnm(rs.getString(++i));
//				dto.setOrgnm(rs.getString(++i));
//				dto.setSeason(rs.getString(++i));
//				dto.setMfgubun(rs.getString(++i));
//				dto.setProdstat(rs.getString(++i));
//				dto.setLocarea(rs.getString(++i));
//				dto.setExpgubun(rs.getString(++i));
//				dto.setExpcost(rs.getString(++i));
//				dto.setTaxgubun(rs.getString(++i));
//				dto.setFlagopt(rs.getString(++i));
//				dto.setOptnm1(rs.getString(++i));
//				dto.setOptnm2(rs.getString(++i));
//				dto.setFlaginvt(rs.getString(++i));
//				dto.setFlagoptchg(rs.getString(++i));
//				dto.setFlagoptset(rs.getString(++i));
//				dto.setOrgname(rs.getString(++i));
//				dto.setSupplier(rs.getString(++i));
//				dto.setBusinessno(rs.getString(++i));
//				dto.setAttrnm(rs.getString(++i));
				dto.setNeccd1(rs.getString(++i));
				dto.setNeccd2(rs.getString(++i));
				dto.setNeccd3(rs.getString(++i));
				dto.setDelivamt(rs.getString(++i));
				dto.setDellivtabno(rs.getString(++i));
				dto.setExpinvqty(rs.getString(++i));
				dto.setExpfile(rs.getString(++i));

				list.add(dto);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<ProductMstDto> getProdcutListByOptionForClaim(int opt, String optStr) throws Exception {

		// opt == {상품코드, 상품명}
		List<ProductMstDto> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select prodcd, "
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,prodcd) as img,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ " CONCAT( IFNULL(P.PRODNM,''),'_',IFNULL(P.SPECDES,'') ) as prodnm, "
					+ " ifnull(p.specdes,''), ifnull(p.ecountcd,''),  "
					+ " ifnull(p.flagset,''), ifnull(p.flagplus,''), ifnull(p.flagout,''), ifnull(p.price,''),  "
					+ " ifnull(p.TAGPRICE,''), ifnull(p.sabcd,''), " + " ifnull(p.remark,''), ifnull(p.ea,''), "
					+ " ifnull(p.useyn,''), ifnull(p.delyn,''), "
					+ " ifnull(p.insertdt,''), ifnull(u.usernam,''), ifnull(p.modifydt,''), ifnull(u.usernam,''),  "
					+ " ifnull(p.cusprice,''), ifnull(p.aproinvt,'') "
					+ " from prodmst as p left join userinf as u  on (p.insertid=u.userid and p.compno = u.compno ) where p.compno = ? ";

			if (opt == 0) {
				if (optStr.length() != 0) {
					sql += " and  p.prodcd like ? ";
				}
				sql += " order by p.prodcd ";
			} else if (opt == 1) {
				if (optStr.length() != 0) {
					sql += " and  p.prodnm like ? ";
				}
				sql += " order by p.prodnm ";
			} else {

			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			if (opt == 0 || opt == 1) {
				if (optStr.length() != 0) {
					pstmt.setString(2, "%" + optStr + "%");
				}
			}

			System.out.println("[getProdcutListByOptionForClaim]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ProductMstDto dto = new ProductMstDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setProdcd(rs.getString(++i));
				dto.setImg(rs.getString(++i)); // 추가 상품 이미지
				dto.setProdnm(rs.getString(++i));
				dto.setSpecdes(rs.getString(++i));
				dto.setEcountcd(rs.getString(++i));
				dto.setFlagset(rs.getString(++i));
				dto.setFlagplus(rs.getString(++i));
				dto.setFlagout(rs.getString(++i));
				dto.setPrice(rs.getString(++i));
				dto.setTagprice(rs.getString(++i));
				dto.setSabcd(rs.getString(++i));
				dto.setRemark(rs.getString(++i));
				dto.setEa(rs.getString(++i));
				dto.setUseyn(rs.getString(++i));
				dto.setDelyn(rs.getString(++i));
				dto.setInsertdt(rs.getString(++i));
				dto.setInsertid(rs.getString(++i));
				dto.setModifydt(rs.getString(++i));
				dto.setModifyid(rs.getString(++i));
				dto.setCusprice(rs.getString(++i));
				dto.setAproinvt(rs.getString(++i));

				list.add(dto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public ProductMstDto getProdcutInfoByProdcd(String prodcd) throws Exception {

		ProductMstDto dto = new ProductMstDto();
		;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select p.prodcd AS PRODCD, "
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,p.prodcd) as IMG,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ "ifnull(p.prodnm,'') AS PRODNM, ifnull(p.specdes,'') AS SPECDES, ifnull(p.ecountcd,'') AS  ECOUNTCD,  "
					+ " ifnull(p.flagset,'') AS FLAGSET , ifnull(p.flagplus,'') AS FLAGPLUS, ifnull(p.flagout,'') AS FLAGOUT , ifnull(p.price,'0') AS PRICE,  "
					+ " ifnull(p.TAGPRICE,'0') AS TAGPRICE , ifnull(p.sabcd,'') AS SABCD, ifnull(p.remark,'') AS REMARK, ifnull(p.ea,'0') AS EA, "
					+ " ifnull(p.useyn,'') AS USEYN, ifnull(p.delyn,'') AS DELYN,"
					+ " ifnull(p.insertdt,'') AS INSERTDT , ifnull(p.insertid,'') AS INSERTID, ifnull(p.modifydt,'') AS MODIFYDT, ifnull(p.modifyid,'') AS MODIFYID, "
					+ " ifnull(p.cusprice,'') AS CUSPRICE,ifnull(p.aproinvt,'') AS APROINVT, ifnull(p.repstt,'') AS REPSTT, ifnull(p.addtn1,'') AS ADDTN1, ifnull(p.addtn2,'') AS ADDTN2, ifnull(p.searchwd,'') AS SEARCHWD,ifnull(p.categlagcd,'') AS CATEGLAGCD ,"
					+ " ifnull(p.categmidcd,'') AS CATEGMIDCD, ifnull(p.categsmlcd,'') AS CATEGSMLCD, ifnull(p.categdetcd,'') AS CATEGDETCD,ifnull(p.orderprice,'0') AS  ORDERPRICE, "
					+ " ifnull(p.INFORNUM,'') AS INFORNUM, ifnull(p.PROPERTYNUM,'') AS PROPERTYNUM, ifnull(r.neccd1,'') AS NECCD1, ifnull(r.neccd2,'') AS NECCD2, ifnull(r.neccd3,'') AS NECCD3, "
					+ " IFNULL(c.DELIVAMT,0) AS DELIVAMT, IFNULL(t.DELIVTABNO,0) AS DELIVTABNO, ifnull(b.EXPINVQTY,'') AS EXPINVQTY, ifnull(b.expfile,'') AS EXPFILE "
					+ " from prodmst AS P LEFT JOIN EXPPRODMST AS B ON P.COMPNO = B.COMPNO AND P.PRODCD = B.PRODCD "
					+ " LEFT JOIN EXPORDQTYTAB AS T ON P.COMPNO = T.COMPNO AND P.PRODCD = T.PRODCD AND T.ORDQTY = 1 "
					+ " LEFT JOIN EXPCOST AS C ON P.COMPNO = C.COMPNO AND P.PRODCD = C.PRODCD AND C.TABNO = 1 "
					+ " LEFT JOIN NECPRODMST AS R ON P.COMPNO = R.COMPNO AND P.PRODCD = R.PRODCD "
					+ " where p.prodcd=? and p.compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getProdcutInfoByProdcd]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;
			if (rs.next()) {
				int i = 0;

				dto.setRowno("" + (++rowno));
				dto.setProdcd(rs.getString("PRODCD"));
				dto.setImg(rs.getString("IMG"));
				dto.setProdnm(rs.getString("PRODNM"));
				dto.setSpecdes(rs.getString("SPECDES"));
				dto.setEcountcd(rs.getString("ECOUNTCD"));
				dto.setFlagset(rs.getString("FLAGSET"));
				dto.setFlagplus(rs.getString("FLAGPLUS"));
				dto.setFlagout(rs.getString("FLAGOUT"));
				dto.setPrice(rs.getString("PRICE"));
				dto.setTagprice(rs.getString("TAGPRICE"));
				dto.setSabcd(rs.getString("SABCD"));
				dto.setRemark(rs.getString("REMARK"));
				dto.setEa(rs.getString("EA"));
				dto.setUseyn(rs.getString("USEYN"));
				dto.setDelyn(rs.getString("DELYN"));
				dto.setInsertdt(rs.getString("INSERTDT"));
				dto.setInsertid(rs.getString("INSERTID"));
				dto.setModifydt(rs.getString("MODIFYDT"));
				dto.setModifyid(rs.getString("MODIFYID"));
				dto.setCusprice(rs.getString("CUSPRICE"));
				dto.setAproinvt(rs.getString("APROINVT"));
				dto.setRepstt(rs.getString("REPSTT"));
				dto.setAddtn1(rs.getString("ADDTN1"));
				dto.setAddtn2(rs.getString("ADDTN2"));
				dto.setSearchwd(rs.getString("SEARCHWD"));
				dto.setLagcategory(rs.getString("CATEGLAGCD"));
				dto.setMidcategory(rs.getString("CATEGMIDCD"));
				dto.setSmlcategory(rs.getString("CATEGSMLCD"));
				dto.setDetcategory(rs.getString("CATEGDETCD"));
				dto.setOrderprice(rs.getString("ORDERPRICE"));
				dto.setInfornum(rs.getString("INFORNUM"));
				dto.setPropertynum(rs.getString("PROPERTYNUM"));
				dto.setNeccd1(rs.getString("NECCD1"));
				dto.setNeccd2(rs.getString("NECCD2"));
				dto.setNeccd3(rs.getString("NECCD3"));
				dto.setDelivamt(rs.getString("DELIVAMT"));
				dto.setDellivtabno(rs.getString("DELIVTABNO"));
				dto.setExpinvqty(rs.getString("EXPINVQTY"));
				dto.setExpfile(rs.getString("EXPFILE"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	/**
	 * 상품코드 중복체크
	 *
	 * @param prodcd
	 * @return
	 * @throws Exception
	 */
	public boolean isExistProduct(String prodcd) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select prodcd from prodmst where prodcd = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
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

	public void processProductMstInsert(String prodcd, String prodnm, String specdes, String flagset, String flagplus,
			String flagout, String price, String tagprice, String sabcd, String remark, String ea, String useyn,
			String delyn, String cusprice, String aproinvt, String representative, String additional1,
			String additional2, String infornum, String propertynum, String searchwd, String categlagcd,
			String categmidcd, String categsmlcd, String categdetcd, String orderprice, String ecountcd)
			throws Exception {

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
			String sql_prodmst = "insert into prodmst (compno, prodcd, prodnm, specdes, flagset, flagplus, flagout, "
					+ " price, tagprice, sabcd, remark, ea,  useyn, delyn, insertdt, insertid, cusprice, aproinvt,repstt, addtn1, addtn2, "
					+ " infornum, propertynum,searchwd,categlagcd,categmidcd,categsmlcd,categdetcd,orderprice,ecountcd )  "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?,? ) ";

			sql_prodmst = sql_prodmst.toUpperCase();

			pstmt_prodmst = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt_prodmst);

			int i = 0;
			pstmt_prodmst.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt_prodmst.setString(++i, prodcd);
			pstmt_prodmst.setString(++i, prodnm);
			pstmt_prodmst.setString(++i, specdes);
			// --- prodmst의 이카운트코드는 상품코드 이카운트 전송되고 업데이트함
			pstmt_prodmst.setString(++i, flagset);
			pstmt_prodmst.setString(++i, flagplus);
			pstmt_prodmst.setString(++i, flagout);

			pstmt_prodmst.setString(++i, price);
			pstmt_prodmst.setString(++i, tagprice);
			pstmt_prodmst.setString(++i, sabcd);
			pstmt_prodmst.setString(++i, remark);
			pstmt_prodmst.setString(++i, ea);

			pstmt_prodmst.setString(++i, useyn);
			pstmt_prodmst.setString(++i, delyn);
			pstmt_prodmst.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt_prodmst.setString(++i, YDMASessonUtil.getUserInfo().getUserId());
			pstmt_prodmst.setString(++i, cusprice);
			pstmt_prodmst.setString(++i, aproinvt);
			pstmt_prodmst.setString(++i, representative);
			pstmt_prodmst.setString(++i, additional1);
			pstmt_prodmst.setString(++i, additional2);
			pstmt_prodmst.setString(++i, infornum);
			pstmt_prodmst.setString(++i, propertynum);
			pstmt_prodmst.setString(++i, searchwd);
			pstmt_prodmst.setString(++i, categlagcd);
			pstmt_prodmst.setString(++i, categmidcd);
			pstmt_prodmst.setString(++i, categsmlcd);
			pstmt_prodmst.setString(++i, categdetcd);
			pstmt_prodmst.setString(++i, orderprice);
			pstmt_prodmst.setString(++i, ecountcd);

			// System.out.println(pstmt_prodmst.toString());
			System.out.println("[processProductMstInsert]" + pstmt_prodmst.toString());

			pstmt_prodmst.execute();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public void insertProductMst(Connection connection, List<PreparedStatement> statementlist, String prodcd,
			String prodnm, String specdes, String flagset, String flagplus, String flagout, String price,
			String tagprice, String sabcd, String remark, String ea, String useyn, String delyn, String cusprice,
			String aproinvt) throws Exception {

		PreparedStatement pstmt_prodmst = null;
		ResultSet rs = null;

		// OPTCHK 는 상품마스터 등록시에만 넣어줌
		String sql_prodmst = "insert into prodmst (compno, prodcd, prodnm, specdes, flagset, flagplus, flagout, "
				+ " price, tagprice, sabcd, remark, ea,  useyn, delyn, insertdt, insertid, cusprice, aproinvt)  "
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

		sql_prodmst = sql_prodmst.toUpperCase();

		pstmt_prodmst = connection.prepareStatement(sql_prodmst);
		statementlist.add(pstmt_prodmst);

		int i = 0;
		pstmt_prodmst.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
		pstmt_prodmst.setString(++i, prodcd);
		pstmt_prodmst.setString(++i, prodnm);
		pstmt_prodmst.setString(++i, specdes);
		// --- prodmst의 이카운트코드는 상품코드 이카운트 전송되고 업데이트함
		pstmt_prodmst.setString(++i, flagset);
		pstmt_prodmst.setString(++i, flagplus);
		pstmt_prodmst.setString(++i, flagout);

		pstmt_prodmst.setString(++i, price);
		pstmt_prodmst.setString(++i, tagprice);
		pstmt_prodmst.setString(++i, sabcd);
		pstmt_prodmst.setString(++i, remark);
		pstmt_prodmst.setString(++i, ea);

		pstmt_prodmst.setString(++i, useyn);
		pstmt_prodmst.setString(++i, delyn);
		pstmt_prodmst.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt_prodmst.setString(++i, YDMASessonUtil.getUserInfo().getUserId());
		pstmt_prodmst.setString(++i, cusprice);
		pstmt_prodmst.setString(++i, aproinvt);

		System.out.println(pstmt_prodmst.toString());

		pstmt_prodmst.executeUpdate();

	}

	public void processProductUpdate(String prodcd, String prodnm, String specdes, String flagset, String flagplus,
			String flagout, String price, String tagprice, String sabcd, String remark, String ea, String useyn,
			String delyn, String cusprice, String aproinvt, String repstt, String addtn1, String addtn2,
			String infornum, String propertynum, String searchwd, String categlagcd, String categmidcd,
			String categsmlcd, String categdetcd, String orderprice, String ecountcd) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

//			updateProduct(connection, statementlist, prodcd,  prodnm,  specdes,  flagset,  flagplus,
//					 flagout,  price,  tagprice,  sabcd,  remark,  ea,  useyn,  delyn, cusprice, aproinvt);

			String sql = "update prodmst set prodnm=?, specdes=?, flagset=?, flagplus=?, flagout=?, "
					+ " price=?, tagprice=?, sabcd=?, remark=?, ea=?, "
					+ " useyn=?, delyn=?, modifydt=?, modifyid=?, cusprice=?, aproinvt=?, repstt=?, addtn1=?, addtn2=?, infornum=?, propertynum=?, "
					+ " searchwd=?, categlagcd=?, categmidcd=?, categsmlcd=?, categdetcd=? , orderprice = ?, ecountcd = ? where prodcd=? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, prodnm);
			pstmt.setString(++i, specdes);
			pstmt.setString(++i, flagset);
			pstmt.setString(++i, flagplus);
			pstmt.setString(++i, flagout);
			pstmt.setString(++i, price);
			pstmt.setString(++i, tagprice);
			pstmt.setString(++i, sabcd);
			pstmt.setString(++i, remark);
			pstmt.setString(++i, ea);
			pstmt.setString(++i, useyn);
			pstmt.setString(++i, delyn);
			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUserId());
			pstmt.setString(++i, cusprice);
			pstmt.setString(++i, aproinvt);
			pstmt.setString(++i, repstt);
			pstmt.setString(++i, addtn1);
			pstmt.setString(++i, addtn2);
			pstmt.setString(++i, infornum);
			pstmt.setString(++i, propertynum);
			pstmt.setString(++i, searchwd);
			pstmt.setString(++i, categlagcd);
			pstmt.setString(++i, categmidcd);
			pstmt.setString(++i, categsmlcd);
			pstmt.setString(++i, categdetcd);
			pstmt.setString(++i, orderprice);
			pstmt.setString(++i, ecountcd);

			pstmt.setString(++i, prodcd); // 조건 key
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

	public void updateProduct(Connection connection, List<PreparedStatement> statementlist, String prodcd,
			String prodnm, String specdes, String flagset, String flagplus, String flagout, String price,
			String tagprice, String sabcd, String remark, String ea, String useyn, String delyn, String cusprice,
			String aproinvt) throws Exception {

		PreparedStatement pstmt = null;

		connection = DBCPInit.getInstance().getConnection();
		String sql = "update prodmst set prodnm=?, specdes=?, flagset=?, flagplus=?, flagout=?, "
				+ " price=?, tagprice=?, sabcd=?, remark=?, ea=?, "
				+ " useyn=?, delyn=?, modifydt=?, modifyid=?,  cusprice=?, aproinvt=? where prodcd=? and compno = ? ";
		sql = sql.toUpperCase();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		int i = 0;
		pstmt.setString(++i, prodnm);
		pstmt.setString(++i, specdes);
		pstmt.setString(++i, flagset);
		pstmt.setString(++i, flagplus);
		pstmt.setString(++i, flagout);
		pstmt.setString(++i, price);
		pstmt.setString(++i, tagprice);
		pstmt.setString(++i, sabcd);
		pstmt.setString(++i, remark);
		pstmt.setString(++i, ea);
		pstmt.setString(++i, useyn);
		pstmt.setString(++i, delyn);
		pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
		pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUserId());
		pstmt.setString(++i, cusprice);
		pstmt.setString(++i, aproinvt);
		pstmt.setString(++i, prodcd); // 조건 key
		pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

		System.out.println("[updateProduct]" + pstmt.toString());
		pstmt.executeUpdate();

	}

	// 새로열어서 이미지 보기 클릭했을때
	public List<String> getProdcd(String prodcd) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			connection = DBCPInit.getInstance().getConnection();
			String sql = " SELECT prodcd,mainpath,subpath1,subpath2 from prodmst where prodcd =? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getProdcd]" + pstmt.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return list;

	}

	// mst디테일화면에 뿌려줄려고
	public List<String> getNumberInforMationProperty(String prodcd) throws Exception {
		List<String> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select infornum, propertynum from prodmst where prodcd = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getNumber]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
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

	public List<List<String>> getProdMstList(String prodcd) throws Exception {
		List<List<String>> contents = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select prodcd, prodnm, specdes, price, tagprice, remark, ea, ifnull(optchk,''),ifnull(repstt,''), ifnull(addtn1,''), "
					+ " ifnull(addtn2,''), ifnull(searchwd,''), ifnull(categlagcd,''), ifnull(categmidcd,''), ifnull(categsmlcd,''), ifnull(categdetcd,''), ifnull(useyn,'')"
					+ " from prodmst where prodcd = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getProdMst]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
				list.add(String.valueOf(i++));
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
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

	public List<String> getCategoryList(String prodcd) throws Exception {
		// List<List<String>> contents = new ArrayList<List<String>>();
		List<String> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(categlagcd,''), ifnull(categmidcd,''), ifnull(categsmlcd,''), ifnull(categdetcd,'') from prodmst where prodcd = ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getCategoryList]" + pstmt.toString());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// List<String> list = new ArrayList<String>();
				int columnIndex = 0;
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				list.add(rs.getString(++columnIndex));//
				// contents.add(list);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	public List<String> getProductMstList(String prodprodcd) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select prodcd, ifnull(prodnm,''), ifnull(specdes,''), ifnull(ecountcd,''),  "
					+ " ifnull(flagset,''), ifnull(flagplus,''), ifnull(flagout,''), ifnull(price,'0'), "
					+ " ifnull(TAGPRICE,'0'), ifnull(sabcd,''), ifnull(remark,''), ifnull(ea,'0'), "
					+ " ifnull(useyn,''), ifnull(delyn,''), ifnull(optchk,''),"
					+ " ifnull(insertdt,''), ifnull(insertid,''), ifnull(modifydt,''), ifnull(modifyid,''), "
					+ " ifnull(cusprice,''),ifnull(aproinvt,''), ifnull(repstt,''), ifnull(addtn1,''), ifnull(addtn2,''), ifnull(infornum,''),ifnull(propertynum,''),"
					+ " ifnull(searchwd,''),ifnull(categlagcd,'0') ,"
					+ " ifnull(categmidcd,'0'), ifnull(categsmlcd,'0'), ifnull(categdetcd,'0'), ifnull(orderprice,'0'), ifnull(shopcateg,'0') ,ifnull(shopcode,'') "
					+ " from prodmst " + " where prodcd=? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodprodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getProductMstList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;
			if (rs.next()) {
				int i = 0;
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//
				list.add(rs.getString(++i));//

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	// 품목별리스트
	public List<List<String>> getCategoryLargeList(List<List<String>> contents) throws Exception {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		List<List<String>> con = new ArrayList<>();
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();
			List<String> params = contents.stream().map(p -> "'" + p.get(29) + "'").collect(Collectors.toList());

			StringBuffer with_cte = new StringBuffer();

			with_cte.append("WITH PRODCDTMP AS ( \r\n");

			for (int i = 0; i < params.size(); ++i) {

				if (i == params.size() - 1) {
					with_cte.append(String.format("SELECT %s AS PRODCD \r\n", params.get(i)));
					with_cte.append(")");
				} else {
					with_cte.append(String.format("SELECT %s AS PRODCD  UNION ALL \r\n", params.get(i)));
				}
			}

			// for(int k =1;k<=list.size()-1;k++) {
			String sql = " SELECT ifnull(a.prodcd,''), ifnull(a.categlagcd,''), ifnull(C.CATEGORY,'') "
					+ " FROM prodmst a " + " INNER JOIN PRODCDTMP b " + "  ON a.prodcd = b.PRODCD "

					+ " JOIN categlarge c ON a.compno=c.compno AND a.categlagcd = c.CODE " + " WHERE a.compno = ?";

			with_cte.append(sql);

			sql = with_cte.toString().toUpperCase();
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getProductMstList]" + pstmt.toString());
			rs = pstmt.executeQuery();

			con.addAll(contents);
			int rowno = 0;
			while (rs.next()) {
				String prodcd = rs.getString(1);
				String cateCode = rs.getString(2);
				String cateName = rs.getString(3);
				rowno++;

				// System.out.println("prodcd :"+prodcd +" : "+cateCode +" : "+cateName+" :
				// "+rowno);

				for (List<String> lst : con) {
					if (lst.get(29).equals(prodcd)) {
						lst.add(0, cateName);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return con;
	}

	// 품목별리스트
	public List<List<String>> getCategoryMidiumList(List<List<String>> contents) throws Exception {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		List<List<String>> con = new ArrayList<>();
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			String param = contents.stream().map(p -> "'" + p.get(1) + "'").collect(Collectors.joining(","));

			String sql = String.format(""
					+ "SELECT ifnull(a.prodcd,''), ifnull(a.CATEGMIDCD,''), ifnull(b.CATEGORY,'') "
					+ "  FROM prodmst a " + "	 JOIN CATEGMIDIUM b ON a.compno=b.compno AND a.CATEGMIDCD = b.CODE "
					+ " WHERE a.prodcd in(%s)", param) + "   AND a.compno = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getCategoryMidiumList]" + pstmt.toString());
			rs = pstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				String prodcd = rs.getString(1);
				String cateCode = rs.getString(2);
				String cateName = rs.getString(3);

				List<String> lst = contents.stream().filter(p -> p.get(1).equals(prodcd)).peek(p -> p.add(0, cateName))
						.collect(Collectors.toList()).get(0);
				con.add(lst);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return con;

	}

	// 옵션코드 가지고 오기
	public List<String> getoptProdcd() throws Exception {

		// opt == {상품코드, 상품명}
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// YWM_FUNC_BOSSPRODCD(",V_COMPNO, ",a.prodcd) as img
			String sql = "select  ifnull(optprodcd,'') from optprodmst where compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getoptProdcd]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;
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

	// 프로드인포다가지고 오기
	public List<List<String>> getProdInfor() throws Exception {
		// opt == {상품코드, 상품명}
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// YWM_FUNC_BOSSPRODCD(",V_COMPNO, ",a.prodcd) as img
			String sql = "select  seq,ifnull(PRODSTAT,''),ifnull(SUPPLIER,'') from prodinfor where compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getProdInfor]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
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

	public List<List<String>> getAttrName() throws Exception {
		// opt == {상품코드, 상품명}
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// YWM_FUNC_BOSSPRODCD(",V_COMPNO, ",a.prodcd) as img
			String sql = "select  ATTRCD,ifnull(ATTRNM,'') from prodattr ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			System.out.println("[getAttrName]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
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

	public List<List<String>> getERPCodeList(String orddt, String ordseq, String seq) throws Exception {
		// opt == {상품코드, 상품명, ERP(ecountcd)}
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT distinct a.ECOUNTCD \r\n" + "  FROM ( SELECT B.ECOUNTCD \r\n"
					+ "			  FROM ORDDTL AS A INNER JOIN PRODMST AS B ON A.COMPNO=B.COMPNO AND A.PRODCD=B.PRODCD \r\n"
					+ "			 WHERE A.COMPNO = ? \r\n" + "			   AND A.ORDDT  = ? \r\n"
					+ "				AND A.ORDSEQ = ? \r\n" + "				AND A.SEQ    = ? \r\n"
					+ "				AND A.PRODCD = B.PRODCD \r\n" + "			UNION ALL \r\n"
					+ "			SELECT B.ECOUNTCD\r\n"
					+ "			  FROM ORDDTL AS A INNER JOIN PRODMST AS B ON A.COMPNO=B.COMPNO AND A.PRODCD=B.PRODCD \r\n"
					+ "			 WHERE A.COMPNO = ? \r\n" + "			   AND A.ORDDT  = ? \r\n"
					+ "				AND A.ORDSEQ = ? \r\n" + "				AND A.MOVESEQ= ? \r\n"
					+ "				AND A.PRODCD = B.PRODCD ) a WHERE a.ECOUNTCD IS NOT null";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, orddt);
			pstmt.setString(3, ordseq);
			pstmt.setString(4, seq);
			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(6, orddt);
			pstmt.setString(7, ordseq);
			pstmt.setString(8, seq);

			System.out.println("[getERPCodeList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
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

}

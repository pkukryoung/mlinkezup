package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class MlinkShopDao {

	public int[] sendProductListToMlinkShop(List<List<String>> contents, boolean isNew, Shell shell) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int[] result = new int[] { 0, 0 };

		List<List<String>> contents_in = new ArrayList<>();
		List<List<String>> contents_up = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT 1  FROM shopprodinfo where compno = ? and compayny_goods_cd=? ";
			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);

			for (List<String> list : contents) {
				int count = 0;
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.get(1));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}

				if (count == 0) { // Not Exists
					contents_in.add(list);
				} else { // Exists
					contents_up.add(list);
				}
			}

			// Insert
			if (contents_in.size() > 0) {
				result[0] = sendProductListToInMlinkShop(contents_in, isNew, shell);
				if (result[0] > 0) {
					sendOptProdListToInMlinkShop(contents_in, isNew, shell);
				}
			}

			// Update
			if (contents_up.size() > 0) {
				result[1] = sendProductListToUpMlinkShop(contents_up, isNew, shell);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public int sendProductListToUpMlinkShop(List<List<String>> contents, boolean isNew, Shell shell) {
		return 0;
	}

	public void sendOptProdListToInMlinkShop(List<List<String>> contents, boolean isNew, Shell shell) throws Exception {

		int result = 0;
//		Connection connection = null;
		Connection connection2 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist2 = new ArrayList<>();

		try {

			connection2 = DBCPInit.getInstance().getConnection();
			connection2.setAutoCommit(false);

			String sql2 = " insert into shop_optprodinfo "
					+ "(PRODSEQ,SKUSEQ,COMPNO,PRODCD,OPTPRODCD,OPTPRODNM,OPTSPECDES,OPTEA,OPTSALE,"
					+ " OPTSALEOUT,OPTNOTUSE,OPTSAFESTOCK,OPTVERTSTOCK,OPTADDAMT,OPTDELYN,BARCODE,"
					+ " INSERTDT,INSERTID,MODIFYDT,MODIFYID )" + " VALUES " + "(?,?,?,?,?,?,?,?,?, "
					+ " ?,?,?,?,?,?,?, " + " ?,?,?,? " + " )";

			sql2 = sql2.toUpperCase();
			pstmt2 = connection2.prepareStatement(sql2);
			statementlist2.add(pstmt2);

			String sql = "SELECT prodseq  FROM shopprodinfo where compno = ? and compayny_goods_cd=? ";
			sql = sql.toUpperCase();
			pstmt = connection2.prepareStatement(sql);

			for (List<String> list : contents) {

				ProductOptDao dao = new ProductOptDao();
				List<List<String>> contents_opt = dao.getProdcutOptList(list.get(1));

				if (contents_opt.size() > 0) {
					int prodseq = 0;
					pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
					pstmt.setString(2, list.get(1));
					rs = pstmt.executeQuery();

					if (rs.next()) {
						prodseq = rs.getInt(1);
					}

					if (prodseq > 0) {
						for (int i = 0; i < contents_opt.size(); i++) {
							List<String> list_opt = contents_opt.get(i);

							int rowIdx2 = 0;
							pstmt2.setInt(++rowIdx2, prodseq);
							pstmt2.setInt(++rowIdx2, i);
							pstmt2.setString(++rowIdx2, YDMASessonUtil.getCompnoInfo().getCompno());

							pstmt2.setString(++rowIdx2, list.get(1));
							pstmt2.setString(++rowIdx2, list_opt.get(1));
							pstmt2.setString(++rowIdx2, list_opt.get(2));
							pstmt2.setString(++rowIdx2, list_opt.get(3));
							pstmt2.setString(++rowIdx2, list_opt.get(4));
							pstmt2.setString(++rowIdx2, list_opt.get(5));
							pstmt2.setString(++rowIdx2, list_opt.get(6));
							pstmt2.setString(++rowIdx2, list_opt.get(7));
							pstmt2.setString(++rowIdx2, list_opt.get(8));
							pstmt2.setString(++rowIdx2, list_opt.get(9));

							pstmt2.setString(++rowIdx2, list_opt.get(10));
							pstmt2.setString(++rowIdx2, list_opt.get(11));
							pstmt2.setString(++rowIdx2, "");
							pstmt2.setString(++rowIdx2, "");
							pstmt2.setString(++rowIdx2, "");
							pstmt2.setString(++rowIdx2, "");
							pstmt2.setString(++rowIdx2, "");

							pstmt2.addBatch();
							pstmt2.clearParameters(); // 파라미터 초기화

						}
					}
				}
			}

			pstmt2.executeBatch();
			pstmt2.clearParameters(); // Batch 초기화
			connection2.commit();

		} catch (Exception ex) {
			connection2.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection2, statementlist2, rs);
		}

	}

	public int sendProductListToInMlinkShop(List<List<String>> contents, boolean isNew, Shell shell) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = " insert into shopprodinfo "
					+ "(COMPNO,GOODS_NM,MODEL_NM,BRAND_NM,COMPAYNY_GOODS_CD,GOODS_SEARCH,"
					+ " GOODS_GUBUN,MAKER,ORIGIN,CLASS_CD1,CLASS_CD2,CLASS_CD3,CLASS_CD4,"
					+ " GOODS_SEASON,SEX,STATUS,DELIV_ABLE_REGION,TAX_YN,DELV_TYPE,DELV_COST,"
					+ " GOODS_PRICE,GOODS_CONSUMER_PRICE,CHAR_1_NM,CHAR_2_NM,char_1_val,CHAR_2_VAL,IMG_PATH,IMG_PATH6,"
					+ " IMG_PATH7,GOODS_REMARKS,STOCK_USE_YN,OPT_TYPE,PROP_EDIT_YN,PROP1_CD,PROP_VAL1,"
					+ " PROP_VAL2,PROP_VAL3,PROP_VAL4,PROP_VAL5,PROP_VAL6,PROP_VAL7,PROP_VAL8,"
					+ " PROP_VAL9,PROP_VAL10,PROP_VAL11,PROP_VAL12,PROP_VAL13,PROP_VAL14,PROP_VAL15,"
					+ " PROP_VAL16,PROP_VAL17,PROP_VAL18,PROP_VAL19,PROP_VAL20,PROP_VAL21,PROP_VAL22,"
					+ " PROP_VAL23,PROP_VAL24,PROP_VAL25,PROP_VAL26,PROP_VAL27,PROP_VAL28," + " INSERTDT,INSERTID )"
					+ " VALUES (?,?,?,?,?,?," + " ?,?,?,?,?,?,?," + " ?,?,?,?,?,?,?," + " ?,?,?,?,?,?,?,?,"
					+ " ?,?,?,?,?,?,?," + " ?,?,?,?,?,?,?," + " ?,?,?,?,?,?,?," + " ?,?,?,?,?,?,?," + " ?,?,?,?,?,?,"
					+ " ?,?)";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			for (List<String> list : contents) {
				int rowIdx = 0;
				// Node COMPNO = document.createElement("COMPNO");
				pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
				// 추가
				ProductMstDao mdao = new ProductMstDao();
				// 상품명으로 mstdao에서 번호가지고오기
				List<String> mstlist = mdao.getNumberInforMationProperty(list.get(1));
				// 번호로 조회하기
				ProductIforDao idao = new ProductIforDao();

				if (mstlist.get(1) != null && mstlist.get(0) != null) {
					List<String> infodao = new ArrayList<>();
					infodao = idao.getOpen(mstlist.get(0));
					// Node GOODS_NM = document.createElement("GOODS_NM");
					if (list.get(2).getBytes().length > 100) {
						byte[] strByte = list.get(2).getBytes();
						String newTitle = new String(strByte, 0, 100);
						pstmt.setString(++rowIdx, newTitle);
					} else {
						pstmt.setString(++rowIdx, list.get(2));
					}

					// Node MODEL_NM = document.createElement("MODEL_NM");
					pstmt.setString(++rowIdx, list.get(1));

					// Node BRAND_NM = document.createElement("BRAND_NM");
					pstmt.setString(++rowIdx, infodao.get(2));

					// Node COMPAYNY_GOODS_CD = document.createElement("COMPAYNY_GOODS_CD");
					pstmt.setString(++rowIdx, list.get(1));

					// Node GOODS_SEARCH = document.createElement("GOODS_SEARCH");
					pstmt.setString(++rowIdx, list.get(12));

					// Node GOODS_GUBUN = document.createElement("GOODS_GUBUN");
					pstmt.setString(++rowIdx, infodao.get(3));

					// Node MAKER = document.createElement("MAKER");
					pstmt.setString(++rowIdx, infodao.get(4));

					// Node ORIGIN = document.createElement("ORIGIN");
					pstmt.setString(++rowIdx, infodao.get(19));

					if (isNew) {// mst에서등록
						// Node CLASS_CD1 = document.createElement("CLASS_CD1");
						if (list.get(13).length() < 3) {
							pstmt.setString(++rowIdx, null);
						} else {
							pstmt.setString(++rowIdx, list.get(13));
						}

						/// Node CLASS_CD2 = document.createElement("CLASS_CD2");
						if (list.get(14).length() < 3) {
							pstmt.setString(++rowIdx, null);
						} else {
							pstmt.setString(++rowIdx,
									list.get(14).substring(list.get(14).length() - 3, list.get(14).length()));
						}

						// Node CLASS_CD3 = document.createElement("CLASS_CD3");
						if (list.get(15).length() < 3) {
							pstmt.setString(++rowIdx, null);
						} else {
							pstmt.setString(++rowIdx,
									list.get(15).substring(list.get(15).length() - 3, list.get(15).length()));
						}

						// Node CLASS_CD4 = document.createElement("CLASS_CD4");
						if (list.get(16).length() < 3) {
							pstmt.setString(++rowIdx, null);
						} else {
							pstmt.setString(++rowIdx,
									list.get(16).substring(list.get(16).length() - 3, list.get(16).length()));
						}
					} else {// 사방넷등록에서
						List<String> categ = mdao.getCategoryList(list.get(1));
						// Node CLASS_CD1 = document.createElement("CLASS_CD1");
						if (categ.get(0).length() < 3) {
							pstmt.setString(++rowIdx, null);
						} else {
							pstmt.setString(++rowIdx, categ.get(0));
						}

						// Node CLASS_CD2 = document.createElement("CLASS_CD2");
						if (categ.get(1).length() < 3) {
							pstmt.setString(++rowIdx, null);
						} else {
							pstmt.setString(++rowIdx,
									categ.get(1).substring(categ.get(1).length() - 3, categ.get(1).length()));
						}

						// Node CLASS_CD3 = document.createElement("CLASS_CD3");
						if (categ.get(2).length() < 3) {
							pstmt.setString(++rowIdx, null);
						} else {
							pstmt.setString(++rowIdx,
									categ.get(2).substring(categ.get(2).length() - 3, categ.get(2).length()));
						}

						// Node CLASS_CD4 = document.createElement("CLASS_CD4");
						if (categ.get(3).length() < 3) {
							pstmt.setString(++rowIdx, null);
						} else {
							pstmt.setString(++rowIdx,
									categ.get(3).substring(categ.get(3).length() - 3, categ.get(3).length()));
						}
					}

					// Node GOODS_SEASON = document.createElement("GOODS_SEASON");
					pstmt.setString(++rowIdx, infodao.get(6));

					// Node SEX = document.createElement("SEX");
					pstmt.setString(++rowIdx, infodao.get(7));

					// Node STATUS = document.createElement("STATUS");
					pstmt.setString(++rowIdx, infodao.get(8));

					// Node DELIV_ABLE_REGION = document.createElement("DELIV_ABLE_REGION");
					pstmt.setString(++rowIdx, infodao.get(9));

					// Node TAX_YN = document.createElement("TAX_YN");
					pstmt.setString(++rowIdx, infodao.get(12));

					// Node DELV_TYPE = document.createElement("DELV_TYPE");
					pstmt.setString(++rowIdx, infodao.get(10));

					// Node DELV_COST = document.createElement("DELV_COST");
					pstmt.setInt(++rowIdx, Integer.parseInt(infodao.get(11)));

					// Node GOODS_PRICE = document.createElement("GOODS_PRICE");
					pstmt.setInt(++rowIdx, Integer.parseInt(list.get(4)));

					// Node GOODS_CONSUMER_PRICE = document.createElement("GOODS_CONSUMER_PRICE");
					pstmt.setInt(++rowIdx, Integer.parseInt(list.get(5)));

					// Node CHAR_1_NM = document.createElement("CHAR_1_NM");
					pstmt.setString(++rowIdx, infodao.get(14));

					// Node CHAR_2_NM = document.createElement("CHAR_2_NM");
					pstmt.setString(++rowIdx, infodao.get(15));

					// Node CHAR_1_VAL = document.createElement("CHAR_1_VAL");
					// 옵션상세 명칭 2(사이즈) 결정을 위한 변수
					String char_2_val = "";
					String[] aaa = { "", "", "", "", "", "", "", "" };
					int num = 0;
					// 옵션체크가 1 이면 별도로
					if (!"1".equals(infodao.get(13))) {

						ProductOptDao dao = new ProductOptDao();
						List<List<String>> contents_opt = dao.getProdcutOptList(list.get(1));

						String char_1_val = "";
						String optsale = "002";

						for (int i = 0; i < contents_opt.size(); i++) {
							List<String> list_opt = contents_opt.get(i);

							if ("1".equals(list_opt.get(5))) { // optsale
								optsale = "002";
							} else if ("1".equals(list_opt.get(6))) {// optsaleout
								optsale = "004";
							} else if ("1".equals(list_opt.get(7))) {// optnotuse
								optsale = "005";
							}
							String optprodcd = list_opt.get(1);
							String optprodnm = list_opt.get(2);
							String optspecdes = list_opt.get(3);
							String optea = list_opt.get(4);
							String optsafestock = list_opt.get(8);
							String optvertstock = list_opt.get(9);
							String optaddamt = list_opt.get(10);

							// 옵션상세 명칭 2(사이즈) 결정
							String optspecdes1 = optspecdes;
							String optspecdes2 = "";

							boolean flagfind = false;

							int idx = 0;
							idx = optspecdes.indexOf(":");
							if (idx > 0) {
								optspecdes1 = optspecdes.substring(0, idx);
								optspecdes2 = optspecdes.substring(idx + 1);
								char_1_val += optprodnm + "_" + optspecdes1 + "^^" + optvertstock + "^^" + optaddamt
										+ "^^^^" + optea + "^^" + optsale + "^^L^^^^" + optsafestock;
								for (String element : aaa) {
									if (element.equals(optspecdes2)) {
										flagfind = true;
										break;
									}
								}
								if (!flagfind) { // 2차원 규격이 없는경우만 추가
									aaa[num] = optspecdes2;
									char_2_val += optspecdes2 + "(" + optprodcd + ")";
									if (i < contents_opt.size() - 1)
										char_2_val += ","; // 마지막이면 컴마 불필요
									num++;
								}

							} else {
								char_1_val += optprodnm + "_" + optspecdes1 + "(" + optprodcd + ")^^" + optvertstock
										+ "^^" + optaddamt + "^^^^" + optea + "^^" + optsale + "^^L^^^^" + optsafestock;
							}

							if (i < contents_opt.size() - 1) {
								char_1_val += ",";
							}

						}
						pstmt.setString(++rowIdx, char_1_val);

					} else {

						String prodnm = list.get(2);
						String specdes = list.get(3);
						String prodcd = list.get(1);
						String ea = list.get(7);

						String char_1_val = prodnm + "_" + specdes + "(" + prodcd + ")^^999^^^^^^" + ea
								+ "^^002^^L^^^^";
						pstmt.setString(++rowIdx, char_1_val);

					}

					// Node CHAR_2_VAL = document.createElement("CHAR_2_VAL");
					pstmt.setString(++rowIdx, char_2_val);

					// Node IMG_PATH = document.createElement("IMG_PATH");
					pstmt.setString(++rowIdx, list.get(9));

					// Node IMG_PATH6 = document.createElement("IMG_PATH6");
					pstmt.setString(++rowIdx, list.get(10));

					// Node IMG_PATH7 = document.createElement("IMG_PATH7");
					pstmt.setString(++rowIdx, list.get(11));

					// Node GOODS_REMARKS = document.createElement("GOODS_REMARKS");
//					String remark = list.get(6);
//					pstmt.setString(++rowIdx,remark);
					pstmt.setString(++rowIdx, list.get(6));

					// Node STOCK_USE_YN = document.createElement("STOCK_USE_YN");
					if (infodao.get(16).equals("1")) {
						pstmt.setString(++rowIdx, "N");
					} else {// 추가
						pstmt.setString(++rowIdx, "Y");
					}

					// Node OPT_TYPE = document.createElement("OPT_TYPE");
					if (infodao.get(17).equals("1")) {
						pstmt.setString(++rowIdx, "2");
					} else {
						pstmt.setString(++rowIdx, "9");
					}
					// 속성수정여부
					// Node PROP_EDIT_YN = document.createElement("PROP_EDIT_YN");
					pstmt.setString(++rowIdx, "Y");
					// 여기서부터 추가
					// Node PROP1_CD = document.createElement("PROP1_CD");
					pstmt.setString(++rowIdx, mstlist.get(1));

					int attIdx = 0;
					List<List<String>> attrvals = idao.getSbapiSelect(mstlist.get(1));
					for (List<String> attrval : attrvals) {
						pstmt.setString(++rowIdx, attrval.get(2));
						attIdx++;
					}
					if (attIdx < 28) {
						for (int i = 0; attIdx < 28; i++) {
							pstmt.setString(++rowIdx, "");
							attIdx++;
						}
					}

				} else {
					MessageDialog.openInformation(shell, "엠링크전송", "정보를 모두 입력후 진행하여 주시기 바랍니다");
				}

				pstmt.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId());

				result++;
				System.out.println("[insert shopprodinfo]" + pstmt.toString());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			pstmt.executeBatch();
			pstmt.clearParameters(); // Batch 초기화

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;

	}

}

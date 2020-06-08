package com.kdj.mlink.ezup3.shop.domesin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdj.mlink.ezup3.shop.common.ShopCommon;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.CategoryDao;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;

public class DomesinShopDao {

	private static class ShopGosiInfo {
		private String gosi1; // 상품고시 항목1
		private String gosi2; // 상품고시 항목2
		private String gosi3; // 상품고시 항목3
		private String gosi4; // 상품고시 항목4
		private String gosi5; // 상품고시 항목5
		private String gosi6; // 상품고시 항목6
		private String gosi7; // 상품고시 항목7
		private String gosi8; // 상품고시 항목8
		private String gosi9; // 상품고시 항목9
		private String gosi10; // 상품고시 항목10
		private String gosi11; // 상품고시 항목11
		private String gosi12; // 상품고시 항목12
		private String gosi13; // 상품고시 항목13
		private String gosi14; // 상품고시 항목14
		private String gosi15; // 상품고시 항목15
		private String gosi16; // 상품고시 항목16
		private String gosi17; // 상품고시 항목17
		private String gosi18; // 상품고시 항목18
		private String gosi19; // 상품고시 항목19
		private String gosi20; // 상품고시 항목20
		private String gosi21; // 상품고시 항목21
		private String gosi22; // 상품고시 항목22

		public String getGosi1() {
			return gosi1;
		}

		public void setGosi1(String gosi1) {
			this.gosi1 = gosi1;
		}

		public String getGosi2() {
			return gosi2;
		}

		public void setGosi2(String gosi2) {
			this.gosi2 = gosi2;
		}

		public String getGosi3() {
			return gosi3;
		}

		public void setGosi3(String gosi3) {
			this.gosi3 = gosi3;
		}

		public String getGosi4() {
			return gosi4;
		}

		public void setGosi4(String gosi4) {
			this.gosi4 = gosi4;
		}

		public String getGosi5() {
			return gosi5;
		}

		public void setGosi5(String gosi5) {
			this.gosi5 = gosi5;
		}

		public String getGosi6() {
			return gosi6;
		}

		public void setGosi6(String gosi6) {
			this.gosi6 = gosi6;
		}

		public String getGosi7() {
			return gosi7;
		}

		public void setGosi7(String gosi7) {
			this.gosi7 = gosi7;
		}

		public String getGosi8() {
			return gosi8;
		}

		public void setGosi8(String gosi8) {
			this.gosi8 = gosi8;
		}

		public String getGosi9() {
			return gosi9;
		}

		public void setGosi9(String gosi9) {
			this.gosi9 = gosi9;
		}

		public String getGosi10() {
			return gosi10;
		}

		public void setGosi10(String gosi10) {
			this.gosi10 = gosi10;
		}

		public String getGosi11() {
			return gosi11;
		}

		public void setGosi11(String gosi11) {
			this.gosi11 = gosi11;
		}

		public String getGosi12() {
			return gosi12;
		}

		public void setGosi12(String gosi12) {
			this.gosi12 = gosi12;
		}

		public String getGosi13() {
			return gosi13;
		}

		public void setGosi13(String gosi13) {
			this.gosi13 = gosi13;
		}

		public String getGosi14() {
			return gosi14;
		}

		public void setGosi14(String gosi14) {
			this.gosi14 = gosi14;
		}

		public String getGosi15() {
			return gosi15;
		}

		public void setGosi15(String gosi15) {
			this.gosi15 = gosi15;
		}

		public String getGosi16() {
			return gosi16;
		}

		public void setGosi16(String gosi16) {
			this.gosi16 = gosi16;
		}

		public String getGosi17() {
			return gosi17;
		}

		public void setGosi17(String gosi17) {
			this.gosi17 = gosi17;
		}

		public String getGosi18() {
			return gosi18;
		}

		public void setGosi18(String gosi18) {
			this.gosi18 = gosi18;
		}

		public String getGosi19() {
			return gosi19;
		}

		public void setGosi19(String gosi19) {
			this.gosi19 = gosi19;
		}

		public String getGosi20() {
			return gosi20;
		}

		public void setGosi20(String gosi20) {
			this.gosi20 = gosi20;
		}

		public String getGosi21() {
			return gosi21;
		}

		public void setGosi21(String gosi21) {
			this.gosi21 = gosi21;
		}

		public String getGosi22() {
			return gosi22;
		}

		public void setGosi22(String gosi22) {
			this.gosi22 = gosi22;
		}

	}

	// shop 카테고리 아이템..
	private static class ShopCateItem {
		private String shopcd;
		private String esm_gmarket;
		private String esm_auction;
		private String cid;

		public String getShopcd() {
			return shopcd;
		}

		public void setShopcd(String shopcd) {
			this.shopcd = shopcd;
		}

		public String getEsm_gmarket() {
			return esm_gmarket;
		}

		public void setEsm_gmarket(String esm_gmarket) {
			this.esm_gmarket = esm_gmarket;
		}

		public String getEsm_auction() {
			return esm_auction;
		}

		public void setEsm_auction(String esm_auction) {
			this.esm_auction = esm_auction;
		}

		public String getCid() {
			return cid;
		}

		public void setCid(String cid) {
			this.cid = cid;
		}

	}

	private static class ShopCateInfo {
		private String cid;
		private String es_auction;
		private String es_gmarket;
		private String es_11st;
		private String es_interpark;
		private String es_storefarm;
		private String esm_auction;
		private String esm_gmarket;
		private String es_cafe24;
		private String es_coupang;

		public ShopCateInfo(String cid, String es_auction, String es_gmarket, String es_11st, String es_interpark,
				String es_storefarm, String esm_auction, String esm_gmarket, String es_cafe24, String es_coupang) {

			this.cid = cid;
			this.es_auction = es_auction;
			this.es_gmarket = es_gmarket;
			this.es_11st = es_11st;
			this.es_interpark = es_interpark;
			this.es_storefarm = es_storefarm;
			this.esm_auction = esm_auction;
			this.esm_gmarket = esm_gmarket;
			this.es_cafe24 = es_cafe24;
			this.es_coupang = es_coupang;
		}

	}

	public static DomesinShopDao instance = new DomesinShopDao();

	private DomesinShopDao() {
	}

	public static DomesinShopDao get() {
		return instance;
	}

	/*
	 * 카테고리 저장..
	 */
	public void saveCategory(List<ProductItem> list) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "INSERT INTO shopcatinf \r\n"
					+ "	(COMPNO, SHOPCD, SHOPCATNO, SHOPCATNM, SHOPCATSITENM, EBAYCATCD, SHOPSMLCATCD, SERVICEPROD, USE_YN, SHOPGENERAL, SHOPID, INSERTDT) \r\n"
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON  DUPLICATE KEY UPDATE  EBAYCATCD=?,  SHOPSMLCATCD=?  ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			List<ShopCateInfo> shopcateinfos = null;
			if (list.get(0).isModifyMode()) { // 수정 모드 이면 카테고리 수정만.
				shopcateinfos = list.stream().filter(p -> p.isCategoryModify())
						.map(p -> new ShopCateInfo(p.getCid(), p.getEs_auction(), p.getEs_gmarket(), p.getEs_11st(),
								p.getEs_interpark(), p.getEs_storefarm(), p.getEsm_auction(), p.getEsm_gmarket(), "",
								p.getEs_coupang()))
						.distinct().collect(Collectors.toList());

			} else {
				shopcateinfos = list.stream()
						.map(p -> new ShopCateInfo(p.getCid(), p.getEs_auction(), p.getEs_gmarket(), p.getEs_11st(),
								p.getEs_interpark(), p.getEs_storefarm(), p.getEsm_auction(), p.getEsm_gmarket(), "",
								p.getEs_coupang()))
						.distinct().collect(Collectors.toList());
			}

			List<String> shopList = DomesinSessonUtil.ManagerShop;

			for (ShopCateInfo shopcateinf : shopcateinfos) {

				String class_cd3 = shopcateinf.cid;
				String class_cd2 = DomesinSessonUtil.get().getParentClassCd(class_cd3);
				String class_cd1 = DomesinSessonUtil.get().getParentClassCd(class_cd2);
				String cateName = DomesinSessonUtil.get().getCateName(class_cd3);
				String full_cateName = DomesinSessonUtil.get().getFullCateName(class_cd3);
				// ---- 카테고리 1 저장..
				CateDao.get().categoryLargeInsert(class_cd1, DomesinSessonUtil.get().getCateName(class_cd1), "0", "1",
						"도매의신", "");
				// ---- 카테고리 2 저장..
				CateDao.get().categoryMidiumInsert(class_cd1, class_cd2, DomesinSessonUtil.get().getCateName(class_cd2),
						"0", "1", "도매의신", "");

				CateDao.get().categorySmallInsert(class_cd2, class_cd3, DomesinSessonUtil.get().getCateName(class_cd3),
						"0", "1", "도매의신", "", "");

				for (String shopcd : shopList) {
					String esm_code = "";
					String code = "";
					switch (shopcd) {
					case ShopCommon.쿠팡:
						code = shopcateinf.es_coupang;
						break;
					case ShopCommon.인터파크:
						code = shopcateinf.es_interpark;
						break;
					case ShopCommon.지마켓:
						code = shopcateinf.es_gmarket;
						esm_code = shopcateinf.esm_gmarket;
						break;
					case ShopCommon.옥션:
						code = shopcateinf.es_auction;
						esm_code = shopcateinf.esm_auction;
						break;
					case ShopCommon.스토어팜:
						code = shopcateinf.es_storefarm;
						break;

					case ShopCommon.십일번가:
						code = shopcateinf.es_11st;
						break;

					}

					if (code.isEmpty())
						continue;

					String seq = shopcd + "_" + class_cd3;
					// ---- 라지맵 업데이트 ..
					CateDao.get().setProductMstShopCategoryUpdate(class_cd3, shopcd, String.valueOf(seq), "3");

					String catenm = shopcd.concat("_").concat(cateName);
					int i = 0;

					pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
					pstmt.setString(++i, shopcd);
					pstmt.setString(++i, seq);
					pstmt.setString(++i, catenm);
					pstmt.setString(++i, full_cateName);

					pstmt.setString(++i, esm_code);
					pstmt.setString(++i, code);
					pstmt.setString(++i, "일반배송상품");
					pstmt.setString(++i, "Y");
					pstmt.setString(++i, "01");
					pstmt.setString(++i, "kjm");
					pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());

					pstmt.setString(++i, esm_code);
					pstmt.setString(++i, code);

					pstmt.addBatch();
					pstmt.clearParameters(); // 파라미터 초기화
				}

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

	}

	public int saveOrUpdate(List<ProductItem> contents) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);
			String sql = "INSERT INTO ds_shopprodinfo(icode,iname,cid,shop_cid_info,icountry,"
					+ " maker,brand,tax,vender_code,delivery_type,"
					+ " delivery_amount,delivery_qty,keyword,price,price_consumer,"
					+ " islimit,limit_price,content,img,select_option," + " text_option,notice,itype,status,adult,"
					+ " reg_datetime,up_datetime,isreturn,cert_type,cert," + " cert_no,gosi_code,gosi_info) VALUES("
					+ " ?,?,?,?,?  \r\n" // icode,iname,cid,shop_cid_info,icountry,
					+ " ,?,?,?,?,? \r\n" // maker,brand,tax,vender_code,delivery_type,
					+ " ,?,?,?,?,? \r\n" // delivery_amount,delivery_qty,keyword,price,price_consumer,
					+ " ,?,?,?,?,? \r\n" // islimit,limit_price,content,img,select_option
					+ " ,?,?,?,?,? \r\n" // text_option,notice,itype,status,adult,
					+ " ,?,?,?,?,? \r\n" // reg_datetime,up_datetime,isreturn,cert_type,cert,
					+ "  ,?,?,?)  ON DUPLICATE KEY UPDATE iname = ?  \r\n"; // cert_no,gosi_code,gosi_info

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			for (ProductItem item : contents) {
				int rowIdx = 0;
				// icode,iname,cid,shop_cid_info,icountry,
				pstmt.setString(++rowIdx, item.getIcode());
				pstmt.setString(++rowIdx, YDMAStringUtil.setTextStrtok(item.getIname(), 100));
				pstmt.setString(++rowIdx, item.getCid());
				pstmt.setString(++rowIdx, getCateJson(item));
				pstmt.setString(++rowIdx, item.getIcountry());

				// "maker,brand,tax,vender_code,delivery_type,"
				pstmt.setString(++rowIdx, item.getMaker());
				pstmt.setString(++rowIdx, item.getBrand());
				pstmt.setString(++rowIdx, item.getTax());
				pstmt.setString(++rowIdx, item.getVender_code());
				pstmt.setString(++rowIdx, item.getDelivery_type());

				// + "delivery_amount,delivery_qty,keyword,price,price_consumer,"
				pstmt.setInt(++rowIdx, YDMAStringUtil.convertToInt(item.getDelivery_amount()));
				pstmt.setString(++rowIdx, item.getDelivery_qty());
				pstmt.setString(++rowIdx, item.getKeyword());
				pstmt.setInt(++rowIdx, YDMAStringUtil.convertToInt(item.getPrice()));
				pstmt.setInt(++rowIdx, YDMAStringUtil.convertToInt(item.getPrice_consumer()));

				// + "islimit,limit_price,content,img,select_option,"
				pstmt.setString(++rowIdx, item.getIslimit());
				pstmt.setInt(++rowIdx, YDMAStringUtil.convertToInt(item.getLimit_price()));

				String content = item.getContent().replaceAll("\"", "'");
				content = content.replaceAll("\\\\", "");
				// System.out.println(content);

				pstmt.setString(++rowIdx, content);

				String img = Arrays.asList(item.getImg()).stream().collect(Collectors.joining(","));
				pstmt.setString(++rowIdx, img);
				pstmt.setString(++rowIdx, getOptionJson(item.getSelect_option()));

				// + "text_option,notice,itype,status,adult,"
				pstmt.setString(++rowIdx, item.getText_option());
				pstmt.setString(++rowIdx, item.getNotice());
				pstmt.setString(++rowIdx, item.getItype());
				pstmt.setString(++rowIdx, item.getStatus());
				pstmt.setString(++rowIdx, item.getAdult());

				// + "reg_datetime,up_datetime,isreturn,cert_type,cert,"
				pstmt.setString(++rowIdx, item.getReg_datetime());
				pstmt.setString(++rowIdx, item.getUp_datetime());
				pstmt.setString(++rowIdx, item.getIsreturn());
				pstmt.setString(++rowIdx, item.getCert_type());
				pstmt.setString(++rowIdx, item.getCert());

				// + "cert_no,gosi_code,gosi_info
				pstmt.setString(++rowIdx, item.getCert_no());
				pstmt.setString(++rowIdx, item.getGosi_code());
				pstmt.setString(++rowIdx, getGosiInfoJson(item));
				pstmt.setString(++rowIdx, YDMAStringUtil.setTextStrtok(item.getIname(), 100));

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

	/*
	 * 옵션 스트링..
	 */
	public String getOptionJson(String optionString) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<DomesinOption> options = DomesinCommon.get().getOptionList(optionString).options;

		String json = mapper.writeValueAsString(options);
		return json;
	}

	// 고시정보 json
	public String getGosiInfoJson(ProductItem item) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		ShopGosiInfo shopGosiInfo = new ShopGosiInfo();
		shopGosiInfo.setGosi1(item.getGosi1());
		shopGosiInfo.setGosi2(item.getGosi2());
		shopGosiInfo.setGosi3(item.getGosi3());
		shopGosiInfo.setGosi4(item.getGosi4());
		shopGosiInfo.setGosi5(item.getGosi5());
		shopGosiInfo.setGosi6(item.getGosi6());
		shopGosiInfo.setGosi7(item.getGosi7());
		shopGosiInfo.setGosi8(item.getGosi8());
		shopGosiInfo.setGosi9(item.getGosi9());
		shopGosiInfo.setGosi10(item.getGosi10());
		shopGosiInfo.setGosi11(item.getGosi11());
		shopGosiInfo.setGosi12(item.getGosi12());
		shopGosiInfo.setGosi13(item.getGosi13());
		shopGosiInfo.setGosi14(item.getGosi14());
		shopGosiInfo.setGosi15(item.getGosi15());
		shopGosiInfo.setGosi16(item.getGosi16());
		shopGosiInfo.setGosi17(item.getGosi17());
		shopGosiInfo.setGosi18(item.getGosi18());
		shopGosiInfo.setGosi19(item.getGosi19());
		shopGosiInfo.setGosi20(item.getGosi20());
		shopGosiInfo.setGosi21(item.getGosi21());
		shopGosiInfo.setGosi22(item.getGosi22());

		String json = mapper.writeValueAsString(shopGosiInfo);
		return json;

	}

	/*
	 * 카테고리를 가져온다..
	 */
	public String getCateJson(ProductItem item) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		List<String> shopList = DomesinSessonUtil.ManagerShop;

		List<ShopCateItem> cate_list = new ArrayList<>();
		for (String shopcd : shopList) {
			ShopCateItem cateItem = new ShopCateItem();
			String esm_gmarket = (shopcd.equals(ShopCommon.지마켓)) ? item.getEsm_gmarket() : "";
			String esm_auction = (shopcd.equals(ShopCommon.옥션)) ? item.getEsm_auction() : "";

			cateItem.shopcd = shopcd;
			cateItem.esm_gmarket = esm_gmarket;
			cateItem.esm_auction = esm_auction;
			cateItem.cid = getCategoryCd(shopcd, item);
			cate_list.add(cateItem);
		}

		String json = mapper.writeValueAsString(cate_list);
		return json;
	}

	public void saveOrdInfo(List<ProductItem> contents) throws Exception {

		saveShopProdInfo(contents);
		saveShopOptProdInfo(contents);
		saveCategory(contents);
	}

	public String getCategoryCd(String shopcd, ProductItem item) {

		switch (shopcd) {
		case ShopCommon.옥션:
			return item.getEs_auction();
		case ShopCommon.지마켓:
			return item.getEs_gmarket();
		case ShopCommon.인터파크:
			return item.getEs_interpark();
		case ShopCommon.스토어팜:
			return item.getEs_storefarm();
		case ShopCommon.카페24:
			return "";
		}
		return "";
	}

	public void saveShopCategoryInfo(List<ProductItem> contents) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "insert into shopcatinfo (SHOPCATNO, SHOPCATNM,FULL_CATENM, ITEMS, USE_YN, INSERTDT )  "
					+ " values (?, ?, ?, ?, ?,? )  ON  DUPLICATE KEY UPDATE SHOPCATNM=? ,  FULL_CATENM=?, ITEMS=? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			Map<String, String> shopCategoryList = new HashMap<>();
			// 카테고리 동일한걸로 묶는다..
			List<String> cidList = contents.stream().map(p -> p.getCid()).distinct().collect(Collectors.toList());

			for (String cid : cidList) {

				ProductItem item = contents.stream().filter(p -> p.getCid().equals(cid)).findAny()
						.orElse(new ProductItem());
				// String class_cd3 = item.getCid();
				// String class_cd2 = DomesinSessonUtil.get().getParentClassCd(class_cd3);
				// String class_cd1 = DomesinSessonUtil.get().getParentClassCd(class_cd2);
				String full_cateName = DomesinSessonUtil.get().getFullCateName(cid);
				String cateName = DomesinSessonUtil.get().getCateName(cid);

				ObjectMapper mapper = new ObjectMapper();

				shopCategoryList.clear();
				List<String> shopList = Arrays.asList(ShopCommon.쿠팡, ShopCommon.인터파크, ShopCommon.지마켓, ShopCommon.옥션,
						ShopCommon.스토어팜);

				List<ShopCateItem> cate_list = new ArrayList<>();
				for (String shopcd : shopList) {
					ShopCateItem cateItem = new ShopCateItem();
					String esm_gmarket = (shopcd.equals(ShopCommon.지마켓)) ? item.getEsm_gmarket() : "";
					String esm_auction = (shopcd.equals(ShopCommon.옥션)) ? item.getEsm_auction() : "";

					cateItem.shopcd = shopcd;
					cateItem.esm_gmarket = esm_gmarket;
					cateItem.esm_auction = esm_auction;
					cateItem.cid = getCategoryCd(shopcd, item);
					cate_list.add(cateItem);
				}

				String json = mapper.writeValueAsString(cate_list);

				int i = 0;

				pstmt.setString(++i, cid);
				pstmt.setString(++i, cateName);
				pstmt.setString(++i, full_cateName);
				pstmt.setString(++i, json);
				pstmt.setString(++i, "Y");
				pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());

				pstmt.setString(++i, cateName);
				pstmt.setString(++i, full_cateName);
				pstmt.setString(++i, json);
				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}

			System.out.println("[smlCategoryInsert]" + pstmt.toString());

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

	}

	public void saveShopCatInfo(List<ProductItem> contents) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "insert into shopcatinf (compno, shopcd, shopcatno, shopcatnm, shopcatsitenm, shoplagcatcd, shopmidcatcd, shopsmlcatcd, shopdetcatcd, serviceprod, use_yn, SHOPGENERAL, SHOPID, insertdt,"
					+ " SHOPCOMMIS )  " + " values (?, ?, ?, ?, ? ,?, ? , ?, ?, ?, ?, ?, ?, ?, ? ) ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			Map<String, String> list_shopCd = new HashMap<>();

			// list_shopCd.put(ShopCommon.쿠팡, "쿠팡");
			// list_shopCd.put(ShopCommon.인터파크, "인터파크");
			// list_shopCd.put(ShopCommon.지마켓, "지마켓");
			// list_shopCd.put(ShopCommon.옥션, "옥션");
			list_shopCd.put(ShopCommon.스토어팜, "스토어팜");
			// list_shopCd.put(ShopCommon.카페24, "카페24");

			// 카테고리 동일한걸로 묶는다..
			List<String> cidList = contents.stream().map(p -> p.getCid()).distinct().collect(Collectors.toList());
			CategoryDao dao = new CategoryDao();
			int seq = dao.getShopcatinfMaxseq() + 1;
			for (String cid : cidList) {
				// List<ProductItem> items = mapList.get(cid);
				String class_cd3 = cid;
				String class_cd2 = DomesinSessonUtil.get().getParentClassCd(class_cd3);
				String class_cd1 = DomesinSessonUtil.get().getParentClassCd(class_cd2);
				String full_cateName = DomesinSessonUtil.get().getFullCateName(cid);
				String cateName = DomesinSessonUtil.get().getCateName(cid);

				ProductItem item = contents.stream().filter(p -> p.getCid().equals(cid)).findAny().orElse(null);
				CateDao.get().categoryLargeInsert(class_cd1, DomesinSessonUtil.get().getCateName(class_cd1), "0", "1",
						"도매의신카테고리", "");
				CateDao.get().categoryMidiumInsert(class_cd1, class_cd2, DomesinSessonUtil.get().getCateName(class_cd2),
						"0", "1", "도매의신카테고리", "");
				CateDao.get().categorySmallInsert(class_cd2, class_cd3, DomesinSessonUtil.get().getCateName(class_cd3),
						"0", "1", "도매의신카테고리", "", "");
				String class_code = class_cd1.concat(class_cd2).concat(class_cd3);

				for (String shopcode : list_shopCd.keySet()) {
					String catenm = list_shopCd.get(shopcode).concat("_").concat(cateName);
					int i = 0;
					seq++;
					// ---- 라지맵 업데이트 ..
					CateDao.get().setProductMstShopCategoryUpdate(class_code, shopcode, String.valueOf(seq), "3");
					pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
					pstmt.setString(++i, shopcode);
					pstmt.setInt(++i, seq);
					pstmt.setString(++i, catenm);
					pstmt.setString(++i, full_cateName);

					String code = item.getEs_storefarm();

					if (code.isEmpty())
						break;

					String lagcd = code.substring(0, 6);
					String midcd = code.substring(0, 9);
					String smlcd = (code.length() > 10) ? code.substring(0, 12) : "";
					String detcd = (code.length() > 14) ? code.substring(0, 15) : "";

					pstmt.setString(++i, lagcd);
					pstmt.setString(++i, midcd);
					pstmt.setString(++i, smlcd);
					pstmt.setString(++i, detcd);
					pstmt.setString(++i, "일반배송상품");
					pstmt.setString(++i, "Y");
					pstmt.setString(++i, "01");
					pstmt.setString(++i, "kjm");
					pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
					pstmt.setFloat(++i, 0);
					pstmt.addBatch();
					pstmt.clearParameters(); // 파라미터 초기화

				}
			}

			System.out.println("[smlCategoryInsert]" + pstmt.toString());

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

	}

	public void saveShopOptProdInfo(List<ProductItem> contents) throws Exception {

		int result = 0;
//		Connection connection = null;
		Connection connection2 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist2 = new ArrayList<PreparedStatement>();

		try {

			connection2 = DBCPInit.getInstance().getConnection();
			connection2.setAutoCommit(false);

			String sql2 = " insert into shop_optprodinfo "
					+ "(PRODSEQ,SKUSEQ,COMPNO,PRODCD,OPTPRODCD,OPTPRODNM,OPTSPECDES,OPTEA,OPTSALE,"
					+ " OPTSALEOUT,OPTNOTUSE,OPTSAFESTOCK,OPTVERTSTOCK,OPTADDAMT,OPTDELYN,BARCODE,"
					+ " INSERTDT,INSERTID,MODIFYDT,MODIFYID )" + " VALUES " + "(?,?,?,?,?,?,?,?,?, "
					+ " ?,?,?,?,?,?,?, " + " ?,?,?,? " + " ) " + " ON\r\n" + "      DUPLICATE KEY" + " UPDATE  \r\n"
					+ "PRODCD = ?, \r\n" + "OPTPRODCD = ?, \r\n" + "OPTPRODNM = ?, \r\n" + "OPTSPECDES = ?, \r\n"
					+ "OPTEA = ?, \r\n" + "OPTSALE = ?, \r\n" + "OPTSALEOUT = ?, \r\n" + "OPTNOTUSE = ?, \r\n"
					+ "OPTSAFESTOCK = ?, \r\n" + "OPTVERTSTOCK = ?, \r\n" + "OPTADDAMT = ?, \r\n" + "OPTDELYN = ?, \r\n"
					+ "BARCODE = ?, \r\n" + "MODIFYDT = ?, \r\n" + "MODIFYID = ? ";

			sql2 = sql2.toUpperCase();
			pstmt2 = connection2.prepareStatement(sql2);
			statementlist2.add(pstmt2);

			String sql = "SELECT prodseq  FROM shopprodinfo where compno = ? and compayny_goods_cd=? ";
			sql = sql.toUpperCase();
			pstmt = connection2.prepareStatement(sql);

			for (ProductItem list : contents) {

				if (list.isModifyMode()) { // 수정 모드일경우에.. 기본 정보가 수정이 없다면..
					if (!list.isOptionModify())
						continue;
				}

				String optionString = list.getSelect_option();

				int prodseq = 0;
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.getIcode());
				rs = pstmt.executeQuery();

				if (rs.next()) {
					prodseq = rs.getInt(1);
				}

				if (optionString.isEmpty())
					continue;

				System.out.println();
				List<DomesinOption> options = DomesinCommon.get().getOptionList(optionString).options;

				int i = 1;
				for (DomesinOption option : options) {

					int rowIdx2 = 0;
					pstmt2.setInt(++rowIdx2, prodseq); // PRODSEQ
					pstmt2.setInt(++rowIdx2, i); // SKUSEQ
					pstmt2.setString(++rowIdx2, YDMASessonUtil.getCompnoInfo().getCompno()); // COMPNO
					for (int k = 0; k < 2; k++) {
						pstmt2.setString(++rowIdx2, list.getIcode()); // PRODCD
						String optrprodcd = list.getIcode().concat("-")
								.concat(YDMAStringUtil.leftPad(String.valueOf(i), 2, "0"));
						pstmt2.setString(++rowIdx2, optrprodcd); // OPTPRODCD
						pstmt2.setString(++rowIdx2, option.getOptionName1()); // OPTPRODNM
						pstmt2.setString(++rowIdx2,
								!option.getOptionName1().equals("")
										? option.getOptionName1().concat(
												option.getOptionName2().equals("") ? "" : ":" + option.getOptionName2())
										: ""); // OPTSPECDES
						pstmt2.setInt(++rowIdx2, 0); // OPTEA
						pstmt2.setString(++rowIdx2, ""); // OPTSALE
						pstmt2.setString(++rowIdx2, ""); // OPTSALEOUT
						pstmt2.setString(++rowIdx2, ""); // OPTNOTUSE

						int cnt = (option.getSoldOut() == 0) ? 999 : 0;

						pstmt2.setInt(++rowIdx2, cnt); // OPTSAFESTOCK
						pstmt2.setInt(++rowIdx2, cnt); // OPTVERTSTOCK

						int price = YDMAStringUtil.convertToInt(list.getPrice()) - option.getOptionPrice();

						pstmt2.setInt(++rowIdx2, price); // OPTADDAMT

						pstmt2.setString(++rowIdx2, ""); // OPTDELYN
						pstmt2.setString(++rowIdx2, ""); // BARCODE
						if (k == 0) {
							pstmt2.setString(++rowIdx2, ""); // INSERTDT
							pstmt2.setString(++rowIdx2, ""); // INSERTID
						}
						pstmt2.setString(++rowIdx2, YDMATimeUtil.getCurrentTimeByYDFormat()); // MODIFYDT
						pstmt2.setString(++rowIdx2, YDMASessonUtil.getUserInfo().getUserId()); // MODIFYID
					}

					pstmt2.addBatch();
					pstmt2.clearParameters(); // 파라미터 초기화
					++i;

				}
			}
			System.out.println(pstmt2.toString());
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

	public int updateShopProdStatus(List<ProductItem> contents) throws Exception {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = "UPDATE shopprodinfo\r\n" + "SET\r\n" + "    ShopSendStatus =? \r\n"
					+ "   , ShopModifyContent =?\r\n" +  "WHERE\r\n" + "  COMPAYNY_GOODS_CD =?\r\n"
					+ "  AND COMPNO = 4";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			for (ProductItem list : contents) {
				int rowIdx = 0;

				if (list.isModifyMode()) { // 수정 모드일경우에.. 기본 정보가 수정이 없다면..
					if (!list.isBaseInfoModify())
						continue;
				}

				String modifyContent = list.getChange_list().stream().collect(Collectors.joining(","));
				pstmt.setInt(++rowIdx, -2);
				pstmt.setString(++rowIdx, modifyContent);
				pstmt.setString(++rowIdx, list.getIcode());

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

	/*
	 * 저장..
	 */
	public int saveShopProdInfo(List<ProductItem> contents) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql = " insert into shopprodinfo "
					+ "(SHOP_CID_INFO, SELECT_OPTION,COMPNO,GOODS_NM,MODEL_NM,BRAND_NM,COMPAYNY_GOODS_CD,GOODS_SEARCH,"
					+ " GOODS_GUBUN,MAKER,ORIGIN,CLASS_CD1,CLASS_CD2,CLASS_CD3,CLASS_CD4,"
					+ " GOODS_SEASON,SEX,STATUS,DELIV_ABLE_REGION,TAX_YN,DELV_TYPE,DELV_COST,"
					+ " GOODS_PRICE,GOODS_CONSUMER_PRICE,CHAR_1_NM,CHAR_2_NM,char_1_val,CHAR_2_VAL,IMG_PATH,IMG_PATH6,"
					+ " IMG_PATH7,GOODS_REMARKS,STOCK_USE_YN,OPT_TYPE,PROP_EDIT_YN,PROP1_CD,PROP_VAL1,"
					+ " PROP_VAL2,PROP_VAL3,PROP_VAL4,PROP_VAL5,PROP_VAL6,PROP_VAL7,PROP_VAL8,"
					+ " PROP_VAL9,PROP_VAL10,PROP_VAL11,PROP_VAL12,PROP_VAL13,PROP_VAL14,PROP_VAL15,"
					+ " PROP_VAL16,PROP_VAL17,PROP_VAL18,PROP_VAL19,PROP_VAL20,PROP_VAL21,PROP_VAL22,"
					+ " PROP_VAL23,PROP_VAL24,PROP_VAL25,PROP_VAL26,PROP_VAL27,PROP_VAL28,"
					+ " INSERTDT,INSERTID  ,ShopSendStatus , ShopModifyContent  )" + " VALUES (?,?,?,?,?,?,"
					+ " ?,?,?,?,?,?,?,?,?," + " ?,?,?,?,?,?,?," + " ?,?,?,?,?,?,?,?," + " ?,?,?,?,?,?,?,"
					+ " ?,?,?,?,?,?,?," + " ?,?,?,?,?,?,?," + " ?,?,?,?,?,?,?," + " ?,?,?,?,?,?," + " ?,?, ?, ?) "
					+ " ON\r\n" + "      DUPLICATE KEY \r\n" + " UPDATE \r\n" + "GOODS_NM = ? ,\r\n"
					+ "MODEL_NM = ? ,\r\n" + "BRAND_NM = ? ,\r\n" + "COMPAYNY_GOODS_CD = ? ,\r\n"
					+ "GOODS_SEARCH = ? ,\r\n" + "GOODS_GUBUN = ? ,\r\n" + "MAKER = ? ,\r\n" + "ORIGIN = ? ,\r\n"
					+ "CLASS_CD1 = ? ,\r\n" + "CLASS_CD2 = ? ,\r\n" + "CLASS_CD3 = ? ,\r\n" + "CLASS_CD4 = ? ,\r\n"
					+ "GOODS_SEASON = ? ,\r\n" + "SEX = ? ,\r\n" + "STATUS = ? ,\r\n" + "DELIV_ABLE_REGION = ? ,\r\n"
					+ "TAX_YN = ? ,\r\n" + "DELV_TYPE = ? ,\r\n" + "DELV_COST = ? ,\r\n" + "GOODS_PRICE = ? ,\r\n"
					+ "GOODS_CONSUMER_PRICE = ? ,\r\n" + "CHAR_1_NM = ? ,\r\n" + "CHAR_2_NM = ? ,\r\n"
					+ "char_1_val = ? ,\r\n" + "CHAR_2_VAL = ? ,\r\n" + "IMG_PATH = ? ,\r\n" + "IMG_PATH6 = ? ,\r\n"
					+ "IMG_PATH7 = ? ,\r\n" + "GOODS_REMARKS = ? ,\r\n" + "STOCK_USE_YN = ? ,\r\n"
					+ "OPT_TYPE = ? ,\r\n" + "PROP_EDIT_YN = ? ,\r\n" + "PROP1_CD = ? ,\r\n" + "PROP_VAL1 = ? ,\r\n"
					+ "PROP_VAL2 = ? ,\r\n" + "PROP_VAL3 = ? ,\r\n" + "PROP_VAL4 = ? ,\r\n" + "PROP_VAL5 = ? ,\r\n"
					+ "PROP_VAL6 = ? ,\r\n" + "PROP_VAL7 = ? ,\r\n" + "PROP_VAL8 = ? ,\r\n" + "PROP_VAL9 = ? ,\r\n"
					+ "PROP_VAL10 = ? ,\r\n" + "PROP_VAL11 = ? ,\r\n" + "PROP_VAL12 = ? ,\r\n" + "PROP_VAL13 = ? ,\r\n"
					+ "PROP_VAL14 = ? ,\r\n" + "PROP_VAL15 = ? ,\r\n" + "PROP_VAL16 = ? ,\r\n" + "PROP_VAL17 = ? ,\r\n"
					+ "PROP_VAL18 = ? ,\r\n" + "PROP_VAL19 = ? ,\r\n" + "PROP_VAL20 = ? ,\r\n" + "PROP_VAL21 = ? ,\r\n"
					+ "PROP_VAL22 = ? ,\r\n" + "PROP_VAL23 = ? ,\r\n" + "PROP_VAL24 = ? ,\r\n" + "PROP_VAL25 = ? ,\r\n"
					+ "PROP_VAL26 = ? ,\r\n" + "PROP_VAL27 = ? ,\r\n" + "PROP_VAL28 = ? ,\r\n" + "MODIFYDT = ? ,\r\n"
					+ "MODIFYID = ?  ,\r\n ShopSendStatus=? , \r\n ShopModifyContent=?  ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			for (ProductItem list : contents) {
				int rowIdx = 0;

				if (list.isModifyMode()) { // 수정 모드일경우에.. 기본 정보가 수정이 없다면..
					if (!list.isBaseInfoModify())
						continue;
				}

				// Node COMPNO = document.createElement("COMPNO");
				String cate_json = getCateJson(list);
				String select_option = list.getSelect_option();
				pstmt.setString(++rowIdx, cate_json);
				pstmt.setString(++rowIdx, select_option);

				pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
				for (int j = 0; j < 2; j++) {
					if (list.getIname().getBytes().length > 100) {
						byte[] strByte = list.getIname().getBytes();
						String newTitle = new String(strByte, 0, 100);
						pstmt.setString(++rowIdx, list.getIname());
					} else {
						pstmt.setString(++rowIdx, list.getIname());
					}

					// Node MODEL_NM = document.createElement("MODEL_NM");
					pstmt.setString(++rowIdx, list.getIcode());

					// Node BRAND_NM = document.createElement("BRAND_NM");
					pstmt.setString(++rowIdx, list.getBrand());

					// Node COMPAYNY_GOODS_CD = document.createElement("COMPAYNY_GOODS_CD");
					pstmt.setString(++rowIdx, list.getIcode());

					// Node GOODS_SEARCH = document.createElement("GOODS_SEARCH");
					pstmt.setString(++rowIdx, list.getKeyword());

					// Node GOODS_GUBUN = document.createElement("GOODS_GUBUN");
					pstmt.setString(++rowIdx, "");

					// Node MAKER = document.createElement("MAKER");
					pstmt.setString(++rowIdx, list.getMaker());

					// Node ORIGIN = document.createElement("ORIGIN");
					pstmt.setString(++rowIdx, list.getIcountry());

					String class_cd3 = list.getCid();

					String class_cd2 = DomesinSessonUtil.get().getParentClassCd(class_cd3);
					String class_cd1 = DomesinSessonUtil.get().getParentClassCd(class_cd2);

					pstmt.setString(++rowIdx, class_cd1);

					pstmt.setString(++rowIdx, class_cd2);

					pstmt.setString(++rowIdx, class_cd3);
					pstmt.setString(++rowIdx, "");

					// Node GOODS_SEASON = document.createElement("GOODS_SEASON");
					pstmt.setString(++rowIdx, "");

					// Node SEX = document.createElement("SEX");
					pstmt.setString(++rowIdx, "");

					// Node STATUS = document.createElement("STATUS");
					pstmt.setString(++rowIdx, list.getStatus());

					// Node DELIV_ABLE_REGION = document.createElement("DELIV_ABLE_REGION");
					pstmt.setString(++rowIdx, "");

					// Node TAX_YN = document.createElement("TAX_YN");
					pstmt.setString(++rowIdx, (list.getTax().equals("0")) ? "1" : "2");

					// Node DELV_TYPE = document.createElement("DELV_TYPE");
					pstmt.setString(++rowIdx, "");

					// Node DELV_COST = document.createElement("DELV_COST");
					pstmt.setInt(++rowIdx, 0);

					// Node GOODS_PRICE = document.createElement("GOODS_PRICE");
					pstmt.setInt(++rowIdx, YDMAStringUtil.convertToInt(list.getPrice()));

					// Node GOODS_CONSUMER_PRICE = document.createElement("GOODS_CONSUMER_PRICE");
					pstmt.setInt(++rowIdx, YDMAStringUtil.convertToInt(list.getPrice_consumer()));

					// Node CHAR_1_NM = document.createElement("CHAR_1_NM");

					DomesinOptionList options = DomesinCommon.get().getOptionList(list.getSelect_option());

					String option = (options.getChar_1_nm().isEmpty()) ? "단품" : options.getChar_1_nm();

					pstmt.setString(++rowIdx, option); // 컬러

					// Node CHAR_2_NM = document.createElement("CHAR_2_NM");
					pstmt.setString(++rowIdx, options.getChar_2_nm()); // 사이즈.

					// Node CHAR_1_VAL = document.createElement("CHAR_1_VAL");
					// 옵션상세 명칭 2(사이즈) 결정을 위한 변수
					String char_2_val = "";

					pstmt.setString(++rowIdx, "");
					// Node CHAR_2_VAL = document.createElement("CHAR_2_VAL");
					pstmt.setString(++rowIdx, options.getChar_2_val());

					// Node IMG_PATH = document.createElement("IMG_PATH");
					List<String> imgs = new ArrayList<>();

					for (int i = 0; i < 3; ++i) {

						if (i > list.getImg().length - 1) {
							imgs.add("");
						} else {
							imgs.add(list.getImg()[i]);
						}

					}

					for (String img : imgs) {
						pstmt.setString(++rowIdx, img);
					}

					System.out.println("----------------goodsmark ----------------");
					System.out.println(list.getContent().length());
					System.out.println(list.getContent().replaceAll("\"", "'"));
					System.out.println("----------------goodsmark ----------------");

					String content = list.getContent().replaceAll("\"", "'");
					content = content.replaceAll("\\\\", "");
					System.out.println(content);
					pstmt.setString(++rowIdx, content);

					// Node STOCK_USE_YN = document.createElement("STOCK_USE_YN");
					pstmt.setString(++rowIdx, "N");

					// Node OPT_TYPE = document.createElement("OPT_TYPE");
					pstmt.setString(++rowIdx, "9");

					// 속성수정여부
					// Node PROP_EDIT_YN = document.createElement("PROP_EDIT_YN");
					pstmt.setString(++rowIdx, "Y");
					// 여기서부터 추가
					// Node PROP1_CD = document.createElement("PROP1_CD");

					pstmt.setString(++rowIdx, list.getGosi_code());

					pstmt.setString(++rowIdx, list.getGosi1());
					pstmt.setString(++rowIdx, list.getGosi2());
					pstmt.setString(++rowIdx, list.getGosi3());
					pstmt.setString(++rowIdx, list.getGosi4());
					pstmt.setString(++rowIdx, list.getGosi5());
					pstmt.setString(++rowIdx, list.getGosi6());
					pstmt.setString(++rowIdx, list.getGosi7());
					pstmt.setString(++rowIdx, list.getGosi8());
					pstmt.setString(++rowIdx, list.getGosi9());
					pstmt.setString(++rowIdx, list.getGosi10());
					pstmt.setString(++rowIdx, list.getGosi11());
					pstmt.setString(++rowIdx, list.getGosi12());
					pstmt.setString(++rowIdx, list.getGosi13());
					pstmt.setString(++rowIdx, list.getGosi14());
					pstmt.setString(++rowIdx, list.getGosi15());
					pstmt.setString(++rowIdx, list.getGosi16());
					pstmt.setString(++rowIdx, list.getGosi17());
					pstmt.setString(++rowIdx, list.getGosi18());
					pstmt.setString(++rowIdx, list.getGosi19());
					pstmt.setString(++rowIdx, list.getGosi20());
					pstmt.setString(++rowIdx, list.getGosi21());
					pstmt.setString(++rowIdx, list.getGosi22());
					pstmt.setString(++rowIdx, "");
					pstmt.setString(++rowIdx, "");
					pstmt.setString(++rowIdx, "");
					pstmt.setString(++rowIdx, "");
					pstmt.setString(++rowIdx, "");
					pstmt.setString(++rowIdx, "");

					pstmt.setString(++rowIdx, YDMATimeUtil.getCurrentTimeByYDFormat());
					pstmt.setString(++rowIdx, YDMASessonUtil.getUserInfo().getUserId());

					pstmt.setInt(++rowIdx, list.getShopSendStatus());
					pstmt.setString(++rowIdx, list.getShopModifyContent());

				}

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

	// 마진 판매간설정 인설트
	public int MarginInsert(String title, String shopid, String testprice, String sellerdiscount, String discountrate,
			String cumdiscount, String marginrate, String commrate, String marketselprice, String useyn, String shopcd)
			throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		int result = 0;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "insert into marginmngr (compno,SHOPCD,TITLE, SHOPID, TESTPRICE, SELLERDISCOUNT,DISCOUNTRATE,CUMDISCOUNT,MARGINRATE,COMMRATE,MARKETSELPRICE,USE_YN,INSERTDT,INSERTID )  "
					+ " values (?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?) ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno()); // YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, shopcd);
			pstmt.setString(++i, title);
			pstmt.setString(++i, shopid);
			pstmt.setString(++i, testprice);
			pstmt.setString(++i, sellerdiscount);
			pstmt.setString(++i, discountrate);
			pstmt.setString(++i, cumdiscount);
			pstmt.setString(++i, marginrate);
			pstmt.setString(++i, commrate);
			pstmt.setString(++i, marketselprice);
			pstmt.setString(++i, useyn);
			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUserId());

			System.out.println("[CategoryLargeInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;

	}

	// 마켓판매가 가지고 오기
	public List<List<String>> getMarginList(String date, String shopping, String search, String prodFrom, String prodTo,
			int shopcd, String useyn, List<String> shoplist) throws Exception {
		List<List<String>> contents = new ArrayList<List<String>>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select MARGINSEQ, ifnull(SHOPCD,''), ifnull(TITLE,''), ifnull(SHOPID,''), ifnull(TESTPRICE,'0'), ifnull(SELLERDISCOUNT,'0'), ifnull(DISCOUNTRATE,'0'), "
					+ " ifnull(CUMDISCOUNT,'0'), ifnull(MARGINRATE,'0'), ifnull(COMMRATE,'0'), ifnull(MARKETSELPRICE,'0'), ifnull(USE_YN,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), "
					+ " ifnull(MODIFYDT,''), ifnull(MODIFYID,'') from marginmngr where compno = ? ";
			sql += String.format(" and %s >= '%s' and %s <= '%s' ", date, prodFrom, date, prodTo);
			if (!useyn.equals("")) {
				sql += String.format(" and USE_YN = '%s' ", useyn);
			}
			if (!shopping.equals("")) {
				sql += String.format(" and %s like % " + search + "% ", shopping);
			}
			if (shopcd != 0) {
				sql += String.format(" and shopcd = '%s' ", shoplist.get(0));
			}
			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getCategorySearchList]" + pstmt.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<String>();
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

	public int MarginUpdate(String title, String shopid, String testprice, String sellerdiscount, String discountrate,
			String cumdiscount, String marginrate, String commrate, String marketselprice, String useyn,
			List<String> uplist) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		int result = 0;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		PreparedStatement pstmt = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "update marginmngr set TITLE = ?, SHOPID = ?, TESTPRICE = ?, SELLERDISCOUNT = ?,DISCOUNTRATE = ?,CUMDISCOUNT = ?,MARGINRATE = ?,"
					+ " COMMRATE = ?, MARKETSELPRICE = ?, USE_YN = ? ,MODIFYDT = ?,MODIFYID = ? where compno = ? and MARGINSEQ = ? and shopcd = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			int i = 0;
			pstmt.setString(++i, title);
			pstmt.setString(++i, shopid);
			pstmt.setString(++i, testprice);
			pstmt.setString(++i, sellerdiscount);
			pstmt.setString(++i, discountrate);
			pstmt.setString(++i, cumdiscount);
			pstmt.setString(++i, marginrate);
			pstmt.setString(++i, commrate);
			pstmt.setString(++i, marketselprice);
			pstmt.setString(++i, useyn);
			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++i, YDMASessonUtil.getUserInfo().getUserId());

			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, uplist.get(0));
			pstmt.setString(++i, uplist.get(1));

			System.out.println("[CategoryLargeInsert]" + pstmt.toString());

			result = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public void MarginListDelete(List<List<String>> contents) throws Exception {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "delete from marginmngr where compno = ? and MARGINSEQ = ? and shopcd = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (List<String> list : contents) {
				int i = 0;
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++i, list.get(0));
				pstmt.setString(++i, list.get(1));

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

	public List<DomesinMarginDto> getMarginSelectList(String shopcd) throws Exception {
		List<DomesinMarginDto> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select MARGINSEQ, ifnull(SHOPCD,''), ifnull(TITLE,''), ifnull(SHOPID,''), ifnull(TESTPRICE,'0'), ifnull(SELLERDISCOUNT,'0'), ifnull(DISCOUNTRATE,'0'), "
					+ " ifnull(CUMDISCOUNT,'0'), ifnull(MARGINRATE,'0'), ifnull(COMMRATE,'0'), ifnull(MARKETSELPRICE,'0'), ifnull(USE_YN,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), "
					+ " ifnull(MODIFYDT,''), ifnull(MODIFYID,'') from marginmngr where compno = ? and shopcd = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);

			System.out.println("[getCategorySearchList]" + pstmt.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DomesinMarginDto dto = new DomesinMarginDto();
				int columnIndex = 0;
				dto.setMarginseq(rs.getString(++columnIndex));
				dto.setShopcd(rs.getString(++columnIndex));
				dto.setTitle(rs.getString(++columnIndex));
				dto.setShopid(rs.getString(++columnIndex));
				dto.setTestprice(rs.getString(++columnIndex));
				dto.setSellerdiscount(rs.getString(++columnIndex));
				dto.setDiscountrate(rs.getString(++columnIndex));
				dto.setCumdiscount(rs.getString(++columnIndex));
				dto.setMarginrate(rs.getString(++columnIndex));
				dto.setCommrate(rs.getString(++columnIndex));
				dto.setMarketselprice(rs.getString(++columnIndex));
				dto.setUse_yn(rs.getString(++columnIndex));
				dto.setInsertdt(rs.getString(++columnIndex));
				dto.setInsertid(rs.getString(++columnIndex));
				dto.setModifydt(rs.getString(++columnIndex));
				dto.setModifyid(rs.getString(++columnIndex));

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

	public DomesinMarginDto MarginOneSelect(String marginseq, String shopcd) throws Exception {
		DomesinMarginDto dto = new DomesinMarginDto();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select MARGINSEQ, ifnull(SHOPCD,''), ifnull(TITLE,''), ifnull(SHOPID,''), ifnull(TESTPRICE,'0'), ifnull(SELLERDISCOUNT,'0'), ifnull(DISCOUNTRATE,'0'), "
					+ " ifnull(CUMDISCOUNT,'0'), ifnull(MARGINRATE,'0'), ifnull(COMMRATE,'0'), ifnull(MARKETSELPRICE,'0'), ifnull(USE_YN,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), "
					+ " ifnull(MODIFYDT,''), ifnull(MODIFYID,'') from marginmngr where compno = ? and shopcd = ? and MARGINSEQ = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setString(3, marginseq);

			System.out.println("[getCategorySearchList]" + pstmt.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				dto.setMarginseq(rs.getString(++columnIndex));
				dto.setShopcd(rs.getString(++columnIndex));
				dto.setTitle(rs.getString(++columnIndex));
				dto.setShopid(rs.getString(++columnIndex));
				dto.setTestprice(rs.getString(++columnIndex));
				dto.setSellerdiscount(rs.getString(++columnIndex));
				dto.setDiscountrate(rs.getString(++columnIndex));
				dto.setCumdiscount(rs.getString(++columnIndex));
				dto.setMarginrate(rs.getString(++columnIndex));
				dto.setCommrate(rs.getString(++columnIndex));
				dto.setMarketselprice(rs.getString(++columnIndex));
				dto.setUse_yn(rs.getString(++columnIndex));
				dto.setInsertdt(rs.getString(++columnIndex));
				dto.setInsertid(rs.getString(++columnIndex));
				dto.setModifydt(rs.getString(++columnIndex));
				dto.setModifyid(rs.getString(++columnIndex));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	public List<ShopOrderMstDto> getNaverDeliveryCode(List<ShopOrderMstDto> list) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(URL,'') from expressdeliverys where CODE = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, list.get(0).getDelvcode());

			System.out.println("[getCategorySearchList]" + pstmt.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				list.get(0).setDelvurl(rs.getString(++columnIndex));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}
}

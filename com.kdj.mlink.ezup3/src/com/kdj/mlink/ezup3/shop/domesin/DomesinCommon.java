package com.kdj.mlink.ezup3.shop.domesin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.coupang.openapi.sdk.Hmac;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kdj.mlink.ezup3.shop.common.ChromeExtention;
import com.kdj.mlink.ezup3.shop.common.ChromeScript;
import com.kdj.mlink.ezup3.shop.common.HttpClientEx;
import com.kdj.mlink.ezup3.shop.common.OrderStatus;
import com.kdj.mlink.ezup3.shop.common.ShopCommon;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductSendDto;
import com.kdj.mlink.ezup3.common.YDMAProgressBar;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.CompInfoDao;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;
import com.kdj.mlink.ezup3.data.dao.QuestListDto;

public class DomesinCommon {

	public static DomesinCommon instance = new DomesinCommon();

	private DomesinCommon() {
	}

	public static DomesinCommon get() {
		return instance;
	}

	final static String LOGIN_HOST = "http://www.domesin.com";

	public final static String DATA_HOST = "http://data.domesin.com";

	public static String UserId = "";
	public static String UserPassword = "";
	public static String UserApiKey = "";
	public static String FailReason = "";
	public static List<CategoryItemDomesin> category;

	private static class LoginResponse {
		@JsonProperty("code")
		public String code;
		@JsonProperty("m_id")
		public String m_id;
		@JsonProperty("api_key")
		public String api_key;
		@JsonProperty("notice_url")
		public String notice_url;

		@Override
		public String toString() {
			return "LoginResponse [code=" + code + ", m_id=" + m_id + ", api_key=" + api_key + ", notice_url="
					+ notice_url + "]";
		}
	}

	private final static class CategoryDomesin {
		@JsonProperty("code")
		public String code;
		@JsonProperty("data")
		public CategoryItemDomesin[] data;
	}

	private final static class CategoryItemDomesin {
		public String cid;
		public String name;
		public String deep;

		@Override
		public String toString() {
			return "CategoryItemDomesin [cid=" + cid + ", name=" + name + ", deep=" + deep + ", parent_cid="
					+ parent_cid + ", ca_qty=" + ca_qty + ", fullcat=" + fullcat + ", ec=" + ec + "]";
		}

		public String parent_cid;
		public String ca_qty;
		public String fullcat;
		public String ec;
	}

	public boolean LoadCategory() throws Exception {
		Map<String, String> postPara = null;
		String response = "";
		String path = "/API/COUPANG/category.php";
		System.out.println("[DoMeSin] load category start...");

		// 파라메터 생성
		postPara = new HashMap<>();

		postPara.put("id", UserId);
		postPara.put("api_key", UserApiKey);
		String postForm = ConvertDictionaryToParameter(postPara);
		response = HttpClientEx.get().addParam("ContentType", "application/x-www-form-urlencoded")
				.Post(DATA_HOST.concat(path), postForm);

		if (response == null)
			return false;

		ObjectMapper mapper = new ObjectMapper();

		CategoryDomesin Category = null;
		Category = mapper.readValue(response, CategoryDomesin.class);

		if (Category.code.equals("0000")) {
			int maxDebug = (Category.data.length > 5) ? 5 : Category.data.length;
			System.out.println("    debug counts: " + maxDebug);

			for (int idx = 0; idx < maxDebug; idx++) {

				System.out.println("    [" + idx + "]");
				System.out.println("      cid: " + Category.data[idx].cid);
				System.out.println("      name: " + Category.data[idx].name);
				System.out.println("      deep: " + Category.data[idx].deep);
				System.out.println("      parent_cid: " + Category.data[idx].parent_cid);
				System.out.println("      ca_qty: " + Category.data[idx].ca_qty);
				System.out.println("      fullcat: " + Category.data[idx].fullcat);
				System.out.println("      ec: " + Category.data[idx].ec);
			}
		} else {
			FailReason = "카테고리에 정의되지 않은 에러가 있습니다...";
			System.out.println("    Error: " + FailReason);
		}
		category = Arrays.asList(Category.data);
		System.out.println("[DoMeSin] load category finish...");
		return true;
	}

	/*
	 * 상위 클래스 cd를 가져온다.
	 */
	public String getParentClassCd(String cid) {
		return this.category.stream().filter(p -> p.cid.equals(cid)).map(p -> p.parent_cid).findAny().orElse("");
	}

	public String getCateName(String cid) {
		return this.category.stream().filter(p -> p.cid.equals(cid)).map(p -> p.name).findAny().orElse("");
	}

	public String getFullCateName(String cid) {
		return this.category.stream().filter(p -> p.cid.equals(cid)).map(p -> p.fullcat).findAny().orElse("");
	}

	public void setCategoryDao() throws Exception {

		List<CategoryItemDomesin> firstLevel = this.category.stream().filter(p -> p.deep.equals("1"))
				.collect(Collectors.toList());

		for (CategoryItemDomesin item : firstLevel) {
			System.out.println(item.toString());
			CateDao.get().categoryLargeInsert(item.cid, item.name, "0", "1", "도매의신", "");
		}

		System.out.println();
		List<CategoryItemDomesin> secondLevel = this.category.stream().filter(p -> p.deep.equals("2"))
				.collect(Collectors.toList());

		for (CategoryItemDomesin item : secondLevel) {
			System.out.println(item.toString());
			CateDao.get().categoryMidiumInsert(item.parent_cid, item.parent_cid.concat(item.cid), item.name, "0", "1",
					"도매의신", "");
		}

		System.out.println();
		List<CategoryItemDomesin> thirdLevel = this.category.stream().filter(p -> p.deep.equals("3"))
				.collect(Collectors.toList());

		for (CategoryItemDomesin item : thirdLevel) {
			System.out.println(item.toString());
			CategoryItemDomesin parentItem = secondLevel.stream().filter(p -> p.cid.equals(item.parent_cid)).findAny()
					.get();
			String midcode = String.format("%s%s", parentItem.parent_cid, parentItem.cid);
			CateDao.get().categorySmallInsert(midcode, midcode.concat(item.cid), item.name, "0", "1", "도매의신", "",
					item.ec);
		}

		System.out.println();
	}

	private final static class OptionDomesin {

	}

	// date_type=0&start_date=2020-03-09&end_date=2020-03-09&cid_type=1&cid_arr=&min_amount=0&max_amount=0&vender_m_code_type=0&vender_m_code=&icode_type=0&icode_arr=&q=&is_overseas=&is_tax=&isreturn=&islimit=&delivery_type=&list_option_use=&adult=&status=1&page=1&m_id=theilda&api_key=60aNl0BeKDTpdAhBofJD

	public Map<String, String> GetProductQueryParameters() {
		Map<String, String> parameters = new HashMap<String, String>();

		// 기간 선택
		parameters.put("date_type", "0"); // 0 = 신규상품 조회, 1 = 수정상품 조회
		parameters.put("start_date", "2020-03-01");
		parameters.put("end_date", "2020-03-09");

		// 카테고리 선택
		parameters.put("cid_type", "1"); // 0:포함, 1:제외

//         List<string> cid_arr = new List<string>();
//         if (dataGridCategory.Rows.Count > 0) {
//             foreach (DataGridViewRow dataRow in dataGridCategory.Rows) {
//                 cid_arr.Add((string)dataRow.Cells["Column_cid"].Value);
//             }
//         }

		parameters.put("min_amount", "0");
		parameters.put("max_amount", "0");
		parameters.put("vender_m_code_type", "0");
		parameters.put("vender_m_code", "");
		parameters.put("icode_type", "0");
		parameters.put("icode_arr", "");
		parameters.put("q", "");
		parameters.put("is_overseas", "");

		parameters.put("is_tax", "");
		parameters.put("isreturn", "");
		parameters.put("islimit", "");
		parameters.put("delivery_type", "");
		parameters.put("list_option_use", "");
		parameters.put("adult", "");
		parameters.put("status", "1");
		return parameters;
	}

	/*
	 * 도매의신 옵션을 불리해서 가져온다..
	 */
	public DomesinOptionList getOptionList(String option) {

		DomesinOptionList optionList = new DomesinOptionList();
		List<DomesinOption> ret_options = new ArrayList<>();
		List<String> lines = Arrays.stream(option.split("\\r|\\n")).collect(Collectors.toList());
		// String pattern = "\\[[.+.+\\]]";

		String title = lines.get(0);
		lines.remove(0);

		title = title.replace("[", "");
		title = title.replace("]", "");

		int dan = (title.indexOf("=") > 0) ? 2 : 1; // 2 단 옵션임..{
		optionList.setDan(dan); // 1 단옵션인지 2단옵션인지 체크 ..
		if (dan == 1) {
			String char_1_nm = (title.indexOf("색상") > 0) ? "칼라" : title;
			optionList.setChar_1_nm(char_1_nm);
		} else {
			String[] charnms = title.split("=");
			if (charnms[0].indexOf("색상") > 0) {
				optionList.setChar_1_nm("칼라");
			} else {
				optionList.setChar_1_nm(charnms[0]);
			}
			optionList.setChar_2_nm(charnms[1]);
		}

		for (String line : lines) {
			DomesinOption dto = new DomesinOption();
			List<String> lst_option = Arrays.asList(line.split("="));
			if (dan == 1) { // 1 단옵션일경우..

				dto.setOptionName1(lst_option.get(0)); // 옵셥명..
				dto.setOptionName2("");
				dto.setOptionPrice(YDMAStringUtil.convertToInt(lst_option.get(2))); // 옵션가격
				dto.setCompliancePrice(YDMAStringUtil.convertToInt(lst_option.get(3))); // 준수가
				dto.setRetailPrice(YDMAStringUtil.convertToInt(lst_option.get(4))); // 소비자가
				dto.setSoldOut(YDMAStringUtil.convertToInt(lst_option.get(5)));
			} else {
				dto.setOptionName1(lst_option.get(0)); // 옵셥명..
				dto.setOptionName2(lst_option.get(1));
				dto.setOptionPrice(YDMAStringUtil.convertToInt(lst_option.get(2))); // 옵션가격
				dto.setCompliancePrice(YDMAStringUtil.convertToInt(lst_option.get(3))); // 준수가
				dto.setRetailPrice(YDMAStringUtil.convertToInt(lst_option.get(4))); // 소비자가
				dto.setSoldOut(YDMAStringUtil.convertToInt(lst_option.get(5)));
			}

			ret_options.add(dto);
		}

		if (ret_options.size() > 0) {
			if (dan == 2) {
				String char_2_val = ret_options.stream().map(p -> p.getOptionName2()).collect(Collectors.joining(","));
				optionList.setChar_2_val(char_2_val);
			}
		}

		optionList.setOptions(ret_options);

		return optionList;
	}

	public Map<String, ProductResult> getProductQuery(Map<String, String> parameters) throws Exception {

		int page = 0;

		Map<String, ProductResult> resultProdList = new HashMap<>();

		while (true) {
			String path = "/API/COUPANG2/item_list.php";

			String response = null;
			int totalCount = 0;

			parameters.put("page", String.valueOf(++page)); // 첫번째 페이지만 조회
			parameters.put("m_id", UserId); // 사용자 ID
			parameters.put("api_key", UserApiKey); // API Key
			String postForm = ConvertDictionaryToParameter(parameters);

			System.out.println(postForm);
			response = HttpClientEx.get().addParam("ContentType", "application/x-www-form-urlencoded")
					.Post(DATA_HOST.concat(path), postForm);
			ProductResult productDomesin = getProductMapping(response); // 현재 페이지와 total 페이지가 같은지 체크한다.
			if (productDomesin.total_count.equals("0")) {
				break;
			}
			if (productDomesin.code.equals("0000")) {
				resultProdList.put(productDomesin.current_page, productDomesin);
			}
			if (productDomesin.current_page.equals("1")) {
				YDMAProgressBar.get().start("도매의신 상품정보 수집", YDMAStringUtil.convertToInt(productDomesin.total_page), 100,
						true);
			}

			String message = String.format("Total Page: {%s} 현재페이지 {%s}  가져오기완료 ", productDomesin.total_page,
					productDomesin.current_page);

			YDMAProgressBar.get().setValue(message + " ID 주문 정보 수집을 시작합니다.",
					YDMAStringUtil.convertToInt(productDomesin.current_page));

			if (productDomesin.current_page.equals(productDomesin.total_page))
				break;

		}
		return resultProdList;

	}

	/*
	 * 상품 정보를 맵핑한다.
	 */
	public ProductResult getProductMapping(String response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		ProductResult productDomesin = null;

		productDomesin = mapper.readValue(response, ProductResult.class);

		// productDomesin.items = getExistNotProductList(productDomesin.items);

//		for(ProductItem item : items) {
//			String  class_cd2 = this.category.stream()
//							.filter(p->p.cid.equals(item.getCid()))
//							.map(p->p.parent_cid)
//							.findAny().orElse("");
//			
//			String class_cd1 = this.category.stream()
//					.filter(p->p.cid.equals(class_cd2))
//					.map(p->p.parent_cid)
//					.findAny().orElse("");
//			
//			String class_cd3 = item.getCid();
//			
//			String class_cd4 = "";
//			
//			item.setClass_cd1(class_cd1);
//			item.setClass_cd2(class_cd1);
//			item.setClass_cd3(class_cd1);
//			item.setClass_cd4(class_cd1);
//		}
//		

		// sendProductListToMlinkShop(items);

		return productDomesin;
	}

	/*
	 * 현재 상품코드가 등록 되어 있는지 확인 한다.
	 */
	public List<ProductItem> getExistNotProductList(List<ProductItem> items) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductItem> retItems = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT 1  FROM shopprodinfo where compno = ? and compayny_goods_cd=? ";
			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			for (ProductItem list : items) {
				int count = 0;
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.getIcode());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}

				if (count == 0) {
					retItems.add(list);
				}
			}
			return retItems;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

	public int[] sendProductListToMlinkShop(List<ProductItem> items) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int[] result = new int[] { 0, 0 };

		List<ProductItem> contents_in = new ArrayList<ProductItem>();
		List<List<String>> contents_up = new ArrayList<List<String>>();

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT 1  FROM shopprodinfo where compno = ? and compayny_goods_cd=? ";
			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);

			for (ProductItem list : items) {
				int count = 0;
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.getIcode());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}

				if (count == 0) {

					// Not Exists
					contents_in.add(list);
				} else { // Exists
					// contents_up.add(list);
				}
			}

			// Insert
			if (contents_in.size() > 0) {
				result[0] = sendProductListToInMlinkShop(contents_in);
				if (result[0] > 0) {
					sendOptProdListToInMlinkShop(contents_in);
				}
			}

			// Update
			if (contents_up.size() > 0) {
				// result[1] = sendProductListToUpMlinkShop(contents_up, isNew, shell);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return result;
	}

	public void sendOptProdListToInMlinkShop(List<ProductItem> contents) throws Exception {

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
					+ " ?,?,?,?,?,?,?, " + " ?,?,?,? " + " )";

			sql2 = sql2.toUpperCase();
			pstmt2 = connection2.prepareStatement(sql2);
			statementlist2.add(pstmt2);

			String sql = "SELECT prodseq  FROM shopprodinfo where compno = ? and compayny_goods_cd=? ";
			sql = sql.toUpperCase();
			pstmt = connection2.prepareStatement(sql);

			for (ProductItem list : contents) {
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

				String[] lines = optionString.split("\\r\\n|\\r|\\n ");
				int i = 0;
				for (String line : lines) {

					if (line.indexOf("]") > 0)
						continue;
					List<String> list_opt = Arrays.asList(line.split("="));

					int rowIdx2 = 0;
					pstmt2.setInt(++rowIdx2, prodseq);
					pstmt2.setInt(++rowIdx2, i);
					pstmt2.setString(++rowIdx2, YDMASessonUtil.getCompnoInfo().getCompno());

					pstmt2.setString(++rowIdx2, list.getIcode());
					pstmt2.setString(++rowIdx2, list_opt.get(1));
					pstmt2.setString(++rowIdx2, list_opt.get(2));
					pstmt2.setString(++rowIdx2, "");
					pstmt2.setString(++rowIdx2, "");
					pstmt2.setString(++rowIdx2, "");
					pstmt2.setString(++rowIdx2, "");
					pstmt2.setString(++rowIdx2, "");
					pstmt2.setString(++rowIdx2, "");
					pstmt2.setString(++rowIdx2, "");

					pstmt2.setString(++rowIdx2, "");
					pstmt2.setString(++rowIdx2, "");
					pstmt2.setString(++rowIdx2, "");
					pstmt2.setString(++rowIdx2, "");
					pstmt2.setString(++rowIdx2, "");
					pstmt2.setString(++rowIdx2, "");
					pstmt2.setString(++rowIdx2, "");

					pstmt2.addBatch();
					pstmt2.clearParameters(); // 파라미터 초기화
					++i;

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

	public int sendProductListToInMlinkShop(List<ProductItem> contents) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

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

			for (ProductItem list : contents) {
				int rowIdx = 0;
				// Node COMPNO = document.createElement("COMPNO");
				pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
				// 추가

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

				pstmt.setString(++rowIdx, list.getClass_cd1());

				pstmt.setString(++rowIdx, list.getClass_cd2());

				pstmt.setString(++rowIdx, list.getClass_cd3());
				pstmt.setString(++rowIdx, list.getClass_cd4());

				// Node GOODS_SEASON = document.createElement("GOODS_SEASON");
				pstmt.setString(++rowIdx, "");

				// Node SEX = document.createElement("SEX");
				pstmt.setString(++rowIdx, "");

				// Node STATUS = document.createElement("STATUS");
				pstmt.setString(++rowIdx, list.getStatus());

				// Node DELIV_ABLE_REGION = document.createElement("DELIV_ABLE_REGION");
				pstmt.setString(++rowIdx, "");

				// Node TAX_YN = document.createElement("TAX_YN");
				pstmt.setString(++rowIdx, (list.getTax().equals("0")) ? "Y" : "N");

				// Node DELV_TYPE = document.createElement("DELV_TYPE");
				pstmt.setString(++rowIdx, "");

				// Node DELV_COST = document.createElement("DELV_COST");
				pstmt.setInt(++rowIdx, 0);

				// Node GOODS_PRICE = document.createElement("GOODS_PRICE");
				pstmt.setInt(++rowIdx, YDMAStringUtil.convertToInt(list.getPrice()));

				// Node GOODS_CONSUMER_PRICE = document.createElement("GOODS_CONSUMER_PRICE");
				pstmt.setInt(++rowIdx, YDMAStringUtil.convertToInt(list.getPrice_consumer()));

				// Node CHAR_1_NM = document.createElement("CHAR_1_NM");

				pstmt.setString(++rowIdx, ""); // 컬러

				// Node CHAR_2_NM = document.createElement("CHAR_2_NM");
				pstmt.setString(++rowIdx, ""); // 사이즈.

				// Node CHAR_1_VAL = document.createElement("CHAR_1_VAL");
				// 옵션상세 명칭 2(사이즈) 결정을 위한 변수
				String char_2_val = "";

				pstmt.setString(++rowIdx, "");
				// Node CHAR_2_VAL = document.createElement("CHAR_2_VAL");
				pstmt.setString(++rowIdx, char_2_val);

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

	public String ConvertDictionaryToParameter(Map<String, String> parameters) {
		List<String> list = new ArrayList<String>();
		for (String key : parameters.keySet()) {
			String value = parameters.get(key);
			list.add(key + "=" + value);
		}

		return list.stream().collect(Collectors.joining("&"));
	}

	public boolean Login(String id, String password) throws Exception {
		boolean retVal = false;

		password = "1q2w3e!@";
		id = "hosikan";
		String path = "/API/COUPANG/login.php";
		String formParams = String.format("id=%s&pw=%s", id, password);
		String response = HttpClientEx.get().addParam("ContentType", "application/x-www-form-urlencoded")
				.Post(LOGIN_HOST.concat(path), formParams);

		if (response != null && response.length() > 0) {
			try {

				ObjectMapper mapper = new ObjectMapper();
				LoginResponse userInfo;

				userInfo = mapper.readValue(response, LoginResponse.class);

				if (userInfo.code.equals("0000")) {
					UserId = userInfo.m_id;
					UserPassword = password;
					UserApiKey = userInfo.api_key;
					retVal = true; // 로그인 성공..formParams
					LoadCategory();
					System.out.println(userInfo.toString());
				} else {
					// Login Fail
					switch (userInfo.code) {
					case "1001":
						FailReason = "로그인하실 수 없습니다. 고객센터에 문의해 주십시오(차단아이디)";
						break;
					case "1002":
						FailReason = "승인대기중 이십니다. 승인후 이용하실 수 있습니다.";
						break;
					case "1003":
						FailReason = "공급사회원은 이용하실 수 없습니다. 셀러회원 신청후 이용하실 수 있습니다.";
						break;
					case "1004":
						FailReason = "기타오류(인증과정에 문제가 발생했습니다. 고객센터로 문의해 주세요)";
						break;
					case "1005":
						FailReason = "판매회원 인증키가 없습니다. 관리자 승인후 이용하실 수 있습니다. 고객센터로 요청해 주세요";
						break;
					case "1006":
						FailReason = "회원아이디 또는 비밀번호를 확인해 주세요";
						break;
					default:
						FailReason = "정의되어 있지 않는 에러입니다. 에러번호: [" + userInfo.code + "], 고객센터로 문의해 주세요";
						break;
					}
					UserId = UserApiKey = null;
					retVal = false;
				}
			} catch (Exception e) {
				retVal = false;
				FailReason = e.getMessage();
			}
		} else {
			retVal = false;
			FailReason = "로그인 서버로부터 응답이 없습니다.";
		}

		return retVal;
	}

	public Map<String, ProductResult> getProductEditQuery(Map<String, String> parameters) throws Exception {
		int page = 0;

		Map<String, ProductResult> resultProdList = new HashMap<>();

		while (true) {
			String path = "/API/COUPANG/item_change_list.php";

			String response = null;
			int totalCount = 0;

			parameters.put("page", String.valueOf(++page)); // 첫번째 페이지만 조회
			parameters.put("m_id", UserId); // 사용자 ID
			parameters.put("api_key", UserApiKey); // API Key
			String postForm = ConvertDictionaryToParameter(parameters);

			response = HttpClientEx.get().addParam("ContentType", "application/x-www-form-urlencoded")
					.Post(DATA_HOST.concat(path), postForm);
			ProductResult productDomesin = getProductMapping(response); // 현재 페이지와 total 페이지가 같은지 체크한다.
			if (productDomesin.code.equals("0000")) {
				resultProdList.put(productDomesin.current_page, productDomesin);
			}
			if (productDomesin.current_page.equals("1")) {
				YDMAProgressBar.get().start("도매의신 상품정보 수집", YDMAStringUtil.convertToInt(productDomesin.total_page), 100,
						true);
			}

			String message = String.format("Total Page: {%s} 현재페이지 {%s}  가져오기완료 ", productDomesin.total_page,
					productDomesin.current_page);

			YDMAProgressBar.get().setValue(message + " ID 주문 정보 수집을 시작합니다.",
					YDMAStringUtil.convertToInt(productDomesin.current_page));

			if (productDomesin.current_page.equals(productDomesin.total_page))
				break;
		}
		return resultProdList;
	}

	private static class OrderDomesinItem {
		String icode;
		String option1;
		String option2;
		Long qty;

		public String getIcode() {
			return icode;
		}

		public void setIcode(String icode) {
			this.icode = icode;
		}

		public String getOption1() {
			return option1;
		}

		public void setOption1(String option1) {
			this.option1 = option1;
		}

		public String getOption2() {
			return option2;
		}

		public void setOption2(String option2) {
			this.option2 = option2;
		}

		public Long getQty() {
			return qty;
		}

		public void setQty(Long qty) {
			this.qty = qty;
		}

	}

	private static class OrderDomesin {
		String api_key;
		String m_id;
		String c_name;
		String c_hp;
		String c_tel;
		String c_zip;
		String c_addr;
		String c_addr_detail;
		String c_ship_memo;
		String c_memo;
		String c_memo2;
		List<OrderDomesinItem> items = new ArrayList<>();

		public String getApi_key() {
			return api_key;
		}

		public void setApi_key(String api_key) {
			this.api_key = api_key;
		}

		public String getM_id() {
			return m_id;
		}

		public void setM_id(String m_id) {
			this.m_id = m_id;
		}

		public String getC_name() {
			return c_name;
		}

		public void setC_name(String c_name) {
			this.c_name = c_name;
		}

		public String getC_hp() {
			return c_hp;
		}

		public void setC_hp(String c_hp) {
			this.c_hp = c_hp;
		}

		public String getC_tel() {
			return c_tel;
		}

		public void setC_tel(String c_tel) {
			this.c_tel = c_tel;
		}

		public String getC_zip() {
			return c_zip;
		}

		public void setC_zip(String c_zip) {
			this.c_zip = c_zip;
		}

		public String getC_addr() {
			return c_addr;
		}

		public void setC_addr(String c_addr) {
			this.c_addr = c_addr;
		}

		public String getC_addr_detail() {
			return c_addr_detail;
		}

		public void setC_addr_detail(String c_addr_detail) {
			this.c_addr_detail = c_addr_detail;
		}

		public String getC_ship_memo() {
			return c_ship_memo;
		}

		public void setC_ship_memo(String c_ship_memo) {
			this.c_ship_memo = c_ship_memo;
		}

		public String getC_memo() {
			return c_memo;
		}

		public void setC_memo(String c_memo) {
			this.c_memo = c_memo;
		}

		public String getC_memo2() {
			return c_memo2;
		}

		public void setC_memo2(String c_memo2) {
			this.c_memo2 = c_memo2;
		}

		public List<OrderDomesinItem> getItems() {
			return items;
		}

		public void setItems(List<OrderDomesinItem> items) {
			this.items = items;
		}

	}

	private static class OrderDomesinCallback {
		String api_key;
		String m_id;
		String order_code;

		public String getApi_key() {
			return api_key;
		}

		public void setApi_key(String api_key) {
			this.api_key = api_key;
		}

		public String getM_id() {
			return m_id;
		}

		public void setM_id(String m_id) {
			this.m_id = m_id;
		}

		public String getOrder_code() {
			return order_code;
		}

		public void setOrder_code(String order_code) {
			this.order_code = order_code;
		}
	}

	private static class OrderDomesinCallbackIcode {
		String api_key;
		String m_id;
		String icode;

		public String getApi_key() {
			return api_key;
		}

		public void setApi_key(String api_key) {
			this.api_key = api_key;
		}

		public String getM_id() {
			return m_id;
		}

		public void setM_id(String m_id) {
			this.m_id = m_id;
		}

		public String getIcode() {
			return icode;
		}

		public void setIcode(String icode) {
			this.icode = icode;
		}

	}

	public List<ShopOrderMstDto> OrderListComplate(List<ShopOrderMstDto> list) throws Exception {
		int page = 1;
		List<ShopOrderMstDto> retList = new ArrayList<>();
		Map<String, List<ShopOrderMstDto>> mapList = list.stream()
				.collect(Collectors.groupingBy(ShopOrderMstDto::getReceive_addr));
		
		List<OrderDomesin> datas = new ArrayList<>();
		for (String key : mapList.keySet()) {
			List<ShopOrderMstDto> orderList = mapList.get(key);		
			OrderDomesin data = new OrderDomesin();
			data.api_key = DomesinSessonUtil.get().getApiKey(); // 사용자 ID
			data.m_id = DomesinSessonUtil.get().getUserId();
			data.c_name = list.get(0).getReceive_name();
			String phone = list.get(0).getReceive_cel().equals("") ? list.get(0).getReceive_tel()
					: list.get(0).getReceive_cel();
			String phone2 = list.get(0).getReceive_tel().equals("") ? list.get(0).getReceive_cel()
					: list.get(0).getReceive_tel();
			data.c_hp = phone;
			data.c_tel = phone2;
			data.c_zip = list.get(0).getReceive_zipcode() == null ? "" : list.get(0).getReceive_zipcode();
			data.c_addr = list.get(0).getReceive_addr();
			data.c_addr_detail = list.get(0).getReceive_detail() == null ? "." : list.get(0).getReceive_detail();
			data.c_ship_memo = list.get(0).getDelv_msg() == null ? "" : list.get(0).getDelv_msg();
			data.c_memo = list.get(0).getOrder_id() == null ? "" : list.get(0).getOrder_id();
			data.c_memo2 = "";
			for (ShopOrderMstDto dto : orderList) {
				OrderDomesinItem item = new OrderDomesinItem();

				item.icode = dto.getCompayny_goods_cd();
				item.option1 = dto.getP_sku_value().substring(dto.getP_sku_value().indexOf(":") + 1,
						dto.getP_sku_value().length());
				item.option2 = "";
				item.qty = Long.parseLong(dto.getSale_cnt());
				data.items.add(item);
			}
			datas.add(data);
		}
		for(OrderDomesin data : datas) {
			String path = "/API/v11/order.php";
			
			String response = null;
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);

			String json = mapper.writeValueAsString(data);

			response = getApiList(LOGIN_HOST.concat(path), json);
			JsonParser jsonparser = new JsonParser();
			JsonObject object = (JsonObject) jsonparser.parse(response);
			List<ShopOrderMstDto> choice = list.stream().filter(d->d.getReceive_addr().equals(data.getC_addr())&&d.getReceive_name().equals(data.getC_name())).collect(Collectors.toList());
			
			if (object.get("code").equals("0000")) {
				for(ShopOrderMstDto dto : choice) {
					dto.setDosinOrder_code(object.get("order_code").getAsString());// 주문코드
					dto.setOrder_status(OrderStatus.도신신규);
					dto.setResult_code(OrderStatus.정상처리);
					YDMAProgressBar.get()
					.setValue(String.format("수취인명 : %s, 주문번호 : %s, 상품코드 : %s, 수량 : %s, 전송내용 : %s",
							dto.getReceive_name(), dto.getDosinOrder_code(), dto.getCompayny_goods_cd(),
							dto.getSale_cnt(), object.get("message").getAsString()), page);
				}
			
			} else {				
				for(ShopOrderMstDto dto : choice) {
					YDMAProgressBar.get()
					.setValue(
							String.format("수취인명 : %s, 상품코드 : %s, 수량 : %s, 전송내용 : %s", dto.getReceive_name(),
									dto.getCompayny_goods_cd(), dto.getSale_cnt(), object.get("message").getAsString()),
							page);
					dto.setResult_code(OrderStatus.에러발생);
				}
				
			}
			retList.addAll(choice);
		}
		

		return retList;

	}

	public List<ShopOrderMstDto> OrderListComplateCallBack(List<ShopOrderMstDto> list) throws Exception {
		int page = 1;
		//YDMAProgressBar.get().setValue("주문정보 가져오기를 시작합니다", 0);
		for (ShopOrderMstDto dto : list) {
			String path = "/API/v11/order_status.php";

			OrderDomesinCallback data = new OrderDomesinCallback();
			data.api_key = DomesinSessonUtil.get().getApiKey();
			data.m_id = DomesinSessonUtil.get().getUserId();
			data.order_code = dto.getDosinOrder_code();

			String response = null;
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);

			String json = mapper.writeValueAsString(data);

			response = getApiList(LOGIN_HOST.concat(path), json);
			JsonParser jsonparser = new JsonParser();
			JsonObject object = (JsonObject) jsonparser.parse(response);
			if (object.get("code").equals("0000")) {
//				dto.setDosinOrder_code(object.get("order_code").getAsString());// 주문코드
//				object.get("item_amount").getAsString();//상품합계금액
//				object.get("delivery_amount").getAsString();//도서산간일경우추가배송비를포함
				JsonArray jsonarray = new JsonArray();
				jsonarray = (JsonArray) object.get("order_list_detail");
				for (int i = 0; i < jsonarray.size(); i++) {
					JsonObject obj = (JsonObject) jsonarray.get(i);
					String status = obj.get("status").getAsString();
					dto.setDs_order_status(getStatus(status));
//					dto.setOrder_status(status.equals("신규주문") ? OrderStatus.도신신규
//							: status.equals("배송준비중") ? OrderStatus.도신배송
//									: status.equals("발송완료") ? OrderStatus.송장입력 : OrderStatus.도신보류);// 신규|배송준비|발송완료|취소완료
					obj.get("del_corp").getAsString();// 배송업체명
					dto.setInvoice_no(obj.get("del_no") == null ? "" : obj.get("del_no").getAsString());// 배송번호
					dto.setResult_code(OrderStatus.정상처리);
				}
				YDMAProgressBar.get()
						.setValue(String.format("수취인명 : %s, 주문번호 : %s, 택배업체 : %s, 송장번호 : %s, 전송내용 : %s",
								dto.getReceive_name(), dto.getDosinOrder_code(), dto.getDelivery_id(),
								dto.getInvoice_no(), object.get("message").getAsString()), page);
			} else {
				YDMAProgressBar.get().setValue(String.format("수취인명 : %s, 전송내용 : %s", dto.getReceive_name(),
						object.get("message").getAsString()), page);
			}

			page++;

		}

		return list;

	}

	private String getStatus(String status) {
		String value = "";
		switch(status) {
			case "":
			break;
			default : break;
		}
		return value;
	}

	public List<ShopOrderMstDto> ProductList(List<ShopOrderMstDto> list) throws Exception {
		int page = 1;
		for (ShopOrderMstDto dto : list) {
			String path = "/API/v11/order.php";
			System.out.println();

			OrderDomesinCallbackIcode data = new OrderDomesinCallbackIcode();
			data.api_key = DomesinSessonUtil.get().getApiKey(); // 사용자 ID
			data.m_id = DomesinSessonUtil.get().getUserId();
			data.icode = dto.getCompayny_goods_cd();

			String response = null;
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);

			String json = mapper.writeValueAsString(data);

			response = getApiList(LOGIN_HOST.concat(path), json);
			JsonParser jsonparser = new JsonParser();
			JsonObject object = (JsonObject) jsonparser.parse(response);
			if (object.get("code").equals("0000")) {
				dto.setDosinOrder_code(object.get("order_code").getAsString());// 주문코드
				dto.setOrder_status(OrderStatus.도신신규);
				dto.setResult_code(OrderStatus.정상처리);
//				object.get("item_amount").getAsString();//상품합계금액
//				object.get("delivery_amount").getAsString();//도서산간일경우추가배송비를포함
//				JsonArray jsonarray = new JsonArray();
//				jsonarray = (JsonArray) object.get("order_list_detail");
//				for(int i = 0; i<jsonarray.size();i++) {
//					JsonObject obj = (JsonObject) jsonarray.get(i);
//					obj.get("order_list_code").getAsString();//주문서개별원장번호로 상품주문상태조회
//					obj.get("icode").getAsString();//택배출력상품
//					obj.get("iname").getAsString();//도매의신카테고리
//					obj.get("qty").getAsString();//수량
//					obj.get("item_amount").getAsString();//단가
//					obj.get("is_island").getAsString();//도서산간 0이면해당없음 ,1이면도서산간추가배송비포함
//				}	
				YDMAProgressBar.get()
						.setValue(String.format("수취인명 : %s, 주문번호 : %s, 상품코드 : %s, 수량 : %s, 전송내용 : %s",
								dto.getReceive_name(), dto.getDosinOrder_code(), dto.getCompayny_goods_cd(),
								dto.getSale_cnt(), object.get("message").getAsString()), page);
			} else {
				YDMAProgressBar.get()
						.setValue(String.format("수취인명 : %s, 상품코드 : %s, 수량 : %s, 전송내용 : %s", dto.getReceive_name(),
								dto.getCompayny_goods_cd(), dto.getSale_cnt(), object.get("message").getAsString()),
								page);
				dto.setResult_code(OrderStatus.에러발생);
			}

			page++;

		}
		return list;
	}

	/*
	 * 두개를 가져온다.
	 */
	private String getApiList(String host, String json) throws Exception {
		String result = null;
		List<ShopOrderMstDto> ret_list = new ArrayList<>();
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;

		try {

			URIBuilder uriBuilder = new URIBuilder().setPath(host);

			/************** URL 빌드 를 셋팅한다. ********************/

			StringEntity params = new StringEntity(json, "UTF-8"); // 파라미터.

			client = HttpClients.createDefault();
			HttpPost requestPatch = new HttpPost(uriBuilder.build().toString());
			requestPatch.addHeader("content-type", "application/json");
			requestPatch.setEntity(params);
			response = client.execute(requestPatch);

			int status = response.getStatusLine().getStatusCode();
			String message = response.getStatusLine().getReasonPhrase();
			if (status == HttpStatus.SC_OK) {
				InputStream is = response.getEntity().getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
				JsonParser parser = new JsonParser();
				JsonObject resObject = (JsonObject) parser.parse(br);
				result = resObject.toString();

			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					throw e;
				}
			}

			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return result;
	}

	ChromeDriver driver;

	public ShopOrderMstDto InvoiceSearch(List<ShopOrderMstDto> list) {
		List<ShopOrderMstDto> delivery = new ArrayList<>();
		ShopOrderMstDto dto = new ShopOrderMstDto();
		YDMAProgressBar.get().start("송정번호조회중", 100, 10, true);
		YDMAProgressBar.get().setValue("송장조회를 시작합니다.", 100);
		driver = ChromeExtention.getInstace().getDriver();
//		http://kdexp.com/basicNewDelivery.kd?barcode=
//		https://cargo.koreanair.com/ko/tracking?awbNO=
//		https://www.ilogen.com/m/personal/trace.pop/
//		http://www.epantos.com/jsp/gx/tracking/tracking/trackingInquery.jsp?refNo=
//		http://service.epost.go.kr/trace.RetrieveRegiPrclDeliv.postal?sid1=
//		http://www.ilyanglogis.com/functionality/tracking_result.asp?hawb_no=
//		http://www.chunil.co.kr/HTrace/HTrace.jsp?transNo=
//		http://www.hanips.com/html/sub03_03_1.html?logicnum=
//		http://www.hanjin.co.kr/Delivery_html/inquiry/result_waybill.jsp?wbl_num=
//		https://www.lotteglogis.com/home/reservation/tracking/linkView?InvNo=
//		http://honamlogis.co.kr/page/?pid=tracking_number&SLIP_BARCD=
//		https://www.doortodoor.co.kr/parcel/doortodoor.do?fsp_action=PARC_ACT_002&fsp_cmd=retrieveInvNoACT&invc_no=
//		http://www.cvsnet.co.kr/reservation-inquiry/delivery/index.do?dlvry_type=domestic&srch_type=01&invoice_no=
//		http://www.dhl.co.kr/content/kr/ko/express/tracking.shtml?brand=DHL&AWB=
//		http://service.epost.go.kr/trace.RetrieveEmsRigiTraceList.comm?POST_CODE=
//		http://www.fedex.com/Tracking?ascend_header=1&clienttype=dotcomreg&cntry_code=kr&language=korean&tracknumbers=
//		http://www.tnt.com/webtracker/tracking.do?respCountry=kr&respLang=ko&searchType=CON&cons=
//		http://wwwapps.ups.com/WebTracking/track?track=yes&loc=ko_kr&trackNums=
//		http://www.gsiexpress.com/track_pop.php?track_type=ship_num&query_num= 
//		http://slx.co.kr/delivery/delivery_number.php?param1=
//		https://www.cupost.co.kr/postbox/delivery/localResult.cupost?invoice_no= 
//		https://delivery.sebang.com/sdelivery/guest/trace/trace.xhtml?DISPATCH_NOTE_NO=
//		http://www.realsystem.co.kr/wooritb/search/s_search.asp?f_slipno=
//		http://tracking.zoommaok.com/zoomma/tracking.php?invoice=
//		http://btob.e-handex.co.kr/work/app/tm/tmtr01/tmtr01_s4.jsp?IC_INV_NO=
		String URL = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%EC%86%A1%EC%9E%A5%EB%B2%88%ED%98%B8%EC%A1%B0%ED%9A%8C";
		driver.get(URL);

		Map<String, String> cookies = ChromeScript.get().getCookies(driver);

		org.jsoup.Connection.Response rs;
		try {
			rs = Jsoup.connect(
					"https://m.search.naver.com/p/csearch/ocontent/util/headerjson.nhn?_callback=window.__jindo2_callback._306&callapi=parceltracking&t_code=04&t_invoice=111111111111111&passportKey=QlqbAzK6P6uUT%2FL8neG%2Fr%2FyP1sO5S33Gvcf8ZeNoLqY%3D")
					.cookies(cookies)
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36")
					.data("_callback", "window.__jindo2_callback._306").data("callapi", "parceltracking")
					.data("t_code", list.get(0).getNaverexpcode()).data("t_invoice", list.get(0).getInvoice_no())
					.data("passportKey", "QlqbAzK6P6uUT/L8neG/r/yP1sO5S33Gvcf8ZeNoLqY=")
					.referrer(
							" https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%86%A1%EC%9E%A5%EB%B2%88%ED%98%B8%EC%A1%B0%ED%9A%8C&oquery=%EC%86%A1%EC%9E%A5%EB%B2%88%ED%98%B8%EC%A1%B0%ED%9A%8C&tqi="
									+ cookies.get("page_uid") + "")
					.method(org.jsoup.Connection.Method.GET).ignoreContentType(true).followRedirects(true).execute();

			String body = rs.body();
			body = body.substring(body.indexOf("{"), body.lastIndexOf(")"));
			JsonParser jsonparser = new JsonParser();
			JsonObject jsonobject = (JsonObject) jsonparser.parse(body);
			if (jsonobject.get("message") == null) {

			} else {
				JsonObject message = (JsonObject) jsonobject.get("message");
				String error = message.get("error") == null ? "" : message.get("error").getAsString();
				YDMAProgressBar.get().setValue(String.format("고객명 : %s, 택배업체 : %s, 송장번호 : %s, 내용 : %s",
						list.get(0).getReceive_name(), list.get(0).getDelvnm(), list.get(0).getInvoice_no(), error),
						100);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dto;
	}

	public Map<String, ProductResult> OrderOneItemSalesNSoldCheck(List<ShopOrderMstDto> list) throws Exception {
		int page = 1;
		Map<String, String> parameters = new HashMap<>();
		Map<String, ProductResult> resultProdList = new HashMap<>();
		parameters.put("m_id", DomesinSessonUtil.get().getUserId()); // 사용자 ID
		parameters.put("api_key", DomesinSessonUtil.get().getApiKey()); // API Key
		String response = null;
		for (ShopOrderMstDto dto : list) {
			String path = "/API/COUPANG2/item_info.php";

			parameters.put("icode", dto.getCompayny_goods_cd()); // 첫번째 페이지만 조회
			String postForm = ConvertDictionaryToParameter(parameters);

			response = HttpClientEx.get().addParam("Content-Type", "application/x-www-form-urlencoded")
					.Post(DATA_HOST.concat(path), postForm);

			ProductResult productDomesin = getProductMapping(response); // 현재 페이지와 total 페이지가 같은지 체크한다.
			if (productDomesin.code.equals("0000")) {
				resultProdList.put(String.valueOf(page), productDomesin);
			}

			page++;
		}
		return resultProdList;
	}

}

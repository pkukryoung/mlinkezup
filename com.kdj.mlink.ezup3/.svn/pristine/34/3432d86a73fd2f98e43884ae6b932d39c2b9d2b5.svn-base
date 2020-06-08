package com.kdj.mlink.ezup3.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdj.mlink.ezup3.shop.domesin.DomesinMarginDto;
import com.kdj.mlink.ezup3.shop.domesin.DomesinOption;
import com.kdj.mlink.ezup3.shop.domesin.DomesinSessonUtil;
import com.kdj.mlink.ezup3.shop.domesin.MapperShopCateDto;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;

/*
 *  상품 정보를 액세스 한다. 
 */
public class ShopProductDao {
	
	  @JsonIgnoreProperties(ignoreUnknown = true)
	    private static class Gosi{
	        @JsonProperty("gosi1")
	        private String gosi1;
	        @JsonProperty("gosi2")
	        private String gosi2;
	        @JsonProperty("gosi3")
	        private String gosi3;
	        @JsonProperty("gosi4")
	        private String gosi4;
	        @JsonProperty("gosi5")
	        private String gosi5;
	        @JsonProperty("gosi6")
	        private String gosi6;
	        @JsonProperty("gosi7")
	        private String gosi7;
	        @JsonProperty("gosi8")
	        private String gosi8;
	        @JsonProperty("gosi9")
	        private String gosi9;
	        @JsonProperty("gosi10")
	        private String gosi10;
	        @JsonProperty("gosi11")
	        private String gosi11;
	        @JsonProperty("gosi12")
	        private String gosi12;
	        @JsonProperty("gosi13")
	        private String gosi13;
	        @JsonProperty("gosi14")
	        private String gosi14;
	        @JsonProperty("gosi15")
	        private String gosi15;
	        @JsonProperty("gosi16")
	        private String gosi16;
	        @JsonProperty("gosi17")
	        private String gosi17;
	        @JsonProperty("gosi18")
	        private String gosi18;
	        @JsonProperty("gosi19")
	        private String gosi19;
	        @JsonProperty("gosi20")
	        private String gosi20;
	        @JsonProperty("gosi21")
	        private String gosi21;
	        @JsonProperty("gosi22")
	        private String gosi22;

	      
	    }
	
	
	private static ShopProductDao daoInstance = new ShopProductDao(); // dao 싱글톤으로 생성 한다.

	Map<String, String> mapAddrList = new HashMap<>();

	private ShopProductDao() {
		mapAddrList.put("shop0003", "shopaddr11stdtl");
		mapAddrList.put("shop0004", "shopaddrinterparkdtl");
		mapAddrList.put("shop0075", "shopaddrcoupangdtl");
		mapAddrList.put("shop0110", "shopaddrcafe24dtl");
		mapAddrList.put("shop0055", "shopaddrnaverstoredtl");
		mapAddrList.put("shop0067", "shopaddrauctiondtl");
		mapAddrList.put("shop0068", "shopaddrauctiondtl");
	}

	public static ShopProductDao get() {
		return daoInstance;
	}

	
	
	
	
	public List<ShopProductDto> getShopProductDomesinList(String shopcd, int option, String prodFrom, String prodTo,
			String lagcateg, String midcateg, String smlcateg, String detcateg, String purch, String logis, int supply,
			String orderIvtr, String shopfee, String sex, String season, String prod, String searchgubun,
			String searchtxt) throws Exception {

		String compno = YDMASessonUtil.getCompnoInfo().getCompno();
		// opt == {상품코드, 상품명}
		// List<List<String>> contents = new ArrayList<>();
		List<ShopProductDto> list = new ArrayList<ShopProductDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append(" SELECT                                           \r\n ");
			sql.append(" IFNULL( B.send_result,'') AS send_result,         \r\n "); 
			sql.append(" YWM_FUNC_BOSSPRODCD(4,A.icode) AS IMG,  \r\n");
			sql.append(" IFNULL( A.icode, '') AS icode,                     \r\n ");
			sql.append(" IFNULL( A.iname, '') AS iname,                     \r\n ");
			sql.append(" IFNULL( A.cid, '') AS cid,                         \r\n ");
			sql.append(" IFNULL( A.shop_cid_info, '') AS shop_cid_info,     \r\n ");
			sql.append(" IFNULL( A.icountry, '') AS icountry,               \r\n ");
			sql.append(" IFNULL( A.maker, '') AS maker,                     \r\n ");
			sql.append(" IFNULL( A.brand, '') AS brand,                     \r\n ");
			sql.append(" IFNULL( A.tax, '') AS tax,                         \r\n ");
			sql.append(" IFNULL( A.vender_code, '') AS vender_code,         \r\n ");
			sql.append(" IFNULL( A.delivery_type, '') AS delivery_type,     \r\n ");
			sql.append(" IFNULL( A.delivery_amount, '') AS delivery_amount, \r\n ");
			sql.append(" IFNULL( A.delivery_qty, '') AS delivery_qty,       \r\n ");
			sql.append(" IFNULL( A.keyword,  '') AS keyword,                \r\n ");
			sql.append(" IFNULL( A.price,  '') AS price,                    \r\n ");
			sql.append(" IFNULL( A.price_consumer, '') AS price_consumer,   \r\n ");
			sql.append(" IFNULL( A.islimit, '') AS islimit,                 \r\n ");
			sql.append(" IFNULL( A.limit_price, '') AS limit_price,         \r\n ");
			sql.append(" IFNULL( A.content, '') AS content,                 \r\n ");
			sql.append(" IFNULL( A.img, '') AS imgs,                         \r\n ");
			sql.append(" IFNULL( A.select_option, '') AS select_option,     \r\n ");
			sql.append(" IFNULL( A.text_option, '') AS text_option,         \r\n ");
			sql.append(" IFNULL( A.notice, '') AS notice,                   \r\n ");
			sql.append(" IFNULL( A.itype, '') AS itype,                     \r\n ");
			sql.append(" IFNULL( A.`status`, '') AS `status`,               \r\n ");
			sql.append(" IFNULL( A.adult,'') AS adult,                      \r\n ");
			sql.append(" IFNULL(A.reg_datetime, '') AS reg_datetime,        \r\n ");
			sql.append(" IFNULL(A.up_datetime, '') AS up_datetime,          \r\n ");
			sql.append(" IFNULL(A.isreturn, '') AS isreturn,                \r\n ");
			sql.append(" IFNULL(A.cert_type,'') AS cert_type,               \r\n ");
			sql.append(" IFNULL(A.cert, '') AS cert,                        \r\n ");
			sql.append(" IFNULL(A.cert_no, '') AS cert_no,                  \r\n ");
			sql.append(" IFNULL(A.gosi_code,'') AS gosi_code,               \r\n ");
			sql.append(" IFNULL(A.gosi_info,'') AS  gosi_info              \r\n ");
			sql.append(" FROM ds_shopprodinfo  AS A LEFT JOIN             \r\n ");
			sql.append("  ds_shopprodin AS B  \r\n");
			sql.append(" ON A.icode = B.icode \r\n");
	
			if (option == 0) {
				sql.append(" WHERE  A.reg_datetime >= ? AND A.reg_datetime <= ? \r\n");

			} else {
				sql.append(" WHERE  A.up_datetime >= ? AND A.up_datetime <= ? \r\n");
			}

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			
			pstmt.setString(1, prodFrom);
			pstmt.setString(2, prodTo);

			System.out.println("[getShopProductList]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ShopProductDto dto = new ShopProductDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setImg(rs.getString("Img")); // 이미지 추가..
				dto.setCompno("4"); // 타입:int
				
				dto.setCompayny_goods_cd(rs.getString("icode"));
				dto.setGoods_nm(rs.getString("iname")); // 상품명 타입:varchar
				dto.setClass_cd4(rs.getString("cid")); // 세분류코드 타입:varchar
				dto.setOrigin(rs.getString("icountry")); // 원산지(제조국) 타입:varchar
				dto.setMaker(rs.getString("maker")); // 제조사 타입:varchar
				dto.setBrand_nm(rs.getString("brand")); // 브랜드명 타입:varchar
				dto.setTax_yn(rs.getString("tax")); // 세금구분 타입:varchar
				dto.setDelv_type(rs.getString("delivery_type")); // 배송비구분 타입:varchar
				dto.setDelv_cost(rs.getString("delivery_amount")); // 배송비 타입:int
				dto.setGoods_keyword(rs.getString("keyword")); // 상품약어 타입:varchar
				dto.setGoods_price(rs.getString("price")); // 판매가 타입:int
				dto.setGoods_consumer_price(rs.getString("price_consumer")); // TAG가(소비자가) 타입:int
			    dto.setIslimit(rs.getString("islimit"));
				dto.setLimit_price(rs.getString("limit_price")); //가격준수가
				dto.setGoods_remarks(rs.getString("content"));
				dto.setDelivery_qty(rs.getString("delivery_qty"));
				
				List<String> images =  Arrays.asList(rs.getString("imgs").split(","));
				if(images.size() > 0) {
					dto.setImg_path(images.get(0));
					if(images.size() > 1) {
						dto.setImg_path1(images.get(1));
					}
					
					if(images.size() > 2) {
						dto.setImg_path2(images.get(2));
					}
					
					if(images.size() > 3) {
						dto.setImg_path3(images.get(3));
					}
					
					if(images.size() > 4) {
						dto.setImg_path4(images.get(4));
					}
				}
				
				String select_option = rs.getString("select_option");
				dto.setText_option(rs.getString("text_option"));
				dto.setDescrition(rs.getString("notice")); // 관리자메모 타입:varchar
				
				dto.setStatus(rs.getString("status")); // 상품상태 타입:varchar
				
				dto.setItype(rs.getString("itype"));
				dto.setAdult(rs.getString("adult"));
				
				dto.setIsreturn(rs.getString("isreturn"));
				dto.setCert(rs.getString("cert"));
				dto.setCert_type(rs.getString("cert_type"));
				dto.setCertno(rs.getString("cert_no"));
				dto.setProp1_cd(rs.getString("gosi_code"));
				dto.setProp_val1(rs.getString("gosi_info"));
				String shop_cid_info =   rs.getString("shop_cid_info"); 
				 ObjectMapper mapper = new ObjectMapper();
				 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				String strgosi =  rs.getString("gosi_info");
				Gosi   gosi =  mapper.readValue(strgosi, Gosi.class);
				
				dto.setProp_val1(gosi.gosi1);
				dto.setProp_val2(gosi.gosi2);
				dto.setProp_val3(gosi.gosi3);
				dto.setProp_val4(gosi.gosi4);
				dto.setProp_val5(gosi.gosi5);
				dto.setProp_val6(gosi.gosi6);
				dto.setProp_val7(gosi.gosi7);
				dto.setProp_val8(gosi.gosi8);
				dto.setProp_val9(gosi.gosi9);
				dto.setProp_val10(gosi.gosi10);
				dto.setProp_val11(gosi.gosi11);
				dto.setProp_val12(gosi.gosi12);
				dto.setProp_val13(gosi.gosi13);
				dto.setProp_val14(gosi.gosi14);
				dto.setProp_val15(gosi.gosi15);
				dto.setProp_val16(gosi.gosi16);
				dto.setProp_val17(gosi.gosi17);
				dto.setProp_val18(gosi.gosi18);
				dto.setProp_val19(gosi.gosi19);
				dto.setProp_val20(gosi.gosi20);
				dto.setProp_val21(gosi.gosi21);
				dto.setProp_val22(gosi.gosi22);

				
				
				// -----------  쇼핑몰 상품정보 셋팅.. -------------///
		         List<MapperShopCateDto>   mapperShopCateDtos =  mapper.readValue(shop_cid_info, mapper.getTypeFactory().constructCollectionType(List.class, MapperShopCateDto.class));
		         dto.setMapperShopCateDtos(mapperShopCateDtos);
		        
		         
		         // -------------  옵션 정보 -------------------//
		         List<DomesinOption>   domesinOptions =  mapper.readValue(select_option, mapper.getTypeFactory().constructCollectionType(List.class, DomesinOption.class));
		         dto.setDomesinOptions(domesinOptions);
		         
		    
		         
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
	
	
	
	
	
	
	
	
	// 아이디가지고오기 웨어절추가
	public List<List<String>> getShopidListCheck(String shopcd) throws Exception {
		List<List<String>> contents = new ArrayList<List<String>>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select SHOPCD,SHOPPINGID,SHOPSEQ,ifnull(DEALTREAD,'') AS DEALTREAD " + "  from shopdtl "
					+ " where compno = ? " + "   and DEALTREAD = '사용중' " + "   and shopcd = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);

			System.out.println("[getShopidListCheck]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				int columnIndex = 0;
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

	public ShopProductAdditionDto getAdditionaOneList(int seq) throws Exception {
		ShopProduct11stAdditionDto dto = new ShopProduct11stAdditionDto();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT IFNULL(SEQ,''),IFNULL(COMPNO,''),IFNULL(SHOPCD,''),IFNULL(TITLE,''),IFNULL(MEMO,''),IFNULL(SHOPID,''),IFNULL(SELMTHDCD,''),"
					+ " IFNULL(PRODTYPCD,''),IFNULL(MALL_VAR_1,''),IFNULL(MALL_VAR_2,''),IFNULL(MALL_VAR_3,''),IFNULL(MALL_VAR_4,''),IFNULL(MALL_VAR_5,''),IFNULL(MALL_VAR_6,''),"
					+ " IFNULL(MALL_VAR_7,''),IFNULL(MALL_VAR_8,''),IFNULL(MALL_VAR_9,''),IFNULL(PRODSTATCD,''),IFNULL(MINORSELCNYN,''), IFNULL(MALL_VAR_10,''),"
					+ " IFNULL(MALL_VAR_11,''),IFNULL(NICKNM,''), IFNULL(MALL_VAR_12,''),IFNULL(MALL_VAR_13,''),IFNULL(MALL_VAR_94,'0'),IFNULL(MALL_VAR_14,''), "
					+ " IFNULL(MALL_VAR_15,''),IFNULL(MALL_VAR_16,''), ifnull(FPSELTERMYN,''), ifnull(PRODFPSELCD,''), ifnull(MALL_VAR_17,''), ifnull(MALL_VAR_18,''), "
					+ " IFNULL(MALL_VAR_19,''),IFNULL(MALL_VAR_20,''),IFNULL(MALL_VAR_21,''),IFNULL(MALL_VAR_22,''),IFNULL(MALL_VAR_23,''),IFNULL(MALL_VAR_24,''), "
					+ " IFNULL(MALL_VAR_25,''), IFNULL(MALL_VAR_26,''),IFNULL(MALL_VAR_27,''),IFNULL(MALL_VAR_28,''),IFNULL(MALL_VAR_29,''),IFNULL(MALL_VAR_30,''), "
					+ " IFNULL(MALL_VAR_31,''),IFNULL(MALL_VAR_32,''), IFNULL(MALL_VAR_33,''),IFNULL(MALL_VAR_34,''),IFNULL(MALL_VAR_35,''),IFNULL(MALL_VAR_36,''), "
					+ " IFNULL(MALL_VAR_37,''), IFNULL(MALL_VAR_38,''),IFNULL(MALL_VAR_39,''),IFNULL(MALL_VAR_40,''), IFNULL(MALL_VAR_41,'0'),IFNULL(MALL_VAR_42,''), "
					+ " IFNULL(MALL_VAR_43,'0'),IFNULL(MALL_VAR_44,'0'), IFNULL(MALL_VAR_45,'0'),IFNULL(MALL_VAR_46,''), ifnull(MALL_VAR_47,''), "
					+ " ifnull(MALL_VAR_49,''), ifnull(MALL_VAR_50,''),IFNULL(MALL_VAR_51,''),IFNULL(MALL_VAR_52,''),IFNULL(EXPWYCD,''),IFNULL(MALL_VAR_95,''),IFNULL(SENDEXP,''),"
					+ " IFNULL(MALL_VAR_53,''),IFNULL(MALL_VAR_54,''), IFNULL(GBLDIVYN,''),IFNULL(GBLWGHT,''),IFNULL(HSCD,''),IFNULL(MALL_VAR_55,''),IFNULL(ADDROUT,''), "
					+ " IFNULL(MALL_VAR_56,''),IFNULL(ADDRIN,''), IFNULL(SHIPPRC,'0'),IFNULL(CONDIPRICE,'0'),IFNULL(MALL_VAR_57,'0'),IFNULL(MALL_VAR_58,'0'),IFNULL(DIFFPRICE1,'0'),"
					+ " IFNULL(MALL_VAR_59,'0'),IFNULL(JEJUPRC,'0'),IFNULL(ISLANDPRC,'0'), IFNULL(PRCTYPCD,''),IFNULL(BNDYN,''),IFNULL(RETPRC,'0'),IFNULL(EXCPRC,'0'), "
					+ " IFNULL(RUDCD,''),IFNULL(ASDTL,''), ifnull(RTEXCDTL,''), ifnull(MALL_VAR_60,''), ifnull(MALL_VAR_61,''), ifnull(MALL_VAR_62,'0'),IFNULL(MALL_VAR_63,'0'),"
					+ " IFNULL(MALL_VAR_64,''),IFNULL(MALL_VAR_65,''),IFNULL(MALL_VAR_66,''),IFNULL(MALL_VAR_67,''),IFNULL(MALL_VAR_68,''),IFNULL(MALL_VAR_69,''),"
					+ " IFNULL(MALL_VAR_70,''),IFNULL(MALL_VAR_71,''),IFNULL(MALL_VAR_72,''),IFNULL(MALL_VAR_73,''),IFNULL(MALL_VAR_74,''),IFNULL(MALL_VAR_75,'0'),"
					+ " IFNULL(MALL_VAR_76,''), IFNULL(MALL_VAR_77,''),IFNULL(MALL_VAR_78,''),IFNULL(MALL_VAR_79,''),IFNULL(MALL_VAR_80,''),IFNULL(MALL_VAR_81,'0'), "
					+ " IFNULL(MALL_VAR_82,''),IFNULL(MALL_VAR_83,'0'),IFNULL(MALL_VAR_84,''), IFNULL(MALL_VAR_85,''),IFNULL(MALL_VAR_86,'0'),IFNULL(MALL_VAR_87,'0'),"
					+ " IFNULL(MALL_VAR_88,''), IFNULL(MALL_VAR_89,''),IFNULL(MALL_VAR_90,''), ifnull(MALL_VAR_91,''), ifnull(MALL_VAR_92,''), ifnull(MALL_VAR_93,''), ifnull(USEYN,'') "
					+ "  from shopaddr11stdtl " + " where  seq=? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, seq);
			System.out.println("[getShopMstOneSelectAllList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				dto.setSeq(rs.getString(++columnIndex)); // 일련번호
				dto.setCompno(rs.getString(++columnIndex)); // 회사코드
				dto.setShopcd(rs.getString(++columnIndex)); // 쇼핑몰코드
				dto.setTitle(rs.getString(++columnIndex)); // 제목
				dto.setMemo(rs.getString(++columnIndex)); // 메모
				dto.setShopid(rs.getString(++columnIndex)); // 아이디
				dto.setSelmthdcd(rs.getString(++columnIndex)); // 판매방식
				dto.setProdtypcd(rs.getString(++columnIndex)); // 서비스상품
				dto.setMall_var_1(rs.getString(++columnIndex)); // 상품홍보문구
				dto.setMall_var_2(rs.getString(++columnIndex)); // 원산지물품
				dto.setMall_var_3(rs.getString(++columnIndex)); // 원산지사용여부
				dto.setMall_var_4(rs.getString(++columnIndex)); // 원산지사용여부에따른내용
				dto.setMall_var_5(rs.getString(++columnIndex)); // 원산지다중등록여부
				dto.setMall_var_6(rs.getString(++columnIndex)); // 상품모델
				dto.setMall_var_7(rs.getString(++columnIndex)); // 상품수정모델
				dto.setMall_var_8(rs.getString(++columnIndex)); // 판매자상품코드
				dto.setMall_var_9(rs.getString(++columnIndex)); // 셀러캐시사용여부
				dto.setProdstatcd(rs.getString(++columnIndex)); // 상품상태
				dto.setMinorselcnyn(rs.getString(++columnIndex)); // 미성년자구매여부
				dto.setMall_var_10(rs.getString(++columnIndex)); // 지점선택
				dto.setMall_var_11(rs.getString(++columnIndex)); // 디즈니시리즈선택
				dto.setNicknm(rs.getString(++columnIndex)); // 닉네임
				dto.setMall_var_12(rs.getString(++columnIndex)); // 브랜드
				dto.setMall_var_13(rs.getString(++columnIndex)); // 가입신청URL
				dto.setMall_var_94(rs.getString(++columnIndex)); // 휴대폰약정여부
				dto.setMall_var_14(rs.getString(++columnIndex)); // 휴대폰요금제
				dto.setMall_var_15(rs.getString(++columnIndex)); // 유효일자
				dto.setMall_var_16(rs.getString(++columnIndex)); // 가격비교등록여부
				dto.setFpseltermyn(rs.getString(++columnIndex)); // 판매기간설정여부
				dto.setProdfpselcd(rs.getString(++columnIndex)); // 판매기간코드
				dto.setMall_var_17(rs.getString(++columnIndex)); // 1원폰상품여부
				dto.setMall_var_18(rs.getString(++columnIndex)); // 옵션설정여부
				dto.setMall_var_19(rs.getString(++columnIndex)); // 2단옵션적용여부
				dto.setMall_var_20(rs.getString(++columnIndex)); // 옵션값노출방식
				dto.setMall_var_21(rs.getString(++columnIndex)); // 작성형옵션여부
				dto.setMall_var_22(rs.getString(++columnIndex)); // 옵션명1
				dto.setMall_var_23(rs.getString(++columnIndex)); // 옵션명2
				dto.setMall_var_24(rs.getString(++columnIndex)); // 옵션명3
				dto.setMall_var_25(rs.getString(++columnIndex)); // 옵션명4
				dto.setMall_var_26(rs.getString(++columnIndex)); // 옵션명5
				dto.setMall_var_27(rs.getString(++columnIndex)); // 옵션명6
				dto.setMall_var_28(rs.getString(++columnIndex)); // 옵션명7
				dto.setMall_var_29(rs.getString(++columnIndex)); // 옵션명8
				dto.setMall_var_30(rs.getString(++columnIndex)); // 옵션명9
				dto.setMall_var_31(rs.getString(++columnIndex)); // 옵션명10
				dto.setMall_var_32(rs.getString(++columnIndex)); // 제휴가상품URL
				dto.setMall_var_33(rs.getString(++columnIndex)); // 최초출발일
				dto.setMall_var_34(rs.getString(++columnIndex)); // 마지막출발일
				dto.setMall_var_35(rs.getString(++columnIndex)); // 베네피아할인여부
				dto.setMall_var_36(rs.getString(++columnIndex)); // 베네피아할인금액및율
				dto.setMall_var_37(rs.getString(++columnIndex)); // 베네피아기간설정여부
				dto.setMall_var_38(rs.getString(++columnIndex)); // 베네피아시작일
				dto.setMall_var_39(rs.getString(++columnIndex)); // 베네피아종료일
				dto.setMall_var_40(rs.getString(++columnIndex)); // 최소구매수량제한여부
				dto.setMall_var_41(rs.getString(++columnIndex)); // 최소구매제한시수량
				dto.setMall_var_42(rs.getString(++columnIndex)); // 최대구매수량제한여부
				dto.setMall_var_43(rs.getString(++columnIndex)); // 1회제한시수량
				dto.setMall_var_44(rs.getString(++columnIndex)); // 기간제한시기간
				dto.setMall_var_45(rs.getString(++columnIndex)); // 기간제한시수량
				dto.setMall_var_46(rs.getString(++columnIndex)); // 사은품명
				dto.setMall_var_47(rs.getString(++columnIndex)); // 사은품기간시작일
				dto.setMall_var_49(rs.getString(++columnIndex)); // 사은품기간종료일
				dto.setMall_var_50(rs.getString(++columnIndex)); // 사은품정보
				dto.setMall_var_51(rs.getString(++columnIndex)); // 배송정보템플릿
				dto.setMall_var_52(rs.getString(++columnIndex)); // 배송가능지역
				dto.setExpwycd(rs.getString(++columnIndex)); // 배송방법
				dto.setMall_var_95(rs.getString(++columnIndex)); // 방문수령
				dto.setSendexp(rs.getString(++columnIndex)); // 발송택배사
				dto.setMall_var_53(rs.getString(++columnIndex)); // 발송예정일
				dto.setMall_var_54(rs.getString(++columnIndex)); // 방문수령주소
				dto.setGbldivyn(rs.getString(++columnIndex)); // 전세계배송여부
				dto.setGblwght(rs.getString(++columnIndex)); // 전세계배송무게
				dto.setHscd(rs.getString(++columnIndex)); // HS코드
				dto.setMall_var_55(rs.getString(++columnIndex)); // 출고지주소해외여부
				dto.setAddrout(rs.getString(++columnIndex)); // 출고주소
				dto.setMall_var_56(rs.getString(++columnIndex)); // 교환반품주소해외여부
				dto.setAddrin(rs.getString(++columnIndex)); // 교환/반품주소
				dto.setShiptypcd(rs.getString(++columnIndex)); // 배송비조건
				dto.setShipprc(rs.getString(++columnIndex)); // 기본배송비
				dto.setCondiprice(rs.getString(++columnIndex)); // 상품조건별시 뒤에금액
				dto.setMall_var_57(rs.getString(++columnIndex)); // 수량차등별수량1
				dto.setMall_var_58(rs.getString(++columnIndex)); // 수량차등별수량2
				dto.setDiffprice1(rs.getString(++columnIndex)); // 수량차등별금액1
				dto.setMall_var_59(rs.getString(++columnIndex)); // 수량차등별금액2
				dto.setJejuprc(rs.getString(++columnIndex)); // 제주배송비
				dto.setIslandprc(rs.getString(++columnIndex)); // 도서산간배송비
				dto.setPrctypcd(rs.getString(++columnIndex)); // 선결제여부
				dto.setBndyn(rs.getString(++columnIndex)); // 묶음배송여부
				dto.setRetprc(rs.getString(++columnIndex)); // 반품배송비
				dto.setExcprc(rs.getString(++columnIndex)); // 교환배송비
				dto.setRudcd(rs.getString(++columnIndex)); // 왕복편도여부
				dto.setAsdtl(rs.getString(++columnIndex)); // A/S안내
				dto.setRtexcdtl(rs.getString(++columnIndex)); // 교환/반품안내
				dto.setMall_var_60(rs.getString(++columnIndex)); // 복수구매할인여부
				dto.setMall_var_61(rs.getString(++columnIndex)); // 복수할인기준
				dto.setMall_var_62(rs.getString(++columnIndex)); // 복수할인금액및갯수1
				dto.setMall_var_63(rs.getString(++columnIndex)); // 복수할인금액및갯수2
				dto.setMall_var_64(rs.getString(++columnIndex)); // 복수할인방법기준
				dto.setMall_var_65(rs.getString(++columnIndex)); // 구입처선택
				dto.setMall_var_66(rs.getString(++columnIndex)); // 대표이미지
				dto.setMall_var_67(rs.getString(++columnIndex)); // 모바일상세설명이미지선택
				dto.setMall_var_68(rs.getString(++columnIndex)); // 모바일쿠폰선택여부
				dto.setMall_var_69(rs.getString(++columnIndex)); // 해외구매대행상품여부
				dto.setMall_var_70(rs.getString(++columnIndex)); // 상품명앞추가문구
				dto.setMall_var_71(rs.getString(++columnIndex)); // 상품명뒷추가문구
				dto.setMall_var_72(rs.getString(++columnIndex)); // 상품설명상단추가문구
				dto.setMall_var_73(rs.getString(++columnIndex)); // 상품설명하단추가문구
				dto.setMall_var_74(rs.getString(++columnIndex)); // 기본즉시할인여부
				dto.setMall_var_75(rs.getString(++columnIndex)); // 기본즉시할인금액
				dto.setMall_var_76(rs.getString(++columnIndex)); // 기본즉시할인기준
				dto.setMall_var_77(rs.getString(++columnIndex)); // 쿠폰지급기간설정여부
				dto.setMall_var_78(rs.getString(++columnIndex)); // 쿠폰지급기간종료일
				dto.setMall_var_79(rs.getString(++columnIndex)); // 무이자할부제공여부
				dto.setMall_var_80(rs.getString(++columnIndex)); // 무이자할부개월수
				dto.setMall_var_81(rs.getString(++columnIndex)); // 무이자할부회수제한
				dto.setMall_var_82(rs.getString(++columnIndex)); // 희망할인설정여부
				dto.setMall_var_83(rs.getString(++columnIndex)); // 희망후원금액
				dto.setMall_var_84(rs.getString(++columnIndex)); // 희망후원할인기준
				dto.setMall_var_85(rs.getString(++columnIndex)); // SK포인트지급여부
				dto.setMall_var_86(rs.getString(++columnIndex)); // SK포인트금액
				dto.setMall_var_87(rs.getString(++columnIndex)); // SK포인트할인기준
				dto.setMall_var_88(rs.getString(++columnIndex)); // 상품리뷰여부
				dto.setMall_var_89(rs.getString(++columnIndex)); // 플러스광고여부
				dto.setMall_var_90(rs.getString(++columnIndex)); // 플러스UP광고여부
				dto.setMall_var_91(rs.getString(++columnIndex)); // GIF광고여부
				dto.setMall_var_92(rs.getString(++columnIndex)); // 볼드체광고여부
				dto.setMall_var_93(rs.getString(++columnIndex)); // 배경색광고여부
				dto.setUseyn(rs.getString(++columnIndex)); // 사용여부

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	// 거래중인 쇼핑몰만 가지고 오기
	public List<List<String>> getShopDealTrade() throws Exception {
		List<List<String>> contents = new ArrayList<List<String>>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(a.SHOPCD,''),ifnull(b.SHOPNM,''),ifnull(b.CUSTNM,''),ifnull(b.PRODREGIS,''),ifnull(b.PRODMODIFY,''),ifnull(b.PRODSOLDOUT,''),ifnull(b.STOCKSEND,''),ifnull(b.ORDERCOLLECT,''),"
					+ " 	  ifnull(b.INVOICSEND,''),ifnull(b.CLAIMCOLLECT,''),ifnull(b.QUESTION,''),ifnull(b.MARKETCATEG,''),ifnull(a.IDNUM,''),ifnull(a.DEALTRADE,'') "
					+ "  from shopmst a " + "	 join shopinfo b on a.shopcd = b.shopcd " + " where a.compno = ? "
					+ "   and a.DEALTRADE = '거래중' ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getShopMstSelectList]" + pstmt.toString());

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

	public List<List<String>> getShopAddrListCheck(String shopcd, int result, String search, String title, String addr)
			throws Exception {
		List<List<String>> contents = new ArrayList<List<String>>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT IFNULL(b.title,''),IFNULL(b.seq,''),IFNULL(a.insertdt,''),IFNULL(a.updatedt,'') "
					+ String.format(
							" FROM SHOPADDRMST a JOIN %s b ON a.SHOPCD=b.SHOPCD and a.compno = b.compno and a.seq = b.seq ",
							mapAddrList.get(shopcd))
					+ "  WHERE b.shopcd = ? " + "    and b.compno = ? ";
			if (search.equals("1")) {
				sql += " and b.title LIKE ? ";
			} else if (search.equals("2")) {
				sql += " and b.seq LIKE ? ";
			}
			if (result == 0) {
				sql += " order by b.title";
			} else if (result == 1) {
				sql += " order by a.insertdt ";
			} else {
				sql += " order by a.updatedt ";
			}

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, shopcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			if (search.equals("1")) {
				pstmt.setString(3, "%" + title + "%");
			} else if (search.equals("2")) {
				pstmt.setString(3, "%" + addr + "%");
			}

			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				int columnIndex = 0;
				list.add(++rowno + "");
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add("");
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

	public List<List<String>> getShopAddrList(String shopcd) throws Exception {
		List<List<String>> contents = new ArrayList<List<String>>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT IFNULL(b.title,''),IFNULL(b.seq,''),IFNULL(a.insertdt,''),IFNULL(a.updatedt,'') "
					+ "   FROM SHOPADDRMST a " + "	  JOIN shopaddr11stdtl b "
					+ "		ON a.SHOPCD=b.SHOPCD and a.compno = b.compno and a.seq = b.seq " + "  WHERE b.shopcd = ? "
					+ "    and b.compno = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, shopcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				int columnIndex = 0;
				list.add(++rowno + "");
				list.add(rs.getString(++columnIndex));
				list.add(rs.getString(++columnIndex));
				list.add("");
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

//	// 11번가 전송누를시 전부가지고오기
//	public List<List<String>> getShopprodAllList(List<ShopProductDto> list) throws Exception {
//
//		List<List<String>> contents = new ArrayList<List<String>>();
//
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			connection = DBCPInit.getInstance().getConnection();
//
//			String sql = " select ifnull(PRODSEQ,'0'), ifnull(COMPNO,''), ifnull(GOODS_NM,''), ifnull(GOODS_KEYWORD,''), ifnull(MODEL_NM,''), ifnull(MODEL_NO,''), ifnull(BRAND_NM,''), ifnull(COMPAYNY_GOODS_CD,''), ";
//			sql += " ifnull(GOODS_SEARCH,''), ifnull(GOODS_GUBUN,''), ifnull(CLASS_CD1,''), ifnull(CLASS_CD2,''),  ifnull(CLASS_CD3,''), ifnull(CLASS_CD4,''), ifnull(PARTNER_ID,''), ifnull(DPARTNER_ID,''),";// 물류처아이디
//			sql += " ifnull(MAKER,''), ifnull(ORIGIN,''), ifnull(MAKE_YEAR,''), ifnull(MAKE_DM,''), ifnull(GOODS_SEASON,''), ifnull(SEX,''), ifnull(STATUS,''), ifnull(DELIV_ABLE_REGION,''), ifnull(TAX_YN,''),";// 세금구분
//			sql += " ifnull(DELV_TYPE,''), ifnull(DELV_COST,0), ifnull(BANPUM_AREA,''), ifnull(GOODS_COST,0), ifnull(GOODS_PRICE,0), ifnull(GOODS_CONSUMER_PRICE,0), ifnull(CHAR_1_NM,''), ifnull(CHAR_1_VAL,''), ";// 옵션상세명칭1
//			sql += " ifnull(CHAR_2_NM,''), ifnull(CHAR_2_VAL,''), ifnull(IMG_PATH, ''), ifnull(IMG_PATH1,''), ifnull(IMG_PATH2,''), ifnull(IMG_PATH3,''), ifnull(IMG_PATH4,''), ifnull(IMG_PATH5,''), ifnull(IMG_PATH6,''), ";
//			sql += " ifnull(IMG_PATH7,''), ifnull(IMG_PATH8,''), ifnull(IMG_PATH9, ''), ifnull(IMG_PATH10,''), ifnull(IMG_PATH11,''), ifnull(IMG_PATH12,''), ifnull(IMG_PATH13,''), ifnull(IMG_PATH14,''), "
//					+ " ifnull(IMG_PATH15,''),ifnull(IMG_PATH16,''), ifnull(IMG_PATH17,''), ifnull(IMG_PATH18, ''), ifnull(IMG_PATH19,''), ifnull(IMG_PATH20,''), ifnull(IMG_PATH21,''), ifnull(IMG_PATH22,''), "
//					+ " ifnull(IMG_PATH23,''), ifnull(IMG_PATH24,''), replace(ifnull(GOODS_REMARKS,''),\"\"\"\",''''), ifnull(CERTNO, ''), ifnull(AVLST_DM,''), ifnull(AVLED_DM,''), ifnull(ISSUEDATE,''), ifnull(CERTDATE,'')," // 인증일자
//					+ " ifnull(CERT_AGENCY,''), ifnull(CERTFIELD,''), ifnull(MATERIAL,''), ifnull(STOCK_USE_YN, ''), ifnull(OPT_TYPE,''), ifnull(PROP_EDIT_YN,''), ifnull(PROP1_CD,''), ifnull(PROP_VAL1,''), "// 속성값1
//					+ " ifnull(PROP_VAL2,''), ifnull(PROP_VAL3,''), ifnull(PROP_VAL4,''), ifnull(PROP_VAL5, ''), ifnull(PROP_VAL6,''), ifnull(PROP_VAL7,''), ifnull(PROP_VAL8,''), ifnull(PROP_VAL9,''), ifnull(PROP_VAL10,''), "
//					+ " ifnull(PROP_VAL11,''), ifnull(PROP_VAL12,''), ifnull(PROP_VAL13,''), ifnull(PROP_VAL14, ''), ifnull(PROP_VAL15,''), ifnull(PROP_VAL16,''), ifnull(PROP_VAL17,''), ifnull(PROP_VAL18,''), "
//					+ " ifnull(PROP_VAL19,''), ifnull(PROP_VAL20,''), ifnull(PROP_VAL21,''), ifnull(PROP_VAL22, ''), ifnull(PROP_VAL23,''), ifnull(PROP_VAL24,''), ifnull(PROP_VAL25,''), ifnull(PROP_VAL26,''), "
//					+ " ifnull(PROP_VAL27,''), ifnull(PROP_VAL28,''), ifnull(PACK_CODE_STR,''), ifnull(GOODS_NM_EN, ''), ifnull(GOODS_NM_PR,''), ifnull(GOODS_REMARKS2,''), ifnull(GOODS_REMARKS3,''), "// 추가상품상세설명2
//					+ " ifnull(GOODS_REMARKS4,''), ifnull(IMPORTNO,''), ifnull(GOODS_COST2,''), ifnull(ORIGIN2, ''), ifnull(EXPIRE_DM,''), ifnull(SUPPLY_SAVE_YN,''), ifnull(DESCRITION,''),  "// 관리자메모
//					+ " ifnull(shopprodno,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), ifnull(MODIFYDT,''), ifnull(MODIFYID, '')";
//			sql += " FROM shopprodinfo ";
//			sql += " where compno = ? and prodseq = ? ";
//
//			sql = sql.toUpperCase();
//
//			pstmt = connection.prepareStatement(sql);
//			for (int k = 0; k < list.size(); k++) {
//				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
//				pstmt.setString(2, list.get(k).getProdseq());
//
//				rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//					int i = 0;
//					List<String> listarr = new ArrayList<String>();
//					for (int j = 1; j <= 118; j++) {
//						listarr.add(rs.getString(j));
//					}
//
//					contents.add(listarr);
//
//				}
//			}
////				for(List<String> prodlist : list) {
////					
////						
////				System.out.println("[getShopprodAllList]"+pstmt.toString());
////					
////					
////				}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw ex;
//		} finally {
//			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
//		}
//
//		return contents;
//	}

	public List<ShopCatInfDto> getCatInfo(String shopcd) throws Exception {
		String compno = YDMASessonUtil.getCompnoInfo().getCompno();
		List<ShopCatInfDto> list = new ArrayList<ShopCatInfDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "SELECT \r\n" + "   IFNULL(CATEMAP.CODE,'' ) AS CODE,"
					+ "	 IFNULL(CATEINF.COMPNO,'' ) AS COMPNO,  \r\n"
					+ "	 IFNULL(CATEINF.SHOPCD,'' ) AS SHOPCD,  \r\n"
					+ "	 IFNULL(CATEINF.SHOPCATNO,'' ) AS SHOPCATNO,  \r\n"
					+ "	 IFNULL(CATEINF.SHOPCATNM,'' ) AS SHOPCATNM,  \r\n"
					+ "	 IFNULL(CATEINF.SHOPCATSITENM,'' ) AS SHOPCATSITENM,  \r\n"
					+ "	 IFNULL(CATEINF.EBAYCATCD,'' ) AS EBAYCATCD,  \r\n"
					+ "	 IFNULL(CATEINF.SHOPLAGCATCD,'' ) AS SHOPLAGCATCD,  \r\n"
					+ "	 IFNULL(CATEINF.SHOPMIDCATCD,'' ) AS SHOPMIDCATCD,  \r\n"
					+ "	 IFNULL(CATEINF.SHOPSMLCATCD,'' ) AS SHOPSMLCATCD,  \r\n"
					+ "	 IFNULL(CATEINF.SHOPDETCATCD,'' ) AS SHOPDETCATCD,  \r\n"
					+ "	 IFNULL(CATEINF.SERVICEPROD,'' ) AS SERVICEPROD,  \r\n"
					+ "	 IFNULL(CATEINF.USE_YN,'' ) AS USE_YN,  \r\n"
					+ "	 IFNULL(CATEINF.SHOPGENERAL,'' ) AS SHOPGENERAL,  \r\n"
					+ "	 IFNULL(CATEINF.SHOPID,'' ) AS SHOPID,  \r\n"
					+ "	 IFNULL(CATEINF.SHOPCOMMIS,'' ) AS SHOPCOMMIS,  \r\n"
					+ "	 IFNULL(CATEINF.INSERTDT,'' ) AS INSERTDT,  \r\n"
					+ "	 IFNULL(CATEINF.MODIFYDT,'' ) AS MODIFYDT   \r\n" + "	FROM   \r\n" + "	(      \r\n"
					+ String.format(
							"		SELECT COMPNO, CODE, SHOPCD, SHOPCATNO FROM CATEGLGMAP WHERE COMPNO = '%s' AND SHOPCD= '%s' UNION ALL  \r\n",
							compno, shopcd)
					+ String.format(
							"		SELECT COMPNO, CODE, SHOPCD, SHOPCATNO FROM CATEGMDMAP WHERE COMPNO = '%s' AND SHOPCD= '%s' UNION ALL  \r\n",
							compno, shopcd)
					+ String.format(
							"		SELECT COMPNO, CODE, SHOPCD, SHOPCATNO FROM CATEGSMMAP WHERE COMPNO = '%s' AND SHOPCD= '%s' UNION ALL  \r\n",
							compno, shopcd)
					+ String.format(
							"	    SELECT COMPNO, CODE, SHOPCD, SHOPCATNO FROM CATEGDTLMAP WHERE COMPNO = '%s' AND SHOPCD= '%s'           \r\n",
							compno, shopcd)
					+ "	) CATEMAP                                                                                                                                                    \r\n"
					+ " LEFT JOIN SHOPCATINF AS CATEINF       \r\n" + " ON CATEMAP.COMPNO = CATEINF.COMPNO    \r\n"
					+ " AND CATEMAP.SHOPCD = CATEINF.SHOPCD   \r\n" + "AND CATEMAP.SHOPCATNO  = CATEINF.SHOPCATNO \r\n"
					+ "AND CATEINF.COMPNO= ?\r\n" + "WHERE CATEINF.USE_YN = 'Y' ";

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getShopMstSelectList]" + pstmt.toString());
			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				/********* addtinon ************/
				ShopCatInfDto ShopCatInfDto = new ShopCatInfDto();
				ShopCatInfDto.setCode(rs.getString("CODE"));
				ShopCatInfDto.setCompno(rs.getString("COMPNO"));
				ShopCatInfDto.setShopcd(rs.getString("SHOPCD"));
				ShopCatInfDto.setShopcatno(rs.getString("SHOPCATNO"));
				ShopCatInfDto.setShopcatnm(rs.getString("SHOPCATNM"));
				ShopCatInfDto.setShopcatsitenm(rs.getString("SHOPCATSITENM"));

				ShopCatInfDto.setShopebaylagcatcd(rs.getString("EBAYCATCD")); // 대분류옥션..
				ShopCatInfDto.setShopebaymidcatcd(rs.getString("EBAYCATCD")); // 이베이 중분류.
				ShopCatInfDto.setShopebaysmlcatcd(rs.getString("EBAYCATCD")); // 3단계
				ShopCatInfDto.setShopebaydetcatcd(rs.getString("EBAYCATCD")); // 4단계
				ShopCatInfDto.setShopebaytalcatcd(rs.getString("EBAYCATCD"));

				ShopCatInfDto.setShoplagcatcd(rs.getString("SHOPLAGCATCD"));
				ShopCatInfDto.setShopmidcatcd(rs.getString("SHOPMIDCATCD"));
				ShopCatInfDto.setShopsmlcatcd(rs.getString("SHOPSMLCATCD"));
				ShopCatInfDto.setShopdetcatcd(rs.getString("SHOPDETCATCD"));
				ShopCatInfDto.setServiceprod(rs.getString("SERVICEPROD"));
				ShopCatInfDto.setUse_yn(rs.getString("USE_YN"));
				ShopCatInfDto.setShopgeneral(rs.getString("SHOPGENERAL"));
				ShopCatInfDto.setShopid(rs.getString("SHOPID"));
				ShopCatInfDto.setShopcommis(rs.getString("SHOPCOMMIS"));
				ShopCatInfDto.setInsertdt(rs.getString("INSERTDT"));
				ShopCatInfDto.setModifydt(rs.getString("MODIFYDT"));

				list.add(ShopCatInfDto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;

	}

	public ShopProductDto getShopProductIn(int sendseq) throws Exception {

		String compno = YDMASessonUtil.getCompnoInfo().getCompno();
		ShopProductDto dto = new ShopProductDto();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "WITH CATEALL AS(                                                                 \r\n"
					+ "		SELECT                                                                    \r\n"
					+ "		   IFNULL(A.CODE,'')     AS CLASS_CD1 ,                                   \r\n"
					+ "			IFNULL(A.CATEGORY,'') AS CLASS_NM1 ,                                  \r\n"
					+ "			IFNULL(RIGHT(B.CODE,3),'')     AS CLASS_CD2  ,                        \r\n"
					+ "			IFNULL(B.CATEGORY,'') AS CLASS_NM2 ,                                  \r\n"
					+ "			IFNULL(RIGHT(C.CODE,3),'')     AS CLASS_CD3,                          \r\n"
					+ "			IFNULL(C.CATEGORY,'') AS CLASS_NM3,                                   \r\n"
					+ "			IFNULL(RIGHT(D.CODE,3),'')     AS CLASS_CD4 ,                         \r\n"
					+ "			IFNULL(D.CATEGORY,'') AS CLASS_NM4                                    \r\n"
					+ "		FROM CATEGLARGE A left JOIN CATEGMIDIUM B                                      \r\n"
					+ "			ON A.COMPNO=B.COMPNO AND A.CODE = B.LRGCODE left JOIN CATEGSMALL C         \r\n"
					+ "			ON A.COMPNO = C.COMPNO AND B.CODE = C.MIDCODE left JOIN CATEGDETAIL D ON   \r\n"
					+ " A.COMPNO = D.COMPNO AND C.CODE = D.SMLCODE WHERE A.COMPNO = '3'         \r\n"
					+ "		 )                                                                        \r\n"
					+ " SELECT SHOPIN.*  , \r\n" + " IFNULL(CATE.CLASS_NM1, '')  CLASS_NM1, \r\n"
					+ " IFNULL(CATE.CLASS_NM2, '')  CLASS_NM2, \r\n" + " IFNULL(CATE.CLASS_NM3, '')  CLASS_NM3, \r\n"
					+ " IFNULL(CATE.CLASS_NM4, '')  CLASS_NM4, \r\n" + "(CASE                                   \r\n"
					+ "	     WHEN GOODS_GUBUN = '1'         \r\n" + "	     THEN '위탁상품'                \r\n"
					+ "	     WHEN GOODS_GUBUN = '2'         \r\n" + "	     THEN '제조상품'                \r\n"
					+ "	     WHEN GOODS_GUBUN = '3'         \r\n" + "	     THEN '사업상품'                \r\n"
					+ "	     WHEN GOODS_GUBUN = '4'         \r\n" + "	     THEN '직영상품'                \r\n"
					+ "	     ELSE ''                        \r\n" + "	  END )AS GOODS_GUBUN_NM,            \r\n"
					+ "(CASE                            \r\n" + "	     WHEN STATUS = '1'       \r\n"
					+ "	     THEN '대기중'           \r\n" + "	     WHEN STATUS = '2'       \r\n"
					+ "	     THEN '공급중'           \r\n" + "	     WHEN STATUS = '3'       \r\n"
					+ "	     THEN '일시중지'         \r\n" + "	     WHEN STATUS = '4'       \r\n"
					+ "	     THEN '완전품절'         \r\n" + "	      WHEN STATUS = '5'      \r\n"
					+ "	     THEN '미사용'           \r\n" + "	      WHEN STATUS = '6'      \r\n"
					+ "	     THEN '삭제'             \r\n" + "	     ELSE ''                 \r\n"
					+ "	  END )AS STATUS_NM,         \r\n" + "(CASE                             \r\n"
					+ "	     WHEN DELV_TYPE = '1'     \r\n" + "	     THEN '무료'              \r\n"
					+ "	     WHEN DELV_TYPE = '2'     \r\n" + "	     THEN '착불'              \r\n"
					+ "	     WHEN DELV_TYPE = '3'     \r\n" + "	     THEN '선결제'            \r\n"
					+ "	     WHEN DELV_TYPE = '4'     \r\n" + "	     THEN '착불/선결제'       \r\n"
					+ "	     ELSE ''                  \r\n" + "	  END )AS DELV_TYPE_NM       \r\n"
					+ " FROM                             \r\n" + "(SELECT                           \r\n"
					+ " CONCAT(IFNULL(A.CLASS_CD1,''), IFNULL(A.CLASS_CD2,''), IFNULL(A.CLASS_CD3,''), IFNULL(A.CLASS_CD4,'')) AS CODE, \r\n"
					+ "(SELECT SHOPNM FROM shopinfo WHERE SHOPCD = A.SHOPCD) AS SHOPNM, \r\n"
					+ "( SELECT SHOPPINGID FROM shopdtl WHERE SHOPCD = A.SHOPCD AND COMPNO = A.COMPNO AND SHOPSEQ = A.SHOPSEQ) AS  SHOPUSERID,"
					+ " YWM_FUNC_BOSSPRODCD(3,A.COMPAYNY_GOODS_CD) AS IMG,\r\n"
					+ " IFNULL(A.SENDSEQ,'' ) AS SENDSEQ,\r\n" + " IFNULL(A.COMPNO,'' ) AS COMPNO,\r\n"
					+ " IFNULL(A.SHOPCD,'' ) AS SHOPCD,\r\n" + " IFNULL(A.SHOPSEQ,'' ) AS SHOPSEQ,\r\n"
					+ " IFNULL(A.PRODSEQ,'' ) AS PRODSEQ,\r\n" + " IFNULL(A.SHOPPRODNO,'' ) AS SHOPPRODNO,\r\n"
					+ " IFNULL(A.SEQ,'' ) AS SEQ,\r\n" + " IFNULL(A.SHOPCATNO,'' ) AS SHOPCATNO,\r\n"
					+ " IFNULL(A.GOODS_NM,'' ) AS GOODS_NM,\r\n" + " IFNULL(A.GOODS_KEYWORD,'' ) AS GOODS_KEYWORD,\r\n"
					+ " IFNULL(A.MODEL_NM,'' ) AS MODEL_NM,\r\n" + " IFNULL(A.MODEL_NO,'' ) AS MODEL_NO,\r\n"
					+ " IFNULL(A.BRAND_NM,'' ) AS BRAND_NM,\r\n"
					+ " IFNULL(A.COMPAYNY_GOODS_CD,'' ) AS COMPAYNY_GOODS_CD,\r\n"
					+ " IFNULL(A.GOODS_SEARCH,'' ) AS GOODS_SEARCH,\r\n"
					+ " IFNULL(A.GOODS_GUBUN,'' ) AS GOODS_GUBUN,\r\n" + " IFNULL(A.CLASS_CD1,'' ) AS CLASS_CD1,\r\n"
					+ " IFNULL(A.CLASS_CD2,'' ) AS CLASS_CD2,\r\n" + " IFNULL(A.CLASS_CD3,'' ) AS CLASS_CD3,\r\n"
					+ " IFNULL(A.CLASS_CD4,'' ) AS CLASS_CD4,\r\n" + " IFNULL(A.PARTNER_ID,'' ) AS PARTNER_ID,\r\n"
					+ " IFNULL(A.DPARTNER_ID,'' ) AS DPARTNER_ID,\r\n" + " IFNULL(A.MAKER,'' ) AS MAKER,\r\n"
					+ " IFNULL(A.ORIGIN,'' ) AS ORIGIN,\r\n" + " IFNULL(A.MAKE_YEAR,'' ) AS MAKE_YEAR,\r\n"
					+ " IFNULL(A.MAKE_DM,'' ) AS MAKE_DM,\r\n" + " IFNULL(A.GOODS_SEASON,'' ) AS GOODS_SEASON,\r\n"
					+ " IFNULL(A.SEX,'' ) AS SEX,\r\n" + " IFNULL(A.STATUS,'' ) AS STATUS,\r\n"
					+ " IFNULL(A.DELIV_ABLE_REGION,'' ) AS DELIV_ABLE_REGION,\r\n"
					+ " IFNULL(A.TAX_YN,'' ) AS TAX_YN,\r\n" + " IFNULL(A.DELV_TYPE,'' ) AS DELV_TYPE,\r\n"
					+ " IFNULL(A.DELV_COST,'' ) AS DELV_COST,\r\n" + " IFNULL(A.BANPUM_AREA,'' ) AS BANPUM_AREA,\r\n"
					+ " IFNULL(A.GOODS_COST,'' ) AS GOODS_COST,\r\n" + " IFNULL(A.GOODS_PRICE,'' ) AS GOODS_PRICE,\r\n"
					+ " IFNULL(A.GOODS_CONSUMER_PRICE,'' ) AS GOODS_CONSUMER_PRICE,\r\n"
					+ " IFNULL(A.CHAR_1_NM,'' ) AS CHAR_1_NM,\r\n" + " IFNULL(A.CHAR_1_VAL,'' ) AS CHAR_1_VAL,\r\n"
					+ " IFNULL(A.CHAR_2_NM,'' ) AS CHAR_2_NM,\r\n" + " IFNULL(A.CHAR_2_VAL,'' ) AS CHAR_2_VAL,\r\n"
					+ " IFNULL(A.IMG_PATH,'' ) AS IMG_PATH,\r\n" + " IFNULL(A.IMG_PATH1,'' ) AS IMG_PATH1,\r\n"
					+ " IFNULL(A.IMG_PATH2,'' ) AS IMG_PATH2,\r\n" + " IFNULL(A.IMG_PATH3,'' ) AS IMG_PATH3,\r\n"
					+ " IFNULL(A.IMG_PATH4,'' ) AS IMG_PATH4,\r\n" + " IFNULL(A.IMG_PATH5,'' ) AS IMG_PATH5,\r\n"
					+ " IFNULL(A.IMG_PATH6,'' ) AS IMG_PATH6,\r\n" + " IFNULL(A.IMG_PATH7,'' ) AS IMG_PATH7,\r\n"
					+ " IFNULL(A.IMG_PATH8,'' ) AS IMG_PATH8,\r\n" + " IFNULL(A.IMG_PATH9,'' ) AS IMG_PATH9,\r\n"
					+ " IFNULL(A.IMG_PATH10,'' ) AS IMG_PATH10,\r\n" + " IFNULL(A.IMG_PATH11,'' ) AS IMG_PATH11,\r\n"
					+ " IFNULL(A.IMG_PATH12,'' ) AS IMG_PATH12,\r\n" + " IFNULL(A.IMG_PATH13,'' ) AS IMG_PATH13,\r\n"
					+ " IFNULL(A.IMG_PATH14,'' ) AS IMG_PATH14,\r\n" + " IFNULL(A.IMG_PATH15,'' ) AS IMG_PATH15,\r\n"
					+ " IFNULL(A.IMG_PATH16,'' ) AS IMG_PATH16,\r\n" + " IFNULL(A.IMG_PATH17,'' ) AS IMG_PATH17,\r\n"
					+ " IFNULL(A.IMG_PATH18,'' ) AS IMG_PATH18,\r\n" + " IFNULL(A.IMG_PATH19,'' ) AS IMG_PATH19,\r\n"
					+ " IFNULL(A.IMG_PATH20,'' ) AS IMG_PATH20,\r\n" + " IFNULL(A.IMG_PATH21,'' ) AS IMG_PATH21,\r\n"
					+ " IFNULL(A.IMG_PATH22,'' ) AS IMG_PATH22,\r\n" + " IFNULL(A.IMG_PATH23,'' ) AS IMG_PATH23,\r\n"
					+ " IFNULL(A.IMG_PATH24,'' ) AS IMG_PATH24,\r\n"
					+ " IFNULL(A.GOODS_REMARKS,'' ) AS GOODS_REMARKS,\r\n" + " IFNULL(A.CERTNO,'' ) AS CERTNO,\r\n"
					+ " IFNULL(A.AVLST_DM,'' ) AS AVLST_DM,\r\n" + " IFNULL(A.AVLED_DM,'' ) AS AVLED_DM,\r\n"
					+ " IFNULL(A.ISSUEDATE,'' ) AS ISSUEDATE,\r\n" + " IFNULL(A.CERTDATE,'' ) AS CERTDATE,\r\n"
					+ " IFNULL(A.CERT_AGENCY,'' ) AS CERT_AGENCY,\r\n" + " IFNULL(A.CERTFIELD,'' ) AS CERTFIELD,\r\n"
					+ " IFNULL(A.MATERIAL,'' ) AS MATERIAL,\r\n" + " IFNULL(A.STOCK_USE_YN,'' ) AS STOCK_USE_YN,\r\n"
					+ " IFNULL(A.OPT_TYPE,'' ) AS OPT_TYPE,\r\n" + " IFNULL(A.PROP_EDIT_YN,'' ) AS PROP_EDIT_YN,\r\n"
					+ " IFNULL(A.PROP1_CD,'' ) AS PROP1_CD,\r\n" + " IFNULL(A.PROP_VAL1,'' ) AS PROP_VAL1,\r\n"
					+ " IFNULL(A.PROP_VAL2,'' ) AS PROP_VAL2,\r\n" + " IFNULL(A.PROP_VAL3,'' ) AS PROP_VAL3,\r\n"
					+ " IFNULL(A.PROP_VAL4,'' ) AS PROP_VAL4,\r\n" + " IFNULL(A.PROP_VAL5,'' ) AS PROP_VAL5,\r\n"
					+ " IFNULL(A.PROP_VAL6,'' ) AS PROP_VAL6,\r\n" + " IFNULL(A.PROP_VAL7,'' ) AS PROP_VAL7,\r\n"
					+ " IFNULL(A.PROP_VAL8,'' ) AS PROP_VAL8,\r\n" + " IFNULL(A.PROP_VAL9,'' ) AS PROP_VAL9,\r\n"
					+ " IFNULL(A.PROP_VAL10,'' ) AS PROP_VAL10,\r\n" + " IFNULL(A.PROP_VAL11,'' ) AS PROP_VAL11,\r\n"
					+ " IFNULL(A.PROP_VAL12,'' ) AS PROP_VAL12,\r\n" + " IFNULL(A.PROP_VAL13,'' ) AS PROP_VAL13,\r\n"
					+ " IFNULL(A.PROP_VAL14,'' ) AS PROP_VAL14,\r\n" + " IFNULL(A.PROP_VAL15,'' ) AS PROP_VAL15,\r\n"
					+ " IFNULL(A.PROP_VAL16,'' ) AS PROP_VAL16,\r\n" + " IFNULL(A.PROP_VAL17,'' ) AS PROP_VAL17,\r\n"
					+ " IFNULL(A.PROP_VAL18,'' ) AS PROP_VAL18,\r\n" + " IFNULL(A.PROP_VAL19,'' ) AS PROP_VAL19,\r\n"
					+ " IFNULL(A.PROP_VAL20,'' ) AS PROP_VAL20,\r\n" + " IFNULL(A.PROP_VAL21,'' ) AS PROP_VAL21,\r\n"
					+ " IFNULL(A.PROP_VAL22,'' ) AS PROP_VAL22,\r\n" + " IFNULL(A.PROP_VAL23,'' ) AS PROP_VAL23,\r\n"
					+ " IFNULL(A.PROP_VAL24,'' ) AS PROP_VAL24,\r\n" + " IFNULL(A.PROP_VAL25,'' ) AS PROP_VAL25,\r\n"
					+ " IFNULL(A.PROP_VAL26,'' ) AS PROP_VAL26,\r\n" + " IFNULL(A.PROP_VAL27,'' ) AS PROP_VAL27,\r\n"
					+ " IFNULL(A.PROP_VAL28,'' ) AS PROP_VAL28,\r\n"
					+ " IFNULL(A.PACK_CODE_STR,'' ) AS PACK_CODE_STR,\r\n"
					+ " IFNULL(A.GOODS_NM_EN,'' ) AS GOODS_NM_EN,\r\n"
					+ " IFNULL(A.GOODS_NM_PR,'' ) AS GOODS_NM_PR,\r\n"
					+ " IFNULL(A.GOODS_REMARKS2,'' ) AS GOODS_REMARKS2,\r\n"
					+ " IFNULL(A.GOODS_REMARKS3,'' ) AS GOODS_REMARKS3,\r\n"
					+ " IFNULL(A.GOODS_REMARKS4,'' ) AS GOODS_REMARKS4,\r\n"
					+ " IFNULL(A.IMPORTNO,'' ) AS IMPORTNO,\r\n" + " IFNULL(A.GOODS_COST2,'' ) AS GOODS_COST2,\r\n"
					+ " IFNULL(A.ORIGIN2,'' ) AS ORIGIN2,\r\n" + " IFNULL(A.EXPIRE_DM,'' ) AS EXPIRE_DM,\r\n"
					+ " IFNULL(A.SUPPLY_SAVE_YN,'' ) AS SUPPLY_SAVE_YN,\r\n"
					+ " IFNULL(A.DESCRITION,'' ) AS DESCRITION,\r\n" + " IFNULL(A.INSERTDT,'' ) AS INSERTDT,\r\n"
					+ " IFNULL(A.INSERTID,'' ) AS INSERTID,\r\n" + " IFNULL(A.MODIFYDT,'' ) AS MODIFYDT,\r\n"
					+ " IFNULL(A.MODIFYID,'' ) AS MODIFYID \r\n" +

					"  FROM SHOPPRODIN AS A \r\n" +
//					"  INNER JOIN shopprodinfo AS B \r\n" + 
//					"  ON A.COMPNO = B.COMPNO AND A.PRODSEQ = B.PRODSEQ\r\n" + 
					" WHERE A.COMPNO = ?  AND A.SENDSEQ = ?  \r\n" + " ) AS SHOPIN  LEFT JOIN CATEALL AS CATE  \r\n"
					+ "		 ON SHOPIN.CLASS_CD1 = CATE.CLASS_CD1  \r\n"
					+ " 		 AND SHOPIN.CLASS_CD2 = CATE.CLASS_CD2 \r\n"
					+ "AND SHOPIN.CLASS_CD3 = CATE.CLASS_CD3   \r\n" + "AND SHOPIN.CLASS_CD4 = CATE.CLASS_CD4  ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setInt(2, sendseq);

			System.out.println("[getShopProductIn]" + pstmt.toString());
			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;

				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setSendstats("연동생성");

				dto.setShopnm(rs.getString("SHOPNM"));
				dto.setShopuserid(rs.getString("SHOPUSERID"));
				dto.setClass_nm1(rs.getString("CLASS_NM1"));
				dto.setClass_nm2(rs.getString("CLASS_NM2"));
				dto.setClass_nm3(rs.getString("CLASS_NM3"));
				dto.setClass_nm4(rs.getString("CLASS_NM4"));

				dto.setGoods_gubun_nm(rs.getString("GOODS_GUBUN_NM"));
				dto.setStatus_nm(rs.getString("STATUS_NM"));
				dto.setDelv_type_nm(rs.getString("DELV_TYPE_NM"));

				dto.setSendseq(rs.getString("SENDSEQ")); //
				dto.setCompno(rs.getString("COMPNO")); //
				dto.setShopcd(rs.getString("SHOPCD")); //
				dto.setShopseq(rs.getString("SHOPSEQ")); //
				dto.setProdseq(rs.getString("PRODSEQ")); //
				dto.setShopprodno(rs.getString("SHOPPRODNO")); //
				dto.setSeq(rs.getString("SEQ")); //
				dto.setShopcatno(rs.getString("SHOPCATNO")); //
				dto.setGoods_nm(rs.getString("GOODS_NM")); //
				dto.setGoods_keyword(rs.getString("GOODS_KEYWORD")); //
				dto.setModel_nm(rs.getString("MODEL_NM")); //
				dto.setModel_no(rs.getString("MODEL_NO")); //
				dto.setBrand_nm(rs.getString("BRAND_NM")); //
				dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD")); //
				dto.setGoods_search(rs.getString("GOODS_SEARCH")); //
				dto.setGoods_gubun(rs.getString("GOODS_GUBUN")); //
				dto.setClass_cd1(rs.getString("CLASS_CD1")); //
				dto.setClass_cd2(rs.getString("CLASS_CD2")); //
				dto.setClass_cd3(rs.getString("CLASS_CD3")); //
				dto.setClass_cd4(rs.getString("CLASS_CD4")); //
				dto.setPartner_id(rs.getString("PARTNER_ID")); //
				dto.setDpartner_id(rs.getString("DPARTNER_ID")); //
				dto.setMaker(rs.getString("MAKER")); //
				dto.setOrigin(rs.getString("ORIGIN")); //
				dto.setMake_year(rs.getString("MAKE_YEAR")); //
				dto.setMake_dm(rs.getString("MAKE_DM")); //
				dto.setGoods_season(rs.getString("GOODS_SEASON")); //
				dto.setSex(rs.getString("SEX")); //
				dto.setStatus(rs.getString("STATUS")); //
				dto.setDeliv_able_region(rs.getString("DELIV_ABLE_REGION")); //
				dto.setTax_yn(rs.getString("TAX_YN")); //
				dto.setDelv_type(rs.getString("DELV_TYPE")); //
				dto.setDelv_cost(rs.getString("DELV_COST")); //
				dto.setBanpum_area(rs.getString("BANPUM_AREA")); //
				dto.setGoods_cost(rs.getString("GOODS_COST")); //
				dto.setGoods_price(rs.getString("GOODS_PRICE")); //
				dto.setGoods_consumer_price(rs.getString("GOODS_CONSUMER_PRICE")); //
				dto.setChar_1_nm(rs.getString("CHAR_1_NM")); //
				dto.setChar_1_val(rs.getString("CHAR_1_VAL")); //
				dto.setChar_2_nm(rs.getString("CHAR_2_NM")); //
				dto.setChar_2_val(rs.getString("CHAR_2_VAL")); //
				dto.setImg_path(rs.getString("IMG_PATH")); //
				dto.setImg_path1(rs.getString("IMG_PATH1")); //
				dto.setImg_path2(rs.getString("IMG_PATH2")); //
				dto.setImg_path3(rs.getString("IMG_PATH3")); //
				dto.setImg_path4(rs.getString("IMG_PATH4")); //
				dto.setImg_path5(rs.getString("IMG_PATH5")); //
				dto.setImg_path6(rs.getString("IMG_PATH6")); //
				dto.setImg_path7(rs.getString("IMG_PATH7")); //
				dto.setImg_path8(rs.getString("IMG_PATH8")); //
				dto.setImg_path9(rs.getString("IMG_PATH9")); //
				dto.setImg_path10(rs.getString("IMG_PATH10")); //
				dto.setImg_path11(rs.getString("IMG_PATH11")); //
				dto.setImg_path12(rs.getString("IMG_PATH12")); //
				dto.setImg_path13(rs.getString("IMG_PATH13")); //
				dto.setImg_path14(rs.getString("IMG_PATH14")); //
				dto.setImg_path15(rs.getString("IMG_PATH15")); //
				dto.setImg_path16(rs.getString("IMG_PATH16")); //
				dto.setImg_path17(rs.getString("IMG_PATH17")); //
				dto.setImg_path18(rs.getString("IMG_PATH18")); //
				dto.setImg_path19(rs.getString("IMG_PATH19")); //
				dto.setImg_path20(rs.getString("IMG_PATH20")); //
				dto.setImg_path21(rs.getString("IMG_PATH21")); //
				dto.setImg_path22(rs.getString("IMG_PATH22")); //
				dto.setImg_path23(rs.getString("IMG_PATH23")); //
				dto.setImg_path24(rs.getString("IMG_PATH24")); //
				dto.setGoods_remarks(rs.getString("GOODS_REMARKS")); //
				dto.setCertno(rs.getString("CERTNO")); //
				dto.setAvlst_dm(rs.getString("AVLST_DM")); //
				dto.setAvled_dm(rs.getString("AVLED_DM")); //
				dto.setIssuedate(rs.getString("ISSUEDATE")); //
				dto.setCertdate(rs.getString("CERTDATE")); //
				dto.setCert_agency(rs.getString("CERT_AGENCY")); //
				dto.setCertfield(rs.getString("CERTFIELD")); //
				dto.setMaterial(rs.getString("MATERIAL")); //
				dto.setStock_use_yn(rs.getString("STOCK_USE_YN")); //
				dto.setOpt_type(rs.getString("OPT_TYPE")); //
				dto.setProp_edit_yn(rs.getString("PROP_EDIT_YN")); //
				dto.setProp1_cd(rs.getString("PROP1_CD")); //
				dto.setProp_val1(rs.getString("PROP_VAL1")); //
				dto.setProp_val2(rs.getString("PROP_VAL2")); //
				dto.setProp_val3(rs.getString("PROP_VAL3")); //
				dto.setProp_val4(rs.getString("PROP_VAL4")); //
				dto.setProp_val5(rs.getString("PROP_VAL5")); //
				dto.setProp_val6(rs.getString("PROP_VAL6")); //
				dto.setProp_val7(rs.getString("PROP_VAL7")); //
				dto.setProp_val8(rs.getString("PROP_VAL8")); //
				dto.setProp_val9(rs.getString("PROP_VAL9")); //
				dto.setProp_val10(rs.getString("PROP_VAL10")); //
				dto.setProp_val11(rs.getString("PROP_VAL11")); //
				dto.setProp_val12(rs.getString("PROP_VAL12")); //
				dto.setProp_val13(rs.getString("PROP_VAL13")); //
				dto.setProp_val14(rs.getString("PROP_VAL14")); //
				dto.setProp_val15(rs.getString("PROP_VAL15")); //
				dto.setProp_val16(rs.getString("PROP_VAL16")); //
				dto.setProp_val17(rs.getString("PROP_VAL17")); //
				dto.setProp_val18(rs.getString("PROP_VAL18")); //
				dto.setProp_val19(rs.getString("PROP_VAL19")); //
				dto.setProp_val20(rs.getString("PROP_VAL20")); //
				dto.setProp_val21(rs.getString("PROP_VAL21")); //
				dto.setProp_val22(rs.getString("PROP_VAL22")); //
				dto.setProp_val23(rs.getString("PROP_VAL23")); //
				dto.setProp_val24(rs.getString("PROP_VAL24")); //
				dto.setProp_val25(rs.getString("PROP_VAL25")); //
				dto.setProp_val26(rs.getString("PROP_VAL26")); //
				dto.setProp_val27(rs.getString("PROP_VAL27")); //
				dto.setProp_val28(rs.getString("PROP_VAL28")); //
				dto.setPack_code_str(rs.getString("PACK_CODE_STR")); //
				dto.setGoods_nm_en(rs.getString("GOODS_NM_EN")); //
				dto.setGoods_nm_pr(rs.getString("GOODS_NM_PR")); //
				dto.setGoods_remarks2(rs.getString("GOODS_REMARKS2")); //
				dto.setGoods_remarks3(rs.getString("GOODS_REMARKS3")); //
				dto.setGoods_remarks4(rs.getString("GOODS_REMARKS4")); //
				dto.setImportno(rs.getString("IMPORTNO")); //
				dto.setGoods_cost2(rs.getString("GOODS_COST2")); //
				dto.setOrigin2(rs.getString("ORIGIN2")); //
				dto.setExpire_dm(rs.getString("EXPIRE_DM")); //
				dto.setSupply_save_yn(rs.getString("SUPPLY_SAVE_YN")); //
				dto.setDescrition(rs.getString("DESCRITION")); //
				dto.setInsertdt(rs.getString("INSERTDT")); //
				dto.setInsertid(rs.getString("INSERTID")); //
				dto.setModifydt(rs.getString("MODIFYDT")); //
				dto.setModifyid(rs.getString("MODIFYID")); //

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	public List<ShopProductDto> getShopProductModifyList(Map<String, String> params) throws Exception {

		String compno = YDMASessonUtil.getCompnoInfo().getCompno();

		List<ShopProductDto> list = new ArrayList<ShopProductDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "WITH CATEALL AS(                                                                 \r\n"
					+ "		SELECT                                                                    \r\n"
					+ "		   IFNULL(A.CODE,'')     AS CLASS_CD1 ,                                   \r\n"
					+ "			IFNULL(A.CATEGORY,'') AS CLASS_NM1 ,                                  \r\n"
					+ "			IFNULL(RIGHT(B.CODE,3),'')     AS CLASS_CD2  ,                        \r\n"
					+ "			IFNULL(B.CATEGORY,'') AS CLASS_NM2 ,                                  \r\n"
					+ "			IFNULL(RIGHT(C.CODE,3),'')     AS CLASS_CD3,                          \r\n"
					+ "			IFNULL(C.CATEGORY,'') AS CLASS_NM3,                                   \r\n"
					+ "			IFNULL(RIGHT(D.CODE,3),'')     AS CLASS_CD4 ,                         \r\n"
					+ "			IFNULL(D.CATEGORY,'') AS CLASS_NM4                                    \r\n"
					+ "		FROM CATEGLARGE A left JOIN CATEGMIDIUM B                                      \r\n"
					+ "			ON A.COMPNO=B.COMPNO AND A.CODE = B.LRGCODE left JOIN CATEGSMALL C         \r\n"
					+ "			ON A.COMPNO = C.COMPNO AND B.CODE = C.MIDCODE left JOIN CATEGDETAIL D ON   \r\n"
					+ " A.COMPNO = D.COMPNO AND C.CODE = D.SMLCODE WHERE A.COMPNO = '3'         \r\n"
					+ "		 )                                                                        \r\n"
					+ " SELECT SHOPIN.*  , \r\n" + " IFNULL(CATE.CLASS_NM1, '')  CLASS_NM1, \r\n"
					+ " IFNULL(CATE.CLASS_NM2, '')  CLASS_NM2, \r\n" + " IFNULL(CATE.CLASS_NM3, '')  CLASS_NM3, \r\n"
					+ " IFNULL(CATE.CLASS_NM4, '')  CLASS_NM4, \r\n" + "(CASE                                   \r\n"
					+ "	     WHEN GOODS_GUBUN = '1'         \r\n" + "	     THEN '위탁상품'                \r\n"
					+ "	     WHEN GOODS_GUBUN = '2'         \r\n" + "	     THEN '제조상품'                \r\n"
					+ "	     WHEN GOODS_GUBUN = '3'         \r\n" + "	     THEN '사업상품'                \r\n"
					+ "	     WHEN GOODS_GUBUN = '4'         \r\n" + "	     THEN '직영상품'                \r\n"
					+ "	     ELSE ''                        \r\n" + "	  END )AS GOODS_GUBUN_NM,            \r\n"
					+ "(CASE                            \r\n" + "	     WHEN STATUS = '1'       \r\n"
					+ "	     THEN '대기중'           \r\n" + "	     WHEN STATUS = '2'       \r\n"
					+ "	     THEN '공급중'           \r\n" + "	     WHEN STATUS = '3'       \r\n"
					+ "	     THEN '일시중지'         \r\n" + "	     WHEN STATUS = '4'       \r\n"
					+ "	     THEN '완전품절'         \r\n" + "	      WHEN STATUS = '5'      \r\n"
					+ "	     THEN '미사용'           \r\n" + "	      WHEN STATUS = '6'      \r\n"
					+ "	     THEN '삭제'             \r\n" + "	     ELSE ''                 \r\n"
					+ "	  END )AS STATUS_NM,         \r\n" + "(CASE                             \r\n"
					+ "	     WHEN DELV_TYPE = '1'     \r\n" + "	     THEN '무료'              \r\n"
					+ "	     WHEN DELV_TYPE = '2'     \r\n" + "	     THEN '착불'              \r\n"
					+ "	     WHEN DELV_TYPE = '3'     \r\n" + "	     THEN '선결제'            \r\n"
					+ "	     WHEN DELV_TYPE = '4'     \r\n" + "	     THEN '착불/선결제'       \r\n"
					+ "	     ELSE ''                  \r\n" + "	  END )AS DELV_TYPE_NM       \r\n"
					+ " FROM                             \r\n" + "(SELECT                           \r\n"
					+ " CONCAT(IFNULL(A.CLASS_CD1,''), IFNULL(A.CLASS_CD2,''), IFNULL(A.CLASS_CD3,''), IFNULL(A.CLASS_CD4,'')) AS CODE, \r\n"
					+ "(SELECT SHOPNM FROM shopinfo WHERE SHOPCD = A.SHOPCD) AS SHOPNM, \r\n"
					+ "( SELECT SHOPPINGID FROM shopdtl WHERE SHOPCD = A.SHOPCD AND COMPNO = A.COMPNO AND SHOPSEQ = A.SHOPSEQ) AS  SHOPUSERID,"
					+ " YWM_FUNC_BOSSPRODCD(3,A.COMPAYNY_GOODS_CD) AS IMG,\r\n"
					+ " IFNULL(A.SENDSEQ,'' ) AS SENDSEQ,\r\n" + " IFNULL(A.COMPNO,'' ) AS COMPNO,\r\n"
					+ " IFNULL(A.SHOPCD,'' ) AS SHOPCD,\r\n" + " IFNULL(A.SHOPSEQ,'' ) AS SHOPSEQ,\r\n"
					+ " IFNULL(A.PRODSEQ,'' ) AS PRODSEQ,\r\n" + " IFNULL(A.SHOPPRODNO,'' ) AS SHOPPRODNO,\r\n"
					+ " IFNULL(A.SEQ,'' ) AS SEQ,\r\n" + " IFNULL(A.SHOPCATNO,'' ) AS SHOPCATNO,\r\n"
					+ " IFNULL(A.MARGINSEQ,'') AS MARGINSEQ, \r\n";

			if (params.get("GOODS_NM").equals("1")) {
				sql = sql + " IFNULL(B.GOODS_NM,'' ) AS GOODS_NM,\r\n";
			} else {
				sql = sql + " IFNULL(A.GOODS_NM,'' ) AS GOODS_NM,\r\n";
			}

			sql = sql + " IFNULL(A.GOODS_KEYWORD,'' ) AS GOODS_KEYWORD,\r\n"
					+ " IFNULL(A.MODEL_NM,'' ) AS MODEL_NM,\r\n" + " IFNULL(A.MODEL_NO,'' ) AS MODEL_NO,\r\n"
					+ " IFNULL(A.BRAND_NM,'' ) AS BRAND_NM,\r\n"
					+ " IFNULL(A.COMPAYNY_GOODS_CD,'' ) AS COMPAYNY_GOODS_CD,\r\n"
					+ " IFNULL(A.GOODS_SEARCH,'' ) AS GOODS_SEARCH,\r\n"
					+ " IFNULL(A.GOODS_GUBUN,'' ) AS GOODS_GUBUN,\r\n" + " IFNULL(A.CLASS_CD1,'' ) AS CLASS_CD1,\r\n"
					+ " IFNULL(A.CLASS_CD2,'' ) AS CLASS_CD2,\r\n" + " IFNULL(A.CLASS_CD3,'' ) AS CLASS_CD3,\r\n"
					+ " IFNULL(A.CLASS_CD4,'' ) AS CLASS_CD4,\r\n" + " IFNULL(A.PARTNER_ID,'' ) AS PARTNER_ID,\r\n"
					+ " IFNULL(A.DPARTNER_ID,'' ) AS DPARTNER_ID,\r\n" + " IFNULL(A.MAKER,'' ) AS MAKER,\r\n"
					+ " IFNULL(A.ORIGIN,'' ) AS ORIGIN,\r\n" + " IFNULL(A.MAKE_YEAR,'' ) AS MAKE_YEAR,\r\n"
					+ " IFNULL(A.MAKE_DM,'' ) AS MAKE_DM,\r\n" + " IFNULL(A.GOODS_SEASON,'' ) AS GOODS_SEASON,\r\n"
					+ " IFNULL(A.SEX,'' ) AS SEX,\r\n" + " IFNULL(A.STATUS,'' ) AS STATUS,\r\n"
					+ " IFNULL(A.DELIV_ABLE_REGION,'' ) AS DELIV_ABLE_REGION,\r\n"
					+ " IFNULL(A.TAX_YN,'' ) AS TAX_YN,\r\n" + " IFNULL(A.DELV_TYPE,'' ) AS DELV_TYPE,\r\n"
					+ " IFNULL(A.DELV_COST,'' ) AS DELV_COST,\r\n" + " IFNULL(A.BANPUM_AREA,'' ) AS BANPUM_AREA,\r\n"
					+ " IFNULL(A.GOODS_COST,'' ) AS GOODS_COST,\r\n";

			if (params.get("GOODS_PRICE").equals("1")) {
				sql = sql + " IFNULL(B.GOODS_PRICE,'' ) AS GOODS_PRICE,\r\n";
			} else {
				sql = sql + " IFNULL(A.GOODS_PRICE,'' ) AS GOODS_PRICE,\r\n";
			}

			sql = sql + " IFNULL(A.GOODS_CONSUMER_PRICE,'' ) AS GOODS_CONSUMER_PRICE,\r\n"
					+ " IFNULL(A.CHAR_1_NM,'' ) AS CHAR_1_NM,\r\n" + " IFNULL(A.CHAR_1_VAL,'' ) AS CHAR_1_VAL,\r\n"
					+ " IFNULL(A.CHAR_2_NM,'' ) AS CHAR_2_NM,\r\n" + " IFNULL(A.CHAR_2_VAL,'' ) AS CHAR_2_VAL,\r\n"
					+ " IFNULL(A.IMG_PATH,'' ) AS IMG_PATH,\r\n" + " IFNULL(A.IMG_PATH1,'' ) AS IMG_PATH1,\r\n"
					+ " IFNULL(A.IMG_PATH2,'' ) AS IMG_PATH2,\r\n" + " IFNULL(A.IMG_PATH3,'' ) AS IMG_PATH3,\r\n"
					+ " IFNULL(A.IMG_PATH4,'' ) AS IMG_PATH4,\r\n" + " IFNULL(A.IMG_PATH5,'' ) AS IMG_PATH5,\r\n"
					+ " IFNULL(A.IMG_PATH6,'' ) AS IMG_PATH6,\r\n" + " IFNULL(A.IMG_PATH7,'' ) AS IMG_PATH7,\r\n"
					+ " IFNULL(A.IMG_PATH8,'' ) AS IMG_PATH8,\r\n" + " IFNULL(A.IMG_PATH9,'' ) AS IMG_PATH9,\r\n"
					+ " IFNULL(A.IMG_PATH10,'' ) AS IMG_PATH10,\r\n" + " IFNULL(A.IMG_PATH11,'' ) AS IMG_PATH11,\r\n"
					+ " IFNULL(A.IMG_PATH12,'' ) AS IMG_PATH12,\r\n" + " IFNULL(A.IMG_PATH13,'' ) AS IMG_PATH13,\r\n"
					+ " IFNULL(A.IMG_PATH14,'' ) AS IMG_PATH14,\r\n" + " IFNULL(A.IMG_PATH15,'' ) AS IMG_PATH15,\r\n"
					+ " IFNULL(A.IMG_PATH16,'' ) AS IMG_PATH16,\r\n" + " IFNULL(A.IMG_PATH17,'' ) AS IMG_PATH17,\r\n"
					+ " IFNULL(A.IMG_PATH18,'' ) AS IMG_PATH18,\r\n" + " IFNULL(A.IMG_PATH19,'' ) AS IMG_PATH19,\r\n"
					+ " IFNULL(A.IMG_PATH20,'' ) AS IMG_PATH20,\r\n" + " IFNULL(A.IMG_PATH21,'' ) AS IMG_PATH21,\r\n"
					+ " IFNULL(A.IMG_PATH22,'' ) AS IMG_PATH22,\r\n" + " IFNULL(A.IMG_PATH23,'' ) AS IMG_PATH23,\r\n"
					+ " IFNULL(A.IMG_PATH24,'' ) AS IMG_PATH24,\r\n"
					+ " IFNULL(A.GOODS_REMARKS,'' ) AS GOODS_REMARKS,\r\n" + " IFNULL(A.CERTNO,'' ) AS CERTNO,\r\n"
					+ " IFNULL(A.AVLST_DM,'' ) AS AVLST_DM,\r\n" + " IFNULL(A.AVLED_DM,'' ) AS AVLED_DM,\r\n"
					+ " IFNULL(A.ISSUEDATE,'' ) AS ISSUEDATE,\r\n" + " IFNULL(A.CERTDATE,'' ) AS CERTDATE,\r\n"
					+ " IFNULL(A.CERT_AGENCY,'' ) AS CERT_AGENCY,\r\n" + " IFNULL(A.CERTFIELD,'' ) AS CERTFIELD,\r\n"
					+ " IFNULL(A.MATERIAL,'' ) AS MATERIAL,\r\n" + " IFNULL(A.STOCK_USE_YN,'' ) AS STOCK_USE_YN,\r\n"
					+ " IFNULL(A.OPT_TYPE,'' ) AS OPT_TYPE,\r\n" + " IFNULL(A.PROP_EDIT_YN,'' ) AS PROP_EDIT_YN,\r\n"
					+ " IFNULL(A.PROP1_CD,'' ) AS PROP1_CD,\r\n" + " IFNULL(A.PROP_VAL1,'' ) AS PROP_VAL1,\r\n"
					+ " IFNULL(A.PROP_VAL2,'' ) AS PROP_VAL2,\r\n" + " IFNULL(A.PROP_VAL3,'' ) AS PROP_VAL3,\r\n"
					+ " IFNULL(A.PROP_VAL4,'' ) AS PROP_VAL4,\r\n" + " IFNULL(A.PROP_VAL5,'' ) AS PROP_VAL5,\r\n"
					+ " IFNULL(A.PROP_VAL6,'' ) AS PROP_VAL6,\r\n" + " IFNULL(A.PROP_VAL7,'' ) AS PROP_VAL7,\r\n"
					+ " IFNULL(A.PROP_VAL8,'' ) AS PROP_VAL8,\r\n" + " IFNULL(A.PROP_VAL9,'' ) AS PROP_VAL9,\r\n"
					+ " IFNULL(A.PROP_VAL10,'' ) AS PROP_VAL10,\r\n" + " IFNULL(A.PROP_VAL11,'' ) AS PROP_VAL11,\r\n"
					+ " IFNULL(A.PROP_VAL12,'' ) AS PROP_VAL12,\r\n" + " IFNULL(A.PROP_VAL13,'' ) AS PROP_VAL13,\r\n"
					+ " IFNULL(A.PROP_VAL14,'' ) AS PROP_VAL14,\r\n" + " IFNULL(A.PROP_VAL15,'' ) AS PROP_VAL15,\r\n"
					+ " IFNULL(A.PROP_VAL16,'' ) AS PROP_VAL16,\r\n" + " IFNULL(A.PROP_VAL17,'' ) AS PROP_VAL17,\r\n"
					+ " IFNULL(A.PROP_VAL18,'' ) AS PROP_VAL18,\r\n" + " IFNULL(A.PROP_VAL19,'' ) AS PROP_VAL19,\r\n"
					+ " IFNULL(A.PROP_VAL20,'' ) AS PROP_VAL20,\r\n" + " IFNULL(A.PROP_VAL21,'' ) AS PROP_VAL21,\r\n"
					+ " IFNULL(A.PROP_VAL22,'' ) AS PROP_VAL22,\r\n" + " IFNULL(A.PROP_VAL23,'' ) AS PROP_VAL23,\r\n"
					+ " IFNULL(A.PROP_VAL24,'' ) AS PROP_VAL24,\r\n" + " IFNULL(A.PROP_VAL25,'' ) AS PROP_VAL25,\r\n"
					+ " IFNULL(A.PROP_VAL26,'' ) AS PROP_VAL26,\r\n" + " IFNULL(A.PROP_VAL27,'' ) AS PROP_VAL27,\r\n"
					+ " IFNULL(A.PROP_VAL28,'' ) AS PROP_VAL28,\r\n"
					+ " IFNULL(A.PACK_CODE_STR,'' ) AS PACK_CODE_STR,\r\n"
					+ " IFNULL(A.GOODS_NM_EN,'' ) AS GOODS_NM_EN,\r\n"
					+ " IFNULL(A.GOODS_NM_PR,'' ) AS GOODS_NM_PR,\r\n"
					+ " IFNULL(A.GOODS_REMARKS2,'' ) AS GOODS_REMARKS2,\r\n"
					+ " IFNULL(A.GOODS_REMARKS3,'' ) AS GOODS_REMARKS3,\r\n"
					+ " IFNULL(A.GOODS_REMARKS4,'' ) AS GOODS_REMARKS4,\r\n"
					+ " IFNULL(A.IMPORTNO,'' ) AS IMPORTNO,\r\n" + " IFNULL(A.GOODS_COST2,'' ) AS GOODS_COST2,\r\n"
					+ " IFNULL(A.ORIGIN2,'' ) AS ORIGIN2,\r\n" + " IFNULL(A.EXPIRE_DM,'' ) AS EXPIRE_DM,\r\n"
					+ " IFNULL(A.SUPPLY_SAVE_YN,'' ) AS SUPPLY_SAVE_YN,\r\n"
					+ " IFNULL(A.DESCRITION,'' ) AS DESCRITION,\r\n" + " IFNULL(A.INSERTDT,'' ) AS INSERTDT,\r\n"
					+ " IFNULL(A.INSERTID,'' ) AS INSERTID,\r\n" + " IFNULL(A.MODIFYDT,'' ) AS MODIFYDT,\r\n"
					+ " IFNULL(A.MODIFYID,'' ) AS MODIFYID \r\n" +

					"  FROM SHOPPRODIN AS A \r\n" + "  INNER JOIN shopprodinfo AS B \r\n"
					+ "  ON A.COMPNO = B.COMPNO AND A.PRODSEQ = B.PRODSEQ\r\n"
					+ " WHERE A.COMPNO = ?  AND A.INSERTDT >= ? AND A.INSERTDT <= ? \r\n";

			if (params.get("SEARCH_GUBUN").isEmpty()) {
			} else if (params.get("SEARCH_GUBUN").equals("PRODSEQ") || params.get("SEARCH_GUBUN").equals("DELV_COST")) {
				sql = sql + String.format(" AND B.%s = %s", params.get("SEARCH_GUBUN"), params.get("SEARCH_TEXT"));
			} else {
				sql = sql + String.format(" AND B.%s LIKE '%s%s%s' ", params.get("SEARCH_GUBUN"), "%",
						params.get("SEARCH_TEXT"), "%");
			}

			sql = sql + " ) AS SHOPIN  LEFT JOIN CATEALL AS CATE  \r\n"
					+ "		 ON SHOPIN.CLASS_CD1 = CATE.CLASS_CD1  \r\n"
					+ " 		 AND SHOPIN.CLASS_CD2 = CATE.CLASS_CD2 \r\n"
					+ "AND SHOPIN.CLASS_CD3 = CATE.CLASS_CD3   \r\n" + "AND SHOPIN.CLASS_CD4 = CATE.CLASS_CD4  \r\n";

			if (!params.get("CLASS_CD4").equals("CLASS_CD4")) {
				sql = sql + String.format(" WHERE CODE = '%s'", params.get("CLASS_CD4"));
			} else if (!params.get("CLASS_CD3").equals("CLASS_CD3")) {
				sql = sql + String.format(" WHERE CODE LIKE '%s%s'", params.get("CLASS_CD3"), "%");
			} else if (!params.get("CLASS_CD2").equals("CLASS_CD2")) {
				sql = sql + String.format(" WHERE CODE LIKE '%s%s'", params.get("CLASS_CD2"), "%");
			} else if (!params.get("CLASS_CD1").equals("CLASS_CD1")) {
				sql = sql + String.format(" WHERE CODE LIKE '%s%s'", params.get("CLASS_CD1"), "%");
			}

			if (!params.get("SHOPCD").equals("SHOPCD")) {
				if (params.get("CLASS_CD1").equals("CLASS_CD1")) {
					sql = sql + String.format(" WHERE SHOPCD = '%s'", params.get("SHOPCD"));
				} else {
					sql = sql + String.format("   AND SHOPCD = '%s'", params.get("SHOPCD"));
				}
				if (!params.get("SHOPUSER_ID").equals("SHOPUSER_ID")) {
					sql = sql + String.format(" AND SHOPSEQ = '%s'", params.get("SHOPUSER_ID"));
				}
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, params.get("DATE_FROM"));
			pstmt.setString(3, params.get("DATE_TO"));

			System.out.println("[getShopMstSelectList]" + pstmt.toString());
			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ShopProductDto dto = new ShopProductDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setSendstats("연동생성");

				dto.setShopnm(rs.getString("SHOPNM"));
				dto.setShopuserid(rs.getString("SHOPUSERID"));
				dto.setClass_nm1(rs.getString("CLASS_NM1"));
				dto.setClass_nm2(rs.getString("CLASS_NM2"));
				dto.setClass_nm3(rs.getString("CLASS_NM3"));
				dto.setClass_nm4(rs.getString("CLASS_NM4"));

				dto.setGoods_gubun_nm(rs.getString("GOODS_GUBUN_NM"));
				dto.setStatus_nm(rs.getString("STATUS_NM"));
				dto.setDelv_type_nm(rs.getString("DELV_TYPE_NM"));

				dto.setSendseq(rs.getString("SENDSEQ")); //
				dto.setCompno(rs.getString("COMPNO")); //
				dto.setShopcd(rs.getString("SHOPCD")); //
				dto.setShopseq(rs.getString("SHOPSEQ")); //
				dto.setProdseq(rs.getString("PRODSEQ")); //
				dto.setShopprodno(rs.getString("SHOPPRODNO")); //
				dto.setSeq(rs.getString("SEQ")); //
				dto.setShopcatno(rs.getString("SHOPCATNO")); //
				dto.setMarginseq(rs.getString("MARGINSEQ")); //
				dto.setGoods_nm(rs.getString("GOODS_NM")); //
				dto.setGoods_keyword(rs.getString("GOODS_KEYWORD")); //
				dto.setModel_nm(rs.getString("MODEL_NM")); //
				dto.setModel_no(rs.getString("MODEL_NO")); //
				dto.setBrand_nm(rs.getString("BRAND_NM")); //
				dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD")); //
				dto.setGoods_search(rs.getString("GOODS_SEARCH")); //
				dto.setGoods_gubun(rs.getString("GOODS_GUBUN")); //
				dto.setClass_cd1(rs.getString("CLASS_CD1")); //
				dto.setClass_cd2(rs.getString("CLASS_CD2")); //
				dto.setClass_cd3(rs.getString("CLASS_CD3")); //
				dto.setClass_cd4(rs.getString("CLASS_CD4")); //
				dto.setPartner_id(rs.getString("PARTNER_ID")); //
				dto.setDpartner_id(rs.getString("DPARTNER_ID")); //
				dto.setMaker(rs.getString("MAKER")); //
				dto.setOrigin(rs.getString("ORIGIN")); //
				dto.setMake_year(rs.getString("MAKE_YEAR")); //
				dto.setMake_dm(rs.getString("MAKE_DM")); //
				dto.setGoods_season(rs.getString("GOODS_SEASON")); //
				dto.setSex(rs.getString("SEX")); //
				dto.setStatus(rs.getString("STATUS")); //
				dto.setDeliv_able_region(rs.getString("DELIV_ABLE_REGION")); //
				dto.setTax_yn(rs.getString("TAX_YN")); //
				dto.setDelv_type(rs.getString("DELV_TYPE")); //
				dto.setDelv_cost(rs.getString("DELV_COST")); //
				dto.setBanpum_area(rs.getString("BANPUM_AREA")); //
				dto.setGoods_cost(rs.getString("GOODS_COST")); //
				dto.setGoods_price(rs.getString("GOODS_PRICE")); //
				dto.setGoods_consumer_price(rs.getString("GOODS_CONSUMER_PRICE")); //
				dto.setChar_1_nm(rs.getString("CHAR_1_NM")); //
				dto.setChar_1_val(rs.getString("CHAR_1_VAL")); //
				dto.setChar_2_nm(rs.getString("CHAR_2_NM")); //
				dto.setChar_2_val(rs.getString("CHAR_2_VAL")); //
				dto.setImg_path(rs.getString("IMG_PATH")); //
				dto.setImg_path1(rs.getString("IMG_PATH1")); //
				dto.setImg_path2(rs.getString("IMG_PATH2")); //
				dto.setImg_path3(rs.getString("IMG_PATH3")); //
				dto.setImg_path4(rs.getString("IMG_PATH4")); //
				dto.setImg_path5(rs.getString("IMG_PATH5")); //
				dto.setImg_path6(rs.getString("IMG_PATH6")); //
				dto.setImg_path7(rs.getString("IMG_PATH7")); //
				dto.setImg_path8(rs.getString("IMG_PATH8")); //
				dto.setImg_path9(rs.getString("IMG_PATH9")); //
				dto.setImg_path10(rs.getString("IMG_PATH10")); //
				dto.setImg_path11(rs.getString("IMG_PATH11")); //
				dto.setImg_path12(rs.getString("IMG_PATH12")); //
				dto.setImg_path13(rs.getString("IMG_PATH13")); //
				dto.setImg_path14(rs.getString("IMG_PATH14")); //
				dto.setImg_path15(rs.getString("IMG_PATH15")); //
				dto.setImg_path16(rs.getString("IMG_PATH16")); //
				dto.setImg_path17(rs.getString("IMG_PATH17")); //
				dto.setImg_path18(rs.getString("IMG_PATH18")); //
				dto.setImg_path19(rs.getString("IMG_PATH19")); //
				dto.setImg_path20(rs.getString("IMG_PATH20")); //
				dto.setImg_path21(rs.getString("IMG_PATH21")); //
				dto.setImg_path22(rs.getString("IMG_PATH22")); //
				dto.setImg_path23(rs.getString("IMG_PATH23")); //
				dto.setImg_path24(rs.getString("IMG_PATH24")); //
				dto.setGoods_remarks(rs.getString("GOODS_REMARKS")); //
				dto.setCertno(rs.getString("CERTNO")); //
				dto.setAvlst_dm(rs.getString("AVLST_DM")); //
				dto.setAvled_dm(rs.getString("AVLED_DM")); //
				dto.setIssuedate(rs.getString("ISSUEDATE")); //
				dto.setCertdate(rs.getString("CERTDATE")); //
				dto.setCert_agency(rs.getString("CERT_AGENCY")); //
				dto.setCertfield(rs.getString("CERTFIELD")); //
				dto.setMaterial(rs.getString("MATERIAL")); //
				dto.setStock_use_yn(rs.getString("STOCK_USE_YN")); //
				dto.setOpt_type(rs.getString("OPT_TYPE")); //
				dto.setProp_edit_yn(rs.getString("PROP_EDIT_YN")); //
				dto.setProp1_cd(rs.getString("PROP1_CD")); //
				dto.setProp_val1(rs.getString("PROP_VAL1")); //
				dto.setProp_val2(rs.getString("PROP_VAL2")); //
				dto.setProp_val3(rs.getString("PROP_VAL3")); //
				dto.setProp_val4(rs.getString("PROP_VAL4")); //
				dto.setProp_val5(rs.getString("PROP_VAL5")); //
				dto.setProp_val6(rs.getString("PROP_VAL6")); //
				dto.setProp_val7(rs.getString("PROP_VAL7")); //
				dto.setProp_val8(rs.getString("PROP_VAL8")); //
				dto.setProp_val9(rs.getString("PROP_VAL9")); //
				dto.setProp_val10(rs.getString("PROP_VAL10")); //
				dto.setProp_val11(rs.getString("PROP_VAL11")); //
				dto.setProp_val12(rs.getString("PROP_VAL12")); //
				dto.setProp_val13(rs.getString("PROP_VAL13")); //
				dto.setProp_val14(rs.getString("PROP_VAL14")); //
				dto.setProp_val15(rs.getString("PROP_VAL15")); //
				dto.setProp_val16(rs.getString("PROP_VAL16")); //
				dto.setProp_val17(rs.getString("PROP_VAL17")); //
				dto.setProp_val18(rs.getString("PROP_VAL18")); //
				dto.setProp_val19(rs.getString("PROP_VAL19")); //
				dto.setProp_val20(rs.getString("PROP_VAL20")); //
				dto.setProp_val21(rs.getString("PROP_VAL21")); //
				dto.setProp_val22(rs.getString("PROP_VAL22")); //
				dto.setProp_val23(rs.getString("PROP_VAL23")); //
				dto.setProp_val24(rs.getString("PROP_VAL24")); //
				dto.setProp_val25(rs.getString("PROP_VAL25")); //
				dto.setProp_val26(rs.getString("PROP_VAL26")); //
				dto.setProp_val27(rs.getString("PROP_VAL27")); //
				dto.setProp_val28(rs.getString("PROP_VAL28")); //
				dto.setPack_code_str(rs.getString("PACK_CODE_STR")); //
				dto.setGoods_nm_en(rs.getString("GOODS_NM_EN")); //
				dto.setGoods_nm_pr(rs.getString("GOODS_NM_PR")); //
				dto.setGoods_remarks2(rs.getString("GOODS_REMARKS2")); //
				dto.setGoods_remarks3(rs.getString("GOODS_REMARKS3")); //
				dto.setGoods_remarks4(rs.getString("GOODS_REMARKS4")); //
				dto.setImportno(rs.getString("IMPORTNO")); //
				dto.setGoods_cost2(rs.getString("GOODS_COST2")); //
				dto.setOrigin2(rs.getString("ORIGIN2")); //
				dto.setExpire_dm(rs.getString("EXPIRE_DM")); //
				dto.setSupply_save_yn(rs.getString("SUPPLY_SAVE_YN")); //
				dto.setDescrition(rs.getString("DESCRITION")); //
				dto.setInsertdt(rs.getString("INSERTDT")); //
				dto.setInsertid(rs.getString("INSERTID")); //
				dto.setModifydt(rs.getString("MODIFYDT")); //
				dto.setModifyid(rs.getString("MODIFYID")); //

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

	public ShopProductDto getShopProductInfo(int prodseq) throws Exception {

		String compno = YDMASessonUtil.getCompnoInfo().getCompno();
		ShopProductDto dto = new ShopProductDto();

		List<ShopProductDto> list = new ArrayList<ShopProductDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append(" WITH CateAll AS(                                                                 \r\n");
			sql.append("		SELECT                                                                    \r\n");
			sql.append("		   ifnull(a.CODE,'')     AS CLASS_CD1 ,                                   \r\n");
			sql.append("			ifnull(a.CATEGORY,'') AS CLASS_NM1 ,                                  \r\n");
			sql.append("			IFNULL(RIGHT(b.CODE,3),'')     AS CLASS_CD2  ,                        \r\n");
			sql.append("			ifnull(b.CATEGORY,'') AS CLASS_NM2 ,                                  \r\n");
			sql.append("			ifnull(RIGHT(c.CODE,3),'')     AS CLASS_CD3,                          \r\n");
			sql.append("			ifnull(C.CATEGORY,'') AS CLASS_NM3,                                   \r\n");
			sql.append("			ifnull(RIGHT(D.CODE,3),'')     AS CLASS_CD4 ,                         \r\n");
			sql.append("			ifnull(D.CATEGORY,'') AS CLASS_NM4                                    \r\n");
			sql.append("		FROM CATEGLARGE a left JOIN CATEGMIDIUM b                                      \r\n");
			sql.append("			ON a.COMPNO=b.COMPNO AND a.CODE = b.LRGCODE left JOIN CATEGSMALL C         \r\n");
			sql.append("			ON a.COMPNO = C.COMPNO AND b.CODE = C.MIDCODE left JOIN CATEGDETAIL D ON   \r\n");
			sql.append(String.format(" a.COMPNO = D.COMPNO AND C.CODE = D.SMLCODE WHERE a.compno = '%s'         \r\n",
					YDMASessonUtil.getCompnoInfo().getCompno()));
			sql.append("		 )                                                                        \r\n");

			sql.append(" SELECT INFO.*  , \r\n");
			sql.append(" IFNULL(CATE.CLASS_NM1, '')  CLASS_NM1, \r\n");
			sql.append(" IFNULL(CATE.CLASS_NM2, '')  CLASS_NM2, \r\n");
			sql.append(" IFNULL(CATE.CLASS_NM3, '')  CLASS_NM3, \r\n");
			sql.append(" IFNULL(CATE.CLASS_NM4, '')  CLASS_NM4, \r\n");

			// ----- 사업상분..
			sql.append("(CASE                                   \r\n");
			sql.append("	     WHEN GOODS_GUBUN = '1'         \r\n");
			sql.append("	     THEN '위탁상품'                \r\n");
			sql.append("	     WHEN GOODS_GUBUN = '2'         \r\n");
			sql.append("	     THEN '제조상품'                \r\n");
			sql.append("	     WHEN GOODS_GUBUN = '3'         \r\n");
			sql.append("	     THEN '사업상품'                \r\n");
			sql.append("	     WHEN GOODS_GUBUN = '4'         \r\n");
			sql.append("	     THEN '직영상품'                \r\n");
			sql.append("	     ELSE ''                        \r\n");
			sql.append("	  END )AS GOODS_GUBUN_NM,            \r\n");

			// ------- 상태값..
			sql.append("(CASE                            \r\n");
			sql.append("	     WHEN STATUS = '1'       \r\n");
			sql.append("	     THEN '대기중'           \r\n");
			sql.append("	     WHEN STATUS = '2'       \r\n");
			sql.append("	     THEN '공급중'           \r\n");
			sql.append("	     WHEN STATUS = '3'       \r\n");
			sql.append("	     THEN '일시중지'         \r\n");
			sql.append("	     WHEN STATUS = '4'       \r\n");
			sql.append("	     THEN '완전품절'         \r\n");
			sql.append("	      WHEN STATUS = '5'      \r\n");
			sql.append("	     THEN '미사용'           \r\n");
			sql.append("	      WHEN STATUS = '6'      \r\n");
			sql.append("	     THEN '삭제'             \r\n");
			sql.append("	     ELSE ''                 \r\n");
			sql.append("	  END )AS STATUS_NM,         \r\n");

			// ----- 택배 상태값..
			sql.append("(CASE                             \r\n");
			sql.append("	     WHEN DELV_TYPE = '1'     \r\n");
			sql.append("	     THEN '무료'              \r\n");
			sql.append("	     WHEN DELV_TYPE = '2'     \r\n");
			sql.append("	     THEN '착불'              \r\n");
			sql.append("	     WHEN DELV_TYPE = '3'     \r\n");
			sql.append("	     THEN '선결제'            \r\n");
			sql.append("	     WHEN DELV_TYPE = '4'     \r\n");
			sql.append("	     THEN '착불/선결제'       \r\n");
			sql.append("	     ELSE ''                  \r\n");
			sql.append("	  END )AS DELV_TYPE_NM       \r\n");

//			sql.append("IFNULL(CATEINF.COMPNO,'' ) AS COMPNO,  \r\n "); // int,
//			sql.append("IFNULL(CATEINF.SHOPCD,'' ) AS SHOPCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPCATNO,'' ) AS SHOPCATNO,  \r\n "); // int,
//			sql.append("IFNULL(CATEINF.SHOPCATNM,'' ) AS SHOPCATNM,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPCATSITENM,'' ) AS SHOPCATSITENM,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPLAGCATCD,'' ) AS SHOPLAGCATCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPMIDCATCD,'' ) AS SHOPMIDCATCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPSMLCATCD,'' ) AS SHOPSMLCATCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPDETCATCD,'' ) AS SHOPDETCATCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SERVICEPROD,'' ) AS SERVICEPROD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.USE_YN,'' ) AS USE_YN,  \r\n "); // char,
//			sql.append("IFNULL(CATEINF.SHOPGENERAL,'' ) AS SHOPGENERAL,  \r\n "); // varchar,01:일반 02:해외배송
//			sql.append("IFNULL(CATEINF.SHOPID,'' ) AS SHOPID,  \r\n "); // varchar,쇼핑몰아이디
//			sql.append("IFNULL(CATEINF.SHOPCOMMIS,'' ) AS SHOPCOMMIS,  \r\n "); // float,쇼핑몰기본수수료율
//			sql.append("IFNULL(CATEINF.INSERTDT,'' ) AS INSERTDT,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.MODIFYDT,'' ) AS MODIFYDT  \r\n "); // varchar,

			sql.append(" FROM                             \r\n");
			sql.append(" (SELECT                           \r\n");
			sql.append(
					" CONCAT(IFNULL(CLASS_CD1,''), IFNULL(CLASS_CD2,''), IFNULL(CLASS_CD3,''), IFNULL(CLASS_CD4,'')) AS CODE, \r\n");
			sql.append("IFNULL(PRODSEQ,'' ) AS PRODSEQ,  \r\n "); // int,품번코드
			sql.append("IFNULL(COMPNO,'' ) AS COMPNO,  \r\n "); // int,
			sql.append(String.format("YWM_FUNC_BOSSPRODCD(%s,COMPAYNY_GOODS_CD) as img,",
					YDMASessonUtil.getCompnoInfo().getCompno()));
			sql.append("IFNULL(GOODS_NM,'' ) AS GOODS_NM,  \r\n "); // varchar,상품명
			sql.append("IFNULL(GOODS_KEYWORD,'' ) AS GOODS_KEYWORD,  \r\n "); // varchar,상품약어
			sql.append("IFNULL(MODEL_NM,'' ) AS MODEL_NM,  \r\n "); // varchar,모델명
			sql.append("IFNULL(MODEL_NO,'' ) AS MODEL_NO,  \r\n "); // varchar,모델No
			sql.append("IFNULL(BRAND_NM,'' ) AS BRAND_NM,  \r\n "); // varchar,브랜드명
			sql.append("IFNULL(COMPAYNY_GOODS_CD,'' ) AS COMPAYNY_GOODS_CD,  \r\n "); // varchar,자체상품코드
			sql.append("IFNULL(GOODS_SEARCH,'' ) AS GOODS_SEARCH,  \r\n "); // varchar,사이트검색어
			sql.append("IFNULL(GOODS_GUBUN,'' ) AS GOODS_GUBUN,  \r\n "); // varchar,상품구분
			sql.append("IFNULL(CLASS_CD1,'' ) AS CLASS_CD1,  \r\n "); // varchar,대분류코드
			sql.append("IFNULL(CLASS_CD2,'' ) AS CLASS_CD2,  \r\n "); // varchar,중분류코드
			sql.append("IFNULL(CLASS_CD3,'' ) AS CLASS_CD3,  \r\n "); // varchar,소분류코드
			sql.append("IFNULL(CLASS_CD4,'' ) AS CLASS_CD4,  \r\n "); // varchar,세분류코드
			sql.append("IFNULL(PARTNER_ID,'' ) AS PARTNER_ID,  \r\n "); // varchar,매입처ID
			sql.append("IFNULL(DPARTNER_ID,'' ) AS DPARTNER_ID,  \r\n "); // varchar,물류처ID
			sql.append("IFNULL(MAKER,'' ) AS MAKER,  \r\n "); // varchar,제조사
			sql.append("IFNULL(ORIGIN,'' ) AS ORIGIN,  \r\n "); // varchar,원산지(제조국)
			sql.append("IFNULL(MAKE_YEAR,'' ) AS MAKE_YEAR,  \r\n "); // varchar,생산연도
			sql.append("IFNULL(MAKE_DM,'' ) AS MAKE_DM,  \r\n "); // varchar,제조일자
			sql.append("IFNULL(GOODS_SEASON,'' ) AS GOODS_SEASON,  \r\n "); // varchar,시즌
			sql.append("IFNULL(SEX,'' ) AS SEX,  \r\n "); // varchar,남녀구분
			sql.append("IFNULL(STATUS,'' ) AS STATUS,  \r\n "); // varchar,상품상태
			sql.append("IFNULL(DELIV_ABLE_REGION,'' ) AS DELIV_ABLE_REGION,  \r\n "); // varchar,판매지역
			sql.append("IFNULL(TAX_YN,'' ) AS TAX_YN,  \r\n "); // varchar,세금구분
			sql.append("IFNULL(DELV_TYPE,'' ) AS DELV_TYPE,  \r\n "); // varchar,배송비구분
			sql.append("IFNULL(DELV_COST,'' ) AS DELV_COST,  \r\n "); // int,배송비
			sql.append("IFNULL(BANPUM_AREA,'' ) AS BANPUM_AREA,  \r\n "); // varchar,반품지구분
			sql.append("IFNULL(GOODS_COST,'' ) AS GOODS_COST,  \r\n "); // int,자체 상품 원가
			sql.append("IFNULL(GOODS_PRICE,'' ) AS GOODS_PRICE,  \r\n "); // int,판매가
			sql.append("IFNULL(GOODS_CONSUMER_PRICE,'' ) AS GOODS_CONSUMER_PRICE,  \r\n "); // int,TAG가(소비자가)
			sql.append("IFNULL(CHAR_1_NM,'' ) AS CHAR_1_NM,  \r\n "); // varchar,옵션제목(1)
			sql.append("IFNULL(CHAR_1_VAL,'' ) AS CHAR_1_VAL,  \r\n "); // varchar,옵션상세명칭(1)
			sql.append("IFNULL(CHAR_2_NM,'' ) AS CHAR_2_NM,  \r\n "); // varchar,옵션제목(2)
			sql.append("IFNULL(CHAR_2_VAL,'' ) AS CHAR_2_VAL,  \r\n "); // varchar,옵션상세명칭(2)
			sql.append("IFNULL(IMG_PATH,'' ) AS IMG_PATH,  \r\n "); // varchar,대표이미지
			sql.append("IFNULL(IMG_PATH1,'' ) AS IMG_PATH1,  \r\n "); // varchar,종합몰(JPG)이미지
			sql.append("IFNULL(IMG_PATH2,'' ) AS IMG_PATH2,  \r\n "); // varchar,부가이미지2
			sql.append("IFNULL(IMG_PATH3,'' ) AS IMG_PATH3,  \r\n "); // varchar,부가이미지3
			sql.append("IFNULL(IMG_PATH4,'' ) AS IMG_PATH4,  \r\n "); // varchar,부가이미지4
			sql.append("IFNULL(IMG_PATH5,'' ) AS IMG_PATH5,  \r\n "); // varchar,부가이미지5
			sql.append("IFNULL(IMG_PATH6,'' ) AS IMG_PATH6,  \r\n "); // varchar,부가이미지6
			sql.append("IFNULL(IMG_PATH7,'' ) AS IMG_PATH7,  \r\n "); // varchar,부가이미지7
			sql.append("IFNULL(IMG_PATH8,'' ) AS IMG_PATH8,  \r\n "); // varchar,부가이미지8
			sql.append("IFNULL(IMG_PATH9,'' ) AS IMG_PATH9,  \r\n "); // varchar,부가이미지9
			sql.append("IFNULL(IMG_PATH10,'' ) AS IMG_PATH10,  \r\n "); // varchar,부가이미지10
			sql.append("IFNULL(IMG_PATH11,'' ) AS IMG_PATH11,  \r\n "); // varchar,부가이미지11
			sql.append("IFNULL(IMG_PATH12,'' ) AS IMG_PATH12,  \r\n "); // varchar,부가이미지12
			sql.append("IFNULL(IMG_PATH13,'' ) AS IMG_PATH13,  \r\n "); // varchar,부가이미지13
			sql.append("IFNULL(IMG_PATH14,'' ) AS IMG_PATH14,  \r\n "); // varchar,부가이미지14
			sql.append("IFNULL(IMG_PATH15,'' ) AS IMG_PATH15,  \r\n "); // varchar,부가이미지15
			sql.append("IFNULL(IMG_PATH16,'' ) AS IMG_PATH16,  \r\n "); // varchar,부가이미지16
			sql.append("IFNULL(IMG_PATH17,'' ) AS IMG_PATH17,  \r\n "); // varchar,부가이미지17
			sql.append("IFNULL(IMG_PATH18,'' ) AS IMG_PATH18,  \r\n "); // varchar,부가이미지18
			sql.append("IFNULL(IMG_PATH19,'' ) AS IMG_PATH19,  \r\n "); // varchar,부가이미지19
			sql.append("IFNULL(IMG_PATH20,'' ) AS IMG_PATH20,  \r\n "); // varchar,부가이미지20
			sql.append("IFNULL(IMG_PATH21,'' ) AS IMG_PATH21,  \r\n "); // varchar,부가이미지21
			sql.append("IFNULL(IMG_PATH22,'' ) AS IMG_PATH22,  \r\n "); // varchar,부가이미지22
			sql.append("IFNULL(IMG_PATH23,'' ) AS IMG_PATH23,  \r\n "); // varchar,인증서이미지
			sql.append("IFNULL(IMG_PATH24,'' ) AS IMG_PATH24,  \r\n "); // varchar,수입면장이미지
			sql.append("IFNULL(GOODS_REMARKS,'' ) AS GOODS_REMARKS,  \r\n "); // varchar,상품상세설명
			sql.append("IFNULL(CERTNO,'' ) AS CERTNO,  \r\n "); // varchar,인증번호
			sql.append("IFNULL(AVLST_DM,'' ) AS AVLST_DM,  \r\n "); // varchar,인증유효 시작일
			sql.append("IFNULL(AVLED_DM,'' ) AS AVLED_DM,  \r\n "); // varchar,인증유효 마지막일
			sql.append("IFNULL(ISSUEDATE,'' ) AS ISSUEDATE,  \r\n "); // varchar,발급일자
			sql.append("IFNULL(CERTDATE,'' ) AS CERTDATE,  \r\n "); // varchar,인증일자
			sql.append("IFNULL(CERT_AGENCY,'' ) AS CERT_AGENCY,  \r\n "); // varchar,인증기관
			sql.append("IFNULL(CERTFIELD,'' ) AS CERTFIELD,  \r\n "); // varchar,인증분야
			sql.append("IFNULL(MATERIAL,'' ) AS MATERIAL,  \r\n "); // varchar,식품재료/원산지
			sql.append("IFNULL(STOCK_USE_YN,'' ) AS STOCK_USE_YN,  \r\n "); // varchar,재고관리사용여부
			sql.append("IFNULL(OPT_TYPE,'' ) AS OPT_TYPE,  \r\n "); // varchar,옵션수정여부
			sql.append("IFNULL(PROP_EDIT_YN,'' ) AS PROP_EDIT_YN,  \r\n "); // varchar,속성수정여부
			sql.append("IFNULL(PROP1_CD,'' ) AS PROP1_CD,  \r\n "); // varchar,속성분류코드
			sql.append("IFNULL(PROP_VAL1,'' ) AS PROP_VAL1,  \r\n "); // varchar,속성값1
			sql.append("IFNULL(PROP_VAL2,'' ) AS PROP_VAL2,  \r\n "); // varchar,속성값2
			sql.append("IFNULL(PROP_VAL3,'' ) AS PROP_VAL3,  \r\n "); // varchar,속성값3
			sql.append("IFNULL(PROP_VAL4,'' ) AS PROP_VAL4,  \r\n "); // varchar,속성값4
			sql.append("IFNULL(PROP_VAL5,'' ) AS PROP_VAL5,  \r\n "); // varchar,속성값5
			sql.append("IFNULL(PROP_VAL6,'' ) AS PROP_VAL6,  \r\n "); // varchar,속성값6
			sql.append("IFNULL(PROP_VAL7,'' ) AS PROP_VAL7,  \r\n "); // varchar,속성값7
			sql.append("IFNULL(PROP_VAL8,'' ) AS PROP_VAL8,  \r\n "); // varchar,속성값8
			sql.append("IFNULL(PROP_VAL9,'' ) AS PROP_VAL9,  \r\n "); // varchar,속성값9
			sql.append("IFNULL(PROP_VAL10,'' ) AS PROP_VAL10,  \r\n "); // varchar,속성값10
			sql.append("IFNULL(PROP_VAL11,'' ) AS PROP_VAL11,  \r\n "); // varchar,속성값11
			sql.append("IFNULL(PROP_VAL12,'' ) AS PROP_VAL12,  \r\n "); // varchar,속성값12
			sql.append("IFNULL(PROP_VAL13,'' ) AS PROP_VAL13,  \r\n "); // varchar,속성값13
			sql.append("IFNULL(PROP_VAL14,'' ) AS PROP_VAL14,  \r\n "); // varchar,속성값14
			sql.append("IFNULL(PROP_VAL15,'' ) AS PROP_VAL15,  \r\n "); // varchar,속성값15
			sql.append("IFNULL(PROP_VAL16,'' ) AS PROP_VAL16,  \r\n "); // varchar,속성값16
			sql.append("IFNULL(PROP_VAL17,'' ) AS PROP_VAL17,  \r\n "); // varchar,속성값17
			sql.append("IFNULL(PROP_VAL18,'' ) AS PROP_VAL18,  \r\n "); // varchar,속성값18
			sql.append("IFNULL(PROP_VAL19,'' ) AS PROP_VAL19,  \r\n "); // varchar,속성값19
			sql.append("IFNULL(PROP_VAL20,'' ) AS PROP_VAL20,  \r\n "); // varchar,속성값20
			sql.append("IFNULL(PROP_VAL21,'' ) AS PROP_VAL21,  \r\n "); // varchar,속성값21
			sql.append("IFNULL(PROP_VAL22,'' ) AS PROP_VAL22,  \r\n "); // varchar,속성값22
			sql.append("IFNULL(PROP_VAL23,'' ) AS PROP_VAL23,  \r\n "); // varchar,속성값23
			sql.append("IFNULL(PROP_VAL24,'' ) AS PROP_VAL24,  \r\n "); // varchar,속성값24
			sql.append("IFNULL(PROP_VAL25,'' ) AS PROP_VAL25,  \r\n "); // varchar,속성값25
			sql.append("IFNULL(PROP_VAL26,'' ) AS PROP_VAL26,  \r\n "); // varchar,속성값26
			sql.append("IFNULL(PROP_VAL27,'' ) AS PROP_VAL27,  \r\n "); // varchar,속성값27
			sql.append("IFNULL(PROP_VAL28,'' ) AS PROP_VAL28,  \r\n "); // varchar,속성값28
			sql.append("IFNULL(PACK_CODE_STR,'' ) AS PACK_CODE_STR,  \r\n "); // varchar,추가상품그룹코드
			sql.append("IFNULL(GOODS_NM_EN,'' ) AS GOODS_NM_EN,  \r\n "); // varchar,영문 상품명
			sql.append("IFNULL(GOODS_NM_PR,'' ) AS GOODS_NM_PR,  \r\n "); // varchar,출력 상품명
			sql.append("IFNULL(GOODS_REMARKS2,'' ) AS GOODS_REMARKS2,  \r\n "); // varchar,추가 상품상세설명_1
			sql.append("IFNULL(GOODS_REMARKS3,'' ) AS GOODS_REMARKS3,  \r\n "); // varchar,추가 상품상세설명_2
			sql.append("IFNULL(GOODS_REMARKS4,'' ) AS GOODS_REMARKS4,  \r\n "); // varchar,추가 상품상세설명_3
			sql.append("IFNULL(IMPORTNO,'' ) AS IMPORTNO,  \r\n "); // varchar,수입신고번호
			sql.append("IFNULL(GOODS_COST2,'' ) AS GOODS_COST2,  \r\n "); // varchar,원가2
			sql.append("IFNULL(ORIGIN2,'' ) AS ORIGIN2,  \r\n "); // varchar,원산지 상세지역
			sql.append("IFNULL(EXPIRE_DM,'' ) AS EXPIRE_DM,  \r\n "); // varchar,유효일자
			sql.append("IFNULL(SUPPLY_SAVE_YN,'' ) AS SUPPLY_SAVE_YN,  \r\n "); // varchar,합포제외여부
			sql.append("IFNULL(DESCRITION,'' ) AS DESCRITION,  \r\n "); // varchar,관리자메모
			sql.append("IFNULL(SHOPPRODNO,'' ) AS SHOPPRODNO,  \r\n "); // varchar,완료후쇼핑몰코드(등록수정을한폼으로진행하기위해)
			sql.append("IFNULL(INSERTDT,'' ) AS INSERTDT,  \r\n "); // varchar,입력일자
			sql.append("IFNULL(INSERTID,'' ) AS INSERTID,  \r\n "); // varchar,입력자id
			sql.append("IFNULL(MODIFYDT,'' ) AS MODIFYDT,  \r\n "); // varchar,수정일자
			sql.append("IFNULL(MODIFYID,'' ) AS MODIFYID  \r\n "); // varchar,수정자ID

			sql.append(" FROM SHOPPRODINFO \r\n");
			sql.append(" WHERE COMPNO = ?  AND PRODSEQ = ? \r\n");

			sql.append(" ) AS INFO  LEFT JOIN CateAll AS CATE  \r\n");
			sql.append("		 ON INFO.CLASS_CD1 = CATE.CLASS_CD1  \r\n");
			sql.append(" 		 AND INFO.CLASS_CD2 = CATE.CLASS_CD2 \r\n");
			sql.append("AND INFO.CLASS_CD3 = CATE.CLASS_CD3   \r\n");
			sql.append("AND INFO.CLASS_CD4 = CATE.CLASS_CD4   \r\n");

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, compno);
			pstmt.setInt(2, prodseq);

			System.out.println("[getShopProductInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;

				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setCode(rs.getString("CODE"));
				dto.setProdseq(rs.getString("PRODSEQ")); // 품번코드 타입:int
				dto.setImg(rs.getString("Img")); // 이미지 추가..
				dto.setCompno(rs.getString("COMPNO")); // 타입:int
				dto.setGoods_nm(rs.getString("GOODS_NM")); // 상품명 타입:varchar
				dto.setGoods_keyword(rs.getString("GOODS_KEYWORD")); // 상품약어 타입:varchar
				dto.setModel_nm(rs.getString("MODEL_NM")); // 모델명 타입:varchar
				dto.setModel_no(rs.getString("MODEL_NO")); // 모델No 타입:varchar
				dto.setBrand_nm(rs.getString("BRAND_NM")); // 브랜드명 타입:varchar
				dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD")); // 자체상품코드 타입:varchar
				dto.setGoods_search(rs.getString("GOODS_SEARCH")); // 사이트검색어 타입:varchar
				dto.setGoods_gubun(rs.getString("GOODS_GUBUN")); // 상품구분 타입:varchar
				dto.setClass_cd1(rs.getString("CLASS_CD1")); // 대분류코드 타입:varchar
				dto.setClass_cd2(rs.getString("CLASS_CD2")); // 중분류코드 타입:varchar
				dto.setClass_cd3(rs.getString("CLASS_CD3")); // 소분류코드 타입:varchar
				dto.setClass_cd4(rs.getString("CLASS_CD4")); // 세분류코드 타입:varchar
				dto.setPartner_id(rs.getString("PARTNER_ID")); // 매입처ID 타입:varchar
				dto.setDpartner_id(rs.getString("DPARTNER_ID")); // 물류처ID 타입:varchar
				dto.setMaker(rs.getString("MAKER")); // 제조사 타입:varchar
				dto.setOrigin(rs.getString("ORIGIN")); // 원산지(제조국) 타입:varchar
				dto.setMake_year(rs.getString("MAKE_YEAR")); // 생산연도 타입:varchar
				dto.setMake_dm(rs.getString("MAKE_DM")); // 제조일자 타입:varchar
				dto.setGoods_season(rs.getString("GOODS_SEASON")); // 시즌 타입:varchar
				dto.setSex(rs.getString("SEX")); // 남녀구분 타입:varchar
				dto.setStatus(rs.getString("STATUS")); // 상품상태 타입:varchar
				dto.setDeliv_able_region(rs.getString("DELIV_ABLE_REGION")); // 판매지역 타입:varchar
				dto.setTax_yn(rs.getString("TAX_YN")); // 세금구분 타입:varchar
				dto.setDelv_type(rs.getString("DELV_TYPE")); // 배송비구분 타입:varchar
				dto.setDelv_cost(rs.getString("DELV_COST")); // 배송비 타입:int
				dto.setBanpum_area(rs.getString("BANPUM_AREA")); // 반품지구분 타입:varchar
				dto.setGoods_cost(rs.getString("GOODS_COST")); // 자체 상품 원가 타입:int
				dto.setGoods_price(rs.getString("GOODS_PRICE")); // 판매가 타입:int
				dto.setGoods_consumer_price(rs.getString("GOODS_CONSUMER_PRICE")); // TAG가(소비자가) 타입:int
				dto.setChar_1_nm(rs.getString("CHAR_1_NM")); // 옵션제목(1) 타입:varchar
				dto.setChar_1_val(rs.getString("CHAR_1_VAL")); // 옵션상세명칭(1) 타입:varchar
				dto.setChar_2_nm(rs.getString("CHAR_2_NM")); // 옵션제목(2) 타입:varchar
				dto.setChar_2_val(rs.getString("CHAR_2_VAL")); // 옵션상세명칭(2) 타입:varchar
				dto.setImg_path(rs.getString("IMG_PATH")); // 대표이미지 타입:varchar
				dto.setImg_path1(rs.getString("IMG_PATH1")); // 종합몰(JPG)이미지 타입:varchar
				dto.setImg_path2(rs.getString("IMG_PATH2")); // 부가이미지2 타입:varchar
				dto.setImg_path3(rs.getString("IMG_PATH3")); // 부가이미지3 타입:varchar
				dto.setImg_path4(rs.getString("IMG_PATH4")); // 부가이미지4 타입:varchar
				dto.setImg_path5(rs.getString("IMG_PATH5")); // 부가이미지5 타입:varchar
				dto.setImg_path6(rs.getString("IMG_PATH6")); // 부가이미지6 타입:varchar
				dto.setImg_path7(rs.getString("IMG_PATH7")); // 부가이미지7 타입:varchar
				dto.setImg_path8(rs.getString("IMG_PATH8")); // 부가이미지8 타입:varchar
				dto.setImg_path9(rs.getString("IMG_PATH9")); // 부가이미지9 타입:varchar
				dto.setImg_path10(rs.getString("IMG_PATH10")); // 부가이미지10 타입:varchar
				dto.setImg_path11(rs.getString("IMG_PATH11")); // 부가이미지11 타입:varchar
				dto.setImg_path12(rs.getString("IMG_PATH12")); // 부가이미지12 타입:varchar
				dto.setImg_path13(rs.getString("IMG_PATH13")); // 부가이미지13 타입:varchar
				dto.setImg_path14(rs.getString("IMG_PATH14")); // 부가이미지14 타입:varchar
				dto.setImg_path15(rs.getString("IMG_PATH15")); // 부가이미지15 타입:varchar
				dto.setImg_path16(rs.getString("IMG_PATH16")); // 부가이미지16 타입:varchar
				dto.setImg_path17(rs.getString("IMG_PATH17")); // 부가이미지17 타입:varchar
				dto.setImg_path18(rs.getString("IMG_PATH18")); // 부가이미지18 타입:varchar
				dto.setImg_path19(rs.getString("IMG_PATH19")); // 부가이미지19 타입:varchar
				dto.setImg_path20(rs.getString("IMG_PATH20")); // 부가이미지20 타입:varchar
				dto.setImg_path21(rs.getString("IMG_PATH21")); // 부가이미지21 타입:varchar
				dto.setImg_path22(rs.getString("IMG_PATH22")); // 부가이미지22 타입:varchar
				dto.setImg_path23(rs.getString("IMG_PATH23")); // 인증서이미지 타입:varchar
				dto.setImg_path24(rs.getString("IMG_PATH24")); // 수입면장이미지 타입:varchar
				dto.setGoods_remarks(rs.getString("GOODS_REMARKS")); // 상품상세설명 타입:varchar
				dto.setCertno(rs.getString("CERTNO")); // 인증번호 타입:varchar
				dto.setAvlst_dm(rs.getString("AVLST_DM")); // 인증유효 시작일 타입:varchar
				dto.setAvled_dm(rs.getString("AVLED_DM")); // 인증유효 마지막일 타입:varchar
				dto.setIssuedate(rs.getString("ISSUEDATE")); // 발급일자 타입:varchar
				dto.setCertdate(rs.getString("CERTDATE")); // 인증일자 타입:varchar
				dto.setCert_agency(rs.getString("CERT_AGENCY")); // 인증기관 타입:varchar
				dto.setCertfield(rs.getString("CERTFIELD")); // 인증분야 타입:varchar
				dto.setMaterial(rs.getString("MATERIAL")); // 식품재료/원산지 타입:varchar
				dto.setStock_use_yn(rs.getString("STOCK_USE_YN")); // 재고관리사용여부 타입:varchar
				dto.setOpt_type(rs.getString("OPT_TYPE")); // 옵션수정여부 타입:varchar
				dto.setProp_edit_yn(rs.getString("PROP_EDIT_YN")); // 속성수정여부 타입:varchar
				dto.setProp1_cd(rs.getString("PROP1_CD")); // 속성분류코드 타입:varchar
				dto.setProp_val1(rs.getString("PROP_VAL1")); // 속성값1 타입:varchar
				dto.setProp_val2(rs.getString("PROP_VAL2")); // 속성값2 타입:varchar
				dto.setProp_val3(rs.getString("PROP_VAL3")); // 속성값3 타입:varchar
				dto.setProp_val4(rs.getString("PROP_VAL4")); // 속성값4 타입:varchar
				dto.setProp_val5(rs.getString("PROP_VAL5")); // 속성값5 타입:varchar
				dto.setProp_val6(rs.getString("PROP_VAL6")); // 속성값6 타입:varchar
				dto.setProp_val7(rs.getString("PROP_VAL7")); // 속성값7 타입:varchar
				dto.setProp_val8(rs.getString("PROP_VAL8")); // 속성값8 타입:varchar
				dto.setProp_val9(rs.getString("PROP_VAL9")); // 속성값9 타입:varchar
				dto.setProp_val10(rs.getString("PROP_VAL10")); // 속성값10 타입:varchar
				dto.setProp_val11(rs.getString("PROP_VAL11")); // 속성값11 타입:varchar
				dto.setProp_val12(rs.getString("PROP_VAL12")); // 속성값12 타입:varchar
				dto.setProp_val13(rs.getString("PROP_VAL13")); // 속성값13 타입:varchar
				dto.setProp_val14(rs.getString("PROP_VAL14")); // 속성값14 타입:varchar
				dto.setProp_val15(rs.getString("PROP_VAL15")); // 속성값15 타입:varchar
				dto.setProp_val16(rs.getString("PROP_VAL16")); // 속성값16 타입:varchar
				dto.setProp_val17(rs.getString("PROP_VAL17")); // 속성값17 타입:varchar
				dto.setProp_val18(rs.getString("PROP_VAL18")); // 속성값18 타입:varchar
				dto.setProp_val19(rs.getString("PROP_VAL19")); // 속성값19 타입:varchar
				dto.setProp_val20(rs.getString("PROP_VAL20")); // 속성값20 타입:varchar
				dto.setProp_val21(rs.getString("PROP_VAL21")); // 속성값21 타입:varchar
				dto.setProp_val22(rs.getString("PROP_VAL22")); // 속성값22 타입:varchar
				dto.setProp_val23(rs.getString("PROP_VAL23")); // 속성값23 타입:varchar
				dto.setProp_val24(rs.getString("PROP_VAL24")); // 속성값24 타입:varchar
				dto.setProp_val25(rs.getString("PROP_VAL25")); // 속성값25 타입:varchar
				dto.setProp_val26(rs.getString("PROP_VAL26")); // 속성값26 타입:varchar
				dto.setProp_val27(rs.getString("PROP_VAL27")); // 속성값27 타입:varchar
				dto.setProp_val28(rs.getString("PROP_VAL28")); // 속성값28 타입:varchar
				dto.setPack_code_str(rs.getString("PACK_CODE_STR")); // 추가상품그룹코드 타입:varchar
				dto.setGoods_nm_en(rs.getString("GOODS_NM_EN")); // 영문 상품명 타입:varchar
				dto.setGoods_nm_pr(rs.getString("GOODS_NM_PR")); // 출력 상품명 타입:varchar
				dto.setGoods_remarks2(rs.getString("GOODS_REMARKS2")); // 추가 상품상세설명_1 타입:varchar
				dto.setGoods_remarks3(rs.getString("GOODS_REMARKS3")); // 추가 상품상세설명_2 타입:varchar
				dto.setGoods_remarks4(rs.getString("GOODS_REMARKS4")); // 추가 상품상세설명_3 타입:varchar
				dto.setImportno(rs.getString("IMPORTNO")); // 수입신고번호 타입:varchar
				dto.setGoods_cost2(rs.getString("GOODS_COST2")); // 원가2 타입:varchar
				dto.setOrigin2(rs.getString("ORIGIN2")); // 원산지 상세지역 타입:varchar
				dto.setExpire_dm(rs.getString("EXPIRE_DM")); // 유효일자 타입:varchar
				dto.setSupply_save_yn(rs.getString("SUPPLY_SAVE_YN")); // 합포제외여부 타입:varchar
				dto.setDescrition(rs.getString("DESCRITION")); // 관리자메모 타입:varchar
				dto.setShopprodno(rs.getString("SHOPPRODNO")); // 완료후쇼핑몰코드(등록수정을한폼으로진행하기위해) 타입:varchar
				dto.setInsertdt(rs.getString("INSERTDT")); // 입력일자 타입:varchar
				dto.setInsertid(rs.getString("INSERTID")); // 입력자id 타입:varchar
				dto.setModifydt(rs.getString("MODIFYDT")); // 수정일자 타입:varchar
				dto.setModifyid(rs.getString("MODIFYID")); // 수정자ID 타입:varchar

				// ----- class nm 추가 -----
				dto.setClass_nm1(rs.getString("CLASS_NM1")); // 대분류
				dto.setClass_nm2(rs.getString("CLASS_NM2")); // 중분류
				dto.setClass_nm3(rs.getString("CLASS_NM3")); // 소분류
				dto.setClass_nm4(rs.getString("CLASS_NM4")); // 세분류

				dto.setDelv_type_nm(rs.getString("DELV_TYPE_NM"));
				dto.setGoods_gubun_nm(rs.getString("GOODS_GUBUN_NM"));
				dto.setStatus_nm(rs.getString("STATUS_NM"));

				/********* addtinon ************/
//				ShopCatInfDto ShopCatInfDto = new ShopCatInfDto();
//
//				ShopCatInfDto.setCompno(rs.getString("COMPNO"));
//				ShopCatInfDto.setShopcd(rs.getString("SHOPCD"));
//				ShopCatInfDto.setShopcatno(rs.getString("SHOPCATNO"));
//				ShopCatInfDto.setShopcatnm(rs.getString("SHOPCATNM"));
//				ShopCatInfDto.setShopcatsitenm(rs.getString("SHOPCATSITENM"));
//				ShopCatInfDto.setShoplagcatcd(rs.getString("SHOPLAGCATCD"));
//				ShopCatInfDto.setShopmidcatcd(rs.getString("SHOPMIDCATCD"));
//				ShopCatInfDto.setShopsmlcatcd(rs.getString("SHOPSMLCATCD"));
//				ShopCatInfDto.setShopdetcatcd(rs.getString("SHOPDETCATCD"));
//				ShopCatInfDto.setServiceprod(rs.getString("SERVICEPROD"));
//				ShopCatInfDto.setUse_yn(rs.getString("USE_YN"));
//				ShopCatInfDto.setShopgeneral(rs.getString("SHOPGENERAL"));
//				ShopCatInfDto.setShopid(rs.getString("SHOPID"));
//				ShopCatInfDto.setShopcommis(rs.getString("SHOPCOMMIS"));
//				ShopCatInfDto.setInsertdt(rs.getString("INSERTDT"));
//				ShopCatInfDto.setModifydt(rs.getString("MODIFYDT"));
//
//				dto.setShopCatInDto(ShopCatInfDto);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	public List<ShopProductDto> getShopProductList(String shopcd, int option, String prodFrom, String prodTo,
			String lagcateg, String midcateg, String smlcateg, String detcateg, String purch, String logis, int supply,
			String orderIvtr, String shopfee, String sex, String season, String prod, String searchgubun,
			String searchtxt) throws Exception {

		String compno = YDMASessonUtil.getCompnoInfo().getCompno();
		// opt == {상품코드, 상품명}
		// List<List<String>> contents = new ArrayList<>();
		List<ShopProductDto> list = new ArrayList<ShopProductDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();

//			sql.append(" WITH CateAll AS(                                                                 \r\n");
//			sql.append("		SELECT                                                                    \r\n");
//			sql.append("		   ifnull(a.CODE,'')     AS CLASS_CD1 ,                                   \r\n");
//			sql.append("			ifnull(a.CATEGORY,'') AS CLASS_NM1 ,                                  \r\n");
//			sql.append("			IFNULL(RIGHT(b.CODE,3),'')     AS CLASS_CD2  ,                        \r\n");
//			sql.append("			ifnull(b.CATEGORY,'') AS CLASS_NM2 ,                                  \r\n");
//			sql.append("			ifnull(RIGHT(c.CODE,3),'')     AS CLASS_CD3,                          \r\n");
//			sql.append("			ifnull(C.CATEGORY,'') AS CLASS_NM3,                                   \r\n");
//			sql.append("			ifnull(RIGHT(D.CODE,3),'')     AS CLASS_CD4 ,                         \r\n");
//			sql.append("			ifnull(D.CATEGORY,'') AS CLASS_NM4                                    \r\n");
//			sql.append("		FROM CATEGLARGE a left JOIN CATEGMIDIUM b                                      \r\n");
//			sql.append("			ON a.COMPNO=b.COMPNO AND a.CODE = b.LRGCODE left JOIN CATEGSMALL C         \r\n");
//			sql.append("			ON a.COMPNO = C.COMPNO AND b.CODE = C.MIDCODE left JOIN CATEGDETAIL D ON   \r\n");
//			sql.append(String.format(" a.COMPNO = D.COMPNO AND C.CODE = D.SMLCODE WHERE a.compno = '%s'         \r\n",
//					YDMASessonUtil.getCompnoInfo().getCompno()));
//			sql.append("		 )                                                                        \r\n");

			sql.append(" SELECT INFO.*  , \r\n");
			sql.append(String.format(
					" IFNULL((SELECT CATEGORY FROM  CATEGLARGE WHERE compno = %s AND CODE = INFO.CLASS_CD1), '')  CLASS_NM1, \r\n",
					YDMASessonUtil.getCompnoInfo().getCompno()));
			sql.append(String.format(
					" IFNULL((SELECT CATEGORY FROM  CATEGMIDIUM WHERE compno = %s AND CODE = CONCAT(INFO.CLASS_CD1,INFO.CLASS_CD2)), '')  CLASS_NM2,  \r\n",
					YDMASessonUtil.getCompnoInfo().getCompno()));
			sql.append(String.format(
					" IFNULL((SELECT CATEGORY FROM  CATEGSMALL WHERE compno = %s AND CODE = CONCAT(INFO.CLASS_CD1,INFO.CLASS_CD2,INFO.CLASS_CD3)), '')  CLASS_NM3,  \r\n",
					YDMASessonUtil.getCompnoInfo().getCompno()));
			sql.append(String.format(
					" IFNULL((SELECT CATEGORY FROM  CATEGDETAIL WHERE compno = %s AND CODE = CONCAT(INFO.CLASS_CD1,INFO.CLASS_CD2,INFO.CLASS_CD3,INFO.CLASS_CD4)), '') as CLASS_NM4, \r\n",
					YDMASessonUtil.getCompnoInfo().getCompno()));

			// ----- 사업상분..
			sql.append("(CASE                                   \r\n");
			sql.append("	     WHEN GOODS_GUBUN = '1'         \r\n");
			sql.append("	     THEN '위탁상품'                \r\n");
			sql.append("	     WHEN GOODS_GUBUN = '2'         \r\n");
			sql.append("	     THEN '제조상품'                \r\n");
			sql.append("	     WHEN GOODS_GUBUN = '3'         \r\n");
			sql.append("	     THEN '사업상품'                \r\n");
			sql.append("	     WHEN GOODS_GUBUN = '4'         \r\n");
			sql.append("	     THEN '직영상품'                \r\n");
			sql.append("	     ELSE ''                        \r\n");
			sql.append("	  END )AS GOODS_GUBUN_NM,            \r\n");

			// ------- 상태값..
			sql.append("(CASE                            \r\n");
			sql.append("	     WHEN STATUS = '1'       \r\n");
			sql.append("	     THEN '대기중'           \r\n");
			sql.append("	     WHEN STATUS = '2'       \r\n");
			sql.append("	     THEN '공급중'           \r\n");
			sql.append("	     WHEN STATUS = '3'       \r\n");
			sql.append("	     THEN '일시중지'         \r\n");
			sql.append("	     WHEN STATUS = '4'       \r\n");
			sql.append("	     THEN '완전품절'         \r\n");
			sql.append("	      WHEN STATUS = '5'      \r\n");
			sql.append("	     THEN '미사용'           \r\n");
			sql.append("	      WHEN STATUS = '6'      \r\n");
			sql.append("	     THEN '삭제'             \r\n");
			sql.append("	     ELSE ''                 \r\n");
			sql.append("	  END )AS STATUS_NM,         \r\n");

			// ----- 택배 상태값..
			sql.append("(CASE                             \r\n");
			sql.append("	     WHEN DELV_TYPE = '1'     \r\n");
			sql.append("	     THEN '무료'              \r\n");
			sql.append("	     WHEN DELV_TYPE = '2'     \r\n");
			sql.append("	     THEN '착불'              \r\n");
			sql.append("	     WHEN DELV_TYPE = '3'     \r\n");
			sql.append("	     THEN '선결제'            \r\n");
			sql.append("	     WHEN DELV_TYPE = '4'     \r\n");
			sql.append("	     THEN '착불/선결제'       \r\n");
			sql.append("	     ELSE ''                  \r\n");
			sql.append("	  END )AS DELV_TYPE_NM       \r\n");

//			sql.append("IFNULL(CATEINF.COMPNO,'' ) AS COMPNO,  \r\n "); // int,
//			sql.append("IFNULL(CATEINF.SHOPCD,'' ) AS SHOPCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPCATNO,'' ) AS SHOPCATNO,  \r\n "); // int,
//			sql.append("IFNULL(CATEINF.SHOPCATNM,'' ) AS SHOPCATNM,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPCATSITENM,'' ) AS SHOPCATSITENM,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPLAGCATCD,'' ) AS SHOPLAGCATCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPMIDCATCD,'' ) AS SHOPMIDCATCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPSMLCATCD,'' ) AS SHOPSMLCATCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPDETCATCD,'' ) AS SHOPDETCATCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SERVICEPROD,'' ) AS SERVICEPROD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.USE_YN,'' ) AS USE_YN,  \r\n "); // char,
//			sql.append("IFNULL(CATEINF.SHOPGENERAL,'' ) AS SHOPGENERAL,  \r\n "); // varchar,01:일반 02:해외배송
//			sql.append("IFNULL(CATEINF.SHOPID,'' ) AS SHOPID,  \r\n "); // varchar,쇼핑몰아이디
//			sql.append("IFNULL(CATEINF.SHOPCOMMIS,'' ) AS SHOPCOMMIS,  \r\n "); // float,쇼핑몰기본수수료율
//			sql.append("IFNULL(CATEINF.INSERTDT,'' ) AS INSERTDT,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.MODIFYDT,'' ) AS MODIFYDT  \r\n "); // varchar,
			sql.append(" FROM                             \r\n");
			sql.append(" (SELECT                           \r\n");
			sql.append(
					" CONCAT(IFNULL(CLASS_CD1,''), IFNULL(CLASS_CD2,''), IFNULL(CLASS_CD3,''), IFNULL(CLASS_CD4,'')) AS CODE, \r\n");
			sql.append("IFNULL(PRODSEQ,'' ) AS PRODSEQ,  \r\n "); // int,품번코드
			sql.append("IFNULL(COMPNO,'' ) AS COMPNO,  \r\n "); // int,
			sql.append(String.format("YWM_FUNC_BOSSPRODCD(%s,COMPAYNY_GOODS_CD) as img,",
					YDMASessonUtil.getCompnoInfo().getCompno()));
			sql.append("IFNULL(GOODS_NM,'' ) AS GOODS_NM,  \r\n "); // varchar,상품명
			sql.append("IFNULL(GOODS_KEYWORD,'' ) AS GOODS_KEYWORD,  \r\n "); // varchar,상품약어
			sql.append("IFNULL(MODEL_NM,'' ) AS MODEL_NM,  \r\n "); // varchar,모델명
			sql.append("IFNULL(MODEL_NO,'' ) AS MODEL_NO,  \r\n "); // varchar,모델No
			sql.append("IFNULL(BRAND_NM,'' ) AS BRAND_NM,  \r\n "); // varchar,브랜드명
			sql.append("IFNULL(COMPAYNY_GOODS_CD,'' ) AS COMPAYNY_GOODS_CD,  \r\n "); // varchar,자체상품코드
			sql.append("IFNULL(GOODS_SEARCH,'' ) AS GOODS_SEARCH,  \r\n "); // varchar,사이트검색어
			sql.append("IFNULL(GOODS_GUBUN,'' ) AS GOODS_GUBUN,  \r\n "); // varchar,상품구분
			sql.append("IFNULL(CLASS_CD1,'' ) AS CLASS_CD1,  \r\n "); // varchar,대분류코드
			sql.append("IFNULL(CLASS_CD2,'' ) AS CLASS_CD2,  \r\n "); // varchar,중분류코드
			sql.append("IFNULL(CLASS_CD3,'' ) AS CLASS_CD3,  \r\n "); // varchar,소분류코드
			sql.append("IFNULL(CLASS_CD4,'' ) AS CLASS_CD4,  \r\n "); // varchar,세분류코드
			sql.append("IFNULL(PARTNER_ID,'' ) AS PARTNER_ID,  \r\n "); // varchar,매입처ID
			sql.append("IFNULL(DPARTNER_ID,'' ) AS DPARTNER_ID,  \r\n "); // varchar,물류처ID
			sql.append("IFNULL(MAKER,'' ) AS MAKER,  \r\n "); // varchar,제조사
			sql.append("IFNULL(ORIGIN,'' ) AS ORIGIN,  \r\n "); // varchar,원산지(제조국)
			sql.append("IFNULL(MAKE_YEAR,'' ) AS MAKE_YEAR,  \r\n "); // varchar,생산연도
			sql.append("IFNULL(MAKE_DM,'' ) AS MAKE_DM,  \r\n "); // varchar,제조일자
			sql.append("IFNULL(GOODS_SEASON,'' ) AS GOODS_SEASON,  \r\n "); // varchar,시즌
			sql.append("IFNULL(SEX,'' ) AS SEX,  \r\n "); // varchar,남녀구분
			sql.append("IFNULL(STATUS,'' ) AS STATUS,  \r\n "); // varchar,상품상태
			sql.append("IFNULL(DELIV_ABLE_REGION,'' ) AS DELIV_ABLE_REGION,  \r\n "); // varchar,판매지역
			sql.append("IFNULL(TAX_YN,'' ) AS TAX_YN,  \r\n "); // varchar,세금구분
			sql.append("IFNULL(DELV_TYPE,'' ) AS DELV_TYPE,  \r\n "); // varchar,배송비구분
			sql.append("IFNULL(DELV_COST,'' ) AS DELV_COST,  \r\n "); // int,배송비
			sql.append("IFNULL(BANPUM_AREA,'' ) AS BANPUM_AREA,  \r\n "); // varchar,반품지구분
			sql.append("IFNULL(GOODS_COST,'' ) AS GOODS_COST,  \r\n "); // int,자체 상품 원가
			sql.append("IFNULL(GOODS_PRICE,'' ) AS GOODS_PRICE,  \r\n "); // int,판매가
			sql.append("IFNULL(GOODS_CONSUMER_PRICE,'' ) AS GOODS_CONSUMER_PRICE,  \r\n "); // int,TAG가(소비자가)
			sql.append("IFNULL(CHAR_1_NM,'' ) AS CHAR_1_NM,  \r\n "); // varchar,옵션제목(1)
			sql.append("IFNULL(CHAR_1_VAL,'' ) AS CHAR_1_VAL,  \r\n "); // varchar,옵션상세명칭(1)
			sql.append("IFNULL(CHAR_2_NM,'' ) AS CHAR_2_NM,  \r\n "); // varchar,옵션제목(2)
			sql.append("IFNULL(CHAR_2_VAL,'' ) AS CHAR_2_VAL,  \r\n "); // varchar,옵션상세명칭(2)
			sql.append("IFNULL(IMG_PATH,'' ) AS IMG_PATH,  \r\n "); // varchar,대표이미지
			sql.append("IFNULL(IMG_PATH1,'' ) AS IMG_PATH1,  \r\n "); // varchar,종합몰(JPG)이미지
			sql.append("IFNULL(IMG_PATH2,'' ) AS IMG_PATH2,  \r\n "); // varchar,부가이미지2
			sql.append("IFNULL(IMG_PATH3,'' ) AS IMG_PATH3,  \r\n "); // varchar,부가이미지3
			sql.append("IFNULL(IMG_PATH4,'' ) AS IMG_PATH4,  \r\n "); // varchar,부가이미지4
			sql.append("IFNULL(IMG_PATH5,'' ) AS IMG_PATH5,  \r\n "); // varchar,부가이미지5
			sql.append("IFNULL(IMG_PATH6,'' ) AS IMG_PATH6,  \r\n "); // varchar,부가이미지6
			sql.append("IFNULL(IMG_PATH7,'' ) AS IMG_PATH7,  \r\n "); // varchar,부가이미지7
			sql.append("IFNULL(IMG_PATH8,'' ) AS IMG_PATH8,  \r\n "); // varchar,부가이미지8
			sql.append("IFNULL(IMG_PATH9,'' ) AS IMG_PATH9,  \r\n "); // varchar,부가이미지9
			sql.append("IFNULL(IMG_PATH10,'' ) AS IMG_PATH10,  \r\n "); // varchar,부가이미지10
			sql.append("IFNULL(IMG_PATH11,'' ) AS IMG_PATH11,  \r\n "); // varchar,부가이미지11
			sql.append("IFNULL(IMG_PATH12,'' ) AS IMG_PATH12,  \r\n "); // varchar,부가이미지12
			sql.append("IFNULL(IMG_PATH13,'' ) AS IMG_PATH13,  \r\n "); // varchar,부가이미지13
			sql.append("IFNULL(IMG_PATH14,'' ) AS IMG_PATH14,  \r\n "); // varchar,부가이미지14
			sql.append("IFNULL(IMG_PATH15,'' ) AS IMG_PATH15,  \r\n "); // varchar,부가이미지15
			sql.append("IFNULL(IMG_PATH16,'' ) AS IMG_PATH16,  \r\n "); // varchar,부가이미지16
			sql.append("IFNULL(IMG_PATH17,'' ) AS IMG_PATH17,  \r\n "); // varchar,부가이미지17
			sql.append("IFNULL(IMG_PATH18,'' ) AS IMG_PATH18,  \r\n "); // varchar,부가이미지18
			sql.append("IFNULL(IMG_PATH19,'' ) AS IMG_PATH19,  \r\n "); // varchar,부가이미지19
			sql.append("IFNULL(IMG_PATH20,'' ) AS IMG_PATH20,  \r\n "); // varchar,부가이미지20
			sql.append("IFNULL(IMG_PATH21,'' ) AS IMG_PATH21,  \r\n "); // varchar,부가이미지21
			sql.append("IFNULL(IMG_PATH22,'' ) AS IMG_PATH22,  \r\n "); // varchar,부가이미지22
			sql.append("IFNULL(IMG_PATH23,'' ) AS IMG_PATH23,  \r\n "); // varchar,인증서이미지
			sql.append("IFNULL(IMG_PATH24,'' ) AS IMG_PATH24,  \r\n "); // varchar,수입면장이미지
			sql.append("IFNULL(GOODS_REMARKS,'' ) AS GOODS_REMARKS,  \r\n "); // varchar,상품상세설명
			sql.append("IFNULL(CERTNO,'' ) AS CERTNO,  \r\n "); // varchar,인증번호
			sql.append("IFNULL(AVLST_DM,'' ) AS AVLST_DM,  \r\n "); // varchar,인증유효 시작일
			sql.append("IFNULL(AVLED_DM,'' ) AS AVLED_DM,  \r\n "); // varchar,인증유효 마지막일
			sql.append("IFNULL(ISSUEDATE,'' ) AS ISSUEDATE,  \r\n "); // varchar,발급일자
			sql.append("IFNULL(CERTDATE,'' ) AS CERTDATE,  \r\n "); // varchar,인증일자
			sql.append("IFNULL(CERT_AGENCY,'' ) AS CERT_AGENCY,  \r\n "); // varchar,인증기관
			sql.append("IFNULL(CERTFIELD,'' ) AS CERTFIELD,  \r\n "); // varchar,인증분야
			sql.append("IFNULL(MATERIAL,'' ) AS MATERIAL,  \r\n "); // varchar,식품재료/원산지
			sql.append("IFNULL(STOCK_USE_YN,'' ) AS STOCK_USE_YN,  \r\n "); // varchar,재고관리사용여부
			sql.append("IFNULL(OPT_TYPE,'' ) AS OPT_TYPE,  \r\n "); // varchar,옵션수정여부
			sql.append("IFNULL(PROP_EDIT_YN,'' ) AS PROP_EDIT_YN,  \r\n "); // varchar,속성수정여부
			sql.append("IFNULL(PROP1_CD,'' ) AS PROP1_CD,  \r\n "); // varchar,속성분류코드
			sql.append("IFNULL(PROP_VAL1,'' ) AS PROP_VAL1,  \r\n "); // varchar,속성값1
			sql.append("IFNULL(PROP_VAL2,'' ) AS PROP_VAL2,  \r\n "); // varchar,속성값2
			sql.append("IFNULL(PROP_VAL3,'' ) AS PROP_VAL3,  \r\n "); // varchar,속성값3
			sql.append("IFNULL(PROP_VAL4,'' ) AS PROP_VAL4,  \r\n "); // varchar,속성값4
			sql.append("IFNULL(PROP_VAL5,'' ) AS PROP_VAL5,  \r\n "); // varchar,속성값5
			sql.append("IFNULL(PROP_VAL6,'' ) AS PROP_VAL6,  \r\n "); // varchar,속성값6
			sql.append("IFNULL(PROP_VAL7,'' ) AS PROP_VAL7,  \r\n "); // varchar,속성값7
			sql.append("IFNULL(PROP_VAL8,'' ) AS PROP_VAL8,  \r\n "); // varchar,속성값8
			sql.append("IFNULL(PROP_VAL9,'' ) AS PROP_VAL9,  \r\n "); // varchar,속성값9
			sql.append("IFNULL(PROP_VAL10,'' ) AS PROP_VAL10,  \r\n "); // varchar,속성값10
			sql.append("IFNULL(PROP_VAL11,'' ) AS PROP_VAL11,  \r\n "); // varchar,속성값11
			sql.append("IFNULL(PROP_VAL12,'' ) AS PROP_VAL12,  \r\n "); // varchar,속성값12
			sql.append("IFNULL(PROP_VAL13,'' ) AS PROP_VAL13,  \r\n "); // varchar,속성값13
			sql.append("IFNULL(PROP_VAL14,'' ) AS PROP_VAL14,  \r\n "); // varchar,속성값14
			sql.append("IFNULL(PROP_VAL15,'' ) AS PROP_VAL15,  \r\n "); // varchar,속성값15
			sql.append("IFNULL(PROP_VAL16,'' ) AS PROP_VAL16,  \r\n "); // varchar,속성값16
			sql.append("IFNULL(PROP_VAL17,'' ) AS PROP_VAL17,  \r\n "); // varchar,속성값17
			sql.append("IFNULL(PROP_VAL18,'' ) AS PROP_VAL18,  \r\n "); // varchar,속성값18
			sql.append("IFNULL(PROP_VAL19,'' ) AS PROP_VAL19,  \r\n "); // varchar,속성값19
			sql.append("IFNULL(PROP_VAL20,'' ) AS PROP_VAL20,  \r\n "); // varchar,속성값20
			sql.append("IFNULL(PROP_VAL21,'' ) AS PROP_VAL21,  \r\n "); // varchar,속성값21
			sql.append("IFNULL(PROP_VAL22,'' ) AS PROP_VAL22,  \r\n "); // varchar,속성값22
			sql.append("IFNULL(PROP_VAL23,'' ) AS PROP_VAL23,  \r\n "); // varchar,속성값23
			sql.append("IFNULL(PROP_VAL24,'' ) AS PROP_VAL24,  \r\n "); // varchar,속성값24
			sql.append("IFNULL(PROP_VAL25,'' ) AS PROP_VAL25,  \r\n "); // varchar,속성값25
			sql.append("IFNULL(PROP_VAL26,'' ) AS PROP_VAL26,  \r\n "); // varchar,속성값26
			sql.append("IFNULL(PROP_VAL27,'' ) AS PROP_VAL27,  \r\n "); // varchar,속성값27
			sql.append("IFNULL(PROP_VAL28,'' ) AS PROP_VAL28,  \r\n "); // varchar,속성값28
			sql.append("IFNULL(PACK_CODE_STR,'' ) AS PACK_CODE_STR,  \r\n "); // varchar,추가상품그룹코드
			sql.append("IFNULL(GOODS_NM_EN,'' ) AS GOODS_NM_EN,  \r\n "); // varchar,영문 상품명
			sql.append("IFNULL(GOODS_NM_PR,'' ) AS GOODS_NM_PR,  \r\n "); // varchar,출력 상품명
			sql.append("IFNULL(GOODS_REMARKS2,'' ) AS GOODS_REMARKS2,  \r\n "); // varchar,추가 상품상세설명_1
			sql.append("IFNULL(GOODS_REMARKS3,'' ) AS GOODS_REMARKS3,  \r\n "); // varchar,추가 상품상세설명_2
			sql.append("IFNULL(GOODS_REMARKS4,'' ) AS GOODS_REMARKS4,  \r\n "); // varchar,추가 상품상세설명_3
			sql.append("IFNULL(IMPORTNO,'' ) AS IMPORTNO,  \r\n "); // varchar,수입신고번호
			sql.append("IFNULL(GOODS_COST2,'' ) AS GOODS_COST2,  \r\n "); // varchar,원가2
			sql.append("IFNULL(ORIGIN2,'' ) AS ORIGIN2,  \r\n "); // varchar,원산지 상세지역
			sql.append("IFNULL(EXPIRE_DM,'' ) AS EXPIRE_DM,  \r\n "); // varchar,유효일자
			sql.append("IFNULL(SUPPLY_SAVE_YN,'' ) AS SUPPLY_SAVE_YN,  \r\n "); // varchar,합포제외여부
			sql.append("IFNULL(DESCRITION,'' ) AS DESCRITION,  \r\n "); // varchar,관리자메모
			sql.append("IFNULL(SHOPPRODNO,'' ) AS SHOPPRODNO,  \r\n "); // varchar,완료후쇼핑몰코드(등록수정을한폼으로진행하기위해)
			sql.append("IFNULL(INSERTDT,'' ) AS INSERTDT,  \r\n "); // varchar,입력일자
			sql.append("IFNULL(INSERTID,'' ) AS INSERTID,  \r\n "); // varchar,입력자id
			sql.append("IFNULL(MODIFYDT,'' ) AS MODIFYDT,  \r\n "); // varchar,수정일자
			sql.append("IFNULL(MODIFYID,'' ) AS MODIFYID, \r\n "); // varchar,수정자ID
			sql.append("IFNULL(FLAG,'' ) AS FLAG  \r\n "); // 싱크

			sql.append(" FROM SHOPPRODINFO \r\n");

			if (option == 0) {
				sql.append(" WHERE COMPNO = ?  AND INSERTDT >= ? AND INSERTDT <= ? \r\n");

			} else {
				sql.append(" WHERE COMPNO = ?  AND MODIFYDT >= ? AND MODIFYDT <= ? \r\n");
			}

//			"조건선택", "품번코드", "상품명", "상품약어", "모델명", "모델NO", "브랜드명", "자체상품코드", "제조사",
//			"배송비", "생산연도", "관리자메모"
			if (searchgubun.equals("품번코드")) {
				sql.append(" and prodseq = ? \r\n");
			} else if (searchgubun.equals("상품명")) {
				sql.append(" and GOODS_NM LIKE ? \r\n");
			} else if (searchgubun.equals("상품약어")) {
				sql.append(" and GOODS_KEYWORD LIKE ? \r\n");
			} else if (searchgubun.equals("모델명")) {
				sql.append(" and MODEL_NM LIKE ? \r\n");
			} else if (searchgubun.equals("모델NO")) {
				sql.append(" and MODEL_NO LIKE ? \r\n");
			} else if (searchgubun.equals("브랜드명")) {
				sql.append(" and BRAND_NM LIKE ? \r\n");
			} else if (searchgubun.equals("자체상품코드")) {
				sql.append(" and COMPAYNY_GOODS_CD LIKE ? \r\n");
			} else if (searchgubun.equals("제조사")) {
				sql.append(" and MAKER LIKE ? \r\n");
			} else if (searchgubun.equals("배송비")) {
				sql.append(" and DELV_COST = ? \r\n");
			} else if (searchgubun.equals("생산연도")) {
				sql.append(" and MAKE_YEAR LIKE ? \r\n");
			} else if (searchgubun.equals("관리자메모")) {
				sql.append(" and GOODS_SEARCH LIKE ? \r\n");
			}
			sql.append(" ) AS INFO \r\n");

//			sql.append(" ) AS INFO  LEFT JOIN CateAll AS CATE  \r\n");
//			sql.append("		 ON INFO.CLASS_CD1 = CATE.CLASS_CD1  \r\n");
//			sql.append(" 		 AND INFO.CLASS_CD2 = CATE.CLASS_CD2 \r\n");
//			sql.append("AND INFO.CLASS_CD3 = CATE.CLASS_CD3   \r\n");
//			sql.append("AND INFO.CLASS_CD4 = CATE.CLASS_CD4   \r\n");

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, prodFrom);
			pstmt.setString(3, prodTo);
//			if (searchgubun.equals("조건선택")) {
//			} else if (searchgubun.equals("배송비") || searchgubun.equals("품번코드")) {
//				pstmt.setInt(4, Integer.parseInt(searchtxt));
//			} else {
//				pstmt.setString(4, "%".concat(searchtxt).concat("%"));
//			}

			System.out.println("[getShopProductList]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ShopProductDto dto = new ShopProductDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setCode(rs.getString("CODE"));
				dto.setProdseq(rs.getString("PRODSEQ")); // 품번코드 타입:int
				dto.setImg(rs.getString("Img")); // 이미지 추가..
				dto.setCompno(rs.getString("COMPNO")); // 타입:int
				dto.setGoods_nm(rs.getString("GOODS_NM")); // 상품명 타입:varchar
				dto.setGoods_keyword(rs.getString("GOODS_KEYWORD")); // 상품약어 타입:varchar
				dto.setModel_nm(rs.getString("MODEL_NM")); // 모델명 타입:varchar
				dto.setModel_no(rs.getString("MODEL_NO")); // 모델No 타입:varchar
				dto.setBrand_nm(rs.getString("BRAND_NM")); // 브랜드명 타입:varchar
				dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD")); // 자체상품코드 타입:varchar
				dto.setGoods_search(rs.getString("GOODS_SEARCH")); // 사이트검색어 타입:varchar
				dto.setGoods_gubun(rs.getString("GOODS_GUBUN")); // 상품구분 타입:varchar
				dto.setClass_cd1(rs.getString("CLASS_CD1")); // 대분류코드 타입:varchar
				dto.setClass_cd2(rs.getString("CLASS_CD2")); // 중분류코드 타입:varchar
				dto.setClass_cd3(rs.getString("CLASS_CD3")); // 소분류코드 타입:varchar
				dto.setClass_cd4(rs.getString("CLASS_CD4")); // 세분류코드 타입:varchar
				dto.setPartner_id(rs.getString("PARTNER_ID")); // 매입처ID 타입:varchar
				dto.setDpartner_id(rs.getString("DPARTNER_ID")); // 물류처ID 타입:varchar
				dto.setMaker(rs.getString("MAKER")); // 제조사 타입:varchar
				dto.setOrigin(rs.getString("ORIGIN")); // 원산지(제조국) 타입:varchar
				dto.setMake_year(rs.getString("MAKE_YEAR")); // 생산연도 타입:varchar
				dto.setMake_dm(rs.getString("MAKE_DM")); // 제조일자 타입:varchar
				dto.setGoods_season(rs.getString("GOODS_SEASON")); // 시즌 타입:varchar
				dto.setSex(rs.getString("SEX")); // 남녀구분 타입:varchar
				dto.setStatus(rs.getString("STATUS")); // 상품상태 타입:varchar
				dto.setDeliv_able_region(rs.getString("DELIV_ABLE_REGION")); // 판매지역 타입:varchar
				dto.setTax_yn(rs.getString("TAX_YN")); // 세금구분 타입:varchar
				dto.setDelv_type(rs.getString("DELV_TYPE")); // 배송비구분 타입:varchar
				dto.setDelv_cost(rs.getString("DELV_COST")); // 배송비 타입:int
				dto.setBanpum_area(rs.getString("BANPUM_AREA")); // 반품지구분 타입:varchar
				dto.setGoods_cost(rs.getString("GOODS_COST")); // 자체 상품 원가 타입:int
				dto.setGoods_price(rs.getString("GOODS_PRICE")); // 판매가 타입:int
				dto.setGoods_consumer_price(rs.getString("GOODS_CONSUMER_PRICE")); // TAG가(소비자가) 타입:int
				dto.setChar_1_nm(rs.getString("CHAR_1_NM")); // 옵션제목(1) 타입:varchar
				dto.setChar_1_val(rs.getString("CHAR_1_VAL")); // 옵션상세명칭(1) 타입:varchar
				dto.setChar_2_nm(rs.getString("CHAR_2_NM")); // 옵션제목(2) 타입:varchar
				dto.setChar_2_val(rs.getString("CHAR_2_VAL")); // 옵션상세명칭(2) 타입:varchar
				dto.setImg_path(rs.getString("IMG_PATH")); // 대표이미지 타입:varchar
				dto.setImg_path1(rs.getString("IMG_PATH1")); // 종합몰(JPG)이미지 타입:varchar
				dto.setImg_path2(rs.getString("IMG_PATH2")); // 부가이미지2 타입:varchar
				dto.setImg_path3(rs.getString("IMG_PATH3")); // 부가이미지3 타입:varchar
				dto.setImg_path4(rs.getString("IMG_PATH4")); // 부가이미지4 타입:varchar
				dto.setImg_path5(rs.getString("IMG_PATH5")); // 부가이미지5 타입:varchar
				dto.setImg_path6(rs.getString("IMG_PATH6")); // 부가이미지6 타입:varchar
				dto.setImg_path7(rs.getString("IMG_PATH7")); // 부가이미지7 타입:varchar
				dto.setImg_path8(rs.getString("IMG_PATH8")); // 부가이미지8 타입:varchar
				dto.setImg_path9(rs.getString("IMG_PATH9")); // 부가이미지9 타입:varchar
				dto.setImg_path10(rs.getString("IMG_PATH10")); // 부가이미지10 타입:varchar
				dto.setImg_path11(rs.getString("IMG_PATH11")); // 부가이미지11 타입:varchar
				dto.setImg_path12(rs.getString("IMG_PATH12")); // 부가이미지12 타입:varchar
				dto.setImg_path13(rs.getString("IMG_PATH13")); // 부가이미지13 타입:varchar
				dto.setImg_path14(rs.getString("IMG_PATH14")); // 부가이미지14 타입:varchar
				dto.setImg_path15(rs.getString("IMG_PATH15")); // 부가이미지15 타입:varchar
				dto.setImg_path16(rs.getString("IMG_PATH16")); // 부가이미지16 타입:varchar
				dto.setImg_path17(rs.getString("IMG_PATH17")); // 부가이미지17 타입:varchar
				dto.setImg_path18(rs.getString("IMG_PATH18")); // 부가이미지18 타입:varchar
				dto.setImg_path19(rs.getString("IMG_PATH19")); // 부가이미지19 타입:varchar
				dto.setImg_path20(rs.getString("IMG_PATH20")); // 부가이미지20 타입:varchar
				dto.setImg_path21(rs.getString("IMG_PATH21")); // 부가이미지21 타입:varchar
				dto.setImg_path22(rs.getString("IMG_PATH22")); // 부가이미지22 타입:varchar
				dto.setImg_path23(rs.getString("IMG_PATH23")); // 인증서이미지 타입:varchar
				dto.setImg_path24(rs.getString("IMG_PATH24")); // 수입면장이미지 타입:varchar
				dto.setGoods_remarks(rs.getString("GOODS_REMARKS")); // 상품상세설명 타입:varchar
				dto.setCertno(rs.getString("CERTNO")); // 인증번호 타입:varchar
				dto.setAvlst_dm(rs.getString("AVLST_DM")); // 인증유효 시작일 타입:varchar
				dto.setAvled_dm(rs.getString("AVLED_DM")); // 인증유효 마지막일 타입:varchar
				dto.setIssuedate(rs.getString("ISSUEDATE")); // 발급일자 타입:varchar
				dto.setCertdate(rs.getString("CERTDATE")); // 인증일자 타입:varchar
				dto.setCert_agency(rs.getString("CERT_AGENCY")); // 인증기관 타입:varchar
				dto.setCertfield(rs.getString("CERTFIELD")); // 인증분야 타입:varchar
				dto.setMaterial(rs.getString("MATERIAL")); // 식품재료/원산지 타입:varchar
				dto.setStock_use_yn(rs.getString("STOCK_USE_YN")); // 재고관리사용여부 타입:varchar
				dto.setOpt_type(rs.getString("OPT_TYPE")); // 옵션수정여부 타입:varchar
				dto.setProp_edit_yn(rs.getString("PROP_EDIT_YN")); // 속성수정여부 타입:varchar
				dto.setProp1_cd(rs.getString("PROP1_CD")); // 속성분류코드 타입:varchar
				dto.setProp_val1(rs.getString("PROP_VAL1")); // 속성값1 타입:varchar
				dto.setProp_val2(rs.getString("PROP_VAL2")); // 속성값2 타입:varchar
				dto.setProp_val3(rs.getString("PROP_VAL3")); // 속성값3 타입:varchar
				dto.setProp_val4(rs.getString("PROP_VAL4")); // 속성값4 타입:varchar
				dto.setProp_val5(rs.getString("PROP_VAL5")); // 속성값5 타입:varchar
				dto.setProp_val6(rs.getString("PROP_VAL6")); // 속성값6 타입:varchar
				dto.setProp_val7(rs.getString("PROP_VAL7")); // 속성값7 타입:varchar
				dto.setProp_val8(rs.getString("PROP_VAL8")); // 속성값8 타입:varchar
				dto.setProp_val9(rs.getString("PROP_VAL9")); // 속성값9 타입:varchar
				dto.setProp_val10(rs.getString("PROP_VAL10")); // 속성값10 타입:varchar
				dto.setProp_val11(rs.getString("PROP_VAL11")); // 속성값11 타입:varchar
				dto.setProp_val12(rs.getString("PROP_VAL12")); // 속성값12 타입:varchar
				dto.setProp_val13(rs.getString("PROP_VAL13")); // 속성값13 타입:varchar
				dto.setProp_val14(rs.getString("PROP_VAL14")); // 속성값14 타입:varchar
				dto.setProp_val15(rs.getString("PROP_VAL15")); // 속성값15 타입:varchar
				dto.setProp_val16(rs.getString("PROP_VAL16")); // 속성값16 타입:varchar
				dto.setProp_val17(rs.getString("PROP_VAL17")); // 속성값17 타입:varchar
				dto.setProp_val18(rs.getString("PROP_VAL18")); // 속성값18 타입:varchar
				dto.setProp_val19(rs.getString("PROP_VAL19")); // 속성값19 타입:varchar
				dto.setProp_val20(rs.getString("PROP_VAL20")); // 속성값20 타입:varchar
				dto.setProp_val21(rs.getString("PROP_VAL21")); // 속성값21 타입:varchar
				dto.setProp_val22(rs.getString("PROP_VAL22")); // 속성값22 타입:varchar
				dto.setProp_val23(rs.getString("PROP_VAL23")); // 속성값23 타입:varchar
				dto.setProp_val24(rs.getString("PROP_VAL24")); // 속성값24 타입:varchar
				dto.setProp_val25(rs.getString("PROP_VAL25")); // 속성값25 타입:varchar
				dto.setProp_val26(rs.getString("PROP_VAL26")); // 속성값26 타입:varchar
				dto.setProp_val27(rs.getString("PROP_VAL27")); // 속성값27 타입:varchar
				dto.setProp_val28(rs.getString("PROP_VAL28")); // 속성값28 타입:varchar
				dto.setPack_code_str(rs.getString("PACK_CODE_STR")); // 추가상품그룹코드 타입:varchar
				dto.setGoods_nm_en(rs.getString("GOODS_NM_EN")); // 영문 상품명 타입:varchar
				dto.setGoods_nm_pr(rs.getString("GOODS_NM_PR")); // 출력 상품명 타입:varchar
				dto.setGoods_remarks2(rs.getString("GOODS_REMARKS2")); // 추가 상품상세설명_1 타입:varchar
				dto.setGoods_remarks3(rs.getString("GOODS_REMARKS3")); // 추가 상품상세설명_2 타입:varchar
				dto.setGoods_remarks4(rs.getString("GOODS_REMARKS4")); // 추가 상품상세설명_3 타입:varchar
				dto.setImportno(rs.getString("IMPORTNO")); // 수입신고번호 타입:varchar
				dto.setGoods_cost2(rs.getString("GOODS_COST2")); // 원가2 타입:varchar
				dto.setOrigin2(rs.getString("ORIGIN2")); // 원산지 상세지역 타입:varchar
				dto.setExpire_dm(rs.getString("EXPIRE_DM")); // 유효일자 타입:varchar
				dto.setSupply_save_yn(rs.getString("SUPPLY_SAVE_YN")); // 합포제외여부 타입:varchar
				dto.setDescrition(rs.getString("DESCRITION")); // 관리자메모 타입:varchar
				dto.setShopprodno(rs.getString("SHOPPRODNO")); // 완료후쇼핑몰코드(등록수정을한폼으로진행하기위해) 타입:varchar
				dto.setInsertdt(rs.getString("INSERTDT")); // 입력일자 타입:varchar
				dto.setInsertid(rs.getString("INSERTID")); // 입력자id 타입:varchar
				dto.setModifydt(rs.getString("MODIFYDT")); // 수정일자 타입:varchar
				dto.setModifyid(rs.getString("MODIFYID")); // 수정자ID 타입:varchar

				
			
				// ----- class nm 추가 -----
				dto.setClass_nm1(rs.getString("CLASS_NM1")); // 대분류
				dto.setClass_nm2(rs.getString("CLASS_NM2")); // 중분류
				dto.setClass_nm3(rs.getString("CLASS_NM3")); // 소분류
				dto.setClass_nm4(rs.getString("CLASS_NM4")); // 세분류

				dto.setDelv_type_nm(rs.getString("DELV_TYPE_NM"));
				dto.setGoods_gubun_nm(rs.getString("GOODS_GUBUN_NM"));
				dto.setStatus_nm(rs.getString("STATUS_NM"));
				dto.setFlag(rs.getString("FLAG"));
				
				
				if(YDMASessonUtil.getLoginType() == YDMASessonUtil.DOMESIN_LOGIN_TYPE) {
					String classcd3=	  rs.getString("CLASS_CD3");
					String full_cateName = DomesinSessonUtil.get().getFullCateName(classcd3);
					
						// ----- class nm 추가 -----
					    dto.setCode(classcd3);
						dto.setClass_nm1(full_cateName); // 대분류
							
				}
				
				
				/********* addtinon ************/
//				ShopCatInfDto ShopCatInfDto = new ShopCatInfDto();
//
//				ShopCatInfDto.setCompno(rs.getString("COMPNO"));
//				ShopCatInfDto.setShopcd(rs.getString("SHOPCD"));
//				ShopCatInfDto.setShopcatno(rs.getString("SHOPCATNO"));
//				ShopCatInfDto.setShopcatnm(rs.getString("SHOPCATNM"));
//				ShopCatInfDto.setShopcatsitenm(rs.getString("SHOPCATSITENM"));
//				ShopCatInfDto.setShoplagcatcd(rs.getString("SHOPLAGCATCD"));
//				ShopCatInfDto.setShopmidcatcd(rs.getString("SHOPMIDCATCD"));
//				ShopCatInfDto.setShopsmlcatcd(rs.getString("SHOPSMLCATCD"));
//				ShopCatInfDto.setShopdetcatcd(rs.getString("SHOPDETCATCD"));
//				ShopCatInfDto.setServiceprod(rs.getString("SERVICEPROD"));
//				ShopCatInfDto.setUse_yn(rs.getString("USE_YN"));
//				ShopCatInfDto.setShopgeneral(rs.getString("SHOPGENERAL"));
//				ShopCatInfDto.setShopid(rs.getString("SHOPID"));
//				ShopCatInfDto.setShopcommis(rs.getString("SHOPCOMMIS"));
//				ShopCatInfDto.setInsertdt(rs.getString("INSERTDT"));
//				ShopCatInfDto.setModifydt(rs.getString("MODIFYDT"));
//
//				dto.setShopCatInDto(ShopCatInfDto);
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

	public void setProductMstShopcode(List<List<String>> contents, List<List<String>> allContents) throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodinfo 송신쇼핑몰번호 업데이트
			String sql_prodmst = " Update shopprodin set MODIFYDT = ? , " + "					     MODIFYID = ? "
					+ "  where SENDSEQ = ? " + "    and compno = ? ";

			PreparedStatement pstmt = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt);
			// [SUCCESS, 수정, sabang_prodcdcd, ydms_prodcd]
			// [FAIL, 수정, ydms_prodcd]
			for (int i = 0; i < contents.size(); i++) {
				List<String> alllist = allContents.get(i);
				int idx = 0;
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());

				pstmt.setString(++idx, alllist.get(0));
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}

			pstmt.executeBatch();
			pstmt.clearParameters();
			// pstmt.clearBatch();// Batch 초기화

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public void setProductErrInsert(ShopProductSendDto dtos) throws Exception {

		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = "INSERT INTO shopproderr\r\n"
					+ " ( COMPNO, SHOPCD, SHOPSEQ, PRODSEQ, SEQ, SHOPCATNO, DESCRITION, INSERTDT, INSERTID, FAILCONTENT )\r\n"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
//					"COMPNO,\r\n" + 
//					"? ,  -- SHOPCD\r\n" + 
//					"? , -- ID 순번\r\n" + 
//					"PRODSEQ,\r\n" + 
////					"'' ,  -- 쇼핑몰 완료 번호\r\n" + 
//					"?,   -- 부가정보코드 ..   SHOPDEL SEQ\r\n" + 
//					"?,   -- 카테고리정보코드 ..   SHOPCATINF SHOPCATNO\r\n" + 
//					"GOODS_NM, \r\n" + 
//					"GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, \r\n" + 
//					"COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, CLASS_CD2, CLASS_CD3, CLASS_CD4, PARTNER_ID, DPARTNER_ID, MAKER, \r\n" + 
//					"ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, `STATUS`, DELIV_ABLE_REGION, TAX_YN, DELV_TYPE, DELV_COST, BANPUM_AREA, GOODS_COST,\r\n" + 
//					"GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM, CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3,\r\n" + 
//					"IMG_PATH4, IMG_PATH5, IMG_PATH6, IMG_PATH7, IMG_PATH8, IMG_PATH9, IMG_PATH10, IMG_PATH11, IMG_PATH12, IMG_PATH13, IMG_PATH14,\r\n" + 
//					"IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22, IMG_PATH23, IMG_PATH24, \r\n" + 
//					"GOODS_REMARKS, CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, \r\n" + 
//					"PROP1_CD, PROP_VAL1, PROP_VAL2, PROP_VAL3, PROP_VAL4, PROP_VAL5, PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10,\r\n" + 
//					"PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18, PROP_VAL19, PROP_VAL20,\r\n" + 
//					"PROP_VAL21, PROP_VAL22, PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN, \r\n" + 
//					"GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, GOODS_COST2, ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN,\r\n" + 
//					"DESCRITION,  DATE_FORMAT(NOW(), '%Y-%m-%d %h:%m:%s'), ?  ,?   -- 사용자 아이디.. \r\n" +  // 실패사유 
//					"FROM SHOPPRODINFO \r\n" + 
//					"WHERE COMPNO = ? AND PRODSEQ = ? AND COMPAYNY_GOODS_CD = ?";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			List<ShopProductDto> prodList = dtos.getShopProductDto();
			ShopProductAdditionDto shopProductAdditionDto = dtos.getProductAdditionDto();
			ShoppingMallDetailDto shoppingMallDetailDto = dtos.getShoppingMallDetailDto();

			for (ShopProductDto dto : prodList) {
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, shoppingMallDetailDto.getShopcd());
				pstmt.setString(3, shoppingMallDetailDto.getShopseq());// shopdtl코드
				pstmt.setString(4, dto.getProdseq());
				pstmt.setString(5, shopProductAdditionDto.getSeq());// 부가정보
				if (dto.getShopCatInDto() == null) {
					pstmt.setString(6, "");
				} else {
					pstmt.setString(6, dto.getShopCatInDto().getShopcatno());
				}
				pstmt.setString(7, "");// 관리자메모
				pstmt.setString(8, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(9, YDMASessonUtil.getUserInfo().getUserId());
				pstmt.setString(10, dto.getResult_text());
				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}

			System.out.println("[setProductErrInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	/*
	 * 상품 수정
	 */
	public void setProductInUpate(ShopProductSendDto dtos) throws Exception {

		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;

		try {
			ShopProductDto dto = (ShopProductDto) dtos.getShopProductDto();

			if (dtos.getIsshopprodin()) { // 송신된(SHOPPRODIN 을 사용하여 업데이트 함

				// UPDATE : SHOPPRODIN(shopprodno-쇼핑몰상품코드)
				String sql = "update shopprodin " + "   set shopprodno = ?, MODIFYDT = ?, MODIFYID = ? "
						+ " where sendseq= ? " + "   and compno = ? ";

				sql = sql.toUpperCase();
				pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, dto.getShopprodno());
				pstmt.setString(2, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(3, YDMASessonUtil.getUserInfo().getUserId());

				pstmt.setString(4, dto.getSendseq());
				pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());

				cnt = pstmt.executeUpdate();

			} else {
				// 송실할(SHOPPRODINFO 을 사용하여 shopprodin예 업데이트 함
				cnt = setProductInUpdate(dto, dto.getSendseq(), "shopprodin");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	/*
	 * 상품 등록..
	 */
	public void setProductInInsert(ShopProductSendDto dtos) throws Exception {

		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			// ShopProdIn process
			int result = setShopProductInInsert(dtos);

			// ShopOptProdIn process
			if (result > 0) {
				String sql = "SELECT 1 FROM Shop_OptProdInfo where prodseq = ? ";
				sql = sql.toUpperCase();
				pstmt = connection.prepareStatement(sql);

				List<ShopProductDto> prodList = dtos.getShopProductDto();
				ShoppingMallDetailDto shoppingMallDetailDto = dtos.getShoppingMallDetailDto();
				for (ShopProductDto dto : prodList) {

					int count = 0;
					pstmt.setString(1, dto.getProdseq());
					rs = pstmt.executeQuery();

					if (rs.next()) {
						count = rs.getInt(1);
					}
					if (count > 0) {
						setShopOptProductInInsert(dto, shoppingMallDetailDto.getShopcd(),
								shoppingMallDetailDto.getShopseq());
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public int setShopProductInInsert(ShopProductSendDto dtos) throws Exception {

		int result = 0;
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			// prodin에 인설트하기
			String sql = "INSERT INTO shopprodin\r\n" + "(\r\n"
					+ "	COMPNO, SHOPCD, SHOPSEQ, PRODSEQ, SHOPPRODNO, SEQ, SHOPCATNO,GOODS_NM, GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, \r\n"
					+ "	COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, CLASS_CD2, CLASS_CD3, CLASS_CD4, PARTNER_ID, DPARTNER_ID, MAKER,\r\n"
					+ "	ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, `STATUS`, DELIV_ABLE_REGION, TAX_YN, DELV_TYPE, DELV_COST, BANPUM_AREA, GOODS_COST, \r\n"
					+ "	GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM, CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3, \r\n"
					+ "	IMG_PATH4, IMG_PATH5, IMG_PATH6, IMG_PATH7, IMG_PATH8, IMG_PATH9, IMG_PATH10, IMG_PATH11, IMG_PATH12, IMG_PATH13, IMG_PATH14,\r\n"
					+ "	IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22, IMG_PATH23, IMG_PATH24,\r\n"
					+ "	GOODS_REMARKS, CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, \r\n"
					+ "	PROP1_CD, PROP_VAL1, PROP_VAL2, PROP_VAL3, PROP_VAL4, PROP_VAL5, PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10, \r\n"
					+ "	PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18, PROP_VAL19, PROP_VAL20,\r\n"
					+ "	PROP_VAL21, PROP_VAL22, PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN,\r\n"
					+ "	GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, GOODS_COST2, ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN, \r\n"
					+ "	DESCRITION, INSERTDT, INSERTID\r\n" + ")\r\n" + "SELECT \r\n" + "COMPNO,\r\n"
					+ "? ,  -- SHOPCD\r\n" + "? , -- ID 순번\r\n" + "PRODSEQ,\r\n" + "? ,  -- 쇼핑몰 완료 번호\r\n"
					+ "?,   -- 부가정보코드 ..   SHOPDEL SEQ\r\n" + "?,   -- 카테고리정보코드 ..   SHOPCATINF SHOPCATNO\r\n"
					+ "GOODS_NM, \r\n" + "GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, \r\n"
					+ "COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, CLASS_CD2, CLASS_CD3, CLASS_CD4, PARTNER_ID, DPARTNER_ID, MAKER, \r\n"
					+ "ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, `STATUS`, DELIV_ABLE_REGION, TAX_YN, DELV_TYPE, DELV_COST, BANPUM_AREA, GOODS_COST,\r\n"
					+ "GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM, CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3,\r\n"
					+ "IMG_PATH4, IMG_PATH5, IMG_PATH6, IMG_PATH7, IMG_PATH8, IMG_PATH9, IMG_PATH10, IMG_PATH11, IMG_PATH12, IMG_PATH13, IMG_PATH14,\r\n"
					+ "IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22, IMG_PATH23, IMG_PATH24, \r\n"
					+ "GOODS_REMARKS, CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, \r\n"
					+ "PROP1_CD, PROP_VAL1, PROP_VAL2, PROP_VAL3, PROP_VAL4, PROP_VAL5, PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10,\r\n"
					+ "PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18, PROP_VAL19, PROP_VAL20,\r\n"
					+ "PROP_VAL21, PROP_VAL22, PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN, \r\n"
					+ "GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, GOODS_COST2, ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN,\r\n"
					+ "DESCRITION, NOW(), ?   -- 사용자 아이디.. \r\n" + "FROM SHOPPRODINFO \r\n"
					+ "WHERE COMPNO = ? AND PRODSEQ = ? AND COMPAYNY_GOODS_CD = ?";
			sql = sql.toUpperCase();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			List<ShopProductDto> prodList = dtos.getShopProductDto();
			ShopProductAdditionDto shopProductAdditionDto = dtos.getProductAdditionDto();
			ShoppingMallDetailDto shoppingMallDetailDto = dtos.getShoppingMallDetailDto();

			for (ShopProductDto dto : prodList) {
				pstmt.setString(1, shoppingMallDetailDto.getShopcd());
				pstmt.setString(2, shoppingMallDetailDto.getShopseq());
				pstmt.setString(3, dto.getShopprodno());
				pstmt.setString(4, shopProductAdditionDto.getSeq());
				pstmt.setString(5, dto.getShopCatInDto().getShopcatno());
				pstmt.setString(6, YDMASessonUtil.getUserInfo().getUserId());

				pstmt.setString(7, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(8, dto.getProdseq());
				pstmt.setString(9, dto.getCompayny_goods_cd());

				result++;
				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}

			System.out.println("[setShopProductInInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public void setShopOptProductInInsert(ShopProductDto dto, String shopcd, String shopseq) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		try {

			connection = DBCPInit.getInstance().getConnection();

			// ShopProdIn에 인서트하기
			String sql = "INSERT INTO shop_optprodin" + "(SENDSEQ,SHOPPRODNO,SHOPPRODSKU,SHOPPRODSKU_NM,\r\n"
					+ " PRODSEQ,SKUSEQ,COMPNO,OPTPRODCD,OPTPRODNM,OPTSPECDES,OPTEA,OPTSALE,OPTSALEOUT,OPTNOTUSE,OPTSAFESTOCK,OPTVERTSTOCK,OPTADDAMT,\r\n"
					+ " OPTDELYN,BARCODE,INSERTDT,INSERTID) \r\n" + "SELECT " + "? , -- ShopProdIn 자동부여 일련번호 \r\n"
					+ "? ,  -- 쇼핑몰상품코드 \r\n" + "? ,  -- 쇼핑몰상품단품코드 \r\n" + "? ,  -- 쇼핑몰상품단품명칭 \r\n"
					+ "PRODSEQ,SKUSEQ,COMPNO,OPTPRODCD,OPTPRODNM,OPTSPECDES,OPTEA,OPTSALE,OPTSALEOUT,OPTNOTUSE,OPTSAFESTOCK,OPTVERTSTOCK,OPTADDAMT,\r\n"
					+ "OPTDELYN,BARCODE,?,? \r\n" + "  FROM shop_optprodinfo\r\n" + " WHERE prodseq=?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			// ShopProdInd의 자동부여 일련번호 가저오기
			int sendseq = getSendseqFromShopProdin(dto, shopcd, shopseq);

			if (sendseq > 0) {
				pstmt.setInt(1, sendseq);
				pstmt.setString(2, dto.getShopprodno());
				pstmt.setString(3, "");
				pstmt.setString(4, "");

				pstmt.setString(5, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(6, YDMASessonUtil.getUserInfo().getUserId());

				pstmt.setString(7, dto.getProdseq());

				pstmt.executeUpdate();
			}

			System.out.println("[setShopOptProductInInsert]" + pstmt.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public int getSendseqFromShopProdin(ShopProductDto dto, String shopcd, String shopseq) throws Exception {

		int sendseq = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT sendseq  FROM ShopProdin \r\n" + " where compno= ?  \r\n" + "   and shopcd=?  \r\n"
					+ "   and shopseq=?  \r\n" + "   and prodseq=?  \r\n" + "   and shopprodno=?  \r\n";
			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setString(3, shopseq);
			pstmt.setString(4, dto.getProdseq());
			pstmt.setString(5, dto.getShopprodno());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				sendseq = rs.getInt(1);
			}

			System.out.println("[getSendseqFromShopProdin]" + pstmt.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return sendseq;

	}

	public List<ShopProductDto> getShopProdInList(int option, String prodFrom, String prodTo, String lagcateg,
			String midcateg, String smlcateg, String detcateg, String shopnm, String shopid, String purch, String logis,
			String orderIvtr, String shopfee, String sex, String season, String prod, String prodststsd,
			String prodststpr, String price, String prodnm, String yorN, String bundle, String searchgubun,
			String search) throws Exception {
		// List<List<String>> contents = new ArrayList<>();
		List<ShopProductDto> list = new ArrayList<ShopProductDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT IFNULL(A.shopcd,'') , IFNULL(A.shopseq,''), IFNULL(A.sendseq,'0'), "
					+ "		 IFNULL(A.INSERTDT,''),IFNULL(A.MODIFYDT,''), IFNULL(A.seq,'0'),IFNULL(A.SHOPPRODNO,''),"
					+ "		 IFNULL(A.prodseq,''), "
					+ "		 IFNULL(A.COMPAYNY_GOODS_CD,''), IFNULL(A.GOODS_NM,''), IFNULL(A.CLASS_CD1,''), "
					+ "		 IFNULL(A.CLASS_CD2,''), IFNULL(A.CLASS_CD3,''), IFNULL(A.CLASS_CD4,''),IFNULL(A.GOODS_GUBUN,''), "
					+ "		 IFNULL(A.PARTNER_ID,'') ,IFNULL(A.MODEL_NM,''), IFNULL(A.BRAND_NM,''), IFNULL(A.MAKER,''), "
					+ "		 IFNULL(A.DELV_TYPE,''), IFNULL(A.DELV_COST,'0'), IFNULL(A.STATUS,''), "
					+ "		 IFNULL(A.GOODS_PRICE,'0'), IFNULL(A.GOODS_COST,'0'),IFNULL(B.SHOPPINGID,''), "
					+ "		 IFNULL(B.PASSWORD,'') " + "  FROM shopprodin AS A "
					+ "		 INNER JOIN shopdtl AS B ON A.COMPNO = B.COMPNO AND A.SHOPCD = B.SHOPCD AND A.SHOPSEQ = B.SHOPSEQ "
					+ " where A.compno = ? ";

			if (option == 0) {
				sql += " and A.insertdt >= ? and A.insertdt <= ? ";
			} else {
				sql += " and A.MODIFYDT >= ? and A.MODIFYDT <= ? ";
			}
			// 카테고리분류코드넣어야함
			sql += " ORDER by A.INSERTDT desc ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, prodFrom);
			pstmt.setString(3, prodTo);

			System.out.println("[getShopProdInList]" + pstmt.toString());
			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ShopProductDto dto = new ShopProductDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setSendstats("연동생성");// 임의로만듬
				dto.setShopcd(rs.getString(++i));
				dto.setShopseq(rs.getString(++i));
				dto.setSendseq(rs.getString(++i));
				dto.setInsertdt(rs.getString(++i));
				// 재송신일시
				dto.setModifydt(rs.getString(++i));
				dto.setSeq(rs.getString(++i));
				// 쇼핑몰카테고리코드
				dto.setShopprodno(rs.getString(++i));
				dto.setProdseq(rs.getString(++i));
				dto.setCompayny_goods_cd(rs.getString(++i));
				dto.setGoods_nm(rs.getString(++i));
				dto.setClass_cd1(rs.getString(++i));
				dto.setClass_cd2(rs.getString(++i));
				dto.setClass_cd3(rs.getString(++i));
				dto.setClass_cd4(rs.getString(++i));
				dto.setGoods_gubun(rs.getString(++i));
				dto.setPartner_id(rs.getString(++i));
				dto.setModel_nm(rs.getString(++i));
				dto.setBrand_nm(rs.getString(++i));
				dto.setMaker(rs.getString(++i));
				dto.setDelv_type(rs.getString(++i));
				dto.setDelv_cost(rs.getString(++i));
				dto.setStatus(rs.getString(++i));
				dto.setGoods_price(rs.getString(++i));
				dto.setGoods_cost(rs.getString(++i));
				// 판매기간
				// 체크박스3개
				dto.setShopid(rs.getString(++i));// 아이디
				dto.setShoppw(rs.getString(++i));// 비밀번호
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

	public void setProductErrInsert(List<List<String>> contents, List<List<String>> allContents, List<String> dtllist,
			List<String> idlist) throws Exception {

		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = " insert into shopproderr (compno, SHOPCD, SHOPSEQ,  PRODSEQ, seq,GOODS_NM, GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, "
					+ " CLASS_CD2,CLASS_CD3, CLASS_CD4, PARTNER_ID,  DPARTNER_ID, MAKER, ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, STATUS, DELIV_ABLE_REGION, TAX_YN,DELV_TYPE,DELV_COST,BANPUM_AREA, "
					+ " GOODS_COST,GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM,  CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3, IMG_PATH4, IMG_PATH5, IMG_PATH6,IMG_PATH7,IMG_PATH8, "
					+ " IMG_PATH9,IMG_PATH10, IMG_PATH11, IMG_PATH12,  IMG_PATH13, IMG_PATH14, IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22,IMG_PATH23,IMG_PATH24, "
					+ " GOODS_REMARKS,CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, PROP1_CD, PROP_VAL1,PROP_VAL2,PROP_VAL3, PROP_VAL4, "
					+ " PROP_VAL5,PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10, PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18,PROP_VAL19,PROP_VAL20, PROP_VAL21, "
					+ " PROP_VAL22,PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN, GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, "
					+ " GOODS_COST2,ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN,DESCRITION, FAILCONTENT, INSERTDT,INSERTID ) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,"// 23
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, "// 86
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ) ";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (int i = 0; i < contents.size(); i++) {
				List<String> alllist = allContents.get(i);
				int idx = 0;
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++idx, idlist.get(1));
				pstmt.setString(++idx, idlist.get(2));
				pstmt.setString(++idx, alllist.get(0));
				pstmt.setString(++idx, dtllist.get(2));
				for (int k = 3; k <= 114; k++) {
					if (k == 114) {
						pstmt.setString(++idx, contents.get(i).get(1));
					} else {
						pstmt.setString(++idx, alllist.get(k - 1));
					}
				}
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());
				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			System.out.println("[setProductInInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public List<ShopProductDto> getShopProdErrorList(Map<String, String> params) throws Exception {
		// List<List<String>> contents = new ArrayList<>();
		List<ShopProductDto> list = new ArrayList<ShopProductDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = "WITH CATEALL AS(                                                                 \r\n"
					+ "		SELECT                                                                    \r\n"
					+ "		   IFNULL(A.CODE,'')     AS CLASS_CD1 ,                                   \r\n"
					+ "			IFNULL(A.CATEGORY,'') AS CLASS_NM1 ,                                  \r\n"
					+ "			IFNULL(RIGHT(B.CODE,3),'')     AS CLASS_CD2  ,                        \r\n"
					+ "			IFNULL(B.CATEGORY,'') AS CLASS_NM2 ,                                  \r\n"
					+ "			IFNULL(RIGHT(C.CODE,3),'')     AS CLASS_CD3,                          \r\n"
					+ "			IFNULL(C.CATEGORY,'') AS CLASS_NM3,                                   \r\n"
					+ "			IFNULL(RIGHT(D.CODE,3),'')     AS CLASS_CD4 ,                         \r\n"
					+ "			IFNULL(D.CATEGORY,'') AS CLASS_NM4                                    \r\n"
					+ "		FROM CATEGLARGE A left JOIN CATEGMIDIUM B                                      \r\n"
					+ "			ON A.COMPNO=B.COMPNO AND A.CODE = B.LRGCODE left JOIN CATEGSMALL C         \r\n"
					+ "			ON A.COMPNO = C.COMPNO AND B.CODE = C.MIDCODE left JOIN CATEGDETAIL D ON   \r\n"
					+ String.format(" A.COMPNO = D.COMPNO AND C.CODE = D.SMLCODE WHERE A.COMPNO = '%s'  \r\n",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ "		 )                                                                        \r\n"
					+ " SELECT SHOPERR.*  , \r\n" + " IFNULL(CATE.CLASS_NM1, '')  CLASS_NM1, \r\n"
					+ " IFNULL(CATE.CLASS_NM2, '')  CLASS_NM2, \r\n" + " IFNULL(CATE.CLASS_NM3, '')  CLASS_NM3, \r\n"
					+ " IFNULL(CATE.CLASS_NM4, '')  CLASS_NM4, \r\n" + "(CASE                                   \r\n"
					+ "	     WHEN GOODS_GUBUN = '1'         \r\n" + "	     THEN '위탁상품'                \r\n"
					+ "	     WHEN GOODS_GUBUN = '2'         \r\n" + "	     THEN '제조상품'                \r\n"
					+ "	     WHEN GOODS_GUBUN = '3'         \r\n" + "	     THEN '사업상품'                \r\n"
					+ "	     WHEN GOODS_GUBUN = '4'         \r\n" + "	     THEN '직영상품'                \r\n"
					+ "	     ELSE ''                        \r\n" + "	  END )AS GOODS_GUBUN_NM,            \r\n"
					+ "(CASE                            \r\n" + "	     WHEN STATUS = '1'       \r\n"
					+ "	     THEN '대기중'           \r\n" + "	     WHEN STATUS = '2'       \r\n"
					+ "	     THEN '공급중'           \r\n" + "	     WHEN STATUS = '3'       \r\n"
					+ "	     THEN '일시중지'         \r\n" + "	     WHEN STATUS = '4'       \r\n"
					+ "	     THEN '완전품절'         \r\n" + "	      WHEN STATUS = '5'      \r\n"
					+ "	     THEN '미사용'           \r\n" + "	      WHEN STATUS = '6'      \r\n"
					+ "	     THEN '삭제'             \r\n" + "	     ELSE ''                 \r\n"
					+ "	  END )AS STATUS_NM,         \r\n" + "(CASE                             \r\n"
					+ "	     WHEN DELV_TYPE = '1'     \r\n" + "	     THEN '무료'              \r\n"
					+ "	     WHEN DELV_TYPE = '2'     \r\n" + "	     THEN '착불'              \r\n"
					+ "	     WHEN DELV_TYPE = '3'     \r\n" + "	     THEN '선결제'            \r\n"
					+ "	     WHEN DELV_TYPE = '4'     \r\n" + "	     THEN '착불/선결제'       \r\n"
					+ "	     ELSE ''                  \r\n" + "	  END )AS DELV_TYPE_NM       \r\n"
					+ " FROM                             \r\n" + "(SELECT                           \r\n"
					+ " CONCAT(IFNULL(B.CLASS_CD1,''), IFNULL(B.CLASS_CD2,''), IFNULL(B.CLASS_CD3,''), IFNULL(B.CLASS_CD4,'')) AS CODE, \r\n"
					+ "(SELECT SHOPNM FROM shopinfo WHERE SHOPCD = A.SHOPCD) AS SHOPNM, \r\n"
					+ "( SELECT SHOPPINGID FROM shopdtl WHERE SHOPCD = A.SHOPCD AND COMPNO = A.COMPNO AND SHOPSEQ = A.SHOPSEQ) AS  SHOPUSERID,"
					+ String.format(" YWM_FUNC_BOSSPRODCD(%s,B.COMPAYNY_GOODS_CD) AS IMG,\r\n",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ " IFNULL(A.SENDSEQ,'' ) AS SENDSEQ,\r\n" + " IFNULL(A.COMPNO,'' ) AS COMPNO,\r\n"
					+ " IFNULL(A.SHOPCD,'' ) AS SHOPCD,\r\n" + " IFNULL(A.SHOPSEQ,'' ) AS SHOPSEQ,\r\n"
					+ " IFNULL(A.PRODSEQ,'' ) AS PRODSEQ,\r\n" +
//					" IFNULL(A.SHOPPRODNO,'' ) AS SHOPPRODNO,\r\n" + 
					" IFNULL(A.SEQ,'' ) AS SEQ,\r\n" + " IFNULL(A.SHOPCATNO,'' ) AS SHOPCATNO,\r\n";

//			if(params.get("GOODS_NM").equals("1")) {
//				sql	= sql + " IFNULL(B.GOODS_NM,'' ) AS GOODS_NM,\r\n";
//			} else  {
			sql = sql + " IFNULL(B.GOODS_NM,'' ) AS GOODS_NM,\r\n";
//			}

			sql = sql + " IFNULL(B.GOODS_KEYWORD,'' ) AS GOODS_KEYWORD,\r\n"
					+ " IFNULL(B.MODEL_NM,'' ) AS MODEL_NM,\r\n" + " IFNULL(B.MODEL_NO,'' ) AS MODEL_NO,\r\n"
					+ " IFNULL(B.BRAND_NM,'' ) AS BRAND_NM,\r\n"
					+ " IFNULL(B.COMPAYNY_GOODS_CD,'' ) AS COMPAYNY_GOODS_CD,\r\n"
					+ " IFNULL(B.GOODS_SEARCH,'' ) AS GOODS_SEARCH,\r\n"
					+ " IFNULL(B.GOODS_GUBUN,'' ) AS GOODS_GUBUN,\r\n" + " IFNULL(B.CLASS_CD1,'' ) AS CLASS_CD1,\r\n"
					+ " IFNULL(B.CLASS_CD2,'' ) AS CLASS_CD2,\r\n" + " IFNULL(B.CLASS_CD3,'' ) AS CLASS_CD3,\r\n"
					+ " IFNULL(B.CLASS_CD4,'' ) AS CLASS_CD4,\r\n" + " IFNULL(B.PARTNER_ID,'' ) AS PARTNER_ID,\r\n"
					+ " IFNULL(B.DPARTNER_ID,'' ) AS DPARTNER_ID,\r\n" + " IFNULL(B.MAKER,'' ) AS MAKER,\r\n"
					+ " IFNULL(B.ORIGIN,'' ) AS ORIGIN,\r\n" + " IFNULL(B.MAKE_YEAR,'' ) AS MAKE_YEAR,\r\n"
					+ " IFNULL(B.MAKE_DM,'' ) AS MAKE_DM,\r\n" + " IFNULL(B.GOODS_SEASON,'' ) AS GOODS_SEASON,\r\n"
					+ " IFNULL(B.SEX,'' ) AS SEX,\r\n" + " IFNULL(B.STATUS,'' ) AS STATUS,\r\n"
					+ " IFNULL(B.DELIV_ABLE_REGION,'' ) AS DELIV_ABLE_REGION,\r\n"
					+ " IFNULL(B.TAX_YN,'' ) AS TAX_YN,\r\n" + " IFNULL(B.DELV_TYPE,'' ) AS DELV_TYPE,\r\n"
					+ " IFNULL(B.DELV_COST,'' ) AS DELV_COST,\r\n" + " IFNULL(B.BANPUM_AREA,'' ) AS BANPUM_AREA,\r\n"
					+ " IFNULL(B.GOODS_COST,'' ) AS GOODS_COST,\r\n";

//			if(params.get("GOODS_PRICE").equals("1")) {
//				sql	= sql + " IFNULL(B.GOODS_PRICE,'' ) AS GOODS_PRICE,\r\n";
//			} else  {
			sql = sql + " IFNULL(B.GOODS_PRICE,'' ) AS GOODS_PRICE,\r\n";
//			} 

			sql = sql + " IFNULL(B.GOODS_CONSUMER_PRICE,'' ) AS GOODS_CONSUMER_PRICE,\r\n"
					+ " IFNULL(B.CHAR_1_NM,'' ) AS CHAR_1_NM,\r\n" + " IFNULL(B.CHAR_1_VAL,'' ) AS CHAR_1_VAL,\r\n"
					+ " IFNULL(B.CHAR_2_NM,'' ) AS CHAR_2_NM,\r\n" + " IFNULL(B.CHAR_2_VAL,'' ) AS CHAR_2_VAL,\r\n"
					+ " IFNULL(B.IMG_PATH,'' ) AS IMG_PATH,\r\n" + " IFNULL(B.IMG_PATH1,'' ) AS IMG_PATH1,\r\n"
					+ " IFNULL(B.IMG_PATH2,'' ) AS IMG_PATH2,\r\n" + " IFNULL(B.IMG_PATH3,'' ) AS IMG_PATH3,\r\n"
					+ " IFNULL(B.IMG_PATH4,'' ) AS IMG_PATH4,\r\n" + " IFNULL(B.IMG_PATH5,'' ) AS IMG_PATH5,\r\n"
					+ " IFNULL(B.IMG_PATH6,'' ) AS IMG_PATH6,\r\n" + " IFNULL(B.IMG_PATH7,'' ) AS IMG_PATH7,\r\n"
					+ " IFNULL(B.IMG_PATH8,'' ) AS IMG_PATH8,\r\n" + " IFNULL(B.IMG_PATH9,'' ) AS IMG_PATH9,\r\n"
					+ " IFNULL(B.IMG_PATH10,'' ) AS IMG_PATH10,\r\n" + " IFNULL(B.IMG_PATH11,'' ) AS IMG_PATH11,\r\n"
					+ " IFNULL(B.IMG_PATH12,'' ) AS IMG_PATH12,\r\n" + " IFNULL(B.IMG_PATH13,'' ) AS IMG_PATH13,\r\n"
					+ " IFNULL(B.IMG_PATH14,'' ) AS IMG_PATH14,\r\n" + " IFNULL(B.IMG_PATH15,'' ) AS IMG_PATH15,\r\n"
					+ " IFNULL(B.IMG_PATH16,'' ) AS IMG_PATH16,\r\n" + " IFNULL(B.IMG_PATH17,'' ) AS IMG_PATH17,\r\n"
					+ " IFNULL(B.IMG_PATH18,'' ) AS IMG_PATH18,\r\n" + " IFNULL(B.IMG_PATH19,'' ) AS IMG_PATH19,\r\n"
					+ " IFNULL(B.IMG_PATH20,'' ) AS IMG_PATH20,\r\n" + " IFNULL(B.IMG_PATH21,'' ) AS IMG_PATH21,\r\n"
					+ " IFNULL(B.IMG_PATH22,'' ) AS IMG_PATH22,\r\n" + " IFNULL(B.IMG_PATH23,'' ) AS IMG_PATH23,\r\n"
					+ " IFNULL(B.IMG_PATH24,'' ) AS IMG_PATH24,\r\n"
					+ " IFNULL(B.GOODS_REMARKS,'' ) AS GOODS_REMARKS,\r\n" + " IFNULL(B.CERTNO,'' ) AS CERTNO,\r\n"
					+ " IFNULL(B.AVLST_DM,'' ) AS AVLST_DM,\r\n" + " IFNULL(B.AVLED_DM,'' ) AS AVLED_DM,\r\n"
					+ " IFNULL(B.ISSUEDATE,'' ) AS ISSUEDATE,\r\n" + " IFNULL(B.CERTDATE,'' ) AS CERTDATE,\r\n"
					+ " IFNULL(B.CERT_AGENCY,'' ) AS CERT_AGENCY,\r\n" + " IFNULL(B.CERTFIELD,'' ) AS CERTFIELD,\r\n"
					+ " IFNULL(B.MATERIAL,'' ) AS MATERIAL,\r\n" + " IFNULL(B.STOCK_USE_YN,'' ) AS STOCK_USE_YN,\r\n"
					+ " IFNULL(B.OPT_TYPE,'' ) AS OPT_TYPE,\r\n" + " IFNULL(B.PROP_EDIT_YN,'' ) AS PROP_EDIT_YN,\r\n"
					+ " IFNULL(B.PROP1_CD,'' ) AS PROP1_CD,\r\n" + " IFNULL(B.PROP_VAL1,'' ) AS PROP_VAL1,\r\n"
					+ " IFNULL(B.PROP_VAL2,'' ) AS PROP_VAL2,\r\n" + " IFNULL(B.PROP_VAL3,'' ) AS PROP_VAL3,\r\n"
					+ " IFNULL(B.PROP_VAL4,'' ) AS PROP_VAL4,\r\n" + " IFNULL(B.PROP_VAL5,'' ) AS PROP_VAL5,\r\n"
					+ " IFNULL(B.PROP_VAL6,'' ) AS PROP_VAL6,\r\n" + " IFNULL(B.PROP_VAL7,'' ) AS PROP_VAL7,\r\n"
					+ " IFNULL(B.PROP_VAL8,'' ) AS PROP_VAL8,\r\n" + " IFNULL(B.PROP_VAL9,'' ) AS PROP_VAL9,\r\n"
					+ " IFNULL(B.PROP_VAL10,'' ) AS PROP_VAL10,\r\n" + " IFNULL(B.PROP_VAL11,'' ) AS PROP_VAL11,\r\n"
					+ " IFNULL(B.PROP_VAL12,'' ) AS PROP_VAL12,\r\n" + " IFNULL(B.PROP_VAL13,'' ) AS PROP_VAL13,\r\n"
					+ " IFNULL(B.PROP_VAL14,'' ) AS PROP_VAL14,\r\n" + " IFNULL(B.PROP_VAL15,'' ) AS PROP_VAL15,\r\n"
					+ " IFNULL(B.PROP_VAL16,'' ) AS PROP_VAL16,\r\n" + " IFNULL(B.PROP_VAL17,'' ) AS PROP_VAL17,\r\n"
					+ " IFNULL(B.PROP_VAL18,'' ) AS PROP_VAL18,\r\n" + " IFNULL(B.PROP_VAL19,'' ) AS PROP_VAL19,\r\n"
					+ " IFNULL(B.PROP_VAL20,'' ) AS PROP_VAL20,\r\n" + " IFNULL(B.PROP_VAL21,'' ) AS PROP_VAL21,\r\n"
					+ " IFNULL(B.PROP_VAL22,'' ) AS PROP_VAL22,\r\n" + " IFNULL(B.PROP_VAL23,'' ) AS PROP_VAL23,\r\n"
					+ " IFNULL(B.PROP_VAL24,'' ) AS PROP_VAL24,\r\n" + " IFNULL(B.PROP_VAL25,'' ) AS PROP_VAL25,\r\n"
					+ " IFNULL(B.PROP_VAL26,'' ) AS PROP_VAL26,\r\n" + " IFNULL(B.PROP_VAL27,'' ) AS PROP_VAL27,\r\n"
					+ " IFNULL(B.PROP_VAL28,'' ) AS PROP_VAL28,\r\n"
					+ " IFNULL(B.PACK_CODE_STR,'' ) AS PACK_CODE_STR,\r\n"
					+ " IFNULL(B.GOODS_NM_EN,'' ) AS GOODS_NM_EN,\r\n"
					+ " IFNULL(B.GOODS_NM_PR,'' ) AS GOODS_NM_PR,\r\n"
					+ " IFNULL(B.GOODS_REMARKS2,'' ) AS GOODS_REMARKS2,\r\n"
					+ " IFNULL(B.GOODS_REMARKS3,'' ) AS GOODS_REMARKS3,\r\n"
					+ " IFNULL(B.GOODS_REMARKS4,'' ) AS GOODS_REMARKS4,\r\n"
					+ " IFNULL(B.IMPORTNO,'' ) AS IMPORTNO,\r\n" + " IFNULL(B.GOODS_COST2,'' ) AS GOODS_COST2,\r\n"
					+ " IFNULL(B.ORIGIN2,'' ) AS ORIGIN2,\r\n" + " IFNULL(B.EXPIRE_DM,'' ) AS EXPIRE_DM,\r\n"
					+ " IFNULL(B.SUPPLY_SAVE_YN,'' ) AS SUPPLY_SAVE_YN,\r\n"
					+ " IFNULL(A.DESCRITION,'' ) AS DESCRITION,\r\n" + " IFNULL(FAILCONTENT,'' ) AS FAILCONTENT,  \r\n"
					+ " IFNULL(B.INSERTDT,'' ) AS INSERTDT,\r\n" + " IFNULL(B.INSERTID,'' ) AS INSERTID,\r\n"
					+ " IFNULL(A.INSERTDT,'' ) AS ERRINSERTDT,\r\n" + " IFNULL(A.INSERTID,'' ) AS ERRINSERTID,\r\n"
					+ " IFNULL(A.MODIFYDT,'' ) AS MODIFYDT,\r\n" + " IFNULL(A.MODIFYID,'' ) AS MODIFYID \r\n" +

					"  FROM SHOPPRODERR AS A \r\n" + "  INNER JOIN shopprodinfo AS B \r\n"
					+ "  ON A.COMPNO = B.COMPNO AND A.PRODSEQ = B.PRODSEQ\r\n"
					+ " WHERE A.COMPNO = ?  AND A.INSERTDT >= ? AND A.INSERTDT <= ? \r\n";

			if (params.get("SEARCH_GUBUN").isEmpty()) {
			}
//			else if (params.get("SEARCH_GUBUN").equals("PRODSEQ") || params.get("SEARCH_GUBUN").equals("DELV_COST")) {
//				sql	= sql + String.format(" AND B.%s = %s",params.get("SEARCH_GUBUN"),params.get("SEARCH_TEXT"));
//			} 
			else {
				sql = sql + String.format(" AND B.%s LIKE '%s%s%s' ", params.get("SEARCH_GUBUN"), "%",
						params.get("SEARCH_TEXT"), "%");
			}

			sql = sql + " ) AS SHOPERR  LEFT JOIN CATEALL AS CATE  \r\n"
					+ "		 ON SHOPERR.CLASS_CD1 = CATE.CLASS_CD1  \r\n"
					+ " 		 AND SHOPERR.CLASS_CD2 = CATE.CLASS_CD2 \r\n"
					+ "AND SHOPERR.CLASS_CD3 = CATE.CLASS_CD3   \r\n" + "AND SHOPERR.CLASS_CD4 = CATE.CLASS_CD4  \r\n";

			if (!params.get("CLASS_CD4").equals("CLASS_CD4")) {
				sql = sql + String.format(" WHERE CODE = '%s'", params.get("CLASS_CD4"));
			} else if (!params.get("CLASS_CD3").equals("CLASS_CD3")) {
				sql = sql + String.format(" WHERE CODE LIKE '%s%s'", params.get("CLASS_CD3"), "%");
			} else if (!params.get("CLASS_CD2").equals("CLASS_CD2")) {
				sql = sql + String.format(" WHERE CODE LIKE '%s%s'", params.get("CLASS_CD2"), "%");
			} else if (!params.get("CLASS_CD1").equals("CLASS_CD1")) {
				sql = sql + String.format(" WHERE CODE LIKE '%s%s'", params.get("CLASS_CD1"), "%");
			}

			if (!params.get("SHOPCD").equals("SHOPCD")) {
				if (params.get("CLASS_CD1").equals("CLASS_CD1")) {
					sql = sql + String.format(" WHERE SHOPCD = '%s'", params.get("SHOPCD"));
				} else {
					sql = sql + String.format("   AND SHOPCD = '%s'", params.get("SHOPCD"));
				}
				if (!params.get("SHOPUSER_ID").equals("SHOPUSER_ID")) {
					sql = sql + String.format(" AND SHOPSEQ = '%s'", params.get("SHOPUSER_ID"));
				}
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, params.get("DATE_FROM"));
			pstmt.setString(3, params.get("DATE_TO"));
//			String sql = "WITH CATEALL AS(                                                                 \r\n" + 
//					"		SELECT                                                                    \r\n" + 
//					"		   IFNULL(A.CODE,'')     AS CLASS_CD1 ,                                   \r\n" + 
//					"			IFNULL(A.CATEGORY,'') AS CLASS_NM1 ,                                  \r\n" + 
//					"			IFNULL(RIGHT(B.CODE,3),'')     AS CLASS_CD2  ,                        \r\n" + 
//					"			IFNULL(B.CATEGORY,'') AS CLASS_NM2 ,                                  \r\n" + 
//					"			IFNULL(RIGHT(C.CODE,3),'')     AS CLASS_CD3,                          \r\n" + 
//					"			IFNULL(C.CATEGORY,'') AS CLASS_NM3,                                   \r\n" + 
//					"			IFNULL(RIGHT(D.CODE,3),'')     AS CLASS_CD4 ,                         \r\n" + 
//					"			IFNULL(D.CATEGORY,'') AS CLASS_NM4                                    \r\n" + 
//					"		FROM CATEGLARGE A JOIN CATEGMIDIUM B                                      \r\n" + 
//					"			ON A.COMPNO=B.COMPNO AND A.CODE = B.LRGCODE JOIN CATEGSMALL C         \r\n" + 
//					"			ON A.COMPNO = C.COMPNO AND B.CODE = C.MIDCODE JOIN CATEGDETAIL D ON   \r\n" + 
//					" A.COMPNO = D.COMPNO AND C.CODE = D.SMLCODE WHERE A.COMPNO = '3'         \r\n" + 
//					"		 )                                                                        \r\n" + 
//					" SELECT INFO.*  , \r\n" + 
//					" IFNULL(CATE.CLASS_NM1, '')  CLASS_NM1, \r\n" + 
//					" IFNULL(CATE.CLASS_NM2, '')  CLASS_NM2, \r\n" + 
//					" IFNULL(CATE.CLASS_NM3, '')  CLASS_NM3, \r\n" + 
//					" IFNULL(CATE.CLASS_NM4, '')  CLASS_NM4, \r\n" + 
//					"(CASE                                   \r\n" + 
//					"	     WHEN GOODS_GUBUN = '1'         \r\n" + 
//					"	     THEN '위탁상품'                \r\n" + 
//					"	     WHEN GOODS_GUBUN = '2'         \r\n" + 
//					"	     THEN '제조상품'                \r\n" + 
//					"	     WHEN GOODS_GUBUN = '3'         \r\n" + 
//					"	     THEN '사입상품'                \r\n" + 
//					"	     WHEN GOODS_GUBUN = '4'         \r\n" + 
//					"	     THEN '직영상품'                \r\n" + 
//					"	     ELSE ''                        \r\n" + 
//					"	  END )AS GOODS_GUBUN_NM,            \r\n" + 
//					"(CASE                            \r\n" + 
//					"	     WHEN STATUS = '1'       \r\n" + 
//					"	     THEN '대기중'           \r\n" + 
//					"	     WHEN STATUS = '2'       \r\n" + 
//					"	     THEN '공급중'           \r\n" + 
//					"	     WHEN STATUS = '3'       \r\n" + 
//					"	     THEN '일시중지'         \r\n" + 
//					"	     WHEN STATUS = '4'       \r\n" + 
//					"	     THEN '완전품절'         \r\n" + 
//					"	      WHEN STATUS = '5'      \r\n" + 
//					"	     THEN '미사용'           \r\n" + 
//					"	      WHEN STATUS = '6'      \r\n" + 
//					"	     THEN '삭제'             \r\n" + 
//					"	     ELSE ''                 \r\n" + 
//					"	  END )AS STATUS_NM,         \r\n" + 
//					"(CASE                             \r\n" + 
//					"	     WHEN DELV_TYPE = '1'     \r\n" + 
//					"	     THEN '무료'              \r\n" + 
//					"	     WHEN DELV_TYPE = '2'     \r\n" + 
//					"	     THEN '착불'              \r\n" + 
//					"	     WHEN DELV_TYPE = '3'     \r\n" + 
//					"	     THEN '선결제'            \r\n" + 
//					"	     WHEN DELV_TYPE = '4'     \r\n" + 
//					"	     THEN '착불/선결제'       \r\n" + 
//					"	     ELSE ''                  \r\n" + 
//					"	  END )AS DELV_TYPE_NM       \r\n" + 
//					" FROM                             \r\n" + 
//					" (SELECT                           \r\n" + 
//					" CONCAT(IFNULL(CLASS_CD1,''), IFNULL(CLASS_CD2,''), IFNULL(CLASS_CD3,''), IFNULL(CLASS_CD4,'')) AS CODE, \r\n" + 
//					" IFNULL(SENDSEQ,'') AS SENDSEQ,\r\n" + 
//					" IFNULL(COMPNO,'' ) AS COMPNO, \r\n" + 
//					" IFNULL(SHOPCD,'') AS SHOPCD,\r\n" + 
//					" IFNULL(SHOPSEQ,'') AS SHOPSEQ,\r\n" + 
//					" IFNULL(PRODSEQ,'') AS PRODSEQ, \r\n" + 
//					" IFNULL(SEQ,'') AS SEQ, \r\n" + 
//					" IFNULL(SHOPCATNO,'') AS SHOPCATNO, \r\n" + 
//					" IFNULL(GOODS_NM,'' ) AS GOODS_NM,  \r\n" + 
//					" IFNULL(GOODS_KEYWORD,'' ) AS GOODS_KEYWORD,  \r\n" + 
//					" IFNULL(MODEL_NM,'' ) AS MODEL_NM,  \r\n" + 
//					" IFNULL(MODEL_NO,'' ) AS MODEL_NO,  \r\n" + 
//					" IFNULL(BRAND_NM,'' ) AS BRAND_NM,  \r\n" + 
//					" IFNULL(COMPAYNY_GOODS_CD,'' ) AS COMPAYNY_GOODS_CD,  \r\n" + 
//					" IFNULL(GOODS_SEARCH,'' ) AS GOODS_SEARCH,  \r\n" + 
//					" IFNULL(GOODS_GUBUN,'' ) AS GOODS_GUBUN,  \r\n" + 
//					" IFNULL(CLASS_CD1,'' ) AS CLASS_CD1,  \r\n" + 
//					" IFNULL(CLASS_CD2,'' ) AS CLASS_CD2,  \r\n" + 
//					" IFNULL(CLASS_CD3,'' ) AS CLASS_CD3,  \r\n" + 
//					" IFNULL(CLASS_CD4,'' ) AS CLASS_CD4,  \r\n" + 
//					" IFNULL(PARTNER_ID,'' ) AS PARTNER_ID,  \r\n" + 
//					" IFNULL(DPARTNER_ID,'' ) AS DPARTNER_ID,  \r\n" + 
//					" IFNULL(MAKER,'' ) AS MAKER,  \r\n" + 
//					" IFNULL(ORIGIN,'' ) AS ORIGIN,  \r\n" + 
//					" IFNULL(MAKE_YEAR,'' ) AS MAKE_YEAR,  \r\n" + 
//					" IFNULL(MAKE_DM,'' ) AS MAKE_DM,  \r\n" + 
//					" IFNULL(GOODS_SEASON,'' ) AS GOODS_SEASON,  \r\n" + 
//					" IFNULL(SEX,'' ) AS SEX,  \r\n" + 
//					" IFNULL(STATUS,'' ) AS STATUS,  \r\n" + 
//					" IFNULL(DELIV_ABLE_REGION,'' ) AS DELIV_ABLE_REGION,  \r\n" + 
//					" IFNULL(TAX_YN,'' ) AS TAX_YN,  \r\n" + 
//					" IFNULL(DELV_TYPE,'' ) AS DELV_TYPE,  \r\n" + 
//					" IFNULL(DELV_COST,'' ) AS DELV_COST,  \r\n" + 
//					" IFNULL(BANPUM_AREA,'' ) AS BANPUM_AREA,  \r\n" + 
//					" IFNULL(GOODS_COST,'' ) AS GOODS_COST,  \r\n" + 
//					" IFNULL(GOODS_PRICE,'' ) AS GOODS_PRICE,  \r\n" + 
//					" IFNULL(GOODS_CONSUMER_PRICE,'' ) AS GOODS_CONSUMER_PRICE,  \r\n" + 
//					" IFNULL(CHAR_1_NM,'' ) AS CHAR_1_NM,  \r\n" + 
//					" IFNULL(CHAR_1_VAL,'' ) AS CHAR_1_VAL,  \r\n" + 
//					" IFNULL(CHAR_2_NM,'' ) AS CHAR_2_NM,  \r\n" + 
//					" IFNULL(CHAR_2_VAL,'' ) AS CHAR_2_VAL,  \r\n" + 
//					" IFNULL(IMG_PATH,'' ) AS IMG_PATH,  \r\n" + 
//					" IFNULL(IMG_PATH1,'' ) AS IMG_PATH1,  \r\n" + 
//					" IFNULL(IMG_PATH2,'' ) AS IMG_PATH2,  \r\n" + 
//					" IFNULL(IMG_PATH3,'' ) AS IMG_PATH3,  \r\n" + 
//					" IFNULL(IMG_PATH4,'' ) AS IMG_PATH4,  \r\n" + 
//					" IFNULL(IMG_PATH5,'' ) AS IMG_PATH5,  \r\n" + 
//					" IFNULL(IMG_PATH6,'' ) AS IMG_PATH6,  \r\n" + 
//					" IFNULL(IMG_PATH7,'' ) AS IMG_PATH7,  \r\n" + 
//					" IFNULL(IMG_PATH8,'' ) AS IMG_PATH8,  \r\n" + 
//					" IFNULL(IMG_PATH9,'' ) AS IMG_PATH9,  \r\n" + 
//					" IFNULL(IMG_PATH10,'' ) AS IMG_PATH10,  \r\n" + 
//					" IFNULL(IMG_PATH11,'' ) AS IMG_PATH11,  \r\n" + 
//					" IFNULL(IMG_PATH12,'' ) AS IMG_PATH12,  \r\n" + 
//					" IFNULL(IMG_PATH13,'' ) AS IMG_PATH13,  \r\n" + 
//					" IFNULL(IMG_PATH14,'' ) AS IMG_PATH14,  \r\n" + 
//					" IFNULL(IMG_PATH15,'' ) AS IMG_PATH15,  \r\n" + 
//					" IFNULL(IMG_PATH16,'' ) AS IMG_PATH16,  \r\n" + 
//					" IFNULL(IMG_PATH17,'' ) AS IMG_PATH17,  \r\n" + 
//					" IFNULL(IMG_PATH18,'' ) AS IMG_PATH18,  \r\n" + 
//					" IFNULL(IMG_PATH19,'' ) AS IMG_PATH19,  \r\n" + 
//					" IFNULL(IMG_PATH20,'' ) AS IMG_PATH20,  \r\n" + 
//					" IFNULL(IMG_PATH21,'' ) AS IMG_PATH21,  \r\n" + 
//					" IFNULL(IMG_PATH22,'' ) AS IMG_PATH22,  \r\n" + 
//					" IFNULL(IMG_PATH23,'' ) AS IMG_PATH23,  \r\n" + 
//					" IFNULL(IMG_PATH24,'' ) AS IMG_PATH24,  \r\n" + 
//					" IFNULL(GOODS_REMARKS,'' ) AS GOODS_REMARKS,  \r\n" + 
//					" IFNULL(CERTNO,'' ) AS CERTNO,  \r\n" + 
//					" IFNULL(AVLST_DM,'' ) AS AVLST_DM,  \r\n" + 
//					" IFNULL(AVLED_DM,'' ) AS AVLED_DM,  \r\n" + 
//					" IFNULL(ISSUEDATE,'' ) AS ISSUEDATE,  \r\n" + 
//					" IFNULL(CERTDATE,'' ) AS CERTDATE,  \r\n" + 
//					" IFNULL(CERT_AGENCY,'' ) AS CERT_AGENCY,  \r\n" + 
//					" IFNULL(CERTFIELD,'' ) AS CERTFIELD,  \r\n" + 
//					" IFNULL(MATERIAL,'' ) AS MATERIAL,  \r\n" + 
//					" IFNULL(STOCK_USE_YN,'' ) AS STOCK_USE_YN,  \r\n" + 
//					" IFNULL(OPT_TYPE,'' ) AS OPT_TYPE,  \r\n" + 
//					" IFNULL(PROP_EDIT_YN,'' ) AS PROP_EDIT_YN,  \r\n" + 
//					" IFNULL(PROP1_CD,'' ) AS PROP1_CD,  \r\n" + 
//					" IFNULL(PROP_VAL1,'' ) AS PROP_VAL1,  \r\n" + 
//					" IFNULL(PROP_VAL2,'' ) AS PROP_VAL2,  \r\n" + 
//					" IFNULL(PROP_VAL3,'' ) AS PROP_VAL3,  \r\n" + 
//					" IFNULL(PROP_VAL4,'' ) AS PROP_VAL4,  \r\n" + 
//					" IFNULL(PROP_VAL5,'' ) AS PROP_VAL5,  \r\n" + 
//					" IFNULL(PROP_VAL6,'' ) AS PROP_VAL6,  \r\n" + 
//					" IFNULL(PROP_VAL7,'' ) AS PROP_VAL7,  \r\n" + 
//					" IFNULL(PROP_VAL8,'' ) AS PROP_VAL8,  \r\n" + 
//					" IFNULL(PROP_VAL9,'' ) AS PROP_VAL9,  \r\n" + 
//					" IFNULL(PROP_VAL10,'' ) AS PROP_VAL10,  \r\n" + 
//					" IFNULL(PROP_VAL11,'' ) AS PROP_VAL11,  \r\n" + 
//					" IFNULL(PROP_VAL12,'' ) AS PROP_VAL12,  \r\n" + 
//					" IFNULL(PROP_VAL13,'' ) AS PROP_VAL13,  \r\n" + 
//					" IFNULL(PROP_VAL14,'' ) AS PROP_VAL14,  \r\n" + 
//					" IFNULL(PROP_VAL15,'' ) AS PROP_VAL15,  \r\n" + 
//					" IFNULL(PROP_VAL16,'' ) AS PROP_VAL16,  \r\n" + 
//					" IFNULL(PROP_VAL17,'' ) AS PROP_VAL17,  \r\n" + 
//					" IFNULL(PROP_VAL18,'' ) AS PROP_VAL18,  \r\n" + 
//					" IFNULL(PROP_VAL19,'' ) AS PROP_VAL19,  \r\n" + 
//					" IFNULL(PROP_VAL20,'' ) AS PROP_VAL20,  \r\n" + 
//					" IFNULL(PROP_VAL21,'' ) AS PROP_VAL21,  \r\n" + 
//					" IFNULL(PROP_VAL22,'' ) AS PROP_VAL22,  \r\n" + 
//					" IFNULL(PROP_VAL23,'' ) AS PROP_VAL23,  \r\n" + 
//					" IFNULL(PROP_VAL24,'' ) AS PROP_VAL24,  \r\n" + 
//					" IFNULL(PROP_VAL25,'' ) AS PROP_VAL25,  \r\n" + 
//					" IFNULL(PROP_VAL26,'' ) AS PROP_VAL26,  \r\n" + 
//					" IFNULL(PROP_VAL27,'' ) AS PROP_VAL27,  \r\n" + 
//					" IFNULL(PROP_VAL28,'' ) AS PROP_VAL28,  \r\n" + 
//					" IFNULL(PACK_CODE_STR,'' ) AS PACK_CODE_STR,  \r\n" + 
//					" IFNULL(GOODS_NM_EN,'' ) AS GOODS_NM_EN,  \r\n" + 
//					" IFNULL(GOODS_NM_PR,'' ) AS GOODS_NM_PR,  \r\n" + 
//					" IFNULL(GOODS_REMARKS2,'' ) AS GOODS_REMARKS2,  \r\n" + 
//					" IFNULL(GOODS_REMARKS3,'' ) AS GOODS_REMARKS3,  \r\n" + 
//					" IFNULL(GOODS_REMARKS4,'' ) AS GOODS_REMARKS4,  \r\n" + 
//					" IFNULL(IMPORTNO,'' ) AS IMPORTNO,  \r\n" + 
//					" IFNULL(GOODS_COST2,'' ) AS GOODS_COST2,  \r\n" + 
//					" IFNULL(ORIGIN2,'' ) AS ORIGIN2,  \r\n" + 
//					" IFNULL(EXPIRE_DM,'' ) AS EXPIRE_DM,  \r\n" + 
//					" IFNULL(SUPPLY_SAVE_YN,'' ) AS SUPPLY_SAVE_YN,  \r\n" + 
//					" IFNULL(DESCRITION,'' ) AS DESCRITION,  \r\n" + 
//					" IFNULL(FAILCONTENT,'' ) AS FAILCONTENT,  \r\n" + 
//					" IFNULL(INSERTDT,'' ) AS INSERTDT,  \r\n" + 
//					" IFNULL(INSERTID,'' ) AS INSERTID,  \r\n" + 
//					" IFNULL(MODIFYDT,'' ) AS MODIFYDT,  \r\n" + 
//					" IFNULL(MODIFYID,'' ) AS MODIFYID  \r\n" + 
//					"  FROM shopproderr \r\n" + 
//					String.format(" WHERE COMPNO = ?  AND %s >= ? AND %s <= ? \r\n", option,option);
//			
//					if(!lagcateg.equals("")) {
//						sql += String.format(" AND CLASS_CD1 = '%s' ", lagcateg);
//					}
//					if(!midcateg.equals("")) {
//						sql += String.format(" AND CLASS_CD2 = '%s' ", midcateg.subSequence(midcateg.length()-3, midcateg.length()));
//					}
//					if(!smlcateg.equals("")) {
//						sql += String.format(" AND CLASS_CD3 = '%s' ", smlcateg.subSequence(smlcateg.length()-3, smlcateg.length()));
//					}
//					if(!detcateg.equals("")) {
//						sql += String.format(" AND CLASS_CD4 = '%s' ", detcateg.subSequence(detcateg.length()-3, detcateg.length()));
//					}
//					if(!shopcd.equals("")) {
//						sql += String.format(" AND SHOPCD = '%s' ",shopcd);
//					}
//					if(!prodststsd.equals("")) {
//						sql += String.format(" AND STATUS = '%s' ", prodststsd);
//					}
//					if(!searchgubun.equals("")) {
//						sql += String.format(" %s LIKE '%s' ", searchgubun,"%"+search+"%");
//					}					
//					
//					sql += " ) AS INFO  LEFT JOIN CATEALL AS CATE  \r\n" + 
//					"		 ON INFO.CLASS_CD1 = CATE.CLASS_CD1  \r\n" + 
//					" 		 AND INFO.CLASS_CD2 = CATE.CLASS_CD2 \r\n" + 
//					"AND INFO.CLASS_CD3 = CATE.CLASS_CD3   \r\n" + 
//					String.format("AND INFO.CLASS_CD4 = CATE.CLASS_CD4  ORDER BY  %s DESC ", option);
//
//
//
//			sql = sql.toUpperCase();
//
//			pstmt = connection.prepareStatement(sql);
//			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
//			pstmt.setString(2, prodFrom);
//			pstmt.setString(3, prodTo);
			System.out.println("[setProductInInsert]" + pstmt.toString());
			rs = pstmt.executeQuery();

			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				ShopProductDto dto = new ShopProductDto();
				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setCode(rs.getString("CODE"));
				dto.setSendseq(rs.getString("SENDSEQ"));// 에러코드
				dto.setCompno(rs.getString("COMPNO")); // 타입:int
				dto.setShopcd(rs.getString("SHOPCD"));// 쇼핑몰코드
				dto.setShopseq(rs.getString("SHOPSEQ"));
				dto.setProdseq(rs.getString("PRODSEQ")); // 품번코드 타입:int
				dto.setSeq(rs.getString("SEQ")); // 부가코드 타입:int
				dto.setShopcatno(rs.getString("SHOPCATNO")); // 카테고리번호
				dto.setGoods_nm(rs.getString("GOODS_NM")); // 상품명 타입:varchar
				dto.setGoods_keyword(rs.getString("GOODS_KEYWORD")); // 상품약어 타입:varchar
				dto.setModel_nm(rs.getString("MODEL_NM")); // 모델명 타입:varchar
				dto.setModel_no(rs.getString("MODEL_NO")); // 모델No 타입:varchar
				dto.setBrand_nm(rs.getString("BRAND_NM")); // 브랜드명 타입:varchar
				dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD")); // 자체상품코드 타입:varchar
				dto.setGoods_search(rs.getString("GOODS_SEARCH")); // 사이트검색어 타입:varchar
				dto.setGoods_gubun(rs.getString("GOODS_GUBUN")); // 상품구분 타입:varchar
				dto.setClass_cd1(rs.getString("CLASS_CD1")); // 대분류코드 타입:varchar
				dto.setClass_cd2(rs.getString("CLASS_CD2")); // 중분류코드 타입:varchar
				dto.setClass_cd3(rs.getString("CLASS_CD3")); // 소분류코드 타입:varchar
				dto.setClass_cd4(rs.getString("CLASS_CD4")); // 세분류코드 타입:varchar
				dto.setPartner_id(rs.getString("PARTNER_ID")); // 매입처ID 타입:varchar
				dto.setDpartner_id(rs.getString("DPARTNER_ID")); // 물류처ID 타입:varchar
				dto.setMaker(rs.getString("MAKER")); // 제조사 타입:varchar
				dto.setOrigin(rs.getString("ORIGIN")); // 원산지(제조국) 타입:varchar
				dto.setMake_year(rs.getString("MAKE_YEAR")); // 생산연도 타입:varchar
				dto.setMake_dm(rs.getString("MAKE_DM")); // 제조일자 타입:varchar
				dto.setGoods_season(rs.getString("GOODS_SEASON")); // 시즌 타입:varchar
				dto.setSex(rs.getString("SEX")); // 남녀구분 타입:varchar
				dto.setStatus(rs.getString("STATUS")); // 상품상태 타입:varchar
				dto.setDeliv_able_region(rs.getString("DELIV_ABLE_REGION")); // 판매지역 타입:varchar
				dto.setTax_yn(rs.getString("TAX_YN")); // 세금구분 타입:varchar
				dto.setDelv_type(rs.getString("DELV_TYPE")); // 배송비구분 타입:varchar
				dto.setDelv_cost(rs.getString("DELV_COST")); // 배송비 타입:int
				dto.setBanpum_area(rs.getString("BANPUM_AREA")); // 반품지구분 타입:varchar
				dto.setGoods_cost(rs.getString("GOODS_COST")); // 자체 상품 원가 타입:int
				dto.setGoods_price(rs.getString("GOODS_PRICE")); // 판매가 타입:int
				dto.setGoods_consumer_price(rs.getString("GOODS_CONSUMER_PRICE")); // TAG가(소비자가) 타입:int
				dto.setChar_1_nm(rs.getString("CHAR_1_NM")); // 옵션제목(1) 타입:varchar
				dto.setChar_1_val(rs.getString("CHAR_1_VAL")); // 옵션상세명칭(1) 타입:varchar
				dto.setChar_2_nm(rs.getString("CHAR_2_NM")); // 옵션제목(2) 타입:varchar
				dto.setChar_2_val(rs.getString("CHAR_2_VAL")); // 옵션상세명칭(2) 타입:varchar
				dto.setImg_path(rs.getString("IMG_PATH")); // 대표이미지 타입:varchar
				dto.setImg_path1(rs.getString("IMG_PATH1")); // 종합몰(JPG)이미지 타입:varchar
				dto.setImg_path2(rs.getString("IMG_PATH2")); // 부가이미지2 타입:varchar
				dto.setImg_path3(rs.getString("IMG_PATH3")); // 부가이미지3 타입:varchar
				dto.setImg_path4(rs.getString("IMG_PATH4")); // 부가이미지4 타입:varchar
				dto.setImg_path5(rs.getString("IMG_PATH5")); // 부가이미지5 타입:varchar
				dto.setImg_path6(rs.getString("IMG_PATH6")); // 부가이미지6 타입:varchar
				dto.setImg_path7(rs.getString("IMG_PATH7")); // 부가이미지7 타입:varchar
				dto.setImg_path8(rs.getString("IMG_PATH8")); // 부가이미지8 타입:varchar
				dto.setImg_path9(rs.getString("IMG_PATH9")); // 부가이미지9 타입:varchar
				dto.setImg_path10(rs.getString("IMG_PATH10")); // 부가이미지10 타입:varchar
				dto.setImg_path11(rs.getString("IMG_PATH11")); // 부가이미지11 타입:varchar
				dto.setImg_path12(rs.getString("IMG_PATH12")); // 부가이미지12 타입:varchar
				dto.setImg_path13(rs.getString("IMG_PATH13")); // 부가이미지13 타입:varchar
				dto.setImg_path14(rs.getString("IMG_PATH14")); // 부가이미지14 타입:varchar
				dto.setImg_path15(rs.getString("IMG_PATH15")); // 부가이미지15 타입:varchar
				dto.setImg_path16(rs.getString("IMG_PATH16")); // 부가이미지16 타입:varchar
				dto.setImg_path17(rs.getString("IMG_PATH17")); // 부가이미지17 타입:varchar
				dto.setImg_path18(rs.getString("IMG_PATH18")); // 부가이미지18 타입:varchar
				dto.setImg_path19(rs.getString("IMG_PATH19")); // 부가이미지19 타입:varchar
				dto.setImg_path20(rs.getString("IMG_PATH20")); // 부가이미지20 타입:varchar
				dto.setImg_path21(rs.getString("IMG_PATH21")); // 부가이미지21 타입:varchar
				dto.setImg_path22(rs.getString("IMG_PATH22")); // 부가이미지22 타입:varchar
				dto.setImg_path23(rs.getString("IMG_PATH23")); // 인증서이미지 타입:varchar
				dto.setImg_path24(rs.getString("IMG_PATH24")); // 수입면장이미지 타입:varchar
				dto.setGoods_remarks(rs.getString("GOODS_REMARKS")); // 상품상세설명 타입:varchar
				dto.setCertno(rs.getString("CERTNO")); // 인증번호 타입:varchar
				dto.setAvlst_dm(rs.getString("AVLST_DM")); // 인증유효 시작일 타입:varchar
				dto.setAvled_dm(rs.getString("AVLED_DM")); // 인증유효 마지막일 타입:varchar
				dto.setIssuedate(rs.getString("ISSUEDATE")); // 발급일자 타입:varchar
				dto.setCertdate(rs.getString("CERTDATE")); // 인증일자 타입:varchar
				dto.setCert_agency(rs.getString("CERT_AGENCY")); // 인증기관 타입:varchar
				dto.setCertfield(rs.getString("CERTFIELD")); // 인증분야 타입:varchar
				dto.setMaterial(rs.getString("MATERIAL")); // 식품재료/원산지 타입:varchar
				dto.setStock_use_yn(rs.getString("STOCK_USE_YN")); // 재고관리사용여부 타입:varchar
				dto.setOpt_type(rs.getString("OPT_TYPE")); // 옵션수정여부 타입:varchar
				dto.setProp_edit_yn(rs.getString("PROP_EDIT_YN")); // 속성수정여부 타입:varchar
				dto.setProp1_cd(rs.getString("PROP1_CD")); // 속성분류코드 타입:varchar
				dto.setProp_val1(rs.getString("PROP_VAL1")); // 속성값1 타입:varchar
				dto.setProp_val2(rs.getString("PROP_VAL2")); // 속성값2 타입:varchar
				dto.setProp_val3(rs.getString("PROP_VAL3")); // 속성값3 타입:varchar
				dto.setProp_val4(rs.getString("PROP_VAL4")); // 속성값4 타입:varchar
				dto.setProp_val5(rs.getString("PROP_VAL5")); // 속성값5 타입:varchar
				dto.setProp_val6(rs.getString("PROP_VAL6")); // 속성값6 타입:varchar
				dto.setProp_val7(rs.getString("PROP_VAL7")); // 속성값7 타입:varchar
				dto.setProp_val8(rs.getString("PROP_VAL8")); // 속성값8 타입:varchar
				dto.setProp_val9(rs.getString("PROP_VAL9")); // 속성값9 타입:varchar
				dto.setProp_val10(rs.getString("PROP_VAL10")); // 속성값10 타입:varchar
				dto.setProp_val11(rs.getString("PROP_VAL11")); // 속성값11 타입:varchar
				dto.setProp_val12(rs.getString("PROP_VAL12")); // 속성값12 타입:varchar
				dto.setProp_val13(rs.getString("PROP_VAL13")); // 속성값13 타입:varchar
				dto.setProp_val14(rs.getString("PROP_VAL14")); // 속성값14 타입:varchar
				dto.setProp_val15(rs.getString("PROP_VAL15")); // 속성값15 타입:varchar
				dto.setProp_val16(rs.getString("PROP_VAL16")); // 속성값16 타입:varchar
				dto.setProp_val17(rs.getString("PROP_VAL17")); // 속성값17 타입:varchar
				dto.setProp_val18(rs.getString("PROP_VAL18")); // 속성값18 타입:varchar
				dto.setProp_val19(rs.getString("PROP_VAL19")); // 속성값19 타입:varchar
				dto.setProp_val20(rs.getString("PROP_VAL20")); // 속성값20 타입:varchar
				dto.setProp_val21(rs.getString("PROP_VAL21")); // 속성값21 타입:varchar
				dto.setProp_val22(rs.getString("PROP_VAL22")); // 속성값22 타입:varchar
				dto.setProp_val23(rs.getString("PROP_VAL23")); // 속성값23 타입:varchar
				dto.setProp_val24(rs.getString("PROP_VAL24")); // 속성값24 타입:varchar
				dto.setProp_val25(rs.getString("PROP_VAL25")); // 속성값25 타입:varchar
				dto.setProp_val26(rs.getString("PROP_VAL26")); // 속성값26 타입:varchar
				dto.setProp_val27(rs.getString("PROP_VAL27")); // 속성값27 타입:varchar
				dto.setProp_val28(rs.getString("PROP_VAL28")); // 속성값28 타입:varchar
				dto.setPack_code_str(rs.getString("PACK_CODE_STR")); // 추가상품그룹코드 타입:varchar
				dto.setGoods_nm_en(rs.getString("GOODS_NM_EN")); // 영문 상품명 타입:varchar
				dto.setGoods_nm_pr(rs.getString("GOODS_NM_PR")); // 출력 상품명 타입:varchar
				dto.setGoods_remarks2(rs.getString("GOODS_REMARKS2")); // 추가 상품상세설명_1 타입:varchar
				dto.setGoods_remarks3(rs.getString("GOODS_REMARKS3")); // 추가 상품상세설명_2 타입:varchar
				dto.setGoods_remarks4(rs.getString("GOODS_REMARKS4")); // 추가 상품상세설명_3 타입:varchar
				dto.setImportno(rs.getString("IMPORTNO")); // 수입신고번호 타입:varchar
				dto.setGoods_cost2(rs.getString("GOODS_COST2")); // 원가2 타입:varchar
				dto.setOrigin2(rs.getString("ORIGIN2")); // 원산지 상세지역 타입:varchar
				dto.setExpire_dm(rs.getString("EXPIRE_DM")); // 유효일자 타입:varchar
				dto.setSupply_save_yn(rs.getString("SUPPLY_SAVE_YN")); // 합포제외여부 타입:varchar
				dto.setDescrition(rs.getString("DESCRITION")); // 관리자메모 타입:varchar
				dto.setFailcontent(rs.getString("FAILCONTENT")); // 실패시 에러코드 타입:varchar
				dto.setInsertdt(rs.getString("INSERTDT")); // 입력일자 타입:varchar
				dto.setInsertid(rs.getString("INSERTID")); // 입력자id 타입:varchar
				dto.setErrinsertdt(rs.getString("ERRINSERTDT")); // 입력일자 타입:varchar
				dto.setErrinsertid(rs.getString("ERRINSERTID")); // 입력자id 타입:varchar
				dto.setModifydt(rs.getString("MODIFYDT")); // 수정일자 타입:varchar
				dto.setModifyid(rs.getString("MODIFYID")); // 수정자ID 타입:varchar

				// ----- class nm 추가 -----
				dto.setClass_nm1(rs.getString("CLASS_NM1")); // 대분류
				dto.setClass_nm2(rs.getString("CLASS_NM2")); // 중분류
				dto.setClass_nm3(rs.getString("CLASS_NM3")); // 소분류
				dto.setClass_nm4(rs.getString("CLASS_NM4")); // 세분류

				dto.setDelv_type_nm(rs.getString("DELV_TYPE_NM"));
				dto.setGoods_gubun_nm(rs.getString("GOODS_GUBUN_NM"));
				dto.setStatus_nm(rs.getString("STATUS_NM"));

				/********* addtinon ************/
//				ShopCatInfDto ShopCatInfDto = new ShopCatInfDto();
//
//				ShopCatInfDto.setCompno(rs.getString("COMPNO"));
//				ShopCatInfDto.setShopcd(rs.getString("SHOPCD"));
//				ShopCatInfDto.setShopcatno(rs.getString("SHOPCATNO"));
//				ShopCatInfDto.setShopcatnm(rs.getString("SHOPCATNM"));
//				ShopCatInfDto.setShopcatsitenm(rs.getString("SHOPCATSITENM"));
//				ShopCatInfDto.setShoplagcatcd(rs.getString("SHOPLAGCATCD"));
//				ShopCatInfDto.setShopmidcatcd(rs.getString("SHOPMIDCATCD"));
//				ShopCatInfDto.setShopsmlcatcd(rs.getString("SHOPSMLCATCD"));
//				ShopCatInfDto.setShopdetcatcd(rs.getString("SHOPDETCATCD"));
//				ShopCatInfDto.setServiceprod(rs.getString("SERVICEPROD"));
//				ShopCatInfDto.setUse_yn(rs.getString("USE_YN"));
//				ShopCatInfDto.setShopgeneral(rs.getString("SHOPGENERAL"));
//				ShopCatInfDto.setShopid(rs.getString("SHOPID"));
//				ShopCatInfDto.setShopcommis(rs.getString("SHOPCOMMIS"));
//				ShopCatInfDto.setInsertdt(rs.getString("INSERTDT"));
//				ShopCatInfDto.setModifydt(rs.getString("MODIFYDT"));
//
//				dto.setShopCatInDto(ShopCatInfDto);
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

	// 에러테이블삭제
	public int[] ProductErrordataDelete(List<ShopProductDto> listdto) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		int[] result;

		String sql = "DELETE FROM shopproderr where compno=? and sendseq=? ";
		sql = sql.toUpperCase();
		connection = DBCPInit.getInstance().getConnection();

		pstmt = connection.prepareStatement(sql);
		statementlist.add(pstmt);

		for (ShopProductDto dto : listdto) {
			int i = 0;
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++i, dto.getSendseq());

			System.out.println("[ProductErrordataDelete]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화
		}

		result = pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

		return result;

	}

	// prodin가지고오기prodinfo만큼
	public List<List<String>> getShopprodInList(List<ShopProductDto> list) throws Exception {

		List<List<String>> contents = new ArrayList<List<String>>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(PRODSEQ,'0'),ifnull(COMPNO,''),ifnull(GOODS_NM,''), ifnull(GOODS_KEYWORD,''), ifnull(MODEL_NM,''), ifnull(MODEL_NO,''), ifnull(BRAND_NM,''), ifnull(COMPAYNY_GOODS_CD,''), ";
			sql += " ifnull(GOODS_SEARCH,''), ifnull(GOODS_GUBUN,''), ifnull(CLASS_CD1,''), ifnull(CLASS_CD2,''),  ifnull(CLASS_CD3,''), ifnull(CLASS_CD4,''), ifnull(PARTNER_ID,''), ifnull(DPARTNER_ID,''),";// 물류처아이디
			sql += " ifnull(MAKER,''), ifnull(ORIGIN,''), ifnull(MAKE_YEAR,''), ifnull(MAKE_DM,''), ifnull(GOODS_SEASON,''), ifnull(SEX,''), ifnull(STATUS,''), ifnull(DELIV_ABLE_REGION,''), ifnull(TAX_YN,''),";// 세금구분
			sql += " ifnull(DELV_TYPE,''), ifnull(DELV_COST,0), ifnull(BANPUM_AREA,''), ifnull(GOODS_COST,0), ifnull(GOODS_PRICE,0), ifnull(GOODS_CONSUMER_PRICE,0), ifnull(CHAR_1_NM,''), ifnull(CHAR_1_VAL,''), ";// 옵션상세명칭1
			sql += " ifnull(CHAR_2_NM,''), ifnull(CHAR_2_VAL,''), ifnull(IMG_PATH, ''), ifnull(IMG_PATH1,''), ifnull(IMG_PATH2,''), ifnull(IMG_PATH3,''), ifnull(IMG_PATH4,''), ifnull(IMG_PATH5,''), ifnull(IMG_PATH6,''), ";
			sql += " ifnull(IMG_PATH7,''), ifnull(IMG_PATH8,''), ifnull(IMG_PATH9, ''), ifnull(IMG_PATH10,''), ifnull(IMG_PATH11,''), ifnull(IMG_PATH12,''), ifnull(IMG_PATH13,''), ifnull(IMG_PATH14,''), "
					+ " ifnull(IMG_PATH15,''),ifnull(IMG_PATH16,''), ifnull(IMG_PATH17,''), ifnull(IMG_PATH18, ''), ifnull(IMG_PATH19,''), ifnull(IMG_PATH20,''), ifnull(IMG_PATH21,''), ifnull(IMG_PATH22,''), "
					+ " ifnull(IMG_PATH23,''), ifnull(IMG_PATH24,''), ifnull(GOODS_REMARKS,''), ifnull(CERTNO, ''), ifnull(AVLST_DM,''), ifnull(AVLED_DM,''), ifnull(ISSUEDATE,''), ifnull(CERTDATE,'')," // 인증일자
					+ " ifnull(CERT_AGENCY,''), ifnull(CERTFIELD,''), ifnull(MATERIAL,''), ifnull(STOCK_USE_YN, ''), ifnull(OPT_TYPE,''), ifnull(PROP_EDIT_YN,''), ifnull(PROP1_CD,''), ifnull(PROP_VAL1,''), "// 속성값1
					+ " ifnull(PROP_VAL2,''), ifnull(PROP_VAL3,''), ifnull(PROP_VAL4,''), ifnull(PROP_VAL5, ''), ifnull(PROP_VAL6,''), ifnull(PROP_VAL7,''), ifnull(PROP_VAL8,''), ifnull(PROP_VAL9,''), ifnull(PROP_VAL10,''), "
					+ " ifnull(PROP_VAL11,''), ifnull(PROP_VAL12,''), ifnull(PROP_VAL13,''), ifnull(PROP_VAL14, ''), ifnull(PROP_VAL15,''), ifnull(PROP_VAL16,''), ifnull(PROP_VAL17,''), ifnull(PROP_VAL18,''), "
					+ " ifnull(PROP_VAL19,''), ifnull(PROP_VAL20,''), ifnull(PROP_VAL21,''), ifnull(PROP_VAL22, ''), ifnull(PROP_VAL23,''), ifnull(PROP_VAL24,''), ifnull(PROP_VAL25,''), ifnull(PROP_VAL26,''), "
					+ " ifnull(PROP_VAL27,''), ifnull(PROP_VAL28,''), ifnull(PACK_CODE_STR,''), ifnull(GOODS_NM_EN, ''), ifnull(GOODS_NM_PR,''), ifnull(GOODS_REMARKS2,''), ifnull(GOODS_REMARKS3,''), "// 추가상품상세설명2
					+ " ifnull(GOODS_REMARKS4,''), ifnull(IMPORTNO,''), ifnull(GOODS_COST2,''), ifnull(ORIGIN2, ''), ifnull(EXPIRE_DM,''), ifnull(SUPPLY_SAVE_YN,''), ifnull(DESCRITION,''),  "// 관리자메모
					+ " ifnull(shopprodno,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), ifnull(MODIFYDT,''), ifnull(MODIFYID, '')";
			sql += " FROM shopprodin ";
			sql += " where compno = ? " + "and SENDSEQ = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			for (int k = 0; k < list.size(); k++) {
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.get(k).getSendseq());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					int i = 0;
					List<String> listarr = new ArrayList<String>();
					for (int j = 1; j <= 118; j++) {
						listarr.add(rs.getString(j));
					}

					contents.add(listarr);

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public void setProductInNErrInsert(List<List<String>> contents, List<List<String>> allContents) throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = " insert into shopproderr (compno, SHOPCD, SHOPSEQ,  PRODSEQ, seq,GOODS_NM, GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, "
					+ " CLASS_CD2,CLASS_CD3, CLASS_CD4, PARTNER_ID,  DPARTNER_ID, MAKER, ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, STATUS, DELIV_ABLE_REGION, TAX_YN,DELV_TYPE,DELV_COST,BANPUM_AREA, "
					+ " GOODS_COST,GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM,  CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3, IMG_PATH4, IMG_PATH5, IMG_PATH6,IMG_PATH7,IMG_PATH8, "
					+ " IMG_PATH9,IMG_PATH10, IMG_PATH11, IMG_PATH12,  IMG_PATH13, IMG_PATH14, IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22,IMG_PATH23,IMG_PATH24, "
					+ " GOODS_REMARKS,CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, PROP1_CD, PROP_VAL1,PROP_VAL2,PROP_VAL3, PROP_VAL4, "
					+ " PROP_VAL5,PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10, PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18,PROP_VAL19,PROP_VAL20, PROP_VAL21, "
					+ " PROP_VAL22,PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN, GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, "
					+ " GOODS_COST2,ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN,DESCRITION, FAILCONTENT, INSERTDT,INSERTID ) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,"// 23
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, "// 86
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ) ";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (int i = 0; i < contents.size(); i++) {
				List<String> alllist = allContents.get(i);
				int idx = 0;
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
				for (int k = 3; k <= 118; k++) {
					pstmt.setString(++idx, alllist.get(k - 1));
				}
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());
				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			System.out.println("[setProductInInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public List<List<String>> getShopprodInAllList(List<ShopProductDto> list) throws Exception {

		List<List<String>> contents = new ArrayList<List<String>>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(sendseq,'0'), ifnull(COMPNO,''),ifnull(shopcd,''),ifnull(shopseq,'0'),ifnull(PRODSEQ,'0'),ifnull(seq,'0'),ifnull(GOODS_NM,''), ifnull(GOODS_KEYWORD,''), ifnull(MODEL_NM,''), ifnull(MODEL_NO,''), ifnull(BRAND_NM,''), ifnull(COMPAYNY_GOODS_CD,''), ";
			sql += " ifnull(GOODS_SEARCH,''), ifnull(GOODS_GUBUN,''), ifnull(CLASS_CD1,''), ifnull(CLASS_CD2,''),  ifnull(CLASS_CD3,''), ifnull(CLASS_CD4,''), ifnull(PARTNER_ID,''), ifnull(DPARTNER_ID,''),";// 물류처아이디
			sql += " ifnull(MAKER,''), ifnull(ORIGIN,''), ifnull(MAKE_YEAR,''), ifnull(MAKE_DM,''), ifnull(GOODS_SEASON,''), ifnull(SEX,''), ifnull(STATUS,''), ifnull(DELIV_ABLE_REGION,''), ifnull(TAX_YN,''),";// 세금구분
			sql += " ifnull(DELV_TYPE,''), ifnull(DELV_COST,0), ifnull(BANPUM_AREA,''), ifnull(GOODS_COST,0), ifnull(GOODS_PRICE,0), ifnull(GOODS_CONSUMER_PRICE,0), ifnull(CHAR_1_NM,''), ifnull(CHAR_1_VAL,''), ";// 옵션상세명칭1
			sql += " ifnull(CHAR_2_NM,''), ifnull(CHAR_2_VAL,''), ifnull(IMG_PATH, ''), ifnull(IMG_PATH1,''), ifnull(IMG_PATH2,''), ifnull(IMG_PATH3,''), ifnull(IMG_PATH4,''), ifnull(IMG_PATH5,''), ifnull(IMG_PATH6,''), ";
			sql += " ifnull(IMG_PATH7,''), ifnull(IMG_PATH8,''), ifnull(IMG_PATH9, ''), ifnull(IMG_PATH10,''), ifnull(IMG_PATH11,''), ifnull(IMG_PATH12,''), ifnull(IMG_PATH13,''), ifnull(IMG_PATH14,''), "
					+ " ifnull(IMG_PATH15,''),ifnull(IMG_PATH16,''), ifnull(IMG_PATH17,''), ifnull(IMG_PATH18, ''), ifnull(IMG_PATH19,''), ifnull(IMG_PATH20,''), ifnull(IMG_PATH21,''), ifnull(IMG_PATH22,''), "
					+ " ifnull(IMG_PATH23,''), ifnull(IMG_PATH24,''), ifnull(GOODS_REMARKS,''), ifnull(CERTNO, ''), ifnull(AVLST_DM,''), ifnull(AVLED_DM,''), ifnull(ISSUEDATE,''), ifnull(CERTDATE,'')," // 인증일자
					+ " ifnull(CERT_AGENCY,''), ifnull(CERTFIELD,''), ifnull(MATERIAL,''), ifnull(STOCK_USE_YN, ''), ifnull(OPT_TYPE,''), ifnull(PROP_EDIT_YN,''), ifnull(PROP1_CD,''), ifnull(PROP_VAL1,''), "// 속성값1
					+ " ifnull(PROP_VAL2,''), ifnull(PROP_VAL3,''), ifnull(PROP_VAL4,''), ifnull(PROP_VAL5, ''), ifnull(PROP_VAL6,''), ifnull(PROP_VAL7,''), ifnull(PROP_VAL8,''), ifnull(PROP_VAL9,''), ifnull(PROP_VAL10,''), "
					+ " ifnull(PROP_VAL11,''), ifnull(PROP_VAL12,''), ifnull(PROP_VAL13,''), ifnull(PROP_VAL14, ''), ifnull(PROP_VAL15,''), ifnull(PROP_VAL16,''), ifnull(PROP_VAL17,''), ifnull(PROP_VAL18,''), "
					+ " ifnull(PROP_VAL19,''), ifnull(PROP_VAL20,''), ifnull(PROP_VAL21,''), ifnull(PROP_VAL22, ''), ifnull(PROP_VAL23,''), ifnull(PROP_VAL24,''), ifnull(PROP_VAL25,''), ifnull(PROP_VAL26,''), "
					+ " ifnull(PROP_VAL27,''), ifnull(PROP_VAL28,''), ifnull(PACK_CODE_STR,''), ifnull(GOODS_NM_EN, ''), ifnull(GOODS_NM_PR,''), ifnull(GOODS_REMARKS2,''), ifnull(GOODS_REMARKS3,''), "// 추가상품상세설명2
					+ " ifnull(GOODS_REMARKS4,''), ifnull(IMPORTNO,''), ifnull(GOODS_COST2,''), ifnull(ORIGIN2, ''), ifnull(EXPIRE_DM,''), ifnull(SUPPLY_SAVE_YN,''), ifnull(DESCRITION,''),  "// 관리자메모
					+ " ifnull(shopprodno,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), ifnull(MODIFYDT,''), ifnull(MODIFYID, '')";
			sql += " FROM shopprodin ";
			sql += " where compno = ? and SENDSEQ = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			for (int k = 0; k < list.size(); k++) {
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.get(k).getSendseq());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					int i = 0;
					List<String> listarr = new ArrayList<String>();
					for (int j = 1; j <= 122; j++) {
						listarr.add(rs.getString(j));
					}

					contents.add(listarr);

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	// proderror가지고오기prodinfo만큼
	public List<List<String>> getShopprodErrorList(List<ShopProductDto> list) throws Exception {

		List<List<String>> contents = new ArrayList<List<String>>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(PRODSEQ,'0'),ifnull(COMPNO,''),ifnull(GOODS_NM,''), ifnull(GOODS_KEYWORD,''), ifnull(MODEL_NM,''), ifnull(MODEL_NO,''), ifnull(BRAND_NM,''), ifnull(COMPAYNY_GOODS_CD,''), ";
			sql += " ifnull(GOODS_SEARCH,''), ifnull(GOODS_GUBUN,''), ifnull(CLASS_CD1,''), ifnull(CLASS_CD2,''),  ifnull(CLASS_CD3,''), ifnull(CLASS_CD4,''), ifnull(PARTNER_ID,''), ifnull(DPARTNER_ID,''),";// 물류처아이디
			sql += " ifnull(MAKER,''), ifnull(ORIGIN,''), ifnull(MAKE_YEAR,''), ifnull(MAKE_DM,''), ifnull(GOODS_SEASON,''), ifnull(SEX,''), ifnull(STATUS,''), ifnull(DELIV_ABLE_REGION,''), ifnull(TAX_YN,''),";// 세금구분
			sql += " ifnull(DELV_TYPE,''), ifnull(DELV_COST,0), ifnull(BANPUM_AREA,''), ifnull(GOODS_COST,0), ifnull(GOODS_PRICE,0), ifnull(GOODS_CONSUMER_PRICE,0), ifnull(CHAR_1_NM,''), ifnull(CHAR_1_VAL,''), ";// 옵션상세명칭1
			sql += " ifnull(CHAR_2_NM,''), ifnull(CHAR_2_VAL,''), ifnull(IMG_PATH, ''), ifnull(IMG_PATH1,''), ifnull(IMG_PATH2,''), ifnull(IMG_PATH3,''), ifnull(IMG_PATH4,''), ifnull(IMG_PATH5,''), ifnull(IMG_PATH6,''), ";
			sql += " ifnull(IMG_PATH7,''), ifnull(IMG_PATH8,''), ifnull(IMG_PATH9, ''), ifnull(IMG_PATH10,''), ifnull(IMG_PATH11,''), ifnull(IMG_PATH12,''), ifnull(IMG_PATH13,''), ifnull(IMG_PATH14,''), "
					+ " ifnull(IMG_PATH15,''),ifnull(IMG_PATH16,''), ifnull(IMG_PATH17,''), ifnull(IMG_PATH18, ''), ifnull(IMG_PATH19,''), ifnull(IMG_PATH20,''), ifnull(IMG_PATH21,''), ifnull(IMG_PATH22,''), "
					+ " ifnull(IMG_PATH23,''), ifnull(IMG_PATH24,''), ifnull(GOODS_REMARKS,''), ifnull(CERTNO, ''), ifnull(AVLST_DM,''), ifnull(AVLED_DM,''), ifnull(ISSUEDATE,''), ifnull(CERTDATE,'')," // 인증일자
					+ " ifnull(CERT_AGENCY,''), ifnull(CERTFIELD,''), ifnull(MATERIAL,''), ifnull(STOCK_USE_YN, ''), ifnull(OPT_TYPE,''), ifnull(PROP_EDIT_YN,''), ifnull(PROP1_CD,''), ifnull(PROP_VAL1,''), "// 속성값1
					+ " ifnull(PROP_VAL2,''), ifnull(PROP_VAL3,''), ifnull(PROP_VAL4,''), ifnull(PROP_VAL5, ''), ifnull(PROP_VAL6,''), ifnull(PROP_VAL7,''), ifnull(PROP_VAL8,''), ifnull(PROP_VAL9,''), ifnull(PROP_VAL10,''), "
					+ " ifnull(PROP_VAL11,''), ifnull(PROP_VAL12,''), ifnull(PROP_VAL13,''), ifnull(PROP_VAL14, ''), ifnull(PROP_VAL15,''), ifnull(PROP_VAL16,''), ifnull(PROP_VAL17,''), ifnull(PROP_VAL18,''), "
					+ " ifnull(PROP_VAL19,''), ifnull(PROP_VAL20,''), ifnull(PROP_VAL21,''), ifnull(PROP_VAL22, ''), ifnull(PROP_VAL23,''), ifnull(PROP_VAL24,''), ifnull(PROP_VAL25,''), ifnull(PROP_VAL26,''), "
					+ " ifnull(PROP_VAL27,''), ifnull(PROP_VAL28,''), ifnull(PACK_CODE_STR,''), ifnull(GOODS_NM_EN, ''), ifnull(GOODS_NM_PR,''), ifnull(GOODS_REMARKS2,''), ifnull(GOODS_REMARKS3,''), "// 추가상품상세설명2
					+ " ifnull(GOODS_REMARKS4,''), ifnull(IMPORTNO,''), ifnull(GOODS_COST2,''), ifnull(ORIGIN2, ''), ifnull(EXPIRE_DM,''), ifnull(SUPPLY_SAVE_YN,''), ifnull(DESCRITION,''),  "// 관리자메모
					+ " ifnull(FAILCONTENT,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), ifnull(MODIFYDT,''), ifnull(MODIFYID, '')";
			sql += " FROM shopproderr ";
			sql += " where compno = ? and SENDSEQ = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			for (int k = 0; k < list.size(); k++) {
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.get(k).getSendseq());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					int i = 0;
					List<String> listarr = new ArrayList<String>();
					for (int j = 1; j <= 118; j++) {
						listarr.add(rs.getString(j));
					}

					contents.add(listarr);

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	// 에러테이블 다가지고오기
	public List<List<String>> getShopprodErrorAllList(List<ShopProductDto> list) throws Exception {

		List<List<String>> contents = new ArrayList<List<String>>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " select ifnull(sendseq,'0'), ifnull(COMPNO,''),ifnull(shopcd,''),ifnull(shopseq,'0'),ifnull(PRODSEQ,'0'),ifnull(seq,'0'),ifnull(GOODS_NM,''), ifnull(GOODS_KEYWORD,''), ifnull(MODEL_NM,''), ifnull(MODEL_NO,''), ifnull(BRAND_NM,''), ifnull(COMPAYNY_GOODS_CD,''), ";
			sql += " ifnull(GOODS_SEARCH,''), ifnull(GOODS_GUBUN,''), ifnull(CLASS_CD1,''), ifnull(CLASS_CD2,''),  ifnull(CLASS_CD3,''), ifnull(CLASS_CD4,''), ifnull(PARTNER_ID,''), ifnull(DPARTNER_ID,''),";// 물류처아이디
			sql += " ifnull(MAKER,''), ifnull(ORIGIN,''), ifnull(MAKE_YEAR,''), ifnull(MAKE_DM,''), ifnull(GOODS_SEASON,''), ifnull(SEX,''), ifnull(STATUS,''), ifnull(DELIV_ABLE_REGION,''), ifnull(TAX_YN,''),";// 세금구분
			sql += " ifnull(DELV_TYPE,''), ifnull(DELV_COST,0), ifnull(BANPUM_AREA,''), ifnull(GOODS_COST,0), ifnull(GOODS_PRICE,0), ifnull(GOODS_CONSUMER_PRICE,0), ifnull(CHAR_1_NM,''), ifnull(CHAR_1_VAL,''), ";// 옵션상세명칭1
			sql += " ifnull(CHAR_2_NM,''), ifnull(CHAR_2_VAL,''), ifnull(IMG_PATH, ''), ifnull(IMG_PATH1,''), ifnull(IMG_PATH2,''), ifnull(IMG_PATH3,''), ifnull(IMG_PATH4,''), ifnull(IMG_PATH5,''), ifnull(IMG_PATH6,''), ";
			sql += " ifnull(IMG_PATH7,''), ifnull(IMG_PATH8,''), ifnull(IMG_PATH9, ''), ifnull(IMG_PATH10,''), ifnull(IMG_PATH11,''), ifnull(IMG_PATH12,''), ifnull(IMG_PATH13,''), ifnull(IMG_PATH14,''), "
					+ " ifnull(IMG_PATH15,''),ifnull(IMG_PATH16,''), ifnull(IMG_PATH17,''), ifnull(IMG_PATH18, ''), ifnull(IMG_PATH19,''), ifnull(IMG_PATH20,''), ifnull(IMG_PATH21,''), ifnull(IMG_PATH22,''), "
					+ " ifnull(IMG_PATH23,''), ifnull(IMG_PATH24,''), ifnull(GOODS_REMARKS,''), ifnull(CERTNO, ''), ifnull(AVLST_DM,''), ifnull(AVLED_DM,''), ifnull(ISSUEDATE,''), ifnull(CERTDATE,'')," // 인증일자
					+ " ifnull(CERT_AGENCY,''), ifnull(CERTFIELD,''), ifnull(MATERIAL,''), ifnull(STOCK_USE_YN, ''), ifnull(OPT_TYPE,''), ifnull(PROP_EDIT_YN,''), ifnull(PROP1_CD,''), ifnull(PROP_VAL1,''), "// 속성값1
					+ " ifnull(PROP_VAL2,''), ifnull(PROP_VAL3,''), ifnull(PROP_VAL4,''), ifnull(PROP_VAL5, ''), ifnull(PROP_VAL6,''), ifnull(PROP_VAL7,''), ifnull(PROP_VAL8,''), ifnull(PROP_VAL9,''), ifnull(PROP_VAL10,''), "
					+ " ifnull(PROP_VAL11,''), ifnull(PROP_VAL12,''), ifnull(PROP_VAL13,''), ifnull(PROP_VAL14, ''), ifnull(PROP_VAL15,''), ifnull(PROP_VAL16,''), ifnull(PROP_VAL17,''), ifnull(PROP_VAL18,''), "
					+ " ifnull(PROP_VAL19,''), ifnull(PROP_VAL20,''), ifnull(PROP_VAL21,''), ifnull(PROP_VAL22, ''), ifnull(PROP_VAL23,''), ifnull(PROP_VAL24,''), ifnull(PROP_VAL25,''), ifnull(PROP_VAL26,''), "
					+ " ifnull(PROP_VAL27,''), ifnull(PROP_VAL28,''), ifnull(PACK_CODE_STR,''), ifnull(GOODS_NM_EN, ''), ifnull(GOODS_NM_PR,''), ifnull(GOODS_REMARKS2,''), ifnull(GOODS_REMARKS3,''), "// 추가상품상세설명2
					+ " ifnull(GOODS_REMARKS4,''), ifnull(IMPORTNO,''), ifnull(GOODS_COST2,''), ifnull(ORIGIN2, ''), ifnull(EXPIRE_DM,''), ifnull(SUPPLY_SAVE_YN,''), ifnull(DESCRITION,''),  "// 관리자메모
					+ " ifnull(FAILCONTENT,''), ifnull(INSERTDT,''), ifnull(INSERTID,''), ifnull(MODIFYDT,''), ifnull(MODIFYID, '')";
			sql += " FROM shopproderr ";
			sql += " where compno = ? and SENDSEQ = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			for (int k = 0; k < list.size(); k++) {
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.get(k).getSendseq());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					int i = 0;
					List<String> listarr = new ArrayList<String>();
					for (int j = 1; j <= 122; j++) {
						listarr.add(rs.getString(j));
					}

					contents.add(listarr);

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	// 에러에서 실패했을때
	public void setProductErrorUpdate(List<List<String>> sendResult, List<List<String>> prodContents_target)
			throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		ResultSet rs = null;

		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodinfo 송신쇼핑몰번호 업데이트
			String sql_prodmst = " Update shopproderr " + "    set MODIFYDT = ? , MODIFYID = ? "
					+ "  where SENDSEQ = ? " + "    and compno = ? ";

			PreparedStatement pstmt = connection.prepareStatement(sql_prodmst);
			statementlist.add(pstmt);
			// [SUCCESS, 수정, sabang_prodcdcd, ydms_prodcd]
			// [FAIL, 수정, ydms_prodcd]
			for (int i = 0; i < sendResult.size(); i++) {
				List<String> alllist = prodContents_target.get(i);
				int idx = 0;
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());

				pstmt.setString(++idx, alllist.get(0));
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}

			pstmt.executeBatch();
			pstmt.clearParameters();
			// pstmt.clearBatch();// Batch 초기화

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public void setProductErrorNIninsert(List<List<String>> sendResult, List<List<String>> prodContents_target)
			throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = " insert into shopprodin (compno, SHOPCD, SHOPSEQ,  PRODSEQ, seq,GOODS_NM, GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, "
					+ " CLASS_CD2,CLASS_CD3, CLASS_CD4, PARTNER_ID,  DPARTNER_ID, MAKER, ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, STATUS, DELIV_ABLE_REGION, TAX_YN,DELV_TYPE,DELV_COST,BANPUM_AREA, "
					+ " GOODS_COST,GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM,  CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3, IMG_PATH4, IMG_PATH5, IMG_PATH6,IMG_PATH7,IMG_PATH8, "
					+ " IMG_PATH9,IMG_PATH10, IMG_PATH11, IMG_PATH12,  IMG_PATH13, IMG_PATH14, IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22,IMG_PATH23,IMG_PATH24, "
					+ " GOODS_REMARKS,CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, PROP1_CD, PROP_VAL1,PROP_VAL2,PROP_VAL3, PROP_VAL4, "
					+ " PROP_VAL5,PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10, PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18,PROP_VAL19,PROP_VAL20, PROP_VAL21, "
					+ " PROP_VAL22,PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN, GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, "
					+ " GOODS_COST2,ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN,DESCRITION, SHOPPRODNO, INSERTDT,INSERTID ) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,"// 23
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, "// 86
					+ " ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ) ";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (int i = 0; i < sendResult.size(); i++) {
				List<String> alllist = prodContents_target.get(i);
				int idx = 0;
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
				for (int k = 3; k <= 118; k++) {
					if (k == 118) {
						pstmt.setString(++idx, sendResult.get(i).get(3));
					} else {
						pstmt.setString(++idx, alllist.get(k - 1));
					}
				}
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());
				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			System.out.println("[setProductErrorNIninsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public int ShopProductUpdate(com.kdj.mlink.ezup3.shop.dao.ShopProductDto dto, String prodnm, String price)
			throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "update shopprodin " + "   set GOODS_NM=?, GOODS_PRICE=? " + " where SENDSEQ= ? "
					+ "   and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, prodnm);
			pstmt.setInt(++i, Integer.parseInt(price));

			pstmt.setString(++i, dto.getSendseq()); // 조건 key
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[larCategoryUpdate]" + pstmt.toString());
			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public ShopProductCoupangAdditionDto getAdditionaCoupangOneList(int seq) throws Exception {
		ShopProductCoupangAdditionDto dto = new ShopProductCoupangAdditionDto();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT    \r\n"
					+ "IFNULL(SEQ, '' ) AS SEQ, IFNULL(COMPNO, '' ) AS COMPNO,IFNULL(SHOPCD, '' ) AS SHOPCD,\r\n"
					+ "IFNULL(TITLE, '' ) AS TITLE,IFNULL(MEMO, '' ) AS MEMO,IFNULL(SELMTHDCD, '' ) AS SELMTHDCD,\r\n"
					+ "IFNULL(SHOPID, '' ) AS SHOPID,IFNULL(OFFERCONDITION, '' ) AS OFFERCONDITION,\r\n"
					+ "IFNULL(ORIGINALPRICD, '' ) AS ORIGINALPRICD,IFNULL(COMPANY_GOODS_CD, '' ) AS COMPANY_GOODS_CD,\r\n"
					+ "IFNULL(BARCD, '' ) AS BARCD,IFNULL(BARCDREASON, '' ) AS BARCDREASON,\r\n"
					+ "IFNULL(ADULTONLY, '' ) AS ADULTONLY,IFNULL(PARALLELIMPORT, '' ) AS PARALLELIMPORT,\r\n"
					+ "IFNULL(OVERSEASPURCHASED, '' ) AS OVERSEASPURCHASED,IFNULL(PCCNEEDED, '' ) AS PCCNEEDED,\r\n"
					+ "IFNULL(OVERSEASRECEIPT, '' ) AS OVERSEASRECEIPT,IFNULL(MESSAGE, '' ) AS MESSAGE,\r\n"
					+ "IFNULL(OUTBOUNDSHOPTIMEDAY, '' ) AS OUTBOUNDSHOPTIMEDAY,IFNULL(MAXIMUMBUYPERSON, '' ) AS MAXIMUMBUYPERSON,\r\n"
					+ "IFNULL(MAXIMUMBUYPERSONPERIOD, '' ) AS MAXIMUMBUYPERSONPERIOD,IFNULL(REPIMAGE, '' ) AS REPIMAGE,\r\n"
					+ "IFNULL(RECTIMAGE, '' ) AS RECTIMAGE,IFNULL(CARIMAGE, '' ) AS CARIMAGE,\r\n"
					+ "IFNULL(NOTICEIMAGE, '' ) AS NOTICEIMAGE,IFNULL(PAINTIMAGE, '' ) AS PAINTIMAGE,\r\n"
					+ "IFNULL(OTHERIMAGE, '' ) AS OTHERIMAGE,IFNULL(INCOMEIMAGE, '' ) AS INCOMEIMAGE,\r\n"
					+ "IFNULL(PRODNM, '' ) AS PRODNM,IFNULL(DELIVERYMETHOD, '' ) AS DELIVERYMETHOD,\r\n"
					+ "IFNULL(DELIVERYCOMPANYCD, '' ) AS DELIVERYCOMPANYCD,IFNULL(DELIVERYCHARGETYPE, '' ) AS DELIVERYCHARGETYPE,\r\n"
					+ "IFNULL(DELIVERYCHARGE, '' ) AS DELIVERYCHARGE,IFNULL(FREESHIPOVERCHARGE, '' ) AS FREESHIPOVERCHARGE,\r\n"
					+ "IFNULL(DELIVERYRETCHARGE, '' ) AS DELIVERYRETCHARGE,IFNULL(REMOTEAREACHARGE, '' ) AS REMOTEAREACHARGE,\r\n"
					+ "IFNULL(UNIONDELIVERYTYPE, '' ) AS UNIONDELIVERYTYPE,IFNULL(REMOTEAREADELIVERABLE, '' ) AS REMOTEAREADELIVERABLE,\r\n"
					+ "IFNULL(OUTADDRCD, '' ) AS OUTADDRCD,IFNULL(OUTADDRESS, '' ) AS OUTADDRESS,\r\n"
					+ "IFNULL(OUTADDRESSDTL, '' ) AS OUTADDRESSDTL,IFNULL(RETURNADDRCD, '' ) AS RETURNADDRCD,\r\n"
					+ "IFNULL(RETURNADDRCONTACTNUM, '' ) AS RETURNADDRCONTACTNUM,\r\n"
					+ "IFNULL(RETURNADDRZIPCD, '' ) AS RETURNADDRZIPCD,\r\n"
					+ "IFNULL(RETURNADDRESS, '' ) AS RETURNADDRESS,\r\n"
					+ "IFNULL(RETURNADDRESSDEL, '' ) AS RETURNADDRESSDEL,\r\n"
					+ "IFNULL(RETURNCHARGE, '' ) AS RETURNCHARGE,\r\n"
					+ "IFNULL(RETURNCHARGEVENDOR, '' ) AS RETURNCHARGEVENDOR,\r\n"
					+ "IFNULL(ASINFORMATION, '' ) AS ASINFORMATION,\r\n"
					+ "IFNULL(ASCONTACTNUM, '' ) AS ASCONTACTNUM,\r\n" + "IFNULL(PRODNMFIRST, '' ) AS PRODNMFIRST,\r\n"
					+ "IFNULL(PRODNMEND, '' ) AS PRODNMEND,\r\n" + "IFNULL(PRODNMDTLTOP, '' ) AS PRODNMDTLTOP,\r\n"
					+ "IFNULL(PRODNMDTLBOTTOM, '' ) AS PRODNMDTLBOTTOM,\r\n" + "IFNULL(USEYN, '' ) AS USEYN\r\n"
					+ "FROM shopaddrcoupangdtl WHERE SEQ = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, seq);
			System.out.println("[getAdditionaCoupangOneList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				dto.setSeq(rs.getString("SEQ")); // 일련번호
				dto.setCompno(rs.getString("COMPNO")); // 회사코드
				dto.setShopcd(rs.getString("SHOPCD")); // 쇼핑몰코드
				dto.setTitle(rs.getString("TITLE")); // 제목
				dto.setMemo(rs.getString("MEMO")); // 메모
				dto.setSelmthdcd(rs.getString("SELMTHDCD")); // 형태구분
				dto.setShopid(rs.getString("SHOPID")); // 쇼핑몰아이디
				dto.setOffercondition(rs.getString("OFFERCONDITION")); // 상품상태
				dto.setOriginalpricd(rs.getString("ORIGINALPRICD")); // 할인율기준가
				dto.setCompany_goods_cd(rs.getString("COMPANY_GOODS_CD")); // 판매자상품코드
				dto.setBarcd(rs.getString("BARCD")); // 바코드
				dto.setBarcdreason(rs.getString("BARCDREASON")); // 바코드사유
				dto.setAdultonly(rs.getString("ADULTONLY")); // 19세이상여부
				dto.setParallelimport(rs.getString("PARALLELIMPORT")); // 병행수입여부
				dto.setOverseaspurchased(rs.getString("OVERSEASPURCHASED")); // 해외구매대행여부
				dto.setPccneeded(rs.getString("PCCNEEDED")); // 개인통관부호여부
				dto.setOverseasreceipt(rs.getString("OVERSEASRECEIPT")); // 해외구매대행인보이스영수증
				dto.setMessage(rs.getString("MESSAGE")); // 추가정보요청메세지
				dto.setOutboundshoptimeday(rs.getString("OUTBOUNDSHOPTIMEDAY")); // 출고리드타임
				dto.setMaximumbuyperson(rs.getString("MAXIMUMBUYPERSON")); // 인당최대구매
				dto.setMaximumbuypersonperiod(rs.getString("MAXIMUMBUYPERSONPERIOD")); // 최대구매수량기간
				dto.setRepimage(rs.getString("REPIMAGE")); // 대표이미지
				dto.setRectimage(rs.getString("RECTIMAGE")); // 직사각형이미지
				dto.setCarimage(rs.getString("CARIMAGE")); // 자동차부품자기인증이미지
				dto.setNoticeimage(rs.getString("NOTICEIMAGE")); // 기능성표시이미지
				dto.setPaintimage(rs.getString("PAINTIMAGE")); // 화장품제조업등록필증
				dto.setOtherimage(rs.getString("OTHERIMAGE")); // 기타인증서류
				dto.setIncomeimage(rs.getString("INCOMEIMAGE")); // 수입신고필증이미지
				dto.setProdnm(rs.getString("PRODNM")); // 제품명
				dto.setDeliverymethod(rs.getString("DELIVERYMETHOD")); // 배송방법
				dto.setDeliverycompanycd(rs.getString("DELIVERYCOMPANYCD")); // 택배사
				dto.setDeliverychargetype(rs.getString("DELIVERYCHARGETYPE")); // 배송비종류
				dto.setDeliverycharge(rs.getString("DELIVERYCHARGE")); // 기본배송비
				dto.setFreeshipovercharge(rs.getString("FREESHIPOVERCHARGE")); // 조건부무료금액
				dto.setDeliveryretcharge(rs.getString("DELIVERYRETCHARGE")); // 초도반품배송비
				dto.setRemoteareacharge(rs.getString("REMOTEAREACHARGE")); // 도서산간배송비
				dto.setUniondeliverytype(rs.getString("UNIONDELIVERYTYPE")); // 합배송여부
				dto.setRemoteareadeliverable(rs.getString("REMOTEAREADELIVERABLE")); // 도서산간배송여부
				dto.setOutaddrcd(rs.getString("OUTADDRCD")); // 출고지코드
				dto.setOutaddress(rs.getString("OUTADDRESS")); // 출고지주소
				dto.setOutaddressdtl(rs.getString("OUTADDRESSDTL")); // 출고지상세주소
				dto.setReturnaddrcd(rs.getString("RETURNADDRCD")); // 반품지코드
				dto.setReturnaddrcontactnum(rs.getString("RETURNADDRCONTACTNUM")); // 반품지연락처
				dto.setReturnaddrzipcd(rs.getString("RETURNADDRZIPCD")); // 반품지우편번호
				dto.setReturnaddress(rs.getString("RETURNADDRESS")); // 반품지주소
				dto.setReturnaddressdel(rs.getString("RETURNADDRESSDEL")); // 반품지상세주소
				dto.setReturncharge(rs.getString("RETURNCHARGE")); // 반품배송비
				dto.setReturnchargevendor(rs.getString("RETURNCHARGEVENDOR")); // 착불여부
				dto.setAsinformation(rs.getString("ASINFORMATION")); // a/s안내
				dto.setAscontactnum(rs.getString("ASCONTACTNUM")); // a/s전화번호
				dto.setProdnmfirst(rs.getString("PRODNMFIRST")); // 상품명앞추가문구
				dto.setProdnmend(rs.getString("PRODNMEND")); // 상품명뒷추가문구
				dto.setProdnmdtltop(rs.getString("PRODNMDTLTOP")); // 상품설명상단추가문구
				dto.setProdnmdtlbottom(rs.getString("PRODNMDTLBOTTOM")); // 상품설명하단추가문구
				dto.setUseyn(rs.getString("USEYN")); // 사용여부
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;

	}

	public ShopProductCafe24AdditionDto getAdditionaCafe24OneList(int seq) throws Exception {
		ShopProductCafe24AdditionDto dto = new ShopProductCafe24AdditionDto();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(compno,''), ifnull(SHOPCD,''),ifnull(SEQ,''),ifnull(TITLE,''),ifnull(MEMO,''),ifnull(SHOPID,''),ifnull(SUPPLY,''),ifnull(CONSUMERS,''),"
					+ " 	  ifnull(MANFNM,''),ifnull(SUPPLIER,''),ifnull(BRANDNM,''),ifnull(TRENDNM,''),ifnull(REPIMAGE,''),ifnull(LISTIMAGE,''),ifnull(IMAGEMETHOD,''),ifnull(ADDIIMAGEYN,''),ifnull(ADDIIMAGECNT,'')"
					+ " 	  ,ifnull(SHIPPINGINFO,''), ifnull(SHIPPINGMETHOD,''),ifnull(HOMENABROSHIP,''), ifnull(SHIPPINGAREA,''),ifnull(DELVPERIODFROM,''),ifnull(DELVPERIODTO,''),ifnull(DELVCOSTMETHOD,''),"
					+ " 	  ifnull(DELVCOST1,''),ifnull(DELVCOST2,''),ifnull(DELVCOST3,''),ifnull(DELVFEESETUP,''), ifnull(PRODSTATS,''),ifnull(USEDPERIOD,''), ifnull(SHIPPINGTYPE,''),ifnull(OTHERVAL1,''), "
					+ " 	  ifnull(OTHERVAL2,''),ifnull(MINORSELCNYN,''),ifnull(ORDERQTYTYPE,''),ifnull(MINIMUMQTY,''), ifnull(MAXIMUMTYPE,''),ifnull(MAXIMUMQTY,''), ifnull(USEYN,'') "
					+ "  from shopaddrcafe24dtl " + " where seq=? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, seq);
			System.out.println("[getShopMstOneSelectAllList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int columnIndex = 0;
				dto.setCompno(rs.getString(++columnIndex));
				dto.setShopcd(rs.getString(++columnIndex));
				dto.setSeq(rs.getString(++columnIndex));
				dto.setTitle(rs.getString(++columnIndex));
				dto.setShopid(rs.getString(++columnIndex));
				dto.setMemo(rs.getString(++columnIndex));
				dto.setSupply(rs.getString(++columnIndex));
				dto.setConsumers(rs.getString(++columnIndex));
				dto.setManfnm(rs.getString(++columnIndex));
				dto.setSupplier(rs.getString(++columnIndex));
				dto.setBrandnm(rs.getString(++columnIndex));
				dto.setTrendnm(rs.getString(++columnIndex));
				dto.setRepimage(rs.getString(++columnIndex));
				dto.setListimage(rs.getString(++columnIndex));
				dto.setImagemethod(rs.getString(++columnIndex));
				dto.setAddiimageyn(rs.getString(++columnIndex));
				dto.setAddiimagecnt(rs.getString(++columnIndex));
				dto.setShippinginfo(rs.getString(++columnIndex));
				dto.setShippingmethod(rs.getString(++columnIndex));
				dto.setHomenabroship(rs.getString(++columnIndex));
				dto.setShippingarea(rs.getString(++columnIndex));
				dto.setDelvperiodfrom(rs.getString(++columnIndex));
				dto.setDelvperiodto(rs.getString(++columnIndex));
				dto.setDelvcostmethod(rs.getString(++columnIndex));
				dto.setDelvcost1(rs.getString(++columnIndex));
				dto.setDelvcost2(rs.getString(++columnIndex));
				dto.setDelvcost3(rs.getString(++columnIndex));
				dto.setDelvfeesetup(rs.getString(++columnIndex));
				dto.setProdstats(rs.getString(++columnIndex));
				dto.setUsedperiod(rs.getString(++columnIndex));
				dto.setShippingtype(rs.getString(++columnIndex));
				dto.setOtherval1(rs.getString(++columnIndex));
				dto.setOtherval2(rs.getString(++columnIndex));
				dto.setMinorselcnyn(rs.getString(++columnIndex));
				dto.setOrderqtytype(rs.getString(++columnIndex));
				dto.setMinimumqty(rs.getString(++columnIndex));
				dto.setMaximumtype(rs.getString(++columnIndex));
				dto.setMaximumqty(rs.getString(++columnIndex));
				dto.setUseyn(rs.getString(++columnIndex));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	public ShopProductAdditionDto getAdditionaNaverStoreOneList(int seq) throws Exception {
		ShopProductNaverStoreAdditionDto dto = new ShopProductNaverStoreAdditionDto();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(compno,''), ifnull(SHOPCD,''),ifnull(SEQ,''),ifnull(TITLE,''),ifnull(MEMO,''),ifnull(SHOPID,''),ifnull(PRODSTATS,''),ifnull(MINORTYP,''),"
					+ " 	  ifnull(IMAGE,''),ifnull(ASTELNO,''),ifnull(ASDTL,''),ifnull(PRICECOMPARISON,''),ifnull(REVIEW,''),ifnull(MINIMUMTYP,''),ifnull(MINIMUMQTY,'0'),ifnull(MAXIMUMTYP,''),"
					+ " 	  ifnull(MAXIMUMVAL,''), ifnull(MAXIMUMQTY,'0'), ifnull(GENERALDELVTYP,''), ifnull(CUSTOMORDTYP,''), ifnull(SENDDATE,''), ifnull(SHIPMETHOD,''), ifnull(BUNDLEDSHIPTYP,''),"
					+ " 	  ifnull(BUNDLEDSHIPCD,''), ifnull(SHIPDELVTYP,''),ifnull(DELVCOST,'0'), ifnull(CONDITIONALTYP,''),ifnull(QTYDIVISIONTYP,''),ifnull(DELVCOST2,''),ifnull(PREPAYMENT,''),"
					+ " 	  ifnull(AREADELVTYP,''),ifnull(JEJUCOST,'0'),ifnull(ISLANDCOST,'0'),ifnull(RETNCNGEXP,''), ifnull(RETCOST,'0'),ifnull(CNGCOST,'0'), ifnull(OUTADDR,''),ifnull(RETADDR,''), "
					+ " 	  ifnull(PRODCDTYP,''),ifnull(PRODCDINPUT,''), ifnull(CHILDCERT,''), ifnull(KCCERT,''),ifnull(ECOCERT,''), ifnull(KCEXEMPTION,''),ifnull(SALESPERIODTYP,''), ifnull(SALESFROM,''), ifnull(SALESTO,''), "
					+ " 	  ifnull(OPTIONTYP,''),ifnull(CUSTOMMADE,''),ifnull(SUMMARYTYP1,''),ifnull(SUMMARYVAL1,''), ifnull(SUMMARYTYP2,''),ifnull(SUMMARYVAL2,''), ifnull(SUMMARYTYP3,''), "
					+ " 	  ifnull(SUMMARYVAL3,''),  ifnull(SUMMARYTYP4,''),ifnull(SUMMARYVAL4,''),ifnull(SUMMARYTYP5,''),ifnull(SUMMARYVAL5,''), ifnull(USEYN,'') "
					+ "  from shopaddrnaverstoredtl " + " where seq=? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, seq);
			System.out.println("[getAdditionaNaverStoreOneList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				dto.setCompno(rs.getString(++columnIndex)); // 일련번호
				dto.setShopcd(rs.getString(++columnIndex)); // 회사코드
				dto.setSeq(rs.getString(++columnIndex)); // 쇼핑몰코드
				dto.setTitle(rs.getString(++columnIndex)); // 제목
				dto.setMemo(rs.getString(++columnIndex)); // 메모
				dto.setShopid(rs.getString(++columnIndex)); // 쇼핑몰아이디
				dto.setProdstats(rs.getString(++columnIndex)); // 상품상태
				dto.setMinortyp(rs.getString(++columnIndex)); // 미성년자여부
				dto.setImage(rs.getString(++columnIndex)); // 대표이미지
				dto.setAstelno(rs.getString(++columnIndex)); // A/S전화번호
				dto.setAsdtl(rs.getString(++columnIndex)); // A/S정보
				dto.setPricecomparison(rs.getString(++columnIndex)); // 가격비교여부
				dto.setReview(rs.getString(++columnIndex)); // 구매평노출여부
				dto.setMinimumtyp(rs.getString(++columnIndex)); // 최소수량여부
				dto.setMinimumqty(rs.getString(++columnIndex)); // 최소수량
				dto.setMaximumtyp(rs.getString(++columnIndex)); // 최대수량여부
				dto.setMaximumval(rs.getString(++columnIndex)); // 최대수량조건
				dto.setMaximumqty(rs.getString(++columnIndex)); // 최대수량
				dto.setGeneraldelvtyp(rs.getString(++columnIndex)); // 배송속성여부
				dto.setCustomordtyp(rs.getString(++columnIndex)); // 주문후맞춤제작여부
				dto.setSenddate(rs.getString(++columnIndex)); // 발송예정일
				dto.setShipmethod(rs.getString(++columnIndex)); // 배송방법
				dto.setBundledshiptyp(rs.getString(++columnIndex)); // 묶음배송여부
				dto.setBundledshipcd(rs.getString(++columnIndex)); // 묶음배송코드
				dto.setShipdelvtyp(rs.getString(++columnIndex)); // 배송비유형
				dto.setDelvcost(rs.getString(++columnIndex)); // 기본배송비
				dto.setConditionaltyp(rs.getString(++columnIndex)); // 조건부무료시무료여부체크
				dto.setQtydivisiontyp(rs.getString(++columnIndex)); // 수량별무료시조건
				dto.setDelvcost2(rs.getString(++columnIndex)); // 배송비상세금액들
				dto.setPrepayment(rs.getString(++columnIndex)); // 선결제여부
				dto.setAreadelvtyp(rs.getString(++columnIndex)); // 지역별배송비부과여부
				dto.setJejucost(rs.getString(++columnIndex)); // 제주배송비
				dto.setIslandcost(rs.getString(++columnIndex)); // 제주및도서산간배송비
				dto.setRetncngexp(rs.getString(++columnIndex)); // 교환/반품택배사
				dto.setRetcost(rs.getString(++columnIndex)); // 반품배송비
				dto.setCngcost(rs.getString(++columnIndex)); // 교환배송비
				dto.setOutaddr(rs.getString(++columnIndex)); // 출고지주소
				dto.setRetaddr(rs.getString(++columnIndex)); // 반품지주소
				dto.setProdcdtyp(rs.getString(++columnIndex)); // 상품코드타입
				dto.setProdcdinput(rs.getString(++columnIndex)); // 상품코드
				dto.setChildcert(rs.getString(++columnIndex)); // 어린이인증
				dto.setKccert(rs.getString(++columnIndex)); // KC인증제외여부
				dto.setEcocert(rs.getString(++columnIndex)); // 친환경인증여부
				dto.setKcexemption(rs.getString(++columnIndex)); // KC면제인증여부
				dto.setSalesperiodtyp(rs.getString(++columnIndex)); // 판매기간설정여부
				dto.setSalesfrom(rs.getString(++columnIndex)); // 판매시작일
				dto.setSalesto(rs.getString(++columnIndex)); // 판매종료일
				dto.setOptiontyp(rs.getString(++columnIndex)); // 옵션타입
				dto.setCustommade(rs.getString(++columnIndex)); // 맞춤제작여부
				dto.setSummarytyp1(rs.getString(++columnIndex)); // 요약정보1
				dto.setSummaryval1(rs.getString(++columnIndex)); // 요약내용1
				dto.setSummarytyp2(rs.getString(++columnIndex)); // 요약정보2
				dto.setSummaryval2(rs.getString(++columnIndex)); // 요약내용2
				dto.setSummarytyp3(rs.getString(++columnIndex)); // 요약정보3
				dto.setSummaryval3(rs.getString(++columnIndex)); // 요약내용3
				dto.setSummarytyp4(rs.getString(++columnIndex)); // 요약정보4
				dto.setSummaryval4(rs.getString(++columnIndex)); // 요약내용4
				dto.setSummarytyp5(rs.getString(++columnIndex)); // 요약정보5
				dto.setSummaryval5(rs.getString(++columnIndex)); // 요약내용5
				dto.setUseyn(rs.getString(++columnIndex)); // 사용유무

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	public ShopProductAdditionDto getAdditionaAuctionOneList(int seq) throws Exception {
		ShopProductAuctionAdditionDto dto = new ShopProductAuctionAdditionDto();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(SEQ,''), ifnull(COMPNO,''),ifnull(SHOPCD,''),ifnull(TITLE,''),ifnull(SHOPID,''),ifnull(MEMO,''),ifnull(SELMTHDCD,''),ifnull(VALUE1,''),ifnull(VALUE2,''),ifnull(VALUE3,''),"
					+ " ifnull(VALUE4,''),ifnull(VALUE5,''),ifnull(VALUE6,''),ifnull(VALUE7,''),ifnull(VALUE8,''),ifnull(VALUE9,''),ifnull(VALUE10,''),ifnull(VALUE11,''),ifnull(VALUE12,''),"
					+ "	ifnull(PRODSTATCD,''),ifnull(USEDPERIOD,'0'),ifnull(VALUE13,'0'), ifnull(MAXIMUMQTYTYP,''),ifnull(LIMITQTY,'0'),ifnull(LIMITTIME,'0'),ifnull(OPTEXHIBITION,''),ifnull(VALUE14,''),"
					+ " ifnull(INPUTTYPOPT,''),ifnull(VALUE15,''),ifnull(SALESPERIOD,''),ifnull(PRE_SALE,''),ifnull(VALUE17,''),ifnull(IMAGE,''),"
					+ " ifnull(PRODINFOAREA,''), ifnull(PRODADDITIONAL,''), ifnull(PROMOTION,''), ifnull(ADULTGOODS,''), ifnull(CHILDPROD,''), ifnull(HOMEHOLDPROD,''), ifnull(ELECTRICAL,''),"
					+ " ifnull(CHEMISTRY,''), ifnull(ECO,''),ifnull(MEDICAL,'0'), ifnull(ORIGIN,''),ifnull(MULTIORIGIN,''),ifnull(ASDTL,''),ifnull(SHIPPINGPOLICY,''),ifnull(SHIPPINGMETHOD,''), "
					+ " ifnull(EXPRESS,''),ifnull(VALUE17,''),ifnull(VALUE69,'0'),ifnull(VALUE70,''),ifnull(VALUE18,''),ifnull(VALUE19,''),ifnull(VALUE20,''),ifnull(VALUE21,''),ifnull(VALUE22,''),ifnull(VALUE23,'0'),"
					+ " ifnull(OUTADDR,''),ifnull(BUNDLEDYN,''),ifnull(BUNDLEMAXNMIN,''), ifnull(TEMPLATE,''),ifnull(DELVCOSTTYP,''), ifnull(DELVCOST,''),ifnull(DELVFREECOST,''), "
					+ " ifnull(COSTDISCOUNT,''),ifnull(VALUE24,'0'),ifnull(VALUE25,'0'),ifnull(VALUE26,'0'),ifnull(VALUE27,'0'),ifnull(VALUE28,'0'),ifnull(VALUE29,'0'),ifnull(VALUE30,'0'),"
					+ " ifnull(VALUE31,'0'),ifnull(VALUE32,'0'),ifnull(VALUE33,'0'), ifnull(PREPAYMENT,''), ifnull(RETNEXCHANGEADDR,''), ifnull(RETNEXCHANGECOST,'0'),ifnull(ORDERDELAYTIME,'0'), "
					+ " ifnull(VALUE34,''),ifnull(VALUE35,''),ifnull(VALUE36,''), ifnull(VALUE37,''), ifnull(VALUE38,''), ifnull(VALUE39,''),ifnull(VALUE40,''), ifnull(VALUE41,''),ifnull(VALUE42,''),ifnull(VALUE43,''), "
					+ " ifnull(VALUE44,''),ifnull(VALUE45,'0'),ifnull(VALUE46,'0'), ifnull(VALUE47,'0'), ifnull(VALUE48,'0'), ifnull(VALUE49,''),ifnull(VALUE50,''), ifnull(VALUE51,''), ifnull(VALUE52,''),ifnull(VALUE53,''),"
					+ " ifnull(VALUE54,''),ifnull(VALUE55,'0'),ifnull(VALUE56,''), ifnull(VALUE57,''), ifnull(VALUE58,''), ifnull(VALUE59,''),ifnull(VALUE60,''), ifnull(VALUE61,'0'),ifnull(VALUE62,''), "
					+ " ifnull(VALUE63,'0'),ifnull(VALUE64,''),ifnull(VALUE65,'0'), ifnull(VALUE66,''), ifnull(VALUE67,''), ifnull(VALUE68,'0'), ifnull(USEYN,'') "
					+ "  from shopaddrauctiondtl " + " where seq=? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, seq);
			System.out.println("[getAdditionaNaverStoreOneList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				dto.setSeq(rs.getString(++columnIndex)); // 일련번호
				dto.setCompno(rs.getString(++columnIndex)); // 회사코드
				dto.setShopcd(rs.getString(++columnIndex)); // 쇼핑몰코드
				dto.setTitle(rs.getString(++columnIndex)); // 제목
				dto.setShopid(rs.getString(++columnIndex)); // 쇼핑몰아이디
				dto.setMemo(rs.getString(++columnIndex)); // 메모
				dto.setSelmthdcd(rs.getString(++columnIndex)); // 형태구분
				dto.setValue1(rs.getString(++columnIndex)); // 필수속성정보
				dto.setValue2(rs.getString(++columnIndex)); // 브랜드카테고리선택
				dto.setValue3(rs.getString(++columnIndex)); // 소호노출범위여부
				dto.setValue4(rs.getString(++columnIndex)); // 소호노출카테고리
				dto.setValue5(rs.getString(++columnIndex)); // 소호노출스타일
				dto.setValue6(rs.getString(++columnIndex)); // 소호노출샤프
				dto.setValue7(rs.getString(++columnIndex)); // 소호노출칼라
				dto.setValue8(rs.getString(++columnIndex)); // 판매자닉네임여부
				dto.setValue9(rs.getString(++columnIndex)); // 모델명적용여부
				dto.setValue10(rs.getString(++columnIndex)); // 모델명
				dto.setValue11(rs.getString(++columnIndex)); // 모델명수정여부
				dto.setValue12(rs.getString(++columnIndex)); // ESM브랜드
				dto.setProdstatcd(rs.getString(++columnIndex)); // 상품상태
				dto.setUsedperiod(rs.getString(++columnIndex)); // 중고개월수
				dto.setValue13(rs.getString(++columnIndex)); // 쿠폰유효기간
				dto.setMaximumqtytyp(rs.getString(++columnIndex)); // 최대구매허용수량여부
				dto.setLimitqty(rs.getString(++columnIndex)); // 제한수량
				dto.setLimittime(rs.getString(++columnIndex)); // 제한기간
				dto.setOptexhibition(rs.getString(++columnIndex)); // 옵션재고전시방법
				dto.setValue14(rs.getString(++columnIndex)); // 옵션노출정렬여부
				dto.setInputtypopt(rs.getString(++columnIndex)); // 입력형옵션
				dto.setValue15(rs.getString(++columnIndex)); // 추가상품전시여부
				dto.setSalesperiod(rs.getString(++columnIndex)); // 판매기간
				dto.setPre_sale(rs.getString(++columnIndex)); // 예약판매여부
				dto.setValue16(rs.getString(++columnIndex)); // 예약판매배송일
				dto.setImage(rs.getString(++columnIndex)); // 대표이미지
				dto.setProdinfoarea(rs.getString(++columnIndex)); // 상품정보입력영역
				dto.setProdadditional(rs.getString(++columnIndex)); // 추가구성입력영역
				dto.setPromotion(rs.getString(++columnIndex)); // 광고/홍보입력영역
				dto.setAdultgoods(rs.getString(++columnIndex)); // 성인용품여부
				dto.setChildprod(rs.getString(++columnIndex)); // 어린이제품
				dto.setHomeholdprod(rs.getString(++columnIndex)); // 생활용품
				dto.setElectrical(rs.getString(++columnIndex)); // 전기용품
				dto.setChemistry(rs.getString(++columnIndex)); // 생활화학/살생물제품
				dto.setEco(rs.getString(++columnIndex)); // 친환경
				dto.setMedical(rs.getString(++columnIndex)); // 의료기기
				dto.setOrigin(rs.getString(++columnIndex)); // 원산지선택여부
				dto.setMultiorigin(rs.getString(++columnIndex)); // 복수원산지여부
				dto.setAsdtl(rs.getString(++columnIndex)); // A/S정보
				dto.setShippingpolicy(rs.getString(++columnIndex)); // 발송정책
				dto.setShippingmethod(rs.getString(++columnIndex)); // 배송방법
				dto.setExpress(rs.getString(++columnIndex)); // 택배사
				dto.setValue17(rs.getString(++columnIndex)); // 방문수령여부
				dto.setValue69(rs.getString(++columnIndex)); // 방문수령가격할인금액
				dto.setValue70(rs.getString(++columnIndex)); // 방문수령사은품
				dto.setValue18(rs.getString(++columnIndex)); // 방문수령주소
				dto.setValue19(rs.getString(++columnIndex)); // 퀵서비스업체명
				dto.setValue20(rs.getString(++columnIndex)); // 퀵서비스연락처
				dto.setValue21(rs.getString(++columnIndex)); // 퀵서비스배송가능지역
				dto.setValue22(rs.getString(++columnIndex)); // 일반우편여부
				dto.setValue23(rs.getString(++columnIndex)); // 일반우폄금액
				dto.setOutaddr(rs.getString(++columnIndex)); // 출하지
				dto.setBundledyn(rs.getString(++columnIndex)); // 묶음배송여부
				dto.setBundlemaxnmin(rs.getString(++columnIndex)); // 묶음배송시배송비
				dto.setTemplate(rs.getString(++columnIndex)); // 묶음배송시템플릿
				dto.setDelvcosttyp(rs.getString(++columnIndex)); // 택배비무료여부
				dto.setDelvcost(rs.getString(++columnIndex)); // 배송기본배송비
				dto.setDelvfreecost(rs.getString(++columnIndex)); // 기본배송비2
				dto.setCostdiscount(rs.getString(++columnIndex)); // 배송비할인여부
				dto.setValue24(rs.getString(++columnIndex)); // 수량별차등수량1
				dto.setValue25(rs.getString(++columnIndex)); // 수량별차등수량2
				dto.setValue26(rs.getString(++columnIndex)); // 수량별차등수량3
				dto.setValue27(rs.getString(++columnIndex)); // 수량별차등수량4
				dto.setValue28(rs.getString(++columnIndex)); // 수량별차등수량5
				dto.setValue29(rs.getString(++columnIndex)); // 수량별차등배송비1
				dto.setValue30(rs.getString(++columnIndex)); // 수량별차등배송비2
				dto.setValue31(rs.getString(++columnIndex)); // 수량별차등배송비3
				dto.setValue32(rs.getString(++columnIndex)); // 수량별차등배송비4
				dto.setValue33(rs.getString(++columnIndex)); // 수량별차등배송비5
				dto.setPrepayment(rs.getString(++columnIndex)); // 착/선불여부
				dto.setRetnexchangeaddr(rs.getString(++columnIndex)); // 교환/반품주소
				dto.setRetnexchangecost(rs.getString(++columnIndex)); // 교환/반품배송비
				dto.setOrderdelaytime(rs.getString(++columnIndex)); // 주문후예상배송기간
				dto.setValue34(rs.getString(++columnIndex)); // 건강식품신고기관명
				dto.setValue35(rs.getString(++columnIndex)); // 건강식품신고번호
				dto.setValue36(rs.getString(++columnIndex)); // 건강식품심의번호
				dto.setValue37(rs.getString(++columnIndex)); // 판매자관리코드
				dto.setValue38(rs.getString(++columnIndex)); // 판매자직접입력관리코드
				dto.setValue39(rs.getString(++columnIndex)); // 판매자카테고리
				dto.setValue40(rs.getString(++columnIndex)); // 사은품
				dto.setValue41(rs.getString(++columnIndex)); // 포털가격비교사이트등록여부
				dto.setValue42(rs.getString(++columnIndex)); // 포털가격비교사이트쿠폰적용여부
				dto.setValue43(rs.getString(++columnIndex)); // 사이트부담지원할인
				dto.setValue44(rs.getString(++columnIndex)); // 휴대폰가입신청서URL
				dto.setValue45(rs.getString(++columnIndex)); // 렌탈설치비
				dto.setValue46(rs.getString(++columnIndex)); // 렌탈의무사용기간
				dto.setValue47(rs.getString(++columnIndex)); // 렌탈등록비
				dto.setValue48(rs.getString(++columnIndex)); // 옵션관리코드여부
				dto.setValue49(rs.getString(++columnIndex)); // 기타특이사항
				dto.setValue50(rs.getString(++columnIndex)); // 상품명앞추가문구
				dto.setValue51(rs.getString(++columnIndex)); // 상품명뒷추가문구
				dto.setValue52(rs.getString(++columnIndex)); // 상품설명상단추가문구
				dto.setValue53(rs.getString(++columnIndex)); // 상품설명하단추가문구
				dto.setValue54(rs.getString(++columnIndex)); // 판매자부담할인여부
				dto.setValue55(rs.getString(++columnIndex)); // 판매자부담할인금액
				dto.setValue56(rs.getString(++columnIndex)); // 판매자부담할인시작일
				dto.setValue57(rs.getString(++columnIndex)); // 판매자부담할인종료일
				dto.setValue58(rs.getString(++columnIndex)); // 특별할인여부
				dto.setValue59(rs.getString(++columnIndex)); // 특별할인시작일
				dto.setValue60(rs.getString(++columnIndex)); // 특별할인종료일
				dto.setValue61(rs.getString(++columnIndex)); // 특별할인회원할인금액
				dto.setValue62(rs.getString(++columnIndex)); // 특별할인복수구매주문수량
				dto.setValue63(rs.getString(++columnIndex)); // 특별할인복수구매할인금액
				dto.setValue64(rs.getString(++columnIndex)); // 판매자스마일캐시여부
				dto.setValue65(rs.getString(++columnIndex)); // 스마일캐시적립률
				dto.setValue66(rs.getString(++columnIndex)); // 나눔쇼핑시작일
				dto.setValue67(rs.getString(++columnIndex)); // 나눔쇼핑종료일
				dto.setValue68(rs.getString(++columnIndex)); // 나눔쇼핑금액
				dto.setUseyn(rs.getString(++columnIndex)); // 사용여부
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	public ShopProductAdditionDto getAdditionaInterparkOneList(int seq) throws Exception {
		ShopProductInterParkAdditionDto dto = new ShopProductInterParkAdditionDto();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(compno,''), ifnull(SHOPCD,''),ifnull(SEQ,''),ifnull(TITLE,''),ifnull(MEMO,''),ifnull(SELMTHDCD,''),ifnull(CONTRACTTYPCD,''),ifnull(PRODTYPCD,''),"
					+ " 	  ifnull(PRODSTATCD,''),ifnull(KEYWORD,''),ifnull(PRODNO,''),ifnull(OVERSEASPURCHASED,''),ifnull(MINORSELCNYN,''),ifnull(HEALTHFUNCFOOD,''),ifnull(SALESREPORTING,''),ifnull(SALESREPORTINGNO,''),"
					+ " 	  ifnull(ADREVIEW,''), ifnull(ADREVIEWNO,''), ifnull(ASDTL,''), ifnull(UNIQUENESSYN,''), ifnull(UNIQUENESSVAL,''), ifnull(SALESPERIODYN,''), ifnull(SALESPERIODTYP,''),"
					+ " 	  ifnull(SALESPERIODFROM,''), ifnull(SALESPERIODTO,''),ifnull(QUANTITYYN,''), ifnull(QUANTITYCNT,'0'),ifnull(EXPRESSTYP,''),ifnull(DELVTYPE,''),ifnull(DELVMETHOD,''),ifnull(SHIPPRC,'0'), "
					+ " 	  ifnull(DELVFREECHK,''), ifnull(SHIPPRC2,'0'),ifnull(DELVQTY,''), ifnull(DELVQTYCOST,''),ifnull(PREPAYMENT,''), "
					+ " 	  ifnull(SHIPUNIQUENE,''),ifnull(JEJUPRC,'0'),ifnull(ISLANDPRC,'0'),ifnull(ADDRIN,''), ifnull(RETURNSHIPPINGYN,''),ifnull(RETNCNGPRC,''),ifnull(USEYN,'') "
					+ "  from shopaddrinterparkdtl " + " where seq=? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, seq);
			System.out.println("[getAdditionaNaverStoreOneList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int columnIndex = 0;
				dto.setCompno(rs.getString(++columnIndex)); // 일련번호
				dto.setShopcd(rs.getString(++columnIndex)); // 회사코드
				dto.setSeq(rs.getString(++columnIndex)); // 쇼핑몰코드
				dto.setTitle(rs.getString(++columnIndex)); // 제목
				dto.setMemo(rs.getString(++columnIndex)); // 메모
				dto.setSelmthdcd(rs.getString(++columnIndex)); // 쇼핑몰아이디
				dto.setContracttypcd(rs.getString(++columnIndex)); // 상품상태
				dto.setProdtypcd(rs.getString(++columnIndex)); // 미성년자여부
				dto.setProdstatcd(rs.getString(++columnIndex)); // 대표이미지
				dto.setKeyword(rs.getString(++columnIndex)); // A/S전화번호
				dto.setProdno(rs.getString(++columnIndex)); // A/S정보
				dto.setOverseaspurchased(rs.getString(++columnIndex)); // 가격비교여부
				dto.setMinorselcnyn(rs.getString(++columnIndex)); // 구매평노출여부
				dto.setHealthfuncfood(rs.getString(++columnIndex)); // 최소수량여부
				dto.setSalesreporting(rs.getString(++columnIndex)); // 최소수량
				dto.setSalesreportingno(rs.getString(++columnIndex)); // 최대수량여부
				dto.setAdreview(rs.getString(++columnIndex)); // 최대수량조건
				dto.setAdreviewno(rs.getString(++columnIndex)); // 최대수량
				dto.setAsdtl(rs.getString(++columnIndex)); // 배송속성여부
				dto.setUniquenessyn(rs.getString(++columnIndex)); // 주문후맞춤제작여부
				dto.setUniquenessval(rs.getString(++columnIndex)); // 발송예정일
				dto.setSalesperiodyn(rs.getString(++columnIndex)); // 배송방법
				dto.setSalesperiodtyp(rs.getString(++columnIndex)); // 묶음배송여부
				dto.setSalesperiodfrom(rs.getString(++columnIndex)); // 묶음배송코드
				dto.setSalesperiodto(rs.getString(++columnIndex)); // 배송비유형
				dto.setQuantityyn(rs.getString(++columnIndex)); // 기본배송비
				dto.setQuantitycnt(rs.getString(++columnIndex)); // 조건부무료시무료여부체크
				dto.setExpresstyp(rs.getString(++columnIndex)); // 수량별무료시조건
				dto.setDelvtype(rs.getString(++columnIndex)); // 배송비상세금액들
				dto.setDelvmethod(rs.getString(++columnIndex));// 배송비종류
				dto.setShipprc(rs.getString(++columnIndex)); // 배송비상세금액들
				dto.setDelvfreechk(rs.getString(++columnIndex));
				dto.setShipprc2(rs.getString(++columnIndex));
				dto.setDelvqty(rs.getString(++columnIndex));
				dto.setDelvqtycost(rs.getString(++columnIndex));
				dto.setPrepayment(rs.getString(++columnIndex));
				dto.setShipuniquene(rs.getString(++columnIndex)); // 지역별배송비부과여부
				dto.setJejuprc(rs.getString(++columnIndex)); // 제주배송비
				dto.setIslandprc(rs.getString(++columnIndex)); // 제주및도서산간배송비
				dto.setAddrin(rs.getString(++columnIndex)); // 교환/반품택배사
				dto.setReturnshippingyn(rs.getString(++columnIndex)); // 반품배송비
				dto.setRetncngprc(rs.getString(++columnIndex));
				dto.setUseyn(rs.getString(++columnIndex)); // 교환배송비

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;
	}

	// productin 업데이트
	public int setProductInUpdate(ShopProductDto prod, String seq, String dbname) throws Exception {

		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		try {

			connection = DBCPInit.getInstance().getConnection();

			String sql = "update " + dbname
					+ "   set GOODS_NM = ?, GOODS_KEYWORD = ?, GOODS_NM_EN = ?, GOODS_NM_PR = ?, MODEL_NM = ?, MODEL_NO = ?, BRAND_NM = ?, COMPAYNY_GOODS_CD = ?, "
					+ " 	  GOODS_SEARCH = ?, CERTNO = ?, AVLST_DM = ?, AVLED_DM = ?, ISSUEDATE = ?, CERTDATE = ?, CERT_AGENCY = ?, CERTFIELD = ?, IMG_PATH23 = ?, "
					+ " 	  GOODS_GUBUN = ?, PARTNER_ID = ?, DPARTNER_ID = ?, CLASS_CD1 = ?, CLASS_CD2 = ?, CLASS_CD3 = ?, CLASS_CD4 = ?, MAKER = ?, ORIGIN = ?, "
					+ " 	  IMPORTNO = ?, IMG_PATH24 = ?, MATERIAL = ?, MAKE_YEAR = ?, MAKE_DM = ?, EXPIRE_DM = ?, SUPPLY_SAVE_YN = ?, GOODS_SEASON = ?, "
					+ " 	  SEX = ?, STATUS = ?, DELIV_ABLE_REGION = ?, DELV_TYPE = ?, DELV_COST = ?, TAX_YN = ?, BANPUM_AREA = ?, GOODS_COST = ?, GOODS_COST2 = ?, GOODS_PRICE = ?, "
					+ " 	  GOODS_CONSUMER_PRICE = ?,  STOCK_USE_YN = ?, OPT_TYPE = ?, IMG_PATH = ?, "
					+ " 	  IMG_PATH1 = ?, IMG_PATH2 = ?, IMG_PATH3 = ?, IMG_PATH4 = ?, IMG_PATH5 = ?, IMG_PATH6 = ?, IMG_PATH7 = ?, IMG_PATH8 = ?, IMG_PATH9 = ?, "
					+ " 	  IMG_PATH10 = ?, IMG_PATH11 = ?, IMG_PATH12 = ?, IMG_PATH13 = ?, IMG_PATH14 = ?, IMG_PATH15 = ?, IMG_PATH16 = ?, IMG_PATH17 = ?, "
					+ " 	  IMG_PATH18 = ?, IMG_PATH19 = ?, IMG_PATH20 = ?, IMG_PATH21 = ?, IMG_PATH22 = ?, "
					+ " 	  DESCRITION = ?, GOODS_REMARKS = ?, GOODS_REMARKS2 = ?, GOODS_REMARKS2 = ?, GOODS_REMARKS4 = ?, "
					+ " 	  PROP1_CD = ?, PROP_VAL1 = ?, PROP_VAL2 = ?, PROP_VAL3 = ?, PROP_VAL4 = ?, PROP_VAL5 = ?, PROP_VAL6 = ?, PROP_VAL7 = ?, PROP_VAL8 = ?, PROP_VAL9 = ?, PROP_VAL10 = ?, "
					+ " 	  PROP_VAL11 = ?, PROP_VAL12 = ?, PROP_VAL13 = ?, PROP_VAL14 = ?, PROP_VAL15 = ?, PROP_VAL16 = ?, PROP_VAL17 = ?, PROP_VAL18 = ?, PROP_VAL19 = ?, PROP_VAL20 = ?, PROP_VAL21 = ?, "
					+ " 	  PROP_VAL22 = ?, PROP_VAL23 = ?, PROP_VAL24 = ?, PROP_VAL25 = ?, PROP_VAL26 = ?, PROP_VAL27 = ?, PROP_VAL28 = ?"
					+ " where COMPNO = ? ";
			if (dbname.equals("shopprodin")) {
				sql += "and SENDSEQ = ? ";
			} else {
				sql += "and PRODSEQ = ? ";
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int rowIdx = 0;

			pstmt.setString(++rowIdx, prod.getGoods_nm());
			pstmt.setString(++rowIdx, prod.getGoods_keyword());
			pstmt.setString(++rowIdx, prod.getGoods_nm_en());// 영문상품명
			pstmt.setString(++rowIdx, prod.getGoods_nm_pr());// 출력상품명
			pstmt.setString(++rowIdx, prod.getModel_nm());
			pstmt.setString(++rowIdx, prod.getModel_no());
			pstmt.setString(++rowIdx, prod.getBrand_nm());
			pstmt.setString(++rowIdx, prod.getCompayny_goods_cd());
			pstmt.setString(++rowIdx, prod.getGoods_search());
			pstmt.setString(++rowIdx, prod.getCertno());
			pstmt.setString(++rowIdx, prod.getAvlst_dm());
			pstmt.setString(++rowIdx, prod.getAvled_dm());
			pstmt.setString(++rowIdx, prod.getIssuedate());
			pstmt.setString(++rowIdx, prod.getCertdate());
			pstmt.setString(++rowIdx, prod.getCert_agency());
			pstmt.setString(++rowIdx, prod.getCertfield());
			pstmt.setString(++rowIdx, prod.getImg_path23());
			pstmt.setString(++rowIdx, prod.getGoods_gubun());
			pstmt.setString(++rowIdx, prod.getPartner_id());
			pstmt.setString(++rowIdx, prod.getDpartner_id());
			pstmt.setString(++rowIdx, prod.getClass_cd1());
			pstmt.setString(++rowIdx, prod.getClass_cd2());
			pstmt.setString(++rowIdx, prod.getClass_cd3());
			pstmt.setString(++rowIdx, prod.getClass_cd4());
			pstmt.setString(++rowIdx, prod.getMaker());
			pstmt.setString(++rowIdx, prod.getOrigin());

//			pstmt.setString(++rowIdx, prod.getOrigin2());//텍스트박스원산지

			pstmt.setString(++rowIdx, prod.getImportno());
			pstmt.setString(++rowIdx, prod.getImg_path24());
			pstmt.setString(++rowIdx, prod.getMaterial());
			pstmt.setString(++rowIdx, prod.getMake_year());
			pstmt.setString(++rowIdx, prod.getMake_dm());
			pstmt.setString(++rowIdx, prod.getExpire_dm());
			pstmt.setString(++rowIdx, prod.getSupply_save_yn());
			pstmt.setString(++rowIdx, prod.getGoods_season());
			pstmt.setString(++rowIdx, prod.getSex());
			pstmt.setString(++rowIdx, prod.getStatus());
			pstmt.setString(++rowIdx, prod.getDeliv_able_region());
			pstmt.setString(++rowIdx, prod.getDelv_type());
			pstmt.setString(++rowIdx, prod.getDelv_cost());
			pstmt.setString(++rowIdx, prod.getTax_yn());
			pstmt.setString(++rowIdx, prod.getBanpum_area());
			pstmt.setString(++rowIdx, prod.getGoods_cost());
			pstmt.setString(++rowIdx, prod.getGoods_cost2());// 원가2
			pstmt.setString(++rowIdx, prod.getGoods_price());
			pstmt.setString(++rowIdx, prod.getGoods_consumer_price());

//			pstmt.setString(++rowIdx, prod.getOptstats());//옵션등록상태

			pstmt.setString(++rowIdx, prod.getStock_use_yn());
			pstmt.setString(++rowIdx, prod.getOpt_type());

//			pstmt.setString(++rowIdx, prod.getOptsetting());//옵션세팅방식			
//			pstmt.setString(++rowIdx, prod.getCahr_1_nm());
//			pstmt.setString(++rowIdx, prod.getChar_1_val());
//			pstmt.setString(++rowIdx, prod.getChar_2_nm());
//			pstmt.setString(++rowIdx, prod.getChar_2_val());

			pstmt.setString(++rowIdx, prod.getImg_path());
			pstmt.setString(++rowIdx, prod.getImg_path1());
			pstmt.setString(++rowIdx, prod.getImg_path2());
			pstmt.setString(++rowIdx, prod.getImg_path3());
			pstmt.setString(++rowIdx, prod.getImg_path4());
			pstmt.setString(++rowIdx, prod.getImg_path5());
			pstmt.setString(++rowIdx, prod.getImg_path6());
			pstmt.setString(++rowIdx, prod.getImg_path7());
			pstmt.setString(++rowIdx, prod.getImg_path8());
			pstmt.setString(++rowIdx, prod.getImg_path9());
			pstmt.setString(++rowIdx, prod.getImg_path10());
			pstmt.setString(++rowIdx, prod.getImg_path11());
			pstmt.setString(++rowIdx, prod.getImg_path12());
			pstmt.setString(++rowIdx, prod.getImg_path13());
			pstmt.setString(++rowIdx, prod.getImg_path14());
			pstmt.setString(++rowIdx, prod.getImg_path15());
			pstmt.setString(++rowIdx, prod.getImg_path16());
			pstmt.setString(++rowIdx, prod.getImg_path17());
			pstmt.setString(++rowIdx, prod.getImg_path18());
			pstmt.setString(++rowIdx, prod.getImg_path19());
			pstmt.setString(++rowIdx, prod.getImg_path20());
			pstmt.setString(++rowIdx, prod.getImg_path21());
			pstmt.setString(++rowIdx, prod.getImg_path22());
			pstmt.setString(++rowIdx, prod.getDescrition());
			pstmt.setString(++rowIdx, prod.getGoods_remarks());
			pstmt.setString(++rowIdx, prod.getGoods_remarks2());
			pstmt.setString(++rowIdx, prod.getGoods_remarks3());
			pstmt.setString(++rowIdx, prod.getGoods_remarks4());
			pstmt.setString(++rowIdx, prod.getProp1_cd());
			pstmt.setString(++rowIdx, prod.getProp_val1());
			pstmt.setString(++rowIdx, prod.getProp_val2());
			pstmt.setString(++rowIdx, prod.getProp_val3());
			pstmt.setString(++rowIdx, prod.getProp_val4());
			pstmt.setString(++rowIdx, prod.getProp_val5());
			pstmt.setString(++rowIdx, prod.getProp_val6());
			pstmt.setString(++rowIdx, prod.getProp_val7());
			pstmt.setString(++rowIdx, prod.getProp_val8());
			pstmt.setString(++rowIdx, prod.getProp_val9());
			pstmt.setString(++rowIdx, prod.getProp_val10());
			pstmt.setString(++rowIdx, prod.getProp_val11());
			pstmt.setString(++rowIdx, prod.getProp_val12());
			pstmt.setString(++rowIdx, prod.getProp_val13());
			pstmt.setString(++rowIdx, prod.getProp_val14());
			pstmt.setString(++rowIdx, prod.getProp_val15());
			pstmt.setString(++rowIdx, prod.getProp_val16());
			pstmt.setString(++rowIdx, prod.getProp_val17());
			pstmt.setString(++rowIdx, prod.getProp_val18());
			pstmt.setString(++rowIdx, prod.getProp_val19());
			pstmt.setString(++rowIdx, prod.getProp_val20());
			pstmt.setString(++rowIdx, prod.getProp_val21());
			pstmt.setString(++rowIdx, prod.getProp_val22());
			pstmt.setString(++rowIdx, prod.getProp_val23());
			pstmt.setString(++rowIdx, prod.getProp_val24());
			pstmt.setString(++rowIdx, prod.getProp_val25());
			pstmt.setString(++rowIdx, prod.getProp_val26());
			pstmt.setString(++rowIdx, prod.getProp_val27());
			pstmt.setString(++rowIdx, prod.getProp_val28());

//			pstmt.setString(++rowIdx, goods_nm);
//			pstmt.setString(++rowIdx, goods_keyword);			
//			pstmt.setString(++rowIdx, goods_nm_en);//영문상품명
//			pstmt.setString(++rowIdx, goods_nm_pr);//출력상품명			
//			pstmt.setString(++rowIdx, model_nm);
//			pstmt.setString(++rowIdx, medel_no);
//			pstmt.setString(++rowIdx, brand_nm);
//			pstmt.setString(++rowIdx, compayny_goods_cd); 
//			pstmt.setString(++rowIdx, goods_search);
//			pstmt.setString(++rowIdx, certno);
//			pstmt.setString(++rowIdx, avlst_dm);
//			pstmt.setString(++rowIdx, avled_dm);
//			pstmt.setString(++rowIdx, issuedate);
//			pstmt.setString(++rowIdx, certdate);
//			pstmt.setString(++rowIdx, cert_agency);
//			pstmt.setString(++rowIdx, certfield);
//			pstmt.setString(++rowIdx, img_path23);
//			pstmt.setString(++rowIdx, goods_gubun);
//			pstmt.setString(++rowIdx, partner_id);
//			pstmt.setString(++rowIdx, dpartner_id);
//			pstmt.setString(++rowIdx, class_cd1);
//			pstmt.setString(++rowIdx, class_cd2);
//			pstmt.setString(++rowIdx, class_cd3);
//			pstmt.setString(++rowIdx, class_cd4);
//			pstmt.setString(++rowIdx, maker);
//			pstmt.setString(++rowIdx, origin);
//			
////			pstmt.setString(++rowIdx, origin2);//텍스트박스원산지
//			
//			pstmt.setString(++rowIdx, importno);
//			pstmt.setString(++rowIdx, img_path24);
//			pstmt.setString(++rowIdx, material);
//			pstmt.setString(++rowIdx, make_year);
//			pstmt.setString(++rowIdx, make_dm);
//			pstmt.setString(++rowIdx, expire_dm);
//			pstmt.setString(++rowIdx, supply_save_yn);
//			pstmt.setString(++rowIdx, goods_season);
//			pstmt.setString(++rowIdx, sex);
//			pstmt.setString(++rowIdx, status);
//			pstmt.setString(++rowIdx, deliv_able_region);
//			pstmt.setString(++rowIdx, delv_type);
//			pstmt.setString(++rowIdx, delv_cost);
//			pstmt.setString(++rowIdx, tax_yn);
//			pstmt.setString(++rowIdx, banpum_area);
//			pstmt.setString(++rowIdx, goods_cost);			
//			pstmt.setString(++rowIdx, goods_cost2);//원가2			
//			pstmt.setString(++rowIdx, goods_price);
//			pstmt.setString(++rowIdx, goods_consumer_price);
//			
////			pstmt.setString(++rowIdx, optstats);//옵션등록상태
//			
//			pstmt.setString(++rowIdx, stock_use_yn);
//			pstmt.setString(++rowIdx, opt_type);
//			
////			pstmt.setString(++rowIdx, optsetting);//옵션세팅방식			
////			pstmt.setString(++rowIdx, cahr_1_nm);
////			pstmt.setString(++rowIdx, char_1_val);
////			pstmt.setString(++rowIdx, char_2_nm);
////			pstmt.setString(++rowIdx, char_2_val);
//			
//			pstmt.setString(++rowIdx, img_path);
//			pstmt.setString(++rowIdx, img_path1);
//			pstmt.setString(++rowIdx, img_path2);
//			pstmt.setString(++rowIdx, img_path3);
//			pstmt.setString(++rowIdx, img_path4);
//			pstmt.setString(++rowIdx, img_path5);
//			pstmt.setString(++rowIdx, img_path6);
//			pstmt.setString(++rowIdx, img_path7);
//			pstmt.setString(++rowIdx, img_path8);
//			pstmt.setString(++rowIdx, img_path9);
//			pstmt.setString(++rowIdx, img_path10);
//			pstmt.setString(++rowIdx, img_path11);
//			pstmt.setString(++rowIdx, img_path12);
//			pstmt.setString(++rowIdx, img_path13);
//			pstmt.setString(++rowIdx, img_path14);
//			pstmt.setString(++rowIdx, img_path15);
//			pstmt.setString(++rowIdx, img_path16);
//			pstmt.setString(++rowIdx, img_path17);
//			pstmt.setString(++rowIdx, img_path18);
//			pstmt.setString(++rowIdx, img_path19);
//			pstmt.setString(++rowIdx, img_path20);
//			pstmt.setString(++rowIdx, img_path21);
//			pstmt.setString(++rowIdx, img_path22);
//			pstmt.setString(++rowIdx, descrition);
//			pstmt.setString(++rowIdx, goods_remarks);
//			pstmt.setString(++rowIdx, goods_remarks2);
//			pstmt.setString(++rowIdx, goods_remarks3);
//			pstmt.setString(++rowIdx, goods_remarks4);
//			pstmt.setString(++rowIdx, prop1cd);
//			pstmt.setString(++rowIdx, attr_val1);
//			pstmt.setString(++rowIdx, attr_val2);
//			pstmt.setString(++rowIdx, attr_val3);
//			pstmt.setString(++rowIdx, attr_val4);
//			pstmt.setString(++rowIdx, attr_val5);
//			pstmt.setString(++rowIdx, attr_val6);
//			pstmt.setString(++rowIdx, attr_val7);
//			pstmt.setString(++rowIdx, attr_val8);
//			pstmt.setString(++rowIdx, attr_val9);
//			pstmt.setString(++rowIdx, attr_val10);
//			pstmt.setString(++rowIdx, attr_val11);
//			pstmt.setString(++rowIdx, attr_val12);
//			pstmt.setString(++rowIdx, attr_val13);
//			pstmt.setString(++rowIdx, attr_val14);
//			pstmt.setString(++rowIdx, attr_val15);
//			pstmt.setString(++rowIdx, attr_val16);
//			pstmt.setString(++rowIdx, attr_val17);
//			pstmt.setString(++rowIdx, attr_val18);
//			pstmt.setString(++rowIdx, attr_val19);
//			pstmt.setString(++rowIdx, attr_val20);
//			pstmt.setString(++rowIdx, attr_val21);
//			pstmt.setString(++rowIdx, attr_val22);
//			pstmt.setString(++rowIdx, attr_val23);
//			pstmt.setString(++rowIdx, attr_val24);
//			pstmt.setString(++rowIdx, attr_val25);
//			pstmt.setString(++rowIdx, attr_val26);
//			pstmt.setString(++rowIdx, attr_val27);
//			pstmt.setString(++rowIdx, attr_val28);

			pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(++rowIdx, seq);
			result = pstmt.executeUpdate();

			System.out.println("[ShopAddrDtlNaverStoreUpdate]" + pstmt.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;

	}

	public void setProductErrDelete(ShopProductSendDto sendDto) throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		List<ShopProductDto> prodList = sendDto.getShopProductDto();
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = "delete from shopproderr " + " where sendseq = ? " + "   and compno = ? ";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (ShopProductDto dto : prodList) {
				int idx = 0;
				pstmt.setString(++idx, dto.getSendseq());
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			System.out.println("[setProductInInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	public void setProductErrUpdate(ShopProductSendDto sendDto) throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		List<ShopProductDto> prodList = sendDto.getShopProductDto();
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = " update shopproderr "
					+ "	   set shopseq = ?, prodseq = ?, seq = ?, shopcatno = ?, descrition = ?, failcontent = ?, modifydt = ?, modifyid = ? "
					+ "  where sendseq = ? " + "    and compno = ? " + "    and shopcd = ?";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (ShopProductDto dto : prodList) {
				int idx = 0;
				pstmt.setString(++idx, dto.getShopseq());
				pstmt.setString(++idx, dto.getProdseq());
				pstmt.setString(++idx, dto.getSeq());
				pstmt.setString(++idx, dto.getShopcatno());
				pstmt.setString(++idx, "");
				pstmt.setString(++idx, dto.getResult_text());
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());

				pstmt.setString(++idx, dto.getSendseq());
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++idx, dto.getShopcd());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			System.out.println("[setProductInInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	/*
	 * 상품 1건씩등록등록..
	 */
	public void setProductInOneInsert(ShopProductDto dto, ShopProductAdditionDto dtllist, ShoppingMallDetailDto idlist,
			DomesinMarginDto margindto) throws Exception {

		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			// ShopProdIn process
			int result = setShopProductInOneInsert(dto, dtllist, idlist, margindto);

			// ShopOptProdIn process
			if (result > 0) {
				String sql = "SELECT 1 FROM Shop_OptProdInfo where prodseq = ? ";
				sql = sql.toUpperCase();
				pstmt = connection.prepareStatement(sql);

				int count = 0;
				pstmt.setString(1, dto.getProdseq());
				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt(1);
				}
				if (count > 0) {
					setShopOptProductInInsert(dto, idlist.getShopcd(), idlist.getShopseq());
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	/*
	 * 1건씩 에러저장
	 */
	public void setProductErrOneInsert(ShopProductDto dto, ShopProductAdditionDto dtllist, ShoppingMallDetailDto idlist,
			DomesinMarginDto margindto) throws Exception {

		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = "INSERT INTO shopproderr\r\n"
					+ " ( COMPNO, SHOPCD, SHOPSEQ, PRODSEQ, SEQ, SHOPCATNO, MARGINSEQ, DESCRITION, INSERTDT, INSERTID, FAILCONTENT )\r\n"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
//					"COMPNO,\r\n" + 
//					"? ,  -- SHOPCD\r\n" + 
//					"? , -- ID 순번\r\n" + 
//					"PRODSEQ,\r\n" + 
////					"'' ,  -- 쇼핑몰 완료 번호\r\n" + 
//					"?,   -- 부가정보코드 ..   SHOPDEL SEQ\r\n" + 
//					"?,   -- 카테고리정보코드 ..   SHOPCATINF SHOPCATNO\r\n" + 
//					"GOODS_NM, \r\n" + 
//					"GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, \r\n" + 
//					"COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, CLASS_CD2, CLASS_CD3, CLASS_CD4, PARTNER_ID, DPARTNER_ID, MAKER, \r\n" + 
//					"ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, `STATUS`, DELIV_ABLE_REGION, TAX_YN, DELV_TYPE, DELV_COST, BANPUM_AREA, GOODS_COST,\r\n" + 
//					"GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM, CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3,\r\n" + 
//					"IMG_PATH4, IMG_PATH5, IMG_PATH6, IMG_PATH7, IMG_PATH8, IMG_PATH9, IMG_PATH10, IMG_PATH11, IMG_PATH12, IMG_PATH13, IMG_PATH14,\r\n" + 
//					"IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22, IMG_PATH23, IMG_PATH24, \r\n" + 
//					"GOODS_REMARKS, CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, \r\n" + 
//					"PROP1_CD, PROP_VAL1, PROP_VAL2, PROP_VAL3, PROP_VAL4, PROP_VAL5, PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10,\r\n" + 
//					"PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18, PROP_VAL19, PROP_VAL20,\r\n" + 
//					"PROP_VAL21, PROP_VAL22, PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN, \r\n" + 
//					"GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, GOODS_COST2, ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN,\r\n" + 
//					"DESCRITION,  DATE_FORMAT(NOW(), '%Y-%m-%d %h:%m:%s'), ?  ,?   -- 사용자 아이디.. \r\n" +  // 실패사유 
//					"FROM SHOPPRODINFO \r\n" + 
//					"WHERE COMPNO = ? AND PRODSEQ = ? AND COMPAYNY_GOODS_CD = ?";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, idlist.getShopcd());
			pstmt.setString(3, idlist.getShopseq());// shopdtl코드
			pstmt.setString(4, dto.getProdseq());
			pstmt.setString(5, dtllist.getSeq());// 부가정보
			pstmt.setString(6, dto.getShopCatInDto().getShopcatno());
			pstmt.setString(7, margindto.getMarginseq());
			pstmt.setString(8, "");// 관리자메모
			pstmt.setString(9, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(10, YDMASessonUtil.getUserInfo().getUserId());
			pstmt.setString(11, dto.getResult_text());
			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화

			System.out.println("[setProductErrInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

	/*
	 * 1건씩처리
	 */
	public int setShopProductInOneInsert(ShopProductDto dto, ShopProductAdditionDto dtllist,
			ShoppingMallDetailDto idlist, DomesinMarginDto margindto) throws Exception {

		int result = 0;
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();

		ResultSet rs = null;
		try {

			connection = DBCPInit.getInstance().getConnection();

			// prodin에 인설트하기
			String sql = "INSERT INTO shopprodin\r\n" + "(\r\n"
					+ "	COMPNO, SHOPCD, SHOPSEQ, PRODSEQ, SHOPPRODNO, SEQ, SHOPCATNO,MARGINSEQ, GOODS_NM, GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, \r\n"
					+ "	COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, CLASS_CD2, CLASS_CD3, CLASS_CD4, PARTNER_ID, DPARTNER_ID, MAKER,\r\n"
					+ "	ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, `STATUS`, DELIV_ABLE_REGION, TAX_YN, DELV_TYPE, DELV_COST, BANPUM_AREA, GOODS_COST, \r\n"
					+ "	GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM, CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3, \r\n"
					+ "	IMG_PATH4, IMG_PATH5, IMG_PATH6, IMG_PATH7, IMG_PATH8, IMG_PATH9, IMG_PATH10, IMG_PATH11, IMG_PATH12, IMG_PATH13, IMG_PATH14,\r\n"
					+ "	IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22, IMG_PATH23, IMG_PATH24,\r\n"
					+ "	GOODS_REMARKS, CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, \r\n"
					+ "	PROP1_CD, PROP_VAL1, PROP_VAL2, PROP_VAL3, PROP_VAL4, PROP_VAL5, PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10, \r\n"
					+ "	PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18, PROP_VAL19, PROP_VAL20,\r\n"
					+ "	PROP_VAL21, PROP_VAL22, PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN,\r\n"
					+ "	GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, GOODS_COST2, ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN, \r\n"
					+ "	DESCRITION, INSERTDT, INSERTID\r\n" + ")\r\n" + "SELECT \r\n" + "COMPNO,\r\n"
					+ "? ,  -- SHOPCD\r\n" + "? , -- ID 순번\r\n" + "PRODSEQ,\r\n" + "? ,  -- 쇼핑몰 완료 번호\r\n"
					+ "?,   -- 부가정보코드 ..   SHOPDEL SEQ\r\n" + "?,   -- 카테고리정보코드 ..   SHOPCATINF SHOPCATNO\r\n"
					+ "?,   -- 마진코드정보 ..   MARGINMNGR MARGINSEQ\r\n" + "GOODS_NM, \r\n"
					+ "GOODS_KEYWORD, MODEL_NM, MODEL_NO, BRAND_NM, \r\n"
					+ "COMPAYNY_GOODS_CD, GOODS_SEARCH, GOODS_GUBUN, CLASS_CD1, CLASS_CD2, CLASS_CD3, CLASS_CD4, PARTNER_ID, DPARTNER_ID, MAKER, \r\n"
					+ "ORIGIN, MAKE_YEAR, MAKE_DM, GOODS_SEASON, SEX, `STATUS`, DELIV_ABLE_REGION, TAX_YN, DELV_TYPE, DELV_COST, BANPUM_AREA, GOODS_COST,\r\n"
					+ "GOODS_PRICE, GOODS_CONSUMER_PRICE, CHAR_1_NM, CHAR_1_VAL, CHAR_2_NM, CHAR_2_VAL, IMG_PATH, IMG_PATH1, IMG_PATH2, IMG_PATH3,\r\n"
					+ "IMG_PATH4, IMG_PATH5, IMG_PATH6, IMG_PATH7, IMG_PATH8, IMG_PATH9, IMG_PATH10, IMG_PATH11, IMG_PATH12, IMG_PATH13, IMG_PATH14,\r\n"
					+ "IMG_PATH15, IMG_PATH16, IMG_PATH17, IMG_PATH18, IMG_PATH19, IMG_PATH20, IMG_PATH21, IMG_PATH22, IMG_PATH23, IMG_PATH24, \r\n"
					+ "GOODS_REMARKS, CERTNO, AVLST_DM, AVLED_DM, ISSUEDATE, CERTDATE, CERT_AGENCY, CERTFIELD, MATERIAL, STOCK_USE_YN, OPT_TYPE, PROP_EDIT_YN, \r\n"
					+ "PROP1_CD, PROP_VAL1, PROP_VAL2, PROP_VAL3, PROP_VAL4, PROP_VAL5, PROP_VAL6, PROP_VAL7, PROP_VAL8, PROP_VAL9, PROP_VAL10,\r\n"
					+ "PROP_VAL11, PROP_VAL12, PROP_VAL13, PROP_VAL14, PROP_VAL15, PROP_VAL16, PROP_VAL17, PROP_VAL18, PROP_VAL19, PROP_VAL20,\r\n"
					+ "PROP_VAL21, PROP_VAL22, PROP_VAL23, PROP_VAL24, PROP_VAL25, PROP_VAL26, PROP_VAL27, PROP_VAL28, PACK_CODE_STR, GOODS_NM_EN, \r\n"
					+ "GOODS_NM_PR, GOODS_REMARKS2, GOODS_REMARKS3, GOODS_REMARKS4, IMPORTNO, GOODS_COST2, ORIGIN2, EXPIRE_DM, SUPPLY_SAVE_YN,\r\n"
					+ "DESCRITION, NOW(), ?   -- 사용자 아이디.. \r\n" + "FROM SHOPPRODINFO \r\n"
					+ "WHERE COMPNO = ? AND PRODSEQ = ? AND COMPAYNY_GOODS_CD = ?";
			sql = sql.toUpperCase();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			pstmt.setString(1, idlist.getShopcd());
			pstmt.setString(2, idlist.getShopseq());
			pstmt.setString(3, dto.getShopprodno());
			pstmt.setString(4, dtllist.getSeq());
			pstmt.setString(5, dto.getShopCatInDto().getShopcatno());
			pstmt.setString(6, margindto.getMarginseq());
			pstmt.setString(7, YDMASessonUtil.getUserInfo().getUserId());

			pstmt.setString(8, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(9, dto.getProdseq());
			pstmt.setString(10, dto.getCompayny_goods_cd());

			result++;
			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화

			System.out.println("[setShopProductInOneInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

		return result;
	}

	public ShopProductDto getProductInfoCompaynyCode(String icode) throws Exception {
		String compno = YDMASessonUtil.getCompnoInfo().getCompno();
		ShopProductDto dto = new ShopProductDto();

		List<ShopProductDto> list = new ArrayList<ShopProductDto>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append(" WITH CateAll AS(                                                                 \r\n");
			sql.append("		SELECT                                                                    \r\n");
			sql.append("		   ifnull(a.CODE,'')     AS CLASS_CD1 ,                                   \r\n");
			sql.append("			ifnull(a.CATEGORY,'') AS CLASS_NM1 ,                                  \r\n");
			sql.append("			IFNULL(RIGHT(b.CODE,3),'')     AS CLASS_CD2  ,                        \r\n");
			sql.append("			ifnull(b.CATEGORY,'') AS CLASS_NM2 ,                                  \r\n");
			sql.append("			ifnull(RIGHT(c.CODE,3),'')     AS CLASS_CD3,                          \r\n");
			sql.append("			ifnull(C.CATEGORY,'') AS CLASS_NM3,                                   \r\n");
			sql.append("			ifnull(RIGHT(D.CODE,3),'')     AS CLASS_CD4 ,                         \r\n");
			sql.append("			ifnull(D.CATEGORY,'') AS CLASS_NM4                                    \r\n");
			sql.append("		FROM CATEGLARGE a left JOIN CATEGMIDIUM b                                      \r\n");
			sql.append("			ON a.COMPNO=b.COMPNO AND a.CODE = b.LRGCODE left JOIN CATEGSMALL C         \r\n");
			sql.append("			ON a.COMPNO = C.COMPNO AND b.CODE = C.MIDCODE left JOIN CATEGDETAIL D ON   \r\n");
			sql.append(String.format(" a.COMPNO = D.COMPNO AND C.CODE = D.SMLCODE WHERE a.compno = '%s'         \r\n",
					YDMASessonUtil.getCompnoInfo().getCompno()));
			sql.append("		 )                                                                        \r\n");

			sql.append(" SELECT INFO.*  , \r\n");
			sql.append(" IFNULL(CATE.CLASS_NM1, '')  CLASS_NM1, \r\n");
			sql.append(" IFNULL(CATE.CLASS_NM2, '')  CLASS_NM2, \r\n");
			sql.append(" IFNULL(CATE.CLASS_NM3, '')  CLASS_NM3, \r\n");
			sql.append(" IFNULL(CATE.CLASS_NM4, '')  CLASS_NM4, \r\n");

			// ----- 사업상분..
			sql.append("(CASE                                   \r\n");
			sql.append("	     WHEN GOODS_GUBUN = '1'         \r\n");
			sql.append("	     THEN '위탁상품'                \r\n");
			sql.append("	     WHEN GOODS_GUBUN = '2'         \r\n");
			sql.append("	     THEN '제조상품'                \r\n");
			sql.append("	     WHEN GOODS_GUBUN = '3'         \r\n");
			sql.append("	     THEN '사업상품'                \r\n");
			sql.append("	     WHEN GOODS_GUBUN = '4'         \r\n");
			sql.append("	     THEN '직영상품'                \r\n");
			sql.append("	     ELSE ''                        \r\n");
			sql.append("	  END )AS GOODS_GUBUN_NM,            \r\n");

			// ------- 상태값..
			sql.append("(CASE                            \r\n");
			sql.append("	     WHEN STATUS = '1'       \r\n");
			sql.append("	     THEN '대기중'           \r\n");
			sql.append("	     WHEN STATUS = '2'       \r\n");
			sql.append("	     THEN '공급중'           \r\n");
			sql.append("	     WHEN STATUS = '3'       \r\n");
			sql.append("	     THEN '일시중지'         \r\n");
			sql.append("	     WHEN STATUS = '4'       \r\n");
			sql.append("	     THEN '완전품절'         \r\n");
			sql.append("	      WHEN STATUS = '5'      \r\n");
			sql.append("	     THEN '미사용'           \r\n");
			sql.append("	      WHEN STATUS = '6'      \r\n");
			sql.append("	     THEN '삭제'             \r\n");
			sql.append("	     ELSE ''                 \r\n");
			sql.append("	  END )AS STATUS_NM,         \r\n");

			// ----- 택배 상태값..
			sql.append("(CASE                             \r\n");
			sql.append("	     WHEN DELV_TYPE = '1'     \r\n");
			sql.append("	     THEN '무료'              \r\n");
			sql.append("	     WHEN DELV_TYPE = '2'     \r\n");
			sql.append("	     THEN '착불'              \r\n");
			sql.append("	     WHEN DELV_TYPE = '3'     \r\n");
			sql.append("	     THEN '선결제'            \r\n");
			sql.append("	     WHEN DELV_TYPE = '4'     \r\n");
			sql.append("	     THEN '착불/선결제'       \r\n");
			sql.append("	     ELSE ''                  \r\n");
			sql.append("	  END )AS DELV_TYPE_NM       \r\n");

//			sql.append("IFNULL(CATEINF.COMPNO,'' ) AS COMPNO,  \r\n "); // int,
//			sql.append("IFNULL(CATEINF.SHOPCD,'' ) AS SHOPCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPCATNO,'' ) AS SHOPCATNO,  \r\n "); // int,
//			sql.append("IFNULL(CATEINF.SHOPCATNM,'' ) AS SHOPCATNM,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPCATSITENM,'' ) AS SHOPCATSITENM,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPLAGCATCD,'' ) AS SHOPLAGCATCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPMIDCATCD,'' ) AS SHOPMIDCATCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPSMLCATCD,'' ) AS SHOPSMLCATCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SHOPDETCATCD,'' ) AS SHOPDETCATCD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.SERVICEPROD,'' ) AS SERVICEPROD,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.USE_YN,'' ) AS USE_YN,  \r\n "); // char,
//			sql.append("IFNULL(CATEINF.SHOPGENERAL,'' ) AS SHOPGENERAL,  \r\n "); // varchar,01:일반 02:해외배송
//			sql.append("IFNULL(CATEINF.SHOPID,'' ) AS SHOPID,  \r\n "); // varchar,쇼핑몰아이디
//			sql.append("IFNULL(CATEINF.SHOPCOMMIS,'' ) AS SHOPCOMMIS,  \r\n "); // float,쇼핑몰기본수수료율
//			sql.append("IFNULL(CATEINF.INSERTDT,'' ) AS INSERTDT,  \r\n "); // varchar,
//			sql.append("IFNULL(CATEINF.MODIFYDT,'' ) AS MODIFYDT  \r\n "); // varchar,

			sql.append(" FROM                             \r\n");
			sql.append(" (SELECT                           \r\n");
			sql.append(
					" CONCAT(IFNULL(CLASS_CD1,''), IFNULL(CLASS_CD2,''), IFNULL(CLASS_CD3,''), IFNULL(CLASS_CD4,'')) AS CODE, \r\n");
			sql.append("IFNULL(PRODSEQ,'' ) AS PRODSEQ,  \r\n "); // int,품번코드
			sql.append("IFNULL(COMPNO,'' ) AS COMPNO,  \r\n "); // int,
			sql.append(String.format("YWM_FUNC_BOSSPRODCD(%s,COMPAYNY_GOODS_CD) as img,",
					YDMASessonUtil.getCompnoInfo().getCompno()));
			sql.append("IFNULL(GOODS_NM,'' ) AS GOODS_NM,  \r\n "); // varchar,상품명
			sql.append("IFNULL(GOODS_KEYWORD,'' ) AS GOODS_KEYWORD,  \r\n "); // varchar,상품약어
			sql.append("IFNULL(MODEL_NM,'' ) AS MODEL_NM,  \r\n "); // varchar,모델명
			sql.append("IFNULL(MODEL_NO,'' ) AS MODEL_NO,  \r\n "); // varchar,모델No
			sql.append("IFNULL(BRAND_NM,'' ) AS BRAND_NM,  \r\n "); // varchar,브랜드명
			sql.append("IFNULL(COMPAYNY_GOODS_CD,'' ) AS COMPAYNY_GOODS_CD,  \r\n "); // varchar,자체상품코드
			sql.append("IFNULL(GOODS_SEARCH,'' ) AS GOODS_SEARCH,  \r\n "); // varchar,사이트검색어
			sql.append("IFNULL(GOODS_GUBUN,'' ) AS GOODS_GUBUN,  \r\n "); // varchar,상품구분
			sql.append("IFNULL(CLASS_CD1,'' ) AS CLASS_CD1,  \r\n "); // varchar,대분류코드
			sql.append("IFNULL(CLASS_CD2,'' ) AS CLASS_CD2,  \r\n "); // varchar,중분류코드
			sql.append("IFNULL(CLASS_CD3,'' ) AS CLASS_CD3,  \r\n "); // varchar,소분류코드
			sql.append("IFNULL(CLASS_CD4,'' ) AS CLASS_CD4,  \r\n "); // varchar,세분류코드
			sql.append("IFNULL(PARTNER_ID,'' ) AS PARTNER_ID,  \r\n "); // varchar,매입처ID
			sql.append("IFNULL(DPARTNER_ID,'' ) AS DPARTNER_ID,  \r\n "); // varchar,물류처ID
			sql.append("IFNULL(MAKER,'' ) AS MAKER,  \r\n "); // varchar,제조사
			sql.append("IFNULL(ORIGIN,'' ) AS ORIGIN,  \r\n "); // varchar,원산지(제조국)
			sql.append("IFNULL(MAKE_YEAR,'' ) AS MAKE_YEAR,  \r\n "); // varchar,생산연도
			sql.append("IFNULL(MAKE_DM,'' ) AS MAKE_DM,  \r\n "); // varchar,제조일자
			sql.append("IFNULL(GOODS_SEASON,'' ) AS GOODS_SEASON,  \r\n "); // varchar,시즌
			sql.append("IFNULL(SEX,'' ) AS SEX,  \r\n "); // varchar,남녀구분
			sql.append("IFNULL(STATUS,'' ) AS STATUS,  \r\n "); // varchar,상품상태
			sql.append("IFNULL(DELIV_ABLE_REGION,'' ) AS DELIV_ABLE_REGION,  \r\n "); // varchar,판매지역
			sql.append("IFNULL(TAX_YN,'' ) AS TAX_YN,  \r\n "); // varchar,세금구분
			sql.append("IFNULL(DELV_TYPE,'' ) AS DELV_TYPE,  \r\n "); // varchar,배송비구분
			sql.append("IFNULL(DELV_COST,'' ) AS DELV_COST,  \r\n "); // int,배송비
			sql.append("IFNULL(BANPUM_AREA,'' ) AS BANPUM_AREA,  \r\n "); // varchar,반품지구분
			sql.append("IFNULL(GOODS_COST,'' ) AS GOODS_COST,  \r\n "); // int,자체 상품 원가
			sql.append("IFNULL(GOODS_PRICE,'' ) AS GOODS_PRICE,  \r\n "); // int,판매가
			sql.append("IFNULL(GOODS_CONSUMER_PRICE,'' ) AS GOODS_CONSUMER_PRICE,  \r\n "); // int,TAG가(소비자가)
			sql.append("IFNULL(CHAR_1_NM,'' ) AS CHAR_1_NM,  \r\n "); // varchar,옵션제목(1)
			sql.append("IFNULL(CHAR_1_VAL,'' ) AS CHAR_1_VAL,  \r\n "); // varchar,옵션상세명칭(1)
			sql.append("IFNULL(CHAR_2_NM,'' ) AS CHAR_2_NM,  \r\n "); // varchar,옵션제목(2)
			sql.append("IFNULL(CHAR_2_VAL,'' ) AS CHAR_2_VAL,  \r\n "); // varchar,옵션상세명칭(2)
			sql.append("IFNULL(IMG_PATH,'' ) AS IMG_PATH,  \r\n "); // varchar,대표이미지
			sql.append("IFNULL(IMG_PATH1,'' ) AS IMG_PATH1,  \r\n "); // varchar,종합몰(JPG)이미지
			sql.append("IFNULL(IMG_PATH2,'' ) AS IMG_PATH2,  \r\n "); // varchar,부가이미지2
			sql.append("IFNULL(IMG_PATH3,'' ) AS IMG_PATH3,  \r\n "); // varchar,부가이미지3
			sql.append("IFNULL(IMG_PATH4,'' ) AS IMG_PATH4,  \r\n "); // varchar,부가이미지4
			sql.append("IFNULL(IMG_PATH5,'' ) AS IMG_PATH5,  \r\n "); // varchar,부가이미지5
			sql.append("IFNULL(IMG_PATH6,'' ) AS IMG_PATH6,  \r\n "); // varchar,부가이미지6
			sql.append("IFNULL(IMG_PATH7,'' ) AS IMG_PATH7,  \r\n "); // varchar,부가이미지7
			sql.append("IFNULL(IMG_PATH8,'' ) AS IMG_PATH8,  \r\n "); // varchar,부가이미지8
			sql.append("IFNULL(IMG_PATH9,'' ) AS IMG_PATH9,  \r\n "); // varchar,부가이미지9
			sql.append("IFNULL(IMG_PATH10,'' ) AS IMG_PATH10,  \r\n "); // varchar,부가이미지10
			sql.append("IFNULL(IMG_PATH11,'' ) AS IMG_PATH11,  \r\n "); // varchar,부가이미지11
			sql.append("IFNULL(IMG_PATH12,'' ) AS IMG_PATH12,  \r\n "); // varchar,부가이미지12
			sql.append("IFNULL(IMG_PATH13,'' ) AS IMG_PATH13,  \r\n "); // varchar,부가이미지13
			sql.append("IFNULL(IMG_PATH14,'' ) AS IMG_PATH14,  \r\n "); // varchar,부가이미지14
			sql.append("IFNULL(IMG_PATH15,'' ) AS IMG_PATH15,  \r\n "); // varchar,부가이미지15
			sql.append("IFNULL(IMG_PATH16,'' ) AS IMG_PATH16,  \r\n "); // varchar,부가이미지16
			sql.append("IFNULL(IMG_PATH17,'' ) AS IMG_PATH17,  \r\n "); // varchar,부가이미지17
			sql.append("IFNULL(IMG_PATH18,'' ) AS IMG_PATH18,  \r\n "); // varchar,부가이미지18
			sql.append("IFNULL(IMG_PATH19,'' ) AS IMG_PATH19,  \r\n "); // varchar,부가이미지19
			sql.append("IFNULL(IMG_PATH20,'' ) AS IMG_PATH20,  \r\n "); // varchar,부가이미지20
			sql.append("IFNULL(IMG_PATH21,'' ) AS IMG_PATH21,  \r\n "); // varchar,부가이미지21
			sql.append("IFNULL(IMG_PATH22,'' ) AS IMG_PATH22,  \r\n "); // varchar,부가이미지22
			sql.append("IFNULL(IMG_PATH23,'' ) AS IMG_PATH23,  \r\n "); // varchar,인증서이미지
			sql.append("IFNULL(IMG_PATH24,'' ) AS IMG_PATH24,  \r\n "); // varchar,수입면장이미지
			sql.append("IFNULL(GOODS_REMARKS,'' ) AS GOODS_REMARKS,  \r\n "); // varchar,상품상세설명
			sql.append("IFNULL(CERTNO,'' ) AS CERTNO,  \r\n "); // varchar,인증번호
			sql.append("IFNULL(AVLST_DM,'' ) AS AVLST_DM,  \r\n "); // varchar,인증유효 시작일
			sql.append("IFNULL(AVLED_DM,'' ) AS AVLED_DM,  \r\n "); // varchar,인증유효 마지막일
			sql.append("IFNULL(ISSUEDATE,'' ) AS ISSUEDATE,  \r\n "); // varchar,발급일자
			sql.append("IFNULL(CERTDATE,'' ) AS CERTDATE,  \r\n "); // varchar,인증일자
			sql.append("IFNULL(CERT_AGENCY,'' ) AS CERT_AGENCY,  \r\n "); // varchar,인증기관
			sql.append("IFNULL(CERTFIELD,'' ) AS CERTFIELD,  \r\n "); // varchar,인증분야
			sql.append("IFNULL(MATERIAL,'' ) AS MATERIAL,  \r\n "); // varchar,식품재료/원산지
			sql.append("IFNULL(STOCK_USE_YN,'' ) AS STOCK_USE_YN,  \r\n "); // varchar,재고관리사용여부
			sql.append("IFNULL(OPT_TYPE,'' ) AS OPT_TYPE,  \r\n "); // varchar,옵션수정여부
			sql.append("IFNULL(PROP_EDIT_YN,'' ) AS PROP_EDIT_YN,  \r\n "); // varchar,속성수정여부
			sql.append("IFNULL(PROP1_CD,'' ) AS PROP1_CD,  \r\n "); // varchar,속성분류코드
			sql.append("IFNULL(PROP_VAL1,'' ) AS PROP_VAL1,  \r\n "); // varchar,속성값1
			sql.append("IFNULL(PROP_VAL2,'' ) AS PROP_VAL2,  \r\n "); // varchar,속성값2
			sql.append("IFNULL(PROP_VAL3,'' ) AS PROP_VAL3,  \r\n "); // varchar,속성값3
			sql.append("IFNULL(PROP_VAL4,'' ) AS PROP_VAL4,  \r\n "); // varchar,속성값4
			sql.append("IFNULL(PROP_VAL5,'' ) AS PROP_VAL5,  \r\n "); // varchar,속성값5
			sql.append("IFNULL(PROP_VAL6,'' ) AS PROP_VAL6,  \r\n "); // varchar,속성값6
			sql.append("IFNULL(PROP_VAL7,'' ) AS PROP_VAL7,  \r\n "); // varchar,속성값7
			sql.append("IFNULL(PROP_VAL8,'' ) AS PROP_VAL8,  \r\n "); // varchar,속성값8
			sql.append("IFNULL(PROP_VAL9,'' ) AS PROP_VAL9,  \r\n "); // varchar,속성값9
			sql.append("IFNULL(PROP_VAL10,'' ) AS PROP_VAL10,  \r\n "); // varchar,속성값10
			sql.append("IFNULL(PROP_VAL11,'' ) AS PROP_VAL11,  \r\n "); // varchar,속성값11
			sql.append("IFNULL(PROP_VAL12,'' ) AS PROP_VAL12,  \r\n "); // varchar,속성값12
			sql.append("IFNULL(PROP_VAL13,'' ) AS PROP_VAL13,  \r\n "); // varchar,속성값13
			sql.append("IFNULL(PROP_VAL14,'' ) AS PROP_VAL14,  \r\n "); // varchar,속성값14
			sql.append("IFNULL(PROP_VAL15,'' ) AS PROP_VAL15,  \r\n "); // varchar,속성값15
			sql.append("IFNULL(PROP_VAL16,'' ) AS PROP_VAL16,  \r\n "); // varchar,속성값16
			sql.append("IFNULL(PROP_VAL17,'' ) AS PROP_VAL17,  \r\n "); // varchar,속성값17
			sql.append("IFNULL(PROP_VAL18,'' ) AS PROP_VAL18,  \r\n "); // varchar,속성값18
			sql.append("IFNULL(PROP_VAL19,'' ) AS PROP_VAL19,  \r\n "); // varchar,속성값19
			sql.append("IFNULL(PROP_VAL20,'' ) AS PROP_VAL20,  \r\n "); // varchar,속성값20
			sql.append("IFNULL(PROP_VAL21,'' ) AS PROP_VAL21,  \r\n "); // varchar,속성값21
			sql.append("IFNULL(PROP_VAL22,'' ) AS PROP_VAL22,  \r\n "); // varchar,속성값22
			sql.append("IFNULL(PROP_VAL23,'' ) AS PROP_VAL23,  \r\n "); // varchar,속성값23
			sql.append("IFNULL(PROP_VAL24,'' ) AS PROP_VAL24,  \r\n "); // varchar,속성값24
			sql.append("IFNULL(PROP_VAL25,'' ) AS PROP_VAL25,  \r\n "); // varchar,속성값25
			sql.append("IFNULL(PROP_VAL26,'' ) AS PROP_VAL26,  \r\n "); // varchar,속성값26
			sql.append("IFNULL(PROP_VAL27,'' ) AS PROP_VAL27,  \r\n "); // varchar,속성값27
			sql.append("IFNULL(PROP_VAL28,'' ) AS PROP_VAL28,  \r\n "); // varchar,속성값28
			sql.append("IFNULL(PACK_CODE_STR,'' ) AS PACK_CODE_STR,  \r\n "); // varchar,추가상품그룹코드
			sql.append("IFNULL(GOODS_NM_EN,'' ) AS GOODS_NM_EN,  \r\n "); // varchar,영문 상품명
			sql.append("IFNULL(GOODS_NM_PR,'' ) AS GOODS_NM_PR,  \r\n "); // varchar,출력 상품명
			sql.append("IFNULL(GOODS_REMARKS2,'' ) AS GOODS_REMARKS2,  \r\n "); // varchar,추가 상품상세설명_1
			sql.append("IFNULL(GOODS_REMARKS3,'' ) AS GOODS_REMARKS3,  \r\n "); // varchar,추가 상품상세설명_2
			sql.append("IFNULL(GOODS_REMARKS4,'' ) AS GOODS_REMARKS4,  \r\n "); // varchar,추가 상품상세설명_3
			sql.append("IFNULL(IMPORTNO,'' ) AS IMPORTNO,  \r\n "); // varchar,수입신고번호
			sql.append("IFNULL(GOODS_COST2,'' ) AS GOODS_COST2,  \r\n "); // varchar,원가2
			sql.append("IFNULL(ORIGIN2,'' ) AS ORIGIN2,  \r\n "); // varchar,원산지 상세지역
			sql.append("IFNULL(EXPIRE_DM,'' ) AS EXPIRE_DM,  \r\n "); // varchar,유효일자
			sql.append("IFNULL(SUPPLY_SAVE_YN,'' ) AS SUPPLY_SAVE_YN,  \r\n "); // varchar,합포제외여부
			sql.append("IFNULL(DESCRITION,'' ) AS DESCRITION,  \r\n "); // varchar,관리자메모
			sql.append("IFNULL(SHOPPRODNO,'' ) AS SHOPPRODNO,  \r\n "); // varchar,완료후쇼핑몰코드(등록수정을한폼으로진행하기위해)
			sql.append("IFNULL(INSERTDT,'' ) AS INSERTDT,  \r\n "); // varchar,입력일자
			sql.append("IFNULL(INSERTID,'' ) AS INSERTID,  \r\n "); // varchar,입력자id
			sql.append("IFNULL(MODIFYDT,'' ) AS MODIFYDT,  \r\n "); // varchar,수정일자
			sql.append("IFNULL(MODIFYID,'' ) AS MODIFYID  \r\n "); // varchar,수정자ID

			sql.append(" FROM SHOPPRODINFO \r\n");
			sql.append(" WHERE COMPNO = ?  AND COMPAYNY_GOODS_CD = ? \r\n");

			sql.append(" ) AS INFO  LEFT JOIN CateAll AS CATE  \r\n");
			sql.append("		 ON INFO.CLASS_CD1 = CATE.CLASS_CD1  \r\n");
			sql.append(" 		 AND INFO.CLASS_CD2 = CATE.CLASS_CD2 \r\n");
			sql.append("AND INFO.CLASS_CD3 = CATE.CLASS_CD3   \r\n");
			sql.append("AND INFO.CLASS_CD4 = CATE.CLASS_CD4   \r\n");

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			pstmt.setString(1, compno);
			pstmt.setString(2, icode);

			System.out.println("[getShopProductInfo]" + pstmt.toString());

			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;

				dto.setRowno("" + (++rowno));// 디비에서 조회한 값 세팅하는 거 아님
				dto.setCode(rs.getString("CODE"));
				dto.setProdseq(rs.getString("PRODSEQ")); // 품번코드 타입:int
				dto.setImg(rs.getString("Img")); // 이미지 추가..
				dto.setCompno(rs.getString("COMPNO")); // 타입:int
				dto.setGoods_nm(rs.getString("GOODS_NM")); // 상품명 타입:varchar
				dto.setGoods_keyword(rs.getString("GOODS_KEYWORD")); // 상품약어 타입:varchar
				dto.setModel_nm(rs.getString("MODEL_NM")); // 모델명 타입:varchar
				dto.setModel_no(rs.getString("MODEL_NO")); // 모델No 타입:varchar
				dto.setBrand_nm(rs.getString("BRAND_NM")); // 브랜드명 타입:varchar
				dto.setCompayny_goods_cd(rs.getString("COMPAYNY_GOODS_CD")); // 자체상품코드 타입:varchar
				dto.setGoods_search(rs.getString("GOODS_SEARCH")); // 사이트검색어 타입:varchar
				dto.setGoods_gubun(rs.getString("GOODS_GUBUN")); // 상품구분 타입:varchar
				dto.setClass_cd1(rs.getString("CLASS_CD1")); // 대분류코드 타입:varchar
				dto.setClass_cd2(rs.getString("CLASS_CD2")); // 중분류코드 타입:varchar
				dto.setClass_cd3(rs.getString("CLASS_CD3")); // 소분류코드 타입:varchar
				dto.setClass_cd4(rs.getString("CLASS_CD4")); // 세분류코드 타입:varchar
				dto.setPartner_id(rs.getString("PARTNER_ID")); // 매입처ID 타입:varchar
				dto.setDpartner_id(rs.getString("DPARTNER_ID")); // 물류처ID 타입:varchar
				dto.setMaker(rs.getString("MAKER")); // 제조사 타입:varchar
				dto.setOrigin(rs.getString("ORIGIN")); // 원산지(제조국) 타입:varchar
				dto.setMake_year(rs.getString("MAKE_YEAR")); // 생산연도 타입:varchar
				dto.setMake_dm(rs.getString("MAKE_DM")); // 제조일자 타입:varchar
				dto.setGoods_season(rs.getString("GOODS_SEASON")); // 시즌 타입:varchar
				dto.setSex(rs.getString("SEX")); // 남녀구분 타입:varchar
				dto.setStatus(rs.getString("STATUS")); // 상품상태 타입:varchar
				dto.setDeliv_able_region(rs.getString("DELIV_ABLE_REGION")); // 판매지역 타입:varchar
				dto.setTax_yn(rs.getString("TAX_YN")); // 세금구분 타입:varchar
				dto.setDelv_type(rs.getString("DELV_TYPE")); // 배송비구분 타입:varchar
				dto.setDelv_cost(rs.getString("DELV_COST")); // 배송비 타입:int
				dto.setBanpum_area(rs.getString("BANPUM_AREA")); // 반품지구분 타입:varchar
				dto.setGoods_cost(rs.getString("GOODS_COST")); // 자체 상품 원가 타입:int
				dto.setGoods_price(rs.getString("GOODS_PRICE")); // 판매가 타입:int
				dto.setGoods_consumer_price(rs.getString("GOODS_CONSUMER_PRICE")); // TAG가(소비자가) 타입:int
				dto.setChar_1_nm(rs.getString("CHAR_1_NM")); // 옵션제목(1) 타입:varchar
				dto.setChar_1_val(rs.getString("CHAR_1_VAL")); // 옵션상세명칭(1) 타입:varchar
				dto.setChar_2_nm(rs.getString("CHAR_2_NM")); // 옵션제목(2) 타입:varchar
				dto.setChar_2_val(rs.getString("CHAR_2_VAL")); // 옵션상세명칭(2) 타입:varchar
				dto.setImg_path(rs.getString("IMG_PATH")); // 대표이미지 타입:varchar
				dto.setImg_path1(rs.getString("IMG_PATH1")); // 종합몰(JPG)이미지 타입:varchar
				dto.setImg_path2(rs.getString("IMG_PATH2")); // 부가이미지2 타입:varchar
				dto.setImg_path3(rs.getString("IMG_PATH3")); // 부가이미지3 타입:varchar
				dto.setImg_path4(rs.getString("IMG_PATH4")); // 부가이미지4 타입:varchar
				dto.setImg_path5(rs.getString("IMG_PATH5")); // 부가이미지5 타입:varchar
				dto.setImg_path6(rs.getString("IMG_PATH6")); // 부가이미지6 타입:varchar
				dto.setImg_path7(rs.getString("IMG_PATH7")); // 부가이미지7 타입:varchar
				dto.setImg_path8(rs.getString("IMG_PATH8")); // 부가이미지8 타입:varchar
				dto.setImg_path9(rs.getString("IMG_PATH9")); // 부가이미지9 타입:varchar
				dto.setImg_path10(rs.getString("IMG_PATH10")); // 부가이미지10 타입:varchar
				dto.setImg_path11(rs.getString("IMG_PATH11")); // 부가이미지11 타입:varchar
				dto.setImg_path12(rs.getString("IMG_PATH12")); // 부가이미지12 타입:varchar
				dto.setImg_path13(rs.getString("IMG_PATH13")); // 부가이미지13 타입:varchar
				dto.setImg_path14(rs.getString("IMG_PATH14")); // 부가이미지14 타입:varchar
				dto.setImg_path15(rs.getString("IMG_PATH15")); // 부가이미지15 타입:varchar
				dto.setImg_path16(rs.getString("IMG_PATH16")); // 부가이미지16 타입:varchar
				dto.setImg_path17(rs.getString("IMG_PATH17")); // 부가이미지17 타입:varchar
				dto.setImg_path18(rs.getString("IMG_PATH18")); // 부가이미지18 타입:varchar
				dto.setImg_path19(rs.getString("IMG_PATH19")); // 부가이미지19 타입:varchar
				dto.setImg_path20(rs.getString("IMG_PATH20")); // 부가이미지20 타입:varchar
				dto.setImg_path21(rs.getString("IMG_PATH21")); // 부가이미지21 타입:varchar
				dto.setImg_path22(rs.getString("IMG_PATH22")); // 부가이미지22 타입:varchar
				dto.setImg_path23(rs.getString("IMG_PATH23")); // 인증서이미지 타입:varchar
				dto.setImg_path24(rs.getString("IMG_PATH24")); // 수입면장이미지 타입:varchar
				dto.setGoods_remarks(rs.getString("GOODS_REMARKS")); // 상품상세설명 타입:varchar
				dto.setCertno(rs.getString("CERTNO")); // 인증번호 타입:varchar
				dto.setAvlst_dm(rs.getString("AVLST_DM")); // 인증유효 시작일 타입:varchar
				dto.setAvled_dm(rs.getString("AVLED_DM")); // 인증유효 마지막일 타입:varchar
				dto.setIssuedate(rs.getString("ISSUEDATE")); // 발급일자 타입:varchar
				dto.setCertdate(rs.getString("CERTDATE")); // 인증일자 타입:varchar
				dto.setCert_agency(rs.getString("CERT_AGENCY")); // 인증기관 타입:varchar
				dto.setCertfield(rs.getString("CERTFIELD")); // 인증분야 타입:varchar
				dto.setMaterial(rs.getString("MATERIAL")); // 식품재료/원산지 타입:varchar
				dto.setStock_use_yn(rs.getString("STOCK_USE_YN")); // 재고관리사용여부 타입:varchar
				dto.setOpt_type(rs.getString("OPT_TYPE")); // 옵션수정여부 타입:varchar
				dto.setProp_edit_yn(rs.getString("PROP_EDIT_YN")); // 속성수정여부 타입:varchar
				dto.setProp1_cd(rs.getString("PROP1_CD")); // 속성분류코드 타입:varchar
				dto.setProp_val1(rs.getString("PROP_VAL1")); // 속성값1 타입:varchar
				dto.setProp_val2(rs.getString("PROP_VAL2")); // 속성값2 타입:varchar
				dto.setProp_val3(rs.getString("PROP_VAL3")); // 속성값3 타입:varchar
				dto.setProp_val4(rs.getString("PROP_VAL4")); // 속성값4 타입:varchar
				dto.setProp_val5(rs.getString("PROP_VAL5")); // 속성값5 타입:varchar
				dto.setProp_val6(rs.getString("PROP_VAL6")); // 속성값6 타입:varchar
				dto.setProp_val7(rs.getString("PROP_VAL7")); // 속성값7 타입:varchar
				dto.setProp_val8(rs.getString("PROP_VAL8")); // 속성값8 타입:varchar
				dto.setProp_val9(rs.getString("PROP_VAL9")); // 속성값9 타입:varchar
				dto.setProp_val10(rs.getString("PROP_VAL10")); // 속성값10 타입:varchar
				dto.setProp_val11(rs.getString("PROP_VAL11")); // 속성값11 타입:varchar
				dto.setProp_val12(rs.getString("PROP_VAL12")); // 속성값12 타입:varchar
				dto.setProp_val13(rs.getString("PROP_VAL13")); // 속성값13 타입:varchar
				dto.setProp_val14(rs.getString("PROP_VAL14")); // 속성값14 타입:varchar
				dto.setProp_val15(rs.getString("PROP_VAL15")); // 속성값15 타입:varchar
				dto.setProp_val16(rs.getString("PROP_VAL16")); // 속성값16 타입:varchar
				dto.setProp_val17(rs.getString("PROP_VAL17")); // 속성값17 타입:varchar
				dto.setProp_val18(rs.getString("PROP_VAL18")); // 속성값18 타입:varchar
				dto.setProp_val19(rs.getString("PROP_VAL19")); // 속성값19 타입:varchar
				dto.setProp_val20(rs.getString("PROP_VAL20")); // 속성값20 타입:varchar
				dto.setProp_val21(rs.getString("PROP_VAL21")); // 속성값21 타입:varchar
				dto.setProp_val22(rs.getString("PROP_VAL22")); // 속성값22 타입:varchar
				dto.setProp_val23(rs.getString("PROP_VAL23")); // 속성값23 타입:varchar
				dto.setProp_val24(rs.getString("PROP_VAL24")); // 속성값24 타입:varchar
				dto.setProp_val25(rs.getString("PROP_VAL25")); // 속성값25 타입:varchar
				dto.setProp_val26(rs.getString("PROP_VAL26")); // 속성값26 타입:varchar
				dto.setProp_val27(rs.getString("PROP_VAL27")); // 속성값27 타입:varchar
				dto.setProp_val28(rs.getString("PROP_VAL28")); // 속성값28 타입:varchar
				dto.setPack_code_str(rs.getString("PACK_CODE_STR")); // 추가상품그룹코드 타입:varchar
				dto.setGoods_nm_en(rs.getString("GOODS_NM_EN")); // 영문 상품명 타입:varchar
				dto.setGoods_nm_pr(rs.getString("GOODS_NM_PR")); // 출력 상품명 타입:varchar
				dto.setGoods_remarks2(rs.getString("GOODS_REMARKS2")); // 추가 상품상세설명_1 타입:varchar
				dto.setGoods_remarks3(rs.getString("GOODS_REMARKS3")); // 추가 상품상세설명_2 타입:varchar
				dto.setGoods_remarks4(rs.getString("GOODS_REMARKS4")); // 추가 상품상세설명_3 타입:varchar
				dto.setImportno(rs.getString("IMPORTNO")); // 수입신고번호 타입:varchar
				dto.setGoods_cost2(rs.getString("GOODS_COST2")); // 원가2 타입:varchar
				dto.setOrigin2(rs.getString("ORIGIN2")); // 원산지 상세지역 타입:varchar
				dto.setExpire_dm(rs.getString("EXPIRE_DM")); // 유효일자 타입:varchar
				dto.setSupply_save_yn(rs.getString("SUPPLY_SAVE_YN")); // 합포제외여부 타입:varchar
				dto.setDescrition(rs.getString("DESCRITION")); // 관리자메모 타입:varchar
				dto.setShopprodno(rs.getString("SHOPPRODNO")); // 완료후쇼핑몰코드(등록수정을한폼으로진행하기위해) 타입:varchar
				dto.setInsertdt(rs.getString("INSERTDT")); // 입력일자 타입:varchar
				dto.setInsertid(rs.getString("INSERTID")); // 입력자id 타입:varchar
				dto.setModifydt(rs.getString("MODIFYDT")); // 수정일자 타입:varchar
				dto.setModifyid(rs.getString("MODIFYID")); // 수정자ID 타입:varchar

				// ----- class nm 추가 -----
				dto.setClass_nm1(rs.getString("CLASS_NM1")); // 대분류
				dto.setClass_nm2(rs.getString("CLASS_NM2")); // 중분류
				dto.setClass_nm3(rs.getString("CLASS_NM3")); // 소분류
				dto.setClass_nm4(rs.getString("CLASS_NM4")); // 세분류

				dto.setDelv_type_nm(rs.getString("DELV_TYPE_NM"));
				dto.setGoods_gubun_nm(rs.getString("GOODS_GUBUN_NM"));
				dto.setStatus_nm(rs.getString("STATUS_NM"));

				/********* addtinon ************/
//				ShopCatInfDto ShopCatInfDto = new ShopCatInfDto();
//
//				ShopCatInfDto.setCompno(rs.getString("COMPNO"));
//				ShopCatInfDto.setShopcd(rs.getString("SHOPCD"));
//				ShopCatInfDto.setShopcatno(rs.getString("SHOPCATNO"));
//				ShopCatInfDto.setShopcatnm(rs.getString("SHOPCATNM"));
//				ShopCatInfDto.setShopcatsitenm(rs.getString("SHOPCATSITENM"));
//				ShopCatInfDto.setShoplagcatcd(rs.getString("SHOPLAGCATCD"));
//				ShopCatInfDto.setShopmidcatcd(rs.getString("SHOPMIDCATCD"));
//				ShopCatInfDto.setShopsmlcatcd(rs.getString("SHOPSMLCATCD"));
//				ShopCatInfDto.setShopdetcatcd(rs.getString("SHOPDETCATCD"));
//				ShopCatInfDto.setServiceprod(rs.getString("SERVICEPROD"));
//				ShopCatInfDto.setUse_yn(rs.getString("USE_YN"));
//				ShopCatInfDto.setShopgeneral(rs.getString("SHOPGENERAL"));
//				ShopCatInfDto.setShopid(rs.getString("SHOPID"));
//				ShopCatInfDto.setShopcommis(rs.getString("SHOPCOMMIS"));
//				ShopCatInfDto.setInsertdt(rs.getString("INSERTDT"));
//				ShopCatInfDto.setModifydt(rs.getString("MODIFYDT"));
//
//				dto.setShopCatInDto(ShopCatInfDto);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return dto;

	}

	/*
	 * 상품수정에서 선택된상품 수정하기 버튼
	 */
	public void setShopProdInfoUpdate(List<ShopProductDto> list) throws Exception {
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			// prodin에 인설트하기
			String sql = " update shopprodinfo "
					+ "	   set GOODS_NM = ?, GOODS_SEARCH = ?, CLASS_CD1 = ?, CLASS_CD2 = ?, CLASS_CD3 = ?, CLASS_CD4 = ?, modifydt = ?, modifyid = ? "
					+ "  where PRODSEQ = ? and compno = ? ";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (ShopProductDto dto : list) {
				int idx = 0;
				pstmt.setString(++idx, dto.getGoods_nm());
				pstmt.setString(++idx, dto.getGoods_keyword());
				pstmt.setString(++idx, dto.getClass_cd1());
				pstmt.setString(++idx, dto.getClass_cd2());
				pstmt.setString(++idx, dto.getClass_cd3());
				pstmt.setString(++idx, dto.getClass_cd4());
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++idx, YDMASessonUtil.getUserInfo().getUserId());

				pstmt.setString(++idx, dto.getProdseq());
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화

			}
			System.out.println("[setProductInInsert]" + pstmt.toString());

			pstmt.executeBatch();
			pstmt.clearParameters();// Batch 초기화
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}

	}

}

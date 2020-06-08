package com.kdj.mlink.ezup3.shop.domesin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdj.mlink.ezup3.shop.common.ShopCommon;
import com.kdj.mlink.ezup3.shop.dao.ShopCatInfDto;
import com.kdj.mlink.ezup3.shop.dao.ShopOptionProductInDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;
import com.sun.mail.imap.protocol.Item;

public class ProductGetDao {

	// shop 카테고리 아이템..

	public static ProductGetDao instance = new ProductGetDao();

	private ProductGetDao() {
	}

	public static ProductGetDao get() {
		return instance;
	}

	/*
	 * Shop 카테고리 시디를 맵핑한다.
	 */
	public boolean getIsModefiyShopCate(ProductItem item, List<MapperShopCateDto> shopCidInfoList) {
		
		for (MapperShopCateDto mapperShopCateDto : shopCidInfoList) {
			switch (mapperShopCateDto.getShopcd()) {
			case ShopCommon.쿠팡:
				if(!item.getEs_coupang().equals(mapperShopCateDto.getCid())) {
					return true;
				}
				break;
			case ShopCommon.인터파크:
				if(!item.getEs_interpark().equals(mapperShopCateDto.getCid())) {
					return true;
				}
				break;
			case ShopCommon.지마켓:
				if(!item.getEsm_gmarket().equals(mapperShopCateDto.getEsm_gmarket())) {
					return true;
				}
				
				if(!item.getEs_gmarket().equals(mapperShopCateDto.getCid())) {
					return true;
				}
				break;
			case ShopCommon.옥션:
				if(!item.getEsm_auction().equals(mapperShopCateDto.getEsm_auction())) {
					return true;
				}
				
				if(!item.getEs_auction().equals(mapperShopCateDto.getCid())) {
					return true;
				}

				break;
			case ShopCommon.스토어팜:
				if(!item.getEs_storefarm().equals(mapperShopCateDto.getCid())) {
					return true;
				}
				break;

			case ShopCommon.십일번가:
				if(!item.getEs_11st().equals(mapperShopCateDto.getCid())) {
					return true;
				}
				break;

			}

		}
		
		return false;

	}

	
	public int getModifyProduct(List<ProductItem> productItems, String query) throws Exception {
		List<ProductItem>  items =    getIF_ModifyProduct(productItems, query);
		if(items.size() == 0) return 0;
		DomesinShopDao.get().saveShopProdInfo(items);
		DomesinShopDao.get().saveShopOptProdInfo(items);
		DomesinShopDao.get().saveCategory(items);
		DomesinShopDao.get().updateShopProdStatus(items);
		return items.size();
	}
	
	
	public List<ProductItem> getIF_ModifyProduct(List<ProductItem> productItems, String query) throws Exception {
		String compno = YDMASessonUtil.getCompnoInfo().getCompno();

		List<ProductItem> list = new ArrayList<ProductItem>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(query);
			sql.append("\r\n");

			sql.append("SELECT  \r\n");
			sql.append("PRODSEQ  AS PRODSEQ , \r\n");
			sql.append("COMPNO  AS COMPNO , \r\n");
			sql.append("COMPAYNY_GOODS_CD  AS COMPAYNY_GOODS_CD , \r\n");
			sql.append("GOODS_NM  AS GOODS_NM , \r\n");
			sql.append("CLASS_CD3  AS CLASS_CD3 , \r\n");
			sql.append("SHOP_CID_INFO  AS SHOP_CID_INFO , \r\n");
			sql.append("ORIGIN  AS ORIGIN , \r\n");
			sql.append("MAKER  AS MAKER , \r\n");
			sql.append("BRAND_NM  AS BRAND_NM , \r\n");
			sql.append("TAX_YN  AS TAX_YN , \r\n");
			sql.append("DELV_TYPE  AS DELV_TYPE , \r\n");
			sql.append("DELV_COST  AS DELV_COST , \r\n");
			sql.append("GOODS_SEARCH  AS GOODS_SEARCH , \r\n");
			sql.append("GOODS_PRICE  AS GOODS_PRICE , \r\n");
			sql.append("GOODS_CONSUMER_PRICE  AS GOODS_CONSUMER_PRICE , \r\n");
			sql.append("GOODS_REMARKS  AS GOODS_REMARKS , \r\n");
			sql.append("IMG_PATH  AS IMG_PATH , \r\n");
			sql.append("IMG_PATH6  AS IMG_PATH6 , \r\n");
			sql.append("IMG_PATH7  AS IMG_PATH7 , \r\n");
			sql.append("SELECT_OPTION  AS SELECT_OPTION , \r\n");
			sql.append("STATUS  AS STATUS , \r\n");
			sql.append("PROP1_CD  AS PROP1_CD , \r\n");
			sql.append("PROP_VAL1  AS PROP_VAL1 , \r\n");
			sql.append("PROP_VAL2  AS PROP_VAL2 , \r\n");
			sql.append("PROP_VAL3  AS PROP_VAL3 , \r\n");
			sql.append("PROP_VAL4  AS PROP_VAL4 , \r\n");
			sql.append("PROP_VAL5  AS PROP_VAL5 , \r\n");
			sql.append("PROP_VAL6  AS PROP_VAL6 , \r\n");
			sql.append("PROP_VAL7  AS PROP_VAL7 , \r\n");
			sql.append("PROP_VAL8  AS PROP_VAL8 , \r\n");
			sql.append("PROP_VAL9  AS PROP_VAL9 , \r\n");
			sql.append("PROP_VAL10  AS PROP_VAL10 , \r\n");
			sql.append("PROP_VAL11  AS PROP_VAL11 , \r\n");
			sql.append("PROP_VAL12  AS PROP_VAL12 , \r\n");
			sql.append("PROP_VAL13  AS PROP_VAL13 , \r\n");
			sql.append("PROP_VAL14  AS PROP_VAL14 , \r\n");
			sql.append("PROP_VAL15  AS PROP_VAL15 , \r\n");
			sql.append("PROP_VAL16  AS PROP_VAL16 , \r\n");
			sql.append("PROP_VAL17  AS PROP_VAL17 , \r\n");
			sql.append("PROP_VAL18  AS PROP_VAL18 , \r\n");
			sql.append("PROP_VAL19  AS PROP_VAL19 , \r\n");
			sql.append("PROP_VAL20  AS PROP_VAL20 , \r\n");
			sql.append("PROP_VAL21  AS PROP_VAL21 , \r\n");
			sql.append("PROP_VAL22  AS PROP_VAL22 , \r\n");

			sql.append("ShopSendStatus  AS ShopSendStatus , \r\n");
			sql.append("ShopModifyContent  AS ShopModifyContent , \r\n");
			sql.append("FLAG  AS FLAG  \r\n");
			sql.append("  FROM  shopprodinfo AS A  \r\n");
			sql.append("   INNER JOIN  CTE_PRODLIST AS B  \r\n");
			sql.append("  ON A.COMPAYNY_GOODS_CD = B.ICODE \r\n");
			sql.append("  WHERE COMPNO=4 ");

			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			System.out.println("[getShopProductList]" + pstmt.toString());
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			rs = pstmt.executeQuery();
			int rowno = 0;
			while (rs.next()) {
				int i = 0;
				String company_goods_cd = rs.getString("COMPAYNY_GOODS_CD");

				ProductItem item = productItems.stream().filter(p -> p.getIcode().equals(company_goods_cd)).findAny()
						.orElse(null);
				
				if (item == null)
					continue;

				 List<String> change_list = new ArrayList<>();
				
				 String SHOP_CID_INFO =   rs.getString("SHOP_CID_INFO");
				 List<MapperShopCateDto> shopCidInfoList = mapper.readValue(SHOP_CID_INFO, new TypeReference<List<MapperShopCateDto>>(){});
				 
				boolean isModeShopCate =    getIsModefiyShopCate(item, shopCidInfoList);
				 
				if(isModeShopCate) {
					item.setCategoryModify(true);
				}
				
				if (!item.getIname().equals(rs.getString("GOODS_NM"))) {
					item.setBaseInfoModify(true);
					change_list
							.add(String.format("상품명변경전 :  %s  ->  변경후: %s", rs.getString("GOODS_NM"), item.getIname()));
				}

				if (!item.getCid().equals(rs.getString("CLASS_CD3"))) {
					
					item.setBaseInfoModify(true);
					change_list
							.add(String.format("카테고리변경전 :  %s  ->  변경후: %s", rs.getString("CLASS_CD3"), item.getCid()));
				}

				if (!item.getIcountry().equals(rs.getString("ORIGIN"))) { // 원산지 변경..
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("원산지변경전 :  %s  ->  변경후: %s", rs.getString("ORIGIN"), item.getIcountry()));
				}

				if (!item.getMaker().equals(rs.getString("MAKER"))) {
					item.setBaseInfoModify(true);
					change_list
							.add(String.format("메이커 변경전 :  %s  ->  변경후: %s", rs.getString("MAKER"), item.getMaker()));
				}

				if (!item.getBrand().equals(rs.getString("BRAND_NM"))) { // 브랜드..
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("브랜드 변경전 :  %s  ->  변경후: %s", rs.getString("BRAND_NM"), item.getBrand()));
				}

				// 과세여부..
				String tax = rs.getString("TAX_YN").equals("1") ? "0" : "1";

				if (!item.getTax().equals(tax)) { // 과세여부..
					item.setBaseInfoModify(true);
					change_list.add(String.format("과세여부 변경전 :  %s  ->  변경후: %s", tax, item.getTax()));
				}

				if (!item.getDelivery_type().equals(rs.getString("DELV_TYPE"))) { // 배송타입..
					item.setBaseInfoModify(true);
					change_list.add(String.format("배송타입 변경전 :  %s  ->  변경후: %s", rs.getString("DELV_TYPE"),
							item.getDelivery_type()));
				}

				if (!item.getDelivery_amount().equals(rs.getString("DELV_COST"))) { // 배송금액.. ..
					item.setBaseInfoModify(true);
					change_list.add(String.format("배송금액 변경전 :  %s  ->  변경후: %s", rs.getString("DELV_COST"),
							item.getDelivery_amount()));
				}

				if (!item.getKeyword().equals(rs.getString("GOODS_SEARCH"))) { // 키워드
					item.setBaseInfoModify(true);
					change_list.add(String.format("키워드 변경전 :  %s  ->  변경후: %s", rs.getString("GOODS_SEARCH"),
							item.getKeyword()));
				}

				if (!item.getPrice().equals(rs.getString("GOODS_PRICE"))) { // 금액
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("금액 변경전 :  %s  ->  변경후: %s", rs.getString("GOODS_PRICE"), item.getPrice()));
				}

				if (!item.getPrice_consumer().equals(rs.getString("GOODS_CONSUMER_PRICE"))) { // 소비자가.. 변경여부..
					item.setBaseInfoModify(true);
					change_list.add(String.format("소비자가 변경전 :  %s  ->  변경후: %s", rs.getString("GOODS_CONSUMER_PRICE"),
							item.getPrice_consumer()));
				}

				if (!item.getContent().equals(rs.getString("GOODS_REMARKS"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("상세설명 변경전 :  %s  ->  변경후: %s", rs.getString("GOODS_REMARKS"),
							item.getContent()));
				}

				String[] img = item.getImg();
				int length =   img.length;
				
				
				if (length >= 1    && !rs.getString("IMG_PATH").equals(item.getImg()[0]))// 이미지
				{
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("대표이미지 변경전 :  %s  ->  변경후: %s", rs.getString("IMG_PATH"), item.getImg()[0]));
				}

				if (length >= 2    && !rs.getString("IMG_PATH6").equals(item.getImg()[1]))// 이미지
				{
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("이미지1 변경전 :  %s  ->  변경후: %s", rs.getString("IMG_PATH6"), item.getImg()[1]));
				}

				if (length >= 3    && !rs.getString("IMG_PATH7").equals(item.getImg()[2]))// 이미지
				{
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("이미지2 변경전 :  %s  ->  변경후: %s", rs.getString("IMG_PATH7"), item.getImg()[2]));
				}

				if (!item.getSelect_option().equals(rs.getString("SELECT_OPTION"))) { // 상세설명..
					item.setOptionModify(true);
					
					change_list.add(String.format("옵션 변경전 :  %s  ->  변경후: %s", rs.getString("SELECT_OPTION"),
							item.getSelect_option()));
				}

				if (!item.getStatus().equals(rs.getString("STATUS"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list
							.add(String.format("상태값 변경전 :  %s  ->  변경후: %s", rs.getString("STATUS"), item.getStatus()));
				}

				
				
				if (item.getGosi1()!=null &&  !item.getGosi1().equals(rs.getString("PROP_VAL1"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("고정정보 1 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL1"), item.getGosi1()));
				}
				
				if (item.getGosi2()!=null && !item.getGosi2().equals(rs.getString("PROP_VAL2"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("고정정보 2 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL2"), item.getGosi2()));
				}
				if (item.getGosi3()!=null && !item.getGosi3().equals(rs.getString("PROP_VAL3"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("고정정보 3 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL3"), item.getGosi3()));
				}
				if (item.getGosi4()!=null && !item.getGosi4().equals(rs.getString("PROP_VAL4"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("고정정보 4 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL4"), item.getGosi4()));
				}
				if (item.getGosi5()!=null && !item.getGosi5().equals(rs.getString("PROP_VAL5"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("고정정보 5 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL5"), item.getGosi5()));
				}
				if (item.getGosi6()!=null && !item.getGosi6().equals(rs.getString("PROP_VAL6"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("고정정보 6 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL6"), item.getGosi6()));
				}
				if (item.getGosi7()!=null && !item.getGosi7().equals(rs.getString("PROP_VAL7"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("고정정보 7 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL7"), item.getGosi7()));
				}
				if (item.getGosi8()!=null && !item.getGosi8().equals(rs.getString("PROP_VAL8"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("고정정보 8 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL8"), item.getGosi8()));
				}
				if (item.getGosi9()!=null && !item.getGosi9().equals(rs.getString("PROP_VAL9"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(
							String.format("고정정보 9 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL9"), item.getGosi9()));
				}
				if (item.getGosi10()!=null && !item.getGosi10().equals(rs.getString("PROP_VAL10"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 10 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL10"),
							item.getGosi10()));
				}
				if (item.getGosi11()!=null && !item.getGosi11().equals(rs.getString("PROP_VAL11"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 11 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL11"),
							item.getGosi11()));
				}
				if (item.getGosi12()!=null && !item.getGosi12().equals(rs.getString("PROP_VAL12"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 12 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL12"),
							item.getGosi12()));
				}
				if (item.getGosi13()!=null && !item.getGosi13().equals(rs.getString("PROP_VAL13"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 13 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL13"),
							item.getGosi13()));
				}

				if (item.getGosi14()!=null && !item.getGosi14().equals(rs.getString("PROP_VAL14"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 14 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL14"),
							item.getGosi14()));
				}
				if (item.getGosi15()!=null && !item.getGosi15().equals(rs.getString("PROP_VAL15"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 15 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL15"),
							item.getGosi15()));
				}
				if (item.getGosi16()!=null && !item.getGosi16().equals(rs.getString("PROP_VAL16"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 16 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL16"),
							item.getGosi16()));
				}
				if (item.getGosi17()!=null && !item.getGosi17().equals(rs.getString("PROP_VAL17"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 17 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL17"),
							item.getGosi17()));
				}
				if (item.getGosi18()!=null && !item.getGosi18().equals(rs.getString("PROP_VAL18"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 18 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL18"),
							item.getGosi18()));
				}
				if (item.getGosi19()!=null && !item.getGosi19().equals(rs.getString("PROP_VAL19"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 19 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL19"),
							item.getGosi19()));
				}
				if (item.getGosi20()!=null && !item.getGosi20().equals(rs.getString("PROP_VAL20"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 20 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL20"),
							item.getGosi20()));
				}
				if (item.getGosi21()!=null && !item.getGosi21().equals(rs.getString("PROP_VAL21"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 21 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL21"),
							item.getGosi21()));
				}
				if (item.getGosi22()!=null && !item.getGosi22().equals(rs.getString("PROP_VAL22"))) { // 상세설명..
					item.setBaseInfoModify(true);
					change_list.add(String.format("고정정보 22 변경전 :  %s  ->  변경후: %s", rs.getString("PROP_VAL22"),
							item.getGosi22()));
				}

				if (change_list.size() == 0)
					continue; // 변경된 사항이 없음...
				item.setModifyMode(true);
				item.setChange_list(change_list);
				list.add(item);
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

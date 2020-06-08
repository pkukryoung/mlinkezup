package com.kdj.mlink.ezup3.shop.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.swt.graphics.Color;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDtoList;

public class CategoryStatus {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = YDMASessonUtil.getAppPath() + "\\YDwmsData\\chromedriver.exe";

	public static final String ESM_Level1 = "6";
	public static final String ESM_Level2 = "7";
	public static final String ESM_Level3 = "8";
	public static final String ESM_Level4 = "9";
	public static final String ESM_Level5 = "10";

	public static final String Level1 = "1";
	public static final String Level2 = "2";
	public static final String Level3 = "3";
	public static final String Level4 = "4";
	public static final String Level5 = "5";

	// 결과값..
	public static final String 데이타없음 = "0";
	public static final String 에러발생 = "-1";
	public static final String 정상처리 = "1";
	String result = "";

	private CategoryStatus() {
	}

	private static CategoryStatus instance = new CategoryStatus();
	private List<OrderCodeMapperDto> items = new ArrayList<>();

	public static CategoryStatus getInstance() {
		if (instance.items.size() == 0) {
			instance.bind();
		}
		return instance;
	}

	/*
	 * 상태값 확인..
	 */
	public static String validate(String status, String changeStatus) {
		String ret = "0";
		switch (changeStatus) {

		case CategoryStatus.Level1:
			if (!status.equals(CategoryStatus.Level1))
				ret = "(신규주문) 상태일때만 주문확인 가능합니다. ";
			break;

		case CategoryStatus.Level2: // 현제 상태가 보류 일 경우에만 보류해제를 한다.
			if (status.equals(CategoryStatus.Level2)) {
				ret = "0";
			} else {
				ret = "현제 상태가 보류가 아닙니다. 상태값을 확인해주세요";
			}
			break;
		case CategoryStatus.Level3:
			if (!status.equals(CategoryStatus.Level3))
				ret = "(신규주문) 상태일때만 주문확인 가능합니다. ";
			break;
		case CategoryStatus.Level4:
			if (!status.equals(CategoryStatus.Level4))
				ret = "(신규주문) 상태일때만 주문확인 가능합니다. ";
			break;
		case CategoryStatus.Level5:
			if (!status.equals(CategoryStatus.Level5))
				ret = "(신규주문) 상태일때만 주문확인 가능합니다. ";
			break;
		}
		return ret;
	}

	/*
	 * 해당 코드를 가져온다.
	 */
	public String findCode(String code) {
		String result = "";
		for (OrderCodeMapperDto dto : items) {
			if (dto.getCode().equals(code))
				return dto.getName();
		}
		return result;
	}

	public Color findColor(String code) {
		Color bgColor = null;

//		switch (code) {
//		case CategoryStatus.신규주문:
//			bgColor = SWTResourceManager.getColor(255, 255, 0);
//			break;
//		case CategoryStatus.주문확인:
//			bgColor = SWTResourceManager.getColor(255, 248, 191);
//			break;
//		case CategoryStatus.보류:
//			bgColor = SWTResourceManager.getColor(255, 251, 234);
//			break;
//		case CategoryStatus.송장출력:
//			bgColor = SWTResourceManager.getColor(171, 242, 2);
//			break;
//		case CategoryStatus.송장입력:
//			bgColor = SWTResourceManager.getColor(135, 255, 143);
//			break;
//		case CategoryStatus.출고:
//			bgColor = SWTResourceManager.getColor(236, 255, 201);
//			break;
//		case CategoryStatus.배송중:
//			bgColor = SWTResourceManager.getColor(42, 152, 255);
//			break;
//		case CategoryStatus.수취확인:
//			bgColor = SWTResourceManager.getColor(201, 229, 255);
//			break;
//		case CategoryStatus.정산완료:
//			bgColor = SWTResourceManager.getColor(246, 246, 246);
//			break;
//		case CategoryStatus.취소요청:
//			bgColor = SWTResourceManager.getColor(255, 0, 0);
//			break;
//		case CategoryStatus.취소마감:
//			bgColor = SWTResourceManager.getColor(255, 176, 176);
//			break;
//		case CategoryStatus.교환요청:
//			bgColor = SWTResourceManager.getColor(254, 68, 163);
//			break;
//		case CategoryStatus.교환마감:
//			bgColor = SWTResourceManager.getColor(255, 202, 229);
//			break;
//		case CategoryStatus.반품요청:
//			bgColor = SWTResourceManager.getColor(144, 0, 255);
//			break;
//		case CategoryStatus.반품마감:
//			bgColor = SWTResourceManager.getColor(231, 200, 255);
//			break;
//		default:
//			bgColor = SWTResourceManager.getColor(255, 255, 0);
//			break;
//
//		}
		return bgColor;
	}

	public CodeItem findCode(String shopcd, String mall_code) {
		CodeItem item = new CodeItem();
		OrderCodeMapperDto dto = items.stream()
				.filter(p -> p.getShopid().equals(shopcd) && p.mall_code.equals(mall_code)).collect(Collectors.toList())
				.get(0);

		item.setCode(dto.code);
		item.setName(dto.getName());
		return item;
	}

	public String findCodeString(String shopcd, String mall_code) {
		String result = "";
		for (OrderCodeMapperDto dto : items) {
			if (dto.getShopid().equals(shopcd) && dto.getMall_code().trim().equals(mall_code)) {
				result = dto.getCode();
				break;
			}
		}

		if (result == "")
			result = mall_code;

		return result;
	}

	public void setStatusShopMst(String code, ShopOrderMstDtoList list) {
		for (ShopOrderMstDto dto : list.getList()) {
			dto.setOrder_status(code);
		}
	}

	/*
	 * 마스터에 맵핑한다.
	 */
	public void setMappingStatusShopMst(String shopID, ShopOrderMstDtoList list) {
		for (ShopOrderMstDto dto : list.getList()) {
			String code = this.findCodeString(shopID, dto.getOrder_status());
			dto.setOrder_status(code);
		}
	}

	public CategoryStatus bind() {
		items.clear();
		setCodeMapper();
		return this;
	}

	public void setCodeMapper() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT \r\n");
			sql.append("IFNULL(SHOPID,  '' ) AS SHOPID,  \r\n");// SHOP ID
			sql.append("IFNULL(CODE,  '' ) AS CODE,  \r\n");// 상태코드
			sql.append("IFNULL(MALL_CODE,  '' ) AS MALL_CODE,  \r\n");// 몰 상태코드
			sql.append("IFNULL(NAME,  '' ) AS NAME  \r\n");// 상태.
			sql.append(" FROM shopordstatus  \r\n");
			pstmt = connection.prepareStatement(sql.toString().toUpperCase());
			System.out.println("[ShopOrderStatus]" + pstmt.toString());
			rs = pstmt.executeQuery();

			int rowNum = 0;

			while (rs.next()) {
				OrderCodeMapperDto dto = new OrderCodeMapperDto();
				dto.setShopid(rs.getString("SHOPID"));
				dto.setCode(rs.getString("CODE"));
				dto.setMall_code(rs.getString("MALL_CODE"));
				dto.setName(rs.getString("NAME"));

				items.add(dto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	class OrderCodeMapperDto {
		private String shopid;
		private String code;
		private String name;
		private String mall_code;

		public String getShopid() {
			return shopid;
		}

		public void setShopid(String shopid) {
			this.shopid = shopid;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMall_code() {
			return mall_code;
		}

		public void setMall_code(String mall_code) {
			this.mall_code = mall_code;
		}

		public CategoryStatus getInstance() {
			return instance;
		}
	}

}

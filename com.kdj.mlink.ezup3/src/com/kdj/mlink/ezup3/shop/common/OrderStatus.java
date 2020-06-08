package com.kdj.mlink.ezup3.shop.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.swt.graphics.Color;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDtoList;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;

public class OrderStatus {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = YDMASessonUtil.getAppPath() + "\\YDwmsData\\chromedriver.exe";
	public static final String 신규주문 = "100";
	public static final String 주문확인 = "201";
	public static final String 보류 = "202";
	public static final String 보류해제 = "203";  // 실제 값 쓰이지 않지만 명령행 
	public static final String 송장출력 = "301";
	public static final String 송장입력 = "401";
	public static final String 출고 = "402";
	public static final String 배송중 = "403";
	public static final String 수취확인 = "410";
	public static final String 정산완료 = "900";
	public static final String 구매확정 = "901";
	public static final String 취소처리 = "C00";
	public static final String 취소요청 = "C01";
	public static final String 취소마감 = "C02";
	public static final String 교환처리 = "E00";
	public static final String 교환요청 = "E01";
	public static final String 교환마감 = "E02";
	public static final String 반품처리 = "R00";
	public static final String 반품요청 = "R01";
	public static final String 반품마감 = "R02";
	
	
	
	public static final String 도신신규 = "500";
	public static final String 도신준비 = "501";
	public static final String 도신반품 = "503";  
	public static final String 도신교환 = "504";
	public static final String 도신취소 = "505";

	public static final String 조회 = "1000";
	public static final String 저장 = "2000";
	public static final String 업데이트 = "3000";

	
	//   결과값.. 
	public static final String 데이타없음 = "0";
	public static final String 에러발생 = "-1";
	public static final String 정상처리 = "1";
	String result = "";

	private OrderStatus() {
	}

	private static OrderStatus instance = new OrderStatus();
	private List<OrderCodeMapperDto> items = new ArrayList<OrderCodeMapperDto>();

	public static OrderStatus getInstance() {
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

		case OrderStatus.주문확인:
		case OrderStatus.도신신규:
			if (!status.equals(OrderStatus.신규주문))
				ret = "(신규주문) 상태일때만 주문확인 가능합니다. ";
			break;
			
		case OrderStatus.보류해제: // 현제 상태가 보류 일 경우에만 보류해제를 한다. 
			if(status.equals(OrderStatus.보류)) { 
				ret = "0";
			}else {
				ret = "현제 상태가 보류가 아닙니다. 상태값을 확인해주세요";
			}
			break;
		case OrderStatus.보류:
			switch (status) { // 현재 상태..
			case OrderStatus.신규주문:
			case OrderStatus.주문확인:
			case OrderStatus.송장입력:
			case OrderStatus.송장출력:
			case OrderStatus.출고:
				ret = "0";
				break;
			default:
				ret = "(신규주문, 주문확인, 송장입력, 송장출력, 출고) 상태가 아닌값은 보류를 할수 없습니다.";
				break;
			}
			break;
		case OrderStatus.송장출력:
		case OrderStatus.송장입력:
		case OrderStatus.출고:
			switch (status) { // 현재 상태..
			case OrderStatus.송장출력:
			case OrderStatus.송장입력:
			case OrderStatus.출고:
			case OrderStatus.주문확인:
				ret = "0";
				break;
			default:
				ret = "( 송장입력, 송장출력, 출고) 상태는  (주문확인 ,송장입력, 송장출력, 출고) 현상태에서 이동이 가능합니다. ";
				break;
			}
			break;
			
//		case OrderStatus.송장출력:
//			switch (status) { // 현재 상태..
//			case OrderStatus.송장입력:
//				ret = "0";
//				break;
//			default:
//				ret = "(송장입력) 상태가 아닌값은 송장출력을 할수 없습니다.";
//				break;
//			}
//			break;
//		case OrderStatus.송장입력:
//			if(status.equals(OrderStatus.주문확인)) { 
//				ret = "0";
//			}else {
//				ret = "(주문확인) 상태가 아닌값은 송장입력을 할수 없습니다.";
//			}
//			break;
//		case OrderStatus.출고:
//			if(status.equals(OrderStatus.송장출력)) { 
//				ret = "0";
//			}else {
//				ret =  "(송장출력) 상태가 아닌값은 출고를 할수 없습니다.";
//			}
//			break;
		case OrderStatus.배송중:
			if (!status.equals(OrderStatus.출고))
				ret = "(출고) 상태가 아닌값은 발송을 할수 없습니다.";
			break;

		case OrderStatus.취소마감:
			if (!status.equals(OrderStatus.취소요청))
				ret = "(취소요청) 상태가 아닌값은 취소마감을 할수 없습니다.";
			break;

		case OrderStatus.교환마감:
			if (!status.equals(OrderStatus.교환요청))
				ret = "(교환요청) 상태가 아닌값은 교환마감을 할수 없습니다.";
			break;
		case OrderStatus.반품요청:

			break;
		case OrderStatus.반품마감:
			if (!status.equals(OrderStatus.반품요청))
				ret = "(반품요청) 상태가 아닌값은 반품마감을 할수 없습니다.";
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

		switch (code) {
		case OrderStatus.신규주문:
			bgColor = SWTResourceManager.getColor(255, 255, 0);
			break;
		case OrderStatus.도신신규:
			bgColor = SWTResourceManager.getColor(255, 255, 0);
			break;
		case OrderStatus.주문확인:
			bgColor = SWTResourceManager.getColor(255, 248, 191);
			break;
		case OrderStatus.도신준비:
			bgColor = SWTResourceManager.getColor(255, 248, 191);
			break;
		case OrderStatus.보류:
			bgColor = SWTResourceManager.getColor(255, 251, 234);
			break;
		case OrderStatus.송장출력:
			bgColor = SWTResourceManager.getColor(171, 242, 2);
			break;
		case OrderStatus.송장입력:
			bgColor = SWTResourceManager.getColor(135, 255, 143);
			break;
		case OrderStatus.출고:
			bgColor = SWTResourceManager.getColor(236, 255, 201);
			break;
		case OrderStatus.배송중:
			bgColor = SWTResourceManager.getColor(42, 152, 255);
			break;
		case OrderStatus.수취확인:
			bgColor = SWTResourceManager.getColor(201, 229, 255);
			break;
		case OrderStatus.정산완료:
			bgColor = SWTResourceManager.getColor(246, 246, 246);
			break;
		case OrderStatus.취소처리:
			bgColor = SWTResourceManager.getColor(255, 0, 0);
			break;
		case OrderStatus.취소요청:
			bgColor = SWTResourceManager.getColor(255, 0, 0);
			break;
		case OrderStatus.취소마감:
			bgColor = SWTResourceManager.getColor(255, 176, 176);
			break;
		case OrderStatus.도신취소:
			bgColor = SWTResourceManager.getColor(255, 176, 176);
			break;
		case OrderStatus.교환요청:
			bgColor = SWTResourceManager.getColor(254, 68, 163);
			break;
		case OrderStatus.교환마감:
			bgColor = SWTResourceManager.getColor(255, 202, 229);
			break;
		case OrderStatus.도신교환:
			bgColor = SWTResourceManager.getColor(255, 202, 229);
			break;
		case OrderStatus.반품요청:
			bgColor = SWTResourceManager.getColor(144, 0, 255);
			break;
		case OrderStatus.반품마감:
			bgColor = SWTResourceManager.getColor(231, 200, 255);
			break;
		case OrderStatus.도신반품:
			bgColor = SWTResourceManager.getColor(231, 200, 255);
			break;
		default:
			bgColor = SWTResourceManager.getColor(255, 255, 0);
			break;

		}
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
		
		if(result =="") result= mall_code;
		
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

	public OrderStatus bind() {
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

		public OrderStatus getInstance() {
			return instance;
		}
	}

}

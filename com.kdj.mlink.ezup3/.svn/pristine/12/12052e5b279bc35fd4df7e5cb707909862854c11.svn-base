package com.kdj.mlink.ezup3.shop.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.wb.swt.SWTResourceManager;

import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDtoList;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;

public class WorkStatus {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = YDMASessonUtil.getAppPath() + "\\YDwmsData\\chromedriver.exe";
	//작업관리를 위한 코드
	public static final String 대기 = "W01";
	public static final String 취소 = "W02";
	public static final String 진행중 = "W03";
	public static final String 오류 = "W04";
	public static final String 실패 = "W05";
	public static final String 완료 = "W06";
	public static final String 전체 = "W07";

	//작업코드분류
	public static final String 상품등록 = "100";
	public static final String 상품수정 = "200";
	public static final String 주문수집 = "300";
	public static final String 문의수집 = "400";
	
	public static final String 조회 = "1000";
	public static final String 저장 = "2000";
	public static final String 업데이트 = "3000";

	//   결과값.. 
	public static final String 데이타없음 = "0";
	public static final String 에러발생 = "-1";
	public static final String 정상처리 = "1";
	String result = "";

	private WorkStatus() {
	}

	private static WorkStatus instance = new WorkStatus();
	private List<OrderCodeMapperDto> items = new ArrayList<OrderCodeMapperDto>();

	public static WorkStatus getInstance() {
		if (instance.items.size() == 0) {
			instance.bind();
		}
		return instance;
	}
	public Color workfindColor(String code) {
		Color bgColor = null;
		switch (code) {
		case WorkStatus.대기:
			bgColor = SWTResourceManager.getColor(255,251,234);
			break;
		case WorkStatus.취소:
			bgColor = SWTResourceManager.getColor(255,248,191);
			break;
		case WorkStatus.진행중:
			bgColor = SWTResourceManager.getColor(255,255,0);
			break;
		case WorkStatus.오류:
			bgColor = SWTResourceManager.getColor(255,177,177);
			break;
		case WorkStatus.실패:
			bgColor = SWTResourceManager.getColor(255,4,4);
			break;
		case WorkStatus.완료:
			bgColor = SWTResourceManager.getColor(246,246,246);
			break;
		case WorkStatus.전체:
			bgColor = SWTResourceManager.getColor(255,255,255);
			break;
		default:
			bgColor = SWTResourceManager.getColor(255, 255, 0);
			break;

		}
		return bgColor;
	}
	/*
	 * 상태값 확인..
	 */
//	public static String validate(String status, String changeStatus) {
//		String ret = "0";
//		switch (changeStatus) {
//		case WorkStatus.보류해제: // 현제 상태가 보류 일 경우에만 보류해제를 한다. 
//			if(status.equals(WorkStatus.보류)) { 
//				ret = "0";
//			}else {
//				ret = "현제 상태가 보류가 아닙니다. 상태값을 확인해주세요";
//			}
//			break;
//		case WorkStatus.보류:
//			switch (status) { // 현재 상태..
//			case WorkStatus.신규:
//			case WorkStatus.확인완료:
//			case WorkStatus.답변완료:
//				ret = "0";
//				break;
//			default:
//				ret = "(신규, 확인완료, 답변완료) 상태가 아닌값은 보류를 할수 없습니다.";
//				break;
//			}
//			break;
//		}
//		return ret;
//	}

	/*
	 * 해당 코드를 가져온다.
	 */
	public String findCode(String code) {
		String result = "";
		switch (code) {
		case WorkStatus.대기:
			result = "대기";
			break;
		case WorkStatus.취소:
			result = "취소";
			break;
		case WorkStatus.진행중:
			result = "진행중";
			break;
		case WorkStatus.오류:
			result = "오류";
			break;
		case WorkStatus.실패:
			result = "실패";
			break;
		case WorkStatus.완료:
			result = "완료";
			break;
		case WorkStatus.상품등록:
			result = "상품등록";
			break;
		case WorkStatus.주문수집:
			result = "주문수집";
			break;
		case WorkStatus.문의수집:
			result = "문의수집";
			break;
		default:
			result = "";
			break;

		}
		return result;
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

	public WorkStatus bind() {
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

		public WorkStatus getInstance() {
			return instance;
		}
	}

}

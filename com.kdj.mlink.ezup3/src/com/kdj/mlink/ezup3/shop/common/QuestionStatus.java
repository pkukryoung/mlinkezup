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

public class QuestionStatus {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = YDMASessonUtil.getAppPath() + "\\YDwmsData\\chromedriver.exe";
	public static final String 신규 = "100";
	public static final String 답변완료 = "101";
	public static final String 보류 = "102";
	public static final String 보류해제 = "202";
	public static final String 전송완료 = "103";  // 실제 값 쓰이지 않지만 명령행 
	public static final String 확인완료 = "104";

	public static final String 조회 = "1000";
	public static final String 저장 = "2000";
	public static final String 업데이트 = "3000";

	//   결과값.. 
	public static final String 데이타없음 = "0";
	public static final String 에러발생 = "-1";
	public static final String 정상처리 = "1";
	String result = "";

	private QuestionStatus() {
	}

	private static QuestionStatus instance = new QuestionStatus();
	private List<OrderCodeMapperDto> items = new ArrayList<OrderCodeMapperDto>();

	public static QuestionStatus getInstance() {
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
		case QuestionStatus.보류해제: // 현제 상태가 보류 일 경우에만 보류해제를 한다. 
			if(status.equals(QuestionStatus.보류)) { 
				ret = "0";
			}else {
				ret = "현제 상태가 보류가 아닙니다. 상태값을 확인해주세요";
			}
			break;
		case QuestionStatus.보류:
			switch (status) { // 현재 상태..
			case QuestionStatus.신규:
			case QuestionStatus.확인완료:
			case QuestionStatus.답변완료:
				ret = "0";
				break;
			default:
				ret = "(신규, 확인완료, 답변완료) 상태가 아닌값은 보류를 할수 없습니다.";
				break;
			}
			break;
		}
		return ret;
	}

	/*
	 * 해당 코드를 가져온다.
	 */
	public String findCode(String code) {
		String result = "";
		switch (code) {
		case QuestionStatus.신규:
			result = "신규";
			break;
		case QuestionStatus.답변완료:
			result = "답변완료";
			break;
		case QuestionStatus.보류:
			result = "보류";
			break;
		case QuestionStatus.전송완료:
			result = "전송완료";
			break;
		case QuestionStatus.확인완료:
			result = "확인완료";
			break;
		default:
			result = "";
			break;

		}
		return result;
	}

	public Color findColor(String code) {
		Color bgColor = null;

		switch (code) {
		case QuestionStatus.신규:
			bgColor = SWTResourceManager.getColor(255, 255, 0);
			break;
		case QuestionStatus.답변완료:
			bgColor = SWTResourceManager.getColor(255, 248, 191);
			break;
		case QuestionStatus.보류:
			bgColor = SWTResourceManager.getColor(255, 188, 62);
			break;
		case QuestionStatus.전송완료:
			bgColor = SWTResourceManager.getColor(246, 246, 246);
			break;
		case QuestionStatus.확인완료:
			bgColor = SWTResourceManager.getColor(255, 255, 255);
			break;
		default:
			bgColor = SWTResourceManager.getColor(SWT.COLOR_WHITE);
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

	public QuestionStatus bind() {
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

		public QuestionStatus getInstance() {
			return instance;
		}
	}

}

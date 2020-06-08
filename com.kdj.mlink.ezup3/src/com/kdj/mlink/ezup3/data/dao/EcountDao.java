package com.kdj.mlink.ezup3.data.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class EcountDao {

	public String API_KEY;
	public String COM_CODE;
	public String USER_ID;

	public EcountDao() throws Exception {

		try {

			CompInfoDao dao = new CompInfoDao();
			List<String> list = dao.getCompanyInfoByAll();

			API_KEY = list.get(5);
			COM_CODE = list.get(3);
			USER_ID = list.get(4);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

	}

	public String ZONE_URL = "https://oapi.ecounterp.com/OAPI/V2/Zone";
	public String REQ_METHOD = "POST";
	public String ACCEPT = "application/json";
	public String CONTENT_TYPE = "application/json";

	// 주문관련 코드값
	String CUST = "00003";
	String WH_CD = "100";

	private String getZone() throws Exception {

		String zoneStr = null;

		try {

			URL obj = new URL(ZONE_URL);
			HttpsURLConnection httpConnection = (HttpsURLConnection) obj.openConnection();
			httpConnection.setRequestMethod(REQ_METHOD);
			httpConnection.setRequestProperty("Accept", ACCEPT);
			httpConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
			httpConnection.setDoOutput(true);

			String payload = "{'COM_CODE':'" + COM_CODE + "'}";

			DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
			wr.writeBytes(payload);
			wr.flush();
			wr.close();

			int responseCode = httpConnection.getResponseCode();

			if (responseCode == 404) {
				throw new UnknownHostException("\n인터넷 주소를 찾지 못하였습니다.\n" + ZONE_URL);
			} else if (responseCode == 500) {
				throw new UnknownHostException("\nZone 정보가 없습니다.");
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));

			String inputLine = null;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			httpConnection.disconnect();

//			System.out.println("Zon Api 응답 코드: "+responseCode);
//			System.out.println("Zon Api 응답 메시지: \n"+response.toString());

			if (responseCode == 200) {
				JsonParser jsonParser = new JsonParser();
				JsonElement jsonObject = jsonParser.parse(response.toString());

				if (jsonObject.getAsJsonObject().get("Data") != null) {
					JsonElement jdata = jsonObject.getAsJsonObject().get("Data");
					JsonElement jzone = jdata.getAsJsonObject().get("ZONE");
					zoneStr = jzone.getAsString();
				} else if (jsonObject.getAsJsonObject().get("Status") != null) {
					JsonElement status = jsonObject.getAsJsonObject().get("Status");
					String statusCode = status.getAsString();
					if (statusCode.equals("500")) {
						JsonElement errors = jsonObject.getAsJsonObject().get("Errors");
						JsonArray array = errors.getAsJsonArray();
						JsonElement message = array.get(0).getAsJsonObject().get("Message");
						throw new Exception(message.getAsString());
					}
				}

			}

		} catch (java.net.UnknownHostException uhe) {
			throw new Exception(uhe.getMessage() + "\n인터넷 주소를 찾지 못하였습니다.\n" + ZONE_URL);
		}

		return zoneStr;
	}

	// HTTP POST request
	private String getSessionID(String zoneStr) throws Exception {

		String sessionStr = null;
		String sessionUrl = null;
		try {
			sessionUrl = "https://oapi" + zoneStr + ".ecounterp.com/OAPI/V2/OAPILogin";
			URL obj = new URL(sessionUrl);
			HttpsURLConnection httpConnection = (HttpsURLConnection) obj.openConnection();
			// add reuqest header
			httpConnection.setRequestMethod(REQ_METHOD);
			httpConnection.setRequestProperty("Accept", ACCEPT);
			httpConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
			// Send post request
			httpConnection.setDoOutput(true);

			String payload2 = "{" + "'COM_CODE':'" + COM_CODE + "', " + "'USER_ID':'" + USER_ID + "', "
					+ " 'API_CERT_KEY':'" + API_KEY + "', " + " 'LAN_TYPE':'ko-KR', " + " 'ZONE':'" + zoneStr + "'"
					+ "}";

			DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
			out.write(payload2.toString().getBytes("UTF-8"));
			out.flush();
			out.close();

			int responseCode = httpConnection.getResponseCode();

			if (responseCode == 404) {
				throw new UnknownHostException("\n인터넷 주소를 찾지 못하였습니다.\n" + ZONE_URL);
			} else if (responseCode == 500) {
				throw new Exception("\nEcount 담당자에게 문의 바랍니다.");
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));

			String inputLine = null;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			httpConnection.disconnect();

//			System.out.println("Session Api 응답 코드: "+responseCode);
//			System.out.println("Session Api 응답 메시지: \n"+response.toString());

			JsonParser jsonParser = new JsonParser();
			JsonElement jsonObject = jsonParser.parse(response.toString());

			if (jsonObject.getAsJsonObject().get("Data") != null) {
				JsonElement jdata2 = jsonObject.getAsJsonObject().get("Data");
				JsonElement jdatas2 = jdata2.getAsJsonObject().get("Datas");
				if (jdatas2.getAsJsonObject().get("SESSION_ID") == null) {
					JsonElement message = jdata2.getAsJsonObject().get("Message");
					throw new Exception(message.getAsString());
				}

				JsonElement sessionID = jdatas2.getAsJsonObject().get("SESSION_ID");
				sessionStr = sessionID.getAsString();

			} else if (jsonObject.getAsJsonObject().get("Status") != null) {
				JsonElement status = jsonObject.getAsJsonObject().get("Status");
				String statusCode = status.getAsString();
				if (statusCode.equals("500")) {
					JsonElement errors = jsonObject.getAsJsonObject().get("Errors");
					JsonArray array = errors.getAsJsonArray();
					JsonElement message = array.get(0).getAsJsonObject().get("Message");
					throw new Exception(message.getAsString());
				}
			}

		} catch (java.net.UnknownHostException uhe) {
			throw new Exception(uhe.getMessage() + "\n인터넷 주소를 찾지 못하였습니다.\n" + sessionUrl);
		}

		return sessionStr;
	}

	public List<List<String>> sendProductListToEcount(List<List<String>> contents) throws Exception {

		List<List<String>> responContents = new ArrayList<>();

		String zoneStr = this.getZone();
		String sessionId = this.getSessionID(zoneStr);

		String url = "https://oapi" + zoneStr + ".ecounterp.com/OAPI/V2/InventoryBasic/SaveBasicProduct?SESSION_ID="
				+ sessionId;

		URL obj = new URL(url);
		HttpsURLConnection httpConnection = (HttpsURLConnection) obj.openConnection();

		// add reuqest header
		httpConnection.setRequestMethod(REQ_METHOD);
		httpConnection.setRequestProperty("Accept", ACCEPT);
		httpConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
		// Send post request
		httpConnection.setDoOutput(true);

		StringBuffer payload = new StringBuffer();
		payload.append("{ 'ProductList': [ ");
		payload.append("\n"); //
		for (int i = 0; i < contents.size(); i++) {
			payload.append("{ 'Line':'");
			payload.append(i);
			payload.append("', 'BulkDatas': ");
			List<String> list = contents.get(i);
			payload.append("{ ").append("'PROD_CD'").append(":'").append(list.get(1)).append("'");
			payload.append(", ").append("'PROD_DES'").append(":'").append(list.get(2)).append("'");
			payload.append("} } ");

			if (i < contents.size() - 1) {
				payload.append(", ");
			}
			payload.append("\n"); //
		}
		payload.append("] }");
		;

//		System.out.println("payload - 이카운트 상품등록 : \n"+payload);

		DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
		// out.writeBytes(payload.toString());
		out.write(payload.toString().getBytes("UTF-8"));
		out.flush();
		out.close();

		int responseCode = httpConnection.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
		String inputLine = null;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		httpConnection.disconnect();

//		System.out.println("SaveBasicProduct Api 응답 코드: "+responseCode);
//		System.out.println("SaveBasicProduct Api 응답 메시지: \n"+response.toString());

		JsonParser jsonParser = new JsonParser();
		JsonElement jsonObject = jsonParser.parse(response.toString());

		String statusCode = jsonObject.getAsJsonObject().get("Status").getAsString();
		String timeStamp = "";

		if (statusCode.equals("200")) {
			JsonElement dataObject = jsonObject.getAsJsonObject().get("Data");
			JsonElement jsonObject_result = dataObject.getAsJsonObject().get("ResultDetails");
			JsonArray jsonArray_result = jsonObject_result.getAsJsonArray();

			for (int i = 0; i < jsonArray_result.size(); i++) {
				JsonElement jsonElement = jsonArray_result.get(i);
				int msgIndex = jsonElement.getAsJsonObject().get("Line").getAsInt();
				boolean isSuccess = jsonElement.getAsJsonObject().get("IsSuccess").getAsBoolean();

				String errorCod = "";
				String errorMsg = "";
				timeStamp = jsonObject.getAsJsonObject().get("Timestamp").getAsString();

				if (!isSuccess) {
					// 실패는 오류코드와 메시지를 처리한다.
					errorCod = jsonElement.getAsJsonObject().get("Errors").getAsJsonArray().get(0).getAsJsonObject()
							.get("ColCd").getAsString();
					errorMsg = jsonElement.getAsJsonObject().get("Errors").getAsJsonArray().get(0).getAsJsonObject()
							.get("Message").getAsString();

				} else {
					// 성공 - 별도 처리 없음.
				}

				List<String> list = new ArrayList<>();

				List<String> errProdcut = contents.get(msgIndex);

				String prodcd = errProdcut.get(1); // getProdCd(payload.toString(), msgIndex);
				list.add("" + isSuccess);
				list.add(errorCod);
				list.add(errorMsg);
				list.add(timeStamp);
				list.add(prodcd);

				responContents.add(list);
			}

		} else if (statusCode.equals("500")) {

			JsonElement dataObject = jsonObject.getAsJsonObject().get("Errors");
			JsonArray dataObject_error = dataObject.getAsJsonArray();
			String errorCod = dataObject_error.get(0).getAsJsonObject().get("Code").getAsString();
			String errorMsg = dataObject_error.get(0).getAsJsonObject().get("Message").getAsString();
			// 500 오류의 경우 "Timestamp" 응답이 없다.
			timeStamp = YDMATimeUtil.getCurrentTimeByFreeFormat("yyyy-MM-dd HH:mm:ss");

			for (List<String> errorProduct : contents) {
				List<String> list = new ArrayList<>();
				list.add("false");
				list.add(errorCod);
				list.add(errorMsg);
				list.add(timeStamp);
				list.add(errorProduct.get(1)); // prodcd

				responContents.add(list);
			}

		}

		return responContents;
	}

	public List<List<String>> sendStockToEcount(List<List<String>> contents, String orddt, String ordseq)
			throws Exception {

		List<List<String>> responContents = new ArrayList<>();

		String zoneStr = this.getZone();
		String sessionId = this.getSessionID(zoneStr);

		String url = "https://oapi" + zoneStr + ".ecounterp.com/OAPI/V2/Sale/SaveSale?SESSION_ID=" + sessionId;

		URL obj = new URL(url);
		HttpsURLConnection httpConnection = (HttpsURLConnection) obj.openConnection();

		// add reuqest header
		httpConnection.setRequestMethod(REQ_METHOD);
		httpConnection.setRequestProperty("Accept", ACCEPT);
		httpConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
		// Send post request
		httpConnection.setDoOutput(true);

		StringBuffer payload = new StringBuffer();
		payload.append("{ 'SaleList': [ ");
		payload.append("\n"); //
		for (int i = 0; i < contents.size(); i++) {
			payload.append("{ 'Line':'");
			payload.append(i);
			payload.append("', 'BulkDatas': ");
			List<String> list = contents.get(i);

			payload.append("{"); //

			payload.append("'IO_DATE'").append(":'',");// --가변//payload.append("'IO_DATE'").append(":'").append(orddt).append("',
														// ");
			payload.append("'UPLOAD_SER_NO'").append(":'',");// --가변
																// //payload.append("'UPLOAD_SER_NO'").append(":'").append(uploadSerNo).append("',
																// ");
			payload.append("'CUST'").append(":'").append(CUST).append("', "); // --고정 (00003) () 17번째것을 짤라서 넣는다
			payload.append("'CUST_DES'").append(":'',"); // --거래처명 () 이후것 17번째것을 짤라서 넣는다
			payload.append("'EMP_CD'").append(":'',");// --가변
			payload.append("'WH_CD'").append(":'").append(WH_CD).append("', "); // 고정 (100)
			payload.append("'IO_TYPE'").append(":'',");// --가변
			payload.append("'EXCHANGE_TYPE'").append(":'',");// --가변
			payload.append("'EXCHANGE_RATE'").append(":'',");// --가변
			payload.append("'PJT_CD'").append(":'',");// --가변
			payload.append("'DOC_NO'").append(":'',");// --가변
			payload.append("'U_MEMO1'").append(":'',");// --가변
			payload.append("'U_MEMO2'").append(":'',");// --가변
			payload.append("'U_MEMO3'").append(":'',");// --가변
			payload.append("'U_MEMO4'").append(":'',");// --가변
			payload.append("'U_MEMO5'").append(":'',");// --가변
			payload.append("'U_TXT1'").append(":'',");// --가변
			payload.append("'PROD_CD'").append(":'").append(list.get(11)).append("', ");// --가변
			payload.append("'PROD_DES'").append(":'").append(list.get(10)).append("', ");// --가변
			payload.append("'SIZE_DES'").append(":'',");// --가변
			payload.append("'UQTY'").append(":'',");// --가변
			payload.append("'QTY'").append(":'").append(list.get(7)).append("', "); // 수량
			payload.append("'PRICE'").append(":'").append(list.get(20)).append("', "); // 단가
			payload.append("'USER_PRICE_VAT'").append(":'',"); // 부가세 포함 공급가(?)
			payload.append("'SUPPLY_AMT'").append(":'',"); // 공급가액(원화)
			payload.append("'SUPPLY_AMT_F'").append(":'',");// --가변
			payload.append("'VAT_AMT'").append(":'',"); // 부가세
			payload.append("'REMARKS'").append(":'',");// --가변
			payload.append("'ITEM_CD'").append(":'',");// --가변
			payload.append("'P_AMT1'").append(":'',");// --가변
			payload.append("'P_AMT2'").append(":'',");// --가변
			payload.append("'P_REMARKS1'").append(":'',");// --가변
			payload.append("'P_REMARKS2'").append(":'',");// --가변
			payload.append("'P_REMARKS3'").append(":'',");// --가변
			payload.append("'REL_DATE'").append(":'',");// --가변
			payload.append("'REL_NO'").append(":'' ,");// --가변
			payload.append("'MAKE_FLAG'").append(":'',");// --가변
			payload.append("'CUST_AMT'").append(":''");// --가변

			payload.append("} ");
			payload.append("} ");

			if (i < contents.size() - 1) {
				payload.append(", ");
			}

			payload.append("\n"); // --지워야함
		}

		payload.append("]}");
		;

		System.out.println(payload);

		// String enCodepayload = payload.toString().getBytes("UTF-8");
		// System.out.println("인코딩:\n"+enCodepayload);

		DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
		out.write(payload.toString().getBytes("UTF-8"));
		out.flush();
		out.close();

		int responseCode = httpConnection.getResponseCode();

		System.out.println("이카운트 재고차막 응답 : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
		String inputLine = null;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		httpConnection.disconnect();

//		System.out.println("SaveSaleOrder Api 응답 코드: "+responseCode);
		System.out.println("SaveSaleOrder Api 응답 메시지-1: ====================================");
		System.out.println(response.toString());
		System.out.println("SaveSaleOrder Api 응답 메시지-2: ====================================");

		JsonParser jsonParser = new JsonParser();
		JsonElement jsonObject = jsonParser.parse(response.toString());

		String statusCode = jsonObject.getAsJsonObject().get("Status").getAsString();
		String timeStamp = "";

		if (statusCode.equals("200")) {

			JsonElement dataObject = jsonObject.getAsJsonObject().get("Data");
			JsonElement jsonObject_result = dataObject.getAsJsonObject().get("ResultDetails");
			JsonArray jsonArray_result = jsonObject_result.getAsJsonArray();

			for (int i = 0; i < jsonArray_result.size(); i++) {
				JsonElement jsonElement = jsonArray_result.get(i);
				int msgIndex = jsonElement.getAsJsonObject().get("Line").getAsInt();
				boolean isSuccess = jsonElement.getAsJsonObject().get("IsSuccess").getAsBoolean();

				String errorCod = "";
				String errorMsg = "";

				if (!isSuccess) {
					// 판매등록의 경우는 Code null 임 처리시 오류남
					// errorCod = jsonElement.getAsJsonObject().get("Code").getAsString();
					errorCod = "SaveSale_ERR"; // 이카운트 코드 아님- 임의로 만듦.
					errorMsg = jsonElement.getAsJsonObject().get("TotalError").getAsString();

				}

				timeStamp = jsonObject.getAsJsonObject().get("Timestamp").getAsString();

				List<String> list = new ArrayList<>();
				list.add("" + isSuccess);
				list.add(errorCod);
				list.add(errorMsg);
				list.add(timeStamp);
				// --- 이후 주문테이블 key
				list.add(orddt);
				list.add(ordseq);
				list.add(contents.get(msgIndex).get(0));

				responContents.add(list);
			}

		} else if (statusCode.equals("500")) {

			JsonElement dataObject = jsonObject.getAsJsonObject().get("Errors");
			JsonArray dataObject_error = dataObject.getAsJsonArray();
			String errorCod = dataObject_error.get(0).getAsJsonObject().get("Code").getAsString();
			String errorMsg = dataObject_error.get(0).getAsJsonObject().get("Message").getAsString();

			// 500 오류의 경우 "Timestamp" 이 없다.
			timeStamp = YDMATimeUtil.getCurrentTimeByFreeFormat("yyyy-MM-dd HH:mm:ss");

			for (List<String> errProduct : contents) {
				List<String> list = new ArrayList<>();
				list.add("false");
				list.add(errorCod);
				list.add(errorMsg);
				list.add(timeStamp);
				// --- 이후 주문테이블 key
				list.add(orddt);
				list.add(ordseq);
				list.add(errProduct.get(0));

				responContents.add(list);
			}

		}

		return responContents;
	}

	public List<List<String>> sendStockToEcountForBusiness(List<List<String>> contents, String orddt, String ordseq)
			throws Exception {

		List<List<String>> responContents = new ArrayList<>();

		String zoneStr = this.getZone();
		String sessionId = this.getSessionID(zoneStr);

		String url = "https://oapi" + zoneStr + ".ecounterp.com/OAPI/V2/Sale/SaveSale?SESSION_ID=" + sessionId;

		URL obj = new URL(url);
		HttpsURLConnection httpConnection = (HttpsURLConnection) obj.openConnection();

		// add reuqest header
		httpConnection.setRequestMethod(REQ_METHOD);
		httpConnection.setRequestProperty("Accept", ACCEPT);
		httpConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
		// Send post request
		httpConnection.setDoOutput(true);

		StringBuffer payload = new StringBuffer();
		payload.append("{ 'SaleList': [ ");
		payload.append("\n"); //
		for (int i = 0; i < contents.size(); i++) {
			payload.append("{ 'Line':'");
			payload.append(i);
			payload.append("', 'BulkDatas': ");
			List<String> list = contents.get(i);

			payload.append("{"); //
			payload.append("'IO_DATE'").append(":'',");// --가변//payload.append("'IO_DATE'").append(":'").append(orddt).append("',
														// ");
			payload.append("'UPLOAD_SER_NO'").append(":'',");// --가변
																// //payload.append("'UPLOAD_SER_NO'").append(":'").append(uploadSerNo).append("',
																// ");

			String custStr = list.get(18).substring(1, list.get(18).indexOf(")"));
			payload.append("'CUST'").append(":'").append(custStr).append("', "); // --17번째것을 짤라서 넣는다
			payload.append("'CUST_DES'").append(":'',"); // --거래처명 () 이후것 17번째것을 짤라서 넣는다
			payload.append("'EMP_CD'").append(":'',");// --가변
			payload.append("'WH_CD'").append(":'").append(WH_CD).append("', "); // 고정 (100)
			payload.append("'IO_TYPE'").append(":'',");// --가변
			payload.append("'EXCHANGE_TYPE'").append(":'',");// --가변
			payload.append("'EXCHANGE_RATE'").append(":'',");// --가변
			payload.append("'PJT_CD'").append(":'',");// --가변
			payload.append("'DOC_NO'").append(":'',");// --가변
			payload.append("'U_MEMO1'").append(":'',");// --가변
			payload.append("'U_MEMO2'").append(":'',");// --가변
			payload.append("'U_MEMO3'").append(":'',");// --가변
			payload.append("'U_MEMO4'").append(":'',");// --가변
			payload.append("'U_MEMO5'").append(":'',");// --가변
			payload.append("'U_TXT1'").append(":'',");// --가변
			payload.append("'PROD_CD'").append(":'").append(list.get(11)).append("', ");// --가변
			payload.append("'PROD_DES'").append(":'").append(list.get(10)).append("', ");// --가변
			payload.append("'SIZE_DES'").append(":'',");// --가변
			payload.append("'UQTY'").append(":'',");// --가변
			payload.append("'QTY'").append(":'").append(list.get(7)).append("', "); // 수량
			payload.append("'PRICE'").append(":'").append(list.get(21)).append("', "); // 단가
			payload.append("'USER_PRICE_VAT'").append(":'',");

			int suppAmt = Integer.parseInt(list.get(7)) * Integer.parseInt(list.get(21));
			payload.append("'SUPPLY_AMT'").append(":'").append(Integer.toString(suppAmt)).append("', "); // 공급가액(원화)
			payload.append("'SUPPLY_AMT_F'").append(":'',");// --가변

			int suppVat = (int) Math.round(suppAmt * 0.1);
			payload.append("'VAT_AMT'").append(":'").append(Integer.toString(suppVat)).append("', "); // 부가세
			payload.append("'REMARKS'").append(":'',");// --가변
			payload.append("'ITEM_CD'").append(":'',");// --가변
			payload.append("'P_AMT1'").append(":'',");// --가변
			payload.append("'P_AMT2'").append(":'',");// --가변
			payload.append("'P_REMARKS1'").append(":'',");// --가변
			payload.append("'P_REMARKS2'").append(":'',");// --가변
			payload.append("'P_REMARKS3'").append(":'',");// --가변
			payload.append("'REL_DATE'").append(":'',");// --가변
			payload.append("'REL_NO'").append(":'' ,");// --가변
			payload.append("'MAKE_FLAG'").append(":'',");// --가변
			payload.append("'CUST_AMT'").append(":''");// --가변

			payload.append("} ");
			payload.append("} ");

			if (i < contents.size() - 1) {
				payload.append(", ");
			}

			payload.append("\n"); // --지워야함
		}

		payload.append("]}");
		;

		System.out.println(payload);

		// String enCodepayload = payload.toString().getBytes("UTF-8");
		// System.out.println("인코딩:\n"+enCodepayload);

		DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
		out.write(payload.toString().getBytes("UTF-8"));
		out.flush();
		out.close();

		int responseCode = httpConnection.getResponseCode();

		System.out.println("이카운트 재고차막 응답 : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
		String inputLine = null;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		httpConnection.disconnect();

//		System.out.println("SaveSaleOrder Api 응답 코드: "+responseCode);
		System.out.println("SaveSaleOrder Api 응답 메시지-1: ====================================");
		System.out.println(response.toString());
		System.out.println("SaveSaleOrder Api 응답 메시지-2: ====================================");

		JsonParser jsonParser = new JsonParser();
		JsonElement jsonObject = jsonParser.parse(response.toString());

		String statusCode = jsonObject.getAsJsonObject().get("Status").getAsString();
		String timeStamp = "";

		if (statusCode.equals("200")) {

			JsonElement dataObject = jsonObject.getAsJsonObject().get("Data");
			JsonElement jsonObject_result = dataObject.getAsJsonObject().get("ResultDetails");
			JsonArray jsonArray_result = jsonObject_result.getAsJsonArray();

			for (int i = 0; i < jsonArray_result.size(); i++) {
				JsonElement jsonElement = jsonArray_result.get(i);
				int msgIndex = jsonElement.getAsJsonObject().get("Line").getAsInt();
				boolean isSuccess = jsonElement.getAsJsonObject().get("IsSuccess").getAsBoolean();

				String errorCod = "";
				String errorMsg = "";

				if (!isSuccess) {
					// 판매등록의 경우는 Code null 임 처리시 오류남
					// errorCod = jsonElement.getAsJsonObject().get("Code").getAsString();
					errorCod = "SaveSale_ERR"; // 이카운트 코드 아님- 임의로 만듦.
					errorMsg = jsonElement.getAsJsonObject().get("TotalError").getAsString();

				}

				timeStamp = jsonObject.getAsJsonObject().get("Timestamp").getAsString();

				List<String> list = new ArrayList<>();
				list.add("" + isSuccess);
				list.add(errorCod);
				list.add(errorMsg);
				list.add(timeStamp);
				// --- 이후 주문테이블 key
				list.add(orddt);
				list.add(ordseq);
				list.add(contents.get(msgIndex).get(0));

				responContents.add(list);
			}

		} else if (statusCode.equals("500")) {

			JsonElement dataObject = jsonObject.getAsJsonObject().get("Errors");
			JsonArray dataObject_error = dataObject.getAsJsonArray();
			String errorCod = dataObject_error.get(0).getAsJsonObject().get("Code").getAsString();
			String errorMsg = dataObject_error.get(0).getAsJsonObject().get("Message").getAsString();

			// 500 오류의 경우 "Timestamp" 이 없다.
			timeStamp = YDMATimeUtil.getCurrentTimeByFreeFormat("yyyy-MM-dd HH:mm:ss");

			for (List<String> errProduct : contents) {
				List<String> list = new ArrayList<>();
				list.add("false");
				list.add(errorCod);
				list.add(errorMsg);
				list.add(timeStamp);
				// --- 이후 주문테이블 key
				list.add(orddt);
				list.add(ordseq);
				list.add(errProduct.get(0));

				responContents.add(list);
			}

		}

		return responContents;
	}

	public List<List<String>> getProdcutListSendToEcount() throws Exception {

		// // {"상품코드", "상품명"}
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select p.prodcd, "
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,p.prodcd) as img,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ " case ifnull(p.specdes,'') when '' then concat(ifnull(p.prodnm,'')) ELSE concat(ifnull(p.prodnm,''), '_',p.specdes) end "
					+ " from  prodmst as p " + " where ifnull(P.ECOUNTCD, '')='' and p.compno = ? ";
			// SELECT P.PRODCD, IFNULL(P.PRODNM,'') FROM PRODMST AS P WHERE
			// ifnull(P.ECOUNTCD, '') =''
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getProdcutListSendToEcount]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add("" + (++rowno)); // ui grid 상의 순번 , 디비데이타 아님
				list.add(rs.getString(++i)); // prodcd
				list.add(rs.getString(++i)); // image
				list.add(rs.getString(++i)); // prodnm
				list.add("미등록"); //
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

	/**
	 * API 연동시 오류에대한 로그를 조회 (이카운트, 사방넷 공통으로 사용함)
	 * 
	 * @param site
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getProdcutListSendFail(String site) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
			String sql = null;
			if (site.equals(ProductMstDao.GUBUN_E)) {
				sql = "SELECT a.prodcd, "
						+ String.format("YWM_FUNC_BOSSPRODCD(%s,a.prodcd) as img,",
								YDMASessonUtil.getCompnoInfo().getCompno())
						+ "B.PRODNM , if(A.state='false','등록실패','알수없음') , A.errcd,  A.errdsc "
						+ " FROM  ecountprodlog A  LEFT JOIN  prodmst B "
						+ " ON a.prodcd = b.PRODCD and A.compno = B.compno "
						+ " where A.state = 'false' and a.compno = ? " + " and A.gubun = 'E' " // ecountprodlog 에 사방넷(S)
																								// 와 이카운트 (E)을 구분하는 구분자
						+ " Order by a.prodcd ";
			} else if (site.equals(ProductMstDao.GUBUN_S)) {
				sql = "SELECT a.prodcd, "
						+ String.format("YWM_FUNC_BOSSPRODCD(%s,a.prodcd) as img,",
								YDMASessonUtil.getCompnoInfo().getCompno())
						+ "B.PRODNM , if(A.state='FAIL','등록실패','알수없음') , A.errcd,  A.errdsc "
						+ " FROM  ecountprodlog A  LEFT JOIN  prodmst B "
						+ " ON a.prodcd = b.PRODCD and A.compno = B.compno "
						+ " where A.state = 'FAIL' and a.compno = ? " + " and A.gubun = 'S' " // ecountprodlog 에 사방넷(S)
																								// 와 이카운트 (E)을 구분하는 구분자
						+ " Order by a.prodcd ";
			}

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getProdcutListSendFail " + site + "]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add("" + (++rowno)); // ui grid 상의 순번 , 디비데이타 아님
				list.add(rs.getString(++i)); // prodcd
				list.add(rs.getString(++i)); // image
				list.add(rs.getString(++i)); // prodnm
				list.add(rs.getString(++i)); // state (false --> false)
				list.add(rs.getString(++i)); // errcd
				list.add(rs.getString(++i)); // errdsc
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

	public List<List<String>> getOrderListEcountSendFail(String orddt, String ordseq) throws Exception {

		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select  B.errdsc, A.seq , ifnull(rorddt,''), ifnull(rcvnam,''), ifnull(pstno,''),  ";
			sql += " ifnull(addr,''), ifnull(clpno,''), ifnull(telno,''), ifnull(qty,''),  ifnull(shpfee,''), ifnull(credit,''),";
			sql += " ifnull(optdesc,''), ifnull(prodcd,''), ifnull(messge,''), ifnull(pkgclss,''), ";
			sql += " ifnull(shipcls,''), ifnull(sabordno,''), ifnull(shopid,''), ifnull(ordnm,''), ";
			sql += " ifnull(etcmsg,''), ifnull(mallcd,''), ifnull(ordamt,''), ifnull(ECOUNTSNDYN,'N') ECOUNTSNDYN ";
			sql += " from ecountstocklog B , orddtl  A ";
			sql += " where B.orddt = ? and B.ordseq=? and B.compno = ? ";
			sql += " and A.orddt = B.orddt AND A.ordseq = B.ordseq AND A.seq = B.seq and A.compno = B.compno ";
			sql += " order by A.seq ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, orddt);
			pstmt.setString(2, ordseq);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[getOrderListEcountSendFail]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add(rs.getString(++i)); // ecountstocklog.errdsc
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i)); // ECOUNTSNDYN

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

	/**
	 * processEcountProductLog 트랜잭션 updateProdmst - deleteEcountProdlog -
	 * insertEcountProdlog
	 * 
	 * @param contents
	 * @throws Exception
	 */
	public void processEcountProductLog(List<List<String>> contents) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			updateProdmst(connection, statementlist, contents);
			deleteEcountProdlog(connection, statementlist, contents);
			insertEcountProdlog(connection, statementlist, contents);

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	public void updateProdmst(Connection connection, List<PreparedStatement> pstmtlist, List<List<String>> contents)
			throws Exception {

		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" Update PRODMST set ecountcd = prodcd ");
		sqlBuffer.append(" where prodcd = ? and compno = ? ");

		String sql = sqlBuffer.toString().toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmtlist.add(pstmt);

		for (List<String> list : contents) {
//			list.add(""+isSuccess);
//			list.add(errorCod);
//			list.add(errorMsg);
//			list.add(timeStamp);
//			list.add(prodcd);
			if (list.get(0).equals("true")) {
				String prodcd = list.get(4);
				pstmt.setString(1, prodcd);
				pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.addBatch();
				System.out.println("[updateProdmst]" + pstmt.toString());
				pstmt.clearParameters(); // 파라미터 초기화
			}
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

	}

	public void deleteEcountProdlog(Connection connection, List<PreparedStatement> pstmtlist,
			List<List<String>> contents) throws Exception {

		String sql = "DELETE FROM ECOUNTPRODLOG where prodcd = ? and gubun='E' and compno = ? ";

		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmtlist.add(pstmt);

		for (List<String> list : contents) {
//			list.add(""+isSuccess);
//			list.add(errorCod);
//			list.add(errorMsg);
//			list.add(timeStamp);
//			list.add(prodcd);

			pstmt.setString(1, list.get(4));
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[deleteEcountProdlog]" + pstmt.toString());

			pstmt.addBatch();
			pstmt.clearParameters(); // 파라미터 초기화

		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

	}

	/**
	 * insertEcountProdlog 트랜잭션
	 * 
	 * @param connection
	 * @param pstmtList
	 * @param contents
	 * @throws Exception
	 */
	public void insertEcountProdlog(Connection connection, List<PreparedStatement> pstmtList,
			List<List<String>> contents) throws Exception {

		String sql = "insert into ECOUNTPRODLOG  (compno, prodcd, gubun, state, errcd, errdsc, errtime)  values (?, ?, ?, ?, ?, ?, ?) ";

		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmtList.add(pstmt);

		for (List<String> list : contents) {
//			list.add(""+isSuccess);
//			list.add(errorCod);
//			list.add(errorMsg);
//			list.add(timeStamp);
//			list.add(prodcd);
			if (list.get(0).equals("false")) {
				int idx = 0;
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++idx, list.get(4));
				pstmt.setString(++idx, ProductMstDao.GUBUN_E);
				pstmt.setString(++idx, list.get(0));
				pstmt.setString(++idx, list.get(1));
				pstmt.setString(++idx, list.get(2));
				pstmt.setString(++idx, list.get(3));

				System.out.println("[insertEcountProdlog]" + pstmt.toString());
				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화
	}

	/**
	 * processEcountStocklog 트랜잭션 updateOrddtl - deleteEcountStocklog -
	 * insertEcountStocklog
	 * 
	 * @param contents
	 * @throws Exception
	 */
	public void processEcountStocklog(List<List<String>> contents) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			updateOrddtl(connection, statementlist, contents);
			deleteEcountStocklog(connection, statementlist, contents);
			insertEcountStocklog(connection, statementlist, contents);

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
		}
	}

	/**
	 * processEcountStocklog 트랜잭션그룹
	 * 
	 * @param connection
	 * @param pstmtlist
	 * @param contents
	 * @throws Exception
	 */
	public void updateOrddtl(Connection connection, List<PreparedStatement> pstmtlist, List<List<String>> contents)
			throws Exception {

		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" Update ORDDTL set ECOUNTSNDYN = ? ");
		sqlBuffer.append(" where ORDDT =? and ORDSEQ =? and SEQ =? and compno = ? ");

		String sql = sqlBuffer.toString().toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmtlist.add(pstmt);

		System.out.println("[updateOrddtl]" + pstmt.toString());

		for (List<String> list : contents) {
//			list.add(""+isSuccess);	//false
//			list.add(errorCod); //SaveSale_ERR
//			list.add(errorMsg); //[전표묶음1] 생산전표생성(Not Exist Data)
//			list.add(timeStamp); //2019-03-04 19:08:56.768 	타임트템프
//			list.add(orddt); //20190304 - 업로드 일자
//			list.add(ordseq); //1 - 차수
//			list.add(seq);	//2	- 주문인덱스

			if (list.get(0).equals("true")) {
				pstmt.setString(1, "Y");
				pstmt.setString(2, list.get(4));
				pstmt.setString(3, list.get(5));
				pstmt.setString(4, list.get(6));
				pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
//				System.out.println("[updateOrddtl]"+pstmt.toString());
				pstmt.clearParameters(); // 파라미터 초기화
			}
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화
	}

	/**
	 * processEcountStocklog 트랜잭션그룹
	 * 
	 * @param connection
	 * @param pstmtlist
	 * @param contents
	 * @throws Exception
	 */
	public void deleteEcountStocklog(Connection connection, List<PreparedStatement> pstmtlist,
			List<List<String>> contents) throws Exception {

		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" DELETE FROM ECOUNTSTOCKLOG  ");
		sqlBuffer.append(" WHERE  orddt  IN (SELECT orddt   ");
		sqlBuffer.append("                   FROM (SELECT orddt  ");
		sqlBuffer.append("                         FROM ECOUNTSTOCKLOG ");
		sqlBuffer.append("                         WHERE orddt=? and ordseq=? and seq =? and compno = ? ) X) ");

		String sql = sqlBuffer.toString().toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmtlist.add(pstmt);

		int i = 0;
		for (List<String> list : contents) {
//			list.add(""+isSuccess);	//false
//			list.add(errorCod); //SaveSale_ERR
//			list.add(errorMsg); //[전표묶음1] 생산전표생성(Not Exist Data)
//			list.add(timeStamp); //2019-03-04 19:08:56.768 	타임트템프
//			list.add(orddt); //20190304 - 업로드 일자
//			list.add(ordseq); //1 - 차수
//			list.add(seq);	//2	- 주문인덱스

			if (i == 0) {
				System.out.println("[deleteEcountStocklog]" + pstmt.toString());
				i++;
			}

			pstmt.setString(1, list.get(4));
			pstmt.setString(2, list.get(5));
			pstmt.setString(3, list.get(6));
			pstmt.setString(4, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.addBatch();

			pstmt.clearParameters(); // 파라미터 초기화

		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

	}

	/**
	 * processEcountStocklog 트랜잭션그룹
	 * 
	 * @param connection
	 * @param pstmtlist
	 * @param contents
	 * @throws Exception
	 */
	public void insertEcountStocklog(Connection connection, List<PreparedStatement> pstmtlist,
			List<List<String>> contents) throws Exception {
		String sql = "insert into ECOUNTSTOCKLOG (compno, orddt, ordseq, seq, state, errcd, errdsc, errtime)  values (?, ?, ?, ?, ?, ?, ?, ?) ";

		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmtlist.add(pstmt);

		System.out.println("[insertEcountProdlog]" + pstmt.toString());

		for (List<String> list : contents) {
//			list.add(""+isSuccess);	//false
//			list.add(errorCod); //SaveSale_ERR
//			list.add(errorMsg); //[전표묶음1] 생산전표생성(Not Exist Data)
//			list.add(timeStamp); //2019-03-04 19:08:56.768 	타임트템프
//			list.add(orddt); //20190304 - 업로드 일자
//			list.add(ordseq); //1 - 차수
//			list.add(seq);	//2	- 주문인덱스
			if (list.get(0).equals("false")) {
				pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(2, list.get(4));
				pstmt.setString(3, list.get(5));
				pstmt.setString(4, list.get(6));
				pstmt.setString(5, list.get(0));
				pstmt.setString(6, list.get(1));
				pstmt.setString(7, list.get(2));
				pstmt.setString(8, list.get(3));

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

	}

	// 카카오톡
	public List<List<String>> sendKakao(Shell shell, List<List<String>> contents_target, String orddt, String ordseq,
			String template, boolean flag, int len) {
		// String test_profile_key = "89823b83f2182b1e229c2e95e21cf5e6301eed98";
		List<List<String>> responContents = new ArrayList<>();
		try {
			CompInfoDao comdao = new CompInfoDao();
			List<String> comlist = comdao.getCompNoImage();
			String userid = "kdjsystem";
			String real_profile_key = comlist.get(21);
			List<String> msg = new ArrayList<>();
			List<String> tel = new ArrayList<>();
			// String msg="";
			// String tel="";

			String url = "https://alimtalk-api.sweettracker.net/v2/" + real_profile_key + "/sendMessage";
			// String url = "https://dev-alimtalk-api.sweettracker.net/v2/" +
			// test_profile_key + "/sendMessage";

			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();

			AlimTalkChargeDao aldao = new AlimTalkChargeDao();
			List<String> allist = aldao.getAlimTalkChargeList(comlist.get(0));
			List<String> templatecode = new ArrayList<>();
			;
			if (!template.equals("sms")) {
				templatecode = aldao.getTemplateCodeList(comlist.get(0), template);
			}

			// add reuqest header
			httpConnection.setRequestMethod(REQ_METHOD);
			httpConnection.setRequestProperty("Accept", ACCEPT);
			httpConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
			httpConnection.setRequestProperty("userid", userid);
			// Send post request
			httpConnection.setDoOutput(true);
			List<String> suplist = new ArrayList<>();
			List<String> cumlist = new ArrayList<>();
			int seq = Integer.parseInt(allist.get(7));
			StringBuffer payload = new StringBuffer();
			int idx = 1;
			payload.append("[");
			for (int i = 0; i < contents_target.size(); i++) {
				List<String> list = contents_target.get(i);
				if (template.equals("sms")) {
					String num = String.format("%011d", seq + idx);
					msg.add(i, list.get(0));
					payload.append("{"); //
					payload.append("\"msgid\"").append(":\"").append(comlist.get(24) + num).append("\","); // 계정
					// payload.append("\"msgid\"").append(":\"").append(comlist.get(1)+orddt+ordseq+list.get(0)).append("\",");
					// //계정
					payload.append("\"profile_key\"").append(":\"").append(real_profile_key).append("\","); // 발신프로필키
					if (comlist.get(0).equals("1")) {
						payload.append("\"template_code\"").append(":\"").append("").append("\","); // 템플릿코드
					} else if (comlist.get(0).equals("2")) {
						payload.append("\"template_code\"").append(":\"").append("udt001").append("\","); // 템플릿코드
					} else {
						payload.append("\"template_code\"").append(":\"").append("kdj002").append("\","); // 템플릿코드
					}
					// payload.append("\"template_code\"").append(":\"").append("alimtalktest_001").append("\",");
					// //템플릿코드
					payload.append("\"receiver_num\"").append(":\"").append(splitMark(list.get(1))).append("\","); // 전화번호
					tel.add(i, list.get(1));
					// payload.append("\"receiver_num\"").append(":\"").append("01024426116").append("\",");
					// //전화번호
					payload.append("\"message\"").append(":\"").append(msg.get(i)).append("\","); // 메세지
					payload.append("\"reserved_time\"").append(":\"").append("00000000000000").append("\","); // 즉시전송
					payload.append("\"sms_message\"").append(":\"").append(msg.get(i)).append("\","); // 비즈메세지실패시문자대체메세지
					// payload.append("\"sms_message\"").append(":\"").append("\",");
					// //비즈메세지실패시문자대체메세지
					payload.append("\"sms_title\"").append(":\"").append("\","); //
					if (len < 90) {
						payload.append("\"sms_kind\"").append(":\"").append("S").append("\","); // 발송하지않음
					} else {
						payload.append("\"sms_kind\"").append(":\"").append("L").append("\","); // 발송하지않음
					}

				} else {
					String num = String.format("%011d", seq + idx);
					ProductMstDao mstdao = new ProductMstDao();
					ProductIforDao infodao = new ProductIforDao();
					List<String> infolist = new ArrayList<>();
					CustomerDao ctdao = new CustomerDao();
					if (list.size() > 2) {
						// true = 상담접수 flase = 상담처리,이카운트
						if (!flag) {
							if (template.equals("ord")) {
								infolist = mstdao.getProductMstList(list.get(23));
							} else {
								infolist = mstdao.getProductMstList(list.get(6));
							}

						} else {
							if (!list.get(15).equals("")) {
								infolist = mstdao.getProductMstList(list.get(15));
							} else {
								MessageDialog.openError(shell, "카카오톡전송", "상품코드가 없습니다.");
								return responContents;
							}
						}

						if (!infolist.get(24).equals("")) {
							suplist = infodao.getOpen(infolist.get(24));
						} else {
							MessageDialog.openError(shell, "카카오톡전송",
									"상품등록에 기본정보번호가 등록되어 있지 않습니다. 확인후에 다시 진행하여 주시기 바랍니다");
							return responContents;
						}
						if (!suplist.get(21).equals("")) {
							cumlist = ctdao.getCustomerInfoByCustno(splitMark(suplist.get(21)));
						} else {
							MessageDialog.openError(shell, "카카오톡전송", "사업자번호가 등록되어 있지 않습니다. 확인후에 다시 진행하여 주시기 바랍니다");
							return responContents;
						}
					}

					// 메시지
					// true = 상담접수 flase = 상담처리,이카운트
					if (comlist.get(0).equals("3")) {
						if (!flag) {
							if (template.equals("ord")) {
								msg.add(i,
										"하늘다원 이름으로 직발송 부탁 합니다.\r\n\r\n" + infolist.get(1) + " [" + list.get(7)
												+ "]개\r\n" + list.get(2) + "\r\n[" + list.get(3) + "]" + list.get(4)
												+ "\r\n연락처1:" + list.get(5) + "\r\n연락처2:" + list.get(6) + "\r\n배송메시지:"
												+ list.get(13));
							} else {
								if (list.size() > 2) {
									msg.add(i, list.get(20));
								} else {
									msg.add(i, list.get(0));
								}
							}
						} else {
							if (list.size() > 2) {
								msg.add(i, list.get(38));
							} else {
								msg.add(i, list.get(0));
							}
						}
					} else {
						if (!flag) {
							if (template.equals("ord")) {
								msg.add(i,
										"언더텐달러샵 이름으로 직발송 부탁 합니다.\r\n\r\n" + infolist.get(1) + " [" + list.get(7)
												+ "]개\r\n" + list.get(2) + "\r\n[" + list.get(3) + "]" + list.get(4)
												+ "\r\n연락처1:" + list.get(5) + "\r\n연락처2:" + list.get(6) + "\r\n배송메시지:"
												+ list.get(13));
							} else {
								if (list.size() > 2) {
									msg.add(i, list.get(20));
								} else {
									msg.add(i, list.get(0));
								}
							}
						} else {
							if (list.size() > 2) {
								msg.add(i, list.get(38));
							} else {
								msg.add(i, list.get(0));
							}
						}
					}
					// String msg = "하늘다원 이름으로 직발송 부탁 합니다.\r\n\r\n"+list.get(10)+"
					// "+list.get(7)+"개\r\n"+list.get(2)+"\r\n["+list.get(3)+"]"+list.get(4)+"\r\n연락처1:"+list.get(5)+"\r\n연락처2:"+list.get(6)+
					// "\r\n배송메시지:"+list.get(12);
					payload.append("{"); //
					payload.append("\"msgid\"").append(":\"").append(comlist.get(24) + num).append("\","); // 계정
					// payload.append("\"msgid\"").append(":\"").append(comlist.get(1)+orddt+ordseq+list.get(0)).append("\",");
					// //계정
					payload.append("\"profile_key\"").append(":\"").append(real_profile_key).append("\","); // 발신프로필키
					// payload.append("\"template_code\"").append(":\"").append("kdj002").append("\",");
					// //템플릿코드
					payload.append("\"template_code\"").append(":\"").append(templatecode.get(2)).append("\","); // 템플릿코드
					// payload.append("\"template_code\"").append(":\"").append("alimtalktest_001").append("\",");
					// //템플릿코드
					if (!flag) {
						if (template.equals("ord")) {
							payload.append("\"receiver_num\"").append(":\"").append(splitMark(cumlist.get(6)))
									.append("\","); // 전화번호
							tel.add(i, cumlist.get(6));
						} else {
							if (list.size() > 2) {
								payload.append("\"receiver_num\"").append(":\"").append(splitMark(list.get(21)))
										.append("\","); // 전화번호
								tel.add(i, list.get(21));
							} else {
								payload.append("\"receiver_num\"").append(":\"").append(splitMark(list.get(1)))
										.append("\","); // 전화번호
								tel.add(i, list.get(1));
							}
						}
					} else {
						if (list.size() > 2) {
							payload.append("\"receiver_num\"").append(":\"").append(splitMark(list.get(39)))
									.append("\","); // 전화번호
							tel.add(i, list.get(39));
						} else {
							payload.append("\"receiver_num\"").append(":\"").append(splitMark(list.get(1)))
									.append("\","); // 전화번호
							tel.add(i, list.get(1));
						}
					}
					// payload.append("\"receiver_num\"").append(":\"").append("01024426116").append("\",");
					// //전화번호
					payload.append("\"message\"").append(":\"").append(msg.get(i)).append("\","); // 메세지
					payload.append("\"reserved_time\"").append(":\"").append("00000000000000").append("\","); // 즉시전송
					payload.append("\"sms_message\"").append(":\"").append(msg.get(i)).append("\","); // 비즈메세지실패시문자대체메세지
					// payload.append("\"sms_message\"").append(":\"").append("\",");
					// //비즈메세지실패시문자대체메세지
					payload.append("\"sms_title\"").append(":\"").append("\","); //
					if (!template.equals("ord")) {
						if (len < 90) {
							payload.append("\"sms_kind\"").append(":\"").append("S").append("\","); // 발송하지않음
						} else {
							payload.append("\"sms_kind\"").append(":\"").append("L").append("\","); // 발송하지않음
						}
					} else {
						payload.append("\"sms_kind\"").append(":\"").append("N").append("\","); // 발송하지않음
					}
				}

				payload.append("\"sender_num\"").append(":\"").append("0326751102").append("\","); // 발신번호
				payload.append("\"parcel_company\"").append(":\"").append("08").append("\","); // 택배코드
				payload.append("\"parcel_invoice\"").append(":\"").append("").append("\""); // 송장

				payload.append("} ");

				if (i < contents_target.size() - 1) {
					payload.append(", ");
				}
				payload.append("\n"); // --지워야함
				idx++;
			}
			payload.append("] ");

			System.out.println(payload);

			DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
			out.write(payload.toString().getBytes("UTF-8"));
			out.flush();
			out.close();

			int responseCode = httpConnection.getResponseCode();

			System.out.println("카카오톡: " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
			String inputLine = null;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			httpConnection.disconnect();

			System.out.println(response.toString());

			JsonParser jsonParser = new JsonParser();
			JsonElement jsonObject = jsonParser.parse(response.toString());
			// String statusCode = jsonObject.getAsJsonObject().get("result").getAsString();
			// if(statusCode.equals("Y")) {
			String result;
			JsonElement dataObject = jsonObject.getAsJsonArray();
			for (int i = 0; i < dataObject.getAsJsonArray().size(); i++) {
				JsonElement jsonElement = dataObject.getAsJsonArray().get(i);
				result = jsonElement.getAsJsonObject().get("result").getAsString();
				if (result.equals("Y")) {
					// for (int i = 0; i < contents_target.size(); i++) {
					// JsonElement jsonElement = (JsonElement) jsonArray_result.get(i);
					String msgid = jsonElement.getAsJsonObject().get("msgid").getAsString();
					String code = jsonElement.getAsJsonObject().get("code").getAsString();
					String error = jsonElement.getAsJsonObject().get("error").getAsString();
					String kind = jsonElement.getAsJsonObject().get("kind").getAsString();
					String sendtime = jsonElement.getAsJsonObject().get("sendtime").getAsString();
					List<String> list = new ArrayList<>();
					list.add(result);
					list.add(msgid);
					list.add(code);
					list.add(error);
					list.add(kind);
					list.add(tel.get(i));
					list.add(msg.get(i));
					list.add(sendtime);

					responContents.add(list);
					// }
				} else {
					List<String> list = new ArrayList<>();
					list.add(jsonElement.getAsJsonObject().get("result").getAsString());
					list.add(jsonElement.getAsJsonObject().get("msgid").getAsString());
					list.add(jsonElement.getAsJsonObject().get("code").getAsString());
					list.add(jsonElement.getAsJsonObject().get("error").getAsString());
					list.add(jsonElement.getAsJsonObject().get("kind").getAsString());
					list.add(jsonElement.getAsJsonObject().get("sendtime").getAsString());

					responContents.add(list);
				}
			}

			// String result = dataObject.getAsJsonArray().get(0).getAsString();
			// JsonArray jsonArray_result = dataObject.getAsJsonArray();

		} catch (Exception e) {
			e.getMessage();
		}
		return responContents;
	}

	public String splitMark(String text) {
		String[] split = text.split("-| ");
		String complite = "";
		for (String element : split) {
			complite += element;
			complite = complite.trim();
		}
		return complite;
	}

}

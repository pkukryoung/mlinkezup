package com.kdj.mlink.ezup3.data.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.widgets.Shell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kdj.mlink.ezup3.common.YDMAProgressBar;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.shop.common.ChromeExtention;
import com.kdj.mlink.ezup3.shop.common.ChromeScript;

public class PickupExpressAPIDao {
	// 택배신청
	public String REQ_METHOD = "POST";
	public String ACCEPT = "application/json";
	public String CONTENT_TYPE = "application/json";
	public String CONTENT_TYPE_HEADEL = "application/json; charset=utf-8";
	ChromeExtention chrome = ChromeExtention.getInstace();
	ChromeDriver driver = null;

	public List<List<String>> sendPickupExpress(List<List<String>> contents, Shell shell, String exp) throws Exception {

		List<List<String>> responContents = new ArrayList<>();

		try {
			// String url = "http://receipt-dev.sweettracker.net/put_reception";
			String url = "https://receipt.sweettracker.net/put_reception";

			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			CompInfoDao dao = new CompInfoDao();
			List<String> complist = dao.getCompNoImage();
			OrderDao ordao = new OrderDao();
			List<String> expcd = ordao.getExpcd_Lott(exp);
			// add reuqest header
			httpConnection.setRequestMethod(REQ_METHOD);
			httpConnection.setRequestProperty("Accept", ACCEPT);
			httpConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
			// Send post request
			httpConnection.setDoOutput(true);

			AlimTalkChargeDao aldao = new AlimTalkChargeDao();
			List<String> allist = aldao.getAlimTalkChargeList(complist.get(0));
			StringBuffer payload = new StringBuffer();
			payload.append("{ \"tierCode\"").append(":\"").append("KDJSYSTEM").append("\","); // 고객사코드 필수
			payload.append("\"cipherType\"").append(":\"").append("0").append("\","); // 암호화타입
			payload.append("\"data\": [ ");
			payload.append("\n"); //
			for (int i = 0; i < contents.size(); i++) {
				payload.append("{"); //
				List<String> list = contents.get(i);
				payload.append("\"ordCde\"").append(":\"")
						.append(complist.get(24) + list.get(0) + list.get(1) + list.get(2)).append("\","); // 주문번호 필수
//				if(!list.get(38).equals("")) {
//					//payload.append("'comCode'").append(":'").append(list.get(38).substring(list.get(38).length()-2,list.get(38).length())).append("',"); //택배사코드 필수
//					payload.append("\"comCode\"").append(":\"").append("08").append("\","); //택배사코드 필수
//				} else{
//					MessageDialog.openInformation(shell, "택배사전송", "택배업체 코드가 없습니다. 입력후 다시 진행하여주시기 바랍니다.");
//				}
				payload.append("\"comCode\"").append(":\"").append(expcd.get(2)).append("\","); // 택배사코드 필수
				payload.append("\"addDat\"").append(":\"").append(splitMark(list.get(3))).append("\","); // 주문일자
				payload.append("\"cusCde\"").append(":\"").append("971786").append("\","); // 신용코드 필수
				payload.append("\"cusSub\"").append(":\"").append("\","); // 서브신용크드
				payload.append("\"sndNme\"").append(":\"").append(complist.get(1)).append("\","); // 송화인명 필수
				payload.append("\"sndZip\"").append(":\"").append(complist.get(13)).append("\","); // 송화인우편번호 필수
				payload.append("\"sndAd1\"").append(":\"").append(complist.get(14)).append("\","); // 송화인주소 필수
				payload.append("\"sndAd2\"").append(":\"").append("\","); // 송화인주소2
				payload.append("\"sndTel\"").append(":\"").append(complist.get(17)).append("\","); // 송화인전화번호 필수
				payload.append("\"sndMod\"").append(":\"").append("\","); // 송화인전화번호2
				payload.append("\"ownNme\"").append(":\"").append(list.get(4)).append("\","); // 수화인명 필수
				payload.append("\"ownZip\"").append(":\"").append(list.get(5)).append("\","); // 우편번호 필수
				payload.append("\"ownAd1\"").append(":\"").append(list.get(6)).append("\","); // 주소 필수

				payload.append("\"ownAd2\"").append(":\"").append("\","); // 주소2
				if (!list.get(7).equals("")) {
					payload.append("\"ownTel\"").append(":\"").append(splitMark(list.get(7))).append("\","); // 전화번호 필수
				} else {
					payload.append("\"ownTel\"").append(":\"").append(splitMark(list.get(8))).append("\","); // 전화번호 필수
				}
				payload.append("\"ownMod\"").append(":\"").append("\","); // 수화인전화전호
				payload.append("\"adMemo\"").append(":\"").append(list.get(13)).append("\","); // 배송메모
				if (list.get(11).equals("현불")) {
					payload.append("\"wipGbn\"").append(":\"").append("1").append("\","); // 운임구분 필수 1:선물 2:착불 3:신용
				} else if (list.get(11).equals("착불")) {
					payload.append("\"wipGbn\"").append(":\"").append("2").append("\","); // 운임구분 필수 1:선물 2:착불 3:신용
				} else {
					payload.append("\"wipGbn\"").append(":\"").append("3").append("\","); // 운임구분 필수 1:선물 2:착불 3:신용
				}
				payload.append("\"dsoGbn\"").append(":\"").append("2").append("\","); // 화물크기 필수 1:극소 2:소 3:중 4:대 5:극대
				payload.append("\"itmLst\"").append(":\"").append(list.get(12)).append("\",");// 상품명 필수
				payload.append("\"itmCnt\"").append(":\"").append(list.get(9)).append("\"");// 상품수량 필수
//				payload.append("'dsoWght'").append(":'").append("', "); //화물무게 우체국은 필수
//				payload.append("'comDivCde'").append(":'").append("', "); //점포코드(우체국필수)/협력사코드(대한통운필수)
//				payload.append("'itmOption'").append(":'").append("'"); //단품명 대한통운의경우 필요시입력

				payload.append("} ");

				if (i < contents.size() - 1) {
					payload.append(", ");
				}

				payload.append("\n"); // --지워야함
			}

			payload.append("]}");

			System.out.println(payload);

			DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
			out.write(payload.toString().getBytes("UTF-8"));
			out.flush();
			out.close();

			int responseCode = httpConnection.getResponseCode();

			System.out.println("택배사전송: " + responseCode);

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

			String statusCode = jsonObject.getAsJsonObject().get("result").getAsString();
			String ordCde = "";
			String stOrdCde = "";
			if (statusCode.equals("Y")) {

				JsonElement dataObject = jsonObject.getAsJsonObject().get("data");
				JsonArray jsonArray_result = dataObject.getAsJsonArray();

				for (int i = 0; i < jsonArray_result.size(); i++) {
					JsonElement jsonElement = jsonArray_result.get(i);
					String isSuccess = jsonElement.getAsJsonObject().get("result").getAsString();

					String errMsg = "";
					String errCde = "";
					List<String> list = new ArrayList<>();

					if (isSuccess.equals("N")) {
						// 판매등록의 경우는 Code null 임 처리시 오류남
						// errorCod = jsonElement.getAsJsonObject().get("Code").getAsString();
						errCde = jsonElement.getAsJsonObject().get("errCde").getAsString();
						errMsg = jsonElement.getAsJsonObject().get("errMsg").getAsString();
						list.add(errCde);
						list.add(errMsg);
					} else {
						ordCde = jsonElement.getAsJsonObject().get("ordCde").getAsString();
						stOrdCde = jsonElement.getAsJsonObject().get("stOrdCde").getAsString();
						list.add("" + isSuccess);
						list.add(ordCde);
						list.add(stOrdCde);
						list.add(errCde);
						list.add(errMsg);
						// --- 이후 주문테이블 key
						list.add(contents.get(i).get(0));
						list.add(contents.get(i).get(1));
						list.add(contents.get(i).get(2));
						list.add(expcd.get(0));
						list.add(expcd.get(1));
					}

					responContents.add(list);
				}

			} else {
				List<String> list = new ArrayList<>();
				list.add(jsonObject.getAsJsonObject().get("result").getAsString());
				list.add(ordCde);
				list.add(stOrdCde);
				list.add(jsonObject.getAsJsonObject().get("errCde").getAsString());
				list.add(jsonObject.getAsJsonObject().get("errMsg").getAsString());
				responContents.add(list);
			}
		} catch (Exception e) {
			e.getMessage();
		}

		return responContents;

	}

	// 송장번호 수신
	public List<List<String>> getPickupExpressReception(List<String> list2, Shell shell) {
		List<List<String>> responContents = new ArrayList<>();

		try {
			String url = "https://receipt.sweettracker.net/get_reception";

			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			CompInfoDao dao = new CompInfoDao();
			List<String> complist = dao.getCompNoImage();

			// add reuqest header
			httpConnection.setRequestMethod(REQ_METHOD);
			httpConnection.setRequestProperty("Accept", ACCEPT);
			httpConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
			// Send post request
			httpConnection.setDoOutput(true);
			AlimTalkChargeDao aldao = new AlimTalkChargeDao();
			List<String> allist = aldao.getAlimTalkChargeList(complist.get(0));

			StringBuffer payload = new StringBuffer();

			payload.append("{ \"tierCode\"").append(":\"").append("KDJSYSTEM").append("\","); // 고객사코드 필수
			payload.append("\"cipherType\"").append(":\"").append("0").append("\","); // 암호화타입
			// payload.append("\"ordCde\"").append(":\"").append(allist.get(1)+list.get(0)+list.get(1)+list.get(2)).append("\"");
			payload.append("\"ordCde\"").append(":\"")
					.append(complist.get(24) + list2.get(0) + list2.get(1) + list2.get(2)).append("\"");
			payload.append("} ");

			System.out.println(payload);

			DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
			out.write(payload.toString().getBytes("UTF-8"));
			out.flush();
			out.close();

			int responseCode = httpConnection.getResponseCode();

			System.out.println("택배사송장수신: " + responseCode);

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

			String statusCode = jsonObject.getAsJsonObject().get("result").getAsString();
			if (statusCode.equals("Y")) {

				JsonElement dataObject = jsonObject.getAsJsonObject().get("data");
				String result = jsonObject.getAsJsonObject().get("result").getAsString();
				JsonArray jsonArray_result = dataObject.getAsJsonArray();

				for (int i = 0; i < jsonArray_result.size(); i++) {
					JsonElement jsonElement = jsonArray_result.get(i);

					String ownAd2 = jsonElement.getAsJsonObject().get("ownAd2").getAsString();
					String ownAd1 = jsonElement.getAsJsonObject().get("ownAd1").getAsString();
					String sndZip = jsonElement.getAsJsonObject().get("sndZip").getAsString();
					String addDat = jsonElement.getAsJsonObject().get("addDat").getAsString();
					String invoice = jsonElement.getAsJsonObject().get("invoice") == null ? ""
							: jsonElement.getAsJsonObject().get("invoice").getAsString();
					String sndMod = jsonElement.getAsJsonObject().get("sndMod").getAsString();
					String sndNme = jsonElement.getAsJsonObject().get("sndNme").getAsString();
					String sndTel = jsonElement.getAsJsonObject().get("sndTel").getAsString();
					String itmLst = jsonElement.getAsJsonObject().get("itmLst").getAsString();
					String ownNme = jsonElement.getAsJsonObject().get("ownNme").getAsString();
					String ownZip = jsonElement.getAsJsonObject().get("ownZip").getAsString();
					String ownMod = jsonElement.getAsJsonObject().get("ownMod").getAsString();
					String adMemo = jsonElement.getAsJsonObject().get("adMemo").getAsString();
					String wipGbn = jsonElement.getAsJsonObject().get("wipGbn").getAsString();
					String comCode = jsonElement.getAsJsonObject().get("comCode").getAsString();
					String sndAd1 = jsonElement.getAsJsonObject().get("sndAd1").getAsString();
					String ordCde = jsonElement.getAsJsonObject().get("ordCde").getAsString();
					String trnYn = jsonElement.getAsJsonObject().get("trnYn").getAsString();
					String ownTel = jsonElement.getAsJsonObject().get("ownTel").getAsString();
					String dsoGbn = jsonElement.getAsJsonObject().get("dsoGbn").getAsString();
					String sndAd2 = jsonElement.getAsJsonObject().get("sndAd2").getAsString();

					List<String> list = new ArrayList<>();
					list.add(result);
					list.add(ownAd2);
					list.add(ownAd1);
					list.add(sndZip);
					list.add(addDat);
					list.add(invoice);
					list.add(sndMod);
					list.add(sndNme);
					list.add(sndTel);
					list.add(itmLst);
					list.add(invoice);
					list.add(ownNme);
					list.add(ownZip);
					list.add(ownMod);
					list.add(adMemo);
					list.add(wipGbn);
					list.add(comCode);
					list.add(sndAd1);
					list.add(ordCde);
					list.add(trnYn);
					list.add(ownTel);
					list.add(dsoGbn);
					list.add(sndAd2);
					// --- 이후 주문테이블 key
					list.add(list2.get(0));
					list.add(list2.get(1));
					list.add(list2.get(2));

					responContents.add(list);
				}

			} else {
				List<String> list = new ArrayList<>();
				list.add(jsonObject.getAsJsonObject().get("result").getAsString());
				list.add(jsonObject.getAsJsonObject().get("tierCode").getAsString());
				list.add(jsonObject.getAsJsonObject().get("errMsg").getAsString());
				list.add(jsonObject.getAsJsonObject().get("errCde").getAsString());
				list.add(jsonObject.getAsJsonObject().get("ordCde").getAsString());
				responContents.add(list);
			}
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

	public void getPickupExpressInvnoUpdate(List<String> list) throws Exception {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "update pickupexp set INVNO = ?, EXPRECVDT = ? " + " where ORDDT = ? " + "   and ORDSEQ =? "
					+ "   and seq = ? " + "   and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, list.get(3));
			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
			pstmt.setString(++i, list.get(0));
			pstmt.setString(++i, list.get(1));
			pstmt.setString(++i, list.get(2));
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
//			pstmt.setString(++i, list.get(16).equals("08")?"롯데택배_002":"기타");
//			pstmt.setString(++i, list.get(5));
//			pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
//			pstmt.setString(++i, list.get(18).substring(2, 10));
//			pstmt.setString(++i, list.get(18).substring(10, 11));
//			pstmt.setString(++i, list.get(18).substring(11,list.get(18).length()));
//			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

			pstmt.executeUpdate();

			System.out.println("[getPickupExpressInvnoUpdate]" + pstmt.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	// 롯데택배택배전송
	public void setExpressSendDT(List<List<String>> sendResult) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();
			// for(int k =1;k<=list.size()-1;k++) {
			String sql = "update pickupexp set EXPSENDDT=?, expnm = ? " + " where orddt=? " + "   and ordseq = ? "
					+ "   and seq = ? " + "   and compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (List<String> list : sendResult) {
				int i = 0;
				pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++i, list.get(4) + "_" + list.get(3));
				pstmt.setString(++i, list.get(0));
				pstmt.setString(++i, list.get(1));
				pstmt.setString(++i, list.get(2));
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
//				if(list.size()>2) {
//					pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
//					pstmt.setString(++i, list.get(9)+"_"+list.get(8));
//					pstmt.setString(++i, list.get(5));
//					pstmt.setString(++i, list.get(6));
//					pstmt.setString(++i, list.get(7));
//					pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.addBatch();
				pstmt.clearParameters();
//				}

				// pstmt.executeUpdate();
			}
			System.out.println("[setExpressSendDT]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	// 천일택배전송
	public void setExpressSendDTAndInvoiceUpdate(List<List<String>> sendResult) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();
			// for(int k =1;k<=list.size()-1;k++) {
			String sql = "update pickupexp set EXPSENDDT=?, expnm = ?, invno = ?, exprecvdt = ? " + " where orddt=? "
					+ "   and ordseq = ? " + "   and seq = ? " + "   and compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (List<String> list : sendResult) {
				int i = 0;
				if (list.size() > 2) {
					pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
					pstmt.setString(++i, list.get(4) + "_" + list.get(3));
					pstmt.setString(++i, list.get(5));
					pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
					pstmt.setString(++i, list.get(0));
					pstmt.setString(++i, list.get(1));
					pstmt.setString(++i, list.get(2));
					pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
					pstmt.addBatch();
					pstmt.clearParameters();
				}

				// pstmt.executeUpdate();
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

	// 이동할때저장
	public void setMoveList(List<List<String>> contents, String menu) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

//			String sql = "update TEST01 set aa=? ";
			String sql = "update pickupexp set EXPFILE=?, expcd =? where orddt=? and ordseq = ? and seq = ? and compno = ? ";
			String exp = "";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			for (List<String> list : contents) {
				int i = 0;
//				pstmt.setString(++i, "공용");
//				byte [] asciistr2 = "공용".getBytes("KSC5601");
//				String menu2 = new String(asciistr2,"KSC5601");
//				pstmt.setString(++i, menu2);	

				if (menu.equals("대신택배") || menu.equals("택배비초과") || menu.equals("롯데(3500원미만)") || menu.equals("천일택배")) {
					pstmt.setString(++i, "합동택배");
				} else if (menu.equals("롯데택배")) {
					pstmt.setString(++i, "롯데택배");
				} else if (menu.equals("한진택배")) {
					pstmt.setString(++i, "한진택배");
				} else if (menu.equals("경동택배")) {
					pstmt.setString(++i, "경동택배");
				} else {
					pstmt.setString(++i, "공용");
				}

				if (menu.equals("롯데(3500원미만)")) {
					exp = "롯데택배";
				} else {
					exp = menu;
				}
				pstmt.setString(++i, exp);
				pstmt.setString(++i, list.get(0));
				pstmt.setString(++i, list.get(1));
				pstmt.setString(++i, list.get(2));
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

//				pstmt.executeUpdate();
				pstmt.addBatch();
				pstmt.clearParameters();
//				

				// pstmt.executeUpdate();
			}
			System.out.println("[setMoveList]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

	public List<List<String>> sendPickupChunilExpress(List<List<String>> cloneContents, String orddt,
			List<String> comlist) {
		List<List<String>> contents = new ArrayList<>();

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@121.172.114.136:1521:TAEKBAE";
		String user = "yd";
		String password = "chunilps1001";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		java.sql.CallableStatement cstmt = null;
		try {
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
			con = DriverManager.getConnection(url, user, password);
			int num = 1;
			for (List<String> list : cloneContents) {
				long seq = Long.parseLong(list.get(0) + list.get(1) + list.get(2));
				if (list.get(46).equals("X")) {
					seq = Long.parseLong(YDMATimeUtil.getCurrentTime()) + (num++);
				}
				String prodnm = "";
				if (list.get(12).length() > 99) {
					prodnm = list.get(12).substring(0, 100);
				} else {
					prodnm = list.get(12);
				}
				int code = getItemCode(list.get(10));
				cstmt = con.prepareCall(
						"{call NTB.INS_API(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
				cstmt.setLong(1, seq);// seq근데 자동증가라 써있음
				cstmt.setString(2, orddt);// 접수일
				cstmt.setString(3, "KG39");// 영업소코드
				cstmt.setString(4, "KG390011");// 거래처코드
				cstmt.setString(5, list.get(4));// 수화주명
				cstmt.setString(6, list.get(5));// 우편번호
				cstmt.setString(7, list.get(6));// 주소
				cstmt.setString(8, "");// 주소2
				cstmt.setString(9, list.get(7));// 전화번호1
				cstmt.setString(10, list.get(8));// 전화번호2
				cstmt.setString(11, comlist.get(1));// 발화주명
				cstmt.setString(12, comlist.get(14));// 발화주주소
				cstmt.setString(13, "");// 발화주주소2
				cstmt.setString(14, comlist.get(17));// 발화주전화번호1
				cstmt.setString(15, "");// 발화주전화번호2
				cstmt.setString(16, "KG" + code);// 품목코드
				cstmt.setString(17, prodnm);// 품목명
				cstmt.setInt(18, Integer.parseInt(list.get(9)));// 수량
				cstmt.setString(19, list.get(11).equals("현불") ? "1" : "2");// 운임구분
				cstmt.setString(20, list.get(15).equals("택배") ? "2" : "1");// 배달구분
				cstmt.setString(21, list.get(13));// 비고(메세지)
				cstmt.setString(22, "");// 오더번호
				cstmt.setString(23, YDMATimeUtil.getCurrentTimeByYDFormat());// 등록일시
				cstmt.setString(24, "13");// 연계차수
				cstmt.registerOutParameter(25, java.sql.Types.VARCHAR);
				cstmt.execute();

				list.add(0, cstmt.getString(25));

				contents.add(list);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오라클 연결 실패");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {

				}

			}
			if (cstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {

				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {

				}
			}
		}

		return contents;
	}

	// 품목코드 가지고 오기
	private int getItemCode(String delvcost) {
		int code = 0;
		switch (delvcost) {
		case "3500":
			code = 390011001;
			break;
		case "4000":
			code = 390011002;
			break;
		case "4500":
			code = 390011003;
			break;
		case "5000":
			code = 390011004;
			break;
		case "5500":
			code = 390011005;
			break;
		case "6000":
			code = 390011006;
			break;
		case "7000":
			code = 390011007;
			break;
		case "7500":
			code = 390011008;
			break;
		case "8000":
			code = 390011009;
			break;
		case "9000":
			code = 390011010;
			break;
		case "10000":
			code = 390011011;
			break;
		case "11000":
			code = 390011012;
			break;
		case "12000":
			code = 390011013;
			break;
		case "14000":
			code = 390011014;
			break;
		case "20000":
			code = 390011015;
			break;
		case "25000":
			code = 390011016;
			break;
		default:
			code = 0;
		}
		return code;
	}

	public List<List<String>> sendPickupExpressCrawl(List<List<String>> contents, Shell shell, String exp,
			List<String> addr) {

		List<List<String>> express = new ArrayList<>();
		ChromeExtention chrome = ChromeExtention.getInstace();
		ChromeDriver driver = null;
		int count = 1;
		try {
			OrderDao ordao = new OrderDao();
			List<String> expcd = ordao.getExpcd_Lott(exp);
			List<String> expidNpass = ordao.getDvlInfo(expcd.get(0));
			driver = chrome.setFileDown(false).setHeadlessMode(true).getDriver();

			String URL = expidNpass.get(1);
			// driver.executeScript("window.open();",null );
			WebDriverWait wait = new WebDriverWait(driver, 30);
			driver.get(URL);

			Thread.sleep(3000);
			try {
				WebElement elementloginBox = driver.findElement(By.xpath("/html/body/div[3]/div[2]"));
				elementloginBox.findElement(By.xpath("//*[@id=\"principal\"]/input")).sendKeys(expidNpass.get(2));
				elementloginBox.findElement(By.xpath("//*[@id=\"credential\"]/input")).sendKeys(expidNpass.get(3));
				elementloginBox.findElement(By.xpath("//*[@id=\"btn-login\"]")).click();
			} catch (Exception e) {
				YDMAProgressBar.get().setValue("로그인시에 오류가 발생하였습니다. 다시 시도 하여 주시기 바랍니다.", 0);
				throw new Exception(e.getMessage());
			}
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/header/nav/div[1]")));
			// 거래처관리
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[3]/header/nav/div[1]/div[3]/i-button"))).click();
			Thread.sleep(1000);
			// 건별접수
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/div[3]/header/nav/div[3]/div/div[2]/div[2]/div[2]/ul/li[1]/a"))).click();
			// elementsearchBox.findElement(By.xpath("/html/body/div[3]/header/nav/div[3]/div/div[2]/div[2]/div[2]/ul/li[2]/a")).click();

			driver.switchTo().frame(driver.findElement(By.id("workframe_10781")));
			Thread.sleep(5000);

			for (int i = 0; i < contents.size(); i++) {
				try {

					List<String> list = contents.get(i);

					String name = list.get(4);
					if (list.get(7).equals("") && list.get(8).equals("")) {
						throw new Exception("고객명 : " + name + "님의 연락처가 없어 등록 하실수 없습니다.");
					}
					if (list.get(4).equals("")) {
						throw new Exception("No. : " + list.get(2) + "의 고객명이 없어 등록 하실수 없습니다.");
					}
					if (list.get(12).equals("")) {
						throw new Exception("고객명 : " + name + "님의 배송상품명이 없어 등록 하실수 없습니다.");
					}
					if (list.get(9).equals("") || list.get(9).equals("0")) {
						throw new Exception("고객명 : " + name + "발송 수량이 없어 등록 하실수 없습니다.");
					}
					if (list.get(10).equals("") || list.get(10).equals("0")) {
						throw new Exception("고객명 : " + name + "님의 배송비용이 없어 등록 하실수 없습니다.");
					}
					List<String> output = new ArrayList<>();
					Thread.sleep(1000);
					StringBuffer sbInput = new StringBuffer();
//                	sbInput.append( "(()=>{"  );
//                	sbInput.append( String.format("$('#edtAcperTel').val('%s'); ",list.get(7).equals("")?list.get(8):list.get(7)));
//                	sbInput.append( String.format("$('#edtAcperNm').val('%s'); ",list.get(4)));
//                	sbInput.append("})();");
//                	driver.executeScript(sbInput.toString(), "");
					driver.executeScript(String.format("$('#edtAcperTel').val('%s'); ",
							list.get(7).equals("") ? list.get(8) : list.get(7)), "");
					driver.executeScript(String.format("$('#edtAcperNm').val('%s'); ", list.get(4)), "");
					// 주소
					// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btnAcperBadr"))).sendKeys(Keys.ENTER);
					driver.executeScript("$('#btnAcperBadr').click(); ", "");
					Thread.sleep(1000);
					String strExcute = "(()=>{ "
							+ String.format("$('#edtSrchAdrMultiPopGrid').val('%s'); \r\n", addr.get(i))
							+ "$('#btnAdrMultiSrchGrid').click();  \r\n" + " setTimeout( ()=>{ \r\n "
							+ "    if(dsAdrMultiPopGrid.getRowCount() > 0) \r\n" + "    { "
							+ String.format(
									"dsAdrMultiPopGrid.setRowPosition(dsAdrMultiPopGrid.getRows().findIndex((p)=>p.basAreaNo === '%s'  || p.zipcd === '%s')); \r\n ",
									list.get(5), list.get(5))
							+ "$.popCallBack.popupCallBack_fn(dsAdrMultiPopGrid.getRow(dsAdrMultiPopGrid.getRowPosition()));  \r\n"
//                	+ "dsAdrMultiPopGrid.clearData(); \r\n"
//                	+ "$('body > div:nth-child(34) > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1) > span').trigger('click');"
							+ "}" + "},500);" + "})();";

					driver.executeScript(strExcute, "");
					Thread.sleep(1000);

					driver.executeScript(
							"document.querySelector(\"body > div:nth-child(35) > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1) > span\").click()");

					String result = (ChromeScript.get().waitTiem(2000).returnCallbackScripter(
							"return document.querySelector(\"body > div:nth-child(35)\").style.display", driver));

					if (result.equals("block")) {
//                		throw new Exception("고객명 : "+name+"님의 우편번호와 동일한게 없어 찾지 못하여 오류가 발생하였습니다.");
						// 테스트필요..우편번호 오류시 그리드의 첫번째것을 선택하도록 하는 코드 추가함 3/4 mac
						String strExcute2 = "(()=>{ "
								+ String.format("$('#edtSrchAdrMultiPopGrid').val('%s'); \r\n", addr.get(i))
								+ "$('#btnAdrMultiSrchGrid').click();  \r\n" + " setTimeout( ()=>{ \r\n "
								+ "    if(dsAdrMultiPopGrid.getRowCount() > 0) \r\n" + "    { \r\n"
								+ "$.popCallBack.popupCallBack_fn(dsAdrMultiPopGrid.getRow(0)); \r\n" + "}" + "},500);"
								+ "})();";
						driver.executeScript(strExcute2, "");
						Thread.sleep(1000);
						driver.executeScript(
								"document.querySelector(\"body > div:nth-child(35) > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1) > span\").click()");

					}

					Thread.sleep(1000);
					driver.executeScript(String.format("$('#edtAcperEtcAdr').val('%s'); ",
							list.get(6).substring(addr.get(i).length(), list.get(6).length())), "");

					// 상품명출력시 ERP상품코드를 포함 출력.
					ProductMstDao mdao = new ProductMstDao();
//                	List<String> mstlist = mdao.getProductMstList(contents.get(i).get(29));
//            		String ecountcd = mstlist.get(3).equals("")?"":" ("+mstlist.get(3)+")";
					String ecountcd = " (";
					List<List<String>> mstlist = mdao.getERPCodeList(contents.get(i).get(0), contents.get(i).get(1),
							contents.get(i).get(2));
					for (int e = 0; e < mstlist.size(); e++) {
						ecountcd = ecountcd + mstlist.get(e).get(0);
						if (e + 1 < mstlist.size()) {
							ecountcd = ecountcd + ",";
						}
					}
					ecountcd = ecountcd + " )";
					driver.executeScript(String.format("$('#edtGdsNm').val('%s');  ", list.get(12) + ecountcd), "");

					driver.executeScript(String.format("$('#edtDlvMsgCont').val('%s');", list.get(13)), "");
//                	driver.executeScript(String.format("$('#maeQty').val('%s'); ",list.get(9)), "");
					Thread.sleep(1000);
					// 운임구분
					if (list.get(11).equals("착불")) {
						driver.executeScript("$('#cboFareSctCd').val('02')", "");
					} else {
						driver.executeScript("$('#cboFareSctCd').val('03')", "");
					}
					Thread.sleep(1000);
					//
					// 배송비
					driver.executeScript(String.format("$('#maeDlvFare').val('%s')", list.get(10)), "");
					// elementbodyBox.findElement(By.xpath("//*[@id=\"maeDlvFare\"]/input")).sendKeys(list.get(10));
					Thread.sleep(1000);
					driver.executeScript("fnSave()", "");
					Thread.sleep(1000);
					driver.executeScript("$('.msgButton').click()", "");
					Thread.sleep(1500);
					driver.executeScript("$('.msgButton').click()", "");
					// 저장
					// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div[1]/div[2]/div/div[1]/i-button"))).click();
					// 저장확인
					// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[11]/div[2]/div[2]/input[1]"))).click();
					// 저장확인
					// Thread.sleep(1000);
					// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[11]/div[2]/div[2]/input"))).click();
					output.add(list.get(0));
					output.add(list.get(1));
					output.add(list.get(2));
					output.add(expcd.get(0));// 택배코드
					output.add(expcd.get(1));// 택배이름

					express.add(output);
					Thread.sleep(1000);
					YDMAProgressBar.get().setValue("고객명 : " + list.get(4) + "님의 택배 접수가 정상적으로 되었습니다.", count++);
				} catch (Exception e) {
					YDMAProgressBar.get().setValue(e.getMessage(), count++);
					continue;
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			driver.quit();
		}
		return express;
	}

	// 삭제하기
	public int[] deletePickupList(List<List<String>> contents) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		int[] result;
		try {
			String sql = "DELETE FROM pickupexp where compno=? and orddt=? and ordseq = ? and seq = ? ";
			sql = sql.toUpperCase();
			connection = DBCPInit.getInstance().getConnection();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			for (List<String> list : contents) {
				int i = 0;
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++i, list.get(0));
				pstmt.setString(++i, list.get(1));
				pstmt.setString(++i, list.get(2));

				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}
			System.out.println("[ProductErrordataDelete]" + pstmt.toString());
			result = pstmt.executeBatch();
			pstmt.clearBatch();// Batch 초기화
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return result;

	}

	// 네이버에서 값가져오기
	public List<String> getNaverAddress(List<List<String>> contents, Shell shell, String exp) {
		List<String> list = new ArrayList<>();
		ChromeExtention chrome = ChromeExtention.getInstace();
		ChromeDriver driver = null;
		try {
			driver = chrome.setFileDown(false).setHeadlessMode(true).getDriver();

			// TODO Auto-generated method stub
			String URL = "https://search.naver.com/search.naver";
			driver.get(URL);
			// driver.executeScript("window.open();",null );
			WebDriverWait wait = new WebDriverWait(driver, 10);

			for (List<String> addr : contents) {
				String addrerr = String.format("고객명 : %s ", addr.get(4));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nx_query\"]"))).clear();
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nx_query\"]")))
							.sendKeys(addr.get(6));
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//*[@id=\"nx_search_form\"]/fieldset/button"))).click();
					String address = wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//*[@id=\"lcs_region_section\"]/div[2]/div[2]/div[1]/h3")))
							.getText();
					list.add(address);
					YDMAProgressBar.get().setValue(addrerr + "님의 주소[" + address + "] 를 가지고 왔습니다.", 0);
				} catch (Exception e) {
					YDMAProgressBar.get().setValue(addrerr + "님의 주소를 가지고오는데 오류가 발생하였습니다.", 0);
					continue;
				}
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			driver.quit();
		}
		return list;
	}

	public int getPickupListMaxSeq(String orddt, String seq) throws Exception {

		int count = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT max(seq)  FROM pickupexp where compno = ? and orddt=? and ordseq=? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, orddt);
			pstmt.setString(3, seq);

			System.out.println("[checkEcountSentOrder]" + pstmt.toString());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return count + 1;
	}

	// 송장값가져오기
	public List<List<String>> getPickupExpressCrawl(List<List<String>> contents, Shell shell, String exp) {
		List<List<String>> express = new ArrayList<>();
		int count = 1;
		try {
			OrderDao ordao = new OrderDao();
			List<String> expcd = ordao.getExpcd_Lott(exp);
			List<String> expidNpass = ordao.getDvlInfo(expcd.get(0));
			driver = chrome.setFileDown(false).setHeadlessMode(true).getDriver();

			// TODO Auto-generated method stub
			String URL = expidNpass.get(1);
			// driver.executeScript("window.open();",null );
			WebDriverWait wait = new WebDriverWait(driver, 30);
			ChromeScript.get().get(driver, URL);
			Thread.sleep(1000);
			try {
				WebElement elementloginBox = driver.findElement(By.xpath("/html/body/div[3]/div[2]"));
				elementloginBox.findElement(By.xpath("//*[@id=\"principal\"]/input")).sendKeys(expidNpass.get(2));
				elementloginBox.findElement(By.xpath("//*[@id=\"credential\"]/input")).sendKeys(expidNpass.get(3));
				elementloginBox.findElement(By.xpath("//*[@id=\"btn-login\"]")).click();
			} catch (Exception e) {
				YDMAProgressBar.get().setValue("로그인시에 오류가 발생하였습니다. 다시 시도 하여 주시기 바랍니다.", 0);
				throw new Exception(e.getMessage());
			}
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/header/nav/div[1]")));
			// 집배달
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[3]/header/nav/div[1]/div[5]/i-button"))).click();
			Thread.sleep(1000);
			// 통합관리운송장출력
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html/body/div[3]/header/nav/div[3]/div/div[2]/div[4]/div[1]/ul/li[1]/a"))).click();
			driver.switchTo().frame(driver.findElement(By.id("workframe_21847")));
			// ChromeScript.get().until(driver,()->
			// (driver.findElementByCssSelector("workframe_21847");
			for (int i = 0; i < contents.size(); i++) {
				try {
					// Thread.sleep(2000);
					List<String> output = contents.get(i);
					List<String> list = new ArrayList<>();
					// ChromeScript.get().until(driver,()->
					// ((ChromeDriver)driver).findElementByCssSelector(".searchbox iframe"));
					// driver.executeScript("$('#cboSrchPrntSctCd').val('');","");
					ChromeScript.get().addScript("$('#cboSrchPrntSctCd').val('');").waitTiem(2000)
							.executeScripter(driver);
					// driver.executeScript("$('#cboSrchCond').val('40');","");
					ChromeScript.get().addScript("$('#cboSrchCond').val('40');").waitTiem(2000).executeScripter(driver);
					// driver.executeScript(String.format("$('#edtSrchCondVal').val('%s');
					// ",output.get(7).equals("")?output.get(8):output.get(7)),"");
					ChromeScript.get()
							.addScript(String.format("$('#edtSrchCondVal').val('%s'); ",
									output.get(7).equals("") ? output.get(8) : output.get(7)))
							.waitTiem(2000).executeScripter(driver);
					// driver.executeScript("fnBtnSearch();","");
					// ChromeScript.get().addScript(String.format("fnBtnSearch();")).waitTiem(2000).executeScripter(driver);
					ChromeScript.get().until(driver, () -> driver.findElementByCssSelector(
							"body > div.container-fluid.frameArea > div.frameHeader > div.panelBox.searchBox.detailSearch > div.searchBtnGroup > i-button.funcBtn.ico.iconSearch.White.searchBtn"))
							.click();
					// 검색
					// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div[2]/div[3]/i-button[2]"))).click();
					// 글가지고오기
					Thread.sleep(1000);
					// String invoice = (String)driver.executeScript("return
					// grdRsrv.getRowData(0)[8];","");
					try {
						String invoice = ChromeScript.get().returnCallbackScripter("return grdRsrv.getRowData(0)[8];",
								driver);
						if (invoice.equals("")) {
							throw new Exception(output.get(4) + "님의 송장번호를 가지고 오지 못하였습니다.");
						} else {
							System.out.println(invoice);
							list.add(output.get(0));
							list.add(output.get(1));
							list.add(output.get(2));
							list.add(invoice);
							list.add("Y");
							express.add(list);
							YDMAProgressBar.get().setValue(output.get(4) + "님의 송장번호[" + invoice + "]를 가지고 왔습니다.",
									count++);
						}
					} catch (Exception e) {
						throw new Exception(output.get(4) + "님의 휴대번호를 검색된 값을 찾지 못하였습니다.");
					}
				} catch (Exception e) {
					YDMAProgressBar.get().setValue(e.getMessage(), count++);
					continue;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			driver.quit();
		}
		return express;
	}

	public List<List<String>> getCategoryCodeSearch(String shopid) throws Exception {
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT IFNULL(A.CODE,''),IFNULL(A.SHOPCD,''),IFNULL(A.SHOPCATNO,'') FROM CATEGLGMAP A WHERE A.compno = ? AND A.shopcd = ? UNION ALL "
					+ " SELECT IFNULL(B.CODE,''),IFNULL(B.SHOPCD,''),IFNULL(B.SHOPCATNO,'') FROM CATEGMDMAP B WHERE B.compno = ? AND B.shopcd = ? UNION ALL "
					+ " SELECT IFNULL(C.CODE,''),IFNULL(C.SHOPCD,''), IFNULL(C.SHOPCATNO,'') FROM CATEGSMMAP C WHERE C.compno = ? AND C.shopcd = ? UNION ALL "
					+ " SELECT IFNULL(D.CODE,''),IFNULL(D.SHOPCD,'') ,IFNULL(D.SHOPCATNO,'') FROM CATEGDTLMAP D WHERE D.compno = ? AND D.shopcd = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopid);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(4, shopid);
			pstmt.setString(5, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(6, shopid);
			pstmt.setString(7, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(8, shopid);
			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				int columnIndex = 0;
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

	public List<String> getShopCateInfo(String shopcd, String shopid, String catno) throws Exception {
		List<String> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select ifnull(COMPNO,''),ifnull(SHOPCD,''),ifnull(SHOPCATNO,''),ifnull(SHOPCATNM,''),ifnull(SHOPLAGCATCD,''),ifnull(SHOPMIDCATCD,''),ifnull(SHOPSMLCATCD,''),ifnull(SHOPDETCATCD,''),"
					+ " ifnull(SERVICEPROD,''),ifnull(USE_YN,''),ifnull(SHOPGENERAL,'') ,ifnull(SHOPID,'') "
					+ " FROM shopcatinf WHERE compno = ? and shopcd = ? and shopid = ? and SHOPCATNO = ?";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setString(2, shopcd);
			pstmt.setString(3, shopid);
			pstmt.setString(4, catno);
			System.out.println("[getShopMstSelectList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
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

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}

	// 상품권변경하고 삭제하기
	public void removeGongyongFile(List<List<String>> search) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "delete from pickupexp " + " where ordseq = ? AND Orddt = ? and compno = ? and expfile = '공용'";
			sql = sql.toUpperCase();
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (int i = 0; i < search.size(); i++) {

				pstmt.setString(1, search.get(0).get(1));
				pstmt.setString(2, search.get(0).get(0));
				pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				pstmt.clearParameters();

			}
			System.out.println("[removeGongyongFile]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
	}

	public void InsertGongyongFile(List<List<String>> search) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "insert into pickupexp(COMPNO,ORDDT,ORDSEQ,SEQ,RORDDT,RCVNAM,PSTNO,ADDR,CLPNO,TELNO,QTY,SHPFEE,CREDIT,EXPPRODNM,MESSGE,PKGCLSS,SHIPCLS,SABORDNO,SHOPID,ORDNM,ETCMSG,MALLCD,ORDAMT,"
					+ " PRODCD,OPTDESC,EXPBUNDNM,BOXCNT,EXPFILE,EXPCD,FLAGSET,PRODCDM,EXPINVQTY,PRODNM,SPECDES,QTY2,RACKNM,OPTPRODCD,EXPINNER,EXPCOSTNM,EXPNM,INVNO,EXPSENDDT,EXPRECVDT) "
					+ " values(?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? , ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? , ?, ? )";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			// pstmt = connection.prepareStatement(sql);
			for (int i = 0; i < search.size(); i++) {
				int k = 0;
				int idx = 0;
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));
				pstmt.setString(++idx, search.get(i).get(k++));

				pstmt.addBatch();
				pstmt.clearParameters();

			}
			System.out.println("[InsertGongyongFile]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	public void setExpressPrice(List<List<String>> changeprice) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

//			String sql = "update TEST01 set aa=? ";
			String sql = "update pickupexp set CREDIT=? where orddt=? and ordseq = ? and seq = ? and compno = ? ";
			String exp = "";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			for (List<String> list : changeprice) {
				int i = 0;

				pstmt.setString(++i, list.get(11));
				pstmt.setString(++i, list.get(0));
				pstmt.setString(++i, list.get(1));
				pstmt.setString(++i, list.get(2));
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				pstmt.clearParameters();

			}
			System.out.println("[setMoveList]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	public void setDaesinHapPojang(List<String> list) throws Exception {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "update pickupexp set QTY = ?, SHPFEE = ?, EXPPRODNM = ? where ORDDT = ? and ORDSEQ =? and seq = ? and compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);

			int i = 0;
			pstmt.setString(++i, list.get(9));
			pstmt.setString(++i, list.get(10));
			pstmt.setString(++i, list.get(12));
			pstmt.setString(++i, list.get(0));
			pstmt.setString(++i, list.get(1));
			pstmt.setString(++i, list.get(2));
			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());

			pstmt.executeUpdate();

			System.out.println("[getPickupExpressInvnoUpdate]" + pstmt.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	// 한진택배
	public List<List<String>> sendPickupHanjinExpress(List<List<String>> cloneContents, String orddt,
			List<String> comlist) {
		List<List<String>> contents = new ArrayList<>();

		String driver = "oracle.jdbc.driver.OracleDriver";
		// String url = "jdbc:oracle:thin:@211.210.94.46:1531:HDDWEB";개발
		String url = "jdbc:oracle:thin:@211.210.94.2:1531:HDDWEB";// 운영
		String user = "YUYOUNG";
		String password = "YUYOUNG2001";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		java.sql.CallableStatement cstmt = null;
		try {
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
			con = DriverManager.getConnection(url, user, password);

			for (List<String> list : cloneContents) {
				String ordnum = list.get(0) + list.get(1) + list.get(2);
				String box = getBoxCode(list.get(10));
				cstmt = con.prepareCall(
						"{call INSERT_CST_ORD_MTR_MM(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
				cstmt.setString(1, "YUYOUNG");// EDI코드(한진제공)
				cstmt.setString(2, ordnum);// 주문번호
				cstmt.setString(3, "");// 운송장번호
				cstmt.setString(4, "F");// 배송구분//포커스출력
				cstmt.setString(5, "9127150");// 신용번호
				cstmt.setString(6, YDMATimeUtil.getCurrentDateHanjin());// 상품출고일
				cstmt.setString(7, comlist.get(13));// 송하인우편번호
				cstmt.setString(8, comlist.get(14));// 송하인주소
				cstmt.setString(9, "");// 송하인주소
				cstmt.setString(10, comlist.get(1));// 송하인명
				cstmt.setString(11, splitMark(comlist.get(17)));// 송하인전화번호
				cstmt.setString(12, splitMark(comlist.get(18)));// 송화인휴대폰번호
				cstmt.setString(13, "");// 송화인담당자명
				cstmt.setString(14, list.get(5));// 수화인우편번호
				cstmt.setString(15, list.get(6));// 수화인주소
				cstmt.setString(16, "");// 수화인주소
				cstmt.setString(17, list.get(4));// 수화인명
				cstmt.setString(18, splitMark(list.get(7).equals("") ? list.get(8) : list.get(7)));// 수화인전화번호
				cstmt.setString(19, splitMark(list.get(7).equals("") ? list.get(8) : list.get(7)));// 수화인핸드폰번호
				cstmt.setString(20, "");// 수화인담당자명
				cstmt.setString(21, list.get(13));// 배송메세지
				cstmt.setString(22, list.get(12));// 상품명
				cstmt.setInt(23, Integer.parseInt(list.get(9)));// 박스수량
				cstmt.setString(24, list.get(11).equals("착불") ? "CC" : "CD");// 지불조건
				cstmt.setString(25, box);// 박스타입
				cstmt.setString(26, "");// 메모1
				cstmt.setString(27, "");// 메모2
				cstmt.setString(28, "");// 메모3
				cstmt.setString(29, "");// 메모4
				cstmt.registerOutParameter(30, java.sql.Types.VARCHAR);
				cstmt.registerOutParameter(31, java.sql.Types.VARCHAR);
				cstmt.registerOutParameter(32, java.sql.Types.VARCHAR);

				cstmt.execute();
				list.add(0, cstmt.getString(30));
				list.add(1, cstmt.getString(31));
				list.add(2, cstmt.getString(32));
				contents.add(list);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오라클 연결 실패");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {

				}

			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e) {

				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {

				}
			}
		}

		return contents;
	}

	// 한진박스타입가지고 오기
	private String getBoxCode(String delvcost) {
		String code = "";
		switch (delvcost) {
		case "2000":
			code = "A";
			break;
		case "2500":
			code = "B";
			break;
		case "3000":
			code = "C";
			break;
		case "3500":
			code = "C";
			break;
		case "4000":
			code = "C";
			break;
		default:
			code = "";
		}
		return code;
	}

	// 한진송장수신
	public List<List<String>> getHanjinInvoiceNo(List<List<String>> contents) {
		List<List<String>> contents_tarket = new ArrayList<>();

		String driver = "oracle.jdbc.driver.OracleDriver";
//		String url = "jdbc:oracle:thin:@211.210.94.46:1531:HDDWEB";개발
		String url = "jdbc:oracle:thin:@211.210.94.2:1531:HDDWEB";// 운영
		String user = "YUYOUNG";
		String password = "YUYOUNG2001";

		String sql = "select * from cst_pod_mtr_vw";
		sql = sql.toUpperCase();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
			con = DriverManager.getConnection(url, user, password);

			stmt = con.createStatement();// ("{call CST_POD_MTR_VW(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				List<String> list = new ArrayList<>();
				list.add(rs.getString(1));
				list.add(JobStats(rs.getString(2) == null ? "" : rs.getString(2)));
				list.add(rs.getString(3));
				list.add(rs.getString(4));
				list.add(rs.getString(5));
				list.add(Rgtymd(rs.getString(2) == null ? "" : rs.getString(2),
						rs.getString(6) == null ? "" : rs.getString(6)));
				list.add(rs.getString(7));
				list.add(rs.getString(8));
				list.add(rs.getString(9));
				list.add(rs.getString(10));
				list.add(rs.getString(11));
				contents_tarket.add(list);

			}

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오라클 연결 실패");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {

				}

			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {

				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {

				}
			}
		}

		return contents_tarket;
	}

	// 사유코드
	private String Rgtymd(String code, String string) {
		String stats = "";
		if (code.equals("92")) {
			switch (string) {
			case "01":
				stats = "수취 거부";
				break;
			case "02":
				stats = "수하인 이사";
				break;
			case "03":
				stats = "악천후";
				break;
			case "04":
				stats = "수하인 주소 부정확";
				break;
			case "05":
				stats = "고객부재";
				break;
			case "06":
				stats = "송하인 요청";
				break;
			default:
				break;
			}
		} else {
			switch (string) {
			case "01":
				stats = "송하인 부재";
				break;
			case "02":
				stats = "화물미준비 및 재고 부족";
				break;
			case "03":
				stats = "취급불가 화물";
				break;
			case "04":
				stats = "송하인 발송취소";
				break;
			case "05":
				stats = "고객분실";
				break;
			case "06":
				stats = "기 집하";
				break;
			case "07":
				stats = "고객 파손";
				break;
			case "08":
				stats = "타인 양도";
				break;
			case "09":
				stats = "배송불가";
				break;
			case "10":
				stats = "배송불가";
				break;
			case "11":
				stats = "배송불가";
				break;
			case "12":
				stats = "배송불가";
				break;
			case "99":
				stats = "배송불가";
				break;
			default:
				break;
			}
		}

		return stats;
	}

	// 작업상태
	private String JobStats(String string) {
		String stats = "";
		switch (string) {
		case "03":
			stats = "예약취소";
			break;
		case "10":
			stats = "운송장 등록";
			break;
		case "20":
			stats = "집하완료";
			break;
		case "30":
			stats = "배송완료";
			break;
		case "08":
			stats = "미집하";
			break;
		case "92":
			stats = "배송불가";
			break;
		default:
			break;
		}
		return stats;
	}

	public List<List<String>> sendPickupKyungdongExpress(List<List<String>> cloneContents, String orddt,
			List<String> comlist) {
		List<List<String>> responContents = new ArrayList<>();

		try {
			String url = "https://kdapi.kditlab.com/v1/Send2";

			for (int i = 0; i < cloneContents.size(); i++) {

				URL obj = new URL(url);
				HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();

				// add reuqest header
				httpConnection.setRequestMethod("POST");
				httpConnection.setRequestProperty("Content-Type", "application/json");
				httpConnection.setRequestProperty("Accept-Charset", "UTF-8");
				// Send post request
				httpConnection.setDoOutput(true);

				JsonObject object = new JsonObject();
				JsonArray jsonarray = new JsonArray();
				object.addProperty("API_KEY", "93DA0E4789FA431F97BC60D1743D6510");// api 키//운영
				// object.addProperty("API_KEY", "6276CB47D8D244E6885ECC87D862F4F6");//api 키//개발
				// object.addProperty("PD_BARCODE", "");//운송장번호
				object.addProperty("BR_END_NM", "");// 도착영업소
				object.addProperty("COM_NM", "");// 거래처명

				object.addProperty("RECV_NM", cloneContents.get(i).get(4));// 받는분성함
				object.addProperty("RECV_TEL1", cloneContents.get(i).get(7).equals("") ? cloneContents.get(i).get(8)
						: cloneContents.get(i).get(7));// 전화번호
				object.addProperty("RECV_TEL2", "");// 기타전화번호
				object.addProperty("RECV_ZIPCODE", cloneContents.get(i).get(5));// 우편번호
				String addr3 = "";
				String addr4 = "";
				if (cloneContents.get(i).get(6).getBytes().length >= 60) {
					addr3 = YDMAStringUtil.setTextStrtok(cloneContents.get(i).get(6), 60);
					addr4 = cloneContents.get(i).get(6).substring(addr3.length(), cloneContents.get(i).get(6).length());
				} else {
					addr3 = cloneContents.get(i).get(6);
				}
				object.addProperty("RECV_ADDR1", addr3);// 주소
				object.addProperty("RECV_ADDR2", addr4);// 상세주소

				object.addProperty("SEND_NM", comlist.get(1));// 보내는사람성함
				object.addProperty("SEND_TEL1", comlist.get(17));// 보내는분전화번호
				object.addProperty("SEND_TEL2", "");// 기타전화번호
				object.addProperty("SEND_ZIPCODE", comlist.get(13));// 우편번호
				object.addProperty("SEND_ADDR1", comlist.get(14));// 주소 50바이트로 짤림 그래서 짤리면 상세주소로 넘김
				object.addProperty("SEND_ADDR2", "");// 상세주소

				object.addProperty("PD_CNT", cloneContents.get(i).get(9));// 수량
				object.addProperty("PD_DELI_GUBUN", cloneContents.get(i).get(11).equals("착불") ? "착택" : "현택");// 현택/착택
				object.addProperty("PD_DELI_PAY",
						Integer.parseInt(cloneContents.get(i).get(9)) * Integer.parseInt(cloneContents.get(i).get(10)));// 운임
				object.addProperty("PD_MEMO", cloneContents.get(i).get(13));// 메모1
				String prodnm = "";
				if (cloneContents.get(i).get(12).length() > 50) {
					byte[] strByte = cloneContents.get(i).get(12).getBytes();
					prodnm = new String(strByte, 0, 50);
				} else {
					prodnm = cloneContents.get(i).get(12);
				}
				object.addProperty("PD_NM", prodnm);// 품목명
				object.addProperty("PD_PACK", "박스");// 포장상태

				int maxcnt = Integer.parseInt(cloneContents.get(i).get(9));
				String pdprice = "";
				for (int cnt = 0; cnt < maxcnt; cnt++) {
					pdprice += "30|";
				}
				object.addProperty("PD_PRICE", pdprice);// 물건에 대한 각각의 단가 무조건100
				System.out.println(pdprice);
				// object.addProperty("PD_VOLUME", "");//물건부피
				// object.addProperty("PD_WEIGHT", "");//물건무게
				// object.addProperty("PD_MEMO", cloneContents.get(i).get(13));//메모2
				// 품목명이 25글자로 짤려서 메모란에 넣으면 좋음 메모1번에 품목명 메모2번은 배송메세지로

				jsonarray.add(object);

				System.out.println(jsonarray.toString());

				DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
				out.write(jsonarray.toString().getBytes("UTF-8"));
				out.flush();
				out.close();

				int responseCode = httpConnection.getResponseCode();
				System.out.println("택배사송장수신: " + responseCode);

				BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
				String inputLine = null;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
//				httpConnection.disconnect();

				System.out.println(response.toString());

				String xml = response.toString();
				if (xml != null) {
					// xml을 파싱해주는 객체를 생성
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					factory.setNamespaceAware(true);
					// xml 문자열은 InputStream으로 변환
					InputSource is = new InputSource(new StringReader(response.toString()));
					// 파싱 시작
					Document document = builder.parse(is);

					// document.getDocumentElement().normalize();
					// System.out.println("Root element: " +
					// document.getDocumentElement().getNodeName());
					NodeList nList = document.getElementsByTagName("Response");
					// System.out.println("파싱할 리스트 수 : "+ nList.getLength());

					for (int j = 0; j < nList.getLength(); j++) {
						Node nNode = nList.item(j);
						List<String> list = new ArrayList<>();

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							int idx = 0;
//							cloneContents.get(i).get(5)
							list.add(idx++, cloneContents.get(i).get(0)); // 주문번호
							list.add(idx++, cloneContents.get(i).get(1)); // 주문차수
							list.add(idx++, cloneContents.get(i).get(2)); // 주문일련번호
							String statuscode = getTagValue("CODE", eElement);
							list.add(idx++, statuscode); // 상태코드
							list.add(idx++, KyunddongJobStats(statuscode)); // 상태메시지
							list.add(idx++, getTagValue("PD_BARCODE", eElement)); // 송장번호

//							list.add(idx++, getTagValue("BR_END_NM", eElement));
//							list.add(idx++, getTagValue("BR_END_TEL", eElement));
//							list.add(idx++, getTagValue("PD_BOHUM_PAY", eElement));
//							list.add(idx++, getTagValue("PD_DOCK1", eElement));
//							list.add(idx++, getTagValue("PD_DOCK2", eElement));
//							list.add(idx++, getTagValue("PD_DOSUN_PAY", eElement));
//							list.add(idx++, getTagValue("PD_TEMP", eElement));
//							list.add(idx++, getTagValue("PD_TERMINAL1", eElement));
//							list.add(idx++, getTagValue("PD_TERMINAL2", eElement));

						} // if end

						responContents.add(list);

					} // for end
				}

			}

			// Json 파싱은 안됨
//			JsonParser jsonParser = new JsonParser();
//			JsonElement jsonObject = (JsonElement) jsonParser.parse(response.toString());
//			String statusCode = jsonObject.getAsJsonObject().get("result").getAsString();
//			if(statusCode.equals("Y")) {
//			}

		} catch (Exception e) {
			e.getMessage();
		}

		return responContents;
	}

	// tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	// 작업상태
	private String KyunddongJobStats(String string) {
		String stats = "";
		switch (string) {
		case "0":
			stats = "보내는 데이터가 형식이 맞지 않습니다.";
			break;
		case "1":
			stats = "DB에 접속할 수 없습니다. 전산실에 문의하시기 바랍니다.";
			break;
		case "2":
			stats = "Request에 도착 영업소명이 없는 경우 당사의 배달구역에서의 도착영업소 정 보를 찾을 수 없습니다. 전산실에 문의하시기 바랍니다.";
			break;
		case "3":
			stats = "Request에 도착 영업소명(BR_END_NM )이 당사의 영업소명과 일치하지 않습니다. ";
			break;
		case "4":
			stats = "보내는 택배비 운임 값이 현택 / 착택 / 현불 / 착불 이 아닙니다. ";
			break;
		case "5":
			stats = "보내는분의 성함이 없거나 데이터 크기를 넘어섰습니다.";
			break;
		case "6":
			stats = "보내는분 전화번호가 없거나 잘못된 형식입니다.";
			break;
		case "7":
			stats = "보내는분 기타전화번호가 잘못된 형식입니다.";
			break;
		case "8":
			stats = "우편번호가 없거나 형식에 맞지 않습니다.";
			break;
		case "9":
			stats = "주소가 없거나 허용되는 크기를 벗어났습니다.";
			break;
		case "10":
			stats = "상세주소가 허용되는 크기를 벗어났습니다.";
			break;
		case "11":
			stats = "받는분 성함이 없거나 허용되는 크기를 벗어났습니다.";
			break;
		case "12":
			stats = "받는분 전화번호가 없거나 잘못된 형식입니다.";
			break;
		case "13":
			stats = "받는분 기타전화번호가 잘못된 형식입니다.";
			break;
		case "14":
			stats = "받는분 우편번호가 없거나 형식에 맞지 않습니다.";
			break;
		case "15":
			stats = "받는분 주소가 없거나 허용되는 크기를 벗어났습니다.";
			break;
		case "16":
			stats = "받는분 상세주소가 허용되는 크기를 벗어났습니다.";
			break;
		case "17":
			stats = "상품의 품목명이 없거나 허용되는 크기를 벗어났습니다.";
			break;
		case "18":
			stats = "포장상태가 없거나 허용되는 크기를 벗어났습니다.";
			break;
		case "19":
			stats = "운임이 당사의 최저운임 방침보다 작습니다.";
			break;
		case "20":
			stats = "수량이 1개미만이거나 100개를 초과하였습니다.";
			break;
		case "21":
			stats = "개별단가가 수량과 맞지 않거나 형식에 맞지 않습니다.";
			break;
		case "22":
			stats = "부피가 수량과 맞지 않거나 형식에 맞지 않습니다.";
			break;
		case "23":
			stats = "무게가 수량과 맞지 않거나 형식에 맞지 않습니다.";
			break;
		case "24":
			stats = "메모가 허용되는 크기를 벗어났습니다.";
			break;
		case "25":
			stats = "수탁번호 생성에 실패하였습니다. 전산실에 문의하시기 바랍니다.";
			break;
		case "26":
			stats = "해당 지역을 배달구역에서 찾지 못 하였거나 올바른 우편번호가 아닙니다.";
			break;
		case "27":
			stats = "노선 할당에 실패하였습니다. 전산실에 문의하시기 바랍니다.";
			break;
		case "28":
			stats = "도크 할당에 실패하였습니다. 전산실에 문의하시기 바랍니다.";
			break;
		case "29":
			stats = "배차 정보읽기에 실패하였습니다. 전산실에 문의하시기 바랍니다.";
			break;
		case "30":
			stats = "발송 정보 입력에 실패하였습니다. 전산실에 문의하시기 바랍니다.";
			break;
		case "200":
			stats = "택배 전송이 성공적으로 전송되었습니다.";
			break;
		case "900":
			stats = "화물인 경우 찾을 수 없는 영업소명인 경우입니다.";
			break;
		case "901":
			stats = "API키가 없어가 인증되지 않은 키 입니다";
			break;
		case "902":
			stats = "유효하지 않은 거래처명입니다.";
			break;
		case "903":
			stats = "운송장번호를 자체 생성하여 사용하시는 경우에 생성 규칙에 맞지 않습니다.";
			break;
		case "904":
			stats = "운송장번호를 자체 생상하여 사용하시는 경우에 사용된 운송장 번호입니다.";
			break;
		case "905":
			stats = "정의되지 않은 예외입니다.";
			break;
		case "906":
			stats = "일요일에는 전산실에서 허용한 ID외에는 발송 자료저장이 불가합니다.";
			break;
		default:
			break;
		}
		return stats;
	}

	// 에러업데이트하기
	public void setExpressErrorUpdate(List<List<String>> errorResult) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();
			// for(int k =1;k<=list.size()-1;k++) {
			String sql = "update pickupexp set EXPSENDDT=?, expnm = ?, exprecvdt = ?, flag = ? where orddt=? and ordseq = ? and seq = ? and compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (List<String> list : errorResult) {
				int i = 0;

				pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++i, list.get(4) + "_" + list.get(3));
				pstmt.setString(++i, YDMATimeUtil.getCurrentTimeByYDFormat());
				pstmt.setString(++i, "X");

				pstmt.setString(++i, list.get(0));
				pstmt.setString(++i, list.get(1));
				pstmt.setString(++i, list.get(2));
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.addBatch();
				pstmt.clearParameters();

				// pstmt.executeUpdate();
			}
			System.out.println("[setExpressErrorUpdate]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	public void setOrderMoveseq(List<List<String>> moveseq) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();
			// for(int k =1;k<=list.size()-1;k++) {
			String sql = "update orddtl set moveseq=? where orddt=? and ordseq = ? and seq = ? and compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (List<String> list : moveseq) {
				if (list.size() > 47) {
					int i = 0;
					pstmt.setString(++i, list.get(47));

					pstmt.setString(++i, list.get(0));
					pstmt.setString(++i, list.get(1));
					pstmt.setString(++i, list.get(2));
					pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
					pstmt.addBatch();
					pstmt.clearParameters();
				}

			}
			System.out.println("[setOrderMoveseq]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

	public void setDeliveryMethod(List<List<String>> moveseq) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();
			// for(int k =1;k<=list.size()-1;k++) {
			String sql = "update pickupexp set CREDIT=? where orddt=? and ordseq = ? and seq = ? and compno = ? ";

			sql = sql.toUpperCase();
			pstmt = connection.prepareStatement(sql);
			statementlist.add(pstmt);
			for (List<String> list : moveseq) {
				int i = 0;
				pstmt.setString(++i, list.get(11));

				pstmt.setString(++i, list.get(0));
				pstmt.setString(++i, list.get(1));
				pstmt.setString(++i, list.get(2));
				pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.addBatch();
				pstmt.clearParameters();

				// pstmt.executeUpdate();
			}
			System.out.println("[setDeliveryMethod]" + pstmt.toString());
			pstmt.executeBatch();
			pstmt.clearParameters();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

	}

}
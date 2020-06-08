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
	// �ù��û
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
			payload.append("{ \"tierCode\"").append(":\"").append("KDJSYSTEM").append("\","); // �����ڵ� �ʼ�
			payload.append("\"cipherType\"").append(":\"").append("0").append("\","); // ��ȣȭŸ��
			payload.append("\"data\": [ ");
			payload.append("\n"); //
			for (int i = 0; i < contents.size(); i++) {
				payload.append("{"); //
				List<String> list = contents.get(i);
				payload.append("\"ordCde\"").append(":\"")
						.append(complist.get(24) + list.get(0) + list.get(1) + list.get(2)).append("\","); // �ֹ���ȣ �ʼ�
//				if(!list.get(38).equals("")) {
//					//payload.append("'comCode'").append(":'").append(list.get(38).substring(list.get(38).length()-2,list.get(38).length())).append("',"); //�ù���ڵ� �ʼ�
//					payload.append("\"comCode\"").append(":\"").append("08").append("\","); //�ù���ڵ� �ʼ�
//				} else{
//					MessageDialog.openInformation(shell, "�ù������", "�ù��ü �ڵ尡 �����ϴ�. �Է��� �ٽ� �����Ͽ��ֽñ� �ٶ��ϴ�.");
//				}
				payload.append("\"comCode\"").append(":\"").append(expcd.get(2)).append("\","); // �ù���ڵ� �ʼ�
				payload.append("\"addDat\"").append(":\"").append(splitMark(list.get(3))).append("\","); // �ֹ�����
				payload.append("\"cusCde\"").append(":\"").append("971786").append("\","); // �ſ��ڵ� �ʼ�
				payload.append("\"cusSub\"").append(":\"").append("\","); // ����ſ�ũ��
				payload.append("\"sndNme\"").append(":\"").append(complist.get(1)).append("\","); // ��ȭ�θ� �ʼ�
				payload.append("\"sndZip\"").append(":\"").append(complist.get(13)).append("\","); // ��ȭ�ο����ȣ �ʼ�
				payload.append("\"sndAd1\"").append(":\"").append(complist.get(14)).append("\","); // ��ȭ���ּ� �ʼ�
				payload.append("\"sndAd2\"").append(":\"").append("\","); // ��ȭ���ּ�2
				payload.append("\"sndTel\"").append(":\"").append(complist.get(17)).append("\","); // ��ȭ����ȭ��ȣ �ʼ�
				payload.append("\"sndMod\"").append(":\"").append("\","); // ��ȭ����ȭ��ȣ2
				payload.append("\"ownNme\"").append(":\"").append(list.get(4)).append("\","); // ��ȭ�θ� �ʼ�
				payload.append("\"ownZip\"").append(":\"").append(list.get(5)).append("\","); // �����ȣ �ʼ�
				payload.append("\"ownAd1\"").append(":\"").append(list.get(6)).append("\","); // �ּ� �ʼ�

				payload.append("\"ownAd2\"").append(":\"").append("\","); // �ּ�2
				if (!list.get(7).equals("")) {
					payload.append("\"ownTel\"").append(":\"").append(splitMark(list.get(7))).append("\","); // ��ȭ��ȣ �ʼ�
				} else {
					payload.append("\"ownTel\"").append(":\"").append(splitMark(list.get(8))).append("\","); // ��ȭ��ȣ �ʼ�
				}
				payload.append("\"ownMod\"").append(":\"").append("\","); // ��ȭ����ȭ��ȣ
				payload.append("\"adMemo\"").append(":\"").append(list.get(13)).append("\","); // ��۸޸�
				if (list.get(11).equals("����")) {
					payload.append("\"wipGbn\"").append(":\"").append("1").append("\","); // ���ӱ��� �ʼ� 1:���� 2:���� 3:�ſ�
				} else if (list.get(11).equals("����")) {
					payload.append("\"wipGbn\"").append(":\"").append("2").append("\","); // ���ӱ��� �ʼ� 1:���� 2:���� 3:�ſ�
				} else {
					payload.append("\"wipGbn\"").append(":\"").append("3").append("\","); // ���ӱ��� �ʼ� 1:���� 2:���� 3:�ſ�
				}
				payload.append("\"dsoGbn\"").append(":\"").append("2").append("\","); // ȭ��ũ�� �ʼ� 1:�ؼ� 2:�� 3:�� 4:�� 5:�ش�
				payload.append("\"itmLst\"").append(":\"").append(list.get(12)).append("\",");// ��ǰ�� �ʼ�
				payload.append("\"itmCnt\"").append(":\"").append(list.get(9)).append("\"");// ��ǰ���� �ʼ�
//				payload.append("'dsoWght'").append(":'").append("', "); //ȭ������ ��ü���� �ʼ�
//				payload.append("'comDivCde'").append(":'").append("', "); //�����ڵ�(��ü���ʼ�)/���»��ڵ�(��������ʼ�)
//				payload.append("'itmOption'").append(":'").append("'"); //��ǰ�� ��������ǰ�� �ʿ���Է�

				payload.append("} ");

				if (i < contents.size() - 1) {
					payload.append(", ");
				}

				payload.append("\n"); // --��������
			}

			payload.append("]}");

			System.out.println(payload);

			DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
			out.write(payload.toString().getBytes("UTF-8"));
			out.flush();
			out.close();

			int responseCode = httpConnection.getResponseCode();

			System.out.println("�ù������: " + responseCode);

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
						// �Ǹŵ���� ���� Code null �� ó���� ������
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
						// --- ���� �ֹ����̺� key
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

	// �����ȣ ����
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

			payload.append("{ \"tierCode\"").append(":\"").append("KDJSYSTEM").append("\","); // �����ڵ� �ʼ�
			payload.append("\"cipherType\"").append(":\"").append("0").append("\","); // ��ȣȭŸ��
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

			System.out.println("�ù��������: " + responseCode);

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
					// --- ���� �ֹ����̺� key
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
//			pstmt.setString(++i, list.get(16).equals("08")?"�Ե��ù�_002":"��Ÿ");
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

	// �Ե��ù��ù�����
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

	// õ���ù�����
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

	// �̵��Ҷ�����
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
//				pstmt.setString(++i, "����");
//				byte [] asciistr2 = "����".getBytes("KSC5601");
//				String menu2 = new String(asciistr2,"KSC5601");
//				pstmt.setString(++i, menu2);	

				if (menu.equals("����ù�") || menu.equals("�ù���ʰ�") || menu.equals("�Ե�(3500���̸�)") || menu.equals("õ���ù�")) {
					pstmt.setString(++i, "�յ��ù�");
				} else if (menu.equals("�Ե��ù�")) {
					pstmt.setString(++i, "�Ե��ù�");
				} else if (menu.equals("�����ù�")) {
					pstmt.setString(++i, "�����ù�");
				} else if (menu.equals("�浿�ù�")) {
					pstmt.setString(++i, "�浿�ù�");
				} else {
					pstmt.setString(++i, "����");
				}

				if (menu.equals("�Ե�(3500���̸�)")) {
					exp = "�Ե��ù�";
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
			System.out.println("jdbc driver �ε� ����");
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
				cstmt.setLong(1, seq);// seq�ٵ� �ڵ������� ������
				cstmt.setString(2, orddt);// ������
				cstmt.setString(3, "KG39");// �������ڵ�
				cstmt.setString(4, "KG390011");// �ŷ�ó�ڵ�
				cstmt.setString(5, list.get(4));// ��ȭ�ָ�
				cstmt.setString(6, list.get(5));// �����ȣ
				cstmt.setString(7, list.get(6));// �ּ�
				cstmt.setString(8, "");// �ּ�2
				cstmt.setString(9, list.get(7));// ��ȭ��ȣ1
				cstmt.setString(10, list.get(8));// ��ȭ��ȣ2
				cstmt.setString(11, comlist.get(1));// ��ȭ�ָ�
				cstmt.setString(12, comlist.get(14));// ��ȭ���ּ�
				cstmt.setString(13, "");// ��ȭ���ּ�2
				cstmt.setString(14, comlist.get(17));// ��ȭ����ȭ��ȣ1
				cstmt.setString(15, "");// ��ȭ����ȭ��ȣ2
				cstmt.setString(16, "KG" + code);// ǰ���ڵ�
				cstmt.setString(17, prodnm);// ǰ���
				cstmt.setInt(18, Integer.parseInt(list.get(9)));// ����
				cstmt.setString(19, list.get(11).equals("����") ? "1" : "2");// ���ӱ���
				cstmt.setString(20, list.get(15).equals("�ù�") ? "2" : "1");// ��ޱ���
				cstmt.setString(21, list.get(13));// ���(�޼���)
				cstmt.setString(22, "");// ������ȣ
				cstmt.setString(23, YDMATimeUtil.getCurrentTimeByYDFormat());// ����Ͻ�
				cstmt.setString(24, "13");// ��������
				cstmt.registerOutParameter(25, java.sql.Types.VARCHAR);
				cstmt.execute();

				list.add(0, cstmt.getString(25));

				contents.add(list);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver �ε� ����");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("����Ŭ ���� ����");
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

	// ǰ���ڵ� ������ ����
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
				YDMAProgressBar.get().setValue("�α��νÿ� ������ �߻��Ͽ����ϴ�. �ٽ� �õ� �Ͽ� �ֽñ� �ٶ��ϴ�.", 0);
				throw new Exception(e.getMessage());
			}
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/header/nav/div[1]")));
			// �ŷ�ó����
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[3]/header/nav/div[1]/div[3]/i-button"))).click();
			Thread.sleep(1000);
			// �Ǻ�����
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
						throw new Exception("���� : " + name + "���� ����ó�� ���� ��� �ϽǼ� �����ϴ�.");
					}
					if (list.get(4).equals("")) {
						throw new Exception("No. : " + list.get(2) + "�� ������ ���� ��� �ϽǼ� �����ϴ�.");
					}
					if (list.get(12).equals("")) {
						throw new Exception("���� : " + name + "���� ��ۻ�ǰ���� ���� ��� �ϽǼ� �����ϴ�.");
					}
					if (list.get(9).equals("") || list.get(9).equals("0")) {
						throw new Exception("���� : " + name + "�߼� ������ ���� ��� �ϽǼ� �����ϴ�.");
					}
					if (list.get(10).equals("") || list.get(10).equals("0")) {
						throw new Exception("���� : " + name + "���� ��ۺ���� ���� ��� �ϽǼ� �����ϴ�.");
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
					// �ּ�
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
//                		throw new Exception("���� : "+name+"���� �����ȣ�� �����Ѱ� ���� ã�� ���Ͽ� ������ �߻��Ͽ����ϴ�.");
						// �׽�Ʈ�ʿ�..�����ȣ ������ �׸����� ù��°���� �����ϵ��� �ϴ� �ڵ� �߰��� 3/4 mac
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

					// ��ǰ����½� ERP��ǰ�ڵ带 ���� ���.
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
					// ���ӱ���
					if (list.get(11).equals("����")) {
						driver.executeScript("$('#cboFareSctCd').val('02')", "");
					} else {
						driver.executeScript("$('#cboFareSctCd').val('03')", "");
					}
					Thread.sleep(1000);
					//
					// ��ۺ�
					driver.executeScript(String.format("$('#maeDlvFare').val('%s')", list.get(10)), "");
					// elementbodyBox.findElement(By.xpath("//*[@id=\"maeDlvFare\"]/input")).sendKeys(list.get(10));
					Thread.sleep(1000);
					driver.executeScript("fnSave()", "");
					Thread.sleep(1000);
					driver.executeScript("$('.msgButton').click()", "");
					Thread.sleep(1500);
					driver.executeScript("$('.msgButton').click()", "");
					// ����
					// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div[1]/div[2]/div/div[1]/i-button"))).click();
					// ����Ȯ��
					// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[11]/div[2]/div[2]/input[1]"))).click();
					// ����Ȯ��
					// Thread.sleep(1000);
					// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[11]/div[2]/div[2]/input"))).click();
					output.add(list.get(0));
					output.add(list.get(1));
					output.add(list.get(2));
					output.add(expcd.get(0));// �ù��ڵ�
					output.add(expcd.get(1));// �ù��̸�

					express.add(output);
					Thread.sleep(1000);
					YDMAProgressBar.get().setValue("���� : " + list.get(4) + "���� �ù� ������ ���������� �Ǿ����ϴ�.", count++);
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

	// �����ϱ�
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
				pstmt.clearParameters(); // �Ķ���� �ʱ�ȭ
			}
			System.out.println("[ProductErrordataDelete]" + pstmt.toString());
			result = pstmt.executeBatch();
			pstmt.clearBatch();// Batch �ʱ�ȭ
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		return result;

	}

	// ���̹����� ����������
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
				String addrerr = String.format("���� : %s ", addr.get(4));
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
					YDMAProgressBar.get().setValue(addrerr + "���� �ּ�[" + address + "] �� ������ �Խ��ϴ�.", 0);
				} catch (Exception e) {
					YDMAProgressBar.get().setValue(addrerr + "���� �ּҸ� ��������µ� ������ �߻��Ͽ����ϴ�.", 0);
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

	// ���尪��������
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
				YDMAProgressBar.get().setValue("�α��νÿ� ������ �߻��Ͽ����ϴ�. �ٽ� �õ� �Ͽ� �ֽñ� �ٶ��ϴ�.", 0);
				throw new Exception(e.getMessage());
			}
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/header/nav/div[1]")));
			// �����
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[3]/header/nav/div[1]/div[5]/i-button"))).click();
			Thread.sleep(1000);
			// ���հ�����������
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
					// �˻�
					// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div[2]/div[3]/i-button[2]"))).click();
					// �۰��������
					Thread.sleep(1000);
					// String invoice = (String)driver.executeScript("return
					// grdRsrv.getRowData(0)[8];","");
					try {
						String invoice = ChromeScript.get().returnCallbackScripter("return grdRsrv.getRowData(0)[8];",
								driver);
						if (invoice.equals("")) {
							throw new Exception(output.get(4) + "���� �����ȣ�� ������ ���� ���Ͽ����ϴ�.");
						} else {
							System.out.println(invoice);
							list.add(output.get(0));
							list.add(output.get(1));
							list.add(output.get(2));
							list.add(invoice);
							list.add("Y");
							express.add(list);
							YDMAProgressBar.get().setValue(output.get(4) + "���� �����ȣ[" + invoice + "]�� ������ �Խ��ϴ�.",
									count++);
						}
					} catch (Exception e) {
						throw new Exception(output.get(4) + "���� �޴��ȣ�� �˻��� ���� ã�� ���Ͽ����ϴ�.");
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

	// ��ǰ�Ǻ����ϰ� �����ϱ�
	public void removeGongyongFile(List<List<String>> search) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "delete from pickupexp " + " where ordseq = ? AND Orddt = ? and compno = ? and expfile = '����'";
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

	// �����ù�
	public List<List<String>> sendPickupHanjinExpress(List<List<String>> cloneContents, String orddt,
			List<String> comlist) {
		List<List<String>> contents = new ArrayList<>();

		String driver = "oracle.jdbc.driver.OracleDriver";
		// String url = "jdbc:oracle:thin:@211.210.94.46:1531:HDDWEB";����
		String url = "jdbc:oracle:thin:@211.210.94.2:1531:HDDWEB";// �
		String user = "YUYOUNG";
		String password = "YUYOUNG2001";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		java.sql.CallableStatement cstmt = null;
		try {
			Class.forName(driver);
			System.out.println("jdbc driver �ε� ����");
			con = DriverManager.getConnection(url, user, password);

			for (List<String> list : cloneContents) {
				String ordnum = list.get(0) + list.get(1) + list.get(2);
				String box = getBoxCode(list.get(10));
				cstmt = con.prepareCall(
						"{call INSERT_CST_ORD_MTR_MM(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
				cstmt.setString(1, "YUYOUNG");// EDI�ڵ�(��������)
				cstmt.setString(2, ordnum);// �ֹ���ȣ
				cstmt.setString(3, "");// ������ȣ
				cstmt.setString(4, "F");// ��۱���//��Ŀ�����
				cstmt.setString(5, "9127150");// �ſ��ȣ
				cstmt.setString(6, YDMATimeUtil.getCurrentDateHanjin());// ��ǰ�����
				cstmt.setString(7, comlist.get(13));// �����ο����ȣ
				cstmt.setString(8, comlist.get(14));// �������ּ�
				cstmt.setString(9, "");// �������ּ�
				cstmt.setString(10, comlist.get(1));// �����θ�
				cstmt.setString(11, splitMark(comlist.get(17)));// ��������ȭ��ȣ
				cstmt.setString(12, splitMark(comlist.get(18)));// ��ȭ���޴�����ȣ
				cstmt.setString(13, "");// ��ȭ�δ���ڸ�
				cstmt.setString(14, list.get(5));// ��ȭ�ο����ȣ
				cstmt.setString(15, list.get(6));// ��ȭ���ּ�
				cstmt.setString(16, "");// ��ȭ���ּ�
				cstmt.setString(17, list.get(4));// ��ȭ�θ�
				cstmt.setString(18, splitMark(list.get(7).equals("") ? list.get(8) : list.get(7)));// ��ȭ����ȭ��ȣ
				cstmt.setString(19, splitMark(list.get(7).equals("") ? list.get(8) : list.get(7)));// ��ȭ���ڵ�����ȣ
				cstmt.setString(20, "");// ��ȭ�δ���ڸ�
				cstmt.setString(21, list.get(13));// ��۸޼���
				cstmt.setString(22, list.get(12));// ��ǰ��
				cstmt.setInt(23, Integer.parseInt(list.get(9)));// �ڽ�����
				cstmt.setString(24, list.get(11).equals("����") ? "CC" : "CD");// ��������
				cstmt.setString(25, box);// �ڽ�Ÿ��
				cstmt.setString(26, "");// �޸�1
				cstmt.setString(27, "");// �޸�2
				cstmt.setString(28, "");// �޸�3
				cstmt.setString(29, "");// �޸�4
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
			System.out.println("jdbc driver �ε� ����");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("����Ŭ ���� ����");
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

	// �����ڽ�Ÿ�԰����� ����
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

	// �����������
	public List<List<String>> getHanjinInvoiceNo(List<List<String>> contents) {
		List<List<String>> contents_tarket = new ArrayList<>();

		String driver = "oracle.jdbc.driver.OracleDriver";
//		String url = "jdbc:oracle:thin:@211.210.94.46:1531:HDDWEB";����
		String url = "jdbc:oracle:thin:@211.210.94.2:1531:HDDWEB";// �
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
			System.out.println("jdbc driver �ε� ����");
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
			System.out.println("jdbc driver �ε� ����");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("����Ŭ ���� ����");
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

	// �����ڵ�
	private String Rgtymd(String code, String string) {
		String stats = "";
		if (code.equals("92")) {
			switch (string) {
			case "01":
				stats = "���� �ź�";
				break;
			case "02":
				stats = "������ �̻�";
				break;
			case "03":
				stats = "��õ��";
				break;
			case "04":
				stats = "������ �ּ� ����Ȯ";
				break;
			case "05":
				stats = "������";
				break;
			case "06":
				stats = "������ ��û";
				break;
			default:
				break;
			}
		} else {
			switch (string) {
			case "01":
				stats = "������ ����";
				break;
			case "02":
				stats = "ȭ�����غ� �� ��� ����";
				break;
			case "03":
				stats = "��޺Ұ� ȭ��";
				break;
			case "04":
				stats = "������ �߼����";
				break;
			case "05":
				stats = "���н�";
				break;
			case "06":
				stats = "�� ����";
				break;
			case "07":
				stats = "�� �ļ�";
				break;
			case "08":
				stats = "Ÿ�� �絵";
				break;
			case "09":
				stats = "��ۺҰ�";
				break;
			case "10":
				stats = "��ۺҰ�";
				break;
			case "11":
				stats = "��ۺҰ�";
				break;
			case "12":
				stats = "��ۺҰ�";
				break;
			case "99":
				stats = "��ۺҰ�";
				break;
			default:
				break;
			}
		}

		return stats;
	}

	// �۾�����
	private String JobStats(String string) {
		String stats = "";
		switch (string) {
		case "03":
			stats = "�������";
			break;
		case "10":
			stats = "����� ���";
			break;
		case "20":
			stats = "���ϿϷ�";
			break;
		case "30":
			stats = "��ۿϷ�";
			break;
		case "08":
			stats = "������";
			break;
		case "92":
			stats = "��ۺҰ�";
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
				object.addProperty("API_KEY", "93DA0E4789FA431F97BC60D1743D6510");// api Ű//�
				// object.addProperty("API_KEY", "6276CB47D8D244E6885ECC87D862F4F6");//api Ű//����
				// object.addProperty("PD_BARCODE", "");//������ȣ
				object.addProperty("BR_END_NM", "");// ����������
				object.addProperty("COM_NM", "");// �ŷ�ó��

				object.addProperty("RECV_NM", cloneContents.get(i).get(4));// �޴ºм���
				object.addProperty("RECV_TEL1", cloneContents.get(i).get(7).equals("") ? cloneContents.get(i).get(8)
						: cloneContents.get(i).get(7));// ��ȭ��ȣ
				object.addProperty("RECV_TEL2", "");// ��Ÿ��ȭ��ȣ
				object.addProperty("RECV_ZIPCODE", cloneContents.get(i).get(5));// �����ȣ
				String addr3 = "";
				String addr4 = "";
				if (cloneContents.get(i).get(6).getBytes().length >= 60) {
					addr3 = YDMAStringUtil.setTextStrtok(cloneContents.get(i).get(6), 60);
					addr4 = cloneContents.get(i).get(6).substring(addr3.length(), cloneContents.get(i).get(6).length());
				} else {
					addr3 = cloneContents.get(i).get(6);
				}
				object.addProperty("RECV_ADDR1", addr3);// �ּ�
				object.addProperty("RECV_ADDR2", addr4);// ���ּ�

				object.addProperty("SEND_NM", comlist.get(1));// �����»������
				object.addProperty("SEND_TEL1", comlist.get(17));// �����º���ȭ��ȣ
				object.addProperty("SEND_TEL2", "");// ��Ÿ��ȭ��ȣ
				object.addProperty("SEND_ZIPCODE", comlist.get(13));// �����ȣ
				object.addProperty("SEND_ADDR1", comlist.get(14));// �ּ� 50����Ʈ�� ©�� �׷��� ©���� ���ּҷ� �ѱ�
				object.addProperty("SEND_ADDR2", "");// ���ּ�

				object.addProperty("PD_CNT", cloneContents.get(i).get(9));// ����
				object.addProperty("PD_DELI_GUBUN", cloneContents.get(i).get(11).equals("����") ? "����" : "����");// ����/����
				object.addProperty("PD_DELI_PAY",
						Integer.parseInt(cloneContents.get(i).get(9)) * Integer.parseInt(cloneContents.get(i).get(10)));// ����
				object.addProperty("PD_MEMO", cloneContents.get(i).get(13));// �޸�1
				String prodnm = "";
				if (cloneContents.get(i).get(12).length() > 50) {
					byte[] strByte = cloneContents.get(i).get(12).getBytes();
					prodnm = new String(strByte, 0, 50);
				} else {
					prodnm = cloneContents.get(i).get(12);
				}
				object.addProperty("PD_NM", prodnm);// ǰ���
				object.addProperty("PD_PACK", "�ڽ�");// �������

				int maxcnt = Integer.parseInt(cloneContents.get(i).get(9));
				String pdprice = "";
				for (int cnt = 0; cnt < maxcnt; cnt++) {
					pdprice += "30|";
				}
				object.addProperty("PD_PRICE", pdprice);// ���ǿ� ���� ������ �ܰ� ������100
				System.out.println(pdprice);
				// object.addProperty("PD_VOLUME", "");//���Ǻ���
				// object.addProperty("PD_WEIGHT", "");//���ǹ���
				// object.addProperty("PD_MEMO", cloneContents.get(i).get(13));//�޸�2
				// ǰ����� 25���ڷ� ©���� �޸���� ������ ���� �޸�1���� ǰ��� �޸�2���� ��۸޼�����

				jsonarray.add(object);

				System.out.println(jsonarray.toString());

				DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
				out.write(jsonarray.toString().getBytes("UTF-8"));
				out.flush();
				out.close();

				int responseCode = httpConnection.getResponseCode();
				System.out.println("�ù��������: " + responseCode);

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
					// xml�� �Ľ����ִ� ��ü�� ����
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					factory.setNamespaceAware(true);
					// xml ���ڿ��� InputStream���� ��ȯ
					InputSource is = new InputSource(new StringReader(response.toString()));
					// �Ľ� ����
					Document document = builder.parse(is);

					// document.getDocumentElement().normalize();
					// System.out.println("Root element: " +
					// document.getDocumentElement().getNodeName());
					NodeList nList = document.getElementsByTagName("Response");
					// System.out.println("�Ľ��� ����Ʈ �� : "+ nList.getLength());

					for (int j = 0; j < nList.getLength(); j++) {
						Node nNode = nList.item(j);
						List<String> list = new ArrayList<>();

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							int idx = 0;
//							cloneContents.get(i).get(5)
							list.add(idx++, cloneContents.get(i).get(0)); // �ֹ���ȣ
							list.add(idx++, cloneContents.get(i).get(1)); // �ֹ�����
							list.add(idx++, cloneContents.get(i).get(2)); // �ֹ��Ϸù�ȣ
							String statuscode = getTagValue("CODE", eElement);
							list.add(idx++, statuscode); // �����ڵ�
							list.add(idx++, KyunddongJobStats(statuscode)); // ���¸޽���
							list.add(idx++, getTagValue("PD_BARCODE", eElement)); // �����ȣ

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

			// Json �Ľ��� �ȵ�
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

	// tag���� ������ �������� �޼ҵ�
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	// �۾�����
	private String KyunddongJobStats(String string) {
		String stats = "";
		switch (string) {
		case "0":
			stats = "������ �����Ͱ� ������ ���� �ʽ��ϴ�.";
			break;
		case "1":
			stats = "DB�� ������ �� �����ϴ�. ����ǿ� �����Ͻñ� �ٶ��ϴ�.";
			break;
		case "2":
			stats = "Request�� ���� �����Ҹ��� ���� ��� ����� ��ޱ��������� ���������� �� ���� ã�� �� �����ϴ�. ����ǿ� �����Ͻñ� �ٶ��ϴ�.";
			break;
		case "3":
			stats = "Request�� ���� �����Ҹ�(BR_END_NM )�� ����� �����Ҹ�� ��ġ���� �ʽ��ϴ�. ";
			break;
		case "4":
			stats = "������ �ù�� ���� ���� ���� / ���� / ���� / ���� �� �ƴմϴ�. ";
			break;
		case "5":
			stats = "�����º��� ������ ���ų� ������ ũ�⸦ �Ѿ���ϴ�.";
			break;
		case "6":
			stats = "�����º� ��ȭ��ȣ�� ���ų� �߸��� �����Դϴ�.";
			break;
		case "7":
			stats = "�����º� ��Ÿ��ȭ��ȣ�� �߸��� �����Դϴ�.";
			break;
		case "8":
			stats = "�����ȣ�� ���ų� ���Ŀ� ���� �ʽ��ϴ�.";
			break;
		case "9":
			stats = "�ּҰ� ���ų� ���Ǵ� ũ�⸦ ������ϴ�.";
			break;
		case "10":
			stats = "���ּҰ� ���Ǵ� ũ�⸦ ������ϴ�.";
			break;
		case "11":
			stats = "�޴º� ������ ���ų� ���Ǵ� ũ�⸦ ������ϴ�.";
			break;
		case "12":
			stats = "�޴º� ��ȭ��ȣ�� ���ų� �߸��� �����Դϴ�.";
			break;
		case "13":
			stats = "�޴º� ��Ÿ��ȭ��ȣ�� �߸��� �����Դϴ�.";
			break;
		case "14":
			stats = "�޴º� �����ȣ�� ���ų� ���Ŀ� ���� �ʽ��ϴ�.";
			break;
		case "15":
			stats = "�޴º� �ּҰ� ���ų� ���Ǵ� ũ�⸦ ������ϴ�.";
			break;
		case "16":
			stats = "�޴º� ���ּҰ� ���Ǵ� ũ�⸦ ������ϴ�.";
			break;
		case "17":
			stats = "��ǰ�� ǰ����� ���ų� ���Ǵ� ũ�⸦ ������ϴ�.";
			break;
		case "18":
			stats = "������°� ���ų� ���Ǵ� ũ�⸦ ������ϴ�.";
			break;
		case "19":
			stats = "������ ����� �������� ��ħ���� �۽��ϴ�.";
			break;
		case "20":
			stats = "������ 1���̸��̰ų� 100���� �ʰ��Ͽ����ϴ�.";
			break;
		case "21":
			stats = "�����ܰ��� ������ ���� �ʰų� ���Ŀ� ���� �ʽ��ϴ�.";
			break;
		case "22":
			stats = "���ǰ� ������ ���� �ʰų� ���Ŀ� ���� �ʽ��ϴ�.";
			break;
		case "23":
			stats = "���԰� ������ ���� �ʰų� ���Ŀ� ���� �ʽ��ϴ�.";
			break;
		case "24":
			stats = "�޸� ���Ǵ� ũ�⸦ ������ϴ�.";
			break;
		case "25":
			stats = "��Ź��ȣ ������ �����Ͽ����ϴ�. ����ǿ� �����Ͻñ� �ٶ��ϴ�.";
			break;
		case "26":
			stats = "�ش� ������ ��ޱ������� ã�� �� �Ͽ��ų� �ùٸ� �����ȣ�� �ƴմϴ�.";
			break;
		case "27":
			stats = "�뼱 �Ҵ翡 �����Ͽ����ϴ�. ����ǿ� �����Ͻñ� �ٶ��ϴ�.";
			break;
		case "28":
			stats = "��ũ �Ҵ翡 �����Ͽ����ϴ�. ����ǿ� �����Ͻñ� �ٶ��ϴ�.";
			break;
		case "29":
			stats = "���� �����б⿡ �����Ͽ����ϴ�. ����ǿ� �����Ͻñ� �ٶ��ϴ�.";
			break;
		case "30":
			stats = "�߼� ���� �Է¿� �����Ͽ����ϴ�. ����ǿ� �����Ͻñ� �ٶ��ϴ�.";
			break;
		case "200":
			stats = "�ù� ������ ���������� ���۵Ǿ����ϴ�.";
			break;
		case "900":
			stats = "ȭ���� ��� ã�� �� ���� �����Ҹ��� ����Դϴ�.";
			break;
		case "901":
			stats = "APIŰ�� ��� �������� ���� Ű �Դϴ�";
			break;
		case "902":
			stats = "��ȿ���� ���� �ŷ�ó���Դϴ�.";
			break;
		case "903":
			stats = "������ȣ�� ��ü �����Ͽ� ����Ͻô� ��쿡 ���� ��Ģ�� ���� �ʽ��ϴ�.";
			break;
		case "904":
			stats = "������ȣ�� ��ü �����Ͽ� ����Ͻô� ��쿡 ���� ����� ��ȣ�Դϴ�.";
			break;
		case "905":
			stats = "���ǵ��� ���� �����Դϴ�.";
			break;
		case "906":
			stats = "�Ͽ��Ͽ��� ����ǿ��� ����� ID�ܿ��� �߼� �ڷ������� �Ұ��մϴ�.";
			break;
		default:
			break;
		}
		return stats;
	}

	// ����������Ʈ�ϱ�
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
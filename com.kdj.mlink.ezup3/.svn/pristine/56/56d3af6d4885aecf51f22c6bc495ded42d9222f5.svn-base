package com.kdj.mlink.ezup3.data.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.kdj.mlink.ezup3.common.FtpUtil;
import com.kdj.mlink.ezup3.common.YDMAPluginUtils;
import com.kdj.mlink.ezup3.common.YDMAProperties;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.xml.YDWMXmlManager;
import com.kdj.mlink.ezup3.ui.SBNetOrderReceiveDialog;

public class SabangNetDao {

	String sabangGetClaimurl = "http://r.sabangnet.co.kr/RTL_API/xml_clm_info.html?xml_url=";
	String sabangGetOrderUrl = "http://r.sabangnet.co.kr/RTL_API/xml_order_info.html?xml_url=";
	String sabangSetProducturl = "http://r.sabangnet.co.kr/RTL_API/xml_goods_info.html?xml_url=";
	String sabangSetQuestionurl = "http://r.sabangnet.co.kr/RTL_API/xml_cs_ans.html?xml_url=";
	String sabangSetPickupExpressurl = "https://r.sabangnet.co.kr/RTL_API/xml_order_invoice.html?xml_url=";
	String PickupExpressurl = "http://openapi.epost.go.kr/postal/RegisterDeliveryService?wsdl";

	public List<String> getSabangNetOrderIdx(String dateFrom, String dateTo) throws Exception {

		List<String> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "SELECT SABORDNO FROM ORDDTL  WHERE orddt >= ? and orddt <= ? and compno = ? ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, dateFrom);
			pstmt.setString(2, dateTo);
			pstmt.setString(3, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getSabangNetOrderIdx]" + pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				contents.add(rs.getString(1));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return contents;
	}

	public List<List<String>> getSabangNetClaimList(String dateFrom, String dateTo) throws Exception {

		List<List<String>> responseContents = null;
		StringBuffer response = new StringBuffer();

		YDWMXmlManager.updateGetClaimListTemplate(dateFrom, dateTo);

		FtpUtil.uploadXmlFileToWeb('C'); // 대문자

		String fileName = YDMAProperties.getInstance().getAppProperty("sabang.claimTempateFile");
		String url = sabangGetClaimurl + "http://itsm.kdjsystem.com/xmldir/" + fileName;

		System.out.println("클레임 요청 URL " + url);

		URL obj = new URL(url);
		HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
		int responseCode = httpConnection.getResponseCode();

		if (responseCode == 200) {
			BufferedReader buffReader = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
			String inputLine = null;

			while ((inputLine = buffReader.readLine()) != null) {
				response.append(inputLine);
			}

			buffReader.close();
		}

		httpConnection.disconnect();

		System.out.println("Sabang claim list Api 응답 코드: " + responseCode);
//		System.out.println("Sabang claim list Api 응답 : " + response);

		responseContents = getResponseClaim(response.toString());

		return responseContents;
	}

	public List<List<String>> getResponseClaim(String responseData) throws Exception {

		System.out.println("Sabang claim list Api 응답 : " + responseData);

		List<List<String>> responseContents = new ArrayList<>();

		SBNetOrderReceiveDialog d = new SBNetOrderReceiveDialog(YDMAPluginUtils.getShell(), responseData,
				responseContents, 'C');
		d.open();

		return responseContents;
	}

	public List<List<String>> getSabangNetOrderList(String dateFrom, String dateTo) throws Exception {

		List<List<String>> responseContents = null;
		StringBuffer response = new StringBuffer();

		YDWMXmlManager.updateGetOrderListTemplate(dateFrom, dateTo);

		FtpUtil.uploadXmlFileToWeb('O');// 대문자 o

		String fileName = YDMAProperties.getInstance().getAppProperty("sabang.orderTempateFile");
		String url = sabangGetOrderUrl + "http://itsm.kdjsystem.com/xmldir/" + fileName;

		System.out.println("주문내역 요청 URL " + url);

		URL obj = new URL(url);
		HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
		int responseCode = httpConnection.getResponseCode();

		if (responseCode == 200) {
			BufferedReader buffReader = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
			String inputLine = null;

			while ((inputLine = buffReader.readLine()) != null) {
				response.append(inputLine);
			}

			buffReader.close();
		}

		httpConnection.disconnect();

		System.out.println("Sabang orderlist Api 응답 코드: " + responseCode);

		responseContents = getResponseOrder(response.toString());

		return responseContents;
	}

	public List<List<String>> getResponseOrder(String responseData) throws Exception {

		List<List<String>> responseContents = new ArrayList<>();

		SBNetOrderReceiveDialog d = new SBNetOrderReceiveDialog(YDMAPluginUtils.getShell(), responseData,
				responseContents, 'O');
		d.open();

		return responseContents;
	}

	public List<List<String>> sendProductListToSabangNet(List<List<String>> contents, boolean isNew, Shell shell)
			throws Exception {
		List<List<String>> responseContents = null;
		StringBuffer response = new StringBuffer();
		for (List<String> list : contents) { // Log
			if (list.get(2).contains(",") || list.get(2).contains("*")) {
				MessageDialog.openInformation(shell, "aa", "선택하신 내용중 상품명에 (',','*')의 내용이 있어 등록할수 없습니다.");
				responseContents = new ArrayList<>();
				return responseContents;
			}
		}
		YDWMXmlManager.updateSendProductListTemplate(contents, isNew, shell);
		FtpUtil.uploadXmlFileToWeb('P');

		try {
			String fileName = YDMAProperties.getInstance().getAppProperty("sabang.productTempateFile");
			String url = sabangSetProducturl + "http://itsm.kdjsystem.com/xmldir/" + fileName;
			System.out.println("상품등록 요청 URL " + url);

			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			int responseCode = httpConnection.getResponseCode();

			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);
//
//			if (responseCode == 302) {
//				String Location = httpConnection.getHeaderField("Location");
//				if (Location.startsWith("/")) {
//					Location = obj.getProtocol() + "://" + obj.getHost() + Location;
//				}
//				obj = new URL(Location);
//				httpConnection = (HttpURLConnection) obj.openConnection();
//				responseCode = httpConnection.getResponseCode();
//			}
//
//			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}

			httpConnection.disconnect();

			// System.out.println("SendProduct responseData :\n" + responseData);
			responseContents = getResponseProduct(response.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return responseContents;

	}

	// 송장
	public List<List<String>> sendPickupExpressSabangNet(List<List<String>> contents, Shell shell) throws Exception {
		List<List<String>> responseContents = new ArrayList<>();
		List<String> list = new ArrayList<>();
		StringBuffer response = new StringBuffer();

		YDWMXmlManager.updateSendPickupExpressSabangNetTemplate(contents, shell);
		FtpUtil.uploadXmlFileToWeb('S');

		try {
			String fileName = YDMAProperties.getInstance().getAppProperty("sabang.pickupExpressSabangNetTempateFile");
			String url = sabangSetPickupExpressurl + "http://itsm.kdjsystem.com/xmldir/" + fileName;
			System.out.println("상품등록 요청 URL " + url);

			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			int responseCode = httpConnection.getResponseCode();

			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}

			httpConnection.disconnect();

			System.out.println("SendProduct responseData :\n" + response.toString());
			String html = response.toString();
			Document doc = Jsoup.parseBodyFragment(html);
			Elements body = doc.select("body");
			for (int i = 0; i < body.size(); i++) {
				list.add(body.text());
			}
			responseContents.add(list);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return responseContents;

	}

	/// 문의수집사방넷
	public List<List<String>> sendQnaListToSabangNet(List<List<String>> contents) throws Exception {
		List<List<String>> responseContents = new ArrayList<>();
		List<String> list = new ArrayList<>();
		StringBuffer response = new StringBuffer();
		int idx = 0;
		for (int k = 0; k < contents.size(); k++) {
			for (int i = 0; i < contents.get(k).size(); i++) {
				if (contents.get(k).get(i) == null) {
					contents.get(k).set(i, "");
				}
			}
		}
		YDWMXmlManager.SendQuestionTemplate(contents);
		FtpUtil.uploadXmlFileToWeb('Q');

		String fileName = YDMAProperties.getInstance().getAppProperty("sabang.questionTempateFile");
		String url = sabangSetQuestionurl + "http://itsm.kdjsystem.com/xmldir/" + fileName;
		System.out.println("상품등록 요청 URL " + url);

		URL obj = new URL(url);
		HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
		int responseCode = httpConnection.getResponseCode();

		System.out.println("Sabang sendQuestion Api 응답 코드: " + responseCode);

		if (responseCode == 200) {
			BufferedReader buffReader = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
			String inputLine = null;

			while ((inputLine = buffReader.readLine()) != null) {
				response.append(inputLine);
			}

			buffReader.close();
		}

		httpConnection.disconnect();

		System.out.println("SendProduct responseData :\n" + response.toString());
		String html = response.toString();
		Document doc = Jsoup.parseBodyFragment(html);
		Elements body = doc.select("body font");
		for (int i = 0; i < body.size(); i++) {
			list.add(body.text());
		}
		responseContents.add(list);

		return responseContents;

	}

	public List<List<String>> getResponseProduct(String responseData) throws Exception {

		List<List<String>> responseContents = new ArrayList<>();

		responseContents = YDWMXmlManager.parseResponseProduct(responseData);

		return responseContents;
	}

	public List<List<String>> getResponseProduct11st(String responseData, String prodcd) throws Exception {

		List<List<String>> responseContents = new ArrayList<>();

		responseContents = YDWMXmlManager.parseResponseProduct11st(responseData, prodcd);

		return responseContents;
	}

	public List<List<String>> getProdcutListSendToSabangNet() throws Exception {

		// // {"상품코드", "상품명"}
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();
//			String sql = "select prodcd, prodnm, specdes, price, tagprice, remark, ea, ifnull(optchk,''),ifnull(repstt,''), ifnull(addtn1,''), "
//					+ " ifnull(addtn2,''), ifnull(searchwd,''), ifnull(categlagcd,''), ifnull(categmidcd,''), ifnull(categsmlcd,''), ifnull(categdetcd,''), ifnull(useyn,'')"
//					+ " from prodmst where prodcd = ? and compno = ? ";
			String sql = " SELECT prodcd,"
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,prodcd) as img,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ " prodnm, ifnull(specdes,'') as specdes, ifnull(price, 0), ifnull(tagprice, 0), remark, ea, ifnull(optchk, ''), "
					+ "  ifnull(repstt, ''), ifnull(addtn1, ''), ifnull(addtn2, ''), ifnull(searchwd,''), ifnull(infornum,''), ifnull(propertynum,'') ,ifnull(useyn,'') "
					+ "	FROM PRODMST A "
					+ " WHERE ifnull(sabcd,'') = ''	AND NOT EXISTS (SELECT 1 FROM OPTPRODMST B WHERE A.prodcd=B.OPTPRODCD and A.compno = B.compno ) "
					+ " AND IFNULL(A.DELYN,'N') = 'N' and A.compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getProdcutListToSendSabangNet]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add("" + (++rowno)); // ui grid 상의 순번 , 디비데이타 아님
				list.add(rs.getString(++i)); // prodcd
				list.add(rs.getString(++i)); // img
				list.add(rs.getString(++i)); // prodnm
				list.add(rs.getString(++i)); // specdes
				list.add(rs.getString(++i)); // price
				list.add(rs.getString(++i)); // tagprice
				list.add(rs.getString(++i)); // remark
				list.add(rs.getString(++i)); // ea
				list.add(rs.getString(++i)); // optchk
				list.add(rs.getString(++i)); // repstt
				list.add(rs.getString(++i)); // addtn1
				list.add(rs.getString(++i)); // addtn2
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));

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

	public List<List<String>> getProdcutListSendToSabangNetAgain() throws Exception {

		// // {"상품코드", "상품명"}
		List<List<String>> contents = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = " SELECT prodcd, "
					+ String.format("YWM_FUNC_BOSSPRODCD(%s,prodcd) as img,",
							YDMASessonUtil.getCompnoInfo().getCompno())
					+ "prodnm, ifnull(specdes,'') as specdes, ifnull(price, 0), ifnull(tagprice, 0), remark, ea, ifnull(optchk, ''), "
					+ "  ifnull(repstt, ''), ifnull(addtn1, ''), ifnull(addtn2, ''), ifnull(searchwd,''), ifnull(infornum,''), ifnull(propertynum,''), ifnull(useyn,'') "
					+ "	FROM PRODMST A " + " WHERE ifnull(sabcd,'') <> ''	"
					+ " AND NOT EXISTS (SELECT 1 FROM OPTPRODMST B WHERE A.prodcd=B.OPTPRODCD and A.compno = B.compno ) "
					+ " AND IFNULL(A.DELYN,'N') = 'N' and A.compno = ? ";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getProdcutListToSendSabangNetAgain]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowno = 0;

			while (rs.next()) {
				int i = 0;
				List<String> list = new ArrayList<>();
				list.add("" + (++rowno)); // ui grid 상의 순번 , 디비데이타 아님
				list.add(rs.getString(++i)); // prodcd
				list.add(rs.getString(++i)); // image
				list.add(rs.getString(++i)); // prodnm
				list.add(rs.getString(++i)); // specdes
				list.add(rs.getString(++i)); // price
				list.add(rs.getString(++i)); // tagprice
				list.add(rs.getString(++i)); // remark
				list.add(rs.getString(++i)); // ea
				list.add(rs.getString(++i)); // optchk
				list.add(rs.getString(++i)); // repstt
				list.add(rs.getString(++i)); // addtn1
				list.add(rs.getString(++i)); // addtn2
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));
				list.add(rs.getString(++i));

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

///////////////////////////////////////////
	/**
	 * processEcountProductLog 트랜잭션 updateProdmst - deleteEcountProdlog -
	 * insertEcountProdlog
	 *
	 * @param contents
	 * @throws Exception
	 */
	public void processSabangNetProductLog(List<List<String>> contents) throws Exception {

		Connection connection = null;
		ResultSet rs = null;
		List<PreparedStatement> statementlist = new ArrayList<>();

		try {
			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);
			// [SUCCESS, 수정, sabang_prodcdcd, ydms_prodcd]
			// [FAIL, 수정, ydms_prodcd]

			for (List<String> list : contents) {
				if (list.get(0).equals("SUCCESS")) {
					List<List<String>> temp = new ArrayList<>();
					temp.add(list);
					String prodcd = list.get(3);
					if (isMasterProdCD(prodcd)) {
						updateProdmst(connection, statementlist, temp);
					} else {
						updateProddtl(connection, statementlist, temp);
					}
				}
			}

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

	// QNA
//	public void processSabangNetQuestionLog(List<List<String>> contents) throws Exception {
//
//		Connection connection = null;
//		ResultSet rs = null;
//		List<PreparedStatement> statementlist = new ArrayList<PreparedStatement>();
//
//		try {
//			connection = DBCPInit.getInstance().getConnection();
//			connection.setAutoCommit(false);
//			// [SUCCESS, 수정, sabang_prodcdcd, ydms_prodcd]
//			// [FAIL, 수정, ydms_prodcd]
//
//			for (List<String> list : contents) {
//				if (list.get(0).equals("SUCCESS")) {
//					List<List<String>> temp = new ArrayList<List<String>>();
//					temp.add(list);
//					String prodcd = list.get(3);
//					if (isMasterProdCD(prodcd)) {
//						updateProdmst(connection, statementlist, temp);
//					} else {
//						updateProddtl(connection, statementlist, temp);
//					}
//				}
//			}
//
//			deleteEcountProdlog(connection, statementlist, contents);
//			insertEcountProdlog(connection, statementlist, contents);
//
//			connection.commit();
//
//		} catch (Exception ex) {
//			connection.rollback();
//			ex.printStackTrace();
//			throw ex;
//		} finally {
//			DBCPInit.getInstance().freeConnection(connection, statementlist, rs);
//		}
//	}
	/**
	 * 사방넷에서 받은 상품코드를 prodmst 나 proddtl 에 업데이트 해야하는데 v_products 에서 prodcd = prodcdm
	 * 이면 prodmst 아니면 proddtl 에 업데이트
	 * 
	 * @param prodcd
	 * @return
	 * @throws Exception
	 */
	public boolean isMasterProdCD(String prodcd) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select prodcd from v_products where prodcd = ? and compno = ? and prodcd = prodcdm ";
			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, prodcd);
			pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			System.out.println("[isMastProdCD]" + pstmt.toString());

			rs = pstmt.executeQuery();
			flag = rs.next();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return flag;
	}

	public void updateProdmst(Connection connection, List<PreparedStatement> pstmtlist, List<List<String>> contents)
			throws Exception {

		String sql_prodmst = " Update PRODMST set sabcd = ?  where prodcd = ? and compno = ? ";

		PreparedStatement pstmt = connection.prepareStatement(sql_prodmst);
		pstmtlist.add(pstmt);
		// [SUCCESS, 수정, sabang_prodcdcd, ydms_prodcd]
		// [FAIL, 수정, ydms_prodcd]
		for (List<String> list : contents) {

			if (list.get(0).equals("SUCCESS")) {
				int idx = 0;
				String sabcd = list.get(2); // 사방넷에 등록된 상품code
				pstmt.setString(++idx, sabcd);
				String prodcd = list.get(3);
				pstmt.setString(++idx, prodcd);
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.addBatch();
				System.out.println("[updateProdmst]" + pstmt.toString());
				pstmt.clearParameters(); // 파라미터 초기화
			}
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

	}

	public void updateProddtl(Connection connection, List<PreparedStatement> pstmtlist, List<List<String>> contents)
			throws Exception {

		String sql_proddtl = " Update proddtl set sabcd = ?  where PRODDTCD = ? and compno = ? ";

		PreparedStatement pstmt = connection.prepareStatement(sql_proddtl);
		pstmtlist.add(pstmt);
		// [SUCCESS, 수정, sabang_prodcdcd, ydms_prodcd]
		// [FAIL, 수정, ydms_prodcd]
		for (List<String> list : contents) {

			if (list.get(0).equals("SUCCESS")) {
				int idx = 0;
				String sabcd = list.get(2); // 사방넷에 등록된 상품code
				pstmt.setString(++idx, sabcd);
				String prodcd = list.get(3);
				pstmt.setString(++idx, prodcd);
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());

				pstmt.addBatch();
				System.out.println("[updateProddtl]" + pstmt.toString());
				pstmt.clearParameters(); // 파라미터 초기화
			}
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화

	}

	public void deleteEcountProdlog(Connection connection, List<PreparedStatement> pstmtlist,
			List<List<String>> contents) throws Exception {

		String sql = "DELETE FROM ECOUNTPRODLOG where prodcd = ? and gubun='S' and compno = ? ";

		sql = sql.toUpperCase();

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmtlist.add(pstmt);

		for (List<String> list : contents) {
			// [SUCCESS, 수정, sabang_prodcdcd, ydms_prodcd]
			// [FAIL, 상품설명누락, ydms_prodcd]
			if (list.get(0).equals("FAIL")) {
				pstmt.setString(1, list.get(2));
				pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			} else if (list.get(0).equals("SUCCESS")) {
				pstmt.setString(1, list.get(3));
				pstmt.setString(2, YDMASessonUtil.getCompnoInfo().getCompno());
			}

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
			// [SUCCESS, 수정, sabang_prodcdcd, ydms_prodcd]
			// [FAIL, 수정, ydms_prodcd]
			if (list.get(0).equals("FAIL")) {
				int idx = 0;
				pstmt.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());
				pstmt.setString(++idx, list.get(2));// ydms_prodcd
				pstmt.setString(++idx, ProductMstDao.GUBUN_S); // S
				pstmt.setString(++idx, list.get(0)); // SUCCESS
				pstmt.setString(++idx, ""); // 오류코드 없음
				pstmt.setString(++idx, list.get(1)); // 상품설명누락
				pstmt.setString(++idx, YDMATimeUtil.getCurrentTimeByYDFormat());

				System.out.println("[insertEcountProdlog]" + pstmt.toString());
				pstmt.addBatch();
				pstmt.clearParameters(); // 파라미터 초기화
			}
		}

		pstmt.executeBatch();
		pstmt.clearBatch();// Batch 초기화
	}

//	//11번가 상품등록
//	public List<List<String>> sendProductListTo11st(List<String> list, Shell shell, List<String> dtllist, List<String> idlist) throws Exception {
//		List<List<String>> responseContents = null;
//		StringBuffer response = new StringBuffer();
//		ProductMstDao dao = new ProductMstDao();
//		String prodcd = "";
//		List<String> sendseq = dao.getProductMstList(prodcd);//송신했을때 번호 가지고 있는거
//		//for(List<String>list : contents) {
//			YDWMXmlManager.updateSendProductList11stTemplate(list,shell,dtllist,idlist);
//			FtpUtil.uploadXmlFileToWeb('T');
//
//			try {
//				String fileName = YDMAProperties.getInstance().getAppProperty("11st.productListTempateFile");
//				String file ="http://itsm.kdjsystem.com/xmldir/" + fileName;
//				StringBuffer fileData = new StringBuffer();
//				BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream(YDMASessonUtil.getAppPath()+"\\YDwmsData\\03.SabangNet\\template\\SendProductList11st.xml"),"EUC-KR"));
//
//				char[] buf = new char[1024];
//
//		        int numRead=0;
//
//		        while((numRead=reader.read(buf)) != -1){
//		            fileData.append(buf, 0, numRead);
//		        }
//
//		        reader.close();
//		        String text = "text/xml";
//		        //String api = "b043773df271a0a504cf0405a6bcdec5";
//		        String xml_string_to_send = fileData.toString();
//		        String url = "http://api.11st.co.kr/rest/prodservices/product";
//
//				OutputStream os =null;
//				URL obj = new URL(url);
//				HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
//				httpConnection.setDoOutput(true);
//	        	httpConnection.setRequestMethod("POST");
//
//				httpConnection.setRequestProperty( "Content-Type",text );
//				httpConnection.setRequestProperty( "openapikey", idlist.get(12));
//				httpConnection.setRequestProperty( "Content-Length", Integer.toString(xml_string_to_send.length()));
//
//	            os = httpConnection.getOutputStream();
//	            os.write( xml_string_to_send.getBytes("euc-kr") );
//	            os.flush();
//	            os.close();
//
//				int responseCode = httpConnection.getResponseCode();
//
//				System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);
//
//				if (responseCode == 200) {
//					BufferedReader buffReader = new BufferedReader(
//							new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
//					String inputLine = null;
//
//				while ((inputLine = buffReader.readLine()) != null) {
//					response.append(inputLine);
//				}
//
//				buffReader.close();
//				}
//
//				httpConnection.disconnect();
//
//				//System.out.println("SendProduct responseData :\n" + responseData);
//				responseContents = getResponseProduct11st(response.toString(),prodcd);
//
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		//}
//
//
//		return responseContents;
//	}
	// 11번가 수정
	public List<List<String>> sendProductListToEdit11st(List<String> list, Shell shell, List<String> dtllist,
			List<String> idlist) throws Exception {
		List<List<String>> responseContents = null;
		StringBuffer response = new StringBuffer();
		ProductMstDao dao = new ProductMstDao();
		String prodcd = "";
		// List<String> sendseq = dao.getProductMstList(prodcd);//송신했을때 번호 가지고 있는거
		// for(List<String>list : contents) {
		// YDWMXmlManager.updateSendProductList11stTemplate(list,shell,dtllist,idlist);
		FtpUtil.uploadXmlFileToWeb('T');

		try {
			String fileName = YDMAProperties.getInstance().getAppProperty("11st.productListTempateFile");
			// String file ="http://itsm.kdjsystem.com/xmldir/" + fileName;
			StringBuffer fileData = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(
					YDMASessonUtil.getAppPath() + "\\YDwmsData\\03.SabangNet\\template\\SendProductList11st.xml"),
					"EUC-KR"));

			char[] buf = new char[1024];

			int numRead = 0;

			while ((numRead = reader.read(buf)) != -1) {
				fileData.append(buf, 0, numRead);
			}

			reader.close();
			String text = "text/xml";
			// String api = "b043773df271a0a504cf0405a6bcdec5";
			String xml_string_to_send = fileData.toString();
			String url = "http://api.11st.co.kr/rest/prodservices/product/" + list.get(113);

			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("PUT");

			httpConnection.setRequestProperty("Content-Type", text);
			httpConnection.setRequestProperty("openapikey", idlist.get(12));
			httpConnection.setRequestProperty("Content-Length", Integer.toString(xml_string_to_send.length()));

			os = httpConnection.getOutputStream();
			os.write(xml_string_to_send.getBytes("euc-kr"));
			os.flush();
			os.close();

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}

			httpConnection.disconnect();

			// System.out.println("SendProduct responseData :\n" + responseData);
			responseContents = getResponseProduct11st(response.toString(), prodcd);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// }

		return responseContents;
	}

//	//11번가 수정
//	public List<List<String>> sendProductListTo11stEdit(List<List<String>> contents, boolean isNew, Shell shell) throws Exception {
//		List<List<String>> responseContents = null;
//		StringBuffer response = new StringBuffer();
//
//		YDWMXmlManager.updateSendProductList11stTemplate(contents,isNew,shell);
//		FtpUtil.uploadXmlFileToWeb('T');
//
//		try {
//			String fileName = YDMAProperties.getInstance().getAppProperty("11st.productListTempateFile");
//			String file ="http://itsm.kdjsystem.com/xmldir/" + fileName;
//			StringBuffer fileData = new StringBuffer();
//			BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream(YDMASessonUtil.getAppPath()+"\\YDwmsData\\03.SabangNet\\template\\SendProductList11st.xml"),"EUC-KR"));
//
//			char[] buf = new char[1024];
//
//	        int numRead=0;
//
//	        while((numRead=reader.read(buf)) != -1){
//	            fileData.append(buf, 0, numRead);
//	        }
//
//	        reader.close();
//	        String text = "text/xml";
//	        String api = "b043773df271a0a504cf0405a6bcdec5";
//	        String xml_string_to_send = fileData.toString();
//
//			String url = "http://api.11st.co.kr/rest/prodservices/product/"+"2559729353";
//			OutputStream os =null;
//			URL obj = new URL(url);
//			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
//			httpConnection.setDoOutput(true);
//			httpConnection.setRequestMethod("PUT");
//			httpConnection.setRequestProperty( "Content-Type",text );
//			httpConnection.setRequestProperty( "openapikey", api);
//			httpConnection.setRequestProperty( "Content-Length", Integer.toString(xml_string_to_send.length()) );
//
//            os = httpConnection.getOutputStream();
//            os.write( xml_string_to_send.getBytes("euc-kr") );
//            os.flush();
//            os.close();
//
//			int responseCode = httpConnection.getResponseCode();
//
//			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);
//
//			if (responseCode == 200) {
//				BufferedReader buffReader = new BufferedReader(
//						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
//				String inputLine = null;
//
//			while ((inputLine = buffReader.readLine()) != null) {
//				response.append(inputLine);
//			}
//
//			buffReader.close();
//			}
//
//			httpConnection.disconnect();
//
//			//System.out.println("SendProduct responseData :\n" + responseData);
//			responseContents = getResponseProduct11st(response.toString());
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		return responseContents;
//	}
	// 11번가 판매중지
	public List<List<String>> sendProductListTo11stDiscontinued(List<List<String>> contents, boolean isNew, Shell shell,
			String prodcd) throws Exception {
		List<List<String>> responseContents = null;
		StringBuffer response = new StringBuffer();
		try {

			String text = "text/xml";
			String api = "b043773df271a0a504cf0405a6bcdec5";
			ProductMstDao dao = new ProductMstDao();
			List<String> list = dao.getProductMstList(prodcd);
			String url = "";
			if (!list.get(33).equals("")) {
				url = "http://api.11st.co.kr/rest/prodstatservice/stat/stopdisplay/" + list.get(33);
			}

			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("PUT");
			httpConnection.setRequestProperty("Content-Type", text);
			httpConnection.setRequestProperty("openapikey", api);

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Sabang sendProduct Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}

			httpConnection.disconnect();

			responseContents = getResponseProduct11st(response.toString(), prodcd);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return responseContents;
	}

}

package com.kdj.mlink.ezup3.data.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class PostDao {

	String postkey = "sTXZtuCPigvKatgg2kBzPhpJKSXyey%2FgOjRq0r6VbhV7LDvnSV7QxPKOq3uXmK5ccFJycXeCZK8qkccdkGHnDA%3D%3D";
	String postNoChgUrlParm = "http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd?ServiceKey=";
	String postPostparm = "&searchSe=post&srchwrd=";
	String postDongparm = "&searchSe=dong&srchwrd=";
	String postRoadparm = "&searchSe=road&srchwrd=";
	// String postNoChangeUrl =
	// "http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd?ServiceKey=sTXZtuCPigvKatgg2kBzPhpJKSXyey%2FgOjRq0r6VbhV7LDvnSV7QxPKOq3uXmK5ccFJycXeCZK8qkccdkGHnDA%3D%3D&searchSe=post&srchwrd=448-552";

//

	public String getPostNoChange(String postNo, String Addr) throws Exception {

		StringBuffer response = new StringBuffer();

		// String zipCode = postNo.substring(0,3) + "-" + postNo.substring(3);

		String url = postNoChgUrlParm + postkey + postPostparm + postNo;

		System.out.println("구 우편번호  URL " + url);

		URL obj = new URL(url);
		HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
		int responseCode = httpConnection.getResponseCode();

		if (responseCode == 200) {
			BufferedReader buffReader = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
			String inputLine = null;

			while ((inputLine = buffReader.readLine()) != null) {
				response.append(inputLine);
			}

			buffReader.close();
		}

		httpConnection.disconnect();

		System.out.println("PostNo Api 응답 코드: " + responseCode);

		String NewPostNo = getResponseOrder(response.toString(), Addr);

		return (NewPostNo);
	}

	public String getResponseOrder(String responseData, String Addr) throws Exception {

		String NewPostNo = "";

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document document = null;

		InputSource is = new InputSource(new StringReader(responseData));
		builder = factory.newDocumentBuilder();
		document = builder.parse(is);
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		XPathExpression expr_data = xpath.compile("/NewAddressListResponse/newAddressListAreaCd");
		NodeList nodeList = (NodeList) expr_data.evaluate(document, XPathConstants.NODESET);
		Loop1: for (int i = 0; i < nodeList.getLength(); i++) {
			NodeList child = nodeList.item(i).getChildNodes();

			for (int j = 0; j < child.getLength(); j++) {
				Node node = child.item(j);

				if (node.getNodeType() == 1) {
					if (node.getNodeName().equals("zipNo")) {
						NewPostNo = node.getTextContent();
					} else if (node.getNodeName().equals("lnmAdres")) {
						String lnmAdres = node.getTextContent();
						if (Addr.contains(lnmAdres)) {
							break Loop1;
						}
					} else if (node.getNodeName().equals("rnAdres")) {
						String rnAdres = node.getTextContent();
						if (Addr.contains(rnAdres)) {
							break Loop1;
						}
					}

				}

			}

		}

		return NewPostNo;
	}

	public void updateOrderPost(String orddt, int ordseq, int seq, String zipcode) throws Exception {

		Connection connection = null;
		PreparedStatement pstmt_update = null;

		List<PreparedStatement> pstmtlist = new ArrayList<>();
		ResultSet rs = null;

		try {

			connection = DBCPInit.getInstance().getConnection();
			connection.setAutoCommit(false);

			String sql_update = " update orddtl set pstno=? "
					+ " where orddt =? and ordseq=? and seq=? and compno = ? ";
			sql_update = sql_update.toUpperCase();
			pstmt_update = connection.prepareStatement(sql_update);
			pstmtlist.add(pstmt_update);

			int idx = 0;

			pstmt_update.setString(++idx, zipcode);
			pstmt_update.setString(++idx, orddt);
			pstmt_update.setInt(++idx, ordseq);
			pstmt_update.setInt(++idx, seq);
			pstmt_update.setString(++idx, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[updateOrderPost-update]" + pstmt_update.toString());
			pstmt_update.executeLargeUpdate();

			connection.commit();

		} catch (Exception ex) {
			connection.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmtlist, rs);
		}

	}

	public List<List<String>> getPostNo(int opt, String optStr) throws Exception {

		List<List<String>> responseContents = null;
		StringBuffer response = new StringBuffer();
		String url = "";

		if (opt == 0) {
			// String zipCode = optStr.substring(0,3) + "-" + optStr.substring(3);
			url = postNoChgUrlParm + postkey + postPostparm + optStr;
		} else if (opt == 1) {
			url = postNoChgUrlParm + postkey + postDongparm + optStr;
		} else if (opt == 2) {
			url = postNoChgUrlParm + postkey + postRoadparm + optStr;
		} else if (opt == 3) {
			responseContents = getPostNoDetail(optStr);
		}

		System.out.println("구 우편번호  URL " + url);

		if (opt != 3) {
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			int responseCode = httpConnection.getResponseCode();

			if (responseCode == 200) {
				// BufferedReader buffReader = new BufferedReader( new
				// InputStreamReader(httpConnection.getInputStream(), "euc-kr") );
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}
				buffReader.close();
			}
			httpConnection.disconnect();
			System.out.println("PostNo Api 응답 코드: " + responseCode);
			responseContents = getResponsePost(response.toString());
		}

		return (responseContents);
	}

	public List<List<String>> getResponsePost(String responseData) throws Exception {

		List<List<String>> responseContents = new ArrayList<>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document document = null;

		InputSource is = new InputSource(new StringReader(responseData));
		builder = factory.newDocumentBuilder();
		document = builder.parse(is);
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		XPathExpression expr_data = xpath.compile("/NewAddressListResponse/newAddressListAreaCd");
		NodeList nodeList = (NodeList) expr_data.evaluate(document, XPathConstants.NODESET);

		for (int i = 0; i < nodeList.getLength(); i++) {
			List<String> list = new ArrayList<>();
			NodeList child = nodeList.item(i).getChildNodes();

			for (int j = 0; j < child.getLength(); j++) {
				Node node = child.item(j);

				System.out
						.println("getNodeName == " + node.getNodeName() + "getTextContent == " + node.getTextContent());

				if (node.getNodeType() == 1) {
					if (node.getNodeName().equals("zipNo")) {
						list.add(node.getTextContent());
					} else if (node.getNodeName().equals("lnmAdres")) {
						list.add(node.getTextContent());
					} else if (node.getNodeName().equals("rnAdres")) {
						list.add(node.getTextContent());
					}

				}
				responseContents.add(list);

			}

		}

		return responseContents;
	}

	public List<List<String>> getPostNoDetail(String optStr) throws Exception {
		List<List<String>> responseContents = new ArrayList<>();
		HttpURLConnection con = null;
		try {
			int l = 50;
			int p = 1;
			int n1 = 0;
			int n2 = 0;

			URL url = new URL(
					"http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdSearchAllService/retrieveNewAdressAreaCdSearchAllService/getNewAddressListAreaCdSearchAll"
							+ "?ServiceKey=fRoG2z2srsGZYDvBBQaXB30BqjvwBCFpMsx9VFDS2oOeUCyxCUogK9Ia1%2FufL8G3b6X%2BzxlBpRv0tqOzjG6xLw%3D%3D" // 서비스키
							+ "&countPerPage=" + l // 페이지당 출력될 개수를 지정(최대 50)
							+ "&currentPage=" + p // 출력될 페이지 번호
							+ "&srchwrd=" + URLEncoder.encode(optStr, "UTF-8") // 검색어
			);

			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Accept-language", "ko");
			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			DocumentBuilder bd = fac.newDocumentBuilder();
			Document doc = bd.parse(con.getInputStream());
			boolean bOk = false; // <successYN>Y</successYN> 획득 여부
			optStr = null; // 에러 메시지
			String nn;
			Node nd;
			NodeList ns = doc.getElementsByTagName("cmmMsgHeader");
			if (ns.getLength() > 0)
				for (nd = ns.item(0).getFirstChild(); nd != null; nd = nd.getNextSibling()) {
					nn = nd.getNodeName();
					System.out.println("nn == " + nn);
					System.out.println("nd == " + nd.getTextContent());
					if (!bOk) {
						if (nn.equals("successYN")) { // 성공 여부
							if (nd.getTextContent().equals("Y"))
								bOk = true; // 검색 성공
						} else if (nn.equals("errMsg")) { // 에러 메시지
							optStr = nd.getTextContent();
						}
					} else {
						if (nn.equals("totalCount")) { // 전체 검색수
							n1 = Integer.parseInt(nd.getTextContent());
						} else if (nn.equals("currentPage")) { // 현재 페이지 번호
							n2 = Integer.parseInt(nd.getTextContent());
						}
					}
				}
			if (bOk) {
				ns = doc.getElementsByTagName("newAddressListAreaCdSearchAll");
				for (p = 0; p < ns.getLength(); p++) {
					List<String> list = new ArrayList<>();
					// System.out.println("getFirstChild == " + ns.item(p).getFirstChild() );
					for (nd = ns.item(p).getFirstChild(); nd != null; nd = nd.getNextSibling()) {
						System.out.println("getTextContent == " + nd.getTextContent());
						list.add(nd.getTextContent());
					}
					responseContents.add(list);
				}
			}
			if (optStr == null) { // OK!
				if (responseContents.size() < 3)
					optStr = "검색결과가 없습니다.";
			}
		} catch (Exception e) {
			optStr = e.getMessage();
		}

		if (con != null)
			con.disconnect();

		return responseContents;
	}

}

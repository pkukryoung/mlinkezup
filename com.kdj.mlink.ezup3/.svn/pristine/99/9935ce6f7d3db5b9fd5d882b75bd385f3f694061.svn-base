package com.kdj.mlink.ezup3.data.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.kdj.mlink.ezup3.common.YDMAProgressBar;
import com.kdj.mlink.ezup3.common.YDMAProperties;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.CompInfoDao;
import com.kdj.mlink.ezup3.data.dao.ProductIforDao;
import com.kdj.mlink.ezup3.data.dao.ProductMstDao;
import com.kdj.mlink.ezup3.data.dao.ProductOptDao;
import com.kdj.mlink.ezup3.data.dao.ShoppingMallDetailDto;
import com.kdj.mlink.ezup3.shop.common.CodeItem;
import com.kdj.mlink.ezup3.shop.common.ShopCommon;
import com.kdj.mlink.ezup3.shop.dao.ShopOptionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopOptionProductInDto;
import com.kdj.mlink.ezup3.shop.dao.ShopOptionProductInfoDao;
import com.kdj.mlink.ezup3.shop.dao.ShopProduct11stAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;

public class YDWMXmlManager {

	public static void updateGetClaimListTemplate(String dateFrom, String dateTo) throws Exception {

		// template
		String templatePath = YDMASessonUtil
				.getAppPath(YDMAProperties.getInstance().getAppProperty("sabang.claimTemplatePath"));
		String templateFile = YDMAProperties.getInstance().getAppProperty("sabang.claimTempateFile");
		String templateFullpath = templatePath + File.separator + templateFile;

		BufferedReader br = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(templateFullpath);
		CompInfoDao compdao = new CompInfoDao();
		List<String> compInfo = compdao.getCompNoImage();

		Element root = document.getDocumentElement();
		root.normalize();

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		Node nodeCompayny_id = (Node) xpath.evaluate("/SABANG_ORDER_LIST/HEADER/SEND_COMPAYNY_ID", document,
				XPathConstants.NODE);
		Node nodeAuth_key = (Node) xpath.evaluate("/SABANG_ORDER_LIST/HEADER/SEND_AUTH_KEY", document,
				XPathConstants.NODE);
		Node nodeSendDay = (Node) xpath.evaluate("/SABANG_ORDER_LIST/HEADER/SEND_DATE", document, XPathConstants.NODE);
		Node nodeFrom = (Node) xpath.evaluate("/SABANG_ORDER_LIST/DATA/CLM_ST_DATE", document, XPathConstants.NODE);
		Node nodeTo = (Node) xpath.evaluate("/SABANG_ORDER_LIST/DATA/CLM_ED_DATE", document, XPathConstants.NODE);

		nodeCompayny_id.setTextContent(compInfo.get(2));
		nodeAuth_key.setTextContent(compInfo.get(3));
		nodeSendDay.setTextContent(YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd"));
		nodeFrom.setTextContent(dateFrom);
		nodeTo.setTextContent(dateTo);

		System.out.println("claim call date in xml : " + nodeSendDay.getTextContent());
		System.out.println("claim date in xml : " + dateFrom + " ~ " + dateTo);

		writeToXml(root, templateFullpath);
	}

	public static void updateGetOrderListTemplate(String dateFrom, String dateTo) throws Exception {

		// template
		String templatePath = YDMASessonUtil
				.getAppPath(YDMAProperties.getInstance().getAppProperty("sabang.orderTemplatePath"));
		String templateFile = YDMAProperties.getInstance().getAppProperty("sabang.orderTempateFile");
		String templateFullpath = templatePath + File.separator + templateFile;
		// 문자열에서 xml 파싱하는 경우
		// DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new
		// ByteArrayInputStream(xmlString.getBytes()));

		BufferedReader br = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(templateFullpath);
		CompInfoDao compdao = new CompInfoDao();
		List<String> compInfo = compdao.getCompNoImage();
		Element root = document.getDocumentElement();
		root.normalize();

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		Node nodeCompayny_id = (Node) xpath.evaluate("/SABANG_ORDER_LIST/HEADER/SEND_COMPAYNY_ID", document,
				XPathConstants.NODE);
		Node nodeAuth_key = (Node) xpath.evaluate("/SABANG_ORDER_LIST/HEADER/SEND_AUTH_KEY", document,
				XPathConstants.NODE);
		Node nodeSendDay = (Node) xpath.evaluate("/SABANG_ORDER_LIST/HEADER/SEND_DATE", document, XPathConstants.NODE);
		Node nodeFrom = (Node) xpath.evaluate("/SABANG_ORDER_LIST/DATA/ORD_ST_DATE", document, XPathConstants.NODE);
		Node nodeTo = (Node) xpath.evaluate("/SABANG_ORDER_LIST/DATA/ORD_ED_DATE", document, XPathConstants.NODE);
		Node order_status = (Node) xpath.evaluate("/SABANG_ORDER_LIST/DATA/ORDER_STATUS", document,
				XPathConstants.NODE);

		nodeCompayny_id.setTextContent(compInfo.get(2));
		nodeAuth_key.setTextContent(compInfo.get(3));
		nodeSendDay.setTextContent(YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd"));
		nodeFrom.setTextContent(dateFrom);
		nodeTo.setTextContent(dateTo);
		order_status.setTextContent("'002");

		System.out.println("order call date in xml : " + nodeSendDay.getTextContent());
		System.out.println("order date in xml : " + dateFrom + " ~ " + dateTo);

		writeToXml(root, templateFullpath);
	}

	public static void updateSendProductListTemplate(List<List<String>> contents, boolean isNew, Shell shell)
			throws Exception {

		// template
		String templatePath = YDMASessonUtil
				.getAppPath(YDMAProperties.getInstance().getAppProperty("sabang.productTemplatePath"));
		String templateFile = YDMAProperties.getInstance().getAppProperty("sabang.productTempateFile");
		String templateFullpath = templatePath + File.separator + templateFile;

		CompInfoDao compdao = new CompInfoDao();
		List<String> compInfo = compdao.getCompNoImage();
		BufferedReader br = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element ROOT = document.createElement("SABANG_GOODS_REGI");
		ROOT.normalize();

		Node HEADER = document.createElement("HEADER");
		ROOT.appendChild(HEADER);

		Node SEND_COMPAYNY_ID = document.createElement("SEND_COMPAYNY_ID");
		SEND_COMPAYNY_ID.appendChild(document.createCDATASection(compInfo.get(2)));
		HEADER.appendChild(SEND_COMPAYNY_ID);

		Node SEND_AUTH_KEY = document.createElement("SEND_AUTH_KEY");
		SEND_AUTH_KEY.appendChild(document.createCDATASection(compInfo.get(3)));
		HEADER.appendChild(SEND_AUTH_KEY);

		Node SEND_DATE = document.createElement("SEND_DATE");
		SEND_DATE.appendChild(document.createCDATASection(YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd")));
		HEADER.appendChild(SEND_DATE);

		Node SEND_GOODS_CD_RT = document.createElement("SEND_GOODS_CD_RT");
		SEND_GOODS_CD_RT.appendChild(document.createCDATASection("Y"));
		HEADER.appendChild(SEND_GOODS_CD_RT);

		Node RESULT_TYPE = document.createElement("RESULT_TYPE");
		RESULT_TYPE.appendChild(document.createCDATASection("XML"));
		HEADER.appendChild(RESULT_TYPE);
		for (List<String> list : contents) {
			// 추가
			ProductMstDao mdao = new ProductMstDao();
			// 상품명으로 mstdao에서 번호가지고오기
			List<String> mstlist = mdao.getNumberInforMationProperty(list.get(1));
			// 번호로 조회하기
			ProductIforDao idao = new ProductIforDao();

			if (mstlist.get(1) != null && mstlist.get(0) != null) {
				List<String> infodao = new ArrayList<>();
				infodao = idao.getOpen(mstlist.get(0));

				Node DATA = document.createElement("DATA");
				ROOT.appendChild(DATA);

				// [2, DWE107, 확장패널 20cm, 0, 0, null, 미등록]
				Node GOODS_NM = document.createElement("GOODS_NM");
				if (list.get(2).getBytes().length > 100) {
					byte[] strByte = list.get(2).getBytes();
					String newTitle = new String(strByte, 0, 100);
					GOODS_NM.appendChild(document.createCDATASection(newTitle));
				} else {
					GOODS_NM.appendChild(document.createCDATASection(list.get(2)));
				}

				DATA.appendChild(GOODS_NM);

				Node MODEL_NM = document.createElement("MODEL_NM");
				MODEL_NM.appendChild(document.createCDATASection(list.get(1)));
				DATA.appendChild(MODEL_NM);

				Node BRAND_NM = document.createElement("BRAND_NM");
				BRAND_NM.appendChild(document.createCDATASection(infodao.get(2)));
				DATA.appendChild(BRAND_NM);

				Node COMPAYNY_GOODS_CD = document.createElement("COMPAYNY_GOODS_CD");
				COMPAYNY_GOODS_CD.appendChild(document.createCDATASection(list.get(1)));
				DATA.appendChild(COMPAYNY_GOODS_CD);

				Node GOODS_SEARCH = document.createElement("GOODS_SEARCH");
				GOODS_SEARCH.appendChild(document.createCDATASection(list.get(12)));
				DATA.appendChild(GOODS_SEARCH);

				Node GOODS_GUBUN = document.createElement("GOODS_GUBUN");
				GOODS_GUBUN.appendChild(document.createCDATASection(infodao.get(3)));
				DATA.appendChild(GOODS_GUBUN);

				Node MAKER = document.createElement("MAKER");
				MAKER.appendChild(document.createCDATASection(infodao.get(4)));
				DATA.appendChild(MAKER);

				Node ORIGIN = document.createElement("ORIGIN");
				ORIGIN.appendChild(document.createCDATASection(infodao.get(19)));
				DATA.appendChild(ORIGIN);
				if (isNew) {// mst에서등록
					Node CLASS_CD1 = document.createElement("CLASS_CD1");
					if (list.get(13).length() < 3) {
						CLASS_CD1.appendChild(document.createCDATASection("NULL"));
					} else {
						CLASS_CD1.appendChild(document.createCDATASection(list.get(13)));
					}
					DATA.appendChild(CLASS_CD1);

					Node CLASS_CD2 = document.createElement("CLASS_CD2");
					if (list.get(14).length() < 3) {
						CLASS_CD2.appendChild(document.createCDATASection("NULL"));
					} else {
						CLASS_CD2.appendChild(document.createCDATASection(
								list.get(14).substring(list.get(14).length() - 3, list.get(14).length())));
					}
					DATA.appendChild(CLASS_CD2);

					Node CLASS_CD3 = document.createElement("CLASS_CD3");
					if (list.get(15).length() < 3) {
						CLASS_CD3.appendChild(document.createCDATASection("NULL"));
					} else {
						CLASS_CD3.appendChild(document.createCDATASection(
								list.get(15).substring(list.get(15).length() - 3, list.get(15).length())));
					}
					DATA.appendChild(CLASS_CD3);

					Node CLASS_CD4 = document.createElement("CLASS_CD4");
					if (list.get(16).length() < 3) {
						CLASS_CD4.appendChild(document.createCDATASection("NULL"));
					} else {
						CLASS_CD4.appendChild(document.createCDATASection(
								list.get(16).substring(list.get(16).length() - 3, list.get(16).length())));
					}
					DATA.appendChild(CLASS_CD4);
				} else {// 사방넷등록에서
					List<String> categ = mdao.getCategoryList(list.get(1));
					Node CLASS_CD1 = document.createElement("CLASS_CD1");
					if (categ.get(0).length() < 3) {
						CLASS_CD1.appendChild(document.createCDATASection("NULL"));
					} else {
						CLASS_CD1.appendChild(document.createCDATASection(categ.get(0)));
					}
					DATA.appendChild(CLASS_CD1);

					Node CLASS_CD2 = document.createElement("CLASS_CD2");
					if (categ.get(1).length() < 3) {
						CLASS_CD2.appendChild(document.createCDATASection("NULL"));
					} else {
						CLASS_CD2.appendChild(document.createCDATASection(
								categ.get(1).substring(categ.get(1).length() - 3, categ.get(1).length())));
					}
					DATA.appendChild(CLASS_CD2);

					Node CLASS_CD3 = document.createElement("CLASS_CD3");
					if (categ.get(2).length() < 3) {
						CLASS_CD3.appendChild(document.createCDATASection("NULL"));
					} else {
						CLASS_CD3.appendChild(document.createCDATASection(
								categ.get(2).substring(categ.get(2).length() - 3, categ.get(2).length())));
					}
					DATA.appendChild(CLASS_CD3);

					Node CLASS_CD4 = document.createElement("CLASS_CD4");
					if (categ.get(3).length() < 3) {
						CLASS_CD4.appendChild(document.createCDATASection("NULL"));
					} else {
						CLASS_CD4.appendChild(document.createCDATASection(
								categ.get(3).substring(categ.get(3).length() - 3, categ.get(3).length())));
					}
					DATA.appendChild(CLASS_CD4);
				}

				Node GOODS_SEASON = document.createElement("GOODS_SEASON");
				GOODS_SEASON.appendChild(document.createCDATASection(infodao.get(6)));
				DATA.appendChild(GOODS_SEASON);

				Node SEX = document.createElement("SEX");
				SEX.appendChild(document.createCDATASection(infodao.get(7)));
				DATA.appendChild(SEX);

				Node STATUS = document.createElement("STATUS");
				STATUS.appendChild(document.createCDATASection(infodao.get(8)));
				DATA.appendChild(STATUS);

				Node DELIV_ABLE_REGION = document.createElement("DELIV_ABLE_REGION");
				DELIV_ABLE_REGION.appendChild(document.createCDATASection(infodao.get(9)));
				DATA.appendChild(DELIV_ABLE_REGION);

				Node TAX_YN = document.createElement("TAX_YN");
				TAX_YN.appendChild(document.createCDATASection(infodao.get(12)));
				DATA.appendChild(TAX_YN);

				Node DELV_TYPE = document.createElement("DELV_TYPE");
				DELV_TYPE.appendChild(document.createCDATASection(infodao.get(10)));
				DATA.appendChild(DELV_TYPE);

				Node DELV_COST = document.createElement("DELV_COST");
				DELV_COST.appendChild(document.createCDATASection("'" + infodao.get(11)));
				DATA.appendChild(DELV_COST);

				Node GOODS_PRICE = document.createElement("GOODS_PRICE");
				GOODS_PRICE.appendChild(document.createCDATASection(list.get(4)));
				DATA.appendChild(GOODS_PRICE);

				Node GOODS_CONSUMER_PRICE = document.createElement("GOODS_CONSUMER_PRICE");
				GOODS_CONSUMER_PRICE.appendChild(document.createCDATASection(list.get(5)));
				DATA.appendChild(GOODS_CONSUMER_PRICE);

				Node CHAR_1_NM = document.createElement("CHAR_1_NM");
				CHAR_1_NM.appendChild(document.createCDATASection(infodao.get(14)));
				DATA.appendChild(CHAR_1_NM);

				Node CHAR_2_NM = document.createElement("CHAR_2_NM");
				CHAR_2_NM.appendChild(document.createCDATASection(infodao.get(15)));
				DATA.appendChild(CHAR_2_NM);

				Node CHAR_1_VAL = document.createElement("CHAR_1_VAL");

				// 옵션상세 명칭 2(사이즈) 결정을 위한 변수
				String char_2_val = "";
				String[] aaa = { "", "", "", "", "", "", "", "" };
				int num = 0;
				// 옵션체크가 1 이면 별도로
				if (!"1".equals(infodao.get(13))) {
					ProductOptDao dao = new ProductOptDao();
					List<List<String>> contents_opt = dao.getProdcutOptList(list.get(1));
					String char_1_val = "";
					String optsale = "";

					for (int i = 0; i < contents_opt.size(); i++) {
						List<String> list_opt = contents_opt.get(i);
						if (isNew) {
							if (list.get(17).equals("Y")) {
								optsale = "004";
							} else {
								if ("1".equals(list_opt.get(5))) { // optsale
									optsale = "002";
								} else if ("1".equals(list_opt.get(6))) {// optsaleout
									optsale = "004";
								} else if ("1".equals(list_opt.get(7))) {// optnotuse
									optsale = "005";
								}
							}
						} else {
							if (list.get(15).equals("Y")) {
								optsale = "004";
							} else {
								if ("1".equals(list_opt.get(5))) { // optsale
									optsale = "002";
								} else if ("1".equals(list_opt.get(6))) {// optsaleout
									optsale = "004";
								} else if ("1".equals(list_opt.get(7))) {// optnotuse
									optsale = "005";
								}
							}
						}

						String optprodcd = list_opt.get(1);
						String optprodnm = list_opt.get(2);
						String optspecdes = list_opt.get(3);
						String optea = list_opt.get(4);
						String optsafestock = list_opt.get(8);
						String optvertstock = list_opt.get(9);
						String optaddamt = list_opt.get(10);

						// 옵션상세 명칭 2(사이즈) 결정
						String optspecdes1 = optspecdes;
						String optspecdes2 = "";

						boolean flagfind = false;

						int idx = 0;
						idx = optspecdes.indexOf(":");
						if (idx > 0) {
							optspecdes1 = optspecdes.substring(0, idx);
							optspecdes2 = optspecdes.substring(idx + 1);
							char_1_val += optprodnm + "_" + optspecdes1 + "^^" + optvertstock + "^^" + optaddamt
									+ "^^^^" + optea + "^^" + optsale + "^^L^^^^" + optsafestock;
							for (String element : aaa) {
								if (element.equals(optspecdes2)) {
									flagfind = true;
									break;
								}
							}
							if (!flagfind) { // 2차원 규격이 없는경우만 추가
								aaa[num] = optspecdes2;
								char_2_val += optspecdes2 + "(" + optprodcd + ")";
								if (i < contents_opt.size() - 1)
									char_2_val += ","; // 마지막이면 컴마 불필요
								num++;
							}

						} else {
							char_1_val += optprodnm + "_" + optspecdes1 + "(" + optprodcd + ")^^" + optvertstock + "^^"
									+ optaddamt + "^^^^" + optea + "^^" + optsale + "^^L^^^^" + optsafestock;
						}

						if (i < contents_opt.size() - 1) {
							char_1_val += ",";
						}

					}
					CHAR_1_VAL.appendChild(document.createCDATASection(char_1_val));

				} else {

					String prodnm = list.get(2);
					String specdes = "";
					if (list.get(3) != null && list.get(3).trim().length() > 0) {
						specdes = "_" + list.get(3);
					}
					String prodcd = list.get(1);
					int len = YDMAStringUtil.length(prodnm);
					String newProdnm = prodnm;
					if (len > 20) {
						newProdnm = YDMAStringUtil.Char_substring(prodnm, 20);
					}

					String ea = list.get(7) == null ? "0" : list.get(7);
					String useyn = "";
					if (isNew) {
						useyn = list.get(17).equals("Y") ? "004" : "002";
					} else {
						useyn = list.get(15).equals("Y") ? "004" : "002";
					}

					String char_1_val = newProdnm + specdes + "(" + prodcd + ")^^999^^^^^^" + ea + "^^" + useyn
							+ "^^L^^^^";
					System.out.println(char_1_val);
					CHAR_1_VAL.appendChild(document.createCDATASection(char_1_val));

				}

				DATA.appendChild(CHAR_1_VAL);

				Node CHAR_2_VAL = document.createElement("CHAR_2_VAL");
				CHAR_2_VAL.appendChild(document.createCDATASection(char_2_val));
				DATA.appendChild(CHAR_2_VAL);

				Node IMG_PATH = document.createElement("IMG_PATH");
				IMG_PATH.appendChild(document.createCDATASection(list.get(9)));
				DATA.appendChild(IMG_PATH);

				Node IMG_PATH6 = document.createElement("IMG_PATH6");
				IMG_PATH6.appendChild(document.createCDATASection(list.get(10)));
				DATA.appendChild(IMG_PATH6);

				Node IMG_PATH7 = document.createElement("IMG_PATH7");
				IMG_PATH7.appendChild(document.createCDATASection(list.get(11)));
				DATA.appendChild(IMG_PATH7);

				Node GOODS_REMARKS = document.createElement("GOODS_REMARKS");
				String remark = list.get(6);
				GOODS_REMARKS.appendChild(document.createCDATASection(remark));
				DATA.appendChild(GOODS_REMARKS);

				Node STOCK_USE_YN = document.createElement("STOCK_USE_YN");
				if (infodao.get(16).equals("1")) {
					STOCK_USE_YN.appendChild(document.createCDATASection("N"));
				} else {// 추가
					STOCK_USE_YN.appendChild(document.createCDATASection("Y"));
				}
				DATA.appendChild(STOCK_USE_YN);

				Node OPT_TYPE = document.createElement("OPT_TYPE");
				if (infodao.get(17).equals("1")) {
					OPT_TYPE.appendChild(document.createCDATASection("2"));
				} else {
					OPT_TYPE.appendChild(document.createCDATASection("9"));
				}
				DATA.appendChild(OPT_TYPE);
				// 속성수정여부
				Node PROP_EDIT_YN = document.createElement("PROP_EDIT_YN");
				PROP_EDIT_YN.appendChild(document.createCDATASection("Y"));
				DATA.appendChild(PROP_EDIT_YN);
				// 여기서부터 추가
				Node PROP1_CD = document.createElement("PROP1_CD");
				PROP1_CD.appendChild(document.createCDATASection(mstlist.get(1)));
				DATA.appendChild(PROP1_CD);

				List<List<String>> attrvals = idao.getSbapiSelect(mstlist.get(1));
				for (List<String> attrval : attrvals) {
					if (attrval.get(1).equals("1") && !attrval.get(2).equals("")) {
						Node PROP_VAL1 = document.createElement("PROP_VAL1");
						PROP_VAL1.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL1);
					} else if (attrval.get(1).equals("2") && !attrval.get(2).equals("")) {
						Node PROP_VAL2 = document.createElement("PROP_VAL2");
						PROP_VAL2.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL2);
					} else if (attrval.get(1).equals("3") && !attrval.get(2).equals("")) {
						Node PROP_VAL3 = document.createElement("PROP_VAL3");
						PROP_VAL3.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL3);
					} else if (attrval.get(1).equals("4") && !attrval.get(2).equals("")) {
						Node PROP_VAL4 = document.createElement("PROP_VAL4");
						PROP_VAL4.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL4);
					} else if (attrval.get(1).equals("5") && !attrval.get(2).equals("")) {
						Node PROP_VAL5 = document.createElement("PROP_VAL5");
						PROP_VAL5.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL5);
					} else if (attrval.get(1).equals("6") && !attrval.get(2).equals("")) {
						Node PROP_VAL6 = document.createElement("PROP_VAL6");
						PROP_VAL6.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL6);
					} else if (attrval.get(1).equals("7") && !attrval.get(2).equals("")) {
						Node PROP_VAL7 = document.createElement("PROP_VAL7");
						PROP_VAL7.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL7);
					} else if (attrval.get(1).equals("8") && !attrval.get(2).equals("")) {
						Node PROP_VAL8 = document.createElement("PROP_VAL8");
						PROP_VAL8.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL8);
					} else if (attrval.get(1).equals("9") && !attrval.get(2).equals("")) {
						Node PROP_VAL9 = document.createElement("PROP_VAL9");
						PROP_VAL9.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL9);
					} else if (attrval.get(1).equals("10") && !attrval.get(2).equals("")) {
						Node PROP_VAL10 = document.createElement("PROP_VAL10");
						PROP_VAL10.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL10);
					} else if (attrval.get(1).equals("11") && !attrval.get(2).equals("")) {
						Node PROP_VAL11 = document.createElement("PROP_VAL11");
						PROP_VAL11.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL11);
					} else if (attrval.get(1).equals("12") && !attrval.get(2).equals("")) {
						Node PROP_VAL12 = document.createElement("PROP_VAL12");
						PROP_VAL12.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL12);
					} else if (attrval.get(1).equals("13") && !attrval.get(2).equals("")) {
						Node PROP_VAL13 = document.createElement("PROP_VAL13");
						PROP_VAL13.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL13);
					} else if (attrval.get(1).equals("14") && !attrval.get(2).equals("")) {
						Node PROP_VAL14 = document.createElement("PROP_VAL14");
						PROP_VAL14.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL14);
					} else if (attrval.get(1).equals("15") && !attrval.get(2).equals("")) {
						Node PROP_VAL15 = document.createElement("PROP_VAL15");
						PROP_VAL15.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL15);
					} else if (attrval.get(1).equals("16") && !attrval.get(2).equals("")) {
						Node PROP_VAL16 = document.createElement("PROP_VAL16");
						PROP_VAL16.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL16);
					} else if (attrval.get(1).equals("17") && !attrval.get(2).equals("")) {
						Node PROP_VAL17 = document.createElement("PROP_VAL17");
						PROP_VAL17.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL17);
					} else if (attrval.get(1).equals("18") && !attrval.get(2).equals("")) {
						Node PROP_VAL18 = document.createElement("PROP_VAL18");
						PROP_VAL18.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL18);
					} else if (attrval.get(1).equals("19") && !attrval.get(2).equals("")) {
						Node PROP_VAL19 = document.createElement("PROP_VAL19");
						PROP_VAL19.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL19);
					} else if (attrval.get(1).equals("20") && !attrval.get(2).equals("")) {
						Node PROP_VAL20 = document.createElement("PROP_VAL20");
						PROP_VAL20.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL20);
					} else if (attrval.get(1).equals("21") && !attrval.get(2).equals("")) {
						Node PROP_VAL21 = document.createElement("PROP_VAL21");
						PROP_VAL21.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL21);
					} else if (attrval.get(1).equals("22") && !attrval.get(2).equals("")) {
						Node PROP_VAL22 = document.createElement("PROP_VAL22");
						PROP_VAL22.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL22);
					} else if (attrval.get(1).equals("23") && !attrval.get(2).equals("")) {
						Node PROP_VAL23 = document.createElement("PROP_VAL23");
						PROP_VAL23.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL23);
					} else if (attrval.get(1).equals("24") && !attrval.get(2).equals("")) {
						Node PROP_VAL24 = document.createElement("PROP_VAL24");
						PROP_VAL24.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL24);
					} else if (attrval.get(1).equals("25") && !attrval.get(2).equals("")) {
						Node PROP_VAL25 = document.createElement("PROP_VAL25");
						PROP_VAL25.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL25);
					} else if (attrval.get(1).equals("26") && !attrval.get(2).equals("")) {
						Node PROP_VAL26 = document.createElement("PROP_VAL26");
						PROP_VAL26.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL26);
					} else if (attrval.get(1).equals("27") && !attrval.get(2).equals("")) {
						Node PROP_VAL27 = document.createElement("PROP_VAL27");
						PROP_VAL27.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL27);
					} else if (attrval.get(1).equals("28") && !attrval.get(2).equals("")) {
						Node PROP_VAL28 = document.createElement("PROP_VAL28");
						PROP_VAL28.appendChild(document.createCDATASection(attrval.get(2)));
						DATA.appendChild(PROP_VAL28);
					}

				} // 내용물포문
			} else {
				MessageDialog.openInformation(shell, "사방넷전송", "정보를 모두 입력후 진행하여 주시기 바랍니다");
			}

		} //// 처음for문

//		TransformerFactory transFactory = TransformerFactory.newInstance();
//		Transformer transformer = transFactory.newTransformer();
//		transformer.setOutputProperty(OutputKeys.ENCODING, "euc-kr");
//		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//		DOMSource source = new DOMSource(ROOT);
//		StreamResult result = new StreamResult(new File(templateFullpath));
//		transformer.transform(source, result);

		writeToXml(ROOT, templateFullpath);

	}

	// 송장xml
	public static void updateSendPickupExpressSabangNetTemplate(List<List<String>> contents, Shell shell)
			throws Exception {

		// template
		String templatePath = YDMASessonUtil
				.getAppPath(YDMAProperties.getInstance().getAppProperty("sabang.pickupExpressSabangNetTemplatePath"));
		String templateFile = YDMAProperties.getInstance().getAppProperty("sabang.pickupExpressSabangNetTempateFile");
		String templateFullpath = templatePath + File.separator + templateFile;

		CompInfoDao compdao = new CompInfoDao();
		List<String> compInfo = compdao.getCompNoImage();
		BufferedReader br = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element ROOT = document.createElement("SABANG_INV_REGI");
		ROOT.normalize();

		Node HEADER = document.createElement("HEADER");
		ROOT.appendChild(HEADER);

		Node SEND_COMPAYNY_ID = document.createElement("SEND_COMPAYNY_ID");
		SEND_COMPAYNY_ID.appendChild(document.createTextNode(compInfo.get(2)));
		HEADER.appendChild(SEND_COMPAYNY_ID);

		Node SEND_AUTH_KEY = document.createElement("SEND_AUTH_KEY");
		SEND_AUTH_KEY.appendChild(document.createTextNode(compInfo.get(3)));
		HEADER.appendChild(SEND_AUTH_KEY);

		Node SEND_DATE = document.createElement("SEND_DATE");
		SEND_DATE.appendChild(document.createTextNode(YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd")));
		HEADER.appendChild(SEND_DATE);

		Node SEND_GOODS_CD_RT = document.createElement("SEND_INV_EDIT_YN");
		SEND_GOODS_CD_RT.appendChild(document.createTextNode("Y"));
		HEADER.appendChild(SEND_GOODS_CD_RT);

		for (List<String> list : contents) {
			if (!list.get(38).equals("") && !list.get(39).equals("")) {
				Node DATA = document.createElement("DATA");
				ROOT.appendChild(DATA);

				Node SABANGNET_IDX = document.createElement("SABANGNET_IDX");
				SABANGNET_IDX.appendChild(document.createCDATASection(list.get(16)));
				DATA.appendChild(SABANGNET_IDX);

				Node TAK_CODE = document.createElement("TAK_CODE");
				if (!list.get(38).equals("")) {
					TAK_CODE.appendChild(document.createCDATASection(
							list.get(38).substring(list.get(38).length() - 3, list.get(38).length())));
				} else {
					MessageDialog.openInformation(shell, "송장전송", "택배업체가 입력되어  않습니다. 확인후 다시 진행하여 주시기 바랍니다.");
				}
				DATA.appendChild(TAK_CODE);

				Node TAK_INVOICE = document.createElement("TAK_INVOICE");
				if (!list.get(39).equals("")) {
					TAK_INVOICE.appendChild(document.createCDATASection(list.get(39)));
				} else {
					MessageDialog.openInformation(shell, "송장전송", "송장번호가 입력되어 있지않습니다. 확인후 다시 진행하여 주시기 바랍니다.");
				}

				DATA.appendChild(TAK_INVOICE);

				Node DELV_HOPE_DATE = document.createElement("DELV_HOPE_DATE");
				DELV_HOPE_DATE.appendChild(document.createCDATASection(""));
				DATA.appendChild(DELV_HOPE_DATE);
			} else {
				MessageDialog.openInformation(shell, "송장전송", "택배업체 또는 송장번호가 작성하시고 진행하시기 바랍니다.");
				return;
			}

		} //// 처음for문
		writeToXml(ROOT, templateFullpath);

	}

	// 문의사항
	public static void SendQuestionTemplate(List<List<String>> contents) throws Exception {

		// template
		String templatePath = YDMASessonUtil
				.getAppPath(YDMAProperties.getInstance().getAppProperty("sabang.questionTemplatePath"));
		String templateFile = YDMAProperties.getInstance().getAppProperty("sabang.questionTempateFile");
		String templateFullpath = templatePath + File.separator + templateFile;
		CompInfoDao compdao = new CompInfoDao();
		List<String> compInfo = compdao.getCompNoImage();
		BufferedReader br = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element ROOT = document.createElement("SABANG_CS_ANS_REGI");
		ROOT.normalize();

		Node HEADER = document.createElement("HEADER");
		ROOT.appendChild(HEADER);

		Node SEND_COMPAYNY_ID = document.createElement("SEND_COMPAYNY_ID");
		SEND_COMPAYNY_ID.appendChild(document.createCDATASection(compInfo.get(2)));
		HEADER.appendChild(SEND_COMPAYNY_ID);

		Node SEND_AUTH_KEY = document.createElement("SEND_AUTH_KEY");
		SEND_AUTH_KEY.appendChild(document.createCDATASection(compInfo.get(3)));
		HEADER.appendChild(SEND_AUTH_KEY);

		Node SEND_DATE = document.createElement("SEND_DATE");
		SEND_DATE.appendChild(document.createCDATASection(YDMATimeUtil.getCurrentTimeByFreeFormat("yyyyMMdd")));
		HEADER.appendChild(SEND_DATE);

		for (List<String> list : contents) {
			Node DATA = document.createElement("DATA");
			ROOT.appendChild(DATA);
			Node NUM = document.createElement("NUM");
			NUM.appendChild(document.createCDATASection(list.get(19)));
			DATA.appendChild(NUM);
			Node CS_RE_CONTENT = document.createElement("CS_RE_CONTENT");
			CS_RE_CONTENT.appendChild(document.createCDATASection(list.get(13)));
			DATA.appendChild(CS_RE_CONTENT);
		}

		writeToXml(ROOT, templateFullpath);
	}

	public static List<List<String>> parseResponseProduct(String respose) throws Exception {

		List<List<String>> resultContents = new ArrayList<>();

		BufferedReader br = null;
		// DocumentBuilderFactory 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		try {

			// xml 파싱하기
			InputSource is = new InputSource(new StringReader(respose));
			builder = factory.newDocumentBuilder();
			doc = builder.parse(is);
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();

			XPathExpression expr = xpath.compile("SABANG_RESULT/DATA");
			NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node dataNode = nodeList.item(i);

				if (dataNode.getNodeType() == 1) {
					List<String> list = new ArrayList<>();
					NodeList dataChildnodeList = dataNode.getChildNodes();
					for (int j = 0; j < dataChildnodeList.getLength(); j++) {
						Node dataChilNode = dataChildnodeList.item(j);
						if (dataChilNode.getNodeType() == 1) {
//							System.out.println("*현재 노드 이름 : " + dataChilNode.getNodeName());
//							// System.out.println("*현재 노드 타입 : " + node.getNodeType());
//							System.out.println("*현재 노드 값 : " + dataChilNode.getTextContent());
//							// System.out.println("*현재 노드 네임스페이스 : " + node.getPrefix());
//							// System.out.println("*현재 노드의 다음 노드 : " + node.getNextSibling());
//							System.out.println("");
							list.add(dataChilNode.getTextContent());
						}
					}
					resultContents.add(list);
				}

			}

		} catch (Exception e) {
			throw e;
		}

		return resultContents;
	}

	// 11번가
	public static List<List<String>> parseResponseProduct11st(String respose, String prodcd) throws Exception {

		List<List<String>> resultContents = new ArrayList<>();
		List<String> list = new ArrayList<>();
		BufferedReader br = null;
		// DocumentBuilderFactory 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		try {

			// xml 파싱하기
			InputSource is = new InputSource(new StringReader(respose));
			builder = factory.newDocumentBuilder();
			doc = builder.parse(is);
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();

			XPathExpression expr = xpath.compile("ClientMessage");
			NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node dataNode = nodeList.item(i);
				list.add(prodcd);
				if (dataNode.getNodeType() == 1) {

					NodeList dataChildnodeList = dataNode.getChildNodes();
					for (int j = 0; j < dataChildnodeList.getLength(); j++) {
						Node dataChilNode = dataChildnodeList.item(j);
						if (dataChilNode.getNodeType() == 1) {
							list.add(dataChilNode.getTextContent());
						}
					}
					resultContents.add(list);
				}

			}

		} catch (Exception e) {
			throw e;
		}

		return resultContents;
	}

	// 11번가
	public static ShopProductDto parseResponseProductInsert11st(String respose, String prodcd, ShopProductDto dto,
			String shopinfo) throws Exception {

		List<List<String>> resultContents = new ArrayList<>();

		BufferedReader br = null;
		// DocumentBuilderFactory 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		try {

			// xml 파싱하기
			InputSource is = new InputSource(new StringReader(respose));
			builder = factory.newDocumentBuilder();
			doc = builder.parse(is);
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();

			XPathExpression expr = xpath.compile("ClientMessage");
			NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				List<String> list = new ArrayList<>();
				NodeList child = nodeList.item(i).getChildNodes();
				list.add(prodcd);
				for (int j = 0; j < child.getLength(); j++) {
					Node node = child.item(j);
					list.add(node.getTextContent());

				}
				resultContents.add(list);
			}
			for (List<String> content : resultContents) {
				if (content.size() == 5) {
					if (content.get(4).equals("500")) {
						dto.setResult_code(ShopCommon.에러발생);
						dto.setResult_text(content.get(1));
						throw new Exception(content.get(1));
					} else {
						dto.setResult_code(ShopCommon.정상처리);
						dto.setResult_text(content.get(1));
						dto.setShopprodno(content.get(3));
						YDMAProgressBar.get().setValue(shopinfo + "[" + dto.getCompayny_goods_cd() + "]-"
								+ dto.getGoods_nm() + " 상품이 등록되었습니다.", 100);

					}
				} else {
					if (content.get(3).equals("500")) {
						dto.setResult_code(ShopCommon.에러발생);
						dto.setResult_text(content.get(1));
						throw new Exception(content.get(1));
					} else {
						dto.setResult_code(ShopCommon.정상처리);
						dto.setResult_text(content.get(1));
						dto.setShopprodno(content.get(3));
						YDMAProgressBar.get().setValue(shopinfo + "[" + dto.getCompayny_goods_cd() + "]-"
								+ dto.getGoods_nm() + " 상품이 등록되었습니다.", 100);
					}
				}

			}
//			XPathExpression expr = xpath.compile("ClientMessage");
//			NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
//			for (int i = 0; i < nodeList.getLength(); i++) {
//				Node dataNode = nodeList.item(i);
//				list.add(prodcd);
//				if (dataNode.getNodeType() == 1) {
//
//					NodeList dataChildnodeList = dataNode.getChildNodes();
//					for (int j = 0; j < dataChildnodeList.getLength(); j++) {
//						Node dataChilNode = dataChildnodeList.item(j);
//						if (dataChilNode.getNodeType() == 1) {
//							list.add(dataChilNode.getTextContent());
//						}
//					}
//					resultContents.add(list);
//				}
//
//			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return dto;
	}

	public static void writeToXml(Element root, String filePath) throws Exception {
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "euc-kr");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(root);
		StreamResult result = new StreamResult(new File(filePath));
		transformer.transform(source, result);// 널포인트나옴
	}

	// 11번가
	public static void updateSendProductList11stTemplate(ShopProductDto dto, ShopProduct11stAdditionDto dtllist,
			ShoppingMallDetailDto idlist) throws Exception {
		// template
		String templatePath = YDMASessonUtil
				.getAppPath(YDMAProperties.getInstance().getAppProperty("11st.productListTemplatePath"));
		String templateFile = YDMAProperties.getInstance().getAppProperty("11st.productListTempateFile");
		String templateFullpath = templatePath + File.separator + templateFile;

		BufferedReader br = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element ROOT = document.createElement("Product");
		ROOT.normalize();

		Node selMthdCd = document.createElement("selMthdCd");
		selMthdCd.appendChild(document.createCDATASection(dtllist.getSelmthdcd()));// 01고정가판매
		ROOT.appendChild(selMthdCd);

		Node dispCtgrNo = document.createElement("dispCtgrNo");
		// dispCtgrNo.appendChild(document.createCDATASection("1006622"));//카테고리코드
		String categ = !dto.getShopCatInDto().getShopdetcatcd().equals("") ? dto.getShopCatInDto().getShopdetcatcd()
				: !dto.getShopCatInDto().getShopsmlcatcd().equals("") ? dto.getShopCatInDto().getShopsmlcatcd()
						: !dto.getShopCatInDto().getShopmidcatcd().equals("") ? dto.getShopCatInDto().getShopmidcatcd()
								: dto.getShopCatInDto().getShoplagcatcd();
		dispCtgrNo.appendChild(document.createCDATASection(categ));// 카테고리코드
		ROOT.appendChild(dispCtgrNo);

		Node prdNm = document.createElement("prdNm");
		if (dto.getGoods_nm().getBytes().length > 50) {
			byte[] strByte = dto.getGoods_nm().getBytes();
			String newTitle = new String(strByte, 0, 50);
			prdNm.appendChild(document.createCDATASection(newTitle));// 상품명
		} else {
			prdNm.appendChild(document.createCDATASection(dto.getGoods_nm()));// 상품명
		}
		ROOT.appendChild(prdNm);

		Node brand = document.createElement("brand");
		brand.appendChild(document.createCDATASection(dto.getBrand_nm()));// 브랜드
		ROOT.appendChild(brand);

		Node mktPrdNm = document.createElement("mktPrdNm");
		if (dto.getGoods_search().getBytes().length > 50) {
			byte[] strByte = dto.getGoods_search().getBytes();
			String newTitle = new String(strByte, 0, 50);
			mktPrdNm.appendChild(document.createCDATASection(newTitle));// 홍보상품명필수아님50바이트(사이트검색어넣음)
		} else {
			mktPrdNm.appendChild(document.createCDATASection(dto.getGoods_search()));// 홍보상품명필수아님50바이트(사이트검색어넣음)
		}
		ROOT.appendChild(mktPrdNm);

		Node orgnTypCd = document.createElement("orgnTypCd");
		String orgin = "03";
		if (dto.getOrigin().equals("국내산") || dto.getOrigin().equals("대한민국")) {
			orgin = "01";
		}
		orgnTypCd.appendChild(document.createCDATASection(orgin));// 필수아님01국내02해외03기타 원산지코드
		ROOT.appendChild(orgnTypCd);
		if (orgin.equals("03")) {
			Node orgnNmVal = document.createElement("orgnNmVal");
			orgnNmVal.appendChild(document.createCDATASection(dto.getOrigin()));// 상세설명서참조 원산지명 O
			ROOT.appendChild(orgnNmVal);
		}

		Node suplDtyfrPrdClfCd = document.createElement("suplDtyfrPrdClfCd");
		suplDtyfrPrdClfCd.appendChild(document.createCDATASection("0" + dto.getTax_yn()));// 01과세02면세03영세 부가세 O
		ROOT.appendChild(suplDtyfrPrdClfCd);

		Node prdStatCd = document.createElement("prdStatCd");
		prdStatCd.appendChild(document.createCDATASection(dtllist.getProdstatcd()));// 새상품01 상품상태 O
		ROOT.appendChild(prdStatCd);

		Node minorSelCnYn = document.createElement("minorSelCnYn");
		minorSelCnYn.appendChild(document.createCDATASection(dtllist.getMinorselcnyn()));// y가능 미성년자구매가능
		ROOT.appendChild(minorSelCnYn);

		Node prdImage01 = document.createElement("prdImage01");
		prdImage01.appendChild(document.createCDATASection(dto.getImg_path()));// 대표이미지
		// prdImage01.appendChild(document.createCDATASection(list.get(9)));//대표이미지
		ROOT.appendChild(prdImage01);

		Node prdImage02 = document.createElement("prdImage02");
		prdImage02.appendChild(document.createCDATASection(dto.getImg_path1()));// 추가이미지1
		ROOT.appendChild(prdImage02);

		Node prdImage03 = document.createElement("prdImage03");
		prdImage03.appendChild(document.createCDATASection(dto.getImg_path2()));// 추가이미지2
		ROOT.appendChild(prdImage03);

		Node htmlDetail = document.createElement("htmlDetail");
		htmlDetail.appendChild(document.createCDATASection(dto.getGoods_remarks()));// 상세이미지
		ROOT.appendChild(htmlDetail);

		Node selTermUseYn = document.createElement("selTermUseYn");
		selTermUseYn.appendChild(document.createCDATASection(dtllist.getBndyn()));// 밑에 기간이 설정되어 있으면 Y 아니면 N
		ROOT.appendChild(selTermUseYn);

		Node selPrdClfCd = document.createElement("selPrdClfCd");
		selPrdClfCd.appendChild(document.createCDATASection(dtllist.getProdfpselcd()));// 3개월은 90:107 판매기간코드 120:108
		ROOT.appendChild(selPrdClfCd);

		Node aplBgnDy = document.createElement("aplBgnDy");
		String strCurrentDate = YDMATimeUtil.getCurrentTimeByFreeFormat("yyyy/MM/dd");
		aplBgnDy.appendChild(document.createCDATASection(strCurrentDate));// 3개월은 90:107 판매기간코드 120:108
		ROOT.appendChild(aplBgnDy);

		Node selPrc = document.createElement("selPrc");
		selPrc.appendChild(document.createCDATASection(dto.getGoods_price()));// 판매가
		ROOT.appendChild(selPrc);

		Node prdSelQty = document.createElement("prdSelQty");
		prdSelQty.appendChild(document.createCDATASection("1"));// 재고수량입력해야됨
		ROOT.appendChild(prdSelQty);

		Node dlvCnAreaCd = document.createElement("dlvCnAreaCd");

		String areaCd = YDMAStringUtil.leftPad(dto.getDeliv_able_region(), 2, "0");
		dlvCnAreaCd.appendChild(document.createCDATASection(areaCd));// → 01 : 전국→ 02 : *전국(제주
																		// 도서산간지역 제외)→ 03 : 서울→ 26 :
																		// 일부지역불가
		ROOT.appendChild(dlvCnAreaCd);

		Node dlvCstInstBasiCd = document.createElement("dlvCstInstBasiCd");
		dlvCstInstBasiCd.appendChild(document.createCDATASection(dtllist.getShiptypcd()));// 01무료02고정배송비03상품변조건부04수량별차등
																							// 배송비종류
		ROOT.appendChild(dlvCstInstBasiCd);
		if (dtllist.getShiptypcd().equals("03")) {
			Node PrdFrDlvBasiAmt = document.createElement("PrdFrDlvBasiAmt");
			PrdFrDlvBasiAmt.appendChild(document.createCDATASection(dtllist.getCondiprice()));// 조건부무료배송일때 무료기준금액
			ROOT.appendChild(PrdFrDlvBasiAmt);
		}
		if (dtllist.getShiptypcd().equals("02") || dtllist.getShiptypcd().equals("03")) {// 고정배송비일대배송비//조건부무료일때배송비//
			Node dlvCst1 = document.createElement("dlvCst1");
			dlvCst1.appendChild(document.createCDATASection(dtllist.getShipprc())); // 금액
			ROOT.appendChild(dlvCst1);
		} else if (dtllist.getShiptypcd().equals("04")) {// 수량차등
			// dlvCstInstBasiCd>04</dlvCstInstBasiCd>
			// <dlvCst3>5000^1000^0</dlvCst3>
			// <dlvCnt1>1^10^100</dlvCnt1><dlvCnt2>9^99</dlvCnt2>
			Node dlvCst1 = document.createElement("dlvCst3");
			dlvCst1.appendChild(document.createCDATASection(dtllist.getShipprc())); // 금액
			ROOT.appendChild(dlvCst1);
		} else if (dtllist.getShiptypcd().equals("05")) {// 1개당 배송비
			Node dlvCst1 = document.createElement("dlvCst4");
			dlvCst1.appendChild(document.createCDATASection(dtllist.getShipprc())); // 금액
			ROOT.appendChild(dlvCst1);
		}

		Node bndlDlvCnYn = document.createElement("bndlDlvCnYn");
		bndlDlvCnYn.appendChild(document.createCDATASection(dtllist.getBndyn()));// 묶음배송여부 y가능
		ROOT.appendChild(bndlDlvCnYn);

		Node jejuDlvCst = document.createElement("jejuDlvCst");
		jejuDlvCst.appendChild(document.createCDATASection(dtllist.getJejuprc()));// 제주추가배송비
		ROOT.appendChild(jejuDlvCst);

		Node islandDlvCst = document.createElement("islandDlvCst");
		islandDlvCst.appendChild(document.createCDATASection(dtllist.getIslandprc())); // 도서산간추가배송비
		ROOT.appendChild(islandDlvCst);

		Node dlvCstPayTypCd = document.createElement("dlvCstPayTypCd");
		dlvCstPayTypCd.appendChild(document.createCDATASection(dtllist.getPrctypcd()));// 결제방법01선결제02선결제불가03선결제필수
		ROOT.appendChild(dlvCstPayTypCd);

		Node rtngdDlvCst = document.createElement("rtngdDlvCst");
		rtngdDlvCst.appendChild(document.createCDATASection(dtllist.getRetprc()));// 반품배송비
		ROOT.appendChild(rtngdDlvCst);

		Node exchDlvCst = document.createElement("exchDlvCst");
		exchDlvCst.appendChild(document.createCDATASection(dtllist.getExcprc()));// 교환배송비
		ROOT.appendChild(exchDlvCst);

		Node rtngExchDetail = document.createElement("rtngExchDetail");
		rtngExchDetail.appendChild(document.createCDATASection(dtllist.getRtexcdtl()));// 상세상품페이지 반품교환안내
		ROOT.appendChild(rtngExchDetail);

		Node dlvCstInfoCd = document.createElement("dlvCstInfoCd");
		dlvCstInfoCd.appendChild(document.createCDATASection("01"));// 배송비필수 아님고정 배송비(02) 배송비 추가 안내 적용조건
																	// :전세계배송(N),선결제불가(02) 01(상품상세참고)02(상품별 차등 적용)03(지역별
																	// 차등 적용)→ 04(상품/지역별 차등)→ 06(서울/경기 무료, 이외 추가비용)
		ROOT.appendChild(dlvCstInfoCd);

		Node asDetail = document.createElement("asDetail");
		asDetail.appendChild(document.createCDATASection(dtllist.getAsdtl())); // 에이에스안내 상품상세페이지
		ROOT.appendChild(asDetail);

		Node company = document.createElement("company");
		company.appendChild(document.createCDATASection(dto.getMaker()));// 제조사필수아님 없으면없음
		ROOT.appendChild(company);

		Node modelNm = document.createElement("modelNm");
		modelNm.appendChild(document.createCDATASection(dto.getModel_nm()));// 없으면없음예시라인셔츠FEFGHR4654이런식
		ROOT.appendChild(modelNm);
		// 여기서부터 옵션
		String opt = dto.getChar_1_nm().equals("단품") ? "N" : "Y";
		Node optSelectYn = document.createElement("optSelectYn");
		optSelectYn.appendChild(document.createCDATASection(opt));// 옵션없을시 밑에내용다삭제
		ROOT.appendChild(optSelectYn);

		// 위에께 와이면 필요
		if (opt.equals("Y")) {
			List<ShopOptionDto> options = getProductOptions(dto);
			// 종류별로 포문적용
			List<CodeItem> items = options.stream().map(p -> p.getName().split(":"))
					.map(p -> p.length == 2 ? new CodeItem(p[1], p[0]) : new CodeItem("", p[0]))
					.collect(Collectors.toList());
			// 2차원이면 포문 아니면 그냥
			if (dto.getChar_2_nm().equals("")) {
				Node ProductRootOption = document.createElement("ProductRootOption");
				ROOT.appendChild(ProductRootOption);

				Node colTitle = document.createElement("colTitle");
				colTitle.appendChild(document.createCDATASection(dto.getChar_1_nm()));// 색상 사이증 등
				ProductRootOption.appendChild(colTitle);

				Node txtColCnt = document.createElement("txtColCnt");
				txtColCnt.appendChild(document.createCDATASection("1"));// 옵션등록할경우 무조건1
				ROOT.appendChild(txtColCnt);

				for (int i = 0; i < items.size(); i++) {
					ShopOptionDto option = options.get(i);

					Node ProductOption = document.createElement("ProductOption");
					ProductRootOption.appendChild(ProductOption);

					Node colOptPrice = document.createElement("colOptPrice");
					colOptPrice.appendChild(document.createCDATASection(option.getAmt()));// 금액
					ProductOption.appendChild(colOptPrice);

					Node colValue0 = document.createElement("colValue0");
					colValue0.appendChild(document.createCDATASection(option.getName()));// 차이나롱자켓_블루:105
					ProductOption.appendChild(colValue0);

					Node colCount = document.createElement("colCount");
					colCount.appendChild(document.createCDATASection(option.getCnt()));// 수량
					ProductOption.appendChild(colCount);
				}
			} else {
				Node ProductRootOption = document.createElement("ProductRootOption");
				ROOT.appendChild(ProductRootOption);

				Node colTitle = document.createElement("colTitle");
				colTitle.appendChild(document.createCDATASection(dto.getChar_1_nm() + ":" + dto.getChar_2_nm()));// 색상
				ProductRootOption.appendChild(colTitle);

				Node txtColCnt = document.createElement("txtColCnt");
				txtColCnt.appendChild(document.createCDATASection("1"));// 옵션등록할경우 무조건1
				ROOT.appendChild(txtColCnt);

				for (int i = 0; i < items.size(); i++) {
					ShopOptionDto option = options.get(i);
					Node ProductOption = document.createElement("ProductOption");
					ProductRootOption.appendChild(ProductOption);

					Node colOptPrice = document.createElement("colOptPrice");
					colOptPrice.appendChild(document.createCDATASection(option.getAmt()));// 금액
					ProductOption.appendChild(colOptPrice);

					Node colValue0 = document.createElement("colValue0");
					colValue0.appendChild(document.createCDATASection(option.getName()));// 차이나롱자켓_블루:105
					ProductOption.appendChild(colValue0);

					Node colCount = document.createElement("colCount");
					colCount.appendChild(document.createCDATASection(option.getCnt()));// 수량
					ProductOption.appendChild(colCount);
				}

			}

		}

		//
		Node ProductNotification = document.createElement("ProductNotification");
		ROOT.appendChild(ProductNotification);
		// 속성정보 가지고 오기
		ProductIforDao dao = new ProductIforDao();
		List<List<String>> prodattrnm = dao.getProductAttrnm(dto.getProp1_cd());
		List<List<String>> prodattrval = dao.getSelectViewList(dto.getProp1_cd());
		Node type = document.createElement("type");
		type.appendChild(document.createCDATASection(prodattrnm.get(0).get(3)));// 고시유형코드
		ProductNotification.appendChild(type);
		for (int i = 0; i < prodattrnm.size(); i++) {
			if (!prodattrnm.get(i).get(4).equals("")) {
				if (prodattrnm.get(i).get(4).contains(",")) {
					String[] attrname = prodattrnm.get(i).get(4).split(",");
					for (String element : attrname) {
						Node item = document.createElement("item");
						ProductNotification.appendChild(item);

						Node code = document.createElement("code");
						code.appendChild(document.createCDATASection(element));// 항목코드
						item.appendChild(code);

						Node name = document.createElement("name");
						name.appendChild(document.createCDATASection(prodattrval.get(i).get(2)));// 날짜형식 상품상세설명 참조
						item.appendChild(name);
					}

				} else {
					Node item = document.createElement("item");
					ProductNotification.appendChild(item);

					Node code = document.createElement("code");
					code.appendChild(document.createCDATASection(prodattrnm.get(i).get(4)));// 항목코드
					item.appendChild(code);

					Node name = document.createElement("name");
					name.appendChild(document.createCDATASection(prodattrval.get(i).get(2)));// 날짜형식 상품상세설명 참조
					item.appendChild(name);
				}

			}

		}

		writeToXml(ROOT, templateFullpath);

	}

	public static Hashtable getDistinct(String[] arr) {
		Hashtable ht = new Hashtable();
		int j = 0;
		for (String val : arr) {

			if (ht.containsKey(val)) {
				j = 0;

				ht.put(val, ++j);
			} else {
				ht.put(val, ++j);
			}
		}
		return ht;

	}

	// 다중상품조회
	public static void updateSendShoppingMallSearchProduct(String shopnm, String recvdtFrom, String recvdtTo,
			String num, String search, String type, String prodcd, Shell shell) throws Exception {
		// template
		String templatePath = YDMASessonUtil
				.getAppPath(YDMAProperties.getInstance().getAppProperty("11st.productSearchTemplatePath"));
		String templateFile = YDMAProperties.getInstance().getAppProperty("11st.productSearchTempateFile");
		String templateFullpath = templatePath + File.separator + templateFile;

		CompInfoDao compdao = new CompInfoDao();
		List<String> compInfo = compdao.getCompNoImage();
		BufferedReader br = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element ROOT = document.createElement("SearchProduct");
		ROOT.normalize();

		// 추가
		ProductMstDao mdao = new ProductMstDao();

		Node selMthdCd = document.createElement("selMthdCd");
		selMthdCd.appendChild(document.createCDATASection("01"));
		ROOT.appendChild(selMthdCd);

		Node schBgnDt = document.createElement("schBgnDt");
		schBgnDt.appendChild(document.createCDATASection(recvdtFrom));
		ROOT.appendChild(schBgnDt);

		Node schEndDt = document.createElement("schEndDt");
		schEndDt.appendChild(document.createCDATASection(recvdtTo));
		ROOT.appendChild(schEndDt);

		Node selStatCd = document.createElement("selStatCd");
		selStatCd.appendChild(document.createCDATASection(type));
		ROOT.appendChild(selStatCd);
		if (prodcd.equals("1")) {
			Node prdNo = document.createElement("prdNo");
			prdNo.appendChild(document.createCDATASection(search));
			ROOT.appendChild(prdNo);
		} else {
			Node prdNm = document.createElement("prdNm");
			prdNm.appendChild(document.createCDATASection(search));
			ROOT.appendChild(prdNm);
		}

		Node limit = document.createElement("limit");
		limit.appendChild(document.createCDATASection(num));// 브랜드
		ROOT.appendChild(limit);

		writeToXml(ROOT, templateFullpath);

	}

	public static List<ShopOptionDto> getProductOptions(ShopProductDto dto) {
		// String str = dto.getChar_1_val();
		List<ShopOptionDto> options = new ArrayList<>();
		ShopOptionProductInfoDao dao = new ShopOptionProductInfoDao();
		try {
			List<ShopOptionProductInDto> list = dao.getShopOptProdInfoListByProdseq(dto.getProdseq());
			for (ShopOptionProductInDto optdto : list) {
				ShopOptionDto opt = new ShopOptionDto();
				opt.setName(optdto.getOptprodnm().concat("_").concat(optdto.getOptspecdes()));
				opt.setCnt(optdto.getOptvertstock());
				opt.setAmt(Integer.toString(optdto.getOptaddamt()));
				options.add(opt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return options;
	}

}/////////////////////////

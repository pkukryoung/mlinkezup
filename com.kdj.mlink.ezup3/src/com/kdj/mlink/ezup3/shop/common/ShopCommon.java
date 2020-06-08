package com.kdj.mlink.ezup3.shop.common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.coupang.openapi.sdk.Hmac;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.common.YDMAProperties;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class ShopCommon {
	public final static String 인터파크 = "shop0004";
	public final static String 십일번가 = "shop0003";
	public final static String 옥션 = "shop0067";
	public final static String 지마켓 = "shop0068";
	public final static String 위메프 = "shop0287";
	public final static String 스토어팜 = "shop0055";
	public final static String 쿠팡 = "shop0075";
	public final static String 카카오스토어 = "shop0273";
	public final static String 카페24 = "shop0110";
	public final static String 티몬 = "shop0076";
	public final static String 후이즈 = "shop9999";
	/*
	 * 에러코드..
	 */

	public static final String COUPANG_HOST = "api-gateway.coupang.com";
	public static final int COUPANG_PORT = 443;
	public static final String COUPANG_SCHEMA = "https";

	public static final String 데이타없음 = "0";
	public static final String 에러발생 = "-1";
	public static final String 정상처리 = "1";

	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String PUT = "PUT";
	public static final String PATCH = "PATCH";
	public static final String DELETE = "DELETE";

	/* 상품 수정 상태 */
	public static final String 상품상태_공급중 = "9";
	public static final String 상품상태_일시중지 = "8";
	public static final String 상품상태_완전품절 = "7";
	public static final String 상품삭제후_등록 = "6";
	public static final String 상품명수정 = "5";
	public static final String 상품판매가수정 = "4";
	public static final String 상품카테고리수정 = "3";
	public static final String 상품이미지수정 = "2";
	public static final String 상품상세설명수정 = "1";

	private static Map<String, String> productPreviewMap = new HashMap<>();

	public static String getProductUrl(String shopid, String prodID, String skuid) {
		if (productPreviewMap.size() == 0) {
			initProductPreviewMap();
		}
		String url = productPreviewMap.get(shopid);
		if (shopid.equals("shop0075")) {
			url = String.format(url, prodID, skuid);
		} else {
			url = String.format(url, prodID);
		}
		return url;
	}

	private static void initProductPreviewMap() {
		productPreviewMap.put("shop0003",
				"http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=%s");
		productPreviewMap.put("shop0004", "http://shopping.interpark.com/product/productInfo.do?prdNo=%s");
		productPreviewMap.put("shop0055", "https://smartstore.naver.com/under-ten/products/%s");
		productPreviewMap.put("shop0067", "http://itempage3.auction.co.kr/DetailView.aspx?ItemNo=%s&frm3=V2");
		productPreviewMap.put("shop0068", "http://item.gmarket.co.kr/challenge/neo_goods/goods.asp?goodscode=%s");
		productPreviewMap.put("shop0075", "https://www.coupang.com/vp/products/%s?vendorItemId=%s&isAddedCart=");
		productPreviewMap.put("shop0076", "http://www.tmon.co.kr/deal/%s");
		productPreviewMap.put("shop0110", "http://under10.co.kr/product/detail.html?product_no=%s");
		productPreviewMap.put("shop0120", "https://ownerclan.com/V2/product/view.php?selfcode=%s");
		productPreviewMap.put("shop0203", "http://www.wholesaledepot.co.kr/shop/goods/goods_view.php?goodsno=%s");
		productPreviewMap.put("shop0262", "http://lut0001.firstmall.kr/goods/view?no=%s");
		// productPreviewMap.put("shop0273","제공안함");
		productPreviewMap.put("shop0283", "https://www.domesin.com/shop.html?p=view.html&iid=%s");
		productPreviewMap.put("shop0287", "https://front.wemakeprice.com/product/%s");
		productPreviewMap.put("shop0319", "http://domeggook.com/%s");
		productPreviewMap.put("shop0026", "http://under10.co.kr/product/detail.html?product_no=%s");
		productPreviewMap.put("shop9999", "http://www.skytea.net/src/products/products_detail.php?product_mst_id=%s");
	}

	public static String getImgPath() throws Exception {
		String img_path = String.format("%s\\%s",
				YDMASessonUtil.getAppPath(YDMAProperties.getInstance().getAppProperty("Product.image.shopImage")),
				YDMASessonUtil.getImageFolderName());
		return img_path;
	}

	public static boolean isUrlFileExits(String url) throws Exception {
		String name = url.substring(url.lastIndexOf('/') + 1, url.length());
		Path path = Paths.get(getImgPath(), name);
		return Files.exists(path);
	}

	public static String getImgLocalPath(String url) throws Exception {
		String name = url.substring(url.lastIndexOf('/') + 1, url.length());
		Path path = Paths.get(getImgPath(), name);
		return path.toAbsolutePath().toString();
	}

	public static InputStream getImageInputStream(String str_url) throws IOException {
		String file_ext = str_url.substring(str_url.lastIndexOf('.') + 1, str_url.length());
		URL url = new URL(str_url);
		BufferedImage image = ImageIO.read(url);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, file_ext, os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		return is;
	}

	public static void downloadImgFile(String url, int width, int height) throws Exception {
		String localPath = getImgPath();
		String file_ext = url.substring(url.lastIndexOf('.') + 1, url.length());
		String name = url.substring(url.lastIndexOf('/') + 1, url.length());
		BufferedImage image = null;

		try {

			File folder = new File(getImgPath());

			if (!folder.exists()) { // 폴더 가 없으면 폴더를 만든다.
				folder.mkdirs();
			}

			image = ImageIO.read(new URL(url));
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

			Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
			graphics.setBackground(Color.WHITE);
			graphics.drawImage(image, 0, 0, null);
			graphics.dispose();
			ImageIO.write(bufferedImage, file_ext, new File(localPath + "/" + name));
			System.out.println("다운로드 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void downloadImgFile(String url) throws Exception {
		downloadImgFile(url, 600, 600);
	}

	public static void downloadOrgImgFile(String url) throws Exception {
		String localPath = getImgPath();
		String file_ext = url.substring(url.lastIndexOf('.') + 1, url.length());
		String name = url.substring(url.lastIndexOf('/') + 1, url.length());
		BufferedImage bufferedImage = null;

		try {

			File folder = new File(getImgPath());

			if (!folder.exists()) { // 폴더 가 없으면 폴더를 만든다.
				folder.mkdirs();
			}

			bufferedImage = ImageIO.read(new URL(url));

			ImageIO.write(bufferedImage, file_ext, new File(localPath + "/" + name));
			Thread.sleep(2000);
			System.out.println("다운로드 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fileUploadAutoIt(String imgpath) throws Exception {
		String execute = YDMASessonUtil.getAppPath() + String.format("\\YDwmsData\\FileUpload.exe %s", imgpath);
		Process process = Runtime.getRuntime().exec(execute);
		process.getErrorStream().close();
		process.getInputStream().close();
		process.getOutputStream().close();
		process.waitFor();

	}

	public static String getImgLocalPathDosin(String url, String prodcd) throws Exception {
		String name = url.substring(url.lastIndexOf('/') + 1, url.length());
		Path path = Paths.get(getImgPathDosin(), prodcd);
		return path.toAbsolutePath().toString();
	}

	public static String getImgPathDosin() throws Exception {
		String img_path = String.format("%s\\%s",
				YDMASessonUtil.getAppPath(YDMAProperties.getInstance().getAppProperty("Product.image.productImage2")),
				YDMASessonUtil.getImageFolderName());
		return img_path;
	}

	public static boolean isUrlFileExitsDosin(String url, String prodcd) throws Exception {
		String name = url.substring(url.lastIndexOf('/') + 1, url.length());
		Path path = Paths.get(getImgPathDosin(), prodcd);
		return Files.exists(path);
	}

	public static BufferedImage resize(BufferedImage img, int height, int width) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}

	public static ImageData convertToSWT2(BufferedImage bufferedImage) throws IOException {
//		/2) awt.BufferedImage -> raw Data
		int[] data = ((DataBufferInt) bufferedImage.getData().getDataBuffer()).getData();
		ImageData imageData = new ImageData(80, 80, 24, new PaletteData(0xFF0000, 0x00FF00, 0x0000FF));
		imageData.setPixels(0, 0, data.length, data, 0);

		// If alpha is present, transfer that as well.
		if (bufferedImage.getColorModel().hasAlpha()) {
			int[] alpha = ((DataBufferInt) bufferedImage.getAlphaRaster().getDataBuffer()).getData();
			byte[] alphaBytes = new byte[alpha.length];
			for (int i = 0; i < alpha.length; i++) {
				alphaBytes[i] = (byte) ((alpha[i] >> 24) & 0xFF);
			}
			imageData.setAlphas(0, 0, alphaBytes.length, alphaBytes, 0);
		}

		return imageData;

//		java.awt.image.WritableRaster awtRaster = bufferedImage.getRaster();
//		java.awt.image.DataBufferByte awtData = (DataBufferByte) awtRaster.getDataBuffer();
//		byte[] rawData = awtData.getData();
//
//		//3) raw Data -> swt.ImageData
//		org.eclipse.swt.graphics.PaletteData swtPalette = new PaletteData(0xff, 0xff00, 0xff0000);
//
//		int depth = 0x18;
//		org.eclipse.swt.graphics.ImageData swtImageData = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(), 
//				depth, swtPalette, bufferedImage.getWidth(), rawData);
//		
//		return swtImageData;
//		return new Image(Display.getDefault(), swtImageData);		
	}

	public static void downloadImgFileDosin(String url, int width, int height, String prodcd) throws Exception {
		String localPath = getImgPathDosin();
		String file_ext = url.substring(url.lastIndexOf('.') + 1, url.length());
		String name = url.substring(url.lastIndexOf('/') + 1, url.length());
		BufferedImage image = null;
		File file = new File(localPath + "/" + prodcd + ".jpg");
		try {

			File folder = new File(getImgPathDosin());

			if (!folder.exists()) { // 폴더 가 없으면 폴더를 만든다.
				folder.mkdirs();
			}
			URL u = new URL(url);
			image = ImageIO.read(u);

			Image resizeImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

			BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = newImage.getGraphics();
			g.drawImage(resizeImage, 0, 0, null);
			g.dispose();
			ImageIO.write(newImage, "jpg", file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 해당 블록으로 그룹핑 한다..
	 */
	public static <T> Map<Integer, List<T>> getBlockGroup(int blockSize, int totalSize, List<T> list) {
		int block = totalSize / blockSize; // 블록..
		int mod = totalSize % blockSize; // 나머지..
		int k = 0;
		Map<Integer, List<T>> listGroup = new HashMap<>();
		if (blockSize >= totalSize) {
			listGroup.put(1, list);
		} else {
			Integer i = 0;
			for (i = 0; i < block; ++i) {
				k = i * blockSize;
				listGroup.put(i + 1, list.stream().skip(k).limit(blockSize).collect(Collectors.toList()));
			}
			if (mod > 0) {
				k = k + blockSize;
				listGroup.put(i + 1, list.stream().skip(k).collect(Collectors.toList()));
			}
		}
		return listGroup;
	}

	/*
	 * 고유값 가져오기..
	 */
	public static String getUUID() {
		String nowTime = new SimpleDateFormat("YYYYMMddHHmmssSSS").format(System.currentTimeMillis());
		UUID uuid = UUID.randomUUID();
		String uid = nowTime + uuid.toString().replaceAll("-", "");
		return uid;
	}

	/*
	 * 네이버 인기검색어
	 */

}

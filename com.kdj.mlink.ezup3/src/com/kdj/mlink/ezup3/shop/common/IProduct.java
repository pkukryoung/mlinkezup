package com.kdj.mlink.ezup3.shop.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//import com.kdj.mlink.ezup3.shop.data.interpark.InterParkProductRegService;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;
import com.kdj.mlink.ezup3.data.dao.ProductIforDao;
import com.kdj.mlink.ezup3.shop.dao.ShopOptionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopOptionProductInDto;
import com.kdj.mlink.ezup3.shop.dao.ShopOptionProductInfoDao;
import com.kdj.mlink.ezup3.shop.dao.ShopProdAttrnmDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductNaverStoreAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductSendDto;

public interface IProduct extends IShopCommonLogin {

	String ��ǰ������ = "2";
	String ����Ÿ���� = "0";
	String �����߻� = "-1";
	String ����ó�� = "1";

	/*
	 * ó�� ��� ..
	 */
	ShopProductSendDto excute(ShopProductSendDto prodList) throws Exception;

	// �ΰ�����üũ
	static void setAdditionalExceptionThrow(ShopProductAdditionDto shopProductAdditionDto,
			List<ShopProductDto> shopProductDtos) {
		ShopProductSendDto sendDto = new ShopProductSendDto();
		sendDto.setProductAdditionDto(shopProductAdditionDto);

		for (ShopProductDto dto : shopProductDtos) {
			if (shopProductAdditionDto.getShopcd().equals("shop0055")) {
				ShopProductNaverStoreAdditionDto dtllist = (ShopProductNaverStoreAdditionDto) sendDto
						.getProductAdditionDto(); // �ΰ� ����...
				if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", dtllist.getDelvcost())) {// �⺻��ۺ�
					dto.setResult_code(ShopCommon.�����߻�);
					dto.setResult_text(ShopErrorCode.get().getMessage("D0009").getCode());// �������� 0���� ��������
				}
				if (dtllist.getAreadelvtyp().equals("02")) {
					if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", dtllist.getJejucost())) {// ����
						dto.setResult_code(ShopCommon.�����߻�);
						dto.setResult_text(ShopErrorCode.get().getMessage("D0010").getCode());// �������� 0���� ��������
					}
				} else if (dtllist.getAreadelvtyp().equals("03")) {
					if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", dtllist.getJejucost())) {// ����
						dto.setResult_code(ShopCommon.�����߻�);
						dto.setResult_text(ShopErrorCode.get().getMessage("D0010").getCode());// �������� 0���� ��������
					}
					if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", dtllist.getIslandcost())) {// �����갣
						dto.setResult_code(ShopCommon.�����߻�);
						dto.setResult_text(ShopErrorCode.get().getMessage("D0010").getCode());// �������� 0���� ��������
					}
				}
				if (dtllist.getShipdelvtyp().equals("04")) {// �ù�� ������ �ΰ��ΰ��߿��� ���������ԷµȰǿ� ���ؼ� ����ó��
					if (dtllist.getQtydivisiontyp().equals("02")) {
						if (dtllist.getDelvcost2().contains(",")) {
							String[] split = dtllist.getDelvcost2().split(",");
							if (split.length <= 2) {// ���̹����� 2�����϶� üũ
								if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", split[1])) {// 2�����϶� �ݾ�üũ
									dto.setResult_code(ShopCommon.�����߻�);
									dto.setResult_text(ShopErrorCode.get().getMessage("D0011").getCode());// �������� 0����
																											// ��������
								}
							} else {// ���̹����� 3�����϶� üũ
								if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", split[1])) {// 2�����϶� �ݾ�üũ
									dto.setResult_code(ShopCommon.�����߻�);
									dto.setResult_text(ShopErrorCode.get().getMessage("D0011").getCode());// �������� 0����
																											// ��������
								}
								if (Integer.parseInt(split[0]) >= Integer.parseInt(split[2])) {
									dto.setResult_code(ShopCommon.�����߻�);
									dto.setResult_text(ShopErrorCode.get().getMessage("D0012").getCode());// ������������ ����
								}
								if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", split[3])) {// 2�����϶� �ݾ�üũ
									dto.setResult_code(ShopCommon.�����߻�);
									dto.setResult_text(ShopErrorCode.get().getMessage("D0011").getCode());// �������� 0����
																											// ��������
								}
							}
						}
					}
				}
			}
		}

	}

	static void setExceptionThrow(String shopcd, ShopProductDto dto) {

		if (YDMAStringUtil.checkRegex("[*,]", dto.getGoods_nm())) {
			dto.setResult_code(ShopCommon.�����߻�);
			dto.setResult_text(ShopErrorCode.get().getMessage("D0002").getCode());// name:[��ǰ�� �Է��� �Ұ����� ����(*
																					// ,)�� ���ԵǾ� �ֽ��ϴ�.
		}
		if (dto.getGoods_price().length() == 0 || dto.getGoods_price() == null) {
			dto.setResult_code(ShopCommon.�����߻�);
			dto.setResult_text(ShopErrorCode.get().getMessage("D0003").getCode());// �ǸŰ����� �������
		}

		if (YDMAStringUtil.checkRegex("[><|]", dto.getChar_1_nm() + dto.getChar_2_nm())) {
			dto.setResult_code(ShopCommon.�����߻�);
			dto.setResult_text(ShopErrorCode.get().getMessage("D0004").getCode());// �ǸŰ����� �������
		}

		List<ShopOptionDto> options = new ArrayList<>();
		String str = dto.getChar_1_val();

//		options = Arrays
//				.asList(str.split(","))
//				.stream()
//				.map( (val) -> {
////					System.out.println(val);
//					List<String> list = Arrays.asList(val.replaceAll("\\^\\^", ":").split(":"));
////					System.out.println(list);
//					return new ShopOptionDto(list.get(0), list.get(1), list.get(2).equals("") ? "0" : list.get(2));
//					} )
//				.collect(Collectors.toList());

//		List<ShopOptionDto> optionsdetail = options
//				.stream()
//				.filter(p -> !p.getAmt().equals("0"))
//				.collect(Collectors.toList());

		// SHOP_OPTPRODINFO ���̺��� ����Ͽ� �ɼ�(��ǰ)���� �������� 2020.02.29 mac
		// Ȯ���� ��ä �ʿ�

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
		if (!dto.getChar_1_nm().equals("��ǰ")) {
			if (options.size() == 0) {
				dto.setResult_code(ShopCommon.�����߻�);
				dto.setResult_text(ShopErrorCode.get().getMessage("D0005").getCode());// �ɼ��� �߰��ݾ��� 0���� �ɼ��� 1�� �̻�
																						// �ݵ�� �����ؾ� �մϴ�.
			}
		}

		if (dto.getCompayny_goods_cd().startsWith("@") || dto.getModel_nm().startsWith("@")
				|| dto.getGoods_nm().startsWith("@") || dto.getBrand_nm().startsWith("@")
				|| dto.getModel_no().startsWith("@") || dto.getGoods_search().startsWith("@")) {
			dto.setResult_code(ShopCommon.�����߻�);
			dto.setResult_text(ShopErrorCode.get().getMessage("D0008").getCode());
		}

		if (shopcd.equals("shop0076")) { // Ƽ��
			if (dto.getGoods_nm().contains(" ")) {
				dto.setResult_code(ShopCommon.�����߻�);
				dto.setResult_text(ShopErrorCode.get().getMessage("D0006").getCode());// ��ǰ�� ������ �ִ°��
			}
			if (dto.getChar_1_nm().startsWith(" ") || dto.getChar_1_nm().endsWith(" ")
					|| dto.getChar_2_nm().startsWith(" ") || dto.getChar_2_nm().endsWith(" ")
					|| dto.getChar_1_val().startsWith(" ") || dto.getChar_1_val().endsWith(" ")
					|| dto.getChar_2_val().startsWith(" ") || dto.getChar_2_val().endsWith(" ")) {
				dto.setResult_code(ShopCommon.�����߻�);
				dto.setResult_text(ShopErrorCode.get().getMessage("D0007").getCode());// �ɼ�����, �ɼǻ󼼸�Ī �� �Ǵ� �ڿ����ʿ��� ������
																						// �ֽ��ϴ�.
			}

		}

	}

	/*
	 * ��ǰ��� ����..
	 */
	default List<ShopProdAttrnmDto> getProductNotices(ShopProductDto dto) throws Exception {

		String attrcd = dto.getProp1_cd();
		ProductIforDao dao = new ProductIforDao();

		return dao.getProductNotice(attrcd);
	}

	/*
	 * �ɼ� ������ ��ȯ�Ѵ�.
	 */
	default List<ShopOptionDto> getProductOptions(ShopProductDto dto) {
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

//		return	Arrays
//				.asList(str.split(","))
//				.stream()
//				.map((val) -> {
//					List<String> list = Arrays.asList(val.replaceAll("\\^\\^", ":").split(":"));
//					return new ShopOptionDto(list.get(0), list.get(1), list.get(2));
//					})
//				.collect(Collectors.toList());
	}

	/*
	 * �̹��� ������ ����Ʈ ���·� ��ȯ..
	 */
	default List<String> getProductImages(ShopProductDto dto) {
		List<String> ret = Arrays.asList(dto.getImg_path(), dto.getImg_path1(), dto.getImg_path2(), dto.getImg_path3(),
				dto.getImg_path4(), dto.getImg_path5(), dto.getImg_path6(), dto.getImg_path7(), dto.getImg_path8(),
				dto.getImg_path9(), dto.getImg_path10(), dto.getImg_path11(), dto.getImg_path12(), dto.getImg_path13(),
				dto.getImg_path14(), dto.getImg_path15(), dto.getImg_path16(), dto.getImg_path17(), dto.getImg_path18(),
				dto.getImg_path19(), dto.getImg_path20(), dto.getImg_path21(), dto.getImg_path22(), dto.getImg_path23(),
				dto.getImg_path24());

		return ret.stream().filter(p -> p != null && !p.isEmpty()).collect(Collectors.toList());
	}

	/*
	 *
	 */
	static String getContentString(List<List<String>> prodnm) {
		String complite = "";
		for (int i = 0; i < prodnm.size(); i++) {
			complite = prodnm.get(i).get(3);
		}
		return complite;
	}

	static String getContentprodnm(List<List<String>> prodnm) {
		String complite = "";
		for (int i = 0; i < prodnm.size(); i++) {
			complite = prodnm.get(i).get(0).trim();
		}
		return complite;
	}

	static String splitMark(String text) {
		String[] split = text.split("-| ");
		String complite = "";
		for (String element : split) {
			complite += element;
			complite = complite.trim();
		}
		return complite;
	}
}

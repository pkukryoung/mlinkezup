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

	String 상품존재함 = "2";
	String 데이타없음 = "0";
	String 에러발생 = "-1";
	String 정상처리 = "1";

	/*
	 * 처리 결과 ..
	 */
	ShopProductSendDto excute(ShopProductSendDto prodList) throws Exception;

	// 부가정보체크
	static void setAdditionalExceptionThrow(ShopProductAdditionDto shopProductAdditionDto,
			List<ShopProductDto> shopProductDtos) {
		ShopProductSendDto sendDto = new ShopProductSendDto();
		sendDto.setProductAdditionDto(shopProductAdditionDto);

		for (ShopProductDto dto : shopProductDtos) {
			if (shopProductAdditionDto.getShopcd().equals("shop0055")) {
				ShopProductNaverStoreAdditionDto dtllist = (ShopProductNaverStoreAdditionDto) sendDto
						.getProductAdditionDto(); // 부가 정보...
				if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", dtllist.getDelvcost())) {// 기본배송비
					dto.setResult_code(ShopCommon.에러발생);
					dto.setResult_text(ShopErrorCode.get().getMessage("D0009").getCode());// 원단위가 0으로 끝나야함
				}
				if (dtllist.getAreadelvtyp().equals("02")) {
					if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", dtllist.getJejucost())) {// 제주
						dto.setResult_code(ShopCommon.에러발생);
						dto.setResult_text(ShopErrorCode.get().getMessage("D0010").getCode());// 원단위가 0으로 끝나야함
					}
				} else if (dtllist.getAreadelvtyp().equals("03")) {
					if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", dtllist.getJejucost())) {// 제주
						dto.setResult_code(ShopCommon.에러발생);
						dto.setResult_text(ShopErrorCode.get().getMessage("D0010").getCode());// 원단위가 0으로 끝나야함
					}
					if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", dtllist.getIslandcost())) {// 도서산간
						dto.setResult_code(ShopCommon.에러발생);
						dto.setResult_text(ShopErrorCode.get().getMessage("D0010").getCode());// 원단위가 0으로 끝나야함
					}
				}
				if (dtllist.getShipdelvtyp().equals("04")) {// 택배비가 수량별 부과인것중에서 구간직접입력된건에 대해서 에러처리
					if (dtllist.getQtydivisiontyp().equals("02")) {
						if (dtllist.getDelvcost2().contains(",")) {
							String[] split = dtllist.getDelvcost2().split(",");
							if (split.length <= 2) {// 네이버에서 2구간일때 체크
								if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", split[1])) {// 2구간일때 금액체크
									dto.setResult_code(ShopCommon.에러발생);
									dto.setResult_text(ShopErrorCode.get().getMessage("D0011").getCode());// 원단위가 0으로
																											// 끝나야함
								}
							} else {// 네이버에서 3구간일때 체크
								if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", split[1])) {// 2구간일때 금액체크
									dto.setResult_code(ShopCommon.에러발생);
									dto.setResult_text(ShopErrorCode.get().getMessage("D0011").getCode());// 원단위가 0으로
																											// 끝나야함
								}
								if (Integer.parseInt(split[0]) >= Integer.parseInt(split[2])) {
									dto.setResult_code(ShopCommon.에러발생);
									dto.setResult_text(ShopErrorCode.get().getMessage("D0012").getCode());// 구간별수량에 오류
								}
								if (!YDMAStringUtil.checkRegex("^[1-9][0-9]*[0]", split[3])) {// 2구간일때 금액체크
									dto.setResult_code(ShopCommon.에러발생);
									dto.setResult_text(ShopErrorCode.get().getMessage("D0011").getCode());// 원단위가 0으로
																											// 끝나야함
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
			dto.setResult_code(ShopCommon.에러발생);
			dto.setResult_text(ShopErrorCode.get().getMessage("D0002").getCode());// name:[상품명에 입력이 불가능한 문자(*
																					// ,)가 포함되어 있습니다.
		}
		if (dto.getGoods_price().length() == 0 || dto.getGoods_price() == null) {
			dto.setResult_code(ShopCommon.에러발생);
			dto.setResult_text(ShopErrorCode.get().getMessage("D0003").getCode());// 판매가격이 없을경우
		}

		if (YDMAStringUtil.checkRegex("[><|]", dto.getChar_1_nm() + dto.getChar_2_nm())) {
			dto.setResult_code(ShopCommon.에러발생);
			dto.setResult_text(ShopErrorCode.get().getMessage("D0004").getCode());// 판매가격이 없을경우
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

		// SHOP_OPTPRODINFO 테이블을 사용하여 옵션(단품)정보 가저오기 2020.02.29 mac
		// 확인후 교채 필요

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
		if (!dto.getChar_1_nm().equals("단품")) {
			if (options.size() == 0) {
				dto.setResult_code(ShopCommon.에러발생);
				dto.setResult_text(ShopErrorCode.get().getMessage("D0005").getCode());// 옵션의 추가금액이 0원인 옵션이 1개 이상
																						// 반드시 존재해야 합니다.
			}
		}

		if (dto.getCompayny_goods_cd().startsWith("@") || dto.getModel_nm().startsWith("@")
				|| dto.getGoods_nm().startsWith("@") || dto.getBrand_nm().startsWith("@")
				|| dto.getModel_no().startsWith("@") || dto.getGoods_search().startsWith("@")) {
			dto.setResult_code(ShopCommon.에러발생);
			dto.setResult_text(ShopErrorCode.get().getMessage("D0008").getCode());
		}

		if (shopcd.equals("shop0076")) { // 티몬
			if (dto.getGoods_nm().contains(" ")) {
				dto.setResult_code(ShopCommon.에러발생);
				dto.setResult_text(ShopErrorCode.get().getMessage("D0006").getCode());// 상품명에 공백이 있는경우
			}
			if (dto.getChar_1_nm().startsWith(" ") || dto.getChar_1_nm().endsWith(" ")
					|| dto.getChar_2_nm().startsWith(" ") || dto.getChar_2_nm().endsWith(" ")
					|| dto.getChar_1_val().startsWith(" ") || dto.getChar_1_val().endsWith(" ")
					|| dto.getChar_2_val().startsWith(" ") || dto.getChar_2_val().endsWith(" ")) {
				dto.setResult_code(ShopCommon.에러발생);
				dto.setResult_text(ShopErrorCode.get().getMessage("D0007").getCode());// 옵션제목, 옵션상세명칭 앞 또는 뒤에불필요한 공백이
																						// 있습니다.
			}

		}

	}

	/*
	 * 상품고시 정보..
	 */
	default List<ShopProdAttrnmDto> getProductNotices(ShopProductDto dto) throws Exception {

		String attrcd = dto.getProp1_cd();
		ProductIforDao dao = new ProductIforDao();

		return dao.getProductNotice(attrcd);
	}

	/*
	 * 옵션 정보를 반환한다.
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
	 * 이미지 정보를 리스트 형태로 반환..
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

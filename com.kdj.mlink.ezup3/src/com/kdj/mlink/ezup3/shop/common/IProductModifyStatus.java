package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;

import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductSendDto;
import com.kdj.mlink.ezup3.shop.dao.ShoppingMallDetailDto;

/*   상품 수정 상태 */

/*public static final int 상품상태_공급중 = 9;
public static final int 상품상태_일시중지 = 8;
public static final int 상품상태_완전품절 = 7;
public static final int 상품삭제후_등록 = 6;
public static final int 상품명수정 = 5;
public static final int 상품판매가수정 = 4;
public static final int 상품카테고리수정 = 3;
public static final int 상품이미지수정 = 2;
public static final int 상품상세설명수정= 1;*/
public abstract class IProductModifyStatus implements IProduct {

	protected ChromeDriver driver;

	@Override
	public ShopProductSendDto excute(ShopProductSendDto shopProductSendDto) throws Exception {
		driver = ChromeExtention.getInstace().getDriver();
		List<String> flag = shopProductSendDto.getUpdateFlag();
		String firstStatus = flag.get(0);

		switch (firstStatus) {
		case ShopCommon.상품삭제후_등록:
			return deleteOrInsert(shopProductSendDto);

		case ShopCommon.상품상태_공급중:
		case ShopCommon.상품상태_일시중지:
		case ShopCommon.상품상태_완전품절:
			return modifyProductStatus(shopProductSendDto, firstStatus);
		default:
			
			return modifyBaseInfo(shopProductSendDto, flag);
			 

		}
	}

	/*
	 * 상품명 수정..
	 */
	public abstract void modifyProductName(ShopProductDto shopProductDto) throws Exception;

	/*
	 * 판매가 수정..
	 */
	public abstract void modifyPrice(ShopProductDto shopProductDto) throws Exception;

	/*
	 * 카테고리 수정..
	 */
	public abstract void modifyCategory(ShopProductDto shopProductDto,ShoppingMallDetailDto idlist) throws Exception;

	/*
	 * 이미지 대표 이미지 /부가 이미지 수정..
	 */
	public abstract void modifyImage(ShopProductDto shopProductDto) throws Exception;

	/*
	 * 상세 설명 수정..
	 */
	public abstract void modifyDescription(ShopProductDto shopProductDto) throws Exception;

	
	public abstract boolean isExistProdNo(ShopProductDto dto) throws Exception;
	
	/*
	 * 일반항목 수정..
	 */
	public ShopProductSendDto modifyBaseInfo(ShopProductSendDto shopProductSendDto, List<String> modifyList)
			throws Exception {
		List<ShopProductDto> shopProductDtos = shopProductSendDto.getShopProductDto();
		ShoppingMallDetailDto idlist = shopProductSendDto.getShoppingMallDetailDto();
		try {
			for (ShopProductDto dto : shopProductDtos) {
				if(!isExistProdNo(dto)) {
					dto.setResult_code(ShopCommon.에러발생);
					dto.setResult_text("상품번호가 존재 하지 않습니다. ");
					continue;
				}
				System.out.println();
				if (modifyList.stream().anyMatch(p -> p.equals(ShopCommon.상품명수정))) {
					modifyProductName(dto);
				}

				if (modifyList.stream().anyMatch(p -> p.equals(ShopCommon.상품판매가수정))) {
					modifyPrice(dto);
				}

				if (modifyList.stream().anyMatch(p -> p.equals(ShopCommon.상품카테고리수정))) {
					modifyCategory(dto,idlist );
				}

				if (modifyList.stream().anyMatch(p -> p.equals(ShopCommon.상품이미지수정))) {
					modifyImage(dto);
				}

				if (modifyList.stream().anyMatch(p -> p.equals(ShopCommon.상품상세설명수정))) {
					modifyDescription(dto);
				}
				
				modifyBaseInfoAfter();

			}
		} catch (Exception e) {
			throw e;
		}
		return shopProductSendDto;
	}

	
	/*
	 * 저장버튼.. 
	 */
	public abstract void modifyBaseInfoAfter() throws Exception;
	
	
	public abstract void modifyProductStatusDto(ShopProductDto shopProductDto,String status) throws Exception;
	
	/*
	 * 상품 상태 수정..
	 */
	public ShopProductSendDto modifyProductStatus(ShopProductSendDto shopProductSendDto,String status) throws Exception
	{
		List<ShopProductDto> shopProductDtos = shopProductSendDto.getShopProductDto();
		ShoppingMallDetailDto idlist = shopProductSendDto.getShoppingMallDetailDto();
		try {
			for (ShopProductDto dto : shopProductDtos) {
				if(!isExistProdNo(dto)) {
					dto.setResult_code(ShopCommon.에러발생);
					dto.setResult_text("상품번호가 존재 하지 않습니다. ");
					continue;
				}
				
				modifyProductStatusDto(dto, status);

			}
		} catch (Exception e) {
			throw e;
		}
		return shopProductSendDto;
	}

	

	
	/*
	 * 기본옵션 수정..
	 */
	public abstract  ShopProductSendDto deleteOrInsert(ShopProductSendDto shopProductSendDto) throws Exception;
		

}

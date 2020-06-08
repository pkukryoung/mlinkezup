package com.kdj.mlink.ezup3.shop.common;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.chrome.ChromeDriver;

import com.kdj.mlink.ezup3.shop.dao.ShopProductAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductCoupangAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductSendDto;
import com.kdj.mlink.ezup3.shop.dao.ShoppingMallDetailDto;

public abstract class IProductModifyStatusApi implements IProduct {
	protected ChromeDriver driver;
	
	@Override
	public ShopProductSendDto excute(ShopProductSendDto shopProductSendDto) throws Exception {
		List<String> flag = shopProductSendDto.getUpdateFlag();
		String firstStatus = flag.get(0);

		switch (firstStatus) {
		case ShopCommon.상품삭제후_등록:
			return modifyProduct(shopProductSendDto, ShopCommon.상품삭제후_등록);

		case ShopCommon.상품상태_공급중:
		case ShopCommon.상품상태_일시중지:
		case ShopCommon.상품상태_완전품절:
			return modifyProduct(shopProductSendDto, firstStatus);
		default:
			return modifyProduct(shopProductSendDto, ShopCommon.상품삭제후_등록);

		}
	}

	/*
	 * 상품 상태 수정..
	 */
	public ShopProductSendDto modifyProduct(ShopProductSendDto shopProductSendDto, String status) throws Exception {
		List<ShopProductDto> shopProductDtos = shopProductSendDto.getShopProductDto();
		
		ShoppingMallDetailDto shoppingMallDetailDto = shopProductSendDto.getShoppingMallDetailDto(); // 인증정보..
		ShopProductAdditionDto dtllist =  shopProductSendDto.getProductAdditionDto(); // 부가
		for (ShopProductDto dto : shopProductDtos) {
			try {
				dto.setUpdateFlag(status);//카페24는 밑에 그리드 확인에서 삭제하기 때문에 이걸로 구분해서 삭제 안해야될거 구분함 //공급중이나 품절같은경우
				if (!isExistProdNo(dto, shoppingMallDetailDto) ) {
					dto.setResult_code(ShopCommon.에러발생);
					dto.setResult_text("상품번호가 존재 하지 않습니다. ");
					continue;
				}

				modifyProductStatusDto(dto,dtllist, shoppingMallDetailDto, status); // 상태값 변경..
				
				

			} catch (Exception e) {
				dto.setResult_code(ShopCommon.에러발생);
				dto.setResult_text(ShopErrorCode.get().getMessage(e.getMessage()).getCode());
				continue;
			}
		}

		try {
			if (status.equals(ShopCommon.상품삭제후_등록) ) {
				List<ShopProductDto> list_ok = shopProductSendDto.getShopProductDto() // 정상 리스트..
						.stream().filter(p -> p.getResult_code().equals(ShopCommon.정상처리)).collect(Collectors.toList());
				
				List<ShopProductDto> list_error = shopProductSendDto.getShopProductDto() // 정상 리스트..
						.stream().filter(p -> p.getResult_code().equals(ShopCommon.에러발생)).collect(Collectors.toList());
				
				shopProductSendDto.setShopProductDto(list_ok);  // 삭제 성공한 상품만 보낸다. 
				ProductService service = new ProductService(ProductFactory.getRegisterBean(shopProductSendDto.getShoppingMallDetailDto().getShopcd()));
				shopProductSendDto = service.excute(shopProductSendDto);
				
				
				if(list_error.size() > 0) {  //  에러 까지 전송한다.. 이후작업은 화면 에서 처리.. 
					shopProductSendDto.getShopProductDto().addAll(list_error);
				}
				
			}
		} catch (Exception e) {
			throw e;
		}
		return shopProductSendDto;
	}

	/*
	 * 상품 삭제..
	 */
	public abstract boolean deleteProduct(ShopProductDto shopProductDto) throws Exception;

	/*
	 * 상태값을 변경한다....
	 */
	public abstract boolean modifyProductStatusDto(ShopProductDto shopProductDto, ShopProductAdditionDto dtllist, ShoppingMallDetailDto shoppingMallDetailDto, String status) throws Exception;

	/*
	 * 상품번호가 존재 하는지 체크 ..
	 */
	public abstract boolean isExistProdNo(ShopProductDto dto,ShoppingMallDetailDto shoppingMallDetailDto) throws Exception;

}

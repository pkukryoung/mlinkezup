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
		case ShopCommon.��ǰ������_���:
			return modifyProduct(shopProductSendDto, ShopCommon.��ǰ������_���);

		case ShopCommon.��ǰ����_������:
		case ShopCommon.��ǰ����_�Ͻ�����:
		case ShopCommon.��ǰ����_����ǰ��:
			return modifyProduct(shopProductSendDto, firstStatus);
		default:
			return modifyProduct(shopProductSendDto, ShopCommon.��ǰ������_���);

		}
	}

	/*
	 * ��ǰ ���� ����..
	 */
	public ShopProductSendDto modifyProduct(ShopProductSendDto shopProductSendDto, String status) throws Exception {
		List<ShopProductDto> shopProductDtos = shopProductSendDto.getShopProductDto();
		
		ShoppingMallDetailDto shoppingMallDetailDto = shopProductSendDto.getShoppingMallDetailDto(); // ��������..
		ShopProductAdditionDto dtllist =  shopProductSendDto.getProductAdditionDto(); // �ΰ�
		for (ShopProductDto dto : shopProductDtos) {
			try {
				dto.setUpdateFlag(status);//ī��24�� �ؿ� �׸��� Ȯ�ο��� �����ϱ� ������ �̰ɷ� �����ؼ� ���� ���ؾߵɰ� ������ //�������̳� ǰ���������
				if (!isExistProdNo(dto, shoppingMallDetailDto) ) {
					dto.setResult_code(ShopCommon.�����߻�);
					dto.setResult_text("��ǰ��ȣ�� ���� ���� �ʽ��ϴ�. ");
					continue;
				}

				modifyProductStatusDto(dto,dtllist, shoppingMallDetailDto, status); // ���°� ����..
				
				

			} catch (Exception e) {
				dto.setResult_code(ShopCommon.�����߻�);
				dto.setResult_text(ShopErrorCode.get().getMessage(e.getMessage()).getCode());
				continue;
			}
		}

		try {
			if (status.equals(ShopCommon.��ǰ������_���) ) {
				List<ShopProductDto> list_ok = shopProductSendDto.getShopProductDto() // ���� ����Ʈ..
						.stream().filter(p -> p.getResult_code().equals(ShopCommon.����ó��)).collect(Collectors.toList());
				
				List<ShopProductDto> list_error = shopProductSendDto.getShopProductDto() // ���� ����Ʈ..
						.stream().filter(p -> p.getResult_code().equals(ShopCommon.�����߻�)).collect(Collectors.toList());
				
				shopProductSendDto.setShopProductDto(list_ok);  // ���� ������ ��ǰ�� ������. 
				ProductService service = new ProductService(ProductFactory.getRegisterBean(shopProductSendDto.getShoppingMallDetailDto().getShopcd()));
				shopProductSendDto = service.excute(shopProductSendDto);
				
				
				if(list_error.size() > 0) {  //  ���� ���� �����Ѵ�.. �����۾��� ȭ�� ���� ó��.. 
					shopProductSendDto.getShopProductDto().addAll(list_error);
				}
				
			}
		} catch (Exception e) {
			throw e;
		}
		return shopProductSendDto;
	}

	/*
	 * ��ǰ ����..
	 */
	public abstract boolean deleteProduct(ShopProductDto shopProductDto) throws Exception;

	/*
	 * ���°��� �����Ѵ�....
	 */
	public abstract boolean modifyProductStatusDto(ShopProductDto shopProductDto, ShopProductAdditionDto dtllist, ShoppingMallDetailDto shoppingMallDetailDto, String status) throws Exception;

	/*
	 * ��ǰ��ȣ�� ���� �ϴ��� üũ ..
	 */
	public abstract boolean isExistProdNo(ShopProductDto dto,ShoppingMallDetailDto shoppingMallDetailDto) throws Exception;

}

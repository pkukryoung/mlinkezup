package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;

import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductSendDto;
import com.kdj.mlink.ezup3.shop.dao.ShoppingMallDetailDto;

/*   ��ǰ ���� ���� */

/*public static final int ��ǰ����_������ = 9;
public static final int ��ǰ����_�Ͻ����� = 8;
public static final int ��ǰ����_����ǰ�� = 7;
public static final int ��ǰ������_��� = 6;
public static final int ��ǰ����� = 5;
public static final int ��ǰ�ǸŰ����� = 4;
public static final int ��ǰī�װ����� = 3;
public static final int ��ǰ�̹������� = 2;
public static final int ��ǰ�󼼼������= 1;*/
public abstract class IProductModifyStatus implements IProduct {

	protected ChromeDriver driver;

	@Override
	public ShopProductSendDto excute(ShopProductSendDto shopProductSendDto) throws Exception {
		driver = ChromeExtention.getInstace().getDriver();
		List<String> flag = shopProductSendDto.getUpdateFlag();
		String firstStatus = flag.get(0);

		switch (firstStatus) {
		case ShopCommon.��ǰ������_���:
			return deleteOrInsert(shopProductSendDto);

		case ShopCommon.��ǰ����_������:
		case ShopCommon.��ǰ����_�Ͻ�����:
		case ShopCommon.��ǰ����_����ǰ��:
			return modifyProductStatus(shopProductSendDto, firstStatus);
		default:
			
			return modifyBaseInfo(shopProductSendDto, flag);
			 

		}
	}

	/*
	 * ��ǰ�� ����..
	 */
	public abstract void modifyProductName(ShopProductDto shopProductDto) throws Exception;

	/*
	 * �ǸŰ� ����..
	 */
	public abstract void modifyPrice(ShopProductDto shopProductDto) throws Exception;

	/*
	 * ī�װ� ����..
	 */
	public abstract void modifyCategory(ShopProductDto shopProductDto,ShoppingMallDetailDto idlist) throws Exception;

	/*
	 * �̹��� ��ǥ �̹��� /�ΰ� �̹��� ����..
	 */
	public abstract void modifyImage(ShopProductDto shopProductDto) throws Exception;

	/*
	 * �� ���� ����..
	 */
	public abstract void modifyDescription(ShopProductDto shopProductDto) throws Exception;

	
	public abstract boolean isExistProdNo(ShopProductDto dto) throws Exception;
	
	/*
	 * �Ϲ��׸� ����..
	 */
	public ShopProductSendDto modifyBaseInfo(ShopProductSendDto shopProductSendDto, List<String> modifyList)
			throws Exception {
		List<ShopProductDto> shopProductDtos = shopProductSendDto.getShopProductDto();
		ShoppingMallDetailDto idlist = shopProductSendDto.getShoppingMallDetailDto();
		try {
			for (ShopProductDto dto : shopProductDtos) {
				if(!isExistProdNo(dto)) {
					dto.setResult_code(ShopCommon.�����߻�);
					dto.setResult_text("��ǰ��ȣ�� ���� ���� �ʽ��ϴ�. ");
					continue;
				}
				System.out.println();
				if (modifyList.stream().anyMatch(p -> p.equals(ShopCommon.��ǰ�����))) {
					modifyProductName(dto);
				}

				if (modifyList.stream().anyMatch(p -> p.equals(ShopCommon.��ǰ�ǸŰ�����))) {
					modifyPrice(dto);
				}

				if (modifyList.stream().anyMatch(p -> p.equals(ShopCommon.��ǰī�װ�����))) {
					modifyCategory(dto,idlist );
				}

				if (modifyList.stream().anyMatch(p -> p.equals(ShopCommon.��ǰ�̹�������))) {
					modifyImage(dto);
				}

				if (modifyList.stream().anyMatch(p -> p.equals(ShopCommon.��ǰ�󼼼������))) {
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
	 * �����ư.. 
	 */
	public abstract void modifyBaseInfoAfter() throws Exception;
	
	
	public abstract void modifyProductStatusDto(ShopProductDto shopProductDto,String status) throws Exception;
	
	/*
	 * ��ǰ ���� ����..
	 */
	public ShopProductSendDto modifyProductStatus(ShopProductSendDto shopProductSendDto,String status) throws Exception
	{
		List<ShopProductDto> shopProductDtos = shopProductSendDto.getShopProductDto();
		ShoppingMallDetailDto idlist = shopProductSendDto.getShoppingMallDetailDto();
		try {
			for (ShopProductDto dto : shopProductDtos) {
				if(!isExistProdNo(dto)) {
					dto.setResult_code(ShopCommon.�����߻�);
					dto.setResult_text("��ǰ��ȣ�� ���� ���� �ʽ��ϴ�. ");
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
	 * �⺻�ɼ� ����..
	 */
	public abstract  ShopProductSendDto deleteOrInsert(ShopProductSendDto shopProductSendDto) throws Exception;
		

}

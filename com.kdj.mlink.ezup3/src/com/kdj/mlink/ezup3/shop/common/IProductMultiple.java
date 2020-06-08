package com.kdj.mlink.ezup3.shop.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import com.kdj.mlink.ezup3.shop.dao.ShopProductAdditionDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDao;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductSendDto;
import com.kdj.mlink.ezup3.shop.dao.ShopWorkDao;
import com.kdj.mlink.ezup3.shop.dao.ShoppingMallDetailDto;
import com.kdj.mlink.ezup3.shop.domesin.DomesinMarginDto;

import static java.util.concurrent.TimeUnit.SECONDS;
public abstract class IProductMultiple implements IProduct {

	public Map<Integer, Integer> getThreadGroup(int threadCnt, int totalSize) {
		int block = totalSize / threadCnt; // 블록..
		int mod = totalSize % threadCnt; // 나머지..
		Map<Integer, Integer> threadPool = new HashMap<>();
		if (threadCnt >= totalSize) {
			threadPool.put(1, totalSize);
		} else {
			Integer i = 0;
			for (i = 0; i < block; ++i) {
				threadPool.put(i + 1, threadCnt);
			}
			if (mod > 0) {
				threadPool.put(i + 1, mod);
			}
		}
		return threadPool;
	}
	
	

	@Override
	public synchronized ShopProductSendDto excute(ShopProductSendDto shopProductSendDto) throws Exception {

		List<ShopProductDto> prodList = shopProductSendDto.getShopProductDto(); /// 그리드에서 받아온 상품 정보 리스트..
		ShopProductAdditionDto dtllist = shopProductSendDto.getProductAdditionDto(); // 부가정보...
		ShoppingMallDetailDto idlist = shopProductSendDto.getShoppingMallDetailDto();
		DomesinMarginDto margindto = shopProductSendDto.getDomesinMarginDto();
		String shopinfo = String.format("(%s) %s", idlist.getShopnm(), idlist.getShoppingid());

		// 구간 설정..
		int threadCnt = shopProductSendDto.getMulticnt();
		int totalCount = prodList.size();
		int block = 10;
		int coolpoolSize = (block > threadCnt )?threadCnt : block;
		
		Map<Integer, List<ShopProductDto>> map_list =  ShopCommon.getBlockGroup(block, totalCount, prodList);
		
	    LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(prodList.size());
	    ThreadPoolExecutor executorService = new ThreadPoolExecutor(coolpoolSize, threadCnt ,3, SECONDS, queue);
	    
	    
	    //  고유키 저장.. 
	    for(Integer key : map_list.keySet()) {
	    	String uid =     ShopCommon.getUUID();  // 고유값 가져오기.. 
	    	List<ShopProductDto> shopList =          map_list.get(key);
	    	for(Integer k = 0; k<shopList.size(); ++k)
	    	{
	    		String seq = String.valueOf(k+1);
	    		shopList.get(k).setWorkgrp(uid);
	    		shopList.get(k).setWorkseq(seq);
	    	}
	    	ShopWorkDao.get().setWorkListInsert(shopList,dtllist,idlist, WorkStatus.상품등록, WorkStatus.대기);
	    }
	    
	    
	    for(Integer key : map_list.keySet())
        {

	    	 IShopProductListener callBack = new IShopProductListener() {

				@Override
				public void onReturnProduct(List<ShopProductDto> list, ShopProductAdditionDto dtllist,
						ShoppingMallDetailDto idlist) {
			
					for(ShopProductDto dto : list) {
						if(dto.getResult_code().equals(ShopCommon.정상처리)) {
							try {
								ShopProductDao.get().setProductInOneInsert(dto,dtllist,idlist,margindto);
							} catch (Exception e) {
								e.printStackTrace();
							}	
						}
						if(dto.getResult_code().equals(ShopCommon.에러발생)) {
							try {
								ShopProductDao.get().setProductErrOneInsert(dto,dtllist,idlist,margindto);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			};
	    	
	    	
	    	DriverPool driverPool =  ChromeExtention.getInstace().getDriverPool();
	    	Runnable r = ProductFactory.getRegisterRunalbeList (map_list.get(key)  , dtllist, idlist, driverPool, key, shopinfo,callBack);
            executorService.execute(r);
        }
	    
	    executorService.awaitTermination(5, SECONDS);
        executorService.shutdown();
		return shopProductSendDto;
	}

}

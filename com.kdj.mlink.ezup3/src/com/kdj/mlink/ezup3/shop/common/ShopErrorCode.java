package com.kdj.mlink.ezup3.shop.common;

import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.shop.common.OrderStatus.OrderCodeMapperDto;
import com.kdj.mlink.ezup3.shop.dao.ShopCommonDao;
import com.kdj.mlink.ezup3.shop.dao.ShopErrCodeDto;

public class ShopErrorCode {
	private ShopErrorCode() {
	}

	private static ShopErrorCode instance = new ShopErrorCode();
	private List<ShopErrCodeDto> items = new ArrayList<ShopErrCodeDto>();
	
	public static ShopErrorCode get() {
		if (instance.items.size() == 0) {
			instance.bind();
		}
		return instance;
	}
	
	private void  bind()  {
		items.clear();
		try {
			items = ShopCommonDao.get().getErrorCode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ShopErrCodeDto getMessage(String code) {
		 ShopErrCodeDto ret = new ShopErrCodeDto();
		 ret.setContent("알수없는 에러가 발생했습니다..");
		 ShopErrCodeDto ret1  = items.stream().filter(p->p.getCode().equals(code)).findAny().orElse(ret);
		return ret1;
	}
	
	public ShopErrCodeDto getMessage2(String code) {
		 ShopErrCodeDto ret = new ShopErrCodeDto();
		 ret.setContent("");
		 ShopErrCodeDto ret1  = items.stream().filter(p->p.getCode().equals(code)).findAny().orElse(ret);
		return ret1;
	}
	
	
	
}

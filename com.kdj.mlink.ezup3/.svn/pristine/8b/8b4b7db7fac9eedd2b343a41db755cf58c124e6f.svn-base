package com.kdj.mlink.ezup3.shop.job;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;

import com.kdj.mlink.ezup3.shop.dao.ScheduleInfoDto;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;
import com.kdj.mlink.ezup3.data.dao.QuestListDto;

public abstract  class IShopOrderJob implements Job {
	 
	 @Override
	  public void execute(JobExecutionContext context) throws JobExecutionException {
		 JobDataMap data = context.getJobDetail().getJobDataMap();
		 Iterator iter = data.keySet().iterator();
		 ScheduleInfoDto scheduler = null;
		 List<ShopOrderMstDto> list = null;
		 while(iter.hasNext()) {
			 Object key = iter.next();
			 if(key.equals("Order")) {
				 scheduler = (ScheduleInfoDto) data.get(key); 
			 }else {
				 list = (List<ShopOrderMstDto>) data.get(key);
			 }
			 
		 }
		 
		
		if(scheduler.getJobcd()==2) {
			System.out.println("주문 EXECUTE 시작"); 		
	        getNewOrder(context,scheduler,list);
	        setConfirm(context,scheduler,list);
	        getOrderSync(context,scheduler,list);
	        setOrderExpress(context,scheduler,list);	       
		}else if(scheduler.getJobcd()==1) {
			List<QuestListDto> qnalist = conVersion(list);
			System.out.println("문의 EXECUTE 시작"); 
			getQuestion(context,scheduler,qnalist);
			getEmergency(context,scheduler,qnalist);
			getProductName(context,scheduler,qnalist);
			sendReply(context,scheduler,qnalist);
		}
		 sendEmailNKakao(context,scheduler,list);
    }


	// 주문확인..
    protected abstract int setConfirm(JobExecutionContext context, ScheduleInfoDto value, List<ShopOrderMstDto> list);    
    //  신규주문 가져오기..
    protected abstract int getNewOrder(JobExecutionContext context, ScheduleInfoDto value, List<ShopOrderMstDto> list);
    // 주문동기화..
    protected abstract int getOrderSync(JobExecutionContext context, ScheduleInfoDto value, List<ShopOrderMstDto> list);
    // 송장전송
    protected abstract int setOrderExpress(JobExecutionContext context, ScheduleInfoDto value, List<ShopOrderMstDto> list);
    //문의수집
    protected abstract int getQuestion(JobExecutionContext context, ScheduleInfoDto value, List<QuestListDto> qnalist);    
    //긴급메시지수집
    protected abstract int getEmergency(JobExecutionContext context, ScheduleInfoDto value, List<QuestListDto> qnalist);
    //상품평수집
    protected abstract int getProductName(JobExecutionContext context, ScheduleInfoDto value, List<QuestListDto> qnalist);
    //답변전송
    protected abstract int sendReply(JobExecutionContext context, ScheduleInfoDto value,  List<QuestListDto> qnalist);
    //메일및 카카오전송
    protected abstract int sendEmailNKakao(JobExecutionContext context, ScheduleInfoDto value, List<ShopOrderMstDto> list);
    
    //변환해주기
	private List<QuestListDto> conVersion(List<ShopOrderMstDto> list) {
		List<QuestListDto> qnalist = new ArrayList<QuestListDto>();
		for(ShopOrderMstDto dto : list) {
			QuestListDto adddto = new QuestListDto();
			adddto.setApikey(dto.getApikey());
			adddto.setAuthkey1(dto.getAuthkey1());
			adddto.setAuthkey2(dto.getAuthKey2());
			adddto.setShopid(dto.getShop_userid());
			adddto.setShopnm(dto.getShopid());
			adddto.setShopPw(dto.getShopPw());	
			adddto.setStartDt(dto.getStartDt());
			adddto.setEndDt(dto.getEndDt());
			
			qnalist.add(adddto);
		}
		
		return qnalist;
	}
}

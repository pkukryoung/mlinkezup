package com.kdj.mlink.ezup3.shop.common;

import java.util.List;

import com.kdj.mlink.ezup3.data.dao.QuestListDto;

public class ProductQuestionService {
	 IShopQuestion question;
     public  ProductQuestionService(IShopQuestion question){
         this.question = question;
     }
     /*
      *  ½ÇÇà.. 
      */
     public  List<QuestListDto> excute(List<QuestListDto> args) throws Exception {
    	 String loginID = args.get(0).getShopid();
    	 String loginPw = args.get(0).getShopPw();
    	 String shopnm = args.get(0).getShopnm();
    	 String authkey = args.get(0).getAuthkey1()==null?"":args.get(0).getAuthkey1();
    	 this.question.login(loginID, loginPw,shopnm,authkey);
         return this.question.excute(args);
     }
     
}

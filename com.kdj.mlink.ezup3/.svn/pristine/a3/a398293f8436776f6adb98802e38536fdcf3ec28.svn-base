package com.kdj.mlink.ezup3.shop.domesin;

import java.nio.channels.CompletionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import com.kdj.mlink.ezup3.shop.common.HttpClientEx;
import com.kdj.mlink.ezup3.common.YDMAStringUtil;

public class DomesinProductGet {
	 private static DomesinProductGet instance = new DomesinProductGet();
	 private ExecutorService executorService;
	 Map<String, ProductResult> resultProdList = new HashMap<>();
	 
	 public static DomesinProductGet get() {
		 return instance;
	 }
	

	 /*
	  *  콜백 메서드.. 
	  */
	 private  CompletionHandler<String, Void> callback = new CompletionHandler<String, Void>() {
	        @Override
	        public void completed(String result, Void attachment) {
	        	ProductResult productDomesin;
				try {
					productDomesin = DomesinCommon.get().getProductMapping(result);
					resultProdList.put(productDomesin.current_page, productDomesin);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }

	        @Override
	        public void failed(Throwable exc, Void attachment) {
	            System.out.println("실패 콜백 완료");
	        }
	    };
	    
	    
	    
	    public void getProduct( Map<String, String> parameters) {
	    	try {
				DomesinCommon.get().Login("","");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	String response =     getApiProduct(1,"", "",parameters  );
	    	try {
				ProductResult productDomesin =			DomesinCommon.get().getProductMapping(response);
				int totalpage =    YDMAStringUtil.convertToInt(productDomesin.total_page);
				
				resultProdList.put("1", productDomesin);
				
				for(int i = 2; i<=totalpage; ++i) {
					getProduct(2,"", "",parameters  );
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	    
	    
	    public String getApiProduct(int page , String userID, String passwd, Map<String, String> parameters) {
	    	String path = "/API/COUPANG2/item_list.php";

			String response = null;
			int totalCount = 0;

			parameters.put("page", String.valueOf(page)); // 첫번째 페이지만 조회
			parameters.put("m_id", DomesinCommon.get().UserId); // 사용자 ID
			parameters.put("api_key", DomesinCommon.get().UserApiKey); // API Key
			String postForm =  DomesinCommon.get().ConvertDictionaryToParameter(parameters);

			System.out.println(postForm);
			response = HttpClientEx.get().addParam("ContentType", "application/x-www-form-urlencoded")
					.Post(DomesinCommon.get().DATA_HOST.concat(path), postForm);
			
			return response;
	    }
	    
	    
	    //  작업 시작.. 
	    public  void getProduct(int page , String userID, String passwd, Map<String, String> parameters){
	        Runnable task = new Runnable() {
	            @Override
	            public void run() {
	                try {
	                	String response =	  getApiProduct(page, userID, passwd, parameters);
	                    callback.completed(response, null);
	                }catch (Exception e){
	                    callback.failed(e, null);
	                }
	            }
	        };
	        executorService.submit(task);
	    }
	 
	 
	 
	 
}

package com.kdj.mlink.ezup3.common;

import java.util.ArrayList;
import java.util.List;

import com.kdj.mlink.ezup3.data.dao.CommonDao;

public class YDMACommonCode {

	/**
	 * 상품코드리스트 반환
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAllProductCode() throws Exception {
		CommonDao dao = new CommonDao();
		List<String> prodcdList = dao.getAllProductCode();
		return prodcdList;
	}

	/**
	 * 랙(창고) 정보가 정상적으로 처리된 상품코드리스트 반환
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAllRackProductCode() throws Exception {
		CommonDao dao = new CommonDao();
		List<String> list = dao.getAllRackProductCode();
		return list;
	}

	/**
	 * 택배사(배송) 정보가 정상적으로 처리된 상품코드리스트 반환
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAllExpProductCode() throws Exception {
		CommonDao dao = new CommonDao();
		List<String> list = dao.getAllExpProductCode();
		return list;
	}
	
	/**
	 * 삭제안된 상품정보로 처리된 상품코드리스트 반환
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAllProductCodeFoDelyn() throws Exception {
		CommonDao dao = new CommonDao();
		List<String> list = dao.getAllProductCodeFoDelyn();
		return list;
	}

	/**
	 * 택배사를 묶는 각각의 파일 명칭리스트
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAllExpfile() throws Exception {
		CommonDao dao = new CommonDao();
		List<String> list = dao.getAllExpfile();
		// 합동택배, 천ㅅ4, 대한통운, 기타 ...
		return list;
	}

	public static List<String> getAllExpCd() throws Exception {
		CommonDao dao = new CommonDao();
		List<String> list = dao.getAllExpCD();
		// 합동택배, 천ㅅ4, 대한통운, 기타 ...
		return list;
	}

	static String[] mallnm = { 
			"옥션 yewon101",
			"옥션 ydled",
			"옥션 ydinc",
			"옥션 win182",
			"지마켓 yewon104",
			"지마켓 ydstarled",
			"지마켓 ydinc1",
			"지마켓 win182",
			"11번가 yewon102",
			"11번가 starydled",
			"11번가 win182",
			"인터파크 qodnjstjq",
			"인터파크 qodnjstjq",
			"인터파크 win182",
			"스토어팜 ydinc",
			"스토어팜 win182",
			"스토어팜 ncp_1nlxwe_01",
			"스토어팜 ncp_1nmv2b_01",
			"스토어팜 ncp_1np0ix_01",
			"스토어팜 ydinc",
			"GS shop 1034601",
			"롯데홈쇼핑(신) 015093LT",
			"Cafe24 v1.9 ydinc",
			"Cafe24 v1.9 win182",
			"Cafe24 v1.9 ydpet",
			"고도몰 ydinc",
			"네이버 페이 ydinc",
			"위메프 wmpp92030",
			"위메프 win182",
			"쿠팡 ydinc2014",
			"쿠팡 win182",
			"티켓몬스터 와이디",
			"티켓몬스터 ydinc",
			"티켓몬스터 win182",
			"티켓몬스터 ydmall",
			"melchi ydinc2017",
			"오너클랜 2010017113",
			"다이소몰(신) ydinc",
			"다이소몰(신) win182",
			"이랜드몰 V170005012",
			"도매창고 ydinc",
			"도매카페 ydinc2019",
			"도매의신 ydinc",
			"도매꾹 ydinc",
			"위메프(신) wmpp92030",
			"위메프(신) win182"
			};

	public static String[] getMallnm () {
		return mallnm;
	}
	
//	public static List<String> getMallCd() {
//		List<String> list = new ArrayList<String>();
//		for (String nm : mallnm) {
//			list.add(nm);
//		}
//
//		return list;
//	}
}

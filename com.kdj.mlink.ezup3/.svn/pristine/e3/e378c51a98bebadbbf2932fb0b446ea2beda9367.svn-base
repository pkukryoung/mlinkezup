package com.kdj.mlink.ezup3.shop.domesin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdj.mlink.ezup3.shop.common.HttpClientEx;

public class DomesinAPI {

	private static DomesinAPI instance = new DomesinAPI();

	public static DomesinAPI get() {
		return instance;
	}

	final static String LOGIN_HOST = "http://www.domesin.com";

	final static String DATA_HOST = "http://data.domesin.com";

	private final static class CategoryDomesin {
		@JsonProperty("code")
		public String code;
		@JsonProperty("data")
		public CategoryItemDomesinDto[] data;
	}

	public String ConvertDictionaryToParameter(Map<String, String> parameters) {
		List<String> list = new ArrayList<>();
		for (String key : parameters.keySet()) {
			String value = parameters.get(key);
			list.add(key + "=" + value);
		}

		return list.stream().collect(Collectors.joining("&"));
	}

	private boolean loadCategory() throws Exception {
		Map<String, String> postPara = null;
		String response = "";
		String path = "/API/COUPANG/category.php";
		System.out.println("[DoMeSin] load category start...");

		// 파라메터 생성
		postPara = new HashMap<>();

		postPara.put("id", DomesinSessonUtil.get().getUserId());
		postPara.put("api_key", DomesinSessonUtil.get().getApiKey());
		String postForm = ConvertDictionaryToParameter(postPara);
		response = HttpClientEx.get().addParam("ContentType", "application/x-www-form-urlencoded")
				.Post(DATA_HOST.concat(path), postForm);

		if (response == null)
			return false;

		ObjectMapper mapper = new ObjectMapper();

		CategoryDomesin Category = null;
		Category = mapper.readValue(response, CategoryDomesin.class);

		if (Category.code.equals("0000")) {
//			int maxDebug = (Category.data.length > 5) ? 5 : Category.data.length;
//			System.out.println("    debug counts: " + maxDebug);
//
//			for (int idx = 0; idx < maxDebug; idx++) {
//
//				System.out.println("    [" + idx + "]");
//				System.out.println("      cid: " + Category.data[idx].cid);
//				System.out.println("      name: " + Category.data[idx].name);
//				System.out.println("      deep: " + Category.data[idx].deep);
//				System.out.println("      parent_cid: " + Category.data[idx].parent_cid);
//				System.out.println("      ca_qty: " + Category.data[idx].ca_qty);
//				System.out.println("      fullcat: " + Category.data[idx].fullcat);
//				System.out.println("      ec: " + Category.data[idx].ec);
//			}

			List<CategoryItemDomesinDto> largeCategory = Arrays.asList(Category.data).stream()
					.filter(p -> p.deep.equals("1")).collect(Collectors.toList());
			DomesinSessonUtil.get().setLargeCategory(largeCategory);

			// CateDao.get().categoryLargeBatch(largeCategory); // 디비 저장..

			List<CategoryItemDomesinDto> midCategory = Arrays.asList(Category.data).stream()
					.filter(p -> p.deep.equals("2")).collect(Collectors.toList());
			DomesinSessonUtil.get().setMidCategory(midCategory);

			// CateDao.get().categoryMidiumBatch(midCategory); // 디비 저장..

			List<CategoryItemDomesinDto> smallCategory = Arrays.asList(Category.data).stream()
					.filter(p -> p.deep.equals("3")).collect(Collectors.toList());

			DomesinSessonUtil.get().setSmallCategory(smallCategory);

			// CateDao.get().categorySmallBatch(smallCategory); // 디비 저장..

			DomesinSessonUtil.get().setAllCategory(Arrays.asList(Category.data));

		} else {
			String FailReason = "카테고리에 정의되지 않은 에러가 있습니다...";
			System.out.println("    Error: " + FailReason);
		}

		System.out.println("[DoMeSin] load category finish...");
		return true;
	}

	/*
	 * 로그인..
	 */
	public boolean Login(String id, String password) throws Exception {
		boolean retVal = false;

		String path = "/API/COUPANG/login.php";
		String formParams = String.format("id=%s&pw=%s", id, password);
		String response = HttpClientEx.get().addParam("ContentType", "application/x-www-form-urlencoded")
				.Post(LOGIN_HOST.concat(path), formParams);

		if (response != null && response.length() > 0) {
			try {

				ObjectMapper mapper = new ObjectMapper();
				LoginResponseDto userInfo;

				userInfo = mapper.readValue(response, LoginResponseDto.class);

				if (userInfo.code.equals("0000")) {

					retVal = true; // 로그인 성공..formParams
					DomesinSessonUtil.get().setUserInfo(userInfo);

					loadCategory();
				} else {
					// Login Fail
					switch (userInfo.code) {
					case "1001":
						DomesinSessonUtil.get().ResultLoginText = "로그인하실 수 없습니다. 고객센터에 문의해 주십시오(차단아이디)";
						break;
					case "1002":
						DomesinSessonUtil.get().ResultLoginText = "승인대기중 이십니다. 승인후 이용하실 수 있습니다.";
						break;
					case "1003":
						DomesinSessonUtil.get().ResultLoginText = "공급사회원은 이용하실 수 없습니다. 셀러회원 신청후 이용하실 수 있습니다.";
						break;
					case "1004":
						DomesinSessonUtil.get().ResultLoginText = "기타오류(인증과정에 문제가 발생했습니다. 고객센터로 문의해 주세요)";
						break;
					case "1005":
						DomesinSessonUtil.get().ResultLoginText = "판매회원 인증키가 없습니다. 관리자 승인후 이용하실 수 있습니다. 고객센터로 요청해 주세요";
						break;
					case "1006":
						DomesinSessonUtil.get().ResultLoginText = "회원아이디 또는 비밀번호를 확인해 주세요";
						break;
					default:
						DomesinSessonUtil.get().ResultLoginText = "정의되어 있지 않는 에러입니다. 에러번호: [" + userInfo.code
								+ "], 고객센터로 문의해 주세요";
						break;
					}

					retVal = false;
				}
			} catch (Exception e) {
				retVal = false;
				DomesinSessonUtil.get().ResultLoginText = e.getMessage();
			}
		} else {
			retVal = false;
			DomesinSessonUtil.get().ResultLoginText = "로그인 서버로부터 응답이 없습니다.";
		}
		return retVal;
	}

}

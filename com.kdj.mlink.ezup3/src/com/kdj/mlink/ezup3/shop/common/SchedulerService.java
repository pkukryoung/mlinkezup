package com.kdj.mlink.ezup3.shop.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.AlimTalkChargeDao;
import com.kdj.mlink.ezup3.shop.dao.ScheduleInfoDao;
import com.kdj.mlink.ezup3.shop.dao.ScheduleInfoDto;
import com.kdj.mlink.ezup3.shop.dao.ShopCommonDao;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;
import com.kdj.mlink.ezup3.shop.dao.ShoppingMallDetailDto;

public class SchedulerService {

	private static SchedulerService instance = new SchedulerService();
	public String ZONE_URL = "https://oapi.ecounterp.com/OAPI/V2/Zone";
	public String REQ_METHOD = "POST";
	public String ACCEPT = "application/json";
	public String CONTENT_TYPE = "application/json";

	private SchedulerService() {
	}

	public static SchedulerService get() {
		return instance;
	}

	// 스케쥴러세팅하기
	public void schedulerSetup(int jobcd, String key, String value) {

	}

	private ScheduleInfoDto setDto(List<ScheduleInfoDto> schedlist) {
		ScheduleInfoDto dto = new ScheduleInfoDto();
		dto.setCompno(schedlist.get(0).getCompno());
		dto.setJobcd(schedlist.get(0).getJobcd());
		dto.setJobstat(schedlist.get(0).getJobstat());
		dto.setExecid(schedlist.get(0).getExecid());
		dto.setExecdt(schedlist.get(0).getExecdt());
		dto.setWeekchk1(schedlist.get(0).getWeekchk1());
		dto.setFewminu1(schedlist.get(0).getFewminu1());
		dto.setJobexe11(schedlist.get(0).getJobexe11());
		dto.setJobexe21(schedlist.get(0).getJobexe21());
		dto.setJobexe31(schedlist.get(0).getJobexe31());
		dto.setJobexe41(schedlist.get(0).getJobexe41());
		dto.setWeekchk2(schedlist.get(0).getWeekchk2());
		dto.setFewminu2(schedlist.get(0).getFewminu2());
		dto.setJobexe12(schedlist.get(0).getJobexe12());
		dto.setJobexe22(schedlist.get(0).getJobexe22());
		dto.setJobexe32(schedlist.get(0).getJobexe32());
		dto.setJobexe42(schedlist.get(0).getJobexe42());
		dto.setWeekchk3(schedlist.get(0).getWeekchk3());
		dto.setFewminu3(schedlist.get(0).getFewminu3());
		dto.setJobexe13(schedlist.get(0).getJobexe13());
		dto.setJobexe23(schedlist.get(0).getJobexe23());
		dto.setJobexe33(schedlist.get(0).getJobexe33());
		dto.setJobexe43(schedlist.get(0).getJobexe43());
		dto.setWeekchk4(schedlist.get(0).getWeekchk4());
		dto.setFewminu4(schedlist.get(0).getFewminu4());
		dto.setJobexe14(schedlist.get(0).getJobexe14());
		dto.setJobexe24(schedlist.get(0).getJobexe24());
		dto.setJobexe34(schedlist.get(0).getJobexe34());
		dto.setJobexe44(schedlist.get(0).getJobexe44());
		dto.setSitechk(schedlist.get(0).getSitechk());
		dto.setTypechk(schedlist.get(0).getTypechk());
		dto.setMaxchk(schedlist.get(0).getMaxchk());
		dto.setMaxcnt(schedlist.get(0).getMaxcnt());
		dto.setExcepchk1(schedlist.get(0).getExcepchk1());
		dto.setExceps1(schedlist.get(0).getExceps1());
		dto.setExcepe1(schedlist.get(0).getExcepe1());
		dto.setExcepchk2(schedlist.get(0).getExcepchk2());
		dto.setExceps2(schedlist.get(0).getExceps2());
		dto.setExcepe2(schedlist.get(0).getExcepe2());
		dto.setExcepchk3(schedlist.get(0).getExcepchk3());
		dto.setExceps3(schedlist.get(0).getExceps3());
		dto.setExcepe3(schedlist.get(0).getExcepe3());
		dto.setOutofexe(schedlist.get(0).getOutofexe());
		dto.setSmschk(schedlist.get(0).getSmschk());
		dto.setSmsno(schedlist.get(0).getSmsno());
		dto.setDontchk(schedlist.get(0).getDontchk());
		dto.setDontstr(schedlist.get(0).getDontstr());
		dto.setDontend(schedlist.get(0).getDontend());
		dto.setEmailchk(schedlist.get(0).getEmailchk());
		dto.setEmailaddr(schedlist.get(0).getEmailaddr());
		String shopseq = "";
		for (int i = 0; i < schedlist.size(); i++) {
			if (i + 1 == schedlist.size()) {
				shopseq += schedlist.get(i).getShopseq();
			} else {
				shopseq += schedlist.get(i).getShopseq() + ",";
			}

		}
		dto.setShopseq(shopseq);

		return dto;
	}

	// shoporderlist 다 가지고 오기
	private List<ShopOrderMstDto> getShopOrderAllList() {
		List<ShopOrderMstDto> params = new ArrayList<>();
		try {
			List<ShoppingMallDetailDto> list = ShopCommonDao.get().ShoppingMallDetailList();
			for (ShoppingMallDetailDto d : list) {
				ShopOrderMstDto dto = new ShopOrderMstDto();
				dto.setShopid(d.getShopcd());
				dto.setShop_userid(d.getShoppingid());
				dto.setShopPw(d.getPassword());
				dto.setApikey(d.getApikey());

				// 쿠팡 인증키..
				dto.setAuthkey1(d.getAuthkey1());
				dto.setAuthKey2(d.getAuthkey2());
				dto.setVendorId(d.getVendorId());

				dto.setStartDt(YDMATimeUtil.getCurrentDateHanjin().concat("0000"));
				dto.setEndDt(YDMATimeUtil.getCurrentTime().substring(0, 12));
				params.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return params;
	}

	// 제외 시간..
	private List<Integer> getNotHour(Integer start, Integer end) {
		List<Integer> ret = new ArrayList<>();
		Integer cnt = start;
		while (cnt != end) {
			if (cnt == 24)
				cnt = 0;
			ret.add(cnt);
			cnt++;
		}
		ret.add(cnt);
		return ret;
	}

	// Hour 시간..
	private List<Integer> getHourList(List<Integer> hourList, List<Integer> notHour) {
		List<Integer> retList = new ArrayList<>();
		for (Integer hour : hourList) {
			if (!notHour.contains(hour)) {
				retList.add(hour);
			}
		}
		return retList;
	}

	private String getHourString(List<Integer> hourList) {
		String ret = "";
		ret = hourList.stream().map(h -> String.valueOf(h)).collect(Collectors.joining(","));
		return ret;
	}

	// 메일보내기
	public void sendEmail(List<String> complist, String emailaddr) throws Exception {
		try {
			final String username = "hosikan@kdjsystem.com";
			final String password = "dksghtlr1";

			Properties props = new Properties();
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("hosikan@kdjsystem.com"));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailaddr));
			message.setSubject("스케쥴링 처리현황");

			String body = YDMATimeUtil.getCurrentDate() + " 스케쥴링이 정상적으로 동작하였습니다.";
			message.setText(body);

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	// 카카오톡
	public List<String> sendKakao(String smsno, String startdt, String enddt, String smschk, List<String> comlist) {
		// String test_profile_key = "89823b83f2182b1e229c2e95e21cf5e6301eed98";
		List<String> responContents = new ArrayList<>();
		try {
			String userid = "kdjsystem";
			String real_profile_key = comlist.get(21);

			String url = "https://alimtalk-api.sweettracker.net/v2/" + real_profile_key + "/sendMessage";

			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();

			AlimTalkChargeDao aldao = new AlimTalkChargeDao();
			List<String> allist = aldao.getAlimTalkChargeList(comlist.get(0));

			// add reuqest header
			httpConnection.setRequestMethod(REQ_METHOD);
			httpConnection.setRequestProperty("Accept", ACCEPT);
			httpConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
			httpConnection.setRequestProperty("userid", userid);
			// Send post request
			httpConnection.setDoOutput(true);
			int seq = Integer.parseInt(allist.get(7));
			StringBuffer payload = new StringBuffer();
			payload.append("[");

			String num = String.format("%011d", seq + 1);
			payload.append("{"); //
			payload.append("\"msgid\"").append(":\"").append(comlist.get(24) + num).append("\","); // 계정
			// payload.append("\"msgid\"").append(":\"").append(comlist.get(1)+orddt+ordseq+list.get(0)).append("\",");
			// //계정
			payload.append("\"profile_key\"").append(":\"").append(real_profile_key).append("\","); // 발신프로필키
			if (comlist.get(0).equals("1")) {
				payload.append("\"template_code\"").append(":\"").append("").append("\","); // 템플릿코드
			} else if (comlist.get(0).equals("2")) {
				payload.append("\"template_code\"").append(":\"").append("udt001").append("\","); // 템플릿코드
			} else {
				payload.append("\"template_code\"").append(":\"").append("kdj002").append("\","); // 템플릿코드
			}
			// payload.append("\"template_code\"").append(":\"").append("alimtalktest_001").append("\",");
			// //템플릿코드
			payload.append("\"receiver_num\"").append(":\"").append(splitMark(smsno)).append("\","); // 전화번호
			// payload.append("\"receiver_num\"").append(":\"").append("01024426116").append("\",");
			// //전화번호
			payload.append("\"message\"").append(":\"").append(YDMATimeUtil.getCurrentDate() + "스케쥴링이 정상적으로 동작하였습니다.")
					.append("\","); // 메세지
			payload.append("\"reserved_time\"").append(":\"").append("00000000000000").append("\","); // 즉시전송
			payload.append("\"sms_message\"").append(":\"")
					.append(YDMATimeUtil.getCurrentDate() + "스케쥴링이 정상적으로 동작하였습니다.").append("\","); // 비즈메세지실패시문자대체메세지
			// payload.append("\"sms_message\"").append(":\"").append("\",");
			// //비즈메세지실패시문자대체메세지
			payload.append("\"sms_title\"").append(":\"").append("\","); //

			payload.append("\"sms_kind\"").append(":\"").append("S").append("\","); // 발송하지않음

			payload.append("\"sender_num\"").append(":\"").append("0326751102").append("\","); // 발신번호
			payload.append("\"parcel_company\"").append(":\"").append("08").append("\","); // 택배코드
			payload.append("\"parcel_invoice\"").append(":\"").append("").append("\""); // 송장

			payload.append("} ");

			payload.append("\n"); // --지워야함

			payload.append("] ");

			System.out.println(payload);

			DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
			out.write(payload.toString().getBytes("UTF-8"));
			out.flush();
			out.close();

			int responseCode = httpConnection.getResponseCode();

			System.out.println("카카오톡: " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
			String inputLine = null;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			httpConnection.disconnect();

			System.out.println(response.toString());

			JsonParser jsonParser = new JsonParser();
			JsonElement jsonObject = jsonParser.parse(response.toString());
			// String statusCode = jsonObject.getAsJsonObject().get("result").getAsString();
			// if(statusCode.equals("Y")) {
			String result;
			JsonElement dataObject = jsonObject.getAsJsonArray();
			for (int i = 0; i < dataObject.getAsJsonArray().size(); i++) {
				JsonElement jsonElement = dataObject.getAsJsonArray().get(i);
				result = jsonElement.getAsJsonObject().get("result").getAsString();
				if (result.equals("Y")) {
					// for (int i = 0; i < contents_target.size(); i++) {
					// JsonElement jsonElement = (JsonElement) jsonArray_result.get(i);
					String msgid = jsonElement.getAsJsonObject().get("msgid").getAsString();
					String code = jsonElement.getAsJsonObject().get("code").getAsString();
					String error = jsonElement.getAsJsonObject().get("error").getAsString();
					String kind = jsonElement.getAsJsonObject().get("kind").getAsString();
					String sendtime = jsonElement.getAsJsonObject().get("sendtime").getAsString();
					List<String> list = new ArrayList<>();
					responContents.add(result);
					responContents.add(msgid);
					responContents.add(code);
					responContents.add(error);
					responContents.add(kind);
					responContents.add(smsno);
					responContents.add(YDMATimeUtil.getCurrentDate() + "스케쥴링이 정상적으로 동작하였습니다.");
					responContents.add(sendtime);

					// }
				} else {
					List<String> list = new ArrayList<>();
					responContents.add(jsonElement.getAsJsonObject().get("result").getAsString());
					responContents.add(jsonElement.getAsJsonObject().get("msgid").getAsString());
					responContents.add(jsonElement.getAsJsonObject().get("code").getAsString());
					responContents.add(jsonElement.getAsJsonObject().get("error").getAsString());
					responContents.add(jsonElement.getAsJsonObject().get("kind").getAsString());
					responContents.add(jsonElement.getAsJsonObject().get("sendtime").getAsString());

				}
			}

//			 String result = dataObject.getAsJsonArray().get(0).getAsString();
//			 JsonArray jsonArray_result = dataObject.getAsJsonArray();

		} catch (Exception e) {
			e.getMessage();
		}
		return responContents;
	}

	public String splitMark(String text) {
		String[] split = text.split("-| ");
		String complite = "";
		for (String element : split) {
			complite += element;
			complite = complite.trim();
		}
		return complite;
	}

	// 디비에 있는지 없는지 여부 체크
	public void schedulerYesorNo() {
		ScheduleInfoDao dao = new ScheduleInfoDao();
		try {
			List<ScheduleInfoDto> list = dao.getJobcd();
			for (ScheduleInfoDto dto : list) {
				System.out.println(dto.getJobcd());
				schedulerSetup(dto.getJobcd(), "Order", "Ordergroup1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

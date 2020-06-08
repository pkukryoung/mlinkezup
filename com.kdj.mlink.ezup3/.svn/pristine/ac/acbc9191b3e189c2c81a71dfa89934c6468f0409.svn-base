package com.kdj.mlink.ezup3.shop.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class NaverSearch {

	private static class NaverRank {
		public String message;
		public Integer statusCode;
		public Integer returnCode;
		public String range;
		public String datetime;
		public String date;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Integer getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(Integer statusCode) {
			this.statusCode = statusCode;
		}

		public Integer getReturnCode() {
			return returnCode;
		}

		public void setReturnCode(Integer returnCode) {
			this.returnCode = returnCode;
		}

		public String getRange() {
			return range;
		}

		public void setRange(String range) {
			this.range = range;
		}

		public String getDatetime() {
			return datetime;
		}

		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public List<NaverRanks> getRanks() {
			return ranks;
		}

		public void setRanks(List<NaverRanks> ranks) {
			this.ranks = ranks;
		}

		public List<NaverRanks> ranks;
	}

	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = YDMASessonUtil.getAppPath() + "\\YDwmsData\\chromedriver.exe";
	static WebDriver driver;

	public static List<NaverRanks> getNaverBestSearch() throws IOException {
		NaverRank naverRank = null;
		try {
			String URL = "https://datalab.naver.com/shoppingInsight/sCategory.naver";

			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments(
					"user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36");
			options.addArguments("window-size=1920x1080");
			options.addArguments("disable-gpu");
			driver = new ChromeDriver(options);

			Map<String, String> cookie = ChromeScript.get().getCookies((ChromeDriver) driver);

			Connection.Response rs = Jsoup
					.connect("https://datalab.naver.com/shoppingInsight/getCategoryKeywordRank.naver").cookies(cookie)
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36")
					.data("cid", "50000805").data("timeUnit", "date").data("startDate", "2020-02-26")
					.data("endDate", "2020-03-26").data("age", "").data("gender", "").data("device", "")
					.data("page", "1").data("count", "20")
					.referrer("https://datalab.naver.com/shoppingInsight/sCategory.naver")
					.method(Connection.Method.POST)
//                .timeout(2000)
					.execute();

			String body = rs.body();
			System.out.println(body);
			ObjectMapper mapper = new ObjectMapper();
			naverRank = mapper.readValue(body, NaverRank.class);

		} catch (Exception e) {

		} finally {
			driver.quit();
		}

		return naverRank.getRanks();
	}
}

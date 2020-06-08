package com.kdj.mlink.ezup3.shop.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/*
 *  로그인 공통처리.. 
 */
public interface IShopCommonLogin {

	public default ChromeDriver login(String loginID, String password, String shopcd, boolean flag) throws Exception {
		switch (shopcd) {
		case ShopCommon.옥션:
			return loginAuction(loginID, password, flag);
		case ShopCommon.지마켓:
			return loginGmarket(loginID, password, flag);
		case ShopCommon.인터파크:
			return loginInterPark(loginID, password, flag);
		case ShopCommon.스토어팜:
			return loginNaverStore(loginID, password, flag);
		case ShopCommon.십일번가:
			return loginelevvenst(loginID, password, flag);
		case ShopCommon.카페24:
			return loginCafe24(loginID, password, flag);
		case ShopCommon.티몬:
			return loginTmon(loginID, password, flag);
		case ShopCommon.카카오스토어:
			return loginKakao(loginID, password, flag);
		case ShopCommon.후이즈:
			return loginTmon(loginID, password, flag);
		case ShopCommon.쿠팡:
			return loginCoupang(loginID, password, flag);
		}
		return null;
	}

	public default ChromeDriver login(String loginID, String password, String shopcd, ChromeDriver driver)
			throws Exception {
		switch (shopcd) {
		case ShopCommon.옥션:
			return loginAuction(loginID, password, driver);
		case ShopCommon.지마켓:
			return loginGmarket(loginID, password, driver);
		case ShopCommon.인터파크:
			return loginInterPark(loginID, password, driver);
		case ShopCommon.스토어팜:
			return loginNaverStore(loginID, password, driver);
		case ShopCommon.십일번가:
			return loginelevvenst(loginID, password);
		case ShopCommon.카페24:
			return loginCafe24(loginID, password, driver);
		case ShopCommon.티몬:
			return loginTmon(loginID, password);
		case ShopCommon.카카오스토어:
			return loginKakao(loginID, password, driver);
		case ShopCommon.후이즈:
			return loginTmon(loginID, password);
		}
		return null;
	}

	// 쿠팡
	public default ChromeDriver loginCoupang(String loginID, String password, boolean flag) throws Exception {// 11번가
																												// API라서
																												// 따로
																												// 없음..
		if (flag) {
			final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
			try {
				String URL = "https://wing.coupang.com/login?returnUrl=https%3A%2F%2Fwing.coupang.com%2F";
				ChromeScript.get().get(driver, URL);

				ChromeScript.get().addScript(String.format("document.querySelector('#userID').value = '%s';", loginID))
						.addScript(String.format("document.querySelector('#userPWD').value = '%s';", password))
						.addScript("document.querySelector('#btnLogin').click();").executeScripter(driver);
				ChromeScript.get().waitOtherPageSleep(URL);
			} catch (Exception e) {
				String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
				throw new Exception(message);
			}
			return driver;
		}
		return null;
	}

	// 11번가
	public default ChromeDriver loginelevvenst(String loginID, String password, boolean flag) throws Exception {// 11번가
																												// API라서
																												// 따로
																												// 없음..
		if (flag) {
			final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
			try {
				String URL = "https://login.11st.co.kr/auth/front/selleroffice/login.tmall?returnURL=http://soffice.11st.co.kr";
				ChromeScript.get().get(driver, URL);

				ChromeScript.get().addScript(String.format("$('#user-id').val('%s');", loginID))
						.addScript(String.format("$('#passWord').val('%s');", password))
						.addScript("$('button.btn_atype.btn_a').trigger('click');").executeScripter(driver);
				ChromeScript.get().waitOtherPageSleep(URL);
			} catch (Exception e) {
				String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
				throw new Exception(message);
			}
			return driver;
		}
		return null;
	}

	public default ChromeDriver loginelevvenst(String loginID, String password) throws Exception {
		return null;
	}

	public default ChromeDriver loginAuction(String loginID, String password, ChromeDriver driver) throws Exception {// 옥션
																														// 로그인..
		try {

			// driver = ChromeExtention.getInstace().getDriver();
			// driver.quit();
			String URL = "https://www.esmplus.com/Member/SignIn/LogOn";
			ChromeScript.get().get(driver, URL);

			/* 옥션 로그인 처리 eq0 : 옥션 , eq1 지마켓 */

			ChromeScript.get().addScript(String.format("$('input:radio[name=rdoSiteSelect]:eq(0)').trigger('click');")) // 옥션..
					.addScript(String.format("$('#SiteId').val('%s');", loginID))
					.addScript(String.format("$('#SitePassword').val('%s');", password))
					.addScript("$('#btnSiteLogOn').trigger('click'); ").executeScripter(driver);
			ChromeScript.get().waitOtherPageSleep(URL, driver);
		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	// 옥션 로그인..
	public default ChromeDriver loginAuction(String loginID, String password, boolean flag) throws Exception {// 옥션
																												// 로그인..
		final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		try {

			// driver = ChromeExtention.getInstace().getDriver();
			// driver.quit();
			String URL = "https://www.esmplus.com/Member/SignIn/LogOn";
			ChromeScript.get().get(driver, URL);

			/* 옥션 로그인 처리 eq0 : 옥션 , eq1 지마켓 */

			ChromeScript.get().addScript(String.format("$('input:radio[name=rdoSiteSelect]:eq(0)').trigger('click');")) // 옥션..
					.addScript(String.format("$('#SiteId').val('%s');", loginID))
					.addScript(String.format("$('#SitePassword').val('%s');", password))
					.addScript("$('#btnSiteLogOn').trigger('click'); ").executeScripter(driver);
			ChromeScript.get().waitOtherPageSleep(URL);
		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	// 지마켓 로그인..
	public default ChromeDriver loginGmarket(String loginID, String password, ChromeDriver driver) throws Exception { // 지마켓
																														// 로그인..
		try {

			// driver = ChromeExtention.getInstace().getDriver();
			// driver.quit();
			String URL = "https://www.esmplus.com/Member/SignIn/LogOn";
			ChromeScript.get().get(driver, URL);

			/* 옥션 로그인 처리 eq0 : 옥션 , eq1 지마켓 */

			ChromeScript.get().addScript(String.format("$('input:radio[name=rdoSiteSelect]:eq(1)').trigger('click');")) // 옥션..
					.addScript(String.format("$('#SiteId').val('%s');", loginID))
					.addScript(String.format("$('#SitePassword').val('%s');", password))
					.addScript("$('#btnSiteLogOn').trigger('click'); ").executeScripter(driver);
			ChromeScript.get().waitOtherPageSleep(URL, driver);
		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	// 지마켓 로그인..
	public default ChromeDriver loginGmarket(String loginID, String password, boolean flag) throws Exception { // 지마켓
																												// 로그인..
		final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		try {

			// driver = ChromeExtention.getInstace().getDriver();
			// driver.quit();
			String URL = "https://www.esmplus.com/Member/SignIn/LogOn";
			ChromeScript.get().get(driver, URL);

			/* 옥션 로그인 처리 eq0 : 옥션 , eq1 지마켓 */

			ChromeScript.get().addScript(String.format("$('input:radio[name=rdoSiteSelect]:eq(1)').trigger('click');")) // 옥션..
					.addScript(String.format("$('#SiteId').val('%s');", loginID))
					.addScript(String.format("$('#SitePassword').val('%s');", password))
					.addScript("$('#btnSiteLogOn').trigger('click'); ").executeScripter(driver);
			ChromeScript.get().waitOtherPageSleep(URL);
		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	// 인터파크 로그인..
	public default ChromeDriver loginInterPark(String loginID, String password, ChromeDriver driver) throws Exception {// 인터파크
																														// 로그인..
		// final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		String URL = "https://ipss.interpark.com/member/login.do?_method=loginIframe&entrJoin=N&ipssYn=Y&tabId=spSeller&logintgt=null&enterEntr=Y";

		try {
			ChromeScript.get().get(driver, URL);

			WebElement elementloginBox = driver.findElement(By.xpath("/html/body/form[1]/div"));
			elementloginBox.findElement(By.xpath("/html/body/form/div/div/div[1]/div[1]/ul/li[1]/div/input"))
					.sendKeys(loginID);
			elementloginBox.findElement(By.xpath("/html/body/form/div/div/div[1]/div[1]/ul/li[2]/div/input"))
					.sendKeys(password);
			elementloginBox.findElement(By.xpath("/html/body/form/div/div/div[1]/div[1]/div[2]/button")).click();

			ChromeScript.get().waitOtherPageSleep(URL, driver); // 로그인이 완료 될때 까지 기다린다.
//			URL = "http://ipss.interpark.com/service/SellerProductAlimiNQnaServiceManager.do?_method=initial&_style=ipssPro";
//			
//			ChromeScript.get().get(driver,URL);
		} catch (Exception e) {
			throw new Exception("인터파크 로그인싫패");
		}
		return driver;
	}

	// 인터파크 로그인..
	public default ChromeDriver loginInterPark(String loginID, String password, boolean flag) throws Exception {// 인터파크
																												// 로그인..
		final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		String URL = "https://ipss.interpark.com/member/login.do?_method=loginIframe&entrJoin=N&ipssYn=Y&tabId=spSeller&logintgt=null&enterEntr=Y";

		try {
			ChromeScript.get().get(driver, URL);

			WebElement elementloginBox = driver.findElement(By.xpath("/html/body/form[1]/div"));
			elementloginBox.findElement(By.xpath("/html/body/form/div/div/div[1]/div[1]/ul/li[1]/div/input"))
					.sendKeys(loginID);
			elementloginBox.findElement(By.xpath("/html/body/form/div/div/div[1]/div[1]/ul/li[2]/div/input"))
					.sendKeys(password);
			elementloginBox.findElement(By.xpath("/html/body/form/div/div/div[1]/div[1]/div[2]/button")).click();

			ChromeScript.get().waitOtherPageSleep(URL); // 로그인이 완료 될때 까지 기다린다.
//			URL = "http://ipss.interpark.com/service/SellerProductAlimiNQnaServiceManager.do?_method=initial&_style=ipssPro";
//			
//			ChromeScript.get().get(driver,URL);
		} catch (Exception e) {
			throw new Exception("인터파크 로그인싫패");
		}
		return driver;
	}

	// 네이버 로그인..
	public default ChromeDriver loginNaverStore(String loginID, String password, ChromeDriver driver) throws Exception { // 네이버
																															// 로그인..
		// final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		try {

			String URL = "https://sell.smartstore.naver.com/#/login";
			ChromeScript.get().get(driver, URL);

			Thread.sleep(2000);
			WebElement elementloginBox = ChromeScript.get().until(driver,
					() -> ((ChromeDriver) driver).findElement(By.xpath("/html/body/ui-view[1]/div[3]/div/div/div")));
			if (loginID.contains("@")) {
				elementloginBox
						.findElement(
								By.xpath("/html/body/ui-view[1]/div[3]/div/div/div/form/div[1]/div/div[1]/div/input"))
						.sendKeys(loginID);
				elementloginBox
						.findElement(
								By.xpath("/html/body/ui-view[1]/div[3]/div/div/div/form/div[1]/div/div[2]/div/input"))
						.sendKeys(password);

				elementloginBox.findElement(By.xpath("/html/body/ui-view[1]/div[3]/div/div/div/form/div[2]/button"))
						.click();
				// Thread.sleep(500);
			} else {
				elementloginBox.findElement(By.xpath("/html/body/ui-view[1]/div[3]/div/div/div/form/div[1]/ul/li[2]/a"))
						.click();
				// Thread.sleep(2000);
				elementloginBox = driver.findElement(By.id("frmNIDLogin"));
				elementloginBox.findElement(By.id("id")).sendKeys(loginID);
				;
				elementloginBox.findElement(By.id("pw")).sendKeys(password);
				;
				elementloginBox.findElement(By.className("btn_global")).click();
				;

			}
			ChromeScript.get().waitOtherPageSleep(URL, driver); // 로그인이 완료 될때 까지 기다린다.
		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	// 네이버 로그인..
	public default ChromeDriver loginNaverStore(String loginID, String password, boolean flag) throws Exception { // 네이버
																													// 로그인..
		final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		try {

			String URL = "https://sell.smartstore.naver.com/#/login";
			ChromeScript.get().get(driver, URL);

			Thread.sleep(2000);
			WebElement elementloginBox = ChromeScript.get().until(driver,
					() -> ((ChromeDriver) driver).findElement(By.xpath("/html/body/ui-view[1]/div[3]/div/div/div")));
			if (loginID.contains("@")) {
				elementloginBox
						.findElement(
								By.xpath("/html/body/ui-view[1]/div[3]/div/div/div/form/div[1]/div/div[1]/div/input"))
						.sendKeys(loginID);
				elementloginBox
						.findElement(
								By.xpath("/html/body/ui-view[1]/div[3]/div/div/div/form/div[1]/div/div[2]/div/input"))
						.sendKeys(password);

				elementloginBox.findElement(By.xpath("/html/body/ui-view[1]/div[3]/div/div/div/form/div[2]/button"))
						.click();
				// Thread.sleep(500);
			} else {
				elementloginBox.findElement(By.xpath("/html/body/ui-view[1]/div[3]/div/div/div/form/div[1]/ul/li[2]/a"))
						.click();
				// Thread.sleep(2000);
				elementloginBox = driver.findElement(By.id("frmNIDLogin"));
				elementloginBox.findElement(By.id("id")).sendKeys(loginID);
				;
				elementloginBox.findElement(By.id("pw")).sendKeys(password);
				;
				elementloginBox.findElement(By.className("btn_global")).click();
				;

			}
			ChromeScript.get().waitOtherPageSleep(URL, driver); // 로그인이 완료 될때 까지 기다린다.
		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	// 카페24로그인..
	public default ChromeDriver loginCafe24(String loginID, String password, ChromeDriver driver) throws Exception { // 네이버
																														// 로그인..
		// final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		try {

			String URL = "https://eclogin.cafe24.com/Shop/";
			ChromeScript.get().get(driver, URL);

			WebElement elementloginBox = ChromeScript.get().until(driver, () -> ((ChromeDriver) driver)
					.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/form/div/div")));
			elementloginBox = driver.findElement(By.id("tabAdmin"));
			elementloginBox.findElement(By.id("mall_id")).sendKeys(loginID);
			elementloginBox.findElement(By.id("userpasswd")).sendKeys(password);
			elementloginBox.findElement(By.className("btnSubmit")).click();
			ChromeScript.get().waitOtherPageSleep(URL, driver); // 로그인이 완료 될때 까지 기다린다.
		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	// 카페24로그인..
	public default ChromeDriver loginCafe24(String loginID, String password, boolean flag) throws Exception { // 네이버
																												// 로그인..
		final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		try {

			String URL = "https://eclogin.cafe24.com/Shop/";
			ChromeScript.get().get(driver, URL);

			WebElement elementloginBox = ChromeScript.get().until(driver, () -> ((ChromeDriver) driver)
					.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/form/div/div")));
			elementloginBox = driver.findElement(By.id("tabAdmin"));
			elementloginBox.findElement(By.id("mall_id")).sendKeys(loginID);
			elementloginBox.findElement(By.id("userpasswd")).sendKeys(password);
			elementloginBox.findElement(By.className("btnSubmit")).click();
			ChromeScript.get().waitOtherPageSleep(URL);
		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	// Tmon..
	public default ChromeDriver loginTmon(String loginID, String password, boolean flag) throws Exception { // 네이버 로그인..
		final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		try {

			String URL = "https://spc.tmon.co.kr/member/login?return_url=%2F";
			ChromeScript.get().get(driver, URL);
			Thread.sleep(1000);

			/* 옥션 로그인 처리 eq0 : 옥션 , eq1 지마켓 */

			ChromeScript.get().addScript(String.format("$('#form_id').val('%s');", loginID))
					.addScript(String.format("$('#form_password').val('%s');", password)).addScript("submitLogin(); ")
					.executeScripter(driver);
			Thread.sleep(1000);

			ChromeScript.get().addScript("dalayModify(); ").executeScripter(driver);
			ChromeScript.get().waitOtherPageSleep(URL);

		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	// Tmon..
	public default ChromeDriver loginTmon(String loginID, String password) throws Exception { // 네이버 로그인..
		final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		try {

			String URL = "https://spc.tmon.co.kr/member/login?return_url=%2F";
			ChromeScript.get().get(driver, URL);
			Thread.sleep(1000);

			/* 옥션 로그인 처리 eq0 : 옥션 , eq1 지마켓 */

			ChromeScript.get().addScript(String.format("$('#form_id').val('%s');", loginID))
					.addScript(String.format("$('#form_password').val('%s');", password)).addScript("submitLogin(); ")
					.executeScripter(driver);
			Thread.sleep(1000);

			ChromeScript.get().addScript("dalayModify(); ").executeScripter(driver);
			ChromeScript.get().waitOtherPageSleep(URL);

		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	// Kakao..
	public default ChromeDriver loginKakao(String loginID, String password, ChromeDriver driver) throws Exception { // 네이버
																													// 로그인..
		try {

			String URL = "https://comm-auth-web.kakao.com/seller/index";
			ChromeScript.get().get(driver, URL);
			Thread.sleep(1000);

			ChromeScript.get().addScript("document.querySelector(\"#mArticle > div > div > button\").click();")
					.executeScripter(driver);
			Thread.sleep(2000);

			ChromeScript.get().addScript(String.format("$('#id_email_2').val('%s');", loginID))
					.addScript(String.format("$('#id_password_3').val('%s');", password))
					.addScript("document.querySelector(\"#login-form > fieldset > div.wrap_btn > button\").click()")
					.executeScripter(driver);
			Thread.sleep(2000);

			ChromeScript.get().addScript(
					"document.querySelector(\"#mArticle > div.inner_comm > div.shop_list > div.item_shop.item_on > button\").click()")
					.executeScripter(driver);
			Thread.sleep(2000);

			ChromeScript.get().addScript(
					"document.querySelector(\"body > cu-container > div > div > div > div > div > cu-dialog > div > div.popup_foot > a > span\").click()")
					.executeScripter(driver);
			Thread.sleep(2000);
			ChromeScript.get().waitOtherPageSleep(URL, driver); // 로그인이 완료 될때 까지 기다린다.
		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	// Kakao..
	public default ChromeDriver loginKakao(String loginID, String password, boolean flag) throws Exception { // 네이버
																												// 로그인..
		final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		try {

			String URL = "https://comm-auth-web.kakao.com/seller/index";
			ChromeScript.get().get(driver, URL);
			Thread.sleep(1000);

			ChromeScript.get().addScript("document.querySelector(\"#mArticle > div > div > button\").click();")
					.executeScripter(driver);
			Thread.sleep(2000);

			ChromeScript.get().addScript(String.format("$('#id_email_2').val('%s');", loginID))
					.addScript(String.format("$('#id_password_3').val('%s');", password))
					.addScript("document.querySelector(\"#login-form > fieldset > div.wrap_btn > button\").click()")
					.executeScripter(driver);
			Thread.sleep(2000);

			ChromeScript.get().addScript(
					"document.querySelector(\"#mArticle > div.inner_comm > div.shop_list > div.item_shop.item_on > button\").click()")
					.executeScripter(driver);
			Thread.sleep(2000);

			ChromeScript.get().addScript(
					"document.querySelector(\"body > cu-container > div > div > div > div > div > cu-dialog > div > div.popup_foot > a > span\").click()")
					.executeScripter(driver);
			Thread.sleep(2000);

		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	// Whois..
	public default ChromeDriver loginWhois(String loginID, String password) throws Exception { // 네이버 로그인..
		final ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		String adminID = "admin";
		try {

			String URL = "http://blueribbon.whoismall.com/_solution/_login/form.login.php?end_url=%2F";
			ChromeScript.get().get(driver, URL);
			Thread.sleep(1000);

			ChromeScript.get()
					.addScript(String.format("document.querySelector(\"#o_shop_account\").setValue('%s')", loginID))
					.addScript(String.format(
							"document.querySelector(\"#o_form > div > table > tbody > tr > td > div > table > tbody > tr > td:nth-child(1) > label:nth-child(2) > input\").setValue('%s');",
							adminID))
					.addScript(String.format(
							"document.querySelector(\"#o_form > div > table > tbody > tr > td > div > table > tbody > tr > td:nth-child(1) > label:nth-child(3) > input\").setValue('%s')",
							password))
					.addScript(
							"document.querySelector(\"#o_form > div > table > tbody > tr > td > div > table > tbody > tr > td:nth-child(2) > input[type=image]\").click();")
					.executeScripter(driver);
			ChromeScript.get().waitOtherPageSleep(URL);

		} catch (Exception e) {
			String message = String.format("사용자 아이디 : %s , 패스워드 : %s 로그인이  실패했습니다.", loginID, password);
			throw new Exception(message);
		}
		return driver;
	}

	public static Map<String, String> getCookiesAuctionOrGmarket(String id, String password, String shopcd) {
		Map<String, Map<String, String>> retMap = new HashMap<>();
		Map<String, String> mapCookie = new HashMap<>();
		final DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("https://www.esmplus.com/Member/SignIn/Authenticate");
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("Id", id));
		params.add(new BasicNameValuePair("Password", password));
		params.add(new BasicNameValuePair("Type", "S"));
		params.add(new BasicNameValuePair("ReturnUrl", ""));

		String type = (shopcd.equals(ShopCommon.옥션)) ? "IAC" : "GMKT";
		params.add(new BasicNameValuePair("SiteType", type));
		params.add(new BasicNameValuePair("RememberMe", "false"));

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// writing error to Log
			e.printStackTrace();
		} /* * Making HTTP Request */
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity respEntity = response.getEntity();
			if (respEntity != null) {
				Header[] hp = response.getAllHeaders();
				for (Header headers : hp) {
					System.out.println(headers);
				}

				List<Cookie> cookies = httpClient.getCookieStore().getCookies();

				if (cookies.isEmpty()) {
					System.out.println("비어있음");
				} else {

					for (Cookie cookieItem : cookies) {

						mapCookie.put(cookieItem.getName(), cookieItem.getValue());

						System.out.println("쿠키이름\t : " + cookieItem.getName());
						System.out.println("쿠키값\t : " + cookieItem.getValue());
						System.out.println("쿠키도메인 : " + cookieItem.getDomain());
					}
					retMap.put("Cookie", mapCookie);
				}

				String content = EntityUtils.toString(respEntity);
				System.out.println(content);
			}

			return mapCookie;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mapCookie;
	}

}

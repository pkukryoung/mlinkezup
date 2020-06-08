package com.kdj.mlink.ezup3.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kdj.mlink.ezup3.common.YDMASessonUtil;

public class SabangNetDemonThread extends Thread{
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = YDMASessonUtil.getAppPath() + "\\YDwmsData\\chromedriver.exe";
	WebDriver driver;
	
	@Override
	public void run() {
		String URL = "http://www.sabangnet.co.kr//";

		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		WebElement webElement;
		CompInfoDao dao = new CompInfoDao();
		List<String> list = new ArrayList<>();
		try {
			list = dao.getCompNoImage();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ChromeOptions options = new ChromeOptions();
		// options.addArguments("headless");
		options.addArguments(
				"user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36");
		options.addArguments("window-size=1920x1080");
		options.addArguments("disable-gpu");
		driver = new ChromeDriver(options);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get(URL);

		// 아이디입력
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"container\"]/div[1]/div[3]/div[1]/form/fieldset/div/input[1]")))
				.sendKeys(list.get(22));

		// 비번입력
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"container\"]/div[1]/div[3]/div[1]/form/fieldset/div/input[2]")))
				.sendKeys(list.get(23));

		// 로그인버튼
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"container\"]/div[1]/div[3]/div[1]/form/fieldset/div/button[1]"))).click();

		// 업무시스템접속클릭
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header\"]/div/div[1]"))).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.switchTo().frame(driver.findElement(By.id("center")));

		try {
			WebElement e = ((ChromeDriver) driver).findElementByCssSelector(".simplemodal-close");
			if (e != null) {
				((ChromeDriver) driver).executeScript("$('.simplemodal-close').trigger('click');", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 쇼핑몰관리클릭
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"oe_menu\"]/li[4]/a/img"))).click();

		// 쇼핑몰운송장송신클릭
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"oe_menu\"]/li[4]/div/ul/li[10]/a")))
				.click();
		// 오늘클릭
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/form/table[1]/tbody/tr[1]/td[2]/a[1]"))).click();
		// 25클릭
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div[4]/form/table/tbody/tr[1]/td[4]/select[1]"))).click();
		// 1000클릭
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div[4]/form/table/tbody/tr[1]/td[4]/select[1]/option[7]")))
				.click();
		//송장미송신 클릭
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div[4]/form/table[1]/tbody/tr[3]/td[4]/input[1]"))).click();		
		// 검색클릭
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div[4]/form/table/tbody/tr[5]/td[2]/input[2]"))).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean flag = true;
		while(flag) {
			try {
				String e = (String) ((ChromeDriver) driver).executeScript("return Layer2010.style.display", "");;
				if (e.length()>0) {
					flag = false;
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		// 전체클릭
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div[4]/form/table[3]/tbody/tr[1]/td[2]/a"))).click();
		// driver.executeScript("select_all('chk')","");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ChromeDriver) driver).executeScript("song_communi(\"\")", "");;
		
		
//		// 운송장송신클릭
//		webElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
//				"body > div.page_cls > form > table:nth-child(4) > tbody > tr > td:nth-child(2) > img:nth-child(3)")));
//		webElement.click();
		//Thread.sleep(10000);
		
	}
}

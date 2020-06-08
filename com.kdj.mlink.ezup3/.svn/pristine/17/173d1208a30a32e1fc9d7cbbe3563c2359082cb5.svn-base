package com.kdj.mlink.ezup3.shop.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.kdj.mlink.ezup3.common.YDMAStringUtil;

/*
 *  빌더 패턴 으로 구현.
 */
public class ChromeScript {
	private static ChromeScript instance = new ChromeScript();
	private StringBuffer sbScript = null;
	private long time;

	private ChromeScript() {
	}

	public static ChromeScript get() {
		instance.clear();
		return instance;
	}

	public void setMainWindow(ChromeDriver driver) {

		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(windowHandles.get(0));
	}

	/*
	 * 팝업으로 포커스 이동.
	 */
	public void setSwitchPopup(ChromeDriver driver) throws InterruptedException {
		boolean flag = true;
		Integer count = 0;

		while (flag) {
			List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
			if (windowHandles.size() == 2) {
				driver.switchTo().window(windowHandles.get(1));
				flag = false;
				break;
			}
			if (count > 3)
				break;
			Thread.sleep(1000);
			count++;
		}
	}

	public void waitAlertOk(ChromeDriver driver) throws InterruptedException {
		boolean flag = true;
		Integer max = 5000;
		Integer cnt = 10;
		while (flag) {
			if (ExpectedConditions.alertIsPresent().apply(driver) == null) {
				Thread.sleep(10);
				cnt += 10;
				if (cnt > max)
					break;
			} else {
				// 알림창이 존재하면 알림창 확인을 누를것
				flag = false;
				driver.switchTo().alert().accept();

			}
		}
	}

	public void waitAlertOk() throws InterruptedException {
		ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		boolean flag = true;
		Integer max = 5000;
		Integer cnt = 10;
		while (flag) {
			if (ExpectedConditions.alertIsPresent().apply(driver) == null) {
				Thread.sleep(10);
				cnt += 10;
				if (cnt > max)
					break;
			} else {
				// 알림창이 존재하면 알림창 확인을 누를것
				flag = false;
				driver.switchTo().alert().accept();

			}
		}
	}

	public boolean visibleUntilWait(String jpathElement, ChromeDriver driver) throws InterruptedException {
		int max = 5000;
		int cnt = 10;
		String ret = "0";
		while (ret.equals("0")) {
			String tag = String.format("let tag = %s; ", jpathElement);

			ret = ChromeScript.get().returnCallbackScripter(
					tag + "return (typeof tag === 'undefined' || tag===null || tag ==='null') ? '0' : '1'; ", driver);
			// .addScript(tag)
			// .addScript("return (typeof tag === 'undefined' || tag===null || tag
			// ==='null') ? '0' : '1'; ")
			// .executeScripter(driver);
			this.clear();

			if (ret == null || ret.equals("null"))
				ret = "0";
			cnt += 10;
			System.out.println(ret);
			if (cnt > max)
				break;
			Thread.sleep(10);
		}
		return (!ret.equals("0"));
	}

	/*
	 * 쿠키를 가져온다.
	 */
	public Map<String, String> getCookies(ChromeDriver driver) {
		Map<String, String> mapCookie = new HashMap<>();
		for (Cookie cookie : driver.manage().getCookies()) {
			mapCookie.put(cookie.getName(), cookie.getValue());
		}

		return mapCookie;
	}

	public Map<String, String> getElementIdOrValue(org.jsoup.select.Elements elements) {
		Map<String, String> datas = new HashMap<>();
		for (org.jsoup.nodes.Element e : elements) {
			datas.put(e.attr("id"), e.val());
		}
		return datas;
	}

	public Map<String, String> getElementNameOrValue(org.jsoup.select.Elements elements) {
		Map<String, String> datas = new HashMap<>();
		for (org.jsoup.nodes.Element e : elements) {
			datas.put(e.attr("name"), e.val());
		}
		return datas;
	}

	public boolean visibleUntilWait(String jpathElement) throws InterruptedException {
		int max = 1000;
		int cnt = 10;
		ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		String ret = "0";
		while (ret.equals("0")) {
			String tag = String.format("let tag = %s; ", jpathElement);

			ret = ChromeScript.get().returnCallbackScripter(
					tag + "return (typeof tag === 'undefined' || tag===null || tag ==='null') ? '0' : '1'; ", driver);
			// .addScript(tag)
			// .addScript("return (typeof tag === 'undefined' || tag===null || tag
			// ==='null') ? '0' : '1'; ")
			// .executeScripter(driver);
			this.clear();

			if (ret == null || ret.equals("null"))
				ret = "0";
			cnt += 10;
			System.out.println(ret);
			if (cnt > max)
				break;
			Thread.sleep(10);
		}
		return (!ret.equals("0"));
	}

	public void selectWatUntil(String script, ChromeDriver driver) throws InterruptedException {
		Integer max = 1000;
		Integer cnt = 10;
		int ret = 0;
		while (ret == 0) {
			this.clear();
			String val = (String) driver.executeScript(script, "");
			ret = YDMAStringUtil.convertToInt(val);
			cnt += 10;
			if (cnt > max)
				break;
			Thread.sleep(10);
		}
	}

	public void selectWatUntil(String script) throws InterruptedException {
		Integer max = 1000;
		Integer cnt = 10;
		selectWatUntil(script, max);
	}

	public void selectWatUntil(String script, int tiemr) throws InterruptedException {
		int max = tiemr;
		int cnt = 10;
		ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		int ret = 0;
		while (ret == 0) {
			this.clear();
			String val = (String) driver.executeScript(script, "");
			ret = YDMAStringUtil.convertToInt(val);
			cnt += 10;
			if (cnt > max)
				break;
			Thread.sleep(10);
		}
	}

	public void waitOtherPageSleep(String URL, ChromeDriver driver) {
		Integer max = 1000;
		Integer cnt = 10;
		boolean flag = true;

		while (flag) {
			if (!URL.equals(driver.getCurrentUrl())) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag = false;
			}
			cnt += 10;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (cnt > max)
				break;
		}
	}

	public void waitOtherPageSleep(String URL) {
		Integer max = 1000;
		Integer cnt = 10;
		ChromeDriver driver = ChromeExtention.getInstace().getDriver();
		boolean flag = true;

		while (flag) {
			if (!URL.equals(driver.getCurrentUrl())) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag = false;
			}
			cnt += 10;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (cnt > max)
				break;
		}
	}

	public void get(ChromeDriver driver, String url) {
		String readyState = "";

		Integer max = 10000;
		Integer cnt = 10;

		driver.get(url);
		while (true) {
			readyState = (String) driver.executeScript("return document.readyState");
			if (readyState != null && readyState.equals("complete")) {
				// System.out.println("찾아다..");
				break;
			} else {
				try {
					cnt += 10;
					Thread.sleep(10);
					if (cnt == max)
						break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// System.out.println("못찾음..");
			}
		}
	}

	public void clear() {
		sbScript = null;
		this.time = 0;
	}

	public ChromeScript addScript(String script) {
		if (sbScript == null) {
			sbScript = new StringBuffer();
			sbScript.append("(()=>{    \r\n"); // IIFE
		}
		sbScript.append(script.concat("\r\n"));
		return this;
	}

	public ChromeScript waitTiem(long time) {
		this.time = time;
		return this;
	}

	public WebElement until(WebDriver driver, Supplier<WebElement> r) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(driver1 -> r.get());

		return element;
	}

	public WebElement untilClick(WebDriver driver, Supplier<WebElement> r) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(driver1 -> {
			r.get().click();
			return r.get(); // ((ChromeDriver)driver).findElementByCssSelector(".searchbox iframe");
		});

		return element;
	}

	public String executeScripter(ChromeDriver driver) throws InterruptedException {
		sbScript.append(" })();");
		System.out.println(sbScript);
		String ret = "";
		String readyState = "";
		final Predicate<String> isReadyState = r -> r.equals("complete");

		Integer max = 10000;
		Integer cnt = 10;
		boolean isNotFind = true;
		while (isNotFind) { // 찾을 동안에만 루프를 돌린다.
			readyState = (String) driver.executeScript("return document.readyState");
			if (isReadyState.test(readyState)) {
				isNotFind = false;
			}

			try {
				cnt += 10;
				Thread.sleep(10);
				if (cnt == max)
					break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (this.time > 0) {
			Thread.sleep(this.time);
		}

		ret = (String) driver.executeScript(sbScript.toString());
		return ret;
	}

	public String returnCallbackScripter(String script, ChromeDriver driver) throws InterruptedException {
		String ret = "";
		String readyState = "";
		final Predicate<String> isReadyState = r -> r.equals("complete");
		Integer max = 10000;
		Integer cnt = 10;
		boolean isNotFind = true;
		while (isNotFind) {
			readyState = (String) driver.executeScript("return document.readyState");
			if (isReadyState.test(readyState)) {
				isNotFind = false;
			}

			try {
				cnt += 10;
				Thread.sleep(10);
				if (cnt == max)
					break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (this.time > 0) {
			Thread.sleep(this.time);
		}

		System.out.println(script);

		ret = (String) driver.executeScript(script);
		return ret;
	}

	public String returnCallbackScripter(ChromeDriver driver) {
		String ret = "";
		if (this.time > 0) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ret = (String) driver.executeScript("return window.retValue");
		return ret;
	}
}

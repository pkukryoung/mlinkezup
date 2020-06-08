package com.kdj.mlink.ezup3.shop.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;

public class ChromeExtention {
	/* ũ�� ����̺� ���.. */
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = YDMASessonUtil.getAppPath() + "\\YDwmsData\\chromedriver.exe";

	private boolean isFileDown;
	private boolean isCheckDrive;
	private boolean headless_mode = false;

	private Path downloadPath;
	private ChromeDriver driver = null;
	private ChromeDriverService driverService = null;

	Map<Integer, DriverPool> driverPools = new HashMap<>();

	public static ChromeExtention instance = new ChromeExtention();

	private ChromeExtention() {
	} // ��ü ���� ����..

	/*
	 * �ν��Ͻ��� �����´�..
	 */
	public static ChromeExtention getInstace() {
		return instance;
	}

	/*
	 * �ڵ帮�� ��� .
	 */
	public ChromeExtention setHeadlessMode(boolean headless_mode) {
		isCheckDrive = (this.headless_mode == headless_mode) ? true : false;

		this.headless_mode = headless_mode;
		return this;
	}

	/*
	 * ���� �ٿ�ε� .
	 */
	public ChromeExtention setFileDown(boolean isFileDown) {
		isCheckDrive = (this.isFileDown == isFileDown) ? true : false;
		this.isFileDown = isFileDown;
		return this;
	}

	/*
	 * ���� �¾��� �Ѵ�.
	 */
//	public Map<Integer, DriverPool> setupDriver(int poolSize) {
//		int size = (poolSize > 5) ? 5 : 3;
//
//		for(Integer i = 0; i<size; ++i) {
//			  driverPools.put(i, new DriverPool(setupDriver(false) ,i ));
//		}
//
//		 return driverPools;
//	}

	/*
	 * Ű ���� ���� �´�. .
	 */
	private int getKeyGenerator() {
		return driverPools.keySet().stream().mapToInt(p -> p.intValue()).max().orElse(0) + 1;
	}

	/*
	 * ����̹� Ǯ�� ��ȯ �Ѵ�..
	 */
	public DriverPool getDriverPool() {
		DriverPool driverPool = null;
		Integer key = driverPools.entrySet().stream().filter(p -> p.getValue().isUse() == false).map(p -> p.getKey())
				.findAny().orElse(-1);

		if (key > 0) {
			driverPool = driverPools.get(key);
		} else {
			key = getKeyGenerator();
			driverPool = new DriverPool(setupDriver(false), key); // Ǯ�� ���� �����.
			driverPools.put(key, driverPool);
		}
		driverPool.setUse(true);
		return driverPool;
	}

	/*
	 * �ڿ��� ��ȯ�Ѵ�..
	 */
	public void setDriverPoolRelease(DriverPool driverPool) {
		driverPool.getChromeDriver().quit(); // ���� �ڿ��� ���� �Ѵ�..
		Integer key = driverPool.getKey();
		driverPools.remove(key);
		// driverPools.put(key, new DriverPool(setupDriver(false), key ));
	}

	/*
	 * ����̹� ������..
	 */
	public void driverPoolRelease() {
		for (Integer key : driverPools.keySet()) {
			DriverPool driverPool = driverPools.get(key);

			if (driverPool.isComplete()) // ��� �Ϸ� �� �׸� �����ؼ��� �ڿ��� �ٽ� �Ҵ��Ѵ�.
			{
				driverPool.getChromeDriver().quit(); // ���� �ڿ��� ���� �Ѵ�..
				// driverPools.put(key, new DriverPool(setupDriver(false), key ));
			}
		}
	}

	/*
	 * ����̹� �¾�.
	 */
	private ChromeDriver setupDriver(boolean headless_mode) {

		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		// if (headless_mode) // ũ��â ������..
		// {
		options.addArguments("--headless --disable-gpu");
		// }
		options.addArguments("--test-type");
		options.addArguments("--disable-extension");
		ChromeDriver driver = new ChromeDriver(options);
		return driver;
	}

	/*
	 * ����̽� ����.
	 */
	private ChromeDriverService getDriverService() {
		ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(WEB_DRIVER_PATH)).usingAnyFreePort().build();
		try {
			chromeDriverService.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chromeDriverService;
	}

	/*
	 * ũ�� ����̺긦 �ִ´�.
	 */
	public ChromeDriver getDriver() {
		if (driverService == null) {

			driverService = new ChromeDriverService.Builder().usingDriverExecutable(new File(WEB_DRIVER_PATH))
					.usingAnyFreePort().build();
			try {
				driverService.start();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/* ���� ����̹��� null �� �ƴϸ�.. */
		if (driver != null) {
			try {
				String s = driver.getWindowHandle();
			} catch (Exception e) {
				driver.quit();
				init();
			}
		} else {
			init();
		}

		return driver;
	}

	/*
	 * �ٿ�ε� �н�..
	 */
	public Path getDownloadPath() {
		return downloadPath;
	}

	/*
	 * ������ ������ �����´�.
	 */
	public File getLastModifiedFile() {
		String strPath = this.getDownloadPath().toAbsolutePath().toString();
		File[] files = new File(strPath).listFiles();

		File lastFile = Arrays.stream(files)
				.reduce((a, b) -> (Long.valueOf(a.lastModified()).compareTo(b.lastModified()) > 0) ? a : b).get();

		return lastFile;
	}

	public String getLastFileAbsolutePath() {
		String strPath = downloadPath.toAbsolutePath().toString();
		return strPath.concat("\\").concat(getLastModifiedFile().getName());
	}

	public void init() {

		if (isFileDown) { // ���� �ٿ�ε� �̸� �ӽ������� �����Ѵ�.
			try {
				downloadPath = createDirectory();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("download.default_directory", downloadPath);
		chromePrefs.put("download.directory_upgrade", true);
		chromePrefs.put("download.prompt_for_download", false);
		chromePrefs.put("browser.set_download_behavior", "{behavior:\"allow\"}");

		ChromeOptions options = new ChromeOptions();

		if (this.headless_mode) // ũ��â ������..
		{
			options.addArguments("--headless --disable-gpu");
		}

		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");
		options.addArguments("--disable-extension");
		// driver = new ChromeDriver(options);
		// driverService = ChromeDriverService.createDefaultService();
		driver = new ChromeDriver(driverService, options);
	}

	/*
	 * ���� ��� �� ���� ���丮�� �����.
	 */
	public Path createDirectory() throws IOException {
		Path basePath = java.nio.file.Paths.get("").toAbsolutePath();

		String currentDate = YDMATimeUtil.getCurrentTime().substring(0, 8); // ������¥

		downloadPath = basePath.resolve("EXCEL_" + currentDate);

		if (!Files.exists(downloadPath)) {
			downloadPath = Files.createDirectory(downloadPath);
		}
		return downloadPath;
	}

	/*
	 * ���丮 ����..
	 */
	public void deleteDirectory() {
		File folder = new File(downloadPath.toAbsolutePath().toString());
		try {
			if (folder.exists()) {
				File[] folder_list = folder.listFiles(); // ���ϸ���Ʈ ������

				for (File element : folder_list) {
					System.gc();
					Thread.sleep(2000);

					boolean isSucess = element.delete();
					System.out.println(isSucess);
				}

				if (folder.isDirectory()) {
					folder.delete();
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/*
	 * Ŭ�ҿ��� ������ �ٿ�ε� �ް� �Ѵ�.
	 */
	public void crome_headless_filedown() throws ClientProtocolException, IOException {
		downloadPath = createDirectory();
		Map<String, Object> commandParams = new HashMap<>();
		commandParams.put("cmd", "Page.setDownloadBehavior");
		Map<String, String> params = new HashMap<>();
		params.put("behavior", "allow");
		params.put("downloadPath", downloadPath.toAbsolutePath().toString());
		params.put("cmd", "Page.setDownloadBehavior");

		commandParams.put("params", params);
		ObjectMapper objectMapper = new ObjectMapper();
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		String command = objectMapper.writeValueAsString(commandParams);
		String u = driverService.getUrl().toString() + "/session/" + driver.getSessionId() + "/chromium/send_command";
		HttpPost request = new HttpPost(u);
		request.addHeader("content-type", "application/json");
		request.setEntity(new StringEntity(command));
		httpClient.execute(request);
	}

}

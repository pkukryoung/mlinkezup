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
	/* 크롬 드라이브 경로.. */
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
	} // 객체 생성 금지..

	/*
	 * 인스턴스를 가져온다..
	 */
	public static ChromeExtention getInstace() {
		return instance;
	}

	/*
	 * 핸드리스 모드 .
	 */
	public ChromeExtention setHeadlessMode(boolean headless_mode) {
		isCheckDrive = (this.headless_mode == headless_mode) ? true : false;

		this.headless_mode = headless_mode;
		return this;
	}

	/*
	 * 파일 다운로드 .
	 */
	public ChromeExtention setFileDown(boolean isFileDown) {
		isCheckDrive = (this.isFileDown == isFileDown) ? true : false;
		this.isFileDown = isFileDown;
		return this;
	}

	/*
	 * 최초 셋업을 한다.
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
	 * 키 값을 가져 온다. .
	 */
	private int getKeyGenerator() {
		return driverPools.keySet().stream().mapToInt(p -> p.intValue()).max().orElse(0) + 1;
	}

	/*
	 * 드라이버 풀을 반환 한다..
	 */
	public DriverPool getDriverPool() {
		DriverPool driverPool = null;
		Integer key = driverPools.entrySet().stream().filter(p -> p.getValue().isUse() == false).map(p -> p.getKey())
				.findAny().orElse(-1);

		if (key > 0) {
			driverPool = driverPools.get(key);
		} else {
			key = getKeyGenerator();
			driverPool = new DriverPool(setupDriver(false), key); // 풀을 새로 만든다.
			driverPools.put(key, driverPool);
		}
		driverPool.setUse(true);
		return driverPool;
	}

	/*
	 * 자원을 반환한다..
	 */
	public void setDriverPoolRelease(DriverPool driverPool) {
		driverPool.getChromeDriver().quit(); // 현재 자원을 종료 한다..
		Integer key = driverPool.getKey();
		driverPools.remove(key);
		// driverPools.put(key, new DriverPool(setupDriver(false), key ));
	}

	/*
	 * 드라이버 릴리즈..
	 */
	public void driverPoolRelease() {
		for (Integer key : driverPools.keySet()) {
			DriverPool driverPool = driverPools.get(key);

			if (driverPool.isComplete()) // 사용 완료 된 항목 에대해서는 자원을 다시 할당한다.
			{
				driverPool.getChromeDriver().quit(); // 현재 자원을 종료 한다..
				// driverPools.put(key, new DriverPool(setupDriver(false), key ));
			}
		}
	}

	/*
	 * 드라이버 셋업.
	 */
	private ChromeDriver setupDriver(boolean headless_mode) {

		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		// if (headless_mode) // 크롬창 숨김모드..
		// {
		options.addArguments("--headless --disable-gpu");
		// }
		options.addArguments("--test-type");
		options.addArguments("--disable-extension");
		ChromeDriver driver = new ChromeDriver(options);
		return driver;
	}

	/*
	 * 디바이스 서비스.
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
	 * 크롬 드라이브를 넣는다.
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

		/* 최초 드라이버가 null 이 아니면.. */
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
	 * 다운로드 패스..
	 */
	public Path getDownloadPath() {
		return downloadPath;
	}

	/*
	 * 마지막 파일을 가져온다.
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

		if (isFileDown) { // 파일 다운로드 이면 임시폴더를 생성한다.
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

		if (this.headless_mode) // 크롬창 숨김모드..
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
	 * 절대 경로 에 템프 디렉토리를 만든다.
	 */
	public Path createDirectory() throws IOException {
		Path basePath = java.nio.file.Paths.get("").toAbsolutePath();

		String currentDate = YDMATimeUtil.getCurrentTime().substring(0, 8); // 현제날짜

		downloadPath = basePath.resolve("EXCEL_" + currentDate);

		if (!Files.exists(downloadPath)) {
			downloadPath = Files.createDirectory(downloadPath);
		}
		return downloadPath;
	}

	/*
	 * 디렉토리 삭제..
	 */
	public void deleteDirectory() {
		File folder = new File(downloadPath.toAbsolutePath().toString());
		try {
			if (folder.exists()) {
				File[] folder_list = folder.listFiles(); // 파일리스트 얻어오기

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
	 * 클롬에서 엑셀을 다운로드 받게 한다.
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

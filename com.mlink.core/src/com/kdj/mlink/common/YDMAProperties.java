package com.kdj.mlink.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

import org.osgi.framework.FrameworkUtil;

public class YDMAProperties {

	static YDMAProperties instance;
	private Properties appProperties;

	public String getAppProperty(String key) {
		
		//mac에서 사용하기 위해 변경 
		//return (String) appProperties.get(key);
		
		String myOS = System.getProperty("os.name").toLowerCase();
		if(myOS.contains("windows")) {
			return (String) appProperties.get(key);
		} else {
			String aa = (String) appProperties.get(key);
			return aa.replace("C:/","/");
		}
		
	}

	public static YDMAProperties getInstance() throws Exception {

		if (instance == null) {
			instance = new YDMAProperties();
		}

		return instance;
	}

	public YDMAProperties() throws Exception {
		loadProperties();
	}

	public void loadProperties() throws Exception {

		String symbolicName = (FrameworkUtil.getBundle(this.getClass())).getSymbolicName();
		String path = "platform:/plugin/" + symbolicName + "/plugin.properties";
		URL url = new URL(path);
		InputStream inputStream = url.openConnection().getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		appProperties = new Properties();
		appProperties.load(in);

		System.out.println("[jdbcdriver]" + appProperties.getProperty("jdbc.driver"));
		System.out.println("[jdbcurl]" + appProperties.getProperty("jdbc.url"));
		System.out.println("[username]" + appProperties.getProperty("jdbc.username"));
		System.out.println("[password]" + appProperties.getProperty("jdbc.password"));
		System.out.println("[ftp.host]" + appProperties.getProperty("ftp.host"));
		System.out.println("[ftp.port]" + appProperties.getProperty("ftp.port"));
		System.out.println("[ftp.webpath]" + appProperties.getProperty("ftp.webpath"));
		System.out.println("[ftp.imagepath]" + appProperties.getProperty("ftp.imagepath"));
		System.out.println("[ftp.username]" + appProperties.getProperty("ftp.username"));
		System.out.println("[ftp.password]" + appProperties.getProperty("ftp.password"));
		
		
		in.close();

	}
}

package com.kdj.mlink.ezup3.common;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class YDMAHttpUtil {

	public static void browser(String url) {
		try {

			String myOS = System.getProperty("os.name").toLowerCase();
			if (myOS.contains("windows")) {
				// if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI(url));
			} else {
				Runtime runtime = Runtime.getRuntime();
				if (myOS.contains("mac")) {
					runtime.exec("open " + url);
				} else if (myOS.contains("nix") || myOS.contains("nux")) {
					runtime.exec("xdg-open " + url);
				}
			}

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
	}

	public static void browserList(List<String> selData) {

	}

	/*
	 * get 방식으로 데이타 받기..
	 */
	public static String get(String url, Hashtable ht) throws Exception {
		StringBuffer response = new StringBuffer();

		try {

			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("GET");
			Enumeration keys = ht.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				httpConnection.setRequestProperty(key, ht.get(key).toString());
			}

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}
				buffReader.close();
			} else {
				httpConnection.disconnect();
				throw new Exception("해당 URL 와 통신을 할수 없습니다.");
			}

			System.out.println(response);
			httpConnection.disconnect();
			return response.toString();
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * 포스트 방식으로 데이타 받기..
	 *
	 */
	public static String post(String url, String fileName, Hashtable ht) throws Exception {
		StringBuffer response = new StringBuffer();
		try {
			StringBuffer fileData = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "EUC-KR"));

			char[] buf = new char[1024];

			int numRead = 0;

			while ((numRead = reader.read(buf)) != -1) {
				fileData.append(buf, 0, numRead);
			}

			reader.close();
			String xml_string_to_send = fileData.toString();

			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			ht.forEach((k, v) -> {
				httpConnection.setRequestProperty(k.toString(), v.toString());
			});

			httpConnection.setRequestProperty("Content-Length", Integer.toString(xml_string_to_send.length()));

			os = httpConnection.getOutputStream();
			os.write(xml_string_to_send.getBytes("euc-kr"));
			os.flush();
			os.close();

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			}
			httpConnection.disconnect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// }
		return response.toString();
	}

	public static String put(String url, String xml_string_to_send, Hashtable ht) throws Exception {
		List<List<String>> responseContents = null;
		StringBuffer response = new StringBuffer();
		HttpURLConnection httpConnection = null;
		try {
			StringBuffer fileData = new StringBuffer();

			OutputStream os = null;
			URL obj = new URL(url);
			httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("PUT");

			httpConnection.setRequestProperty("Content-Type", "PUT");
			Enumeration keys = ht.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				httpConnection.setRequestProperty(key, ht.get(key).toString());
			}
			httpConnection.setRequestProperty("Content-Length", Integer.toString(xml_string_to_send.length()));

			os = httpConnection.getOutputStream();
			os.write(xml_string_to_send.getBytes("euc-kr"));
			os.flush();
			os.close();

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "euc-kr"));
				String inputLine = null;

				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}

				buffReader.close();
			} else {
				throw new Exception("서버와 통신을 할수가 없습니다.");
			}

			return response.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			httpConnection.disconnect();
		}
		return response.toString();
	}

}

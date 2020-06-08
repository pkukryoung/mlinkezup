package com.kdj.mlink.ezup3.shop.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.coupang.openapi.sdk.Hmac;

public class HttpClientEx {

	private static HttpClientEx instance = new HttpClientEx();
	Map<String, String> header = new HashMap<>();

	
	public static HttpClientEx get() {

		return instance;
	}

	private HttpClientEx() {
	}

	/*
	 * 파라미터를 던진다.
	 */
	public HttpClientEx addParam(String key, String value) {
		header.put(key, value);
		return this;
	}

	public  String Post(String url , String formParams) {
		StringBuffer response = new StringBuffer();
		try {
			StringBuffer fileData = new StringBuffer();
			

			int numRead = 0;

			OutputStream os = null;
			URL obj = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			
			header.forEach((k, v) -> {
				httpConnection.setRequestProperty(k.toString(), v.toString());
			});

			httpConnection.setRequestProperty("Content-Length", Integer.toString(formParams.length()));

			os = httpConnection.getOutputStream();
			os.write(formParams.getBytes("euc-kr"));
			os.flush();
			os.close();

			int responseCode = httpConnection.getResponseCode();

			System.out.println("Api 응답 코드: " + responseCode);

			if (responseCode == 200) {
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
				String inputLine = null;
				System.out.println(buffReader.toString());
				while ((inputLine = buffReader.readLine()) != null) {
					response.append(inputLine);
				}
				

				
				buffReader.close();
			}
			httpConnection.disconnect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			header.clear();
		}
		// }
		return response.toString();
	}

	

}

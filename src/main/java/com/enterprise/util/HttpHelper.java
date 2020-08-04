//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.enterprise.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpHelper {
	public HttpHelper() {
	}

	public static String postRequest(String path, String post) {
		URL url = null;
		String result = "";

		try {
			url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(2000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			PrintWriter printWriter = new PrintWriter(conn.getOutputStream());
			printWriter.write(post);
			printWriter.flush();
			printWriter.close();

			BufferedReader in;
			String line;
			for(in = new BufferedReader(new InputStreamReader(conn.getInputStream())); (line = in.readLine()) != null; result = result + line) {
			}

			in.close();
			conn.disconnect();
		} catch (Exception var8) {
			var8.printStackTrace();
		}

		return result;
	}

	public static void verifyPost(String strUrl, String params) throws Exception {
		strUrl = strUrl + "&" + params;
		URL url = new URL(strUrl);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();

		try {
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			Map<String, List<String>> map = conn.getHeaderFields();
			Iterator var5 = map.keySet().iterator();

			while(var5.hasNext()) {
				String key = (String)var5.next();
				System.err.println(key + "--->" + map.get(key));
			}

			InputStream is = conn.getInputStream();
			System.out.println(is);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str = "";

			while((str = br.readLine()) != null) {
				System.out.println(str);
			}

			is.close();
			System.out.println("结束");
			conn.disconnect();
		} catch (Exception var8) {
			var8.printStackTrace();
		}

	}

	public static String getRequest(String path, Map<String, Object> data) {
		try {
			String strUrl = path;
			if (null != data && data.size() > 0) {
				strUrl = path + "?" + urlencode(data);
			}

			URL url = new URL(strUrl.trim());
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setConnectTimeout(10000);
			urlConnection.setRequestMethod("GET");
			if (200 == urlConnection.getResponseCode()) {
				InputStream is = urlConnection.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				boolean var8 = false;

				int len;
				while(-1 != (len = is.read(buffer))) {
					baos.write(buffer, 0, len);
					baos.flush();
				}

				return baos.toString("utf-8");
			}
		} catch (IOException var9) {
			var9.printStackTrace();
		}

		return null;
	}

	public static void verify(String strUrl, Map<String, Object> params) throws Exception {
		strUrl = strUrl + "?" + urlencode(params);
		URL url = new URL(strUrl.trim());
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();

		try {
			try {
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
				conn.setRequestProperty("accept", "*/*");
				conn.setRequestProperty("connection", "Keep-Alive");
				conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				InputStream is = conn.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String str = "";

				while((str = br.readLine()) != null) {
					System.out.println(str);
				}

				is.close();
				System.out.println("结束");
			} catch (Exception var10) {
				var10.printStackTrace();
			}

		} finally {
			;
		}
	}

	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		Iterator var2 = data.entrySet().iterator();

		while(var2.hasNext()) {
			Entry i = (Entry)var2.next();

			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException var5) {
				var5.printStackTrace();
			}
		}

		return sb.toString();
	}
}

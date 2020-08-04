//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.enterprise.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

public class HttpUtilInstance {
	private static HttpUtilInstance instance = null;

	private HttpUtilInstance() {
	}

	public static HttpUtilInstance getInstance() {
		if (instance == null) {
			instance = new HttpUtilInstance();
		}

		return instance;
	}

	public String sendHttpPost(String url, String data, String contentType) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = null;
		post = new HttpPost(url);
		post.setHeader("Content-Type", contentType + ";charset=UTF-8");

		try {
			StringEntity s = new StringEntity(data, "utf-8");
			s.setContentEncoding(new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8"));
			post.setEntity(s);
			HttpResponse httpResponse = client.execute(post);
			InputStream inStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
			StringBuilder strber = new StringBuilder();
			String line = null;

			while((line = reader.readLine()) != null) {
				strber.append(line + "\n");
			}

			inStream.close();
			return strber.toString();
		} catch (Exception var12) {
			throw new RuntimeException(var12);
		}
	}

	public String sendHttpPut(String url, String data, String contentType) {
		HttpClient client = new DefaultHttpClient();
		HttpPut put = null;
		put = new HttpPut(url);
		put.setHeader("Content-Type", contentType);

		try {
			StringEntity s = new StringEntity(data, "utf-8");
			s.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
			put.setEntity(s);
			HttpResponse httpResponse = client.execute(put);
			InputStream inStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
			StringBuilder strber = new StringBuilder();
			String line = null;

			while((line = reader.readLine()) != null) {
				strber.append(line + "\n");
			}

			inStream.close();
			return strber.toString();
		} catch (Exception var12) {
			throw new RuntimeException(var12);
		}
	}

	public String sendHttpDelete(String url) {
		HttpClient client = new DefaultHttpClient();
		HttpDelete del = null;
		del = new HttpDelete(url);
		del.setHeader("Content-Type", "application/json");

		try {
			HttpResponse httpResponse = client.execute(del);
			InputStream inStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
			StringBuilder strber = new StringBuilder();
			String line = null;

			while((line = reader.readLine()) != null) {
				strber.append(line + "\n");
			}

			inStream.close();
			return strber.toString();
		} catch (Exception var9) {
			throw new RuntimeException(var9);
		}
	}
}

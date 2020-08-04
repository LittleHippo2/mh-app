//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.enterprise.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    public TokenUtil() {
    }

    public static String obaainUrlDataByPost(URL url, String token) throws IOException {
        String urlParameters = "access_token=" + token;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        Throwable var7 = null;

        try {
            wr.write(postData);
        } catch (Throwable var16) {
            var7 = var16;
            throw var16;
        } finally {
            if (wr != null) {
                if (var7 != null) {
                    try {
                        wr.close();
                    } catch (Throwable var15) {
                        var7.addSuppressed(var15);
                    }
                } else {
                    wr.close();
                }
            }

        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder strBuf = new StringBuilder();

        String line;
        while((line = reader.readLine()) != null) {
            strBuf.append(line);
        }

        reader.close();
        return strBuf.toString();
    }

    public static Map StringToMap(String jsonString) throws IOException {
        Map dataMap = new HashMap();
        if (null != jsonString && jsonString.length() > 0) {
            jsonString = jsonString.replaceAll("\"", "");
            jsonString = jsonString.replaceAll("\\{", "");
            jsonString = jsonString.replaceAll("\\}", "");
            String[] datas = jsonString.split(",");
            if (null != datas && datas.length > 0) {
                String[] var3 = datas;
                int var4 = datas.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    String oneDataString = var3[var5];
                    String[] oneDataKeyAndValue = oneDataString.split(":");
                    String key = oneDataKeyAndValue[0];
                    String value = null;
                    if (oneDataKeyAndValue.length > 1) {
                        value = oneDataKeyAndValue[1];
                    }

                    dataMap.put(key, value);
                }
            }

            return dataMap;
        } else {
            return dataMap;
        }
    }
}

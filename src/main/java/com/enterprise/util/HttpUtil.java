package com.enterprise.util;


import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class HttpUtil {

    public static String time = "";


    private static final int READ_TIMEOUT = 60000;

    private static final int CONNECT_TIMEOUT = 60000;


    /**
     * http get request
     */
    public static String get(String urlAddr, Map<String, Object> paramsMap, int connectTimeout, int readTimeout) throws Exception {

        long begin = System.currentTimeMillis();
        String line;
        String params = "";
        HttpURLConnection conn = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        StringBuffer result = new StringBuffer();
        try {
            if (connectTimeout < 1) {
                connectTimeout = CONNECT_TIMEOUT;
            }
            if (readTimeout < 1) {
                readTimeout = READ_TIMEOUT;
            }
            if (paramsMap != null && !paramsMap.isEmpty()) {
                StringBuffer str = new StringBuffer();
                Set set = paramsMap.keySet();
                Iterator iter = set.iterator();
                while (iter.hasNext()) {
                    String key = iter.next().toString();
                    if (paramsMap.get(key) == null) {
                        continue;
                    }
                    str.append(key).append("=").append(paramsMap.get(key)).append("&");
                }
                if (str.length() > 0) {
                    params = "?" + str.substring(0, str.length() - 1);
                }
            }
            URL url = new URL(urlAddr + params);
            conn = (HttpURLConnection) url.openConnection();
            // 设置读取超时时间
            conn.setReadTimeout(readTimeout);
            // 设置连接超时时间
            conn.setConnectTimeout(connectTimeout);
            conn.connect();
            inputStreamReader = new InputStreamReader(conn.getInputStream(), "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                }
            }
        }
        long end = System.currentTimeMillis();
        time = (end - begin) + "ms";
        return result.toString();
    }

    /**
     * http post request
     */
    public static String post(String urlAddr, Map<String, Object> paramsMap, int connectTimeout, int readTimeout) throws Exception {
        long begin = System.currentTimeMillis();
        String line;
        String params = "";
        HttpURLConnection conn = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        StringBuffer result = new StringBuffer();
        try {
            if (connectTimeout < 1) {
                connectTimeout = CONNECT_TIMEOUT;
            }
            if (readTimeout < 1) {
                readTimeout = READ_TIMEOUT;
            }
            if (paramsMap != null && !paramsMap.isEmpty()) {
                StringBuffer str = new StringBuffer();
                Set set = paramsMap.keySet();
                Iterator iter = set.iterator();
                while (iter.hasNext()) {
                    String key = iter.next().toString();
                    if (paramsMap.get(key) == null) {
                        continue;
                    }
                    str.append(key).append("=").append(paramsMap.get(key)).append("&");
                }
                if (str.length() > 0) {
                    params = str.substring(0, str.length() - 1);
                }
            }

            params = new String(params.getBytes("utf-8"));
            URL url = new URL(urlAddr);
            conn = (HttpURLConnection) url.openConnection();
            // 设置读取超时时间
            conn.setReadTimeout(readTimeout);
            // 设置连接超时时间
            conn.setConnectTimeout(connectTimeout);
            // 设置是否向HttpURLConnection输出，因为这个是post请求，参数要放在http正文内，
            // 因此需要设为true, 默认情况下是false
            conn.setDoOutput(true);
            // 不使用缓存,默认情况下是true
            conn.setUseCaches(false);
            // 设定请求的方法为"POST",默认是GET
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            if (!params.isEmpty()) {
                // 此处getOutputStream会隐含的进行connect()
                outputStreamWriter = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                // 写入
                outputStreamWriter.write(params);
                // 刷新该流的缓冲
                outputStreamWriter.flush();
            }
            inputStreamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                }
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                }
            }
        }
        long end = System.currentTimeMillis();
        time = (end - begin) + "ms";
        return result.toString();
    }


    /**
     * http post request
     */
    public static String post(String urlAddr, String paramsStr, int connectTimeout, int readTimeout) throws Exception {

        long begin = System.currentTimeMillis();
        String line;
        HttpURLConnection conn = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        StringBuffer result = new StringBuffer();

        try {
            if (connectTimeout < 1) {
                connectTimeout = CONNECT_TIMEOUT;
            }
            if (readTimeout < 1) {
                readTimeout = READ_TIMEOUT;
            }
            URL url = new URL(urlAddr);
            conn = (HttpURLConnection) url.openConnection();
            // 设置读取超时时间
            conn.setReadTimeout(readTimeout);
            conn.setRequestProperty("Content-Type", "application/json");
            try {
                JSONObject jsonObject = JSONObject.parseObject(paramsStr);
                if (jsonObject.getString("client_ip") != null) {
                    conn.setRequestProperty("client_ip", jsonObject.getString("client_ip"));
                    jsonObject.remove("client_ip");
                    paramsStr = jsonObject.toString();
                }
            } catch (Exception e) {
            }
            // 设置连接超时时间
            conn.setConnectTimeout(connectTimeout);
            // 设置是否向HttpURLConnection输出，因为这个是post请求，参数要放在http正文内，
            // 因此需要设为true, 默认情况下是false
            conn.setDoOutput(true);
            // 不使用缓存,默认情况下是true
            conn.setUseCaches(false);
            // 设定请求的方法为"POST",默认是GET
            conn.setRequestMethod("POST");
            if (paramsStr != null && !paramsStr.isEmpty()) {
                // 此处getOutputStream会隐含的进行connect()
                outputStreamWriter = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                // 写入
                outputStreamWriter.write(paramsStr);
                // 刷新该流的缓冲
                outputStreamWriter.flush();
            }
            inputStreamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                }
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                }
            }
        }
        long end = System.currentTimeMillis();
        time = (end - begin) + "ms";
        return result.toString();
    }

    /*public static void main(String[] args) throws MalformedURLException {
        String urlAddr = "http://172.16.5.200:10005/api/msg/message/user/a6c7a79f-543b-4043-9426-43d3cc8b340c";
        //String line;
        //String params = "content={\"appid\":\"legacycpk.csse.cms\",\"display\":{\"notification\":true,\"system\":true,\"msgbox\":true},\"type\":\"application\",\"title\":\"title\",\"content\":\"中文\"}";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded;charset=UTF-8"));

        //HttpEntity<String> request1 = new HttpEntity<>(params);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("access_token","b428218f-dd57-43db-9442-1ccf822b7cf9");
        map.add("content", "{\"appid\":\"legacycpk.csse.cms\",\"display\":{\"notification\":true,\"system\":true,\"msgbox\":true},\"type\":\"application\",\"title\":\"title\",\"content\":\"中文\"}");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( urlAddr, request , String.class );
        System.out.println(response.getBody());


    }*/
    public static String postMsg(String urlAddr, Map<String, Object> paramsMap, int connectTimeout, int readTimeout) throws Exception {
        long begin = System.currentTimeMillis();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded;charset=UTF-8"));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("access_token", (String) paramsMap.get("access_token"));
        map.add("content", (String) paramsMap.get("content"));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(urlAddr, request, String.class);

        return response.getBody();
    }

    public static String pksGet(String urlAddr, Map<String, Object> paramsMap) throws Exception {
        long begin = System.currentTimeMillis();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded;charset=UTF-8"));
      //  headers.set("slw.jwt.token", token);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            map.add(entry.getKey(), (String) entry.getValue());
        }


        /*map.add("access_token", (String) paramsMap.get("access_token"));
        map.add("content", (String) paramsMap.get("content"));*/

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.getForEntity(urlAddr, String.class, request);
        // ResponseEntity<String> response = restTemplate.postForEntity( urlAddr, request , String.class );

        return response.getBody();
    }

    public static String pksPost(String urlAddr, Map<String, Object> paramsMap, String contentType) throws Exception {
        long begin = System.currentTimeMillis();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.valueOf(contentType));


        //headers.set("slw.jwt.token",token);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            map.add(entry.getKey(), (String) entry.getValue());
        }

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
       // restTemplate.getForObject(urlAddr,String.class,"");
        ResponseEntity<String> response = restTemplate.postForEntity(urlAddr, request, String.class);
        if ("200".equals(response.getStatusCode())) {
            return response.getBody();
        }
        return "fail";

    }
    public static String pksPost(String urlAddr, String params, String contentType) throws Exception {
        long begin = System.currentTimeMillis();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(contentType));
       // headers.set("Content-Type","application/json;charset=UTF-8");
        //headers.set("slw.jwt.token",token);
        //restTemplate
        HttpEntity<String> request = new HttpEntity<>(params, headers);
       // String res = restTemplate.postForObject(urlAddr,request,String.class);
        ResponseEntity<String> response = restTemplate.postForEntity(urlAddr,request,String.class);

        return response.getBody();


    }
    public static String pksGet(String urlAddr, String paramsMap,String contentType) throws Exception {
        long begin = System.currentTimeMillis();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.valueOf(contentType));
        //headers.set("slw.jwt.token", token);
        /*MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            map.add(entry.getKey(), (String) entry.getValue());
        }*/


        /*map.add("access_token", (String) paramsMap.get("access_token"));
        map.add("content", (String) paramsMap.get("content"));*/
        HttpEntity<String> request = new HttpEntity<>(paramsMap,headers);
        ResponseEntity<String> response = restTemplate.getForEntity(urlAddr,String.class,request);
       /* HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.getForEntity(urlAddr, String.class, request);*/
        // ResponseEntity<String> response = restTemplate.postForEntity( urlAddr, request , String.class );

        return response.getBody();
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
//        String token = TokenUtil.getToken();
//        System.out.println(token);
        /*params.put("loginName", "sysadmin");
        params.put("password", "efd9d1b8bfb00e8e3647990f7d74d1d8");*/
        // String result = HttpUtil.post("http://172.16.147.13:8080/gxbbase/rest/suser/check",params,3600,3600);
//        String result = HttpUtil.pksGet("http://172.16.147.13:8080/gxbbase/rest/suser/check?loginName=sysadmin&password=efd9d1b8bfb00e8e3647990f7d74d1d8", params);
        // String result = HttpUtil.pksGet("http://172.16.147.13:8080/gxbbase/rest/suser/getSUserListByUpdateTime?editDate=1583897939777&num=2",params);
        /*DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = df.format("Jan 17, 2020 1:39:28 PM");
        System.out.println(dateString);
        java.util.Date date = df.parse("2020-04-22");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long timestamp = cal.getTimeInMillis();
        System.out.println(timestamp/1000);*/

       // System.out.println(System.currentTimeMillis());
        /*String aa = Md5Util.getMD5("admin111111");
        System.out.println(aa);*/
        // pksPost("","");


        JSONObject object = new JSONObject();

        object.put("organdId", "111");
        object.put("code", "1111");
        object.put("organName","测试");
        object.put("fatherId", "root");
        object.put("orderId", 1000);
        object.put("isdelete", 0);
        object.put("isTemporary", 0);
        //object.put("timestamp", new Timestamp((org.getEditDate()).getTime()));
        object.put("timestamp", System.currentTimeMillis());

        String data = object.toJSONString();
        System.out.println(data);
        String res = HttpUtil.pksPost("http://172.16.5.200:10005/api/org/department?access_token=7694304a-98cd-4886-abcf-d77389f42bce",data,"");
        System.out.println(res);
    }

    /**
     * http post request
     */
    public static String post(String urlAddr, String paramsStr,String contentType) throws Exception {

        long begin = System.currentTimeMillis();
        String line;
        HttpURLConnection conn = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        StringBuffer result = new StringBuffer();

        try {

            URL url = new URL(urlAddr);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Content-Type", contentType);

            // 设置连接超时时间
            /*conn.setConnectTimeout(connectTimeout);*/
            // 设置是否向HttpURLConnection输出，因为这个是post请求，参数要放在http正文内，
            // 因此需要设为true, 默认情况下是false
            conn.setDoOutput(true);
            // 不使用缓存,默认情况下是true
            conn.setUseCaches(false);
            // 设定请求的方法为"POST",默认是GET
            conn.setRequestMethod("POST");
            if (paramsStr != null && !paramsStr.isEmpty()) {
                // 此处getOutputStream会隐含的进行connect()
                outputStreamWriter = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                // 写入
                outputStreamWriter.write(paramsStr);
                // 刷新该流的缓冲
                outputStreamWriter.flush();
            }
            inputStreamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                }
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                }
            }
        }
        long end = System.currentTimeMillis();
        time = (end - begin) + "ms";
        return result.toString();
    }


}
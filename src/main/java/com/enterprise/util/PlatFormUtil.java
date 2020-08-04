//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.enterprise.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.enterprise.entity.department.DepartmentPo;
import com.enterprise.entity.department.UserPo;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class PlatFormUtil {
    public static final String CLIENT_ID = ProPertiesUtils.getProperties("/conf.properties", "api.appid");
    public static final String CLIENT_SECRET = ProPertiesUtils.getProperties("/conf.properties", "api.secret");
    public static final String GRANT_TYPE = "client_credentials";
    public static final String CECOA_MSG_URL = ProPertiesUtils.getProperties("/conf.properties", "api.ip");
    public static final String APP_ID = ProPertiesUtils.getProperties("/conf.properties", "api.appid");

    public PlatFormUtil() {
    }

    public static String getAccessToken() {
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("/api/uaa/oauth/token?client_id=");
        sb.append(CLIENT_ID);
        sb.append("&client_secret=");
        sb.append(CLIENT_SECRET);
        sb.append("&grant_type=");
        sb.append(GRANT_TYPE);
        String url = sb.toString();
        String token = HttpHelper.postRequest(url, "post");
        JSONObject jsonObj = JSONObject.parseObject(token);
        String key = "access_token";
        String access_token = jsonObj.getString(key);
        return access_token;
    }

    public static String getUserInfo(String token) {
        String access_token = getAccessToken();
        String userInfo = null;
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("/api/sso/user");
        String url = sb.toString();
        sb.append("access_token=");
        sb.append(access_token);
        String data = "access_token=" + token;

        try {
            userInfo = HttpHelper.postRequest(url, data);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        JSONObject jsonObj = JSONObject.parseObject(userInfo);
        System.out.print(jsonObj.toString());
        String userId = (String)jsonObj.get("userId");
        return userId;
    }

    public static void sendMSGToPerson(String userid, String title, String contents, String detailUrl, String countTodu) {
        String access_token = getAccessToken();
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("/api/msg/message/user/");
        sb.append(userid);
        sb.append("?access_token=");
        sb.append(access_token);
        String contentType = "application/x-www-form-urlencoded";
        String url = sb.toString();
        System.out.println("url:" + url);
        Map<String, Object> param = new HashMap();
        param.put("type", "application");
        param.put("title", title);
        param.put("content", contents);
        param.put("appid", APP_ID);
        param.put("redirect", detailUrl);
        HashMap operation;
        if (countTodu.length() > 0) {
            Object[] operations = new Object[1];
            operation = new HashMap();
            operation.put("type", "update_todo_count");
            operation.put("value", countTodu);
            operations[0] = operation;
            param.put("operations", operations);
        }

        String str = JSON.toJSONString(param);
        System.out.println("内容详情：" + str);
        operation = null;

        try {
            String data = "content=" + URLEncoder.encode(str, "UTF-8");
            String result = HttpUtilInstance.getInstance().sendHttpPost(url, data, contentType);
            System.out.println("返回结果：" + result);
        } catch (Exception var13) {
            var13.printStackTrace();
        }

    }

    public static void addOrg(DepartmentPo org) {
        System.err.println(222222222);
        System.err.println(org.getDeptId());
        String access_token = getAccessToken();
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("/api/org/department?access_token=");
        sb.append(access_token);
        String contentType = "application/json";
        String url = sb.toString();
        JSONObject object = new JSONObject();
        object.put("organId", org.getDeptId());
        object.put("code", org.getDeptId());
        object.put("organName", org.getName());
        object.put("fatherId", org.getParentId());
        object.put("orderId", org.getOrderId());
        object.put("isdelete", 0);
        object.put("timestamp", System.currentTimeMillis());
        String data = object.toJSONString();
        System.out.println("待新增部门详情：" + data);

        try {
            String result = HttpUtilInstance.getInstance().sendHttpPost(url, data, contentType);
            System.out.println("返回结果：" + result);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    public static List<DepartmentPo> getDeptsList(String deptid) {
        String invalid = "invalid";
        String sublevel = "sublevel";
        String access_token = getAccessToken();
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("/api/org/department/");
        sb.append(deptid);
        sb.append("/subdepartments");
        sb.append("?" + invalid);
        sb.append("&" + sublevel);
        sb.append("&access_token=" + access_token);
        String url = sb.toString();
        String depts = "";

        try {
            depts = HttpHelper.getRequest(url, new HashMap());
        } catch (Exception var14) {
            var14.printStackTrace();
        }

        List<Object> jsonObj = JSONObject.parseArray(depts, Object.class);
        List<DepartmentPo> deptDomainList = new ArrayList();
        if (null != jsonObj && jsonObj.size() > 0) {
            Iterator var9 = jsonObj.iterator();

            while(var9.hasNext()) {
                Object obj = var9.next();
                String objString = obj.toString();
                JSONObject json = JSONObject.parseObject(objString);
                DepartmentPo dept = new DepartmentPo();
                dept.setDeptId(json.getString("organId"));
                dept.setName(json.getString("organName"));
                dept.setParentId(json.getString("fatherId"));
                deptDomainList.add(dept);
            }
        }

        return deptDomainList;
    }

    public static DepartmentPo getDeptInfo(String deptid) {
        String access_token = getAccessToken();
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("/api/org/department/");
        sb.append(deptid);
        sb.append("?access_token=" + access_token);
        String url = sb.toString();
        String dept = "";

        try {
            dept = HttpHelper.getRequest(url, new HashMap());
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        JSONObject json = JSONObject.parseObject(dept);
        DepartmentPo deptDomain = new DepartmentPo();
        deptDomain.setDeptId(json.getString("organId"));
        deptDomain.setName(json.getString("organName"));
        deptDomain.setParentId(json.getString("fatherId"));
        return deptDomain;
    }

    public static void updOrg(DepartmentPo org) {
        String access_token = getAccessToken();
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("/api/org/department/");
        sb.append(org.getDeptId());
        sb.append("?access_token=");
        sb.append(access_token);
        String contentType = "application/json";
        String url = sb.toString();
        JSONObject object = new JSONObject();
        object.put("organdId", org.getDeptId());
        object.put("code", org.getDeptId());
        object.put("organName", org.getName());
        object.put("fatherId", org.getParentId());
        object.put("orderId", org.getOrderId());
        object.put("isdelete", 0);
        String data = object.toJSONString();
        System.out.println("待更新部门详情" + data);

        try {
            String result = HttpUtilInstance.getInstance().sendHttpPut(url, data, contentType);
            System.out.println("返回结果：" + result);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    public static void delOrg(String departmentid) {
        String access_token = getAccessToken();
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("/api/org/department/");
        sb.append(departmentid);
        sb.append("?access_token=");
        sb.append(access_token);
        String url = sb.toString();
        System.out.println("待删除机构id：" + departmentid);

        try {
            String result = HttpUtilInstance.getInstance().sendHttpDelete(url);
            System.out.println("返回结果：" + result);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static void addUser(UserPo user, String password) {
        String access_token = getAccessToken();
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("/api/org/userinfo?access_token=");
        sb.append(access_token);
        String contentType = "application/json";
        String url = sb.toString();
        JSONObject object = new JSONObject();
        object.put("account", user.getAccount());
        object.put("fullname", user.getUserName());
        object.put("mobile", user.getTelephone());
        object.put("isManager", 0);
        object.put("organId", user.getDepId());
        object.put("password", password);
        object.put("secLevel", "4");
        object.put("sex", 1);
        object.put("tel", "13113113113");
        object.put("useremail", user.getMail());
        object.put("orderId", user.getOrderId());
        object.put("isDelete", 0);
        object.put("failedLoginCount", 0);
        object.put("userid", user.getUserId());
        String data = object.toJSONString();
        System.out.println("待新增人员详情：" + data);

        try {
            String result = HttpUtilInstance.getInstance().sendHttpPost(url, data, contentType);
            System.out.println("返回结果：" + result);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    public static List<UserPo> getUsersList(String deptid) {
        String access_token = getAccessToken();
        String userInfos = null;
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("/api/org/department/");
        sb.append(deptid);
        sb.append("/userinfos?");
        sb.append("&access_token=" + access_token);
        String url = sb.toString();

        try {
            userInfos = HttpHelper.getRequest(url, new HashMap());
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        List<Object> usersJson = JSONObject.parseArray(userInfos, Object.class);
        List<UserPo> users = new ArrayList();
        if (null != usersJson && usersJson.size() > 0) {
            Iterator var7 = usersJson.iterator();

            while(var7.hasNext()) {
                Object obj = var7.next();
                String objString = obj.toString();
                JSONObject json = JSONObject.parseObject(objString);
                UserPo user = new UserPo();
                user.setUserId(json.getString("userid"));
                user.setUserName(json.getString("fullname"));
                user.setDepId(json.getString("organId"));
                user.setAccount(json.getString("account"));
                user.setPassword(json.getString("password"));
                user.setMail(json.getString("useremail"));
                users.add(user);
            }
        }

        return users;
    }

    public static void rePasswordUser(UserPo user, String repassword) {
        String access_token = getAccessToken();
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("api/sso/resetpwd?access_token=");
        sb.append(access_token);
        String contentType = "application/json";
        String url = sb.toString();
        JSONObject object = new JSONObject();
        object.put("account", user.getAccount());
        object.put("password", repassword);
        String data = object.toJSONString();

        try {
            String result = HttpUtilInstance.getInstance().sendHttpPost(url, data, contentType);
            System.out.println("返回结果：" + result);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    public static void changePwd(String account, String oldPassword, String newPassword, String repassword) {
        String access_token = getAccessToken();
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("api/sso/password");
        String contentType = "application/x-www-form-urlencoded";
        String url = sb.toString();
        JSONObject object = new JSONObject();
        object.put("account", account);
        object.put("oldpassword", oldPassword);
        object.put("newpassword", newPassword);
        object.put("repassword", repassword);
        String data = object.toJSONString();

        try {
            String result = HttpUtilInstance.getInstance().sendHttpPost(url, data, contentType);
            System.out.println("返回结果：" + result);
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

    public static void updUser(UserPo user) {
        String access_token = getAccessToken();
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("/api/org/userinfo/");
        sb.append(user.getUserId());
        sb.append("?access_token=");
        sb.append(access_token);
        String contentType = "application/json";
        String url = sb.toString();
        JSONObject object = new JSONObject();
        object.put("fullname", user.getUserName());
        object.put("organId", user.getDepId());
        object.put("useremail", user.getMail());
        object.put("sex", 0);
        object.put("failedLoginCount", 0);
        String data = object.toJSONString();
        System.out.println("待修改人员详情：" + data);

        try {
            String result = HttpUtilInstance.getInstance().sendHttpPut(url, data, contentType);
            System.out.println("返回结果：" + result);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    public static void delUser(String userid) {
        String access_token = getAccessToken();
        StringBuffer sb = new StringBuffer();
        sb.append(CECOA_MSG_URL);
        sb.append("/api/org/userinfo/");
        sb.append(userid);
        sb.append("?access_token=");
        sb.append(access_token);
        String url = sb.toString();
        System.out.println("待删除人员id：" + userid);

        try {
            String result = HttpUtilInstance.getInstance().sendHttpDelete(url);
            System.out.println("返回结果：" + result);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static String getIp(HttpServletRequest request) {
        String client_ip = request.getHeader("client_ip");
        System.out.println("client_ip-------------------------" + client_ip);
        if (client_ip == null || "".equals(client_ip)) {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            client_ip = ip;
        }

        return client_ip;
    }
}

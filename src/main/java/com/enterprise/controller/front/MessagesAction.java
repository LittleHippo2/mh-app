package com.enterprise.controller.front;


import com.alibaba.fastjson.JSONObject;
import com.enterprise.cache.FrontCache;
import com.enterprise.entity.Messages;
import com.enterprise.util.HttpUtil;
import com.enterprise.util.RequestHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enterprise.service.MessageService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 前台留言
 *
 * @author Cesiumai
 */
@Controller("frontMessageController")
@RequestMapping("/")
public class MessagesAction {

    @Value("#{config['api.url']}")
    private String URL;
    @Value("#{config['api.appid']}")
    private String APPID;
    @Value("#{config['api.secret']}")
    private String SECRET;
    @Autowired
    private MessageService messageService;
    @Autowired
    private FrontCache frontCache;


    @RequestMapping("message")
    public String message() {
        return "/front/message/message";
    }

    /**
     * 前台留言
     *
     * @param e
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("message/leaveMessage")
    @ResponseBody
    public String leaveMessage(Messages e, ModelMap model) throws Exception {

        //发送消息

        JSONObject messageJson = new JSONObject();
        messageJson.put("appid",APPID);
        messageJson.put("type","application");
        messageJson.put("redirect","/manage/message/selectList?init=y");
        messageJson.put("title","您有新的消息!");
        messageJson.put("content","您有新的留言，请注意查收！");
        //{\"appid\":\"legacycpk.csse.cms\",\"type\":\"application\",\"redirect\": \"/manage/message/selectList?init=y\",\"title\":\"您有新的消息\",\"content\":\"您有新的留言，请注意查收！\",\"display\":{\"system\":true,\"notification\":true,\"msgbox\":true}}";//

        String content = messageJson.toString();
        String userid = "342189d5-d5ea-458b-9928-fb91588dfe81,6b673e2c-6cb4-4ca3-b3f3-a174b4ab3e0a,d7abdc88-5204-40ef-860c-b1df2d4e75c9";
        JSONObject result = new JSONObject();
        Map inParam = new HashMap<String,Object>();
        //inParam.put("userid",userid);
        inParam.put("access_token",getToken());
        JSONObject jsonObject ;
        //jsonObject = JSONObject.parseObject(content);
        System.out.println(content);
        inParam.put("content",content);
        String url = URL+"/api/msg/message/user/"+userid;
        // url = URLEncoder.encode(url,"utf-8");
        System.out.println(url);
        // String request = HttpUtil.post(url,inParam,3000,3000);
        String request = HttpUtil.postMsg(url,inParam,3000,3000);
//            log.info("result:"+request);
        result = JSONObject.parseObject(request);
        System.out.printf(result.toString());

        messageService.insert(e);
        frontCache.loadMessage();
        return "ok";
    }
    @RequestMapping("checkVcode")
    @ResponseBody
    public String checkVcode(@ModelAttribute("e") Messages e, HttpServletResponse response) throws IOException {
        String vcode = RequestHolder.getSession().getAttribute("validateCode").toString();
        if(StringUtils.isNotBlank(vcode)&&!(vcode.toLowerCase()).equals(e.getVcode().toLowerCase())){
            return "{\"error\":\"验证码不正确!\"}";
        }
        return null;
    }
    public String getToken() {
        JSONObject result = new JSONObject();
        String access_token = "";
        Map inParam = new HashMap<String,Object>();
        inParam.put("client_id",APPID);
        inParam.put("client_secret",SECRET);
        inParam.put("grant_type","client_credentials");//目前grant_type 仅支持 client_credentials
        try{
            String request = HttpUtil.post(URL+"/api/uaa/oauth/token",inParam,3000,3000);
            result = JSONObject.parseObject(request);
            JSONObject jsonObj = JSONObject.parseObject(request);
            String key = "access_token";

            if (request != null && request.length() > 0) {
                access_token = jsonObj.getString(key);
            } else {
                access_token = "获取微服务token失败！";
            }

        }catch (Exception e){
            e.printStackTrace();
//            log.error(e.getMessage());
        }
        return access_token;
    }

}

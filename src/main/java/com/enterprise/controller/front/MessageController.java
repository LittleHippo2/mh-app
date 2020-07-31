package com.enterprise.controller.front;

import com.alibaba.fastjson.JSONObject;

import com.enterprise.util.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller("messageController")
public class MessageController {

    @Value("#{config['api.url']}")
    private String URL;


//    @GetMapping
//    public String message() {
//        return "admin/admin_message";
//    }

    @RequestMapping(value = "/sendPerson")
    @ResponseBody
    public JSONObject sendOnce(String access_token, String userid, String content){
        JSONObject result = new JSONObject();
        Map inParam = new HashMap<String,Object>();
        //inParam.put("userid",userid);
        inParam.put("access_token",access_token);
        JSONObject jsonObject ;
        try{
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
        }catch (Exception e){
//            log.error(e.getMessage());
            result.put("result","error");
        }
        return result;
    }

    @RequestMapping(value = "/sendDepartment")
    @ResponseBody
    public JSONObject sendDepartment(String access_token,String content,String departmentid){
        JSONObject result = new JSONObject();
        Map inParam = new HashMap<String,Object>();
        inParam.put("access_token",access_token);
        JSONObject jsonObject;
        try{
            jsonObject = JSONObject.parseObject(content);
            inParam.put("content",jsonObject);
            String url = URL+"/api/msg/message/department/"+departmentid;
            String request = HttpUtil.post(url,inParam,3000,3000);
//            log.info("result:"+request);
            result = JSONObject.parseObject(request);
        }catch (Exception e){
//            log.error(e.getMessage());
            result.put("result","error");
        }
        return result;
    }
}

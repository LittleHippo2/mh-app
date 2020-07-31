package com.enterprise.controller.front;

import com.alibaba.fastjson.JSONObject;

import com.enterprise.util.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("logController")
public class LogController {

    @Value("#{config['api.url']}")
    private String URL;
    /**
     * 添加日志
     * @return
     */
    @RequestMapping(value = "/addlog" ,method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addlog(String access_token,String content){
        JSONObject result = new JSONObject();
        try{
            URL = URL+ "/api/log/addlog?access_token="+access_token;
            String request = HttpUtil.post(URL,content,3000,3000);
//            log.info("result:"+request);
            result = JSONObject.parseObject(request);
        }catch (Exception e){
//            log.error(e.getMessage());
            result.put("error","error");
        }
        return result;
    }


    /**
     * 根据用户名审计应用日志
     * @param useraccount
     * @return
     */
    @RequestMapping(value = "/getlogs")
    @ResponseBody
    public JSONObject getlogs(String access_token,String useraccount,String starttime,String endtime){
        JSONObject result = new JSONObject();
        URL = URL+"api/log/" + useraccount+"/getlogs";
        Map inParam = new HashMap<String,Object>();
        inParam.put("access_token",access_token);
        inParam.put("starttime",starttime);
        inParam.put("endtime",endtime);
        try{
            String request = HttpUtil.get(URL,inParam,3000,3000);
            result = JSONObject.parseObject(request);
//            log.info("result:"+request);
        }catch (Exception e){
//            log.error(e.getMessage());
            result.put("error","error");
        }
        return result;
    }

    /**
     * 根据应用标识审计应用日志
     * @param appid
     * @return
     */
    @RequestMapping(value = "/getloginfo")
    @ResponseBody
    public JSONObject getloginfo(String access_token,String appid,String starttime,String endtime){
        JSONObject result = new JSONObject();
        URL = URL+"api/log/" +appid+"/getloginfo";
        Map inParam = new HashMap<String,Object>();
        inParam.put("access_token",access_token);
        inParam.put("starttime",starttime);
        inParam.put("endtime",endtime);
        try{
            String request = HttpUtil.get(URL,inParam,3000,3000);
//            log.info("result:"+request);
            result = JSONObject.parseObject(request);
        }catch (Exception e){
//            log.error(e.getMessage());
            result.put("error","error");
        }
        return result;
    }

    /**
     * 根据应用标识审计平台日志
     * @param appid
     * @return
     */
    @RequestMapping(value = "/getplatformlogs")
    @ResponseBody
    public JSONObject getplatformlogs(String access_token,String appid){
        JSONObject result = new JSONObject();
        URL = URL+"api/log/"+appid+"/getplatformlogs";
        Map inParam = new HashMap<String,Object>();
        inParam.put("access_token",access_token);
        inParam.put("starttime","1470154736000");
        inParam.put("endtime","1470154836000");
        inParam.put("limit","10");
        inParam.put("page","1");
        try{
            String request = HttpUtil.get(URL,inParam,3000,3000);
//            log.info("result:"+request);
            result = JSONObject.parseObject(request);
        }catch (Exception e){
//            log.error(e.getMessage());
            result.put("error","error");
        }
        return result;
    }


}

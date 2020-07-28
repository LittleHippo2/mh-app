package com.enterprise.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.enterprise.entity.ResultMap;
import com.enterprise.util.HttpUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl {

    private static final Logger logger = LogManager.getLogger(TokenServiceImpl.class);

    @Value("#{config['api.ip']}")
    private String apiIp;

    @Value("#{config['api.oauth.token']}")
    private String authToken;

    public  ResultMap getAccessToken(String clientId, String clientSecret){

        ResultMap resultMap = new ResultMap();
        String url =apiIp+authToken;

        Map inParam = new HashMap<String,Object>();
        inParam.put("client_id",clientId);
        inParam.put("client_secret",clientSecret);
        inParam.put("grant_type","client_credentials");//目前grant_type 仅支持 client_credentials
        try{
            String request = HttpUtil.post(url,inParam,3000,3000);
            resultMap.setData(request);
            logger.info("result:"+request);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return resultMap;
    }


}

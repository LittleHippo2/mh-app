package com.enterprise.task;

import com.alibaba.fastjson.JSONObject;
import com.enterprise.entity.ResultMap;
import com.enterprise.service.DepartmentService;
import com.enterprise.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskJob")
public class TaskJob {

    @Value("#{config['api.appid']}")
    private String appid;

    @Value("#{config['api.secret']}")
    private String secret;

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private DepartmentService departmentService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void job(){

//        ResultMap resultMap = tokenService.getAccessToken(appid, secret);
//
//        JSONObject result = JSONObject.parseObject(resultMap.getData().toString());
//        String token = result.getString("access_token");
//
//        try {
//            departmentService.syncDeptAndUser(token);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }
}

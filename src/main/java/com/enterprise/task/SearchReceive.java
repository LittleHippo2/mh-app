package com.enterprise.task;

import com.alibaba.fastjson.JSONObject;
import com.enterprise.entity.ResultMap;
import com.enterprise.service.DepartmentService;
import com.enterprise.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
public class SearchReceive implements ApplicationListener<ContextRefreshedEvent> {

    @Value("#{config['api.appid']}")
    private String appid;

    @Value("#{config['api.secret']}")
    private String secret;

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {//保证只执行一次
            ResultMap resultMap = tokenService.getAccessToken(appid, secret);
//            if(resultMap.getData().toString() != null){
//                JSONObject result = JSONObject.parseObject(resultMap.getData().toString());
//                String token = result.getString("access_token");
//                try {
//                    //departmentService.truncateTable();
//                    //departmentService.updateTime((long)1);
//                    departmentService.syncDeptAndUser(token);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }

    }
}

package com.enterprise.service.impl;

import com.enterprise.entity.ResultMap;
import com.enterprise.service.DepartmentService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);


    @Value("#{config['api.ip']}")
    private String apiIp;

    @Value("#{config['api.sync.dept']}")
    private String apiSyncDept;


    /**
     *
     * @param token 微服务token
     * @return
     */
    @Override
    public ResultMap syncDeptAndUser(String token) {
        ResultMap resultMap = new ResultMap();
        //方法开始时间
        String startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        resultMap.setStartTime(startTime);
        long startTime1 = System.currentTimeMillis();







        //方法结束时间
        long endTime2 =  System.currentTimeMillis();
        String endTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //resultMap.setEndTime();
        resultMap.setTimeConsuming((endTime2-startTime1)+"ms");
        return null;
    }
}

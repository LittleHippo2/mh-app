package com.enterprise.util;

import com.enterprise.entity.LogPo;
import com.enterprise.service.LogService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class LogUtil {

    private static final Logger logger = LogManager.getLogger(LogUtil.class);

    @Resource
    private LogService logService;

    public static LogUtil logUtil;

    @PostConstruct
    public void init() {
        logUtil = this;
    }

    public void addLog(String param, String result, long beginTime, long endTime, long time, String status) {
        LogPo logPo = new LogPo();
        logPo.setParam(param);
        logPo.setResult(result);
        logPo.setbeginTime(beginTime);
        logPo.setEndTime(endTime);
        logPo.setTime(time);
        logPo.setStatus(status);
        try {
            logUtil.logService.addLog(logPo);
            logger.info("日志添加成功");
        } catch (Exception e) {
            logger.info("日志添加失败:",e);
        }
    }
}

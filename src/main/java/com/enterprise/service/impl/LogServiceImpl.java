package com.enterprise.service.impl;

import com.enterprise.dao.LogDao;
import com.enterprise.dao.ServersManage;
import com.enterprise.entity.LogPo;
import com.enterprise.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("LogService")
public class LogServiceImpl extends ServersManage<LogPo, LogDao> implements LogService {
    @Resource(name = "logDao")
    public void setDao(LogDao logDao) {
        this.dao = logDao;
    }

    @Override
    public void addLog(LogPo logPo) {
        dao.addLog(logPo);
    }
}

package com.enterprise.service;

import com.enterprise.entity.LogPo;

public interface LogService extends Services<LogPo>{
    void addLog(LogPo logPo);
}

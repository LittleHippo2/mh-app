package com.enterprise.dao;


import com.enterprise.entity.LogPo;

public interface LogDao extends DaoManage<LogPo>{

    void addLog(LogPo logPo);
}

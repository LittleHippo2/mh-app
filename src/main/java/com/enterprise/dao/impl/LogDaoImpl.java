package com.enterprise.dao.impl;

import com.enterprise.dao.BaseDao;
import com.enterprise.dao.LogDao;
import com.enterprise.entity.LogPo;
import com.enterprise.entity.page.PageModel;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("LogDao")
public class LogDaoImpl implements LogDao {

    @Resource
    private BaseDao dao;

    @Override
    public void addLog(LogPo logPo) {
        dao.insert("addLog", logPo);
    }

    @Override
    public int insert(LogPo logPo) {
        return 0;
    }

    @Override
    public int delete(LogPo logPo) {
        return 0;
    }

    @Override
    public int update(LogPo logPo) {
        return 0;
    }

    @Override
    public LogPo selectOne(LogPo logPo) {
        return null;
    }

    @Override
    public PageModel selectPageList(LogPo logPo) {
        return null;
    }

    @Override
    public List<LogPo> selectList(LogPo logPo) {
        return null;
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public LogPo selectById(int id) {
        return null;
    }
}

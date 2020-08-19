package com.enterprise.dao.impl;

import com.enterprise.dao.BaseDao;
import com.enterprise.dao.LogDao;
import com.enterprise.entity.LogPo;
import com.enterprise.entity.Service;
import com.enterprise.entity.page.PageModel;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("logDao")
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
        return (LogPo) dao.selectOne("logPo.selectOne",logPo);
    }

    @Override
    public PageModel selectPageList(LogPo logPo) {
        return dao.selectPageList("logPo.selectPageList","logPo.selectPageCount",logPo);
    }

    @Override
    public List<LogPo> selectList(LogPo logPo) {
        return dao.selectList("logPo.selectList",logPo);
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public LogPo selectById(int id) {
        return (LogPo) dao.selectOne("logPo.selectById",id);
    }
}

package com.enterprise.dao.impl;

import com.enterprise.dao.BaseDao;
import com.enterprise.dao.DepartmentDao;
import com.enterprise.entity.department.DepartmentPo;
import com.enterprise.entity.department.UserPo;
import com.enterprise.entity.page.PageModel;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao {

    @Resource
    private BaseDao dao;

    public void setDao(BaseDao dao) {
        this.dao = dao;
    }


    @Override
    public List<DepartmentPo> selectDepartment(Integer page, Integer size, String parentDeptId) {
        return dao.selectList("selectDepartment");
    }

    @Override
    public List<UserPo> selectUser(Integer page, Integer size, String deptId) {
        return dao.selectList("selectUser");
    }

    @Override
    public void updateDepartment(DepartmentPo departmentPo) {
        dao.update("updateDepartment", departmentPo);
    }

    @Override
    public void updateUser(UserPo userPo) {
        dao.insert("updateUser", userPo);
    }
//
//    @Override
//    public Integer insertDepartment(List<DepartmentPo> list) {
//        return dao.insertDepartment(list);
//    }
//
//    @Override
//    public Integer insertUser(List<UserPo> list) {
//        return dao.insertUser(list);
//    }

    @Override
    public void insertDeptByOne(DepartmentPo departmentPo) {
        dao.insert("insertDeptByOne", departmentPo);
    }

    @Override
    public void insertUserByOne(UserPo userPo) {
        dao.insert("insertUserByOne", userPo);
    }

    @Override
    public long getTimestamp() {
        return (long) dao.selectOne("getTimestamp");
    }

    @Override
    public void updateTimestamp(Long timestamp) {
        dao.update("updateTimestamp", timestamp);
    }


    @Override
    public int insert(DepartmentPo departmentPo) {
        return 0;
    }

    @Override
    public int delete(DepartmentPo departmentPo) {
        return 0;
    }

    @Override
    public int update(DepartmentPo departmentPo) {
        return 0;
    }

    @Override
    public DepartmentPo selectOne(DepartmentPo departmentPo) {
        return null;
    }

    @Override
    public PageModel selectPageList(DepartmentPo departmentPo) {
        return null;
    }

    @Override
    public List<DepartmentPo> selectList(DepartmentPo departmentPo) {
        return null;
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public DepartmentPo selectById(int id) {
        return null;
    }
}
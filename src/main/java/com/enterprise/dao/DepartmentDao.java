package com.enterprise.dao;

import com.enterprise.entity.department.DepartmentPo;
import com.enterprise.entity.department.UserPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 处理组织机构和用户同步的接口
 */

public interface DepartmentDao extends DaoManage<DepartmentPo> {

    List<DepartmentPo> selectDepartment(@Param("page") Integer page, @Param("size") Integer size, @Param("parentDeptId") String parentDeptId);

    List<UserPo> selectUser(@Param("page") Integer page, @Param("size") Integer size, @Param("deptId") String deptId);

    void updateDepartment(DepartmentPo departmentPo);

    void updateUser( UserPo userPo);

//    Integer insertDepartment(@Param("list") List<DepartmentPo> list);
//
//    Integer insertUser(@Param("list") List<UserPo> list);

    void insertDeptByOne( DepartmentPo departmentPo);

    void insertUserByOne( UserPo userPo);

    //获取最新的时间戳
    long getTimestamp();

    //修改时间戳
    void updateTimestamp(@Param("timestamp") Long timestamp);



}

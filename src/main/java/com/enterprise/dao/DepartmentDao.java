package com.enterprise.dao;

import com.enterprise.entity.User;
import com.enterprise.entity.department.DepartmentPo;
import com.enterprise.entity.department.PageUtils;
import com.enterprise.entity.department.UserPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 处理组织机构和用户同步的接口
 */

public interface DepartmentDao extends DaoManage<DepartmentPo> {

    List<DepartmentPo> selectDepartment(PageUtils pageUtils);

    Integer countDept(String fatherId);

    Integer countUser(String deptId);

    List<UserPo> selectUser(PageUtils pageUtils);

    List<DepartmentPo> selectDeptList();

    void updateDepartment(@Param("list")List<DepartmentPo> list);

    void updateUser( @Param("list")List<UserPo> list);

    void insertDepartment(@Param("list") List<DepartmentPo> list);

    void insertUser(@Param("list") List<UserPo> list);

    void insertDeptByOne( DepartmentPo departmentPo);

    void insertUserByOne( UserPo userPo);

    //获取最新的时间戳
    long getTimestamp();

    //修改时间戳
    void updateTimestamp(@Param("timestamp") Long timestamp);

    void truncateTable();

    //向user表中插入数据
    void insertTUser(@Param("list")List<User> list);

    UserPo selectUserByAccount(String account);



}

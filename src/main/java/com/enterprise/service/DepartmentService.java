package com.enterprise.service;

import com.enterprise.entity.ResultMap;
import com.enterprise.entity.department.DepartmentPo;

import javax.servlet.http.HttpServletRequest;

public interface DepartmentService extends Services<DepartmentPo> {

    ResultMap syncDeptAndUser(String token) throws Exception;

    ResultMap getDeptList();

    ResultMap selectDeptList(Integer page, Integer limit, String fatherId);

    ResultMap selectUserList(Integer page, Integer limit, String deptId );

    ResultMap selectCsseDeptList(String token, String fatherId,  String invalid);

    ResultMap selectCsseDeptListInfo(Integer page, Integer limit, String token, String fatherId,String invalid);

    ResultMap selectDeptByDeptId(HttpServletRequest request,String token, String deptId);

    ResultMap selectCsseUserList(Integer page, Integer limit, String token, String deptId);

    ResultMap selectCsseUserInfo(HttpServletRequest request, String token, String userId, String account);

    void truncateTable();

    void updateTime(Long time);

    ResultMap test(String token);

    ResultMap selectUserByAccount(String account);
}

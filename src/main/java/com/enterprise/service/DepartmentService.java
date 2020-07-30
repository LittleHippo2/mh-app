package com.enterprise.service;

import com.enterprise.entity.ResultMap;
import com.enterprise.entity.department.DepartmentPo;

public interface DepartmentService extends Services<DepartmentPo> {

    ResultMap syncDeptAndUser(String token) throws Exception;

    ResultMap getDeptList();

    ResultMap selectDeptList(Integer page, Integer limit, String fatherId);

    ResultMap selectUserList(Integer page, Integer limit, String deptId );
}

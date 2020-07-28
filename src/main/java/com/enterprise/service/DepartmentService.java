package com.enterprise.service;

import com.enterprise.entity.ResultMap;
import com.enterprise.entity.department.DepartmentPo;

public interface DepartmentService extends Services<DepartmentPo> {

    ResultMap syncDeptAndUser(String token) throws Exception;
}
